package rewards.application;

import java.util.HashMap;
import java.util.Map;

import rewards.domain.model.Account;
import rewards.domain.model.AccountRepository;

public class StubAccountRepository implements AccountRepository {

	private Map<String, Account> accountsByCardNumber = new HashMap<>();

	/**
	 * Creates a repository with one account using the given account number.
	 * The account is initialized with one card using the given card number.
	 * @param accountNumber
	 * @param cardNumber
	 */
	public StubAccountRepository(String accountNumber, String cardNumber) {
		Account account = new Account(accountNumber, "Juan Dela Cruz");
		account.addCard(cardNumber);
		accountsByCardNumber.put(cardNumber, account);
	}

	@Override
	public Account findByCardNumber(String cardNumber) {
		Account account = accountsByCardNumber.get(cardNumber);
		if (account == null) {
			throw new RuntimeException(
					"Account with card number [" + cardNumber + "] not found");
		}
		return account;
	}

}
