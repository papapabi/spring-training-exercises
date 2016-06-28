package working.model;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Person {

	@Id
	private String name;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			joinColumns={@JoinColumn(name="person1_name")},
			inverseJoinColumns={@JoinColumn(name="person2_name")})
	private Set<Person> friends = new HashSet<Person>();
	
	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Set<Person> getFriends() {
		return Collections.unmodifiableSet(friends);
		// return friends;
	}

	public void setFriends(Set<Person> friends) {
		this.friends = friends;
	}

	public boolean addFriend(Person person) {
		return friends.add(person);
	}

	public boolean removeFriend(Person person) {
		return friends.remove(person);
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	protected	Person() {
		/* as needed by JPA/ORM */
	}

}
