package rewards.infrastructure.jpa;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import common.Money;
import rewards.domain.model.Reward;
import rewards.domain.model.RewardPoints;
import rewards.domain.model.RewardRepository;
import rewards.domain.model.embeddable.AccountDetails;
import rewards.domain.model.embeddable.CardDetails;
import rewards.domain.model.embeddable.MerchantDetails;

@Repository
public class JpaRewardRepository implements RewardRepository {
	
	private static final String SQL_NEXT_CONFIRMATION_NUMBER =
			"select next value for S_REWARD_CONFIRMATION_NUMBER from DUAL_REWARD_CONFIRMATION_NUMBER";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String confirmReward(String accountNumber, String cardNumber,
			String merchantNumber, Money purchaseAmount, Date purchaseDate,
			RewardPoints pointsEarned) {

		AccountDetails accountDetails = createAccountDetails(accountNumber);
		CardDetails cardDetails = createCardDetails(cardNumber);
		MerchantDetails merchantDetails = createMerchantDetails(merchantNumber);
		
		String confirmationNumber = nextConfirmationNumber();
		Reward reward = new Reward(pointsEarned, confirmationNumber, accountDetails, cardDetails, merchantDetails, purchaseAmount.getValue(), purchaseDate);
		//TODO 13: Using the EntityManager, save the newly created Reward object.
		
		return confirmationNumber;
	}

	private MerchantDetails createMerchantDetails(String merchantNumber) {
		MerchantDetails merchantDetails = new MerchantDetails();
		merchantDetails.setNumber(merchantNumber);
		return merchantDetails;
	}

	private CardDetails createCardDetails(String cardNumber) {
		CardDetails cardDetails = new CardDetails();
		cardDetails.setNumber(cardNumber);
		return cardDetails;
	}

	private AccountDetails createAccountDetails(String accountNumber) {
		AccountDetails accountDetails = new AccountDetails();
		accountDetails.setAcctNumber(accountNumber);
		return accountDetails;
	}
	
	private String nextConfirmationNumber() {
		//TODO 14: Using the EntityManager, reuse the query to select the next sequence number from the database
		return null;
	}

}
