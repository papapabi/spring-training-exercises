package libraryapp.interfaces;

import java.util.Date;

/**
 * Data object that contains: title of book, unique barcode of borrowed book
 * copy, the date the book was borrowed, and the date the book is to be
 * returned.
 */
public class BorrowReceipt {

	private final String title;
	private final String barcode;
	private final Date borrowDate;
	private final Date dueDate;

	public BorrowReceipt(
			String title, String barcode,
			Date borrowDate, Date dueDate) {
		super();
		this.title = title;
		this.barcode = barcode;
		this.borrowDate = borrowDate;
		this.dueDate = dueDate;
	}

	public String getTitle() {
		return title;
	}

	public String getBarcode() {
		return barcode;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

}
