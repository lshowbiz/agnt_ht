package com.joymain.jecs.pm.service;

import java.util.List;
import java.util.Map;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pm.model.JpmProductWineTemplateSub;
import com.joymain.jecs.pm.dao.JpmProductWineTemplateSubDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmProductWineTemplateSubManager extends Manager
{
    /**
     * Retrieves all of the jpmProductWineTemplateSubs
     */
    public List getJpmProductWineTemplateSubs(JpmProductWineTemplateSub jpmProductWineTemplateSub);
    
    /**
     * Gets jpmProductWineTemplateSub's information based on subNo.
     * 
     * @param subNo the jpmProductWineTemplateSub's subNo
     * @return jpmProductWineTemplateSub populated jpmProductWineTemplateSub
     *         object
     */
    public JpmProductWineTemplateSub getJpmProductWineTemplateSub(final String subNo);
    
    /**
     * Saves a jpmProductWineTemplateSub's information
     * 
     * @param jpmProductWineTemplateSub the object to be saved
     */
    public void saveJpmProductWineTemplateSub(JpmProductWineTemplateSub jpmProductWineTemplateSub);
    
    public void saveJpmProductWineTemplateSubList(
        List<JpmProductWineTemplateSub> jpmProductWineTemplateSubList);
    
    /**
     * Removes a jpmProductWineTemplateSub from the database by subNo
     * 
     * @param subNo the jpmProductWineTemplateSub's subNo
     */
    public void removeJpmProductWineTemplateSub(final String subNo);
    
    // added for getJpmProductWineTemplateSubsByCrm
    public List getJpmProductWineTemplateSubsByCrm(CommonRecord crm, Pager pager);
    
    public List<JpmProductWineTemplateSub> getJpmProductWineTemplateSubListByProductTemplateNo(
        Map<String, String> map);
    
    public JpmProductWineTemplateSub getJpmProductWineTemplateSubBySubNo(String subNo);
}
