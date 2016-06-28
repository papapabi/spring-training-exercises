package rewards.domain.model;

public interface AccountRepository {

	Account findByCardNumber(String cardNumber);

}
