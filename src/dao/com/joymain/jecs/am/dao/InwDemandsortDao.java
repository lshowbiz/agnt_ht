
package com.joymain.jecs.am.dao;

import java.util.List;


import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.am.model.InwDemandsort;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface InwDemandsortDao extends Dao {

    /**
     * Retrieves all of the inwDemandsorts
     */
    public List getInwDemandsorts(InwDemandsort inwDemandsort);

    /**
     * Gets inwDemandsort's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the inwDemandsort's id
     * @return inwDemandsort populated inwDemandsort object
     */
    public InwDemandsort getInwDemandsort(final Long id);

    /**
     * Saves a inwDemandsort's information
     * @param inwDemandsort the object to be saved
     */    
    public void saveInwDemandsort(InwDemandsort inwDemandsort);

    /**
     * Removes a inwDemandsort from the database by id
     * @param id the inwDemandsort's id
     */
    public void removeInwDemandsort(final Long id);
    //added for getInwDemandsortsByCrm
    public List getInwDemandsortsByCrm(CommonRecord crm, Pager pager);

    /**
	 * 查询创新共赢需求分类表的所有数据
	 * @author gw  2013-11-05
	 * @return
	 */
	public List getDemandsortList();

	/**
	 * 创新共赢-------------需求分类------新需求--------通过ID获取需求分类的名字
	 * @author gw 2013-13-14
	 * @return
	 */
	public String getInwDemandsortById(String id);

}

