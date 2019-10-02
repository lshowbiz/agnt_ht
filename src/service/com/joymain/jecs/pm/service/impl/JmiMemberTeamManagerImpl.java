
package com.joymain.jecs.pm.service.impl;

import java.util.Iterator;
import java.util.List;

import com.joymain.jecs.Constants;
import com.joymain.jecs.mi.dao.JmiMemberDao;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.pm.dao.JmiMemberTeamDao;
import com.joymain.jecs.pm.model.JmiMemberTeam;
import com.joymain.jecs.pm.service.JmiMemberTeamManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

public class JmiMemberTeamManagerImpl extends BaseManager implements JmiMemberTeamManager {
    private JmiMemberTeamDao dao;
    private JmiMemberDao jmiMemberDao;
    
    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiMemberTeamDao(JmiMemberTeamDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pm.service.JmiMemberTeamManager#getJmiMemberTeams(com.joymain.jecs.pm.model.JmiMemberTeam)
     */
    public List getJmiMemberTeams(final JmiMemberTeam jmiMemberTeam) {
        return dao.getJmiMemberTeams(jmiMemberTeam);
    }

    /**
     * @see com.joymain.jecs.pm.service.JmiMemberTeamManager#getJmiMemberTeam(String code)
     */
    public JmiMemberTeam getJmiMemberTeam(final String code) {
        return dao.getJmiMemberTeam(new String(code));
    }

    /**
     * @see com.joymain.jecs.pm.service.JmiMemberTeamManager#saveJmiMemberTeam(JmiMemberTeam jmiMemberTeam)
     */
    public void saveJmiMemberTeam(JmiMemberTeam jmiMemberTeam) {
        dao.saveJmiMemberTeam(jmiMemberTeam);
    }

    /**
     * @see com.joymain.jecs.pm.service.JmiMemberTeamManager#removeJmiMemberTeam(String code)
     */
    public void removeJmiMemberTeam(final String code) {
        dao.removeJmiMemberTeam(new String(code));
    }
    //added for getJmiMemberTeamsByCrm
    public List getJmiMemberTeamsByCrm(CommonRecord crm){
	return dao.getJmiMemberTeamsByCrm(crm);
    }
    
    //added for getJmiMemberTeamsByCrm
    public List getJmiMemberTeamsByCrm(CommonRecord crm,Pager pager){
	return dao.getJmiMemberTeamsByCrm(crm,pager);
    }
    
    /**
     * @param crm：查询条件
     * @param type：查询类型    0：新增   1：修改
     * @return：返回是否已经有重复的数据
     */
	public boolean isExist(CommonRecord crm, String type) {
		return dao.isExist(crm,type);
	}
	
	public String getTeamStr(SysUser curUser) {
		 String str = Constants.JM;
		 if(Constants.MEMBER_M.equals(curUser.getUserType())){
			 
			 CommonRecord crm = new CommonRecord();
			 crm.addField("status",Constants.TEAM_Y);
			 List<JmiMemberTeam> teamList = dao.getJmiMemberTeamsByCrm(crm);
			
			 Iterator<JmiMemberTeam> ite = teamList.iterator();
			 
			 while(ite.hasNext()){
	        	JmiMemberTeam team=ite.next();
	        		
	        	JmiMember lcMiMember =jmiMemberDao.getJmiMember(team.getCode());
	        	if(lcMiMember!=null && ! lcMiMember.getUserCode().equals(str) && 
	        			lcMiMember.getJmiRecommendRef()!=null){	
	        		
		        	String teamIndex=lcMiMember.
		        			getJmiRecommendRef().getTreeIndex();
		       		String loginTreeIndex=curUser.getJmiMember().
		       				getJmiRecommendRef().getTreeIndex();
		       		String rmemberIndexTmp = StringUtil.getLeft(
		       				loginTreeIndex, teamIndex.length());
		       		
		       		if(loginTreeIndex.length() >= teamIndex.length() && 
		       				rmemberIndexTmp.equals(teamIndex)){
		       			
		       			str = team.getCode();
		       			break;
		       		}
		       	 }
			 }
	     }
		 return str;
	 }

	public String teamStr(SysUser curUser){
		 return dao.teamStr(curUser);
	 } 
	
	public JmiMemberDao getJmiMemberDao() {
		return jmiMemberDao;
	}

	public void setJmiMemberDao(JmiMemberDao jmiMemberDao) {
		this.jmiMemberDao = jmiMemberDao;
	}
	
}
