/**
 * Contains Hibernate-specific user-defined mapping. As such, it is not portable
 * to other JPA providers.
 */
@TypeDefs({
	@TypeDef(typeClass = PersistentMonthDayAsIntegers.class,
			defaultForType = MonthDay.class)
})
package mapping.hibernate.usertype;

// TODO 04f: Study how the custom mapping is added to Hibernate's configuration

import java.time.MonthDay;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
