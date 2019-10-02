package com.joymain.jecs.bd.dao.hibernate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;
import com.joymain.jecs.bd.dao.JbdYd399RecommendListDao;
import com.joymain.jecs.bd.model.JbdYd399RecommendList;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;


public class JbdYd399RecommendListDaoHibernate extends BaseDaoHibernate implements JbdYd399RecommendListDao {
	
	
	public List getJbdYd399RecommendLists(final JbdYd399RecommendList jbdYd399RecommendList) {
		return getHibernateTemplate().findByExample(jbdYd399RecommendList);
	}


	public JbdYd399RecommendList getJbdYd399RecommendList(final Long id) {
		JbdYd399RecommendList jbdYd399RecommendList = (JbdYd399RecommendList) getHibernateTemplate().get(JbdYd399RecommendList.class, id);
		if (jbdYd399RecommendList == null) {
			log.warn("uh oh, jbdYd399RecommendList with id '" + id + "' not found...");
			throw new ObjectRetrievalFailureException(JbdYd399RecommendList.class, id);
		}
		return jbdYd399RecommendList;
	}

	@Override
	public void removeJbdYd399RecommendList(Long id) {
	    getHibernateTemplate().delete(getJbdYd399RecommendList(id));
	}

/*	public void saveJbdYd399RecommendList(final JbdYd399RecommendList jbdYd399RecommendList) {
		getHibernateTemplate().saveOrUpdate(jbdYd399RecommendList);
	}*/

/*	public void saveJbdYd399RecommendLists(List jbdYd399RecommendLists) {
		getHibernateTemplate().saveOrUpdateAll(jbdYd399RecommendLists);
	}
*/

	//added for getJbdYd399RecommendListsByCrm
	public List getJbdYd399RecommendListsByCrm(CommonRecord crm, Pager pager) {
		String hql = "from VJbdYd399RecommendList jbdYd399RecommendList where 1=1";
		
		String userCode = crm.getString("userCode", "");
		if (!StringUtil.isEmpty(userCode)) {
			hql += " and jbdYd399RecommendList.jmiMember.userCode='" + userCode + "'";
		}
		
		String memberType = crm.getString("memberType", "");
		if (!StringUtil.isEmpty(memberType)) {
			hql += " and jbdYd399RecommendList.memberType='" + memberType + "'";
		}
		
		String sendStatus = crm.getString("sendStatus", "");
		if (!StringUtil.isEmpty(sendStatus)) {
			hql += " and jbdYd399RecommendList.sendStatus='" + sendStatus + "'";
		}
		
		String freezeStatus = crm.getString("freezeStatus", "");
		if (!StringUtils.isEmpty(freezeStatus)) {
			hql += " and jbdYd399RecommendList.freezeStatus = " + freezeStatus;
		}
		
		String startCreateTime = crm.getString("startCreateTime", "");
		if (!StringUtils.isEmpty(startCreateTime)) {
			hql += " and jbdYd399RecommendList.calcTime >=to_date('" + startCreateTime + "','yyyy-MM-dd hh24:mi:ss') ";
		}
		
		String endCreateTime = crm.getString("endCreateTime", "");
		if (!StringUtils.isEmpty(endCreateTime)) {
			hql += " and jbdYd399RecommendList.calcTime <=to_date('" + endCreateTime + "','yyyy-MM-dd hh24:mi:ss') ";
		}
		
		if (!pager.getLimit().getSort().isSorted()) {
			//
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}
	
	
	
	public List getJbdYd399RecommendListsByCrm2(CommonRecord crm, Pager pager) {
		String hql = "from JbdYd399RecommendList jbdYd399RecommendList where 1=1";
		
		String userCode = crm.getString("userCode", "");
		if (!StringUtil.isEmpty(userCode)) {
			hql += " and jbdYd399RecommendList.jmiMember.userCode='" + userCode + "'";
		}
		
		String sendStatus = crm.getString("sendStatus", "");
		if (!StringUtil.isEmpty(sendStatus)) {
			hql += " and jbdYd399RecommendList.sendStatus='" + sendStatus + "'";
		}
		
		String freezeStatus = crm.getString("freezeStatus", "");
		if (!StringUtils.isEmpty(freezeStatus)) {
			hql += " and jbdYd399RecommendList.freezeStatus = " + freezeStatus;
		}
		
		String startCreateTime = crm.getString("startCreateTime", "");
		if (!StringUtils.isEmpty(startCreateTime)) {
			hql += " and jbdYd399RecommendList.calcTime >=to_date('" + startCreateTime + "','yyyy-MM-dd hh24:mi:ss') ";
		}
		
		String endCreateTime = crm.getString("endCreateTime", "");
		if (!StringUtils.isEmpty(endCreateTime)) {
			hql += " and jbdYd399RecommendList.calcTime <=to_date('" + endCreateTime + "','yyyy-MM-dd hh24:mi:ss') ";
		}
		
		if (!pager.getLimit().getSort().isSorted()) {
			//
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}
	
	
	public List getJbdYd399RecommendListsByCrmBySql(CommonRecord crm, Pager pager) {
		String sql = "select jbdYd399RecommendList.* from V_JBD_YD_399_RECOMMEND_LIST jbdYd399RecommendList where 1=1 ";

		String userCode = crm.getString("userCode", "");
		if (!StringUtil.isEmpty(userCode)) {
			sql += " and jbdYd399RecommendList.user_code='" + userCode + "'";
		}
		
		String memberType = crm.getString("memberType", "");
		if (!StringUtils.isEmpty(memberType)) {
			sql += " and jbdYd399RecommendList.member_type >= " + memberType;
		}
		
		String startCreateTime = crm.getString("startCreateTime", "");
		if (!StringUtils.isEmpty(startCreateTime)) {
			sql += " and jbdYd399RecommendList.calc_Time >=to_date('" + startCreateTime + "','yyyy-MM-dd hh24:mi:ss') ";
		}
		
		String endCreateTime = crm.getString("endCreateTime", "");
		if (!StringUtils.isEmpty(endCreateTime)) {
			sql += " and jbdYd399RecommendList.calc_Time >=to_date('" + endCreateTime + "','yyyy-MM-dd hh24:mi:ss') ";
		}
		
		String freezeStatus = crm.getString("freezeStatus", "");
		if (!StringUtils.isEmpty(freezeStatus)) {
			sql += " and jbdYd399RecommendList.freeze_status = " + freezeStatus;
		}
		
		String status = crm.getString("status", "");
		if (!StringUtils.isEmpty(status)) {
			sql += " and jbdYd399RecommendList.send_status = " + status;
		}

		if (!pager.getLimit().getSort().isSorted()) {
			//
			sql += " order by id desc";
		} else {
			sql += " ORDER BY jbdYd399RecommendList." + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsBySQL(sql, pager);
	}


	
}