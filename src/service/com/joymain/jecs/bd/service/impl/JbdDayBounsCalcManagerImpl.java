
package com.joymain.jecs.bd.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdDayBounsCalc;
import com.joymain.jecs.bd.dao.JbdDayBounsCalcDao;
import com.joymain.jecs.bd.service.JbdDayBounsCalcManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdDayBounsCalcManagerImpl extends BaseManager implements JbdDayBounsCalcManager {
    private JbdDayBounsCalcDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdDayBounsCalcDao(JbdDayBounsCalcDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdDayBounsCalcManager#getJbdDayBounsCalcs(com.joymain.jecs.bd.model.JbdDayBounsCalc)
     */
    public List getJbdDayBounsCalcs(final JbdDayBounsCalc jbdDayBounsCalc) {
        return dao.getJbdDayBounsCalcs(jbdDayBounsCalc);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdDayBounsCalcManager#getJbdDayBounsCalc(String id)
     */
    public JbdDayBounsCalc getJbdDayBounsCalc(final String id) {
        return dao.getJbdDayBounsCalc(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdDayBounsCalcManager#saveJbdDayBounsCalc(JbdDayBounsCalc jbdDayBounsCalc)
     */
    public void saveJbdDayBounsCalc(JbdDayBounsCalc jbdDayBounsCalc) {
        dao.saveJbdDayBounsCalc(jbdDayBounsCalc);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdDayBounsCalcManager#removeJbdDayBounsCalc(String id)
     */
    public void removeJbdDayBounsCalc(final String id) {
        dao.removeJbdDayBounsCalc(new Long(id));
    }
    //added for getJbdDayBounsCalcsByCrm
    public List getJbdDayBounsCalcsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdDayBounsCalcsByCrm(crm,pager);
    }

	public HashMap getBdDayBounsCalcBySql(String memberNo, Integer wweek) {
		return dao.getBdDayBounsCalcBySql(memberNo, wweek);
	}

	public List getChildBdDayBounsCalcBySql(JmiMember jmiMember, Integer wweek) {
		return dao.getChildBdDayBounsCalcBySql(jmiMember, wweek);
	}

	public Map getBdDayBounsCalcByUserCode(String userCode, String wweek) {
		return dao.getBdDayBounsCalcByUserCode(userCode, wweek);
	}

	public List getBdDayBounsCalcByTop(String netType, String userCode, String wweek) {
		return dao.getBdDayBounsCalcByTop(netType, userCode, wweek);
	}
}
