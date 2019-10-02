
package com.joymain.jecs.al.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.al.model.AlCompanyArea;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface AlCompanyAreaDao extends Dao {

	//	public List getPiProductsByHql(String hql);

	//	public List getPiProductsBySql(String sql);
    /**
     * Retrieves all of the alCompanyAreas
     */
    public List getAlCompanyAreas(AlCompanyArea alCompanyArea);

    /**
     * Gets alCompanyArea's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the alCompanyArea's id
     * @return alCompanyArea populated alCompanyArea object
     */
    public AlCompanyArea getAlCompanyArea(final Long id);

    /**
     * Saves a alCompanyArea's information
     * @param alCompanyArea the object to be saved
     */    
    public void saveAlCompanyArea(AlCompanyArea alCompanyArea);

    /**
     * Removes a alCompanyArea from the database by id
     * @param id the alCompanyArea's id
     */
    public void removeAlCompanyArea(final Long id);
		//added for getAlCompanyAreasByCrm
		public List getAlCompanyAreasByCrm(CommonRecord crm, Pager pager);
}

