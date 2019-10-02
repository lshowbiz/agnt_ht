
package com.joymain.jecs.fi.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiUmbpayLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

@SuppressWarnings("unchecked")
public interface JfiUmbpayLogDao extends Dao {

    /**
     * Retrieves all of the jfiUmbpayLogs
     */
	public List getJfiUmbpayLogs(JfiUmbpayLog jfiUmbpayLog);
    
    public List getJfiUmbpayLogsByMerId(String merId);

    /**
     * Gets jfiUmbpayLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param logId the jfiUmbpayLog's logId
     * @return jfiUmbpayLog populated jfiUmbpayLog object
     */
    public JfiUmbpayLog getJfiUmbpayLog(final Long logId);

    /**
     * Saves a jfiUmbpayLog's information
     * @param jfiUmbpayLog the object to be saved
     */    
    public void saveJfiUmbpayLog(JfiUmbpayLog jfiUmbpayLog);

    /**
     * Removes a jfiUmbpayLog from the database by logId
     * @param logId the jfiUmbpayLog's logId
     */
    public void removeJfiUmbpayLog(final Long logId);
    //added for getJfiUmbpayLogsByCrm
    public List getJfiUmbpayLogsByCrm(CommonRecord crm, Pager pager);
}

