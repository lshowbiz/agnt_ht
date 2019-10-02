
package com.joymain.jecs.bd.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.bd.dao.JbdTravelPointAllDao;
import com.joymain.jecs.bd.dao.JbdTravelPointLogAllDao;
import com.joymain.jecs.bd.model.JbdTravelPointAll;
import com.joymain.jecs.bd.model.JbdTravelPointAllPK;
import com.joymain.jecs.bd.model.JbdTravelPointLog2013;
import com.joymain.jecs.bd.model.JbdTravelPointLog2014;
import com.joymain.jecs.bd.model.JbdTravelPointLog2015;
import com.joymain.jecs.bd.model.JbdTravelPointLogAll;
import com.joymain.jecs.bd.service.JbdTravelPointLogAllManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdTravelPointLogAllManagerImpl extends BaseManager implements JbdTravelPointLogAllManager {
    private JbdTravelPointLogAllDao dao;
    private JbdTravelPointAllDao jbdTravelPointAllDao;
    
    public void setJbdTravelPointAllDao(JbdTravelPointAllDao jbdTravelPointAllDao) {
		this.jbdTravelPointAllDao = jbdTravelPointAllDao;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdTravelPointLogAllDao(JbdTravelPointLogAllDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointLogAllManager#getJbdTravelPointLogAlls(com.joymain.jecs.bd.model.JbdTravelPointLogAll)
     */
    public List getJbdTravelPointLogAlls(final JbdTravelPointLogAll jbdTravelPointLogAll) {
        return dao.getJbdTravelPointLogAlls(jbdTravelPointLogAll);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointLogAllManager#getJbdTravelPointLogAll(String logId)
     */
    public JbdTravelPointLogAll getJbdTravelPointLogAll(final String logId) {
        return dao.getJbdTravelPointLogAll(new Long(logId));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointLogAllManager#saveJbdTravelPointLogAll(JbdTravelPointLogAll jbdTravelPointLogAll)
     */
    public void saveJbdTravelPointLogAll(JbdTravelPointLogAll jbdTravelPointLogAll) {
        dao.saveJbdTravelPointLogAll(jbdTravelPointLogAll);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointLogAllManager#removeJbdTravelPointLogAll(String logId)
     */
    public void removeJbdTravelPointLogAll(final String logId) {
        dao.removeJbdTravelPointLogAll(new Long(logId));
    }
    //added for getJbdTravelPointLogAllsByCrm
    public List getJbdTravelPointLogAllsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdTravelPointLogAllsByCrm(crm,pager);
    }

	@Override
	public void saveJbdTravelPointLogAll(
			JbdTravelPointLogAll jbdTravelPointLogAll, SysUser defSysUser) {
		// TODO Auto-generated method stub
		Date curDate = new Date();
		jbdTravelPointLogAll.setCreateTime(curDate);
		jbdTravelPointLogAll.setCreateUser(defSysUser.getUserCode());

		JbdTravelPointAll jbdTravelPointAll=jbdTravelPointAllDao.getJbdTravelPointAll(jbdTravelPointLogAll.getUserCode(),jbdTravelPointLogAll.getFyear().intValue());
		if(null==jbdTravelPointAll){
			JbdTravelPointAllPK cp = new JbdTravelPointAllPK();
			cp.setFyear(jbdTravelPointLogAll.getFyear());
			cp.setUserCode(jbdTravelPointLogAll.getUserCode());
			jbdTravelPointAll = new JbdTravelPointAll();
			jbdTravelPointAll.setComp_id(cp);
			jbdTravelPointAll.setPassStar(0);
			jbdTravelPointAll.setTotal(new BigDecimal(0));
		}
		
		if("A".equals(jbdTravelPointLogAll.getDealType())){
			jbdTravelPointAll.setTotal(jbdTravelPointAll.getTotal().add(jbdTravelPointLogAll.getUsePoint()));
			
		}else if ("D".equals(jbdTravelPointLogAll.getDealType())){
			jbdTravelPointAll.setTotal(jbdTravelPointAll.getTotal().subtract(jbdTravelPointLogAll.getUsePoint()));
		}

		dao.saveJbdTravelPointLogAll(jbdTravelPointLogAll);
		jbdTravelPointAllDao.saveJbdTravelPointAll(jbdTravelPointAll);
	}
	
	public void changeJbdTravelPointAlls(List list, SysUser defSysUser) {
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			String wweek = map.get("wweek").toString();
			String userCode = map.get("userCode").toString();
			String remark = map.get("remark").toString();
			String points = map.get("points").toString();
			String dealType = map.get("dealType").toString();
			
			JbdTravelPointLogAll jbdTravelPointLogAll = new JbdTravelPointLogAll();
			jbdTravelPointLogAll.setFyear(new BigDecimal(wweek));
			jbdTravelPointLogAll.setUserCode(userCode);
			jbdTravelPointLogAll.setRemark(remark);
			jbdTravelPointLogAll.setUsePoint(new BigDecimal(points));
			jbdTravelPointLogAll.setDealType(dealType);
			this.saveJbdTravelPointLogAll(jbdTravelPointLogAll, defSysUser);
		}
	}
}
