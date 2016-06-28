package libraryapp.interfaces;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.transaction.TransactionConfiguration;

@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
@TransactionConfiguration(defaultRollback=true)
public abstract class AbstractReservationAwareBorrowingServiceFacadeTests
		extends AbstractBorrowingServiceFacadeTests {

	@Test
	public void reserveBook() throws Exception {
		String isbn13 = "9783125341470"; // Advanced Grammar in Use by Martin Hewings
		String barcode = "9783125341470-1";

		// TODO Comment out line below and make test pass
		// fail("Not yet implemented");
		// Borrow the one (1) and only available copy of the book
		service.borrowBook(barcode, sallyAccountId);

		// Now, John has no available copies to borrow. So, John reserves it.
		ReservationReceipt receipt =
				service.reserveBook(isbn13, johnAccountId);
		assertThat(receipt,
				allOf(hasProperty("title", equalTo("Advanced Grammar in Use")),
						hasProperty("isbn13", equalTo("9783125341470")),
						hasProperty("isbn10", equalTo("3125341477"))));

		// Sally returns the borrowed copy
		service.returnBook(barcode, sallyAccountId);

		// Pat cannot borrow it, since John has it reserved.
		try {
			service.borrowBook(barcode, patAccountId);
			fail("Pat should not be able to borrow, since John has it reserved");
		} catch (Exception e) {
			// expected, pass!
		}

		// John can now borrow, since he reserved it.
		service.borrowBook(barcode, johnAccountId);
		
		service.returnBook(barcode, johnAccountId);

		// After John returns it, Pat can now borrow
		// (since John's reservation has been satisfied).
		try {
			service.borrowBook(barcode, patAccountId);
		} catch (Exception e) {
			fail("Pat should be able to borrow, after John has returned the book."
					+ " If you add a reservation for John, have it removed when John gets to borrow the book.");
		}
	}

	@Test
	public void bookReservationsAreFirstComeFirstServed() throws Exception {
		String isbn13 = "9783125341470"; // Advanced Grammar in Use by Martin Hewings
		String barcode = "9783125341470-1";

		// TODO Comment out line below and make test pass
		// fail("Not yet implemented");
		service.reserveBook(isbn13, sallyAccountId);
		service.reserveBook(isbn13, johnAccountId);
		service.reserveBook(isbn13, patAccountId);

		// At this point, only Sally can borrow
		try {
			service.borrowBook(barcode, johnAccountId);
			fail("John should not be able to borrow, since Sally has it reserved first");
		} catch (Exception e) {
			// expected, pass!
		}
		try {
			service.borrowBook(barcode, patAccountId);
			fail("Pat should not be able to borrow, since Sally has it reserved first");
		} catch (Exception e) {
			// expected, pass!
		}

		// Sally borrows and returns the borrowed copy
		service.borrowBook(barcode, sallyAccountId);
		service.returnBook(barcode, sallyAccountId);

		// Pat still cannot borrow, since John reserved it first.
		try {
			service.borrowBook(barcode, patAccountId);
			fail("Pat should still not be able to borrow, since John has it reserved first");
		} catch (Exception e) {
			// expected, pass!
		}
		
		// John can now borrow, since he reserved it.
		service.borrowBook(barcode, johnAccountId);
		service.returnBook(barcode, johnAccountId);

		// After John returns it, Pat can now borrow
		// and satisfy his reservation.
		service.borrowBook(barcode, patAccountId);
		service.returnBook(barcode, patAccountId);
	}

	@Test
	public void cannotReserveSameBookMoreThanOnce() throws Exception {
		// fail("Not yet implemented");
		String isbn13 = "9783125341470"; // Advanced Grammar in Use by Martin Hewings
		service.reserveBook(isbn13, patAccountId);
		try {
			service.reserveBook(isbn13, patAccountId);
			fail("Cannot reserve same book more than once");
		} catch (Exception e) {
			// expected, pass!
		}
	}

}
