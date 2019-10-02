package com.joymain.jecs.memberrecord.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.memberrecord.dao.JmiMemberRecordDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JmiMemberRecordDaoHibernate extends BaseDaoHibernate implements
		JmiMemberRecordDao {
	public List getJmiMemberRecordList(CommonRecord crm, Pager pager) {
		StringBuffer hql = new StringBuffer(200);
		hql.append(" select a.user_code,a.mi_user_name,a.papernumber,sum(b.send_money+b.current_dev) as sum_money,c.balance as jbalance,d.balance ");
		hql.append(" from jmi_member a left join JBD_SEND_RECORD_HIST b on a.user_code=b.user_code ");
		hql.append(" left join jfi_bankbook_balance c on c.user_code=a.user_code ");
		hql.append(" left join FI_BANKBOOK_BALANCE d on d.user_code=a.user_code where 1=1 ");

		String userCode = crm.getString("usercode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql.append(" and a.user_code = '" + userCode + "'" );
		}
		
		String papernumber = crm.getString("papernumber", "");
		if (!StringUtils.isEmpty(papernumber)) {
			hql.append(" and a.papernumber = '" + papernumber + "'" );
		}
		hql.append("  and d.BANKBOOK_TYPE='1' ");
		hql.append("  group by  a.user_code,a.mi_user_name,a.papernumber,c.balance,d.balance  ");
		return this.findObjectsBySQL(hql.toString(), pager);
	}

}
