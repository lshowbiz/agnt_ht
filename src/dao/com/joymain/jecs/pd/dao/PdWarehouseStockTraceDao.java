
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdWarehouseStockTrace;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdWarehouseStockTraceDao extends Dao {

    /**
     * Retrieves all of the pdWarehouseStockTraces
     */
    public List getPdWarehouseStockTraces(PdWarehouseStockTrace pdWarehouseStockTrace);

    /**
     * Gets pdWarehouseStockTrace's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param uniNo the pdWarehouseStockTrace's uniNo
     * @return pdWarehouseStockTrace populated pdWarehouseStockTrace object
     */
    public PdWarehouseStockTrace getPdWarehouseStockTrace(final Long uniNo);

    /**
     * Saves a pdWarehouseStockTrace's information
     * @param pdWarehouseStockTrace the object to be saved
     */    
    public void savePdWarehouseStockTrace(PdWarehouseStockTrace pdWarehouseStockTrace);

    /**
     * Removes a pdWarehouseStockTrace from the database by uniNo
     * @param uniNo the pdWarehouseStockTrace's uniNo
     */
    public void removePdWarehouseStockTrace(final Long uniNo);
    //added for getPdWarehouseStockTracesByCrm
    public List getPdWarehouseStockTracesByCrm(CommonRecord crm, Pager pager);

	public List getBeginStocksByCrm(CommonRecord crm);

	public List getEndStocksByCrm(CommonRecord crm);

	public List getPdWarehouseStockTraceStaticByCrm(CommonRecord crm,
			String actionType);
	public List getPdWarehouseStockTraceStaticByCrm(CommonRecord crm);
	
	public List getStockTraceDetailByCrm(CommonRecord crm);

	/**
	 * 判断发货退回单是否已经做过入库的操作
	 * @author modify fu 2015-12-26 
	 * @param rpNo
	 * @param productNo
	 * @return boolean true 表明发货退回单已经做过入库的操作了
	 */
	public boolean getPdReturnPurhcaseChangeStock(String rpNo,String productNo);
	
}

