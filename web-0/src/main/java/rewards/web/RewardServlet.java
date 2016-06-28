package rewards.web;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Money;
import rewards.application.Purchase;
import rewards.application.RewardConfirmation;
import rewards.application.RewardsApplicationService;

public class RewardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private RewardsApplicationService rewardService;

	public void init(ServletConfig config) throws ServletException {	
		//TODO 3a Use WebApplicationContextUtils to retrieve the ApplicationContext
		//TODO 3b Use the ApplicationContext to retrieve the RewardsApplicationService
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String purchaseAmount = req.getParameter("purchaseAmount");
		String merchantNumber = req.getParameter("merchantNumber");
		String cardNumber = req.getParameter("cardNumber");
		
		Money money = new Money(new BigDecimal(purchaseAmount));
		Purchase purchase = new Purchase(money, merchantNumber, cardNumber);
		
		RewardConfirmation confirmation = rewardService.rewardAccountFor(purchase);
		req.setAttribute("rewardConfirmation", confirmation);
		
		RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/views/reward.jsp");		
		view.forward(req, resp);
		
	}
	
}
