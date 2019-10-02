
package com.joymain.jecs.mi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.mi.model.JmiSpecialList;
import com.joymain.jecs.mi.dao.JmiSpecialListDao;
import com.joymain.jecs.mi.service.JmiSpecialListManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JmiSpecialListManagerImpl extends BaseManager implements JmiSpecialListManager {
    private JmiSpecialListDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiSpecialListDao(JmiSpecialListDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiSpecialListManager#getJmiSpecialLists(com.joymain.jecs.mi.model.JmiSpecialList)
     */
    public List getJmiSpecialLists(final JmiSpecialList jmiSpecialList) {
        return dao.getJmiSpecialLists(jmiSpecialList);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiSpecialListManager#getJmiSpecialList(String id)
     */
    public JmiSpecialList getJmiSpecialList(final String id) {
        return dao.getJmiSpecialList(new Long(id));
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiSpecialListManager#saveJmiSpecialList(JmiSpecialList jmiSpecialList)
     */
    public void saveJmiSpecialList(JmiSpecialList jmiSpecialList) {
        dao.saveJmiSpecialList(jmiSpecialList);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiSpecialListManager#removeJmiSpecialList(String id)
     */
    public void removeJmiSpecialList(final String id) {
        dao.removeJmiSpecialList(new Long(id));
    }
    //added for getJmiSpecialListsByCrm
    public List getJmiSpecialListsByCrm(CommonRecord crm, Pager pager){
	return dao.getJmiSpecialListsByCrm(crm,pager);
    }

	@Override
	public void saveJmiSpecialList(List<JmiSpecialList> jmiSpecialList) {
		dao.saveJmiSpecialList(jmiSpecialList);
		
	}
}
