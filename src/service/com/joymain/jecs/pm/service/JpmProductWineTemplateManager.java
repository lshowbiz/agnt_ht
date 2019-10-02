package com.joymain.jecs.pm.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pm.model.JpmProductWineTemplate;
import com.joymain.jecs.pm.dao.JpmProductWineTemplateDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmProductWineTemplateManager extends Manager {
    /**
     * Retrieves all of the jpmProductWineTemplates
     */
    public List getJpmProductWineTemplates(JpmProductWineTemplate jpmProductWineTemplate);

    /**
     * Gets jpmProductWineTemplate's information based on productTemplateNo.
     * 
     * @param productTemplateNo the jpmProductWineTemplate's productTemplateNo
     * @return jpmProductWineTemplate populated jpmProductWineTemplate object
     */
    public JpmProductWineTemplate getJpmProductWineTemplate(final String productTemplateNo);

    /**
     * Saves a jpmProductWineTemplate's information
     * 
     * @param jpmProductWineTemplate the object to be saved
     */
    public void saveJpmProductWineTemplate(JpmProductWineTemplate jpmProductWineTemplate);

    /**
     * Removes a jpmProductWineTemplate from the database by productTemplateNo
     * 
     * @param productTemplateNo the jpmProductWineTemplate's productTemplateNo
     */
    public void removeJpmProductWineTemplate(final String productTemplateNo);

    //added for getJpmProductWineTemplatesByCrm
    public List getJpmProductWineTemplatesByCrm(CommonRecord crm, Pager pager);

    public int updateInvalidStatus(final String productTemplateNo, final String invalidSatus);

    public void saveJpmProductWineTemplateAndSub(HttpServletRequest request, JpmProductWineTemplate jpmProductWineTemplate);
    
    /**
     * 查询出所有可用模版
     * @param map
     * @return
     */
    public List<JpmProductWineTemplate> getTemplateList();
    
    /**
     * 根据商品编码获取对应的模版信息
     * @param map
     * @return
     */
    public List<JpmProductWineTemplate> getTemplateListByProductNo(String productNo);
}
