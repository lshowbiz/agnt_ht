package com.joymain.jecs.po.webapp.action;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.pd.service.PdWarehouseManager;

import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.pm.service.JpmProductSaleManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderFee;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 批量建重消订单
 * @author luoting
 *
 */
public class JpoMemberBathROrderFormController extends BaseFormController {
    private JpoMemberOrderManager jpoMemberOrderManager = null;
	private SysUserManager sysUserManager = null;
    private AlCityManager alCityManager;
    private JpmProductSaleManager jpmProductSaleManager = null;
    private SysIdManager sysIdManager = null;
    private PdWarehouseManager pdWarehouseManager = null;

    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
        this.jpoMemberOrderManager = jpoMemberOrderManager;
    }
    public JpoMemberBathROrderFormController() {
        setCommandName("jpoMemberOrder");
        setCommandClass(JpoMemberOrder.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        RequestUtil.freshSession(request,"jpoMemberOrderBatch",60l);

        return new JpoMemberOrder();
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

    	SysUser loginSysUser = SessionLogin.getLoginUser(request);
    	SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
    	
        if ("importPoMemberOrder".equals(request.getParameter("strAction"))) {
        	
        	if(RequestUtil.saveOperationSession(request,"jpoMemberOrderBatch",60l)!=0){
    			return new ModelAndView("redirect:jpoMemberOrderBatch.html");
    		}
        	
        	
        	Long limitNum = new Long(ConfigUtil.getConfigValue(SessionLogin.getLoginUser(request).getCompanyCode(), "bankbook_temp_import_limit"));
        	try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("xlsFile");
				//retrieve the file data
				InputStream stream = file.getInputStream();
				
				Date date = new Date();

				ExcelUtil eu = new ExcelUtil();
				//获取可读的工作表对象，定位到要读取的excel文件
				Workbook workbook = eu.getWorkbook(stream);
				//读取此文件的第一个工作表，工作表序号从0开始。
				Sheet sheet0 = workbook.getSheet(0);
				List<JpoMemberOrder> jpoMemberOrders = new ArrayList<JpoMemberOrder>();
				List<Set> jpoMemberOrderSets = new ArrayList<Set>();
				List<Set> jpoMemberOrderFeeSets = new ArrayList<Set>();
				if(sheet0.getRows()>limitNum){
					throw new AppException(getText("fiBankbookTemp.import.limit.error"));
				}
				for (int i = 1; i < sheet0.getRows(); i++) {
					//会员编号,会员名称,收货人FirstName,收货人LastName,联系电话,收货市,收货地址,邮编,仓库号,备注,物理费,团队类型
					String userCode = eu.getContents(sheet0, 0, i);
					if (!StringUtils.isEmpty(userCode)) {
						userCode = StringUtils.trim(userCode);
					}else{
						throw new Exception(i + getText("errors.required") + " userCode");
					}

					String userName = eu.getContents(sheet0, 1, i);
					if (!StringUtils.isEmpty(userName)) {
						userName = StringUtils.trim(userName);
					}else{
						throw new Exception(i + getText("errors.required") + " userName");
					}

					String firstName = eu.getContents(sheet0, 2, i);
					if (!StringUtils.isEmpty(firstName)) {
						firstName = StringUtils.trim(firstName);
					}else{
						throw new Exception(i + getText("errors.required") + " firstName");
					}

					String lastName = eu.getContents(sheet0, 3, i);
					if (!StringUtils.isEmpty(lastName)) {
						lastName = StringUtils.trim(lastName);
					}else{
						throw new Exception(i + getText("errors.required") + " lastName");
					}

					String phone = eu.getContents(sheet0, 4, i);
					if (!StringUtils.isEmpty(phone)) {
						phone = StringUtils.trim(phone);
					}else{
						throw new Exception(i + getText("errors.required") + " phone");
					}

					String province = "";
					String city = eu.getContents(sheet0, 5, i);
					if (!StringUtils.isEmpty(city)) {
						city = StringUtils.trim(city);
						AlCity alCity = alCityManager.getAlCity(city);
						if(alCity!=null){
							province = alCity.getAlStateProvince().getStateProvinceId().toString();
						}else{
							throw new Exception(i + getText("errors.city") + " city");
						}
					}else{
						throw new Exception(i + getText("errors.required") + " city");
					}

					String address = eu.getContents(sheet0, 6, i);
					if (!StringUtils.isEmpty(address)) {
						address = StringUtils.trim(address);
					}else{
						throw new Exception(i + getText("errors.required") + " address");
					}

					String postalcode = eu.getContents(sheet0, 7, i);
					if (!StringUtils.isEmpty(postalcode)) {
						postalcode = StringUtils.trim(postalcode);
					}else{
						throw new Exception(i + getText("errors.required") + " postalcode");
					}

					String resendSpNo = eu.getContents(sheet0, 8, i);
					if (!StringUtils.isEmpty(resendSpNo)) {
						Boolean existWarehouse = pdWarehouseManager.existWarehouseNoByCompany(resendSpNo,SessionLogin.getLoginUser(request).getCompanyCode());
						if(existWarehouse==false){
							throw new Exception(i + getText("errors.required") + " resendSpNo");
						}
						resendSpNo = StringUtils.trim(resendSpNo);
					}else{
						throw new Exception(i + getText("errors.required") + " resendSpNo");
					}

					String teamCode = eu.getContents(sheet0, 7, i);
					if (!StringUtils.isEmpty(teamCode)) {
						teamCode = StringUtils.trim(teamCode);
					}else{
						throw new Exception(i + getText("errors.required") + " postalcode");
					}


					String shippingCompany = "0000";//eu.getContents(sheet0, 9, i);
					
/*					LinkedHashMap list = ListUtil.getListOptions(SessionLogin.getLoginUser(request).getCompanyCode(), "feetype");
			    	Iterator iterator = list.keySet().iterator();
			    	while (iterator.hasNext()) {
			    		String feetype = (String)iterator.next();
			    		boolean isPass = false;
			    	}*/
					
					
					if (!StringUtils.isEmpty(shippingCompany)) {
						shippingCompany = StringUtils.trim(shippingCompany);
					}else{
						throw new Exception(i + getText("errors.required") + " shippingCompany");
					}

					String remark = eu.getContents(sheet0, 9, i);
					if (!StringUtils.isEmpty(remark)) {
						remark = StringUtils.trim(remark);
					}else{
						throw new Exception(i + getText("errors.required") + " remark");
					}
					String orderFee = eu.getContents(sheet0, 10, i);//物流费
					if (!StringUtils.isEmpty(orderFee)) {
						orderFee = StringUtils.trim(orderFee);
					}else{
						throw new Exception(i + getText("errors.required") + " orderFee");
					}
					String orderTeamType = eu.getContents(sheet0, 11, i);//用于标示团队 1 老中脉的，2 青岛 3 田阳1 4田阳2 5田阳3 6云南团队
					if (!StringUtils.isEmpty(orderTeamType)) {
						orderTeamType = StringUtils.trim(orderTeamType);
					}else{
						throw new Exception(i + getText("errors.required") + " orderTeamType");
					}
					
					
					SysUser sysUser = this.sysUserManager.getSysUser(userCode);
					if (sysUser == null) {
						throw new Exception(i + getText("miMember.notFound") + ": " + userCode);
					}
					if(!sysUser.getJmiMember().getMiUserName().equals(userName)){
						throw new Exception(i + getText("miMember.name.notFound") + ": " + userCode);
					}

					if (!SessionLogin.getLoginUser(request).getCompanyCode().equals("AA") && !SessionLogin.getLoginUser(request).getCompanyCode().equals(sysUser.getCompanyCode())) {
						throw new Exception("当前操作人的公司编码(" + SessionLogin.getLoginUser(request).getCompanyCode() + ")和用户【"+sysUser.getUserCode()+"】对应的公司编码(" + sysUser.getCompanyCode() + ")不同");
					}
					

		
					String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
					JpoMemberOrder jpoMemberOrder = new JpoMemberOrder();
					jpoMemberOrder.setAddress(address);
					jpoMemberOrder.setCity(city);
					jpoMemberOrder.setCompanyCode(companyCode);
					
					if("1".equals(orderTeamType))//老中脉，重消物流费店铺承担
					{
					   jpoMemberOrder.setConsumerAmount(new BigDecimal(orderFee));//店铺物流费
					}else if("2".equals(orderTeamType))//青岛
					{
						jpoMemberOrder.setConsumerAmount(new BigDecimal("0"));
					}else if("3".equals(orderTeamType))//田阳1
					{
						jpoMemberOrder.setConsumerAmount(new BigDecimal("0"));
					}else if("4".equals(orderTeamType))//田阳2
					{
						jpoMemberOrder.setConsumerAmount(new BigDecimal("0"));
					}else if("5".equals(orderTeamType))//田阳3
					{
						jpoMemberOrder.setConsumerAmount(new BigDecimal("0"));
					}else if("6".equals(orderTeamType))//云南团队
					{
						jpoMemberOrder.setConsumerAmount(new BigDecimal(orderFee));//店铺物流费
					}
				
					jpoMemberOrder.setCountryCode(companyCode);
					jpoMemberOrder.setEmail("");
					jpoMemberOrder.setFirstName(firstName);
					jpoMemberOrder.setIsPay("1");
					jpoMemberOrder.setLastName(lastName);
					jpoMemberOrder.setMobiletele("");
					jpoMemberOrder.setOrderTime(date);
					jpoMemberOrder.setOrderType("4");
					jpoMemberOrder.setOrderUserCode(SessionLogin.getLoginUser(request).getUserCode());
					jpoMemberOrder.setPayMode("1");
					jpoMemberOrder.setPhone(phone);
					jpoMemberOrder.setPickup("0");
					jpoMemberOrder.setPostalcode(postalcode);
					jpoMemberOrder.setProvince(province);
					jpoMemberOrder.setRemark(remark);
					jpoMemberOrder.setResendSpNo(resendSpNo);
					jpoMemberOrder.setShippingCompany(shippingCompany);
					jpoMemberOrder.setShippingSpecial("1");
					jpoMemberOrder.setStatus("1");
					jpoMemberOrder.setSubmitStatus("1");
					jpoMemberOrder.setSubmitTime(date);
					jpoMemberOrder.setSubmitUserCode(SessionLogin.getLoginUser(request).getUserCode());
					jpoMemberOrder.setSysUser(sysUser);
					jpoMemberOrder.setUserSpCode(sysUser.getUserCode());
					jpoMemberOrder.setIsSpecial("0");
					jpoMemberOrder.setLocked("0");
					jpoMemberOrder.setTeamCode(teamCode);
					
					Sheet sheet1 = workbook.getSheet(1);
					BigDecimal pvAmt = new BigDecimal("0");
					BigDecimal sumPrice = new BigDecimal("0");
					Set jpoMemberOrderListSet = new HashSet(0);
					for (int m = 1; m < sheet1.getRows(); m++) {
						JpoMemberOrderList jpoMemberOrderList = new JpoMemberOrderList();
						//产品编码,数量,金额,PV,
						String productNo = eu.getContents(sheet1, 0, m);
						if (!StringUtils.isEmpty(productNo)) {
							productNo = StringUtils.trim(productNo);
						}else{
							throw new Exception(m + getText("errors.required") + "");
						}

						String qty = eu.getContents(sheet1, 1, m);
						if (!StringUtils.isEmpty(qty)) {
							qty = StringUtils.trim(qty);
						}else{
							throw new Exception(m + getText("errors.required") + "");
						}

						String price = eu.getContents(sheet1, 2, m);
						if (!StringUtils.isEmpty(price)) {
							price = StringUtils.trim(price);
						}else{
							throw new Exception(m + getText("errors.required") + "");
						}

						String pv = eu.getContents(sheet1, 3, m);
						if (!StringUtils.isEmpty(pv)) {
							pv = StringUtils.trim(pv);
						}else{
							throw new Exception(m + getText("errors.required") + "");
						}
						JpmProductSaleTeamType jpmProductSale = jpmProductSaleManager.getJpmProductSaleTeamType(companyCode, productNo,Constants.FIRST_PURCHASE,teamCode);
						if(jpmProductSale==null){
							throw new Exception("产品不存在");
						}
						jpoMemberOrderList.setJpmProductSaleTeamType(jpmProductSale);
						jpoMemberOrderList.setJpoMemberOrder(jpoMemberOrder);
						jpoMemberOrderList.setPrice(new BigDecimal(price));
						jpoMemberOrderList.setProductType("0");
						jpoMemberOrderList.setPv(new BigDecimal(pv));
						jpoMemberOrderList.setQty(Integer.parseInt(qty));
						jpoMemberOrderList.setVolume(jpmProductSale.getJpmProductSaleNew().getVolume());
						jpoMemberOrderList.setWeight(jpmProductSale.getJpmProductSaleNew().getWeight());
						pvAmt = pvAmt.add(new BigDecimal(pv).multiply(new BigDecimal(qty)));
						sumPrice = sumPrice.add(new BigDecimal(price).multiply(new BigDecimal(qty)));
						jpoMemberOrderListSet.add(jpoMemberOrderList);
					}
					
					if(pvAmt.compareTo(new BigDecimal("0"))==-1){
						throw new Exception(i + getText("pv.notEnough0"));
					}
				
					jpoMemberOrder.setPvAmt(pvAmt);
				
		    		
		        	JpoMemberOrderFee jpoMemberOrderFee = new JpoMemberOrderFee();
		        	jpoMemberOrderFee.setJpoMemberOrder(jpoMemberOrder);
		        	jpoMemberOrderFee.setFeeType("1");//物流费
		        	jpoMemberOrderFee.setDetailType("0000");
		        	if("1".equals(orderTeamType))//老中脉，重消物流费店铺承担
					{
		        		jpoMemberOrderFee.setFee(new BigDecimal("0"));//店铺物流费
					}else if("2".equals(orderTeamType))//青岛
					{
						jpoMemberOrderFee.setFee(new BigDecimal(orderFee));
					}else if("3".equals(orderTeamType))//田阳1
					{
						jpoMemberOrderFee.setFee(new BigDecimal(orderFee));
					}else if("4".equals(orderTeamType))//田阳2
					{
						jpoMemberOrderFee.setFee(new BigDecimal(orderFee));
					}else if("5".equals(orderTeamType))//田阳3
					{
						jpoMemberOrderFee.setFee(new BigDecimal(orderFee));
					}else if("6".equals(orderTeamType))//云南团队
					{
						jpoMemberOrderFee.setFee(new BigDecimal("0"));//店铺物流费
					}
				
		        
		        	Set jpoMemberOrderFeeSet = new HashSet(0);
		        	jpoMemberOrderFeeSet.add(jpoMemberOrderFee);
		        	BigDecimal amount =new BigDecimal("0");
		        	Iterator its2 = jpoMemberOrderFeeSet.iterator();
		        	while (its2.hasNext()) {
		    			JpoMemberOrderFee jpoMemberOrderFee2 = (JpoMemberOrderFee) its2.next();
		    			amount = sumPrice.add(jpoMemberOrderFee.getFee());
		    		}
		        	jpoMemberOrder.setAmount(amount);
		        	String memberOrderNo = sysIdManager.buildIdStr("pono");//生成订单编号
    				jpoMemberOrder.setMemberOrderNo(memberOrderNo);
    				jpoMemberOrders.add(jpoMemberOrder);
    				jpoMemberOrderSets.add(jpoMemberOrderListSet);
    				jpoMemberOrderFeeSets.add(jpoMemberOrderFeeSet);
				}
				jpoMemberOrderManager.editJpoMemberOrderBatch(jpoMemberOrders, jpoMemberOrderSets, jpoMemberOrderFeeSets);
				saveMessage(request, getText("dataImport.suc"));
			} catch (Exception ex) {
				saveMessage(request, getText("dataImport.unSuc")+": "+ex.getMessage());
				log.error("xls文件导入失败!", ex);
			}
			
        }

        return new ModelAndView(getSuccessView());
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}
	public void setAlCityManager(AlCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}
	public void setJpmProductSaleManager(JpmProductSaleManager jpmProductSaleManager) {
		this.jpmProductSaleManager = jpmProductSaleManager;
	}
	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}
	public void setPdWarehouseManager(PdWarehouseManager pdWarehouseManager) {
		this.pdWarehouseManager = pdWarehouseManager;
	}
}