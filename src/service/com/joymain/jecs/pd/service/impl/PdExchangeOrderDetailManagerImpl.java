
package com.joymain.jecs.pd.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;

import com.joymain.jecs.Constants;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdExchangeOrderDetail;
import com.joymain.jecs.pd.dao.PdExchangeOrderDetailDao;
import com.joymain.jecs.pd.service.PdExchangeOrderDetailManager;
import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.pm.service.JpmProductSaleManager;
import com.joymain.jecs.po.dao.JpoMemberOrderDao;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
public class PdExchangeOrderDetailManagerImpl extends BaseManager implements PdExchangeOrderDetailManager {
    private PdExchangeOrderDetailDao dao;
    private JpoMemberOrderDao jpoMemberOrderDao;
	private JpmProductSaleManager jpmProductSaleManager;


    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdExchangeOrderDetailDao(PdExchangeOrderDetailDao dao) {
        this.dao = dao;
    }

    public void setJpoMemberOrderDao(JpoMemberOrderDao jpoMemberOrderDao) {
		this.jpoMemberOrderDao = jpoMemberOrderDao;
	}
    
    public void setJpmProductSaleManager(JpmProductSaleManager jpmProductSaleManager) {
		this.jpmProductSaleManager = jpmProductSaleManager;
	}
    
