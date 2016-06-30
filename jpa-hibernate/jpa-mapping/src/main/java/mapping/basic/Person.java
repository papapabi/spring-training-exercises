package mapping.basic;

import java.util.Date;

import javax.persistence.*;

// TODO 01: Map basic data-members
// TODO 01a: Map the Person class as a persistent entity
@Entity
public class Person {
	
	// TODO 01b1: Add an "id" field of type Long and map it as the primary key
	// TODO 01b3: Make the primary key generated (AUTO)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Basic
	private String firstName;
	// unannotated fields of an @Entity-annotated class automatically default to @Basic, such as this one
	private String lastName;
	// TODO 01c: Map the birth date as a DATE field (no hours-minutes-seconds)
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	// TODO 01d: Map the gender (enum) as a STRING
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	// TODO 01f1: Add a constructor that accepts firstName and lastName arguments
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	// TODO 01f3: Add a zero-args constructor to fix the error
	// Note: You can make the zero-args constructor public or protected.
	protected Person() {
		
	}
	
	// TODO 01b2: Add a getter method for this "id" field
	public long getId() {
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
