package com.joymain.jecs.fi.service.impl;

import java.util.List;

import com.joymain.jecs.fi.dao.JfiUmbpayLogDao;
import com.joymain.jecs.fi.model.JfiUmbpayLog;
import com.joymain.jecs.fi.service.JfiUmbpayLogManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

@SuppressWarnings("unchecked")
public class JfiUmbpayLogManagerImpl extends BaseManager implements JfiUmbpayLogManager {
	private JfiUmbpayLogDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setJfiUmbpayLogDao(JfiUmbpayLogDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.fi.service.JfiUmbpayLogManager#getJfiUmbpayLogs(com.joymain.jecs.fi.model.JfiUmbpayLog)
	 */
	public List getJfiUmbpayLogs(final JfiUmbpayLog jfiUmbpayLog) {
		return dao.getJfiUmbpayLogs(jfiUmbpayLog);
	}

	public List getJfiUmbpayLogsByMerId(String merId) {
		return dao.getJfiUmbpayLogsByMerId(merId);
	}

	/**
	 * @see com.joymain.jecs.fi.service.JfiUmbpayLogManager#getJfiUmbpayLog(String
	 *      logId)
	 */
	public JfiUmbpayLog getJfiUmbpayLog(final String logId) {
		return dao.getJfiUmbpayLog(new Long(logId));
	}

	/**
	 * @see com.joymain.jecs.fi.service.JfiUmbpayLogManager#saveJfiUmbpayLog(JfiUmbpayLog
	 *      jfiUmbpayLog)
	 */
	public void saveJfiUmbpayLog(JfiUmbpayLog jfiUmbpayLog) {
		dao.saveJfiUmbpayLog(jfiUmbpayLog);
	}

	/**
	 * @see com.joymain.jecs.fi.service.JfiUmbpayLogManager#removeJfiUmbpayLog(String
	 *      logId)
	 */
	public void removeJfiUmbpayLog(final String logId) {
		dao.removeJfiUmbpayLog(new Long(logId));
	}

	// added for getJfiUmbpayLogsByCrm
	public List getJfiUmbpayLogsByCrm(CommonRecord crm, Pager pager) {
		return dao.getJfiUmbpayLogsByCrm(crm, pager);
	}
}
