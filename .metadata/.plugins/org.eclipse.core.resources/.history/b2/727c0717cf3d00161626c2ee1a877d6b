package jpa.spring;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {

	@Id
	private Long id;
	// @Basic
	private String name;

	public Person(long id) {
		this.id = id;
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

	protected Person() { /* as needed by ORM/JPA */ }

}
