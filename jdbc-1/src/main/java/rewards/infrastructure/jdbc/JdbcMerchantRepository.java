package rewards.infrastructure.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import rewards.domain.model.Merchant;
import rewards.domain.model.MerchantRepository;

//TODO 4 add a field of type JdbcTemplate and refactor the findByNumber to use queryForObject method of JdbcTemplate and pass a RowMapper
public class JdbcMerchantRepository implements MerchantRepository {

	private DataSource dataSource;
	
	public JdbcMerchantRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final String SQL_FINDBY_NUMBER =
			"SELECT m.ID AS ID, m.NUMBER AS NUMBER, m.NAME AS NAME, m.AMOUNT_PER_POINT AS AMOUNT_PER_POINT, m.MINIMUM_PURCHASE_AMOUNT AS MINIMUM_PURCHASE_AMOUNT"
					+ " FROM T_MERCHANT m"
					+ " WHERE m.NUMBER = ?";

	@Override
	public Merchant findByNumber(String merchantNumber) {
		try {
			Connection conn = dataSource.getConnection();
			try {
				PreparedStatement ps = conn.prepareStatement(SQL_FINDBY_NUMBER);
				try {
					ps.setString(1, merchantNumber);
					ResultSet rs = ps.executeQuery();
					try {
						if (rs.next()) {
							return mapMerchant(rs);
						} else {
							throw new EmptyResultDataAccessException(1);
						}
					} finally {
						rs.close();
					}
				} finally {
					ps.close();
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			// Interface defines no throws clause,
			// but SQLException is not a RuntimeException.
			// So, we wrap it, and comply with the interface.
			throw new RuntimeException(e);
		}
	}

	private Merchant mapMerchant(ResultSet rs) throws SQLException {
		Merchant merchant = new Merchant(
				rs.getString("NUMBER"), rs.getString("NAME"),
				rs.getBigDecimal("AMOUNT_PER_POINT"), rs.getBigDecimal("MINIMUM_PURCHASE_AMOUNT"));
		// To support updates, unique ID must be restored
		/*
		Field idField = ReflectionUtils.findField(Merchant.class, "id");
		ReflectionUtils.makeAccessible(idField);
		ReflectionUtils.setField(
				idField, merchant, rs.getLong("ID"));
		*/
		return merchant;
	}

}
