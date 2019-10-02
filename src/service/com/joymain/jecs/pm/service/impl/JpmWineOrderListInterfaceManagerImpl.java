
package com.joymain.jecs.pm.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pm.model.JpmWineOrderListInterface;
import com.joymain.jecs.pm.dao.JpmWineOrderListInterfaceDao;
import com.joymain.jecs.pm.service.JpmWineOrderListInterfaceManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpmWineOrderListInterfaceManagerImpl extends BaseManager implements JpmWineOrderListInterfaceManager {
    private JpmWineOrderListInterfaceDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpmWineOrderListInterfaceDao(JpmWineOrderListInterfaceDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmWineOrderListInterfaceManager#getJpmWineOrderListInterfaces(com.joymain.jecs.pm.model.JpmWineOrderListInterface)
     */
    public List getJpmWineOrderListInterfaces(final JpmWineOrderListInterface jpmWineOrderListInterface) {
        return dao.getJpmWineOrderListInterfaces(jpmWineOrderListInterface);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmWineOrderListInterfaceManager#getJpmWineOrderListInterface(String idf)
     */
    public JpmWineOrderListInterface getJpmWineOrderListInterface(final String idf) {
        return dao.getJpmWineOrderListInterface(new Long(idf));
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmWineOrderListInterfaceManager#saveJpmWineOrderListInterface(JpmWineOrderListInterface jpmWineOrderListInterface)
     */
    public void saveJpmWineOrderListInterface(JpmWineOrderListInterface jpmWineOrderListInterface) {
        dao.saveJpmWineOrderListInterface(jpmWineOrderListInterface);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmWineOrderListInterfaceManager#removeJpmWineOrderListInterface(String idf)
     */
    public void removeJpmWineOrderListInterface(final String idf) {
        dao.removeJpmWineOrderListInterface(new Long(idf));
    }
    //added for getJpmWineOrderListInterfacesByCrm
    public List getJpmWineOrderListInterfacesByCrm(CommonRecord crm, Pager pager){
	return dao.getJpmWineOrderListInterfacesByCrm(crm,pager);
    }
}
