package querying.intermediate.shopping;

import java.util.*;

import javax.money.*;
import javax.persistence.*;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@OneToMany(mappedBy="cart", orphanRemoval=true, cascade=CascadeType.ALL)
	@MapKey(name="product")
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
