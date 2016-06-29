package working.managed;

import static org.hamcrest.Matchers.*;
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

import working.model.Address;
import working.model.Employee;
import working.model.Phone;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PersistingUnmanagedTests {

	@PersistenceContext
	private EntityManager entityManager;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void persistingNew() {
		final long id = 42;
		Employee emp = new Employee(id);
		emp.setName("Johnny");
		entityManager.persist(emp);
		entityManager.flush(); // Just so SQL commands can be seen
		
		entityManager.clear();
		Employee emp3 = entityManager.find(Employee.class, id);
		assertEquals("Johnny", emp3.getName());
	}

	@Test
	public void persistingNew2() {
		final long id = 42;
		Employee emp = new Employee(id);
		emp.setName("Johnny");
		entityManager.persist(emp);
		entityManager.flush(); // Just so SQL commands can be seen

		// Since emp is managed, changes will still be saved
		emp.setSalary(40000);
		entityManager.flush(); // Just so SQL commands can be seen
		
		entityManager.clear();
		Employee emp3 = entityManager.find(Employee.class, id);
		assertEquals(40000, emp3.getSalary());
	}

	@Test
	public void persistingNew3() {
		final long id = 42;
		Employee emp = new Employee(id);
		emp.setName("Johnny");
		emp.setSalary(40000);
		
		// Add phones
		Phone phone1 = new Phone();
		phone1.setNumber("1800-FLOWERS");
		emp.getPhones().add(phone1);
		// Remember, relationship maintenance is the responsibility of the application.
		phone1.setEmployee(emp);
		
		// The new phone entities will be persisted
		// due to cascade of PERSIST operation.

		entityManager.persist(emp);
		entityManager.flush(); // Just so SQL commands can be seen
		
		entityManager.clear();
		Employee emp3 = entityManager.find(Employee.class, id);
		assertEquals(1, emp3.getPhones().size());
		assertThat(emp3.getPhones(),
				hasItem(hasProperty("number", equalTo("1800-FLOWERS"))));
	}

	@Test
	public void persistingNew4() {
		final long id = 42;
		Employee emp = new Employee(id);
		emp.setName("Johnny");
		emp.setSalary(40000);
		
		// Add the address
		Address addr = new Address();
		addr.setStreet("Wall");
		addr.setCity("New York");
		// Remember, relationship maintenance is the responsibility of the application.
		emp.setAddress(addr);

		// The new address entity will be persisted
		// due to cascade of PERSIST operation.

		entityManager.persist(emp);
		entityManager.flush(); // Just so SQL commands can be seen
		
		entityManager.clear();
		Employee emp3 = entityManager.find(Employee.class, id);
		assertNotNull(emp3.getAddress());
		assertEquals("Wall", emp3.getAddress().getStreet());
		assertEquals("New York", emp3.getAddress().getCity());
	}

}
