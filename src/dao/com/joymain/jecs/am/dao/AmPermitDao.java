
package com.joymain.jecs.am.dao;

import java.util.List;

import com.joymain.jecs.am.model.AmPermit;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface AmPermitDao extends Dao {

	//	public List getPiProductsByHql(String hql);

	//	public List getPiProductsBySql(String sql);
    /**
     * Retrieves all of the amPermits
     */
    public List getAmPermits(AmPermit amPermit);

    /**
     * Gets amPermit's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param permitNo the amPermit's permitNo
     * @return amPermit populated amPermit object
     */
    public AmPermit getAmPermit(final String permitNo);

    /**
     * Saves a amPermit's information
     * @param amPermit the object to be saved
     */    
    public void saveAmPermit(AmPermit amPermit);

    /**
     * Removes a amPermit from the database by permitNo
     * @param permitNo the amPermit's permitNo
     */
    public void removeAmPermit(final String permitNo);
		//added for getAmPermitsByCrm
		public List getAmPermitsByCrm(CommonRecord crm, Pager pager);
}

