package querying.intermediate.party;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("EMAIL")
public class Email extends ContactMechanism {

	private String address;

	public Email(Party party) {
		super(party);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	protected Email() { /* as needed by ORM/JPA */ }

}
