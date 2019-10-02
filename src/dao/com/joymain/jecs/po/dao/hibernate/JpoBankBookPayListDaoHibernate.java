package com.joymain.jecs.po.dao.hibernate;

import java.util.Date;
import java.util.List;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.po.dao.JpoBankBookPayListDao;
import com.joymain.jecs.po.model.JpoBankBookPayList;

public class JpoBankBookPayListDaoHibernate extends BaseDaoHibernate implements JpoBankBookPayListDao{

	@Override
	public List<JpoBankBookPayList> getBankBookPayListByOrderNo(String orderNo) {
		String sql = "from JpoBankBookPayList t where t.memberOrderNo=?";
		return this.getHibernateTemplate().find(sql, orderNo);
	}

	@Override
	public Date getDBdate() {
		return super.getDbDate();
	}
	public void saveJpoBankBookPayListList(List<JpoBankBookPayList> list){
		this.getHibernateTemplate().saveOrUpdateAll(list);
	}
}
