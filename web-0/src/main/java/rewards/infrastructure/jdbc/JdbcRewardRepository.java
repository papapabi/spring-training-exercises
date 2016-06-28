package rewards.infrastructure.jdbc;

import java.util.Date;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import common.Money;

import rewards.domain.model.RewardPoints;
import rewards.domain.model.RewardRepository;

public class JdbcRewardRepository implements RewardRepository {

	// private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;

	public JdbcRewardRepository(DataSource dataSource) {
		// this.dataSource = dataSource;
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
		/*
		try {
			Connection conn = dataSource.getConnection();
			try {
				PreparedStatement ps = conn.prepareStatement(SQL_INSERT_REWARD);
				try {
					String nextConfirmationNumber = nextConfirmationNumber();
					ps.setString(1, nextConfirmationNumber);
					ps.setDouble(2, pointsEarned.getValue().doubleValue());
					ps.setString(3, accountNumber);
					ps.setString(4, cardNumber);
					ps.setString(5, merchantNumber);
					ps.setDouble(6, purchaseAmount.getValue().doubleValue());
					ps.setDate(7, new java.sql.Date(purchaseDate.getTime()));
					ps.execute();
					return nextConfirmationNumber;
				} finally {
					ps.close();
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			// Interface defines no throws clause,
			// but SQLException is not a RuntimeException.
			// So, we wrap it, and comply with the interface.
			throw new RuntimeException(
					"SQL exception inserting reward", e);
		}
		*/
	}

	private static final String SQL_NEXT_CONFIRMATION_NUMBER =
			"select next value for S_REWARD_CONFIRMATION_NUMBER from DUAL_REWARD_CONFIRMATION_NUMBER";
	private String nextConfirmationNumber() {
		return jdbcTemplate.queryForObject(SQL_NEXT_CONFIRMATION_NUMBER, String.class);
		/*
		try {
			Connection conn = dataSource.getConnection();
			try {
				PreparedStatement ps = conn.prepareStatement(SQL_NEXT_CONFIRMATION_NUMBER);
				ResultSet rs = ps.executeQuery();
				try {
					rs.next();
					return rs.getString(1);
				} finally {
					rs.close();
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			// Interface defines no throws clause,
			// but SQLException is not a RuntimeException.
			// So, we wrap it, and comply with the interface.
			throw new RuntimeException(
					"SQL exception getting next confirmation number", e);
		}
		*/
	}

}
