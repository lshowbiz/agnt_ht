
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiBillAccountWarning;
import com.joymain.jecs.fi.dao.FiBillAccountWarningDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class FiBillAccountWarningDaoHibernate extends BaseDaoHibernate implements FiBillAccountWarningDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiBillAccountWarningDao#getFiBillAccountWarnings(com.joymain.jecs.fi.model.FiBillAccountWarning)
     */
    public List getFiBillAccountWarnings(final FiBillAccountWarning fiBillAccountWarning) {
        return getHibernateTemplate().find("from FiBillAccountWarning");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiBillAccountWarning == null) {
            return getHibernateTemplate().find("from FiBillAccountWarning");
        } else {
            // filter on properties set in the fiBillAccountWarning
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiBillAccountWarning).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiBillAccountWarning.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiBillAccountWarningDao#getFiBillAccountWarning(String billAccountCode)
     */
    public FiBillAccountWarning getFiBillAccountWarning(final String billAccountCode) {
        FiBillAccountWarning fiBillAccountWarning = (FiBillAccountWarning) getHibernateTemplate().get(FiBillAccountWarning.class, billAccountCode);
        if (fiBillAccountWarning == null) {
            log.warn("uh oh, fiBillAccountWarning with billAccountCode '" + billAccountCode + "' not found...");
            throw new ObjectRetrievalFailureException(FiBillAccountWarning.class, billAccountCode);
        }

        return fiBillAccountWarning;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiBillAccountWarningDao#saveFiBillAccountWarning(FiBillAccountWarning fiBillAccountWarning)
     */    
    public void saveFiBillAccountWarning(final FiBillAccountWarning fiBillAccountWarning) {
        getHibernateTemplate().saveOrUpdate(fiBillAccountWarning);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiBillAccountWarningDao#removeFiBillAccountWarning(String billAccountCode)
     */
    public void removeFiBillAccountWarning(final String billAccountCode) {
        getHibernateTemplate().delete(getFiBillAccountWarning(billAccountCode));
    }

    /**
     * 终端经销商收款预警
     */
    public List getFiBillAccountWarningsByCrm(CommonRecord crm, Pager pager){

    	String sqlQuery = "select a.account_id,a.field,a.province,a.sort_index,a.bill_account_code,a.user_code,a.account_name,a.income_limit,b.now_total_amount from FI_BILL_ACCOUNT a left join FI_BILL_ACCOUNT_WARNING b on a.bill_account_code=b.bill_account_code order by a.field,a.province,a.sort_index asc";
    	
    	return this.findObjectsBySQL(sqlQuery, pager);
    }
    
    /**
	 * 根据商户号查询预警记录
	 * @param billAccountCode
	 * @return
	 */
    public FiBillAccountWarning getFiBillAccountWarningByBillAccountCode(final String billAccountCode) {
    	
    	String hql = "from FiBillAccountWarning fiBillAccountWarning where billAccountCode='"+billAccountCode+"'";
    	
    	return (FiBillAccountWarning) this.getObjectByHqlQuery(hql);
    }
    
    /**
	 * 重新统计进账额度
	 */
	@Override
	public void refComFiBillAccountWarnings() {
		// TODO Auto-generated method stub
		
		List<FiBillAccountWarning> list = this.getFiBillAccountWarnings(null);
		
		for(int i=0;i<list.size();i++){
		
			FiBillAccountWarning FiBillAccountWarning = list.get(i);
				
			//String sql = "update FI_BILL_ACCOUNT_WARNING set NOW_TOTAL_AMOUNT=(select sum(t.pay_amount)/100 from JFI_99BILL_LOG t where t.merchant_acct_id='"+FiBillAccountWarning.getBillAccountCode()+"' and t.inc='1') where BILL_ACCOUNT_CODE='"+FiBillAccountWarning.getBillAccountCode()+"'";
			
			String sql ="update FI_BILL_ACCOUNT_WARNING a set a.NOW_TOTAL_AMOUNT=(select (case when sum(t.pay_amount) is null then 0 else sum(t.pay_amount)/100 end) from JFI_99BILL_LOG t where t.merchant_acct_id='"+FiBillAccountWarning.getBillAccountCode()+"' and t.inc='1') where a.BILL_ACCOUNT_CODE='"+FiBillAccountWarning.getBillAccountCode()+"'";
			
			this.jdbcTemplate.execute(sql);
		}
	}
}
