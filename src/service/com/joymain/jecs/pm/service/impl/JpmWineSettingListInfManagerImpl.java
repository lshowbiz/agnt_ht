
package com.joymain.jecs.pm.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pm.model.JpmWineSettingListInf;
import com.joymain.jecs.pm.dao.JpmWineSettingListInfDao;
import com.joymain.jecs.pm.service.JpmWineSettingListInfManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpmWineSettingListInfManagerImpl extends BaseManager implements JpmWineSettingListInfManager {
    private JpmWineSettingListInfDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpmWineSettingListInfDao(JpmWineSettingListInfDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmWineSettingListInfManager#getJpmWineSettingListInfs(com.joymain.jecs.pm.model.JpmWineSettingListInf)
     */
    public List getJpmWineSettingListInfs(final JpmWineSettingListInf jpmWineSettingListInf) {
        return dao.getJpmWineSettingListInfs(jpmWineSettingListInf);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmWineSettingListInfManager#getJpmWineSettingListInf(String idf)
     */
    public JpmWineSettingListInf getJpmWineSettingListInf(final String idf) {
        return dao.getJpmWineSettingListInf(new Long(idf));
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmWineSettingListInfManager#saveJpmWineSettingListInf(JpmWineSettingListInf jpmWineSettingListInf)
     */
    public void saveJpmWineSettingListInf(JpmWineSettingListInf jpmWineSettingListInf) {
        dao.saveJpmWineSettingListInf(jpmWineSettingListInf);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmWineSettingListInfManager#removeJpmWineSettingListInf(String idf)
     */
    public void removeJpmWineSettingListInf(final String idf) {
        dao.removeJpmWineSettingListInf(new Long(idf));
    }
    //added for getJpmWineSettingListInfsByCrm
    public List getJpmWineSettingListInfsByCrm(CommonRecord crm, Pager pager){
	return dao.getJpmWineSettingListInfsByCrm(crm,pager);
    }
}
