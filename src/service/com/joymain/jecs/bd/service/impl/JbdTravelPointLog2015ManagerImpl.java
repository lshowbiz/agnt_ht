
package com.joymain.jecs.bd.service.impl;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.bd.model.JbdTravelPoint2015;
import com.joymain.jecs.bd.model.JbdTravelPointLog2015;
import com.joymain.jecs.bd.dao.JbdTravelPoint2015Dao;
import com.joymain.jecs.bd.dao.JbdTravelPointLog2015Dao;
import com.joymain.jecs.bd.service.JbdTravelPointLog2015Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdTravelPointLog2015ManagerImpl extends BaseManager implements JbdTravelPointLog2015Manager {
    private JbdTravelPointLog2015Dao dao;
    private JbdTravelPoint2015Dao jbdTravelPoint2015Dao;
    public void setJbdTravelPoint2015Dao(JbdTravelPoint2015Dao jbdTravelPoint2015Dao) {
		this.jbdTravelPoint2015Dao = jbdTravelPoint2015Dao;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdTravelPointLog2015Dao(JbdTravelPointLog2015Dao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointLog2015Manager#getJbdTravelPointLog2015s(com.joymain.jecs.bd.model.JbdTravelPointLog2015)
     */
    public List getJbdTravelPointLog2015s(final JbdTravelPointLog2015 jbdTravelPointLog2015) {
        return dao.getJbdTravelPointLog2015s(jbdTravelPointLog2015);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointLog2015Manager#getJbdTravelPointLog2015(String logId)
     */
    public JbdTravelPointLog2015 getJbdTravelPointLog2015(final String logId) {
        return dao.getJbdTravelPointLog2015(new Long(logId));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointLog2015Manager#saveJbdTravelPointLog2015(JbdTravelPointLog2015 jbdTravelPointLog2015)
     */
    public void saveJbdTravelPointLog2015(JbdTravelPointLog2015 jbdTravelPointLog2015) {
        dao.saveJbdTravelPointLog2015(jbdTravelPointLog2015);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointLog2015Manager#removeJbdTravelPointLog2015(String logId)
     */
    public void removeJbdTravelPointLog2015(final String logId) {
        dao.removeJbdTravelPointLog2015(new Long(logId));
    }
    //added for getJbdTravelPointLog2015sByCrm
    public List getJbdTravelPointLog2015sByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdTravelPointLog2015sByCrm(crm,pager);
    }
    public void changeJbdTravelPoint2015(JbdTravelPointLog2015 jbdTravelPointLog2015, SysUser defSysUser) {
		Date curDate = new Date();
		jbdTravelPointLog2015.setCreateTime(curDate);
		jbdTravelPointLog2015.setCreateUser(defSysUser.getUserCode());

		JbdTravelPoint2015 jbdTravelPoint2015=jbdTravelPoint2015Dao.getJbdTravelPoint2015(jbdTravelPointLog2015.getUserCode());
		
		if("A".equals(jbdTravelPointLog2015.getDealType())){
			jbdTravelPoint2015.setTotal(jbdTravelPoint2015.getTotal().add(jbdTravelPointLog2015.getUsePoint()));
			
		}else if ("D".equals(jbdTravelPointLog2015.getDealType())){
			jbdTravelPoint2015.setTotal(jbdTravelPoint2015.getTotal().subtract(jbdTravelPointLog2015.getUsePoint()));
		}

		dao.saveJbdTravelPointLog2015(jbdTravelPointLog2015);
		jbdTravelPoint2015Dao.saveJbdTravelPoint2015(jbdTravelPoint2015);
		
		
	}
}
