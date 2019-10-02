
package com.joymain.jecs.am.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.am.model.JamPromotion;
import com.joymain.jecs.am.dao.JamPromotionDao;
import com.joymain.jecs.am.service.JamPromotionManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JamPromotionManagerImpl extends BaseManager implements JamPromotionManager {
    private JamPromotionDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJamPromotionDao(JamPromotionDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.am.service.JamPromotionManager#getJamPromotions(com.joymain.jecs.am.model.JamPromotion)
     */
    public List getJamPromotions(final JamPromotion jamPromotion) {
        return dao.getJamPromotions(jamPromotion);
    }

    /**
     * @see com.joymain.jecs.am.service.JamPromotionManager#getJamPromotion(String id)
     */
    public JamPromotion getJamPromotion(final String id) {
        return dao.getJamPromotion(new Long(id));
    }

    /**
     * @see com.joymain.jecs.am.service.JamPromotionManager#saveJamPromotion(JamPromotion jamPromotion)
     */
    public void saveJamPromotion(JamPromotion jamPromotion) {
        dao.saveJamPromotion(jamPromotion);
    }

    /**
     * @see com.joymain.jecs.am.service.JamPromotionManager#removeJamPromotion(String id)
     */
    public void removeJamPromotion(final String id) {
        dao.removeJamPromotion(new Long(id));
    }
    //added for getJamPromotionsByCrm
    public List getJamPromotionsByCrm(CommonRecord crm, Pager pager){
	return dao.getJamPromotionsByCrm(crm,pager);
    }
}
