package com.joymain.jecs.po.webapp.action;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JpoMemberFOrdereLevelController extends BaseFormController{
	 protected Object formBackingObject(HttpServletRequest request)
	    throws Exception {
	        JpoMemberOrder jpoMemberOrder = new JpoMemberOrder();
	    	this.setFormView("po/jpoMemberFOrderLevelForm");
	    	if(RequestUtil.isMobileRequest(request)){
	    		this.setFormView("/mobile/po/jpoMemberFOrderLevelForm");
	    	}
	        return jpoMemberOrder;
	    }
}
