
package com.joymain.jecs.am.dao;

import java.util.List;

import com.joymain.jecs.am.model.AmAnnounce;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface AmAnnounceDao extends Dao {

	//	public List getPiProductsByHql(String hql);

	//	public List getPiProductsBySql(String sql);
    /**
     * Retrieves all of the amAnnounces
     */
    public List getAmAnnounces(AmAnnounce amAnnounce);

    /**
     * Gets amAnnounce's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param aaNo the amAnnounce's aaNo
     * @return amAnnounce populated amAnnounce object
     */
    public AmAnnounce getAmAnnounce(final String aaNo);

    /**
     * Saves a amAnnounce's information
     * @param amAnnounce the object to be saved
     */    
    public void saveAmAnnounce(AmAnnounce amAnnounce);

    /**
     * Removes a amAnnounce from the database by aaNo
     * @param aaNo the amAnnounce's aaNo
     */
    public void removeAmAnnounce(final String aaNo);
		//added for getAmAnnouncesByCrm
		public List getAmAnnouncesByCrm(CommonRecord crm, Pager pager);
		
		public int removeAnnounceBatch(String aanos);
		
		public void checkAnnounceBatch(String aanos,String userCode,String userName);
		
		public long getAnnounceReadNum(CommonRecord crm, Pager pager);
		public long getAnnounceNoReadNum(CommonRecord crm, Pager pager);
		public void updateOutAmAnnounce(String aanos);
}

