package rewards.infrastructure.jdbc;

import java.util.Date;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import rewards.domain.model.RewardPoints;
import rewards.domain.model.RewardRepository;

import common.Money;

public class JdbcRewardRepository implements RewardRepository {

	private JdbcTemplate jdbcTemplate;

	public JdbcRewardRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final String SQL_INSERT_REWARD =
			"INSERT INTO T_REWARD (confirmation_number, reward_points, reward_date, account_number, card_number, merchant_number, purchase_amount, purchase_date)"
					+ " VALUES (?, ?, TODAY, ?, ?, ?, ?, ?)";
	@Override
	public String confirmReward(String accountNumber, String cardNumber,
			String merchantNumber, Money purchaseAmount, Date purchaseDate,
			RewardPoints pointsEarned) {
		String nextConfirmationNumber = nextConfirmationNumber();
		jdbcTemplate.update(SQL_INSERT_REWARD,
				nextConfirmationNumber,
				pointsEarned.getValue().doubleValue(),
				accountNumber,
				cardNumber,
				merchantNumber,
				purchaseAmount.getValue().doubleValue(),
				new java.sql.Date(purchaseDate.getTime()));
		return nextConfirmationNumber;
	}

	private static final String SQL_NEXT_CONFIRMATION_NUMBER =
			"select next value for S_REWARD_CONFIRMATION_NUMBER from DUAL_REWARD_CONFIRMATION_NUMBER";
	private String nextConfirmationNumber() {
		return jdbcTemplate.queryForObject(SQL_NEXT_CONFIRMATION_NUMBER, String.class);
	}

}
