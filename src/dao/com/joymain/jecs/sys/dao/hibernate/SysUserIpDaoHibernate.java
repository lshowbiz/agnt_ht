
package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.dao.SysUserIpDao;
import com.joymain.jecs.sys.model.SysUserIp;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysUserIpDaoHibernate extends BaseDaoHibernate implements SysUserIpDao {
    /**
     * @see com.joymain.jecs.sys.dao.SysUserIpDao#getSysUserIps(com.joymain.jecs.sys.model.SysUserIp)
     */
    public List getSysUserIps(final SysUserIp sysUserIp) {
        return getHibernateTemplate().find("from SysUserIp");
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysUserIpDao#getSysUserIp(Long ipId)
     */
    public SysUserIp getSysUserIp(final Long ipId) {
        SysUserIp sysUserIp = (SysUserIp) getHibernateTemplate().get(SysUserIp.class, ipId);
        if (sysUserIp == null) {
            log.warn("uh oh, sysUserIp with ipId '" + ipId + "' not found...");
            throw new ObjectRetrievalFailureException(SysUserIp.class, ipId);
        }

        return sysUserIp;
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysUserIpDao#saveSysUserIp(SysUserIp sysUserIp)
     */    
    public void saveSysUserIp(final SysUserIp sysUserIp) {
        getHibernateTemplate().saveOrUpdate(sysUserIp);
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysUserIpDao#removeSysUserIp(Long ipId)
     */
    public void removeSysUserIp(final Long ipId) {
        getHibernateTemplate().delete(getSysUserIp(ipId));
    }
    //added for getSysUserIpsByCrm
    public List getSysUserIpsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from SysUserIp sysUserIp where 1=1";
    	// ��������
		if (!pager.getLimit().getSort().isSorted()) {
			//ȱʡ����
			hql += " order by ipId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    /**
     * 获取某用户是否有某IP的记录
     * @param userCode
     * @param ipAddress
     * @return
     */
    public SysUserIp getSysUserIp(final String userCode, final String ipAddress){
    	return (SysUserIp)this.getObjectByHqlQuery("from SysUserIp where userCode='"+userCode+"' and ipAddress='"+ipAddress+"'");
    }
    
    /**
     * 删除某用户不在IP范围内的IP记录
     * @param userCode
     * @param ipAddress
     */
    public void removeSysUserIpsNotIn(final String userCode, final String[] ipAddress){
    	String hqlQuery="delete from SysUserIp where userCode='"+userCode+"'";
    	if(ipAddress!=null && ipAddress.length>0){
    		hqlQuery+=" and ipAddress not in(";
    		for(int i=0;i<ipAddress.length;i++){
    			if(i>0){
    				hqlQuery+=",";
    			}
    			hqlQuery+="'"+ipAddress[i]+"'";
    		}
    		hqlQuery+=")";
    	}
    	this.executeUpdate(hqlQuery);
    }
    
    /**
     * 批量保存多条记录
     * @param sysUserIps
     */
    public void saveSysUserIps(List sysUserIps){
    	this.getHibernateTemplate().saveOrUpdateAll(sysUserIps);
    }
    
    /**
     * 获取某用户对应的IP列表
     * @param userCode
     * @return
     */
    public List getSysUserIpsByUser(final String userCode){
    	return this.getHibernateTemplate().find("from SysUserIp where userCode=?",userCode);
    }
}