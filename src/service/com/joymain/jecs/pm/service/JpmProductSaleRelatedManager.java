
package com.joymain.jecs.pm.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pm.model.JpmProductSaleRelated;
import com.joymain.jecs.pm.dao.JpmProductSaleRelatedDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpmProductSaleRelatedManager extends Manager {
    /**
     * Retrieves all of the jpmProductSaleRelateds
     */
    public List getJpmProductSaleRelateds(JpmProductSaleRelated jpmProductSaleRelated);

    /**
     * Gets jpmProductSaleRelated's information based on id.
     * @param id the jpmProductSaleRelated's id
     * @return jpmProductSaleRelated populated jpmProductSaleRelated object
     */
    public JpmProductSaleRelated getJpmProductSaleRelated(final String id);

    /**
     * Saves a jpmProductSaleRelated's information
     * @param jpmProductSaleRelated the object to be saved
     */
    public void saveJpmProductSaleRelated(JpmProductSaleRelated jpmProductSaleRelated);

    /**
     * Removes a jpmProductSaleRelated from the database by id
     * @param id the jpmProductSaleRelated's id
     */
    public void removeJpmProductSaleRelated(final String id);
    //added for getJpmProductSaleRelatedsByCrm
    public List getJpmProductSaleRelatedsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * @param crm：查询条件
     * @param type：查询类型    0：新增   1：修改
     * @return：返回是否已经有重复的数据
     */
    public boolean isExist(CommonRecord crm,String type);
}

