
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;

import com.joymain.jecs.bd.dao.JbdMemberStarRewardsDao;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

public class JbdMemberStarRewardsDaoHibernate extends BaseDaoHibernate implements JbdMemberStarRewardsDao {

    public List getJbdMemberStarRewardsByCrm(CommonRecord crm, Pager pager){
    	String hql = "select j.id,j.user_code,j.f_year,j.rewards,j.is_reach,j.remark,j.member_remark,m.mi_user_name from JBD_MEMBER_STAR_REWARDS j, JMI_MEMBER m where j.user_code=m.user_code";

		String userCode = crm.getString("userCode", "");
		if(!StringUtil.isEmpty(userCode)){
			hql += " and j.user_code='"+userCode+"'";
		}
		String fyear = crm.getString("fyear", "");
		if(!StringUtil.isEmpty(fyear)){
			hql += " and j.f_year="+fyear;
		}
		String rewards = crm.getString("rewards", "");
		if(!StringUtil.isEmpty(rewards)){
			hql += " and j.rewards="+rewards;
		}
		String isReach = crm.getString("isReach", "");
		if(!StringUtil.isEmpty(isReach)){
			hql += " and j.is_reach="+isReach;
		}
		
		if (!pager.getLimit().getSort().isSorted()) {
		    hql += " order by j.f_year desc";
        } else {
            if("mi_user_name".equals(pager.getLimit().getSort().getProperty())) {
                hql += " ORDER BY m." + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
            } else {
                hql += " ORDER BY j." + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
            }
        }
		
		return this.findObjectsBySQL(hql, pager);
    }

}
