package rewards.infrastructure.jdbc;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.ReflectionUtils;

import rewards.domain.model.Account;
import rewards.domain.model.AccountRepository;
import rewards.domain.model.Card;

public class JdbcAccountRepository implements AccountRepository {
	
	private final DataSource dataSource;

	public JdbcAccountRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final String SQL_FINDBY_CARDNUMBER =
			"SELECT a.ID AS ID, a.NUMBER AS NUMBER, a.NAME AS NAME, a.TOTAL_POINTS AS TOTAL_POINTS,"
					+ " b.NUMBER AS CARD_NUMBER, b.ID AS CARD_ID"
					+ " FROM T_ACCOUNT a, T_ACCOUNT_CARD c"
					+ " LEFT OUTER JOIN T_ACCOUNT_CARD b ON a.ID = b.ACCOUNT_ID"
					+ " WHERE c.NUMBER = ? AND c.ACCOUNT_ID = a.ID";

	@Override
	public Account findByCardNumber(String cardNumber) {
		try {
			Connection conn = dataSource.getConnection();
			try {
				PreparedStatement ps = conn.prepareStatement(SQL_FINDBY_CARDNUMBER);
				try {
					ps.setString(1, cardNumber);
					ResultSet rs = ps.executeQuery();
					try {
						return mapAccount(rs);
					} finally {
						rs.close();
					}
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
					"SQL exception finding account by card number", e);
		}
	}

	private Account mapAccount(ResultSet rs) throws SQLException {
		Account account = null;
		Set<Card> cards = new HashSet<>();
		while (rs.next()) {
			if (account == null) {
				account = new Account(rs.getString("NUMBER"), rs.getString("NAME"));
				// To support updates, unique ID must be restored
				/*
				Field idField = ReflectionUtils.findField(Account.class, "id");
				ReflectionUtils.makeAccessible(idField);
				ReflectionUtils.setField(
						idField, account, rs.getLong("ID"));
				*/
			}
			cards.add(mapCard(rs));
		}
		if (account != null) {
			// Use reflection API to restore Set<Card> inside Account
			Field cardsField = ReflectionUtils.findField(Account.class, "cards");
			ReflectionUtils.makeAccessible(cardsField);
			ReflectionUtils.setField(cardsField, account, cards);
		}
		if (account == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return account;
	}

	private Card mapCard(ResultSet rs) throws SQLException {
		Card card = new Card(rs.getString("CARD_NUMBER"));
		// Use reflection API to restore Set<Card> inside Account
		/*
		ReflectionUtils.setField(
				ReflectionUtils.findField(Card.class, "id"), card, rs.getLong("CARD_ID"));
		*/
		return card;
	}

}
