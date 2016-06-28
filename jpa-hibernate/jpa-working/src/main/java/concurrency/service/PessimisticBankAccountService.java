package concurrency.service;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import concurrency.model.BankAccount;

@Service
public class PessimisticBankAccountService extends BankAccountService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	protected BankAccount getBankAccount(Long bankAccountId) {
		BankAccount bankAccount = entityManager.find(
				BankAccount.class, bankAccountId,
				LockModeType.PESSIMISTIC_READ);
		return bankAccount;
	}

}
