
package com.joymain.jecs.am.dao;

import java.util.List;

import com.joymain.jecs.am.model.AmAnnounceRecord;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface AmAnnounceRecordDao extends Dao {

	//	public List getPiProductsByHql(String hql);

	//	public List getPiProductsBySql(String sql);
    /**
     * Retrieves all of the amAnnounceRecords
     */
    public List getAmAnnounceRecords(AmAnnounceRecord amAnnounceRecord);

    /**
     * Gets amAnnounceRecord's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param uniNo the amAnnounceRecord's uniNo
     * @return amAnnounceRecord populated amAnnounceRecord object
     */
    public AmAnnounceRecord getAmAnnounceRecord(final String uniNo);

    /**
     * Saves a amAnnounceRecord's information
     * @param amAnnounceRecord the object to be saved
     */    
    public void saveAmAnnounceRecord(AmAnnounceRecord amAnnounceRecord);

    /**
     * Removes a amAnnounceRecord from the database by uniNo
     * @param uniNo the amAnnounceRecord's uniNo
     */
    public void removeAmAnnounceRecord(final String uniNo);
		//added for getAmAnnounceRecordsByCrm
		public List getAmAnnounceRecordsByCrm(CommonRecord crm, Pager pager);

		public boolean existAmAnnounceRecord(String aaNo,
				String userNo);
}

