package com.joymain.jecs.pm.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pm.model.JpmWineOrderInterface;
import com.joymain.jecs.pm.model.JpmWineSettingInf;
import com.joymain.jecs.pm.dao.JpmWineSettingInfDao;
import com.joymain.jecs.pm.service.JpmWineSettingInfManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.wine.WineInterfaceUtil;

public class JpmWineSettingInfManagerImpl extends BaseManager implements JpmWineSettingInfManager {
    private JpmWineSettingInfDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * 
     * @param dao
     */
    public void setJpmWineSettingInfDao(JpmWineSettingInfDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmWineSettingInfManager#getJpmWineSettingInfs(com.joymain.jecs.pm.model.JpmWineSettingInf)
     */
    public List getJpmWineSettingInfs(final JpmWineSettingInf jpmWineSettingInf) {
        return dao.getJpmWineSettingInfs(jpmWineSettingInf);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmWineSettingInfManager#getJpmWineSettingInf(String
     *      settingId)
     */
    public JpmWineSettingInf getJpmWineSettingInf(final String settingId) {
        return dao.getJpmWineSettingInf(new Long(settingId));
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmWineSettingInfManager#saveJpmWineSettingInf(JpmWineSettingInf
     *      jpmWineSettingInf)
     */
    public void saveJpmWineSettingInf(JpmWineSettingInf jpmWineSettingInf) {
        dao.saveJpmWineSettingInf(jpmWineSettingInf);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmWineSettingInfManager#removeJpmWineSettingInf(String
     *      settingId)
     */
    public void removeJpmWineSettingInf(final String settingId) {
        dao.removeJpmWineSettingInf(new Long(settingId));
    }

    //added for getJpmWineSettingInfsByCrm
    public List getJpmWineSettingInfsByCrm(CommonRecord crm, Pager pager) {
        return dao.getJpmWineSettingInfsByCrm(crm, pager);
    }

    @Override
    public int rePushSettingToERP(String settingId) {
        JpmWineSettingInf jpmWineSettingInf = dao.getJpmWineSettingInf(new Long(settingId));
        int ret = new WineInterfaceUtil().sendSettingToERP(jpmWineSettingInf, 0);
        return ret;
    }
}
