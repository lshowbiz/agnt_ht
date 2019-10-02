
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiPayAdvice;
import com.joymain.jecs.fi.dao.JfiPayAdviceDao;
import com.joymain.jecs.fi.service.JfiPayAdviceManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiPayAdviceManagerImpl extends BaseManager implements JfiPayAdviceManager {
    private JfiPayAdviceDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiPayAdviceDao(JfiPayAdviceDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiPayAdviceManager#getJfiPayAdvices(com.joymain.jecs.fi.model.JfiPayAdvice)
     */
    public List getJfiPayAdvices(final JfiPayAdvice jfiPayAdvice) {
        return dao.getJfiPayAdvices(jfiPayAdvice);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiPayAdviceManager#getJfiPayAdvice(String adviceCode)
     */
    public JfiPayAdvice getJfiPayAdvice(final String adviceCode) {
        return dao.getJfiPayAdvice(new String(adviceCode));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiPayAdviceManager#saveJfiPayAdvice(JfiPayAdvice jfiPayAdvice)
     */
    public void saveJfiPayAdvice(JfiPayAdvice jfiPayAdvice) {
        dao.saveJfiPayAdvice(jfiPayAdvice);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiPayAdviceManager#removeJfiPayAdvice(String adviceCode)
     */
    public void removeJfiPayAdvice(final String adviceCode) {
        dao.removeJfiPayAdvice(new String(adviceCode));
    }
    //added for getJfiPayAdvicesByCrm
    public List getJfiPayAdvicesByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiPayAdvicesByCrm(crm,pager);
    }

	/**
	 * 批量保存或更新多个付款通知
	 * @param fiPayAdvices
	 */
	public void saveJfiPayAdvices(List jfiPayAdvices) {
		dao.saveJfiPayAdvices(jfiPayAdvices);
	}
	
	/**
     * 获取存折查询统计
     * @param crm
     * @return
     */
    public Map getJfiPayAdviceStatMap(CommonRecord crm){
    	return dao.getJfiPayAdviceStatMap(crm);
    }
	
	/**
	 * 代理商分组查询付款通知
	 * @param crm
	 * @return
	 */
	public List getJfiPayAdvicesStatGroup(CommonRecord crm){
		return dao.getJfiPayAdvicesStatGroup(crm);
	}
	
	/**
	 * 银行提款报表
	 * @param crm
	 * @return
	 */
	public List getJfiPayAdvicesStat(CommonRecord crm){
		return dao.getJfiPayAdvicesStat(crm);
	}
}
