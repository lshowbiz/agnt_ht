
package com.joymain.jecs.mi.dao;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiAssure;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiAssureDao extends Dao {

    /**
     * Retrieves all of the jmiAssures
     */
    public List getJmiAssures(JmiAssure jmiAssure);

    /**
     * Gets jmiAssure's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jmiAssure's id
     * @return jmiAssure populated jmiAssure object
     */
    public JmiAssure getJmiAssure(final Long id);

    /**
     * Saves a jmiAssure's information
     * @param jmiAssure the object to be saved
     */    
    public void saveJmiAssure(JmiAssure jmiAssure);

    public void updateJmiAssure(JmiAssure jmiAssure);
    /**
     * Removes a jmiAssure from the database by id
     * @param id the jmiAssure's id
     */
    public void removeJmiAssure(final Long id);
    //added for getJmiAssuresByCrm
    public List getJmiAssuresByCrm(CommonRecord crm, Pager pager);

	public List getReportByContion(CommonRecord crm);
	
	public int removeJmiAssureByIds(final String ids);
	
	public JpoMemberOrder getJpoMemberOrderByInterface(String memberOrderNo);
	
	 /**
     * 批量新增
     */
    public void saveJmiAssureList(List<JmiAssure> jmiAssureList)  throws Exception;
}

