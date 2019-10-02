package com.joymain.jecs.po.webapp.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pm.model.JpmCombinationRelated;
import com.joymain.jecs.pm.service.JpmCombinationRelatedManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.model.MemberOrder;
import com.joymain.jecs.po.model.OrderCompareResult;
import com.joymain.jecs.po.model.OrderDetail;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.util.ListSort;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;

public class BatchCompareOrderController extends BaseFormController
{
    
    private JpoMemberOrderManager jpoMemberOrderManagerReport = null;
    private JpmCombinationRelatedManager jpmCombinationRelatedManager = null;

    public void setJpmCombinationRelatedManager(JpmCombinationRelatedManager jpmCombinationRelatedManager) {
        this.jpmCombinationRelatedManager = jpmCombinationRelatedManager;
    }
    public void setJpoMemberOrderManagerReport(
			JpoMemberOrderManager jpoMemberOrderManagerReport) {
		this.jpoMemberOrderManagerReport = jpoMemberOrderManagerReport;
	}
    
	public BatchCompareOrderController() {
		setCommandName("batchCompareOrder");
		setCommandClass(MemberOrder.class);
	}
    
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		MemberOrder memberOrder = new MemberOrder();

		return memberOrder;
	}
    
    public ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("importExcelFile");
            if (file == null || file.getInputStream() == null) {
                //文件读取错误
                errors.reject("bdBounsDeduct.importFile.failed");
                return showForm(request, response, errors);
            }
            // 设置上传路径
            //retrieve the file data
            InputStream stream = file.getInputStream();
            
            ExcelUtil eu = new ExcelUtil();
            //获取可读的工作表对象，定位到要读取的excel文件
            Workbook workbook = eu.getWorkbook(stream);
            //读取此文件的第一个工作表，工作表序号从0开始。
            Sheet sheet = workbook.getSheet(0);//会员信息与订单信息
            Sheet sheet1 = workbook.getSheet(1);//商品与数量信息
            
            List<String> messages = new ArrayList<String>();
            int errCount = 0;
            //从第2行开始读,第一行为标题
            messages.add(LocaleUtil.getLocalText("bdBounsDeduct.impot.skip.first"));
            
            JpoMemberOrder jpoMemberOrder = null;
            MemberOrder memberOrder = null;
            OrderDetail orderDetail = null;
            Set<JpoMemberOrderList> jpoMemberOrderList = null;//订单详情
            List<MemberOrder> memberOrderList = new ArrayList<MemberOrder>();
            List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
