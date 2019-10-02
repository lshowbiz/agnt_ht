
package com.joymain.jecs.pm.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.joymain.jecs.pm.dao.JpmProductSaleTeamTypeDao;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.pm.service.JpmProductSaleTeamTypeManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpmProductSaleTeamTypeManagerImpl extends BaseManager implements JpmProductSaleTeamTypeManager {
    private JpmProductSaleTeamTypeDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpmProductSaleTeamTypeDao(JpmProductSaleTeamTypeDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductSaleTeamTypeManager#getJpmProductSaleTeamTypes(com.joymain.jecs.pm.model.JpmProductSaleTeamType)
     */
    public List getJpmProductSaleTeamTypes(final JpmProductSaleTeamType jpmProductSaleTeamType) {
        return dao.getJpmProductSaleTeamTypes(jpmProductSaleTeamType);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductSaleTeamTypeManager#getJpmProductSaleTeamType(String pttId)
     */
    public JpmProductSaleTeamType getJpmProductSaleTeamType(final String pttId) {
        return dao.getJpmProductSaleTeamType(new Long(pttId));
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductSaleTeamTypeManager#saveJpmProductSaleTeamType(JpmProductSaleTeamType jpmProductSaleTeamType)
     */
    public void saveJpmProductSaleTeamType(JpmProductSaleTeamType jpmProductSaleTeamType) {
        dao.saveJpmProductSaleTeamType(jpmProductSaleTeamType);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductSaleTeamTypeManager#removeJpmProductSaleTeamType(String pttId)
     */
    public void removeJpmProductSaleTeamType(final String pttId) {
        dao.removeJpmProductSaleTeamType(new Long(pttId));
    }
    //added for getJpmProductSaleTeamTypesByCrm
    public List getJpmProductSaleTeamTypesByCrm(CommonRecord crm, Pager pager){
	return dao.getJpmProductSaleTeamTypesByCrm(crm,pager);
    }

    
    /**
     * @param crm：查询条件
     * @param type：查询类型    0：新增   1：修改
     * @return：返回是否已经有重复的数据
     */
	public boolean isExist(CommonRecord crm, String type) {
		return dao.isExist(crm,type);
	}

	 /**
     * 得到指定类型编码的全部数据
     * @param listCode
     * @return
     */
	public List getJsysListValues(String listCode,String companyCode) {
		return dao.getJsysListValues(listCode,companyCode);
	}

	/**
     * 通过商品编号和
     * @param companyCode：分区
     * @param productNo：商品编号
     * @param teamCode：团队编号
     * @return：返回商品的订单类型字符串集合
     */
	public String getOrderTypeStr(String companyCode, String uniNo,	String teamCode) {
		return dao.getOrderTypeStr(companyCode,uniNo,teamCode);
	}

	/**
     * 获得指定商品团队类型对象
     * @param companyCode
     * @param productNo
     * @return
     */
	public JpmProductSaleNew getJpmProductSaleNew(String companyCode,
			String productNo) {
		return dao.getJpmProductSaleNew(companyCode,productNo);
	}
	
	/**
     * 获得指定商品对象
     * @param companyCode
     * @param productNo
     * @return
     */
	public List getJpmProductSaleTeamType(String companyCode,
			String productNo) {
		return dao.getJpmProductSaleTeamType(companyCode,productNo);
	}
	
	/**
     * 批量审核图片
     * @param uniNoStr:图片编码字符串：用逗号隔开
     * @return
     */
    public int batchAuditProductTeamTypes(String uniNoStr,String status){
    	return dao.batchAuditProductTeamTypes(uniNoStr,status);
    }
    
    /**
     * 根据分区，订单类型，团队标志，商品编码获取商品价格
     * @author gw 2015-08-11
     * @param companyCode 公司编号
     * @param orderType 订单类型
     * @param teamCode 团队标志
     * @param productNo 商品编码
     * @return BigDecimal 商品价格
     */
	public BigDecimal getSubProductNoPrice(String companyCode, String orderType,String teamCode, String productNo) {
		return dao.getSubProductNoPrice(companyCode,orderType,teamCode,productNo);
	}
    
}
