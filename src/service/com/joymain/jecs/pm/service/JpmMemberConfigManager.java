package com.joymain.jecs.pm.service;

import java.util.List;
import java.util.Map;

import com.joymain.jecs.pm.model.JpmMemberConfig;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;

public interface JpmMemberConfigManager extends Manager
{
    /**
     * Retrieves all of the jpmMemberConfigs
     */
    public List getJpmMemberConfigs(JpmMemberConfig jpmMemberConfig);
    
    /**
     * Gets jpmMemberConfig's information based on configNo.
     * 
     * @param configNo the jpmMemberConfig's configNo
     * @return jpmMemberConfig populated jpmMemberConfig object
     */
    public JpmMemberConfig getJpmMemberConfig(final String configNo);
    
    /**
     * Saves a jpmMemberConfig's information
     * 
     * @param jpmMemberConfig the object to be saved
     */
    public void saveJpmMemberConfig(JpmMemberConfig jpmMemberConfig);
    
    /**
     * Removes a jpmMemberConfig from the database by configNo
     * 
     * @param configNo the jpmMemberConfig's configNo
     */
    public void removeJpmMemberConfig(final String configNo);
    
    // added for getJpmMemberConfigsByCrm
    public List getJpmMemberConfigsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * 根据商品编号查询商品配置信息
     * 
     * @param map
     * @return
     */
    public List<JpmMemberConfig> getJpmMemberConfigListByProductId(Map<String, String> map);
    
    /**
     * 添加配置信息
     * 
     * @return
     */
    public Integer addJpmMemberConfig(JpmMemberConfig jpmMemberConfig);
    
    /**
     * 修改配置信息
     * 
     * @return
     */
    public Integer modifyJpmMemberConfig(JpmMemberConfig jpmMemberConfig);
    
    /**
     * 删除配置信息
     * 
     * @param configNo
     * @return
     */
    public Integer delJpmMemberConfig(Long configNo);
    
    /**
     * 根据订单商品编号查询该商品的已配置列表
     * 
     * @param map
     * @return
     */
    public List<JpmMemberConfig> getWineConfigByMolId(Map<String, Long> map);
    
    /**
     * 根据订单商品编号查询该商品的已配置列表
     * 
     * @param map
     * @return
     */
    public List<JpmMemberConfig> getNoStatusWineConfigByMolId(Map<String, Long> map);
    
    public JpmMemberConfig getJpmMemberConfigByConfigNo(Long configNo);
    
    public void modifyJpmMemberConfigStatusByConfigNo(String configNo, String status,Long price);
    
    public List<JpmMemberConfig> getAllConfigStatusByConfigNo(String configNo, String status);
    
    public String getProductNoByProductId(String productId);
    
    /**
     * 酒业配置单支付审核
     * 
     * @param configNo 订单号
     * @param jsysUser 操作用户
     */
    public void checkJpmMemberConfig(String configNo, SysUser jsysUser) throws AppException;
}
