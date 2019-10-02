package com.joymain.jecs.fi.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiQuota;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

@SuppressWarnings("rawtypes")
public interface JfiQuotaDao extends Dao {

	/**
	 * Retrieves all of the jfiQuotas
	 */

	public List getJfiQuotas(JfiQuota jfiQuota);

	public JfiQuota getJfiQuota(JfiQuota jfiQuota, String merchantId);

	/**
	 * Gets jfiQuota's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param quotaId
	 *            the jfiQuota's quotaId
	 * @return jfiQuota populated jfiQuota object
	 */
	public JfiQuota getJfiQuota(final Long quotaId);

	/**
	 * Saves a jfiQuota's information
	 * 
	 * @param jfiQuota
	 *            the object to be saved
	 */
	public void saveJfiQuota(JfiQuota jfiQuota);

	/**
	 * Removes a jfiQuota from the database by quotaId
	 * 
	 * @param quotaId
	 *            the jfiQuota's quotaId
	 */
	public void removeJfiQuota(final Long quotaId);

	// added for getJfiQuotasByCrm
	public List getJfiQuotasByCrm(CommonRecord crm, Pager pager);

	/**
	 * 金流限额定时调用功能实现
	 */
	public void quartJfiQuota();

	/**
	 * 修改指定期别、商户号的原数据状态
	 * 
	 * @param crm
	 */
	public void updateJfiQuotaStatus(CommonRecord crm);
}
