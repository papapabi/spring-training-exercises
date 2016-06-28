package rewards.application;

import java.util.HashMap;
import java.util.Map;

import rewards.domain.model.Merchant;
import rewards.domain.model.MerchantRepository;

public class StubMerchantRepository implements MerchantRepository {

	private Map<String, Merchant> merchants = new HashMap<>();

	/**
	 * Creates a repository with one merchant with the given merchant number.
	 * @param merchantNumber
	 */
	public StubMerchantRepository(String merchantNumber) {
		Merchant merchant = new Merchant(merchantNumber, "Acme Supplies");
		merchants.put(merchant.getNumber(), merchant);
	}

	@Override
	public Merchant findByNumber(String merchantNumber) {
		Merchant merchant = merchants.get(merchantNumber);
		if (merchant == null) {
			throw new RuntimeException("Merchant not found");
		}
		return merchant;
	}

}
