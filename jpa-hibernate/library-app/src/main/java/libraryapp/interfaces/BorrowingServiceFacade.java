package libraryapp.interfaces;

import java.util.Collection;

/**
 * This facade shields the domain layer - model, services, repositories -
 * from concerns about such things as the user interface and remoting.
 */
public interface BorrowingServiceFacade {

	/**
	 * Checks-out a book copy with the given barcode, and records this against the
	 * given member's account.
	 * <p>
	 * Implementations should throw a runtime exception when the borrowing rules
	 * are violated, and prevent the book from being borrowed/checked-out.
	 * 
	 * @param barcode
	 *          the barcode of the book copy to be borrowed
	 * @param memberAccountId
	 *          the borrowing member's unique account identifier
	 * @return an object containing the book's title, unique barcode, the date the
	 *         book was borrowed, and the date the book is to be returned by
	 */
	BorrowReceipt borrowBook(String barcode, String memberAccountId);

	/**
	 * Returns a checked-out copy of a book with the given barcode that was
	 * recorded against the given member's account.
	 * <p>
	 * Implementations should throw a runtime exception when there are
	 * inconsistencies with the data (e.g. returning a book that was not borrowed
	 * by the account).
	 * 
	 * @param barcode
	 *          the barcode of the book copy to be returned
	 * @param memberAccountId
	 *          the returning member's unique account identifier
	 */
	void returnBook(String barcode, String memberAccountId);

	/**
	 * Reserves a book with the given ISBN (either in 10-, or 13-digit formats).
	 * 
	 * @param isbn the ISBN of the book to be reserved
	 * @param memberAccountId the reserving member's unique account identifier
	 * @return an object containing the book's title, and ISBN
	 */
	ReservationReceipt reserveBook(String isbn, String memberAccountId);

	/**
	 * Returns the member's book loan status (i.e. collection of loan books and
	 * their respective borrowed/due dates).
	 * <p>
	 * Implementations must return an empty non-<code>null</code> collection if
	 * the member has no book loans.
	 * 
	 * @param memberAccountId
	 *          the member's unique account identifier
	 * @return the member's book loan status.
	 */
	Collection<BookLoanStatus> getBookLoanStatus(String memberAccountId);

}
