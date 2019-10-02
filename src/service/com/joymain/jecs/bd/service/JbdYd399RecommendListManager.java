package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.bd.model.JbdYd399RecommendList;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdYd399RecommendListManager extends Manager {

	public List getJbdYd399RecommendLists(JbdYd399RecommendList jbdYd399RecommendList);

	public JbdYd399RecommendList getJbdYd399RecommendList(final String id);

//	public void saveJbdYd399RecommendList(JbdYd399RecommendList jbdYd399RecommendList);

//	public void saveJbdYd399RecommendLists(List jbdYd399RecommendLists);


	public List getJbdYd399RecommendListsByCrm(CommonRecord crm, Pager pager);
	
	public List getJbdYd399RecommendListsByCrm2(CommonRecord crm, Pager pager);

    public void removeJbdYd399RecommendList(final String id);
    
	public List getJbdYd399RecommendListsByCrmBySql(CommonRecord crm, Pager pager);
	
    public void saveInJdFiBook(SysUser defSysUser,String id);
}
