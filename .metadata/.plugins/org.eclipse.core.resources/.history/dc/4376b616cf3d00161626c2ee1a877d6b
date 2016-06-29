package mapping.intermediate.demo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Maps a collection of {@link String}s (basic) as a {@link Set}.
 * <p>
 * Note how {@link ElementCollection} does not have an attribute
 * for cascading operations. It does have an attribute to specify
 * the fetch type.
 */
@Entity
public class Employee {
	
	@Id
	private Long id;

	private String name;

	@ElementCollection
	private Set<String> nickNames;

	public Employee() {
		this.nickNames = new HashSet<>();
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

	public Set<String> getNickNames() {
		return Collections.unmodifiableSet(nickNames);
	}

	public boolean addNickName(String nickName) {
		return nickNames.add(nickName);
	}

	public boolean removeNickName(String nickName) {
		return nickNames.remove(nickName);
	}

}
