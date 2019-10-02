package com.joymain.jecs.pm.dao.hibernate;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.model.JpmMemberConfig;
import com.joymain.jecs.pm.dao.JpmMemberConfigDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JpmMemberConfigDaoHibernate extends BaseDaoHibernate implements JpmMemberConfigDao
{
    
    /**
     * @see com.joymain.jecs.pm.dao.JpmMemberConfigDao#getJpmMemberConfigs(com.joymain.jecs.pm.model.JpmMemberConfig)
     */
    public List getJpmMemberConfigs(final JpmMemberConfig jpmMemberConfig)
    {
        return getHibernateTemplate().find("from JpmMemberConfig");
        
        /*
         * Remove the line above and uncomment this code block if you want to
         * use Hibernate's Query by Example API. if (jpmMemberConfig == null) {
         * return getHibernateTemplate().find("from JpmMemberConfig"); } else {
         * // filter on properties set in the jpmMemberConfig HibernateCallback
         * callback = new HibernateCallback() { public Object
         * doInHibernate(Session session) throws HibernateException { Example ex
         * =Example.create(jpmMemberConfig).ignoreCase().enableLike(MatchMode.
         * ANYWHERE); return
         * session.createCriteria(JpmMemberConfig.class).add(ex).list(); } };
         * return (List) getHibernateTemplate().execute(callback); }
         */
    }
    
    /**
     * @see com.joymain.jecs.pm.dao.JpmMemberConfigDao#getJpmMemberConfig(Long
     *      configNo)
     */
    public JpmMemberConfig getJpmMemberConfig(final Long configNo)
    {
        JpmMemberConfig jpmMemberConfig =
            (JpmMemberConfig) getHibernateTemplate().get(JpmMemberConfig.class, configNo);
        if (jpmMemberConfig == null)
        {
            log.warn("uh oh, jpmMemberConfig with configNo '" + configNo + "' not found...");
            throw new ObjectRetrievalFailureException(JpmMemberConfig.class, configNo);
        }
        
        return jpmMemberConfig;
    }
    
    /**
     * @see com.joymain.jecs.pm.dao.JpmMemberConfigDao#saveJpmMemberConfig(JpmMemberConfig
     *      jpmMemberConfig)
     */
    public void saveJpmMemberConfig(final JpmMemberConfig jpmMemberConfig)
    {
        getHibernateTemplate().saveOrUpdate(jpmMemberConfig);
    }
    
    /**
     * @see com.joymain.jecs.pm.dao.JpmMemberConfigDao#removeJpmMemberConfig(Long
     *      configNo)
     */
    public void removeJpmMemberConfig(final Long configNo)
    {
        getHibernateTemplate().delete(getJpmMemberConfig(configNo));
    }
    
    // added for getJpmMemberConfigsByCrm
    public List getJpmMemberConfigsByCrm(CommonRecord crm, Pager pager)
    {
        String hql = "from JpmMemberConfig jpmMemberConfig where 1=1";
        /**
         * ---example--- String userCode = crm.getString("userCode", "");
         * if(StringUtils.isNotEmpty(userCode)){ hql += "???????????"; }
         ***/
        // 
        if (!pager.getLimit().getSort().isSorted())
        {
            // sort
            hql += " order by configNo desc";
        }
        else
        {
            hql +=
                " ORDER BY " + pager.getLimit().getSort().getProperty() + " "
                    + pager.getLimit().getSort().getSortOrder();
        }
        return this.findObjectsByHqlQuery(hql, pager);
    }
    
    @Override
    public Integer delJpmMemberConfig(Long configNo)
    {
        StringBuffer sql =
            new StringBuffer("delete from jpm_member_config where config_no = " + configNo);
        this.getJdbcTemplate().execute(sql.toString());
        return 0;
    }
    
    @Override
    public Integer addJpmMemberConfig(JpmMemberConfig jpmMemberConfig)
    {
        this.getSession().saveOrUpdate(jpmMemberConfig);
        return 0;
    }
    
    @Override
    public Integer modifyJpmMemberConfig(JpmMemberConfig jpmMemberConfig)
    {
        // TODO Auto-generated method stub
        return null;
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
        // 查询为已支付完成，配置完成的数量
        StringBuffer hql =
            new StringBuffer("from JpmMemberConfig where molId = '" + map.get("molId") + "'");
        Query q = getSession().createQuery(hql.toString());
        return q.list();
    }
    
    @Override
    public JpmMemberConfig getJpmMemberConfigByconfigNo(Long configNo)
    {
        StringBuffer hql = new StringBuffer("from JpmMemberConfig j where j.configNo=" + configNo);
        return (JpmMemberConfig) this.getObjectByHqlQuery(hql.toString());
    }
    
    @Override
    public List<JpmMemberConfig> getNoStatusWineConfigByMolId(Map<String, Long> map)
    {
        // 查询为已支付完成，配置完成的数量
        StringBuffer hql =
            new StringBuffer("from JpmMemberConfig where molId = '" + map.get("molId") + "'");
        Query q = getSession().createQuery(hql.toString());
        return q.list();
    }
    
    @Override
    public void modifyJpmMemberConfigStatusByConfigNo(String configNo, String status,Long price)
    {
        StringBuffer sql = null;
        if(null != price)
        {
            sql =
                new StringBuffer("update jpm_member_config set status='" + status
                    + "', price="+ price +" where config_no='" + configNo + "'");
        }
        else
        {
            sql =
                new StringBuffer("update jpm_member_config set status='" + status
                    + "' where config_no='" + configNo + "'");
        }
        this.getJdbcTemplate().execute(sql.toString());
    }
    
    @Override
    public List<JpmMemberConfig> getAllConfigStatusByConfigNo(String configNo, String status)
    {
        StringBuffer hql =
            new StringBuffer("from JpmMemberConfig where configNo = '" + configNo + "'");
        JpmMemberConfig config = (JpmMemberConfig) this.getObjectByHqlQuery(hql.toString());
        hql = new StringBuffer("from JpmMemberConfig where moId = " + config.getMoId());
        Query query = getSession().createQuery(hql.toString());
        return query.list();
    }
    
    @Override
    public String getProductNoByProductId(String productId)
    {
        StringBuffer sql =
            new StringBuffer("select n.product_no from jpm_product_sale_new n where n.uni_no = (");
        sql.append("select t.uni_no from jpm_product_sale_team_type t where t.ptt_id ='"
            + productId + "')");
        return (String) getSession().createQuery(sql.toString()).uniqueResult();
    }
}
