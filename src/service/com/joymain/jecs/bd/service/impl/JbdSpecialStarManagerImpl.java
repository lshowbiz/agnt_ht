
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdSpecialStar;
import com.joymain.jecs.bd.dao.JbdSpecialStarDao;
import com.joymain.jecs.bd.service.JbdSpecialStarManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdSpecialStarManagerImpl extends BaseManager implements JbdSpecialStarManager {
    private JbdSpecialStarDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdSpecialStarDao(JbdSpecialStarDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSpecialStarManager#getJbdSpecialStars(com.joymain.jecs.bd.model.JbdSpecialStar)
     */
    public List getJbdSpecialStars(final JbdSpecialStar jbdSpecialStar) {
        return dao.getJbdSpecialStars(jbdSpecialStar);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSpecialStarManager#getJbdSpecialStar(String userCode)
     */
    public JbdSpecialStar getJbdSpecialStar(final String userCode) {
        return dao.getJbdSpecialStar(new String(userCode));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSpecialStarManager#saveJbdSpecialStar(JbdSpecialStar jbdSpecialStar)
     */
    public void saveJbdSpecialStar(JbdSpecialStar jbdSpecialStar) {
        dao.saveJbdSpecialStar(jbdSpecialStar);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSpecialStarManager#removeJbdSpecialStar(String userCode)
     */
    public void removeJbdSpecialStar(final String userCode) {
        dao.removeJbdSpecialStar(new String(userCode));
    }
    //added for getJbdSpecialStarsByCrm
    public List getJbdSpecialStarsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdSpecialStarsByCrm(crm,pager);
    }
}
