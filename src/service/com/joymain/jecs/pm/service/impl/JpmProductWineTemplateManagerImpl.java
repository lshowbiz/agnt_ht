package com.joymain.jecs.pm.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.pm.dao.JpmProductWineTemplateDao;
import com.joymain.jecs.pm.dao.JpmProductWineTemplateSubDao;
import com.joymain.jecs.pm.model.JpmProductWineTemplate;
import com.joymain.jecs.pm.model.JpmProductWineTemplateSub;
import com.joymain.jecs.pm.service.JpmProductWineTemplateManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JpmProductWineTemplateManagerImpl extends BaseManager implements JpmProductWineTemplateManager {
    private JpmProductWineTemplateDao dao;

    private JpmProductWineTemplateSubDao subDao;

    /**
     * Set the Dao for communication with the data layer.
     * 
     * @param dao
     */
    public void setJpmProductWineTemplateDao(JpmProductWineTemplateDao dao) {
        this.dao = dao;
    }

    public void setJpmProductWineTemplateSubDao(JpmProductWineTemplateSubDao subDao) {
        this.subDao = subDao;
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductWineTemplateManager#getJpmProductWineTemplates(com.joymain.jecs.pm.model.JpmProductWineTemplate)
     */
    public List getJpmProductWineTemplates(final JpmProductWineTemplate jpmProductWineTemplate) {
        return dao.getJpmProductWineTemplates(jpmProductWineTemplate);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductWineTemplateManager#getJpmProductWineTemplate(String
     *      productTemplateNo)
     */
    public JpmProductWineTemplate getJpmProductWineTemplate(final String productTemplateNo) {
        return dao.getJpmProductWineTemplate(new Long(productTemplateNo));
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductWineTemplateManager#saveJpmProductWineTemplate(JpmProductWineTemplate
     *      jpmProductWineTemplate)
     */
    public void saveJpmProductWineTemplate(JpmProductWineTemplate jpmProductWineTemplate) {
        dao.saveJpmProductWineTemplate(jpmProductWineTemplate);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductWineTemplateManager#removeJpmProductWineTemplate(String
     *      productTemplateNo)
     */
    public void removeJpmProductWineTemplate(final String productTemplateNo) {
        dao.removeJpmProductWineTemplate(new Long(productTemplateNo));
    }

    //added for getJpmProductWineTemplatesByCrm
    public List getJpmProductWineTemplatesByCrm(CommonRecord crm, Pager pager) {
        return dao.getJpmProductWineTemplatesByCrm(crm, pager);
    }

    public int updateInvalidStatus(final String productTemplateNo, final String invalidSatus) {
        return dao.updateInvalidStatus(productTemplateNo, invalidSatus);
    }

    @Override
    public void saveJpmProductWineTemplateAndSub(HttpServletRequest request, JpmProductWineTemplate jpmProductWineTemplate) {
        dao.saveJpmProductWineTemplate(jpmProductWineTemplate);
        initAndSaveSub(jpmProductWineTemplate, request);
    }

    private JpmProductWineTemplate initAndSaveSub(JpmProductWineTemplate jpmProductWineTemplate, HttpServletRequest request) {
        String subSize = request.getParameter("jpmProductWineTemplatesubSize");
        int size = new Integer(subSize).intValue();
        List<JpmProductWineTemplateSub> list = new ArrayList<JpmProductWineTemplateSub>();
        for (int i = 0; i < size; i++) {
            JpmProductWineTemplateSub sub = new JpmProductWineTemplateSub();
            String subNo = request.getParameter("jpmProductWineTemplatesub[" + i + "].subNo");
            String subName = request.getParameter("jpmProductWineTemplatesub[" + i + "].subName");
            String productNo = request.getParameter("jpmProductWineTemplatesub[" + i + "].productNo");
            String productName = request.getParameter("jpmProductWineTemplatesub[" + i + "].productName");
            String price = StringUtils.defaultIfEmpty(request.getParameter("jpmProductWineTemplatesub[" + i + "].price"), "0");
            String specification = request.getParameter("jpmProductWineTemplatesub[" + i + "].specification");
            String num = StringUtils.defaultIfEmpty(request.getParameter("jpmProductWineTemplatesub[" + i + "].num"), "0");
            String unit = request.getParameter("jpmProductWineTemplatesub[" + i + "].unit");

            String lossRatio = StringUtils.defaultIfEmpty(request.getParameter("jpmProductWineTemplatesub[" + i + "].lossRatio"), "0");
            String isMainMaterial = StringUtils.defaultIfEmpty(request.getParameter("jpmProductWineTemplatesub[" + i + "].isMainMaterial"), "0");
            String isSendMaterial = StringUtils.defaultIfEmpty(request.getParameter("jpmProductWineTemplatesub[" + i + "].isSendMaterial"), "0");
            String isDelegateOut = StringUtils.defaultIfEmpty(request.getParameter("jpmProductWineTemplatesub[" + i + "].isDelegateOut"), "0");
            String isFeatureItem = StringUtils.defaultIfEmpty(request.getParameter("jpmProductWineTemplatesub[" + i + "].isFeatureItem"), "0");

            String isMustSelected = StringUtils.defaultIfEmpty(request.getParameter("jpmProductWineTemplatesub[" + i + "].isMustSelected"), "0");
            String isDefaultSelected = StringUtils.defaultIfEmpty(request.getParameter("jpmProductWineTemplatesub[" + i + "].isDefaultSelected"), "0");
            String isNumChange = StringUtils.defaultIfEmpty(request.getParameter("jpmProductWineTemplatesub[" + i + "].isNumChange"), "0");
            String numMin = StringUtils.defaultIfEmpty(request.getParameter("jpmProductWineTemplatesub[" + i + "].numMin"), "0");
            String numMax = StringUtils.defaultIfEmpty(request.getParameter("jpmProductWineTemplatesub[" + i + "].numMax"), "0");

            if (StringUtils.isNotEmpty(subNo)) {
                sub.setSubNo(subNo);
            }
            sub.setSubName(subName);
            sub.setProductNo(productNo);
            sub.setProductName(productName);
            sub.setPrice(new BigDecimal(price));
            sub.setSpecification(specification);

            sub.setNum(new BigDecimal(num));
            sub.setUnit(unit);
            sub.setLossRatio(new BigDecimal(lossRatio));
            sub.setIsMainMaterial(isMainMaterial);
            sub.setIsSendMaterial(isSendMaterial);

            sub.setIsDelegateOut(isDelegateOut);
            sub.setIsFeatureItem(isFeatureItem);
            sub.setIsMustSelected(isMustSelected);
            sub.setIsDefaultSelected(isDefaultSelected);
            sub.setIsNumChange(isNumChange);
            sub.setNumMin(new BigDecimal(numMin));

            sub.setNumMax(new BigDecimal(numMax));
            sub.setIsInvalid(jpmProductWineTemplate.getIsInvalid());

            sub.setJpmProductWineTemplate(jpmProductWineTemplate);

            list.add(sub);
        }
        subDao.saveJpmProductWineTemplateSubList(list);
        return jpmProductWineTemplate;
    }
    
    @Override
    public List<JpmProductWineTemplate> getTemplateList()
    {
        return dao.getTemplateList();
    }

    /**
     * 根据商品编码获取对应的模版信息
     * @param map
     * @return
     */
    @Override
    public List<JpmProductWineTemplate> getTemplateListByProductNo(String productNo)
    {
        return dao.getTemplateListByProductNo(productNo);
    }
}
