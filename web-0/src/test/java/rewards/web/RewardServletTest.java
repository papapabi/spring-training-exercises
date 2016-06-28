package rewards.web;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import rewards.application.RewardConfirmation;

public class RewardServletTest {

	@Test
	public void testDoPost() throws Exception {
		XmlWebApplicationContext xmlWebApplicationContext = new XmlWebApplicationContext();
		
		String[] configs = {"file:src/main/webapp/WEB-INF/web-app-config.xml", "classpath:rewards/app-config.xml"};
		xmlWebApplicationContext.setConfigLocations(configs);
		
		MockServletContext servletContext = new MockServletContext();
		servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, xmlWebApplicationContext);
		
		xmlWebApplicationContext.setServletContext(servletContext);
		xmlWebApplicationContext.refresh();
		
		MockServletConfig servletConfig = new MockServletConfig(servletContext);
				
		MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
		httpServletRequest.addParameter("purchaseAmount", "100.00");
		httpServletRequest.addParameter("merchantNumber", "1115558888");
		httpServletRequest.addParameter("cardNumber", "1234567890");
		MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();
		
		RewardServlet servlet = new RewardServlet();
		servlet.init(servletConfig);
		servlet.doPost(httpServletRequest, httpServletResponse);
		
		RewardConfirmation results = (RewardConfirmation) httpServletRequest.getAttribute("rewardConfirmation");
		assertEquals("8861888", results.getAccountNumber());
		assertEquals(new BigDecimal("2.00"), results.getPointsEarned());
		assertEquals(new BigDecimal("2.00"), results.getTotalPoints());
		assertEquals("1", results.getConfirmationNumber());
		
		xmlWebApplicationContext.registerShutdownHook();
		
	}
	
}
