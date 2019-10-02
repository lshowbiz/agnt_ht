
package com.joymain.jecs.pm.service.impl;

import java.util.List;

import com.joymain.jecs.pm.dao.JpmSalePromoterDao;
import com.joymain.jecs.pm.dao.JpmSalepromoterProDao;
import com.joymain.jecs.pm.model.JpmSalePromoter;
import com.joymain.jecs.pm.service.JpmSalePromoterManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpmSalePromoterManagerImpl extends BaseManager implements JpmSalePromoterManager {
    private JpmSalePromoterDao dao;
    private JpmSalepromoterProDao jpmSalepromoterProDao;
    
    /**
     * @see com.joymain.jecs.pm.service.JpmSalePromoterManager#getJpmSalePromoters(com.joymain.jecs.pm.model.JpmSalePromoter)
     */
    public List<JpmSalePromoter> getJpmSalePromoters(final JpmSalePromoter jpmSalePromoter) {
        return dao.getJpmSalePromoters(jpmSalePromoter);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmSalePromoterManager#getJpmSalePromoter(String spno)
     */
    public JpmSalePromoter getJpmSalePromoter(final String spno) {
        return dao.getJpmSalePromoter(new Long(spno));
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmSalePromoterManager#saveJpmSalePromoter(JpmSalePromoter jpmSalePromoter)
     */
    public void saveJpmSalePromoter(JpmSalePromoter jpmSalePromoter) {
        dao.saveJpmSalePromoter(jpmSalePromoter);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmSalePromoterManager#removeJpmSalePromoter(String spno)
     */
    public void removeJpmSalePromoter(final String spno) {
    	jpmSalepromoterProDao.delJpmSalepromoterProBySpno(spno);
        dao.removeJpmSalePromoter(new Long(spno));
    }
    //added for getJpmSalePromotersByCrm
    public List<JpmSalePromoter> getJpmSalePromotersByCrm(CommonRecord crm, Pager pager){
    	return dao.getJpmSalePromotersByCrm(crm,pager);
    }

	public List<JpmSalePromoter> getSaleByDate(String stime,String isActiva,String saleType) {
		
		return dao.getSaleByDate(stime, isActiva,saleType);
	}
	 public JpmSalepromoterProDao getJpmSalepromoterProDao() {
			return jpmSalepromoterProDao;
		}

		public void setJpmSalepromoterProDao(JpmSalepromoterProDao jpmSalepromoterProDao) {
			this.jpmSalepromoterProDao = jpmSalepromoterProDao;
		}

		/**
	     * Set the Dao for communication with the data layer.
	     * @param dao
	     */
	    public void setJpmSalePromoterDao(JpmSalePromoterDao dao) {
	        this.dao = dao;
	    }
}
