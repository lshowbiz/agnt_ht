
package com.joymain.jecs.mi.service.impl;

import java.util.List;

import com.joymain.jecs.mi.dao.JmiRefStateLogDao;
import com.joymain.jecs.mi.service.JmiRefStateLogManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JmiRefStateLogManagerImpl extends BaseManager implements JmiRefStateLogManager {
    private JmiRefStateLogDao jmiRefStateLogDao;

    public void setJmiRefStateLogDao(JmiRefStateLogDao jmiRefStateLogDao) {
        super.setDao(jmiRefStateLogDao);
        this.jmiRefStateLogDao = jmiRefStateLogDao;
    }

    @Override
    public List getJmiRefStateLogsByCrm(CommonRecord crm, Pager pager)
    {
        return this.jmiRefStateLogDao.getJmiRefStateLogsByCrm(crm,pager);
    }
  
}
