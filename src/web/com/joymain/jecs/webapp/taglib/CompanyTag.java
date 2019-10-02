package com.joymain.jecs.webapp.taglib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.cxf.common.util.StringUtils;
import org.displaytag.tags.el.ExpressionEvaluator;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.model.LabelValue;
/**
 * Tag for creating multiple &lt;select&gt; options for displaying a list of
 * company names.
 *
 * <p>
 * <b>NOTE</b> - This tag requires a Java2 (JDK 1.2 or later) platform.
 * </p>
 *
 * @author Aidy.Q
 * @version 
 *
 * @jsp.tag name="company" bodycontent="empty"
 */
public class CompanyTag extends TagSupport {

	private String name;
	private String scope;
	private String selected;
	private String withAA;
	private String prompt;
	private String label;
	
	private String styleClass;
	
	

	

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	/**
	 * 
	 * @param prompt 
	 * @jsp.attribute required="false" rtexprvalue="true" description="是否显示成label,默认为false"
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * 
	 * @param prompt 选择框的首行
	 * @jsp.attribute required="false" rtexprvalue="true" description="选择框的首行"
	 */
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	/**
	 * 
	 * @param name 选择框的名称
	 * @jsp.attribute required="false" rtexprvalue="true" description="选择框的名称"
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @param scope 
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}
/**
 * 
 * @param selected The selected option.
 * @jsp.attribute required="false" rtexprvalue="true"  description="默认选择项"
 */
	public void setSelected(String selected) {
		this.selected = selected;
	}
/**
 * 
 * @param withAA 选择项中是否包含AA,默认为false 不包含
 * @jsp.attribute required="false" rtexprvalue="true"
 */
	public void setWithAA(String withAA) {
		this.withAA = withAA;
	}

	/**
	 * 
	 */
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
//		 ExpressionEvaluator eval = new ExpressionEvaluator(this, pageContext);

//	        if (selected != null) {
//	            selected = eval.evalString("default", selected);
//	        }
	        List companys = new ArrayList();
	        if("true".equalsIgnoreCase(withAA)){
	        	companys=Constants.companyHasAAList;
	        }else{
	        	companys=Constants.companyList;
	        }
	        if (scope != null) {
	            if (scope.equals("page")) {
	                pageContext.setAttribute(name, companys);
	            } else if (scope.equals("request")) {
	                pageContext.getRequest().setAttribute(name, companys);
	            } else if (scope.equals("session")) {
	                pageContext.getSession().setAttribute(name, companys);
	            } else if (scope.equals("application")) {
	                pageContext.getServletContext().setAttribute(name, companys);
	            } else {
	                throw new JspException("Attribute 'scope' must be: page, request, session or application");
	            }
	        } 
	        StringBuffer sb = new StringBuffer();
	        if("true".equals(label)){
	        	
	        	 for(int i=0;i<companys.size();i++){
	        		 AlCompany company = (AlCompany) companys.get(i);
	        		 if ((selected != null) && selected.equals(company.getCompanyCode())) {
	                     sb.append(company.getCompanyName());
	                 }
	        	 }
	        	
	        	
	        }else {
	           
	        	//sb.append("<select name=\"" + name + "\" id=\"" + name + "\" class=\"select\">\n");
	        	// add by hdg 2016-11-02
	        	if(StringUtils.isEmpty(styleClass)) {
	        		sb.append("<select name=\"" + name + "\" id=\"" + name + "\" class=\"select\">\n");
	        	} else {
	        		sb.append("<select name=\"" + name + "\" id=\"" + name + "\" class=\""+styleClass+"\">\n");
	        	}
	            
	        	
	            if (prompt != null) {
	                sb.append("    <option value=\"\" >");
	                sb.append(prompt + "</option>\n");
	            }

	            for(int i=0;i<companys.size();i++){
	            	AlCompany company = (AlCompany) companys.get(i);
	            	sb.append("    <option value=\"" + company.getCompanyCode() + "\"");

	                if ((selected != null) && selected.equals(company.getCompanyCode())) {
	                    sb.append(" selected=\"selected\"");
	                }

	                sb.append(">" + company.getCompanyName() + "</option>\n");
	            }
	            

	            sb.append("</select>");

	            
	        }
	        try {
                pageContext.getOut().write(sb.toString());
            } catch (IOException io) {
                throw new JspException(io);
            }

	        return this.SKIP_BODY;
	}
	
}
