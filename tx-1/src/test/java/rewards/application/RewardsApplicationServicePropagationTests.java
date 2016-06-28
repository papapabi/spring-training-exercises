package rewards.application;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import common.Money;

@ContextConfiguration(locations="classpath:/rewards/system-test-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RewardsApplicationServicePropagationTests {

	@Autowired
	private RewardsApplicationService rewardsService;

	private String merchantNumber;
	private String cardNumber;

	@Autowired
	private PlatformTransactionManager transactionManager;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setUp() throws Exception {
		merchantNumber = "1115558888";
		cardNumber = "1234567890";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Autowired
	public void initJdbcTemplate(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// TODO 4a: This test passes when the #rewardAccountFor() is @Transaction and has propagation REQUIRED (default)
	@Test
	public void testPropagation() throws Exception {
		// Open a transaction for testing
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		doRewardForPurchase();
		transactionManager.rollback(status);
		// TODO 4b: The assertion below fails when #rewardAccountFor() uses a NEW transaction via Propagation.REQUIRES_NEW
		// Try changing and see what happens
		// Check that rewards were not inserted
		assertEquals(0, (int) jdbcTemplate.queryForObject("SELECT count(*) FROM t_reward", Integer.class));
	}

	private void doRewardForPurchase() {
		Purchase purchase = new Purchase(
				new Money(100.0), merchantNumber, cardNumber);
		rewardsService.rewardAccountFor(purchase);
	}

}
