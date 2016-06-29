package querying.intermediate.party;

import javax.persistence.*;

@Entity
@Inheritance //(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="type", length=5)
public abstract class ContactMechanism {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="partyId", nullable=false, updatable=false)
	private Party party;
	
	public ContactMechanism(Party party) {
		if (party == null) {
			throw new IllegalArgumentException(
					"Party cannot be null;"
					+ " Contact mechanism must belong to a party");
		}
		this.party = party;
	}

	public Long getId() {
		return id;
	}
	
	protected ContactMechanism() { /* as needed by ORM/JPA */ }

}
