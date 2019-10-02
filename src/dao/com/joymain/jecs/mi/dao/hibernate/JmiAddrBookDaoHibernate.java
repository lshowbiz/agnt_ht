
package com.joymain.jecs.mi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.model.JmiAddrBook;
import com.joymain.jecs.mi.dao.JmiAddrBookDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JmiAddrBookDaoHibernate extends BaseDaoHibernate implements JmiAddrBookDao {

    /**
     * @see com.joymain.jecs.mi.dao.JmiAddrBookDao#getJmiAddrBooks(com.joymain.jecs.mi.model.JmiAddrBook)
     */
    public List getJmiAddrBooks(final JmiAddrBook jmiAddrBook) {
        return getHibernateTemplate().find("from JmiAddrBook");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiAddrBook == null) {
            return getHibernateTemplate().find("from JmiAddrBook");
        } else {
            // filter on properties set in the jmiAddrBook
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiAddrBook).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiAddrBook.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiAddrBookDao#getJmiAddrBook(Long fabId)
     */
    public JmiAddrBook getJmiAddrBook(final Long fabId) {
        JmiAddrBook jmiAddrBook = (JmiAddrBook) getHibernateTemplate().get(JmiAddrBook.class, fabId);
        if (jmiAddrBook == null) {
            log.warn("uh oh, jmiAddrBook with fabId '" + fabId + "' not found...");
            throw new ObjectRetrievalFailureException(JmiAddrBook.class, fabId);
        }

        return jmiAddrBook;
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiAddrBookDao#saveJmiAddrBook(JmiAddrBook jmiAddrBook)
     */    
    public void saveJmiAddrBook(final JmiAddrBook jmiAddrBook) {
        getHibernateTemplate().saveOrUpdate(jmiAddrBook);
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiAddrBookDao#removeJmiAddrBook(Long fabId)
     */
    public void removeJmiAddrBook(final Long fabId) {
        getHibernateTemplate().delete(getJmiAddrBook(fabId));
    }
    //added for getJmiAddrBooksByCrm
    public List getJmiAddrBooksByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JmiAddrBook jmiAddrBook where 1=1";
		String userCode = crm.getString("userCode", "");
		if(!StringUtil.isEmpty(userCode)){
			hql += " and jmiMember.userCode='"+userCode+"'";
		}
		String companyCode = crm.getString("companyCode", "");
		if(!StringUtil.isEmpty(companyCode)){
			hql += " and jmiMember.companyCode='"+companyCode+"'";
		}
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by fabId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public List getJmiAddrBooksByUserCode(String userCode) {
		// TODO Auto-generated method stub
		String hql = "from JmiAddrBook jmiAddrBook where 1=1  and jmiMember.userCode='"+userCode+"'";
		
		return this.getHibernateTemplate().find(hql);
	}
    
	 /**
     * 根据会员编号查询默认收货地址
     * @param userCode
     * @return
     */
    public JmiAddrBook getDefaultAddrBookByUserCode(String userCode){
    	
    	List<JmiAddrBook> addrList=this.getSession().createQuery("select a from JmiAddrBook a where a.jmiMember.userCode='"+userCode+"' and isDefault=1 ").list();
    	if(addrList!=null&&addrList.size()>0)
		{
    		
    		return addrList.get(0);
		}else{
			return null;
		}
    }

    /**
     * 移除默认的收货地址
     * removeDefaultForJmiAddrBook
     *
     * @param userCode
     */
    @Override
    public void removeDefaultForJmiAddrBook(String userCode)
    {
        this.getSession().createSQLQuery("update jmi_addr_book a set a.is_default='0' where a.user_code=?").setParameter(0, userCode)
        .executeUpdate();
    }
}
