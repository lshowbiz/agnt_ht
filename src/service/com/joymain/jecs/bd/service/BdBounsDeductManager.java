package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.bd.model.BdBounsDeduct;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface BdBounsDeductManager extends Manager {
	/**
	 * Retrieves all of the bdBounsDeducts
	 */
	public List getBdBounsDeducts(BdBounsDeduct bdBounsDeduct);

	/**
	 * Gets bdBounsDeduct's information based on id.
	 * @param id the bdBounsDeduct's id
	 * @return bdBounsDeduct populated bdBounsDeduct object
	 */
	public BdBounsDeduct getBdBounsDeduct(final String id);

	/**
	 * Saves a bdBounsDeduct's information
	 * @param bdBounsDeduct the object to be saved
	 */
	public void saveBdBounsDeduct(BdBounsDeduct bdBounsDeduct);

	public void saveBdBounsDeducts(List bdBounsDeducts);

	/**
	 * Removes a bdBounsDeduct from the database by id
	 * @param id the bdBounsDeduct's id
	 */
	public void removeBdBounsDeduct(final String id);

	//added for getBdBounsDeductsByCrm
	public List getBdBounsDeductsByCrm(CommonRecord crm, Pager pager);

	public void removeBdBounsDeducts(List bdBounsDeducts);

	/**
	 * 获取某批号下已扣款的扣补数据行数
	 * @param treatedNo
	 * @return
	 */
	public long getDeductedCountByTreatedNo(final String treatedNo);
	
	/**
	 * 删除某批号所对应的扣补数据
	 * @param treatedNo
	 * @return 删除的记录数
	 */
	public int removeBdBounsDeductsByTreatedNo(final String treatedNo);
	public List getBdBounsDeductsByCrmBySql(CommonRecord crm, Pager pager) ;
}
