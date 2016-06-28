package mapping.intermediate.demo;

import javax.money.MonetaryAmount;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Immutable order item.
 */
@Embeddable
public class OrderItem {

	@Column(name="product_id")
	private long productId;

	@org.hibernate.annotations.Type(
			type="mapping.intermediate.shopping.PersistentMonetaryAmountAsAmountAndCurrency")
	@org.hibernate.annotations.Columns(columns={
			@Column(name="currency", nullable=false),
			@Column(name="amount", nullable=false) })
	private MonetaryAmount price;
	
	private int quantity;

	public OrderItem(long productId, MonetaryAmount price, int quantity) {
		if (price == null) {
			throw new IllegalArgumentException("Price cannot be null");
		}
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public long getProductId() {
		return productId;
	}

	public MonetaryAmount getPrice() {
		return price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + (int) (productId ^ (productId >>> 32));
		result = prime * result + quantity;
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
		OrderItem other = (OrderItem) obj;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (productId != other.productId)
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

	protected OrderItem() { /* as needed by ORM/JPA */ }

}
