package rewards.application;

import java.math.BigDecimal;

public class RewardConfirmation {

	private final String confirmationNumber;
	private final String accountNumber;
	private final BigDecimal pointsEarned;
	private final BigDecimal totalPoints;

	public RewardConfirmation(
			String confirmationNumber,
			String accountNumber,
			BigDecimal pointsEarned, BigDecimal totalPoints) {
		super();
		this.confirmationNumber = confirmationNumber;
		this.accountNumber = accountNumber;
		this.pointsEarned = pointsEarned;
		this.totalPoints = totalPoints;
	}

	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public BigDecimal getPointsEarned() {
		return pointsEarned;
	}

	public BigDecimal getTotalPoints() {
		return totalPoints;
	}

}
