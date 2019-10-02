
package com.joymain.jecs.am.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.am.model.JamDownload;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JamDownloadDao extends Dao {

    /**
     * Retrieves all of the jamDownloads
     */
    public List getJamDownloads(JamDownload jamDownload);

    /**
     * Gets jamDownload's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param dlId the jamDownload's dlId
     * @return jamDownload populated jamDownload object
     */
    public JamDownload getJamDownload(final Long dlId);

    /**
     * Saves a jamDownload's information
     * @param jamDownload the object to be saved
     */    
    public void saveJamDownload(JamDownload jamDownload);

    /**
     * Removes a jamDownload from the database by dlId
     * @param dlId the jamDownload's dlId
     */
    public void removeJamDownload(final Long dlId);
    //added for getJamDownloadsByCrm
    public List getJamDownloadsByCrm(CommonRecord crm, Pager pager);
}

