package rewards.domain.model;

public interface MerchantRepository {

	Merchant findByNumber(String merchantNumber);

}
