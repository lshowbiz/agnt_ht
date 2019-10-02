package com.joymain.jecs.pr.webapp.action;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.pm.service.JpmProductSaleManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.service.JpoMemberOrderListManager;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.pr.model.JprRefund;
import com.joymain.jecs.pr.model.JprRefundList;
import com.joymain.jecs.pr.service.JprRefundManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 退货单表单
 * @author Alvin
 *
 */
public class JprRefundFormController extends BaseFormController {
    private JprRefundManager jprRefundManager = null;
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private JpmProductSaleManager jpmProductSaleManager = null;
	private SysIdManager sysIdManager;
	private JpoMemberOrderListManager jpoMemberOrderListManager;
	private PdWarehouseStockManager pdWarehouseStockManager;

    public void setJpmProductSaleManager(JpmProductSaleManager jpmProductSaleManager) {
		this.jpmProductSaleManager = jpmProductSaleManager;
	}
	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}
	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}
	public void setJprRefundManager(JprRefundManager jprRefundManager) {
        this.jprRefundManager = jprRefundManager;
    }
	
	public void setJpoMemberOrderListManager(
			JpoMemberOrderListManager jpoMemberOrderListManager) {
		this.jpoMemberOrderListManager = jpoMemberOrderListManager;
	}
	
    public void setPdWarehouseStockManager(
			PdWarehouseStockManager pdWarehouseStockManager) {
		this.pdWarehouseStockManager = pdWarehouseStockManager;
	}
	public JprRefundFormController() {
        setCommandName("jprRefund");
        setCommandClass(JprRefund.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
    	SysUser defSysUser = SessionLogin.getLoginUser(request);
		JprRefund jprRefund = null;
		
		String moId = request.getParameter("moId");
		String roNo = request.getParameter("roNo");
		String strDifference = request.getParameter("strDifference");
		String strAction = request.getParameter("strAction");
		if(!StringUtil.isEmpty(moId)){
			jprRefund = jprRefundManager.getRefundByMoId(Long.parseLong(moId),null);
		
		}else if(!StringUtil.isEmpty(roNo)){
			jprRefund = jprRefundManager.getJprRefund(roNo);
			if((!StringUtil.isEmpty(strAction))&&(!"deletePrRefund".equals(strAction))){
				//modify fu 2015-11-06 修改退单的编辑商品数量--------begin
				Long moIdOne = jprRefund.getJpoMemberOrder().getMoId();
				JprRefund jprRefundOne = jprRefundManager.getRefundByMoId(moIdOne,roNo);
				jprRefund.setJpoMemberOrder(jprRefundOne.getJpoMemberOrder());
				//modify fu 2015-11-06 修改退单的编辑商品数量--------end
			}
			if("2".equals(jprRefund.getOrderStatus())|| "3".equals(jprRefund.getOrderStatus())){
				request.setAttribute("strAction", "alreadyCheck");
				if("strDifference".equals(strDifference)){
					request.setAttribute("strDifference", strDifference);
				}
			}
		}
		
		if(jprRefund.getJpoMemberOrder().getJpoMemberOrderList().size()==0){
			//throw new AppException("商品已退!");
			request.setAttribute("productNoHasRetired", "productNoHasRetired");
		}
		
		if(!defSysUser.getCompanyCode().equals(jprRefund.getCompanyCode()) ||
				!"N".equals(jprRefund.getLocked())){
			//modify fu 2015-11-11 为避免这里面新建的退单的w_year,w_month,w_week为空，在退单编辑里面重新赋值
			JprRefund jprRefundO = new JprRefund();
			jprRefundO.setWyear(0);
			jprRefundO.setWmonth(0);
			jprRefundO.setWweek(0);
			return jprRefundO;
		}
		return jprRefund;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        
        //modify gw 2014-10-31 
        String strAction = request.getParameter("strAction");
        String view = "redirect:jprRefunds.html?strAction=initJprRefund";
        
    	//================AiAgent LOGIN IMFORMATION
    	SysUser defSysUser = SessionLogin.getLoginUser(request);
    	//=========================================
        log.info("在类JprRefundFormController的方法onSubmit中运行，操作人："+defSysUser.getUserCode());
    	
    	JprRefund jprRefund = (JprRefund) command;
    	
    	//modify gw 2014-11-04----------------------begin---结算费用
    	 String repairFee = request.getParameter("repairFee");
    	 String renovationFee = request.getParameter("renovationFee");
    	 String logisticsFee = request.getParameter("logisticsFee");
    	 String settledBonus = request.getParameter("settledBonus");
    	 String fillFreight = request.getParameter("fillFreight");
    	

    	// String logisticsFeeSix = request.getParameter("logisticsFeeSix");
    	// String logisticsFeeSeven = request.getParameter("logisticsFeeSeven");
    	// String logisticsFeeEight = request.getParameter("logisticsFeeEight");	
			jprRefund.setRepairFee(repairFee);
			jprRefund.setRenovationFee(renovationFee);
			jprRefund.setLogisticsFee(logisticsFee);
			jprRefund.setSettledBonus(settledBonus);
			jprRefund.setFillFreight(fillFreight);
			

		//	jprRefund.setLogisticsFeeSix(logisticsFeeSix);
		//	jprRefund.setLogisticsFeeSeven(logisticsFeeSeven);
		//	jprRefund.setLogisticsFeeEight(logisticsFeeEight);
		
			Float sum =0f;	
			
		//2015-02-06 gw 退单的结算费用不计算在总账中，因此这个地方注释掉---begin
		/*if(!StringUtil.isEmpty(repairFee)){
			sum += Float.parseFloat(repairFee);
		}
		if(!StringUtil.isEmpty(renovationFee)){
			sum += Float.parseFloat(renovationFee);
		}
		if(!StringUtil.isEmpty(logisticsFee)){
			sum += Float.parseFloat(logisticsFee);
		}
		if(!StringUtil.isEmpty(settledBonus)){
			sum += Float.parseFloat(settledBonus);
		}
		if(!StringUtil.isEmpty(fillFreight)){
			sum += Float.parseFloat(fillFreight);
		}*/
		//2015-02-06 gw 退单的结算费用不计算在总账中，因此这个地方注释掉---end
		
		/*if(!StringUtil.isEmpty(logisticsFeeSix)){
			sum += Float.parseFloat(logisticsFeeSix);
		}
		if(!StringUtil.isEmpty(logisticsFeeSeven)){
			sum += Float.parseFloat(logisticsFeeSeven);
		}
		if(!StringUtil.isEmpty(logisticsFeeEight)){
			sum += Float.parseFloat(logisticsFeeEight);
		}*/
		
    	//modify gw 2014-11-04----------------------end

		 String refundTye = request.getParameter("refundTye");	
		 jprRefund.setRefundTye(refundTye);
		boolean isNew = StringUtil.isEmpty(jprRefund.getRoNo())?true:false;
		
		if ("deletePrRefund".equals(request.getParameter("strAction"))  ) {
        	if(jprRefund.getIntoStatus().equals("1") && jprRefund.getRefundStatus().equals("1")){
        		
        		//在删除之前去WMS询问退货单能否删除----begin modify by fu 2016-03-21
				String methodNameWMS = "checkstatus";
				String typeWMS = "22";
				String canDelete = pdWarehouseStockManager.getPdLogisticsCanModify(jprRefund.getRoNo(), typeWMS, methodNameWMS);
				if((!StringUtil.isEmpty(canDelete))&&(!"allow".equals(canDelete))){
					if("notAllow".equals(canDelete)){
						MessageUtil.saveLocaleMessage(request, "因退货单后续系统已经操作，故发货单不能编辑！");
					}else{
						MessageUtil.saveLocaleMessage(request,canDelete);
					}
		        	return new ModelAndView("redirect:jprRefunds.html?strAction=initJprRefund");
				}
				//在删除之前去WMS询问退货单能否删除----end modify by fu 2016-03-21
        		
        		jprRefundManager.removeJprRefund(jprRefund.getRoNo());
        		//Modify By WuCF 20151211 修改对应订单的字段
//    			jprRefundManager.updateJpoMemberOrderFlag(jprRefund);
    			 
        		String  intoStatus = jprRefundManager.getIntoStatus(jprRefund.getRoNo());
        		if((!StringUtil.isEmpty(intoStatus)) && "2".equals(intoStatus)){
    				MessageUtil.saveLocaleMessage(request, "退货单已经接收到接口入库消息，这张退货单不可以删除！");
        			// 在退货单接口消息推送下去之前，判断退货单有没有接收到接口退货入库操作，如果有，那么就不允许修改这张退货单
        		}else{
        			    saveMessage(request, getText(defSysUser.getDefCharacterCoding(),"prRefund.deleted"));
        		}
        	}else{
        		saveMessage(request, getText(defSysUser.getDefCharacterCoding(),"prRefund.notDelete"));
        	}
        	//return new ModelAndView(getSuccessView()).addObject("needReload", "1");
        	return new ModelAndView("redirect:jprRefunds.html?strAction=initJprRefund");
        }

		jprRefund.setOrderDate(new Date());
		jprRefund.setLocked("N");
		Set jprRefundSet = new HashSet(0);
		try{
			jprRefundSet = createJprRefundList(request,jprRefund,sum);
		}catch(NumberFormatException e){
    		errors.reject("error.isNotInteger", new Object[] {},LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(),"error.isNotInteger"));
    		return showForm(request, response, errors);
		}catch(AppException app){
        	if("error.prIsNotFound".equals(app.getMessage())){
        		errors.reject("error.prIsNotFound", new Object[] {},LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(),"error.prIsNotFound"));
        		return showForm(request, response, errors);
        	}
        	if("error.prIsNotEnough".equals(app.getMessage())){
        		errors.reject("error.prIsNotEnough", new Object[] {},LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(),"error.prIsNotEnough"));
        		return showForm(request, response, errors);
        	}
        	if("error.poMemberOrder.null".equals(app.getMessage())){
        		errors.reject("error.poMemberOrder.null", new Object[] {},LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(),"error.poMemberOrder.null"));
        		return showForm(request, response, errors);
        	}
        	if("error.qytNotEnought".equals(app.getMessage())){
        		errors.reject("error.qytNotEnought", new Object[] {},LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(),"error.qytNotEnought"));
        		return showForm(request, response, errors);
        	}
        	if("integer.notEnough".equals(app.getMessage())){
        		errors.reject("integer.notEnough", new Object[] {},LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(),"integer.notEnough"));
        		return showForm(request, response, errors);
        	}
        	errors.reject("refund.notChoose", new Object[] {},LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(),"refund.notChoose"));
    		return showForm(request, response, errors);
		}
		String key = isNew?"prRefund.success":"prRefund.editSuccess";
		if(isNew){
			jprRefund.setCreateTime(new Date());
			jprRefund.setCreateUNo(defSysUser.getUserCode());
			jprRefund.setIntoStatus("1");
			jprRefund.setRefundStatus("1");
			jprRefund.setLocked("N");
			String RoNo = sysIdManager.getBuildIdStr("mro");
			
			jprRefund.setRoNo(RoNo);
			jprRefund.setLogisticsFeeSeven("Y");
			//modify gw 2014-10-31 
			//ORDER_STATUS   '退货单状态（1未提交（新单），2已提交，3已审核'            
			jprRefund.setOrderStatus("1");
			
			//modify fx 20150817 添加退货单没有推送出去消息
			//退货单消息是否推送出去标识：isNew表明退货单没有推送出去;noNew表明退货单已经推送出去
			jprRefund.setLogisticsFeeSix("isNew");
	        log.info("在类JprRefundFormController的方法onSubmit中运行，新增退货单开始："+defSysUser.getUserCode());

			jprRefundManager.saveJprRefund(jprRefund,jprRefundSet);
			
			//Modify By WuCF 20151211 修改对应订单的字段
//			jprRefundManager.updateJpoMemberOrderFlag(jprRefund);
			
			String  intoStatus = jprRefundManager.getIntoStatus(jprRefund.getRoNo());
    		if((!StringUtil.isEmpty(intoStatus)) && "2".equals(intoStatus)){
				MessageUtil.saveLocaleMessage(request, "退货单已经接收到接口入库消息，这张退货单不可以修改！");
    			// 在退货单接口消息推送下去之前，判断退货单有没有接收到接口退货入库操作，如果有，那么就不允许修改这张退货单
    		}else{
    			boolean isOrNotReturnStatus = jpoMemberOrderManager.getOrderReturnableStatus(jprRefund.getJpoMemberOrder().getMemberOrderNo());
    			if(isOrNotReturnStatus){
    				MessageUtil.saveLocaleMessage(request, "因订单被挂起，所以不可以制退单！");
				}else{
					saveMessage(request, getText(defSysUser.getDefCharacterCoding(),key));
				}
			}
			
			return new ModelAndView(view);
			
		}else{
			//modify gw 2014-10-31 
			//ORDER_STATUS   '退货单状态（1未提交（新单），2已提交，3已审核'
			if("alreadySubmit".equals(strAction)){
				jprRefund.setOrderStatus("2");
			}else if("alreadyCheck".equals(strAction)){
				jprRefund.setOrderStatus("3");
				//modify gw 2014-11-04    INTO_STATUS
				//因在报单中心的物流没有出入库及真实的库存，所以就再审核的同时，让该退货单就入库 2014-11-04
				//-------------这样会导致库存变更记录及库存数据对不上，因此不能将这部分代码同步到正在运行的EC

				jprRefund.setIntoStatus("2");
				
				view = "redirect:jprRefunds.html?strAction=jprRefundsCheck";
				
				
				jprRefundManager.saveJprRefund(jprRefund,jprRefundSet);
				saveMessage(request, getText(defSysUser.getDefCharacterCoding(),key));
				//return new ModelAndView(getSuccessView()).addObject("needReload", "1");
				
				return new ModelAndView(view);
				
				
				
			}else if("toNewSingle".equals(strAction)){
				jprRefund.setOrderStatus("1");
				view = "redirect:jprRefunds.html?strAction=jprRefundsCheck";
			}

			//退货单编辑类型：21退货单编辑、22退货单删除 modify by fu 2016-03-21
			jprRefund.setLogisticsFeeEight("21");
			
			jprRefund.setEditTime(new Date());
			jprRefund.setEditUNo(defSysUser.getUserCode());
		}
		//modify fx 20150817 添加退货单没有推送出去消息
		//退货单消息是否推送出去标识：isNew表明退货单没有推送出去;noNew表明退货单已经推送出去
		jprRefund.setLogisticsFeeSix("isNew");
		
		//在退货单编辑之前去WMS询问退货单能否编辑----begin modify by fu 2016-03-21
			String methodNameWMS = "checkstatus";
			String typeWMS = "21";
			String canUpdate = pdWarehouseStockManager.getPdLogisticsCanModify(jprRefund.getRoNo(), typeWMS, methodNameWMS);
			if((!StringUtil.isEmpty(canUpdate))&&(!"allow".equals(canUpdate))){
				if("notAllow".equals(canUpdate)){
					MessageUtil.saveLocaleMessage(request, "因退货单后续系统已经操作，故退货单不能编辑！");
				}else{
					MessageUtil.saveLocaleMessage(request,canUpdate);
				}
				return new ModelAndView(view);
			}
		//在退货单编辑之前去WMS询问退货单能否编辑----end modify by fu 2016-03-21
		
		jprRefundManager.saveJprRefund(jprRefund,jprRefundSet);
		String  intoStatus = jprRefundManager.getIntoStatus(jprRefund.getRoNo());
		if((!StringUtil.isEmpty(intoStatus)) && "2".equals(intoStatus)){
			MessageUtil.saveLocaleMessage(request, "退货单已经接收到接口入库消息，这张退货单不可以修改！");
			// 在退货单接口消息推送下去之前，判断退货单有没有接收到接口退货入库操作，如果有，那么就不允许修改这张退货单
		}else{
		    saveMessage(request, getText(defSysUser.getDefCharacterCoding(),key));
		}
		//return new ModelAndView(getSuccessView()).addObject("needReload", "1");
		return new ModelAndView(view);
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
    	String[] allowedFields = {"remark","resendSpNo"};
    	binder.setAllowedFields(allowedFields);
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
    
    /**
     * 检查数量是否小于库存量和数值必为正数
     * @param prRefund
     * @param prRefundListSet
     */
    private void checkQTY(JprRefund jprRefund, Set jprRefundListSet) throws Exception{
    	if(jprRefund.getAmount().compareTo(new BigDecimal(0)) == -1 || jprRefund.getPvAmt().compareTo(new BigDecimal(0))==-1){
    		throw new AppException("integer.notEnough");//数值不能小于0
    	}
    	Iterator jprRefundListIts = jprRefundListSet.iterator();
    	while(jprRefundListIts.hasNext()){
    		JprRefundList jprRefundList = (JprRefundList)jprRefundListIts.next();
    		boolean isFind = false;
        	Iterator jpoMemberOrderListIts;
        	try{
            	jpoMemberOrderListIts = jprRefund.getJpoMemberOrder().getJpoMemberOrderList().iterator();
        	}catch(NullPointerException e){
        		throw new AppException("error.poMemberOrder.null");
        	}
        	while(jpoMemberOrderListIts.hasNext()){
        		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList)jpoMemberOrderListIts.next();
        		try{
        			/*JpmProductSale jpmProductSaleO = jpoMemberOrderList.getJpmProductSale();
        			JpmProductSale jpmProductSaleR = jprRefundList.getJpmProductSale();*/
        			JpmProductSaleTeamType   JpmProductSaleTeamTypeO=new JpmProductSaleTeamType();
        			JpmProductSaleTeamType   JpmProductSaleTeamTypeR=new JpmProductSaleTeamType();
            		if(JpmProductSaleTeamTypeO.getPttId()==JpmProductSaleTeamTypeR.getPttId() && jpoMemberOrderList.getMolId().equals(jprRefundList.getMolId())){
            			if(jpoMemberOrderList.getQty()<jprRefundList.getQty()){
            				throw new AppException("error.qytNotEnought");//退货量比订购量多
            			}else if(jprRefundList.getQty()<0 || jprRefundList.getPv().compareTo(new BigDecimal(0))==-1 || jprRefundList.getPrice().compareTo(new BigDecimal(0)) == -1){
            				throw new AppException("integer.notEnough");//数值不能小于0
            			}
            			isFind = true;
            			break;
            		}
        		}catch(NullPointerException e){
        			throw new AppException("error.prIsNotFound");//产品未找到
        		}
        	}
        	if(!isFind){
        		throw new AppException("error.prIsNotFound");//产品未找到
        	}
    	}
    }

	/**
	 * 生成退货单明细
	 * @param request
	 * @param jprRefund
	 */
	private Set createJprRefundList(HttpServletRequest request, JprRefund jprRefund,Float sum) throws Exception{
		String[] jpmProductSaleUniNos = request.getParameterValues("g_no");
		String[] prices = request.getParameterValues("price");
		String[] qtys = request.getParameterValues("qty");
		String[] pvs = request.getParameterValues("pv");
		String[] molIds = request.getParameterValues("molId");
		
		String product = null;
		Integer flag = 0;
		BigDecimal amtPv = new BigDecimal(0);
		BigDecimal amount = new BigDecimal(0);
		Set jprRefundSet = new HashSet(0);
		for (int i = 0; i < jpmProductSaleUniNos.length; i++) {
			if(Integer.parseInt(qtys[i])>0){
				JpmProductSaleTeamType jpmProductSale = jpmProductSaleManager.getJpmProductSaleTeamType(jpmProductSaleUniNos[i]);
				JprRefundList jprRefundList = new JprRefundList();
				int qty = Integer.parseInt(qtys[i]);
				BigDecimal price = new BigDecimal(prices[i]);
				BigDecimal pv = new BigDecimal(pvs[i]);
				Long molId = new Long(molIds[i]);
				jprRefundList.setJprRefund(jprRefund);
				jprRefundList.setJpmProductSaleTeamType(jpmProductSale);
				jprRefundList.setPrice(price);
				jprRefundList.setQty(qty);
				jprRefundList.setPv(pv);
				jprRefundList.setMolId(molId);	
				
				//modify gw 2014-11-21 添加ERP商品编码的字段
				jprRefundList.setErpProductNo(jpmProductSale.getJpmProductSaleNew().getJpmProduct().getErpProductNo());
				
				amtPv = amtPv.add(pv.multiply(new BigDecimal(qty)));
				amount = amount.add(new BigDecimal(prices[i]).multiply(new BigDecimal(qtys[i])));
				jprRefundSet.add(jprRefundList);
			}
		}
		jprRefund.setPvAmt(amtPv);

	    if(sum>0){   	
            Double aaAmount = Double.parseDouble(amount.toString());
		    Double aaaAmount =  aaAmount - sum;
		    DecimalFormat df=new DecimalFormat("0.00"); //格式化，保留两位小数 
		    //BigDecimal totalAmount = new BigDecimal(aaaAmount);
		    BigDecimal totalAmount = new BigDecimal(df.format(aaaAmount));
		    jprRefund.setAmount(totalAmount);	   
	    }else{
		    jprRefund.setAmount(amount);
	    }
		
		checkQTY(jprRefund,jprRefundSet);
		if(jprRefundSet.size()>0){
			return jprRefundSet;
		} else {
			throw new AppException("未选择退货明细!");
		}
		
		
	}
	
	

}
