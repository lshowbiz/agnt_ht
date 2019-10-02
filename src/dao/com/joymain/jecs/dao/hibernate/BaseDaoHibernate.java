package com.joymain.jecs.dao.hibernate;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.joymain.jecs.dao.Dao;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.joymain.jecs.util.data.AbstractDAO;
/**
 * This class serves as the Base class for all other Daos - namely to hold
 * common methods that they might all use. Can be used for standard CRUD
 * operations.</p>
 *
 * <p><a href="BaseDaoHibernate.java.html"><i>View Source</i></a></p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class BaseDaoHibernate extends AbstractDAO implements Dao {
    protected final Log log = LogFactory.getLog(getClass());
    private Date dbDate;
    /**
     * @see com.joymain.jecs.dao.Dao#saveObject(java.lang.Object)
     */
    public void saveObject(Object o) {
        getHibernateTemplate().saveOrUpdate(o);
    }

    /**
     * @see com.joymain.jecs.dao.Dao#getObject(java.lang.Class, java.io.Serializable)
     */
    public Object getObject(Class clazz, Serializable id) {
        Object o = getHibernateTemplate().get(clazz, id);

        if (o == null) {
            throw new ObjectRetrievalFailureException(clazz, id);
        }

        return o;
    }

    /**
     * @see com.joymain.jecs.dao.Dao#getObjects(java.lang.Class)
     */
    public List getObjects(Class clazz) {
        return getHibernateTemplate().loadAll(clazz);
    }

    /**
     * @see com.joymain.jecs.dao.Dao#removeObject(java.lang.Class, java.io.Serializable)
     */
    public void removeObject(Class clazz, Serializable id) {
        getHibernateTemplate().delete(getObject(clazz, id));
    }

    public Date getDbDate() {
        if(dbDate !=null){
            return dbDate;
        } else {
            String sql = "select sysdate from dual";
            List list = this.jdbcTemplate.queryForList(sql);
            Map map = (Map)list.get(0);
            Date date = (Date)map.get("SYSDATE");
            return date;
        }
    }
    public void setDbDate(Date dbDate) {
        this.dbDate = dbDate;
    }
}
