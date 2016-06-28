package mapping.converter;

import static org.junit.Assert.*;

import java.time.LocalDate;

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

import mapping.converter.Person;

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

	// TODO 05e: Run these tests and see the output

	@Test
	@Sql(statements={
		"INSERT INTO Person (id, name, birthDate)"
				+ " VALUES (42, 'Mike', DATE '2000-03-15')"
	})
	public void testGenerateSchema() throws Exception {
		entityManager.getMetamodel().entity(Person.class);
		// When the above call does not throw an exception,
		// the Person class is mapped as a persistent entity.
		Person p = entityManager.find(Person.class, 42L);
		assertEquals("Mike", p.getName());
		assertEquals(LocalDate.of(2000, 3, 15), p.getBirthDate());
	}
	
}
