package com.joymain.jecs.mi.webapp.action;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.multipart.support.StringMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCharacterCodingManager;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.log.util.ReportLogUtil;
import com.joymain.jecs.mi.model.JmiLinkRef;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiRecommendRef;
import com.joymain.jecs.mi.service.JmiLinkRefManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiRecommendRefManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysBankManager;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class MiChangeInfoController extends AbstractWizardFormController {
	private JmiMemberManager jmiMemberManager;
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	private BdPeriodManager bdPeriodManager;
	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public MiChangeInfoController() {
		setCommandClass(ArrayList.class); // 设置命令类 
		setCommandName("jmiMemberList");
	}
	
	//处理表单数据
	protected ModelAndView processFinish(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
//
//		System.out.println("submit");
		
		ArrayList jmiMemberList= (ArrayList) command;

        SysUser defSysUser = SessionLogin.getLoginUser(request);

        String modifyType=request.getParameter("modifyType");
        

		List<Map> jmiMembers=new ArrayList<Map>();

		//开始日志--start
		Long refId2 =ReportLogUtil.beginReport(SessionLogin.getLoginUser(request).getUserCode(), RequestUtil.getIpAddr(request), modifyType, "");
		//开始日志--end
		
		if("modifyMemberLevel".equals(modifyType)){
			
			for (int i = 0; i < jmiMemberList.size(); i++) {
				JmiMember jmiMember=(JmiMember) jmiMemberList.get(i);
				
				String newMemberLevel=request.getParameter(jmiMember.getUserCode()+"_newMemberLevel");
				String wweek=request.getParameter(jmiMember.getUserCode()+"_wweek");
				String remark=request.getParameter(jmiMember.getUserCode()+"_remark");
				Map map=new HashMap();
				map.put("newMember", newMemberLevel);
				map.put("jmiMember", jmiMember);
				map.put("wweek", StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f", wweek)));
				map.put("remark", remark);

				jmiMembers.add(map);
				
			}
			try {
				jmiMemberManager.changeMiMemberMemberLevelAll(jmiMembers,defSysUser);
				MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("filemsg", e.getMessage());
			}
			
		}else if("modifyActive".equals(modifyType)){
			

			for (int i = 0; i < jmiMemberList.size(); i++) {
				JmiMember jmiMember=(JmiMember) jmiMemberList.get(i);
				
				String newActive=request.getParameter(jmiMember.getUserCode()+"_newActive");
				String remark=request.getParameter(jmiMember.getUserCode()+"_remark");
				
				Map map=new HashMap();
				map.put("newActive", newActive);
				map.put("jmiMember", jmiMember);
				map.put("remark", remark);
				jmiMembers.add(map);
			}
			
			try {
				jmiMemberManager.activeMembers(jmiMembers);
				MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("filemsg", e.getMessage());
			}
			
			
		}else if("modifyFreeze".equals(modifyType)){
			

			for (int i = 0; i < jmiMemberList.size(); i++) {
				JmiMember jmiMember=(JmiMember) jmiMemberList.get(i);
				
				String newFreezeStatus=request.getParameter(jmiMember.getUserCode()+"_newFreezeStatus");
				String remark=request.getParameter(jmiMember.getUserCode()+"_remark");
				
				Map map=new HashMap();
				map.put("newFreezeStatus", newFreezeStatus);
				map.put("jmiMember", jmiMember);
				map.put("remark", remark);
				jmiMembers.add(map);
			}
			
			try {
				jmiMemberManager.freezeMembers(jmiMembers);
				MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("filemsg", e.getMessage());
			}
			
			
		}else if("modifyState".equals(modifyType)){
			

			for (int i = 0; i < jmiMemberList.size(); i++) {
				JmiMember jmiMember=(JmiMember) jmiMemberList.get(i);
				
				String newState=request.getParameter(jmiMember.getUserCode()+"_newState");
				String remark=request.getParameter(jmiMember.getUserCode()+"_remark");
				
				Map map=new HashMap();
				map.put("newState", newState);
				map.put("jmiMember", jmiMember);
				map.put("remark", remark);
				jmiMembers.add(map);
			}
			
			try {
				jmiMemberManager.saveStateMembers(jmiMembers,defSysUser);
				MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("filemsg", e.getMessage());
			}
			
			
		}else if("modifyRemark".equals(modifyType)){
			

			for (int i = 0; i < jmiMemberList.size(); i++) {
				JmiMember jmiMember=(JmiMember) jmiMemberList.get(i);
				
				//String newState=request.getParameter(jmiMember.getUserCode()+"_newState");
				String remark=request.getParameter(jmiMember.getUserCode()+"_remark");
				
				Map map=new HashMap();
				//map.put("newState", newState);
				map.put("jmiMember", jmiMember);
				map.put("remark", remark);
				jmiMembers.add(map);
			}
			
			try {
				jmiMemberManager.saveRemarkMembers(jmiMembers);
				MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("filemsg", e.getMessage());
			}
			
			
		}
		
		//更改加盟店类型    add by gw 2014-05-14
        else if("modifyIsstore".equals(modifyType)){
			

			for (int i = 0; i < jmiMemberList.size(); i++) {
				JmiMember jmiMember=(JmiMember) jmiMemberList.get(i);
				
				String newIsstore=request.getParameter(jmiMember.getUserCode()+"_newIsstore");
				String remark=request.getParameter(jmiMember.getUserCode()+"_remark");

				Map map=new HashMap();
				map.put("jmiMember", jmiMember);
				map.put("newIsstore", newIsstore);
				map.put("remark", remark);

				jmiMembers.add(map);
			}
			
			try {
				jmiMemberManager.saveIsstoreMembers(jmiMembers);
				MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("filemsg", e.getMessage());
			}
			
			
		}else if("modifyExitDate".equals(modifyType)){
			

			for (int i = 0; i < jmiMemberList.size(); i++) {
				JmiMember jmiMember=(JmiMember) jmiMemberList.get(i);
				
				String newExitDate=request.getParameter(jmiMember.getUserCode()+"_newExitDate");
				String remark=request.getParameter(jmiMember.getUserCode()+"_remark");
				
				Map map=new HashMap();
				map.put("newExitDate", newExitDate);
				map.put("jmiMember", jmiMember);
				map.put("remark", remark);
				jmiMembers.add(map);
			}
			
			try {
				jmiMemberManager.exitDateMembers(jmiMembers);
				MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("filemsg", e.getMessage());
			}
			
			
		}


		//结束日志--start
		ReportLogUtil.endReport(refId2, modifyType);
		//结束日志--end
		
		return new ModelAndView("redirect:miChangeInfo.html");
		
	}


	@Override
	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors, int page) throws Exception {
		
		ArrayList jmiMemberList= (ArrayList) command;

        String modifyType=request.getParameter("modifyType");
        
        
        SysUser defSysUser = SessionLogin.getLoginUser(request);
		if(page==0){
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");
            if(file!=null && !file.isEmpty()) {
            	

				List<String> messages = new ArrayList<String>();
            	try {
            		InputStream stream = file.getInputStream();

    				ExcelUtil eu = new ExcelUtil();
    				//获取可读的工作表对象，定位到要读取的excel文件
    				Workbook workbook = eu.getWorkbook(stream);
    				//读取此文件的第一个工作表，工作表序号从0开始。
    				Sheet sheet = workbook.getSheet(0);

    				for (int i = 1; i < sheet.getRows(); i++) {
    					String userCode = eu.getContents(sheet, 0, i);
    					
    					String content = " ([ " + userCode + " ])";
    					String message = (i + 1) + ": ";
    					if (StringUtils.isEmpty(userCode)) {
    						messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + LocaleUtil.getLocalText("bdBounsDeduct.error.memberNo.empty") + content+"</font>");
    						continue;
    					}
    					JmiMember miMember = this.jmiMemberManager.getJmiMember(userCode.trim());
    					if (miMember == null) {
    						messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + LocaleUtil.getLocalText("bdBounsDeduct.error.memberNo.notExists") + content+"</font>");
    						continue;
    					}else if(!defSysUser.getCompanyCode().equals(miMember.getCompanyCode())){
    						messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + LocaleUtil.getLocalText("不属于当前分公司") + content+"</font>");
    						continue;
    					}
    					jmiMemberList.add(miMember);
    				}
				} catch (Exception e) {
					request.setAttribute("filemsg", e.getMessage());
					jmiMemberList.clear();
				} finally{
					request.setAttribute("messages", messages);
				}
            }
            
            if(jmiMemberList.isEmpty()){
            	errors.reject("读取会员资料失败","读取会员资料失败");
            	jmiMemberList.clear();
            }
            if(jmiMemberList.size()>1000){
            	errors.reject("数据过多","数据过多");
            	jmiMemberList.clear();
            }
            if(StringUtil.isEmpty(modifyType)){
            	errors.reject("请选择修改类型","请选择修改类型");
            	jmiMemberList.clear();
            }else{
            	request.setAttribute("modifyType", modifyType);
            }
		}
		
		if(page==1){

			//更改级别
			
			
			if("modifyMemberLevel".equals(modifyType)){
				
				for (int i = 0; i < jmiMemberList.size(); i++) {
					JmiMember jmiMember=(JmiMember) jmiMemberList.get(i);
					String newMemberLevel=request.getParameter(jmiMember.getUserCode()+"_newMemberLevel");
					String wweek=request.getParameter(jmiMember.getUserCode()+"_wweek");

					String userCode = jmiMember.getUserCode();
					String msg="";
					if(StringUtil.isEmpty(newMemberLevel)){
						msg="新级别不能为空";
						errors.reject(userCode+msg,userCode+msg);
					}else if(jmiMember.getMemberLevel().equals(newMemberLevel)){
						msg="会员新旧级别相同";
						errors.reject(userCode+msg,userCode+msg);
					}
					
					if(StringUtil.isEmpty(wweek)){
						msg="期别不能为空";
						errors.reject(userCode+msg,userCode+msg);
					}else{
			    		BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(wweek);
			    		if(!StringUtil.isInteger(wweek)||bdPeriod==null||bdPeriod.getArchivingStatus()==1){
							msg="期别已结算";
							errors.reject(userCode+msg,userCode+msg);
			    		}
					}
				}
				
			}else if("modifyActive".equals(modifyType)){
				//更改死点状态
				for (int i = 0; i < jmiMemberList.size(); i++) {
					JmiMember jmiMember=(JmiMember) jmiMemberList.get(i);
					String newActive=request.getParameter(jmiMember.getUserCode()+"_newActive");

					String userCode = jmiMember.getUserCode();
					String msg="";
					if(StringUtil.isEmpty(newActive)){
						msg="死点更改状态 不能为空";
						errors.reject(userCode+msg,userCode+msg);
					}else if(jmiMember.getActive().equals(newActive)){
						msg="新旧死点状态相同";
						errors.reject(userCode+msg,userCode+msg);
					}
				}	
			}else if("modifyFreeze".equals(modifyType)){
				for (int i = 0; i < jmiMemberList.size(); i++) {
					JmiMember jmiMember=(JmiMember) jmiMemberList.get(i);
					String newFreezeStatus=request.getParameter(jmiMember.getUserCode()+"_newFreezeStatus");

					String userCode = jmiMember.getUserCode();
					String msg="";
					if(StringUtil.isEmpty(newFreezeStatus)){
						msg="冻结更改状态 不能为空";
						errors.reject(userCode+msg,userCode+msg);
					}else if(jmiMember.getFreezeStatus().toString().equals(newFreezeStatus)){
						msg="新旧冻结状态相同";
						errors.reject(userCode+msg,userCode+msg);
					}
				}	
			}else if("modifyState".equals(modifyType)){
				//更改死点状态
				for (int i = 0; i < jmiMemberList.size(); i++) {
					JmiMember jmiMember=(JmiMember) jmiMemberList.get(i);
					String newState=request.getParameter(jmiMember.getUserCode()+"_newState");

					String userCode = jmiMember.getUserCode();
					String msg="";
					if(StringUtil.isEmpty(newState)){
						msg="登陆更改状态 不能为空";
						errors.reject(userCode+msg,userCode+msg);
					}else if(jmiMember.getSysUser().getState().equals(newState)){
						msg="新旧登陆状态相同";
						errors.reject(userCode+msg,userCode+msg);
					}
				}	
			}else if("modifyRemark".equals(modifyType)){
				//更改备注
			}
			
			//update gw 2014-05-14 
			else if("modifyIsstore".equals(modifyType)){
				//更改加盟店类型
				for (int i = 0; i < jmiMemberList.size(); i++) {
					JmiMember jmiMember=(JmiMember) jmiMemberList.get(i);
					String newIsstore=request.getParameter(jmiMember.getUserCode()+"_newIsstore");

					String userCode = jmiMember.getUserCode();
					String msg="";
					if(StringUtil.isEmpty(newIsstore)){
						msg="更改加盟店类型不能为空";
						errors.reject(userCode+msg,userCode+msg);
					}else if(jmiMember.getIsstore().equals(newIsstore)){
						msg="新旧加盟店类型相同";
						errors.reject(userCode+msg,userCode+msg);
					}
				}	
				
			}else if("modifyExitDate".equals(modifyType)){
				for (int i = 0; i < jmiMemberList.size(); i++) {
					JmiMember jmiMember=(JmiMember) jmiMemberList.get(i);
					String newExitDate=request.getParameter(jmiMember.getUserCode()+"_newExitDate");

					String userCode = jmiMember.getUserCode();
					String msg="";
					if(StringUtil.isEmpty(newExitDate)){
						msg="退出更改状态 不能为空";
						errors.reject(userCode+msg,userCode+msg);
					}else if("0".equals(newExitDate) && null==jmiMember.getExitDate()){
						msg="新旧退出状态相同";
						errors.reject(userCode+msg,userCode+msg);
					}else if("1".equals(newExitDate) && null!=jmiMember.getExitDate()){
						msg="新旧退出状态相同";
						errors.reject(userCode+msg,userCode+msg);
					}
				}	
			}
			
		}
		try {
			
			super.onBindAndValidate(request, command, errors, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	@Override
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Integer.class, null, new CustomNumberEditor(Integer.class, null, true));
		binder.registerCustomEditor(Long.class, null, new CustomNumberEditor(Long.class, null, true));
		
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());//BLOB   
		binder.registerCustomEditor(String.class, new StringMultipartFileEditor());//CLOB
		//SimpleDateFormat dateFormat = new SimpleDateFormat(getText("date.format"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

}
