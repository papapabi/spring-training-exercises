package querying.intermediate.party;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ORG")
public class Org extends Party {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
