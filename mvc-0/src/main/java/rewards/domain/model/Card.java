package rewards.domain.model;

import static javax.persistence.GenerationType.AUTO;
import static org.apache.commons.lang.builder.EqualsBuilder.*;
import static org.apache.commons.lang.builder.HashCodeBuilder.*;
import static org.apache.commons.lang.builder.ToStringBuilder.*;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import rewards.domain.model.embeddable.CardDetails;

@Entity
@Table(name = "T_ACCOUNT_CARD")
@Access(AccessType.FIELD)
public class Card {

	@Id
	@GeneratedValue(strategy = AUTO)
	private Long id;
	
	@Embedded
	private CardDetails cardDetails;
	
	public Card(String number) {
		if (number == null || number.isEmpty()) {
			throw new IllegalArgumentException("Card number cannot be null");
		}
		// To keep things simple, no MOD-10 check is done.
		this.cardDetails = new CardDetails();
		this.cardDetails.setNumber(number);
	}

	public Long getId() {
	  return id;
	}
	
	public CardDetails getCardDetails() {
		return cardDetails;
	}
	
	@Override
	public int hashCode() {
		return reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return reflectionToString(this);
	}
	
	@SuppressWarnings("unused")
	private Card() {
		//required by persistence layer
	}

	

}
