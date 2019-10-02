
package com.joymain.jecs.am.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.am.model.JamDownload;
import com.joymain.jecs.am.dao.JamDownloadDao;
import com.joymain.jecs.am.service.JamDownloadManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JamDownloadManagerImpl extends BaseManager implements JamDownloadManager {
    private JamDownloadDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJamDownloadDao(JamDownloadDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.am.service.JamDownloadManager#getJamDownloads(com.joymain.jecs.am.model.JamDownload)
     */
    public List getJamDownloads(final JamDownload jamDownload) {
        return dao.getJamDownloads(jamDownload);
    }

    /**
     * @see com.joymain.jecs.am.service.JamDownloadManager#getJamDownload(String dlId)
     */
    public JamDownload getJamDownload(final String dlId) {
        return dao.getJamDownload(new Long(dlId));
    }

    /**
     * @see com.joymain.jecs.am.service.JamDownloadManager#saveJamDownload(JamDownload jamDownload)
     */
    public void saveJamDownload(JamDownload jamDownload) {
        dao.saveJamDownload(jamDownload);
    }

    /**
     * @see com.joymain.jecs.am.service.JamDownloadManager#removeJamDownload(String dlId)
     */
    public void removeJamDownload(final String dlId) {
        dao.removeJamDownload(new Long(dlId));
    }
    //added for getJamDownloadsByCrm
    public List getJamDownloadsByCrm(CommonRecord crm, Pager pager){
	return dao.getJamDownloadsByCrm(crm,pager);
    }
}
