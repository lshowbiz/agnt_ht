package com.joymain.jecs.po.webapp.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import yspay.util.StringUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.joymain.jecs.util.MeteorUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.po.dao.YDorderDao;
import com.joymain.jecs.po.model.YDOrder;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.webapp.action.BaseController;

public class YDorderstatistic extends BaseController implements Controller{

	private YDorderDao ydOrderDao;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String flag = req.getParameter("flag");
		if(flag == null){
			Pager page = new Pager("ydOrderListTable",req, 20);
			page.getLimit().setRowAttributes(page.getTotalObjects(), 0);
			req.setAttribute("ydOrderListTable_totalRows", page.getTotalObjects());
			return new ModelAndView("po/ydOrderList");
		}
		req.setAttribute("flag", flag);
		String userCode = req.getParameter("userCode");
		String orderNo = req.getParameter("orderNo");
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		String teamCode = req.getParameter("teamCode1");
		String orderPaystartDate = req.getParameter("orderPaystartDate");
		String orderPayendDate = req.getParameter("orderPayendDate");
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("userCode", userCode);
		map.put("orderNo", orderNo);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		
		map.put("teamCode", teamCode);
		map.put("orderPaystartDate", orderPaystartDate);
		map.put("orderPayendDate", orderPayendDate);
		
		Pager page = new Pager("ydOrderListTable",req, 20);
		List<Map> ydOrderList = ydOrderDao.getYDorders(map, page);    
		List<YDOrder> ydOrderListTemp=new ArrayList<YDOrder>();
		for(int i=0;ydOrderList.size()>i;i++){
			YDOrder ydOrder =new YDOrder();
			Map ydOrderListMap=(Map) ydOrderList.get(i);
			ydOrder.setMoid(Long.valueOf((String)ydOrderListMap.get("mo_id")));
			ydOrder.setOrderNo(ydOrderListMap.get("orderno")!=null?(String)ydOrderListMap.get("orderno"):"");
			ydOrder.setUserCode(ydOrderListMap.get("usercode")!=null?(String)ydOrderListMap.get("usercode"):"");
			BigDecimal AMOUNT=new BigDecimal(ydOrderListMap.get("amount")!=null?(String)ydOrderListMap.get("amount"):"0");
			ydOrder.setAmount(AMOUNT);
			BigDecimal ydPv=new BigDecimal(ydOrderListMap.get("yd_pv")!=null&&ydOrderListMap.get("yd_pv")!=""?(String)ydOrderListMap.get("yd_pv"):"0");
			ydOrder.setYdPV(ydPv);
			BigDecimal pvamt=new BigDecimal(ydOrderListMap.get("pvamt")!=null&&ydOrderListMap.get("pvamt")!=""?(String)ydOrderListMap.get("pvamt"):"0") ;
			ydOrder.setPvamt(pvamt);
			BigDecimal yd_rebate=new BigDecimal(ydOrderListMap.get("yd_rebate")!=null&&ydOrderListMap.get("yd_rebate")!=""?(String)ydOrderListMap.get("yd_rebate"):"0") ;
			ydOrder.setYdRebate(yd_rebate);
			BigDecimal ec_rebate=new BigDecimal(ydOrderListMap.get("ec_rebate")!=null&&ydOrderListMap.get("ec_rebate")!=""?(String)ydOrderListMap.get("ec_rebate"):"0") ;
			ydOrder.setEcRebate(ec_rebate);
			BigDecimal total_rebate=new BigDecimal(ydOrderListMap.get("total_rebate")!=null&&ydOrderListMap.get("total_rebate")!=""?(String)ydOrderListMap.get("total_rebate"):"0") ;
			ydOrder.setTotalRebate(total_rebate);

			if(StringUtil.isNotEmpty((String)ydOrderListMap.get("ordertime"))){
				ydOrder.setOrderTimeString((String)ydOrderListMap.get("ordertime"));
			}
			if(StringUtil.isNotEmpty((String)ydOrderListMap.get("orderpaytime"))){
				ydOrder.setOrderPayTime(MeteorUtil.doConvertToDate(String.valueOf(ydOrderListMap.get("orderpaytime"))));
			}
			
			ydOrder.setTeamCode((String)ydOrderListMap.get("teamcode")!=null?(String)ydOrderListMap.get("teamcode"):"");
			ydOrderListTemp.add(ydOrder);
		}
		
        Map sumMap = ydOrderDao.getSumAmount(map);
        req.setAttribute("sumMap", sumMap);
		
		page.getLimit().setRowAttributes(page.getTotalObjects(), 0);
		req.setAttribute("ydOrderListTable_totalRows", page.getTotalObjects());
		 
		return new ModelAndView("po/ydOrderList", "ydOrderList", ydOrderListTemp);
	}

	public YDorderDao getYdOrderDao() {
		return ydOrderDao;
	}

	public void setYdOrderDao(YDorderDao ydOrderDao) {
		this.ydOrderDao = ydOrderDao;
	}
	
}
