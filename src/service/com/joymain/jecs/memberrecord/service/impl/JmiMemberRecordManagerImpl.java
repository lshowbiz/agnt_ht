
package com.joymain.jecs.memberrecord.service.impl;

import java.util.List;

import com.joymain.jecs.memberrecord.dao.JmiMemberRecordDao;
import com.joymain.jecs.memberrecord.service.JmiMemberRecordManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JmiMemberRecordManagerImpl extends BaseManager implements JmiMemberRecordManager {
    private JmiMemberRecordDao jmiMemberRecordDao;
    public JmiMemberRecordDao getJmiMemberRecordDao() {
		return jmiMemberRecordDao;
	}

	public void setJmiMemberRecordDao(JmiMemberRecordDao jmiMemberRecordDao) {
		this.jmiMemberRecordDao = jmiMemberRecordDao;
	}

	@Override
	public List getJmiMemberRecordList(CommonRecord crm, Pager pager) {
		// TODO Auto-generated method stub
		return jmiMemberRecordDao.getJmiMemberRecordList(crm,pager);
	}

}
