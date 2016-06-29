package querying.intermediate.shopping;

import javax.money.MonetaryAmount;
import javax.persistence.*;

@Entity
public class CartItem {

	@EmbeddedId
	private CartItemId id;

	@ManyToOne
	@MapsId("cartId")
	private Cart cart;

	@ManyToOne
	@MapsId("productId")
	private Product product;

	@org.hibernate.annotations.Type(
			type="mapping.intermediate.shopping.PersistentMonetaryAmountAsAmountAndCurrency")
	@org.hibernate.annotations.Columns(columns={
			@Column(name="currency", nullable=false),
			@Column(name="amount", nullable=false) })
	private MonetaryAmount price;

	private int quantity;

	CartItem(Cart cart, Product product, int quantity) {
		if (cart == null) {
			throw new IllegalArgumentException(
					"Cart cannot be null");
		}
		if (product == null) {
			throw new IllegalArgumentException(
					"Product cannot be null");
		}
		if (quantity <= 0) {
			throw new IllegalArgumentException(
					"Quantity must be greater than zero");
		}
		this.id = new CartItemId(cart, product);
		this.cart = cart;
		this.product = product;
		this.price = product.getPrice();
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public MonetaryAmount getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public MonetaryAmount getSubTotal() {
		return getPrice().multiply(quantity);
	}

	protected CartItem() { /* as needed by ORM/JPA */ }

}
