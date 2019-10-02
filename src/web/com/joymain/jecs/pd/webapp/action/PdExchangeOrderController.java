package com.joymain.jecs.pd.webapp.action;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pd.model.PdExchangeOrder;
import com.joymain.jecs.pd.model.PdExchangeOrderBack;
import com.joymain.jecs.pd.model.PdExchangeOrderDetail;
import com.joymain.jecs.pd.service.PdExchangeOrderBackManager;
import com.joymain.jecs.pd.service.PdExchangeOrderDetailManager;
import com.joymain.jecs.pd.service.PdExchangeOrderManager;
import com.joymain.jecs.pd.service.PdWarehouseUserManager;
import com.joymain.jecs.pm.model.JpmProduct;
import com.joymain.jecs.pm.service.JpmProductManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;
@SuppressWarnings({"rawtypes","unused"})
public class PdExchangeOrderController extends BaseController implements
		Controller {
	private final Log log = LogFactory.getLog(PdExchangeOrderController.class);
	private PdExchangeOrderManager pdExchangeOrderManager = null;
	private JpoMemberOrderManager jpoMemberOrderManager = null;
	private PdWarehouseUserManager pdWarehouseUserManager = null;
	private PdExchangeOrderBackManager pdExchangeOrderBackManager =null;
	private PdExchangeOrderDetailManager pdExchangeOrderDetailManager = null;
	private JpmProductManager jpmProductManager = null;
	private SysIdManager sysIdManager = null;
	
	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}

	public void setPdWarehouseUserManager(
			PdWarehouseUserManager pdWarehouseUserManager) {
		this.pdWarehouseUserManager = pdWarehouseUserManager;
	}

	public void setJpoMemberOrderManager(
			JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public void setPdExchangeOrderManager(
			PdExchangeOrderManager pdExchangeOrderManager) {
		this.pdExchangeOrderManager = pdExchangeOrderManager;
	}

	public void setPdExchangeOrderBackManager(
			PdExchangeOrderBackManager pdExchangeOrderBackManager) {
		this.pdExchangeOrderBackManager = pdExchangeOrderBackManager;
	}

	public void setPdExchangeOrderDetailManager(
			PdExchangeOrderDetailManager pdExchangeOrderDetailManager) {
		this.pdExchangeOrderDetailManager = pdExchangeOrderDetailManager;
	}

	public void setJpmProductManager(JpmProductManager jpmProductManager) {
		this.jpmProductManager = jpmProductManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		String strAction = request.getParameter("strAction");
		String view = "pd/pdExchangeOrderList";
		PdExchangeOrder pdExchangeOrder = new PdExchangeOrder();
		//Modify By WuCF 20140603 对于root账号不用限制仓库权限
		String warehouseNoStr = "";
		if(!"root".equals(sessionLogin.getUserCode())){
			warehouseNoStr = pdWarehouseUserManager.getStringWarehouseByUser(sessionLogin.getUserCode());
		}
		// populate object with request parameters
		// BeanUtils.populate(pdExchangeOrder, request.getParameterMap());

		// List pdExchangeOrders =
		// pdExchangeOrderManager.getPdExchangeOrders(pdExchangeOrder);
		/**** auto pagination ***/
		CommonRecord crm = initCommonRecord(request);
		//新增仓库管理权限控制
    	crm.setValue("defaultWarehouse", warehouseNoStr);
		//Modify By WuCF 20131114 仓库权限管理修改，查询时过滤
		crm.setValue("userCodeT", sessionLogin.getUserCode());
		
			if (!"AA".equalsIgnoreCase(sessionLogin.getCompanyCode())) {
				crm.setValue("companyCode", sessionLogin.getCompanyCode());
			}
		// CommonRecord crm=RequestUtil.toCommonRecord(request);
		if ("newExchangeOrder".equals(strAction)) {
			view = "pd/pdShowMemberOrders";

			List orders = jpoMemberOrderManager
					.getChageableJpoMemberOrders(request
							.getParameter("customCode"));
			request.setAttribute("memberOrders", orders);
			return new ModelAndView(view);
		} else if ("addPdExchangeOrder".equals(strAction)) {
			//modify gw 2015-05-27 优化换货单流程的时候注释掉的
			/*String moId = request.getParameter("moId");
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
			pdExchangeOrder.setEoNo(sysIdManager.buildIdStr("pd_eono"));
			pdExchangeOrder.setCreateTime(new Date());
			pdExchangeOrder.setCreateUsrCode(sessionLogin.getUserCode());
			pdExchangeOrder.setOrderFlag(-1);
			pdExchangeOrder.setStockFlag("0");

			pdExchangeOrderManager.addPdExchangeOrder(pdExchangeOrder);
			view = "redirect:editPdExchangeOrder.html?strAction=editPdExchangeOrder&eoNo="
				+ pdExchangeOrder.getEoNo();*/
			//modify gw 2015-05-27 优化换货单流程的时候添加的
			String moId = request.getParameter("moId");
			view = "redirect:/editPdExchangeOrder.html?strAction=addPdExchangeOrder&moId="+ moId;
			
			return new ModelAndView(view);

		} else if ("checkPdExchangeOrder".equals(strAction)) {
//			crm.setValue("checkUsrCode", sessionLogin.getUserCode());
//			crm.setValue("hasCheckUsrCodeBlank", "1");
			crm.setValue("orderFlagDefault", "0,1,2");

		} else if ("confirmPdExchangeOrder".equals(strAction)) {

			crm.setValue("okUsrNo", sessionLogin.getUserCode());
			crm.setValue("hasCheckUsrCodeBlank", "1");
			crm.setValue("orderFlagDefault", "1,2");
		}else if ("statisticPdExchangeOrder".equals(strAction)) {

//			crm.setValue("okUsrNo", sessionLogin.getUserCode());
//			crm.setValue("hasCheckUsrCodeBlank", "1");
			crm.setValue("orderFlagDefault", "1,2");
			//List pdExchangeOrderTotal = pdExchangeOrderManager.getTotalPdExchangeOrder(crm);
			List pdExchangeOrderTotal = pdExchangeOrderManager.getTotalPdExchangeOrder2(crm);
        	request.setAttribute("pdExchangeOrderTotal", pdExchangeOrderTotal);
		}else if("pdExchangeOrderReport".equals(strAction)){
		
			crm.setValue("orderFlagDefault", "1,2");
			List list = pdExchangeOrderManager.getPdExchangeOrdersByCrm(crm, null);
			//生成xls文件
//			String excelTitle = "换货单信息报表";
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=Generate Report PdExchangeOrder"
					+ "_" + new Date() + ".xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			ExcelUtil excelUtil = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet wsheet = wwb.createSheet("Sheet1", 0);
			
			DecimalFormat df = new DecimalFormat("0.00");//保留两位小数
		
			//Excel表格的标题
			//第一行
			excelUtil.addString(wsheet, 0, 0, "换货单制单日期");
			excelUtil.mergeCells(wsheet, 0, 0, 0, 1);
			excelUtil.addString(wsheet, 1, 0, "原订单编号");
			excelUtil.mergeCells(wsheet, 1, 0, 1, 1);
			excelUtil.addString(wsheet, 2, 0, "原订单产品");
			excelUtil.mergeCells(wsheet, 2, 0, 7, 0);
			excelUtil.addString(wsheet, 8, 0, "换货单编号");
			excelUtil.mergeCells(wsheet, 8, 0, 8, 1);
			excelUtil.addString(wsheet, 9, 0, "换货单产品");
			excelUtil.mergeCells(wsheet, 9, 0, 13, 0);
			excelUtil.addString(wsheet, 14, 0, "需补差价");
			excelUtil.mergeCells(wsheet, 14, 0, 14, 1);
			
			//第二行
			excelUtil.addString(wsheet, 2, 1, "原订单属性");
			excelUtil.addString(wsheet, 3, 1, "商品编号");
			excelUtil.addString(wsheet, 4, 1, "商品名称");
			excelUtil.addString(wsheet, 5, 1, "数量");
			excelUtil.addString(wsheet, 6, 1, "金额");
			excelUtil.addString(wsheet, 7, 1, "PV");
			
			excelUtil.addString(wsheet, 9, 1, "商品编号");
			excelUtil.addString(wsheet, 10, 1, "商品名称");
			excelUtil.addString(wsheet, 11, 1, "数量");
			excelUtil.addString(wsheet, 12, 1, "总价");
			excelUtil.addString(wsheet, 13, 1, "PV");
			
			//换货单列表不为空
			if(list != null && list.size() > 0){
				int size_back = 0;
				int size_detail = 0;
				BigDecimal money_back = new BigDecimal(0);
				BigDecimal pv_back = new BigDecimal(0);
				BigDecimal money_detail = new BigDecimal(0);
				BigDecimal pv_detail = new BigDecimal(0);
				BigDecimal spread_need_to_pay = new BigDecimal(0);
				int start_row = 2;
				int end_row = 0;
				
				for (int i = 0; i < list.size(); i++) {
					size_back = 0;
					size_detail = 0;
					money_back = new BigDecimal(0);
					pv_back = new BigDecimal(0);
					money_detail = new BigDecimal(0);
					pv_detail = new BigDecimal(0);
					spread_need_to_pay = new BigDecimal(0);
					
					//得到每一张换货单
					PdExchangeOrder pdExchangeOrder_tem = (PdExchangeOrder) list.get(i);
					//得到每一张换货单的原订单产品
					Set<PdExchangeOrderBack> pdExchangeOrderBacks = pdExchangeOrder_tem.getPdExchangeOrderBacks();
					//得到每一张换货单的换货单产品
					Set<PdExchangeOrderDetail> pdExchangeOrderDetails = pdExchangeOrder_tem.getPdExchangeOrderDetails();
					
					if(pdExchangeOrderBacks != null && pdExchangeOrderBacks.size() >0 
							&& pdExchangeOrderDetails != null && pdExchangeOrderDetails.size() >0){
						size_back = pdExchangeOrderBacks.size();
						size_detail = pdExchangeOrderDetails.size();
						
//						end_row = (size_back >= size_detail) ? (start_row + size_back -1):(start_row + size_detail -1);
						//原订单商品数量多于换货单的商品数量
						if(size_back>=size_detail){
							//换货单制单日期
							end_row = start_row + size_back - 1;
							excelUtil.addString(wsheet, 0, start_row, pdExchangeOrder_tem.getCreateTime().toString());
							excelUtil.mergeCells(wsheet, 0, start_row, 0, end_row);
							
							//原订单编号
							excelUtil.addString(wsheet, 1, start_row, pdExchangeOrder_tem.getOrderNo());
							excelUtil.mergeCells(wsheet, 1, start_row, 1, end_row);
							
							//原订单产品
							//原订单属性
							JpoMemberOrder jmo = getJpoMemberOrder(pdExchangeOrder_tem);
							String orderType = "";
							String orderTypeDesc = "";
							if(null!=jmo){
								orderType = jmo.getOrderType();
								orderTypeDesc = pdExchangeOrderManager.getOrderTypeDescByOrderType(orderType);
								excelUtil.addString(wsheet, 2, start_row, orderTypeDesc);
							
							
								//原订单的总金额和总PV
								money_back = jmo.getAmount();
								pv_back = jmo.getPvAmt();
								
							}
							
							excelUtil.mergeCells(wsheet, 2, start_row, 2, end_row);
							
							int j = start_row;
							//double sum_back = 0.0;
							for (PdExchangeOrderBack pdExchangeOrderBack : pdExchangeOrderBacks) {
								
								//商品编号
								excelUtil.addString(wsheet, 3, j, pdExchangeOrderBack.getProductNo());
								//商品名称
								excelUtil.addString(wsheet, 4, j, getJpmProduct(pdExchangeOrderBack).getProductName());
								//数量
								excelUtil.addString(wsheet, 5, j, pdExchangeOrderBack.getQty().toString());
								//PV
								excelUtil.addString(wsheet, 7, j, pdExchangeOrderBack.getPv().toString());
								
								j++;
								/*
								sum_back = (pdExchangeOrderBack.getQty().intValue()) * (pdExchangeOrderBack.getPrice().doubleValue());
								money_back += sum_back;	*/
								
							}
							//金额
							excelUtil.addString(wsheet, 6, start_row, money_back.toString());
							excelUtil.mergeCells(wsheet, 6, start_row, 6, end_row);
							
							//换货单编号
							excelUtil.addString(wsheet, 8, start_row, pdExchangeOrder_tem.getEoNo());
							excelUtil.mergeCells(wsheet, 8, start_row, 8, end_row);
							//换货单产品
							int k = start_row;
							//BigDecimal sum_detail = new BigDecimal(0);
							for (PdExchangeOrderDetail pdExchangeOrderDetail : pdExchangeOrderDetails) {
								
								//商品编号
								excelUtil.addString(wsheet, 9, k, pdExchangeOrderDetail.getProductNo());
								//商品名称
								excelUtil.addString(wsheet, 10, k, getJpmProduct(pdExchangeOrderDetail).getProductName());
								//数量
								excelUtil.addString(wsheet, 11, k, pdExchangeOrderDetail.getQty().toString());
								//PV
								excelUtil.addString(wsheet, 13, k, pdExchangeOrderDetail.getPv().toString());
								k++;
								//sum_detail = new BigDecimal(pdExchangeOrderDetail.getQty()).multiply(pdExchangeOrderDetail.getPrice());
								//(pdExchangeOrderDetail.getQty().intValue()) * (pdExchangeOrderDetail.getPrice().doubleValue());
								money_detail = money_detail.add(new BigDecimal(pdExchangeOrderDetail.getQty()).multiply(pdExchangeOrderDetail.getPrice()));
								//money_detail += sum_detail;
							}
							//总价
							excelUtil.addString(wsheet, 12, start_row, money_detail.toString());
							excelUtil.mergeCells(wsheet, 12, start_row, 12, end_row);
							
							//需补差价
							//spread_need_to_pay = money_detail - money_back;
							spread_need_to_pay = money_detail.subtract(money_back);
							excelUtil.addString(wsheet, 14, start_row, spread_need_to_pay.toString());
							excelUtil.mergeCells(wsheet, 14, start_row, 14, end_row);
						
							//写入多于的空行 多于的行数=size_back - size_detail
							int size_blank = size_back - size_detail;
							int kk = end_row;
							for (int m = 0; m < size_blank; m++) {
								excelUtil.addString(wsheet, 9, kk , "");
								excelUtil.addString(wsheet, 10, kk, "");
								excelUtil.addString(wsheet, 11, kk, "");
								excelUtil.addString(wsheet, 13, kk, "");
								kk--;
							}
							start_row = end_row + 1;
						}else{
							//换货单制单日期
							end_row = start_row + size_detail - 1;
							excelUtil.addString(wsheet, 0, start_row, pdExchangeOrder_tem.getCreateTime().toString());
							excelUtil.mergeCells(wsheet, 0, start_row, 0, end_row);
							
							//原订单编号
							excelUtil.addString(wsheet, 1, start_row, pdExchangeOrder_tem.getOrderNo());
							excelUtil.mergeCells(wsheet, 1, start_row, 1, end_row);
							
							//原订单产品
							//原订单属性
							//excelUtil.addString(wsheet, 2, start_row, getJpoMemberOrder(pdExchangeOrder_tem).getOrderType());
							JpoMemberOrder jmo = getJpoMemberOrder(pdExchangeOrder_tem);
							String orderType = "";
							String orderTypeDesc = "";
							if(null!=jmo){
								//orderType显示为orderType的描述，例如，orderTye=4，是会员重消单
								//excelUtil.addString(wsheet, 2, start_row, jmo.getOrderType());
								orderType = jmo.getOrderType();
								orderTypeDesc = pdExchangeOrderManager.getOrderTypeDescByOrderType(orderType);
								excelUtil.addString(wsheet, 2, start_row, orderTypeDesc);
								
								//原定单的总金额和总PV
								money_back = jmo.getAmount();
								pv_back = jmo.getPvAmt();
								
							}
							excelUtil.mergeCells(wsheet, 2, start_row, 2, end_row);
							
							int j = start_row;
							//double sum_back = 0.0;
							for (PdExchangeOrderBack pdExchangeOrderBack : pdExchangeOrderBacks) {
								
								//商品编号
								excelUtil.addString(wsheet, 3, j, pdExchangeOrderBack.getProductNo());
								//商品名称
								excelUtil.addString(wsheet, 4, j, getJpmProduct(pdExchangeOrderBack).getProductName());
								//数量
								excelUtil.addString(wsheet, 5, j, pdExchangeOrderBack.getQty().toString());
								//PV
								excelUtil.addString(wsheet, 7, j, pdExchangeOrderBack.getPv().toString());
								
								j++;
								/*
								sum_back = (pdExchangeOrderBack.getQty().intValue()) * (pdExchangeOrderBack.getPrice().doubleValue());
								money_back += sum_back;	*/
								
							}
							//金额
							excelUtil.addString(wsheet, 6, start_row, money_back.toString());
							excelUtil.mergeCells(wsheet, 6, start_row, 6, end_row);
							
							//换货单编号
							excelUtil.addString(wsheet, 8, start_row, pdExchangeOrder_tem.getEoNo());
							excelUtil.mergeCells(wsheet, 8, start_row, 8, end_row);
							//换货单产品
							int k = start_row;
							//BigDecimal sum_detail = new BigDecimal(0);
							for (PdExchangeOrderDetail pdExchangeOrderDetail : pdExchangeOrderDetails) {
								
								//商品编号
								excelUtil.addString(wsheet, 9, k, pdExchangeOrderDetail.getProductNo());
								//商品名称
								excelUtil.addString(wsheet, 10, k, getJpmProduct(pdExchangeOrderDetail).getProductName());
								//数量
								excelUtil.addString(wsheet, 11, k, pdExchangeOrderDetail.getQty().toString());
								//PV
								excelUtil.addString(wsheet, 13, k, pdExchangeOrderDetail.getPv().toString());
								k++;
								//sum_detail = (pdExchangeOrderDetail.getQty().intValue()) * (pdExchangeOrderDetail.getPrice().doubleValue());
								//sum_detail = new BigDecimal(pdExchangeOrderDetail.getQty()).multiply(pdExchangeOrderDetail.getPrice());
								//money_detail += sum_detail;
								money_detail = money_detail.add(new BigDecimal(pdExchangeOrderDetail.getQty()).multiply(pdExchangeOrderDetail.getPrice()));
								
							}
							//总价
							excelUtil.addString(wsheet, 12, start_row, money_detail.toString());
							excelUtil.mergeCells(wsheet, 12, start_row, 12, end_row);
							
							//需补差价
							//spread_need_to_pay = money_detail - money_back;
							spread_need_to_pay = money_detail.subtract(money_back);
							excelUtil.addString(wsheet, 14, start_row, spread_need_to_pay.toString());
							excelUtil.mergeCells(wsheet, 14, start_row, 14, end_row);
						
							//写入多于的空行 多于的行数=size_detail - size_back
							int size_blank = size_detail - size_back;
							int kk = end_row;
							for (int m = 0; m < size_blank; m++) {
								excelUtil.addString(wsheet, 3, kk , "");
								excelUtil.addString(wsheet, 4, kk, "");
								excelUtil.addString(wsheet, 5, kk, "");
								excelUtil.addString(wsheet, 7, kk, "");
								kk--;
							}
							start_row = end_row +1 ;
						}
					}
				//start_row = end_row + 1;
				}
			}
			
			
			excelUtil.writeExcel(wwb);
			excelUtil.closeWritableWorkbook(wwb);
			os.close();
			
			return null;
		}
		Pager pager = new Pager("pdExchangeOrderListTable", request, 20);
		List pdExchangeOrders = pdExchangeOrderManager
				.getPdExchangeOrdersByCrm(crm, pager);
		request.setAttribute("pdExchangeOrderListTable_totalRows", pager
				.getTotalObjects());
		/*****/

		return new ModelAndView("pd/pdExchangeOrderList",
				Constants.PDEXCHANGEORDER_LIST, pdExchangeOrders);
	}
	
	/**
	 * 根据PdExchangeOrder对象得到JpoMemberOrder对象,用于得到报表中的“原订单属性”
	 * @param pdExchangeOrder
	 * @return
	 */
	private JpoMemberOrder getJpoMemberOrder(PdExchangeOrder pdExchangeOrder){
		return jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(pdExchangeOrder.getOrderNo());
	}
	/**
	 * 
	 * 根据PdExchangeOrder对象得到原订单产品
	 * @param pdExchangeOrder
	 * @return
	 */
	private Set<PdExchangeOrderBack> getJpmProductBack(PdExchangeOrder pdExchangeOrder){
		return pdExchangeOrder.getPdExchangeOrderBacks();
	}
	
	/**
	 * 根据PdExchangeOrder对象得到换货单产品
	 */
	private Set<PdExchangeOrderDetail> getJpmProductDetail(PdExchangeOrder pdExchangeOrder){
		return pdExchangeOrder.getPdExchangeOrderDetails();
	}
	
	/**
	 * 根据PdExchangeOrderDetail对象得到JpmProduct，用于得到“商品名称”
	 * @param pdExchangeOrderDetail
	 * @return
	 */
	private JpmProduct getJpmProduct(PdExchangeOrderDetail pdExchangeOrderDetail){
		return jpmProductManager.getJpmProduct(pdExchangeOrderDetail.getProductNo());
	}
	
	/**
	 * 根据PdExchangeOrderBack对象得到JpmProduct，用于得到“商品名称”
	 * @param pdExchangeOrderDetail
	 * @return
	 */
	private JpmProduct getJpmProduct(PdExchangeOrderBack pdExchangeOrderBack){
		return jpmProductManager.getJpmProduct(pdExchangeOrderBack.getProductNo());
	}
	
	
}
