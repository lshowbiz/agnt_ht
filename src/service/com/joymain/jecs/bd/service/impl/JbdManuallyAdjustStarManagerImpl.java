
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdManuallyAdjustStar;
import com.joymain.jecs.bd.dao.JbdManuallyAdjustStarDao;
import com.joymain.jecs.bd.service.JbdManuallyAdjustStarManager;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdManuallyAdjustStarManagerImpl extends BaseManager implements JbdManuallyAdjustStarManager {
    private JbdManuallyAdjustStarDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdManuallyAdjustStarDao(JbdManuallyAdjustStarDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdManuallyAdjustStarManager#getJbdManuallyAdjustStars(com.joymain.jecs.bd.model.JbdManuallyAdjustStar)
     */
    public List getJbdManuallyAdjustStars(final JbdManuallyAdjustStar jbdManuallyAdjustStar) {
        return dao.getJbdManuallyAdjustStars(jbdManuallyAdjustStar);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdManuallyAdjustStarManager#getJbdManuallyAdjustStar(String id)
     */
    public JbdManuallyAdjustStar getJbdManuallyAdjustStar(final String id) {
        return dao.getJbdManuallyAdjustStar(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdManuallyAdjustStarManager#saveJbdManuallyAdjustStar(JbdManuallyAdjustStar jbdManuallyAdjustStar)
     */
    public void saveJbdManuallyAdjustStar(JbdManuallyAdjustStar jbdManuallyAdjustStar) {
        dao.saveJbdManuallyAdjustStar(jbdManuallyAdjustStar);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdManuallyAdjustStarManager#removeJbdManuallyAdjustStar(String id)
     */
    public void removeJbdManuallyAdjustStar(final String id) {
        dao.removeJbdManuallyAdjustStar(new Long(id));
    }
    //added for getJbdManuallyAdjustStarsByCrm
    public List getJbdManuallyAdjustStarsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdManuallyAdjustStarsByCrm(crm,pager);
    }

	public JbdManuallyAdjustStar getJbdManuallyAdjustStarByUserCodeAndWeek(String userCode, String wweek) {
		return dao.getJbdManuallyAdjustStarByUserCodeAndWeek(userCode, wweek);
	}
	public void saveJbdManuallyAdjustStarList(List<JbdManuallyAdjustStar> jbdManuallyAdjustStarList) {
		JbdManuallyAdjustStar curJbdManuallyAdjustStar= null;
		if(!MeteorUtil.isBlankByList(jbdManuallyAdjustStarList)){
			for(JbdManuallyAdjustStar s : jbdManuallyAdjustStarList){
				curJbdManuallyAdjustStar= this.getJbdManuallyAdjustStarByUserCodeAndWeek(s.getUserCode(), s.getWweek().toString());
				if(null == curJbdManuallyAdjustStar){
					this.saveJbdManuallyAdjustStar(s);
				}else{
					curJbdManuallyAdjustStar.setHonorStar(s.getHonorStar());
					curJbdManuallyAdjustStar.setPassStar(s.getPassStar());
					curJbdManuallyAdjustStar.setHonorGrade(s.getHonorGrade());
					curJbdManuallyAdjustStar.setPassGrade(s.getPassGrade());
					this.saveJbdManuallyAdjustStar(curJbdManuallyAdjustStar);
				}
				
			}
		}
        
    }
}
