package mapping.hibernate.usertype.demo;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.Year;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.IntegerType;
import org.hibernate.usertype.UserType;

public class PersistentYearAsInteger implements UserType {

	@Override
	public int[] sqlTypes() {
		return new int[] { Types.INTEGER };
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class returnedClass() {
		return Year.class;
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
		Integer object = (Integer) IntegerType.INSTANCE.get(
				rs, names[0], session); // already handles null check
		return object == null ? null : Year.of(object);
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
			throws HibernateException, SQLException {
		if (value == null) {
			IntegerType.INSTANCE.set(st, null, index, session);
		} else {
			IntegerType.INSTANCE.set(
					st, ((Year) value).getValue(), index, session);
		}
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value; // since value is immutable
	}

	@Override
	public boolean isMutable() {
		return false; // immutable
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value; // since java.time.Year is serializable
	}

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached; // since java.time.Year is serializable
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original; // since java.time.Year is immutable, it is safe to return the first parameter
	}

}
