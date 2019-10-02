package com.joymain.jecs.pd.webapp.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
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

import com.joymain.jecs.pd.model.PdExchangeOrder;
import com.joymain.jecs.pd.model.PdExchangeOrderBack;
import com.joymain.jecs.pd.model.PdExchangeOrderDetail;
import com.joymain.jecs.pd.model.PdWarehouse;
import com.joymain.jecs.pd.service.PdExchangeOrderBackManager;
import com.joymain.jecs.pd.service.PdExchangeOrderDetailManager;
import com.joymain.jecs.pd.service.PdExchangeOrderManager;
import com.joymain.jecs.pd.service.PdWarehouseManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class PdExchangeOrderFormController extends BaseFormController {
	private PdExchangeOrderManager pdExchangeOrderManager = null;
	private JpoMemberOrderManager jpoMemberOrderManager = null;
	private PdExchangeOrderBackManager pdExchangeOrderBackManager = null;
	private PdExchangeOrderDetailManager pdExchangeOrderDetailManager = null;
	private SysIdManager sysIdManager = null;
	
	private PdWarehouseManager pdWarehouseManager = null;

	public void setPdExchangeOrderBackManager(
			PdExchangeOrderBackManager pdExchangeOrderBackManager) {
		this.pdExchangeOrderBackManager = pdExchangeOrderBackManager;
	}

	public void setPdExchangeOrderDetailManager(
			PdExchangeOrderDetailManager pdExchangeOrderDetailManager) {
		this.pdExchangeOrderDetailManager = pdExchangeOrderDetailManager;
	}

	

	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}

	public void setJpoMemberOrderManager(
			JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public void setPdExchangeOrderManager(
			PdExchangeOrderManager pdExchangeOrderManager) {
		this.pdExchangeOrderManager = pdExchangeOrderManager;
	}

	public void setPdWarehouseManager(PdWarehouseManager pdWarehouseManager) {
		this.pdWarehouseManager = pdWarehouseManager;
	}

	public PdExchangeOrderFormController() {
		setCommandName("pdExchangeOrder");
		setCommandClass(PdExchangeOrder.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String eoNo = request.getParameter("eoNo");
		String moId = request.getParameter("moId");
		PdExchangeOrder pdExchangeOrder = null;
		super.initPmProductMap(request);
		super.initStateCodeParem(request);
		Boolean disabledFlag = true;
		//2015-05-27 换货单的流程变更  modify gw -------------begin
		String strAction = request.getParameter("strAction");
		if((!StringUtils.isEmpty(strAction)) && ("addPdExchangeOrder".equals(strAction)) && (!StringUtils.isEmpty(moId))){
			        SysUser sessionLogin = SessionLogin.getLoginUser(request); 
			        pdExchangeOrder = new PdExchangeOrder();
			        JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(moId);
			        pdExchangeOrder = this.getPdExchangeOrderAdd(pdExchangeOrder,jpoMemberOrder,sessionLogin);
					disabledFlag = false;
		}
		else if((!StringUtils.isEmpty(strAction)) && ("pdExchangeOrderAdd".equals(strAction))){
			       disabledFlag = false;
			       pdExchangeOrder = new PdExchangeOrder();
		}
		else{
		//2015-05-27 换货单的流程变更  modify gw -------------end

				if (!StringUtils.isEmpty(eoNo)) {
					pdExchangeOrder = pdExchangeOrderManager.getPdExchangeOrder(eoNo);
					if((!StringUtil.isEmpty(strAction))&&("pdExchangeOrderAddNext".equals(strAction))){
						Set<PdExchangeOrderDetail> pdExchangeOrderDetailSet = pdExchangeOrder.getPdExchangeOrderDetails();
						if(null!=pdExchangeOrderDetailSet){
							if(pdExchangeOrderDetailSet.size()>0){
							}else{
								     // Set<PdExchangeOrderDetail> pdExchangeOrderDetails = new HashSet<PdExchangeOrderDetail>();
								      //pdExchangeOrderDetails = pdExchangeOrderDetailManager.getPdExchangeOrderDetailForOrderNo(pdExchangeOrder.getOrderNo());
								     // pdExchangeOrder.setPdExchangeOrderDetails(pdExchangeOrderDetails);
							}
						}
					}
				} else {
					pdExchangeOrder = new PdExchangeOrder();
		//			if (!StringUtils.isEmpty(moId)) {
						JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager
								.getJpoMemberOrder(moId);
						pdExchangeOrder.setCompanyCode(jpoMemberOrder.getCompanyCode());
						pdExchangeOrder.setCustomer(jpoMemberOrder.getSysUser());
						pdExchangeOrder.setOrderNo(jpoMemberOrder.getMemberOrderNo());
						// 发货信息
						pdExchangeOrder.setFirstName(jpoMemberOrder.getFirstName());
						pdExchangeOrder.setLastName(jpoMemberOrder.getLastName());
						pdExchangeOrder.setProvince(jpoMemberOrder.getProvince());
						pdExchangeOrder.setCity(jpoMemberOrder.getCity());
						pdExchangeOrder.setDistrict(jpoMemberOrder.getDistrict());
						pdExchangeOrder.setAddress(jpoMemberOrder.getAddress());
						pdExchangeOrder.setPostalcode(jpoMemberOrder.getPostalcode());
						pdExchangeOrder.setPhone(jpoMemberOrder.getPhone());
						pdExchangeOrder.setEmail(jpoMemberOrder.getEmail());
						pdExchangeOrder.setMobiletele(jpoMemberOrder.getMobiletele());
		//			}
		
				}
				
				if(pdExchangeOrder.getOrderFlag() == -1){
					disabledFlag = false;
				}
		}
		
		
		
		//得到退回仓库：价格和PV的总和
		BigDecimal totalBackPrice = new BigDecimal(0);
		BigDecimal totalBackPv = new BigDecimal(0);
		Set s1 = pdExchangeOrder.getPdExchangeOrderBacks();
		PdExchangeOrderBack p = null;
		for(Object o : s1){
			p = (PdExchangeOrderBack)o;
			totalBackPrice = totalBackPrice.add(p.getPrice().multiply(new BigDecimal(p.getQty())));
			totalBackPv = totalBackPv.add(p.getPv().multiply(new BigDecimal(p.getQty())));
		}  
		//得到换仓库：价格和PV的总和
		BigDecimal totalDetailPrice = new BigDecimal(0);
		BigDecimal totalDetailPv = new BigDecimal(0);
		Set s2 = pdExchangeOrder.getPdExchangeOrderDetails();
		PdExchangeOrderDetail p2 = null;
		for(Object o : s2){
			p2 = (PdExchangeOrderDetail)o;
			totalDetailPrice = totalDetailPrice.add(p2.getPrice().multiply(new BigDecimal(p2.getQty())));
			totalDetailPv = totalDetailPv.add(p2.getPv().multiply(new BigDecimal(p2.getQty())));
		}  
		request.setAttribute("totalBackPrice",totalBackPrice);
		request.setAttribute("totalBackPv",totalBackPv);
		request.setAttribute("totalDetailPrice",totalDetailPrice);
		request.setAttribute("totalDetailPv",totalDetailPv);
		 
		request.setAttribute("disabledFlag", disabledFlag);
		
		request.setAttribute("strAction", strAction);
		
		request.setAttribute("moId",moId);
		
		//modify by fu 2016-05-11 --------------------------------------begin
		//自助换货单会员不填退回仓库编号，为了规避页面的校验，特意在这里给自助换货单的退回仓库赋值，但是这个仓库值并不能正在的保存进数据库
		//后台制的换货单对应的仓库编号还是必填的
		String selfReplacement =  pdExchangeOrder.getSelfReplacement();
		if((!StringUtil.isEmpty(selfReplacement))&&("Y".equals(selfReplacement))){
			String warehouseNo = pdExchangeOrder.getWarehouseNo();
			if(StringUtil.isEmpty(warehouseNo)){
				List list = pdWarehouseManager.getPdWarehouses(null);
				if(null!=list && list.size()>0){
					PdWarehouse pdWarehouse = (PdWarehouse) list.get(0);
					pdExchangeOrder.setWarehouseNo(pdWarehouse.getWarehouseNo());
				}
			}
		}
		//modify by fu 2016-05-11 --------------------------------------end

		return pdExchangeOrder;
	}

	/**
	 * 换货单在新增初始化进页面时候的赋值
	 * @author gw 2015-05-27
	 * @param pdExchangeOrder 换货单的对象
	 * @param jpoMemberOrder 订单表的对象
	 * @param sessionLogin Sysuser对象
	 * @return pdExchangeOrder
	 */
	private PdExchangeOrder getPdExchangeOrderAdd(PdExchangeOrder pdExchangeOrder, JpoMemberOrder jpoMemberOrder,SysUser sessionLogin) {
				//主键赋值
		        //pdExchangeOrder.setEoNo(sysIdManager.buildIdStr("pd_eono"));
		
		        //换货主对象赋值pdExchangeOrder--------begin
		        pdExchangeOrder.setEoNo(sysIdManager.buildIdStr("pd_eono"));//--------------------
		        pdExchangeOrder.setCompanyCode(jpoMemberOrder.getCompanyCode());
		        pdExchangeOrder.setCustomer(jpoMemberOrder.getSysUser());
		        pdExchangeOrder.setOrderNo(jpoMemberOrder.getMemberOrderNo());
		        pdExchangeOrder.setFirstName(jpoMemberOrder.getFirstName());
		        pdExchangeOrder.setLastName(jpoMemberOrder.getLastName());
		        pdExchangeOrder.setProvince(jpoMemberOrder.getProvince());
		        pdExchangeOrder.setCity(jpoMemberOrder.getCity());
		        pdExchangeOrder.setDistrict(jpoMemberOrder.getDistrict());
		        pdExchangeOrder.setAddress(jpoMemberOrder.getAddress());
		        pdExchangeOrder.setPostalcode(jpoMemberOrder.getPostalcode());
		        String phone  = jpoMemberOrder.getPhone();
		        if(!StringUtil.isEmpty(phone)){
		        	pdExchangeOrder.setPhone(jpoMemberOrder.getPhone());
		        }else{
			        pdExchangeOrder.setPhone(jpoMemberOrder.getMobiletele());
		        }
		        pdExchangeOrder.setEmail(jpoMemberOrder.getEmail());
		        pdExchangeOrder.setMobiletele(jpoMemberOrder.getMobiletele());
		        pdExchangeOrder.setCreateTime(new Date());
		        pdExchangeOrder.setCreateUsrCode(sessionLogin.getUserCode());
		        pdExchangeOrder.setOrderFlag(-1);
		        pdExchangeOrder.setStockFlag("0");
		       
		        
		        Set<PdExchangeOrderBack> pdExchangeOrderBacks = new HashSet<PdExchangeOrderBack>();
		        //换货从对象赋值pdExchangeOrderBack----begin
		        Set jpoMemberOrderDetails = jpoMemberOrder.getJpoMemberOrderList();
				Iterator iterator = jpoMemberOrderDetails.iterator();
				while (iterator.hasNext()) {
					JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) iterator
							.next();
					PdExchangeOrderBack pdExchangeOrderBack = new PdExchangeOrderBack();
					pdExchangeOrderBack.setProductNo(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo());
					pdExchangeOrderBack.setPrice(jpoMemberOrderList.getPrice());
					pdExchangeOrderBack.setPv(jpoMemberOrderList.getPv());
					pdExchangeOrderBack.setQty(new Long(jpoMemberOrderList.getQty()));
					pdExchangeOrderBack.setOriginalQty(new Long(jpoMemberOrderList.getQty()));
					
					String erpProductNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getErpProductNo();
					if(StringUtils.isEmpty(erpProductNo)){
						erpProductNo = "erpProductNo";
					}
					pdExchangeOrderBack.setErpProductNo(erpProductNo);
					//允许换货的商品才在页面展示
					if (!"1".equals(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getChangeabledFlag())) {
						pdExchangeOrderBacks.add(pdExchangeOrderBack);
					}
				}
				//换货从对象赋值pdExchangeOrderBack----end
				pdExchangeOrder.setPdExchangeOrderBacks(pdExchangeOrderBacks);
				 //换货主对象赋值pdExchangeOrder--------end
				return pdExchangeOrder;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		PdExchangeOrder pdExchangeOrder = (PdExchangeOrder) command;
		boolean isNew = (pdExchangeOrder.getEoNo() == null);
		Locale locale = request.getLocale();
		String key = null;
		String strAction = request.getParameter("strAction");
		String view = "redirect:pdExchangeOrders.html?strAction=" + strAction;
		//modify gw 2015-05-29 换货单路程优化------begin
		//换货单-新增
		if("pdExchangeOrderAdd".equals(strAction)){
			String moId = request.getParameter("moId");
			JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(moId);
		    view = pdExchangeOrderAdd(pdExchangeOrder,sessionLogin,request,jpoMemberOrder);
			return new ModelAndView(view);
		}
		//换货单-下一步
		else if("pdExchangeOrderAddNext".equals(strAction)){
			        //pdExchangeOrderAddNext(sessionLogin,pdExchangeOrder,request);
			        editPdExchangeOrder(pdExchangeOrder, request, response);
					view = "redirect:pdExchangeOrders.html?strAction=editPdExchangeOrder";
					return new ModelAndView(view);
		}
		//modify gw 2015-05-29 换货单路程优化-----end
		//modify gw 2015-06-02 换货单流程优化注释掉没有使用的代码---begin
		/*else if ("addPdExchangeOrder".equals(strAction)) {
			String eoNo = addPdExchangeOrder(pdExchangeOrder, request, response);
			key = "pdExchangeOrder.add";
			view = "redirect:editExchangeOrder.html?strAction=editPdExchangeOrder&eoNo="
					+ eoNo;
		}*/
		//modify gw 2015-06-02 换货单流程优化注释掉没有使用的代码---end

		else if ("editPdExchangeOrder".equals(strAction)) {
			key = "pdExchangeOrder.update";
			if(pdExchangeOrder.getOrderFlag()==-1){
				editPdExchangeOrder(pdExchangeOrder, request, response);
			}else{
				key="pdExchangeOrder.submited";
			}
		}else if ("submitPdExchangeOrder".equals(strAction)) {
			//判断有没有要换走的商品
			Set<PdExchangeOrderDetail> pdExchangeOrderDetails = pdExchangeOrder.getPdExchangeOrderDetails();
			if(null!=pdExchangeOrderDetails){
				if(pdExchangeOrderDetails.size()>0){
					pdExchangeOrder.setOrderFlag(0);
					editPdExchangeOrder(pdExchangeOrder, request, response);
					key = "pd.hasSubmited";
					view = "redirect:pdExchangeOrders.html?strAction=editPdExchangeOrder";
					return new ModelAndView(view);
				}
			}
			MessageUtil.saveLocaleMessage(request, "请至少添加一种换货商品!");
			view="redirect:editPdExchangeOrder.html?strAction=editPdExchangeOrder&eoNo="+pdExchangeOrder.getEoNo();
			return new ModelAndView(view);
		}else if ("checkPdExchangeOrder".equals(strAction)) {
			view = "redirect:pdExchangeOrders.html?strAction=checkPdExchangeOrder";
			key = "pdExchangeOrder.checked";
			if(pdExchangeOrder.getOrderFlag()==0){
				pdExchangeOrder.setCheckTime(new Date());
				pdExchangeOrder.setCheckUsrCode(sessionLogin.getUserCode());
				pdExchangeOrder.setOrderFlag(1);
				pdExchangeOrderManager.savePdExchangeOrder(pdExchangeOrder);
			}else{
				key="pdExchangeOrder.submited";
			}
		}else if ("confirmPdExchangeOrder".equals(strAction)) {
			key = "pdExchangeOrder.confirmed";
			if(pdExchangeOrder.getOrderFlag()==1){
				
				//modify by fu 2016-04-08 自助换货单是不允许确认的----begin
				//自助换货单对应的订单都是完全没有发货的，所以自助换货单的换货退回商品不需要入库；
				//另外自助换货单对应的发货单是通过存储过程生成的；
				//自助换货单通过存储过程生成发货单的同时会将自助换货单状态修改为确认状态;
				String selfReplacement = pdExchangeOrder.getSelfReplacement();
				if((!StringUtil.isEmpty(selfReplacement))&&("Y".equals(selfReplacement))){
					this.saveMessage(request,"自助换货单的确认由系统完成，不需要手动确认！");
					return new ModelAndView(view);
				}
				//modify by fu 2016-04-08 自助换货单是不允许确认的----end

				confirmPdExchangeOrder(pdExchangeOrder, request, response);
			}else{
				key="pdExchangeOrder.submited";
			}
			
		}else if ("tonewPdExchangeOrder".equals(strAction)) {
			//view = "redirect:pdExchangeOrders.html?strAction=editPdExchangeOrder";
			view = "redirect:pdExchangeOrders.html?strAction=checkPdExchangeOrder";
			key = "pdExchangeOrder.confirmed";
			
			//modify by fu 2016-04-07已审核的换货单转新单:需要支付的自助换货单在已支付情况下不能转新单;不需要支付的自助换货单在已生成发货单的情况下不能转新单---begin
			//已审核的换货单
			String selfReplacement = pdExchangeOrder.getSelfReplacement();
			if(pdExchangeOrder.getOrderFlag()==1){
				//自助换货单
				if((!StringUtil.isEmpty(selfReplacement))&&("Y".equals(selfReplacement))){
					String needPay =  pdExchangeOrder.getNeedPay();
					//需要支付的自助换货单
					if((!StringUtil.isEmpty(needPay))&&("Y".equals(needPay))){
						String isPay = pdExchangeOrder.getIsPay();
						if((!StringUtil.isEmpty(isPay))&&("Y".equals(isPay))){
							this.saveMessage(request,"已支付的自助换货单不能转新单！");
							return new ModelAndView(view);
						}
					}
					//不需要支付的自助换货单
					else{
						String whetherPd = pdExchangeOrder.getWhetherPd();
						//whetherPd生成发货单的标志:1生成发货单，2生成发货单异常，空表示未生成发货单,3生成发货单中，其他数值均表示生成发货单异常 
						//whetherPd空值表示未生成发货单，其他值都表示在进行或已经进行过生成发货单的动作,whetherPd有值的话就不能取消审核转新单
						if(!StringUtil.isEmpty(whetherPd)){
							this.saveMessage(request,"已生成发货单的自助换货单不能转新单！");
							return new ModelAndView(view);
						}
					}
				}
			}
			//modify by fu 2016-04-07已审核的换货单转新单:需要支付的自助换货单在已支付情况下不能转新单;不需要支付的自助换货单在已生成发货单的情况下不能转新单---end

			if(pdExchangeOrder.getOrderFlag()==0 ||  pdExchangeOrder.getOrderFlag()==1){
				pdExchangeOrder.setOkTime(new Date());
				pdExchangeOrder.setOkUsrCode(sessionLogin.getUserCode());
				pdExchangeOrder.setOrderFlag(-1);
				//modify by fu 2016-04-08 自助换货单
				if((!StringUtil.isEmpty(selfReplacement))&&("Y".equals(selfReplacement))){
					pdExchangeOrder.setSelfCheckStatus("N");//自助换货单审核状态：Y审核通过，N审核不通过，空表示初始化制单的时候不用赋值
				}
				//modify by fu 2016-04-08自助换货单
				pdExchangeOrderManager.savePdExchangeOrder(pdExchangeOrder);
			}else{
				key="pdExchangeOrder.submited";
			}
		}else if ("deletePdExchangeOrder".equals(strAction)) {
			view = "redirect:pdExchangeOrders.html?strAction=editPdExchangeOrder";
			key = "pdExchangeOrder.delete";
			if(pdExchangeOrder.getOrderFlag()==-1){
				pdExchangeOrderManager.removePdExchangeOrder(pdExchangeOrder.getEoNo());
				if(!StringUtils.isEmpty(pdExchangeOrder.getOrderNo())){
					JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(pdExchangeOrder.getOrderNo());
					if(null != jpoMemberOrder){
						jpoMemberOrder.setExchangeFlag("0");//设置订单是否换货标志位为换货取消
						jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder);
					}
				}
				
			}else{
				key="pdExchangeOrder.submited";
			}
		}

		return new ModelAndView(view);
	}
	
	/**
	 * 换货单-新增
	 * @author gw 2015-06-02
	 * @param pdExchangeOrder
	 * @param sessionLogin
	 * @param request
	 * @return
	 */
    private String  pdExchangeOrderAdd(PdExchangeOrder pdExchangeOrder,SysUser sessionLogin, HttpServletRequest request,JpoMemberOrder jpoMemberOrder) {
    	
    	/*Set jpoMemberOrderDetailDBBA = jpoMemberOrderDBA.getJpoMemberOrderList();
		Iterator iteratordDBA = jpoMemberOrderDetailDBBA.iterator();
		while (iteratordDBA.hasNext()) {
			JpoMemberOrderList jpoMemberOrderListd = (JpoMemberOrderList) iteratordDBA.next();
    	    String producntN = jpoMemberOrderListd.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
		    System.out.println(producntN);
		}*/
    	String view = "redirect:pdExchangeOrders.html?strAction=editPdExchangeOrder";
        try{	
	    	pdExchangeOrder.setCustomer(jpoMemberOrder.getSysUser());
			pdExchangeOrder.setCreateUsrCode(sessionLogin.getUserCode());
			pdExchangeOrder.setCompanyCode(sessionLogin.getCompanyCode());
			pdExchangeOrder = pdExchangeOrderManager.pdExchangeOrderAdd(pdExchangeOrder);
			String moId = request.getParameter("moId");
			
			if(!StringUtils.isEmpty(pdExchangeOrder.getOrderNo())){
				if(null != jpoMemberOrder){
					jpoMemberOrder.setExchangeFlag("1");//设置订单是否换货标志位为已换货
					jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder);
				}
				
				Set jpoMemberOrderDetails = jpoMemberOrder.getJpoMemberOrderList();
				Iterator iterator = jpoMemberOrderDetails.iterator();
				while (iterator.hasNext()) {
					JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) iterator.next();
					String a = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo()+"-"+jpoMemberOrderList.getPrice()+"-bqty";
					String b = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo()+"-"+jpoMemberOrderList.getPrice()+ "-bqtyOne";
					    //允许换货的商品
					if (!"1".equals(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getChangeabledFlag())) {
						String combinationStr = request.getParameter(a);
						
					    if(StringUtil.isEmpty(combinationStr)){
							continue;
						}
						String[] combinationStrSz = combinationStr.split(",");
						String bqtyOne = request.getParameter(b);
						PdExchangeOrderBack pdExchangeOrderBack = new PdExchangeOrderBack();
						pdExchangeOrderBack.setEoNo(pdExchangeOrder.getEoNo());
						pdExchangeOrderBack.setProductNo(combinationStrSz[0]);
						pdExchangeOrderBack.setPrice(new BigDecimal(combinationStrSz[1]));
						pdExchangeOrderBack.setPv(new BigDecimal(combinationStrSz[2]));
						pdExchangeOrderBack.setQty(Long.parseLong(bqtyOne));//换货数量
						pdExchangeOrderBack.setOriginalQty(Long.parseLong(combinationStrSz[4]));
						pdExchangeOrderBack.setErpProductNo(combinationStrSz[5]);
						pdExchangeOrderBackManager.savePdExchangeOrderBack(pdExchangeOrderBack);
					}
				}
			}
			//window.location="editPdExchangeOrder.html?strAction=${param.strAction}&eoNo="+eoNo;
			//editPdExchangeOrder
	        //view="redirect:editPdExchangeOrder.html?strAction=editPdExchangeOrder&eoNo="+pdExchangeOrder.getEoNo();
			view="redirect:editPdExchangeOrder.html?strAction=pdExchangeOrderAddNext&eoNo="+pdExchangeOrder.getEoNo();
        }catch(Exception e){
        	e.printStackTrace();
			log.info("在类PdExchangeOrderFormController的方法pdExchangeOrderAdd(换货单-新增)中报错:"+e);
			view = "redirect:pdExchangeOrders.html?strAction=editPdExchangeOrder";
		}finally{
             return view;
		}
	}

	

	private void confirmPdExchangeOrder(PdExchangeOrder pdExchangeOrder,
			HttpServletRequest request, HttpServletResponse response) {
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdExchangeOrder.setOkTime(new Date());
		pdExchangeOrder.setOkUsrCode(sessionLogin.getUserCode());
		pdExchangeOrder.setOrderFlag(2);
		
		pdExchangeOrderManager.conformPdExchangeOrder(pdExchangeOrder);
	}

	private void editPdExchangeOrder(PdExchangeOrder pdExchangeOrder,
			HttpServletRequest request, HttpServletResponse response) {
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdExchangeOrder.setEditTime(new Date());
		pdExchangeOrder.setEditUsrCode(sessionLogin.getUserCode());
		// 退回
		Set backOrders = pdExchangeOrder.getPdExchangeOrderBacks();
		Iterator iterator = backOrders.iterator();
		while (iterator.hasNext()) {
			PdExchangeOrderBack pdExchangeOrderBack = (PdExchangeOrderBack) iterator
					.next();
			String qty = request.getParameter(pdExchangeOrderBack.getUniNo()
					.toString()
					+ "-bqty");
			if (StringUtils.isNumeric(qty)) {
				pdExchangeOrderBack.setQty(new Long(qty));
			}
			pdExchangeOrderBackManager
					.savePdExchangeOrderBack(pdExchangeOrderBack);
		}
		// 换走
		Set detailOrders = pdExchangeOrder.getPdExchangeOrderDetails();
		Iterator diterator = detailOrders.iterator();
		while (diterator.hasNext()) {
			PdExchangeOrderDetail pdExchangeOrderDetail = (PdExchangeOrderDetail) diterator
					.next();
			String dqty = request.getParameter(pdExchangeOrderDetail.getUniNo()
					.toString()
					+ "-dqtyT");
			if(!StringUtil.isEmpty(dqty)){
				if (StringUtils.isNumeric(dqty)) {
					pdExchangeOrderDetail.setQty(new Long(dqty));
				}
				pdExchangeOrderDetailManager
						.savePdExchangeOrderDetail(pdExchangeOrderDetail);
			}
		}
		pdExchangeOrderManager.savePdExchangeOrder(pdExchangeOrder);
	}

	//modify gw 2015-06-02 换货单流程优化注释掉没有使用的代码---begin
	/*private String addPdExchangeOrder(PdExchangeOrder pdExchangeOrder,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		
		pdExchangeOrder.setEoNo(sysIdManager.buildIdStr("pd_eono"));
		pdExchangeOrder.setCreateTime(new Date());
		pdExchangeOrder.setCreateUsrCode(sessionLogin.getUserCode());
		pdExchangeOrder.setOrderFlag(-1);
		pdExchangeOrder.setStockFlag("0");

		pdExchangeOrderManager.addPdExchangeOrder(pdExchangeOrder);
		return pdExchangeOrder.getEoNo();
	}*/
	//modify gw 2015-06-02 换货单流程优化注释掉没有使用的代码---end

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		super.initBinder(request, binder);
	}
}
