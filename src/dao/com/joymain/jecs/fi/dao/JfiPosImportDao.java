
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiPosImport;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiPosImportDao extends Dao {

    /**
     * Retrieves all of the jfiPosImports
     */
    public List getJfiPosImports(JfiPosImport jfiPosImport);

    /**
     * Gets jfiPosImport's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param jpiId the jfiPosImport's jpiId
     * @return jfiPosImport populated jfiPosImport object
     */
    public JfiPosImport getJfiPosImport(final Long jpiId);

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
     * 获取总金额
     * @param crm
     * @return
     */
    public String getSumJfiPosImportsByCrm(CommonRecord crm);

    /**
     * Removes a jfiPosImport from the database by jpiId
     * @param jpiId the jfiPosImport's jpiId
     */
    public void removeJfiPosImport(final Long jpiId);
    //added for getJfiPosImportsByCrm
    public List getJfiPosImportsByCrm(CommonRecord crm, Pager pager);
}

