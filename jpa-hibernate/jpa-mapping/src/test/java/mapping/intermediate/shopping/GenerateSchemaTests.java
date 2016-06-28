package mapping.intermediate.shopping;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class GenerateSchemaTests {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

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

	// TODO 08e: Run these tests and see the output

	@Test
	public void testGenerateSchema() throws Exception {
		Product bathSoap = new Product(
				"Bath soap", money("PHP", new BigDecimal("8.50")));
		entityManager.persist(bathSoap);

		Product milk = new Product(
				"Fresh milk", money("PHP", new BigDecimal("80.00")));
		entityManager.persist(milk);

		Product orangeJuice = new Product(
				"Orange juice", money("PHP", new BigDecimal("50.00")));
		entityManager.persist(orangeJuice);

		Cart cart = new Cart();
		cart.setItemQuantity(milk, 2);
		cart.addItem(bathSoap);
		cart.addItem(orangeJuice);

		assertThat(cart.getTotals(),
				hasEntry(Monetary.getCurrency("PHP"),
						money("PHP", new BigDecimal("218.50"))));

		entityManager.persist(cart);
		entityManager.clear(); // just so that the Cart entity is re-loaded

		Cart retrievedCart = entityManager.find(Cart.class, cart.getId());
		assertEquals(3, retrievedCart.getItems().size());

		Long retrievedCartId = retrievedCart.getId();
		entityManager.remove(retrievedCart);
		retrievedCart = null;
		entityManager.flush();
			// just so that the Cart entity is removed from underlying storage

		assertEquals(0, JdbcTestUtils.countRowsInTableWhere(
				jdbcTemplate, "Cart", "id = " + retrievedCartId));
	}

}
