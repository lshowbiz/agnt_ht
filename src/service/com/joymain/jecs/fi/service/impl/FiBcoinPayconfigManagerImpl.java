
package com.joymain.jecs.fi.service.impl;

import java.util.Iterator;
import java.util.List;
import java.math.BigDecimal;

import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.FiBcoinPayconfig;
import com.joymain.jecs.fi.model.FiBcoinPayconfigDetail;
import com.joymain.jecs.fi.dao.FiBcoinPayconfigDao;
import com.joymain.jecs.fi.dao.FiBcoinPayconfigDetailDao;
import com.joymain.jecs.fi.service.FiBcoinPayconfigManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiBcoinPayconfigManagerImpl extends BaseManager implements FiBcoinPayconfigManager {
    private FiBcoinPayconfigDao dao;
    private FiBcoinPayconfigDetailDao fiBcoinPayconfigDetailDao;
    
    public void setFiBcoinPayconfigDetailDao(
			FiBcoinPayconfigDetailDao fiBcoinPayconfigDetailDao) {
		this.fiBcoinPayconfigDetailDao = fiBcoinPayconfigDetailDao;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiBcoinPayconfigDao(FiBcoinPayconfigDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBcoinPayconfigManager#getFiBcoinPayconfigs(com.joymain.jecs.fi.model.FiBcoinPayconfig)
     */
    public List getFiBcoinPayconfigs(final FiBcoinPayconfig fiBcoinPayconfig) {
        return dao.getFiBcoinPayconfigs(fiBcoinPayconfig);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBcoinPayconfigManager#getFiBcoinPayconfig(String configId)
     */
    public FiBcoinPayconfig getFiBcoinPayconfig(final String configId) {
        return dao.getFiBcoinPayconfig(new Long(configId));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBcoinPayconfigManager#saveFiBcoinPayconfig(FiBcoinPayconfig fiBcoinPayconfig)
     */
    public void saveFiBcoinPayconfig(FiBcoinPayconfig fiBcoinPayconfig) {
        dao.saveFiBcoinPayconfig(fiBcoinPayconfig);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBcoinPayconfigManager#removeFiBcoinPayconfig(String configId)
     */
    public void removeFiBcoinPayconfig(final String configId) {
        dao.removeFiBcoinPayconfig(new Long(configId));
    }
    //added for getFiBcoinPayconfigsByCrm
    public List getFiBcoinPayconfigsByCrm(CommonRecord crm, Pager pager){
	return dao.getFiBcoinPayconfigsByCrm(crm,pager);
    }

    /**
     * 查询当前时间内积分换购活动
     * @return
     */
	@Override
	public FiBcoinPayconfig getFiBcoinPayconfigByNowTime() {
		
		return dao.getFiBcoinPayconfigByNowTime();
	}

	/**
     * 查询当前订单积分换购商品权限
     * @param jpoMemberOrder
     * @return
     */
	@Override
	public boolean getCanUseCoinPayByOrder(FiBcoinPayconfig fiBcoinPayconfig, JpoMemberOrder jpoMemberOrder) {
		
		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		
		while (its1.hasNext()) {
        	JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
        	//商品编码
        	String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
        	//数量
        	int buNum = jpoMemberOrderList.getQty();
        	
        	FiBcoinPayconfigDetail fiBcoinPayconfigDetail = fiBcoinPayconfigDetailDao.getFiBcoinPayconfigDetailsByProductNo(fiBcoinPayconfig.getConfigId().toString(), productNo);
        	
        	if(("1").equals(fiBcoinPayconfig.getLimit())){//1：限制部分产品不参加，
        		
        		if(fiBcoinPayconfigDetail!=null){
            		
            		return false;
            	}
        	}
        	if(("2").equals(fiBcoinPayconfig.getLimit())){//2：开放部分产品参加
        		
        		if(fiBcoinPayconfigDetail==null){
        			
            		return false;
            	}else{
            		
            		//判断是否超过当前剩余数量
	        		if(buNum > fiBcoinPayconfigDetail.getNowQuantity()){
	        			
	        			return false;
	        		}
        		}
        	}
        }
		
		return true;
	}
}
