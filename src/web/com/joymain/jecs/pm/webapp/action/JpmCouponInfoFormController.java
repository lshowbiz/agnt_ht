package com.joymain.jecs.pm.webapp.action;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.pm.model.JpmCouponInfo;
import com.joymain.jecs.pm.service.JpmCouponInfoManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JpmCouponInfoFormController extends BaseFormController {
    private JpmCouponInfoManager jpmCouponInfoManager = null;
    
    public void setJpmCouponInfoManager(JpmCouponInfoManager jpmCouponInfoManager) {
        this.jpmCouponInfoManager = jpmCouponInfoManager;
    }
    public JpmCouponInfoFormController() {
        setCommandName("jpmCouponInfo");
        setCommandClass(JpmCouponInfo.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String cpId = request.getParameter("cpId");
        JpmCouponInfo jpmCouponInfo = null;

        if (!StringUtils.isEmpty(cpId)) {
            jpmCouponInfo = jpmCouponInfoManager.getJpmCouponInfo(cpId);
        } else {
            jpmCouponInfo = new JpmCouponInfo();
        }

        return jpmCouponInfo;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        String userCode=loginSysUser.getUserCode();
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        JpmCouponInfo jpmCouponInfo = (JpmCouponInfo) command;
        
        String startTime1=request.getParameter("startTime");
        String endTime1=request.getParameter("endTime");
        if(jpmCouponInfo.getStartTime()!=null){
        	jpmCouponInfo.setStartTime(smf.parse(startTime1));
		}		
		if(jpmCouponInfo.getEndTime()!=null){
			jpmCouponInfo.setEndTime(smf.parse(endTime1));
		}
        
        boolean isNew = (jpmCouponInfo.getCpId() == null);
        Locale locale = request.getLocale();
        String key=null;
        String strAction = request.getParameter("strAction");
        Date startTime= smf.parse("2017-01-01 00:00:00");
        Date endTime= smf.parse("2050-12-31 23:59:59");
	if ("deleteJpmCouponInfo".equals(strAction)  ) {
		//删除，暂时不用
		jpmCouponInfoManager.removeJpmCouponInfo(jpmCouponInfo.getCpId().toString());
		key="jpmCouponInfo.delete";
	}else if("addJpmCouponInfo".equals(strAction)){
		//新增
		CommonRecord crm=new CommonRecord();
		crm.setValue("cpName", jpmCouponInfo.getCpName());
		crm.setValue("cpValue", jpmCouponInfo.getCpValue());
		//判断代金券名称与面额是否存在，存在不能新增。
		boolean isExist=jpmCouponInfoManager.serachJpmCouponInfosByParams(crm);
		if(!isExist){
		//有效期为空给默认时间
		if(jpmCouponInfo.getStartTime()==null){
			jpmCouponInfo.setStartTime(startTime);
		}		
		if(jpmCouponInfo.getEndTime()==null){
			jpmCouponInfo.setEndTime(endTime);
		}
		jpmCouponInfo.setStatus("0");
		jpmCouponInfo.setCreateTime(new Date());
		jpmCouponInfo.setCreateUserCode(userCode);
		jpmCouponInfoManager.saveJpmCouponInfo(jpmCouponInfo);
		key="jpmCouponInfo.add";
		}else{
        	errors.reject("error.jpmcouponinfo.nameorvalue.isexist");
        	return showForm(request, response, errors);
		}
	}else if("editJpmCouponInfo".equals(strAction)){
		//编辑
		jpmCouponInfo.setStatus("0");
		jpmCouponInfo.setStartTime(startTime);
		jpmCouponInfo.setEndTime(endTime);
		jpmCouponInfo.setUpdateTime(new Date());
		jpmCouponInfo.setUpdateUserCode(userCode);
		jpmCouponInfoManager.saveJpmCouponInfo(jpmCouponInfo);	
		key="jpmCouponInfo.edit";
	}
		saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), key)); 
        return new ModelAndView(getSuccessView());
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
			super.initBinder(request, binder);
	}
}
