package rewards.infrastructure.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;

import rewards.domain.model.Account;
import rewards.domain.model.AccountRepository;

//TODO 10: Add an annotation to make this class eligible for DataAccessException translation
@Repository
public class JpaAccountRepository implements AccountRepository {

	private static final String SQL_FINDBY_CARDNUMBER =
			"SELECT a.ID AS ID, a.NUMBER AS NUMBER, a.NAME AS NAME, a.TOTAL_POINTS AS TOTAL_POINTS,"
					+ " b.NUMBER AS CARD_NUMBER, b.ID AS CARD_ID"
					+ " FROM T_ACCOUNT a, T_ACCOUNT_CARD c"
					+ " LEFT OUTER JOIN T_ACCOUNT_CARD b ON a.ID = b.ACCOUNT_ID"
					+ " WHERE c.NUMBER = :cardNumber AND c.ACCOUNT_ID = a.ID";
	
	
    private EntityManager entityManager;
    
    //TODO 11: Add an annotation that will enable injection of an EntityManager
    @PersistenceContext 
    // spring will inject the appropriate entitymanager, as long as the factory is defined
    // in the configuration xml
    public void setEntityManager(EntityManager entityManager) {
    	this.entityManager = entityManager;
    }

    //TODO 12: Using the entityManager, reuse the SQL defined above to return the Account associated with a card number.
	@Override
	public Account findByCardNumber(String cardNumber) {
		// you can also do
		// Query query = entityManager.createNativeQuery(SQL_FINDBY_CARDNUMBER);
		// code at the bottom, A taste of J
	    TypedQuery<Account> query = entityManager.createQuery
	    		("SELECT a FROM Account a JOIN a.cards c WHERE c.cardDetails.number = :cardNumber",
	    				Account.class);
	    query.setParameter("cardNumber", cardNumber);
	    return query.getSingleResult();
	}

}
