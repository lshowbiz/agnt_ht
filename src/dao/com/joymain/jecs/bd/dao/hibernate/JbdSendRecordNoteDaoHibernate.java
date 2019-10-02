
package com.joymain.jecs.bd.dao.hibernate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdSendRecordNote;
import com.joymain.jecs.bd.dao.JbdSendRecordNoteDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.hibernate.LockMode;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdSendRecordNoteDaoHibernate extends BaseDaoHibernate implements JbdSendRecordNoteDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdSendRecordNoteDao#getJbdSendRecordNotes(com.joymain.jecs.bd.model.JbdSendRecordNote)
     */
    public List getJbdSendRecordNotes(final JbdSendRecordNote jbdSendRecordNote) {
        return getHibernateTemplate().find("from JbdSendRecordNote");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdSendRecordNote == null) {
            return getHibernateTemplate().find("from JbdSendRecordNote");
        } else {
            // filter on properties set in the jbdSendRecordNote
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdSendRecordNote).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdSendRecordNote.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdSendRecordNoteDao#getJbdSendRecordNote(Long id)
     */
    public JbdSendRecordNote getJbdSendRecordNote(final Long id) {
        JbdSendRecordNote jbdSendRecordNote = (JbdSendRecordNote) getHibernateTemplate().get(JbdSendRecordNote.class, id);
        if (jbdSendRecordNote == null) {
            log.warn("uh oh, jbdSendRecordNote with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdSendRecordNote.class, id);
        }

        return jbdSendRecordNote;
    }

    public JbdSendRecordNote getJbdSendRecordNoteForUpdate(final Long id) {
        JbdSendRecordNote jbdSendRecordNote = (JbdSendRecordNote) getHibernateTemplate().get(JbdSendRecordNote.class, id, LockMode.UPGRADE);
        if (jbdSendRecordNote == null) {
            log.warn("uh oh, jbdSendRecordNote with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdSendRecordNote.class, id);
        }

        return jbdSendRecordNote;
    }
    
    /**
     * @see com.joymain.jecs.bd.dao.JbdSendRecordNoteDao#saveJbdSendRecordNote(JbdSendRecordNote jbdSendRecordNote)
     */    
    public void saveJbdSendRecordNote(final JbdSendRecordNote jbdSendRecordNote) {
        getHibernateTemplate().saveOrUpdate(jbdSendRecordNote);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdSendRecordNoteDao#removeJbdSendRecordNote(Long id)
     */
    public void removeJbdSendRecordNote(final Long id) {
        getHibernateTemplate().delete(getJbdSendRecordNote(id));
    }
    //added for getJbdSendRecordNotesByCrm
    public List getJbdSendRecordNotesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdSendRecordNote jbdSendRecordNote where 1=1";
    	/** 
	---example---
	String userCode = crm.getString("userCode", "");
	if(StringUtils.isNotEmpty(userCode)){
		hql += "???????????";
	}
	 ***/
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    public List getJbdSendRecordsBySqlByCrm(CommonRecord crm, Pager pager) {
		String sql=" select h.current_dev, h.id,h.w_week,h.user_code,h.name,h.card_type,h.bank,h.bankaddress,h.bankbook,h.bankcard,h.remittance_money,h.operater_code,h.remittance_b_num,h.freeze_status from jbd_send_record_note h" +
				" where 1=1 ";

    	
    	sql+=this.returnSQLBySql(crm);

		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			sql += " order by h.current_dev desc";
		} else {
			sql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsBySQL(sql, pager);
	}

	private String returnSQLBySql(CommonRecord crm){
		String sql="";
		String wweek=crm.getString("wweek", "");
    	if(!StringUtil.isEmpty(wweek)){
    		sql+=" and h.w_week="+wweek;
    	}
		String cardType=crm.getString("cardType", "");
    	if(!StringUtil.isEmpty(cardType)){
    		sql+=" and h.card_type='"+cardType+"'";
    	}
    	String userCode=crm.getString("userCode", "");
    	if(!StringUtil.isEmpty(userCode)){
    		sql+=" and h.user_code='"+userCode+"'";
    	}
    	String notEqualCardType=crm.getString("notEqualCardType", "");
    	if(!StringUtil.isEmpty(notEqualCardType)){
    		//sql+=" and  (h.card_type !='"+notEqualCardType+"' or  h.member_level >=10) ";
    	}
    	String exitDateNull=crm.getString("exitDateNull", "");
    	if("exitDateNull".equals(exitDateNull)){
    		sql+=" and h.exit_date is null";
    	}
    	String active=crm.getString("active", "");
    	if(!StringUtils.isEmpty(active)){
    		sql+=" and h.active ='"+active+"'";
    	}
    	String freezeStatus=crm.getString("freezeStatus", "");
    	if(!StringUtils.isEmpty(freezeStatus)){
    		sql+=" and h.freeze_status ="+freezeStatus;
    	}
    	String remittanceMoneyGreater=crm.getString("remittanceMoneyGreater", "");
    	if(!StringUtil.isEmpty(remittanceMoneyGreater)){
    		sql+=" and h.send_money > "+remittanceMoneyGreater;
    	}
    	String registerStatus =crm.getString("registerStatus", "");
    	if(!StringUtil.isEmpty(registerStatus)){
    		sql+=" and h.register_status='"+registerStatus+"'";
    	}
    	String reissueStatus =crm.getString("reissueStatus", "");
    	if(!StringUtil.isEmpty(reissueStatus)){
    		sql+=" and h.reissue_status='"+reissueStatus+"'";
    	}
    	String companyCode =crm.getString("companyCode", "");
    	if(!StringUtil.isEmpty(companyCode)){
    		sql+=" and h.company_code='"+companyCode+"'";
    	}
    	String currentDevGreater =crm.getString("currentDevGreater", "");
    	if(!StringUtil.isEmpty(currentDevGreater)){
    		sql+=" and h.current_dev >  "+currentDevGreater;
    	}
    	String sendStatusDev =crm.getString("sendStatusDev", "");
    	if(!StringUtil.isEmpty(sendStatusDev)){
    		if("1".equals(sendStatusDev)){
    			sql+=" and (h.send_status_dev='"+sendStatusDev+"' or h.send_status_dev is null )";
    		}else if("2".equals(sendStatusDev)){
    			sql+=" and (h.send_status_dev='"+sendStatusDev+"' ";
    		}
    		
    	}
    	String bank =crm.getString("bank", "");
    	if(!StringUtil.isEmpty(bank)){
    		sql+=" and h.bank='"+bank+"'";
    	}
    	String operaterCode =crm.getString("operaterCode", "");
    	if(!StringUtil.isEmpty(operaterCode)){
    		sql+=" and h.operater_code='"+operaterCode+"'";
    	}
    	String allot=crm.getString("allot", "");
    	if("0".equals(allot)){
    		sql+=" and (h.remittance_b_num is null or h.operater_code is null)";
    	}else if("1".equals(allot)){
    		sql+=" and h.remittance_b_num is not null and h.operater_code is not null";
    	}
    	

    	String flag=crm.getString("flag", "");
    	String allotFi=crm.getString("allotFi", "");
    	if("allotFi".equals(allotFi)){
        	String startAllotMoney=crm.getString("startAllotMoney", "");
    		if(StringUtil.isDouble(startAllotMoney)){
        		sql+=" And b.Bonus_Balance >= "+startAllotMoney+" ";
        	}else{
        		sql+=" And b.Bonus_Balance > 0 ";
        	}
        	String endAllotMoney=crm.getString("endAllotMoney", "");
    		sql+=" and b.flag = '"+crm.getString("flag", "")+"' and b.status='0' ";
        	if(StringUtil.isDouble(endAllotMoney)){
        		sql+=" And b.Bonus_Balance <= "+endAllotMoney+" ";
        	}
    	}
    	
    	if("0".equals(allot)){
    		
    		if("0".equals(flag)){
            	String startAllotMoney=crm.getString("startAllotMoney", "");
            	if(StringUtil.isDouble(startAllotMoney)){
            		sql+=" And Exists (Select 1 From Jbd_Bonus_Balance b Where h.User_Code = b.User_Code And b.Bonus_Balance >= "+startAllotMoney+" ";
            	}else{
            		sql+=" And Exists (Select 1 From Jbd_Bonus_Balance b Where h.User_Code = b.User_Code And b.Bonus_Balance  > 0  ";
            	}
            	sql+=" and b.flag = '"+crm.getString("flag", "")+"' and b.status='0' ";
            	String endAllotMoney=crm.getString("endAllotMoney", "");
            	if(StringUtil.isDouble(endAllotMoney)){
            		sql+=" And b.Bonus_Balance  <= "+endAllotMoney+" )";
            	}else{
            		sql+=" )";
            	}
    		}else{
    			sql+=" And Exists (Select 1 From Jbd_Bonus_Balance b Where h.User_Code = b.User_Code And b.Bonus_Balance > 0 and b.flag='"+flag+"' and b.status='0' ";

    	    	String startDate=crm.getString("startDate", "");
    	    	String endDate=crm.getString("endDate", "");

	        	if(StringUtil.isDate(startDate)){
	        		sql+=" and b.flag_time >= to_date('"+startDate+" 00:00:00', 'yyyy-mm-dd hh24:mi:ss') ";
	        	}

	        	if(StringUtil.isDate(endDate)){
	        		sql+=" and b.flag_time <= to_date('"+endDate+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss') ";
	        	}
	        	sql+=" ) ";
	        	
    		}
        	
    	}
    	return sql;
	}
	public BigDecimal getJbdSendRecordsBySqlByCrmSum(CommonRecord crm) {
		String sql="select nvl(sum(remittance_money),0) as sum_money from jbd_send_record_note h where 1=1 ";
		sql+=this.returnSQLBySql(crm);
		return new BigDecimal((String)((Map)((List)this.findObjectsBySQL(sql)).get(0)).get("sum_money"));
	}
	

    public List getJbdSendRecordHistsByCrm(CommonRecord crm, Pager pager){

    	String tableName=crm.getString("tableName", "JbdSendRecordNote");
    	
    	String hql = "from "+tableName+" jbdSendRecordHist where 1=1";
    	hql+=this.returnHql(crm);
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by jbdSendRecordHist.wweek desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    

	private String returnHql(CommonRecord crm){
		String hql = "";
    	String userCode=crm.getString("userCode", "");
    	if(!StringUtil.isEmpty(userCode)){
    		hql+=" and jbdSendRecordHist.jmiMember.userCode='"+userCode+"'";
    	}
    	String wweek=crm.getString("wweek", "");
    	if(!StringUtil.isEmpty(wweek)&&StringUtil.isInteger(wweek)){
    		hql+=" and jbdSendRecordHist.wweek="+wweek;
    	}
    	String cardType=crm.getString("cardType", "");
    	if(!StringUtil.isEmpty(cardType)){
    		hql+=" and jbdSendRecordHist.cardType='"+cardType+"'";
    	}
    	String name=crm.getString("name", "");
    	if(!StringUtil.isEmpty(name)){
    		hql+=" and jbdSendRecordHist.name='"+name+"'";
    	}
    	
//    	String startBonus=crm.getString("startBonus", "");
//    	if(!StringUtil.isEmpty(startBonus)&&StringUtil.isInteger(startBonus)){
//    		hql+=" and bdSendRecord.sendMoney>='"+StringUtil.formatInt(startBonus)+"'";
//    	}
//    	
//    	String endBonus=crm.getString("endBonus", "");
//    	if(!StringUtil.isEmpty(endBonus)&&StringUtil.isInteger(endBonus)){
//    		hql+=" and bdSendRecord.sendMoney<='"+StringUtil.formatInt(endBonus)+"'";
//    	}

    	String companyCode =crm.getString("companyCode", "");
    	if(!StringUtil.isEmpty(companyCode)){
    		hql+=" and jbdSendRecordHist.companyCode='"+companyCode+"'";
    	}
    	
    	String bank =crm.getString("bank", "");
    	if(!StringUtil.isEmpty(bank)){
    		hql+=" and jbdSendRecordHist.jmiMember.bank='"+bank+"'";
    	}

    	String allot=crm.getString("allot", "");
    	if("0".equals(allot)){
    		hql+=" and (jbdSendRecordHist.remittanceBNum is null or jbdSendRecordHist.operaterSysUser is null)";
    	}else if("1".equals(allot)){
    		hql+=" and jbdSendRecordHist.remittanceBNum is not null and jbdSendRecordHist.operaterSysUser is not null";
    	}
    	String exitDateNull=crm.getString("exitDateNull", "");
    	if("exitDateNull".equals(exitDateNull)){
    		hql+=" and jbdSendRecordHist.exitDate is null";
    	}
    	String active=crm.getString("active", "");
    	if(!StringUtil.isEmpty(active)){
    		hql+=" and jbdSendRecordHist.active='"+active+"'";
    	}
    	String notEqualCardType=crm.getString("notEqualCardType", "");
    	if(!StringUtil.isEmpty(notEqualCardType)){
    		//hql+=" and (jbdSendRecordHist.cardType !='"+notEqualCardType+"' or jbdSendRecordHist.memberLevel  >=10) ";
    	}
    	String remittanceMoneyGreater=crm.getString("remittanceMoneyGreater", "");
    	if(!StringUtil.isEmpty(remittanceMoneyGreater)){
    		hql+=" and jbdSendRecordHist.remittanceMoney > "+remittanceMoneyGreater;
    	}
    	
//    	String startRemittanceMoney =crm.getString("startRemittanceMoney", "");
//    	if(!StringUtil.isEmpty(startRemittanceMoney)&&StringUtil.isInteger(startRemittanceMoney)){
//    		hql+=" and bdSendRecord.remittanceMoney>="+startRemittanceMoney;
//    	}
//    	
//    	String endRemittanceMoney =crm.getString("endRemittanceMoney", "");
//    	if(!StringUtil.isEmpty(endRemittanceMoney)&&StringUtil.isInteger(endRemittanceMoney)){
//    		hql+=" and bdSendRecord.remittanceMoney<="+endRemittanceMoney;
//    	}
    	
    	
    	String operaterCode =crm.getString("operaterCode", "");
    	if(!StringUtil.isEmpty(operaterCode)){
    		hql+=" and jbdSendRecordHist.operaterSysUser.userCode='"+operaterCode+"'";
    	}
    	
    	String registerStatus =crm.getString("registerStatus", "");
    	if(!StringUtil.isEmpty(registerStatus)){
    		hql+=" and jbdSendRecordHist.registerStatus='"+registerStatus+"'";
    	}
    	
    	String reissueStatus =crm.getString("reissueStatus", "");
    	if(!StringUtil.isEmpty(reissueStatus)){
    		hql+=" and jbdSendRecordHist.reissueStatus='"+reissueStatus+"'";
    	}
    	
    	String bankcard =crm.getString("bankcard", "");
    	if(!StringUtil.isEmpty(bankcard)){
    		hql+=" and jbdSendRecordHist.jmiMember.bankcard='"+bankcard+"'";
    	}
    	
    	String bankbook =crm.getString("bankbook", "");
    	if(!StringUtil.isEmpty(bankbook)){
    		hql+=" and jbdSendRecordHist.jmiMember.bankbook='"+bankbook+"'";
    	}
    	String toReissue =crm.getString("toReissue", "");
    	if(!StringUtil.isEmpty(toReissue)&&"toReissue".equals(toReissue)){
    		hql+=" and ((jbdSendRecordHist.registerStatus = '2' and jbdSendRecordHist.reissueStatus = '3') or (jbdSendRecordHist.registerStatus = '3' and jbdSendRecordHist.reissueStatus = '2'))";
    	}
    	
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	String startOperaterTime = crm.getString("startOperaterTime", "");
    	String endOperaterTime = crm.getString("endOperaterTime", "");
    	if (!StringUtils.isEmpty(startOperaterTime)) {
			try {
				hql += " and jbdSendRecordHist.operaterTime >=to_date('" + dateFormat.format(dateFormat.parse(startOperaterTime)) + " 00:00:00','yyyy-MM-dd hh24:mi:ss') ";
			} catch (ParseException e) {
			}
		}
		if (!StringUtils.isEmpty(endOperaterTime)) {
			try {
				hql += " and jbdSendRecordHist.operaterTime <=to_date('" + dateFormat.format(dateFormat.parse(endOperaterTime)) + " 23:59:59','yyyy-MM-dd hh24:mi:ss')  ";
			} catch (ParseException e) {

			}
		}
    	
//		String sendLateCause =crm.getString("sendLateCause", "");
//    	if(!StringUtil.isEmpty(sendLateCause)){
//    		hql+=" and bdSendRecord.sendLateCause='"+sendLateCause+"'";
//    	}
		
    	String remittanceBNum =crm.getString("remittanceBNum", "");
    	if(!StringUtil.isEmpty(remittanceBNum)){
    		hql+=" and jbdSendRecordHist.remittanceBNum='"+remittanceBNum+"'";
    	}
    	
    	return hql;
	}
	

	public BigDecimal getSumRemittanceMoney(CommonRecord crm) {
		String hql="select nvl(sum(round(jbdSendRecordHist.remittanceMoney,2)),0) from JbdSendRecordNote jbdSendRecordHist where 1=1";
		hql+=returnHql(crm);
		return (BigDecimal)this.getHibernateTemplate().find(hql).get(0);
		
	}
	

	public List getJbdSendRecordsBySqlByCrmNew(CommonRecord crm, Pager pager) {
 		String sql=" select h.current_dev, h.id,h.w_week,h.user_code,h.name,h.card_type,h.bank,h.bankaddress,h.bankbook,h.bankcard,h.remittance_money," +
				"h.operater_code,h.remittance_b_num,h.freeze_status,h.member_type, h.register_status, h.reissue_status " +
				"from jbd_send_record_note h where  1=1 ";
		
		
		sql+=this.returnSQLBySql(crm);
		

		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			sql += " order by h.remittance_money desc";
		} else {
			sql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsBySQL(sql, pager);

	}
}
