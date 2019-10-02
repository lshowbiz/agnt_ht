package com.joymain.jecs.pm.service.impl;

import java.util.List;

import com.joymain.jecs.pm.dao.JpmWineOrderInterfaceDao;
import com.joymain.jecs.pm.dao.JpmWineOrderListInterfaceDao;
import com.joymain.jecs.pm.model.JpmWineOrderInterface;
import com.joymain.jecs.pm.service.JpmWineOrderInterfaceManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.wine.WineInterfaceUtil;

public class JpmWineOrderInterfaceManagerImpl extends BaseManager implements JpmWineOrderInterfaceManager {
    private JpmWineOrderInterfaceDao dao;

    private JpmWineOrderListInterfaceDao jpmWineOrderListInterfaceDao;

    /**
     * Set the Dao for communication with the data layer.
     * 
     * @param dao
     */
    public void setJpmWineOrderInterfaceDao(JpmWineOrderInterfaceDao dao) {
        this.dao = dao;
    }

    public void setJpmWineOrderListInterfaceDao(JpmWineOrderListInterfaceDao jpmWineOrderListInterfaceDao) {
        this.jpmWineOrderListInterfaceDao = jpmWineOrderListInterfaceDao;
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmWineOrderInterfaceManager#getJpmWineOrderInterfaces(com.joymain.jecs.pm.model.JpmWineOrderInterface)
     */
    public List getJpmWineOrderInterfaces(final JpmWineOrderInterface jpmWineOrderInterface) {
        return dao.getJpmWineOrderInterfaces(jpmWineOrderInterface);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmWineOrderInterfaceManager#getJpmWineOrderInterface(String
     *      moId)
     */
    public JpmWineOrderInterface getJpmWineOrderInterface(final String moId) {
        return dao.getJpmWineOrderInterface(new Long(moId));
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmWineOrderInterfaceManager#saveJpmWineOrderInterface(JpmWineOrderInterface
     *      jpmWineOrderInterface)
     */
    public void saveJpmWineOrderInterface(JpmWineOrderInterface jpmWineOrderInterface) {
        dao.saveJpmWineOrderInterface(jpmWineOrderInterface);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmWineOrderInterfaceManager#removeJpmWineOrderInterface(String
     *      moId)
     */
    public void removeJpmWineOrderInterface(final String moId) {
        dao.removeJpmWineOrderInterface(new Long(moId));
    }

    //added for getJpmWineOrderInterfacesByCrm
    public List getJpmWineOrderInterfacesByCrm(CommonRecord crm, Pager pager) {
        return dao.getJpmWineOrderInterfacesByCrm(crm, pager);
    }

    @Override
    public int rePushOrderToERP(String moId) {
        JpmWineOrderInterface jpmWineOrderInterface = dao.getJpmWineOrderInterface(new Long(moId));
        int ret = new WineInterfaceUtil().sendOrder2ERP(jpmWineOrderInterface, 0);
        return ret;
    }
}
