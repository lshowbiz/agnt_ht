
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.bd.dao.JbdMemberFrozenDao;
import com.joymain.jecs.bd.model.JbdMemberFrozen;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JbdMemberFrozenDaoHibernate extends BaseDaoHibernate implements JbdMemberFrozenDao {

	@Override
	public void saveJbdMemberFrozen(JbdMemberFrozen jbdMemberFrozen) {
		 getHibernateTemplate().saveOrUpdate(jbdMemberFrozen);
	}

	@Override
	public void removeJbdMemberFrozen(final String userCode) {
		getHibernateTemplate().delete(getJbdMemberFrozen(userCode));
	}
	
	public void deleteJbdMemberFrozen(String[] userCodes){
		if(userCodes!=null&&userCodes.length>0){
			for (String userCode : userCodes) {
				this.removeJbdMemberFrozen(userCode);
			}
		}
	}

	public JbdMemberFrozen getJbdMemberFrozen(final String userCode) {
		JbdMemberFrozen jbdMemberFrozen = (JbdMemberFrozen) getHibernateTemplate().get(JbdMemberFrozen.class, userCode);
        return jbdMemberFrozen;
    }

	@Override
	public List getJbdMemberFrozenByCrm(CommonRecord crm, Pager pager) {
		String sql = "select * from JBD_MEMBER_FROZEN f where 1=1 ";
		String userCode = crm.getString("userCode", "");
		if(StringUtils.isNotEmpty(userCode)){
    		sql +=" and f.USER_CODE like '%"+userCode+"%'";
    	}
		return this.findObjectsBySQL(sql, pager);
	}

}
