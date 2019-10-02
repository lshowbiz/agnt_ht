
package com.joymain.jecs.pm.service;

import java.math.BigDecimal;
import java.util.List;

import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpmProductSaleTeamTypeManager extends Manager {
    /**
     * Retrieves all of the jpmProductSaleTeamTypes
     */
    public List getJpmProductSaleTeamTypes(JpmProductSaleTeamType jpmProductSaleTeamType);

    /**
     * Gets jpmProductSaleTeamType's information based on pttId.
     * @param pttId the jpmProductSaleTeamType's pttId
     * @return jpmProductSaleTeamType populated jpmProductSaleTeamType object
     */
    public JpmProductSaleTeamType getJpmProductSaleTeamType(final String pttId);

    /**
     * Saves a jpmProductSaleTeamType's information
     * @param jpmProductSaleTeamType the object to be saved
     */
    public void saveJpmProductSaleTeamType(JpmProductSaleTeamType jpmProductSaleTeamType);

    /**
     * Removes a jpmProductSaleTeamType from the database by pttId
     * @param pttId the jpmProductSaleTeamType's pttId
     */
    public void removeJpmProductSaleTeamType(final String pttId);
    //added for getJpmProductSaleTeamTypesByCrm
    public List getJpmProductSaleTeamTypesByCrm(CommonRecord crm, Pager pager);
    
    /**
     * @param crm：查询条件
     * @param type：查询类型    0：新增   1：修改
     * @return：返回是否已经有重复的数据
     */
    public boolean isExist(CommonRecord crm,String type);
    
    /**
     * 得到指定类型编码的全部数据
     * @param listCode
     * @return
     */
    public List getJsysListValues(String listCode,String companyCode);
    
    /**
     * 通过商品编号和
     * @param companyCode：分区
     * @param productNo：商品编号
     * @param teamCode：团队编号
     * @return：返回商品的订单类型字符串集合
     */
    public String getOrderTypeStr(String companyCode,String uniNo,String teamCode);
    
    /**
     * 获得指定商品团队类型对象
     * @param companyCode
     * @param productNo
     * @return
     */
    public JpmProductSaleNew getJpmProductSaleNew(String companyCode,String productNo);
    
    /**
     * 获得指定商品团队类型对象
     * @param companyCode
     * @param productNo
     * @return
     */
    public List getJpmProductSaleTeamType(String companyCode,String productNo);
    
    /**
     * 批量审核图片
     * @param uniNoStr:图片编码字符串：用逗号隔开
     * @return
     */
    public int batchAuditProductTeamTypes(String uniNoStr,String status);

    /**
     * 根据分区，订单类型，团队标志，商品编码获取商品价格
     * @author fx 2015-08-11
     * @param companyCode 公司编号
     * @param orderType 订单类型
     * @param teamCode 团队标志
     * @param productNo 商品编码
     * @return BigDecimal 商品价格
     */
	public BigDecimal getSubProductNoPrice(String companyCode,
			String orderType, String teamCode, String subProductNo);
}

