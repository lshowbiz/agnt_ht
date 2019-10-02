
package com.joymain.jecs.po.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.po.model.JpoMemberNycLog;
import com.joymain.jecs.po.dao.JpoMemberNycLogDao;
import com.joymain.jecs.po.service.JpoMemberNycLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpoMemberNycLogManagerImpl extends BaseManager implements JpoMemberNycLogManager {
    private JpoMemberNycLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpoMemberNycLogDao(JpoMemberNycLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberNycLogManager#getJpoMemberNycLogs(com.joymain.jecs.po.model.JpoMemberNycLog)
     */
    public List getJpoMemberNycLogs(final JpoMemberNycLog jpoMemberNycLog) {
        return dao.getJpoMemberNycLogs(jpoMemberNycLog);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberNycLogManager#getJpoMemberNycLog(String id)
     */
    public JpoMemberNycLog getJpoMemberNycLog(final String id) {
        return dao.getJpoMemberNycLog(new String(id));
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberNycLogManager#saveJpoMemberNycLog(JpoMemberNycLog jpoMemberNycLog)
     */
    public void saveJpoMemberNycLog(JpoMemberNycLog jpoMemberNycLog) {
        dao.saveJpoMemberNycLog(jpoMemberNycLog);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberNycLogManager#removeJpoMemberNycLog(String id)
     */
    public void removeJpoMemberNycLog(final String id) {
        dao.removeJpoMemberNycLog(new String(id));
    }
    //added for getJpoMemberNycLogsByCrm
    public List getJpoMemberNycLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getJpoMemberNycLogsByCrm(crm,pager);
    }
}
