package working.model;

import java.util.*;

import javax.persistence.*;

@Entity
public class Employee {
	
	@Id private Long id;
	private String name;
	private long salary;
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
	private Address address;
	@OneToMany(mappedBy="employee", cascade=CascadeType.ALL, orphanRemoval=true)
	private Collection<Phone> phones;

	public Employee(long id) {
		this.id = id;
		this.phones = new LinkedList<>();
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

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Collection<Phone> getPhones() {
		return phones;
	}

	public void setPhones(Collection<Phone> phones) {
		this.phones = phones;
	}

	protected Employee() { /* as needed by ORM/JPA */ }

}
