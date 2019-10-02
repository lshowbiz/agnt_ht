package com.joymain.jecs.bd.dao.hibernate;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.bd.dao.BdBounsDeductDao;
import com.joymain.jecs.bd.model.BdBounsDeduct;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
public class BdBounsDeductDaoHibernate extends BaseDaoHibernate implements BdBounsDeductDao {
	/**
	 * @see com.joymain.jecs.bd.dao.BdBounsDeductDao#getBdBounsDeducts(com.joymain.jecs.bd.model.BdBounsDeduct)
	 */
	public List getBdBounsDeducts(final BdBounsDeduct bdBounsDeduct) {
		return getHibernateTemplate().findByExample(bdBounsDeduct);
	}

	/**
	 * @see com.joymain.jecs.bd.dao.BdBounsDeductDao#getBdBounsDeduct(BigDecimal id)
	 */
	public BdBounsDeduct getBdBounsDeduct(final Long id) {
		BdBounsDeduct bdBounsDeduct = (BdBounsDeduct) getHibernateTemplate().get(BdBounsDeduct.class, id);
		if (bdBounsDeduct == null) {
			log.warn("uh oh, bdBounsDeduct with id '" + id + "' not found...");
			throw new ObjectRetrievalFailureException(BdBounsDeduct.class, id);
		}

		return bdBounsDeduct;
	}

	/**
	 * @see com.joymain.jecs.bd.dao.BdBounsDeductDao#saveBdBounsDeduct(BdBounsDeduct bdBounsDeduct)
	 */
	public void saveBdBounsDeduct(final BdBounsDeduct bdBounsDeduct) {
		getHibernateTemplate().saveOrUpdate(bdBounsDeduct);
	}

	/**
	 * 批量保存奖金扣补数据
	 * @param bdBounsDeducts
	 */
	public void saveBdBounsDeducts(List bdBounsDeducts) {
		getHibernateTemplate().saveOrUpdateAll(bdBounsDeducts);
	}

	/**
	 * @see com.joymain.jecs.bd.dao.BdBounsDeductDao#removeBdBounsDeduct(BigDecimal id)
	 */
	public void removeBdBounsDeduct(final Long id) {
		getHibernateTemplate().delete(getBdBounsDeduct(id));
	}

