
package com.joymain.jecs.am.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.am.dao.AmAnnounceDao;
import com.joymain.jecs.am.model.AmAnnounce;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class AmAnnounceDaoHibernate extends BaseDaoHibernate implements AmAnnounceDao {

/**
		public List getPiProductsByHql(String hql){
			return getHibernateTemplate().find(hql);
		}

		public List getPiProductsBySql(String sql){
		}
**/		
    /**
     * @see com.joymain.jecs.am.dao.AmAnnounceDao#getAmAnnounces(com.joymain.jecs.am.model.AmAnnounce)
     */
    public List getAmAnnounces(final AmAnnounce amAnnounce) {
        return getHibernateTemplate().find("from AmAnnounce");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (amAnnounce == null) {
            return getHibernateTemplate().find("from AmAnnounce");
        } else {
            // filter on properties set in the amAnnounce
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(amAnnounce).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(AmAnnounce.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.am.dao.AmAnnounceDao#getAmAnnounce(String aaNo)
     */
    public AmAnnounce getAmAnnounce(final String aaNo) {
        AmAnnounce amAnnounce = (AmAnnounce) getHibernateTemplate().get(AmAnnounce.class, aaNo);
        if (amAnnounce == null) {
            log.warn("uh oh, amAnnounce with aaNo '" + aaNo + "' not found...");
            throw new ObjectRetrievalFailureException(AmAnnounce.class, aaNo);
        }

        return amAnnounce;
    }

    /**
     * @see com.joymain.jecs.am.dao.AmAnnounceDao#saveAmAnnounce(AmAnnounce amAnnounce)
     */    
    public void saveAmAnnounce(final AmAnnounce amAnnounce) {
        getHibernateTemplate().saveOrUpdate(amAnnounce);
    }

    /**
     * @see com.joymain.jecs.am.dao.AmAnnounceDao#removeAmAnnounce(String aaNo)
     */
    public void removeAmAnnounce(final String aaNo) {
        getHibernateTemplate().delete(getAmAnnounce(aaNo));
    }
    //added for getAmAnnouncesByCrm
    public List getAmAnnouncesByCrm(CommonRecord crm, Pager pager){
//    	String hql = "from AmAnnounce amAnnounce  where 1=1";
    	
    	String sql = "select distinct am.AA_NO aaNo,am.COMPANY_CODE companyCode,am.SUBJECT subject, " +
    		"am.issue_user_no ,am.STATUS status,am.CREATE_TIME, am.issuer_name ,am.end_time " +
    			" ,am.check_user_no, am.checker_name ,am.anno_Class_No,am.highlight,am.out_announce from am_announce am,am_announce_permit ap where 1=1  ";
    	/*** ����Լ��Ĳ�ѯ���������***/
    	//
    	
    	String reply_status = crm.getString("reply_status", "");
    	   	
    	String companyCode = crm.getString("companyCode", "");
    	if(StringUtils.isNotEmpty(companyCode)){
    		sql +=" and am.COMPANY_CODE='"+companyCode+"'";
    	}
    	
    	String issuerName = crm.getString("issuerName", "");
    	if(StringUtils.isNotEmpty(issuerName)){
    		sql +=" and am.issue_user_no='"+issuerName+"'";
    	}
    	
    	String viewFlag = crm.getString("viewFlag", "");
    	if("view".equals(viewFlag)){
    		sql +=" and (am.END_TIME >= sysdate or am.END_TIME is null) and (am.start_time <= sysdate or am.start_time is null)";
    	}
    	
    	String subject = crm.getString("subject", "");
    	if(StringUtils.isNotEmpty(subject)){
    		sql +=" and am.subject like '%"+subject+"%'";
    	}
    	
    	String annoClassNo = crm.getString("annoClassNo","");
    	if(StringUtils.isNotEmpty(annoClassNo)){
    		sql += " and am.anno_Class_No='"+annoClassNo+"'";
    	}
    	
    	String content = crm.getString("content", "");
    	if(StringUtils.isNotEmpty(content)){
    		sql +=" and am.content like '%"+content+"%'";
    	}
    	
    	String status = crm.getString("status", "");
    	if(StringUtils.isNotEmpty(status)){
    		sql +=" and am.STATUS = "+status;
    	}
    	String permitClass = crm.getString("permitClass", "");
    	if(StringUtils.isNotEmpty(permitClass)){
    		sql +=" and am.AA_NO=ap.AA_NO and ap.PERMIT_NO in('"+permitClass+"')";
    	}
    	
    	String createTimeStart = crm.getString("createTimeStart","");
    	String createTimeEnd = crm.getString("createTimeEnd","");
    	if(StringUtils.isNotEmpty(createTimeStart)){
    		sql += " and am.CREATE_TIME >=to_date('"+createTimeStart+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
    	}
    	if(StringUtils.isNotEmpty(createTimeEnd)){
    		sql += " and am.CREATE_TIME <=to_date('"+createTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
    	}
    	
    	if(pager == null){
    		return this.findObjectsBySQL(sql);
    	}
    	if (!pager.getLimit().getSort().isSorted()) {
			//ȱʡ����
    		sql += " order by CREATE_TIME desc";
		} else {
			sql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
    	
    	if("2".equals(reply_status)){
    		sql = "select * from ( "+
			sql+" ) t1 ,(select * from am_announce_record s where s.user_no='"+crm.getString("browserNo")+"') t2  where t1.aano = t2.aa_no(+) and t2.user_no is null ";
    	}
    	
    	if("3".equals(reply_status)){
    		sql = "select * from ( "+
    			sql+" ) t1 ,(select * from am_announce_record s where s.user_no='"+crm.getString("browserNo")+"') t2 where t1.aano = t2.aa_no and t2.user_no='"+crm.getString("browserNo")+"'";
    	}
    	
    	
		return this.findObjectsBySQL(sql, pager);
    }

	public int removeAnnounceBatch(String aanos) {
		
    	
		String  hql = "delete from am_announce_permit t where t.aa_no in("+aanos+")";
    	
    	this.getJdbcTemplate().execute(hql);
    	
    	hql = "delete from am_announce_record t where t.aa_no in("+aanos+")";
    	
    	this.getJdbcTemplate().execute(hql);
    	
    	hql = "delete from am_announce t where t.aa_no in("+aanos+")";
    	
    	this.getJdbcTemplate().execute(hql);
    	
		return 0;
		
	}

	public void checkAnnounceBatch(String aanos,String userCode,String userName) {
		String[] aanosList = aanos.split(",");
    	for(int i=0;i<aanosList.length;i++){
    		AmAnnounce amAnnounce = (AmAnnounce) getHibernateTemplate().get(AmAnnounce.class, aanosList[i]);

    		amAnnounce.setCheckUserNo(userCode);
    		amAnnounce.setCheckerName(userName);
    		amAnnounce.setCheckTime(new Date());
    		amAnnounce.setStatus(1);
    		
    		this.getHibernateTemplate().saveOrUpdate(amAnnounce);
    	}
		
	}

	public long getAnnounceReadNum(CommonRecord crm, Pager pager) {
		String sql = "select distinct am.AA_NO aaNo,am.COMPANY_CODE companyCode,am.SUBJECT subject, " +
		"am.issue_user_no ,am.STATUS status,am.CREATE_TIME, am.issuer_name ,am.end_time " +
			" ,am.check_user_no, am.checker_name from am_announce am,am_announce_permit ap where 1=1  ";

		String reply_status = crm.getString("reply_status", "");
		   	
		String companyCode = crm.getString("companyCode", "");
		if(StringUtils.isNotEmpty(companyCode)){
			sql +=" and am.COMPANY_CODE='"+companyCode+"'";
		}	
		
		String viewFlag = crm.getString("viewFlag", "");
		if("view".equals(viewFlag)){
			sql +=" and (am.END_TIME >= sysdate or am.END_TIME is null)  and (am.start_time <= sysdate or am.start_time is null)";
		}
		
		String status = crm.getString("status", "");
		if(StringUtils.isNotEmpty(status)){
			sql +=" and am.STATUS = "+status;
		}
		String permitClass = crm.getString("permitClass", "");
		if(StringUtils.isNotEmpty(permitClass)){
			sql +=" and am.AA_NO=ap.AA_NO and ap.PERMIT_NO in('"+permitClass+"')";
		}

		
		sql = "select * from ( "+sql+" ) t1 ,(select * from am_announce_record s where s.user_no='"+crm.getString("browserNo")+"') t2 where t1.aano = t2.aa_no and t2.user_no='"+crm.getString("browserNo")+"' ";

		return this.getTotalCountBySQL(sql);
	}

	public long getAnnounceNoReadNum(CommonRecord crm, Pager pager) {
		String sql = "select distinct am.AA_NO aaNo,am.COMPANY_CODE companyCode,am.SUBJECT subject, " +
		"am.issue_user_no ,am.STATUS status,am.CREATE_TIME, am.issuer_name ,am.end_time " +
			" ,am.check_user_no, am.checker_name from am_announce am,am_announce_permit ap where 1=1  ";

		String reply_status = crm.getString("reply_status", "");
		   	
		String companyCode = crm.getString("companyCode", "");
		if(StringUtils.isNotEmpty(companyCode)){
			sql +=" and am.COMPANY_CODE='"+companyCode+"'";
		}	
		
		String viewFlag = crm.getString("viewFlag", "");
		if("view".equals(viewFlag)){
			sql +=" and (am.END_TIME >= sysdate or am.END_TIME is null)  and (am.start_time <= sysdate or am.start_time is null)";
		}
		
		String status = crm.getString("status", "");
		if(StringUtils.isNotEmpty(status)){
			sql +=" and am.STATUS = "+status;
		}
		String permitClass = crm.getString("permitClass", "");
		if(StringUtils.isNotEmpty(permitClass)){
			sql +=" and am.AA_NO=ap.AA_NO and ap.PERMIT_NO in('"+permitClass+"')";
		}
		sql = "select * from ("+sql+") t1 ,(select * from am_announce_record s where s.user_no='"+crm.getString("browserNo")+"') t2  where t1.aano = t2.aa_no(+) and t2.user_no is null ";
		return this.getTotalCountBySQL(sql);
	}
	
	/**
	 * 下架公告
	 * @param aanos 格式 '1','2','3'。。。。
	 */
	public void updateOutAmAnnounce(String aanos){
		String sql = "update AM_ANNOUNCE set OUT_ANNOUNCE='1' where AA_NO in("+aanos+")";
		this.getJdbcTemplate().update(sql);
	}
}
