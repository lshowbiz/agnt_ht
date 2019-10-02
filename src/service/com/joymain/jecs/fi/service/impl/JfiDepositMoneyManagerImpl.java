
package com.joymain.jecs.fi.service.impl;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.fi.model.JfiDepositMoney;
import com.joymain.jecs.fi.dao.JfiDepositMoneyDao;
import com.joymain.jecs.fi.service.JfiDepositMoneyManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiDepositMoneyManagerImpl extends BaseManager implements JfiDepositMoneyManager {
    private JfiDepositMoneyDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiDepositMoneyDao(JfiDepositMoneyDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiDepositMoneyManager#getJfiDepositMoneys(com.joymain.jecs.fi.model.JfiDepositMoney)
     */
    public List getJfiDepositMoneys(final JfiDepositMoney jfiDepositMoney) {
        return dao.getJfiDepositMoneys(jfiDepositMoney);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiDepositMoneyManager#getJfiDepositMoney(String id)
     */
    public JfiDepositMoney getJfiDepositMoney(final String id) {
        return dao.getJfiDepositMoney(new Long(id));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiDepositMoneyManager#saveJfiDepositMoney(JfiDepositMoney jfiDepositMoney)
     */
    public void saveJfiDepositMoney(JfiDepositMoney jfiDepositMoney) {
        dao.saveJfiDepositMoney(jfiDepositMoney);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiDepositMoneyManager#removeJfiDepositMoney(String id)
     */
    public void removeJfiDepositMoney(final String id) {
        dao.removeJfiDepositMoney(new Long(id));
    }
    //added for getJfiDepositMoneysByCrm
    public List getJfiDepositMoneysByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiDepositMoneysByCrm(crm,pager);
    }

	@Override
	public BigDecimal getSumDepositMoney(CommonRecord crm) {
		return dao.getSumDepositMoney(crm);
	}
	
	
}
