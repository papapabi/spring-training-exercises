package jpa.spring;

import common.infrastructure.jpa.JpaGenericRepository;

// TODO 02: Implement a DAO/repository with Spring

// TODO 02a: Study how JpaGenericRepository (the base class) gets an EntityManager
// The code for a DAO/repository has already been provided by JpaGenericRepository.

// TODO 02b: Mark this for component scan and exception translation (@Repository)

// TODO 02c: Mark this as transactional (@Transactional)
public class JpaPersonRepository
		extends JpaGenericRepository<Person, Long>
		implements PersonRepository {

	public JpaPersonRepository() {
		super(Person.class);
	}

}
