package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.dao.SysUserDao;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.model.SysUserInter;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.json.InterfaceJsonUtil;

public class SysUserDaoHibernate extends BaseDaoHibernate implements SysUserDao {
	public List getObjsBySQL(final String sqlQuery){
    	return this.findObjectsBySQL(sqlQuery);
    }
    
    public void saveObjecsBySQL(final String sqlQuery){
    	this.excuteSql(sqlQuery);
    }
    
	public List getSysUserNotInRole(CommonRecord crm, Pager pager) {
		String table1="select * from jsys_user su where 1=1 ";
		String table2="select * from jsys_user_role sur where 1=1 ";
		String companyCode = crm.getString("companyCode", "");
		if(StringUtils.isNotEmpty(companyCode)){
			table1 +=" and su.company_code='"+companyCode+"'";
		}
		String userType = crm.getString("userType", "");
		if(StringUtils.isNotEmpty(userType)){
			table1 +=" and su.user_type='"+userType+"'";
		}
		String roleId = crm.getString("roleId", "");
		if(StringUtils.isNotEmpty(roleId)){
			table2 +=" and sur.role_id='"+roleId+"'";
		}
		String sql = "select t1.user_code,t1.user_name,t1.user_type,t1.state,t1.company_code from("+table1+") t1," +
				"("+table2+") t2 where  t1.user_code =t2.user_code(+) and t2.user_code is null";
		return super.findObjectsBySQL(sql, pager);
	}

