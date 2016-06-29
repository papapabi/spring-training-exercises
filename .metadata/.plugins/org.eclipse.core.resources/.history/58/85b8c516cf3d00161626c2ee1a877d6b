package querying.intermediate.party;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
@Inheritance
@DiscriminatorColumn(name="type", length=3)
public abstract class Party {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(nullable=false)
	protected String name; // to be used by subclasses

	@OneToMany(mappedBy="party", orphanRemoval=true, cascade=CascadeType.ALL)
	private List<ContactMechanism> contactMechanisms = new LinkedList<>();

}
