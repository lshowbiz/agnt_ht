
package com.joymain.jecs.pm.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.dao.JpmCardSeqDao;
import com.joymain.jecs.pm.model.JpmCardSeq;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

public class JpmCardSeqDaoHibernate extends BaseDaoHibernate implements JpmCardSeqDao {

    /**
     * @see com.joymain.jecs.pm.dao.JpmCardSeqDao#getJpmCardSeqs(com.joymain.jecs.pm.model.JpmCardSeq)
     */
    public List getJpmCardSeqs(final JpmCardSeq jpmCardSeq) {
        return getHibernateTemplate().find("from JpmCardSeq");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpmCardSeq == null) {
            return getHibernateTemplate().find("from JpmCardSeq");
        } else {
            // filter on properties set in the jpmCardSeq
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpmCardSeq).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpmCardSeq.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmCardSeqDao#getJpmCardSeq(Long id)
     */
    public JpmCardSeq getJpmCardSeq(final Long id) {
        JpmCardSeq jpmCardSeq = (JpmCardSeq) getHibernateTemplate().get(JpmCardSeq.class, id);
        if (jpmCardSeq == null) {
            log.warn("uh oh, jpmCardSeq with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JpmCardSeq.class, id);
        }

        return jpmCardSeq;
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmCardSeqDao#saveJpmCardSeq(JpmCardSeq jpmCardSeq)
     */    
    public void saveJpmCardSeq(final JpmCardSeq jpmCardSeq) {
        getHibernateTemplate().saveOrUpdate(jpmCardSeq);
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmCardSeqDao#removeJpmCardSeq(Long id)
     */
    public void removeJpmCardSeq(final Long id) {
        getHibernateTemplate().delete(getJpmCardSeq(id));
    }
    //added for getJpmCardSeqsByCrm
    public List getJpmCardSeqsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpmCardSeq jpmCardSeq where 1=1";

    	String userCode=crm.getString("userCode", "");
    	if(!StringUtil.isEmpty(userCode)){
    		hql+=" and userCode='"+userCode+"'";
    	}
    	String state=crm.getString("state", "");
    	if(!StringUtil.isEmpty(state)){
    		hql+=" and state='"+state+"'";
    	}

		String createBTime = crm.getString("createBTime", "");
		if (!StringUtil.isEmpty(createBTime)) {
			hql += " and createTime >= to_date('" + createBTime
					+ " 00:00:00','yyyy-MM-dd hh24:mi:ss')";
		}
		String createETime = crm.getString("createETime", "");
		if (!StringUtil.isEmpty(createETime)) {
			hql += " and createTime <= to_date('" + createETime
					+ " 23:59:59','yyyy-MM-dd hh24:mi:ss')";
		}
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by createTime asc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public JpmCardSeq getJpmCardSeqByCardNoAndSeq(String cardNo, String seq) {
		String hql="from JpmCardSeq where cardNo='"+cardNo+"' and sequenceNo = "+seq;
		return (JpmCardSeq) this.getObjectByHqlQuery(hql);
	}

	public String getJpmCardSeqsNotUse(int qty) {
		List list=this.findObjectsBySQL(" select * from (select * from jpm_card_seq s where s.state='0' order by dbms_random.random)  where rownum <= "+qty);

		
		if(list.size()>0){
			String ids="";
			for (int i = 0; i < list.size(); i++) {
				Map map=(Map) list.get(i);
				String id=(String) map.get("id");
				if(i>0){
					ids+=",";
				}
				ids+="'"+id+"'";
			}
			return ids;
		}else{
			return null;
		}
	}

	public List getJpmCardSeqByIds(String ids) {
		return this.findObjectsByHqlQuery("from JpmCardSeq jpmCardSeq where id in ("+ids+")");
	}
	
	public List getJpmCardSeqGrade(CommonRecord crm,String strAction){
		
		
		
		String sql="select s.grade,s.id,s.card_no,m.user_code,m.mi_user_name,m.papernumber,o.member_order_no," +
				"o.amount,o.discount_amount,p.state_province_name,c.city_name,d.district_name,o.address,o.mobiletele " +
				"from jpm_card_seq s, jmi_member m, jpo_member_order o left join jal_state_province p on p.state_province_id = o.province " +
				"left join jal_city c on c.city_id = o.city left join jal_district d on d.district_id = o.district where s.create_time >= " +
				"to_date('2013-1-19 00:00:00', 'yyyy-mm-dd hh24:mi:ss') " +
				"and s.create_time < to_date('2013-2-9 00:00:00', 'yyyy-mm-dd hh24:mi:ss') and" +
				" s.user_code = m.user_code and o.member_order_no = s.member_order_no";
		
		
		
    	String getGradeSql="";
    	
//    	String getGrade=crm.getString("getGrade", "");
    	String gradeNum=crm.getString("gradeNum", "");
    	
    	
    	if("getGrade".equals(strAction)){
    		sql+=" and s.grade is null ";
    		
    		String gradeBTime = crm.getString("gradeBTime", "");
    		if (!StringUtil.isEmpty(gradeBTime)) {
    			sql += " and o.check_time >= to_date('" + gradeBTime+ "','yyyy-MM-dd hh24:mi:ss')";
    		}
    		String gradeETime = crm.getString("gradeETime", "");
    		if (!StringUtil.isEmpty(gradeETime)) {
    			sql += " and o.check_time <= to_date('" + gradeETime+ "','yyyy-MM-dd hh24:mi:ss')";
    		}
    		
    		
    		getGradeSql="select * from ("+sql+"  order by dbms_random.random)  where rownum <=   "+gradeNum;
    		

    		return this.findObjectsBySQL(getGradeSql);
    		
    	}
		

		String grade=crm.getString("grade", "");
    	if(!StringUtil.isEmpty(grade)){
    		sql+=" and s.grade is not null";
    	}
		return this.findObjectsBySQL(sql+" order by grade");
	}
	
	
	
	
	
	
	
	
	
	
}
