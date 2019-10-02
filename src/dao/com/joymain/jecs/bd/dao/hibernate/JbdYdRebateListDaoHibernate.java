
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdYdRebateList;
import com.joymain.jecs.bd.dao.JbdYdRebateListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdYdRebateListDaoHibernate extends BaseDaoHibernate implements JbdYdRebateListDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdYdRebateListDao#getJbdYdRebateLists(com.joymain.jecs.bd.model.JbdYdRebateList)
     */
    public List getJbdYdRebateLists(final JbdYdRebateList jbdYdRebateList) {
        return getHibernateTemplate().find("from JbdYdRebateList");

    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdYdRebateListDao#getJbdYdRebateList(BigDecimal id)
     */
    public JbdYdRebateList getJbdYdRebateList(final Long id) {
        JbdYdRebateList jbdYdRebateList = (JbdYdRebateList) getHibernateTemplate().get(JbdYdRebateList.class, id);
        if (jbdYdRebateList == null) {
            log.warn("uh oh, jbdYdRebateList with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdYdRebateList.class, id);
        }

        return jbdYdRebateList;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdYdRebateListDao#saveJbdYdRebateList(JbdYdRebateList jbdYdRebateList)
     */    
    public void saveJbdYdRebateList(final JbdYdRebateList jbdYdRebateList) {
        getHibernateTemplate().saveOrUpdate(jbdYdRebateList);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdYdRebateListDao#removeJbdYdRebateList(BigDecimal id)
     */
    public void removeJbdYdRebateList(final Long id) {
        getHibernateTemplate().delete(getJbdYdRebateList(id));
    }
    //added for getJbdYdRebateListsByCrm
    public List getJbdYdRebateListsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdYdRebateList jbdYdRebateList where 1=1";
    	
    	String userCode=crm.getString("userCode", "");
    	if(!StringUtil.isEmpty(userCode)){
    		hql+=" and jbdYdRebateList.jmiMember.userCode='"+userCode+"'";
    	}
    	String freezeStatus=crm.getString("freezeStatus", "");
    	if(!StringUtil.isEmpty(freezeStatus)){
    		hql+=" and  freezeStatus='"+freezeStatus+"'";
    	}
    	String sendStatus = crm.getString("sendStatus", "");
		if (!StringUtil.isEmpty(sendStatus)) {
			hql += " and sendStatus=" + sendStatus  ;
		}
    	
    	String startCalcTime=crm.getString("startCalcTime", "");
    	if(!StringUtil.isEmpty(startCalcTime)){
    		hql += " and calcTime >=to_date('"+startCalcTime+" ','yyyy-mm-dd HH24:MI:SS') ";
    	}
    	String endCalcTime=crm.getString("endCalcTime", "");
    	if(!StringUtil.isEmpty(endCalcTime)){
    		hql += " and calcTime<=to_date('"+endCalcTime+"  ','yyyy-mm-dd HH24:MI:SS') ";
    	}
    	String memberType=crm.getString("memberType", "");
    	if(!StringUtil.isEmpty(memberType)){
    		hql += " and memberType ='"+memberType+"'  ";
    	}
    	
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    public List getVJbdYdRebateListsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from VJbdYdRebateList jbdYdRebateList where 1=1";
    	
    	//crm.addField("userCodeDetail", userCodeDetail);
    	String userCode=crm.getString("userCode", "");
    	if(!StringUtil.isEmpty(userCode)){
    		hql+=" and jbdYdRebateList.jmiMember.userCode='"+userCode+"'";
    	}
    	
    	String freezeStatus=crm.getString("freezeStatus", "");
    	if(!StringUtil.isEmpty(freezeStatus)){
    		hql+=" and  freezeStatus='"+freezeStatus+"'";
    	}
    	
    	String sendStatus = crm.getString("sendStatus", "");
		if (!StringUtil.isEmpty(sendStatus)) {
			hql += " and sendStatus=" + sendStatus  ;
		}
    	
    	String startCalcTime=crm.getString("startCalcTime", "");
    	if(!StringUtil.isEmpty(startCalcTime)){
    		hql += " and calcTime >=to_date('"+startCalcTime+" ','yyyy-mm-dd HH24:MI:SS') ";
    	}
    	String endCalcTime=crm.getString("endCalcTime", "");
    	if(!StringUtil.isEmpty(endCalcTime)){
    		hql += " and calcTime<=to_date('"+endCalcTime+"  ','yyyy-mm-dd HH24:MI:SS') ";
    	}
    	
    	String memberType=crm.getString("memberType", "");
    	if(!StringUtil.isEmpty(memberType)){
    		hql += " and memberType ='"+memberType+"'  ";
    	}
    	
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
