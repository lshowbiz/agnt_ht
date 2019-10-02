
package com.joymain.jecs.bd.service.impl;

import java.util.List;

import com.joymain.jecs.bd.dao.JbdMemberCongrationsDao;
import com.joymain.jecs.bd.model.JbdMemberCongrations;
import com.joymain.jecs.bd.service.JbdMemberCongrationsManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdMemberCongrationsManagerImpl extends BaseManager implements JbdMemberCongrationsManager {
   
    private JbdMemberCongrationsDao jbdMemberCongrationsDao;

    public void setJbdMemberCongrationsDao(
            JbdMemberCongrationsDao jbdMemberCongrationsDao)
    {
        super.setDao(jbdMemberCongrationsDao);
        this.jbdMemberCongrationsDao = jbdMemberCongrationsDao;
    }

    @Override
    public List getJbdMemberCongrationsByCrm(CommonRecord crm, Pager pager)
    {
        return jbdMemberCongrationsDao.getJbdMemberCongrationsByCrm(crm, pager);
    }

    @Override
    public void saveImportJbdMemberCongrations(
            List<JbdMemberCongrations> congrationsLists)
    {
        for(JbdMemberCongrations congrations : congrationsLists) {
            jbdMemberCongrationsDao.saveObject(congrations);
        }
    }

}
