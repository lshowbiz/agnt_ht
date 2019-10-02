
package com.joymain.jecs.pm.dao;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pm.model.JpmSalePromoter;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmSalePromoterDao extends Dao {

    /**
     * Retrieves all of the jpmSalePromoters
     */
    public List getJpmSalePromoters(JpmSalePromoter jpmSalePromoter);

    /**
     * Gets jpmSalePromoter's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param spno the jpmSalePromoter's spno
     * @return jpmSalePromoter populated jpmSalePromoter object
     */
    public JpmSalePromoter getJpmSalePromoter(final Long spno);

    /**
     * Saves a jpmSalePromoter's information
     * @param jpmSalePromoter the object to be saved
     */    
    public void saveJpmSalePromoter(JpmSalePromoter jpmSalePromoter);

    /**
     * Removes a jpmSalePromoter from the database by spno
     * @param spno the jpmSalePromoter's spno
     */
    public void removeJpmSalePromoter(final Long spno);
    //added for getJpmSalePromotersByCrm
    public List<JpmSalePromoter> getJpmSalePromotersByCrm(CommonRecord crm, Pager pager);
    /**
     * get jpmSalePromoter by date
     * @param stime
     * @param isActiva
     * @param flag 策略类型 (若为null值,则查询当前可用的促销策略)
     * @return list
     */
    public List<JpmSalePromoter> getSaleByDate(String stime,String isActiva,String flag);
}

