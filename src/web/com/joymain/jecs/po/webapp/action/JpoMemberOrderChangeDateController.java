package com.joymain.jecs.po.webapp.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.webapp.util.ConfigUtil;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 更新审核日期
 * @author Alvin
 *
 */
public class JpoMemberOrderChangeDateController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoMemberOrderChangeDateController.class);
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private BdPeriodManager bdPeriodManager = null;

    public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
        this.jpoMemberOrderManager = jpoMemberOrderManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
        RequestUtil.freshSession(request,"UpdateAudits",1l);

        String orderIdStr = request.getParameter("orderId");
        if(orderIdStr!=null){
        	List successList=new ArrayList();
        	List errorList=new ArrayList();
            String[] orderId = orderIdStr.split(",");
            String strAction = request.getParameter("strAction");
            if("UpdateAudits".equals(strAction)){
            	if("C".equals(loginSysUser.getUserType())){
            		if(RequestUtil.saveOperationSession(request,"UpdateAuditsC",10l)!=0){
            			return new ModelAndView("redirect:jpoMemberOrdersChangeDate.html");
            		}
            	}
                if(orderId!=null && orderId.length>0){
                    String logCreateTime = request.getParameter("logCreateTime");
        			if (StringUtils.isEmpty(logCreateTime)) {
        				MessageUtil.saveLocaleMessage(request, "operation.notice.js.poMemberOrder.dateNotNull");
        				return new ModelAndView("redirect:" + request.getHeader("Referer"));
        			}
        			Date date = null;
        			try {
        				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        				date = sdf.parse(logCreateTime);
        				if (date == null) {
        					MessageUtil.saveLocaleMessage(request, "common.error.dateFormatError");
        					return new ModelAndView("redirect:" + request.getHeader("Referer"));
        				}
        			} catch (Exception e) {
        				MessageUtil.saveLocaleMessage(request, "common.error.dateFormatError");
        				return new ModelAndView("redirect:" + request.getHeader("Referer"));
        			}
                	for(int i=0; i<orderId.length;i++){
                		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId[i]);
                		
                		if("1".equals(jpoMemberOrder.getReturnableStatus())){
							errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + jpoMemberOrder.getMemberOrderNo() + "，" + LocaleUtil.getLocalText("订单已挂起，不允许调期"));
							continue;
						}

						if(jpoMemberOrder.getOrderType().equals("3") || jpoMemberOrder.getOrderType().equals("5")){
							errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + jpoMemberOrder.getMemberOrderNo() + "，" + LocaleUtil.getLocalText("operation.notice.js.orderNo.udpateFail"));
							continue;
						}
						if(!jpoMemberOrder.getCompanyCode().equals(loginSysUser.getCompanyCode())){
							errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + jpoMemberOrder.getMemberOrderNo() + "，" + LocaleUtil.getLocalText("operation.notice.js.orderNo.udpateFail"));
							continue;
						}
						
    					//锁定
    					if ("1".equals(jpoMemberOrder.getLocked()) || jpoMemberOrder.getJprRefund().size() != 0) {
    						errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + jpoMemberOrder.getMemberOrderNo() + "，" + LocaleUtil.getLocalText("poMemberOrder.locked"));
    						continue;
    					}
    					//小于创建日期
