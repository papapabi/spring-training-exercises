package querying.relationship;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

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

	// TODO 07: Run these tests and learn about JPQL with JOINs
	// Make the tests pass with the correct query.
	// Complete the partial query strings below.
	// Study the Department and Employee objects in the same package (under src/main/java).

	@Test
	@Sql(statements={
			"INSERT INTO Department (id, name)"
					+ " VALUES (1, 'Human Resources')",
			"INSERT INTO Department (id, name)"
					+ " VALUES (2, 'Executive Committee')",
			"INSERT INTO Employee (id, firstName, lastName, department_id)"
					+ " VALUES (18, 'Bob', 'Jones', 2)",
			"INSERT INTO Employee (id, firstName, lastName, department_id)"
					+ " VALUES (21, 'Adam', 'Scott', 2)",
			"INSERT INTO Employee (id, firstName, lastName, department_id)"
					+ " VALUES (42, 'John', 'Smith', 1)"
		})
	public void employeesOfADepartment() throws Exception {
		// TODO 07a: Retrieve employees of a given department
		TypedQuery<Employee> query = entityManager.createQuery(
				"SELECT e FROM Employee e",
				Employee.class);
		query.setParameter("id", 2L); // executive committee
		List<Employee> employees = query.getResultList();
		assertThat(employees, hasSize(2));
		assertThat("Employee #18 is in department #2",
				employees, hasItem(hasProperty("id", equalTo(18L))));
		assertThat("Employee #21 is in department #2",
				employees, hasItem(hasProperty("id", equalTo(21L))));
	}

	@Test
	@Sql(statements={
			"INSERT INTO Department (id, name)"
					+ " VALUES (1, 'Human Resources')",
			"INSERT INTO Department (id, name)"
					+ " VALUES (2, 'Executive Committee')",
			"INSERT INTO Department (id, name)"
					+ " VALUES (3, 'Office Administration')",
			"INSERT INTO Employee (id, firstName, lastName, department_id)"
					+ " VALUES (18, 'Bob', 'Jones', 2)",
			"INSERT INTO Employee (id, firstName, lastName, department_id)"
					+ " VALUES (21, 'Adam', 'Scott', 2)",
			"INSERT INTO Employee (id, firstName, lastName, department_id)"
					+ " VALUES (19, 'Paul', 'Johnson', 2)",
			"INSERT INTO Employee (id, firstName, lastName, department_id)"
					+ " VALUES (20, 'Ally', 'McBeal', 3)",
			"INSERT INTO Employee (id, firstName, lastName, department_id)"
					+ " VALUES (22, 'Fred', 'Summers', 3)",
			"INSERT INTO Employee (id, firstName, lastName, department_id)"
					+ " VALUES (42, 'John', 'Smith', 1)"
		})
	public void departmentsThatHaveMoreThanTwoEmployees() throws Exception {
		// TODO 07b: Retrieve departments that have more than two employees
		TypedQuery<Department> query = entityManager.createQuery(
				"SELECT d FROM Department d",
				Department.class);
		query.setParameter("size", 2);
		List<Department> departments = query.getResultList();
		assertThat("Only department #2 has more than two employees",
				departments, hasSize(1));
		assertThat("Department #2 has three employees",
				departments, hasItem(hasProperty("id", equalTo(2L))));
	}

}
