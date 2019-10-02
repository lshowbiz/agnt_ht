
package com.joymain.jecs.po.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.po.model.JpoProductNumLimit;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpoProductNumLimitDao extends Dao {

    /**
     * Retrieves all of the jpoProductNumLimits
     */
    public List getJpoProductNumLimits(JpoProductNumLimit jpoProductNumLimit);

    /**
     * Gets jpoProductNumLimit's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jpoProductNumLimit's id
     * @return jpoProductNumLimit populated jpoProductNumLimit object
     */
    public JpoProductNumLimit getJpoProductNumLimit(final Long id);

    /**
     * Saves a jpoProductNumLimit's information
     * @param jpoProductNumLimit the object to be saved
     */    
    public void saveJpoProductNumLimit(JpoProductNumLimit jpoProductNumLimit);

    /**
     * Removes a jpoProductNumLimit from the database by id
     * @param id the jpoProductNumLimit's id
     */
    public void removeJpoProductNumLimit(final Long id);
    //added for getJpoProductNumLimitsByCrm
    public List getJpoProductNumLimitsByCrm(CommonRecord crm, Pager pager);
    
    public JpoProductNumLimit getByProductNo(String productNo);
    
    public List<JpoProductNumLimit>  getAll();
    
   public JpoProductNumLimit getNum(String productNo);
    
    public List<JpoProductNumLimit> getAllPros();
}

