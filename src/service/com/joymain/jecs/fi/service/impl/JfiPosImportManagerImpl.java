
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiPosImport;
import com.joymain.jecs.fi.dao.JfiPosImportDao;
import com.joymain.jecs.fi.service.JfiPosImportManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiPosImportManagerImpl extends BaseManager implements JfiPosImportManager {
    private JfiPosImportDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiPosImportDao(JfiPosImportDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiPosImportManager#getJfiPosImports(com.joymain.jecs.fi.model.JfiPosImport)
     */
    public List getJfiPosImports(final JfiPosImport jfiPosImport) {
        return dao.getJfiPosImports(jfiPosImport);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiPosImportManager#getJfiPosImport(String jpiId)
     */
    public JfiPosImport getJfiPosImport(final String jpiId) {
        return dao.getJfiPosImport(new Long(jpiId));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiPosImportManager#saveJfiPosImport(JfiPosImport jfiPosImport)
     */
    public void saveJfiPosImport(JfiPosImport jfiPosImport) {
        dao.saveJfiPosImport(jfiPosImport);
    }

	/**
	 * 批量保存多条记录
	 * @param fiPayDatas
	 */
	public void saveJfiPosImports(List jfiPosImport){
		dao.saveJfiPosImports(jfiPosImport);
	}

    /**
     * @see com.joymain.jecs.fi.service.JfiPosImportManager#removeJfiPosImport(String jpiId)
     */
    public void removeJfiPosImport(final String jpiId) {
        dao.removeJfiPosImport(new Long(jpiId));
    }
    //added for getJfiPosImportsByCrm
    public List getJfiPosImportsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiPosImportsByCrm(crm,pager);
    }
    
    /**
     * 获取总金额
     * @param crm
     * @return
     */
    public String getSumJfiPosImportsByCrm(CommonRecord crm){
    	return dao.getSumJfiPosImportsByCrm(crm);
    }
}
