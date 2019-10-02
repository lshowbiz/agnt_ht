
package com.joymain.jecs.al.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.al.model.AlCompanyArea;
import com.joymain.jecs.al.dao.AlCompanyAreaDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface AlCompanyAreaManager extends Manager {
    /**
     * Retrieves all of the alCompanyAreas
     */
    public List getAlCompanyAreas(AlCompanyArea alCompanyArea);

    /**
     * Gets alCompanyArea's information based on id.
     * @param id the alCompanyArea's id
     * @return alCompanyArea populated alCompanyArea object
     */
    public AlCompanyArea getAlCompanyArea(final String id);

    /**
     * Saves a alCompanyArea's information
     * @param alCompanyArea the object to be saved
     */
    public void saveAlCompanyArea(AlCompanyArea alCompanyArea);

    /**
     * Removes a alCompanyArea from the database by id
     * @param id the alCompanyArea's id
     */
    public void removeAlCompanyArea(final String id);
    //added for getAlCompanyAreasByCrm
		public List getAlCompanyAreasByCrm(CommonRecord crm, Pager pager);
}

