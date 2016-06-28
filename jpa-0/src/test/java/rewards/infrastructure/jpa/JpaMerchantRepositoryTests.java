package rewards.infrastructure.jpa;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rewards.domain.model.Merchant;
import rewards.domain.model.MerchantRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:rewards/test-infrastructure-config.xml",
		"classpath:rewards/app-config.xml"
})
public class JpaMerchantRepositoryTests {

	@Autowired
	private MerchantRepository merchantRepository;
	
	private String merchantNumber = "1115558888";
	
	@Test
	public void findByNumber() throws Exception {
		Merchant merchant = merchantRepository.findByNumber(merchantNumber);
		assertNotNull(merchant);
		assertEquals("Acme Supplies", merchant.getName());
		assertEquals(merchantNumber, merchant.getMerchantDetails().getNumber());
		assertEquals(new BigDecimal("50.0"), merchant.getAmountPerPoint().setScale(1));
		assertEquals(new BigDecimal("100.0"), merchant.getMinimumAmount().setScale(1));
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void throwsExceptionWhenCardNumberNotFound() throws Exception {
		merchantRepository.findByNumber("NON-EXISTENT CARD");
	}
	
}
