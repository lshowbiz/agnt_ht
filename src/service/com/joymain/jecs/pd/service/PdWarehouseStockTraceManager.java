
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdWarehouseStockTrace;
import com.joymain.jecs.pd.dao.PdWarehouseStockTraceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdWarehouseStockTraceManager extends Manager {
	
	
	public List getPdWarehouseStockReportByCrm(CommonRecord crm);
	public List getPdWarehouseStockTraceStaticByCrm(CommonRecord crm,String actionType);
    /**
     * Retrieves all of the pdWarehouseStockTraces
     */
    public List getPdWarehouseStockTraces(PdWarehouseStockTrace pdWarehouseStockTrace);

    /**
     * Gets pdWarehouseStockTrace's information based on uniNo.
     * @param uniNo the pdWarehouseStockTrace's uniNo
     * @return pdWarehouseStockTrace populated pdWarehouseStockTrace object
     */
    public PdWarehouseStockTrace getPdWarehouseStockTrace(final String uniNo);

    /**
     * Saves a pdWarehouseStockTrace's information
     * @param pdWarehouseStockTrace the object to be saved
     */
    public void savePdWarehouseStockTrace(PdWarehouseStockTrace pdWarehouseStockTrace);

    /**
     * Removes a pdWarehouseStockTrace from the database by uniNo
     * @param uniNo the pdWarehouseStockTrace's uniNo
     */
    public void removePdWarehouseStockTrace(final String uniNo);
    //added for getPdWarehouseStockTracesByCrm
    public List getPdWarehouseStockTracesByCrm(CommonRecord crm, Pager pager);
    
    public List getBeginStocksByCrm(CommonRecord crm);
    public List getEndStocksByCrm(CommonRecord crm);
    
    public List getStockTraceDetailByCrm(CommonRecord crm);
    public List getPbWarehouseStocksTraceFinceReport(CommonRecord crm);
}

