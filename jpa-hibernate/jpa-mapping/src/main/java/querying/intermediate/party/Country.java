package querying.intermediate.party;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country {

	@Id
	private String iso2;

	@Column(unique=true)
	private String iso3;
	
	private String name;

	public String getIso3() {
		return iso3;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIso2() {
		return iso2;
	}

}
