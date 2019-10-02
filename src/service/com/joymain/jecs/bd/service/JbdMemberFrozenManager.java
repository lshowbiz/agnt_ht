
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.bd.model.JbdMemberFrozen;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdMemberFrozenManager extends Manager {
	/**
	 * 保存
	 * @param jbdMemberFrozen
	 */
	public void saveJbdMemberFrozen(JbdMemberFrozen jbdMemberFrozen);
	/**
	 * 删除
	 * @param userCode
	 */
	public void removeJbdMemberFrozen(final String userCode);
	/**
	 * 批量删除
	 * @param userCodes
	 */
	public void deleteJbdMemberFrozen(String[] userCodes);
	/**
	 * 获取JbdMemberFrozen
	 * @param userCode
	 * @return
	 */
	public JbdMemberFrozen getJbdMemberFrozen(final String userCode);
	/**
	 * 查询
	 * @param crm
	 * @param pager
	 */
	public List getJbdMemberFrozenByCrm(CommonRecord crm, Pager pager);
}

