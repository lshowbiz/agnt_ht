
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdLogisticsBaseDetail;
import com.joymain.jecs.pd.model.PdLogisticsBaseNum;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdLogisticsBaseDetailDao extends Dao {

    /**
     * Retrieves all of the pdLogisticsBaseDetails
     */
    public List getPdLogisticsBaseDetails(PdLogisticsBaseDetail pdLogisticsBaseDetail);

    /**
     * Gets pdLogisticsBaseDetail's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param detailId the pdLogisticsBaseDetail's detailId
     * @return pdLogisticsBaseDetail populated pdLogisticsBaseDetail object
     */
    public PdLogisticsBaseDetail getPdLogisticsBaseDetail(final BigDecimal detailId);

    /**
     * Saves a pdLogisticsBaseDetail's information
     * @param pdLogisticsBaseDetail the object to be saved
     */    
    public void savePdLogisticsBaseDetail(PdLogisticsBaseDetail pdLogisticsBaseDetail);

    /**
     * Removes a pdLogisticsBaseDetail from the database by detailId
     * @param detailId the pdLogisticsBaseDetail's detailId
     */
    public void removePdLogisticsBaseDetail(final BigDecimal detailId);
    //added for getPdLogisticsBaseDetailsByCrm
    public List getPdLogisticsBaseDetailsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * 根据EC商品编码和物流状态接口物流信息表主键获取对象PdLogisticsBaseDetail
     * @author 2014-12-02
     * @param  numId 物流状态接口物流信息表主键
     * @param productNo
     * @return pdLogisticsBaseDetail
     */
    public PdLogisticsBaseDetail getPdLogisticsBaseDetailByInter(String numId,String productNo,BigDecimal price,String qty,String combination_product_no);

    /**
     * 
     * @author gw 2015-01-26
     * @param pdLogisticsBaseNum
     * @return
     */
	public List<PdLogisticsBaseDetail> getPdLogisticsBaseDetailByInterList(PdLogisticsBaseNum pdLogisticsBaseNum);
    
    
}

