
package com.joymain.jecs.po.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.po.model.JpoMemberNycQualify;
import com.joymain.jecs.po.dao.JpoMemberNycQualifyDao;
import com.joymain.jecs.po.service.JpoMemberNycQualifyManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpoMemberNycQualifyManagerImpl extends BaseManager implements JpoMemberNycQualifyManager {
    private JpoMemberNycQualifyDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpoMemberNycQualifyDao(JpoMemberNycQualifyDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberNycQualifyManager#getJpoMemberNycQualifys(com.joymain.jecs.po.model.JpoMemberNycQualify)
     */
    public List getJpoMemberNycQualifys(final JpoMemberNycQualify jpoMemberNycQualify) {
        return dao.getJpoMemberNycQualifys(jpoMemberNycQualify);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberNycQualifyManager#getJpoMemberNycQualify(String id)
     */
    public JpoMemberNycQualify getJpoMemberNycQualify(final String id) {
        return dao.getJpoMemberNycQualify(Long.parseLong(id));
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberNycQualifyManager#saveJpoMemberNycQualify(JpoMemberNycQualify jpoMemberNycQualify)
     */
    public void saveJpoMemberNycQualify(JpoMemberNycQualify jpoMemberNycQualify) {
        dao.saveJpoMemberNycQualify(jpoMemberNycQualify);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberNycQualifyManager#removeJpoMemberNycQualify(String id)
     */
    public void removeJpoMemberNycQualify(final String id) {
        dao.removeJpoMemberNycQualify(Long.parseLong(id));
    }
    //added for getJpoMemberNycQualifysByCrm
    public List getJpoMemberNycQualifysByCrm(CommonRecord crm, Pager pager){
	return dao.getJpoMemberNycQualifysByCrm(crm,pager);
    }
}
