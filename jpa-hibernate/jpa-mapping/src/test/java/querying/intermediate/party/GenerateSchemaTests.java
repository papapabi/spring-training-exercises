package querying.intermediate.party;

import static org.junit.Assert.*;

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

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Sql //(scripts="GenerateSchemaTests-joined.sql")
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

	// TODO 10 (optional): Run these tests and learn JPQL with JOINs and inheritance

	@Test
	public void partiesWithNoContactMechanism() throws Exception {
		// TODO 10a: Select parties with no contact mechanisms
		// Hint: Use the "IS EMPTY" on collections
		TypedQuery<Party> query = entityManager.createQuery(
				"SELECT p FROM Party p", Party.class);
		assertEquals(4, query.getResultList().size());
	}

	@Test
	public void partiesWithPostalAddressInUS() throws Exception {
		// TODO 10b: Select parties with postal address in the US
		TypedQuery<Party> query = entityManager.createQuery(
				"SELECT p FROM Party p", Party.class);
		assertEquals(6, query.getResultList().size());
	}

	@Test
	public void partiesWithUSBasedPhoneNumber() throws Exception {
		// TODO 10c: Select parties with US-based phone number
		TypedQuery<Party> query = entityManager.createQuery(
				"SELECT p FROM Party p", Party.class);
		assertEquals(1, query.getResultList().size());
	}

}
