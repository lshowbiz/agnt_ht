
package com.joymain.jecs.pm.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pm.model.JpmProductSaleImage;
import com.joymain.jecs.pm.dao.JpmProductSaleImageDao;
import com.joymain.jecs.pm.service.JpmProductSaleImageManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpmProductSaleImageManagerImpl extends BaseManager implements JpmProductSaleImageManager {
    private JpmProductSaleImageDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpmProductSaleImageDao(JpmProductSaleImageDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductSaleImageManager#getJpmProductSaleImages(com.joymain.jecs.pm.model.JpmProductSaleImage)
     */
    public List getJpmProductSaleImages(final JpmProductSaleImage jpmProductSaleImage) {
        return dao.getJpmProductSaleImages(jpmProductSaleImage);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductSaleImageManager#getJpmProductSaleImage(String imageId)
     */
    public JpmProductSaleImage getJpmProductSaleImage(final String imageId) {
        return dao.getJpmProductSaleImage(new Long(imageId));
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductSaleImageManager#saveJpmProductSaleImage(JpmProductSaleImage jpmProductSaleImage)
     */
    public void saveJpmProductSaleImage(JpmProductSaleImage jpmProductSaleImage) {
        dao.saveJpmProductSaleImage(jpmProductSaleImage);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductSaleImageManager#removeJpmProductSaleImage(String imageId)
     */
    public void removeJpmProductSaleImage(final String imageId) {
        dao.removeJpmProductSaleImage(new Long(imageId));
    }
    //added for getJpmProductSaleImagesByCrm
    public List getJpmProductSaleImagesByCrm(CommonRecord crm, Pager pager){
	return dao.getJpmProductSaleImagesByCrm(crm,pager);
    }
    
    /**
     * 批量审核团队订单类型
     * @param uniNoStr:图片编码字符串：用逗号隔开
     * @return
     */
    public int batchAuditProductNews(String uniNoStr,String status){
    	return dao.batchAuditProductNews(uniNoStr,status);
    }
}
