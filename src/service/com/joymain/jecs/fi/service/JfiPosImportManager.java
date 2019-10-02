
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiPosImport;
import com.joymain.jecs.fi.dao.JfiPosImportDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiPosImportManager extends Manager {
    /**
     * Retrieves all of the jfiPosImports
     */
    public List getJfiPosImports(JfiPosImport jfiPosImport);

    /**
     * Gets jfiPosImport's information based on jpiId.
     * @param jpiId the jfiPosImport's jpiId
     * @return jfiPosImport populated jfiPosImport object
     */
    public JfiPosImport getJfiPosImport(final String jpiId);

    /**
     * Saves a jfiPosImport's information
     * @param jfiPosImport the object to be saved
     */
    public void saveJfiPosImport(JfiPosImport jfiPosImport);

	/**
	 * 批量保存多条记录
	 * @param fiPayDatas
	 */
	public void saveJfiPosImports(List jfiPosImport);

    /**
     * Removes a jfiPosImport from the database by jpiId
     * @param jpiId the jfiPosImport's jpiId
     */
    public void removeJfiPosImport(final String jpiId);
    //added for getJfiPosImportsByCrm
    public List getJfiPosImportsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * 获取总金额
     * @param crm
     * @return
     */
    public String getSumJfiPosImportsByCrm(CommonRecord crm);
}

