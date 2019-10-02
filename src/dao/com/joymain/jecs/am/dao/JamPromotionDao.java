
package com.joymain.jecs.am.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.am.model.JamPromotion;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JamPromotionDao extends Dao {

    /**
     * Retrieves all of the jamPromotions
     */
    public List getJamPromotions(JamPromotion jamPromotion);

    /**
     * Gets jamPromotion's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jamPromotion's id
     * @return jamPromotion populated jamPromotion object
     */
    public JamPromotion getJamPromotion(final Long id);

    /**
     * Saves a jamPromotion's information
     * @param jamPromotion the object to be saved
     */    
    public void saveJamPromotion(JamPromotion jamPromotion);

    /**
     * Removes a jamPromotion from the database by id
     * @param id the jamPromotion's id
     */
    public void removeJamPromotion(final Long id);
    //added for getJamPromotionsByCrm
    public List getJamPromotionsByCrm(CommonRecord crm, Pager pager);
}

