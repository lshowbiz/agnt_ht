package com.joymain.jecs.pm.service;

import java.util.List;

import com.joymain.jecs.pm.model.JpmWineTemplatePicture;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmWineTemplatePictureManager extends Manager {
    /**
     * Retrieves all of the jpmWineTemplatePictures
     */
    public List getJpmWineTemplatePictures(JpmWineTemplatePicture jpmWineTemplatePicture);

    /**
     * Gets jpmWineTemplatePicture's information based on idf.
     * 
     * @param idf the jpmWineTemplatePicture's idf
     * @return jpmWineTemplatePicture populated jpmWineTemplatePicture object
     */
    public JpmWineTemplatePicture getJpmWineTemplatePicture(final String idf);

    /**
     * Saves a jpmWineTemplatePicture's information
     * 
     * @param jpmWineTemplatePicture the object to be saved
     */
    public void saveJpmWineTemplatePicture(JpmWineTemplatePicture jpmWineTemplatePicture);

    /**
     * Removes a jpmWineTemplatePicture from the database by idf
     * 
     * @param idf the jpmWineTemplatePicture's idf
     */
    public void removeJpmWineTemplatePicture(final String idf);

    //added for getJpmWineTemplatePicturesByCrm
    public List getJpmWineTemplatePicturesByCrm(CommonRecord crm, Pager pager);
    
    /**
     * 根据配件id查询图片列表
     * @param subNo
     * @return
     */
    public List<JpmWineTemplatePicture> getJpmWineTemplatePictureListBySubNo(String subNo);
}
