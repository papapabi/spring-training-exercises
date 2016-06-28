package rewards.infrastructure.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import rewards.domain.model.Merchant;
import rewards.domain.model.MerchantRepository;

@Repository
public class JpaMerchantRepository implements MerchantRepository {

	private static final String SQL_FINDBY_NUMBER =
			"SELECT m.ID AS ID, m.NUMBER AS NUMBER, m.NAME AS NAME, m.AMOUNT_PER_POINT AS AMOUNT_PER_POINT, m.MINIMUM_PURCHASE_AMOUNT AS MINIMUM_PURCHASE_AMOUNT"
					+ " FROM T_MERCHANT m"
					+ " WHERE m.NUMBER = :merchantNumber";
	
	private EntityManager entityManager;
	
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Merchant findByNumber(String merchantNumber) {
		Query query = entityManager.createNativeQuery(SQL_FINDBY_NUMBER, Merchant.class);
		query.setParameter("merchantNumber", merchantNumber);
		return (Merchant) query.getSingleResult();
	}

}
