package com.joymain.jecs.pm.service.impl;

import java.util.List;
import com.joymain.jecs.pm.dao.JpmWineTemplatePictureDao;
import com.joymain.jecs.pm.model.JpmWineTemplatePicture;
import com.joymain.jecs.pm.service.JpmWineTemplatePictureManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JpmWineTemplatePictureManagerImpl extends BaseManager implements JpmWineTemplatePictureManager {
    private JpmWineTemplatePictureDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * 
     * @param dao
     */
    public void setJpmWineTemplatePictureDao(JpmWineTemplatePictureDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmWineTemplatePictureManager#getJpmWineTemplatePictures(com.joymain.jecs.pm.model.JpmWineTemplatePicture)
     */
    public List getJpmWineTemplatePictures(final JpmWineTemplatePicture jpmWineTemplatePicture) {
        return dao.getJpmWineTemplatePictures(jpmWineTemplatePicture);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmWineTemplatePictureManager#getJpmWineTemplatePicture(String
     *      idf)
     */
    public JpmWineTemplatePicture getJpmWineTemplatePicture(final String idf) {
        return dao.getJpmWineTemplatePicture(new Long(idf));
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmWineTemplatePictureManager#saveJpmWineTemplatePicture(JpmWineTemplatePicture
     *      jpmWineTemplatePicture)
     */
    public void saveJpmWineTemplatePicture(JpmWineTemplatePicture jpmWineTemplatePicture) {
        dao.saveJpmWineTemplatePicture(jpmWineTemplatePicture);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmWineTemplatePictureManager#removeJpmWineTemplatePicture(String
     *      idf)
     */
    public void removeJpmWineTemplatePicture(final String idf) {
        dao.removeJpmWineTemplatePicture(new Long(idf));
    }

    //added for getJpmWineTemplatePicturesByCrm
    public List getJpmWineTemplatePicturesByCrm(CommonRecord crm, Pager pager) {
        return dao.getJpmWineTemplatePicturesByCrm(crm, pager);
    }
    
    /**
     * 根据配件id查询图片列表
     * @param subNo
     * @return
     */
    @Override
    public List<JpmWineTemplatePicture> getJpmWineTemplatePictureListBySubNo(String subNo)
    {
        return dao.getJpmWineTemplatePictureListBySubNo(subNo);
    }
}
