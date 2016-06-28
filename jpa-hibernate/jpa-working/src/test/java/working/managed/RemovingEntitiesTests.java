package working.managed;

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

import working.model.Employee;
import working.model.Phone;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RemovingEntitiesTests {

	@PersistenceContext
	private EntityManager entityManager;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Sql(statements={
		"INSERT INTO Employee (id, name, salary) VALUES (42, 'Johnny', 40000)"
	})
	public void removingEntity() {
		final long id = 42;
		Employee emp = entityManager.find(Employee.class, id);
		entityManager.remove(emp);
		entityManager.flush(); // Just so SQL commands can be seen
		
		entityManager.clear();
		assertNull(entityManager.find(Employee.class, id));
	}

	@Test
	@Sql(statements={
		"INSERT INTO Employee (id, name, salary) VALUES (42, 'Johnny', 40000)",
		"INSERT INTO Phone (number, employee_id) VALUES ('1800-FLOWERS', 42)"
	})
	public void removingEntity2() {
		final long id = 42;
		Employee emp = entityManager.find(Employee.class, id);
		entityManager.remove(emp);
		entityManager.flush(); // Just so SQL commands can be seen
		
		// The related phone entity was also removed
		// due to cascade of REMOVE operation.
		// Otherwise, a FK constraint violation would have occured.

		entityManager.clear();
		assertNull(entityManager.find(Employee.class, id));
	}

	@Test
	@Sql(statements={
		"INSERT INTO Employee (id, name, salary) VALUES (42, 'Johnny', 40000)",
		"INSERT INTO Phone (number, employee_id) VALUES ('1800-FLOWERS', 42)"
	})
	public void orphanRemoval() {
		final long id = 42;
		Employee emp = entityManager.find(Employee.class, id);
		final int originalSize = emp.getPhones().size();
		assertTrue(emp.getPhones().size() > 0);
		Phone phone1 = emp.getPhones().iterator().next(); // first one
		emp.getPhones().remove(phone1);
		// entityManager.remove(phone1);
		entityManager.flush(); // Just so SQL commands can be seen

		// The related phone entity will be removed
		// due to orphanRemoval=true.

		entityManager.clear();
		Employee emp3 = entityManager.find(Employee.class, id);
		assertEquals(originalSize - 1, emp3.getPhones().size());
	}

}
