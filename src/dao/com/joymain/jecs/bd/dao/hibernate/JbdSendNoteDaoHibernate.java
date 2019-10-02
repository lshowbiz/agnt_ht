
package com.joymain.jecs.bd.dao.hibernate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdSendNote;
import com.joymain.jecs.bd.dao.JbdSendNoteDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdSendNoteDaoHibernate extends BaseDaoHibernate implements JbdSendNoteDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdSendNoteDao#getJbdSendNotes(com.joymain.jecs.bd.model.JbdSendNote)
     */
    public List getJbdSendNotes(final JbdSendNote jbdSendNote) {
        return getHibernateTemplate().find("from JbdSendNote");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdSendNote == null) {
            return getHibernateTemplate().find("from JbdSendNote");
        } else {
            // filter on properties set in the jbdSendNote
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdSendNote).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdSendNote.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdSendNoteDao#getJbdSendNote(Long id)
     */
    public JbdSendNote getJbdSendNote(final Long id) {
        JbdSendNote jbdSendNote = (JbdSendNote) getHibernateTemplate().get(JbdSendNote.class, id);
        if (jbdSendNote == null) {
            log.warn("uh oh, jbdSendNote with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdSendNote.class, id);
        }

        return jbdSendNote;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdSendNoteDao#saveJbdSendNote(JbdSendNote jbdSendNote)
     */    
    public void saveJbdSendNote(final JbdSendNote jbdSendNote) {
        getHibernateTemplate().saveOrUpdate(jbdSendNote);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdSendNoteDao#removeJbdSendNote(Long id)
     */
    public void removeJbdSendNote(final Long id) {
        getHibernateTemplate().delete(getJbdSendNote(id));
    }
    //added for getJbdSendNotesByCrm
    public List getJbdSendNotesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdSendNote jbdSendNote where 1=1";

    	hql+=this.returnHql(crm);
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
	public BigDecimal getSumRemittanceMoney(CommonRecord crm) {
		String hql="select sum(remittanceMoney) from JbdSendNote jbdSendNote where 1=1";
		hql+=returnHql(crm);
		return (BigDecimal)this.getHibernateTemplate().find(hql).get(0);
	}
	
	private String returnHql(CommonRecord crm){
		String hql="";
		
		String userCode=crm.getString("userCode", "");
    	if(!StringUtil.isEmpty(userCode)){
    		hql+=" and jmiMember.userCode='"+userCode+"'";
    	}

    	String companyCode =crm.getString("companyCode", "");
    	if(!StringUtil.isEmpty(companyCode)){
    		hql+=" and companyCode='"+companyCode+"'";
    	}
    	String bank =crm.getString("bank", "");
    	if(!StringUtil.isEmpty(bank)){
    		hql+=" and jmiMember.bank='"+bank+"'";
    	}
    	String allot=crm.getString("allot", "");
    	if("0".equals(allot)){
    		hql+=" and (remittanceBNum is null or operaterCode is null)";
    	}else if("1".equals(allot)){
    		hql+=" and remittanceBNum is not null and operaterCode is not null";
    	}
    	String remittanceMoneyGreater=crm.getString("remittanceMoneyGreater", "");
    	if(!StringUtil.isEmpty(remittanceMoneyGreater)){
    		hql+=" and remittanceMoney > "+remittanceMoneyGreater;
    	}
    	String operaterCode =crm.getString("operaterCode", "");
    	if(!StringUtil.isEmpty(operaterCode)){
    		hql+=" and operaterCode='"+operaterCode+"'";
    	}
    	String registerStatus =crm.getString("registerStatus", "");
    	if(!StringUtil.isEmpty(registerStatus)){
    		hql+=" and registerStatus='"+registerStatus+"'";
    	}
    	String reissueStatus =crm.getString("reissueStatus", "");
    	if(!StringUtil.isEmpty(reissueStatus)){
    		hql+=" and reissueStatus='"+reissueStatus+"'";
    	}
    	String toReissue =crm.getString("toReissue", "");
    	if(!StringUtil.isEmpty(toReissue)&&"toReissue".equals(toReissue)){
    		hql+=" and ((registerStatus = '2' and reissueStatus = '3') or (registerStatus = '3' and reissueStatus = '2'))";
    	}
    	
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	String startOperaterTime = crm.getString("startOperaterTime", "");
    	String endOperaterTime = crm.getString("endOperaterTime", "");
    	if (!StringUtils.isEmpty(startOperaterTime)) {
				hql += " and operaterTime >=to_date('" + startOperaterTime + "','yyyy-MM-dd hh24:mi:ss') ";
		
		}
		if (!StringUtils.isEmpty(endOperaterTime)) {
				hql += " and operaterTime <=to_date('" +endOperaterTime+ "','yyyy-MM-dd hh24:mi:ss')  ";
			
		}

    	String startCreateTime = crm.getString("startCreateTime", "");
    	String endCreateTime = crm.getString("endCreateTime", "");
    	if (!StringUtils.isEmpty(startCreateTime)) {
				hql += " and createTime >=to_date('" +startCreateTime + "','yyyy-MM-dd hh24:mi:ss') ";
		
		}
		if (!StringUtils.isEmpty(endCreateTime)) {
				hql += " and createTime <=to_date('" + endCreateTime + "','yyyy-MM-dd hh24:mi:ss')  ";

		}

		
		
    	String remittanceBNum =crm.getString("remittanceBNum", "");
    	if(!StringUtil.isEmpty(remittanceBNum)){
    		hql+=" and remittanceBNum='"+remittanceBNum+"'";
    	}
    	
    	
    	
		
		return hql;
	}
}
