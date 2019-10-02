
package com.joymain.jecs.pm.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pm.model.JpmProductSaleImage;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmProductSaleImageDao extends Dao {

    /**
     * Retrieves all of the jpmProductSaleImages
     */
    public List getJpmProductSaleImages(JpmProductSaleImage jpmProductSaleImage);

    /**
     * Gets jpmProductSaleImage's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param imageId the jpmProductSaleImage's imageId
     * @return jpmProductSaleImage populated jpmProductSaleImage object
     */
    public JpmProductSaleImage getJpmProductSaleImage(final Long imageId);

    /**
     * Saves a jpmProductSaleImage's information
     * @param jpmProductSaleImage the object to be saved
     */    
    public void saveJpmProductSaleImage(JpmProductSaleImage jpmProductSaleImage);

    /**
     * Removes a jpmProductSaleImage from the database by imageId
     * @param imageId the jpmProductSaleImage's imageId
     */
    public void removeJpmProductSaleImage(final Long imageId);
    //added for getJpmProductSaleImagesByCrm
    public List getJpmProductSaleImagesByCrm(CommonRecord crm, Pager pager);
    
    /**
     * 批量审核图片
     * @param uniNoStr:图片编码字符串：用逗号隔开
     * @return
     */
    public int batchAuditProductNews(String uniNoStr,String status);
}

