package com.joymain.jecs.sys.dao;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.sys.model.SysMenu;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysMenuDao extends Dao {

	public List getSysMenusByPage(CommonRecord sysMenu, Pager page);
	
	public List getAllSysMenus();

	public int getSubMenuCounts(String menuId);

	public SysMenu getSysMenu(String menuId) throws ObjectRetrievalFailureException;

	public void saveSysMenu(SysMenu sysMenu);

	public void removeSysMenu(String menuId);
	
	/**
	 * 根据URL获取对应的菜单
	 * @param address
	 * @return
	 */
	public SysMenu getSysMenuByUrl(String address);
}
