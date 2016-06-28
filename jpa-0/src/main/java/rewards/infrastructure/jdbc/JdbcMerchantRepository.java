package rewards.infrastructure.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import rewards.domain.model.Merchant;
import rewards.domain.model.MerchantRepository;

public class JdbcMerchantRepository implements MerchantRepository {

	private JdbcTemplate jdbcTemplate;

	public JdbcMerchantRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final String SQL_FINDBY_NUMBER =
			"SELECT m.ID AS ID, m.NUMBER AS NUMBER, m.NAME AS NAME, m.AMOUNT_PER_POINT AS AMOUNT_PER_POINT, m.MINIMUM_PURCHASE_AMOUNT AS MINIMUM_PURCHASE_AMOUNT"
					+ " FROM T_MERCHANT m"
					+ " WHERE m.NUMBER = ?";

	@Override
	public Merchant findByNumber(String merchantNumber) {
		return jdbcTemplate.queryForObject(SQL_FINDBY_NUMBER, new RowMapper<Merchant>() {
			@Override
			public Merchant mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Merchant(
						rs.getString("NUMBER"), rs.getString("NAME"),
						rs.getBigDecimal("AMOUNT_PER_POINT"), rs.getBigDecimal("MINIMUM_PURCHASE_AMOUNT"));
			}
		}, merchantNumber);
	}

}
