package com.joymain.jecs.mi.webapp.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.io.OutputStream;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.bd.model.JbdSendRecordHist;
import com.joymain.jecs.mi.model.JmiSubStore;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiSubStoreManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JmiSubStoreAddrController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiSubStoreAddrController.class);
    private JmiSubStoreManager jmiSubStoreManager = null;
    private JmiMemberManager jmiMemberManager;
    private AlStateProvinceManager alStateProvinceManager;
    private AlCityManager alCityManager;
    private AlDistrictManager alDistrictManager;
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	public void setJmiSubStoreManager(JmiSubStoreManager jmiSubStoreManager) {
        this.jmiSubStoreManager = jmiSubStoreManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

    	SysUser defSysUser=SessionLogin.getLoginUser(request);
    	String strAction=request.getParameter("strAction");
//        if("checkJmiSubStore".equals(strAction)||"unCheckJmiSubStore".equals(strAction)){
//        	String[] strCodes = request.getParameter("strCodes").split(",");
//        	String checkStatus="";
//        	String checkRemark=request.getParameter("checkRemark");
//        	if("checkJmiSubStore".equals(strAction)){
//        		checkStatus="1";
//        	}else if("unCheckJmiSubStore".equals(strAction)){
//        		checkStatus="3";
//        	}
//			try {
//				
//				jmiSubStoreManager.checkJmiSubStore(strCodes, defSysUser, checkStatus, checkRemark);
//				saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.operaterSuccess"));
//			} catch (Exception e) {
//				saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.operaterFail"));
//			}
//        }
//    	
//
//        if("confirmJmiSubStore".equals(strAction)||"unConfirmJmiSubStore".equals(strAction)){
//        	String[] strCodes = request.getParameter("strCodes").split(",");
//        	String confirmStatus="";
//        	String confirmRemark=request.getParameter("confirmRemark");
//        	if("confirmJmiSubStore".equals(strAction)){
//        		confirmStatus="1";
//        	}else if("unConfirmJmiSubStore".equals(strAction)){
//        		confirmStatus="2";
//        	}
//			try {
//				
//				jmiSubStoreManager.confirmJmiSubStore(strCodes, defSysUser, confirmStatus, confirmRemark);
//				saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.operaterSuccess"));
//			} catch (Exception e) {
//				saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.operaterFail"));
//			}
//        }
    	
    	if("businessLiceseJmiSubStore".equals(strAction)||"contracJmiSubStore".equals(strAction)||"storePicJmiSubStore".equals(strAction)||"noticeTimeJmiSubStore".equals(strAction)||"addrConfirmJmiSubStore".equals(strAction)){
    		this.saveJmiSubStore(request, strAction);
    	}

    	
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        
        String userCode=request.getParameter("userCode");
        String subStoreStatus=request.getParameter("subStoreStatus");
        String confirmStatus=request.getParameter("confirmStatus");
        String startTime=request.getParameter("startTime");
        String endTime=request.getParameter("endTime");
        
    	if("M".equals(defSysUser.getUserType())){
    		crm.addField("userCode", defSysUser.getUserCode());
    	}else{
    		crm.addField("companyCode", defSysUser.getCompanyCode());
    	}

    	crm.addField("subStoreStatusNotNull", "subStoreStatusNotNull");
    	
        Pager pager = new Pager("jmiSubStoreListTable",request, 20);
        
        List jmiSubStores=null;

        if(!StringUtil.isEmpty(crm.getString("userCode", ""))||!StringUtil.isEmpty(subStoreStatus)||!StringUtil.isEmpty(confirmStatus)||StringUtil.isDate(startTime)||StringUtil.isDate(endTime)){

 			if("xslText".equals(request.getParameter("xslText"))){
 				 pager = new Pager("jmiSubStoreListTable", request, 0);
 			}
 			
        	jmiSubStores = jmiSubStoreManager.getJmiSubStoresByCrm(crm,pager);
        	
			if("xslText".equals(request.getParameter("xslText"))){
				this.getReport(jmiSubStores, request, response, defSysUser);
				return null;
			}
 			
        }
        request.setAttribute("jmiSubStoreListTable_totalRows", pager.getTotalObjects());
        

        return new ModelAndView("mi/jmiSubStoreAddrList", Constants.JMISUBSTORE_LIST, jmiSubStores);
    }
    
	//导出EXCEL。。
	private void getReport(List jmiSubStores,HttpServletRequest request, HttpServletResponse response,SysUser defSysUser) throws Exception{

		//生成excel文件
   	 	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        String date = format.format(new Date());
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-Disposition", "attachment; filename="+date+".xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=30" ); 
		OutputStream os = response.getOutputStream();
		ExcelUtil eu = new ExcelUtil();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		//在此创建的新excel文件创建一工作表 
		WritableSheet wsheet = wwb.createSheet("Sheet1", 0);
		
		int index=0;
		int i=0;
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("miMember.memberNo"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("bdCalculatingSubDetail.name"));
		
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("subStore.province"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("subStore.city"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("subStore.address"));
		
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("subStore.mobiletele"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("miMember.cardType"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("miMember.subRecommendStore"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("miMember.subRecommendStore.name"));

		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("miMember.createTime"));


		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("jmiSubStore.subStoreCheckDate"));

		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("customerFollow.state"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("jmiSubStore.addrCheckUser"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("jmiSubStore.checkRemark"));

		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("pd.okstatus"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("jmiSubStore.addrConfirmUser"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("jmiSubStore.confirmRemark"));
		
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("jmiSubStore.businessLicese"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("jmiSubStore.contract"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("jmiSubStore.storePic"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("jmiSubStore.noticeTime"));
		
		

		index++;
		

		Map provinceMap=alStateProvinceManager.getAlStateProvincesMapByCountry(defSysUser.getCompanyCode());
		Map cityMap=alCityManager.getAlCityMap(defSysUser.getCompanyCode());


		Map cardTypeMap=ListUtil.getListOptions(defSysUser.getCompanyCode(), "bd.cardtype");
		Map checkStatusMap=ListUtil.getListOptions(defSysUser.getCompanyCode(), "jmisubstore.status");
		Map confirmStatusMap=ListUtil.getListOptions(defSysUser.getCompanyCode(), "jmisubstore.confirmstatus");
		
		
		for (int j = 0; j < jmiSubStores.size(); j++) {
			i=0;
			JmiSubStore jmiSubStore=(JmiSubStore) jmiSubStores.get(j);
			

			eu.addString(wsheet, i++, index,jmiSubStore.getJmiMember().getUserCode());
			eu.addString(wsheet, i++, index,jmiSubStore.getJmiMember().getMiUserName());
			

			String alStateProvinceStr="";
			if(null!=jmiSubStore.getProvince()){
				AlStateProvince alStateProvince=alStateProvinceManager.getAlStateProvince(jmiSubStore.getProvince().toString());
				alStateProvinceStr=alStateProvince.getStateProvinceName();
			}
			String alCityStr="";
			if(null!=jmiSubStore.getCity()){
				AlCity alCity=alCityManager.getAlCity(jmiSubStore.getCity().toString());
				alCityStr=alCity.getCityName();
			}
			String alDistrictStr="";
			if(null!=jmiSubStore.getDistrict()){
				AlDistrict alDistrict=alDistrictManager.getAlDistrict(jmiSubStore.getDistrict().toString());
				alDistrictStr=alDistrict.getDistrictName();
			}
			eu.addString(wsheet, i++, index,alStateProvinceStr);
			eu.addString(wsheet, i++, index,alCityStr);
			eu.addString(wsheet, i++, index,formatString(jmiSubStore.getAddress()));
			eu.addString(wsheet, i++, index,jmiSubStore.getMobiletele());
			eu.addString(wsheet, i++, index,LocaleUtil.getLocalText(cardTypeMap.get(jmiSubStore.getJmiMember().getCardType()).toString()));

			eu.addString(wsheet, i++, index,jmiSubStore.getJmiMember().getSubRecommendStore());
			
			if(jmiMemberManager.getJmiMember(jmiSubStore.getJmiMember().getSubRecommendStore())!=null){
				eu.addString(wsheet, i++, index,jmiMemberManager.getJmiMember(jmiSubStore.getJmiMember().getSubRecommendStore()).getMiUserName());
			}else{
				eu.addString(wsheet, i++, index,"");
			}
			
			
			eu.addString(wsheet, i++, index,jmiSubStore.getCreateTime().toString());
			
			if(null!=jmiSubStore.getJmiMember().getSubStoreCheckDate()){
				eu.addString(wsheet, i++, index,jmiSubStore.getJmiMember().getSubStoreCheckDate().toString());
			}else{
				eu.addString(wsheet, i++, index, "");
			}
			
			
			eu.addString(wsheet, i++, index,LocaleUtil.getLocalText(checkStatusMap.get(jmiSubStore.getAddrCheck()).toString()));
			eu.addString(wsheet, i++, index,formatString(jmiSubStore.getAddrCheckUser()));
			eu.addString(wsheet, i++, index,formatString(jmiSubStore.getCheckRemark()));
			
			eu.addString(wsheet, i++, index,LocaleUtil.getLocalText(confirmStatusMap.get(jmiSubStore.getAddrConfirm()).toString()));
			eu.addString(wsheet, i++, index,formatString(jmiSubStore.getAddrConfirmUser()));
			eu.addString(wsheet, i++, index,formatString(jmiSubStore.getConfirmRemark()));
			
			eu.addString(wsheet, i++, index,formatStatus(jmiSubStore.getBusinessLicese()));
			eu.addString(wsheet, i++, index,formatStatus(jmiSubStore.getContract()));
			eu.addString(wsheet, i++, index,formatStatus(jmiSubStore.getStorePic()));
			
			if(null!=jmiSubStore.getNoticeTime()){
				eu.addString(wsheet, i++, index,jmiSubStore.getNoticeTime().toString());
			}else{
				eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("busi.notice"));
			}
			index++;
		}
		

		eu.writeExcel(wwb);
		eu.closeWritableWorkbook(wwb);
		os.close();
	}
	private String formatString(String param){
		if(StringUtil.isEmpty(param)){
			return "";
		}
		return param;
	}
	private String formatStatus(String param){
		if("0".equals(param)){
			return LocaleUtil.getLocalText("operation.button.confirm");
		}else if("1".equals(param)){
			return LocaleUtil.getLocalText("busi.cancel");
		}else{
			return "";
		}
	}
	private void saveJmiSubStore(HttpServletRequest request,String strAction){
		String id=request.getParameter("id");
		JmiSubStore jmiSubStore=jmiSubStoreManager.getJmiSubStore(id);
		
    	if("businessLiceseJmiSubStore".equals(strAction)){
    		if("0".equals(jmiSubStore.getBusinessLicese())){
    			jmiSubStore.setBusinessLicese("1");
    		}else{
    			jmiSubStore.setBusinessLicese("0");
    		}
    	}else if("contracJmiSubStore".equals(strAction)){
    		if("0".equals(jmiSubStore.getContract())){
    			jmiSubStore.setContract("1");
    		}else{
    			jmiSubStore.setContract("0");
    		}
    	}else if("storePicJmiSubStore".equals(strAction)){
    		if("0".equals(jmiSubStore.getStorePic())){
    			jmiSubStore.setStorePic("1");
    		}else{
    			jmiSubStore.setStorePic("0");
    		}
    	}else if("noticeTimeJmiSubStore".equals(strAction)){
    		if(null==jmiSubStore.getNoticeTime()){
    			jmiSubStore.setNoticeTime(new Date());
    		}
    	}else if("addrConfirmJmiSubStore".equals(strAction)){
    		if("0".equals(jmiSubStore.getAddrConfirm())){
    			jmiSubStore.setAddrConfirm("1");
    		}else{
    			jmiSubStore.setAddrConfirm("0");
    		}
    	}
		try {
			jmiSubStoreManager.saveJmiSubStore(jmiSubStore);
			saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.operaterSuccess"));
		} catch (Exception e) {
			saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.operaterFail"));
		}
	}

	public void setAlCityManager(AlCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}

	public void setAlDistrictManager(AlDistrictManager alDistrictManager) {
		this.alDistrictManager = alDistrictManager;
	}

	public void setAlStateProvinceManager(
			AlStateProvinceManager alStateProvinceManager) {
		this.alStateProvinceManager = alStateProvinceManager;
	}
}
