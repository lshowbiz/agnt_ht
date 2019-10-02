
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.bd.model.JbdTravelPointLogAll;
import com.joymain.jecs.bd.dao.JbdTravelPointLogAllDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdTravelPointLogAllManager extends Manager {
    /**
     * Retrieves all of the jbdTravelPointLogAlls
     */
    public List getJbdTravelPointLogAlls(JbdTravelPointLogAll jbdTravelPointLogAll);

    /**
     * Gets jbdTravelPointLogAll's information based on logId.
     * @param logId the jbdTravelPointLogAll's logId
     * @return jbdTravelPointLogAll populated jbdTravelPointLogAll object
     */
    public JbdTravelPointLogAll getJbdTravelPointLogAll(final String logId);

    /**
     * Saves a jbdTravelPointLogAll's information
     * @param jbdTravelPointLogAll the object to be saved
     */
    public void saveJbdTravelPointLogAll(JbdTravelPointLogAll jbdTravelPointLogAll);

    /**
     * Removes a jbdTravelPointLogAll from the database by logId
     * @param logId the jbdTravelPointLogAll's logId
     */
    public void removeJbdTravelPointLogAll(final String logId);
    //added for getJbdTravelPointLogAllsByCrm
    public List getJbdTravelPointLogAllsByCrm(CommonRecord crm, Pager pager);
    /**
     * 保存积分
     * @param jbdTravelPointLogAll
     * @param defSysUser
     */
    public void saveJbdTravelPointLogAll(JbdTravelPointLogAll jbdTravelPointLogAll, SysUser defSysUser);
    /**
     * 批量保存
     * @param list
     * @param defSysUser
     */
    public void changeJbdTravelPointAlls(List list, SysUser defSysUser);
}

