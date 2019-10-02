/*
 * 文件名：  JpoWineMemberOrderManagerImpl.java 2013-12-18
 * 版权：    Copyright 2000-2013 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：    (Initialize)
 * 作者：    Administrator
 * 时间：    2013-12-18
 * 版本号：  RBT_CNCMSXV5.0D605SP33
 * 评审人:    
 * 评审时间:
 */
package com.joymain.jecs.pm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.joymain.jecs.pm.dao.JpoWineMemberOrderDao;
import com.joymain.jecs.pm.model.JpmMemberConfig;
import com.joymain.jecs.pm.service.JpoWineMemberOrderManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.Pager;

 /**
 * <p>Title: (Initialize)</p>
 * <p>Description: (Initialize)</p>
 * @author  jfoy
 * @version  [RBT_CNCMSXV5.0D605SP33, 2013-12-18]
 * @since SP33
 */
public class JpoWineMemberOrderManagerImpl extends BaseManager implements JpoWineMemberOrderManager
{
    private JpoWineMemberOrderDao dao;
    
    public void setJpoWineMemberOrderDao(JpoWineMemberOrderDao dao)
    {
        this.dao = dao;
    }

    /**
     * 根据条件查询会员的酒业订单
     */
    @Override
    public List<JpoMemberOrder> getWineOrderByParam(Map<String, String> map,Pager pager)
    {
        List<JpoMemberOrder> orderList = dao.getWineOrderByParam(map,pager);
        List pttIdList = dao.getWineOrderList();
        // 转换list
        Map idMap = new HashMap();
        for (int i = 0; i < pttIdList.size(); i++)
        {
            Map pttMap = (Map) pttIdList.get(i);
            idMap.put(i, pttMap.get("ptt_id").toString());
        }
        
        // 根据商品id查询到商品唯一编码
        List<JpoMemberOrder> wineOrderList = null;
        if (orderList != null && orderList.size() > 0)
        {
            wineOrderList = new ArrayList<JpoMemberOrder>();
            // 过滤含酒业商品的订单列表
            Iterator<JpoMemberOrder> it = orderList.iterator();
            while (it.hasNext())
            {
                JpoMemberOrder jpoMemberOrder = it.next();
                Integer productAmount = 0;
                if (CollectionUtils.isNotEmpty(jpoMemberOrder.getJpoMemberOrderList()))
                {
                    Iterator itList = jpoMemberOrder.getJpoMemberOrderList().iterator();
                    while(itList.hasNext())
                    {
                        JpoMemberOrderList jpoMemberOrderDetail = (JpoMemberOrderList) itList.next();
                        // 校验数据中是否包含酒业商品信息
                        if (idMap.containsValue(jpoMemberOrderDetail.getJpmProductSaleTeamType()
                            .getPttId()
                            .toString()))
                        {
                            // 商品总数量
                            if (null != jpoMemberOrder.getProductAmount())
                            {
                                productAmount = jpoMemberOrder.getProductAmount();
                            }
                            productAmount += jpoMemberOrderDetail.getQty();
                            jpoMemberOrder.setProductAmount(productAmount);
                            if (!wineOrderList.contains(jpoMemberOrder))
                            {
                                wineOrderList.add(jpoMemberOrder);
                            }
                        }
                    }
                }
                // 商品已配置数量
                for (JpoMemberOrder order : wineOrderList)
                {
                    // 通过订单表的订单号查询配置表中的已配置数量
                    Map<String, Long> configAmountMap = new HashMap<String, Long>();
                    configAmountMap.put("moId", order.getMoId());
                    order.setProductConfigAmount(getWineProductConfigCountByOrderId(configAmountMap).intValue());
                    // 根据订单id查询商品信息
                    Iterator itOrder = order.getJpoMemberOrderList().iterator();
                    while(itOrder.hasNext())
                    {
                        JpoMemberOrderList product = (JpoMemberOrderList) itOrder.next();
                        Map<String, Long> molIdMap = new HashMap<String, Long>();
                        molIdMap.put("molId", product.getMolId());
                        product.setProductConfigAmount(getWineProductConfigCountByMolId(molIdMap).intValue());
                    }
                }
            }
        }
        return wineOrderList;
    }
    
    /**
     * 根据订单编号查询订单下商品已配置总数量
     * 
     * @param map
     * @return
     */
    @Override
    public Long getWineProductConfigCountByOrderId(Map<String, Long> map)
    {
        return dao.getWineProductConfigCountByOrderId(map);
    }
    
    /**
     * 酒业商品信息查询
     */
    @Override
    public List<JpoMemberOrderList> getWineProductOrderByParam(Map<String, String> map)
    {
        List<JpoMemberOrderList> orderList = dao.getWineProductOrderByParam(map);
        // 商品已配置数量
        for (JpoMemberOrderList jpoMemberOrderList : orderList)
        {
            // 根据商品编号查询已配置数量
            Map<String, Long> molIdMap = new HashMap<String, Long>();
            molIdMap.put("molId", jpoMemberOrderList.getMolId());
            jpoMemberOrderList.setProductConfigAmount(getWineProductConfigCountByMolId(molIdMap).intValue());
        }
        return orderList;
    }
    
    /**
     * 根据订单商品编号查询订单下商品已配置总数量
     * 
     * @param map
     * @return
     */
    @Override
    public Long getWineProductConfigCountByMolId(Map<String, Long> map)
    {
        return dao.getWineProductConfigCountByMolId(map);
    }
    
    /**
     * 根据订单商品编号查询该商品的已配置列表
     * 
     * @param map
     * @return
     */
    @Override
    public List<JpmMemberConfig> getWineConfigByMolId(Map<String, Long> map,Pager pager)
    {
        return dao.getWineConfigByMolId(map,pager);
    }
    
    /**
     * 根据商品id查询商品编码
     * 
     * @param map
     * @return
     */
    @Override
    public String getProductNoByProductId(Map<String, Long> map)
    {
        return dao.getProductNoByProductId(map);
    }
    
}

