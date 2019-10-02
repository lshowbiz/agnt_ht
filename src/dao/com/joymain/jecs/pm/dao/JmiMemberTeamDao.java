
package com.joymain.jecs.pm.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pm.model.JmiMemberTeam;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiMemberTeamDao extends Dao {

    /**
     * Retrieves all of the jmiMemberTeams
     */
    public List getJmiMemberTeams(JmiMemberTeam jmiMemberTeam);

    /**
     * Gets jmiMemberTeam's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
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
     * 找团队
     * @param curUser
     * @return
     */
    public String teamStr(SysUser curUser);
}

