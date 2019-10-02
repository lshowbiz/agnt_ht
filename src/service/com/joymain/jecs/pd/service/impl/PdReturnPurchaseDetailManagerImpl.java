
package com.joymain.jecs.pd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdReturnPurchaseDetail;
import com.joymain.jecs.pd.dao.PdReturnPurchaseDetailDao;
import com.joymain.jecs.pd.service.PdReturnPurchaseDetailManager;
import com.joymain.jecs.pm.model.JpmProduct;
import com.joymain.jecs.pm.service.JpmProductManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
public class PdReturnPurchaseDetailManagerImpl extends BaseManager implements PdReturnPurchaseDetailManager {
    private PdReturnPurchaseDetailDao dao;
    private JpmProductManager jpmProductManager;
    
    public void setJpmProductManager(JpmProductManager jpmProductManager) {
		this.jpmProductManager = jpmProductManager;
	}
    
	public List getTotalPdReturnPurchaseDetails(CommonRecord crm) {
		// TODO Auto-generated method stub
    	return dao.getTotalPdReturnPurchaseDetails(crm);
	}
	public boolean existPdReturnPurchaseDetail(String productNo, String rpNo) {
		// TODO Auto-generated method stub
		List list = dao.getPdReturnPurchaseDetail(productNo, rpNo);
		if(list.size()>0){
			return true;
		}else{
			return false;
		}
		
	}
    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdReturnPurchaseDetailDao(PdReturnPurchaseDetailDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdReturnPurchaseDetailManager#getPdReturnPurchaseDetails(com.joymain.jecs.pd.model.PdReturnPurchaseDetail)
     */
    public List getPdReturnPurchaseDetails(final PdReturnPurchaseDetail pdReturnPurchaseDetail) {
        return dao.getPdReturnPurchaseDetails(pdReturnPurchaseDetail);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdReturnPurchaseDetailManager#getPdReturnPurchaseDetail(String uniNo)
     */
    public PdReturnPurchaseDetail getPdReturnPurchaseDetail(final String uniNo) {
        return dao.getPdReturnPurchaseDetail(new Long(uniNo));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdReturnPurchaseDetailManager#savePdReturnPurchaseDetail(PdReturnPurchaseDetail pdReturnPurchaseDetail)
     */
    public void savePdReturnPurchaseDetail(PdReturnPurchaseDetail pdReturnPurchaseDetail) {
    	//------modify fu 20150820 赋值ERP商品编码--------------------begin
    	String erpProductNoDBA = pdReturnPurchaseDetail.getErpProductNo();
    	if(StringUtil.isEmpty(erpProductNoDBA)){
	    	String productNo = pdReturnPurchaseDetail.getProductNo();
	    	if(!StringUtil.isEmpty(productNo)){
	    		JpmProduct jpmProduct = jpmProductManager.getJpmProduct(productNo);
	    		if(null!=jpmProduct){
	    			String erpProductNo = jpmProduct.getErpProductNo();
	    			pdReturnPurchaseDetail.setErpProductNo(erpProductNo);
	    		}
	    	}
    	}
    	//-------modify fu 20150820 赋值ERP商品编码--------------------end
        dao.savePdReturnPurchaseDetail(pdReturnPurchaseDetail);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdReturnPurchaseDetailManager#removePdReturnPurchaseDetail(String uniNo)
     */
    public void removePdReturnPurchaseDetail(final String uniNo) {
        dao.removePdReturnPurchaseDetail(new Long(uniNo));
    }
    //added for getPdReturnPurchaseDetailsByCrm
    public List getPdReturnPurchaseDetailsByCrm(CommonRecord crm, Pager pager){
	return dao.getPdReturnPurchaseDetailsByCrm(crm,pager);
    }
}
