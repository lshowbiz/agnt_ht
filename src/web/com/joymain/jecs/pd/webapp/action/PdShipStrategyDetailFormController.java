package com.joymain.jecs.pd.webapp.action;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.pd.model.PdShipStrategyDetail;
import com.joymain.jecs.pd.service.PdShipStrategyDetailManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class PdShipStrategyDetailFormController extends BaseFormController {
    private PdShipStrategyDetailManager pdShipStrategyDetailManager = null;

    public void setPdShipStrategyDetailManager(PdShipStrategyDetailManager pdShipStrategyDetailManager) {
        this.pdShipStrategyDetailManager = pdShipStrategyDetailManager;
    }
    public PdShipStrategyDetailFormController() {
        setCommandName("pdShipStrategyDetail");
        setCommandClass(PdShipStrategyDetail.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String ssdId = request.getParameter("ssdId");
        PdShipStrategyDetail pdShipStrategyDetail = null;

        if (!StringUtils.isEmpty(ssdId)) {
        	super.initStateCodeParem(request);
            pdShipStrategyDetail = pdShipStrategyDetailManager.getPdShipStrategyDetail(ssdId);
        } else {
            pdShipStrategyDetail = new PdShipStrategyDetail();
        }
        CommonRecord crm = RequestUtil.toCommonRecord(request);
        /*SysUser su = SessionLogin.getLoginUser(request);
        String companyCode = su.getCompanyCode();
        crm.addField("companyCode", companyCode);*/
        crm.addField("companyCode", "CN");
        //查找省份
		List areaList = pdShipStrategyDetailManager.findArea(crm);
		request.setAttribute("areaList", areaList);
        return pdShipStrategyDetail;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
	    PdShipStrategyDetail pdShipStrategyDetail = (PdShipStrategyDetail) command;
	    String ssId = request.getParameter("ssId");
	    String[] shipArea = request.getParameterValues("shipAreaA");
	    String warehouseNo = request.getParameter("warehouseNo");
	    String shNo = request.getParameter("shNo");
//	    pdShipStrategyDetail = (PdShipStrategyDetail) command;
	    Locale locale = request.getLocale();
		String key=null;
		String strAction = request.getParameter("strAction");
		if ("deletePdShipStrategyDetail".equals(strAction)  ) {
			pdShipStrategyDetailManager.removePdShipStrategyDetail(pdShipStrategyDetail.getSsdId().toString());
			key="pdShipStrategyDetail.delete";
		}else if("addPdShipStrategyDetail".equals(strAction)){
			
			if(shipArea==null || shipArea.length==0){
				errors.reject("error.shipArea.notnull", new Object[] {},LocaleUtil.getLocalText("error.shipArea.notnull"));
				return showForm(request, response, errors);
			}
			
			if(warehouseNo==null || "".equals(warehouseNo)){
				errors.reject("error.warehouseNo.notnull", new Object[] {},LocaleUtil.getLocalText("error.warehouseNo.notnull"));
				return showForm(request, response, errors);
			}
			
			if(shNo==null || "".equals(shNo)){
				errors.reject("error.shNo.notnull", new Object[] {},LocaleUtil.getLocalText("error.shNo.notnull"));
				return showForm(request, response, errors);
			}
			
			if (shipArea != null && shipArea.length > 0) {
				for (String area : shipArea) {
					pdShipStrategyDetail = new PdShipStrategyDetail();
					pdShipStrategyDetail.setSsId(ssId);
					pdShipStrategyDetail.setShipArea(Long.parseLong(area));
					pdShipStrategyDetail.setWarehouseNo(warehouseNo);
					pdShipStrategyDetail.setShNo(shNo);
					pdShipStrategyDetail.setStatus("1");//新增的都是禁用的
					pdShipStrategyDetailManager.savePdShipStrategyDetail(pdShipStrategyDetail);
				}
			}
			key="pdShipStrategyDetail.add";
		}else if("editPdShipStrategyDetail".equals(strAction)){
			pdShipStrategyDetailManager.savePdShipStrategyDetail(pdShipStrategyDetail);
			key="pdShipStrategyDetail.update";
		}else if("confirmPdShipStrategyList".equals(strAction)){
//			jpmProductSaleNew.setStatus("1"); 
			String view = "pdShipStrategyDetails.html?strAction=ssDetailList&ssId=22";
			key="jpmProductSaleNew.batchAudit"; 
			
			//修改人
//			jpmProductSaleNew.setUpdateUserCode(loginUser.getUserCode());
//			jpmProductSaleNew.setUpdateTime(new Date());
			
			String uniNoStr = request.getParameter("uniNoStr");
			String status2 = request.getParameter("status2"); 
			if(StringUtils.isNotEmpty(uniNoStr) && StringUtils.isNotEmpty(uniNoStr)){
				int i = pdShipStrategyDetailManager.batchAuditPdShipNews(uniNoStr, status2); 
				key = "jpmProductSaleNew.batchAudit";
			}
			request.setAttribute("uniNoStr", uniNoStr);
			request.setAttribute("status2", status2); 
		} 
		saveMessage(request, getText(sessionLogin.getDefCharacterCoding(), key));
	        return new ModelAndView("redirect:pdShipStrategyDetails.html?strAction=ssDetailList&ssId=" + ssId);
//	        return new ModelAndView(getSuccessView());
	}
    
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
    	String view = "";
		String key = "";
		String uniNoStr = request.getParameter("uniNoStr");//获取多选字符串的uniNoStr
		String status2 = request.getParameter("status2");//修改的状态
		String ssId = request.getParameter("ssId");//获取多选字符串的uniNoStr
		request.setAttribute("uniNoStr", uniNoStr);
		request.setAttribute("status2", status2);
		request.setAttribute("ssId", ssId); 
		if(StringUtils.isNotEmpty(uniNoStr) && !"null".equals(uniNoStr)){//批量审核处理
			view = "redirect:pdShipStrategyDetails.html?strAction=ssDetailList";
			key="pdShipStrategyDetail.batchAudit.success";
			saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), key));  
			return ; 
		}else{
			super.initBinder(request, binder);
		}
	}
}
