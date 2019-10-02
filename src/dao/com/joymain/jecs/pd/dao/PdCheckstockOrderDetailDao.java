
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdCheckstockOrderDetail;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdCheckstockOrderDetailDao extends Dao {

    /**
     * Retrieves all of the pdCheckstockOrderDetails
     */
    public List getPdCheckstockOrderDetails(PdCheckstockOrderDetail pdCheckstockOrderDetail);

    /**
     * Gets pdCheckstockOrderDetail's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param uniNo the pdCheckstockOrderDetail's uniNo
     * @return pdCheckstockOrderDetail populated pdCheckstockOrderDetail object
     */
    public PdCheckstockOrderDetail getPdCheckstockOrderDetail(final Long uniNo);

    /**
     * Saves a pdCheckstockOrderDetail's information
     * @param pdCheckstockOrderDetail the object to be saved
     */    
    public void savePdCheckstockOrderDetail(PdCheckstockOrderDetail pdCheckstockOrderDetail);

    /**
     * Removes a pdCheckstockOrderDetail from the database by uniNo
     * @param uniNo the pdCheckstockOrderDetail's uniNo
     */
    public void removePdCheckstockOrderDetail(final Long uniNo);
    //added for getPdCheckstockOrderDetailsByCrm
    public List getPdCheckstockOrderDetailsByCrm(CommonRecord crm, Pager pager);
}

