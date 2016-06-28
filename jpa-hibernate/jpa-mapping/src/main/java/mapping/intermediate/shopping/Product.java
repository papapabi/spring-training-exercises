package mapping.intermediate.shopping;

import javax.money.MonetaryAmount;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.util.StringUtils;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;

	@org.hibernate.annotations.Type(
			type="mapping.intermediate.shopping.PersistentMonetaryAmountAsAmountAndCurrency")
	@org.hibernate.annotations.Columns(columns={
			@Column(name="currency"),
			@Column(name="amount") })
	private MonetaryAmount price;
	
	public Product(String name, MonetaryAmount price) {
		setName(name);
		setPrice(price);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (!StringUtils.hasText(name)) {
			throw new IllegalArgumentException(
					"Product name must contain"
					+ " at least one non-whitespace character");
		}
		this.name = name;
	}

	public MonetaryAmount getPrice() {
		return price;
	}

	public void setPrice(MonetaryAmount price) {
		if (price == null) {
			throw new IllegalArgumentException(
					"Product price cannot be null");
		}
		if (price.isNegative()) {
			throw new IllegalArgumentException(
					"Product price cannot be negative");
		}
		this.price = price;
	}
	
	protected Product() { /* as needed by ORM/JPA */ }

}