/*    					if(!"US".equals(jpoMemberOrder.getCompanyCode())&&!"PH".equals(jpoMemberOrder.getCompanyCode())){
        					if(jpoMemberOrder.getSysUser().getJmiMember().getCreateTime().after(date)){
        						errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + jpoMemberOrder.getMemberOrderNo() + "，" + LocaleUtil.getLocalText("operation.notice.js.orderNo.dateNotMatch") + ":1");
        						continue;
        					}
    					}*/
    					//小于首购单日期
    					if(!"1".equals(jpoMemberOrder.getOrderType())){
        					String checkTime = jpoMemberOrderManager.getMemberFirstOrderStatusTime(jpoMemberOrder.getSysUser().getUserCode());
        					Date dateCheck = null;
                			try {
                				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                				dateCheck = sdf.parse(checkTime);
                				if (dateCheck == null) {
                					MessageUtil.saveLocaleMessage(request, "common.error.dateFormatError");
                					return new ModelAndView("redirect:" + request.getHeader("Referer"));
                				}
                			} catch (Exception e) {
                				MessageUtil.saveLocaleMessage(request, "common.error.dateFormatError");
                				return new ModelAndView("redirect:" + request.getHeader("Referer"));
                			}
                			
        					//if(dateCheck.after(date)){
        					//	errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + jpoMemberOrder.getMemberOrderNo() + "，" + LocaleUtil.getLocalText("operation.notice.js.orderNo.dateNotMatch") + ":2");
        						//continue;
        					//}
    					}
    					//未审核
    					if (!"2".equals(jpoMemberOrder.getStatus())) {
    						errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + jpoMemberOrder.getMemberOrderNo() + "，" + LocaleUtil.getLocalText("operation.notice.js.orderNo.unAudit"));
    						continue;
    					}
    					Date bakAuditDate = jpoMemberOrder.getCheckTime();
    					int later = Integer.parseInt(ConfigUtil.getConfigValue(jpoMemberOrder.getCompanyCode(), "order.edittime.later"));
    					//40天
    					int early = Integer.parseInt(ConfigUtil.getConfigValue(jpoMemberOrder.getCompanyCode(), "order.edittime.early"));
    					
    					//判断是否在审核时间之前
    					boolean notMatch = true;
//    					20150312 w
//    					if(bakAuditDate.after(date)){
//    						int dateArea = DateUtil.daysBetweenDates( bakAuditDate,date);
//    						log.info("one dateArea = "+dateArea+" and early="+early);
//    						if (dateArea > early) {
//    							notMatch = false;
//    						} 
//    					}else{
//    						int dateArea = DateUtil.daysBetweenDates( date,bakAuditDate);
//    						log.info("two dateArea = "+dateArea+" and early="+later);
//    						if (dateArea > later) {
//    							//推后超过1日
//    							notMatch = false;
//    						}
//    					}
    					if (false == notMatch) {
    						errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + jpoMemberOrder.getMemberOrderNo() + "，" + LocaleUtil.getLocalText("operation.notice.js.orderNo.dateNotMatch") + ":3");
    						continue;
    					}else{
    						BdPeriod bdPeriod = bdPeriodManager.getBdPeriodByTime(date, null);
    						BdPeriod lastDate = bdPeriodManager.getBdPeriodByTime(new Date(), null);
    						if(date.after(lastDate.getEndTime())){
    							errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + jpoMemberOrder.getMemberOrderNo() + "，" + LocaleUtil.getLocalText("operation.notice.js.orderNo.dateNotMatch"));
    							continue;
    						}
    						log.info("status ="+bdPeriod.getArchivingStatus());
    						if (bdPeriod.getArchivingStatus()==0) {
    							try{
                        			jpoMemberOrderManager.changeJpoMemberOrderDate(jpoMemberOrder,operatorSysUser,date);
                        			successList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + jpoMemberOrder.getMemberOrderNo() + "，" + LocaleUtil.getLocalText("poMemberOrder.updated"));
                        		}catch(AppException app){
                        			errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + jpoMemberOrder.getMemberOrderNo() + "，" + LocaleUtil.getLocalText("operation.notice.js.orderNo.udpateFail",new String[]{app.getMessage()}));
                        		}
    						}else{
    							errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + jpoMemberOrder.getMemberOrderNo() + "，" + LocaleUtil.getLocalText("operation.notice.js.orderNo.dateNotMatch") + ":4");
    							continue;
    						}
    					}
                	}
                }
            }
            request.setAttribute("errorList", errorList);
            request.setAttribute("successList", successList);
        }
        
        String search = request.getParameter("search");
        CommonRecord crm = RequestUtil.toCommonRecord(request);
    	crm.addField("companyCode", loginSysUser.getCompanyCode());
    	crm.addField("locked", "0");
    	crm.addField("status", "2");
    	crm.addField("orderTypeIn", "1,2,4,6,9,11,12,14,40");
    
    	List jpoMemberOrders = null;
        Pager pager = new Pager("jpoMemberOrderListTable",request, 500);
        if(!StringUtils.isEmpty(crm.getString("search",""))){
        jpoMemberOrders = jpoMemberOrderManager.
    		getJpoMemberOrdersByCrm(crm,pager);
        }
            
        request.setAttribute("jpoMemberOrderListTable_totalRows", pager.getTotalObjects());
        
        return new ModelAndView("po/jpoMemberOrdersChangeDate","jpoMemberOrderList",jpoMemberOrders);
    }
}
