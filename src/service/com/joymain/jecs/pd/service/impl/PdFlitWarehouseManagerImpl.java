
package com.joymain.jecs.pd.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;

import com.joymain.jecs.Constants;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdFlitWarehouse;
import com.joymain.jecs.pd.model.PdFlitWarehouseDetail;
import com.joymain.jecs.pd.dao.PdFlitWarehouseDao;
import com.joymain.jecs.pd.service.PdFlitWarehouseManager;
import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;

public class PdFlitWarehouseManagerImpl extends BaseManager implements PdFlitWarehouseManager {
	private PdFlitWarehouseDao dao;
	private PdWarehouseStockManager pdWarehouseStockManager;
    public void setPdWarehouseStockManager(
			PdWarehouseStockManager pdWarehouseStockManager) {
		this.pdWarehouseStockManager = pdWarehouseStockManager;
	}

	public void confirmFlitWarehouse(PdFlitWarehouse pdFlitWarehouse,
			String flag) {
		// TODO Auto-generated method stub
    	if("confirm".equals(flag)){
			if("0".equals(pdFlitWarehouse.getStockFlag())){
				
				pdFlitWarehouse.setStockFlag("1");
				pdFlitWarehouse.setOrderFlag(2);
				dao.savePdFlitWarehouse(pdFlitWarehouse);
				pdWarehouseStockManager.updatePdWarehouseStock(pdFlitWarehouse,"out");
			}else{
				throw new AppException("erro.pdflitwarehouse.confirm");
			}
		}else if("arrived".equals(flag)){
			if("1".equals(pdFlitWarehouse.getStockFlag())){
				
				pdFlitWarehouse.setStockFlag("2");
				pdFlitWarehouse.setOrderFlag(3);
				dao.savePdFlitWarehouse(pdFlitWarehouse);
				pdWarehouseStockManager.updatePdWarehouseStock(pdFlitWarehouse,"in");
			}else{
				throw new AppException("erro.pdflitwarehouse.arrived");
			}
		}
	}

	public Long getOnWayCountByWarehouseNo(String warehouseNo, String productNo) {
		// TODO Auto-generated method stub
		return dao.getOnWayCountByWarehouseNo(warehouseNo,productNo);
	}

	public long getProductCountByFwNo(String fwNo) {
		// TODO Auto-generated method stub
		long ret = 0;
		PdFlitWarehouse pdFlitWarehouse = dao.getPdFlitWarehouse(fwNo);
		Set sets = pdFlitWarehouse.getPdFlitWarehouseDetails();
		Iterator iterator =sets.iterator();
		while(iterator.hasNext()){
//			PdFlitWarehouseDetail pdFlitWarehouseDetail = (PdFlitWarehouseDetail) iterator.next();
			ret += ((PdFlitWarehouseDetail) iterator.next()).getQty();
		}
		return ret;
	}

	public Long getSumOnWayByWarehouseNo(CommonRecord crm, String productNo) {
		// TODO Auto-generated method stub
		return dao.getSumOnWayByWarehouseNo(crm, productNo);
	}

	public void updateAmount(String fwNo) {
		// TODO Auto-generated method stub
		
	}
	

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdFlitWarehouseDao(PdFlitWarehouseDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdFlitWarehouseManager#getPdFlitWarehouses(com.joymain.jecs.pd.model.PdFlitWarehouse)
     */
    public List getPdFlitWarehouses(final PdFlitWarehouse pdFlitWarehouse) {
        return dao.getPdFlitWarehouses(pdFlitWarehouse);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdFlitWarehouseManager#getPdFlitWarehouse(String fwNo)
     */
    public PdFlitWarehouse getPdFlitWarehouse(final String fwNo) {
        return dao.getPdFlitWarehouse(new String(fwNo));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdFlitWarehouseManager#savePdFlitWarehouse(PdFlitWarehouse pdFlitWarehouse)
     */
    public void savePdFlitWarehouse(PdFlitWarehouse pdFlitWarehouse) {
        dao.savePdFlitWarehouse(pdFlitWarehouse);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdFlitWarehouseManager#removePdFlitWarehouse(String fwNo)
     */
    public void removePdFlitWarehouse(final String fwNo) {
        dao.removePdFlitWarehouse(new String(fwNo));
    }
    //added for getPdFlitWarehousesByCrm
    public List getPdFlitWarehousesByCrm(CommonRecord crm, Pager pager){
	return dao.getPdFlitWarehousesByCrm(crm,pager);
    }

	public List getFlitDetail(CommonRecord crm) {
		// TODO Auto-generated method stub
		return dao.getFlitDetail(crm);
	}

	/**
     * 录入或编辑之前，不为空的校验
     * @author gw 2015-01-21
     * @param outWarehouseNo
     * @param inWarehouseNo
     * @param errors
     * @param characterCoding
     * @return boolean
     */
	public boolean getEmptyCheckResult(String outWarehouseNo,String inWarehouseNo,BindException errors,String characterCoding){
		
		if(StringUtil.isEmpty(outWarehouseNo)){
			errors.rejectValue("outWarehouse", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "busi.warehouse.outwatehouseno") },this.getCharacterValue(characterCoding, "isNotNull"));
			return true;
		}
		if(StringUtil.isEmpty(inWarehouseNo)){
			errors.rejectValue("inWarehouse", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "busi.pd.enterWarehouseNo") },this.getCharacterValue(characterCoding, "isNotNull"));
			return true;
		}
		
		return false;
	}
	
	/**
	 * @author gw 2015-01-21
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
    
}
