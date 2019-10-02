
package com.joymain.jecs.am.service.impl;

import java.util.List;

import com.joymain.jecs.am.dao.AmAnnounceRecordDao;
import com.joymain.jecs.am.model.AmAnnounceRecord;
import com.joymain.jecs.am.service.AmAnnounceRecordManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class AmAnnounceRecordManagerImpl extends BaseManager implements AmAnnounceRecordManager {
    private AmAnnounceRecordDao dao;

   
    public boolean existAmAnnounceRecord(String aaNo, String userNo) {
		// TODO Auto-generated method stub
    	return dao.existAmAnnounceRecord(aaNo,userNo);
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setAmAnnounceRecordDao(AmAnnounceRecordDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.am.service.AmAnnounceRecordManager#getAmAnnounceRecords(com.joymain.jecs.am.model.AmAnnounceRecord)
     */
    public List getAmAnnounceRecords(final AmAnnounceRecord amAnnounceRecord) {
        return dao.getAmAnnounceRecords(amAnnounceRecord);
    }

    /**
     * @see com.joymain.jecs.am.service.AmAnnounceRecordManager#getAmAnnounceRecord(String uniNo)
     */
    public AmAnnounceRecord getAmAnnounceRecord(final String uniNo) {
        return dao.getAmAnnounceRecord(new String(uniNo));
    }

    /**
     * @see com.joymain.jecs.am.service.AmAnnounceRecordManager#saveAmAnnounceRecord(AmAnnounceRecord amAnnounceRecord)
     */
    public void saveAmAnnounceRecord(AmAnnounceRecord amAnnounceRecord) {
        dao.saveAmAnnounceRecord(amAnnounceRecord);
    }

    /**
     * @see com.joymain.jecs.am.service.AmAnnounceRecordManager#removeAmAnnounceRecord(String uniNo)
     */
    public void removeAmAnnounceRecord(final String uniNo) {
        dao.removeAmAnnounceRecord(new String(uniNo));
    }
    //added for getAmAnnounceRecordsByCrm
		public List getAmAnnounceRecordsByCrm(CommonRecord crm, Pager pager){
				return dao.getAmAnnounceRecordsByCrm(crm,pager);
		}
}
