package com.joymain.jecs.pm.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class JpmSalePromoterValidator implements Validator{

	public boolean supports(Class obj) {
		
		return JpmSalePromoter.class.isAssignableFrom(obj);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startime", "jpmSalePro.startime.required") ;  
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endtime", "jpmSalePro.endtime.required") ;  
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "discount", "jpmSalePro.discount.required") ;  
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "presentno", "jpmSalePro.presentno.required") ;  
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "presentname", "jpmSalePro.presentname.required");  
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isorder", "jpmSalePro.isorder.required");  
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "integral", "jpmSalePro.integral.required"); 
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isactiva", "jpmSalePro.isactiva.required"); 
	}

}
