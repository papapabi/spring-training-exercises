package rewards.application;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import common.Money;

// TODO 0: Create a Spring Bean Configuration File in src/main/resources/rewards, name it app-config.xml.
// TODO 1: In app-config.xml, define a bean of class RewardsApplicationServiceImpl.
//
// TODO 2: In app-config.xml, to supply the needed repositories, define 3 more beans:
// TODO 2a: JdbcAccountRepository
// TODO 2b: JdbcMerchantRepository
// TODO 2c: JdbcRewardRepository
public class RewardsApplicationServiceTests {

	private RewardsApplicationService rewardsService;

	private String merchantNumber;
	private String cardNumber;

	private ApplicationContext context;

	@Before
	public void setUp() throws Exception {
		merchantNumber = "1115558888";
		cardNumber = "1234567890";

		// TODO 3: Create a context using ClassPathXmlApplicationContext
		// and two configuration files in your class path:
		// 1. app-config.xml
		// 2. test-infrastructure-config.xml.

		// TODO 4: Ask the context to get a bean of type RewardsApplicationService (defined in a previous step)
		// TODO 5: Run this test to check if all is well
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

	@Test
	public void testZeroRewardForPurchaseBelowMinimumAmount() {
		Purchase purchase = new Purchase(
				new Money(75.0), merchantNumber, cardNumber);
		RewardConfirmation rewardConfirmation = rewardsService.rewardAccountFor(purchase);

		// assert the expected reward confirmation results
		assertNotNull(rewardConfirmation);
		assertNotNull(rewardConfirmation.getConfirmationNumber());

		// assert that reward is given to "8861888"
		assertEquals("8861888", rewardConfirmation.getAccountNumber());

		// assert reward of 0 points (since less than 100.0 minimum amount purchase)
		assertEquals(BigDecimal.ZERO, rewardConfirmation.getPointsEarned());
	}

	@Test(expected=Exception.class)
	public void testAccountNotFound() {
		Purchase purchase = new Purchase(
				new Money(100.0), merchantNumber, "NON-EXISTENT CARD");
		rewardsService.rewardAccountFor(purchase);
	}

	@Test(expected=Exception.class)
	public void testMerchantNotFound() {
		Purchase purchase = new Purchase(
				new Money(100.0), "NON-EXISTENT MERCHANT", cardNumber);
		rewardsService.rewardAccountFor(purchase);
	}

}
