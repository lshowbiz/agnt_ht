package com.joymain.jecs.pm.webapp.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pd.model.PdWarehouse;
import com.joymain.jecs.pd.service.PdWarehouseManager;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.pm.service.JpmProductSaleManager;
import com.joymain.jecs.service.PdLogisticsService;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JpmProductSaleController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpmProductSaleController.class);
    private JpmProductSaleManager jpmProductSaleManager = null;
    private PdWarehouseManager pdWarehouseManager = null;
    private PdLogisticsService starsExpressService = null;
    
    public void setStarsExpressService(PdLogisticsService starsExpressService) {
		this.starsExpressService = starsExpressService;
	}

	public void setPdWarehouseManager(PdWarehouseManager pdWarehouseManager) {
		this.pdWarehouseManager = pdWarehouseManager;
	}

	public void setJpmProductSaleManager(JpmProductSaleManager jpmProductSaleManager) {
        this.jpmProductSaleManager = jpmProductSaleManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        String view = "pm/jpmProductSaleList";
        String selectControl = request.getParameter("selectControl");
        String strAction = request.getParameter("strAction");
        CommonRecord crm = new CommonRecord();
        if("selectProduct".equals(strAction)){
		      view = "pm/jpmProductSaleSelect";
		      crm=RequestUtil.toCommonRecord(request);
		      crm.setValue("status", "0,1,2");//查询所有商品数据
//		      request.setAttribute("selectControl", selectControl);
		 }else{
			 crm=initCommonRecord(request);
		 }
        if(!"AA".equalsIgnoreCase(sessionLogin.getCompanyCode())){
			crm.setValue("companyCode",sessionLogin.getCompanyCode() );
		}
        
//        JpmProductSale jpmProductSale = new JpmProductSale();
        // populate object with request parameters
//        BeanUtils.populate(jpmProductSale, request.getParameterMap());
        String batchSynProduct = request.getParameter("batchSynProduct");
        if("batchSynProduct".equals(batchSynProduct)){
        	crm.setValue("companyCode", "CN");
        	List products = jpmProductSaleManager.getJpmProductSalesByCrmNew(crm, null);
        	for(int i=0;i<products.size();i++){
        		JpmProductSaleNew jpmProductSale = (JpmProductSaleNew) products.get(i);
        		try {
//					this.sendStarsProductSale(jpmProductSale, request);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					
				}
        	}
        }

        Pager pager = new Pager("jpmProductSaleListTable",request, 20);
        List jpmProductSales = jpmProductSaleManager.getJpmProductSalesByCrm(crm,pager);
        request.setAttribute("jpmProductSaleListTable_totalRows", pager.getTotalObjects());
        
        //如果是异动单，需要获取“中脉团队”首单的价格，可能取不到价格的，则特殊处理！
        List<JpmProductSaleNew> tempList = new ArrayList<JpmProductSaleNew>();
        JpmProductSaleNew jpsn = null; 
        Set<JpmProductSaleTeamType> jpsttSet = null;//商品团队类型数据
        for(Object t : jpmProductSales){
        	jpsn = (JpmProductSaleNew)t;
        	jpsttSet = jpsn.getJpmProductSaleTeamTypes();
        	if(jpsttSet!=null && jpsttSet.size()>=1){
        		for(JpmProductSaleTeamType jpstt : jpsttSet){//取商品对应的中脉团队、会员首单的价格
            		if("1".equals(jpstt.getOrderType()) && "8888888888".equals(jpstt.getTeamCode())){
            			jpsn.setWhoPrice(jpstt.getPrice());
            			break;        			
            		}else{
            			jpsn.setWhoPrice(new BigDecimal(0.00));
            		}
            	}
        	}else{
        		jpsn.setWhoPrice(new BigDecimal(0.00));
        	}        	
        	tempList.add(jpsn);
        }

//        for(Object t : tempList){
//        	jpsn = (JpmProductSaleNew)t;
//        	System.out.println(jpsn.getJpmProduct().getProductNo()+"\t"+jpsn.getJpmProduct().getProductName()+"\t"+jpsn.getWhoPrice());
//        }
        jpmProductSales = tempList;
//        System.out.println("====================================");
//        for(Object t : jpmProductSales){
//        	jpsn = (JpmProductSaleNew)t;
//        	System.out.println(jpsn.getJpmProduct().getProductNo()+"\t"+jpsn.getJpmProduct().getProductName()+"\t"+jpsn.getWhoPrice());
//        }
        return new ModelAndView(view, Constants.JPMPRODUCTSALE_LIST, jpmProductSales);
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
					log.info("同步商品错误："+jpmProductSale.getProductName());
				}
			}
		}
		
		
	}
}
