package com.joymain.jecs.pm.webapp.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.pm.service.JmiMemberTeamManager;
import com.joymain.jecs.pm.service.JpmProductSaleNewManager;
import com.joymain.jecs.pm.service.JpmProductSaleTeamTypeManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JpmProductSaleTeamTypeFormController extends BaseFormController {
	private JpmProductSaleTeamTypeManager jpmProductSaleTeamTypeManager = null;
	private AlCountryManager alCountryManager;
	private JmiMemberTeamManager jmiMemberTeamManager = null;
	private JpmProductSaleNewManager jpmProductSaleNewManager = null;
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public void setJpmProductSaleNewManager(JpmProductSaleNewManager jpmProductSaleNewManager) {
		this.jpmProductSaleNewManager = jpmProductSaleNewManager;
	}	
	
	public JmiMemberTeamManager getJmiMemberTeamManager() {
		return jmiMemberTeamManager;
	}
	public void setJmiMemberTeamManager(JmiMemberTeamManager jmiMemberTeamManager) {
		this.jmiMemberTeamManager = jmiMemberTeamManager;
	}
	public AlCountryManager getAlCountryManager() {
		return alCountryManager;
	}
	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}
	public JpmProductSaleTeamTypeManager getJpmProductSaleTeamTypeManager() {
		return jpmProductSaleTeamTypeManager;
	}
	public void setJpmProductSaleTeamTypeManager(JpmProductSaleTeamTypeManager jpmProductSaleTeamTypeManager) {
		this.jpmProductSaleTeamTypeManager = jpmProductSaleTeamTypeManager;
	}
	public JpmProductSaleTeamTypeFormController() {
		setCommandName("jpmProductSaleTeamType");
		setCommandClass(JpmProductSaleTeamType.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
	throws Exception {
		String pttId = request.getParameter("pttId");
		JpmProductSaleTeamType jpmProductSaleTeamType = new JpmProductSaleTeamType();
		SysUser loginUser = SessionLogin.getLoginUser(request);
		String productNo = request.getParameter("jpmProductSaleNew.jpmProduct.productNo"); 
		String uniNo = request.getParameter("uniNo"); 
		String type = request.getParameter("type");
		
		//商品第一次新增
		if("p".equals(type)){
			String companyCode = loginUser.getCompanyCode();//分公司
			String productNoP = request.getParameter("productNoP");//传递过来的商品编号
			List list = jpmProductSaleTeamTypeManager.getJpmProductSaleTeamType(companyCode,productNoP);
			
			if(list!=null && list.size()>=1 && !StringUtil.isEmpty(productNoP) && !"null".equals(productNoP)){
				request.setAttribute("returnStr", "1");
				return jpmProductSaleTeamType;
			}
			
			//查询商品是否存在
			JpmProductSaleNew jpmProductSaleNew = jpmProductSaleTeamTypeManager.getJpmProductSaleNew(companyCode,productNoP);
			
			if(jpmProductSaleNew.getUniNo()==null || "".equals(jpmProductSaleNew.getUniNo()) ||
				(!"1".equals(jpmProductSaleNew.getStatus()) && !"2".equals(jpmProductSaleNew.getStatus()))){//如果商品不存在或不是销售状态/前台停售，则提示并返回。 
				request.setAttribute("returnStr", "2");
				return jpmProductSaleTeamType; 
			} 
			
			request.setAttribute("uniNoP",jpmProductSaleNew.getUniNo());
			request.setAttribute("companyCodeP", companyCode);
			request.setAttribute("jpmProductSaleNew", jpmProductSaleNew);
		}else if("p2".equals(type)){
			jpmProductSaleTeamType = new JpmProductSaleTeamType();
			if (!"AA".equalsIgnoreCase(loginUser.getCompanyCode())) {
				jpmProductSaleTeamType.setCompanyCode(loginUser.getCompanyCode());
			}
		}else if("p3".equals(type)){
			jpmProductSaleTeamType = new JpmProductSaleTeamType();
			if (!"AA".equalsIgnoreCase(loginUser.getCompanyCode())) {
				jpmProductSaleTeamType.setCompanyCode(loginUser.getCompanyCode());
			}
			//获得条件
			String orderType = request.getParameter("orderType");//订单编号
			String teamCode = request.getParameter("teamCode");//团队编码
			String state = request.getParameter("state");//设置状态
			
			String productNoC = request.getParameter("productNo");// 
			String productName = request.getParameter("productName");// 
			String status = request.getParameter("status");//
			String productCategory = request.getParameter("productCategory");//
			String smNo = request.getParameter("smNo");//
			if(!StringUtil.isEmpty(orderType) && !StringUtil.isEmpty(teamCode) && !StringUtil.isEmpty(state)){
				//查询条件
				CommonRecord crm=RequestUtil.toCommonRecord(request);
				crm.setValue("companyCode",loginUser.getCompanyCode());
				crm.setValue("teamCode",teamCode);
				crm.setValue("teamCode",teamCode);
				crm.setValue("productNo",productNoC);
				crm.setValue("productName",productName);
				crm.setValue("status",status);
				crm.setValue("productCategory",productCategory);
				crm.setValue("smNo",smNo);
				
				List jpmProductSaleNewlist = jpmProductSaleNewManager.getNotExistsJpmProductSaleNews(crm);
//				System.out.println(jpmProductSaleNewlist.size());
				request.setAttribute("orderType", orderType);
				request.setAttribute("teamCode", teamCode);
				request.setAttribute("state", state);
				request.setAttribute("jpmProductSaleNewlist", jpmProductSaleNewlist);
			}
		}else{
			if (!StringUtils.isEmpty(pttId)) {//编辑修改
				jpmProductSaleTeamType = jpmProductSaleTeamTypeManager.getJpmProductSaleTeamType(pttId);
			} else {//非第一次新增
				jpmProductSaleTeamType = new JpmProductSaleTeamType();
				if (!"AA".equalsIgnoreCase(loginUser.getCompanyCode())) {
					jpmProductSaleTeamType.setCompanyCode(loginUser.getCompanyCode());
	
				}
			}
		} 
		request.setAttribute("type", type);
		return jpmProductSaleTeamType;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command,
			BindException errors)
	throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		JpmProductSaleTeamType jpmProductSaleTeamType = (JpmProductSaleTeamType) command;
		boolean isNew = (jpmProductSaleTeamType.getPttId() == null);
		Locale locale = request.getLocale();
		String key=null;
		String strAction = request.getParameter("strAction");
		if ("deleteJpmProductSaleTeamType".equals(strAction)  ) {
			jpmProductSaleTeamTypeManager.removeJpmProductSaleTeamType(jpmProductSaleTeamType.getPttId().toString());
			key="jpmProductSaleTeamType.delete";
		}else if ("addJpmProductSaleTeamType".equals(strAction)  ) {
			CommonRecord crm=RequestUtil.toCommonRecord(request);
			crm.setValue("companyCode", jpmProductSaleTeamType.getCompanyCode());
			crm.setValue("teamCode",jpmProductSaleTeamType.getTeamCode());
			crm.setValue("orderType",jpmProductSaleTeamType.getOrderType());
			crm.setValue("uniNo", jpmProductSaleTeamType.getUniNo());
			String type = request.getParameter("type");//新增类型
			String strKey = "jpmproductsaleteamtype.notcheckordertype";//未选择团队订单类型
			if("p3".equals(type)){
				strKey = "piProductNew.uncheck.unino";//未选择商品
			}
			
			if("p3".equals(type)){//第一次新增
				if(StringUtils.isEmpty(jpmProductSaleTeamType.getOrderTypeW())){ 
	        		errors.reject(strKey, new Object[] {},LocaleUtil.getLocalText(strKey));
					return showForm(request, response, errors);
	        	}
			}
			/*else{
				if(StringUtils.isEmpty(jpmProductSaleTeamType.getTeamCode())){ 
					errors.reject("jpmproductsaleteamtype.notcheckTeamcode", new Object[] {},LocaleUtil.getLocalText("jpmproductsaleteamtype.notcheckTeamcode"));
					return showForm(request, response, errors);
	        	}
				if(StringUtils.isEmpty(jpmProductSaleTeamType.getOrderTypeW())){ 
					errors.reject("jpmproductsaleteamtype.notcheckordertype", new Object[] {},LocaleUtil.getLocalText("jpmproductsaleteamtype.notcheckordertype"));
					return showForm(request, response, errors);
	        	}
			}*/
			//后台获得3个数组的值，但是总是出现“,,,,5”的情况，所以必须去掉空值
			String[] orderTypeWs = jpmProductSaleTeamType.getOrderTypeW().split(",");
			String[] priceWs = jpmProductSaleTeamType.getPriceW().split(",");
			String[] pvWs = jpmProductSaleTeamType.getPvW().split(",");
			String[] teamCodePs = null;
			if(jpmProductSaleTeamType.getTeamCodeP()!=null){
				teamCodePs = jpmProductSaleTeamType.getTeamCodeP().split(",");//首次新增对应选择的团体集合
			}
			
			
			if(!"p3".equals(type)){//如果不是批量新增多商品方式的新增，则不需要判断团队
				//Modify By WuCF 20140218 团队为空的情况
				if((teamCodePs==null || (teamCodePs!=null && teamCodePs.length==0)) && StringUtil.isEmpty(jpmProductSaleTeamType.getTeamCode())){ 
	        		errors.reject("jpmproductsaleteamtype.notcheckTeamcode", new Object[] {},LocaleUtil.getLocalText("jpmproductsaleteamtype.notcheckTeamcode"));
					return showForm(request, response, errors);
	        	}
			}
			
			List<String> orderTypeList = new ArrayList<String>();
			List<String> priceList = new ArrayList<String>();
			List<String> pvList = new ArrayList<String>();
			List<String> teamCodeList = new ArrayList<String>();//首次新增对应选择的团体集合
			//将前台传递的值放入到List，排除可能为空的值
			for(String s : orderTypeWs){
				if(StringUtils.isNotEmpty(s)){
					orderTypeList.add(s);
				}
			}
			for(String s : priceWs){
				if(StringUtils.isNotEmpty(s)){
					priceList.add(s);
				}
			}
			for(String s : pvWs){
				if(StringUtils.isNotEmpty(s)){
					pvList.add(s);
				}
			}
			if(teamCodePs!=null){
				for(String s : teamCodePs){
					if(StringUtils.isNotEmpty(s)){
						teamCodeList.add(s);
					}
				}
			}
			
			//默认为0：启用
			if(StringUtils.isEmpty(jpmProductSaleTeamType.getState())){
				jpmProductSaleTeamType.setState("0");
			}
			
			JpmProductSaleTeamType newJpmProductSaleTeamType = null;
			
			if("p".equals(type)){//首次新增
				//得到sql语句数量
				int num = 0;
				if(teamCodeList!=null && priceList!=null){
					num = teamCodeList.size()*priceList.size();
				}
				System.out.println("num:"+num);
				
				String uniNoP = request.getParameter("uniNoP");
				String companyCodeP = request.getParameter("companyCodeP");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println(sdf.format(new Date()));
				StringBuffer sqlBuf = new StringBuffer("");
				ArrayList<String> sqlList = new ArrayList<String>();
				for(int j=0;j<teamCodeList.size();j++){
					for(int i=0;i<orderTypeList.size();i++){
						sqlBuf = new StringBuffer("insert into jpm_product_sale_team_type values(SEQ_PM.nextval,'");
						sqlBuf.append(Long.parseLong(uniNoP));
						sqlBuf.append("','");
						sqlBuf.append(teamCodeList.get(j));
						sqlBuf.append("','");
						sqlBuf.append(orderTypeList.get(i));
						sqlBuf.append("','");
						sqlBuf.append(companyCodeP);
						sqlBuf.append("','");
						sqlBuf.append(new BigDecimal(priceList.get(i)));
						sqlBuf.append("','");
						sqlBuf.append(new BigDecimal(pvList.get(i)));
						sqlBuf.append("','");
						sqlBuf.append(jpmProductSaleTeamType.getState());
						sqlBuf.append("','','0')");
						sqlList.add(sqlBuf.toString());
						
//						newJpmProductSaleTeamType = new JpmProductSaleTeamType();
//						newJpmProductSaleTeamType.setUniNo(Long.parseLong(uniNoP));
//						newJpmProductSaleTeamType.setCompanyCode(companyCodeP);
//						newJpmProductSaleTeamType.setTeamCode(teamCodeList.get(j));
//						newJpmProductSaleTeamType.setOrderType(orderTypeList.get(i));
//						newJpmProductSaleTeamType.setPrice(new BigDecimal(priceList.get(i)));
//						newJpmProductSaleTeamType.setPv(new BigDecimal(pvList.get(i)));
//						newJpmProductSaleTeamType.setState(jpmProductSaleTeamType.getState());
//						newJpmProductSaleTeamType.setIsHidden("0");//默认不隐藏：0
//						
//						//循环新增
//						jpmProductSaleTeamTypeManager.saveJpmProductSaleTeamType(newJpmProductSaleTeamType);
					} 
				}
				String[] sqlStr = new String[sqlList.size()];
				for(int i=0;i<sqlList.size();i++){
					sqlStr[i] = sqlList.get(i);
				}
				jdbcTemplate.batchUpdate(sqlStr);
				System.out.println(sdf.format(new Date()));
			}else if("p3".equals(type)){
				String teamCode = request.getParameter("teamCode");
				String orderType = request.getParameter("orderType");
				String state = request.getParameter("state");
				for(int i=0;i<orderTypeList.size();i++){
					newJpmProductSaleTeamType = new JpmProductSaleTeamType();
					newJpmProductSaleTeamType.setUniNo(Long.parseLong(orderTypeList.get(i)));
					newJpmProductSaleTeamType.setCompanyCode(jpmProductSaleTeamType.getCompanyCode());
					newJpmProductSaleTeamType.setTeamCode(teamCode);
					newJpmProductSaleTeamType.setOrderType(orderType);
					newJpmProductSaleTeamType.setPrice(new BigDecimal(priceList.get(i)));
					newJpmProductSaleTeamType.setPv(new BigDecimal(pvList.get(i)));
					newJpmProductSaleTeamType.setState(state);
					newJpmProductSaleTeamType.setIsHidden("0");//默认不隐藏：0
					
					//循环新增
					jpmProductSaleTeamTypeManager.saveJpmProductSaleTeamType(newJpmProductSaleTeamType);
				}
			}else{//非首次新增
				//如果没有勾选，则直接不用进入添加功能
				if(jpmProductSaleTeamType.getOrderTypeW()!=null){
					if(orderTypeList!=null && orderTypeList.size()!=0){
						for(int i=0;i<orderTypeList.size();i++){
							newJpmProductSaleTeamType = new JpmProductSaleTeamType();
							newJpmProductSaleTeamType.setUniNo(jpmProductSaleTeamType.getUniNo());
							newJpmProductSaleTeamType.setCompanyCode(jpmProductSaleTeamType.getCompanyCode());
							newJpmProductSaleTeamType.setTeamCode(jpmProductSaleTeamType.getTeamCode());
							newJpmProductSaleTeamType.setOrderType(orderTypeList.get(i));
							newJpmProductSaleTeamType.setPrice(new BigDecimal(priceList.get(i)));
							newJpmProductSaleTeamType.setPv(new BigDecimal(pvList.get(i)));
							newJpmProductSaleTeamType.setState(jpmProductSaleTeamType.getState());
							newJpmProductSaleTeamType.setIsHidden("0");//默认不隐藏：0
							
							//循环新增
							jpmProductSaleTeamTypeManager.saveJpmProductSaleTeamType(newJpmProductSaleTeamType);
						}
					}
				}
			}
			
			key="jpmProductSaleTeamType.add";
		}else if ("editJpmProductSaleTeamType".equals(strAction)  ){
			CommonRecord crm=RequestUtil.toCommonRecord(request);
			crm.setValue("companyCode", jpmProductSaleTeamType.getCompanyCode());
			crm.setValue("teamCode",jpmProductSaleTeamType.getTeamCode());
			crm.setValue("orderType",jpmProductSaleTeamType.getOrderType());
			crm.setValue("uniNo", jpmProductSaleTeamType.getUniNo());
			crm.setValue("pttId", jpmProductSaleTeamType.getPttId());
			//判断是否已经存在
			boolean isExist = jpmProductSaleTeamTypeManager.isExist(crm, "1");
			
			if(isExist){
				errors.rejectValue("teamCode","error.jpmproductsaleteamtype.existed");
				errors.rejectValue("orderType","error.jpmproductsaleteamtype.existed");
				return showForm(request, response, errors); 
			} 
			//默认为0：启用
			if(StringUtils.isEmpty(jpmProductSaleTeamType.getState())){
				jpmProductSaleTeamType.setState("0");
			}
			//默认为0：不隐藏 
			if(StringUtils.isEmpty(jpmProductSaleTeamType.getIsHidden())){
				jpmProductSaleTeamType.setIsHidden("0");
			}
			jpmProductSaleTeamTypeManager.saveJpmProductSaleTeamType(jpmProductSaleTeamType);
			key="jpmProductSaleTeamType.update";
		}
		
		
		
		saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), key));  
		return new ModelAndView(getSuccessView());
	}
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		//获取地区
//    	String companyCode="CN";
//        AlCountry alCountry = new AlCountry();
//    	alCountry = alCountryManager.getAlCountryByCode(companyCode);
//    	alCountry.getAlStateProvinces().iterator();
//    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces()); 
		
		String view = "";
		String key = "";
		String uniNoStr = request.getParameter("uniNoStr");//获取多选字符串的uniNoStr
		String status2 = request.getParameter("status2");//修改的状态
		String productNo = request.getParameter("productNo");//获取多选字符串的uniNoStr
		request.setAttribute("uniNoStr", uniNoStr);
		request.setAttribute("status2", status2);
		request.setAttribute("productNo", productNo); 
		if(StringUtils.isNotEmpty(uniNoStr) && !"null".equals(uniNoStr)){//批量审核处理
			view = "redirect:jpmProductSaleTeamTypes.html?strAction=confirmJpmProductSaleTeamType";
			key="opration.notice.js.orderNo.auditSuccess";
			saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), key));  
			return ; 
		}else{ 
			CommonRecord crm=RequestUtil.toCommonRecord(request);
			try {
				crm.setValue("status", "0");
			} catch (Exception e) { 
				e.printStackTrace();
			}
			SysUser loginUser = SessionLogin.getLoginUser(request);
			Pager pager = new Pager("jmiMemberTeamListTable",request, 20); 
			try {
				crm.setValue("status", 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        List jmiMemberTeams = jmiMemberTeamManager.getJmiMemberTeamsByCrm(crm); 
	        request.setAttribute("jmiMemberTeams", jmiMemberTeams);  
	        
	        List orderTypeList = jpmProductSaleTeamTypeManager.getJsysListValues("po.all.order_type",loginUser.getCompanyCode());
	        request.setAttribute("orderTypeList", orderTypeList); 
	        
			super.initBinder(request, binder);
		}
		super.initBinder(request, binder);
	}
}
