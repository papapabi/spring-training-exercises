package rewards.domain.model.embeddable;

import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;

import javax.persistence.Embeddable;

@Embeddable
public class AccountDetails {

	private String acctNumber;

	public void setAcctNumber(String number) {
		this.acctNumber = number;
	}
	
	public String getAcctNumber() {
		return acctNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acctNumber == null) ? 0 : acctNumber.hashCode());
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
		AccountDetails other = (AccountDetails) obj;
		if (acctNumber == null) {
			if (other.acctNumber != null)
				return false;
		} else if (!acctNumber.equals(other.acctNumber))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return reflectionToString(this);
	}

}
