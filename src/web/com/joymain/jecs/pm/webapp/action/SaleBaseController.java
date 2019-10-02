package com.joymain.jecs.pm.webapp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pm.model.JpmPresentPorductBind;
import com.joymain.jecs.pm.model.JpmProduct;
import com.joymain.jecs.pm.model.JpmSalePromoter;
import com.joymain.jecs.pm.model.JpmSalepromoterPro;
import com.joymain.jecs.pm.service.JpmProductManager;
import com.joymain.jecs.pm.service.JpmSalePromoterManager;
import com.joymain.jecs.pm.service.JpmSalepromoterProManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

public class SaleBaseController  extends BaseController{
	
	protected final Log log = LogFactory.getLog(SaleBaseController.class);
	protected JpmProductManager jpmProductManager;
	protected JpmSalepromoterProManager jpmSalepromoterProManager;
	protected JpmSalePromoterManager jpmSalePromoterManager;
	
	/**
     * 转向 绑定产品页面
     * @param request
     * @return 
     */
	protected ModelAndView showproductPage(HttpServletRequest request){
		String spno = request.getParameter("spno");
		String strAction = request.getParameter("strAction");
		String servletPath = request.getServletPath();
		String flag = "";
		if(StringUtils.isNotBlank(strAction)){
			if(strAction.equalsIgnoreCase("showOrderPVBindPage")){
				//促销按订单总金额或PV
				strAction = "presintorderPV";
				flag = "searchProductPV";
			}else if(strAction.equalsIgnoreCase("showproductPage")){
				//折扣
				strAction = "bindProduct";
				flag = "searchProduct";
			}else if(strAction.equalsIgnoreCase("showPresentPage")){
				strAction = "presentBindProduct";
				flag = "searchProductPresent";
			}
		}
		
		CommonRecord crm = new CommonRecord();
		Pager pager = new Pager("jpmProductListTable",request, 20);
		List<JpmProduct> productList = jpmProductManager.getJpmProductsByCrm(crm, pager);
		
		request.setAttribute("strAction", strAction);
		request.setAttribute("servletPath", servletPath);
		request.setAttribute("spno", spno);
		request.setAttribute("flag", flag);
		log.info("showProductPage servletPath is:"+servletPath+" and flag is:["+flag+"]");
		
		return new ModelAndView("pm/jpmBindProductList","jpmBindProductList", productList);
	}
	
