
package com.joymain.jecs.bd.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.bd.model.JbdTravelPoint;
import com.joymain.jecs.bd.model.JbdTravelPoint2012;
import com.joymain.jecs.bd.model.JbdTravelPointDetail;
import com.joymain.jecs.bd.model.JbdTravelPointDetail2012;
import com.joymain.jecs.bd.model.JbdTravelPointLog;
import com.joymain.jecs.bd.model.JbdTravelPointLog2012;
import com.joymain.jecs.bd.dao.JbdTravelPoint2012Dao;
import com.joymain.jecs.bd.dao.JbdTravelPointDao;
import com.joymain.jecs.bd.dao.JbdTravelPointDetail2012Dao;
import com.joymain.jecs.bd.service.JbdTravelPointDetail2012Manager;
import com.joymain.jecs.bd.service.JbdTravelPointLog2012Manager;
import com.joymain.jecs.bd.service.JbdTravelPointLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
public class JbdTravelPointDetail2012ManagerImpl extends BaseManager implements JbdTravelPointDetail2012Manager {
    private JbdTravelPointDetail2012Dao dao;

    private JbdTravelPoint2012Dao jbdTravelPoint2012Dao;
    private JbdTravelPointLog2012Manager jbdTravelPointLog2012Manager;

	public void setJbdTravelPointLog2012Manager(
			JbdTravelPointLog2012Manager jbdTravelPointLog2012Manager) {
		this.jbdTravelPointLog2012Manager = jbdTravelPointLog2012Manager;
	}


