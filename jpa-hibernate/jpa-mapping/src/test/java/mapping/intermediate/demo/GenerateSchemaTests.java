package mapping.intermediate.demo;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
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
	public void testGenerateSchema() throws Exception {
		entityManager.getMetamodel().entity(Order.class);
	}
	
	@Test
	public void createAndUpdateCart() throws Exception {
		Product product = new Product("Bottled Water", money("USD", new BigDecimal("0.50")));
		entityManager.persist(product);
		entityManager.flush();

		Cart cart = new Cart();
		cart.addItem(product);
		entityManager.persist(cart);
		entityManager.flush();
		entityManager.clear();

		cart = entityManager.find(Cart.class, cart.getId());

		cart.addItem(product);
		entityManager.flush();

		cart.clear();
		entityManager.flush();
	}

}
