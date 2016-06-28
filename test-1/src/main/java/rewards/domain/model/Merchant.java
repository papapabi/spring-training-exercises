package rewards.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import common.Money;

public class Merchant {

	private String number;
	private String name;
	private BigDecimal amountPerPoint; // amount that earns a point
	private BigDecimal minimumAmount; // amount before earning points

	/**
	 * Creates a merchant with the given number, and name. The merchant,
	 * for a minimum purchase amount of 100 bucks, awards one point for
	 * 50 bucks spent.
	 * @param number Merchant number
	 * @param name Merchant name
	 */
	public Merchant(String number, String name) {
		// By default, 50 bucks earns a point.
		// But consumer needs to spend at least 100 bucks for it to earn points.
		// For example, consumer spends 75 bucks. This will not earn a single point.
		// Another example, consumer spends 250 bucks. This will earn 5 points.
		this(number, name,
				new BigDecimal("50.0"), new BigDecimal("100.0"));
	}

	/**
	 * Creates a merchant with the given number, name,
	 * amount to earn one reward point,
	 * minimum amount purchase (before points are awarded).
	 * @param number
	 * @param name
	 * @param amountPerPoint amount to earn one reward point
	 * @param minimumAmount minimum purchase amount before awarding points
	 */
	public Merchant(String number, String name,
			BigDecimal amountPerPoint, BigDecimal minimumAmount) {
		super();
		this.number = number;
		this.name = name;
		this.amountPerPoint = amountPerPoint;
		this.minimumAmount = minimumAmount;
	}

	public String getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getAmountPerPoint() {
		return amountPerPoint;
	}

	public BigDecimal getMinimumAmount() {
		return minimumAmount;
	}

	public RewardPoints calculateRewardPointsFor(Money amount) {
		if (amount.getValue().compareTo(minimumAmount) >= 0) {
			return new RewardPoints(
					amount.getValue().divide(amountPerPoint, RoundingMode.DOWN));
		}
		return RewardPoints.ZERO;
	}

	@SuppressWarnings("unused")
	private Merchant() {
		// required by persistence layer
	}

}
