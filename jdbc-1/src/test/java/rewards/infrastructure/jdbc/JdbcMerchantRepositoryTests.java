package rewards.infrastructure.jdbc;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import rewards.domain.model.Merchant;

public class JdbcMerchantRepositoryTests {

	private JdbcMerchantRepository merchantRepository;
	private String merchantNumber = "1115558888";
	private DataSource dataSource;

	@Before
	public void setUp() throws Exception {
		dataSource = createTestDataSource();
		merchantRepository = new JdbcMerchantRepository(dataSource);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void findByNumber() throws Exception {
		Merchant merchant = merchantRepository.findByNumber(merchantNumber);
		assertNotNull(merchant);
		assertEquals("Acme Supplies", merchant.getName());
		assertEquals(merchantNumber, merchant.getNumber());
		assertEquals(new BigDecimal("50.0"), merchant.getAmountPerPoint().setScale(1));
		assertEquals(new BigDecimal("100.0"), merchant.getMinimumAmount().setScale(1));
	}

	@Test(expected=EmptyResultDataAccessException.class)
	public void throwsExceptionWhenMerchantNotFound() throws Exception {
		merchantRepository.findByNumber("NON-EXISTENT MERCHANT");
	}

	private DataSource createTestDataSource() {
		return new EmbeddedDatabaseBuilder()
			.setName("rewards")
			.addScript("/rewards/testdb/schema.sql")
			.addScript("/rewards/testdb/test-data.sql")
			.build();
	}

}
