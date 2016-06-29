package mapping.hibernate.usertype;

import static org.junit.Assert.*;

import java.time.MonthDay;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
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

	// TODO 04h: Run these tests and see the output

	@Test
	@Sql(statements={
		"INSERT INTO Holiday (id, name, month, day)"
				+ " VALUES (42, 'Christmas Day', 12, 25)"
	})
	public void testGenerateSchema() throws Exception {
		entityManager.getMetamodel().entity(Holiday.class);
		// When the above call does not throw an exception,
		// the Holiday class is mapped as a persistent entity.
		Holiday h = entityManager.find(Holiday.class, 42L);
		assertEquals("Christmas Day", h.getName());
		assertEquals(MonthDay.of(12, 25), h.getMonthDay());
	}

}
