package rewards.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;

import rewards.application.RewardsApplicationService;

// TODO 07 Annotate the class as a controller
public class RewardController {
	
	private RewardsApplicationService rewardService;

	@Autowired
	public RewardController(RewardsApplicationService rewardService) {
		this.rewardService = rewardService;
	}

	// TODO 08a map this to the root url '/'
	// TODO 08b make this method return index.jsp as the view
	public String showIndex() {
		return null;
	}
	
	// TODO 09a map this to '/reward'
	// TODO 09b make this method only accept POST requests (use the "method" attribute in the annotation and set it to RequestMethod.POST)
	// TODO 09c make the return value of rewardService#rewardAccountFor accessible by the view
	// TODO 09d make this method return reward.jsp as the view
	public String processReward(@RequestParam("purchaseAmount") String amount, 
			String merchantNumber, 
			String cardNumber, Model model) {
		
		return null;
	}

	@ExceptionHandler(value = {EmptyResultDataAccessException.class})
	public String entityNotFound() {
		return "errors/notFound";
	}
	
}
