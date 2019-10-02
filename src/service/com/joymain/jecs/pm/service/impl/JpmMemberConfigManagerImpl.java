package com.joymain.jecs.pm.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.mi.model.JmiAddrBook;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiAddrBookManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.pm.dao.JpmMemberConfigDao;
import com.joymain.jecs.pm.model.JpmConfigDetailed;
import com.joymain.jecs.pm.model.JpmConfigSpecDetailed;
import com.joymain.jecs.pm.model.JpmMemberConfig;
import com.joymain.jecs.pm.model.JpmProductWineTemplateSub;
import com.joymain.jecs.pm.model.JpmSendConsignment;
import com.joymain.jecs.pm.model.JpmWineOrderInterface;
import com.joymain.jecs.pm.model.JpmWineOrderListInterface;
import com.joymain.jecs.pm.model.JpmWineSettingInf;
import com.joymain.jecs.pm.model.JpmWineSettingListInf;
import com.joymain.jecs.pm.model.JpmWineTemplatePicture;
import com.joymain.jecs.pm.service.JpmConfigDetailedManager;
import com.joymain.jecs.pm.service.JpmConfigSpecDetailedManager;
import com.joymain.jecs.pm.service.JpmMemberConfigManager;
import com.joymain.jecs.pm.service.JpmProductWineTemplateSubManager;
import com.joymain.jecs.pm.service.JpmSendConsignmentManager;
import com.joymain.jecs.pm.service.JpmWineTemplatePictureManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.wine.WineInterfaceUtil;

public class JpmMemberConfigManagerImpl extends BaseManager implements JpmMemberConfigManager
{
    private JpmMemberConfigDao dao;
    
    private JfiBankbookJournalManager jfiBankbookJournalManager;
    
    private JpmConfigSpecDetailedManager jpmConfigSpecDetailedManager;
    
    private JpmConfigDetailedManager jpmConfigDetailedManager;
    
    private JpmProductWineTemplateSubManager jpmProductWineTemplateSubManager;
    
    private JpmWineTemplatePictureManager jpmWineTemplatePictureManager;
    
    private JpmSendConsignmentManager jpmSendConsignmentManager;
    
    private JpoMemberOrderManager jpoMemberOrderManager;
    
    private JmiAddrBookManager jmiAddrBookManager;
    
