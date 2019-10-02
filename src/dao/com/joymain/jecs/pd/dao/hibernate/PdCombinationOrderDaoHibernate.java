
package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdCombinationOrder;
import com.joymain.jecs.pd.dao.PdCombinationOrderDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class PdCombinationOrderDaoHibernate extends BaseDaoHibernate implements PdCombinationOrderDao {

    /**
     * @see com.joymain.jecs.pd.dao.PdCombinationOrderDao#getPdCombinationOrders(com.joymain.jecs.pd.model.PdCombinationOrder)
     */
    public List getPdCombinationOrders(final PdCombinationOrder pdCombinationOrder) {
        return getHibernateTemplate().find("from PdCombinationOrder");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdCombinationOrder == null) {
            return getHibernateTemplate().find("from PdCombinationOrder");
        } else {
            // filter on properties set in the pdCombinationOrder
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdCombinationOrder).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdCombinationOrder.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdCombinationOrderDao#getPdCombinationOrder(String pcNo)
     */
    public PdCombinationOrder getPdCombinationOrder(final String pcNo) {
        PdCombinationOrder pdCombinationOrder = (PdCombinationOrder) getHibernateTemplate().get(PdCombinationOrder.class, pcNo);
        if (pdCombinationOrder == null) {
            log.warn("uh oh, pdCombinationOrder with pcNo '" + pcNo + "' not found...");
            throw new ObjectRetrievalFailureException(PdCombinationOrder.class, pcNo);
        }

        return pdCombinationOrder;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdCombinationOrderDao#savePdCombinationOrder(PdCombinationOrder pdCombinationOrder)
     */    
    public void savePdCombinationOrder(final PdCombinationOrder pdCombinationOrder) {
        getHibernateTemplate().saveOrUpdate(pdCombinationOrder);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdCombinationOrderDao#removePdCombinationOrder(String pcNo)
     */
    public void removePdCombinationOrder(final String pcNo) {
        getHibernateTemplate().delete(getPdCombinationOrder(pcNo));
    }
    //added for getPdCombinationOrdersByCrm
    public List getPdCombinationOrdersByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdCombinationOrder pdCombinationOrder where 1=1";
    	
    	String pcNo = crm.getString("pcNo","");
    	if(StringUtils.isNotEmpty(pcNo)){
    		hql +=" and pdCombinationOrder.pcNo like '%"+ pcNo +"%'";
    	}
    	
    	String orderFlagDefault = crm.getString("orderFlagDefault","");
    	if(StringUtils.isNotEmpty(orderFlagDefault)){
    		hql +=" and pdCombinationOrder.orderFlag in("+ orderFlagDefault +")";
    	}
    	
    	String orderFlag = crm.getString("orderFlag","");
    	if(StringUtils.isNotEmpty(orderFlag)){
    		hql +=" and pdCombinationOrder.orderFlag ="+ orderFlag +" ";
    	}
    	String companyCode = crm.getString("companyCode","");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hql +=" and pdCombinationOrder.companyCode ='"+ companyCode +"'";
    	}
    	
    	String warehouseNo = crm.getString("warehouseNo","");
    	if(StringUtils.isNotEmpty(warehouseNo)){
    		hql +=" and pdCombinationOrder.warehouseNo ='"+ warehouseNo +"'";
    	}
    	
    	String productNo = crm.getString("productNo","");
    	if(StringUtils.isNotEmpty(productNo)){
    		hql +=" and pdCombinationOrder.productNo ='"+ productNo +"'";
    	}
    	
    	
    	//创建时间
    	String createTimeStart = crm.getString("createTimeStart","");
    	String createTimeEnd = crm.getString("createTimeEnd","");
    	if(StringUtils.isNotEmpty(createTimeStart)){
    		hql += " and pdCombinationOrder.createTime >=to_date('"+createTimeStart+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
    	}
    	if(StringUtils.isNotEmpty(createTimeEnd)){
    		hql += " and pdCombinationOrder.createTime <=to_date('"+createTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
    	}
    	
    	//确认时间
    	String okTimeBegain = crm.getString("okTimeStart", "");
    	if(StringUtils.isNotEmpty(okTimeBegain)){
    		hql += " and pdCombinationOrder.okTime >=to_date('"+okTimeBegain+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
    	}
    	String okTimeEnd = crm.getString("okTimeEnd", "");
    	if(StringUtils.isNotEmpty(okTimeEnd)){
    		hql += " and pdCombinationOrder.okTime <=to_date('"+okTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
    	}
    	String hasCheckUsrCodeBlank = crm.getString("hasCheckUsrCodeBlank", "0");
    	
    	// 审核者createUsrCode
		String checkUsrCode = crm.getString("checkUsrCode", "");
		if (StringUtils.isNotEmpty(checkUsrCode)) {
			if (hasCheckUsrCodeBlank.equals("1")) {

				hql += " and (pdCombinationOrder.checkUsrCode ='" + checkUsrCode
						+ "' or pdCombinationOrder.checkUsrCode is null)";
			} else {
				hql += " and pdCombinationOrder.checkUsrCode='" + checkUsrCode + "'";
			}

		}
		// 审核者createUsrCode
		String okUsrCode = crm.getString("okUsrCode", "");
		if (StringUtils.isNotEmpty(okUsrCode)) {
			if (hasCheckUsrCodeBlank.equals("1")) {

				hql += " and (pdCombinationOrder.okUsrCode ='" + okUsrCode
						+ "' or pdCombinationOrder.okUsrCode is null)";
			} else {
				hql += " and pdCombinationOrder.okUsrCode='" + okUsrCode + "'";
			}

		}
    	
    	/** 
	---example---
	String userCode = crm.getString("userCode", "");
	if(StringUtils.isNotEmpty(userCode)){
		hql += "???????????";
	}
	 ***/
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by pcNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public void removePdCombinationDetails(PdCombinationOrder pdCombinationOrder) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().deleteAll(pdCombinationOrder.getPdCombinationDetails());
	}
    
}
