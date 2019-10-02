
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;

import com.joymain.jecs.bd.dao.JbdMemberCongrationsDao;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

public class JbdMemberCongrationsDaoHibernate extends BaseDaoHibernate implements JbdMemberCongrationsDao {

    public List getJbdMemberCongrationsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdMemberCongrations where 1=1";

		String userCode = crm.getString("userCode", "");
		if(!StringUtil.isEmpty(userCode)){
			hql += " and userCode='"+userCode+"'";
		}
		String yearMonth = crm.getString("yearMonth", "");
		if(!StringUtil.isEmpty(yearMonth)){
			hql += " and yearMonth="+yearMonth;
		}
		String starLevel = crm.getString("starLevel", "");
		if(!StringUtil.isEmpty(starLevel)){
			hql += " and starLevel="+starLevel;
		}
		hql += " order by yearMonth desc";
		return this.findObjectsByHqlQuery(hql, pager);
    }

}
