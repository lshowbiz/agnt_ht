
package com.joymain.jecs.am.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.am.model.JamPromotion;
import com.joymain.jecs.am.dao.JamPromotionDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JamPromotionManager extends Manager {
    /**
     * Retrieves all of the jamPromotions
     */
    public List getJamPromotions(JamPromotion jamPromotion);

    /**
     * Gets jamPromotion's information based on id.
     * @param id the jamPromotion's id
     * @return jamPromotion populated jamPromotion object
     */
    public JamPromotion getJamPromotion(final String id);

    /**
     * Saves a jamPromotion's information
     * @param jamPromotion the object to be saved
     */
    public void saveJamPromotion(JamPromotion jamPromotion);

    /**
     * Removes a jamPromotion from the database by id
     * @param id the jamPromotion's id
     */
    public void removeJamPromotion(final String id);
    //added for getJamPromotionsByCrm
    public List getJamPromotionsByCrm(CommonRecord crm, Pager pager);
}

