package querying.relationship;

import java.util.Collection;

import javax.persistence.*;

@Entity
public class Department {

	@Id
	private Long id;
	private String name;

	@OneToMany(mappedBy="department")
	private Collection<Employee> employees;
	
	public Department(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Collection<Employee> employees) {
		this.employees = employees;
	}
	
	protected Department() { /* as needed by ORM/JPA */ }

}
