package rewards.domain.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.Money;

public class MerchantTests {

	private Merchant merchant;

	@Before
	public void setUp() throws Exception {
		merchant = new Merchant(
				"1234567890", "Acme Supplies",
				new BigDecimal("50.0") /* 50 bucks earn one point */,
				new BigDecimal("500.0") /* minimum purchase amount */);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void rewardsPointsWithMinimumPurchaseAmount() {
		final RewardPoints TEN_POINTS = new RewardPoints(10.0);
		assertEquals(TEN_POINTS,
				merchant.calculateRewardPointsFor(new Money(500.0)));
	}

	@Test
	public void zeroPointsWhenBelowMinimumPurchaseAmount() {
		assertEquals(RewardPoints.ZERO,
				merchant.calculateRewardPointsFor(new Money(499.99)));
	}

}
