
package com.joymain.jecs.al.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.al.model.AlCurrency;
import com.joymain.jecs.al.dao.AlCurrencyDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class AlCurrencyDaoHibernate extends BaseDaoHibernate implements AlCurrencyDao {

    /**
     * @see com.joymain.jecs.al.dao.AlCurrencyDao#getAlCurrencys(com.joymain.jecs.al.model.AlCurrency)
     */
    public List getAlCurrencys(final AlCurrency alCurrency) {
        return getHibernateTemplate().find("from AlCurrency");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (alCurrency == null) {
            return getHibernateTemplate().find("from AlCurrency");
        } else {
            // filter on properties set in the alCurrency
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(alCurrency).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(AlCurrency.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.al.dao.AlCurrencyDao#getAlCurrency(Long currencyId)
     */
    public AlCurrency getAlCurrency(final Long currencyId) {
        AlCurrency alCurrency = (AlCurrency) getHibernateTemplate().get(AlCurrency.class, currencyId);
        if (alCurrency == null) {
            log.warn("uh oh, alCurrency with currencyId '" + currencyId + "' not found...");
            throw new ObjectRetrievalFailureException(AlCurrency.class, currencyId);
        }

        return alCurrency;
    }

    /**
     * @see com.joymain.jecs.al.dao.AlCurrencyDao#saveAlCurrency(AlCurrency alCurrency)
     */    
    public void saveAlCurrency(final AlCurrency alCurrency) {
        getHibernateTemplate().saveOrUpdate(alCurrency);
    }

    /**
     * @see com.joymain.jecs.al.dao.AlCurrencyDao#removeAlCurrency(Long currencyId)
     */
    public void removeAlCurrency(final Long currencyId) {
        getHibernateTemplate().delete(getAlCurrency(currencyId));
    }
}
