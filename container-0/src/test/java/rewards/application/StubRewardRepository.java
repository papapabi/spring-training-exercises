package rewards.application;

import java.util.Date;
import java.util.Random;

import common.Money;

import rewards.domain.model.RewardPoints;
import rewards.domain.model.RewardRepository;

public class StubRewardRepository implements RewardRepository {

	@Override
	public String confirmReward(String accountNumber, String cardNumber,
			String merchantNumber, Money purchaseAmount, Date purchaseDate,
			RewardPoints pointsEarned) {
		return new Random().toString();
	}

}
