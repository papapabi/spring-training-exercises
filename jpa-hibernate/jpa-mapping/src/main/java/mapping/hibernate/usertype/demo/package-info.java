/**
 * This is for the instructor to use as a demo, and not for
 * participants/students.
 */
@TypeDefs({
	@TypeDef(typeClass = PersistentMonthAsInteger.class,
			defaultForType = Month.class),
	@TypeDef(typeClass = PersistentYearAsInteger.class,
			defaultForType = Year.class),
	@TypeDef(typeClass = PersistentMonetaryAmountAsAmountAndCurrency.class,
			defaultForType = MonetaryAmount.class),
})
package mapping.hibernate.usertype.demo;

import java.time.Month;
import java.time.Year;

import javax.money.MonetaryAmount;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import mapping.hibernate.usertype.demo.PersistentMonthAsInteger;
import mapping.hibernate.usertype.demo.PersistentYearAsInteger;
import mapping.hibernate.usertype.demo.PersistentMonetaryAmountAsAmountAndCurrency;
