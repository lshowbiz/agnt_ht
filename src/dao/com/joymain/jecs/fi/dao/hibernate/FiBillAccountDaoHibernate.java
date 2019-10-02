
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.dao.FiBillAccountDao;
import com.joymain.jecs.fi.model.FiBillAccount;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
@SuppressWarnings({"rawtypes","unchecked"})
public class FiBillAccountDaoHibernate extends BaseDaoHibernate implements FiBillAccountDao {
	final Integer STATUS_ENABLE = 0 ; //0：启用，1停用
    /**
     * @see com.joymain.jecs.fi.dao.FiBillAccountDao#getFiBillAccounts(com.joymain.jecs.fi.model.FiBillAccount)
     */
    public List getFiBillAccounts(final FiBillAccount fiBillAccount) {
    	
    	String hql = "from FiBillAccount fiBillAccount where 1=1";

		String userCode = fiBillAccount.getUserCode();
		if(StringUtils.isNotEmpty(userCode)){
			hql += " and fiBillAccount.userCode = '"+userCode+"'";
		}
		
		String accountType = fiBillAccount.getAccountType();
		if(StringUtils.isNotEmpty(accountType)){
			hql += " and fiBillAccount.accountType = '"+accountType+"'";
		}
		
		String businessType = fiBillAccount.getBusinessType(); //订单类型
		if (StringUtils.isNotEmpty(businessType)) {
			hql += " and fiBillAccount.businessType = '"+businessType+"'";
		}
		
		String province = fiBillAccount.getProvince(); //地区
		if (StringUtils.isNotEmpty(province)) {
			hql += " and fiBillAccount.province = '"+province+"'";
		}
		
		String status = fiBillAccount.getStatus();
		if(StringUtils.isNotEmpty(status)){
			hql += " and fiBillAccount.status = '"+status+"'";
		}
		
		return this.findObjectsByHqlQuery(hql);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiBillAccountDao#getFiBillAccount(Long accountId)
     */
    public FiBillAccount getFiBillAccount(final Long accountId) {
        FiBillAccount fiBillAccount = (FiBillAccount) getHibernateTemplate().get(FiBillAccount.class, accountId);
        if (fiBillAccount == null) {
            log.warn("uh oh, fiBillAccount with accountId '" + accountId + "' not found...");
            throw new ObjectRetrievalFailureException(FiBillAccount.class, accountId);
        }

        return fiBillAccount;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiBillAccountDao#saveFiBillAccount(FiBillAccount fiBillAccount)
     */    
    public void saveFiBillAccount(final FiBillAccount fiBillAccount) {
        getHibernateTemplate().saveOrUpdate(fiBillAccount);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiBillAccountDao#removeFiBillAccount(Long accountId)
     */
    public void removeFiBillAccount(final Long accountId) {
        getHibernateTemplate().delete(getFiBillAccount(accountId));
    }
    //added for getFiBillAccountsByCrm
    public List getFiBillAccountsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiBillAccount fiBillAccount where 1=1";

		String billAccountCode = crm.getString("billAccountCode", "");
		if(StringUtils.isNotEmpty(billAccountCode)){
			hql += " and fiBillAccount.billAccountCode='"+billAccountCode+"'";
		}
		
		String accountName = crm.getString("accountName", "");
		if(StringUtils.isNotEmpty(accountName)){
			hql += " and fiBillAccount.accountName like '%"+accountName+"%'";
		}
		
		String userCode = crm.getString("userCode", "");
		if(StringUtils.isNotEmpty(userCode)){
			hql += " and fiBillAccount.userCode = '"+userCode+"'";
		}
		
		String accountType = crm.getString("accountType", "");
		if(StringUtils.isNotEmpty(accountType)){
			hql += " and fiBillAccount.accountType = '"+accountType+"'";
		}
		
		String providerType = crm.getString("providerType", "");
		if(StringUtils.isNotEmpty(providerType)){
			hql += " and fiBillAccount.providerType = '"+providerType+"'";
		}
		String businessType = crm.getString("businessType","2"); //订单类型
		if (StringUtils.isNotEmpty(businessType)) {
			hql += " and fiBillAccount.businessType = '"+businessType+"'";
		}
		String province = crm.getString("province",""); //所属地区
		if (StringUtils.isNotEmpty(province)) {
			hql += " and fiBillAccount.province = '"+province+"'";
		}
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by userCode,accountType,providerType,createTime desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

    
    public List getFiBillAccountsByCrmSql(CommonRecord crm, Pager pager){
    	String businessType = crm.getString("businessType","1"); //订单类型
    	String orderStr = "USER_CODE";
		if ("1".equals(businessType)) {
			orderStr = "PROVINCE";
		}
		String sql = "SELECT * FROM (SELECT K.*, ROW_NUMBER() OVER(PARTITION BY "+orderStr+" ORDER BY STATUS ) CN " + 
		" FROM FI_BILL_ACCOUNT K ORDER BY STATUS ) fiBillAccount WHERE CN = 1 and BUSINESS_TYPE =" + businessType;
		
		String billAccountCode = crm.getString("billAccountCode", "");
		if (StringUtils.isNotEmpty(billAccountCode)) {
			sql += " and BILL_ACCOUNT_CODE='" + billAccountCode + "'";
		}
		String province = crm.getString("province", ""); // 所属地区
		if (StringUtils.isNotEmpty(province)) {
			sql += " and PROVINCE = '" + province + "'";
		}
		String userCode = crm.getString("userCode", "");
		if (StringUtils.isNotEmpty(userCode)) {
			sql += " and  USER_CODE = '" + userCode + "'";
		}
		String providerType = crm.getString("providerType", "");
		if (StringUtils.isNotEmpty(providerType)) {
			sql += " and  PROVIDER_TYPE = '" + providerType + "'";
		}
		
    	return this.findObjectsBySQL(sql, pager);
    }
    
    
    /**
     * 验证同省份下是否有其他默认商户号
     * @param billAccountCode
     * @param province
     * @return
     */
	@Override
	public List getFiBillAccountsByIsDefault(String billAccountCode) {
		
		String hql = "from FiBillAccount where isDefault=1 and billAccountCode!='"+billAccountCode+"'";

		return this.findObjectsByHqlQuery(hql);
	}
	
	/**
	 * 验证经销商编号是否重复
	 * @param billAccountCode
	 * @param userCode
	 * @return
	 */
	@Override
	public List getFiBillAccountsByUserCode(String billAccountCode,String userCode) {

		String hql = "from FiBillAccount where userCode='"+userCode+"'";
		
		if (!StringUtils.isEmpty(billAccountCode)) {
			hql += " and billAccountCode='"+billAccountCode+"'";
		}
		
		return this.findObjectsByHqlQuery(hql);
	}
	
	/**
	 * 验证商户号是否重复
	 * @param billAccountCode
	 * @return
	 */
	@Override
	public List getFiBillAccountsByBillAccountCode(String billAccountCode) {
		
		String hql = "from FiBillAccount where billAccountCode='"+billAccountCode+"' order by accountId desc";
		
		return this.findObjectsByHqlQuery(hql);
	}
	
	/**
	 * 根据商户号获取商户对象
	 * @param billAccountCode
	 * @return
	 */
    @Override
	public FiBillAccount getFiBillAccountByBillAccountCode(String billAccountCode) {

		String hql = "from FiBillAccount where billAccountCode='"+billAccountCode+"'";
		
		return (FiBillAccount) this.getObjectByHqlQuery(hql);
	}
	
	/**
	 * 根据省份获取快钱商户号
	 * @param province
	 * @return
	 */
	@Override
	public FiBillAccount getFiBillAccountsByProvince(String province) {
		
		String queryStr = "select * from FI_BILL_ACCOUNT a left join FI_BILL_ACCOUNT_WARNING b on a.bill_account_code=b.bill_account_code where a.province='"+province+"' and a.status='1' and a.is_default='0' and (a.income_limit-b.now_total_amount)>0 order by (a.income_limit-b.now_total_amount) desc";
		
		List<Map<String, Object>> list=this.jdbcTemplate.queryForList(queryStr);
		
		if(list.isEmpty()){
			
			return null;
		}else{
			
			//获取到值后，跳出循环，实际上只获取第一行记录
			String accountCode ="";
			for(Map<String,Object> m : list){
				
				accountCode =(String) m.get("BILL_ACCOUNT_CODE");
				
				if(!("").equals(accountCode))
					break;
			}
			
			FiBillAccount mfiBillAccount = this.getFiBillAccountByBillAccountCode(accountCode);
			
			return mfiBillAccount;
		}
		
//		String sql = "with tt as(select a.BILL_ACCOUNT_CODE from FI_BILL_ACCOUNT a left join FI_BILL_ACCOUNT_WARNING b on a.bill_account_code=b.bill_account_code where a.province='"+province+"' and a.status='1' and (a.income_limit-b.now_total_amount)>0 order by (a.income_limit-b.now_total_amount) desc) select BILL_ACCOUNT_CODE from tt where rownum=1";
//		
//		Map resultMap = jdbcTemplate.queryForMap(sql);
//		
//		if(resultMap!=null && resultMap.get("BILL_ACCOUNT_CODE")!=null){
//			
//			String accountCode = (String) resultMap.get("BILL_ACCOUNT_CODE");
//
//			FiBillAccount mfiBillAccount = this.getFiBillAccountByBillAccountCode(accountCode);
//
//			return mfiBillAccount;
//		}else{
//			return null;
//		}
	}
	
	/**
	 * 查询省份下面默认商户号
	 * @param province
	 * @return
	 */
	@Override
	public FiBillAccount getDefaultFiBillAccountByProvince(String province){
		
		String hql = "from FiBillAccount where isDefault='1' and province='"+province+"' and status='"+STATUS_ENABLE+"'";
		try{
			return (FiBillAccount) getObjectByHqlQuery(hql);
		}catch(ClassCastException ex){
			
			return null;
		}
	}
	
	/**
	 * 随即查询一个默认商户号
	 * @param province
	 * @return
	 */
	@Override
	public FiBillAccount getOneDefaultFiBillAccount(){
		
		String hql = "from FiBillAccount where isDefault='1' and status='"+STATUS_ENABLE+"' order by incomeLimit desc";
		List<FiBillAccount> list = this.findObjectsByHqlQuery(hql);
		
		if(list!=null && list.size()>0){
			
			return (FiBillAccount) list.get(0);
		}else{
			return null;
		}
	}

	/**
	 * 根据经销商获取商户号
	 */
	@Override
	public FiBillAccount getFiBillAccountByUserCode(String userCode, String accountType) {
		
		String hql = "from FiBillAccount where userCode='"+userCode+"' and accountType='"+accountType+"' and status='"+STATUS_ENABLE+"'";
		try{
			return (FiBillAccount) getObjectByHqlQuery(hql);
		}catch(ClassCastException ex){
			
			return null;
		}
	}
}
