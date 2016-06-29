package querying.intermediate.shopping;

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
			@Column(name="currency", nullable=false),
			@Column(name="amount", nullable=false) })
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	protected Product() { /* as needed by ORM/JPA */ }

}
