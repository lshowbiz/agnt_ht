package com.joymain.jecs.webapp.taglib;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Form;
import org.apache.commons.validator.ValidatorResources;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.validation.Errors;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.RequestContext;
import org.springmodules.validation.commons.ValidatorFactory;

import com.joymain.jecs.webapp.resource.MessageResourceBundle;


/**
 * <p>This class is designed to render a <label> tag for labeling your forms and
 * adds an asterik (*) for required fields.  It was originally written by Erik
 * Hatcher (http://www.ehatchersolutions.com/JavaDevWithAnt/).
 *
 * <p>It is designed to be used as follows:
 * <pre>&lt;tag:label key="userForm.username"/&gt;</pre>
 *
 * @jsp.tag name="substr" bodycontent="empty"
 */
public class SubStrTag extends TagSupport {
    private static final long serialVersionUID = -5310144023136517119L;
    protected RequestContext requestContext;
    protected transient final Log log = LogFactory.getLog(SubStrTag.class);
    protected String key = null;
    protected Integer length = 0;
    protected String styleClass = null;
    protected String errorClass = null;
    protected boolean colon = true;
    protected boolean required=false; 

    public int doStartTag() throws JspException {
        
        try {
            this.requestContext =   
                new RequestContext((HttpServletRequest) this.pageContext.getRequest());
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (Exception ex) {
            pageContext.getServletContext().log("Exception in custom tag", ex);
        }
        
        // Look up this key to see if its a field of the current form
        boolean requiredField = false;
        boolean validationError = false;

        ValidatorResources resources = getValidatorResources();
        
        Locale locale = pageContext.getRequest().getLocale();

        if (locale == null) {
            locale = Locale.getDefault();
        }
        
        // get the name of the bean from the key
        String formName = key;
        String fieldName = key;

        if (resources != null) {
            Form form = resources.getForm(locale, formName);

            if (form != null) {
                Field field = form.getField(fieldName);

                if (field != null) {
                    if (field.isDependency("required") || field.isDependency("validwhen")) {
                        requiredField = true;
                    }
                }
            }
        }
        
        if(this.required){
        	requiredField = true;
        }

		Errors errors = requestContext.getErrors(formName, false);
        List fes = null;
        if (errors != null) {
            fes = errors.getFieldErrors(fieldName);
            //String errorMsg = getErrorMessages(fes);
        }
        
        if (fes != null && fes.size() > 0) {
            validationError = true;
        }

        // Retrieve the message string we are looking for
        String message = null;
        try {
        	message = this.strsDeal(key, length);
        } catch (NoSuchMessageException nsm) {
            message = strLabel();
        }
        
        String cssClass = null;
        if (styleClass != null) {
            cssClass = styleClass;
        } else if (requiredField) {
            cssClass = "required";
        }

        String cssErrorClass = (errorClass != null) ? errorClass : "error";
        StringBuffer label = new StringBuffer();

        if ((message == null) || "".equals(message.trim())) {
            label.append("");
        } else {
            label.append("<label for=\"").append(fieldName).append("\"");

            if (validationError) {
                label.append(" class=\"").append(cssErrorClass).append("\"");
            } else if (cssClass != null) {
                label.append(" class=\"").append(cssClass).append("\"");
            }
            label.append(" title=\""+key+"\"");
            label.append(">");
            //label.append((requiredField) ? "<span class=\"req\">*</span> " : "");
            label.append(message);
//            label.append((colon) ? ":" : "");
            label.append("</label>");
            
            if (validationError) {
                label.append("<img class=\"validationWarning\" alt=\"");
                label.append(getMessageSource().getMessage("icon.warning", null, locale));
                label.append("\"");

                String context = ((HttpServletRequest) pageContext.getRequest()).getContextPath();

                label.append("src=\"" + context);
                label.append(getMessageSource().getMessage("icon.warning.img", null, locale));
                label.append("\" />");
            }
        }
        
        //================================字符串截取
//        StringBuffer label = new StringBuffer();       
//        String keyStr = this.strsDeal(key, length);
//        System.out.println(key+"==="+length);
//        System.out.println("keyStr:"+keyStr);
//        label = new StringBuffer(keyStr);
//        System.out.println("3===:"+label.toString());
        
//        System.out.println("label:"+label.toString());
        
        // Print the retrieved message to our output writer
        try {
            writeMessage(label.toString());
        } catch (IOException io) {
            io.printStackTrace();
            throw new JspException("Error writing label: " + io.getMessage());
        }

        // Continue processing this page
        return (SKIP_BODY);
    }
    
    /**
     * 字符串拼接
     * @param oldStr
     * @param length
     * @return
     */
    private String strsDeal(String oldStr,Integer length){
    	String tempStr = "";
    	if(oldStr!=null){
    		if(oldStr.length()>=length){
    			tempStr = oldStr.substring(0, length)+"...";
    		}else{
    			tempStr = oldStr;
    		}
    	}else{
    		tempStr = "";
    	}
    	return tempStr;
    }

    /**
     * Extract the error messages from the given ObjectError list.
     */
    /*private String getErrorMessages(List fes) throws NoSuchMessageException {
        StringBuffer message = new StringBuffer();
        for (int i = 0; i < fes.size(); i++) {
            ObjectError error = (ObjectError) fes.get(i);
            message.append(this.requestContext.getMessage(error, true));
        }
        return message.toString();
    }*/

    /**
     * Write the message to the page.
     * @param msg the message to write
     * @throws IOException if writing failed
     */
    protected void writeMessage(String msg) throws IOException {
        pageContext.getOut().write(msg);
    }
    


    /**
     * Setter for specifying whether to include colon
     * @jsp.attribute required="false" rtexprvalue="true"
     */
    public void setColon(boolean colon) {
        this.colon = colon;
    }
    
    /**
     * Setter for specifying whether is required
     * @jsp.attribute required="false" rtexprvalue="true"
     */
    public void setRequired(boolean required) {
        this.required = required;
    }

    /**
     * Setter for assigning a CSS class, default is label.
     *
     * @jsp.attribute required="false" rtexprvalue="true"
     */
    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    /**
     * Setter for assigning a CSS class when errors occur,
     * defaults to labelError.
     *
     * @jsp.attribute required="false" rtexprvalue="true"
     */
    public void setErrorClass(String errorClass) {
        this.errorClass = errorClass;
    }
    
    /**
     * Release all allocated resources.
     */
    public void release() {
        super.release();
        key = null;
        colon = false;
        styleClass = null;
        errorClass = null;
    }
    
    /**
     * Get the validator resources from a ValidatorFactory defined in the
     * web application context or one of its parent contexts.
     * The bean is resolved by type (org.springframework.validation.commons.ValidatorFactory).
     *
     * @return ValidatorResources from a ValidatorFactory.
     */
    private ValidatorResources getValidatorResources() {
        // look in servlet beans definition (i.e. action-servlet.xml)
        WebApplicationContext ctx = (WebApplicationContext) pageContext.getRequest()
            .getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        ValidatorFactory factory = null;
        try {
            factory = (ValidatorFactory) BeanFactoryUtils
                    .beanOfTypeIncludingAncestors(ctx, ValidatorFactory.class, true, true);
        } catch (NoSuchBeanDefinitionException e) {
            // look in main application context (i.e. applicationContext.xml)
            ctx = WebApplicationContextUtils
                    .getRequiredWebApplicationContext(pageContext.getServletContext());
            factory = (ValidatorFactory) BeanFactoryUtils
                    .beanOfTypeIncludingAncestors(ctx, ValidatorFactory.class, true, true);
        }
        return factory.getValidatorResources();
    }
    

    /**
     * Use the application context itself for default message resolution.
     */
    private MessageSource getMessageSource() {
        return requestContext.getWebApplicationContext();
    }
    
    private String strLabel(){
    	WebApplicationContext  context = requestContext.
    			getWebApplicationContext();
    	MessageResourceBundle myMessage = (MessageResourceBundle)
    			context.getBean("dbmessageSource");
    	return myMessage.getMessage(key);
    }
    
    /**
     * Setter for specifying whether is required
     * @jsp.attribute required="false" rtexprvalue="true"
     */
    public void setLength(Integer length) {
		this.length = length;
	}

	/**
	 * 获取List值
	 * @jsp.attribute required="true" rtexprvalue="true" description="当前值"
	 * @return
	 */
	public String getKey() {
    	return key;
    }

	public void setKey(String value) {
		try {
			this.key = (String) ExpressionEvaluatorManager.evaluate("key", value.toString(), Object.class, this, pageContext);
		} catch (JspException e) {
		} catch (NullPointerException e) {
			this.key="";
		}
    }
}
