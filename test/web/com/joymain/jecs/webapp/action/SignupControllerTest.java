package com.joymain.jecs.webapp.action;

import javax.servlet.http.HttpServletResponse;

import com.joymain.jecs.Constants;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.dumbster.smtp.SimpleSmtpServer;


public class SignupControllerTest extends BaseControllerTestCase {
    private SignupController c;

    protected void setUp() throws Exception {
        // needed to initialize a user
        super.setUp();
        c = (SignupController) ctx.getBean("signupController");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        c = null;
    }
    
    public void testDisplayForm() throws Exception {
        MockHttpServletRequest request = newGet("/signup.html");
        HttpServletResponse response = new MockHttpServletResponse();
        ModelAndView mv = c.handleRequest(request, response);
        assertTrue("returned correct view name", mv.getViewName().equals("signup"));
    }

    public void testSignupUser() throws Exception {
        MockHttpServletRequest request = newPost("/signup.html");
        request.addParameter("username", "self-registered");
        request.addParameter("password", "Password1");
        request.addParameter("confirmPassword", "Password1");
        request.addParameter("firstName", "First");
        request.addParameter("lastName", "Last");
        request.addParameter("address.city", "Denver");
        request.addParameter("address.province", "Colorado");
        request.addParameter("address.country", "USA");
        request.addParameter("address.postalCode", "80210");
        request.addParameter("email", "self-registered@raibledesigns.com");
        request.addParameter("website", "http://raibledesigns.com");
        request.addParameter("passwordHint", "Password is one with you.");

        HttpServletResponse response = new MockHttpServletResponse();
        
        SimpleSmtpServer server = SimpleSmtpServer.start(2525);
        
        ModelAndView mv = c.handleRequest(request, response);
        Errors errors =
            (Errors) mv.getModel().get(BindException.MODEL_KEY_PREFIX + "user");
        assertTrue("no errors returned in model", errors == null);
        
        // verify an account information e-mail was sent
        server.stop();
        assertTrue(server.getReceivedEmailSize() == 1);
        
        // verify that success messages are in the request
        assertNotNull(request.getSession().getAttribute("successMessages"));
        assertNotNull(request.getSession().getAttribute(Constants.REGISTERED));
    }
}
