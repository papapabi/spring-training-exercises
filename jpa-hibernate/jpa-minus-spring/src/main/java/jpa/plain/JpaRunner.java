package jpa.plain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO 02: Use JPA to persist and retrieve a Person object (mapped in previous steps)
// Study how the EntityManagerFactory was created.
// Study how the EntityManager was created from the factory.
// Look at META-INF/persistence.xml and notice the following:
// - the name of the persistence-unit,
// - the transaction-type,
// - the properties
// - and how the Person class is listed in it.
public class JpaRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(JpaRunner.class);

	public static void main(String[] args) throws Exception {
		String persistenceUnitName = "jpa.plain";
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(
				persistenceUnitName);
		try {
			EntityManager em = emf.createEntityManager();
			try {
				EntityTransaction tx = em.getTransaction();
				tx.begin();
				try {
					final long personId = 42;
					// TODO 02a: Create a Person object with the given id
					// Use the constructor that accepts a long argument (use personId)
					// Set the name to "John Smith"
					Person john = null;

					// TODO 02b: Add the created Person object into the persistence context
					// (by calling EntityManager#persist(Object))
					// em.persist(john);

					// TODO 02c: Retrieve the object that you just persisted
					// (by calling EntityManager#find(Class, Object)
					// Assign it to the person variable
					Person person = null;
					logger.debug("Person's name is {}", person.getName());
					
					// TODO 02d: Run and see output
					// What table was created?
					// How was the Person object persisted?
					tx.commit();
				} catch (Exception e) {
					logger.error("Exception occured", e);
					tx.rollback();
				}
			} finally {
				logger.debug("Closing entity manager...");
				em.close();
				logger.debug("Entity manager closed.");
			}
		} finally {
			logger.debug("Closing entity manager factory...");
			emf.close();
			logger.debug("Entity manager factory closed.");
		}
	}

}