//            List<OrderDetail> tempOrderDetailList = new ArrayList<OrderDetail>();
            String productCodes = ""; //所有商品编码，判断是否有重复商品编码用
            String orderCodes = ""; //所有订单编码，判断是否有重复订单编码用
            
            for (int i = 1; i < sheet1.getRows(); i++) {
            	String productCode = eu.getContents(sheet1, 0, i);//商品编号
            	String productName = eu.getContents(sheet1, 1, i);//商品名称
            	String qty = eu.getContents(sheet1, 2, i);//商品数量
            	orderDetail = new OrderDetail();
            	if(!MeteorUtil.isBlank(qty)){
            		orderDetail.setQty(Integer.valueOf(qty));
            	}else{//数量为空则置为0
            		orderDetail.setQty(0);
            	}
            	orderDetail.setProductCode(productCode);
            	orderDetail.setProductName(productName);
            	
            	orderDetailList.add(orderDetail);
            	productCodes += productCode + ",";
            }
            
            //不为空且有重复商品
            if(MeteorUtil.isBlank(productCodes) || MeteorUtil.isRepeatByArray(productCodes.split(","))){
            	//给出提示 ，程序跳出页面  
                errors.reject("import.batchcompareorder.proc.error");//不能用中文。
                return showForm(request, response, errors);
            }
            
            for (int i = 1; i < sheet.getRows(); i++) {
            	
            	String memberUserCode = eu.getContents(sheet, 0, i);//会员编号
            	String memberUserName = eu.getContents(sheet, 1, i);//会员姓名
            	String oldLevel = eu.getContents(sheet, 2, i);//旧级别
            	String newLevel = eu.getContents(sheet, 3, i);//新级别
            	String orderCode = eu.getContents(sheet, 4, i);//订单编号
            	String orderType = eu.getContents(sheet, 5, i);//订单类型
            	String totalMoney = eu.getContents(sheet, 6, i);//总金额
            	String fundTotalMoney = eu.getContents(sheet, 7, i);//基金总金额
            	String credit = eu.getContents(sheet, 8, i);//抵扣积分
            	String totalPv = eu.getContents(sheet, 9, i);//会员编号
            	String orderPeriod = eu.getContents(sheet, 10, i);//订单期别
            	
            	if(MeteorUtil.isBlank(orderCode)){
            		orderCode = orderCode.trim();
            	}
            	
                String content = " ";
                String message = (i + 1) + ": ";
                //判断下订单是否存在
                if (StringUtils.isEmpty(orderCode)) {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "订单编号不能为空" + content + "</font>");
                    errCount++;
                    continue;
                }
                jpoMemberOrder = this.jpoMemberOrderManagerReport.getJpoMemberOrderByMemberOrderNo(orderCode);
                if (jpoMemberOrder == null) {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + orderCode+" 订单不存在" + content + "</font>");
                    errCount++;
                    continue;
                }
                
                memberOrder = new MemberOrder();
            	memberOrder.setMemberUserCode(memberUserCode);
            	memberOrder.setMemberUserName(memberUserName);
            	memberOrder.setOldLevel(oldLevel);
            	memberOrder.setNewLevel(newLevel);
            	memberOrder.setOrderCode(orderCode);
            	memberOrder.setOrderType(orderType);
            	memberOrder.setTotalMoney(totalMoney);
            	memberOrder.setTotalPv(totalPv);
            	memberOrder.setFundTotalMoney(fundTotalMoney);
            	memberOrder.setCredit(credit);
            	memberOrder.setOrderPeriod(orderPeriod);
            	setOrderShoworder(memberOrder);//根据订单类型，设置优先级，数字越小，越排在前面
            	memberOrderList.add(memberOrder);//需要按类别排序
            	orderCodes += orderCode + ",";
            }
            
            eu.closeWorkbook(workbook);
            
            if(errCount>0){
            	request.setAttribute("messages", messages);
                request.setAttribute("isFinished", true);
                request.setAttribute("errCount", errCount);
                return new ModelAndView(this.getFormView());
            }
            
          //为空或有重复订单编号
            if(MeteorUtil.isBlank(orderCodes) || MeteorUtil.isRepeatByArray(orderCodes.split(","))){
            	//给出提示 ，程序跳出页面  
                errors.reject("import.batchcompareorder.order.error");
                return showForm(request, response, errors);
            }
            
            ListSort<MemberOrder> listSort= new ListSort<MemberOrder>();
            listSort.Sort(memberOrderList, "getShowOrder", "");//正序排序
            
          //生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=orderProc.xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			eu = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			//在此创建的新excel文件创建一工作表 
			WritableSheet wsheet = wwb.createSheet("订单匹配结果", 0);
			
			eu.addString(wsheet, 0, 0, "原订单中产品信息");
			eu.addString(wsheet, 16, 0, "比对");
			
			//设置列头
			excelTopColumn(eu,wsheet,1,"会员编号,会员姓名,订单编号,订单类型,商品编号,商品名称,套餐代码,套餐名称,套餐价格,套餐PV,套餐数量,套餐内产品编号,套餐内产品名称,单价,单个PV值,订购数量,退货产品编码,退货产品名称,退货产品数量,实退金额,实退PV");
