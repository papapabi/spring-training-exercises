package common;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MoneyTests {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=IllegalArgumentException.class)
	public void requiresCurrency() throws Exception {
		new Money((String) null, 100.0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void requiresNonEmptyCurrency() throws Exception {
		new Money("", 100.0);
	}

	@Test
	public void equals() throws Exception {
		assertEquals(new Money(2.0), new Money(2.00));
	}

	@Test
	public void add() throws Exception {
		assertEquals(new Money(100.0),
				new Money(50.0).add(new Money(50.0)));
	}

	@Test(expected=IllegalArgumentException.class)
	public void doesNotAllowAddingDifferentCurrencies() throws Exception {
		new Money("USD", 50.0).add(new Money("EUR", 50.0));
	}

	@Test
	public void subtract() throws Exception {
		assertEquals(new Money(40.0),
				new Money(50.0).subtract(new Money(10.0)));
	}

	@Test(expected=IllegalArgumentException.class)
	public void doesNotAllowSubtractingDifferentCurrencies() throws Exception {
		new Money("USD", 50.0).subtract(new Money("EUR", 50.0));
	}

	@Test
	public void multiply() throws Exception {
		assertEquals(new Money(100.0),
				new Money(50.0).multiplyBy(2));
	}

	@Test
	public void divide() throws Exception {
		assertEquals(new Money(50.0),
				new Money(100.0).divideBy(2));
	}

}
