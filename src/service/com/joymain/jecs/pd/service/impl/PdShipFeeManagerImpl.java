package com.joymain.jecs.pd.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pd.dao.PdShipFeeDao;
import com.joymain.jecs.pd.model.PdShipFee;
import com.joymain.jecs.pd.service.PdShipFeeManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

public class PdShipFeeManagerImpl extends BaseManager implements PdShipFeeManager {
    private PdShipFeeDao dao;
    
	public BigDecimal countFee(String amout, String orderType, String provinceName,boolean b) {
		BigDecimal fee = new BigDecimal(0);
		BigDecimal amoutBD = new BigDecimal(amout);
		String configKey = "criticala";//会员首购单，升级单需支付物流费的临界值
		if (orderType.equals("5")) {
			configKey = "criticalb";//辅消品需支付物流费的临界值
		}
		if (orderType.equals("-5")) {
			return fee;//辅消品不需要支付物流费
		}
		String criticalPointA = Constants.sysConfigMap.get("CN").get(configKey);
		if (StringUtil.isEmpty(criticalPointA)) {
			criticalPointA = "6000";
			if (orderType.equals("5")) {
				criticalPointA = "500";
			}
		}
		BigDecimal criticalPoint = new BigDecimal(criticalPointA);//需要支付物流费用的临界金额
		
		if(b==false){
			if(amoutBD.compareTo(criticalPoint) < 1){
				HashMap<String, BigDecimal> feeMap = dao.getFeeMap();
				return feeMap.get(provinceName) != null ? feeMap.get(provinceName) : new BigDecimal(0);
			}else{
				return criticalPoint;
			}
			
		}else{
			
			//如果订单实付金额amout<=criticalPoint，支付相应收货省份的物流费用
			if (amoutBD.compareTo(criticalPoint) < 1) {
				HashMap<String, BigDecimal> feeMap = dao.getFeeMap();
				fee = feeMap.get(provinceName) != null ? feeMap.get(provinceName) : new BigDecimal(0);
				log.debug("feeMap.size()-------:" + feeMap.size());
			}
			log.debug("amout----:" + amout);
			log.debug("criticalPoint----:" + criticalPoint);
			log.debug("provinceName----:" + provinceName);
			log.debug("fee----:" + fee);
			return fee;
		}
		
	}

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdShipFeeDao(PdShipFeeDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdShipFeeManager#getPdShipFees(com.joymain.jecs.pd.model.PdShipFee)
     */
    public List getPdShipFees(final PdShipFee pdShipFee) {
        return dao.getPdShipFees(pdShipFee);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdShipFeeManager#getPdShipFee(String psfId)
     */
    public PdShipFee getPdShipFee(final String psfId) {
        return dao.getPdShipFee(new String(psfId));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdShipFeeManager#savePdShipFee(PdShipFee pdShipFee)
     */
    public void savePdShipFee(PdShipFee pdShipFee) {
        dao.savePdShipFee(pdShipFee);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdShipFeeManager#removePdShipFee(String psfId)
     */
    public void removePdShipFee(final String psfId) {
        dao.removePdShipFee(new String(psfId));
    }
    //added for getPdShipFeesByCrm
    public List getPdShipFeesByCrm(CommonRecord crm, Pager pager){
	return dao.getPdShipFeesByCrm(crm,pager);
    }
    
    //Add By WuCF 20140813 shell脚本调用存储过程
    public List getPdExportLogByCrm(CommonRecord crm, Pager pager){
    	return dao.getPdExportLogByCrm(crm,pager);
    }
    
    //Add By WuCF 20140909 短信查询功能
    public List getJpmSmssendInfoByCrm(CommonRecord crm, Pager pager){
    	return dao.getJpmSmssendInfoByCrm(crm,pager);
    }

    /**
     * 保存之前先做同一个地区的唯一性校验
     * @author gw 2015-01-20
     * @param pdShipFee
     */
	public boolean getUniqueResult(PdShipFee pdShipFee) {
		return dao.getUniqueResult(pdShipFee);
	}
	
	//Add By WuCF 20141118_接口日志数据
    public List getJocsInterfaceLogByCrm(CommonRecord crm, Pager pager){
    	return dao.getJocsInterfaceLogByCrm(crm,pager);
    }
    
  //Add By WuCF 20141208_接口日志数据重发
    public List getJocsInterfaceRetransmisionByCrm(CommonRecord crm, Pager pager){
    	return dao.getJocsInterfaceRetransmisionByCrm(crm,pager);
    }
}
