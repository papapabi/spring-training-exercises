package concurrency.pessimistic;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import concurrency.model.BankAccount;

@ContextConfiguration(locations="../ConcurrencyTests-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
@Rollback
public class PessimisticConcurrencyControlTests {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	private EntityManager entityManager;

	private Long bankAccountId;

	@Before
	public void setUp() throws Exception {
		setUpBankAccount();
	}

	private void setUpBankAccount() {
		entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			try {
				BankAccount bankAccount = new BankAccount();
				bankAccount.deposit(100);
				entityManager.persist(bankAccount);
				entityManager.getTransaction().commit();
				bankAccountId = bankAccount.getId();
				assertNotNull(bankAccountId);
			} catch(Exception e) {
				entityManager.getTransaction().rollback();
				fail("Failed to setup a bank account with $100 for testing");
				throw e;
			}
		} finally {
			entityManager.close();
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	private abstract class BankAccountAction implements Runnable {

		private Log logger = LogFactory.getLog(this.getClass());

		private final Long bankAccountId;
		private EntityManager entityManager;

		public BankAccountAction(Long bankAccountId) {
			this.bankAccountId = bankAccountId;
		}

		private void execute() {
			entityManager = entityManagerFactory.createEntityManager();
			try {
				entityManager.getTransaction().begin();
				try {
					// TODO Use LockModeType.PESSIMISTIC_READ
					BankAccount bankAccount = entityManager.find(
							BankAccount.class, bankAccountId);
					doInTransactionWithBankAccount(bankAccount);
					entityManager.merge(bankAccount);
					entityManager.getTransaction().commit();
				} catch(Exception e) {
					logger.warn("Bank account action failed", e);
					if (entityManager.getTransaction().isActive()) {
						entityManager.getTransaction().setRollbackOnly();
					}
					throw e;
				}
			} finally {
				entityManager.close();
			}
		}

		@Override
		public void run() {
			execute();
		}

		protected abstract void doInTransactionWithBankAccount(
				BankAccount bankAccount);

	}

	private class WithdrawAction extends BankAccountAction {

		private final int amountToWithdraw;

		public WithdrawAction(Long bankAccountId, int amountToWithdraw) {
			super(bankAccountId);
			this.amountToWithdraw = amountToWithdraw;
		}

		@Override
		protected void doInTransactionWithBankAccount(BankAccount bankAccount) {
			bankAccount.withdraw(amountToWithdraw);
		}

	}

	private class DepositAction extends BankAccountAction {

		private final int amountToDeposit;

		public DepositAction(Long bankAccountId, int amountToDeposit) {
			super(bankAccountId);
			this.amountToDeposit = amountToDeposit;
		}

		@Override
		protected void doInTransactionWithBankAccount(BankAccount bankAccount) {
			bankAccount.deposit(amountToDeposit);
		}

	}

	@Test
	public void simultaneousWithdrawAndDeposit() throws Exception {
		Thread t1 = new Thread(
				new WithdrawAction(bankAccountId, 50), "Withdraw");
		Thread t2 = new Thread(
				new DepositAction(bankAccountId, 50), "Deposit");
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		entityManager = entityManagerFactory.createEntityManager();
		try {
			// TODO Inspect the resulting balance
			BankAccount bankAccount = entityManager.find(BankAccount.class, bankAccountId);
			System.out.println("Resulting balance is: " + bankAccount.getBalance());
			assertEquals(100, bankAccount.getBalance());
		} finally {
			entityManager.close();
		}
	}

}
