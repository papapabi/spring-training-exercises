package concurrency.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import concurrency.model.BankAccount;

@Service
@Transactional
public class BankAccountService {

	@PersistenceContext
	private EntityManager entityManager;
	
	protected BankAccount getBankAccount(Long bankAccountId) {
		BankAccount bankAccount = entityManager.find(
				BankAccount.class, bankAccountId
				/*, javax.persistence.LockModeType.PESSIMISTIC_READ */);
		return bankAccount;
	}

	public void withdraw(Long bankAccountId, int amount) {
		BankAccount bankAccount = getBankAccount(bankAccountId);
		bankAccount.withdraw(amount);
		entityManager.merge(bankAccount);
	}

	public void deposit(Long bankAccountId, int amount) {
		BankAccount bankAccount = getBankAccount(bankAccountId);
		bankAccount.deposit(amount);
		entityManager.merge(bankAccount);
	}

}
