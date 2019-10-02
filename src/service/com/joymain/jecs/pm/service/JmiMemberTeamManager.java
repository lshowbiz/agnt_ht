
package com.joymain.jecs.pm.service;

import java.util.List;

import com.joymain.jecs.pm.model.JmiMemberTeam;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiMemberTeamManager extends Manager {
    /**
     * Retrieves all of the jmiMemberTeams
     */
    public List<JmiMemberTeam> getJmiMemberTeams(JmiMemberTeam jmiMemberTeam);

    /**
     * Gets jmiMemberTeam's information based on code.
     * @param code the jmiMemberTeam's code
     * @return jmiMemberTeam populated jmiMemberTeam object
     */
    public JmiMemberTeam getJmiMemberTeam(final String code);

    /**
     * Saves a jmiMemberTeam's information
     * @param jmiMemberTeam the object to be saved
     */
    public void saveJmiMemberTeam(JmiMemberTeam jmiMemberTeam);

    /**
     * Removes a jmiMemberTeam from the database by code
     * @param code the jmiMemberTeam's code
     */
    public void removeJmiMemberTeam(final String code);
    //added for getJmiMemberTeamsByCrm
    public List getJmiMemberTeamsByCrm(CommonRecord crm);
    
    //added for getJmiMemberTeamsByCrm
    public List getJmiMemberTeamsByCrm(CommonRecord crm,Pager pager);
    
    /**
     * @param crm：查询条件
     * @param type：查询类型    0：新增   1：修改
     * @return：返回是否已经有重复的数据
     */
    public boolean isExist(CommonRecord crm,String type);
    /**
     * 判定当前用户属于某一团队
     * @param curUser
     * @return team code or "root"
     */
    public String getTeamStr(SysUser curUser);
    
    /**
     * 找团队  20150228 w
     * @param curUser
     * @return
     */
    public String teamStr(SysUser curUser);
}

