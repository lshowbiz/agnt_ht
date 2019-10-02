
package com.joymain.jecs.pm.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pm.model.JpmCombinationRelated;
import com.joymain.jecs.pm.dao.JpmCombinationRelatedDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpmCombinationRelatedManager extends Manager {
    /**
     * Retrieves all of the jpmCombinationRelateds
     */
    public List getJpmCombinationRelateds(JpmCombinationRelated jpmCombinationRelated);

    /**
     * Gets jpmCombinationRelated's information based on uniNo.
     * @param uniNo the jpmCombinationRelated's uniNo
     * @return jpmCombinationRelated populated jpmCombinationRelated object
     */
    public JpmCombinationRelated getJpmCombinationRelated(final String uniNo);

    /**
     * Saves a jpmCombinationRelated's information
     * @param jpmCombinationRelated the object to be saved
     */
    public void saveJpmCombinationRelated(JpmCombinationRelated jpmCombinationRelated);

    /**
     * Removes a jpmCombinationRelated from the database by uniNo
     * @param uniNo the jpmCombinationRelated's uniNo
     */
    public void removeJpmCombinationRelated(final String uniNo);
    //added for getJpmCombinationRelatedsByCrm
    public List getJpmCombinationRelatedsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * @Description 根据套餐代码，期别，退货商品代码  查询套餐子商品明细
     * @author houxyu
     * @date 2016-3-30
     * @param packageCode
     * @param productDate
     * @param subProductNo
     * @return
     */
    public List getJpmCombinationRelateds(String packageCode,String productDate,String subProductNo);
}

