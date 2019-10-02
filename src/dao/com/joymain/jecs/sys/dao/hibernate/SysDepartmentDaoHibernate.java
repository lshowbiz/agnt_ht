
package com.joymain.jecs.sys.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.Constants;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.dao.SysDepartmentDao;
import com.joymain.jecs.sys.model.SysDepartment;
import com.joymain.jecs.sys.model.SysManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysDepartmentDaoHibernate extends BaseDaoHibernate implements SysDepartmentDao {
    /**
     * @see com.joymain.jecs.sys.dao.SysDepartmentDao#getSysDepartments(com.joymain.jecs.sys.model.SysDepartment)
     */
    public List getSysDepartments(final SysDepartment sysDepartment) {
        return getHibernateTemplate().find("from SysDepartment");
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysDepartmentDao#getSysDepartment(Long departmentId)
     */
    public SysDepartment getSysDepartment(final Long departmentId) {
        SysDepartment sysDepartment = (SysDepartment) getHibernateTemplate().get(SysDepartment.class, departmentId);

        return sysDepartment;
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysDepartmentDao#saveSysDepartment(SysDepartment sysDepartment)
     */    
    public void saveSysDepartment(final SysDepartment sysDepartment) {
        getHibernateTemplate().saveOrUpdate(sysDepartment);
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysDepartmentDao#removeSysDepartment(Long departmentId)
     */
    public void removeSysDepartment(final Long departmentId) {
        getHibernateTemplate().delete(getSysDepartment(departmentId));
    }
    
    //added for getSysDepartmentsByCrm
    public List getSysDepartmentsByCrm(CommonRecord crm, Pager pager){
    	String sqlQuery = "select * from (select a.*, b.DEPARTMENT_NAME as parent_DEPARTMENT_NAME " +
    			" from jsys_department a  " +
    			" left join jsys_department b on a.parent_id=b.DEPARTMENT_ID " +
    			" where 1=1 ";
    	
    	String companyCode = crm.getString("companyCode", "");
		if (!StringUtils.isEmpty(companyCode)) {
			sqlQuery += " and a.company_code='" + companyCode + "'";
		}
		
		String parentId = crm.getString("parentId", "0");
		if (!StringUtils.isEmpty(parentId)) {
			sqlQuery += " and a.parent_id='" + parentId + "'";
		}
		sqlQuery+=")";
    	// ��������
		if (!pager.getLimit().getSort().isSorted()) {
			//ȱʡ����
			sqlQuery += " order by order_no asc";
		} else {
			sqlQuery += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsBySQL(sqlQuery, pager);
    }
    
    /**
     * 获取公司下所有的部门
     * @param companyCode
     * @return
     */
    public List getSysDepartmentsByCompany(final String companyCode){
    	return this.getHibernateTemplate().find("from SysDepartment where companyCode=? order by treeIndex",companyCode);
    }
    
    /**
     * 获取在某公司有权限的部门
     * @param companyCode
     * @param sysUser
     * @return
     */
    public List getSysDepartmentsByCompany(final String companyCode, final SysUser sysUser){
    	String hqlQuery="from SysDepartment where companyCode='"+companyCode+"'";
    	
    	if(!Constants.ROOT_ACCOUNT.equals(sysUser.getUserCode())){
    		String sqlQuery="select a.tree_index from jsys_department a " +
    		" where a.department_id in (" +
    		" select b.department_id from jsys_manager b, jsys_manager_user c " +
    		" where b.user_code=c.slave_user_code and c.master_user_code='"+sysUser.getUserCode()+"'" +
    		") and a.company_code='"+companyCode+"'";
    		
    		List lessSysDepartMents=this.findObjectsBySQL(sqlQuery);
    		int k=0;
    		if(lessSysDepartMents!=null && !lessSysDepartMents.isEmpty()){
    			hqlQuery+="  and treeIndex in (";
    			for(int i=0;i<lessSysDepartMents.size();i++){
    				Map lessSysDepartMent=(HashMap)lessSysDepartMents.get(i);
    				String treeIndex=(String)lessSysDepartMent.get("tree_index");
    				if(treeIndex!=null && !StringUtils.isEmpty(treeIndex)){
    					for(int j=treeIndex.length();j>0;j=j-2){
    						if(k>0){
    							hqlQuery+=",";
    						}
    						hqlQuery+="'"+treeIndex.substring(0,j)+"'";
    						k++;
    					}
    				}
    			}
    			hqlQuery+=")";
    		}else{
    			return null;
    		}
    	}
		
		hqlQuery+=" order by treeIndex";
    	return this.getHibernateTemplate().find(hqlQuery);
    }
    
    /**
     * 获取直接下级的部门列表
     * @param moduleId
     * @param orderField
     * @return
     */
    public List getDirectChildDepartments(final Long departmentId, final String orderField){
    	return this.getHibernateTemplate().find("from SysDepartment where parentId=? order by "+orderField, departmentId);
    }
    
    /**
     * 获取某用户所管理的人员的所有的部门
     * @param sysUser
     * @param companyCode
     * @param limitCompany 是否限制于指定的公司
     * @return
     */
    public List getMyAllDepartments(final SysUser sysUser, final String companyCode, boolean limitCompany){
    	if(sysUser==null || StringUtils.isEmpty(sysUser.getUserCode())){
    		return null;
    	}
    	//Modify By WuCF 20140605，得到管理对象
    	SysManager sysManager = (SysManager) getHibernateTemplate().get(SysManager.class, sysUser.getUserCode());
    	
    	String hqlQuery="from SysDepartment ";
    	//如果是指定的特定账户root，则不用增加查询条件
    	if(!Constants.ROOT_ACCOUNT.equals(sysUser.getUserCode()) && !Constants.GLOBAL_ACCOUNT.equals(sysUser.getUserCode())){
    		if("AA".equals(sysManager.getCompanyCode())){//如果是全球管理对象
	        	String sqlQuery="select a.tree_index from jsys_department a " +
	        			" where a.department_id in (" +
	        			" select b.department_id from jsys_manager b, jsys_manager_user c " +
	        			" where b.user_code=c.slave_user_code and c.master_user_code='"+sysUser.getUserCode()+"'" +
	        			")";
	        	if(!StringUtils.isEmpty(companyCode) && (limitCompany || !"AA".equals(sysUser.getCompanyCode()))){
	        		sqlQuery+=" and a.company_code='"+companyCode+"'";
	        	}
	        	List lessSysDepartMents=this.findObjectsBySQL(sqlQuery);
	        	int k=0;
	        	if(lessSysDepartMents!=null && !lessSysDepartMents.isEmpty()){
	        		hqlQuery+="  where treeIndex in (";
	        		for(int i=0;i<lessSysDepartMents.size();i++){
	        			Map lessSysDepartMent=(HashMap)lessSysDepartMents.get(i);
	        			String treeIndex=(String)lessSysDepartMent.get("tree_index");
	        			if(treeIndex!=null && !StringUtils.isEmpty(treeIndex)){
	        				for(int j=treeIndex.length();j>0;j=j-2){
	        					if(k>0){
	        						hqlQuery+=",";
	        					}
	        					hqlQuery+="'"+treeIndex.substring(0,j)+"'";
	        					k++;
	        				}
	        			}
	        		}
	        		hqlQuery+=")";
	        	}else{
	        		return null;
	        	}
    		}else{//如果是分公司管理对象
    			if(sysManager!=null){
    				hqlQuery += " where departmentId='"+sysManager.getDepartmentId()+"' ";
    			}
    		}
    	}
    	hqlQuery+=" order by treeIndex";
    	return this.getHibernateTemplate().find(hqlQuery);
    }
    
    /**
     * 重新生成部门树结构,递归
     * @param companyCode
     * @param parentLvel
     * @param parentTreeIndex
     * @param parentId
     */
    private void saveSysDepartmentsRebuild(String companyCode, Long parentLvel, String parentTreeIndex, Long parentId) {
    	String hqlQuery="from SysDepartment where parentId='"+parentId+"' ";
    	if(parentId.longValue()==0){
    		hqlQuery+=" and companyCode='"+companyCode+"'";
    	}
    	hqlQuery+=" order by orderNo, departmentId";
		List sysDepartments = this.getHibernateTemplate().find(hqlQuery);
		if (sysDepartments != null && sysDepartments.size() > 0) {
			for (int i = 0; i < sysDepartments.size(); i++) {
				SysDepartment sysDepartment = (SysDepartment) sysDepartments.get(i);
				sysDepartment.setTreeLevel(parentLvel + 1);
				sysDepartment.setTreeIndex(parentTreeIndex+StringUtils.leftPad(Long.toString(i+1), 2, '0'));
				this.saveSysDepartment(sysDepartment);
				
				this.saveSysDepartmentsRebuild(companyCode, sysDepartment.getTreeLevel(), sysDepartment.getTreeIndex(), sysDepartment.getDepartmentId());
			}
		}
	}
    
    /**
     * 重新生成部门树结构
     * @param companyCode
     */
    public void saveSysDepartmentsRebuild(final String companyCode){
    	this.saveSysDepartmentsRebuild(companyCode, new Long(0), "", new Long(0));
    }
}