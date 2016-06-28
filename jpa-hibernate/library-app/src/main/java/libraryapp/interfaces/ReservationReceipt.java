package libraryapp.interfaces;

public class ReservationReceipt {

	private final String title;
	private final String isbn13, isbn10;

	protected ReservationReceipt(String title, String isbn13, String isbn10) {
		super();
		this.title = title;
		this.isbn13 = isbn13;
		this.isbn10 = isbn10;
	}

	public String getTitle() {
		return title;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public String getIsbn10() {
		return isbn10;
	}

}
