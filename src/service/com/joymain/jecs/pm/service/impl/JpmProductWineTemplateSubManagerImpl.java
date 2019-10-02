package com.joymain.jecs.pm.service.impl;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pm.model.JpmProductWineTemplateSub;
import com.joymain.jecs.pm.dao.JpmProductWineTemplateSubDao;
import com.joymain.jecs.pm.service.JpmProductWineTemplateSubManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JpmProductWineTemplateSubManagerImpl extends BaseManager implements
    JpmProductWineTemplateSubManager
{
    private JpmProductWineTemplateSubDao dao;
    
    /**
     * Set the Dao for communication with the data layer.
     * 
     * @param dao
     */
    public void setJpmProductWineTemplateSubDao(JpmProductWineTemplateSubDao dao)
    {
        this.dao = dao;
    }
    
    /**
     * @see com.joymain.jecs.pm.service.JpmProductWineTemplateSubManager#getJpmProductWineTemplateSubs(com.joymain.jecs.pm.model.JpmProductWineTemplateSub)
     */
    public List getJpmProductWineTemplateSubs(
        final JpmProductWineTemplateSub jpmProductWineTemplateSub)
    {
        return dao.getJpmProductWineTemplateSubs(jpmProductWineTemplateSub);
    }
    
    /**
     * @see com.joymain.jecs.pm.service.JpmProductWineTemplateSubManager#getJpmProductWineTemplateSub(String
     *      subNo)
     */
    public JpmProductWineTemplateSub getJpmProductWineTemplateSub(final String subNo)
    {
        return dao.getJpmProductWineTemplateSub(new String(subNo));
    }
    
    /**
     * @see com.joymain.jecs.pm.service.JpmProductWineTemplateSubManager#saveJpmProductWineTemplateSub(JpmProductWineTemplateSub
     *      jpmProductWineTemplateSub)
     */
    public void saveJpmProductWineTemplateSub(JpmProductWineTemplateSub jpmProductWineTemplateSub)
    {
        dao.saveJpmProductWineTemplateSub(jpmProductWineTemplateSub);
    }
    
    /**
     * @see com.joymain.jecs.pm.service.JpmProductWineTemplateSubManager#removeJpmProductWineTemplateSub(String
     *      subNo)
     */
    public void removeJpmProductWineTemplateSub(final String subNo)
    {
        dao.removeJpmProductWineTemplateSub(new String(subNo));
    }
    
    // added for getJpmProductWineTemplateSubsByCrm
    public List getJpmProductWineTemplateSubsByCrm(CommonRecord crm, Pager pager)
    {
        return dao.getJpmProductWineTemplateSubsByCrm(crm, pager);
    }
    
    @Override
    public void saveJpmProductWineTemplateSubList(
        List<JpmProductWineTemplateSub> jpmProductWineTemplateSubList)
    {
        dao.saveJpmProductWineTemplateSubList(jpmProductWineTemplateSubList);
    }
    
    @Override
    public List<JpmProductWineTemplateSub> getJpmProductWineTemplateSubListByProductTemplateNo(
        Map<String, String> map)
    {
        return dao.getJpmProductWineTemplateSubListByProductTemplateNo(map);
    }
    
    @Override
    public JpmProductWineTemplateSub getJpmProductWineTemplateSubBySubNo(String subNo)
    {
        return dao.getJpmProductWineTemplateSubBySubNo(subNo);
    }
}
