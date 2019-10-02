
package com.joymain.jecs.bd.service.impl;

import java.util.List;

import com.joymain.jecs.bd.dao.JbdTravelPointAllDao;
import com.joymain.jecs.bd.model.JbdTravelPointAll;
import com.joymain.jecs.bd.model.JbdTravelPointAllPK;
import com.joymain.jecs.bd.service.JbdTravelPointAllManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdTravelPointAllManagerImpl extends BaseManager implements JbdTravelPointAllManager {
    private JbdTravelPointAllDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdTravelPointAllDao(JbdTravelPointAllDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointAllManager#getJbdTravelPointAlls(com.joymain.jecs.bd.model.JbdTravelPointAll)
     */
    public List getJbdTravelPointAlls(final JbdTravelPointAll jbdTravelPointAll) {
        return dao.getJbdTravelPointAlls(jbdTravelPointAll);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointAllManager#getJbdTravelPointAll(String comp_id)
     */
    public JbdTravelPointAll getJbdTravelPointAll(final String comp_id) {
//        return dao.getJbdTravelPointAll(new JbdTravelPointAllPK(comp_id));
    	return null;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointAllManager#saveJbdTravelPointAll(JbdTravelPointAll jbdTravelPointAll)
     */
    public void saveJbdTravelPointAll(JbdTravelPointAll jbdTravelPointAll) {
        dao.saveJbdTravelPointAll(jbdTravelPointAll);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointAllManager#removeJbdTravelPointAll(String comp_id)
     */
    public void removeJbdTravelPointAll(final String comp_id) {
//        dao.removeJbdTravelPointAll(new JbdTravelPointAllPK(comp_id));
    }
    //added for getJbdTravelPointAllsByCrm
    public List getJbdTravelPointAllsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdTravelPointAllsByCrm(crm,pager);
    }

	@Override
	public JbdTravelPointAll getJbdTravelPointAll(String userCode, Integer fyear) {
		// TODO Auto-generated method stub
		return dao.getJbdTravelPointAll(userCode,fyear);
	}
}
