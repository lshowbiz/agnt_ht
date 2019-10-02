package com.joymain.jecs.pm.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pm.model.JpmProductWineTemplate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmProductWineTemplateDao extends Dao {

    /**
     * Retrieves all of the jpmProductWineTemplates
     */
    public List getJpmProductWineTemplates(JpmProductWineTemplate jpmProductWineTemplate);

    /**
     * Gets jpmProductWineTemplate's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if
     * nothing is found.
     * 
     * @param productTemplateNo the jpmProductWineTemplate's productTemplateNo
     * @return jpmProductWineTemplate populated jpmProductWineTemplate object
     */
    public JpmProductWineTemplate getJpmProductWineTemplate(final Long productTemplateNo);

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
    public void removeJpmProductWineTemplate(final Long productTemplateNo);

    //added for getJpmProductWineTemplatesByCrm
    public List getJpmProductWineTemplatesByCrm(CommonRecord crm, Pager pager);

    public int updateInvalidStatus(final String productTemplateNo, final String invalidSatus);
    
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
