package mapping.intermediate.demo;

import javax.money.MonetaryAmount;
import javax.persistence.*;

/**
 * Immutable.
 */
@Embeddable
public class CartItem {

	// Removed, since Cart has a Map<Product, CartItem>
	/*
	@ManyToOne(optional=false)
	private Product product;
	*/

	@org.hibernate.annotations.Type(
			type="mapping.intermediate.shopping.PersistentMonetaryAmountAsAmountAndCurrency")
	@org.hibernate.annotations.Columns(columns={
			@Column(name="currency", nullable=false),
			@Column(name="amount", nullable=false) })
	private MonetaryAmount price;
	
	private int quantity;

	CartItem(Product product, int quantity) {
		if (product == null) {
			throw new IllegalArgumentException(
					"Product cannot be null");
		}
		if (quantity <= 0) {
			throw new IllegalArgumentException(
					"Quantity must be greater than zero");
		}
		// this.product = product;
		this.price = product.getPrice();
		this.quantity = quantity;
	}

	public MonetaryAmount getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public MonetaryAmount getSubTotal() {
		return getPrice().multiply(quantity);
	}

	protected CartItem() { /* as needed by ORM/JPA */ }

}
