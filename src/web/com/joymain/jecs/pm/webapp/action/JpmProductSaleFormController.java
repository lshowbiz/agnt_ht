package com.joymain.jecs.pm.webapp.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.pd.model.PdWarehouse;
import com.joymain.jecs.pd.service.PdWarehouseManager;
import com.joymain.jecs.pm.model.JmiMemberTeam;
import com.joymain.jecs.pm.model.JpmProduct;
import com.joymain.jecs.pm.model.JpmProductSaleLog;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.pm.service.JpmProductManager;
import com.joymain.jecs.pm.service.JpmProductSaleLogManager;
import com.joymain.jecs.pm.service.JpmProductSaleManager;
import com.joymain.jecs.service.PdLogisticsService;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JpmProductSaleFormController extends BaseFormController {
	private JpmProductSaleManager jpmProductSaleManager = null;
	private JpmProductSaleLogManager jpmProductSaleLogManager = null;
	private JpmProductManager jpmProductManager = null;
	private PdLogisticsService starsExpressService = null;
	private PdWarehouseManager pdWarehouseManager = null;
	
	private JdbcTemplate jdbcTemplate = null;
	private JmiMemberTeam jmiMemberTeams = null;
	
	public JmiMemberTeam getJmiMemberTeams() {
		return jmiMemberTeams;
	}

	public void setJmiMemberTeams(JmiMemberTeam jmiMemberTeams) {
		this.jmiMemberTeams = jmiMemberTeams;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setPdWarehouseManager(PdWarehouseManager pdWarehouseManager) {
		this.pdWarehouseManager = pdWarehouseManager;
	}

	public void setStarsExpressService(PdLogisticsService starsExpressService) {
		this.starsExpressService = starsExpressService;
	}

	public void setJpmProductManager(JpmProductManager jpmProductManager) {
		this.jpmProductManager = jpmProductManager;
	}

	public void setJpmProductSaleLogManager(
			JpmProductSaleLogManager jpmProductSaleLogManager) {
		this.jpmProductSaleLogManager = jpmProductSaleLogManager;
	}

	public void setJpmProductSaleManager(
			JpmProductSaleManager jpmProductSaleManager) {
		this.jpmProductSaleManager = jpmProductSaleManager;
	}

	public JpmProductSaleFormController() {
		setCommandName("jpmProductSaleNew");
		setCommandClass(JpmProductSaleNew.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		// String uniNo = request.getParameter("uniNo");
		// JpmProductSale jpmProductSale = null;
		//
		// if (!StringUtils.isEmpty(uniNo)) {
		// jpmProductSale = jpmProductSaleManager.getJpmProductSale(uniNo);
		// } else {
		// jpmProductSale = new JpmProductSale();
		// }
		//
		// return jpmProductSale;
		
		
		String uniNo = request.getParameter("uniNo");
		JpmProductSaleNew pmProductSale = null;
		String strAction = request.getParameter("strAction");
		SysUser loginUser = SessionLogin.getLoginUser(request);
		if (!StringUtils.isEmpty(uniNo)) {
			pmProductSale = jpmProductSaleManager.getJpmProductSaleNew(uniNo);
			// setPrePmProductChangeLog(pmProductSale,request);
		} else {
			pmProductSale = new JpmProductSaleNew();
			if (!"AA".equalsIgnoreCase(loginUser.getCompanyCode())) {
				pmProductSale.setCompanyCode(loginUser.getCompanyCode());

			}
		}

		boolean disabled = false;

		if ("confirmJpmProductSale".equals(strAction)) {
			disabled = true;
		}else if("editStorageCordon".equals(strAction)){
			disabled = true;
		}
		
		//Modify By WuCF 20130523 新增查询条件
		List<HashMap<String,String>> list = new LinkedList<HashMap<String,String>>();
//		HashMap<String,String> hm = new HashMap<String,String>();
//		hm.put("1", "one");
//		list.add(hm);
//		request.setAttribute("list", list);
		if(StringUtils.isEmpty(uniNo) || "null".equals(uniNo)){ 
			uniNo="0";
		}
		StringBuffer sqlBuf = new StringBuffer("select t1.code||'#'||t3.code code,t1.name from JMI_MEMBER_TEAM t1 left join ");
		sqlBuf.append(" (select * from PM_TEAM_PRODUCT t2 where t2.uni_no='");
		sqlBuf.append(uniNo); 
		sqlBuf.append("') t3 on t1.code=t3.code where t1.STATUS='0' order by t1.code　 "); 		
		DataSource ds=jdbcTemplate.getDataSource();
        Connection conn=ds.getConnection();
        Statement st=conn.createStatement();		
		ResultSet rs = st.executeQuery(sqlBuf.toString());
		
		list = jpmProductSaleManager.getJmiMemberTeams(rs);
		
		request.setAttribute("list", list);  
		
		request.setAttribute("strAction", strAction);
		request.setAttribute("disabled", disabled);
		return pmProductSale;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
		JpmProduct pmProduct = new JpmProduct();
		JpmProductSaleNew jpmProductSale = (JpmProductSaleNew) command;
		// boolean isNew = (jpmProductSale.getUniNo() == null);
		// Locale locale = request.getLocale();
		String view = "redirect:jpmProductSales.html?strAction=editJpmProductSale";
		String key = null;
		String strAction = request.getParameter("strAction");
		if ("addJpmProductSale".equals(strAction)) {
			key = "pmProductSale.add";
			jpmProductSale.setConfirm("0");
			jpmProductSale.setStatus("0");

			pmProduct = jpmProductManager.getJpmProduct(jpmProductSale.getJpmProduct().getProductNo());
//        	if(jpmProductSaleManager.existProductNo(jpmProductSale.getCompanyCode(), pmProduct.getProductNo())){
//        		errors.reject("piProduct.productNoExits", new Object[] {},LocaleUtil.getLocalText("piProduct.productNoExits"));
//				return showForm(request, response, errors);
//        	}
        	jpmProductSale.setJpmProduct(pmProduct);
        	
		} else if ("confirmJpmProductSale".equals(strAction)) {
			key = "pmProductSale.confirm";
			jpmProductSale.setConfirm("1");
			view = "redirect:jpmProductSales.html?strAction=confirmJpmProductSale";
		} else if ("editJpmProductSale".equals(strAction)) {
			key = "pmProductSale.update";
			
		}
		/*if(jpmProductSale.getJmiMemberTeams().getClass().toString().contains("LinkedHashSet")){
			int size = jpmProductSale.getJmiMemberTeams().size();
			String[] ss = new String[size];
			int i = 0;
			for (Iterator<String> iter = jpmProductSale.getJmiMemberTeams().iterator(); iter.hasNext();) {
			     ss[i] = iter.next();
			     i++;
			} 
			if(ss!=null && ss.length>0){
				Set jmt = new HashSet();
				for(String s : ss){
					s = s.split("#")[0]; //传递过来的参数可能是有未选择的直接传递原始字符串，所有要截取
					jmt.add(jpmProductSaleManager.getJmiMemberTeam(s));
				} 
				//jpmProductSale.setJmiMemberTeams(null);
				jpmProductSale.setJmiMemberTeams(jmt); 
			}
			
		}else{//如果没有选择，需要手动设置为空
			jpmProductSale.setJmiMemberTeams(null);
		} 
		
		
		jpmProductSaleManager.saveJpmProductSale(jpmProductSale);
		//暂时没项动作 都想星辰同步商品信息
		sendStarsProductSale(jpmProductSale,request);*/
		saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), key));  
		return new ModelAndView(view);
	}

	private void sendStarsProductSale(JpmProductSaleNew jpmProductSale,
			HttpServletRequest request) throws Exception{
		 CommonRecord crm = new CommonRecord();
		 crm.setValue("shNo", "STARS");
		 
		List pdWarehouses = pdWarehouseManager.getPdWarehousesByCrm(crm, null);
		if(!pdWarehouses.isEmpty()){
			for(int i=0;i<pdWarehouses.size();i++){
				PdWarehouse pdWarehouse =(PdWarehouse) pdWarehouses.get(i);
				try {
//					starsExpressService.sendProductInfo(jpmProductSale,
//							pdWarehouse.getWarehouseNo());
				} catch (Exception e) {
					// TODO: handle exception
					
				}
			}
		}
		
		
	}
	private void saveLog(JpmProductSaleNew jpmProductSale,
			HttpServletRequest request) {
		try {
			JpmProductSaleLog jpmProductSaleLog = jpmProductSaleLogManager
					.getJpmProductSaleToLog(jpmProductSale);
			jpmProductSaleLog.setRemark(request.getParameter("logRemark"));
			jpmProductSaleLog.setEditTime(new Date());
			jpmProductSaleLog.setEditUserCode(SessionLogin
					.getLoginUser(request).getUserCode());
			jpmProductSaleLogManager.saveJpmProductSaleLog(jpmProductSaleLog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		// binder.setAllowedFields(allowedFields);
		// binder.setDisallowedFields(disallowedFields);
		// binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
