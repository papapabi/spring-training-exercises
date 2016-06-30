package mapping.inheritance;

import javax.persistence.*;

// TODO 03: Mapping inheritance (Party, Person, Org)

// TODO 03a: Map the Party class as a persistent entity
// TODO 03b: Define the inheritance to be used for Party/Person/Org
@Entity
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy=InheritanceType.JOINED)
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS) // default but no harm in making it explicit
// TODO 03e1 (optional): Override the discriminator column name (as TYPE)
// if not overridden, the discriminator column name defaults to DTYPE
//@DiscriminatorColumn(name="TYPE", discriminatorType=DiscriminatorType.STRING)
public abstract class Party {

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;

	public Long getId() {
		return id;
	}

}
