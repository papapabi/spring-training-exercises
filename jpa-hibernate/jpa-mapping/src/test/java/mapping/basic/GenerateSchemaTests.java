package mapping.basic;

import static org.junit.Assert.*;

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


@ContextConfiguration // by default, it loads $(class-name)-context.xml in the same package, if not specified
@RunWith(SpringJUnit4ClassRunner.class) // caches a shared ApplicationContext across test methods
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

	// TODO 01e: Run these tests and see the output

	@Test
	@Sql(statements={
		"INSERT INTO Person (id, firstName, lastName)"
				+ " VALUES (42, 'John', 'Smith')"
	})
	public void testGenerateSchema() throws Exception {
		entityManager.getMetamodel().entity(Person.class);
		// When the above call does not throw an exception,
		// the Person class is mapped as a persistent entity.
		Person p = entityManager.find(Person.class, 42L);
		assertEquals("John", p.getFirstName());
		assertEquals("Smith", p.getLastName());
	}

	// TODO 01f2: Run these tests and see the output
	// What happened? Why did it happen? Proceed to next step to fix.

	// TODO 01f4: Run these tests and see if it is working.

}
