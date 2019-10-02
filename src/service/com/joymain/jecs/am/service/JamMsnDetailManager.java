
package com.joymain.jecs.am.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.am.model.JamMsnDetail;
import com.joymain.jecs.am.dao.JamMsnDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JamMsnDetailManager extends Manager {
    /**
     * Retrieves all of the jamMsnDetails
     */
    public List getJamMsnDetails(JamMsnDetail jamMsnDetail);

    /**
     * Gets jamMsnDetail's information based on mdId.
     * @param mdId the jamMsnDetail's mdId
     * @return jamMsnDetail populated jamMsnDetail object
     */
    public JamMsnDetail getJamMsnDetail(final String mdId);

    /**
     * Saves a jamMsnDetail's information
     * @param jamMsnDetail the object to be saved
     */
    public void saveJamMsnDetail(JamMsnDetail jamMsnDetail);

    /**
     * Removes a jamMsnDetail from the database by mdId
     * @param mdId the jamMsnDetail's mdId
     */
    public void removeJamMsnDetail(final String mdId);
    //added for getJamMsnDetailsByCrm
    public List getJamMsnDetailsByCrm(CommonRecord crm, Pager pager);
	public List getJamMsnDetailsBySql(String userCode);
	/**
	 * 可发送返回TRUE，否则返回FALSE
	 * @param userCode
	 * @param msnKey
	 * @return
	 */
	public boolean getJamMsnDetailsBySql(String userCode, String msnKey);
}

