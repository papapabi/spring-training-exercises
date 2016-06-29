package mapping.intermediate.shopping;

import javax.money.MonetaryAmount;
import javax.persistence.*;

// TODO 08d: Ensure that items can be keyed by product

// Since the Cart uses Map<Product, CartItem> for its items,
// we need to ensure that items can be keyed by product.
// Hint: Use @Table(uniqueConstraints={...})
// Hint: You'll need to override the default column names
// to ensure that the column names used in the unique
// constraint will be correct.
@Entity
public class CartItem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	// TODO 08b: Map many-to-one relationship/association to the cart this item belongs to
	private Cart cart;

	// TODO 08c: Map many-to-one relationship/association to a product
	private Product product;

	private int quantity;

	public CartItem(Cart cart, Product product, int quantity) {
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
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException(
					"Quantity must be greater than zero");
		}
		this.quantity = quantity;
	}

	public MonetaryAmount getSubTotal() {
		return getProduct().getPrice().multiply(quantity);
	}

	protected CartItem() { /* as needed by ORM/JPA */ }

}