	public void setJbdTravelPoint2012Dao(JbdTravelPoint2012Dao jbdTravelPoint2012Dao) {
		this.jbdTravelPoint2012Dao = jbdTravelPoint2012Dao;
	}


	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdTravelPointDetail2012Dao(JbdTravelPointDetail2012Dao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointDetail2012Manager#getJbdTravelPointDetail2012s(com.joymain.jecs.bd.model.JbdTravelPointDetail2012)
     */
    public List getJbdTravelPointDetail2012s(final JbdTravelPointDetail2012 jbdTravelPointDetail2012) {
        return dao.getJbdTravelPointDetail2012s(jbdTravelPointDetail2012);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointDetail2012Manager#getJbdTravelPointDetail2012(String id)
     */
    public JbdTravelPointDetail2012 getJbdTravelPointDetail2012(final String id) {
        return dao.getJbdTravelPointDetail2012(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointDetail2012Manager#saveJbdTravelPointDetail2012(JbdTravelPointDetail2012 jbdTravelPointDetail2012)
     */
    public void saveJbdTravelPointDetail2012(JbdTravelPointDetail2012 jbdTravelPointDetail2012) {
        dao.saveJbdTravelPointDetail2012(jbdTravelPointDetail2012);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointDetail2012Manager#removeJbdTravelPointDetail2012(String id)
     */
    public void removeJbdTravelPointDetail2012(final String id) {
        dao.removeJbdTravelPointDetail2012(new Long(id));
    }
    //added for getJbdTravelPointDetail2012sByCrm
    public List getJbdTravelPointDetail2012sByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdTravelPointDetail2012sByCrm(crm,pager);
    }
    
    
    
    
	public void addJbdTravelPointRecord2012(String userCode,String travelType,SysUser defSysUser) {
		JbdTravelPoint2012 jbdTravelPoint2012=jbdTravelPoint2012Dao.getJbdTravelPoint2012(userCode);
		Map map=this.getTravelPoints2012(travelType,defSysUser.getUserType());
		BigDecimal userPoint=new BigDecimal(map.get("point").toString());
		

		if(jbdTravelPoint2012.getPassStar()<StringUtil.formatInt(map.get("passStar").toString())){
			throw new AppException(map.get("passStarError").toString());
		}
		
		if(jbdTravelPoint2012.getTotal().compareTo(userPoint)==-1){
			throw new AppException("积分不足");
		}
//		if(map.get("vips")!=null){
//			List vips=jbdTravelPoint2012Dao.getRecommendVip(userCode, map.get("startDate").toString(), map.get("endDate").toString());
//			if(vips.size()<StringUtil.formatInt(map.get("vips").toString())){
//				throw new AppException(map.get("vipsError").toString());
//			}
//		}
		
		jbdTravelPoint2012.setTotal(jbdTravelPoint2012.getTotal().subtract(userPoint));
		
		jbdTravelPoint2012Dao.saveJbdTravelPoint2012(jbdTravelPoint2012);
		
		Date curDate=new Date();
		JbdTravelPointDetail2012 jbdTravelPointDetail2012 =new JbdTravelPointDetail2012();
		jbdTravelPointDetail2012.setTravelType(travelType);
		jbdTravelPointDetail2012.setUsePoint(userPoint);
		jbdTravelPointDetail2012.setUserCode(userCode);
		jbdTravelPointDetail2012.setCreateTime(curDate);
		jbdTravelPointDetail2012.setCreateUser(defSysUser.getUserCode());
		
		JbdTravelPointLog2012 jbdTravelPointLog2012 =new JbdTravelPointLog2012();
		jbdTravelPointLog2012.setCreateTime(curDate);
		jbdTravelPointLog2012.setCreateUser(defSysUser.getUserCode());
		jbdTravelPointLog2012.setDealType("D");
		jbdTravelPointLog2012.setUsePoint(userPoint);
		jbdTravelPointLog2012.setUserCode(userCode);
		
		
		this.saveJbdTravelPointDetail2012(jbdTravelPointDetail2012);
		jbdTravelPointLog2012Manager.saveJbdTravelPointLog2012(jbdTravelPointLog2012);
	}

	public void removeJbdTravelPointRecord2012(String userCode,String travelType,SysUser defSysUser,String id) {

		JbdTravelPoint2012 jbdTravelPoint2012=jbdTravelPoint2012Dao.getJbdTravelPoint2012(userCode);
		Map map=this.getTravelPoints2012(travelType,defSysUser.getUserType());
		BigDecimal userPoint=new BigDecimal(map.get("point").toString());
		
		jbdTravelPoint2012.setTotal(jbdTravelPoint2012.getTotal().add(userPoint));

		jbdTravelPoint2012Dao.saveJbdTravelPoint2012(jbdTravelPoint2012);
		
		JbdTravelPointDetail2012 jbdTravelPointDetail2012=this.getJbdTravelPointDetail2012(id);
		if(jbdTravelPointDetail2012==null){
			throw new AppException("信息找不到");
		}
		Date curDate=new Date();
		JbdTravelPointLog2012 jbdTravelPointLog2012 =new JbdTravelPointLog2012();
		jbdTravelPointLog2012.setCreateTime(curDate);
		jbdTravelPointLog2012.setCreateUser(defSysUser.getUserCode());
		jbdTravelPointLog2012.setDealType("A");
		jbdTravelPointLog2012.setUsePoint(userPoint);
		jbdTravelPointLog2012.setUserCode(userCode);
		

		jbdTravelPointLog2012Manager.saveJbdTravelPointLog2012(jbdTravelPointLog2012);
		this.removeJbdTravelPointDetail2012(id);
		
		
	}
	
	
	private Map getTravelPoints2012(String travelType,String userType){
		Map map=new HashMap();
		BigDecimal point=new BigDecimal(0);
		Integer passStar=0;
		String startDate="2011-10-01";
		String endDate="2012-9-28";
		String passStarError="";
		if("1".equals(travelType)){
			point=new BigDecimal(6000);
			passStar=2;
			passStarError="必须红宝石及以上奖衔";
		}else if("2".equals(travelType)){
			point=new BigDecimal(30000);
			passStar=6;
			passStarError="必须黑钻及以上奖衔";
		}else{
			throw new AppException("类型找不到");
		}
		map.put("point", point);
		map.put("passStar", passStar);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("passStarError", passStarError);
		
		return map;
	}

	public void changeJbdTravelPoint2012(JbdTravelPointLog2012 jbdTravelPointLog2012, SysUser defSysUser) {
		Date curDate = new Date();
		jbdTravelPointLog2012.setCreateTime(curDate);
		jbdTravelPointLog2012.setCreateUser(defSysUser.getUserCode());

		JbdTravelPoint2012 jbdTravelPoint2012=jbdTravelPoint2012Dao.getJbdTravelPoint2012(jbdTravelPointLog2012.getUserCode());
		
		if("A".equals(jbdTravelPointLog2012.getDealType())){
			jbdTravelPoint2012.setTotal(jbdTravelPoint2012.getTotal().add(jbdTravelPointLog2012.getUsePoint()));
			
		}else if ("D".equals(jbdTravelPointLog2012.getDealType())){
			jbdTravelPoint2012.setTotal(jbdTravelPoint2012.getTotal().subtract(jbdTravelPointLog2012.getUsePoint()));
		}

		jbdTravelPointLog2012Manager.saveJbdTravelPointLog2012(jbdTravelPointLog2012);
		jbdTravelPoint2012Dao.saveJbdTravelPoint2012(jbdTravelPoint2012);
		
		
	}
    
    
    
    
    
}
