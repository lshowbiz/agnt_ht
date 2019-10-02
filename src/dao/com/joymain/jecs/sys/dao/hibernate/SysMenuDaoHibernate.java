package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.dao.SysMenuDao;
import com.joymain.jecs.sys.model.SysMenu;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysMenuDaoHibernate extends BaseDaoHibernate implements SysMenuDao {

	public List getAllSysMenus() {
		DetachedCriteria ca = DetachedCriteria.forClass(SysMenu.class);
		ca.addOrder(Order.asc("menuOrder"));
		return getHibernateTemplate().findByCriteria(ca);
	}

	public List getSysMenusByPage(final CommonRecord crm, Pager pager) {
		String hqlQuery = "from SysMenu where 1=1";

		String searchFlag = crm.getString("search", "");
		if (StringUtils.isEmpty(searchFlag)) {
			hqlQuery += " and parentId=" + crm.getString("parentId");
		} else {

			hqlQuery += " and menuName like '%" + crm.getString("searchMenuName") + "%'";
			hqlQuery += " and title like '%" + crm.getString("searchTitle") + "%'";

		}

		// 设置排序
		if (!pager.getLimit().getSort().isSorted()) {
			// 缺省排序
			hqlQuery += " order by menuOrder asc";
		} else {
			hqlQuery += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}

		List sysMenus = this.findObjectsByHqlQuery(hqlQuery, pager);

		return sysMenus;
	}

	public int getSubMenuCounts(String menuId) {
		String hqlQuery = "from SysMenu where parentId=" + menuId;
		return getTotalCountByHQL(hqlQuery);
	}

	public SysMenu getSysMenu(String menuId) throws ObjectRetrievalFailureException {
		SysMenu sysMenu = (SysMenu) getHibernateTemplate().get(SysMenu.class, new Long(menuId));
		if (sysMenu == null) {
			log.warn("uh oh, sysMenu with menuId '" + menuId + "' not found...");
			throw new ObjectRetrievalFailureException(SysMenu.class, menuId);
		}
		return sysMenu;
	}

	public void saveSysMenu(SysMenu sysMenu) {
		getHibernateTemplate().saveOrUpdate(sysMenu);
	}

	public void removeSysMenu(String menuId) {
		String hqlQuery="delete from SysMenu where menuId="+menuId;
		this.executeUpdate(hqlQuery);

	}
	
	/**
	 * 根据URL获取对应的菜单
	 * @param address
	 * @return
	 */
	public SysMenu getSysMenuByUrl(String address){
		return (SysMenu)this.getObjectByHqlQuery("from SysMenu where address='"+address+"'");
	}
}
