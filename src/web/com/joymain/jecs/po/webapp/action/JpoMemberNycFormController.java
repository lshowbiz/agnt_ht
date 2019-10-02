package com.joymain.jecs.po.webapp.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.po.model.JpoMemberNyc;
import com.joymain.jecs.po.service.JpoMemberNycManager;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JpoMemberNycFormController extends BaseFormController {
    private JpoMemberNycManager jpoMemberNycManager = null;
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void setJpoMemberNycManager(JpoMemberNycManager jpoMemberNycManager) {
        this.jpoMemberNycManager = jpoMemberNycManager;
    }
    public JpoMemberNycFormController() {
        setCommandName("jpoMemberNyc");
        setCommandClass(JpoMemberNyc.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JpoMemberNyc jpoMemberNyc = null;


        if (!StringUtils.isEmpty(id)) {
            jpoMemberNyc = jpoMemberNycManager.getJpoMemberNyc(id);
            if(null!=jpoMemberNyc){
                jpoMemberNyc.setOldRemarks(jpoMemberNyc.getRemarks());
                jpoMemberNyc.setRemarks("");
            }
        } else {
            jpoMemberNyc = new JpoMemberNyc();
        }

        return jpoMemberNyc;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        SysUser operatorUser = SessionLogin.getOperatorUser(request);
        JpoMemberNyc jpoMemberNyc = (JpoMemberNyc) command;
        boolean isNew = (jpoMemberNyc.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJpoMemberNyc".equals(strAction)  ) {
		jpoMemberNycManager.removeJpoMemberNyc(jpoMemberNyc.getId().toString(),operatorUser.getUserCode());
		key="jpoMemberNyc.delete";
	}else{
		if(StringUtils.isEmpty(jpoMemberNyc.getYearOfMonth())){ 
    		errors.reject("财月不能为空!", new Object[] {},"财月不能为空!");
			return showForm(request, response, errors);
    	}
        if(StringUtils.isEmpty(jpoMemberNyc.getMemberNo())){ 
    		errors.reject("会员编号不能为空!", new Object[] {},"会员编号不能为空!");
			return showForm(request, response, errors);
    	}
        if(jpoMemberNyc.getPushAt()==null){ 
    		errors.reject("推送时间不能为空!", new Object[] {},"推送时间不能为空");
			return showForm(request, response, errors);
    	}
        if(StringUtils.isEmpty(jpoMemberNyc.getStatus())){ 
    		errors.reject("状态不能为空!", new Object[] {},"状态不能为空!");
			return showForm(request, response, errors);
    	}
		
        if(StringUtils.isEmpty(jpoMemberNyc.getRemarks())){ 
    		errors.reject("备注内容不能为空!", new Object[] {},"备注内容不能为空!");
			return showForm(request, response, errors);
    	}
        
	    String old=jpoMemberNyc.getOldRemarks()==null?"":jpoMemberNyc.getOldRemarks()+"<br/>";
        String remark=old+sdf.format(new Date())+":"+jpoMemberNyc.getRemarks();
        //Modify By WuCF 20161115 如果备注是null字符串，直接修改成空字符串
        if(remark!=null){
        	remark = remark.replaceAll("null", "");
        }
        jpoMemberNyc.setRemarks(remark);
        if(jpoMemberNyc.getId()==null){
        	key="saveOrUpdate.success";
        }else{
        	key="update.success";
        }
        
        jpoMemberNycManager.saveJpoMemberNyc(jpoMemberNyc,operatorUser.getUserCode());
	}

        return new ModelAndView(getSuccessView());
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
