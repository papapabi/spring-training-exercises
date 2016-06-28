package rewards.domain.model;

import static javax.persistence.GenerationType.AUTO;
import static org.apache.commons.lang.builder.ToStringBuilder.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import rewards.domain.model.embeddable.AccountDetails;

@Entity
@Table(name = "T_ACCOUNT")
@Access(AccessType.FIELD)
public class Account {

	@Id
	@GeneratedValue(strategy = AUTO)
	private Long id;
	
	@Embedded
	@AttributeOverride(name = "acctNumber", column=@Column(name="number"))
	private AccountDetails accountDetails;

	private String name;

	@Column(name = "total_points")
	private BigDecimal totalPoints;
			
	@OneToMany
	@JoinColumn(name = "account_id")
	private Set<Card> cards = new HashSet<>();

	public Account(String number, String name) {
		this(number, name, BigDecimal.ZERO);
	}

	public Account(String number, String name, BigDecimal points) {
		super();
		this.accountDetails = new AccountDetails();
		this.accountDetails.setAcctNumber(number);
		this.name = name;
		this.totalPoints = points;
	}
	
	public Long getId() {
	  return id;
	}

	public void credit(RewardPoints points) {
		totalPoints = totalPoints.add(points.getValue());
	}

	public AccountDetails getAccountDetails() {
		return accountDetails;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getTotalPoints() {
		return totalPoints;
	}

	public boolean addCard(String cardNumber) {
		return cards.add(new Card(cardNumber));
	}
	
	public Set<Card> getCards() {
		return Collections.unmodifiableSet(cards);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountDetails == null) ? 0 : accountDetails.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((totalPoints == null) ? 0 : totalPoints.hashCode());
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
		Account other = (Account) obj;
		if (accountDetails == null) {
			if (other.accountDetails != null)
				return false;
		} else if (!accountDetails.equals(other.accountDetails))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (totalPoints == null) {
			if (other.totalPoints != null)
				return false;
		} else if (!totalPoints.equals(other.totalPoints))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return reflectionToString(this);
	}
	
	@SuppressWarnings("unused")
	private Account() {
//		 required by persistence layer
	}

	
}
