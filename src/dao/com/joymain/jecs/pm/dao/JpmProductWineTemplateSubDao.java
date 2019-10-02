package com.joymain.jecs.pm.dao;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pm.model.JpmProductWineTemplateSub;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmProductWineTemplateSubDao extends Dao
{
    
    /**
     * Retrieves all of the jpmProductWineTemplateSubs
     */
    public List getJpmProductWineTemplateSubs(JpmProductWineTemplateSub jpmProductWineTemplateSub);
    
    /**
     * Gets jpmProductWineTemplateSub's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
     * found.
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
    
    /**
     * Removes a jpmProductWineTemplateSub from the database by subNo
     * 
     * @param subNo the jpmProductWineTemplateSub's subNo
     */
    public void removeJpmProductWineTemplateSub(final String subNo);
    
    // added for getJpmProductWineTemplateSubsByCrm
    public List getJpmProductWineTemplateSubsByCrm(CommonRecord crm, Pager pager);
    
    public void saveJpmProductWineTemplateSubList(
        List<JpmProductWineTemplateSub> jpmProductWineTemplateSubList);
    
    public List<JpmProductWineTemplateSub> getJpmProductWineTemplateSubListByProductTemplateNo(
        Map<String, String> map);
    
    public JpmProductWineTemplateSub getJpmProductWineTemplateSubBySubNo(String subNo);
}
