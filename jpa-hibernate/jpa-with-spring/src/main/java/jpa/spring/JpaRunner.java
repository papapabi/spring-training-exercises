package jpa.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JpaRunner {

	private static final Logger logger = LoggerFactory.getLogger(JpaRunner.class);

	public static void main(String[] args) throws Exception {
		// TODO 01: Use Spring to initialize a JPA environment
		try (ClassPathXmlApplicationContext ctx =
				new ClassPathXmlApplicationContext("classpath:jpa/spring/config.xml")) {
			// TODO 03: Use JPA/Spring to persist and retrieve a Person object
			// TODO 03a: Get a PersonRepository bean

			final long personId = 42;
			// TODO 03b: Create a Person object with the given id
			// Use the constructor that accepts a long argument (use personId)
			// Set the name to "John Smith"

			// TODO 03c: Add the created Person object into the persistence context
			// (by calling PersonRepository#save(Object))

			// TODO 03d: Retrieve the object that you just persisted
			// (by calling PersonRepository#findOne(Object))
			// Assign it to the person variable
			Person person = null;
			logger.debug("Person's name is {}", person.getName());
						
			// TODO 03e: Run and see output
			// What table was created?
			// How was the Person object persisted?
		}
	}

}
