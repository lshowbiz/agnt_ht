
package com.joymain.jecs.pm.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pm.model.JpmConfigSpecDetailed;
import com.joymain.jecs.pm.dao.JpmConfigSpecDetailedDao;
import com.joymain.jecs.pm.service.JpmConfigSpecDetailedManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpmConfigSpecDetailedManagerImpl extends BaseManager implements JpmConfigSpecDetailedManager {
    private JpmConfigSpecDetailedDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpmConfigSpecDetailedDao(JpmConfigSpecDetailedDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmConfigSpecDetailedManager#getJpmConfigSpecDetaileds(com.joymain.jecs.pm.model.JpmConfigSpecDetailed)
     */
    public List getJpmConfigSpecDetaileds(final JpmConfigSpecDetailed jpmConfigSpecDetailed) {
        return dao.getJpmConfigSpecDetaileds(jpmConfigSpecDetailed);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmConfigSpecDetailedManager#getJpmConfigSpecDetailed(String specNo)
     */
    public JpmConfigSpecDetailed getJpmConfigSpecDetailed(final String specNo) {
        return dao.getJpmConfigSpecDetailed(new Long(specNo));
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmConfigSpecDetailedManager#saveJpmConfigSpecDetailed(JpmConfigSpecDetailed jpmConfigSpecDetailed)
     */
    public void saveJpmConfigSpecDetailed(JpmConfigSpecDetailed jpmConfigSpecDetailed) {
        dao.saveJpmConfigSpecDetailed(jpmConfigSpecDetailed);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmConfigSpecDetailedManager#removeJpmConfigSpecDetailed(String specNo)
     */
    public void removeJpmConfigSpecDetailed(final String specNo) {
        dao.removeJpmConfigSpecDetailed(new Long(specNo));
    }
    //added for getJpmConfigSpecDetailedsByCrm
    public List getJpmConfigSpecDetailedsByCrm(CommonRecord crm, Pager pager){
	return dao.getJpmConfigSpecDetailedsByCrm(crm,pager);
    }
    @Override
    public JpmConfigSpecDetailed getJpmConfigSpecDetailedBySpecNo(Long specNo)
    {
        return dao.getJpmConfigSpecDetailedBySpecNo(specNo);
    }

    @Override
    public Long getJpmConfigSpecDetailedWeightByConfigNo(Long configNo)
    {
        return dao.getJpmConfigSpecDetailedWeightByConfigNo(configNo);
    }

    @Override
    public void delJpmConfigSpecDetailedBySpecNo(Long specNo)
    {
        dao.delJpmConfigSpecDetailedBySpecNo(specNo);
    }

    @Override
    public List<JpmConfigSpecDetailed> getJpmConfigSpecDetailedListByConfigNo(Long configNo)
    {
        return dao.getJpmConfigSpecDetailedListByConfigNo(configNo);
    }

    @Override
    public Long getPriceByConfigNo(String configNo)
    {
        return dao.getPriceByConfigNo(configNo);
    }
}
