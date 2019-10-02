
package com.joymain.jecs.bd.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.bd.model.JbdTravelPoint;
import com.joymain.jecs.bd.model.JbdTravelPointDetail;
import com.joymain.jecs.bd.model.JbdTravelPointLog;
import com.joymain.jecs.bd.dao.JbdTravelPointDao;
import com.joymain.jecs.bd.dao.JbdTravelPointDetailDao;
import com.joymain.jecs.bd.service.JbdTravelPointDetailManager;
import com.joymain.jecs.bd.service.JbdTravelPointLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
public class JbdTravelPointDetailManagerImpl extends BaseManager implements JbdTravelPointDetailManager {
    private JbdTravelPointDetailDao dao;
    private JbdTravelPointDao jbdTravelPointDao;
    private JbdTravelPointLogManager jbdTravelPointLogManager;
    public void setJbdTravelPointLogManager(
			JbdTravelPointLogManager jbdTravelPointLogManager) {
		this.jbdTravelPointLogManager = jbdTravelPointLogManager;
	}

	public void setJbdTravelPointDao(JbdTravelPointDao jbdTravelPointDao) {
		this.jbdTravelPointDao = jbdTravelPointDao;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdTravelPointDetailDao(JbdTravelPointDetailDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointDetailManager#getJbdTravelPointDetails(com.joymain.jecs.bd.model.JbdTravelPointDetail)
     */
    public List getJbdTravelPointDetails(final JbdTravelPointDetail jbdTravelPointDetail) {
        return dao.getJbdTravelPointDetails(jbdTravelPointDetail);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointDetailManager#getJbdTravelPointDetail(String id)
     */
    public JbdTravelPointDetail getJbdTravelPointDetail(final String id) {
        return dao.getJbdTravelPointDetail(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointDetailManager#saveJbdTravelPointDetail(JbdTravelPointDetail jbdTravelPointDetail)
     */
    public void saveJbdTravelPointDetail(JbdTravelPointDetail jbdTravelPointDetail) {
        dao.saveJbdTravelPointDetail(jbdTravelPointDetail);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointDetailManager#removeJbdTravelPointDetail(String id)
     */
    public void removeJbdTravelPointDetail(final String id) {
        dao.removeJbdTravelPointDetail(new Long(id));
    }
    //added for getJbdTravelPointDetailsByCrm
    public List getJbdTravelPointDetailsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdTravelPointDetailsByCrm(crm,pager);
    }

	public void addJbdTravelPointRecord(String userCode,String travelType,SysUser defSysUser) {
		JbdTravelPoint jbdTravelPoint=jbdTravelPointDao.getJbdTravelPoint(userCode);

		Map map=this.getTravelPoints(travelType,defSysUser.getUserType());
		
		if(jbdTravelPoint.getPassStar()<StringUtil.formatInt(map.get("passStar").toString())){
			throw new AppException(map.get("passStarError").toString());
		}
		if(map.get("vips")!=null){
			List vips=jbdTravelPointDao.getRecommendVip(userCode, map.get("startDate").toString(), map.get("endDate").toString());
			if(vips.size()<StringUtil.formatInt(map.get("vips").toString())){
				throw new AppException(map.get("vipsError").toString());
			}
		}
		
		BigDecimal userPoint=new BigDecimal(map.get("point").toString());
		if(jbdTravelPoint.getTotal().compareTo(userPoint)==-1){
			throw new AppException("积分不足");
		}
		
		
		
		jbdTravelPoint.setTotal(jbdTravelPoint.getTotal().subtract(userPoint));
		
		jbdTravelPointDao.saveJbdTravelPoint(jbdTravelPoint);
		
		Date curDate=new Date();
		JbdTravelPointDetail jbdTravelPointDetail =new JbdTravelPointDetail();
		jbdTravelPointDetail.setTravelType(travelType);
		jbdTravelPointDetail.setUsePoint(userPoint);
		jbdTravelPointDetail.setUserCode(userCode);
		jbdTravelPointDetail.setCreateTime(curDate);
		jbdTravelPointDetail.setCreateUser(defSysUser.getUserCode());
		
		JbdTravelPointLog jbdTravelPointLog =new JbdTravelPointLog();
		jbdTravelPointLog.setCreateTime(curDate);
		jbdTravelPointLog.setCreateUser(defSysUser.getUserCode());
		jbdTravelPointLog.setDealType("D");
		jbdTravelPointLog.setUsePoint(userPoint);
		jbdTravelPointLog.setUserCode(userCode);
		
		
		this.saveJbdTravelPointDetail(jbdTravelPointDetail);
		jbdTravelPointLogManager.saveJbdTravelPointLog(jbdTravelPointLog);
	}

	public void removeJbdTravelPointRecord(String userCode,String travelType,SysUser defSysUser,String id) {

		JbdTravelPoint jbdTravelPoint=jbdTravelPointDao.getJbdTravelPoint(userCode);
		Map map=this.getTravelPoints(travelType,defSysUser.getUserType());
		BigDecimal userPoint=new BigDecimal(map.get("point").toString());
		
		jbdTravelPoint.setTotal(jbdTravelPoint.getTotal().add(userPoint));

		jbdTravelPointDao.saveJbdTravelPoint(jbdTravelPoint);
		
		JbdTravelPointDetail jbdTravelPointDetail=this.getJbdTravelPointDetail(id);
		if(jbdTravelPointDetail==null){
			throw new AppException("信息找不到");
		}
		Date curDate=new Date();
		JbdTravelPointLog jbdTravelPointLog =new JbdTravelPointLog();
		jbdTravelPointLog.setCreateTime(curDate);
		jbdTravelPointLog.setCreateUser(defSysUser.getUserCode());
		jbdTravelPointLog.setDealType("D");
		jbdTravelPointLog.setUsePoint(userPoint);
		jbdTravelPointLog.setUserCode(userCode);
		

		jbdTravelPointLogManager.saveJbdTravelPointLog(jbdTravelPointLog);
		this.removeJbdTravelPointDetail(id);
		
		
	}
	
	
	private Map getTravelPoints(String travelType,String userType){
		Map map=new HashMap();
		BigDecimal point=new BigDecimal(0);
		Integer passStar=0;
		Integer vips=0;
		String startDate="2010-10-02";
		String endDate="2011-10-01";
		String passStarError="";
		String vipsError="";
		if("1".equals(travelType)){//一、	季度巴马游奖励
			point=new BigDecimal(1680);
			passStar=2;
			vips=1;
			if("M".equals(userType)){
				endDate="2011-12-24";
			}
			passStarError="必须红宝石及以上奖衔";
			vipsError="至少直接推荐一名VIP";
		}else if("2".equals(travelType)){
			point=new BigDecimal(6000);
			passStar=2;
			vips=3;
			passStarError="必须红宝石及以上奖衔";
			vipsError="至少直接推荐三名VIP";
		}else if("3".equals(travelType)){
			point=new BigDecimal(30000);
			passStar=6;
			vips=3;
			passStarError="必须黑钻及以上奖衔";
			vipsError="至少直接推荐三名VIP";
		}else if("4".equals(travelType)){
			point=new BigDecimal(120000);
			passStar=7;
			vips=null;
			passStarError="必须皇冠及以上奖衔";
		}else{
			throw new AppException("类型找不到");
		}
		map.put("point", point);
		map.put("passStar", passStar);
		map.put("vips", vips);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("passStarError", passStarError);
		map.put("vipsError", vipsError);
		
		return map;
	}

	public void changeJbdTravelPoint(JbdTravelPointLog jbdTravelPointLog, SysUser defSysUser) {
		Date curDate = new Date();
		jbdTravelPointLog.setCreateTime(curDate);
		jbdTravelPointLog.setCreateUser(defSysUser.getUserCode());

		JbdTravelPoint jbdTravelPoint=jbdTravelPointDao.getJbdTravelPoint(jbdTravelPointLog.getUserCode());
		
		if("A".equals(jbdTravelPointLog.getDealType())){
			jbdTravelPoint.setTotal(jbdTravelPoint.getTotal().add(jbdTravelPointLog.getUsePoint()));
			
		}else if ("D".equals(jbdTravelPointLog.getDealType())){
			jbdTravelPoint.setTotal(jbdTravelPoint.getTotal().subtract(jbdTravelPointLog.getUsePoint()));
		}

		jbdTravelPointLogManager.saveJbdTravelPointLog(jbdTravelPointLog);
		jbdTravelPointDao.saveJbdTravelPoint(jbdTravelPoint);
		
		
	}
}
