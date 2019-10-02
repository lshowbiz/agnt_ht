package com.joymain.jecs.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.pm.service.JpmProductSaleManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class BaseController  {
	
	 private AlStateProvinceManager alStateProvinceManager = null;
	    private JpmProductSaleManager jpmProductSaleManager = null;
	    private AlCityManager alCityManager = null;
	    
	public void setAlCityManager(AlCityManager alCityManager) {
			this.alCityManager = alCityManager;
		}


	public void setAlStateProvinceManager(
				AlStateProvinceManager alStateProvinceManager) {
			this.alStateProvinceManager = alStateProvinceManager;
		}


		public void setJpmProductSaleManager(JpmProductSaleManager jpmProductSaleManager) {
			this.jpmProductSaleManager = jpmProductSaleManager;
		}

	protected final String MESSAGES_KEY = "successMessages";
	protected final Log log = LogFactory.getLog(getClass());
	public void saveMessage(HttpServletRequest request, String msg) {
		List messages = (List) request.getSession().getAttribute(MESSAGES_KEY);

		if (messages == null) {
			messages = new ArrayList();
		}

		messages.add(msg);
		request.getSession().setAttribute(MESSAGES_KEY, messages);
	}
	
	
	public void initPmProductMap(HttpServletRequest request){
    	SysUser sessionLogin = SessionLogin.getLoginUser(request);
		String companyCode = sessionLogin.getCompanyCode();
		//WuCF JpmProductSaleNew Modify By WuCF 20130917
		/*Map compamyProductMap = jpmProductSaleManager.getCompanyProductMap(companyCode);*/
		if(request.getSession().getAttribute("compamyProductMap")==null){
			 
			//WuCF JpmProductSaleNew Modify By WuCF 20130917
			/*Map compamyProductMap = jpmProductSaleManager.getCompanyProductMap(companyCode);*/
			Map compamyProductMap = jpmProductSaleManager.getCompanyProductMapNew(companyCode);
			Map productMap = new HashMap();
	    	
	    	Set set = compamyProductMap.entrySet();
	    	Iterator iterator =set.iterator();
	    	while (iterator.hasNext()){
	    		Entry entry = (Entry) iterator.next();
	    		//WuCF JpmProductSaleNew Modify By WuCF 20130917
	    		productMap.put(entry.getKey(), ((JpmProductSaleNew)entry.getValue()).getProductName());
	    		/*productMap.put(entry.getKey(), ((JpmProductSale)entry.getValue()).getProductName());*/
	    	}
	    	request.getSession().setAttribute("compamyProductMap", productMap);
		}
    }
    public void initStateCodeParem(HttpServletRequest request){
    	SysUser sessionLogin = SessionLogin.getLoginUser(request);
    	Map alCityMap = new HashMap();
    	alCityMap=alCityManager.getAlCityMap(sessionLogin.getCompanyCode());
    	Map<String,String> alStateProvinceMap = new HashMap();
    	List alStateProvinces = null;
    	alStateProvinceMap = alStateProvinceManager.getAlStateProvincesMapByCountry(sessionLogin.getCompanyCode());
    	
    	if (sessionLogin.getIsManager()) {

			alStateProvinces = alStateProvinceManager.getAlStateProvinces(null);
		} else {
			alStateProvinces = alStateProvinceManager
					.getAlStateProvincesByCountry(sessionLogin.getCompanyCode());
		}
		request.setAttribute("alStateProvinces", alStateProvinces);
		request.setAttribute("alStateProvinceMap", alStateProvinceMap);
		request.setAttribute("alCityMap", alCityMap);
    }
    
	/**
	 * 将查询条件放到session里
	 * @param request
	 * @return
	 */
	public CommonRecord initCommonRecord(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		String strAction = request.getParameter("strAction");
		CommonRecord crm = (CommonRecord) session.getAttribute(strAction+"~requestCrm");
		crm = RequestUtil.toCommonRecord(crm, request);
		session.setAttribute(strAction+"~requestCrm", crm);
		
		Map requestParaMap = (Map) session.getAttribute(strAction+"~requestParaMap");
		requestParaMap=RequestUtil.populateMap(requestParaMap,request);
		session.setAttribute(strAction+"~requestParaMap", requestParaMap);
		request.setAttribute("requestParaMap", requestParaMap);
		return crm;
	}
	
	public void initRequest(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		String strAction = request.getParameter("strAction");
		Map requestParaMap = (Map) session.getAttribute(strAction+"~requestParaMap");
		log.info("requestParaMap"+requestParaMap);
		requestParaMap=RequestUtil.populateMap(requestParaMap,request);
		log.info("requestParaMap"+requestParaMap);
		session.setAttribute(strAction+"~requestParaMap", requestParaMap);
		request.setAttribute("requestParaMap", requestParaMap);
	}
	
}
