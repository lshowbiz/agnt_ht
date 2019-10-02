
package com.joymain.jecs.al.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.al.model.JalLibraryFile;
import com.joymain.jecs.al.dao.JalLibraryFileDao;
import com.joymain.jecs.al.service.JalLibraryFileManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JalLibraryFileManagerImpl extends BaseManager implements JalLibraryFileManager {
    private JalLibraryFileDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJalLibraryFileDao(JalLibraryFileDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.al.service.JalLibraryFileManager#getJalLibraryFiles(com.joymain.jecs.al.model.JalLibraryFile)
     */
    public List getJalLibraryFiles(final JalLibraryFile jalLibraryFile) {
        return dao.getJalLibraryFiles(jalLibraryFile);
    }

    /**
     * @see com.joymain.jecs.al.service.JalLibraryFileManager#getJalLibraryFile(String fileId)
     */
    public JalLibraryFile getJalLibraryFile(final String fileId) {
        return dao.getJalLibraryFile(new Long(fileId));
    }

    /**
     * @see com.joymain.jecs.al.service.JalLibraryFileManager#saveJalLibraryFile(JalLibraryFile jalLibraryFile)
     */
    public void saveJalLibraryFile(JalLibraryFile jalLibraryFile) {
        dao.saveJalLibraryFile(jalLibraryFile);
    }

    /**
     * @see com.joymain.jecs.al.service.JalLibraryFileManager#removeJalLibraryFile(String fileId)
     */
    public void removeJalLibraryFile(final String fileId) {
        dao.removeJalLibraryFile(new Long(fileId));
    }
    //added for getJalLibraryFilesByCrm
    public List getJalLibraryFilesByCrm(CommonRecord crm, Pager pager){
	return dao.getJalLibraryFilesByCrm(crm,pager);
    }

	public List getJalLibraryFileByCulumnId(String columnId) {
		// TODO Auto-generated method stub
		return dao.getJalLibraryFileByCulumnId(columnId);
	}
}
