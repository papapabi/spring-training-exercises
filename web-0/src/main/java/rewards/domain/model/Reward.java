package rewards.domain.model;

import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import rewards.domain.model.embeddable.AccountDetails;
import rewards.domain.model.embeddable.CardDetails;
import rewards.domain.model.embeddable.MerchantDetails;

@Entity
@Table(name = "T_REWARD")
@Access(AccessType.FIELD)
public class Reward {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name="confirmation_number")
	private String confirmationNumber;

	@Embedded
	@AttributeOverride(name = "value", column=@Column(name = "reward_points"))
	private RewardPoints rewardPoints;

	@Column(name = "reward_date")
	private Date rewardDate;

	@Embedded
	@AttributeOverride(name = "acctNumber", column=@Column(name = "account_number"))
	private AccountDetails accountDetails;

	@Embedded
	@AttributeOverride(name = "number", column=@Column(name = "card_number"))
	private CardDetails cardDetails;
	
	@Embedded
	@AttributeOverride(name = "number", column=@Column(name = "merchant_number"))
	private MerchantDetails merchantDetails;
	
	@Column(name = "purchase_amount")
	private BigDecimal purchaseAmount;
	
	@Column(name = "purchase_date")
	private Date purchaseDate;
	
	public Reward(RewardPoints rewardPoints, String confirmationNumber,
			AccountDetails accountDetails, CardDetails cardDetails,
			MerchantDetails merchantDetails, BigDecimal purchaseAmount, Date purchaseDate) {
		super();		
		this.rewardPoints = rewardPoints;
		this.confirmationNumber = confirmationNumber;
		this.accountDetails = accountDetails;
		this.cardDetails = cardDetails;
		this.merchantDetails = merchantDetails;
		this.purchaseAmount = purchaseAmount;
		this.purchaseDate = purchaseDate;
		this.rewardDate = new Date();
	}

	public RewardPoints getRewardPoints() {
		return rewardPoints;
	}

	public Date getRewardDate() {
		return rewardDate;
	}

	public AccountDetails getAccountDetails() {
		return accountDetails;
	}

	public CardDetails getCardDetails() {
		return cardDetails;
	}

	public MerchantDetails getMerchantDetails() {
		return merchantDetails;
	}

	public BigDecimal getPurchaseAmount() {
		return purchaseAmount;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountDetails == null) ? 0 : accountDetails.hashCode());
		result = prime * result
				+ ((cardDetails == null) ? 0 : cardDetails.hashCode());
		result = prime
				* result
				+ ((confirmationNumber == null) ? 0 : confirmationNumber
						.hashCode());
		result = prime * result
				+ ((merchantDetails == null) ? 0 : merchantDetails.hashCode());
		result = prime * result
				+ ((purchaseAmount == null) ? 0 : purchaseAmount.hashCode());
		result = prime * result
				+ ((purchaseDate == null) ? 0 : purchaseDate.hashCode());
		result = prime * result
				+ ((rewardDate == null) ? 0 : rewardDate.hashCode());
		result = prime * result
				+ ((rewardPoints == null) ? 0 : rewardPoints.hashCode());
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
		Reward other = (Reward) obj;
		if (accountDetails == null) {
			if (other.accountDetails != null)
				return false;
		} else if (!accountDetails.equals(other.accountDetails))
			return false;
		if (cardDetails == null) {
			if (other.cardDetails != null)
				return false;
		} else if (!cardDetails.equals(other.cardDetails))
			return false;
		if (confirmationNumber == null) {
			if (other.confirmationNumber != null)
				return false;
		} else if (!confirmationNumber.equals(other.confirmationNumber))
			return false;
		if (merchantDetails == null) {
			if (other.merchantDetails != null)
				return false;
		} else if (!merchantDetails.equals(other.merchantDetails))
			return false;
		if (purchaseAmount == null) {
			if (other.purchaseAmount != null)
				return false;
		} else if (!purchaseAmount.equals(other.purchaseAmount))
			return false;
		if (purchaseDate == null) {
			if (other.purchaseDate != null)
				return false;
		} else if (!purchaseDate.equals(other.purchaseDate))
			return false;
		if (rewardDate == null) {
			if (other.rewardDate != null)
				return false;
		} else if (!rewardDate.equals(other.rewardDate))
			return false;
		if (rewardPoints == null) {
			if (other.rewardPoints != null)
				return false;
		} else if (!rewardPoints.equals(other.rewardPoints))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return reflectionToString(this);
	}
	
	@SuppressWarnings("unused")
	private Reward() {
		
	}
	
	
}
