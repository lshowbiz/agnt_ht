
package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.model.JsysResRole;
import com.joymain.jecs.sys.model.JsysResource;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.dao.JsysResourceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;


public class JsysResourceDaoHibernate extends BaseDaoHibernate implements JsysResourceDao {

    /**
     * @see com.joymain.jecs.sys.dao.JsysResourceDao#getJsysResources(com.joymain.jecs.sys.model.JsysResource)
     */
    public List getJsysResources(final JsysResource jsysResource) {
        return getHibernateTemplate().find("from JsysResource");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jsysResource == null) {
            return getHibernateTemplate().find("from JsysResource");
        } else {
            // filter on properties set in the jsysResource
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jsysResource).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JsysResource.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.sys.dao.JsysResourceDao#getJsysResource(Long resId)
     */
    public JsysResource getJsysResource(final Long resId) {
    	
        JsysResource jsysResource = (JsysResource) getHibernateTemplate().get(JsysResource.class, resId);
//        if (jsysResource == null) {
//            log.warn("uh oh, jsysResource with resId '" + resId + "' not found...");
//            throw new ObjectRetrievalFailureException(JsysResource.class, resId);
//        }

        return jsysResource;
    }

    /**
     * @see com.joymain.jecs.sys.dao.JsysResourceDao#saveJsysResource(JsysResource jsysResource)
     */    
    public void saveJsysResource(final JsysResource jsysResource) {
        getHibernateTemplate().saveOrUpdate(jsysResource);
    }
    
    public List getDirectChildJsysResources(final Long resId, final String orderField) {
		return this.getHibernateTemplate().find("from JsysResource where parentId=? order by " + orderField, resId);
	}
    
	/**
	 * 重新更新资源树结构,递归
	 * @param parentLvel
	 * @param parentTreeIndex
	 * @param parentId
	 */
	private void saveJsysResourcesRebuild(Long parentLvel, String parentTreeIndex, Long parentId) {
		List JsysResources = this.getHibernateTemplate().find("from JsysResource where parentId=? order by orderNo, resId", parentId);
		if (JsysResources != null && JsysResources.size() > 0) {
			for (int i = 0; i < JsysResources.size(); i++) {
				JsysResource JsysResource = (JsysResource) JsysResources.get(i);
				JsysResource.setTreeLevel(parentLvel + 1);
				JsysResource.setTreeIndex(parentTreeIndex + StringUtils.leftPad(Long.toString(i + 1), 2, '0'));
				this.saveJsysResource(JsysResource);

				this.saveJsysResourcesRebuild(JsysResource.getTreeLevel(), JsysResource.getTreeIndex(), JsysResource.getResId());
			}
		}
	}

	/**
	 * 重新生成资源树结构
	 */
	public void saveJsysResourcesRebuild() {
		this.saveJsysResourcesRebuild(new Long(0), "", new Long(0));
	}

    /**
     * @see com.joymain.jecs.sys.dao.JsysResourceDao#removeJsysResource(Long resId)
     */
    public void removeJsysResource(final Long resId) {
//      getHibernateTemplate().delete(getJsysResource(resId));
      this.getSession().createSQLQuery("delete from JSYS_RES_ROLE where res_id="+resId).executeUpdate();
      this.getSession().createSQLQuery("delete from JSYS_RESOURCE where res_id="+resId).executeUpdate();
    }
    //added for getJsysResourcesByCrm
    public List getJsysResourcesByCrm(CommonRecord crm, Pager pager){
    	    	
    	String sqlQuery = "select * from (select a.*, b.res_name as parent_res_name from JSYS_RESOURCE a "
	        + " left join JSYS_RESOURCE b on a.parent_id=b.res_id where 1=1";
    	
    	String parentId = crm.getString("parentId", "");
		if (!StringUtils.isEmpty(parentId)) {
			sqlQuery += " and a.parent_Id='" + parentId + "'";
		}
		sqlQuery += ")";
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
			sqlQuery += " order by order_No,res_id desc";
		} else {
			sqlQuery += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsBySQL(sqlQuery, pager);
    }

    //通过角色查询资源
	public List getJsysResourcesJoinRole(SysRole sysRole) {
		
		if (sysRole == null) {
			return null;
		}
		String sqlQuery = "select a.*";
		if (sysRole != null && sysRole.getRoleId() != null) {
			sqlQuery += ", b.RES_ID as rp_id ";
		}
		//Modify By WuCF 20161121 修改成只查询启用的菜单功能
		sqlQuery += " from (select * from JSYS_RESOURCE where active='1') a ";
		if (sysRole != null && sysRole.getRoleId() != null) {
			sqlQuery += " left join JSYS_RES_ROLE b on a.res_id=b.res_id and b.role_id='" + sysRole.getRoleId() + "' ";
		}
		sqlQuery += " order by a.tree_index, a.res_id";
		
		return this.findObjectsBySQL(sqlQuery);
	}

	public void saveSysRoleRes(List<JsysResRole> jsysResRoles) {
		// TODO Auto-generated method stub
		//getHibernateTemplate().saveOrUpdate(JsysResRole);
		
		this.getHibernateTemplate().saveOrUpdateAll(jsysResRoles);
		
	}
	
	public void removeSysRoleResByRoleId(final Long roleId){
		this.excuteSql("delete from JSYS_RES_ROLE where ROLE_ID="+roleId);
    	//this.executeUpdate("delete from JsysResRole where roleId="+roleId);
    }
}
