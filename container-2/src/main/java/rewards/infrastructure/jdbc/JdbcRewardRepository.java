package rewards.infrastructure.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import common.Money;

import rewards.domain.model.RewardPoints;
import rewards.domain.model.RewardRepository;

public class JdbcRewardRepository implements RewardRepository {

	private DataSource dataSource;

	public JdbcRewardRepository() {
	}

	// TODO 8d: Add annotation to make this property required
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final String SQL_INSERT_REWARD =
			"INSERT INTO T_REWARD (confirmation_number, reward_points, reward_date, account_number, card_number, merchant_number, purchase_amount, purchase_date)"
					+ " VALUES (?, ?, TODAY, ?, ?, ?, ?, ?)";
	@Override
	public String confirmReward(String accountNumber, String cardNumber,
			String merchantNumber, Money purchaseAmount, Date purchaseDate,
			RewardPoints pointsEarned) {
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
	}

	private static final String SQL_NEXT_CONFIRMATION_NUMBER =
			"select next value for S_REWARD_CONFIRMATION_NUMBER from DUAL_REWARD_CONFIRMATION_NUMBER";
	private String nextConfirmationNumber() {
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
	}

}
