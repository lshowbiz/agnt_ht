
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.fi.model.JfiUmbpayLog;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
@SuppressWarnings("unchecked")
public interface JfiUmbpayLogManager extends Manager {
    /**
     * Retrieves all of the jfiUmbpayLogs
     */
    public List getJfiUmbpayLogs(JfiUmbpayLog jfiUmbpayLog);
    
    public List getJfiUmbpayLogsByMerId(String merId);

    /**
     * Gets jfiUmbpayLog's information based on logId.
     * @param logId the jfiUmbpayLog's logId
     * @return jfiUmbpayLog populated jfiUmbpayLog object
     */
    public JfiUmbpayLog getJfiUmbpayLog(final String logId);

    /**
     * Saves a jfiUmbpayLog's information
     * @param jfiUmbpayLog the object to be saved
     */
    public void saveJfiUmbpayLog(JfiUmbpayLog jfiUmbpayLog);

    /**
     * Removes a jfiUmbpayLog from the database by logId
     * @param logId the jfiUmbpayLog's logId
     */
    public void removeJfiUmbpayLog(final String logId);
    //added for getJfiUmbpayLogsByCrm
    public List getJfiUmbpayLogsByCrm(CommonRecord crm, Pager pager);
}

