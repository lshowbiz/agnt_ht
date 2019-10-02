package com.joymain.jecs.fi.webapp.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.fi.model.FiBcoinPayconfig;
import com.joymain.jecs.fi.model.FiBcoinPayconfigDetail;
import com.joymain.jecs.fi.service.FiBcoinPayconfigDetailManager;
import com.joymain.jecs.fi.service.FiBcoinPayconfigManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FiBcoinPayconfigFormController extends BaseFormController {
    private FiBcoinPayconfigManager fiBcoinPayconfigManager = null;
    private FiBcoinPayconfigDetailManager fiBcoinPayconfigDetailManager = null;

    public void setFiBcoinPayconfigDetailManager(FiBcoinPayconfigDetailManager fiBcoinPayconfigDetailManager) {
        this.fiBcoinPayconfigDetailManager = fiBcoinPayconfigDetailManager;
    }
    public void setFiBcoinPayconfigManager(FiBcoinPayconfigManager fiBcoinPayconfigManager) {
        this.fiBcoinPayconfigManager = fiBcoinPayconfigManager;
    }
    public FiBcoinPayconfigFormController() {
        setCommandName("fiBcoinPayconfig");
        setCommandClass(FiBcoinPayconfig.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
    	
    	//配置ID
        String configId = request.getParameter("configId");
        
        FiBcoinPayconfig fiBcoinPayconfig = null;
        List<FiBcoinPayconfigDetail> fiBcoinPayconfigDetailList =new ArrayList<FiBcoinPayconfigDetail>();

        if (!StringUtils.isEmpty(configId)) {
        	
        	//删除明细
        	if ("deleteFiBcoinPayconfigDetail".equals(request.getParameter("strAction"))  ) {
    			
        		String detailId = request.getParameter("detailId");
    			fiBcoinPayconfigDetailManager.removeFiBcoinPayconfigDetail(detailId);
    		}
        	
            fiBcoinPayconfig = fiBcoinPayconfigManager.getFiBcoinPayconfig(configId);
            
            //查询积分配置关联的商品
            fiBcoinPayconfigDetailList = fiBcoinPayconfigDetailManager.getFiBcoinPayconfigDetailsByConfigId(configId);
        } else {
            fiBcoinPayconfig = new FiBcoinPayconfig();
        }
        
        request.setAttribute("fiBcoinPayconfigDetailList", fiBcoinPayconfigDetailList);
        
        return fiBcoinPayconfig;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiBcoinPayconfig fiBcoinPayconfig = (FiBcoinPayconfig) command;
        boolean isNew = (fiBcoinPayconfig.getConfigId() == null);
        Locale locale = request.getLocale();
        String key=null;
        String strAction = request.getParameter("strAction");
        
        //删除明细
        if ("deleteFiBcoinPayconfigDetail".equals(strAction)  ) {
        	
        	String detailId = request.getParameter("detailId");
        	String configId = request.getParameter("configId");
        	
        	fiBcoinPayconfigDetailManager.removeFiBcoinPayconfigDetail(detailId);
        	
        	return new ModelAndView("redirect:editFiBcoinPayconfig.html?configId="+configId);
        }else if ("deleteFiBcoinPayconfig".equals(strAction)  ) {//删除配置
			
			fiBcoinPayconfigManager.removeFiBcoinPayconfig(fiBcoinPayconfig.getConfigId().toString());
			
		}else{//新增或修改配置
			
			//重新设置开始时间和结束时间
			String startTime = request.getParameter("startTime");
//			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        
//			Date date = format.parse(startTime);
//			fiBcoinPayconfig.setStartTime(date);
//			
//			String endTime = request.getParameter("endTime");   
//			DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
//			Date endDate = format2.parse(endTime);
//			fiBcoinPayconfig.setStartTime(endDate);
			
			//操作人信息
			SysUser loginSysUser = SessionLogin.getLoginUser(request);
			fiBcoinPayconfig.setCreateCode(loginSysUser.getUserCode());
			fiBcoinPayconfig.setCreateName(loginSysUser.getUserName());
//			fiBcoinPayconfig.setCreateTime(new Date());
			
			//保存
			fiBcoinPayconfigManager.saveFiBcoinPayconfig(fiBcoinPayconfig);
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
