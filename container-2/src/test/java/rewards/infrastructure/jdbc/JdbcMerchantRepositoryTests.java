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

	@Before
	public void setUp() throws Exception {
		DataSource dataSource = createTestDataSource();
		merchantRepository = new JdbcMerchantRepository();
		merchantRepository.setDataSource(dataSource);
		// Run this test (and see it fail). Subsequent steps will make it pass.
		// TODO 3: merchantRepository.populateCache();
		// Run this test again (and see some tests pass). Subsequent steps will make it pass.
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

	@Test
	public void merchantGetsCached() {
		long origHits = merchantRepository.getCacheHits();
		boolean isCached = merchantRepository.isMerchantCached(merchantNumber);
		merchantRepository.findByNumber(merchantNumber);
		assertEquals(isCached ? (origHits + 1) : 0, merchantRepository.getCacheHits());
		merchantRepository.findByNumber(merchantNumber);
		assertEquals(isCached ? (origHits + 2) : 1, merchantRepository.getCacheHits());
	}

	@Test(expected=EmptyResultDataAccessException.class)
	public void cacheGetsCleared() throws Exception {
		// Run this test (and see it fail). Subsequent steps will make it pass.
		// TODO 4: merchantRepository.clearCache();
		// Run this test again (and see some tests pass). Subsequent steps will make it pass.
		merchantRepository.findByNumber(merchantNumber);
	}

	private DataSource createTestDataSource() {
		return new EmbeddedDatabaseBuilder()
			.setName("rewards")
			.addScript("/rewards/testdb/schema.sql")
			.addScript("/rewards/testdb/test-data.sql")
			.build();
	}

}