	//added for getBdBounsDeductsByCrm
	public List getBdBounsDeductsByCrm(CommonRecord crm, Pager pager) {
		String hql = "from BdBounsDeduct bdBounsDeduct where 1=1";

		String status = crm.getString("status", "");
		if (!StringUtil.isEmpty(status)) {
			hql += " and bdBounsDeduct.status='" + status + "'";
		}
		String userCode = crm.getString("userCode", "");
		if (!StringUtil.isEmpty(userCode)) {
			hql += " and bdBounsDeduct.jmiMember.userCode='" + userCode + "'";
		}
		String name = crm.getString("name", "");
		if (!StringUtil.isEmpty(name)) {
			hql += " and bdBounsDeduct.jmiMember.miUserName='" + name + "'";
		}
		String createName = crm.getString("createName", "");
		if (!StringUtil.isEmpty(createName)) {
			hql += " and bdBounsDeduct.createName='" + createName + "'";
		}

		String createCode = crm.getString("createCode", "");
		if (!StringUtil.isEmpty(createCode)) {
			hql += " and bdBounsDeduct.createCode='" + createCode + "'";
		}

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String startCreateTime = crm.getString("startCreateTime", "");
		String endCreateTime = crm.getString("endCreateTime", "");
		if (!StringUtils.isEmpty(startCreateTime)) {
			try {
				hql += " and bdBounsDeduct.createTime >=to_date('" + dateFormat.format(dateFormat.parse(startCreateTime)) + " 00:00:00','yyyy-MM-dd hh24:mi:ss') ";
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!StringUtils.isEmpty(endCreateTime)) {
			try {
				hql += " and bdBounsDeduct.createTime <=to_date('" + dateFormat.format(dateFormat.parse(endCreateTime)) + " 23:59:59','yyyy-MM-dd hh24:mi:ss')  ";
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		String startCheckTime = crm.getString("startCheckTime", "");
		String endCheckTime = crm.getString("endCheckTime", "");
		if (!StringUtils.isEmpty(startCheckTime)) {
			try {
				hql += " and bdBounsDeduct.checkTime >=to_date('" + dateFormat.format(dateFormat.parse(startCheckTime)) + " 00:00:00','yyyy-MM-dd hh24:mi:ss') ";
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!StringUtils.isEmpty(endCheckTime)) {
			try {
				hql += " and bdBounsDeduct.checkTime <=to_date('" + dateFormat.format(dateFormat.parse(endCheckTime)) + " 23:59:59','yyyy-MM-dd hh24:mi:ss')  ";
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		
		String startMoney = crm.getString("startMoney", "");
		if (!StringUtil.isEmpty(startMoney) && StringUtil.isInteger(startMoney)) {
			hql += " and bdBounsDeduct.money>=" + new BigDecimal(startMoney);
		}

		String endMoney = crm.getString("endMoney", "");
		if (!StringUtil.isEmpty(endMoney) && StringUtil.isInteger(endMoney)) {
			hql += " and bdBounsDeduct.money<=" + new BigDecimal(endMoney);
		}

		String companyCode = crm.getString("companyCode", "");
		if (!StringUtil.isEmpty(companyCode)) {
			hql += " and bdBounsDeduct.companyCode='" + companyCode + "'";
		}
		if (!pager.getLimit().getSort().isSorted()) {
			//
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}

	public void removeBdBounsDeducts(List bdBounsDeducts) {
		getHibernateTemplate().deleteAll(bdBounsDeducts);

	}

	/**
	 * 获取某批号下已扣款的扣补数据行数
	 * @param treatedNo
	 * @return
	 */
	public long getDeductedCountByTreatedNo(final String treatedNo) {
		return this.getTotalByHql("select count(*) from BdBounsDeduct where treatedNo='" + treatedNo + "' and deductTime is not null");
	}
	
	/**
	 * 删除某批号所对应的扣补数据
	 * @param treatedNo
	 * @return 删除的记录数
	 */
	public int removeBdBounsDeductsByTreatedNo(final String treatedNo){
		return this.executeUpdate("delete from BdBounsDeduct where treatedNo='"+treatedNo+"'");
	}

	public List getBdBounsDeductsByCrmBySql(CommonRecord crm, Pager pager) {
		String sql = "select bdBounsDeduct.*,mi.mi_user_name as user_name,mi.card_type from jbd_bouns_deduct bdBounsDeduct ";

    	String name=crm.getString("name", "");
    	if(!StringUtil.isEmpty(name)){
    		sql+=" inner join jsys_user m on m.user_name='"+name+"' and m.user_code=bdBounsDeduct.user_code";
    	}	
		sql+=" left join jmi_member mi on mi.user_code=bdBounsDeduct.user_code";
    	sql+=" where 1=1";
		String status = crm.getString("status", "");
		if (!StringUtil.isEmpty(status)) {
			sql += " and bdBounsDeduct.status='" + status + "'";
		}
		String type = crm.getString("type", "");
		if (!StringUtil.isEmpty(type)) {
			sql += " and bdBounsDeduct.type='" + type + "'";
		}
		String userCode = crm.getString("userCode", "");
		if (!StringUtil.isEmpty(userCode)) {
			sql += " and bdBounsDeduct.user_code='" + userCode + "'";
		}

		String createName = crm.getString("createName", "");
		if (!StringUtil.isEmpty(createName)) {
			sql += " and bdBounsDeduct.create_name='" + createName + "'";
		}

		String createCode = crm.getString("createCode", "");
		if (!StringUtil.isEmpty(createCode)) {
			sql += " and bdBounsDeduct.create_code='" + createCode + "'";
		}

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String startCreateTime = crm.getString("startCreateTime", "");
		String endCreateTime = crm.getString("endCreateTime", "");
		if (!StringUtils.isEmpty(startCreateTime)) {
			try {
				sql += " and bdBounsDeduct.create_time >=to_date('" + dateFormat.format(dateFormat.parse(startCreateTime)) + " 00:00:00','yyyy-MM-dd hh24:mi:ss') ";
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!StringUtils.isEmpty(endCreateTime)) {
			try {
				sql += " and bdBounsDeduct.create_time <=to_date('" + dateFormat.format(dateFormat.parse(endCreateTime)) + " 23:59:59','yyyy-MM-dd hh24:mi:ss') ";
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String startMoney = crm.getString("startMoney", "");
		if (!StringUtil.isEmpty(startMoney) && StringUtil.isInteger(startMoney)) {
			sql += " and bdBounsDeduct.money>=" + new BigDecimal(startMoney);
		}

		String endMoney = crm.getString("endMoney", "");
		if (!StringUtil.isEmpty(endMoney) && StringUtil.isInteger(endMoney)) {
			sql += " and bdBounsDeduct.money<=" + new BigDecimal(endMoney);
		}

		String companyCode = crm.getString("companyCode", "");
		if (!StringUtil.isEmpty(companyCode)) {
			sql += " and bdBounsDeduct.company_code='" + companyCode + "'";
		}
		String id = crm.getString("id", "");
		if (!StringUtil.isEmpty(id)) {
			sql += " and bdBounsDeduct.id="+ id ;
		}
		if (!pager.getLimit().getSort().isSorted()) {
			//
			sql += " order by id desc";
		} else {
			sql += " ORDER BY bdBounsDeduct." + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsBySQL(sql, pager);
	}
}