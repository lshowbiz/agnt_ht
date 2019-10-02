
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiSunOrder;
import com.joymain.jecs.fi.dao.JfiSunOrderDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiSunOrderDaoHibernate extends BaseDaoHibernate implements JfiSunOrderDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunOrderDao#getJfiSunOrders(com.joymain.jecs.fi.model.JfiSunOrder)
     */
    public List getJfiSunOrders(final JfiSunOrder jfiSunOrder) {
        return getHibernateTemplate().find("from JfiSunOrder");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiSunOrder == null) {
            return getHibernateTemplate().find("from JfiSunOrder");
        } else {
            // filter on properties set in the jfiSunOrder
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiSunOrder).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiSunOrder.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunOrderDao#getJfiSunOrder(Long moId)
     */
    public JfiSunOrder getJfiSunOrder(final Long moId) {
        JfiSunOrder jfiSunOrder = (JfiSunOrder) getHibernateTemplate().get(JfiSunOrder.class, moId);
        if (jfiSunOrder == null) {
            log.warn("uh oh, jfiSunOrder with moId '" + moId + "' not found...");
            throw new ObjectRetrievalFailureException(JfiSunOrder.class, moId);
        }

        return jfiSunOrder;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunOrderDao#saveJfiSunOrder(JfiSunOrder jfiSunOrder)
     */    
    public void saveJfiSunOrder(final JfiSunOrder jfiSunOrder) {
        getHibernateTemplate().saveOrUpdate(jfiSunOrder);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunOrderDao#removeJfiSunOrder(Long moId)
     */
    public void removeJfiSunOrder(final Long moId) {
        getHibernateTemplate().delete(getJfiSunOrder(moId));
    }

	/**
	 * 根据条件统计商品销售量
	 * 
	 * @param crm
	 * @return
	 */
	public List getStatisticProductSale(CommonRecord crm) {
		String sql = "Select c.product_no as product_no, c.product_name as product_name, a.s_Price as s_Price, Sum(a.s_Price*a.Qty) as sprice, Sum(a.Qty) as qty, Sum(a.s_rPrice*a.r_Qty) as srprice, Sum(a.r_Qty) as rqty From Jfi_Sun_Order_List a, jpm_product_sale_new c";
		sql += " Where a.Product_Id = c.Uni_No And Exists (Select 1 From Jfi_Sun_Order b Where a.mo_Id = b.mo_Id";

		String companyCode = crm.getString("companyCode", "");
		if (!"AA".equals(companyCode) && !StringUtils.isEmpty(companyCode)) {
			sql += " And b.company_Code = '" + companyCode + "'";
		}

		String memberOrderNo = crm.getString("memberOrderNo", "");
		if (!StringUtils.isEmpty(memberOrderNo)) {
			sql += " And b.member_Order_No = '" + memberOrderNo + "'";
		}

		String province = crm.getString("province", "");
		if (!StringUtils.isEmpty(province)) {
			sql += " and b.province = '" + province + "'";
		}

		String city = crm.getString("city", "");
		if (!StringUtils.isEmpty(city)) {
			sql += " and b.city = '" + city + "'";
		}

		String district = crm.getString("district", "");
		if (!StringUtils.isEmpty(district)) {
			sql += " and b.district = '" + district + "'";
		}

		String userCode = crm.getString("sysUser.userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			sql += " and b.user_code = '" + userCode + "'";
		}

		String agentNo = crm.getString("agentNo", "");
		if (!StringUtils.isEmpty(agentNo)) {
			sql += " and b.agent_No = '" + agentNo + "'";
		}

		String orderType = crm.getString("orderType", "");
		if (!StringUtils.isEmpty(orderType)) {
			sql += " and b.order_Type='" + orderType + "'";
		}

		String sorderType = crm.getString("sorderType", "");
		if (!StringUtils.isEmpty(sorderType)) {
			sql += " and b.s_order_Type='" + sorderType + "'";
		}

		String isDisable = crm.getString("isDisable", "");
		if (!StringUtils.isEmpty(isDisable)) {
			sql += " and b.is_Disable='" + isDisable + "'";
		}

		String locked = crm.getString("locked", "");
		if (!StringUtils.isEmpty(locked)) {
			sql += " and locked='" + locked + "'";
		}

		String status = crm.getString("status", "");
		if (!StringUtils.isEmpty(status)) {
			sql += " And b.status = '" + status + "'";
		}

		String startReturnTime = crm.getString("startReturnTime", "");
		if (!StringUtils.isEmpty(startReturnTime)) {
			sql += " and return_Time>=to_date('" + startReturnTime
					+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}

		String endReturnTime = crm.getString("endReturnTime", "");
		if (!StringUtils.isEmpty(endReturnTime)) {
			sql += " and return_Time<=to_date('" + endReturnTime
					+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}

		String logType = crm.getString("logType", "");
		if (!StringUtils.isEmpty(logType)) {
			String startLogTime = crm.getString("startLogTime", "");
			if (!StringUtils.isEmpty(startLogTime)) {
				if ("A".equals(logType)) {
					sql += " and b.order_Time>=to_date('" + startLogTime
							+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("C".equals(logType)) {
					sql += " and b.check_Date>=to_date('" + startLogTime
							+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("BC".equals(logType)) {
					sql += " and b.check_Time>=to_date('" + startLogTime
							+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				}
			}
			String endLogTime = crm.getString("endLogTime", "");
			if (!StringUtils.isEmpty(endLogTime)) {
				if ("A".equals(logType)) {
					sql += " and b.order_Time<=to_date('" + endLogTime
							+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("C".equals(logType)) {
					sql += " and b.check_Date<=to_date('" + endLogTime
							+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("BC".equals(logType)) {
					sql += " and b.check_Time<=to_date('" + endLogTime
							+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				}
			}
		}

		sql += ") Group By a.Product_Id,c.product_no,c.product_name,s_Price";

		return this.findObjectsBySQL(sql);
	}

	// added for getJpoMemberOrdersByCrm
	public List getJfiSunOrdersByCrm(CommonRecord crm, Pager pager) {
		String hql = "from JfiSunOrder jfiSunOrder where 1=1";

		hql += this.buildListHqlQuery(crm);

		if (!pager.getLimit().getSort().isSorted()) {
			// sort
			hql += " order by moId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty()
					+ " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}

	/**
	 * 根据外部参数生成查询语句
	 * 
	 * @param crm
	 * @return
	 */
	private String buildListHqlQuery(CommonRecord crm) {
		String hql = "";

		String companyCode = crm.getString("companyCode", "");
		if (!"AA".equals(companyCode) && !StringUtils.isEmpty(companyCode)) {
			hql += " and companyCode='" + companyCode + "'";
		}

		String userCode = crm.getString("sysUser.userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and sysUser.userCode='" + userCode + "'";
		}

		String agentNo = crm.getString("agentNo", "");
		if (!StringUtils.isEmpty(agentNo)) {
			hql += " and agentNo='" + agentNo + "'";
		}

		String orderType = crm.getString("orderType", "");
		if (!StringUtils.isEmpty(orderType)) {
			hql += " and orderType='" + orderType + "'";
		}

		String sorderType = crm.getString("sorderType", "");
		if (!StringUtils.isEmpty(sorderType)) {
			hql += " and sorderType='" + sorderType + "'";
		}

		String orderTypeIn = crm.getString("orderTypeIn", "");
		if (!StringUtils.isEmpty(orderTypeIn)) {
			hql += " and orderType in (" + orderTypeIn + ")";
		}

		String isSpecial = crm.getString("isSpecial", "");
		if (!StringUtils.isEmpty(isSpecial)) {
			hql += " and isSpecial='" + isSpecial + "'";
		}

		String isDisable = crm.getString("isDisable", "");
		if (!StringUtils.isEmpty(isDisable)) {
			hql += " and isDisable='" + isDisable + "'";
		}

		String status = crm.getString("status", "");
		if (!StringUtils.isEmpty(status)) {
			hql += " and status='" + status + "'";
		}

		String isPay = crm.getString("isPay", "");
		if (!StringUtils.isEmpty(isPay)) {
			hql += " and isPay='" + isPay + "'";
		}

		String province = crm.getString("province", "");
		if (!StringUtils.isEmpty(province)) {
			hql += " and province = '" + province + "'";
		}

		String city = crm.getString("city", "");
		if (!StringUtils.isEmpty(city)) {
			hql += " and city = '" + city + "'";
		}

		String district = crm.getString("district", "");
		if (!StringUtils.isEmpty(district)) {
			hql += " and district = '" + district + "'";
		}

		String locked = crm.getString("locked", "");
		if (!StringUtils.isEmpty(locked)) {
			hql += " and locked='" + locked + "'";
		}

		String submitStatus = crm.getString("submitStatus", "");
		if (!StringUtils.isEmpty(submitStatus)) {
			hql += " and submitStatus='" + submitStatus + "'";
		}

		String memberOrderNo = crm.getString("memberOrderNo", "");
		if (!StringUtils.isEmpty(memberOrderNo)) {
			hql += " and memberOrderNo='" + memberOrderNo + "'";
		}

		String startOrderTime = crm.getString("startOrderTime", "");
		if (!StringUtils.isEmpty(startOrderTime)) {
			hql += " and orderTime>=to_date('" + startOrderTime
					+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}

		String endOrderTime = crm.getString("endOrderTime", "");
		if (!StringUtils.isEmpty(endOrderTime)) {
			hql += " and orderTime<=to_date('" + endOrderTime
					+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}

		String startReturnTime = crm.getString("startReturnTime", "");
		if (!StringUtils.isEmpty(startReturnTime)) {
			hql += " and returnTime>=to_date('" + startReturnTime
					+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}

		String endReturnTime = crm.getString("endReturnTime", "");
		if (!StringUtils.isEmpty(endReturnTime)) {
			hql += " and returnTime<=to_date('" + endReturnTime
					+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}

		String startOrderCheckTime = crm.getString("startOrderCheckTime", "");
		if (!StringUtils.isEmpty(startOrderCheckTime)) {
			hql += " and checkTime>=to_date('" + startOrderCheckTime
					+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}

		String endOrderCheckTime = crm.getString("endOrderCheckTime", "");
		if (!StringUtils.isEmpty(endOrderCheckTime)) {
			hql += " and checkTime<=to_date('" + endOrderCheckTime
					+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}

		String inPeriod = crm.getString("inPeriod", "");
		if (!StringUtils.isEmpty(inPeriod)) {
			if ("A".equals(inPeriod)) {
				hql += " and checkDate>=to_date('"
						+ crm.getString("inPeriodStartTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss')";
				hql += " and checkDate< to_date('"
						+ crm.getString("inPeriodEndTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss')";
				hql += " and (checkTime< to_date('"
						+ crm.getString("inPeriodStartTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss')";
				hql += " or checkTime>= to_date('"
						+ crm.getString("inPeriodEndTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss'))";
			} else if ("D".equals(inPeriod)) {
				hql += " and checkTime>=to_date('"
						+ crm.getString("inPeriodStartTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss')";
				hql += " and checkTime< to_date('"
						+ crm.getString("inPeriodEndTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss')";
				hql += " and (checkDate< to_date('"
						+ crm.getString("inPeriodStartTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss')";
				hql += " or checkDate>= to_date('"
						+ crm.getString("inPeriodEndTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss'))";
			}
		}

		String mode = crm.getString("mode", "");
		if (!StringUtils.isEmpty(mode)) {
			hql += " and sysUser.userCode in (select sysUser.userCode from JfiBankbookJournal b where journalId=(select max(journalId) from JfiBankbookJournal c where c.sysUser.userCode = b.sysUser.userCode";
			String company = crm.getString("company", "");
			if (!StringUtils.isEmpty(company)) {
				hql += " and c.companyCode = '" + company + "'";
			}
			hql += " ) and b.balance>=" + mode + ")";
		}

		String logType = crm.getString("logType", "");
		if (!StringUtils.isEmpty(logType)) {
			String startLogTime = crm.getString("startLogTime", "");
			if (!StringUtils.isEmpty(startLogTime)) {
				if ("A".equals(logType)) {
					hql += " and orderTime>=to_date('" + startLogTime
							+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("C".equals(logType)) {
					hql += " and checkDate>=to_date('" + startLogTime
							+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("BC".equals(logType)) {
					hql += " and checkTime>=to_date('" + startLogTime
							+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				}
			}
			String endLogTime = crm.getString("endLogTime", "");
			if (!StringUtils.isEmpty(endLogTime)) {
				if ("A".equals(logType)) {
					hql += " and orderTime<=to_date('" + endLogTime
							+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("C".equals(logType)) {
					hql += " and checkDate<=to_date('" + endLogTime
							+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("BC".equals(logType)) {
					hql += " and checkTime<=to_date('" + endLogTime
							+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				}
			}
		}

		return hql;
	}
}
