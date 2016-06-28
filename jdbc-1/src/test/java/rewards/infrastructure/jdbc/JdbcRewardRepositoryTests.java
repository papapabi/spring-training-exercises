package rewards.infrastructure.jdbc;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import rewards.domain.model.RewardPoints;

import common.Money;

public class JdbcRewardRepositoryTests {

	private JdbcRewardRepository rewardRepository;
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Before
	public void setUp() throws Exception {
		dataSource = createTestDataSource();
		rewardRepository = new JdbcRewardRepository(dataSource);
		// TODO 0: Initialize the JDBC template with a datasource
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateReward() throws Exception {
		String accountNumber = "8861888";
		String cardNumber = "1234567890";
		String merchantNumber = "1115558888";
		Money purchaseAmount = new Money(100.0);
		Date purchaseDate = new Date();
		RewardPoints pointsEarned = new RewardPoints(2.0);
		String confirmationNumber = rewardRepository.confirmReward(
				accountNumber, cardNumber, merchantNumber, purchaseAmount, purchaseDate, pointsEarned);
		assertNotNull(confirmationNumber);
		verifyInsertedValues(confirmationNumber);
	}

	private void verifyInsertedValues(String confirmationNumber) throws Exception {
		assertEquals(1, getRowCount());
		// TODO 2: Use JdbcTemplate to query the inserted row and return a map of fields
		Map<String, Object> values = null;

		assertEquals("8861888", values.get("ACCOUNT_NUMBER"));
		assertEquals("1234567890", values.get("CARD_NUMBER"));
		assertEquals("1115558888", values.get("MERCHANT_NUMBER"));
		assertEquals(2.0, values.get("REWARD_POINTS"));
	}

	private int getRowCount() {
		// TODO 1: Use JdbcTemplate to query the number of rows in T_REWARD
		return -1;
	}

	private DataSource createTestDataSource() {
		return new EmbeddedDatabaseBuilder()
			.setName("rewards")
			.addScript("/rewards/testdb/schema.sql")
			.addScript("/rewards/testdb/test-data.sql")
			.build();
	}

}