	/**
     * 关联产品功能
     * @param request
     * @return
     */
    protected ModelAndView bindProduct(HttpServletRequest request){
    	String proids = request.getParameter("allproId");
    	String pronums = request.getParameter("allproNum");
    	String teams = request.getParameter("teams");
    	String companys = request.getParameter("companys");
    	String orderTypes = request.getParameter("orderTypes");
    	String spno = request.getParameter("spno");
    	String servletPath = request.getServletPath();
    	
    	log.info("spno is:["+spno +"] and teams is:["+teams+"]" +
    			" and companys is:["+companys+"] and orderType is:["+orderTypes+"]"+
    			" and servletPath:["+servletPath+"] and proids is:["+proids+"]");
    	try{
    		String[] proArr = proids.split(",");
    		String[] numArr = pronums.split(",");
        	for(int i=1; i<proArr.length; i++){
        		String prono = proArr[i];
        		JpmSalepromoterPro saPro = jpmSalepromoterProManager.
        				getJpmSaleProByproductId(spno, prono);
//            	if(saPro !=null) continue;//避免同一策略关联多个相同产品的问题
            	
            	if(saPro !=null){
            		for (int a = 1; a < numArr.length; a++) {
                    	String numNo = numArr[a];
                    	String no = numNo.substring(numNo.indexOf("&")+1 , numNo.length());
                    	String num = numNo.substring(0, numNo.indexOf("&"));
                    	log.info("numNo: "+numNo +" no: " +no +" num: " + num + " prono: " +prono);
                    	if(no.equals(prono)){
                    		saPro.setPronum(new Long(num));
                    	}
    				}
            		jpmSalepromoterProManager.saveJpmSalepromoterPro(saPro);
            		
            	}else{
            		
	            	JpmSalepromoterPro pp = new JpmSalepromoterPro();
	                pp.setSpno(Long.parseLong(spno));
	                //pp.setPttid(Long.parseLong(proArr[i]));
	                pp.setProno(prono);
	                for (int j = 1; j < numArr.length; j++) {
	                	String numNo = numArr[j];
	                	String no = numNo.substring(numNo.indexOf("&")+1 , numNo.length());
	                	String num = numNo.substring(0, numNo.indexOf("&"));
	                	if(no.equals(prono)){
	                		pp.setPronum(new Long(num));
	                	}
					}         
	                jpmSalepromoterProManager.saveJpmSalepromoterPro(pp);
            	}
        	}
    	}catch(Exception e){
    		log.error("",e);
    	}
    	return new ModelAndView("redirect:"+servletPath);
    }
    /**
     * 转向已关联的产品列表
     * @return
     */
    protected ModelAndView showSaleProductList(HttpServletRequest request){
    	String spno = request.getParameter("spno");
    	String servletPath = request.getServletPath();
    	String strAction = request.getParameter("strAction");
    	String urlPage = "";
    	List<JpmPresentPorductBind> productList = 
    				new ArrayList<JpmPresentPorductBind>();
    	try{
    		if(StringUtils.isNotBlank(strAction)){
    			if(strAction.equalsIgnoreCase("showOrderPVList")){
    				strAction = "unBindOrderPV";
    			} else if(strAction.equalsIgnoreCase("showDisountProductList")){
    				strAction= "unBindSalePromoter";
    			} else if(strAction.equalsIgnoreCase("showPresentList")){
    				strAction = "unBindSalePresent";
    			}
    		}
    		log.info("showPresent spno is:"+spno+" and sevletPath is:"+servletPath);
        	JpmSalePromoter sp = jpmSalePromoterManager.getJpmSalePromoter(spno);
        	Iterator<JpmSalepromoterPro> spIterator = sp.getSaleProductSet().iterator();
        	
        	log.info("sproSet size is:"+sp.getSaleProductSet().size());
        	
        	while(spIterator.hasNext()){
        		JpmSalepromoterPro salePro = spIterator.next();
        		JpmPresentPorductBind pb = new JpmPresentPorductBind();
        		JpmProduct product = jpmProductManager.getJpmProduct(salePro.getProno());
        		
        		pb.setPresentName(product.getProductName());
        		pb.setPresentNo(product.getProductNo());
        		pb.setId(salePro.getId());
        		//pb.setPrice(product.get);
        		if(salePro.getPronum().equals("") && salePro.getPronum() == null){
        			pb.setSpNum(0);
        		}else{
        			pb.setSpNum(Integer.parseInt(salePro.getPronum().toString())); // spNum 赠品数量	
        		}
        		productList.add(pb);
        	}
        	log.info("presentPorductBind productList size is:"+productList.size());
        	request.setAttribute("sp", sp);
        	request.setAttribute("servletPath", servletPath);
        	request.setAttribute("strAction", strAction);

    	}catch(Exception e){
    		log.error("",e);
    	}
    	return new ModelAndView("pm/presentProductList" ,"presentProductList",productList);
    }
    /**
     * 解除绑定商品
     * @param request
     * @return
     */
    protected ModelAndView unBindProduct(HttpServletRequest request){
    	String ids = request.getParameter("idAll");
    	String spno = request.getParameter("spno");
    	String selvletPath = request.getServletPath();
    	String strAction = request.getParameter("strAction");
    	String url = "";
    	try{
    		if(StringUtils.isNotBlank(strAction)){
        		if(strAction.equalsIgnoreCase("unBindOrderPV")){
        			strAction = "showOrderPVList";
        		} else if(strAction.equalsIgnoreCase("unBindSalePresent")){
        			strAction = "showPresentList";
        		} else if(strAction.equalsIgnoreCase("unBindSalePromoter")){
        			strAction = "showDisountProductList";
        		}
        	}
        	String[] idArr = ids.split(",");
        	log.info("delete salepromoterPro id is:"+ids);
        	for(int i=1; i<idArr.length ;i++){
        		jpmSalepromoterProManager.removeJpmSalepromoterPro(idArr[i]); 
        	}
        	url = "redirect:"+selvletPath+"?strAction="+strAction+"&spno="+spno;
        	
        	log.info("redirect url is:"+url);
    	}catch(Exception e){
    		log.error("",e);
    	}
    	
    	return new ModelAndView(url);
    }
   
