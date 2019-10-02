
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdSendRecord;
import com.joymain.jecs.bd.dao.JbdSendRecordDao;
import com.joymain.jecs.bd.service.JbdSendRecordManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdSendRecordManagerImpl extends BaseManager implements JbdSendRecordManager {
    private JbdSendRecordDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdSendRecordDao(JbdSendRecordDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSendRecordManager#getJbdSendRecords(com.joymain.jecs.bd.model.JbdSendRecord)
     */
    public List getJbdSendRecords(final JbdSendRecord jbdSendRecord) {
        return dao.getJbdSendRecords(jbdSendRecord);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSendRecordManager#getJbdSendRecord(String id)
     */
    public JbdSendRecord getJbdSendRecord(final String id) {
        return dao.getJbdSendRecord(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSendRecordManager#saveJbdSendRecord(JbdSendRecord jbdSendRecord)
     */
    public void saveJbdSendRecord(JbdSendRecord jbdSendRecord) {
        dao.saveJbdSendRecord(jbdSendRecord);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSendRecordManager#removeJbdSendRecord(String id)
     */
    public void removeJbdSendRecord(final String id) {
        dao.removeJbdSendRecord(new Long(id));
    }
    //added for getJbdSendRecordsByCrm
    public List getJbdSendRecordsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdSendRecordsByCrm(crm,pager);
    }

	public List getBdSendRecordReport(CommonRecord crm) {
		return dao.getBdSendRecordReport(crm);
	}





}
