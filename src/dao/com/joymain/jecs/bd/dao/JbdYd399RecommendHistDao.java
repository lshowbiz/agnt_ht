package com.joymain.jecs.bd.dao;

import java.util.List;

import com.joymain.jecs.bd.model.JbdYd399RecommendHist;
import com.joymain.jecs.dao.Dao;


public interface JbdYd399RecommendHistDao extends Dao {

	public List getJbdYd399RecommendHists(JbdYd399RecommendHist jbdYd399RecommendHist);

	public JbdYd399RecommendHist getJbdYd399RecommendHist(final Long id);

	public void saveJbdYd399RecommendHist(JbdYd399RecommendHist jbdYd399RecommendHist);

	public void save(JbdYd399RecommendHist jbdYd399RecommendHist);

//	public List getJbdYd399RecommendHistsByCrm(CommonRecord crm, Pager pager);

//    public void removeJbdYd399RecommendHist(final Long id);
    
//	public List getJbdYd399RecommendHistsByCrmBySql(CommonRecord crm, Pager pager);
}