//			int i=0;
//			//列头  后续改为公共方法。
//			eu.addString(wsheet, i++, 1, "会员姓名");
//			eu.addString(wsheet, i++, 1, "订单编号");
//			eu.addString(wsheet, i++, 1, "订单类型");
//			eu.addString(wsheet, i++, 1, "商品编号");
//			eu.addString(wsheet, i++, 1, "商品名称");
//			eu.addString(wsheet, i++, 1, "套餐代码");
//			eu.addString(wsheet, i++, 1, "套餐名称");
//			eu.addString(wsheet, i++, 1, "套餐价格");
//			eu.addString(wsheet, i++, 1, "套餐PV");
//			eu.addString(wsheet, i++, 1, "套餐数量");
//			eu.addString(wsheet, i++, 1, "套餐内产品编号");
//			eu.addString(wsheet, i++, 1, "套餐内产品名称");
//			eu.addString(wsheet, i++, 1, "单价");
//			eu.addString(wsheet, i++, 1, "单个PV值");
//			eu.addString(wsheet, i++, 1, "订购数量");
//			eu.addString(wsheet, i++, 1, "退货产品编码");
//			eu.addString(wsheet, i++, 1, "退货产品名称");
//			eu.addString(wsheet, i++, 1, "退货产品数量");
//			eu.addString(wsheet, i++, 1, "实退金额");
//			eu.addString(wsheet, i++, 1, "实退PV");//工作表对象，列，行，值
			int s =0;//全局排序号，用来填充导出对象的showorder
			String procode = "";
			String proname = "";
			OrderCompareResult orderCompareResult = null;
			JpmCombinationRelated jpmCombinationRelated = null;
			List<OrderCompareResult> orderCompareResultList = new ArrayList<OrderCompareResult>();
			List<JpmCombinationRelated> jpmCombinationRelatedList = null;
			
			//先匹配套餐
			for (MemberOrder m : memberOrderList){
            	jpoMemberOrder = this.jpoMemberOrderManagerReport.getJpoMemberOrderByMemberOrderNo(m.getOrderCode());
            	
            	for (int ii=0;ii<orderDetailList.size();ii++){
            		OrderDetail o = orderDetailList.get(ii);
            		if(o.getQty()<=0){ //已匹配完
            			continue;
            		}
            		jpoMemberOrderList = jpoMemberOrder.getJpoMemberOrderList();
            		if(!jpoMemberOrderList.isEmpty()){
            			for (JpoMemberOrderList ol : jpoMemberOrderList){//非套餐不执行
            				if(!"1".equals(ol.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getCombineFlag())){//套餐路线
            					continue;
            				}
            				log.info("ol22223333.getMolId()==="+ol.getMolId());
            				orderCompareResult = new OrderCompareResult();
        					procode = ol.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
            				proname = ol.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductName();
            				log.info("proname1===="+proname+":"+procode);
            				//需要去wcf那边查询  根据套餐代码，期别，退货商品代码  查询，查得子商品代码对应上的进行比对，如查不到退出此次比对
            				jpmCombinationRelatedList = jpmCombinationRelatedManager.getJpmCombinationRelateds(procode, m.getOrderPeriod(), o.getProductCode());
            				if(MeteorUtil.isEmptyList(jpmCombinationRelatedList)){
            					continue;
            				}else{
            					jpmCombinationRelated = jpmCombinationRelatedList.get(0);
            				}
            				
            				if(o.getProductCode().equals(jpmCombinationRelated.getSubProductNo())){
            					
            					log.info("o.getProductCode()2===="+o.getProductCode()+":"+o.getProductName());
            					
            					orderCompareResult.setMemberUserCode(m.getMemberUserCode());
                				orderCompareResult.setMemberUserName(m.getMemberUserName());
                				orderCompareResult.setOrderCode(m.getOrderCode());
                				orderCompareResult.setOrderType(m.getOrderType());
                				orderCompareResult.setProductCode("");//商品编码
                				orderCompareResult.setProductName("");//商品名称
                				orderCompareResult.setProductPackageCode(procode);//套餐代码
                				orderCompareResult.setProductPackageName(proname);//套餐名称
                				orderCompareResult.setProductPackagePrice(String.valueOf(ol.getPrice()));//套餐价格
                				orderCompareResult.setProductPackagePv(String.valueOf(ol.getPv()));//套餐PV
                				orderCompareResult.setProductPackageQty(String.valueOf(ol.getQty()));//套餐数量
                				orderCompareResult.setPackageProductCode(o.getProductCode());//套餐内产品编号
                				orderCompareResult.setPackageProductName(o.getProductName());//套餐内产品名称
                				
                				orderCompareResult.setPackageProductPrice(String.valueOf(jpmCombinationRelated.getPrice()));//单价
                				orderCompareResult.setPackageProductPv(String.valueOf(jpmCombinationRelated.getPv()));//单个PV值
                				int dgqty = ol.getQty() * jpmCombinationRelated.getQty();
//                				订购数量需*套餐数量
                				orderCompareResult.setQty(String.valueOf(dgqty));//订购数量
                				orderCompareResult.setReturnProductCode(o.getProductCode());//退货产品编码
                				orderCompareResult.setReturnProductName(o.getProductName());//退货产品名称
                				s++;
                				orderCompareResult.setShoworder(Float.valueOf(s));//排序
            					if(dgqty>=o.getQty()){
            						orderCompareResult.setReturnQty(String.valueOf(o.getQty()));//退货产品数量
            						orderCompareResult.setReturnMoney(String.valueOf(jpmCombinationRelated.getPrice().multiply(new BigDecimal(o.getQty()))));//实退金额
            						orderCompareResult.setReturnPv(String.valueOf(jpmCombinationRelated.getPv().multiply(new BigDecimal(o.getQty()))));//实退PV
            						o.setQty(0);
            					}else{
            						orderCompareResult.setReturnQty(String.valueOf(dgqty));//退货产品数量
            						orderCompareResult.setReturnMoney(String.valueOf(jpmCombinationRelated.getPrice().multiply(new BigDecimal(dgqty))));//实退金额
            						orderCompareResult.setReturnPv(String.valueOf(jpmCombinationRelated.getPv().multiply(new BigDecimal(dgqty))));//实退PV
            						
            						//说明此商品没有匹配完  尚有剩余需要下个订单再次处理
//            						OrderDetail tempo = new OrderDetail();
//            						tempo.setProductCode(o.getProductCode());
//            						tempo.setProductName(o.getProductName());
//            						tempo.setQty(o.getQty()-ol.getQty());
//            						tempOrderDetailList.add(tempo);
            						o.setQty(o.getQty()-dgqty);
            					}
//            					orderDetailList.remove(ii);//说明此商品已匹配，删除此匹配商品元素
            					orderCompareResultList.add(orderCompareResult);//加入导出对象集合
            				}
            			}
            		}
            	}
//            	if(!MeteorUtil.isBlankByList(tempOrderDetailList)){
//            		for(OrderDetail to : tempOrderDetailList){
//            			orderDetailList.add(to);
//            		}
//            	}
//            	tempOrderDetailList = new ArrayList<OrderDetail>();
            }
			
			//再匹配单品
            for (MemberOrder m : memberOrderList){
            	jpoMemberOrder = this.jpoMemberOrderManagerReport.getJpoMemberOrderByMemberOrderNo(m.getOrderCode());
            	
            	for (int ii=0;ii<orderDetailList.size();ii++){
            		OrderDetail o = orderDetailList.get(ii);
            		if(o.getQty()<=0){
            			continue;
            		}
            		jpoMemberOrderList = jpoMemberOrder.getJpoMemberOrderList();
            		log.info("o.getProductCode()=="+o.getProductCode());
            		if(!jpoMemberOrderList.isEmpty()){
            			for (JpoMemberOrderList ol : jpoMemberOrderList){//单品的路线，  套餐直接不执行
            				if("1".equals(ol.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getCombineFlag())){//套餐路线
            					continue;
            				}
            				orderCompareResult = new OrderCompareResult();
        					procode = ol.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
            				proname = ol.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductName();
            				
            				if(o.getProductCode().equals(procode)){
            					
            					orderCompareResult.setMemberUserCode(m.getMemberUserCode());
                				orderCompareResult.setMemberUserName(m.getMemberUserName());
                				orderCompareResult.setOrderCode(m.getOrderCode());
                				orderCompareResult.setOrderType(m.getOrderType());
                				orderCompareResult.setProductCode(procode);
                				orderCompareResult.setProductName(proname);
                				orderCompareResult.setProductPackageCode("");//套餐代码
                				orderCompareResult.setProductPackageName("");//套餐名称
                				orderCompareResult.setProductPackagePrice("");//套餐价格
                				orderCompareResult.setProductPackagePv("");//套餐PV
                				orderCompareResult.setProductPackageQty("");//套餐数量
                				orderCompareResult.setPackageProductCode("");//套餐内产品编号
                				orderCompareResult.setPackageProductName("");//套餐内产品名称
                				orderCompareResult.setPackageProductPrice(String.valueOf(ol.getPrice()));//单价
                				orderCompareResult.setPackageProductPv(String.valueOf(ol.getPv()));//单个PV值
                				orderCompareResult.setQty(String.valueOf(ol.getQty()));//订购数量
                				orderCompareResult.setReturnProductCode(o.getProductCode());//退货产品编码
                				orderCompareResult.setReturnProductName(o.getProductName());//退货产品名称
                				s++;
                				orderCompareResult.setShoworder(Float.valueOf(s));//排序
            					if(ol.getQty()>=o.getQty()){
            						orderCompareResult.setReturnQty(String.valueOf(o.getQty()));//退货产品数量
            						orderCompareResult.setReturnMoney(String.valueOf(ol.getPrice().multiply(new BigDecimal(o.getQty()))));//实退金额
            						orderCompareResult.setReturnPv(String.valueOf(ol.getPv().multiply(new BigDecimal(o.getQty()))));//实退PV
            						o.setQty(0);
            					}else{
            						orderCompareResult.setReturnQty(String.valueOf(ol.getQty()));//退货产品数量
            						orderCompareResult.setReturnMoney(String.valueOf(ol.getPrice().multiply(new BigDecimal(ol.getQty()))));//实退金额
            						orderCompareResult.setReturnPv(String.valueOf(ol.getPv().multiply(new BigDecimal(ol.getQty()))));//实退PV
            						
            						//说明此没有匹配完  尚有剩余需要下个订单再次处理
//            						OrderDetail tempo = new OrderDetail();
//            						tempo.setProductCode(o.getProductCode());
//            						tempo.setProductName(o.getProductName());
//            						tempo.setQty(o.getQty()-ol.getQty());
//            						tempOrderDetailList.add(tempo);
//            						orderDetailList.remove(ii);//说明此商品已匹配，删除此匹配商品元素
            						o.setQty(o.getQty()-ol.getQty());
            					}
            					orderCompareResultList.add(orderCompareResult);//加入导出对象集合
            				}
            			}
            		}
            	}
//            	if(!MeteorUtil.isBlankByList(tempOrderDetailList)){
//            		for(OrderDetail to : tempOrderDetailList){
//            			orderDetailList.add(to);
//            		}
//            	}
//            	tempOrderDetailList = new ArrayList<OrderDetail>();
            }
            String tempordercode = "";//临时记录上一条的订单编号
            int kk = 2;// 填充内容起始行
            //开始填充excel内容
            if(!MeteorUtil.isEmptyList(orderCompareResultList)){
            	listStore(orderCompareResultList);//排序
            	for(OrderCompareResult ocr:orderCompareResultList){
            		if(!tempordercode.equals(ocr.getOrderCode())){
            			eu.addString(wsheet, 0, kk, ocr.getMemberUserCode());
						eu.addString(wsheet, 1, kk, ocr.getMemberUserName());
						eu.addString(wsheet, 2, kk, ocr.getOrderCode());
						eu.addString(wsheet, 3, kk, ocr.getOrderType());
            		}
            		tempordercode = ocr.getOrderCode();//重新给临时订单号赋值
            		eu.addString(wsheet, 4, kk, ocr.getProductCode());
					eu.addString(wsheet, 5, kk, ocr.getProductName());
					eu.addString(wsheet, 6, kk, ocr.getProductPackageCode());//套餐代码
					eu.addString(wsheet, 7, kk, ocr.getProductPackageName());//套餐名称
					eu.addString(wsheet, 8, kk, ocr.getProductPackagePrice());//套餐价格
					eu.addString(wsheet, 9, kk, ocr.getProductPackagePv());//套餐PV
					eu.addString(wsheet, 10, kk, ocr.getProductPackageQty());//套餐数量
					eu.addString(wsheet, 11, kk, ocr.getPackageProductCode());//套餐内产品编号
					eu.addString(wsheet, 12, kk, ocr.getPackageProductName());//套餐内产品名称
					eu.addString(wsheet, 13, kk, ocr.getPackageProductPrice());//单价
					eu.addString(wsheet, 14, kk, ocr.getPackageProductPv());//单个PV值
					eu.addString(wsheet, 15, kk, ocr.getQty());//订购数量
					eu.addString(wsheet, 16, kk, ocr.getReturnProductCode());//退货产品编码
					eu.addString(wsheet, 17, kk, ocr.getReturnProductName());//退货产品名称
					eu.addString(wsheet, 18, kk, ocr.getReturnQty());//退货产品数量
					eu.addString(wsheet, 19, kk, ocr.getReturnMoney());//实退金额
					eu.addString(wsheet, 20, kk, ocr.getReturnPv());//实退PV
            		
            		kk++;
            	}
            }
            
          //在此创建的新excel文件创建一工作表 
			WritableSheet wsheet2 = wwb.createSheet("未匹配上的产品", 1);
			
			eu.addString(wsheet2, 0, 0, "未匹配上的产品");
			//设置列头
			excelTopColumn(eu, wsheet2, 1, "产品代码,产品名称,数量");
