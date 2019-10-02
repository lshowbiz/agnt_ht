
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdSendRecord;
import com.joymain.jecs.bd.dao.JbdSendRecordDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdSendRecordDaoHibernate extends BaseDaoHibernate implements JbdSendRecordDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdSendRecordDao#getJbdSendRecords(com.joymain.jecs.bd.model.JbdSendRecord)
     */
    public List getJbdSendRecords(final JbdSendRecord jbdSendRecord) {
        return getHibernateTemplate().find("from JbdSendRecord");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdSendRecord == null) {
            return getHibernateTemplate().find("from JbdSendRecord");
        } else {
            // filter on properties set in the jbdSendRecord
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdSendRecord).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdSendRecord.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdSendRecordDao#getJbdSendRecord(Long id)
     */
    public JbdSendRecord getJbdSendRecord(final Long id) {
        JbdSendRecord jbdSendRecord = (JbdSendRecord) getHibernateTemplate().get(JbdSendRecord.class, id);
        if (jbdSendRecord == null) {
            log.warn("uh oh, jbdSendRecord with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdSendRecord.class, id);
        }

        return jbdSendRecord;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdSendRecordDao#saveJbdSendRecord(JbdSendRecord jbdSendRecord)
     */    
    public void saveJbdSendRecord(final JbdSendRecord jbdSendRecord) {
        getHibernateTemplate().saveOrUpdate(jbdSendRecord);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdSendRecordDao#removeJbdSendRecord(Long id)
     */
    public void removeJbdSendRecord(final Long id) {
        getHibernateTemplate().delete(getJbdSendRecord(id));
    }
    //added for getJbdSendRecordsByCrm
    public List getJbdSendRecordsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdSendRecord jbdSendRecord where 1=1";
    	
    	
    	
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by userCode desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
	public List getBdSendRecordReport(CommonRecord crm) {
		String cSql="";
    	String cardType=crm.getString("cardType", "");
    	if(!StringUtil.isEmpty(cardType)){
    		cSql=" and v.card_type='"+cardType+"'";
    	}
    	String allot =crm.getString("allot", "");
    	if(!StringUtil.isEmpty(allot)&&"true".equals(allot)){
    		cSql=" and v.card_type in('1','2','3','4')";
    	}
		String sqlQuery1="select 1 as bd_type, count(v.Remittance_Money) as qty,nvl(sum(v.Remittance_Money),0) as sum_money from v_jbd_send_record v where " +
				" v.w_week = "+crm.getString("wweek")+" and v.register_status = '1' and v.reissue_status = '1' "+cSql+" and remittance_money > 0";
		String companyCode =crm.getString("companyCode", "");
    	if(!StringUtil.isEmpty(companyCode)){
    		sqlQuery1+=" and company_code='"+companyCode+"' ";
    	}
		String sqlQuery2="select 2 as bd_type, count(v.Remittance_Money) as qty,nvl(sum(v.Remittance_Money),0) as sum_money from v_jbd_send_record v where " +
		" v.w_week = "+crm.getString("wweek")+" and v.register_status = '2' and v.reissue_status = '1' "+cSql+" and remittance_money > 0";
		if(!StringUtil.isEmpty(companyCode)){
			sqlQuery2+=" and company_code='"+companyCode+"' ";
		}
		String sqlQuery3="select 3 as bd_type, count(v.Remittance_Money) as qty,nvl(sum(v.Remittance_Money),0) as sum_money from v_jbd_send_record v where " +
		" v.w_week = "+crm.getString("wweek")+" and v.reissue_status = '2' "+cSql+" and remittance_money > 0";
		if(!StringUtil.isEmpty(companyCode)){
			sqlQuery3+=" and company_code='"+companyCode+"' ";
		}
		String sqlQuery4="select 4 as bd_type, count(v.Remittance_Money) as qty,nvl(sum(v.Remittance_Money),0) as sum_money from v_jbd_send_record v where " +
		" v.w_week = "+crm.getString("wweek")+" and v.register_status = '4'  "+cSql+" and remittance_money > 0";
		if(!StringUtil.isEmpty(companyCode)){
			sqlQuery4+=" and company_code='"+companyCode+"' ";
		}
		String sqlQuery6="select 6 as bd_type, count(v.Remittance_Money) as qty,nvl(sum(v.Remittance_Money),0) as sum_money from v_jbd_send_record v where " +
		" v.w_week = "+crm.getString("wweek")+" and v.reissue_status = '3' "+cSql+" and remittance_money > 0";
		if(!StringUtil.isEmpty(companyCode)){
			sqlQuery6+=" and company_code='"+companyCode+"' ";
		}
		String sqlQuery5="select 5 as bd_type, count(v.Remittance_Money) as qty,nvl(sum(v.Remittance_Money),0) as sum_money from v_jbd_send_record v where " +
		" v.w_week = "+crm.getString("wweek")+" and ((v.register_status = '2' and v.reissue_status = '3') or (v.register_status = '3' and v.reissue_status = '2')) "+cSql+"  and remittance_money > 0";
		if(!StringUtil.isEmpty(companyCode)){
			sqlQuery5+=" and company_code='"+companyCode+"' ";
		}
		List list1=this.findObjectsBySQL(sqlQuery1);
		List list2=this.findObjectsBySQL(sqlQuery2);
		List list3=this.findObjectsBySQL(sqlQuery3);
		List list6=this.findObjectsBySQL(sqlQuery6);
		List list5=this.findObjectsBySQL(sqlQuery5);
		List list4=this.findObjectsBySQL(sqlQuery4);
		list1.addAll(list2);
		list1.addAll(list3);
		list1.addAll(list6);
		list1.addAll(list5);
		list1.addAll(list4);
		return list1;
	}
}
