
package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.Constants;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.dao.SysManagerDao;
import com.joymain.jecs.sys.model.SysManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysManagerDaoHibernate extends BaseDaoHibernate implements SysManagerDao {
    /**
     * @see com.joymain.jecs.sys.dao.SysManagerDao#getSysManagers(com.joymain.jecs.sys.model.SysManager)
     */
    public List getSysManagers(final SysManager sysManager) {
        return getHibernateTemplate().find("from SysManager");
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysManagerDao#getSysManager(String userCode)
     */
    public SysManager getSysManager(final String userCode) {
        SysManager sysManager = (SysManager) getHibernateTemplate().get(SysManager.class, userCode);
        if (sysManager == null) {
            log.warn("uh oh, sysManager with userCode '" + userCode + "' not found...");
            throw new ObjectRetrievalFailureException(SysManager.class, userCode);
        }

        return sysManager;
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysManagerDao#saveSysManager(SysManager sysManager)
     */    
    public void saveSysManager(final SysManager sysManager) {
        getHibernateTemplate().saveOrUpdate(sysManager);
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysManagerDao#removeSysManager(String userCode)
     */
    public void removeSysManager(final String userCode) {
        getHibernateTemplate().delete(getSysManager(userCode));
    }
    //added for getSysManagersByCrm
    public List getSysManagersByCrm(CommonRecord crm, Pager pager){
    	String hql = "from SysManager where 1=1";
    	
    	String masterUserCode=crm.getString("masterUserCode", "");
    	if(!Constants.ROOT_ACCOUNT.equals(masterUserCode) && !StringUtils.isEmpty(masterUserCode)){
    		hql+="  and userCode in (select slaveUserCode from SysManagerUser where masterUserCode='" + masterUserCode + "')";
    	}
		
		String departmentId = crm.getString("departmentId", "");
		if (!StringUtils.isEmpty(departmentId)) {
			hql += " and departmentId='" + departmentId + "'";
		}
		
		String companyCode = crm.getString("companyCode", "");
		if (!"AA".equals(companyCode) && !StringUtils.isEmpty(companyCode)) {
			hql += " and companyCode='" + companyCode + "'";
		}
		
		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and userCode like '%" + userCode + "%'";
		}
		
		String userName = crm.getString("userName", "");
		if (!StringUtils.isEmpty(userName)) {
			hql += " and sysUser.userName like '%" + userName + "%'";
		}
		
		String state = crm.getString("state", "");
		if (!StringUtils.isEmpty(state)) {
			hql += " and sysUser.state='" + state + "'";
		}
		
    	// ��������
		if (!pager.getLimit().getSort().isSorted()) {
			//ȱʡ����
			hql += " order by userCode desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    /**
     * 获取部门下所有人员
     * @param departmentId
     * @return
     */
    public List getSysManagersByDepartment(final Long departmentId){
    	return this.getHibernateTemplate().find("from SysManager where departmentId=?",departmentId);
    }
}