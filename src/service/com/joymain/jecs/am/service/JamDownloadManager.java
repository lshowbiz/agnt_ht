
package com.joymain.jecs.am.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.am.model.JamDownload;
import com.joymain.jecs.am.dao.JamDownloadDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JamDownloadManager extends Manager {
    /**
     * Retrieves all of the jamDownloads
     */
    public List getJamDownloads(JamDownload jamDownload);

    /**
     * Gets jamDownload's information based on dlId.
     * @param dlId the jamDownload's dlId
     * @return jamDownload populated jamDownload object
     */
    public JamDownload getJamDownload(final String dlId);

    /**
     * Saves a jamDownload's information
     * @param jamDownload the object to be saved
     */
    public void saveJamDownload(JamDownload jamDownload);

    /**
     * Removes a jamDownload from the database by dlId
     * @param dlId the jamDownload's dlId
     */
    public void removeJamDownload(final String dlId);
    //added for getJamDownloadsByCrm
    public List getJamDownloadsByCrm(CommonRecord crm, Pager pager);
}

