package com.joymain.jecs.am.webapp.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.pd.model.PdJprRefundStatus;
import com.joymain.jecs.pd.model.PdLogistics;
import com.joymain.jecs.pd.model.PdLogisticsBase;
import com.joymain.jecs.pd.model.PdLogisticsBaseDetail;
import com.joymain.jecs.pd.model.PdLogisticsBaseNum;
import com.joymain.jecs.pd.model.PdReturnStatus;
import com.joymain.jecs.pd.model.PdSendInfoStatus;
import com.joymain.jecs.pd.model.RspEntity;
import com.joymain.jecs.pd.service.PdLogisticsBaseManager;
import com.joymain.jecs.pd.service.PdReturnPurchaseManager;
import com.joymain.jecs.pd.service.PdSendInfoManager;
import com.joymain.jecs.pr.service.JprRefundManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.am.model.InwProblem;
import com.joymain.jecs.am.service.InwProblemManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class InwProblemController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(InwProblemController.class);
    private InwProblemManager inwProblemManager = null;
    private JprRefundManager jprRefundManager = null;
    private PdSendInfoManager pdSendInfoManager;
    
    private PdReturnPurchaseManager pdReturnPurchaseManager;
    
    private PdLogisticsBaseManager pdLogisticsBaseManager = null;

    public void setInwProblemManager(InwProblemManager inwProblemManager) {
        this.inwProblemManager = inwProblemManager;
    }
    
    public void setJprRefundManager(JprRefundManager jprRefundManager) {
		this.jprRefundManager = jprRefundManager;
	}
    
	public void setPdLogisticsBaseManager(PdLogisticsBaseManager pdLogisticsBaseManager) {
		this.pdLogisticsBaseManager = pdLogisticsBaseManager;
	}
	
	public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}
	
	public void setPdReturnPurchaseManager(PdReturnPurchaseManager pdReturnPurchaseManager) {
		this.pdReturnPurchaseManager = pdReturnPurchaseManager;
	}

	/**
     * 创新共赢的共赢帮助的查询
     * @author gw 2013-08-26
     * @param request
     * @param response
     * @return ModelAndView
     */
    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
    	//退货单入库接口------自测-----20150824 
    	/*
    	PdJprRefundStatus pdJprRefundStatusOne = new PdJprRefundStatus();
    	pdJprRefundStatusOne.setRoNo(null);
    	pdJprRefundStatusOne.setIntoStatus(null);
    	JSONObject joOne = JSONObject.fromObject(pdJprRefundStatusOne);
    	String jsStringOne = joOne.toString();
    	RspEntity rspEntityOne = jprRefundManager.reSetJprRefundStatus(jsStringOne);
    	System.out.println(rspEntityOne.getSub_msg());
    	
    	PdJprRefundStatus pdJprRefundStatusTwo = new PdJprRefundStatus();
    	pdJprRefundStatusTwo.setRoNo("123456");
    	pdJprRefundStatusTwo.setIntoStatus(null);
    	JSONObject joTwo = JSONObject.fromObject(pdJprRefundStatusTwo);
    	String jsStringTwo = joTwo.toString();
    	RspEntity rspEntityTwo = jprRefundManager.reSetJprRefundStatus(jsStringTwo);
    	System.out.println(rspEntityTwo.getSub_msg());
    	
    	PdJprRefundStatus pdJprRefundStatusThree = new PdJprRefundStatus();
    	pdJprRefundStatusThree.setRoNo("M50201507280000002");
    	pdJprRefundStatusThree.setIntoStatus(null);
    	JSONObject joThree = JSONObject.fromObject(pdJprRefundStatusThree);
    	String jsStringThree = joThree.toString();
    	RspEntity rspEntityThree = jprRefundManager.reSetJprRefundStatus(jsStringThree);
    	System.out.println(rspEntityThree.getSub_msg());
    	
    	PdJprRefundStatus pdJprRefundStatusFour = new PdJprRefundStatus();
    	pdJprRefundStatusFour.setRoNo("M50201507280000002");
    	pdJprRefundStatusFour.setIntoStatus("12");
    	JSONObject joFour = JSONObject.fromObject(pdJprRefundStatusFour);
    	String jsStringFour = joFour.toString();
    	RspEntity rspEntityFour = jprRefundManager.reSetJprRefundStatus(jsStringFour);
    	System.out.println(rspEntityFour.getSub_msg());
    	
    	PdJprRefundStatus pdJprRefundStatusFive = new PdJprRefundStatus();
    	pdJprRefundStatusFive.setRoNo("M50201507280000002");
    	pdJprRefundStatusFive.setIntoStatus("Y");
    	JSONObject joFive = JSONObject.fromObject(pdJprRefundStatusFive);
    	String jsStringFive = joFive.toString();
    	RspEntity rspEntityFive = jprRefundManager.reSetJprRefundStatus(jsStringFive);
    	System.out.println(rspEntityFive.getSub_msg());
    	*/
    	
    	/*//定时器功能自测，fx，20150810
    	pdLogisticsBaseManager.gainMailStatus();*/
    	
    	
    	/*//reSetPdSendInfoStatus----------换货单和发货单直接关联的发货单状态接口测试
    	PdSendInfoStatus pdSendInfoStatusOne = new PdSendInfoStatus();
    	pdSendInfoStatusOne.setSiNo(null);
    	pdSendInfoStatusOne.setStatus(null);
    	JSONObject joOne = JSONObject.fromObject(pdSendInfoStatusOne);
    	String jsStringOne = joOne.toString();
    	RspEntity rspEntityOne = pdSendInfoManager.reSetPdSendInfoStatus(jsStringOne);
    	System.out.println(rspEntityOne.getSub_msg());
    	
    	PdSendInfoStatus pdSendInfoStatusTwo = new PdSendInfoStatus();
    	pdSendInfoStatusTwo.setSiNo("1111111111");
    	pdSendInfoStatusTwo.setStatus(null);
    	JSONObject joTwo = JSONObject.fromObject(pdSendInfoStatusTwo);
    	String jsStringTwo = joTwo.toString();
    	RspEntity rspEntityTwo = pdSendInfoManager.reSetPdSendInfoStatus(jsStringTwo);
    	System.out.println(rspEntityTwo.getSub_msg());
    	
    	PdSendInfoStatus pdSendInfoStatusThree = new PdSendInfoStatus();
    	pdSendInfoStatusThree.setSiNo("LO012015080500006");
    	pdSendInfoStatusThree.setStatus(null);
    	JSONObject joThree = JSONObject.fromObject(pdSendInfoStatusThree);
    	String jsStringThree = joThree.toString();
    	RspEntity rspEntityThree = pdSendInfoManager.reSetPdSendInfoStatus(jsStringThree);
    	System.out.println(rspEntityThree.getSub_msg());
    	
    	PdSendInfoStatus pdSendInfoStatusFour = new PdSendInfoStatus();
    	pdSendInfoStatusFour.setSiNo("LO012015081300005");
    	pdSendInfoStatusFour.setStatus("123");
    	JSONObject joFour = JSONObject.fromObject(pdSendInfoStatusFour);
    	String jsStringFour = joFour.toString();
    	RspEntity rspEntityFour = pdSendInfoManager.reSetPdSendInfoStatus(jsStringFour);
    	System.out.println(rspEntityFour.getSub_msg());
    	
    	PdSendInfoStatus pdSendInfoStatusFive = new PdSendInfoStatus();
    	pdSendInfoStatusFive.setSiNo("LO012015081300005");
    	pdSendInfoStatusFive.setStatus("Y");
    	JSONObject joFive = JSONObject.fromObject(pdSendInfoStatusFive);
    	String jsStringFive = joFive.toString();
    	RspEntity rspEntityFive = pdSendInfoManager.reSetPdSendInfoStatus(jsStringFive);
    	System.out.println(rspEntityFive.getSub_msg());*/
    	//reSetPdSendInfoStatus----------换货单和发货单直接关联的发货单状态接口自测完成
    	
    	//发货退回入库接口自测---------------modify fu 20150821
    	/*PdReturnStatus pdReturnStatusOne = new PdReturnStatus();
    	pdReturnStatusOne.setRpNo(null);
    	pdReturnStatusOne.setIntoStatus(null);
    	JSONObject joOne = JSONObject.fromObject(pdReturnStatusOne);
    	String jsStringOne = joOne.toString();
    	RspEntity rspEntityOne = pdReturnPurchaseManager.reSetPdReturnPurchaseStatus(jsStringOne);
    	System.out.println(rspEntityOne.getSub_msg());
    	
    	PdReturnStatus pdReturnStatusTwo = new PdReturnStatus();
    	pdReturnStatusTwo.setRpNo("1234");
    	pdReturnStatusTwo.setIntoStatus(null);
    	JSONObject joTwo = JSONObject.fromObject(pdReturnStatusTwo);
    	String jsStringTwo = joTwo.toString();
    	RspEntity rspEntityTwo = pdReturnPurchaseManager.reSetPdReturnPurchaseStatus(jsStringTwo);
    	System.out.println(rspEntityTwo.getSub_msg());
    	
    	PdReturnStatus pdReturnStatusThree = new PdReturnStatus();
    	pdReturnStatusThree.setRpNo("A912015082100002");
    	pdReturnStatusThree.setIntoStatus(null);
    	JSONObject joThree = JSONObject.fromObject(pdReturnStatusThree);
    	String jsStringThree = joThree.toString();
    	RspEntity rspEntityThree = pdReturnPurchaseManager.reSetPdReturnPurchaseStatus(jsStringThree);
    	System.out.println(rspEntityThree.getSub_msg());
    	
    	PdReturnStatus pdReturnStatusFour = new PdReturnStatus();
    	pdReturnStatusFour.setRpNo("A912015082100002");
    	pdReturnStatusFour.setIntoStatus("1234");
    	JSONObject joFour = JSONObject.fromObject(pdReturnStatusFour);
    	String jsStringFour = joFour.toString();
    	RspEntity rspEntityFour = pdReturnPurchaseManager.reSetPdReturnPurchaseStatus(jsStringFour);
    	System.out.println(rspEntityFour.getSub_msg());
    	
    	PdReturnStatus pdReturnStatusFive = new PdReturnStatus();
    	pdReturnStatusFive.setRpNo("A912015082100002");
    	pdReturnStatusFive.setIntoStatus("Y");
    	JSONObject joFive = JSONObject.fromObject(pdReturnStatusFive);
    	String jsStringFive = joFive.toString();
    	RspEntity rspEntityFive = pdReturnPurchaseManager.reSetPdReturnPurchaseStatus(jsStringFive);
    	System.out.println(rspEntityFive.getSub_msg());*/
    	//发货退回入库接口自测完成
    	
    	
    	/*PdJprRefundStatus pdJprRefundStatusFive = new PdJprRefundStatus();
    	pdJprRefundStatusFive.setRoNo("M50201508240000002");
    	pdJprRefundStatusFive.setIntoStatus("Y");
    	JSONObject joFive = JSONObject.fromObject(pdJprRefundStatusFive);
    	String jsStringFive = joFive.toString();
    	RspEntity rspEntityFive = jprRefundManager.reSetJprRefundStatus(jsStringFive);
    	System.out.println(rspEntityFive.getSub_msg());*/
    	
    	//DO单测试
    	
    	// 物流跟踪单号测试+确认收货功能的测试
    	//DO+LO  自测
    	PdLogisticsBase pdLogisticsBaseOne = new PdLogisticsBase();
    	pdLogisticsBaseOne.setMember_order_no("M020151119000004");
    	pdLogisticsBaseOne.setSi_no("LO012015111900021");
    	pdLogisticsBaseOne.setStatus("1");
    	pdLogisticsBaseOne.setWms_do("DO2015112701001");
    	pdLogisticsBaseOne.setStatus_name("已发货");
    	pdLogisticsBaseOne.setStatus_code("0066");
    	pdLogisticsBaseOne.setStatus_time(new Date());
    	pdLogisticsBaseOne.setOperator("aaaaaa");
    	pdLogisticsBaseOne.setNum_order_type("MO");
    	
  		PdLogisticsBaseNum pdLogisticsBaseNumOne = new PdLogisticsBaseNum();
  		pdLogisticsBaseNumOne.setPdLogisticsBaseNum_no("1195106726105");
  		pdLogisticsBaseNumOne.setName("邮政物流");
  		pdLogisticsBaseNumOne.setStatus("0020");//0020  0099
  		

  		PdLogisticsBaseDetail pdLogisticsBaseDetailOne = new PdLogisticsBaseDetail();
  		pdLogisticsBaseDetailOne.setErp_goods_bn("10020200101");
  		pdLogisticsBaseDetailOne.setproduct_no("P02020100101CN0");// 单价:20
  		pdLogisticsBaseDetailOne.setPrice(new BigDecimal(199.2));
  		pdLogisticsBaseDetailOne.setQty("2");

  		PdLogisticsBaseDetail pdLogisticsBaseDetailTwoo = new PdLogisticsBaseDetail();
  		pdLogisticsBaseDetailTwoo.setErp_goods_bn("10020200201");
  		pdLogisticsBaseDetailTwoo.setproduct_no("P02020100201CN0");// 单价:20
  		pdLogisticsBaseDetailTwoo.setPrice(new BigDecimal(199.2));
  		pdLogisticsBaseDetailTwoo.setQty("2");
  		
  		
  		List<PdLogisticsBaseDetail> listDetailOne = new ArrayList<PdLogisticsBaseDetail>();
  		listDetailOne.add(pdLogisticsBaseDetailOne);
  		listDetailOne.add(pdLogisticsBaseDetailTwoo);
  		

  		pdLogisticsBaseNumOne.setPdLogisticsBaseDetail_items(listDetailOne);
  		
  		List<PdLogisticsBaseNum> listNumOne = new ArrayList<PdLogisticsBaseNum>();
  		listNumOne.add(pdLogisticsBaseNumOne);
  		pdLogisticsBaseOne.setMail_list(listNumOne);
  		JSONObject jsonObject = JSONObject.fromObject(pdLogisticsBaseOne);
   	    String jsonString = jsonObject.toString();
  		
  		RspEntity rspEntity = pdLogisticsBaseManager.savePdLogisticsBaseByInter(jsonString);
   	    System.out.println(rspEntity.getSub_msg());
   	    
   	    //--------------------------------------------------------------------------------------------------------
	   	PdLogisticsBase pdLogisticsBaseOnea = new PdLogisticsBase();
	   	pdLogisticsBaseOnea.setMember_order_no("M020151119000004");
	   	pdLogisticsBaseOnea.setSi_no("LO012015111900021");
	   	pdLogisticsBaseOnea.setStatus("1");
	   	pdLogisticsBaseOnea.setWms_do("DO2015112701002");
	   	pdLogisticsBaseOnea.setStatus_name("已发货");
	   	pdLogisticsBaseOnea.setStatus_code("0066");
	   	pdLogisticsBaseOnea.setStatus_time(new Date());
	   	pdLogisticsBaseOnea.setOperator("aaaaaa");
	   	pdLogisticsBaseOnea.setNum_order_type("MO");
 	
		PdLogisticsBaseNum pdLogisticsBaseNumOnea = new PdLogisticsBaseNum();
		pdLogisticsBaseNumOnea.setPdLogisticsBaseNum_no("1195106726105");
		pdLogisticsBaseNumOnea.setName("邮政物流");
		pdLogisticsBaseNumOnea.setStatus("0020");//0020  0099
		

		PdLogisticsBaseDetail pdLogisticsBaseDetailOnea = new PdLogisticsBaseDetail();
		pdLogisticsBaseDetailOnea.setErp_goods_bn("10020200301");
		pdLogisticsBaseDetailOnea.setproduct_no("P02020100301CN0");// 单价:20
		pdLogisticsBaseDetailOnea.setPrice(new BigDecimal(199.2));
		pdLogisticsBaseDetailOnea.setQty("2");

		PdLogisticsBaseDetail pdLogisticsBaseDetailTwooa = new PdLogisticsBaseDetail();
		pdLogisticsBaseDetailTwooa.setErp_goods_bn("10020200401");
		pdLogisticsBaseDetailTwooa.setproduct_no("P02020100401CN0");// 单价:20
		pdLogisticsBaseDetailTwooa.setPrice(new BigDecimal(199.2));
		pdLogisticsBaseDetailTwooa.setQty("2");
		
		
		List<PdLogisticsBaseDetail> listDetailOnea = new ArrayList<PdLogisticsBaseDetail>();
		listDetailOnea.add(pdLogisticsBaseDetailOnea);
		listDetailOnea.add(pdLogisticsBaseDetailTwooa);
		

		pdLogisticsBaseNumOnea.setPdLogisticsBaseDetail_items(listDetailOnea);
		
		List<PdLogisticsBaseNum> listNumOnea = new ArrayList<PdLogisticsBaseNum>();
		listNumOnea.add(pdLogisticsBaseNumOnea);
		pdLogisticsBaseOnea.setMail_list(listNumOnea);
		JSONObject jsonObjecta = JSONObject.fromObject(pdLogisticsBaseOnea);
	    String jsonStringa = jsonObjecta.toString();
		
		RspEntity rspEntitya = pdLogisticsBaseManager.savePdLogisticsBaseByInter(jsonStringa);
	    System.out.println(rspEntitya.getSub_msg());
   	    
   	    
	   	/* PdLogisticsBase pdLogisticsBaseOne2 = new PdLogisticsBase();
	   	pdLogisticsBaseOne2.setMember_order_no("M020150901000003");
	   	pdLogisticsBaseOne2.setSi_no("LO012015090100003");
	   	pdLogisticsBaseOne2.setStatus("1");
	   	pdLogisticsBaseOne2.setWms_do("DO20150901002");
	   	pdLogisticsBaseOne2.setStatus_name("已发货");
	   	pdLogisticsBaseOne2.setStatus_code("0066");
	   	pdLogisticsBaseOne2.setStatus_time(new Date());
	   	pdLogisticsBaseOne2.setOperator("aaaaaa");
	   	pdLogisticsBaseOne2.setNum_order_type("MO");
 	
 	
 	
		PdLogisticsBaseNum pdLogisticsBaseNumOne2 = new PdLogisticsBaseNum();
		pdLogisticsBaseNumOne2.setPdLogisticsBaseNum_no("20150901002");
		pdLogisticsBaseNumOne2.setName("中餐");
		pdLogisticsBaseNumOne2.setStatus("0099");//0020  0099
		

		PdLogisticsBaseDetail pdLogisticsBaseDetailOne2 = new PdLogisticsBaseDetail();
		pdLogisticsBaseDetailOne2.setErp_goods_bn("10010200301");
		pdLogisticsBaseDetailOne2.setproduct_no("P02020100301CN0");// 单价:20
		pdLogisticsBaseDetailOne2.setPrice(new BigDecimal(199.20));
		pdLogisticsBaseDetailOne2.setQty("5");
		
		List<PdLogisticsBaseDetail> listDetailOne2 = new ArrayList<PdLogisticsBaseDetail>();
		listDetailOne2.add(pdLogisticsBaseDetailOne2);
		


		pdLogisticsBaseNumOne2.setPdLogisticsBaseDetail_items(listDetailOne2);
		
		List<PdLogisticsBaseNum> listNumOne2 = new ArrayList<PdLogisticsBaseNum>();
		listNumOne2.add(pdLogisticsBaseNumOne2);
		pdLogisticsBaseOne2.setMail_list(listNumOne2);
		JSONObject jsonObject2 = JSONObject.fromObject(pdLogisticsBaseOne2);
	    String jsonString2 = jsonObject2.toString();
		
		RspEntity rspEntity2 = pdLogisticsBaseManager.savePdLogisticsBaseByInter(jsonString2);
	    System.out.println(rspEntity2.getSub_msg());
  		
	    PdLogistics pdLogistics = new PdLogistics();
		List<PdLogisticsBase>   pdLogisticsBase_list = new ArrayList();
		pdLogisticsBase_list.add(pdLogisticsBaseOne);
		pdLogisticsBase_list.add(pdLogisticsBaseOne2);
		pdLogistics.setPdLogisticsBase_list(pdLogisticsBase_list);
		JSONObject jo = JSONObject.fromObject(pdLogistics);
	    String joS = jo.toString();
		
		RspEntity rspEntity3 = pdLogisticsBaseManager.savePdLogisticsBaseList(joS);
	    System.out.println(rspEntity3.getSub_msg());*/
	    //LO单自测完成
	    

	    
  		/*PdLogisticsBaseNum pdLogisticsBaseNumTwo = new PdLogisticsBaseNum();
  		pdLogisticsBaseNumTwo.setPdLogisticsBaseNum_no("20150831002");
  		pdLogisticsBaseNumTwo.setName("中餐");
  		pdLogisticsBaseNumTwo.setStatus("0020");//0020  0099
  		

  		PdLogisticsBaseDetail pdLogisticsBaseDetailTTwoOne = new PdLogisticsBaseDetail();
  		pdLogisticsBaseDetailTTwoOne.setErp_goods_bn("10010200201");
  		pdLogisticsBaseDetailTTwoOne.setproduct_no("P02020100201CN0");// 单价:20
  		pdLogisticsBaseDetailTTwoOne.setPrice(new BigDecimal(199.20));
  		pdLogisticsBaseDetailTTwoOne.setQty("5");*/
  		
  		/*PdLogisticsBaseDetail pdLogisticsBaseDetailTwoTwo = new PdLogisticsBaseDetail();
  		pdLogisticsBaseDetailTwoTwo.setErp_goods_bn("10010200201");
  		pdLogisticsBaseDetailTwoTwo.setproduct_no("P02020100201CN0");// 单价:20
  		pdLogisticsBaseDetailTwoTwo.setPrice(new BigDecimal(199.20));
  		pdLogisticsBaseDetailTwoTwo.setQty("1");
  		
  		PdLogisticsBaseDetail pdLogisticsBaseDetailTwoThree = new PdLogisticsBaseDetail();
  		pdLogisticsBaseDetailTwoThree.setErp_goods_bn("100403018");
  		pdLogisticsBaseDetailTwoThree.setproduct_no("P16320700101CN0");// 单价:20
  		pdLogisticsBaseDetailTwoThree.setCombination_product_no("P16321000101CN0");
  		pdLogisticsBaseDetailTwoThree.setPrice(new BigDecimal(0.00));
  		pdLogisticsBaseDetailTwoThree.setQty("1");*/
  		
  		/*List<PdLogisticsBaseDetail> listDetailTwoOne = new ArrayList<PdLogisticsBaseDetail>();
  		listDetailTwoOne.add(pdLogisticsBaseDetailTTwoOne);*/
  		//listDetailTwoOne.add(pdLogisticsBaseDetailTwoTwo);
  		//listDetailTwoOne.add(pdLogisticsBaseDetailTwoThree);


  		//pdLogisticsBaseNumTwo.setPdLogisticsBaseDetail_items(listDetailTwoOne);
  	/*	
  		List<PdLogisticsBaseNum> listNumOne = new ArrayList<PdLogisticsBaseNum>();
  		listNumOne.add(pdLogisticsBaseNumOne);
  		//listNumOne.add(pdLogisticsBaseNumTwo);
  		pdLogisticsBaseOne.setMail_list(listNumOne);
  		JSONObject jsonObject = JSONObject.fromObject(pdLogisticsBaseOne);
   	    String jsonString = jsonObject.toString();
  		
  		RspEntity rspEntity = pdLogisticsBaseManager.savePdLogisticsBaseByInter(jsonString);
   	    System.out.println(rspEntity.getSub_msg());
  		*/
  		
    	
    	
    	 if (log.isDebugEnabled()) {
              log.debug("entering 'handleRequest' method...");
         }
    	 String strAction = request.getParameter("strAction");
    	 //普通查询
    	 if("queryInwProblem".equals(strAction)){
	    	 //获取查询条件  SPECIES   0代表所有,1代表"关于提交给中脉",2代表"关于与中脉合作",3代表"一般问题",4代表"关于中脉"
	    	 String species = request.getParameter("species");
	    	 CommonRecord crm = RequestUtil.toCommonRecord(request);
	    	 crm.setValue("species", species);
	    	 Pager pager = new Pager("inwProblemListTable",request, 20);
	    	 List inwProblemList = inwProblemManager.getInwProblemList(crm,pager);
	    	 request.setAttribute("inwProblemListTable_totalRows", pager.getTotalObjects());
	    	 return new ModelAndView("am/inwProblemList", Constants.INWPROBLEM_LIST, inwProblemList);
    	 }
    	 //详细查询
    	 else{
    		 String id = request.getParameter("id");
    		 InwProblem inwProblem = inwProblemManager.getInwProblem(id);
    		 request.setAttribute("inwProblem", inwProblem);
    		 return new ModelAndView("am/inwProblemQueryDetail","","");
    	 }
    }
}
