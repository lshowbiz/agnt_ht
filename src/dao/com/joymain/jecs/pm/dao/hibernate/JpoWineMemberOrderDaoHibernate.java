/*
 * 文件名：  JpoWineMemberOrderDaoHibernate.java 2013-12-18
 * 版权：    Copyright 2000-2013 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：    (Initialize)
 * 作者：    Administrator
 * 时间：    2013-12-18
 * 版本号：  RBT_CNCMSXV5.0D605SP33
 * 评审人:    
 * 评审时间:
 */
package com.joymain.jecs.pm.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.dao.JpoWineMemberOrderDao;
import com.joymain.jecs.pm.model.JpmMemberConfig;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.util.data.Pager;

 /**
 * <p>Title: (Initialize)</p>
 * <p>Description: (Initialize)</p>
 * @author  jfoy
 * @version  [RBT_CNCMSXV5.0D605SP33, 2013-12-18]
 * @since SP33
 */
public class JpoWineMemberOrderDaoHibernate extends BaseDaoHibernate implements
    JpoWineMemberOrderDao
{
    
    @Override
    public List<JpoMemberOrder> getWineOrderByParam(Map<String, String> map,Pager pager)
    {
        //po.sysUser.userCode='"+map.get("userCode")+"' and po.sysUser.companyCode='"+map.get("companyCode")+"'
        StringBuffer hql=new StringBuffer("from JpoMemberOrder po where po.status = '2'");    
        String memberOrderNo=map.get("memberOrderNo");//订单编号
        String configStatus=map.get("status");//订单状态
        String orderType=map.get("orderType");//订单类型
        String isShipments=map.get("isShipments");//发货状态
        String userCode = map.get("userCode");
        if(!StringUtils.isEmpty(memberOrderNo))
        {
            hql.append("  and po.memberOrderNo='"+memberOrderNo+"'");
        }
        if(StringUtils.isNotEmpty(userCode))
        {
            hql.append("and po.sysUser.userCode='"+userCode+"'");
        }
        if(!StringUtils.isEmpty(orderType))
        {
            hql.append(" and po.orderType='"+orderType+"'");
        }
        if(!StringUtils.isEmpty(isShipments))
        {
            if(!"-1".equals(isShipments))
            {
              hql.append(" and po.isShipments='"+isShipments+"'");
            }
        }
        if(StringUtils.isNotEmpty(configStatus))
        {
            if(!"-1".equals(configStatus))
            {
                //配置表中的配置状态删除，在订单表中增加
                hql.append(" and po.configStatus = '"+ configStatus +"'");
            }
        }
        hql.append(" and po.moId in (");
        hql.append(" select jmo.jpoMemberOrder.moId from JpoMemberOrderList jmo where jmo.jpmProductSaleTeamType.pttId in (");
        hql.append("select jps.pttId from JpmProductSaleTeamType jps where jps.uniNo in (");
        hql.append("select jpn.uniNo from JpmProductSaleNew jpn where jpn.jpmProduct.productNo in ('P1505010101CN0','P1503010101CN0')");
        hql.append(")))");
        hql.append(" order by po.moId desc");
        return this.findObjectsByHqlQuery(hql.toString(), pager);
    }
    
    @Override
    public List getWineOrderList()
    {
        StringBuffer hql = new StringBuffer();
        hql.append("select ptt_id from jpm_product_sale_team_type where uni_no in ");
        //'P1506010101CN0','P1501010101CN0'箱装酒
        hql.append("(select uni_no from jpm_product_sale_new where product_no in ('P1505010101CN0','P1503010101CN0'))");
        return this.getJdbcTemplate().queryForList(hql.toString());
    }

    /**
     * 酒业商品信息查询
     */
    @Override
    public List<JpoMemberOrderList> getWineProductOrderByParam(Map<String, String> map)
    {
        //po.jpoMemberOrder.sysUser.userCode='"+map.get("userCode")+"' and po.jpoMemberOrder.sysUser.companyCode='"+map.get("companyCode")+"'
        StringBuffer hql = new StringBuffer("from JpoMemberOrderList po where 1=1");
        String moId = map.get("moId");
        if(!StringUtils.isEmpty(moId))
        {
            hql.append("  and po.jpoMemberOrder.moId='"+moId+"'");
        }
        hql.append(" and po.jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo in('P1506010101CN0','P1505010101CN0','P1503010101CN0','P1501010101CN0')");
        Query q=getSession().createQuery(hql.toString());
        return q.list();
    }

    /**
     * 根据订单编号查询订单下商品已配置总数量
     * @param map
     * @return
     */
    @Override
    public Long getWineProductConfigCountByOrderId(Map<String, Long> map)
    {
        StringBuffer hql = new StringBuffer("select count(pm.amount) from JpmMemberConfig pm where pm.status = '0' and pm.moId = "+map.get("moId"));
        return (Long)getSession().createQuery(hql.toString()).uniqueResult();
    }

    /**
     * 根据订单商品编号查询订单下商品已配置总数量
     * @param map
     * @return
     */
    @Override
    public Long getWineProductConfigCountByMolId(Map<String, Long> map)
    {
        StringBuffer hql = new StringBuffer("select count(pm.amount) from JpmMemberConfig pm where pm.molId = "+map.get("molId"));
        return (Long)getSession().createQuery(hql.toString()).uniqueResult();
    }

    /**
     * 根据订单商品编号查询该商品的已配置列表
     * @param map
     * @return
     */
    @Override
    public List<JpmMemberConfig> getWineConfigByMolId(Map<String, Long> map,Pager pager)
    {
        StringBuffer hql = new StringBuffer("select j from JpmMemberConfig j where j.molId = '"+map.get("molId")+"'");
        return this.findObjectsByHqlQuery(hql.toString(), pager);
    }
    
    /**
     * 根据商品id查询商品编码
     * @param map
     * @return
     */
    public String getProductNoByProductId(Map<String,Long> map)
    {
        StringBuffer hql = new StringBuffer("select product_no from jpm_product_sale_new where uni_no = (select uni_no from jpm_product_sale_team_type where ptt_id = '"+map.get("productId")+"')");
        List strList = this.getJdbcTemplate().queryForList(hql.toString());
        if(null != strList && strList.size() > 0)
        {
            Map strMap = (Map)strList.get(0);
            return strMap.get("product_no").toString();
        }
        return null;
    }
    
}