    /**
     * @see com.joymain.jecs.pd.service.PdExchangeOrderDetailManager#getPdExchangeOrderDetails(com.joymain.jecs.pd.model.PdExchangeOrderDetail)
     */
    public List getPdExchangeOrderDetails(final PdExchangeOrderDetail pdExchangeOrderDetail) {
        return dao.getPdExchangeOrderDetails(pdExchangeOrderDetail);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdExchangeOrderDetailManager#getPdExchangeOrderDetail(String uniNo)
     */
    public PdExchangeOrderDetail getPdExchangeOrderDetail(final String uniNo) {
        return dao.getPdExchangeOrderDetail(new Long(uniNo));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdExchangeOrderDetailManager#savePdExchangeOrderDetail(PdExchangeOrderDetail pdExchangeOrderDetail)
     */
    public void savePdExchangeOrderDetail(PdExchangeOrderDetail pdExchangeOrderDetail) {
        dao.savePdExchangeOrderDetail(pdExchangeOrderDetail);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdExchangeOrderDetailManager#removePdExchangeOrderDetail(String uniNo)
     */
    public void removePdExchangeOrderDetail(final String uniNo) {
        dao.removePdExchangeOrderDetail(new Long(uniNo));
    }
    //added for getPdExchangeOrderDetailsByCrm
    public List getPdExchangeOrderDetailsByCrm(CommonRecord crm, Pager pager){
	return dao.getPdExchangeOrderDetailsByCrm(crm,pager);
    }

    /**
     * @author gw 2015-03-03
     * @param pdExchangeOrderDetail
     * @return pdExchangeOrderDetail
     */
	public PdExchangeOrderDetail getPdExchangeOrderDetailForEP(PdExchangeOrderDetail pdExchangeOrderDetail) {
		return dao.getPdExchangeOrderDetailForEP(pdExchangeOrderDetail);
	}

	/**
	 * 换货单获取换货商品信息
	 * @author gw 2015-05-28
	 * @param orderNo
	 * @return pdExchangeOrderDetails
	 */
	public Set<PdExchangeOrderDetail> getPdExchangeOrderDetailForOrderNo(String orderNo) {
		Set<PdExchangeOrderDetail> pdExchangeOrderDetails = new HashSet<PdExchangeOrderDetail>();
		try{
			JpoMemberOrder jpoMemberOrder = jpoMemberOrderDao.getJpoMemberOrderByInterface(orderNo);
			List products = jpmProductSaleManager.getExProductSales(jpoMemberOrder.getCompanyCode(),jpoMemberOrder.getOrderType(), jpoMemberOrder.getTeamCode());//默认取值中脉
			for (int i = 0; i < products.size(); i++) {
				JpmProductSaleTeamType jpmProductSaleTeamType = (JpmProductSaleTeamType) products.get(i);
				PdExchangeOrderDetail pdExchangeOrderDetail = new PdExchangeOrderDetail();
				pdExchangeOrderDetail.setQty(new Long(0));
				pdExchangeOrderDetail.setProductNo(jpmProductSaleTeamType.getJpmProductSaleNew().getJpmProduct().getProductNo());
				pdExchangeOrderDetail.setPrice(jpmProductSaleTeamType.getPrice());
				pdExchangeOrderDetail.setPv(jpmProductSaleTeamType.getPv());
				pdExchangeOrderDetail.setErpProductNo(jpmProductSaleTeamType.getJpmProductSaleNew().getJpmProduct().getErpProductNo());
				pdExchangeOrderDetails.add(pdExchangeOrderDetail);
			}
		}catch(Exception e){
			log.info("换货单获取换货商品信息失败--------------"+e.toString());
			e.printStackTrace();
		}
		return pdExchangeOrderDetails;
	}

	/**
	 * 换货明细不为空的校验
	 * @author gw 2015-07-06
	 * @param pdExchangeOrderDetail
	 * @param errors
	 * @param characterCoding
	 * @return String
	 */
	public String getEmptyCheck(PdExchangeOrderDetail pdExchangeOrderDetail,BindException errors, String characterCoding) {
		String returnS = "";
		if(StringUtil.isEmpty(pdExchangeOrderDetail.getProductNo())){
			//errors.rejectValue("productNo", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "jpmProductSaleNew.productNo") },this.getCharacterValue(characterCoding, "isNotNull"));
			//return true;
			 returnS = this.getCharacterValue(characterCoding, "jpmProductSaleNew.productNo")+this.getCharacterValue(characterCoding, "isNotNull");
			 return this.getStringResult(returnS);
		}
		
		if(null==pdExchangeOrderDetail.getPrice()){
		    returnS = this.getCharacterValue(characterCoding, "pd.price")+this.getCharacterValue(characterCoding, "isNotNull");
			return this.getStringResult(returnS);
		}
		
		if(null==pdExchangeOrderDetail.getPv()){
			 returnS = this.getCharacterValue(characterCoding, "PV")+this.getCharacterValue(characterCoding, "isNotNull");
			 return this.getStringResult(returnS);
		}
		
		if(null==pdExchangeOrderDetail.getQty()){
		    returnS = this.getCharacterValue(characterCoding, "pd.qty")+this.getCharacterValue(characterCoding, "isNotNull");
		     return this.getStringResult(returnS);
		}
		
		
		//modify by lihao ,除EC外所以的系统均去除ERP 商品编码的验证
		/*
		if(StringUtil.isEmpty(pdExchangeOrderDetail.getErpProductNo())){
			 returnS = this.getCharacterValue(characterCoding, "jpmProductSaleNew.productNo")+this.getCharacterValue(characterCoding, "isNotNull");
		     return "erp"+this.getStringResult(returnS);
		}
		*/
		
		return returnS;
	}
	
	/**
	 * @author gw 2013-09-10
	 * @param characterCoding
	 * @param msgKey
	 * @return String
	 */
	private String getCharacterValue(String characterCoding,String msgKey){
		String characterValue=(String) Constants.localLanguageMap.get(characterCoding).get(msgKey);
		if(!StringUtils.isEmpty(characterValue)){
			return characterValue;
		}else{
			return msgKey;
		}
	}
	
	public String getStringResult(String returnS){
		if(!StringUtil.isEmpty(returnS)){
			if((-1)!= (returnS.indexOf("{0}"))){
		    	String  rs= returnS.substring(0, returnS.indexOf("{0}"))+returnS.substring(returnS.indexOf("{0}")+3, returnS.length());
		        return rs;
		    }
		}
		return "";
	}
}
