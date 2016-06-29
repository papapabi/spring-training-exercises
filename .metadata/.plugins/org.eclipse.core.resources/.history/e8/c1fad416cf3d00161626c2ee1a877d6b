package querying.intermediate.shopping;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import querying.intermediate.shopping.Cart;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Sql(statements={
	"INSERT INTO Product (id, name, currency, amount) VALUES (101, 'Bath soap', 'PHP', 8.50)",
	"INSERT INTO Product (id, name, currency, amount) VALUES (102, 'Fresh milk', 'PHP', 80.00)",
	"INSERT INTO Product (id, name, currency, amount) VALUES (103, 'Orange juice', 'PHP', 50.00)",
	"INSERT INTO Product (id, name, currency, amount) VALUES (104, 'Bread', 'PHP', 50.00)",
	"INSERT INTO Cart (id) VALUES (401)",
	"INSERT INTO CartItem (cart_id, product_id, quantity, currency, amount) VALUES (401, 101, 2, 'PHP', 8.50)",
	"INSERT INTO CartItem (cart_id, product_id, quantity, currency, amount) VALUES (401, 102, 1, 'PHP', 80.00)",
	"INSERT INTO CartItem (cart_id, product_id, quantity, currency, amount) VALUES (401, 103, 1, 'PHP', 50.00)",
	"INSERT INTO Cart (id) VALUES (402)",
	"INSERT INTO CartItem (cart_id, product_id, quantity, currency, amount) VALUES (402, 101, 2, 'PHP', 8.50)",
	"INSERT INTO CartItem (cart_id, product_id, quantity, currency, amount) VALUES (402, 102, 2, 'PHP', 80.00)",
	"INSERT INTO CartItem (cart_id, product_id, quantity, currency, amount) VALUES (402, 103, 4, 'PHP', 50.00)",
	"INSERT INTO Cart (id) VALUES (403)",
	"INSERT INTO CartItem (cart_id, product_id, quantity, currency, amount) VALUES (403, 101, 2, 'PHP', 8.50)",
	"INSERT INTO Cart (id) VALUES (404)",
	"INSERT INTO CartItem (cart_id, product_id, quantity, currency, amount) VALUES (404, 101, 2, 'PHP', 8.50)",
	"INSERT INTO CartItem (cart_id, product_id, quantity, currency, amount) VALUES (404, 102, 2, 'PHP', 80.00)",
	"INSERT INTO CartItem (cart_id, product_id, quantity, currency, amount) VALUES (404, 103, 1, 'PHP', 50.00)",
	"INSERT INTO CartItem (cart_id, product_id, quantity, currency, amount) VALUES (404, 104, 1, 'PHP', 50.00)",
})
public class GenerateSchemaTests {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Before
	public void setUp() throws Exception {
		assertNotNull(entityManager);
	}

	@After
	public void tearDown() throws Exception {
	}

	protected MonetaryAmount money(
			String currencyCode, BigDecimal amount) {
		return Monetary.getDefaultAmountFactory()
				.setCurrency(currencyCode)
				.setNumber(amount)
				.create();
	}
	
	@Test
	public void cartItemIsRemoved() throws Exception {
		Cart cart = entityManager.find(Cart.class, 401L);
		Product orangeJuice = entityManager.find(Product.class, 103L);
		cart.removeItem(orangeJuice);
		entityManager.flush(); // just so that the SQL commands can be seen
	}

	@Test
	public void cartItemsAreRemovedWhenCartIsRemoved() throws Exception {
		Cart cart = entityManager.find(Cart.class, 401L);
		entityManager.remove(cart);
		entityManager.flush(); // just so that the SQL commands can be seen
	}

	// TODO 09: Run these tests and learn about JPQL with JOINs
	// Make the tests pass with the correct query.
	// Complete the partial query strings below.
	// Study the Cart and CartItem objects in the same package (under src/main/java).

	@Test
	public void cartsWithOrangeJuice() throws Exception {
		// TODO 09a: Select carts with orange juice (product.id = 103)
		Product orangeJuice = entityManager.find(Product.class, 103L);
		TypedQuery<Cart> query = entityManager.createQuery(
				"SELECT c FROM Cart c", Cart.class);
		query.setParameter("product", orangeJuice);
		List<Cart> carts = query.getResultList();
		assertThat(carts, hasSize(3));
		for (Cart cart : carts) {
			assertTrue(cart.getItems().containsKey(orangeJuice));
		}
	}

	@Test
	public void cartsWithThreeOrMoreItems() throws Exception {
		// TODO 09b: Select carts with three or more items (just items; not quantity of each item)
		// HINT: Use JPA's SIZE() function on collections or maps
		TypedQuery<Cart> query = entityManager.createQuery(
				"SELECT c FROM Cart c", Cart.class);
		List<Cart> carts = query.getResultList();
		assertThat(carts, hasSize(3));
		for (Cart cart : carts) {
			assertTrue(cart.getItems().size() >= 3);
		}
	}

	@Test
	public void cartsWithItemQuantitiesOfThreeOrMoreItems() throws Exception {
		// TODO 09c: Select carts with items that have a quantity of 3 or more
		TypedQuery<Cart> query = entityManager.createQuery(
				"SELECT c FROM Cart c", Cart.class);
		List<Cart> carts = query.getResultList();
		assertThat(carts, hasSize(1));
		assertEquals(402L, (long) carts.get(0).getId());
	}

}
