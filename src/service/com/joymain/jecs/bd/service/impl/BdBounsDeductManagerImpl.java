package com.joymain.jecs.bd.service.impl;

import java.util.List;

import com.joymain.jecs.bd.dao.BdBounsDeductDao;
import com.joymain.jecs.bd.model.BdBounsDeduct;
import com.joymain.jecs.bd.service.BdBounsDeductManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class BdBounsDeductManagerImpl extends BaseManager implements BdBounsDeductManager {
	private BdBounsDeductDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setBdBounsDeductDao(BdBounsDeductDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.bd.service.BdBounsDeductManager#getBdBounsDeducts(com.joymain.jecs.bd.model.BdBounsDeduct)
	 */
	public List getBdBounsDeducts(final BdBounsDeduct bdBounsDeduct) {
		return dao.getBdBounsDeducts(bdBounsDeduct);
	}

	/**
	 * @see com.joymain.jecs.bd.service.BdBounsDeductManager#getBdBounsDeduct(String id)
	 */
	public BdBounsDeduct getBdBounsDeduct(final String id) {
		return dao.getBdBounsDeduct(new Long(id));
	}

	/**
	 * @see com.joymain.jecs.bd.service.BdBounsDeductManager#saveBdBounsDeduct(BdBounsDeduct bdBounsDeduct)
	 */
	public void saveBdBounsDeduct(BdBounsDeduct bdBounsDeduct) {
		dao.saveBdBounsDeduct(bdBounsDeduct);
	}

	/**
	 * @see com.joymain.jecs.bd.service.BdBounsDeductManager#removeBdBounsDeduct(String id)
	 */
	public void removeBdBounsDeduct(final String id) {
		dao.removeBdBounsDeduct(new Long(id));
	}

	//added for getBdBounsDeductsByCrm
	public List getBdBounsDeductsByCrm(CommonRecord crm, Pager pager) {
		return dao.getBdBounsDeductsByCrm(crm, pager);
	}

	public void removeBdBounsDeducts(List bdBounsDeducts) {
		dao.removeBdBounsDeducts(bdBounsDeducts);
	}

	public void saveBdBounsDeducts(List bdBounsDeducts) {
		dao.saveBdBounsDeducts(bdBounsDeducts);

	}

	/**
	 * 获取某批号下已扣款的扣补数据行数
	 * @param treatedNo
	 * @return
	 */
	public long getDeductedCountByTreatedNo(final String treatedNo) {
		return dao.getDeductedCountByTreatedNo(treatedNo);
	}
	
	/**
	 * 删除某批号所对应的扣补数据
	 * @param treatedNo
	 * @return 删除的记录数
	 */
	public int removeBdBounsDeductsByTreatedNo(final String treatedNo){
		return dao.removeBdBounsDeductsByTreatedNo(treatedNo);
	}

	public List getBdBounsDeductsByCrmBySql(CommonRecord crm, Pager pager) {
		return dao.getBdBounsDeductsByCrmBySql(crm, pager);
	}
}