	public List getSysUsersByCrm(CommonRecord crm, Pager pager) {
		String hql = "from SysUser sysUser where 1=1 ";
		String userType = crm.getString("userType", "");
		if(StringUtils.isNotEmpty(userType)){
			hql +=" and sysUser.userType in ('"+userType.toUpperCase()+"')";
		}
		String companyCode = crm.getString("companyCode", "");
		if(StringUtils.isNotEmpty(companyCode)){
			if(!companyCode.equals("CN"))
				hql +=" and sysUser.companyCode='"+companyCode+"'";
			else
				hql +=" and (sysUser.companyCode='CN' or sysUser.companyCode='AA') ";
			
		}
		String userCode = crm.getString("userCode", "").trim();
		if(StringUtils.isNotEmpty(userCode)){
			hql +=" and sysUser.userCode like '"+userCode+"%'";
		}
		
		String userName = crm.getString("userName", "");
		if(StringUtils.isNotEmpty(userName)){
			hql +=" and sysUser.userName like '%"+userName+"%'";
		}
		
		String state = crm.getString("state", "");
		if(StringUtils.isNotEmpty(state)){
			hql +=" and sysUser.state='"+state+"'";
		}
		if(pager == null){
			return this.getHibernateTemplate().find(hql);
		}
		if (!pager.getLimit().getSort().isSorted()) {
			hql += " order by sysUser.userCode desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		
		return this.findObjectsByHqlQuery(hql, pager);
		
	}

	public List getSysUserByExample(final SysUser example) {
		DetachedCriteria ca = DetachedCriteria.forClass(SysUser.class);
		ca.add(Expression.and(Expression.eq("userCode", example.getUserCode()), Expression.eq("password", example.getPassword())));
		return getHibernateTemplate().findByCriteria(ca);
	}
	
	/**
	 * 根据用户编号获取用户信息
	 * @param userCode
	 * @return
	 */
	public SysUser getSysUser(final String userCode) {
		SysUser sysUser = (SysUser) getHibernateTemplate().get(SysUser.class, userCode);
//        if (sysUser == null) {
//            log.warn("uh oh, sysUser with userCode '" +userCode + "' not found...");
//            throw new ObjectRetrievalFailureException(SysUser.class, userCode);
//        }

        return sysUser;
    }
	
	/**
	 * @see com.joymain.jecs.sys.dao.SysUserDao#saveSysUser(SysUser sysUser)
	 */
	public void saveSysUser(final SysUser sysUser) {

		getHibernateTemplate().saveOrUpdate(sysUser);
	}

	public List getSysUserRoleList(CommonRecord crm, Pager pager) {
		String table1="select ur.* from jsys_user u,jsys_user_role ur where u.user_code = ur.user_code ";
		
		String userCode = crm.getString("uCode");
		if(StringUtils.isNotEmpty(userCode)){
			table1 +=" and u.user_Code='"+userCode+"'";
		}		
		
		String sql = "select t1.*,t2.role_id as roleid,t2.role_name from ("+table1+") t1," +
			" jsys_role t2 where  t1.role_id(+) = t2.role_id and t2.available = 1 ";
		
		String companyCode = crm.getString("companyCode", "");
		if(StringUtils.isNotEmpty(companyCode)){
			if(!companyCode.equals("AA"))
				sql +=" and t2.company_code='"+companyCode+"'";
		}
		String userType = crm.getString("userType", "");
		if(StringUtils.isNotEmpty(userType)){
			if("M".equals(userType))
				sql +=" and t2.user_type='"+userType+"'";
			else
				sql +=" and t2.user_type in ('P','Q')";
		}
		
		if (!pager.getLimit().getSort().isSorted()) {
			sql += " order by roleid desc";
		} else {
			sql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		
		return super.findObjectsBySQL(sql, pager);
	}
	
	/**
	 * 返回登录用户/密码匹配
	 * @userCodeAndPsswordJson：用户名和密码的json字符串
	 * @return：-2：用户名密码解析异常   -1：json解析异常  0：用户名为空   1：用户名不存在   2：用户名密码不匹配      3：匹配成功
	 */
	public String getLoginStatus(String userCodeAndPsswordJson) {
		String userCode = "";//用户名
		String password = "";//密码
		String type = "3";//默认匹配
		
		//1.解析字符串
		try{
			//例如：{\"userCode\":\"root\",\"password\":\"1\"}
			//字符串解析，如果异常，会捕获，返回字符串：-1
			SysUserInter su = InterfaceJsonUtil.returnnoJsonToModel(userCodeAndPsswordJson, SysUserInter.class, null);
			if(su!=null){
				userCode = su.getMember_num();
				password = su.getLogin_password();
			}
		}catch(Exception e){
			e.printStackTrace();
			return "-1";
		}
		
		
		//2.解析用户名和密码是否匹配
		try{
			if(StringUtils.isEmpty(userCode)){//传递用户名为空，不存在
				type = "0";
			}else{
				SysUser sysUser = this.getSysUser(userCode.trim());
				if(sysUser==null){//查询不到用户名
					type = "1";
				}else{//用户名和密码不匹配
					if(!password.equalsIgnoreCase(sysUser.getPassword())){
						type = "2";
					}
				}
			}			
		}catch(Exception e){
			type = "-2";
			e.printStackTrace();
		}
		return type;
	}

	@Override
	public List getSysUserRoleListNew(CommonRecord crm, Pager pager) {
		//String table1="select ur.* from jsys_user u,jsys_user_role ur,jsys_role r where u.user_code = ur.user_code and ur.role_id = r.ROLE_ID ";
		String table1="select ur.* from jsys_user u,jsys_user_role ur where u.user_code = ur.user_code ";

		String userCode = crm.getString("uCode");
		if(StringUtils.isNotEmpty(userCode)){
			table1 +=" and u.user_Code='"+userCode+"'";
		}		
		
		String sql = "select t1.*,t2.role_id as roleid,t2.role_name from ("+table1+") t1," +
			" jsys_role t2 where  t1.role_id(+) = t2.role_id and t2.available = 1 ";
		
		//modify by lihao,20170119,角色名称 不显示包含“禁用”的角色名
		sql += " and t2.role_name not like '%禁用%' ";
		String companyCode = crm.getString("companyCode", "");
		if(StringUtils.isNotEmpty(companyCode)){
			if(!companyCode.equals("AA"))
				sql +=" and t2.company_code='"+companyCode+"'";
		}
		String userType = crm.getString("userType", "");
		if(StringUtils.isNotEmpty(userType)){
			if("M".equals(userType))
				sql +=" and t2.user_type='"+userType+"'";
			else
				sql +=" and t2.user_type in ('P','Q')";
		}
		
		if (!pager.getLimit().getSort().isSorted()) {
			sql += " order by roleid desc";
		} else {
			sql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		
		return super.findObjectsBySQL(sql, pager);
	}
}