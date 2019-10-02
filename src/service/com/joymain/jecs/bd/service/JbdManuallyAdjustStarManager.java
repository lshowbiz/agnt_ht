
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.JbdManuallyAdjustStar;
import com.joymain.jecs.bd.dao.JbdManuallyAdjustStarDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdManuallyAdjustStarManager extends Manager {
    /**
     * Retrieves all of the jbdManuallyAdjustStars
     */
    public List getJbdManuallyAdjustStars(JbdManuallyAdjustStar jbdManuallyAdjustStar);

    /**
     * Gets jbdManuallyAdjustStar's information based on id.
     * @param id the jbdManuallyAdjustStar's id
     * @return jbdManuallyAdjustStar populated jbdManuallyAdjustStar object
     */
    public JbdManuallyAdjustStar getJbdManuallyAdjustStar(final String id);

    /**
     * Saves a jbdManuallyAdjustStar's information
     * @param jbdManuallyAdjustStar the object to be saved
     */
    public void saveJbdManuallyAdjustStar(JbdManuallyAdjustStar jbdManuallyAdjustStar);

    /**
     * Removes a jbdManuallyAdjustStar from the database by id
     * @param id the jbdManuallyAdjustStar's id
     */
    public void removeJbdManuallyAdjustStar(final String id);
    //added for getJbdManuallyAdjustStarsByCrm
    public List getJbdManuallyAdjustStarsByCrm(CommonRecord crm, Pager pager);
    /**
     * 根据会员编号周期找出记录
     * @return
     */
    
    public JbdManuallyAdjustStar getJbdManuallyAdjustStarByUserCodeAndWeek(String userCode,String wweek);
    
    /**
     * @Description:批量保存手工定级
     * @author:			侯忻宇
     * @date:		    2016-10-11
     * @param jbdManuallyAdjustStarList:
     * @exception:
     * @return:
     */
    public void saveJbdManuallyAdjustStarList(List<JbdManuallyAdjustStar> jbdManuallyAdjustStarList);
}

