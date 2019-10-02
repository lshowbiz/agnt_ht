package com.joymain.jecs.po.webapp.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.pm.service.JpmProductSaleManager;
import com.joymain.jecs.po.model.JpoCounterOrder;
import com.joymain.jecs.po.model.JpoCounterOrderDetail;
import com.joymain.jecs.po.service.JpoCounterOrderDetailManager;
import com.joymain.jecs.po.service.JpoCounterOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JpoCounterOrderFormController extends BaseFormController {
    private JpoCounterOrderManager jpoCounterOrderManager = null;
    private JpoCounterOrderDetailManager jpoCounterOrderDetailManager;
    private JpmProductSaleManager jpmProductSaleManager;
    private PdWarehouseStockManager pdWarehouseStockManager;
    private SysIdManager sysIdManager;
    private SysUserManager sysUserManager;
    public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}
	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}
	public void setPdWarehouseStockManager(
			PdWarehouseStockManager pdWarehouseStockManager) {
		this.pdWarehouseStockManager = pdWarehouseStockManager;
	}
	public void setJpmProductSaleManager(JpmProductSaleManager jpmProductSaleManager) {
		this.jpmProductSaleManager = jpmProductSaleManager;
	}
	public void setJpoCounterOrderDetailManager(
			JpoCounterOrderDetailManager jpoCounterOrderDetailManager) {
		this.jpoCounterOrderDetailManager = jpoCounterOrderDetailManager;
	}
	public void setJpoCounterOrderManager(JpoCounterOrderManager jpoCounterOrderManager) {
        this.jpoCounterOrderManager = jpoCounterOrderManager;
    }
    public JpoCounterOrderFormController() {
        setCommandName("jpoCounterOrder");
        setCommandClass(JpoCounterOrder.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String coNo = request.getParameter("coNo");
        JpoCounterOrder jpoCounterOrder = null;


        String strAction = request.getParameter("strAction");
        boolean disabled = true;
        String checkButtonKey = null;//按钮显示的文字
        String checkFlag="-1";//新建
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        crm.setValue("coNo", coNo);
        Pager pager = new Pager("JpoCounterOrderDetailListTable",request, 20);
        
        List jpoCounterOrderDetails = new ArrayList();
        if (!StringUtils.isEmpty(coNo)) {
        	jpoCounterOrder =  jpoCounterOrderManager.getJpoCounterOrder(coNo);
            if(!"editPoCounterOrder".equals(strAction)){
            	crm.setValue("notZero", "1");
            }
            
            jpoCounterOrderDetails = jpoCounterOrderDetailManager.getJpoCounterOrderDetailsByCrm(crm, null);
//            jpoCounterOrderDetails=jpmProductSaleManager.getProductSales(sessionLogin.getCompanyCode(),  Constants.NOPV_PROCDUCT, "2");
            
        } else {
        	jpoCounterOrder = new JpoCounterOrder();
        }
//        if(StringUtils.isNotEmpty(request.getParameter("sysUser.userCode"))){
//        	
//        	jpoCounterOrder.setSysUser(sysUserManager.getSysUser(request.getParameter("sysUser.userCode")));
//        }
//        if("discountOrder".equals(strAction)){
//        	checkFlag = "0";
//        	checkButtonKey = "button.submit";
//        	disabled = false;
//        	if(poCounterOrder.getCsStatus()>=0){
//        		disabled = true;
//        	}
//        	poCounterOrder.setDiscountFlag("1");
//        }
        if("editPoCounterOrder".equals(strAction)){//修改订单
        	checkFlag = "0";
        	checkButtonKey = "button.submit";
        	disabled = false;
        	if(jpoCounterOrder.getCsStatus()>=0){//已提交订单不能修改 
        		disabled = true;
        	}
        }else if("paymentPoCounterOrder".equals(strAction)){
        	checkFlag = "1";
        	checkButtonKey = "poCounterOrder.payment";
        	disabled = true;
        }else if("repealPoCounterOrder".equals(strAction)){
        	checkFlag = "2";
        	checkButtonKey = "poCounterOrder.repeal";
        	disabled = true;
        }else if("searchPoCounterOrder".equals(strAction)){//查看订单
        	checkButtonKey = "poCounterOrder.back";
        	checkFlag = "-2";
        	disabled = true;
        }else if("addPoCounterOrder".equals(strAction)){//新增订单
        	checkFlag = "-1";
        	checkButtonKey = "button.submit";
        	disabled = false;
        }else if("shipPoCounterOrder".equals(strAction)){//发货
        	checkFlag = "3";
        	checkButtonKey = "pdSendInfo.sendPdSendInfo";
        	disabled = true;
        }
        request.setAttribute("coNo",coNo);
        request.setAttribute("checkButtonKey",checkButtonKey);
        request.setAttribute("checkFlag",checkFlag);
        request.setAttribute("strAction",strAction);
        request.setAttribute("disabledFlag",disabled);
        request.setAttribute("jpoCounterOrderDetailListTable_totalRows", pager.getTotalObjects());
        request.setAttribute("jpoCounterOrderDetailList", jpoCounterOrderDetails);
        return jpoCounterOrder;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JpoCounterOrder jpoCounterOrder = (JpoCounterOrder) command;

        boolean isNew = (jpoCounterOrder.getCoNo() == null);
        Locale locale = request.getLocale();
        String strAction = request.getParameter("strAction");
        String view ="redirect:jpoCounterOrders.html?strAction="+strAction;
        
        String key =null;
//        if("discountOrder".equals(strAction)){
//        	key ="poCounterOrder.updated";
//        	if(jpoCounterOrder.getCsStatus() <0){
//        		editPoCounterOrder(request,response,jpoCounterOrder);
//        	}
//        	view ="redirect:poCounterOrders.html?strAction=discountOrder";
//        }else
        if("addPoCounterOrder".equals(strAction)){
        	if(StringUtil.isEmpty(jpoCounterOrder.getSysUser().getUserCode())||sysUserManager.getSysUser(jpoCounterOrder.getSysUser().getUserCode())==null){
        		errors.reject("miMember.notFound");
				return showForm(request, response, errors);
        	}
        	String coNo=addPoCounterOrder(request,response,jpoCounterOrder);
        	key ="poCounterOrder.add";
        	view ="redirect:editJpoCounterOrder.html?strAction=editPoCounterOrder&coNo="+coNo;
        }else if("editPoCounterOrder".equals(strAction)){
        	editPoCounterOrder(request,response,jpoCounterOrder);

        	view ="redirect:jpoCounterOrders.html?strAction=editPoCounterOrder";
        }else if("deletePoCounterOrder".equals(strAction)){
        	key ="poCounterOrder.delete";
        	if(jpoCounterOrder.getCsStatus() <0){
        		deletePoCounterOrder(request,response,jpoCounterOrder);
        	}
        	view ="redirect:jpoCounterOrders.html?strAction=editPoCounterOrder";
        }else if("paymentPoCounterOrder".equals(strAction)){
        	key ="poCounterOrder.payment";
        	try {
				paymentPoCounterOrder(request,response,jpoCounterOrder);
//				request.setAttribute("printFlag", "1");
//				request.setAttribute("printCoNo", poCounterOrder.getCoNo());
			} catch (AppException e) {
				// TODO Auto-generated catch block
				key=e.getErrMsg();
				errors.reject(key, new Object[] {},LocaleUtil.getLocalText(key));
				return showForm(request, response, errors);
			}
        	view ="redirect:jpoCounterOrders.html?strAction=paymentPoCounterOrder&printFlag=1&printCoNo="+jpoCounterOrder.getCoNo();
        }else if("repealPoCounterOrder".equals(strAction)){
        	key ="poCounterOrder.repeal";
        	repealPoCounterOrder(request,response,jpoCounterOrder);
        	view ="redirect:jpoCounterOrders.html?strAction=repealPoCounterOrder";
        }else if("submitPoCounterOrder".equals(strAction)){
        	key ="poCounterOrder.submit";
//        	if(jpoCounterOrder.getAmount().compareTo(new BigDecimal(0))!=1){
//        		errors.reject("bdSendRegister.operaterFail");
//        		return showForm(request, response, errors);
//        	}
        	List erroList = this.validateStock(request, response, jpoCounterOrder);
        	if(erroList.size()==0){
        		submitPoCounterOrder(request,response,jpoCounterOrder);
    		}else{
    			for(int i=0;i<erroList.size();i++){
        			errors.reject(null, new Object[] {},(String)erroList.get(i)+LocaleUtil.getLocalText("pd.notEnoughStock"));
        		}
        		
				return showForm(request, response, errors);
    		}
        	
        	view ="redirect:jpoCounterOrders.html?strAction=editPoCounterOrder";
        }else if("toNewPoCounterOrder".equals(strAction)){
        	key="error.cannotreturn";
        	if(jpoCounterOrder.getCsStatus() ==0){
        		key="operation.notice.tonew";
        		jpoCounterOrder.setCsStatus(-1);
        		jpoCounterOrderManager.saveJpoCounterOrder(jpoCounterOrder);
        	}
        	view ="redirect:jpoCounterOrders.html?strAction=paymentPoCounterOrder";
        }
        else if("shipPoCounterOrder".equals(strAction)){
        	key ="bdSendRegister.operaterSuccess";
        	try {
        		shipPoCounterOrder(request,response,jpoCounterOrder);
			} catch (AppException e) {
				key=e.getErrMsg();
				errors.reject(key, new Object[] {},LocaleUtil.getLocalText(key));
				
				return showForm(request, response, errors);
			}
        	view ="redirect:jpoCounterOrders.html?strAction=shipPoCounterOrder";
        }
        
        

        saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(),key));
        return new ModelAndView(view);
    }
    private void submitPoCounterOrder(HttpServletRequest request,
			HttpServletResponse response, JpoCounterOrder jpoCounterOrder) {
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		jpoCounterOrder.setCreateTime(new Date());
		
		jpoCounterOrderManager.saveJpoCounterOrder(jpoCounterOrder);
		savePoCounterOrderDetail(request,response,jpoCounterOrder);
	}
	private void repealPoCounterOrder(HttpServletRequest request,
			HttpServletResponse response, JpoCounterOrder jpoCounterOrder) {
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		jpoCounterOrder.setCsStatus(3);
		jpoCounterOrder.setRepealerCode(sessionLogin);
		jpoCounterOrder.setRepealTime(new Date());
		jpoCounterOrderManager.repealPoCounterOrder(jpoCounterOrder);
	}
	//付款
	private void paymentPoCounterOrder(HttpServletRequest request,
			HttpServletResponse response, JpoCounterOrder jpoCounterOrder) {
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		jpoCounterOrder.setCsStatus(1);
		jpoCounterOrder.setConfirmTime(new Date());
		jpoCounterOrder.setConfirmUserNo(sessionLogin.getUserCode());
		jpoCounterOrderManager.saveJpoCounterOrder(jpoCounterOrder);
	}
	//发货
	private void shipPoCounterOrder(HttpServletRequest request,
			HttpServletResponse response, JpoCounterOrder jpoCounterOrder) {
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		jpoCounterOrder.setCsStatus(2);
		jpoCounterOrder.setShipper(sessionLogin);
		jpoCounterOrder.setShipTime(new Date());
		jpoCounterOrderManager.shipJpoCounterOrder(jpoCounterOrder);
	}
	private void deletePoCounterOrder(HttpServletRequest request,
			HttpServletResponse response, JpoCounterOrder jpoCounterOrder) {
		jpoCounterOrderManager.removeJpoCounterOrder(jpoCounterOrder.getCoNo().toString());
	}
	private void editPoCounterOrder(HttpServletRequest request,
			HttpServletResponse response, JpoCounterOrder jpoCounterOrder) {
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		
		jpoCounterOrderManager.saveJpoCounterOrder(jpoCounterOrder);
		
		savePoCounterOrderDetail(request,response,jpoCounterOrder);
		
	}
	private String addPoCounterOrder(HttpServletRequest request,
			HttpServletResponse response, JpoCounterOrder jpoCounterOrder) throws Exception{
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		jpoCounterOrder.setCoNo(sysIdManager.buildIdStr("cono"));
		jpoCounterOrder.setCompanyCode(sysUserManager.getSysUser(jpoCounterOrder.getSysUser().getUserCode()).getCompanyCode());
		log.info("companyno="+jpoCounterOrder.getCompanyCode());
		jpoCounterOrder.setCreateTime(new Date());
		jpoCounterOrder.setCreateUserNo(sessionLogin.getUserCode());
		jpoCounterOrderManager.saveJpoCounterOrder(jpoCounterOrder);
		
		jpoCounterOrderDetailManager.savePreDetails(sessionLogin.getCompanyCode(), jpoCounterOrder);
		
		return jpoCounterOrder.getCoNo();
	}
	
	private void savePoCounterOrderDetail(HttpServletRequest request,
			HttpServletResponse response,JpoCounterOrder jpoCounterOrder){

		
		Set sets = jpoCounterOrder.getJpoCounterOrderDetails();
		Iterator iterator =sets.iterator();
		while(iterator.hasNext()){
			JpoCounterOrderDetail jpoCounterOrderDetail= (JpoCounterOrderDetail) iterator.next();
			
			String price = StringUtils.defaultIfEmpty(request.getParameter(jpoCounterOrderDetail.getCodNo()+"~price"), "0") ;
			String qty = StringUtils.defaultIfEmpty(request.getParameter(jpoCounterOrderDetail.getCodNo()+"~qty"), "0") ;
			jpoCounterOrderDetail.setPrice(new BigDecimal(price));
			jpoCounterOrderDetail.setQty(new Integer(qty));
			jpoCounterOrderDetailManager.saveJpoCounterOrderDetail(jpoCounterOrderDetail);
		}
		jpoCounterOrderManager.updateAmount(jpoCounterOrder.getCoNo());
	}
	
	private List validateStock(HttpServletRequest request,
			HttpServletResponse response,JpoCounterOrder jpoCounterOrder){
			SysUser sessionLogin = SessionLogin.getLoginUser(request);
			List list = new ArrayList();
	

/*			Map compamyProductMap = jpmProductSaleManager.getCompanyProductMap(sessionLogin.getCompanyCode());
>>>>>>> .r5180

			Set sets = jpoCounterOrder.getJpoCounterOrderDetails();
			Iterator iterator =sets.iterator() ;
			while(iterator.hasNext()){
				JpoCounterOrderDetail jpoCounterOrderDetail = (JpoCounterOrderDetail) iterator.next();
				
				if( (jpoCounterOrderDetail.getQty() >0)&&(!pdWarehouseStockManager.enoughStock(jpoCounterOrder.getWarehouseNo(), jpoCounterOrderDetail.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo(), jpoCounterOrderDetail.getQty().longValue()))){
					list.add(jpoCounterOrderDetail.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo()+"-"+((JpmProductSaleTeamType)compamyProductMap.get(jpoCounterOrderDetail.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())).getJpmProductSaleNew().getProductName());

				}
			}*/
			return list;
	}
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
