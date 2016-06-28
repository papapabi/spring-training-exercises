package mapping.hibernate.usertype.demo;

import java.time.Month;
import java.time.Year;

import javax.money.MonetaryAmount;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Not really a book entity, but just to test {@link PersistentYearAsInteger},
 * {@link PersistentMonthAsInteger}, and .
 */
@Entity
public class Book {

	@Id
	private Long id;

	private Year yearPublished;
	private Month monthPublished;

	@org.hibernate.annotations.Columns(columns={
			@Column(name="currency"),
			@Column(name="amount") })
	private MonetaryAmount price;

	public Book(long id) {
		this(id, null, null);
	}

	public Book(long id, Year yearPublished) {
		this(id, yearPublished, null);
	}
	
	public Book(long id, Year yearPublished, Month monthPublished) {
		this(id, yearPublished, monthPublished, null);
	}
	
	public Book(long id, Year yearPublished, Month monthPublished, MonetaryAmount price) {
		this.id = id;
		this.yearPublished = yearPublished;
		this.monthPublished = monthPublished;
		this.price = price;
	}
	
	public Long getId() {
		return id;
	}

	public Year getYearPublished() {
		return yearPublished;
	}

	public void setYearPublished(Year yearPublished) {
		this.yearPublished = yearPublished;
	}

	public Month getMonthPublished() {
		return monthPublished;
	}

	public void setMonthPublished(Month monthPublished) {
		this.monthPublished = monthPublished;
	}

	public MonetaryAmount getPrice() {
		return price;
	}

	public void setPrice(MonetaryAmount price) {
		this.price = price;
	}

	protected Book() { /* as needed by ORM/JPA */ }

}
