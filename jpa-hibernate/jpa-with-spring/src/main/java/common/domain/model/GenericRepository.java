package common.domain.model;

import java.io.Serializable;

/**
 * Generic repository interface providing CRUD operations.
 *
 * @param <T> the entity type
 * @param <ID> the primary key type of the entity type
 */
public interface GenericRepository<T, ID extends Serializable> {
	
	T findOne(ID id);
	
	void save(T entity);
	
	<S extends T> S update(S entity);
	
	void delete(T entity);
	
	void delete(ID id);

}
