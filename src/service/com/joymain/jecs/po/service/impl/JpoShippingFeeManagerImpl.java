
package com.joymain.jecs.po.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.dao.PdShipFeeDao;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderFee;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.model.JpoShippingFee;
import com.joymain.jecs.po.dao.JpoShippingFeeDao;
import com.joymain.jecs.po.service.JpoShippingFeeManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
public class JpoShippingFeeManagerImpl extends BaseManager implements JpoShippingFeeManager {
    private JpoShippingFeeDao dao;
    private PdShipFeeDao pdShipFeeDao;
    
    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpoShippingFeeDao(JpoShippingFeeDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.po.service.JpoShippingFeeManager#getJpoShippingFees(com.joymain.jecs.po.model.JpoShippingFee)
     */
    public List getJpoShippingFees(final JpoShippingFee jpoShippingFee) {
        return dao.getJpoShippingFees(jpoShippingFee);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoShippingFeeManager#getJpoShippingFee(String jpsId)
     */
    public JpoShippingFee getJpoShippingFee(final String jpsId) {
        return dao.getJpoShippingFee(new Long(jpsId));
    }

    /**
     * @see com.joymain.jecs.po.service.JpoShippingFeeManager#saveJpoShippingFee(JpoShippingFee jpoShippingFee)
     */
    public void saveJpoShippingFee(JpoShippingFee jpoShippingFee) {
        dao.saveJpoShippingFee(jpoShippingFee);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoShippingFeeManager#removeJpoShippingFee(String jpsId)
     */
    public void removeJpoShippingFee(final String jpsId) {
        dao.removeJpoShippingFee(new Long(jpsId));
    }
    //added for getJpoShippingFeesByCrm
    public List getJpoShippingFeesByCrm(CommonRecord crm, Pager pager){
	return dao.getJpoShippingFeesByCrm(crm,pager);
    }
    
    
	public BigDecimal getFee(JpoShippingFee fee,BigDecimal totle){
		BigDecimal shippingFee = new BigDecimal(0);
		if(totle.compareTo(new BigDecimal(0)) <= 0){
			shippingFee = new BigDecimal(0);
		}else if(totle.compareTo(fee.getShippingWeight()) <= 0){
			shippingFee = fee.getShippingFee();
		}else{
			shippingFee = fee.getShippingFee().add((totle.setScale(0, RoundingMode.UP).subtract(fee.getShippingWeight())).divide(fee.getShippingReweight(),2).setScale(0,RoundingMode.UP).multiply(fee.getShippingRefee()));
		}
		return shippingFee;
	}
	
	public BigDecimal getFeeV(JpoShippingFee fee,BigDecimal totle){
		BigDecimal shippingFee = new BigDecimal(0);
		if(totle.compareTo(new BigDecimal(0)) <= 0){
			shippingFee = new BigDecimal(0);
		}else{
			shippingFee = fee.getShippingFee().add((totle.setScale(0, RoundingMode.UP)).divide(fee.getShippingReweight(),2).setScale(0,RoundingMode.UP).multiply(fee.getShippingRefee()));
		}
		return shippingFee;
	}
	
	public BigDecimal getFeeWZ(JpoShippingFee fee,BigDecimal totle){
		BigDecimal shippingFee = new BigDecimal(0);
		if(totle.compareTo(new BigDecimal(0)) <= 0){
			shippingFee = new BigDecimal(0);
		}else{
			shippingFee = fee.getShippingFee().add((totle.setScale(0, RoundingMode.UP)).multiply(fee.getShippingRefee()));
		}
		return shippingFee;
	}
	
	  /**
     * 计算物流费
     * @param jpoShippingFee
     * @param sum 
     * @param dtProduct
     * @param request
     * @return
     */
    public JpoMemberOrderFee shippingFeeCalc(JpoMemberOrder jpoMemberOrder, Set jpoMemberOrderSet, HttpServletRequest request){
    	
    	JpoMemberOrderFee fee = null;
    	
    	if("CN".equals(jpoMemberOrder.getCompanyCode())){
    		
    		String orderType = jpoMemberOrder.getOrderType();
    		String provinceCode = jpoMemberOrder.getProvince();
    		
    		if(orderType.equals("1") || orderType.equals("2") || orderType.equals("5") || orderType.equals("12")){
    			JpoMemberOrderFee jpoMemberOrderFee = new JpoMemberOrderFee();
    			HashMap<String, BigDecimal> feeMap = pdShipFeeDao.getFeeMap();
    			BigDecimal feeB = new BigDecimal(0);
    			feeB = feeMap.get(provinceCode) != null ? feeMap.get(provinceCode) : new BigDecimal(0);
    			log.info("fistOrder " +jpoMemberOrder.getMemberOrderNo()+ " feeB is-------:" + feeB);
    			
    			jpoMemberOrder.setConsumerAmount(feeB);
    			jpoMemberOrderFee.setFee(feeB); 
    			jpoMemberOrderFee.setFeeType("1");//物流费
    			jpoMemberOrderFee.setDetailType("Province");
    			jpoMemberOrderFee.setJpoMemberOrder(jpoMemberOrder);
        		return jpoMemberOrderFee;
    		}
    		
    		BigDecimal $sp = new BigDecimal(0);
    		Iterator<JpoMemberOrderList> its1 = jpoMemberOrderSet.iterator();
    		BigDecimal sumWeight = new BigDecimal(0);
			BigDecimal sumVol = new BigDecimal(0);
			BigDecimal sumWeightZ = new BigDecimal(0);
			String shippingType = "";
			JpoShippingFee jpoShippingFee = new JpoShippingFee();
			
    		
        	while (its1.hasNext()) {
        		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();

        		JpmProductSaleNew product = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew();
        		BigDecimal j_weigth = product.getWeight().multiply(new BigDecimal(jpoMemberOrderList.getQty()));
    			BigDecimal j_vol = product.getVolume().multiply(new BigDecimal(jpoMemberOrderList.getQty()));
    			BigDecimal weight =new BigDecimal(0);
    			BigDecimal vol = new BigDecimal(0);
    			
    			
    			log.info("j_weigth==["+j_weigth+"] and j_vol =["+j_vol+"]");
    			
    			if(j_weigth ==null && "".equals("j_weigth")){
    				throw new AppException("商品重量未设置。");
    			}else{
    				weight = j_weigth;
    			}
    			
    			if(j_vol ==null && "".equals("j_vol")){
    				throw new AppException("商品体积未设置。");
    			}else{
    				vol = j_vol;
    			}
        		
    			shippingType = product.getLogisticsStrategy();
        		
        		if("".equals(shippingType) || shippingType == null){
        			log.info("所购商品没有指定物流费策略。");
        			return fee;
//        			throw new AppException("所购商品没有指定物流费策略。");
        			
        		}else if(shippingType.equalsIgnoreCase("w")){
            		sumWeight = sumWeight.add(weight);
            		
        		}else if(shippingType.equalsIgnoreCase("v")){
            		sumVol = sumVol.add(vol);
        		}else if(shippingType.equalsIgnoreCase("Z")){
        			sumWeightZ = sumWeightZ.add(weight);
        		}
        		
        	}
        	
        	CommonRecord crm = new CommonRecord();
    		crm.addField("countryCode", jpoMemberOrder.getCountryCode());
    		crm.addField("province", jpoMemberOrder.getProvince());
    		crm.addField("city", jpoMemberOrder.getCity());
    		crm.addField("district", jpoMemberOrder.getDistrict());
    		if(sumWeight.compareTo(new BigDecimal(0))==1){
    			crm.addField("shippingType","W");
        		List jpoShippingFeeTemps = this.getJpoShippingFeesByCrm(crm, new Pager(request,0));
        		if(jpoShippingFeeTemps.size()>0){
        			jpoShippingFee = (JpoShippingFee)jpoShippingFeeTemps.get(0);
        			$sp =  $sp.add(this.getFee(jpoShippingFee,sumWeight));
        		}else{
        			return fee;
//        			throw new AppException("该地区快递策略未设置。");
        		}
    		}
    		if(sumVol.compareTo(new BigDecimal(0))==1){
    			crm.addField("shippingType","V");
        		List jpoShippingFeeTemps = this.getJpoShippingFeesByCrm(crm, new Pager(request,0));
        		if(jpoShippingFeeTemps.size()>0){
        			jpoShippingFee = (JpoShippingFee)jpoShippingFeeTemps.get(0);
        			$sp =  $sp.add(this.getFeeV(jpoShippingFee,sumVol));
        		}else{
        			return fee;
//        			throw new AppException("该地区零担策略未设置。");
        		}
    		}
    		if(sumWeightZ.compareTo(new BigDecimal(0))==1){
    			crm.addField("shippingType","Z");
        		List jpoShippingFeeTemps = this.getJpoShippingFeesByCrm(crm, new Pager(request,0));
        		if(jpoShippingFeeTemps.size()>0){
        			jpoShippingFee = (JpoShippingFee)jpoShippingFeeTemps.get(0);
        			$sp =  $sp.add(this.getFeeWZ(jpoShippingFee,sumWeightZ));
        		}else{
        			return fee;
//        			throw new AppException("该地区快递策略未设置。");
        		}
    		}
    		
    		JpoMemberOrderFee jpoMemberOrderFee = new JpoMemberOrderFee();
			jpoMemberOrder.setConsumerAmount($sp);
			jpoMemberOrderFee.setFee($sp);  // 是存 $sp 还是存 0 ??? (暂存$sp)
			jpoMemberOrderFee.setFeeType("1");//物流费
			jpoMemberOrderFee.setDetailType("WVZ");
			jpoMemberOrderFee.setJpoMemberOrder(jpoMemberOrder);
    		return jpoMemberOrderFee;

    	}else if("HK".equals(jpoMemberOrder.getCompanyCode())){
    		JpoMemberOrderFee jpoMemberOrderFee = new JpoMemberOrderFee();
    		jpoMemberOrderFee.setJpoMemberOrder(jpoMemberOrder);
    		jpoMemberOrderFee.setFeeType("1");//物流费
    		jpoMemberOrderFee.setDetailType("0000");
    		jpoMemberOrder.setConsumerAmount(new BigDecimal(0));
    		return jpoMemberOrderFee;
    	}
    	return null;
    }

	public PdShipFeeDao getPdShipFeeDao() {
		return pdShipFeeDao;
	}

	public void setPdShipFeeDao(PdShipFeeDao pdShipFeeDao) {
		this.pdShipFeeDao = pdShipFeeDao;
	}
    
}
