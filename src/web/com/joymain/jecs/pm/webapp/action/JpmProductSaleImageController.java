package com.joymain.jecs.pm.webapp.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pm.model.JpmProductSaleImage;
import com.joymain.jecs.pm.service.JpmProductSaleImageManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpmProductSaleImageController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(JpmProductSaleImageController.class);
	private JpmProductSaleImageManager jpmProductSaleImageManager = null;

	public void setJpmProductSaleImageManager(JpmProductSaleImageManager jpmProductSaleImageManager) {
		this.jpmProductSaleImageManager = jpmProductSaleImageManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		} 
		JpmProductSaleImage jpmProductSaleImage = new JpmProductSaleImage();
		// populate object with request parameters
		BeanUtils.populate(jpmProductSaleImage, request.getParameterMap());

		//List jpmProductSaleImages = jpmProductSaleImageManager.getJpmProductSaleImages(jpmProductSaleImage);
		/**** auto pagination ***/
		CommonRecord crm=RequestUtil.toCommonRecord(request);
		Pager pager = new Pager("jpmProductSaleImageListTable",request, 20);
		crm=initCommonRecord(request);//存放request中的值例如：strAction 
		
		//当前用户
		SysUser loginSysUser = SessionLogin.getLoginUser(request);
		String FILE_URL = ListUtil.getListValue(loginSysUser.getCompanyCode().toUpperCase(), "jpmproductsaleimage.url", "1")+"productNew/";
		
		//如果不是全球，则设置区域
		String companyCode = request.getParameter("companyCode");
		SysUser loginUser = SessionLogin.getLoginUser(request);
		if(StringUtils.isNotEmpty(companyCode)){
			crm.setValue("companyCode", companyCode);
		}else{
			if (!"AA".equalsIgnoreCase(loginUser.getCompanyCode())) {
				crm.setValue("companyCode",loginUser.getCompanyCode());
	
			} 
		} 
		
		String uniNoStr = request.getParameter("uniNoStr");
		String status2 = request.getParameter("status2"); 
		if(StringUtils.isNotEmpty(uniNoStr) && StringUtils.isNotEmpty(uniNoStr)){
			int i = jpmProductSaleImageManager.batchAuditProductNews(uniNoStr, status2); 
		}
		
		String productNo = request.getParameter("productNo");
    	String productName = request.getParameter("productName");
    	String imagetype = request.getParameter("imagetype");
    	String status = request.getParameter("status");
    	String cxFlag = request.getParameter("cxFlag");
    	
    	crm.setValue("productNo", productNo);
    	crm.setValue("productName", productName);
    	crm.setValue("imagetype", imagetype);
    	crm.setValue("status", status);
    	crm.setValue("cxFlag", cxFlag);
		
    	
		List jpmProductSaleImages = jpmProductSaleImageManager.getJpmProductSaleImagesByCrm(crm,pager);
		request.setAttribute("jpmProductSaleImageListTable_totalRows", pager.getTotalObjects());
		
		/***图片大小比例***/
		/*LinkedHashMap<String,String> map = ListUtil.getListOptions(loginSysUser.getCompanyCode().toUpperCase(), "jpmproductsaleimage.proportion");
		String[] wh = new String[6];
		if(StringUtils.isNotEmpty(map.get("1"))){
			wh[0] = map.get("1").split(":")[0];
			wh[1] = map.get("1").split(":")[1];
		}
		if(StringUtils.isNotEmpty(map.get("2"))){
			wh[2] = map.get("2").split(":")[0];
			wh[3] = map.get("2").split(":")[1];
		}
		if(StringUtils.isNotEmpty(map.get("3"))){
			wh[4] = map.get("3").split(":")[0];
			wh[5] = map.get("3").split(":")[1];
		}
		request.setAttribute("wh", wh);*/
		request.setAttribute("companyCode", companyCode);
		request.setAttribute("FILE_URL", FILE_URL);
		
		return new ModelAndView("pm/jpmProductSaleImageList", Constants.JPMPRODUCTSALEIMAGE_LIST, jpmProductSaleImages);
	}
}