//			int x=0;
			//列头  后续改为公共方法。
//			eu.addString(wsheet2, x++, 1, "产品代码");
//			eu.addString(wsheet2, x++, 1, "产品名称");
//			eu.addString(wsheet2, x++, 1, "数量");
			int xx = 2;// 填充内容起始行
            if(!MeteorUtil.isEmptyList(orderDetailList)){
            	for (OrderDetail od:orderDetailList){
            		if(od.getQty()==0){
            			continue;
            		}
            		eu.addString(wsheet2, 0, xx, od.getProductCode());
	            	eu.addString(wsheet2, 1, xx, od.getProductName());
	            	eu.addString(wsheet2, 2, xx, String.valueOf(od.getQty()));
	            	xx++;
            	}
            }
            eu.writeExcel(wwb);
			eu.closeWritableWorkbook(wwb);
			os.close();

			return null;
        }
        catch (IOException e) {
            this.saveMessage(request,
                    getText("bdBounsDeduct.importFile.failed"));
            log.error(e.getMessage());
        }
        catch (Exception e) {
            this.saveMessage(request,
                    getText("bdBounsDeduct.import.data.error"));
            log.info("匹配失败",e);
        }
        
        return new ModelAndView(this.getSuccessView());
    } 
    
    /**
     * @Description excel列头
     * @author houxyu
     * @date 2016-3-28
     * @param eu
     * @param wsheet
     * @param row  列头填充到第几行
     */
    public void excelTopColumn(ExcelUtil eu,WritableSheet wsheet,int row,String columns) throws Exception{
    	String [] cs = null;
		if(!MeteorUtil.isBlank(columns)){
			cs = columns.split(",");
		}
		for(int i=0;i<cs.length;i++){
			//列头  后续改为公共方法。
			eu.addString(wsheet, i, row, cs[i]);//工作表对象，列，行，值
		}
    }
    
    /**
     * @Description 排序，先按订单编号排序，再以showorder排序，相同订单号的showorder小的排前面
     * @author houxyu
     * @date 2016-3-30
     * @param list
     */
    public void listStore(List<OrderCompareResult> list){
    	Collections.sort(list,new Comparator<OrderCompareResult>(){ 
            public int compare(OrderCompareResult arg0, OrderCompareResult arg1) { 
//            第一次比较专业 
                int i = arg0.getOrderCode().compareTo(arg1.getOrderCode());
//            如果专业相同则进行第二次比较 
	            if(i==0){ 
	//                第二次比较 
	                int j=arg0.getShoworder().compareTo(arg1.getShoworder()); 
	//                第三次比较 
	//                if(j==0){ 
	//                    return arg0.getCCC().compareTo(arg1.getCCC()); 
	//                } 
	                return j; 
	            } 
            return i; 
            } 
        });
    }
    
    /**
     * @Description 设置订单排序
     * @author houxyu
     * @date 2016-3-29
     * @param o
     */
    public void setOrderShoworder(MemberOrder o){
    	o.setShowOrder(999f);//默认设置为999
    	Map<String, String[]> map = Constants.sysListMap.get("order.type.showorder");//匹配订单按类型排序
		if(map==null || map.isEmpty()){
			
		}else{
			Set codes = map.entrySet();
			if (codes != null) {
				Iterator iter = codes.iterator();
				while (iter.hasNext()) {
					Map.Entry entry=(Map.Entry)iter.next();
					String[] values = (String[])entry.getValue();
					String key = (String)entry.getKey();
					if(values[0].equals(o.getOrderType())){
						o.setShowOrder(Float.valueOf(key));
					}
					
				}
			}
		}
    }
}
