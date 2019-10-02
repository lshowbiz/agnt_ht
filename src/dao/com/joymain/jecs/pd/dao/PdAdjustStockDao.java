
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdAdjustStock;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdAdjustStockDao extends Dao {

    /**
     * Retrieves all of the pdAdjustStocks
     */
    public List getPdAdjustStocks(PdAdjustStock pdAdjustStock);

    /**
     * Gets pdAdjustStock's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param asNo the pdAdjustStock's asNo
     * @return pdAdjustStock populated pdAdjustStock object
     */
    public PdAdjustStock getPdAdjustStock(final String asNo);

    /**
     * Saves a pdAdjustStock's information
     * @param pdAdjustStock the object to be saved
     */    
    public void savePdAdjustStock(PdAdjustStock pdAdjustStock);

    /**
     * Removes a pdAdjustStock from the database by asNo
     * @param asNo the pdAdjustStock's asNo
     */
    public void removePdAdjustStock(final String asNo);
    //added for getPdAdjustStocksByCrm
    public List getPdAdjustStocksByCrm(CommonRecord crm, Pager pager);

	public List getSumGroupByAst(CommonRecord crm);
}

