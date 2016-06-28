package mapping.relationship;

import javax.persistence.*;

@Entity
public class Employee {
	
	@Id
	private Long id;

	private String firstName;
	private String lastName;

	// TODO 02a: Map the many-to-one relationship to Department entity
	private Department department;
	
	public Employee(long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	protected Employee() { /* as needed by ORM/JPA */ }

}