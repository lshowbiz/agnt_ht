
package com.joymain.jecs.am.service.impl;

import java.util.List;

import com.joymain.jecs.am.dao.AmRegularMsgDao;
import com.joymain.jecs.am.model.AmRegularMsg;
import com.joymain.jecs.am.service.AmRegularMsgManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class AmRegularMsgManagerImpl extends BaseManager implements AmRegularMsgManager {
    private AmRegularMsgDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setAmRegularMsgDao(AmRegularMsgDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.am.service.AmRegularMsgManager#getAmRegularMsgs(com.joymain.jecs.am.model.AmRegularMsg)
     */
    public List getAmRegularMsgs(final AmRegularMsg amRegularMsg) {
        return dao.getAmRegularMsgs(amRegularMsg);
    }

    /**
     * @see com.joymain.jecs.am.service.AmRegularMsgManager#getAmRegularMsg(String uniNo)
     */
    public AmRegularMsg getAmRegularMsg(final String uniNo) {
        return dao.getAmRegularMsg(new String(uniNo));
    }

    /**
     * @see com.joymain.jecs.am.service.AmRegularMsgManager#saveAmRegularMsg(AmRegularMsg amRegularMsg)
     */
    public void saveAmRegularMsg(AmRegularMsg amRegularMsg) {
        dao.saveAmRegularMsg(amRegularMsg);
    }

    /**
     * @see com.joymain.jecs.am.service.AmRegularMsgManager#removeAmRegularMsg(String uniNo)
     */
    public void removeAmRegularMsg(final String uniNo) {
        dao.removeAmRegularMsg(new String(uniNo));
    }
    //added for getAmRegularMsgsByCrm
		public List getAmRegularMsgsByCrm(CommonRecord crm, Pager pager){
				return dao.getAmRegularMsgsByCrm(crm,pager);
		}
}
