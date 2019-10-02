package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiQuota;
import com.joymain.jecs.fi.dao.JfiQuotaDao;
import com.joymain.jecs.fi.service.JfiQuotaManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
@SuppressWarnings("rawtypes")
public class JfiQuotaManagerImpl extends BaseManager implements JfiQuotaManager {
	private JfiQuotaDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setJfiQuotaDao(JfiQuotaDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.fi.service.JfiQuotaManager#getJfiQuotas(com.joymain.jecs.fi.model.JfiQuota)
	 */

	public List getJfiQuotas(final JfiQuota jfiQuota) {
		return dao.getJfiQuotas(jfiQuota);
	}

	public JfiQuota getJfiQuota(JfiQuota jfiQuota, String merchantId) {
		return dao.getJfiQuota(jfiQuota, merchantId);
	}

	/**
	 * @see com.joymain.jecs.fi.service.JfiQuotaManager#getJfiQuota(String
	 *      quotaId)
	 */
	public JfiQuota getJfiQuota(final String quotaId) {
		return dao.getJfiQuota(new Long(quotaId));
	}

	/**
	 * @see com.joymain.jecs.fi.service.JfiQuotaManager#saveJfiQuota(JfiQuota
	 *      jfiQuota)
	 */
	public void saveJfiQuota(JfiQuota jfiQuota) {
		dao.saveJfiQuota(jfiQuota);
	}

	/**
	 * @see com.joymain.jecs.fi.service.JfiQuotaManager#removeJfiQuota(String
	 *      quotaId)
	 */
	public void removeJfiQuota(final String quotaId) {
		dao.removeJfiQuota(new Long(quotaId));
	}

	// added for getJfiQuotasByCrm
	public List getJfiQuotasByCrm(CommonRecord crm, Pager pager) {
		return dao.getJfiQuotasByCrm(crm, pager);
	}

	/**
	 * 金流限额定时调用功能实现
	 */
	public void quartJfiQuota() {
		dao.quartJfiQuota();
	}

	/**
	 * 修改指定期别、商户号的原数据状态
	 * 
	 * @param crm
	 */
	public void updateJfiQuotaStatus(CommonRecord crm) {
		dao.updateJfiQuotaStatus(crm);
	}
}
