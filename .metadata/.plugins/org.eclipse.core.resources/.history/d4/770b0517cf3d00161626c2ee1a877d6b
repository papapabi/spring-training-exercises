package common.infrastructure.jpa;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import common.domain.model.GenericRepository;

@Transactional
public class JpaGenericRepository<T, ID extends Serializable>
		implements GenericRepository<T, ID> {

	@PersistenceContext
	private EntityManager entityManager;
	
	private final Class<T> entityClass;
	
	public JpaGenericRepository(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	@Override
	public T findOne(ID id) {
		if (id == null) {
			throw new IllegalArgumentException(
					"Id cannot be null");
		}
		return entityManager.find(getEntityClass(), id);
	}

	@Override
	public void save(T entity) {
		entityManager.persist(entity);
	}

	@Override
	public <S extends T> S update(S entity) {
		return entityManager.merge(entity);
	}

	@Override
	public void delete(T entity) {
		entityManager.remove(
				entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}

	@Override
	public void delete(ID id) {
		T entity = findOne(id);
		if (entity != null) {
			delete(entity);
		}
	}

}
