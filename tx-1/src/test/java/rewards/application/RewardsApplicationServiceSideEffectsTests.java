package rewards.application;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import common.Money;

// TODO 5: Make this side-effect free by adding @Transactional
// Otherwise, when this is run along with other tests, the subsequent tests will fail
// (since other data is inserted into the database)
@ContextConfiguration(locations="classpath:/rewards/system-test-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RewardsApplicationServiceSideEffectsTests {

	@Autowired
	private RewardsApplicationService rewardsService;

	private String merchantNumber;
	private String cardNumber;

	@Before
	public void setUp() throws Exception {
		merchantNumber = "1115558888";
		cardNumber = "1234567890";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1stTime() throws Exception {
		doRewardForPurchase();
	}

	@Test
	public void test2ndTime() throws Exception {
		doRewardForPurchase();
	}

	private void doRewardForPurchase() {
		Purchase purchase = new Purchase(
				new Money(100.0), merchantNumber, cardNumber);
		rewardsService.rewardAccountFor(purchase);
	}

}
