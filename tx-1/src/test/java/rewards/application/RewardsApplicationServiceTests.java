package rewards.application;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import common.Money;

@ContextConfiguration(locations="classpath:/rewards/system-test-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RewardsApplicationServiceTests {

	@Autowired
	private RewardsApplicationService rewardsService;

	private String merchantNumber;
	private String cardNumber;

	@Before
	public void setUp() throws Exception {
		merchantNumber = "1115558888";
		cardNumber = "1234567890";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRewardForPurchase() {
		Purchase purchase = new Purchase(
				new Money(100.0), merchantNumber, cardNumber);
		RewardConfirmation rewardConfirmation = rewardsService.rewardAccountFor(purchase);

		// assert the expected reward confirmation results
		assertNotNull(rewardConfirmation);
		assertNotNull(rewardConfirmation.getConfirmationNumber());

		// assert that reward is given to "8861888"
		assertEquals("8861888", rewardConfirmation.getAccountNumber());

		// assert reward of 2 points (50 bucks per point)
		assertTrue(rewardConfirmation.getPointsEarned().compareTo(
				new BigDecimal("2")) == 0);
		// Two BigDecimal objects that are equal in value but have a
		// different scale (like 2.0 and 2.00) are considered equal by
		// the #compareTo() method.
		// assertEquals(new BigDecimal("2.00"), rewardConfirmation.getPointsEarned());
	}

}
