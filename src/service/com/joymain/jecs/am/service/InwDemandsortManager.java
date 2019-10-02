
package com.joymain.jecs.am.service;

import java.util.List;

import org.springframework.validation.BindException;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.am.model.InwDemandsort;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface InwDemandsortManager extends Manager {
    /**
     * Retrieves all of the inwDemandsorts
     */
    public List getInwDemandsorts(InwDemandsort inwDemandsort);

    /**
     * Gets inwDemandsort's information based on id.
     * @param id the inwDemandsort's id
     * @return inwDemandsort populated inwDemandsort object
     */
    public InwDemandsort getInwDemandsort(final String id);

    /**
     * Saves a inwDemandsort's information
     * @param inwDemandsort the object to be saved
     */
    public void saveInwDemandsort(InwDemandsort inwDemandsort);

    /**
     * Removes a inwDemandsort from the database by id
     * @param id the inwDemandsort's id
     */
    public void removeInwDemandsort(final String id);
    //added for getInwDemandsortsByCrm
    public List getInwDemandsortsByCrm(CommonRecord crm, Pager pager);

    /**
     * 创新共赢-需求分类-录入或修改之前不为空的校验
     * @author gw  2013-11-04
     * @param inwDemandsort
     * @param errors
     * @param characterCoding
     * @return boolean
     */
	public boolean getEmptyCheckResult(InwDemandsort inwDemandsort,BindException errors,String characterCoding);

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

