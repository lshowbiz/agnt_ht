
package com.joymain.jecs.po.service;

import java.util.List;

import com.joymain.jecs.po.model.JpoProductNumLimit;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpoProductNumLimitManager extends Manager {
    /**
     * Retrieves all of the jpoProductNumLimits
     */
    public List getJpoProductNumLimits(JpoProductNumLimit jpoProductNumLimit);

    /**
     * Gets jpoProductNumLimit's information based on id.
     * @param id the jpoProductNumLimit's id
     * @return jpoProductNumLimit populated jpoProductNumLimit object
     */
    public JpoProductNumLimit getJpoProductNumLimit(final String id);

    /**
     * Saves a jpoProductNumLimit's information
     * @param jpoProductNumLimit the object to be saved
     */
    public void saveJpoProductNumLimit(JpoProductNumLimit jpoProductNumLimit);

    /**
     * Removes a jpoProductNumLimit from the database by id
     * @param id the jpoProductNumLimit's id
     */
    public void removeJpoProductNumLimit(final String id);
    //added for getJpoProductNumLimitsByCrm
    public List getJpoProductNumLimitsByCrm(CommonRecord crm, Pager pager);
    
    public JpoProductNumLimit getByProductNo(String productNo);
    
    public List<JpoProductNumLimit>  getAll();
    
    public JpoProductNumLimit getNum(String productNo);
	
	public List getAllPros();
}

