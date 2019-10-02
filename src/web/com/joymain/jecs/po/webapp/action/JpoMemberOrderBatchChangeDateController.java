package com.joymain.jecs.po.webapp.action;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.Constants;
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
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 批次更新审核日期
 * @author Alvin
 *
 */
public class JpoMemberOrderBatchChangeDateController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoMemberOrderBatchChangeDateController.class);
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
        RequestUtil.freshSession(request,"updateDatesAll",300l);

		String strAction = request.getParameter("strAction");
		if("post".equals(request.getMethod().toLowerCase())){
        	if("C".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"updateDatesAll",300l)!=0){
        			return new ModelAndView("redirect:jpoMemberOrdersBatchChangeDate.html");
        		}
        	}
			try {
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
				
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("xlsFile");

				//retrieve the file data
				InputStream stream = file.getInputStream();
				
				ExcelUtil eu = new ExcelUtil();
				//获取可读的工作表对象，定位到要读取的excel文件
				Workbook workbook = eu.getWorkbook(stream);
				//读取此文件的第一个工作表，工作表序号从0开始。
				Sheet sheet = workbook.getSheet(0);

	        	List successList=new ArrayList();
	        	List errorList=new ArrayList();
	        	
				for (int i = 1; i < sheet.getRows(); i++) {
					String memberOrderNo = StringUtils.trim(eu.getContents(sheet, 0, i));
					JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(memberOrderNo);
					
					if(jpoMemberOrder==null){
						errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + memberOrderNo + "，" + LocaleUtil.getLocalText("operation.notice.js.orderNo.udpateFail"));
						continue;
					}else{
						if(jpoMemberOrder.getOrderType().equals("3") || jpoMemberOrder.getOrderType().equals("5")){
							errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + memberOrderNo + "，" + LocaleUtil.getLocalText("operation.notice.js.orderNo.udpateFail"));
							continue;
						}
						if(!jpoMemberOrder.getCompanyCode().equals(loginSysUser.getCompanyCode())){
							errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + memberOrderNo + "，" + LocaleUtil.getLocalText("operation.notice.js.orderNo.udpateFail"));
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
        						errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + jpoMemberOrder.getMemberOrderNo() + "，" + LocaleUtil.getLocalText("operation.notice.js.orderNo.dateNotMatch"));
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
        					/*if(dateCheck.after(date)){
        						errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + jpoMemberOrder.getMemberOrderNo() + "，" + LocaleUtil.getLocalText("operation.notice.js.orderNo.dateNotMatch"));
        						continue;
        					}*/
    					}
    					//未审核
    					if (!"2".equals(jpoMemberOrder.getStatus())) {
    						errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + jpoMemberOrder.getMemberOrderNo() + "，" + LocaleUtil.getLocalText("operation.notice.js.orderNo.unAudit"));
    						continue;
    					}
    					
    					//订单已挂起，不允许调期
                		if("1".equals(jpoMemberOrder.getReturnableStatus())){
							errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + jpoMemberOrder.getMemberOrderNo() + "，" + LocaleUtil.getLocalText("订单已挂起，不允许调期"));
							continue;
						}
    					
    					Date bakAuditDate = jpoMemberOrder.getCheckTime();
    					int later = Integer.parseInt(ConfigUtil.getConfigValue(jpoMemberOrder.getCompanyCode(), "order.edittime.later"));
    					int early = Integer.parseInt(ConfigUtil.getConfigValue(jpoMemberOrder.getCompanyCode(), "order.edittime.early"));
    					
    					//判断是否在审核时间之前
    					boolean notMatch = true;
    					if(bakAuditDate.after(date)){
    						int dateArea = DateUtil.daysBetweenDates( bakAuditDate,date);
    						if (dateArea > early) {
    							//超过前5日
    							notMatch = false;
    						} 
    					}else{
    						int dateArea = DateUtil.daysBetweenDates( date,bakAuditDate);
    						if (dateArea > later) {
    							//推后超过1日
    							notMatch = false;
    						}
    					}
    					if (false == notMatch) {
    						errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + jpoMemberOrder.getMemberOrderNo() + "，" + LocaleUtil.getLocalText("operation.notice.js.orderNo.dateNotMatch"));
    						continue;
    					}else{
    						BdPeriod bdPeriod = bdPeriodManager.getBdPeriodByTime(date, null);
    						if (bdPeriod.getArchivingStatus()==0) {
    							try{
                        			jpoMemberOrderManager.changeJpoMemberOrderDate(jpoMemberOrder,operatorSysUser,date);
                        			successList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + jpoMemberOrder.getMemberOrderNo() + "，" + LocaleUtil.getLocalText("poMemberOrder.updated"));
                        		}catch(AppException app){
                        			errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + jpoMemberOrder.getMemberOrderNo() + "，" + LocaleUtil.getLocalText("operation.notice.js.orderNo.udpateFail",new String[]{app.getMessage()}));
                        		}
    						}else{
    							errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + jpoMemberOrder.getMemberOrderNo() + "，" + LocaleUtil.getLocalText("operation.notice.js.orderNo.dateNotMatch"));
    							continue;
    						}
    					}
					}
				}
				eu.closeWorkbook(workbook);
	            request.setAttribute("errorList", errorList);
	            request.setAttribute("successList", successList);
			} catch (Exception ex) {
				saveMessage(request, LocaleUtil.getLocalText("dataImport.unSuc")+": "+ex.getMessage());
				return new ModelAndView("po/jpoMemberOrdersBatchChangeDate");
			}
			return new ModelAndView("po/jpoMemberOrdersBatchChangeDate");
		}else{
			return new ModelAndView("po/jpoMemberOrdersBatchChangeDate");
		}
    }
}
