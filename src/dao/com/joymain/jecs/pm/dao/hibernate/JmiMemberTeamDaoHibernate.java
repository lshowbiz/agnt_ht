
package com.joymain.jecs.pm.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.dao.JmiMemberTeamDao;
import com.joymain.jecs.pm.model.JmiMemberTeam;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JmiMemberTeamDaoHibernate extends BaseDaoHibernate implements JmiMemberTeamDao {

    /**
     * @see com.joymain.jecs.pm.dao.JmiMemberTeamDao#getJmiMemberTeams(com.joymain.jecs.pm.model.JmiMemberTeam)
     */
    public List getJmiMemberTeams(final JmiMemberTeam jmiMemberTeam) {
        return getHibernateTemplate().find("from JmiMemberTeam jmiMemberTeam where jmiMemberTeam.status = '0'");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiMemberTeam == null) {
            return getHibernateTemplate().find("from JmiMemberTeam");
        } else {
            // filter on properties set in the jmiMemberTeam
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiMemberTeam).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiMemberTeam.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pm.dao.JmiMemberTeamDao#getJmiMemberTeam(String code)
     */
    public JmiMemberTeam getJmiMemberTeam(final String code) {
        JmiMemberTeam jmiMemberTeam = (JmiMemberTeam) getHibernateTemplate().get(JmiMemberTeam.class, code);
        /*if (jmiMemberTeam == null) {
            log.warn("uh oh, jmiMemberTeam with code '" + code + "' not found...");
            throw new ObjectRetrievalFailureException(JmiMemberTeam.class, code);
        }*/

        return jmiMemberTeam;
    }

    /**
     * @see com.joymain.jecs.pm.dao.JmiMemberTeamDao#saveJmiMemberTeam(JmiMemberTeam jmiMemberTeam)
     */    
    public void saveJmiMemberTeam(final JmiMemberTeam jmiMemberTeam) {
        getHibernateTemplate().saveOrUpdate(jmiMemberTeam); 
    }

    /**
     * @see com.joymain.jecs.pm.dao.JmiMemberTeamDao#removeJmiMemberTeam(String code)
     */
    public void removeJmiMemberTeam(final String code) {
        getHibernateTemplate().delete(getJmiMemberTeam(code));
    }
    //added for getJmiMemberTeamsByCrm
    public List getJmiMemberTeamsByCrm(CommonRecord crm){
    	String hql = "from JmiMemberTeam jmiMemberTeam where 1=1 ";
    	/** 
	---example---
	String userCode = crm.getString("userCode", "");
	if(StringUtils.isNotEmpty(userCode)){
		hql += "???????????";
	}
	 ***/
    	String status = crm.getString("status","");
    	if(StringUtils.isNotEmpty(status)){
    		hql += " and jmiMemberTeam.status='"+status+"' "; 
    	}
    	// 
    	hql += " order by name";
		return this.findObjectsByHqlQuery(hql);  
    }
    
    //added for getJmiMemberTeamsByCrm
    public List getJmiMemberTeamsByCrm(CommonRecord crm,Pager pager){
    	String hql = "from JmiMemberTeam jmiMemberTeam where 1=1 ";
    	//编码
    	String code = crm.getString("code","");
    	if(StringUtils.isNotEmpty(code)){
    		hql += " and jmiMemberTeam.code='"+code.trim()+"' ";
    	}
    	
    	//简称
    	String name = crm.getString("name","");
    	if(StringUtils.isNotEmpty(name)){
    		hql += " and jmiMemberTeam.name='"+name.trim()+"' ";
    	}
    	
    	//全称
    	String fullName = crm.getString("fullName","");
    	if(StringUtils.isNotEmpty(fullName)){
    		hql += " and jmiMemberTeam.fullName like '%"+fullName.trim()+"%' ";
    	}
    	
    	//是否绑定事业锦囊
    	String isBuy = crm.getString("isBuy","");
    	if(StringUtils.isNotEmpty(isBuy)){
    		hql += " and jmiMemberTeam.isBuy='"+isBuy.trim()+"' ";
    	}
    	
    	//状态
    	String status = crm.getString("status","");
    	if(StringUtils.isNotEmpty(status)){
    		hql += " and jmiMemberTeam.status='"+status+"' "; 
    	}
    	// 
    	hql += " order by code";
		return this.findObjectsByHqlQuery(hql,pager);
    }
    
    /**
     * @param crm：查询条件
     * @param type：查询类型    0：新增   1：修改
     * @return：返回是否已经有重复的数据
     */
	public boolean isExist(CommonRecord crm, String type) {
		boolean isExist = false;
		String hql = "from JmiMemberTeam jmiMemberTeam where 1=1 ";
		 
		String code = crm.getString("code",""); 
		if(StringUtils.isNotEmpty(code)){
    		hql += " and jmiMemberTeam.code='"+code.trim()+"' ";
    	}
    	
    	List list = this.findObjectsByHqlQuery(hql);
    	if(list!=null && list.size()>0){
    		isExist = true;
    	} 
		return isExist;
	}
	
	public String teamStr(SysUser curUser){

		String userCode="";
		List list=jdbcTemplate.queryForList("select fn_get_team_no('"+curUser.getUserCode()+"') as topuser_code from dual");
		if(!list.isEmpty()){
			Map map =(Map) list.get(0);
			if(map!=null){
				userCode=map.get("topuser_code").toString();
			}
		}
		 return userCode;
	 } 
}
