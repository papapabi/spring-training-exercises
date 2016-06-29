package mapping.hibernate.usertype.demo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;

/**
 * Persists the decimal amount and currency from a MonetaryAmount instance.
 */
public class PersistentMonetaryAmountAsAmountAndCurrency implements CompositeUserType {

	@Override
	public String[] getPropertyNames() {
		// Ordering is important!
		// It must match the order the columns are defined in the property mapping.
		return new String[] { "currency", "amount" };
	}

	@Override
	public Type[] getPropertyTypes() {
		return new Type[] { StringType.INSTANCE, BigDecimalType.INSTANCE };
	}

	@Override
	public Object getPropertyValue(Object component, int propertyIndex) throws HibernateException {
		if (component != null) {
			MonetaryAmount monetaryAmount = (MonetaryAmount) component;
			switch (propertyIndex) {
			case 0:
				return monetaryAmount.getCurrency().getCurrencyCode();
			case 1:
				return monetaryAmount.getNumber().numberValue(BigDecimal.class);
			default:
				throw new HibernateException(
						"Invalid property index [" + propertyIndex + "]" );
			}
		}
		return null;
	}

	@Override
	public void setPropertyValue(Object component, int propertyIndex, Object value) throws HibernateException {
		// This should not be called if #isMutable() returns false.
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class returnedClass() {
		return MonetaryAmount.class;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
    	if ((x == null) && (y == null)) {
    		return true;
    	}
        if ((x == null) || (y == null)) {
            return false;
        }
        return x.equals(y);
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		assert names.length == 2;
		String currencyCode = (String) StringType.INSTANCE.get(rs, names[0], session);
		CurrencyUnit currencyUnit = Monetary.getCurrency(currencyCode);
		BigDecimal amount = (BigDecimal) BigDecimalType.INSTANCE.get(rs, names[1], session);
		if (currencyUnit == null || amount == null) {
			return null;
		}
		return Monetary.getDefaultAmountFactory()
				.setCurrency(currencyUnit)
				.setNumber(amount)
				.create();
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
			throws HibernateException, SQLException {
		if (value == null) {
			StringType.INSTANCE.set(st, null, index, session);
			BigDecimalType.INSTANCE.set(st, null, index + 1, session);
		} else {
			MonetaryAmount monetaryAmount = (MonetaryAmount) value;
			StringType.INSTANCE.set(
					st, monetaryAmount.getCurrency().getCurrencyCode(), index, session);
			BigDecimalType.INSTANCE.set(
					st, monetaryAmount.getNumber().numberValue(BigDecimal.class), index + 1, session);
		}
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value; // since value is immutable
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(Object value, SessionImplementor session) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public Object assemble(Serializable cached, SessionImplementor session, Object owner) throws HibernateException {
		return cached;
	}

	@Override
	public Object replace(Object original, Object target, SessionImplementor session, Object owner)
			throws HibernateException {
		return original;
	}

}
