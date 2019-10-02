package com.joymain.jecs.po.dao.hibernate;

import java.util.List;

import org.hibernate.Query;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.po.model.JpoMovie;

public class JpoMovieDaoJpa extends BaseDaoHibernate implements com.joymain.jecs.po.dao.JpoMovieDao {

	
	
	@Override
	public List<JpoMovie> findMovieByName(char status) {
		String hql = "from JpoMovie m where m.status=? and m.maccount not like 'UA%' order by m.maccount";
		Query query = this.getSession().createQuery(hql);
		query.setCharacter(0, status);
		return query.list();
	}

	@Override
	public JpoMovie getMovieByOrderNo(String orderNo) {
		String hql = "from JpoMovie m where m.orderNo=?";
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, orderNo);
		
		List<JpoMovie> list = query.list();
		if(list !=null && list.size()>0)
			return list.get(0);	
		else
			return null;
	}

}
