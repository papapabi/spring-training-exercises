package mapping.intermediate.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;

/**
 * Sales order or purchase order aggregate root. Notice that {@link OrderItem
 * items} are <strong>not</strong> entities.
 * <p>
 * Note how {@link ElementCollection} is used to map a <em>collection</em> of
 * {@link Embeddable embeddable}s (i.e. not entities).
 */
@Entity
@Table(name="\"Order\"")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;

	@ElementCollection
	@CollectionTable(
		name="Order_Item",
		joinColumns={
				@JoinColumn(name="order_id", referencedColumnName="id")
			},
		uniqueConstraints={
				@UniqueConstraint(columnNames={ "order_id", "product_id" })
			})
	private Collection<OrderItem> items;

	public Order(List<OrderItem> items) {
		this.items = new ArrayList<>(items);
	}

	public Long getId() {
		return id;
	}

	public Collection<OrderItem> getItems() {
		return Collections.unmodifiableCollection(items);
	}

	/* Works on java.util.Map as well
	@ElementCollection
	private Map<Long, OrderItem> items;

	public Order(List<OrderItem> items) {
		this.items = new HashMap<>(items.size());
		// Initialize map from list of items
		for (OrderItem item : items) {
			this.items.put(item.getProductId, item);
			// What happens if productIds are repeated in the list?
		}
	}
	*/

}
