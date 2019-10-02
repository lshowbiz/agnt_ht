package com.joymain.jecs.mi.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.Constants;
import com.joymain.jecs.mi.model.MiCoinLog;
import com.joymain.jecs.mi.service.MiCoinLogManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class MiCoinLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(MiCoinLogController.class);
    private MiCoinLogManager miCoinLogManager = null;

    public void setMiCoinLogManager(MiCoinLogManager miCoinLogManager) {
        this.miCoinLogManager = miCoinLogManager;
    }
	private JdbcTemplate jdbcTemplate = null;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }


        SysUser defSysUser = SessionLogin.getLoginUser(request);
        
        
        

        if("post".equalsIgnoreCase(request.getMethod()) && "miCoinLogSend".equals(request.getParameter("strAction"))){
        	String[] strReissueCodes = request.getParameter("strRegisterSuccessCodes").split(",");
        	List mesList=new ArrayList();

			Date curDate=new Date();
    		String key = (String)Constants.sysConfigMap.get(defSysUser.getCompanyCode()).get("ec.mall.key");
    		String mall_url = (String)Constants.sysConfigMap.get(defSysUser.getCompanyCode()).get("ec.mall.url");

    		WebConversation webConversation = new WebConversation();  

    		
        	for (int i = 0; i < strReissueCodes.length; i++) {
				String id=strReissueCodes[i];
				if(!StringUtil.isEmpty(id)){
					MiCoinLog miCoinLog=miCoinLogManager.getMiCoinLog(id);
					if(miCoinLog!=null){
						Double resturn_code=0.0;
						
						if("1".equals(miCoinLog.getDealType())){
							String code=StringUtil.encodePassword(curDate.getTime()+miCoinLog.getSendNo()+miCoinLog.getCoin()+key+miCoinLog.getId()+miCoinLog.getCoinType(), "MD5");
							//unique_code
				    		String url="get_points.php?mobile_phone="+miCoinLog.getSendNo()+"&time="+curDate.getTime()+"&point="+miCoinLog.getCoin()+
				    		"&unique_code="+miCoinLog.getId()+"&coinType="+miCoinLog.getCoinType()+"&code="+code+"&flag=EC";
				    		WebResponse webResponse = webConversation.getResponse(mall_url+url); 
				    		Gson gson=new Gson();
				    		Map map=gson.fromJson(webResponse.getText(), Map.class);
				    		
				    		map.put("info", miCoinLog.getUserCode()+"-"+map.get("info"));
				    		mesList.add(map);
							

				    		resturn_code=StringUtil.formatDouble(map.get("code").toString());
				    	
						}else if("2".equals(miCoinLog.getDealType())){
							
							mall_url = (String)Constants.sysConfigMap.get(defSysUser.getCompanyCode()).get("maibao.mall.url");
			    			
			    			String code = "uniqueId="+miCoinLog.getId()+"&userCode="+miCoinLog.getUserCode()+"&coin="+miCoinLog.getCoin()+"jmtop@coin!@#$%";
			    			
			    			String url="api/fiBcoinReceive?"+"uniqueId="+miCoinLog.getId()+"&userCode="+miCoinLog.getUserCode()+"&coin="
			    			+miCoinLog.getCoin()+"&signMsg="+StringUtil.encodePassword(code,"MD5");
			    			
			    			WebResponse webResponse = webConversation.getResponse(mall_url+url); 
			    			String str=webResponse.getText();
		        			if(StringUtil.isEmpty(str)){
		        				throw new AppException("异常");
		        			}
		        			 resturn_code=0.0; ////成功返回1,；失败返回2；重复返回3
		        			if("1".equals(str)){
		        				resturn_code=1d;
		        			}else if("2".equals(str)){
		        				resturn_code=0d;
		        			}else if("3".equals(str)){
		        				resturn_code=-1d;
		        			}
							
						}
						if(resturn_code!=0d){
			    			miCoinLog.setSendDate(curDate);
			    			//miCoinLog.setSendNo(defSysUser.getUserCode());
			    			miCoinLog.setStatus("2");
			    			miCoinLogManager.saveMiCoinLog(miCoinLog);
			    		}
						
						
					}
				}
			}
    		request.setAttribute("mesList", mesList);
        }
        
        
        

        List list=jdbcTemplate.queryForList("select sum(balance) as balance from fi_bcoin_balance");
        if(!list.isEmpty()){
       	 request.setAttribute("sum_balance", ((Map)list.get(0)).get("balance"));
       }

        
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        BigDecimal sumCoin=miCoinLogManager.getSumCoin(crm);
        request.setAttribute("sumCoin", sumCoin);
        
        Pager pager = new Pager("miCoinLogListTable",request, 20);
        List miCoinLogs = miCoinLogManager.getMiCoinLogsByCrm(crm,pager);
        request.setAttribute("miCoinLogListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("mi/miCoinLogList", "miCoinLogList", miCoinLogs);
    }
}
