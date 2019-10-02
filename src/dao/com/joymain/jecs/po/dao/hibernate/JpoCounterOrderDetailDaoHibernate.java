
package com.joymain.jecs.po.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.po.model.JpoCounterOrderDetail;
import com.joymain.jecs.po.dao.JpoCounterOrderDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JpoCounterOrderDetailDaoHibernate extends BaseDaoHibernate implements JpoCounterOrderDetailDao {

    /**
     * @see com.joymain.jecs.po.dao.JpoCounterOrderDetailDao#getJpoCounterOrderDetails(com.joymain.jecs.po.model.JpoCounterOrderDetail)
     */
    public List getJpoCounterOrderDetails(final JpoCounterOrderDetail jpoCounterOrderDetail) {
        return getHibernateTemplate().find("from JpoCounterOrderDetail");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpoCounterOrderDetail == null) {
            return getHibernateTemplate().find("from JpoCounterOrderDetail");
        } else {
            // filter on properties set in the jpoCounterOrderDetail
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpoCounterOrderDetail).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpoCounterOrderDetail.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoCounterOrderDetailDao#getJpoCounterOrderDetail(Long codNo)
     */
    public JpoCounterOrderDetail getJpoCounterOrderDetail(final Long codNo) {
        JpoCounterOrderDetail jpoCounterOrderDetail = (JpoCounterOrderDetail) getHibernateTemplate().get(JpoCounterOrderDetail.class, codNo);
        if (jpoCounterOrderDetail == null) {
            log.warn("uh oh, jpoCounterOrderDetail with codNo '" + codNo + "' not found...");
            throw new ObjectRetrievalFailureException(JpoCounterOrderDetail.class, codNo);
        }

        return jpoCounterOrderDetail;
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoCounterOrderDetailDao#saveJpoCounterOrderDetail(JpoCounterOrderDetail jpoCounterOrderDetail)
     */    
    public void saveJpoCounterOrderDetail(final JpoCounterOrderDetail jpoCounterOrderDetail) {
        getHibernateTemplate().saveOrUpdate(jpoCounterOrderDetail);
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoCounterOrderDetailDao#removeJpoCounterOrderDetail(Long codNo)
     */
    public void removeJpoCounterOrderDetail(final Long codNo) {
        getHibernateTemplate().delete(getJpoCounterOrderDetail(codNo));
    }
    //added for getJpoCounterOrderDetailsByCrm
    public List getJpoCounterOrderDetailsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpoCounterOrderDetail jpoCounterOrderDetail where 1=1";
    	
		String coNo = crm.getString("coNo", "");
		if(StringUtils.isNotEmpty(coNo)){
			hql += " and jpoCounterOrderDetail.jpoCounterOrder.coNo='"+coNo+"'";
		}
		String notZero = crm.getString("notZero", "");
		if(StringUtils.isNotEmpty(notZero)){
			hql +=" and jpoCounterOrderDetail.qty <> 0";
		}
		if(pager == null){
			hql +="  order by jpoCounterOrderDetail.jpmProductSaleNew.jpmProduct.productNo ";
			return this.getHibernateTemplate().find(hql);
		}
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by codNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    public List getJpoCounterOrderDetails(String coNo, Long productId) {
		String hql = "from  JpoCounterOrderDetail pc where pc.poCounterOrder.coNo='"+coNo+"' and pc.jpmProductSaleNew.jpmProduct.uniNo="+productId;
		return this.getHibernateTemplate().find(hql);
	}
	public List getPoCounterSumByCrm(CommonRecord crm) {
		String sql="select nvl(sum( l.price),0) amount, nvl(sum(l.qty),0) qty " +
				"from jpo_counter_order t, jpo_counter_order_detail l " +
				"where t.co_no = l.co_no and t.cs_status = '1'";
		
		
		    	String coNo = crm.getString("coNo", "");
				if (StringUtils.isNotEmpty(coNo)) {
					sql += " and t.co_no='" + coNo + "'";
				}
				
		    	String warehouseNo = crm.getString("warehouseNo", "");
				if (StringUtils.isNotEmpty(warehouseNo)) {
					sql += " and t.warehouse_no='" + warehouseNo + "'";
				}

				String createUserNo = crm.getString("createUserNo", "");
				if (StringUtils.isNotEmpty(createUserNo)) {
					sql += " and t.create_user_no='" + createUserNo + "'";
				}
				
				String csStatus = crm.getString("csStatus", "");
				if (StringUtils.isNotEmpty(csStatus)) {
					sql += " and t.cs_status in (" + csStatus + ")";
				}

				String companyCode = crm.getString("companyCode", "");
				if (!StringUtils.isEmpty(companyCode)) {
					sql += " and t.company_code= '" + companyCode + "'";
				}
				
				String userCode = crm.getString("userCode", "");
				if (StringUtils.isNotEmpty(userCode)) {
					sql += " and t.user_code = '" + userCode + "'";
				}
		
				
				String createTimeStart = crm.getString("createTimeStart","");
		    	String createTimeEnd = crm.getString("createTimeEnd","");
		    	if(StringUtils.isNotEmpty(createTimeStart)){
		    		sql += " and t.create_time >=to_date('"+createTimeStart+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		    	}
		    	if(StringUtils.isNotEmpty(createTimeEnd)){
		    		sql += " and t.create_time <=to_date('"+createTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		    	}
		    	
		    	String okTimeStart = crm.getString("okTimeStart","");
		    	String okTimeEnd = crm.getString("okTimeEnd","");
		    	if(StringUtils.isNotEmpty(okTimeStart)){
		    		sql += " and t.confirm_time >=to_date('"+okTimeStart+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		    	}
		    	if(StringUtils.isNotEmpty(okTimeEnd)){
		    		sql += " and t.confirm_time <=to_date('"+okTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		    	}
		
		
		
		
		
		
		
		

			return this.findObjectsBySQL(sql);
	}
	

	
	public List getTotolPoCounterOrderDetails(CommonRecord crm) {
		
			String hql ="select pm.product_no,pm.product_name, sum(pod.qty) qty,sum(pod.qty*pod.price) amount " +
					"from jpo_counter_order po,jpo_counter_order_detail pod,jpm_product_sale_new pm " +
					"where po.co_no=pod.co_no and pod.product_id=pm.uni_no";
			String coNo = crm.getString("coNo", "");
			if (StringUtils.isNotEmpty(coNo)) {
				hql += " and po.co_No='" + coNo + "'";
			}

			
			String warehouseNo = crm.getString("warehouseNo", "");
			if (StringUtils.isNotEmpty(warehouseNo)) {
				hql += " and po.warehouse_no='" + warehouseNo + "'";
			}
			
			String productNo = crm.getString("productNo","");
	    	if(StringUtils.isNotEmpty(productNo)){
	    		hql += " and pod.product_no='"+productNo+"'";
	    	}
			String companyCode = crm.getString("companyCode", "");
			if (StringUtils.isNotEmpty(companyCode)) {
				hql += " and po.company_Code='" + companyCode + "'";
			}

			String createUserNo = crm.getString("createUserNo", "");
			if (StringUtils.isNotEmpty(createUserNo)) {
				hql += " and po.create_User_No='" + createUserNo + "'";
			}
			
			String csStatus = crm.getString("csStatus", "");
			if (StringUtils.isNotEmpty(csStatus)) {
				hql += " and po.cs_Status in (" + csStatus + ")";
			}

			
			String userCode = crm.getString("userCode", "");
			if (StringUtils.isNotEmpty(userCode)) {
				hql += " and po.user_Code ='" + userCode + "'";
			}

			
			String createTimeStart = crm.getString("createTimeStart","");
	    	String createTimeEnd = crm.getString("createTimeEnd","");
	    	if(StringUtils.isNotEmpty(createTimeStart)){
	    		hql += " and po.create_Time >=to_date('"+createTimeStart+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
	    	}
	    	if(StringUtils.isNotEmpty(createTimeEnd)){
	    		hql += " and po.create_Time <=to_date('"+createTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
	    	}
	    	
	    	String okTimeStart = crm.getString("okTimeStart","");
	    	String okTimeEnd = crm.getString("okTimeEnd","");
	    	if(StringUtils.isNotEmpty(okTimeStart)){
	    		hql += " and po.confirm_Time >=to_date('"+okTimeStart+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
	    	}
	    	if(StringUtils.isNotEmpty(okTimeEnd)){
	    		hql += " and po.confirm_Time <=to_date('"+okTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
	    	}


	    	String shipTimeStart = crm.getString("shipTimeStart","");
	    	String shipTimeEnd = crm.getString("shipTimeEnd","");
	    	if(StringUtils.isNotEmpty(shipTimeStart)){
	    		hql += " and po.ship_time >=to_date('"+shipTimeStart+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
	    	}
	    	if(StringUtils.isNotEmpty(shipTimeEnd)){
	    		hql += " and po.ship_time <=to_date('"+shipTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
	    	}
			
	    	hql +=" group by pm.product_no,pm.product_name having sum(pod.qty) <>0 order by pm.product_no";
	    	log.debug("sql="+hql);
			return this.getJdbcTemplate().queryForList(hql);
		}

}
