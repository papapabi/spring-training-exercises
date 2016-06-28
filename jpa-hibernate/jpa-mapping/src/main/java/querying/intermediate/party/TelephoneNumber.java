package querying.intermediate.party;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("TELE")
public class TelephoneNumber extends ContactMechanism {

	@ManyToOne
	private Country country;
	private String areaCode;
	private String number;

	public TelephoneNumber(Party party) {
		super(party);
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	protected TelephoneNumber() { /* as needed by ORM/JPA */ }

}
