package libraryapp.interfaces;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.transaction.TransactionConfiguration;

@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
@TransactionConfiguration(defaultRollback=true)
public abstract class AbstractBorrowingServiceFacadeTests {

	@Autowired
	protected BorrowingServiceFacade service;

	@PersistenceUnit
	protected EntityManagerFactory entityManagerFactory;
	protected EntityManager entityManager;
	
	protected String johnAccountId; // department head
	protected String sallyAccountId; // undergraduate student
	protected String patAccountId; // graduate student

	@Before
	public void setUp() throws Exception {
		assertNotNull(
				"Please provide a service implementation", service);
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		try {
			setUpBooksAndCopies();
			setUpMemberCategories();
			setUpLoanPeriods();
			setUpMemberAccounts();
			entityManager.getTransaction().commit();
		} catch(Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	protected abstract void setUpBooksAndCopies();

	protected abstract void setUpMemberCategories();

	protected abstract void setUpLoanPeriods();

	protected abstract void setUpMemberAccounts();

	@After
	public void tearDown() throws Exception {
	}

	protected Calendar todayAsCalendar() {
		Calendar now = Calendar.getInstance();
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.clear(Calendar.MINUTE);
		now.clear(Calendar.SECOND);
		now.clear(Calendar.MILLISECOND);
		return now;
	}

	protected Date today() {
		Calendar now = todayAsCalendar();
		return now.getTime();
	}

	protected Date todayPlus(int days) {
		Calendar now = todayAsCalendar();
		now.add(Calendar.DATE, days);
		return now.getTime();
	}

	@Test
	public void borrowBookCopyAndReturn() throws Exception {
		String barcode = "9780593075005-1"; // Inferno by Dan Brown

		// TODO Comment out line below and make test pass
		// fail("Not yet implemented");
		BorrowReceipt receipt = service.borrowBook(barcode, sallyAccountId);
		assertEquals("Inferno", receipt.getTitle());
		assertEquals(barcode, receipt.getBarcode());
		assertEquals(today(), receipt.getBorrowDate());
		assertEquals(
				"Sally (an undergraduate student) can borrow novels for up to 10 days",
				todayPlus(10), receipt.getDueDate());
		Collection<BookLoanStatus> bookLoans = service.getBookLoanStatus(sallyAccountId);
		assertEquals("After borrowing, number of loans should be one",
				1, bookLoans.size());

		service.returnBook(barcode, sallyAccountId);
		bookLoans = service.getBookLoanStatus(sallyAccountId);
		assertEquals("After returning, number of loans should be zero",
				0, bookLoans.size());
	}
	
	@Test
	public void borrowBookCopiesWithDifferentLoanPeriods() throws Exception {
		// TODO Comment out line below and make test pass
		// fail("Not yet implemented");
		String barcodeNovel = "9780593075005-1"; // Inferno by Dan Brown
		String barcodeReferenceBook = "9783125341470-1"; // Advanced Grammar in Use by Martin Hewings
		BorrowReceipt receipt;
		receipt = service.borrowBook(barcodeNovel, sallyAccountId);
		assertEquals(
				"Sally (an undergraduate student) can borrow novels for up to 10 days",
				todayPlus(10), receipt.getDueDate());
		receipt = service.borrowBook(barcodeReferenceBook, sallyAccountId);
		assertEquals(
				"Sally (an undergraduate student) can borrow reference books for up to 15 days",
				todayPlus(15), receipt.getDueDate());
	}

	@Test
	public void cannotBorrowBeyondLimit() throws Exception {
		// TODO Comment out line below and make test pass
		// fail("Not yet implemented");
		// Given a graduate student (Pat) account
		// with two borrowed/loaned books...
		String barcode1 = "9780552170024-1"; // The Lost Symbol by Dan Brown
		String barcode2 = "9780593075005-2"; // Inferno by Dan Brown
		service.borrowBook(barcode1, patAccountId);
		service.borrowBook(barcode2, patAccountId);
		Collection<BookLoanStatus> bookLoans = service.getBookLoanStatus(patAccountId);
		assertEquals(2, bookLoans.size());

		// ... try borrowing a third one (Advanced Grammar in Use by Martin Hewings)
		try {
			service.borrowBook("9783125341470-1", patAccountId);
			fail("Should have thrown exception and prevent book from being borrowed");
		} catch (Exception e) {
			// Pat (a graduate student) can only borrow up to 2 books.
			// expected, pass!
			bookLoans = service.getBookLoanStatus(patAccountId);
			assertEquals(2, bookLoans.size());
		}
	}

	@Test
	public void cannotBorrowAlreadyBorrowedBook() throws Exception {
		// TODO Comment out line below and make test pass
		// fail("Not yet implemented");
		String barcode = "9780552170024-1"; // The Lost Symbol by Dan Brown
		service.borrowBook(barcode, patAccountId);
		try {
			service.borrowBook(barcode, patAccountId);
			fail("Should have thrown exception, as the book has already been borrowed");
		} catch (Exception e) {
			// expected, pass!
		}
	}

	@Test
	public void cannotBorrowCertainBookCategories() throws Exception {
		// TODO Comment out line below and make test pass
		// fail("Not yet implemented");
		// Department head (John) cannot borrow novels
		String barcode = "9780552170024-1"; // The Lost Symbol by Dan Brown
		try {
			service.borrowBook(barcode, johnAccountId);
			fail("Should have thrown exception and prevent book from being borrowed");
		} catch (Exception e) {
			// expected, pass!
			Collection<BookLoanStatus> bookLoans = service.getBookLoanStatus(johnAccountId);
			assertEquals(0, bookLoans.size());
		}
	}

	private static class BorrowBookCommand implements Runnable {
		private final String barcode;
		private final String memberAccountId;
		private final BorrowingServiceFacade service;

		public BorrowBookCommand(
				String barcode, String memberAccountId, BorrowingServiceFacade service) {
			this.barcode = barcode;
			this.memberAccountId = memberAccountId;
			this.service = service;
		}
		
		public void execute() {
			service.borrowBook(barcode, memberAccountId);
		}

		@Override
		public void run() {
			execute();
		}
	}

	@Test
	public void preventConcurrentHacks() throws Exception {
		// TODO Comment out line below and make test pass
		// fail("Not yet implemented");
		String barcode1 = "9780552170024-1"; // The Lost Symbol by Dan Brown
		String barcode2 = "9780593075005-2"; // Inferno by Dan Brown
		String barcode3 = "9780373696062-1"; // A Wanted Man by Lee Child

		// Launch threads simultaneously borrowing different books
		Thread t1 = new Thread(new BorrowBookCommand(
				barcode1, patAccountId, service),
				"Pat borrowing " + barcode1);
		Thread t2 = new Thread(new BorrowBookCommand(
				barcode2, patAccountId, service),
				"Pat borrowing " + barcode2);
		Thread t3 = new Thread(new BorrowBookCommand(
				barcode3, patAccountId, service),
				"Pat borrowing " + barcode3);

		// In the case of Pat (graduate student), this should still result
		// in an exception, and prevent Pat from exceeding the maximum
		// number of books borrowed/checked-out.
		t1.start();
		t2.start();
		t3.start();
		t1.join();
		t2.join();
		t3.join();

		Collection<BookLoanStatus> bookLoans = service.getBookLoanStatus(patAccountId);
		assertEquals(
				"Pat (a graduate student) is allowed to borrow up to 2 books",
				2, bookLoans.size());
	}

}
