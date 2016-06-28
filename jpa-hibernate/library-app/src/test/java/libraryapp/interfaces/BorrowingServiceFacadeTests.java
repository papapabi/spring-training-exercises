package libraryapp.interfaces;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BorrowingServiceFacadeTests
		extends AbstractBorrowingServiceFacadeTests {
		// TODO After passing all the tests, extend from AbstractReservationAwareBorrowingServiceFacadeTests
		// and pass reservation-related tests.
		// extends AbstractReservationAwareBorrowingServiceFacadeTests {

	@Override
	protected void setUpBooksAndCopies() {
		// TODO Set-up the following books (and their copies), and categories
		// NOTE: All these book copies are IN (not borrowed/checked-out).

		// An entityManager field has already been defined in the base class.
		// So, you can use it here.
		// entityManager.persist(entity);

		/*
		 * title: The Affair
		 * author: Lee Child
		 * published month: September
		 * published year: 2011
		 * isbn: 1409011445, 9781409011446
		 * category: Novel
		 * copies: 9781409011446-1, 9781409011446-2
		 */

		/*
		 * title: A Wanted Man
		 * author: Lee Child
		 * published month: August
		 * published year: 2012
		 * isbn: 037369606X, 9780373696062
		 * category: Novel
		 * copies: 9780373696062-1
		 */

		/*
		 * title: Inferno
		 * author: Dan Brown
		 * published month: May
		 * published year: 2013
		 * isbn: 0593075005, 9780593075005
		 * category: Novel
		 * copies: 9780593075005-1, 9780593075005-2
		 */

		/*
		 * title: The Lost Symbol
		 * author: Dan Brown
		 * published month: September
		 * published year: 2009
		 * isbn: 055217002X, 9780552170024
		 * category: Novel
		 * copies: 9780552170024-1, 9780552170024-2
		 */

		/*
		 * title: Diary of a Wimpy Kid
		 * author: Jeff Kinney
		 * published month: April
		 * published year: 2007
		 * isbn: 0141324902, 9780141324906
		 * category: Comedy
		 * copies: 9780141324906-1, 9780141324906-2
		 */

		/*
		 * title: Advanced Grammar in Use
		 * author: Martin Hewings
		 * published year: 2005
		 * isbn: 3125341477, 9783125341470
		 * category: Reference
		 * copies: 9783125341470-1
		 */
	}

	@Override
	protected void setUpMemberCategories() {
		/*
		 * Undergraduate students can borrow up to 3 books.
		 * Graduate students can borrow up to 2 books.
		 * Department heads can borrow up to 50 books.
		 * Professors can borrow up to 10 books.
		 */
		// An entityManager field has already been defined in the base class.
		// So, you can use it here.
	}

	@Override
	protected void setUpLoanPeriods() {
		// TODO Set-up the following loan periods
		/*
		 * Undergraduate students can borrow novels for up to 10 days
		 * Undergraduate students can borrow reference books for up to 15 days
		 * Graduate students can borrow novels for up to 5 days
		 * Graduate students can borrow reference books for up to 5 days
		 * Department heads and professors can borrow reference books for up to 30 days,
		 * 	but cannot borrow novels
		 */
		// An entityManager field has already been defined in the base class.
		// So, you can use it here.
	}

	@Override
	protected void setUpMemberAccounts() {
		// TODO Set-up member accounts
		/*
		 * 1. John is a department head
		 * 2. Sally is an undergraduate student
		 * 3. Pat is a graduate student
		 */
		// An entityManager field has already been defined in the base class.
		// So, you can use it here.

		// TODO Initialize the following member account IDs
		johnAccountId = null;
		sallyAccountId = null;
		patAccountId = null;
	}

}
