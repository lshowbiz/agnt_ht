
package com.joymain.jecs.sun.dao.hibernate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sun.model.SunProductInfo;
import com.joymain.jecs.sun.dao.SunProductInfoDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class SunProductInfoDaoHibernate extends BaseDaoHibernate implements SunProductInfoDao {

    /**
     * @see com.joymain.jecs.sun.dao.SunProductInfoDao#getSunProductInfos(com.joymain.jecs.sun.model.SunProductInfo)
     */
    public List getSunProductInfos(final SunProductInfo sunProductInfo) {
        return getHibernateTemplate().find("from SunProductInfo");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (sunProductInfo == null) {
            return getHibernateTemplate().find("from SunProductInfo");
        } else {
            // filter on properties set in the sunProductInfo
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(sunProductInfo).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(SunProductInfo.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.sun.dao.SunProductInfoDao#getSunProductInfo(Long jpiId)
     */
    public SunProductInfo getSunProductInfo(final Long jpiId) {
        SunProductInfo sunProductInfo = (SunProductInfo) getHibernateTemplate().get(SunProductInfo.class, jpiId);
        if (sunProductInfo == null) {
            log.warn("uh oh, sunProductInfo with jpiId '" + jpiId + "' not found...");
            throw new ObjectRetrievalFailureException(SunProductInfo.class, jpiId);
        }

        return sunProductInfo;
    }

    /**
     * @see com.joymain.jecs.sun.dao.SunProductInfoDao#saveSunProductInfo(SunProductInfo sunProductInfo)
     */    
    public void saveSunProductInfo(final SunProductInfo sunProductInfo) {
        getHibernateTemplate().saveOrUpdate(sunProductInfo);
    }

    /**
     * @see com.joymain.jecs.sun.dao.SunProductInfoDao#removeSunProductInfo(Long jpiId)
     */
    public void removeSunProductInfo(final Long jpiId) {
        getHibernateTemplate().delete(getSunProductInfo(jpiId));
    }
    //added for getSunProductInfosByCrm
    public List getSunProductInfosByCrm(CommonRecord crm, Pager pager){
    	String hql = "from SunProductInfo sunProductInfo where 1=1";
    	/** 
	---example---
	String userCode = crm.getString("userCode", "");
	if(StringUtils.isNotEmpty(userCode)){
		hql += "???????????";
	}
	 ***/
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by jpiId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public void updateSunOrder(SunProductInfo sunProductInfo) {
		DateFormat sf  = new SimpleDateFormat("yyyy-MM-dd");
		String start = sf.format(sunProductInfo.getBeginDate());
		String end = sf.format(sunProductInfo.getEndDate());
		// TODO Auto-generated method stub
		String sql="update jfi_sun_order_list s set s.s_price =                                              "+
		"(select p.discount from jfi_sun_order o ,sun_product_info p                              "+
		"where s.product_id=p.product_id and s.mo_id=o.mo_id and o.check_time>= p.begin_date      "+
		"and o.check_time<=p.end_date and p.jpi_Id= "+sunProductInfo.getJpiId()+")                                              "+
		"where  exists (select 1 from  jfi_sun_order o ,sun_product_info p                        "+
		"where s.product_id=p.product_id and s.mo_id=o.mo_id and o.check_time>= p.begin_date      "+
		"and o.check_time<=p.end_date and p.jpi_Id="+sunProductInfo.getJpiId()+")                                              ";
		log.info("updateSunOrder1="+sql);
		this.getJdbcTemplate().execute(sql);
		sql = "update jfi_sun_order o set o.amount = (select t.amount from                               "+
		"(select l. mo_id ,sum(l.qty*l.s_price) amount from jfi_sun_order_list l, jfi_sun_order o  "+
		"where o.mo_id=l.mo_id and o.check_time>=to_date('"+start+"','yyyy-mm-dd')                "+
		"and o.check_time<to_date('"+end+"','yyyy-mm-dd')+1 group by l.mo_id) t                 "+
		"where o.mo_id = t.mo_id) where o.check_time>=to_date('"+start+"','yyyy-mm-dd')           "+
		" and o.check_time<to_date('"+end+"','yyyy-mm-dd')+1                                    ";
		log.info("updateSunOrder2="+sql);
		this.getJdbcTemplate().execute(sql);
	}
    
    
}
