package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import org.springframework.orm.ObjectRetrievalFailureException;
import com.joymain.jecs.bd.dao.JbdYd399RecommendHistDao;
import com.joymain.jecs.bd.model.JbdYd399RecommendHist;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;


public class JbdYd399RecommendHistDaoHibernate extends BaseDaoHibernate implements JbdYd399RecommendHistDao {
	
	
	public List getJbdYd399RecommendHists(final JbdYd399RecommendHist jbdYd399RecommendHist) {
		return getHibernateTemplate().findByExample(jbdYd399RecommendHist);
	}


	public JbdYd399RecommendHist getJbdYd399RecommendHist(final Long id) {
		JbdYd399RecommendHist jbdYd399RecommendHist = (JbdYd399RecommendHist) getHibernateTemplate().get(JbdYd399RecommendHist.class, id);
		if (jbdYd399RecommendHist == null) {
			log.warn("uh oh, jbdYd399RecommendHist with id '" + id + "' not found...");
			throw new ObjectRetrievalFailureException(JbdYd399RecommendHist.class, id);
		}
		return jbdYd399RecommendHist;
	}

	public void saveJbdYd399RecommendHist(final JbdYd399RecommendHist jbdYd399RecommendHist) {
		getHibernateTemplate().saveOrUpdate(jbdYd399RecommendHist);
	}
	
	public void save(final JbdYd399RecommendHist jbdYd399RecommendHist) {
		getHibernateTemplate().save(jbdYd399RecommendHist);
	}

	
}