package rewards.web;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import rewards.application.Purchase;
import rewards.application.RewardConfirmation;
import rewards.application.RewardsApplicationService;

@RunWith(MockitoJUnitRunner.class)
public class RewardControllerTests {

	@Mock
	private RewardsApplicationService rewardService;

	@InjectMocks
	private RewardController controller;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testShowIndex() throws Exception {
		String viewName = controller.showIndex();
		assertEquals("index", viewName);
	}

	@Test
	public void testProcessReward() throws Exception {		
		when(rewardService.rewardAccountFor(any(Purchase.class))).thenReturn(mock(RewardConfirmation.class));
		Model model = new ExtendedModelMap();

		String viewName = controller.processReward("100", "merchantNumber", "cardNumber", model);
		
		assertEquals("reward", viewName);
		assertTrue(model.containsAttribute("rewardConfirmation"));
	}
	
	@Test
	public void testEntityNotFound() throws Exception {
		String viewName = controller.entityNotFound();
		assertEquals("errors/notFound", viewName);
	}
	
	
}