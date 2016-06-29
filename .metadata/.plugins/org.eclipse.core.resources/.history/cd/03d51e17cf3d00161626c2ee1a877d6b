package working.detached;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.LinkedList;

import javax.persistence.*;

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
public class MergingDetachedObjectsTests {

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
		"INSERT INTO Employee (id, name, salary) VALUES (53, 'Johnny', 40000)"
	})
	public void mergeDetached1() throws Exception {
		final long id = 53;
		Employee emp = entityManager.find(Employee.class, id);
		entityManager.clear();
		// At this point, emp is detached!
		emp.setSalary(50000); // Change salary
		entityManager.merge(emp);
		entityManager.flush(); // Just so SQL commands can be seen

		entityManager.clear();
		Employee emp3 = entityManager.find(Employee.class, id);
		assertEquals(50000, emp3.getSalary());
	}

	@Test
	@Sql(statements={
		"INSERT INTO Employee (id, name, salary) VALUES (53, 'Johnny', 40000)"
	})
	public void mergeDetached2() throws Exception {
		final long id = 53;
		Employee emp = entityManager.find(Employee.class, id);
		entityManager.clear();
		// At this point, emp is detached!
		emp.setSalary(50000); // Change salary

		entityManager.merge(emp);

		// How about further changes *after* merge?
		emp.setName("Johnny B. Good"); // This change not be saved.

		// The returned *managed* instance should be used for further changes.
		/*
		Employee managedEmp = entityManager.merge(emp);
		managedEmp.setName("Johnny B. Good");
		*/
		entityManager.flush(); // Just so SQL commands can be seen

		entityManager.clear();
		Employee emp3 = entityManager.find(Employee.class, id);
		assertEquals("Johnny", emp3.getName());
		assertEquals(50000, emp3.getSalary());
	}

	@Test
	@Sql(statements={
		"INSERT INTO Employee (id, name, salary) VALUES (53, 'Johnny', 40000)"
	})
	public void mergePseudoDetached1() throws Exception {
		final long id = 53;
		Employee emp = entityManager.find(Employee.class, id);
		entityManager.clear();
		// At this point, emp is detached!
		// But instead of using the detached entity,
		// we create a new one with the same identifier!
		Employee emp2 = new Employee(id);
		emp2.setName(emp.getName());
		// emp2.setSalary(emp.getSalary());
		emp2.setSalary(50000); // Changes applied to it
		entityManager.merge(emp2);
		entityManager.flush(); // Just so SQL commands can be seen
		
		entityManager.clear();
		Employee emp3 = entityManager.find(Employee.class, id);
		assertEquals(50000, emp3.getSalary());
	}

	@Test
	@Sql(statements={
		"INSERT INTO Employee (id, name, salary) VALUES (53, 'Johnny', 40000)",
		"INSERT INTO Phone (number, employee_id) VALUES ('1800-FLOWERS', 53)"
	})
	public void mergePseudoDetached2() throws Exception {
		final long id = 53;
		Employee emp = entityManager.find(Employee.class, id);
		emp.getPhones().size();
		entityManager.clear();
		// At this point, emp is detached!
		// But instead of using the detached entity,
		// we create a new one with the same identifier!
		Employee emp2 = new Employee(id);
		emp2.setName(emp.getName());
		emp2.setSalary(emp.getSalary());
		emp2.setPhones(new LinkedList<>(emp.getPhones()));
		// Changes applied to it
		emp2.setSalary(50000);
		// And a new related entity is added
		Phone phone1 = new Phone();
		phone1.setNumber("1800-BUYLOTS");
		phone1.setEmployee(emp2);
		emp2.getPhones().add(phone1);
		entityManager.merge(emp2);
		entityManager.flush(); // Just so SQL commands can be seen

		entityManager.clear();
		Employee emp3 = entityManager.find(Employee.class, id);
		assertEquals(50000, emp3.getSalary());
		assertEquals(2, emp3.getPhones().size());
		assertThat(emp3.getPhones(),
				hasItem(hasProperty("number", equalTo("1800-FLOWERS"))));
		assertThat(emp3.getPhones(),
				hasItem(hasProperty("number", equalTo("1800-BUYLOTS"))));
	}

	@Test
	@Sql(statements={
		"INSERT INTO Employee (id, name, salary) VALUES (53, 'Johnny', 40000)",
		"INSERT INTO Phone (number, employee_id) VALUES ('1800-FLOWERS', 53)"
	})
	public void mergePseudoDetached3() throws Exception {
		final long id = 53;
		Employee emp = entityManager.find(Employee.class, id);
		emp.getPhones().size();
		entityManager.clear();
		// At this point, emp is detached!
		// But instead of using the detached entity,
		// we create a new one with the same identifier!
		Employee emp2 = new Employee(id);
		emp2.setName(emp.getName());
		emp2.setSalary(emp.getSalary());
		emp2.setPhones(new LinkedList<>(emp.getPhones()));
		// How about we remove all existing phones?
		emp2.getPhones().clear();
		entityManager.merge(emp2);
		entityManager.flush(); // Just so SQL commands can be seen

		entityManager.clear();
		Employee emp3 = entityManager.find(Employee.class, id);
		assertEquals(0, emp3.getPhones().size());
	}

	@Test
	@Sql(statements={
		"INSERT INTO Employee (id, name, salary) VALUES (53, 'Johnny', 40000)",
		"INSERT INTO Phone (number, employee_id) VALUES ('1800-FLOWERS', 53)"
	})
	public void mergePseudoDetached4() throws Exception {
		final long id = 53;
		Employee emp = entityManager.find(Employee.class, id);
		emp.getPhones().size();
		entityManager.clear();
		// At this point, emp is detached!
		// But instead of using the detached entity,
		// we create a new one with the same identifier!
		Employee emp2 = new Employee(id);
		emp2.setName(emp.getName());
		emp2.setSalary(emp.getSalary());
		emp2.setPhones(new LinkedList<>(emp.getPhones()));
		// How about we remove the existing phones and add a new one?
		emp2.getPhones().clear();
		Phone phone1 = new Phone();
		phone1.setNumber("1800-BUYLOTS");
		phone1.setEmployee(emp2);
		emp2.getPhones().add(phone1);
		entityManager.merge(emp2);
		entityManager.flush(); // Just so SQL commands can be seen

		entityManager.clear();
		Employee emp3 = entityManager.find(Employee.class, id);
		assertEquals(1, emp3.getPhones().size());
		assertThat(emp3.getPhones(),
				hasItem(hasProperty("number", equalTo("1800-BUYLOTS"))));
	}

	@Test
	@Sql(statements={
		"INSERT INTO Employee (id, name, salary) VALUES (53, 'Johnny', 40000)",
		"INSERT INTO Address (id, street, city) VALUES (1400, 'Wall', 'New York')",
		"UPDATE Employee SET address_id = 1400 WHERE id = 53",
		"INSERT INTO Phone (number, employee_id) VALUES ('1800-FLOWERS', 53)"
	})
	public void lazyLoadDetached() throws Exception {
		final long id = 53;
		Employee emp = entityManager.find(Employee.class, id);
		entityManager.clear();
		// At this point, emp is detached!
		
		// It's okay to load the address (since it is EAGER-ly fetched).
		assertEquals("Wall", emp.getAddress().getStreet());
		assertEquals("New York", emp.getAddress().getCity());

		// But phones have not been loaded yet (since it is LAZY-ly fetched.
		try {
			// Loading it will cause an exception
			emp.getPhones().size();
			fail("An exception should have been thrown");
		} catch (Exception e) {
			// We expect a LazyInitializationException (org.hibernate)
			e.printStackTrace();
		}
	}

}
