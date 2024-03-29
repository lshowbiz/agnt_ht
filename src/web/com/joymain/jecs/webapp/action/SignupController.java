package com.joymain.jecs.webapp.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joymain.jecs.Constants;
import com.joymain.jecs.model.User;
import com.joymain.jecs.service.RoleManager;
import com.joymain.jecs.service.UserExistsException;
import com.joymain.jecs.util.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;


import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.BindException;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.Authentication;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.providers.ProviderManager;

/**
 * Controller to signup new users.
 *
 * <p>
 * <a href="SignupController.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class SignupController extends BaseFormController {
    private RoleManager roleManager;

    public void setRoleManager(RoleManager roleManager) {
        this.roleManager = roleManager;
    }
    
    public SignupController() {
        setCommandName("user");
        setCommandClass(User.class);
    }
    
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, 
                                 Object command, BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        User user = (User) command;
        Locale locale = request.getLocale();

        Boolean encrypt = (Boolean) getConfiguration().get(Constants.ENCRYPT_PASSWORD);
        
        if (encrypt != null && encrypt.booleanValue()) {
            String algorithm = (String) getConfiguration().get(Constants.ENC_ALGORITHM);
    
            if (algorithm == null) { // should only happen for test case
                log.debug("assuming testcase, setting algorithm to 'SHA'");
                algorithm = "SHA";
            }
            
            user.setPassword(StringUtil.encodePassword(user.getPassword(), algorithm));
        }
        
        user.setEnabled(true);
        
        // Set the default user role on this new user
        user.addRole(roleManager.getRole(Constants.USER_ROLE));

        try {
            this.getUserManager().saveUser(user);
        } catch (UserExistsException e) {
            log.warn(e.getMessage());

            errors.rejectValue("username", "errors.existing.user",
                               new Object[] {
                                   user.getUsername(), user.getEmail()
                               }, "duplicate user");

            // redisplay the unencrypted passwords
            user.setPassword(user.getConfirmPassword());
            return showForm(request, response, errors);
        }

        saveMessage(request, getText("user.registered", user.getUsername(), locale));
        request.getSession().setAttribute(Constants.REGISTERED, Boolean.TRUE);

        // log user in automatically
        Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getConfirmPassword());
        try {
            ApplicationContext ctx = 
                WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
            if (ctx != null) {
                ProviderManager authenticationManager = (ProviderManager) ctx.getBean("authenticationManager");
                SecurityContextHolder.getContext().setAuthentication(authenticationManager.doAuthentication(auth));
            }
        } catch (NoSuchBeanDefinitionException n) {
            // ignore, should only happen when testing
        }
        
        // Send user an e-mail
        if (log.isDebugEnabled()) {
            log.debug("Sending user '" + user.getUsername() + "' an account information e-mail");
        }

        // Send an account information e-mail
        message.setSubject(getText("signup.email.subject", locale));
        sendUserMessage(user, getText("signup.email.message", locale), RequestUtil.getAppURL(request));
        
        return new ModelAndView(getSuccessView());
    }
}
