
package com.joymain.jecs.bd.service.impl;

import java.util.List;

import com.joymain.jecs.bd.dao.JbdMemberFrozenDao;
import com.joymain.jecs.bd.model.JbdMemberFrozen;
import com.joymain.jecs.bd.service.JbdMemberFrozenManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JbdMemberFrozenManagerImpl extends BaseManager implements JbdMemberFrozenManager {
	private JbdMemberFrozenDao dao;
	public void setJbdMemberFrozenDao(JbdMemberFrozenDao dao) {
		this.dao = dao;
	}

	@Override
	public void saveJbdMemberFrozen(JbdMemberFrozen jbdMemberFrozen) {
		dao.saveJbdMemberFrozen(jbdMemberFrozen);
	}

	@Override
	public void removeJbdMemberFrozen(String userCode) {
		dao.removeJbdMemberFrozen(userCode);
	}
	
	@Override
	public void deleteJbdMemberFrozen(String[] userCodes){
		dao.deleteJbdMemberFrozen(userCodes);
	}

	@Override
	public JbdMemberFrozen getJbdMemberFrozen(String userCode) {
		return dao.getJbdMemberFrozen(userCode);
	}

	@Override
	public List getJbdMemberFrozenByCrm(CommonRecord crm, Pager pager) {
		return dao.getJbdMemberFrozenByCrm(crm, pager);
	}
	
}
