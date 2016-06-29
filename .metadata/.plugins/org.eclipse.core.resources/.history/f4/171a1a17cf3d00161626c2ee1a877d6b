package concurrency.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A crude bank account entity.
 * <p>
 * This is not meant to be a good implementation of a bank account. It is meant
 * to illustrate concurrency control.
 */
@Entity
public class BankAccount {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	// TODO Add a version field to be used for optimistic locking

	private int balance = 0;

	public Long getId() {
		return id;
	}

	public int getBalance() {
		return balance;
	}

	public void withdraw(int amount) {
		balance -= amount;
	}
	
	public void deposit(int amount) {
		balance += amount;
	}

}
