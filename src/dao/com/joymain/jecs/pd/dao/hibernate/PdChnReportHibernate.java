package com.joymain.jecs.pd.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.dao.PdChnReportDao;

public class PdChnReportHibernate extends BaseDaoHibernate implements PdChnReportDao {

	
	public List getChnReport() {
		String sql = 
			" select distinct jps.product_no, jps.product_name                "
			+ " from jpm_product_sale_new jps, pd_warehouse_stock pws                "
			+ " where jps.product_no = pws.product_no and pws.company_code = 'CN'"
			+ " and jps.COMPANY_CODE = pws.COMPANY_CODE"
			+ " order by jps.product_name                                        ";
		return this.findObjectsBySQL(sql);
	}
	public List getChnQtyReport() {
		String sql = 
			" select distinct pws.product_no,                   "
//			+ " pws.normal_qty, pws.warehouse_no"
			+ " nvl(pws.normal_qty, 0) as normal_qty, pws.warehouse_no"
			+ " from jpm_product_sale_new jps, pd_warehouse_stock pws "
			+ " where jps.product_no = pws.product_no             "
			+ " and jps.COMPANY_CODE = pws.COMPANY_CODE"
			+ " and pws.company_code = 'CN'                       "
			+ " order by pws.product_no, pws.warehouse_no        ";
		return this.findObjectsBySQL(sql);
	}

	@Override
	public Object getObject(Class clazz, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getObjects(Class clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeObject(Class clazz, Serializable id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveObject(Object o) {
		// TODO Auto-generated method stub

	}

}
