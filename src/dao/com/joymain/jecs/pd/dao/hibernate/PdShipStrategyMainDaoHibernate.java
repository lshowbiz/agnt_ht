
package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.dao.PdShipStrategyMainDao;
import com.joymain.jecs.pd.model.PdShipStrategyMain;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class PdShipStrategyMainDaoHibernate extends BaseDaoHibernate implements PdShipStrategyMainDao {

    /**
     * @see com.joymain.jecs.pd.dao.PdShipStrategyMainDao#getPdShipStrategyMains(com.joymain.jecs.pd.model.PdShipStrategyMain)
     */
    public List getPdShipStrategyMains(final PdShipStrategyMain pdShipStrategyMain) {
        return getHibernateTemplate().find("from PdShipStrategyMain");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdShipStrategyMain == null) {
            return getHibernateTemplate().find("from PdShipStrategyMain");
        } else {
            // filter on properties set in the pdShipStrategyMain
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdShipStrategyMain).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdShipStrategyMain.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdShipStrategyMainDao#getPdShipStrategyMain(Long valueId)
     */
    public PdShipStrategyMain getPdShipStrategyMain(final Long valueId) {
        PdShipStrategyMain pdShipStrategyMain = (PdShipStrategyMain) getHibernateTemplate().get(PdShipStrategyMain.class, valueId);
        if (pdShipStrategyMain == null) {
            log.warn("uh oh, pdShipStrategyMain with valueId '" + valueId + "' not found...");
            throw new ObjectRetrievalFailureException(PdShipStrategyMain.class, valueId);
        }

        return pdShipStrategyMain;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdShipStrategyMainDao#savePdShipStrategyMain(PdShipStrategyMain pdShipStrategyMain)
     */    
    public void savePdShipStrategyMain(final PdShipStrategyMain pdShipStrategyMain) {
        getHibernateTemplate().saveOrUpdate(pdShipStrategyMain);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdShipStrategyMainDao#removePdShipStrategyMain(Long valueId)
     */
    public void removePdShipStrategyMain(final Long valueId) {
        getHibernateTemplate().delete(getPdShipStrategyMain(valueId));
    }
    //added for getPdShipStrategyMainsByCrm
    public List getPdShipStrategyMainsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdShipStrategyMain pdShipStrategyMain where 1=1 ";

    	String valueTitle = crm.getString("valueTitle", "");
    	if(StringUtils.isNotEmpty(valueTitle)){
    		hql += " and pdShipStrategyMain.valueTitle like '%"+valueTitle.trim()+"%' ";
    	}
    	
    	String importance = crm.getString("importance", "");
    	if(StringUtils.isNotEmpty(importance)){
    		hql += " and pdShipStrategyMain.importance like '%"+ importance.trim()+"' ";
    	}

		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by valueId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    /**
     * 判断主策略是否有相同优先级
     * @param pdShipStrategyMain
     * @return
     */
    public Integer getPriorityIsExists(PdShipStrategyMain pdShipStrategyMain){
    	Integer num = 0;
    	String sql = "select * from pd_ship_strategy_main t where importance='1' and priority='"+pdShipStrategyMain.getPriority()+"' and value_id!='"+pdShipStrategyMain.getValueId()+"' ";
    	List list = jdbcTemplate.queryForList(sql);
    	if(list!=null && list.size()>=1){
    		num = 1;
    	}
		return num;
    }
}
