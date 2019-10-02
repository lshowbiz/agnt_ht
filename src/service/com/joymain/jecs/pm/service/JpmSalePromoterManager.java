
package com.joymain.jecs.pm.service;

import java.util.Date;
import java.util.List;

import com.joymain.jecs.pm.dao.JpmSalePromoterDao;
import com.joymain.jecs.pm.model.JpmSalePromoter;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpmSalePromoterManager extends Manager {
    /**
     * Retrieves all of the jpmSalePromoters
     */
    public List getJpmSalePromoters(JpmSalePromoter jpmSalePromoter);

    /**
     * Gets jpmSalePromoter's information based on spno.
     * @param spno the jpmSalePromoter's spno
     * @return jpmSalePromoter populated jpmSalePromoter object
     */
    public JpmSalePromoter getJpmSalePromoter(final String spno);

    /**
     * Saves a jpmSalePromoter's information
     * @param jpmSalePromoter the object to be saved
     */
    public void saveJpmSalePromoter(JpmSalePromoter jpmSalePromoter);

    /**
     * Removes a jpmSalePromoter from the database by spno
     * @param spno the jpmSalePromoter's spno
     */
    public void removeJpmSalePromoter(final String spno);
    //added for getJpmSalePromotersByCrm
    public List<JpmSalePromoter> getJpmSalePromotersByCrm(CommonRecord crm, Pager pager);
    /**
     * 获取当前已激活的促销策略
     * @param stime (yyyy-MM-dd)
     * @param isActiva
     * @param flag 策略类型 (若为null值,则查询当前可用的促销策略)
     * @return list
     */
    public List<JpmSalePromoter> getSaleByDate(String stime, String isActiva,String saleType);
    
}

