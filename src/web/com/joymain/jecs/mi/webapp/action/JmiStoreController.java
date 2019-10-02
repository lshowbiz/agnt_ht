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
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.mi.model.JmiStore;
import com.joymain.jecs.mi.model.JmiSubStore;
import com.joymain.jecs.mi.service.JmiStoreManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JmiStoreController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiStoreController.class);
    private JmiStoreManager jmiStoreManager = null;

    private AlStateProvinceManager alStateProvinceManager;
    private AlCityManager alCityManager;
    private AlDistrictManager alDistrictManager;
    public void setJmiStoreManager(JmiStoreManager jmiStoreManager) {
        this.jmiStoreManager = jmiStoreManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

    	SysUser defSysUser=SessionLogin.getLoginUser(request);
    	
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        
        
    	if("M".equals(defSysUser.getUserType())){
    		JmiStore jmiStore=jmiStoreManager.getJmiStoreByUserCode(defSysUser.getUserCode());
    		if(jmiStore!=null){
        		request.setAttribute("jmiStoreExist", "jmiStoreExist");
    		}
    		crm.addField("userCode", defSysUser.getUserCode());
    	}else{
    		crm.addField("companyCode", defSysUser.getCompanyCode());
    	}

        String userCode=request.getParameter("userCode");
        String checkStatus=request.getParameter("checkStatus");
        String confirmStatus=request.getParameter("confirmStatus");
        String startTime=request.getParameter("startTime");
        String endTime=request.getParameter("endTime");

        List jmiStoreList=null;

        Pager pager = new Pager("jmiStoreListTable",request, 20);
        
        if(!StringUtil.isEmpty(crm.getString("userCode", ""))||!StringUtil.isEmpty(checkStatus)||!StringUtil.isEmpty(confirmStatus)||StringUtil.isDate(startTime)||StringUtil.isDate(endTime)){
        	
        	if("xslText".equals(request.getParameter("xslText"))){
				 pager = new Pager("jmiSubStoreListTable", request, 0);
			}

            jmiStoreList = jmiStoreManager.getJmiStoresByCrm(crm,pager);
       	
			if("xslText".equals(request.getParameter("xslText"))){
				this.getReport(jmiStoreList, request, response, defSysUser);
				return null;
			}
        }

        
        
        
        request.setAttribute("jmiStoreListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("mi/jmiStoreList", Constants.JMISTORE_LIST, jmiStoreList);
    }
    

	//导出EXCEL。。
	private void getReport(List jmiStoreList,HttpServletRequest request, HttpServletResponse response,SysUser defSysUser) throws Exception{
		
		
		
//		生成excel文件
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
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("miMember.sex"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("bdPinTitleRecord.pinTitle"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("miMember.papernumber"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("subStore.postalcode"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("store.mailAddr"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("subStore.mobiletele"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("miMember.email"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("miMember.createTime"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("customerFollow.state"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("jmiSubStore.addrCheckUser"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("jmiSubStore.checkRemark"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("pd.okstatus"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("jmiSubStore.addrConfirmUser"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("jmiSubStore.confirmRemark"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("store.orderTime"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("store.subStoreAddr"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("store.storeAddr"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("jmiSubStore.checkRemark"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("jmiSubStore.confirmRemark"));
		eu.addString(wsheet, i++, index, LocaleUtil.getLocalText("miMember.remark"));
		

		index++;
		
		

		Map checkStatusMap=ListUtil.getListOptions(defSysUser.getCompanyCode(), "jmisubstore.status");
		Map confirmStatusMap=ListUtil.getListOptions(defSysUser.getCompanyCode(), "jmisubstore.confirmstatus");
		Map sexMap=ListUtil.getListOptions(defSysUser.getCompanyCode(), "sex");
		Map honorStarMap=ListUtil.getListOptions(defSysUser.getCompanyCode(), "honor.star.zero");
		
		for (int j = 0; j < jmiStoreList.size(); j++) {
			i=0;
			JmiStore jmiStore=(JmiStore) jmiStoreList.get(j);
			
			eu.addString(wsheet, i++, index,jmiStore.getJmiMember().getUserCode());
			eu.addString(wsheet, i++, index,jmiStore.getJmiMember().getMiUserName());
			if(jmiStore.getJmiMember().getSex()==null){

				eu.addString(wsheet, i++, index,"");
			}else{
				eu.addString(wsheet, i++, index,formatString(LocaleUtil.getLocalText(sexMap.get(jmiStore.getJmiMember().getSex()).toString())));
			}
			
			eu.addString(wsheet, i++, index,formatString(LocaleUtil.getLocalText(honorStarMap.get(jmiStore.getHonorStar().toString()).toString())));
			eu.addString(wsheet, i++, index,formatString(jmiStore.getJmiMember().getPapernumber()));
			eu.addString(wsheet, i++, index,formatString(jmiStore.getPostalcode()));
			eu.addString(wsheet, i++, index,formatString(jmiStore.getMailAddr()));
			eu.addString(wsheet, i++, index,formatString(jmiStore.getMobiletele()));
			eu.addString(wsheet, i++, index,formatString(jmiStore.getEmail()));
			if(null!=jmiStore.getCreateTime()){
				eu.addString(wsheet, i++, index,jmiStore.getCreateTime().toString());
			}else{
				eu.addString(wsheet, i++, index,"");
			}

			eu.addString(wsheet, i++, index,LocaleUtil.getLocalText(checkStatusMap.get(jmiStore.getCheckStatus()).toString()));
			eu.addString(wsheet, i++, index,formatString(jmiStore.getCheckUser()));
			eu.addString(wsheet, i++, index,formatString(jmiStore.getCheckRemark()));
			
			eu.addString(wsheet, i++, index,LocaleUtil.getLocalText(confirmStatusMap.get(jmiStore.getConfirmStatus()).toString()));
			eu.addString(wsheet, i++, index,formatString(jmiStore.getConfirmUser()));
			eu.addString(wsheet, i++, index,formatString(jmiStore.getConfirmRemark()));
			
			if(null!=jmiStore.getOrderTime()){
				eu.addString(wsheet, i++, index,jmiStore.getOrderTime().toString());
			}else{
				eu.addString(wsheet, i++, index,"");
			}
			
			eu.addString(wsheet, i++, index,formatString(jmiStore.getSubStoreAddr()));
			
			String alStateProvinceStr="";
			if(null!=jmiStore.getProvince()){
				AlStateProvince alStateProvince=alStateProvinceManager.getAlStateProvince(jmiStore.getProvince().toString());
				alStateProvinceStr=alStateProvince.getStateProvinceName();
			}
			String alCityStr="";
			if(null!=jmiStore.getCity()){
				AlCity alCity=alCityManager.getAlCity(jmiStore.getCity().toString());
				alCityStr=alCity.getCityName();
			}
			String alDistrictStr="";
			if(null!=jmiStore.getDistrict()){
				AlDistrict alDistrict=alDistrictManager.getAlDistrict(jmiStore.getDistrict().toString());
				alDistrictStr=alDistrict.getDistrictName();
			}
			
			
			
			eu.addString(wsheet, i++, index,alStateProvinceStr+alCityStr+alDistrictStr+formatString(jmiStore.getAddress()));
			

			eu.addString(wsheet, i++, index,formatString(jmiStore.getCheckRemark()));
			eu.addString(wsheet, i++, index,formatString(jmiStore.getConfirmRemark()));
			eu.addString(wsheet, i++, index,formatString(jmiStore.getJmiMember().getRemark()));


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
