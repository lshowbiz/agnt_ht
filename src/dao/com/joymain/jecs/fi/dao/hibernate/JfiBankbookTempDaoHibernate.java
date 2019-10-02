
package com.joymain.jecs.fi.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiBankbookTemp;
import com.joymain.jecs.fi.model.JfiPosImport;
import com.joymain.jecs.fi.dao.JfiBankbookTempDao;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiBankbookTempDaoHibernate extends BaseDaoHibernate implements JfiBankbookTempDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiBankbookTempDao#getJfiBankbookTemps(com.joymain.jecs.fi.model.JfiBankbookTemp)
     */
    public List getJfiBankbookTemps(final JfiBankbookTemp jfiBankbookTemp) {
        return getHibernateTemplate().find("from JfiBankbookTemp");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiBankbookTemp == null) {
            return getHibernateTemplate().find("from JfiBankbookTemp");
        } else {
            // filter on properties set in the jfiBankbookTemp
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiBankbookTemp).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiBankbookTemp.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiBankbookTempDao#getJfiBankbookTemp(Long tempId)
     */
    public JfiBankbookTemp getJfiBankbookTemp(final Long tempId) {
        JfiBankbookTemp jfiBankbookTemp = (JfiBankbookTemp) getHibernateTemplate().get(JfiBankbookTemp.class, tempId);
        if (jfiBankbookTemp == null) {
            log.warn("uh oh, jfiBankbookTemp with tempId '" + tempId + "' not found...");
            throw new ObjectRetrievalFailureException(JfiBankbookTemp.class, tempId);
        }

        return jfiBankbookTemp;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiBankbookTempDao#saveJfiBankbookTemp(JfiBankbookTemp jfiBankbookTemp)
     */    
    public void saveJfiBankbookTemp(final JfiBankbookTemp jfiBankbookTemp) {
        getHibernateTemplate().saveOrUpdate(jfiBankbookTemp);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiBankbookTempDao#removeJfiBankbookTemp(Long tempId)
     */
    public void removeJfiBankbookTemp(final Long tempId) {
        getHibernateTemplate().delete(getJfiBankbookTemp(tempId));
    }
    //added for getJfiBankbookTempsByCrm
    public List getJfiBankbookTempsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiBankbookTemp jfiBankbookTemp where 1=1";
    	hql+=this.buildListSqlQuery(crm, null);
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by tempId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	
	/**
	 * 获取收支统计金额 incTotal:收入  expTotal:支出
	 * @param crm
	 * @return
	 */
	public Map getIncExpStatMap(CommonRecord crm){
		String incHql = "select nvl(sum(money),0) as inMoney from JfiBankbookTemp where 1=1 " + this.buildListSqlQuery(crm, "A");
		BigDecimal incTotal = (BigDecimal) this.getObjectByHqlQuery(incHql);

		String expHql = "select nvl(sum(money),0) as inMoney from JfiBankbookTemp where 1=1 " + this.buildListSqlQuery(crm, "D");
		BigDecimal expTotal = (BigDecimal) this.getObjectByHqlQuery(expHql);

		Map<String, BigDecimal> incExpStatMap = new HashMap<String, BigDecimal>();
		incExpStatMap.put("incTotal", incTotal);
		incExpStatMap.put("expTotal", expTotal);
		
		return incExpStatMap;
	}

	/**
	 * 获取某用户的存折条数
	 * @param companyCode
	 * @param agentNo
	 * @return
	 */
	public long getCountByDate(final String companyCode, final String userCode) {
		return this.getTotalByHql("select count(*) as totalCount from JfiBankbookTemp where companyCode='" + companyCode + "' and sysUser.userCode='" + userCode + "'");
	}
	
	/**
	 * 根据外部参数生成查询语句
	 * @param crm
	 * @return
	 */
	private String buildListSqlQuery(CommonRecord crm, String dealType){
		String hql="";
		
		String companyCode = crm.getString("companyCode", "");
		if (!"AA".equals(companyCode) && !StringUtils.isEmpty(companyCode)) {
			hql += " and companyCode='" + companyCode + "'";
		}
		
		if (!StringUtils.isEmpty(dealType)) {
			hql += " and dealType='" + dealType + "'";
		}

		String userCode = crm.getString("sysUser.userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and sysUser.userCode='" + userCode + "'";
		}

		String petName = crm.getString("sysUser.jmiMember.petName", "");
		if (!StringUtils.isEmpty(petName)) {
			hql += " and sysUser.jmiMember.petName='" + petName + "'";
		}

		String firstNameKana = crm.getString("sysUser.jmiMember.firstNameKana", "");
		if (!StringUtils.isEmpty(firstNameKana)) {
			hql += " and sysUser.jmiMember.firstNameKana='" + firstNameKana + "'";
		}

		String lastNameKana = crm.getString("sysUser.jmiMember.lastNameKana", "");
		if (!StringUtils.isEmpty(lastNameKana)) {
			hql += " and sysUser.jmiMember.lastNameKana='" + lastNameKana + "'";
		}

		String moneyType = crm.getString("moneyType", "");
		if (!StringUtils.isEmpty(moneyType)) {
			hql += " and moneyType='" + moneyType + "'";
		}

		String status = crm.getString("status", "");
		if (!StringUtils.isEmpty(status)) {
			hql += " and status='" + status + "'";
		}

		String createrCode = crm.getString("createrCode", "");
		if (!StringUtils.isEmpty(createrCode)) {
			hql += " and createrCode='" + createrCode + "'";
		}
		
		String createrName = crm.getString("createrName", "");
		if (!StringUtils.isEmpty(createrName)) {
			hql += " and createrName='" + createrName + "'";
		}
		
		String checkerName = crm.getString("checkerName", "");
		if (!StringUtils.isEmpty(checkerName)) {
			hql += " and checkerName='" + checkerName + "'";
		}

		String startCreateTime = crm.getString("startCreateTime", "");
		if (!StringUtils.isEmpty(startCreateTime)) {
			hql += " and createTime>=to_date('" + startCreateTime + "','yyyy-mm-dd hh24:mi:ss')";
		}

		String endCreateTime = crm.getString("endCreateTime", "");
		if (!StringUtils.isEmpty(endCreateTime)) {
			hql += " and createTime<=to_date('" + endCreateTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		String billpospayNum = crm.getString("billpospayNum", "");
		if (!StringUtils.isEmpty(billpospayNum)) {
			hql += " and billpospayNum='" + billpospayNum + "'";
		}
	
		
		return hql;
	}

	/**
	 * 批量保存多条记录
	 * @param fiBankbookTemps
	 */
	public void saveJfiBankbookTemps(List jfiBankbookTemps){
		this.getHibernateTemplate().saveOrUpdateAll(jfiBankbookTemps);
	}
	
	/**
	 * 支付单号校验
	 * @param posNum
	 * @return
	 */
	public List checkPosNum(String posNum){
		
		//查询存折条目新增POS支付单号数据
		String hql1 = "from JfiBankbookTemp j where j.moneyType=28 and billpospayNum='"+posNum+"'";
		List jfiBankbookTempList= this.findObjectsByHqlQuery(hql1);
		
		//查询POS支付导入数据
		String hql2 = "from JfiPosImport jfiPosImport where posNo='"+posNum+"'";
		List jfiPosImportList= this.findObjectsByHqlQuery(hql2);
		
		//把POS支付导入数据整合进JfiBankbookTemp对象List
		for(int i=0;i<jfiPosImportList.size();i++){
			
			JfiPosImport jfiPosImport = (JfiPosImport) jfiPosImportList.get(i);
			
			if (!StringUtils.isEmpty(jfiPosImport.getPosNo())) {
				
				JfiBankbookTemp jfiBankbookTemp=new JfiBankbookTemp();
				jfiBankbookTemp.setBillpospayNum(jfiPosImport.getPosNo());
				
				SysUser sysUser=new SysUser();
				sysUser.setUserCode(jfiPosImport.getUserCode());
				jfiBankbookTemp.setSysUser(sysUser);
				jfiBankbookTemp.setMoney(jfiPosImport.getAmount());
				jfiBankbookTemp.setCheckeTime(jfiPosImport.getCheckTime());
				jfiBankbookTemp.setCheckerName(jfiPosImport.getCheckUser());
				jfiBankbookTemp.setStatus(Integer.parseInt(jfiPosImport.getStatus()));
				
				jfiBankbookTempList.add(jfiBankbookTemp);
			}
		}
		
		return jfiBankbookTempList;
	}

	/**
	 * 查询数据库里面所有的重复POS单号
	 */
	public List checkAllPosNumFromJfiBankBookTemp() {

		String hql1="select * from v_Jfi_Pos_Import order by pos_no";
		List list= this.findObjectsBySQL(hql1);

		return list;
	}
	
	public List checkAllPosNumFromJfiPosImport() {
		
		String sql="select pos_No,USER_CODE,STATUS,CHECK_USER,CHECK_TIME from JFI_POS_IMPORT where pos_No in(select pos_No from JFI_POS_IMPORT group by pos_No having count(1) >= 2) or pos_No in(select BILLPOSPAY_NUM from JFI_BANKBOOK_TEMP j where j.money_Type=28 group by BILLPOSPAY_NUM having count(1) >= 2)";
		List jfiPosImportList= this.findObjectsBySQL(sql);
		return jfiPosImportList;
	}
	
}
