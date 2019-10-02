
package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdShUrl;
import com.joymain.jecs.pd.dao.PdShUrlDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class PdShUrlDaoHibernate extends BaseDaoHibernate implements PdShUrlDao {

    /**
     * @see com.joymain.jecs.pd.dao.PdShUrlDao#getPdShUrls(com.joymain.jecs.pd.model.PdShUrl)
     */
    public List getPdShUrls(final PdShUrl pdShUrl) {
        return getHibernateTemplate().find("from PdShUrl");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdShUrl == null) {
            return getHibernateTemplate().find("from PdShUrl");
        } else {
            // filter on properties set in the pdShUrl
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdShUrl).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdShUrl.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdShUrlDao#getPdShUrl(BigDecimal id)
     */
    public PdShUrl getPdShUrl(final Long id) {
        PdShUrl pdShUrl = (PdShUrl) getHibernateTemplate().get(PdShUrl.class, id);
        if (pdShUrl == null) {
            log.warn("uh oh, pdShUrl with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(PdShUrl.class, id);
        }

        return pdShUrl;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdShUrlDao#savePdShUrl(PdShUrl pdShUrl)
     */    
    public void savePdShUrl(final PdShUrl pdShUrl) {
        getHibernateTemplate().saveOrUpdate(pdShUrl);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdShUrlDao#removePdShUrl(BigDecimal id)
     */
    public void removePdShUrl(final Long id) {
        getHibernateTemplate().delete(getPdShUrl(id));
    }
    
    /**
     * 物流公司链接的查询
     * @author yxzz 2014-07-09
     * @param  crm
     * @param  pager 
     * @return list
     */
    public List getPdShUrlsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdShUrl pdShUrl where 1=1";
    	
        String valueCode = crm.getString("valueCode", "");
        if(!StringUtil.isEmpty(valueCode)){
        	   hql += " and pdShUrl.valueCode = '"+valueCode+"'";
        }
        
        String valueTitle = crm.getString("valueTitle", "");
        if(!StringUtil.isEmpty(valueTitle)){
        	   hql += " and pdShUrl.valueTitle = '"+valueTitle+"'";
        }
        
               hql += " order by pdShUrl.createTime desc "; 	
		return this.findObjectsByHqlQuery(hql, pager);
    }

    /**
	 * 物流公司编码唯一性校验
	 * @author yxzz 2014-07-09
	 * @param pdShUrl
	 * @return boolean
	 */
	public boolean getValueCodeUniqueCheck(PdShUrl pdShUrl) {
		String hql = " from PdShUrl pdShUrl where pdShUrl.valueCode='"+pdShUrl.getValueCode()+"'";
		if(null!=pdShUrl.getId()){
			   hql += " and pdShUrl.id <> '"+pdShUrl.getId()+"'";
		}
		
		List list = this.findObjectsByHqlQuery(hql);
		if(null!=list){
			if(list.size()>0){
				return true;
			}
		}		
		return false;
	}

	/**
	 * 获取物流公司链接地址
	 * @author gw  2014-07-10
	 * @param shNo 物流公司编码
	 * @return string 物流公司链接地址
	 */
	public String getShUrl(String shNo) {		
		if(!StringUtil.isEmpty(shNo)){
			String hql = " from PdShUrl pdShUrl where pdShUrl.valueCode = '"+shNo+"' ";
			List list = this.findObjectsByHqlQuery(hql);
			if(null!=list){
				if(list.size()>0){
					PdShUrl pdShUrl = (PdShUrl) list.get(0);
					return pdShUrl.getShUrl();
				}
			}
		}		
		return null;
	}
    
}
