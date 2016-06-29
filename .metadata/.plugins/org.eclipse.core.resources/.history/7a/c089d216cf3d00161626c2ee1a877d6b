package mapping.inheritance;

import static org.junit.Assert.*;

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

	// TODO 03f: Run these tests and see the output
	// When persisting a Person, what fields were set?
	// When persisting an Org, what fields were set?

	@Test
	public void testGenerateSchema() throws Exception {
		entityManager.getMetamodel().entity(Party.class);
		entityManager.getMetamodel().entity(Person.class);
		entityManager.getMetamodel().entity(Org.class);
		// When the above call does not throw an exception,
		// the Party/Person/Org classes are mapped as a persistent entities.

		Person p = new Person();
		p.setFirstName("John");
		p.setLastName("Smith");
		entityManager.persist(p);

		Org o = new Org();
		o.setName("Acme Corporation");
		entityManager.persist(o);
		
		entityManager.flush(); // just so the SQL statements are executed and seen
	}

}