    private JmiMemberManager jmiMemberManager;
    
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager)
    {
        this.jmiMemberManager = jmiMemberManager;
    }

    public void setJmiAddrBookManager(JmiAddrBookManager jmiAddrBookManager)
    {
        this.jmiAddrBookManager = jmiAddrBookManager;
    }

    public void setJpmSendConsignmentManager(JpmSendConsignmentManager jpmSendConsignmentManager)
    {
        this.jpmSendConsignmentManager = jpmSendConsignmentManager;
    }
    
    public void setJpmConfigSpecDetailedManager(
        JpmConfigSpecDetailedManager jpmConfigSpecDetailedManager)
    {
        this.jpmConfigSpecDetailedManager = jpmConfigSpecDetailedManager;
    }
    
    public void setJpmConfigDetailedManager(JpmConfigDetailedManager jpmConfigDetailedManager)
    {
        this.jpmConfigDetailedManager = jpmConfigDetailedManager;
    }
    
    public void setJpmProductWineTemplateSubManager(
        JpmProductWineTemplateSubManager jpmProductWineTemplateSubManager)
    {
        this.jpmProductWineTemplateSubManager = jpmProductWineTemplateSubManager;
    }
    
    public void setJpmWineTemplatePictureManager(
        JpmWineTemplatePictureManager jpmWineTemplatePictureManager)
    {
        this.jpmWineTemplatePictureManager = jpmWineTemplatePictureManager;
    }
    
    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager)
    {
        this.jpoMemberOrderManager = jpoMemberOrderManager;
    }
    
    public void setJfiBankbookJournalManager(JfiBankbookJournalManager jfiBankbookJournalManager)
    {
        this.jfiBankbookJournalManager = jfiBankbookJournalManager;
    }
    
    /**
     * Set the Dao for communication with the data layer.
     * 
     * @param dao
     */
    public void setJpmMemberConfigDao(JpmMemberConfigDao dao)
    {
        this.dao = dao;
    }
    
    /**
     * @see com.joymain.jecs.pm.service.JpmMemberConfigManager#getJpmMemberConfigs(com.joymain.jecs.pm.model.JpmMemberConfig)
     */
    public List getJpmMemberConfigs(final JpmMemberConfig jpmMemberConfig)
    {
        return dao.getJpmMemberConfigs(jpmMemberConfig);
    }
    
    /**
     * @see com.joymain.jecs.pm.service.JpmMemberConfigManager#getJpmMemberConfig(String
     *      configNo)
     */
    public JpmMemberConfig getJpmMemberConfig(final String configNo)
    {
        return dao.getJpmMemberConfig(new Long(configNo));
    }
    
    /**
     * @see com.joymain.jecs.pm.service.JpmMemberConfigManager#saveJpmMemberConfig(JpmMemberConfig
     *      jpmMemberConfig)
     */
    public void saveJpmMemberConfig(JpmMemberConfig jpmMemberConfig)
    {
        dao.saveJpmMemberConfig(jpmMemberConfig);
    }
    
    /**
     * @see com.joymain.jecs.pm.service.JpmMemberConfigManager#removeJpmMemberConfig(String
     *      configNo)
     */
    public void removeJpmMemberConfig(final String configNo)
    {
        dao.removeJpmMemberConfig(new Long(configNo));
    }
    
    // added for getJpmMemberConfigsByCrm
    public List getJpmMemberConfigsByCrm(CommonRecord crm, Pager pager)
    {
        return dao.getJpmMemberConfigsByCrm(crm, pager);
    }
    
    @Override
    public Integer delJpmMemberConfig(Long configNo)
    {
        // TODO Auto-generated method stub
        return dao.delJpmMemberConfig(configNo);
    }
    
    @Override
    public List<JpmMemberConfig> getJpmMemberConfigListByProductId(Map<String, String> map)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Integer addJpmMemberConfig(JpmMemberConfig jpmMemberConfig)
    {
        // TODO Auto-generated method stub
        return dao.addJpmMemberConfig(jpmMemberConfig);
    }
    
    @Override
    public Integer modifyJpmMemberConfig(JpmMemberConfig jpmMemberConfig)
    {
        // TODO Auto-generated method stub
        return dao.modifyJpmMemberConfig(jpmMemberConfig);
    }
    
    /**
     * 根据订单商品编号查询该商品的已配置列表
     * 
     * @param map
     * @return
     */
    @Override
    public List<JpmMemberConfig> getWineConfigByMolId(Map<String, Long> map)
    {
        return dao.getWineConfigByMolId(map);
    }
    
    @Override
    public JpmMemberConfig getJpmMemberConfigByConfigNo(Long configNo)
    {
        return dao.getJpmMemberConfigByconfigNo(configNo);
    }
    
    @Override
    public List<JpmMemberConfig> getNoStatusWineConfigByMolId(Map<String, Long> map)
    {
        return dao.getNoStatusWineConfigByMolId(map);
    }
    
    @Override
    public void modifyJpmMemberConfigStatusByConfigNo(String configNo, String status,Long price)
    {
        dao.modifyJpmMemberConfigStatusByConfigNo(configNo, status,price);
    }
    
    @Override
    public List<JpmMemberConfig> getAllConfigStatusByConfigNo(String configNo, String status)
    {
        return dao.getAllConfigStatusByConfigNo(configNo, status);
    }
    
    @Override
    public String getProductNoByProductId(String productId)
    {
        return dao.getProductNoByProductId(productId);
    }
    
    /**
     * 酒业配置单支付审核
     * 
     * @param configNo 订单号
     * @param jsysUser 操作用户
     */
    public void checkJpmMemberConfig(String configNo, SysUser jsysUser) throws AppException
    {
        JmiMember jmiMember = new JmiMember();
        try
        {
            JpmMemberConfig jpmMemberConfig = this.getJpmMemberConfig(configNo);
              JpoMemberOrder order = jpoMemberOrderManager.getJpoMemberOrder(jpmMemberConfig.getMoId().toString());
              jmiMember = jmiMemberManager.getJmiMember(order.getSysUser().getUserCode());
            //1.扣钱
            //公司编码
            String companyCode = jmiMember.getCompanyCode();
                   
            //资金类别
            Integer[] moneyType = new Integer[1];
            moneyType[0] = 100;//100代表审核酒业配置订单
                   
            //要扣的金额
            BigDecimal[] moneyArray = new BigDecimal[1];
            moneyArray[0] = jpmMemberConfig.getPrice();
                   
            //摘要
            String[] notes = new String[1];
            notes[0] = "酒业配置订单支付,订单号：" + jpmMemberConfig.getMoId();
                   
            //电子存折扣钱
            jfiBankbookJournalManager.saveJfiBankbookDeduct(companyCode,
            jsysUser, moneyType, moneyArray, jsysUser.getUserCode(),
            jsysUser.getUserName(), "JY"+jpmMemberConfig.getMoId(), notes,"1");
            //修改配置状态为2（支付成功）
            dao.modifyJpmMemberConfigStatusByConfigNo(configNo, "2",null);
        }catch(AppException appE)
        {
            return;
        }
        catch(Exception e)
        {
            return;
        }
        // 2.
        JpmMemberConfig config = new JpmMemberConfig();
        config = this.getJpmMemberConfig(configNo);
        // 查询订单配置下的所有规格
        List<JpmConfigSpecDetailed> specList =
            jpmConfigSpecDetailedManager.getJpmConfigSpecDetailedListByConfigNo(Long.parseLong(configNo));
        WineInterfaceUtil wineUtil = new WineInterfaceUtil();
        // 配置单推送
        for (JpmConfigSpecDetailed jpmConfigSpecDetailed : specList)
        {
            JpmWineSettingInf jpmWineSettingInf = new JpmWineSettingInf();
            jpmWineSettingInf.setProductId(jpmConfigSpecDetailed.getSpecNo());
            jpmWineSettingInf.setProductName(jpmConfigSpecDetailed.getProductTemplateName());
            jpmWineSettingInf.setProductQty(new Integer(1));
            List<JpmWineSettingListInf> infList = new ArrayList<JpmWineSettingListInf>();
            
            List<JpmConfigDetailed> detailedList =
                jpmConfigDetailedManager.getJpmConfigDetailedBySpecNo(String.valueOf(jpmConfigSpecDetailed.getSpecNo()));
            for (JpmConfigDetailed jpmConfigDetailed : detailedList)
            {
                JpmProductWineTemplateSub sub = new JpmProductWineTemplateSub();
                sub =
                    jpmProductWineTemplateSubManager.getJpmProductWineTemplateSubBySubNo(jpmConfigDetailed.getSubNo());
                JpmWineSettingListInf inf = new JpmWineSettingListInf();
                inf.setMaterialNo(sub.getProductNo());
                inf.setQty(jpmConfigDetailed.getSubAmount().intValue());
                inf.setLossRatio(sub.getLossRatio().floatValue());
                inf.setSdate("2013-1-1");
                inf.setEdate("9999-12-31");
                inf.setIsDelegateOut(sub.getIsDelegateOut());
                inf.setIsFeatureItem(sub.getIsFeatureItem());
                inf.setIsMainMaterial(sub.getIsMainMaterial());
                inf.setIsSendMaterial(sub.getIsSendMaterial());
                inf.setMemo(jpmConfigDetailed.getRemark());
                if (null != jpmConfigDetailed.getIdf())
                {
                    JpmWineTemplatePicture pic = new JpmWineTemplatePicture();
                    pic =
                        jpmWineTemplatePictureManager.getJpmWineTemplatePicture(String.valueOf(jpmConfigDetailed.getIdf()));
                     inf.setPicName(pic.getPictureNameSrc());
                }
                infList.add(inf);
                inf.setJpmWineSettingListInf(jpmWineSettingInf);
            }
            Set<JpmWineSettingListInf> jpmWineSettingListInfSet =
                new HashSet<JpmWineSettingListInf>();
            jpmWineSettingListInfSet.addAll(infList);
            jpmWineSettingInf.setJpmWineSettingListInfSet(jpmWineSettingListInfSet);
            wineUtil.saveAndSendSettingBill(jpmWineSettingInf, 0);
        }
        
        for (JpmConfigSpecDetailed jpmConfigSpecDetailed : specList)
        {
            // 查询发货单
            List<JpmSendConsignment> sendList =
                jpmSendConsignmentManager.getJpmSendConsignmentListBySpecNo(jpmConfigSpecDetailed.getSpecNo());
            Double price = jpmConfigSpecDetailed.getPrice().doubleValue();
            JpmConfigDetailed jpmConfigDetailed =
                jpmConfigDetailedManager.getJpmConfigDetailedNumBySpecNo(String.valueOf(jpmConfigSpecDetailed.getSpecNo()));
            if(null != jpmConfigDetailed)
            {
                price = price / jpmConfigDetailed.getSubAmount();
            }
            
            // 调用发货
            for (JpmSendConsignment jpmSendConsignment : sendList)
            {
                // 发货单推送
                JpmWineOrderInterface jpmWineOrderInterface = new JpmWineOrderInterface();
                jpmWineOrderInterface.setUserCode(jmiMember.getUserCode());
                // 发货单号
                jpmWineOrderInterface.setBillCode(String.valueOf(jpmSendConsignment.getConsignmentNo()));
                jpmWineOrderInterface.setCoperatorid(jmiMember.getUserCode());
                jpmWineOrderInterface.setCuscity(jmiMember.getCity());
                jpmWineOrderInterface.setCusprovince(jmiMember.getProvince());
                jpmWineOrderInterface.setAreaclCode(jmiMember.getDistrict());
                jpmWineOrderInterface.setDbillDate(jpmSendConsignment.getSendDate().toString());
                jpmWineOrderInterface.setMemberaddress(jmiMember.getAddress());
                jpmWineOrderInterface.setMemberid(null);
                jpmWineOrderInterface.setMembername(jmiMember.getFirstName()
                    + jmiMember.getLastName());
                jpmWineOrderInterface.setMembersex(jmiMember.getSex());
                jpmWineOrderInterface.setMemberOrderNo(String.valueOf(config.getMoId()));
                jpmWineOrderInterface.setMemberphone(jmiMember.getPhone());
                jpmWineOrderInterface.setPostalcode(jmiMember.getPostalcode());
                
                // 根据发货单中的地址id查询发货地址信息
                JmiAddrBook jmiAddrBook = jmiAddrBookManager.getJmiAddrBook(String.valueOf(jpmSendConsignment.getFabId()));
                jpmWineOrderInterface.setReceiverAddress(jmiAddrBook.getAddress());
                jpmWineOrderInterface.setReceiverCode(String.valueOf(jpmSendConsignment.getConsignmentNo()));
                jpmWineOrderInterface.setReceiverCusCity(jmiAddrBook.getCity());
                jpmWineOrderInterface.setReceiverCusProvince(jmiAddrBook.getProvince());
                jpmWineOrderInterface.setReceiverAreaclcode(jmiAddrBook.getDistrict());
                jpmWineOrderInterface.setReceiverId(null);
                jpmWineOrderInterface.setReceiverName(jmiAddrBook.getFirstName()
                    + jmiAddrBook.getLastName());
                jpmWineOrderInterface.setReceiverPhone(jmiAddrBook.getPhone());
                jpmWineOrderInterface.setReceiverPostalCode(jmiAddrBook.getPostalcode());
                jpmWineOrderInterface.setReceiverSex(null);
                
                JpmWineOrderListInterface jpmWineOrderListInterface =
                    new JpmWineOrderListInterface();
                
                jpmWineOrderListInterface.setPrice(price);
                jpmWineOrderListInterface.setProductId(String.valueOf(jpmConfigSpecDetailed.getSpecNo()));
                jpmWineOrderListInterface.setDconsigndate(jpmSendConsignment.getSendDate()
                    .toString());
                jpmWineOrderListInterface.setQty(jpmSendConsignment.getConsignmenNum().intValue());
                jpmWineOrderListInterface.setVreceiveaddress(jmiAddrBook.getAddress());
                
                jpmWineOrderInterface.getJpmWineOrderListInterfaceSet()
                .add(jpmWineOrderListInterface);
                jpmWineOrderListInterface.setJpmWineOrderInterface(jpmWineOrderInterface);
                
                wineUtil.saveAndSendOrder(jpmWineOrderInterface, 0);
            }
        }
        
        // 推送成功后，查询订单下是否全部支付，支付完成则更改订单状态为配置完成
        List<JpmMemberConfig> configList = this.getAllConfigStatusByConfigNo(configNo, "");
        boolean bl = true;
        String moId = null;
        for (JpmMemberConfig jpmConfig : configList)
        {
            moId = String.valueOf(jpmConfig.getMoId());
            String jpmConfigNo = jpmConfig.getConfigNo().toString();
            // 配置完成状态'2'
            if (!"2".equals(jpmConfig.getStatus()))
            {
                if(!configNo.equals(jpmConfigNo) && configNo != jpmConfigNo)
                {
                    bl = false;
                    continue;
                }
            }
        }
        if (true == bl)
        {
            // 0为已配置状态
            Map<String, String> modMap = new HashMap<String, String>();
            modMap.put("status", "0");
            modMap.put("moId", moId);
            jpoMemberOrderManager.modifyOrderStatusByMoId(modMap);
        }
    }
}
