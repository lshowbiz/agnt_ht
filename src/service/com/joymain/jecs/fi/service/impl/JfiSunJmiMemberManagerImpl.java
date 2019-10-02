
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiSunJmiMember;
import com.joymain.jecs.fi.dao.JfiSunJmiMemberDao;
import com.joymain.jecs.fi.service.JfiSunJmiMemberManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiSunJmiMemberManagerImpl extends BaseManager implements JfiSunJmiMemberManager {
    private JfiSunJmiMemberDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiSunJmiMemberDao(JfiSunJmiMemberDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunJmiMemberManager#getJfiSunJmiMembers(com.joymain.jecs.fi.model.JfiSunJmiMember)
     */
    public List getJfiSunJmiMembers(final JfiSunJmiMember jfiSunJmiMember) {
        return dao.getJfiSunJmiMembers(jfiSunJmiMember);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunJmiMemberManager#getJfiSunJmiMember(String userCode)
     */
    public JfiSunJmiMember getJfiSunJmiMember(final String userCode) {
        return dao.getJfiSunJmiMember(new String(userCode));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunJmiMemberManager#saveJfiSunJmiMember(JfiSunJmiMember jfiSunJmiMember)
     */
    public void saveJfiSunJmiMember(JfiSunJmiMember jfiSunJmiMember) {
        dao.saveJfiSunJmiMember(jfiSunJmiMember);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunJmiMemberManager#removeJfiSunJmiMember(String userCode)
     */
    public void removeJfiSunJmiMember(final String userCode) {
        dao.removeJfiSunJmiMember(new String(userCode));
    }
    //added for getJfiSunJmiMembersByCrm
    public List getJfiSunJmiMembersByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiSunJmiMembersByCrm(crm,pager);
    }
}
