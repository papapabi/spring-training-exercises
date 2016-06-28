package rewards.application;

import org.springframework.transaction.annotation.Transactional;

import rewards.domain.model.Account;
import rewards.domain.model.AccountRepository;
import rewards.domain.model.Merchant;
import rewards.domain.model.MerchantRepository;
import rewards.domain.model.RewardPoints;
import rewards.domain.model.RewardRepository;

public class RewardsApplicationServiceImpl implements RewardsApplicationService {

	private AccountRepository accountRepository;
	private MerchantRepository merchantRepository;
	private RewardRepository rewardRepository;

	public RewardsApplicationServiceImpl(
			AccountRepository accountRepository,
			MerchantRepository merchantRepository,
			RewardRepository rewardRepository) {
		super();
		this.accountRepository = accountRepository;
		this.merchantRepository = merchantRepository;
		this.rewardRepository = rewardRepository;
	}

	@Override
	public RewardConfirmation rewardAccountFor(Purchase purchase) {
		// 1. Retrieve the account by cardNumber
		Account account = accountRepository.findByCardNumber(purchase.getCardNumber());
		// 2. Retrieve the merchant by number
		Merchant merchant = merchantRepository.findByNumber(purchase.getMerchantNumber());
		// 3. Calculate reward for purchase (based on merchant's policies)
		RewardPoints pointsEarned = merchant.calculateRewardPointsFor(purchase.getAmount());
		// 4. Credit the account
		account.credit(pointsEarned);
		String confirmationNumber = rewardRepository.confirmReward(
				account.getNumber(),
				purchase.getCardNumber(),
				purchase.getMerchantNumber(),
				purchase.getAmount(), purchase.getDate(),
				pointsEarned);
		return new RewardConfirmation(
				confirmationNumber,
				account.getNumber(),
				pointsEarned.getValue(),
				account.getTotalPoints());
	}

}
