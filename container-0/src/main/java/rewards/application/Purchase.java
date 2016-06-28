package rewards.application;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import common.Money;

public class Purchase {

	private final Money amount;
	private final String merchantNumber;
	private final String cardNumber;
	private final Date date;

	public Purchase(
			Money amount, String merchantNumber, String cardNumber,
			Date date) {
		super();
		if (amount == null) {
			throw new IllegalArgumentException("Amount cannot be null");
		}
		if (merchantNumber == null || merchantNumber.isEmpty()) {
			throw new IllegalArgumentException("Merchant number cannot be null or empty");
		}
		if (cardNumber == null || cardNumber.isEmpty()) {
			throw new IllegalArgumentException("Card number cannot be null or empty");
		}
		this.amount = amount;
		this.merchantNumber = merchantNumber;
		this.cardNumber = cardNumber;
		if (date == null) {
			date = today();
		}
		this.date = date;
	}

	public Purchase(
			Money amount, String merchantNumber, String cardNumber) {
		this(amount, merchantNumber, cardNumber, today());
	}

	public final Money getAmount() {
		return amount;
	}

	public final String getMerchantNumber() {
		return merchantNumber;
	}

	public final String getCardNumber() {
		return cardNumber;
	}

	public final Date getDate() {
		return date;
	}

	private static Date today() {
		Calendar today = Calendar.getInstance(TimeZone.getDefault());
		today.set(Calendar.HOUR_OF_DAY, 0);
		return today.getTime();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((merchantNumber == null) ? 0 : merchantNumber.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Purchase other = (Purchase) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (merchantNumber == null) {
			if (other.merchantNumber != null)
				return false;
		} else if (!merchantNumber.equals(other.merchantNumber))
			return false;
		return true;
	}

}
