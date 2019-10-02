package com.joymain.jecs.pd.webapp.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.pd.model.PdShipFee;
import com.joymain.jecs.pd.service.PdShipFeeManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdShipFeeController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdShipFeeController.class);
    private PdShipFeeManager pdShipFeeManager = null;
    private AlCountryManager alCountryManager;

    public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

    public void setPdShipFeeManager(PdShipFeeManager pdShipFeeManager) {
        this.pdShipFeeManager = pdShipFeeManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
		String strAction = request.getParameter("strAction");
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
        //Modify By WuCF 20140722
        if("pdExportLog".equals(strAction)){//发货单信息同步
        	//获取地区
        	String companyCode="CN";
            AlCountry alCountry = new AlCountry();
        	alCountry = alCountryManager.getAlCountryByCode(companyCode);
        	alCountry.getAlStateProvinces().iterator();
        	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
        	
            ModelAndView mav = new ModelAndView();

            PdShipFee pdShipFee = new PdShipFee();
            // populate object with request parameters
            BeanUtils.populate(pdShipFee, request.getParameterMap());

    		//List pdShipFees = pdShipFeeManager.getPdShipFees(pdShipFee);
    		/**** auto pagination ***/
    		CommonRecord crm=RequestUtil.toCommonRecord(request);
            Pager pager = new Pager("pdShipFeeListTable",request, 20);
            if(!"root".equals(sessionLogin.getUserCode()) && !"global".equals(sessionLogin.getUserCode())){
            	crm.setValue("userCode", sessionLogin.getUserCode());
            }
            
            //默认当天日期
            SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy-MM-dd");   
            String logStartTime = crm.getString("logStartTime");
            if(StringUtil.isEmpty(logStartTime) || "null".equals(logStartTime)){
            	logStartTime = dateformat2.format(new Date());
            	crm.setValue("logStartTime", logStartTime);
            }
            String logEndTime = crm.getString("logEndTime");
            if(StringUtil.isEmpty(logEndTime) || "null".equals(logEndTime)){
            	logEndTime = dateformat2.format(new Date());
            	crm.setValue("logEndTime", logEndTime);
            }
            
	        List pdShipFees = pdShipFeeManager.getPdExportLogByCrm(crm,pager);
	        request.setAttribute("pdExportLogListTable_totalRows", pager.getTotalObjects());
	        
	        String jsonUrl = ConfigUtil.getConfigValue("CN", "json.url");//发送json链接地址
	        request.setAttribute("jsonUrl",jsonUrl);
	        request.setAttribute("logStartTime",logStartTime);
	        request.setAttribute("logEndTime",logEndTime);
	        
	        mav = new ModelAndView("pd/pdExportLogList", Constants.ECEXPORTLOG, pdShipFees);
            return mav;
        }else if("jpmSmssendInfo".equals(strAction)){//发货单信息同步
        	//获取地区
        	String companyCode="CN";
            AlCountry alCountry = new AlCountry();
        	alCountry = alCountryManager.getAlCountryByCode(companyCode);
        	alCountry.getAlStateProvinces().iterator();
        	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
        	
            ModelAndView mav = new ModelAndView();

            PdShipFee pdShipFee = new PdShipFee();
            // populate object with request parameters
            BeanUtils.populate(pdShipFee, request.getParameterMap());

    		//List pdShipFees = pdShipFeeManager.getPdShipFees(pdShipFee);
    		/**** auto pagination ***/
    		CommonRecord crm=RequestUtil.toCommonRecord(request);
            Pager pager = new Pager("pdShipFeeListTable",request, 20);
            if(!"root".equals(sessionLogin.getUserCode()) && !"global".equals(sessionLogin.getUserCode())){
            	crm.setValue("userCode", sessionLogin.getUserCode());
            }
            
            //默认当天日期
            SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy-MM-dd");   
            String logStartTime = crm.getString("logStartTime");
            if(StringUtil.isEmpty(logStartTime) || "null".equals(logStartTime)){
            	logStartTime = dateformat2.format(new Date());
            	crm.setValue("logStartTime", logStartTime);
            }
            String logEndTime = crm.getString("logEndTime");
            if(StringUtil.isEmpty(logEndTime) || "null".equals(logEndTime)){
            	logEndTime = dateformat2.format(new Date());
            	crm.setValue("logEndTime", logEndTime);
            }
            
	        List pdShipFees = pdShipFeeManager.getJpmSmssendInfoByCrm(crm,pager);
	        request.setAttribute("jpmSmssendInfoListTable_totalRows", pager.getTotalObjects());
	        
	        String jsonUrl = ConfigUtil.getConfigValue("CN", "json.url");//发送json链接地址
	        request.setAttribute("jsonUrl",jsonUrl);
	        request.setAttribute("logStartTime",logStartTime);
	        request.setAttribute("logEndTime",logEndTime);
	        
	        mav = new ModelAndView("pm/jpmSmssendInfoList", Constants.JPMSMSSENDINFO_LIST, pdShipFees);
            return mav;
        }else if("jocsInterfaceLog".equals(strAction)){//接口日志查询，Modify By WuCF 20141117
        	//获取地区
        	String companyCode="CN";
            AlCountry alCountry = new AlCountry();
        	alCountry = alCountryManager.getAlCountryByCode(companyCode);
        	alCountry.getAlStateProvinces().iterator();
        	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
        	
            ModelAndView mav = new ModelAndView();

            PdShipFee pdShipFee = new PdShipFee();
            // populate object with request parameters
            BeanUtils.populate(pdShipFee, request.getParameterMap());

    		//List pdShipFees = pdShipFeeManager.getPdShipFees(pdShipFee);
    		/**** auto pagination ***/
    		CommonRecord crm=RequestUtil.toCommonRecord(request);
            Pager pager = new Pager("jocsInterfaceLogListTable",request, 20);
            if(!"root".equals(sessionLogin.getUserCode()) && !"global".equals(sessionLogin.getUserCode())){
            	crm.setValue("userCode", sessionLogin.getUserCode());
            }
            
            //默认当天日期
            SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy-MM-dd");   
            String logStartTime = crm.getString("logStartTime");
            if(StringUtil.isEmpty(logStartTime) || "null".equals(logStartTime)){
            	logStartTime = dateformat2.format(new Date());
            	crm.setValue("logStartTime", logStartTime);
            }
            String logEndTime = crm.getString("logEndTime");
            if(StringUtil.isEmpty(logEndTime) || "null".equals(logEndTime)){
            	logEndTime = dateformat2.format(new Date());
            	crm.setValue("logEndTime", logEndTime);
            }
            
	        List pdShipFees = pdShipFeeManager.getJocsInterfaceLogByCrm(crm,pager);
	        request.setAttribute("jocsInterfaceLogListTable_totalRows", pager.getTotalObjects());
	        
	        String jsonUrl = ConfigUtil.getConfigValue("CN", "json.url");//发送json链接地址
	        request.setAttribute("jsonUrl",jsonUrl);
	        request.setAttribute("logStartTime",logStartTime);
	        request.setAttribute("logEndTime",logEndTime);
	        
	        mav = new ModelAndView("pm/jocsInterfaceLogList", Constants.JOCSINTERFACELOG_LIST, pdShipFees);
            return mav;
        }else if("jocsInterfaceRetransmission".equals(strAction)){//接口日志重发，Modify By WuCF 20141208
        	//获取地区
        	String companyCode="CN";
            AlCountry alCountry = new AlCountry();
        	alCountry = alCountryManager.getAlCountryByCode(companyCode);
        	alCountry.getAlStateProvinces().iterator();
        	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
        	
            ModelAndView mav = new ModelAndView();

            PdShipFee pdShipFee = new PdShipFee();
            // populate object with request parameters
            BeanUtils.populate(pdShipFee, request.getParameterMap());

    		//List pdShipFees = pdShipFeeManager.getPdShipFees(pdShipFee);
    		/**** auto pagination ***/
    		CommonRecord crm=RequestUtil.toCommonRecord(request);
            Pager pager = new Pager("jocsInterfaceRetransmissionListTable",request, 20);
            if(!"root".equals(sessionLogin.getUserCode()) && !"global".equals(sessionLogin.getUserCode())){
            	crm.setValue("userCode", sessionLogin.getUserCode());
            }
            
            //默认当天日期
            SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy-MM-dd");   
            String logStartTime = crm.getString("logStartTime");
            if(StringUtil.isEmpty(logStartTime) || "null".equals(logStartTime)){
            	logStartTime = dateformat2.format(new Date());
            	crm.setValue("logStartTime", logStartTime);
            }
            String logEndTime = crm.getString("logEndTime");
            if(StringUtil.isEmpty(logEndTime) || "null".equals(logEndTime)){
            	logEndTime = dateformat2.format(new Date());
            	crm.setValue("logEndTime", logEndTime);
            }
            
	        List pdShipFees = pdShipFeeManager.getJocsInterfaceRetransmisionByCrm(crm,pager);
	        request.setAttribute("jocsInterfaceRetransmissionListTable_totalRows", pager.getTotalObjects());
	        
	        String jsonUrl = ConfigUtil.getConfigValue("CN", "json.url");//发送json链接地址
	        request.setAttribute("jsonUrl",jsonUrl);
	        request.setAttribute("logStartTime",logStartTime);
	        request.setAttribute("logEndTime",logEndTime);
	        
	        mav = new ModelAndView("pm/jocsInterfaceRetransmissionList", Constants.JOCSINTERFACERETRANSMISSION_LIST, pdShipFees);
            return mav;
        }else{//物流费管理
	        //获取地区
	    	String companyCode="CN";
	        AlCountry alCountry = new AlCountry();
	    	alCountry = alCountryManager.getAlCountryByCode(companyCode);
	    	alCountry.getAlStateProvinces().iterator();
	    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
	    	
	        ModelAndView mav = new ModelAndView();
	
	        PdShipFee pdShipFee = new PdShipFee();
	        // populate object with request parameters
	        BeanUtils.populate(pdShipFee, request.getParameterMap());
	
			//List pdShipFees = pdShipFeeManager.getPdShipFees(pdShipFee);
			/**** auto pagination ***/
			CommonRecord crm=RequestUtil.toCommonRecord(request);
	        Pager pager = new Pager("pdShipFeeListTable",request, 20);
	        if (!StringUtil.isEmpty(strAction) && strAction.equals("pdShipFeeList")) {
		        List pdShipFees = pdShipFeeManager.getPdShipFeesByCrm(crm,pager);
		        request.setAttribute("pdShipFeeListTable_totalRows", pager.getTotalObjects());
		        
		        /*
		         * 测试根据不同省份来收取相应的物流费
		         */
		        /*String amount = request.getParameter("amount") != null ? request.getParameter("amount") : "0";
		        String orderType = request.getParameter("orderType") != null ? request.getParameter("orderType") : "0";
	        	String criticalPoint = request.getParameter("criticalPoint") != null ? request.getParameter("criticalPoint") : "0";
	        	String provinceName = request.getParameter("provinceName") != null ? request.getParameter("provinceName") : "0";
	        	
	        	BigDecimal fee = pdShipFeeManager.countFee(amount, orderType, provinceName);
	        	request.setAttribute("testfee", fee);*/
	        	//测试 END
	        	
		        mav = new ModelAndView("pd/pdShipFeeList", Constants.PDSHIPFEE_LIST, pdShipFees);
	        }
	        return mav;
        }
    }
}
