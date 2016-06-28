package rewards.domain.model;

public class Card {

	private String number;

	public Card(String number) {
		if (number == null || number.isEmpty()) {
			throw new IllegalArgumentException("Card number cannot be null");
		}
		// To keep things simple, no MOD-10 check is done.
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	@SuppressWarnings("unused")
	private Card() {
		// required by persistence layer
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		Card other = (Card) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

}
