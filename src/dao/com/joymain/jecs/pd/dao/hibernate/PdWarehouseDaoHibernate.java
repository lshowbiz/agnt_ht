
package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdWarehouse;
import com.joymain.jecs.pd.dao.PdWarehouseDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class PdWarehouseDaoHibernate extends BaseDaoHibernate implements PdWarehouseDao {

    /**
     * @see com.joymain.jecs.pd.dao.PdWarehouseDao#getPdWarehouses(com.joymain.jecs.pd.model.PdWarehouse)
     */
    public List getPdWarehouses(final PdWarehouse pdWarehouse) {
        return getHibernateTemplate().find("from PdWarehouse");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdWarehouse == null) {
            return getHibernateTemplate().find("from PdWarehouse");
        } else {
            // filter on properties set in the pdWarehouse
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdWarehouse).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdWarehouse.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdWarehouseDao#getPdWarehouse(String warehouseNo)
     */
    public PdWarehouse getPdWarehouse(final String warehouseNo) {
        PdWarehouse pdWarehouse = (PdWarehouse) getHibernateTemplate().get(PdWarehouse.class, warehouseNo);
//        if (pdWarehouse == null) {
//            log.warn("uh oh, pdWarehouse with warehouseNo '" + warehouseNo + "' not found...");
//            throw new ObjectRetrievalFailureException(PdWarehouse.class, warehouseNo);
//        }

        return pdWarehouse;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdWarehouseDao#savePdWarehouse(PdWarehouse pdWarehouse)
     */    
    public void savePdWarehouse(final PdWarehouse pdWarehouse) {
        getHibernateTemplate().saveOrUpdate(pdWarehouse);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdWarehouseDao#removePdWarehouse(String warehouseNo)
     */
    public void removePdWarehouse(final String warehouseNo) {
        getHibernateTemplate().delete(getPdWarehouse(warehouseNo));
    }
    //added for getPdWarehousesByCrm
    public List getPdWarehousesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdWarehouse wh where 1=1";
		String warehouseNo = crm.getString("warehouseNo", "");
		String warehouseName = crm.getString("warehouseName", "");
		if(StringUtils.isNotEmpty(warehouseNo)){
			hql += " and wh.warehouseNo like '%"+warehouseNo+"%' ";
		}
		if(StringUtils.isNotEmpty(warehouseName)){
			hql += " and wh.warehouseName like '%"+warehouseName+"%'";
		}
		
		String companyNo = crm.getString("companyCode", "");
		if(StringUtils.isNotEmpty(companyNo)){
			hql += " and wh.companyCode='"+companyNo+"'";
		}
		
		String createUsrCode = crm.getString("createUsrCode", "");
		if(StringUtils.isNotEmpty(createUsrCode)){
			hql += " and wh.createUsrCode='"+createUsrCode+"'";
		}
		
		String warehouseLevel = crm.getString("warehouseLevel", "");
		if(StringUtils.isNotEmpty(warehouseLevel)){
			hql += " and wh.warehouseLevel in('"+warehouseLevel+"')";
		}
		
		String shNo = crm.getString("shNo", "");
		if(StringUtils.isNotEmpty(shNo)){
			hql += " and wh.shNo ='"+shNo+"'";
		}
		
		String userCode = crm.getString("userCode", "");
		
		if(StringUtils.isNotEmpty(userCode)){
			hql += " and wh.warehouseNo in(select pdWarehouseUser.warehouseNo from PdWarehouseUser pdWarehouseUser where pdWarehouseUser.userCode='"+userCode+"' )";
		}
		
		if(pager == null){
			hql += " order by  wh.warehouseNo ";
			return this.getHibernateTemplate().find(hql);
		}
		
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by warehouseNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    /**
     * 获取用户受权仓库列表
     * @param crm
     * @return
     */
    public List getPdWarehousesByCrm(CommonRecord crm){
    	String sql = "select a.WAREHOUSE_NO,a.WAREHOUSE_NAME,b.WU_ID from PD_WAREHOUSE a left join PD_WAREHOUSE_USER b on a.WAREHOUSE_NO=b.WAREHOUSE_NO and b.USER_CODE='"+crm.getString("userCode")+"'";
    	List list = this.findObjectsBySQL(sql);
    	return list;
    }
}
