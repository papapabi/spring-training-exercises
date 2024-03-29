package mapping.intermediate.shopping;

import java.util.HashMap;
import java.util.Map;

import javax.money.*;
import javax.persistence.*;

// TODO 08: Map a shopping cart entity and its items

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	// TODO 08a: Map one cart to many items using java.util.Map
	// The map key is a Product entity
	// The map value is a CartItem entity
	@OneToMany(orphanRemoval=true, cascade=CascadeType.ALL)
	@MapKey(name="product") // map key is @Id of CartItem
	private Map<Product, CartItem> items;

	public Cart() {
		this.items = new HashMap<>();
	}
	
	public Long getId() {
		return id;
	}

	public Map<Product, CartItem> getItems() {
		return items;
	}

	public void addItem(Product product) {
		setItemQuantity(product, 1);
	}

	public void setItemQuantity(Product product, int quantity) {
		if (product == null) {
			throw new IllegalArgumentException(
					"Product cannot be null");
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
				item.setQuantity(quantity);
			} else {
				items.put(product, new CartItem(this, product, quantity));
			}
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
