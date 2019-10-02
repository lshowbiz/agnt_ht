
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdManuallyAdjustStar;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdManuallyAdjustStarDao extends Dao {

    /**
     * Retrieves all of the jbdManuallyAdjustStars
     */
    public List getJbdManuallyAdjustStars(JbdManuallyAdjustStar jbdManuallyAdjustStar);

    /**
     * Gets jbdManuallyAdjustStar's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdManuallyAdjustStar's id
     * @return jbdManuallyAdjustStar populated jbdManuallyAdjustStar object
     */
    public JbdManuallyAdjustStar getJbdManuallyAdjustStar(final Long id);

    /**
     * Saves a jbdManuallyAdjustStar's information
     * @param jbdManuallyAdjustStar the object to be saved
     */    
    public void saveJbdManuallyAdjustStar(JbdManuallyAdjustStar jbdManuallyAdjustStar);

    /**
     * Removes a jbdManuallyAdjustStar from the database by id
     * @param id the jbdManuallyAdjustStar's id
     */
    public void removeJbdManuallyAdjustStar(final Long id);
    //added for getJbdManuallyAdjustStarsByCrm
    public List getJbdManuallyAdjustStarsByCrm(CommonRecord crm, Pager pager);
    /**
     * 根据会员编号周期找出记录
     * @return
     */
    
    public JbdManuallyAdjustStar getJbdManuallyAdjustStarByUserCodeAndWeek(String userCode,String wweek);
}

