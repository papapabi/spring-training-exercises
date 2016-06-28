package rewards.domain.model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Account {

	private String number;
	private String name;
	private BigDecimal totalPoints;
	private Set<Card> cards = new HashSet<>();

	public Account(String number, String name) {
		this(number, name, BigDecimal.ZERO);
	}

	public Account(String number, String name, BigDecimal points) {
		super();
		this.number = number;
		this.name = name;
		this.totalPoints = points;
	}

	public void credit(RewardPoints points) {
		totalPoints = totalPoints.add(points.getValue());
	}

	public String getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getTotalPoints() {
		return totalPoints;
	}

	public boolean addCard(String cardNumber) {
		return cards.add(new Card(cardNumber));
	}
	
	public Set<Card> getCards() {
		return Collections.unmodifiableSet(cards);
	}

	@SuppressWarnings("unused")
	private Account() {
		// required by persistence layer
	}

}