    /**
     * 删除赠品策略功能
     * @param request
     * @return
     */
    protected ModelAndView deleteJpmSalePro(HttpServletRequest request){
    	String spnos = request.getParameter("spnos");
    	String servletPath = request.getServletPath();
    	
    	String[] spnoArr = spnos.split(",");
    	log.info("spnoArr is:"+spnos);
    	try{
    		for(int i=1; i<spnoArr.length; i++){
        		jpmSalePromoterManager.removeJpmSalePromoter(spnoArr[i]);
        	}
    	}catch(Exception e){
    		log.error("",e);
    	}
    	return new ModelAndView("redirect:"+servletPath);
    }
    /**
     * 查询策略
     * @param request
     * @return
     */
    protected ModelAndView searchJpmSalePromoter(HttpServletRequest request){
    	String urlPage = "";
    	List<JpmSalePromoter> jpmSalePromoters = new ArrayList<JpmSalePromoter>();
    	try{
    		CommonRecord crm=RequestUtil.toCommonRecord(request);
            Pager pager = new Pager("jpmSalePromoterListTable",request, 20);
            
            String servletPath = request.getServletPath();
            String stime = request.getParameter("stime");
            String etime = request.getParameter("endTime");
            String discount = request.getParameter("discount");
            String presentno = request.getParameter("presentno");
            String proName = request.getParameter("proName");
            String proNo = request.getParameter("presentno");
            String type = request.getParameter("saleType");
            
        	crm.addField("startime",stime);
        	crm.addField("endtime",etime);
        	crm.addField("discount",discount);
        	crm.addField("presentno", presentno);
        	crm.addField("integral","");
        	crm.addField("proName",proName);
        	crm.addField("proNo",proNo);
        	/*
    		 * 策略类型:
    		 * 1折价促销, 2赠品促销,3积分促销
    		 * 4根据订单总金额或PV送赠品
    		 */
            crm.addField("saleType", type);
            
            jpmSalePromoters = jpmSalePromoterManager.
            		getJpmSalePromotersByCrm(crm,pager);
            
            request.setAttribute("jpmSalePromoterListTable_totalRows", pager.getTotalObjects());
            request.setAttribute("servletPath", servletPath);
            request.setAttribute("stime", stime);
            request.setAttribute("endtime", etime);
            request.setAttribute("presentno", proNo);
            request.setAttribute("proName", proName);
            
            if(servletPath.equalsIgnoreCase("/orderPVPresentSale.html")){
            	urlPage = "pm/jpmSalepresentPVList";
            }
            log.info("conroller to page is:"+urlPage);
    	}catch(Exception e){
    		log.error("",e);
    	}
    	return new ModelAndView(urlPage,Constants.JPMSALEPROMOTER_LIST, jpmSalePromoters);
    }
    /**
     * 绑定商品页面,查询产品功能
     * @param request
     * @return
     */
    protected ModelAndView searchProduct(HttpServletRequest request){
    	String proName = request.getParameter("presentname");
    	String proNo = request.getParameter("presentno");
    	String flag = request.getParameter("flag");
    	String strAction = request.getParameter("strAction");
    	String spno = request.getParameter("spno");
    	
    	CommonRecord crm = new CommonRecord();
    	Pager pager = new Pager("jpmProductListTable",request, 20);
    	
    	crm.addField("productName", proName);
    	crm.addField("productNo", proNo);
    	
    	List<JpmProduct> proList = jpmProductManager.getJpmProductsByCrm(crm, pager);
    	request.setAttribute("servletPath", request.getServletPath());
    	request.setAttribute("strAction", strAction);
    	request.setAttribute("flag", flag);
    	request.setAttribute("proName", proName);
    	request.setAttribute("proNo", proNo);
    	request.setAttribute("spno", spno);
		return new ModelAndView("pm/jpmBindProductList","jpmBindProductList",proList);
    }
    
	public void setJpmProductManager(JpmProductManager jpmProductManager) {
		this.jpmProductManager = jpmProductManager;
	}

	public void setJpmSalepromoterProManager(
			JpmSalepromoterProManager jpmSalepromoterProManager) {
		this.jpmSalepromoterProManager = jpmSalepromoterProManager;
	}

	public JpmProductManager getJpmProductManager() {
		return jpmProductManager;
	}

	public JpmSalepromoterProManager getJpmSalepromoterProManager() {
		return jpmSalepromoterProManager;
	}

	public JpmSalePromoterManager getJpmSalePromoterManager() {
		return jpmSalePromoterManager;
	}

	public void setJpmSalePromoterManager(
			JpmSalePromoterManager jpmSalePromoterManager) {
		this.jpmSalePromoterManager = jpmSalePromoterManager;
	}
	
}
