package mapping.intermediate.demo;

import java.util.*;

import javax.money.*;
import javax.persistence.*;

/**
 * A shopping {@link Cart cart} has zero or more {@link CartItem cart items}.
 * The cart items are {@link Embeddable embeddable}s (i.e. not entities). The
 * attribute is defined as a {@link Map map}.
 * <p>
 * This demonstrates how {@link ElementCollection} is used to map a <em>map</em>
 * of {@link Embeddable embeddable}s.
 */
@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@ElementCollection
	@CollectionTable(name="CART_ITEM")
		// defaults to Cart_items (entity + "_" + attribute name)
	// @MapKey(name="product") does not work here.
	//
	// @MapKey works if map key is an attribute of the target
	// entity. But since the map value is *not* an entity,
	// @MapKey will not work.
	//
	// @MapKeyColumn is used to override the column when the
	// key is a basic type. Since the key is an entity,
	// @MapKeyColumn will not work.
	//
	// @MapKeyJoinColumn is used to override the join column
	// when the key *is* an entity.
	//
	// Since the key *is* an entity, we use @MapKeyJoinColumn.
	// And remove the redundant @ManyToOne product association
	// from the embeddable CartItem.
	@MapKeyJoinColumn(name="product_id") // default is "items_KEY"
	private Map<Product, CartItem> items;

	public Cart() {
		this.items = new HashMap<>();
	}

	public Long getId() {
		return id;
	}

	public Map<Product, CartItem> getItems() {
		return Collections.unmodifiableMap(items);
	}

	public void addItem(Product product) {
		setItemQuantity(product, 1);
	}

	public void setItemQuantity(Product product, int quantity) {
		if (product == null) {
			throw new IllegalArgumentException(
					"Product cannot be null");
		}
		if (product.getId() == null) {
			// Since the #equals(Object) depends on the product's ID
			throw new IllegalArgumentException(
					"Product ID cannot be null");
		}
		if (quantity < 0) {
			throw new IllegalArgumentException(
					"Quantity cannot be negative");
		}
		if (quantity == 0) {
			// Setting quantity to zero should remove product from cart
			removeItem(product);
		} else {
			CartItem item = items.get(product);
			if (item != null) {
				quantity = item.getQuantity() + quantity;
			}
			// Replace cart item
			items.put(product, new CartItem(product, quantity));
		}
	}

	public void removeItem(Product product) {
		if (product == null) {
			throw new IllegalArgumentException(
					"Product cannot be null");
		}
		items.remove(product);
	}

	public void clear() {
		items.clear();
	}
	
	public Map<CurrencyUnit, MonetaryAmount> getTotals() {
		Map<CurrencyUnit, MonetaryAmount> totals = new HashMap<>();
		for (CartItem item : items.values()) {
			MonetaryAmount subTotal = item.getSubTotal();
			CurrencyUnit currencyUnit = subTotal.getCurrency();
			MonetaryAmount currencyTotal = totals.get(currencyUnit);
			if (currencyTotal == null) {
				currencyTotal = subTotal;
			} else {
				currencyTotal = currencyTotal.add(subTotal);
			}
			totals.put(currencyUnit, currencyTotal);
		}
		return totals;
	}

}
