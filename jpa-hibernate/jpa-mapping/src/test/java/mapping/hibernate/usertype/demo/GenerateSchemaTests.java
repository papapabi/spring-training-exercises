package mapping.hibernate.usertype.demo;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;
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
	
	protected MonetaryAmount priceOf(
			String currencyCode, BigDecimal amount) {
		return Monetary.getDefaultAmountFactory()
				.setCurrency(currencyCode)
				.setNumber(amount)
				.create();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGenerateSchema() throws Exception {
		Book book1 = new Book(101,
				Year.of(1995), Month.SEPTEMBER, priceOf("USD", new BigDecimal("49.95")));
		Book book2 = new Book(102,
				Year.of(1998), Month.SEPTEMBER, priceOf("USD", new BigDecimal("69.95")));
		Book book3 = new Book(201,
				Year.of(2000), Month.OCTOBER, priceOf("PHP", new BigDecimal("1249.95")));
		entityManager.persist(book1);
		entityManager.persist(book2);
		entityManager.persist(book3);
		entityManager.flush(); // just so the SQL commands can be seen

		TypedQuery<Book> query;
		
		query = entityManager.createQuery(
				"FROM Book b WHERE b.price.currency = 'USD'", Book.class);
		List<Book> books = query.getResultList();
		assertEquals(2, books.size());
		assertThat(books, hasItems(
				hasProperty("id", equalTo(101L)),
				hasProperty("id", equalTo(102L))));

		query = entityManager.createQuery(
				"FROM Book b WHERE b.price.amount > 50", Book.class);
		assertEquals(2, query.getResultList().size());
	}

}
