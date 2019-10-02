package com.joymain.jecs.po.service.impl;

import java.util.List;
import java.util.Map;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.dao.JpoMemberOrderListDao;
import com.joymain.jecs.po.service.JpoMemberOrderListManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JpoMemberOrderListManagerImpl extends BaseManager implements JpoMemberOrderListManager
{
    private JpoMemberOrderListDao dao;
    
    /**
     * Set the Dao for communication with the data layer.
     * 
     * @param dao
     */
    public void setJpoMemberOrderListDao(JpoMemberOrderListDao dao)
    {
        this.dao = dao;
    }
    
    /**
     * @see com.joymain.jecs.po.service.JpoMemberOrderListManager#getJpoMemberOrderLists(com.joymain.jecs.po.model.JpoMemberOrderList)
     */
    public List getJpoMemberOrderLists(final JpoMemberOrderList jpoMemberOrderList)
    {
        return dao.getJpoMemberOrderLists(jpoMemberOrderList);
    }
    
    /**
     * @see com.joymain.jecs.po.service.JpoMemberOrderListManager#getJpoMemberOrderList(String
     *      molId)
     */
    public JpoMemberOrderList getJpoMemberOrderList(final String molId)
    {
        return dao.getJpoMemberOrderList(new Long(molId));
    }
    
    /**
     * @see com.joymain.jecs.po.service.JpoMemberOrderListManager#saveJpoMemberOrderList(JpoMemberOrderList
     *      jpoMemberOrderList)
     */
    public void saveJpoMemberOrderList(JpoMemberOrderList jpoMemberOrderList)
    {
        dao.saveJpoMemberOrderList(jpoMemberOrderList);
    }
    
    /**
     * @see com.joymain.jecs.po.service.JpoMemberOrderListManager#removeJpoMemberOrderList(String
     *      molId)
     */
    public void removeJpoMemberOrderList(final String molId)
    {
        dao.removeJpoMemberOrderList(new Long(molId));
    }
    
    // added for getJpoMemberOrderListsByCrm
    public List getJpoMemberOrderListsByCrm(CommonRecord crm, Pager pager)
    {
        return dao.getJpoMemberOrderListsByCrm(crm, pager);
    }
    
    /**
     * 根据订单明细流水号查询商品重量等信息　 
     * @param map
     * @return
     */
    @Override
    public JpoMemberOrderList getJpoMemberOrderListByMolId(Map<String, Long> map)
    {
        return dao.getJpoMemberOrderListByMolId(map);
    }

	@Override
	public Integer getSumQtyByProNo() {
		Integer sum1=0, sum2=0,sum3=0,count=0;
		Long pcount1 = dao.getProSumByProNo(com.joymain.jecs.Constants.PRONO);
		Long pcount2 = dao.getProSumByProNo(com.joymain.jecs.Constants.PRONO1);
		Long pcount3 = dao.getProSumByProNo(com.joymain.jecs.Constants.PRONO2);
		
		if(pcount1 !=null){
			sum1 = pcount1.intValue();
		}
		if(pcount2 !=null){
			sum2 = pcount2.intValue();
		}
		if(pcount3 !=null){
			sum3 = pcount3.intValue();
		}
		count = sum1+sum2+sum3;
		
		return count;
	}
}
