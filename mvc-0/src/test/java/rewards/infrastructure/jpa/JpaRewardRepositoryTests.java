package rewards.infrastructure.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import rewards.domain.model.RewardPoints;
import rewards.domain.model.RewardRepository;
import common.Money;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
public class JpaRewardRepositoryTests {
	
	@Autowired
	private RewardRepository rewardRepository;
	
	@Autowired
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setUp() throws Exception {
		jdbcTemplate = new JdbcTemplate(dataSource);
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
		Map<String, Object> values = jdbcTemplate.queryForMap(
				"SELECT * FROM t_reward WHERE confirmation_number = ?", confirmationNumber);

		assertEquals("8861888", values.get("ACCOUNT_NUMBER"));
		assertEquals("1234567890", values.get("CARD_NUMBER"));
		assertEquals("1115558888", values.get("MERCHANT_NUMBER"));
		assertEquals(2.0, values.get("REWARD_POINTS"));
	}

	private int getRowCount() {
		return jdbcTemplate.queryForList("SELECT * FROM T_REWARD").size();
				
	}

}
