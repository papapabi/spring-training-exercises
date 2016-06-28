package rewards.domain.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public final class RewardPoints {

	public static final RewardPoints ZERO = new RewardPoints(BigDecimal.ZERO);

	private BigDecimal value;

	public RewardPoints(BigDecimal value) {
		if (value == null
				|| (value.compareTo(BigDecimal.ZERO) < 0)) {
			throw new IllegalArgumentException(
					"Value cannot be null, and must be greater than or equal to zero");
		}
		this.value = value;
	}

	public RewardPoints(double value) {
		this(BigDecimal.valueOf(value));
	}

	public BigDecimal getValue() {
		return value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RewardPoints [value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		RewardPoints other = (RewardPoints) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (value.compareTo(other.value) != 0)
			return false;
		return true;
	}

	@SuppressWarnings("unused")
	private RewardPoints() {
		//used by persistence layer
	}
}
