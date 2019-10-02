
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiInvoice;
import com.joymain.jecs.fi.dao.JfiInvoiceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiInvoiceDaoHibernate extends BaseDaoHibernate implements JfiInvoiceDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiInvoiceDao#getJfiInvoices(com.joymain.jecs.fi.model.JfiInvoice)
     */
    public List getJfiInvoices(final JfiInvoice jfiInvoice) {
        return getHibernateTemplate().find("from JfiInvoice");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiInvoice == null) {
            return getHibernateTemplate().find("from JfiInvoice");
        } else {
            // filter on properties set in the jfiInvoice
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiInvoice).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiInvoice.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiInvoiceDao#getJfiInvoice(BigDecimal id)
     */
    public JfiInvoice getJfiInvoice(final Long id) {
        JfiInvoice jfiInvoice = (JfiInvoice) getHibernateTemplate().get(JfiInvoice.class, id);
        if (jfiInvoice == null) {
            log.warn("uh oh, jfiInvoice with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JfiInvoice.class, id);
        }

        return jfiInvoice;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiInvoiceDao#saveJfiInvoice(JfiInvoice jfiInvoice)
     */    
    public void saveJfiInvoice(final JfiInvoice jfiInvoice) {
        getHibernateTemplate().saveOrUpdate(jfiInvoice);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiInvoiceDao#removeJfiInvoice(BigDecimal id)
     */
    public void removeJfiInvoice(final Long id) {
        getHibernateTemplate().delete(getJfiInvoice(id));
    }
    //added for getJfiInvoicesByCrm
    public List getJfiInvoicesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiInvoice jfiInvoice where 1=1";
    	/** 
	---example---
	String userCode = crm.getString("userCode", "");
	if(StringUtils.isNotEmpty(userCode)){
		hql += "???????????";
	}
	 ***/
    	// 
    	String userCode = crm.getString("userCode", "");
    	if(StringUtils.isNotEmpty(userCode)){
    		hql += " and userCode = '" + userCode +"'";
    	}
    	
    	String wyear = crm.getString("wyear", "");
    	if(StringUtils.isNotEmpty(wyear)){
    		hql += " and wyear = " + wyear ;
    	}
    	
    	String wweek = crm.getString("wweek", "");
    	if(StringUtils.isNotEmpty(wweek)){
    		hql += " and wweek = " + wweek;
    	}
    	
    	String remark = crm.getString("remark", "");
    	if(StringUtils.isNotEmpty(remark)){
    		hql += " and remark like '%" + remark +"%'";
    	}
    	
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    
    public List getJfiInvoiceExportByCrm(CommonRecord crm){
    	
    	String sql = " SELECT " +
    		   " A.USER_CODE USERCODE," +
		       " A.CREATE_TIME CREATETIME1," +
		       " B.CREATE_TIME CREATETIME2," +
		       " C.CREATE_TIME CREATETIME3," +
		       " A.W_YEAR WYEAR," +
		       " A.W_WEEK WWEEK," +
		       " B.AA AA," +
		       " B.BB BB," +
		       " A.CC CC," +
		       " C.DD DD," +
		       " B.BB - C.DD EE," +
		       " A.REMARK REMARK1," +
		       " B.REMARK REMARK2," +
		       " C.REMARK REMARK3 " +
		 "FROM  ( SELECT t1.create_time, t1.remark, t1.user_code,t1.w_year,t1.w_week, SUM(t1.INVOICE_MONEY) cc FROM jfi_invoice t1 GROUP BY t1.user_code,t1.w_year,t1.w_week, t1.create_time, t1.remark) a " +
		 "LEFT JOIN ( SELECT t2.create_time,t2.remark,t2.user_code ,t2.w_year, t2.w_week, sum(t2.Send_MONEY) aa,SUM(t2.INVOICE_PAYABLE) bb ,SUM(t2.DEPOSIT_MONEY)  FROM JFI_DEPOSIT_MONEY t2 GROUP BY t2.user_code,t2.w_year,t2.w_week, t2.create_time,t2.remark) b " +
		 "ON a.user_code = b.user_code  AND a.w_week = b.w_week " +
		 "LEFT JOIN (  SELECT t3.create_time,t3.remark, t3.user_code ,t3.j_year,t3.j_month, SUM(t3.invoice_value) dd FROM FI_AVAILABLE_INVOICE t3 WHERE status = 1 GROUP BY t3.user_code,t3.j_year,t3.j_month,t3.create_time,t3.remark) c " +
		 "ON b.user_code = c.user_code AND c.j_month = b.w_week " +
		 "where 1=1 " ;
		 
    	String userCode = crm.getString("userCode","");
    	String wyear = crm.getString("wyear","");
		String wweekS = crm.getString("wweekS","");
		String wweekE = crm.getString("wweekE","");
		
		if(StringUtils.isNotEmpty(userCode)){
			sql += " and a.user_code = '" + userCode + "'";
		}
		
		if(StringUtils.isNotEmpty(wyear)){
			sql += " and a.w_year =" + wyear;
		}
		
		if(StringUtils.isNotEmpty(wweekS)){
			sql += " and a.w_week >=" + wweekS;
		}
		if(StringUtils.isNotEmpty(wweekE)){
			sql += " and a.w_week <=" + wweekE;
		}
    	
    	return this.getJdbcTemplate().queryForList(sql);
    }
    
    public List getJfiDepositExportByCrm(CommonRecord crm){
    	
    	String sql = "  SELECT B.USER_CODE userCode, " +
    	"  B.CREATE_TIME createTime1," +
    	"  C.CREATE_TIME createTime2," +
        "  e.CREATE_TIME createTime3," +
        "  b.W_YEAR wyear," +
        "  b.W_WEEK wweek," +
        "  B.AA aa," +
        "  B.BB bb," +
        "  B.FF ff," +
        "  c.GG gg," +
        "  e.HH hh," +
        "  B.FF - c.GG ii," +
        "  c.GG - e.HH jj,  " +
        "  B.REMARK remark1," +
        "  C.REMARK remark2," +
        "  e.REMARK remark3 " +
        "  FROM (SELECT T2.CREATE_TIME,  T2.REMARK,  T2.USER_CODE, T2.W_YEAR, T2.W_WEEK, SUM(T2.SEND_MONEY) AA, SUM(T2.INVOICE_PAYABLE) BB,SUM(T2.DEPOSIT_MONEY) FF FROM JFI_DEPOSIT_MONEY T2 GROUP BY T2.USER_CODE, T2.W_YEAR, T2.W_WEEK, T2.CREATE_TIME, T2.REMARK) B " +
 "  LEFT JOIN (  SELECT t3.create_time,t3.remark, t3.user_code ,t3.j_year,t3.j_month, SUM(t3.invoice_value) hhh,SUM(t3.bond) gg FROM FI_AVAILABLE_INVOICE t3 WHERE status = 1 GROUP BY t3.user_code,t3.j_year,t3.j_month,t3.create_time,t3.remark) c " +
 "  ON b.user_code = c.user_code  AND b.w_week = c.j_month " +
 "  LEFT JOIN (SELECT t4.user_code, t4.w_year,t4.w_week, t4.remark,t4.create_time, sum(t4.deposit_money) hh FROM jfi_deposit_send t4 GROUP BY t4.user_code, t4.w_year,t4.w_week, t4.remark,t4.create_time) e " +
 "  ON c.user_code = e.user_code AND e.w_week = c.j_month " +
 "  where 1=1 " ;
    	
    	String userCode = crm.getString("userCode","");
    	String wyear = crm.getString("wyear","");
		String wweekS = crm.getString("wweekS","");
		String wweekE = crm.getString("wweekE","");
		
		if(StringUtils.isNotEmpty(userCode)){
			sql += " and b.user_code = '" + userCode + "'";
		}
		if(StringUtils.isNotEmpty(wyear)){
			sql += " and b.w_year =" + wyear;
		}
		if(StringUtils.isNotEmpty(wweekS)){
			sql += " and b.w_week >=" + wweekS;
		}
		if(StringUtils.isNotEmpty(wweekE)){
			sql += " and b.w_week <=" + wweekE;
		}
		
    	return this.getJdbcTemplate().queryForList(sql);
    }
    
    public List getJfiDepositInvoiceExportByCrm(CommonRecord crm){
    	
    	String sql = " SELECT B.USER_CODE userCode, " +
    	" (SELECT user_name FROM jsys_user WHERE user_code='B.USER_CODE') AS userName, " +
    	" Fn_Get_Zcw_Message_NEW('B.USER_CODE') AS zcw, " +
    	" A.W_YEAR wyear, " +
    	" A.W_WEEK wweek, " +
    	" B.AA aa , " +
    	" B.BB bb , " +
    	" A.cc cc, " +
    	" c.dd dd , " +
    	" b.bb - c.dd ee, " +
    	" b.ff ff , " +
    	" C.gg gg , " +
    	" e.hh hh, " +
    	" b.bb - c.dd ii, " +
    	" c.GG - e.HH jj  " +
    	" FROM (SELECT T2.CREATE_TIME, T2.REMARK,  T2.USER_CODE, T2.W_YEAR,  T2.W_WEEK, SUM(T2.SEND_MONEY) AA,SUM(T2.INVOICE_PAYABLE) BB, SUM(T2.DEPOSIT_MONEY) FF FROM JFI_DEPOSIT_MONEY T2 GROUP BY T2.USER_CODE, T2.W_YEAR, T2.W_WEEK, T2.CREATE_TIME, T2.REMARK) B " +
    	" LEFT JOIN (SELECT T1.CREATE_TIME,  T1.REMARK, T1.USER_CODE, T1.W_YEAR, T1.W_WEEK, SUM(T1.INVOICE_MONEY) cc FROM JFI_INVOICE T1 GROUP BY T1.USER_CODE, T1.W_YEAR, T1.W_WEEK,T1.CREATE_TIME, T1.REMARK) A " +
    	" ON B.USER_CODE = A.USER_CODE AND a.w_week = b.w_week " +
    	" LEFT JOIN (SELECT T3.CREATE_TIME,T3.REMARK,T3.USER_CODE, T3.J_YEAR,T3.J_MONTH,SUM(T3.INVOICE_VALUE) dd,SUM(T3.BOND) gg FROM FI_AVAILABLE_INVOICE T3 WHERE STATUS = 1 GROUP BY T3.USER_CODE,T3.J_YEAR, T3.J_MONTH, T3.CREATE_TIME, T3.REMARK) C " +
    	" ON B.USER_CODE = C.USER_CODE AND b.w_week = c.j_month " +
    	" LEFT JOIN (SELECT t4.user_code, t4.w_year,t4.w_week, sum(t4.deposit_money) hh FROM jfi_deposit_send t4 GROUP BY t4.user_code, t4.w_year,t4.w_week) e " +
    	" ON c.user_code = e.user_code AND c.j_month = e.w_week " +
    	" where 1=1 "; 
//    	" WHERE b.user_code ='' AND b.w_year = '2015' " +
//    	" AND b.w_week >= '201501' AND b.w_week <='201510' ";
    	
    	String userCode = crm.getString("userCode","");
    	String wyear = crm.getString("wyear","");
		String wweekS = crm.getString("wweekS","");
		String wweekE = crm.getString("wweekE","");
		
		if(StringUtils.isNotEmpty(userCode)){
			sql += " and b.user_code = '" + userCode + "'";
		}
		if(StringUtils.isNotEmpty(wyear)){
			sql += " and b.w_year =" + wyear;
		}
		if(StringUtils.isNotEmpty(wweekS)){
			sql += " and b.w_week >=" + wweekS;
		}
		if(StringUtils.isNotEmpty(wweekE)){
			sql += " and b.w_week <=" + wweekE;
		}
    	
    	return this.getJdbcTemplate().queryForList(sql);
    }
}
