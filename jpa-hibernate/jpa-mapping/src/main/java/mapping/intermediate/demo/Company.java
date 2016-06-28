package mapping.intermediate.demo;

import java.util.Map;

import javax.persistence.*;

@Entity
public class Company {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String name;

	@OneToMany // uni-directional (one-to-many VicePresident entities)
	@JoinTable(name="company_organization", // defaults to "Company_VicePresident" (one-to-many)
		inverseJoinColumns={
			@JoinColumn(name="vicepresident_id")
			// defaults to "organization_id" (attribute name + ID attribute of target entity)
		}) 
	@MapKeyJoinColumn(name="division_id") // defaults to "organization_KEY" (attribute name + "_KEY")
	private Map<Division, VicePresident> organization;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
