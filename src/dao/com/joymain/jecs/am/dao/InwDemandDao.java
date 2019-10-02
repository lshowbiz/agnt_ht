
package com.joymain.jecs.am.dao;

import java.util.List;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.am.model.InwDemand;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface InwDemandDao extends Dao {

    /**
     * Retrieves all of the inwDemands
     */
    public List getInwDemands(InwDemand inwDemand);

    /**
     * Gets inwDemand's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the inwDemand's id
     * @return inwDemand populated inwDemand object
     */
    public InwDemand getInwDemand(final Long id);

    /**
     * Saves a inwDemand's information
     * @param inwDemand the object to be saved
     */    
    public void saveInwDemand(InwDemand inwDemand);

    /**
     * Removes a inwDemand from the database by id
     * @param id the inwDemand's id
     */
    public void removeInwDemand(final Long id);
    public void removeInwDemands(String ids);
    //added for getInwDemandsByCrm
    public List getInwDemandsByCrm(CommonRecord crm, Pager pager);
    public void checkInwDemand(String ids ,String userName);

	/**
	 * 创新共赢-------新需求------删除需求分类这个大类时,同步删除创新共赢需求表的各子类
	 * @author gw  2013-11-12
	 * @param demandsortId
	 */
	public void deleteInwDemandByDemandsortId(String demandsortId);

	/**
	 * 创新共赢--------------新需求-------------通过ID获取需求的名称
	 * @author gw 2013-11-15
	 * @param id
	 * @return
	 */
	public String getInwDemandById(String id);

	/**
	 * 根据需求分类大类别的ID查询出该大类下所有小类需求
	 * @author gw 2014-06-25
	 * @param demandsortID
	 * @return list 
	 */
	public List getInwDemandByDemandsortId(String demandsortID);
	
	
}

