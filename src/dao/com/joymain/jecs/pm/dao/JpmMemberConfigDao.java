package com.joymain.jecs.pm.dao;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pm.model.JpmMemberConfig;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmMemberConfigDao extends Dao
{
    
    /**
     * Retrieves all of the jpmMemberConfigs
     */
    public List getJpmMemberConfigs(JpmMemberConfig jpmMemberConfig);
    
    /**
     * Gets jpmMemberConfig's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
     * found.
     * 
     * @param configNo the jpmMemberConfig's configNo
     * @return jpmMemberConfig populated jpmMemberConfig object
     */
    public JpmMemberConfig getJpmMemberConfig(final Long configNo);
    
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
    public void removeJpmMemberConfig(final Long configNo);
    
    // added for getJpmMemberConfigsByCrm
    public List getJpmMemberConfigsByCrm(CommonRecord crm, Pager pager);
    
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
    public List<JpmMemberConfig> getNoStatusWineConfigByMolId(Map<String, Long> map);
    
    /**
     * 根据订单商品编号查询该商品的已配置列表
     * 
     * @param map
     * @return
     */
    public List<JpmMemberConfig> getWineConfigByMolId(Map<String, Long> map);
    
    public JpmMemberConfig getJpmMemberConfigByconfigNo(Long configNo);
    
    public void modifyJpmMemberConfigStatusByConfigNo(String configNo, String status,Long price);
    
    public List<JpmMemberConfig> getAllConfigStatusByConfigNo(String configNo, String status);
    
    public String getProductNoByProductId(String productId);
}
