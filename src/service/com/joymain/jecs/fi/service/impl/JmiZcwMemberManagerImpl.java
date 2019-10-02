
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JmiZcwMember;
import com.joymain.jecs.fi.dao.JmiZcwMemberDao;
import com.joymain.jecs.fi.service.JmiZcwMemberManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JmiZcwMemberManagerImpl extends BaseManager implements JmiZcwMemberManager {
    private JmiZcwMemberDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiZcwMemberDao(JmiZcwMemberDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JmiZcwMemberManager#getJmiZcwMembers(com.joymain.jecs.fi.model.JmiZcwMember)
     */
    public List getJmiZcwMembers(final JmiZcwMember jmiZcwMember) {
        return dao.getJmiZcwMembers(jmiZcwMember);
    }

    /**
     * @see com.joymain.jecs.fi.service.JmiZcwMemberManager#getJmiZcwMember(String zcwId)
     */
    public JmiZcwMember getJmiZcwMember(final String zcwId) {
        return dao.getJmiZcwMember(new BigDecimal(zcwId));
    }

    /**
     * @see com.joymain.jecs.fi.service.JmiZcwMemberManager#saveJmiZcwMember(JmiZcwMember jmiZcwMember)
     */
    public void saveJmiZcwMember(JmiZcwMember jmiZcwMember) {
        dao.saveJmiZcwMember(jmiZcwMember);
    }

    /**
     * @see com.joymain.jecs.fi.service.JmiZcwMemberManager#removeJmiZcwMember(String zcwId)
     */
    public void removeJmiZcwMember(final String zcwId) {
        dao.removeJmiZcwMember(new BigDecimal(zcwId));
    }
    //added for getJmiZcwMembersByCrm
    public List getJmiZcwMembersByCrm(CommonRecord crm, Pager pager){
	return dao.getJmiZcwMembersByCrm(crm,pager);
    }
}
