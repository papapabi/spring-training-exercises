package querying.intermediate.shopping;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class CartItemId implements Serializable {
	
	@Column(name="cart_id", updatable=false, nullable=false)
	private Long cartId;
	@Column(name="product_id", updatable=false, nullable=false)
	private Long productId;

	CartItemId(Cart cart, Product product) {
		this.cartId = cart.getId();
		this.productId = product.getId();
	}

	public Long getCartId() {
		return cartId;
	}

	public Long getProductId() {
		return productId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
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
		CartItemId other = (CartItemId) obj;
		if (cartId == null) {
			if (other.cartId != null)
				return false;
		} else if (!cartId.equals(other.cartId))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}
	
	protected CartItemId() { /* as needed by ORM/JPA */ }

}
