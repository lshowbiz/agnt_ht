
package com.joymain.jecs.bd.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.bd.dao.JbdTravelPoint2013Dao;
import com.joymain.jecs.bd.dao.JbdTravelPointDetail2013Dao;
import com.joymain.jecs.bd.model.JbdTravelPoint2013;
import com.joymain.jecs.bd.model.JbdTravelPointDetail2013;
import com.joymain.jecs.bd.model.JbdTravelPointLog2013;
import com.joymain.jecs.bd.model.JbdTravelPointLog2014;
import com.joymain.jecs.bd.model.JbdTravelPointLog2015;
import com.joymain.jecs.bd.service.JbdTravelPointDetail2013Manager;
import com.joymain.jecs.bd.service.JbdTravelPointDetail2014Manager;
import com.joymain.jecs.bd.service.JbdTravelPointLog2013Manager;
import com.joymain.jecs.bd.service.JbdTravelPointLog2015Manager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
public class JbdTravelPointDetail2013ManagerImpl extends BaseManager implements JbdTravelPointDetail2013Manager {
    private JbdTravelPointDetail2013Dao dao;

    private JbdTravelPoint2013Dao jbdTravelPoint2013Dao;
    private JbdTravelPointLog2013Manager jbdTravelPointLog2013Manager;
    private JbdTravelPointLog2015Manager jbdTravelPointLog2015Manager;
    public void setJbdTravelPointLog2015Manager(
			JbdTravelPointLog2015Manager jbdTravelPointLog2015Manager) {
		this.jbdTravelPointLog2015Manager = jbdTravelPointLog2015Manager;
	}

	private JbdTravelPointDetail2014Manager jbdTravelPointDetail2014Manager;
    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdTravelPointDetail2013Dao(JbdTravelPointDetail2013Dao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointDetail2013Manager#getJbdTravelPointDetail2013s(com.joymain.jecs.bd.model.JbdTravelPointDetail2013)
     */
    public List getJbdTravelPointDetail2013s(final JbdTravelPointDetail2013 jbdTravelPointDetail2013) {
        return dao.getJbdTravelPointDetail2013s(jbdTravelPointDetail2013);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointDetail2013Manager#getJbdTravelPointDetail2013(String id)
     */
    public JbdTravelPointDetail2013 getJbdTravelPointDetail2013(final String id) {
        return dao.getJbdTravelPointDetail2013(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointDetail2013Manager#saveJbdTravelPointDetail2013(JbdTravelPointDetail2013 jbdTravelPointDetail2013)
     */
    public void saveJbdTravelPointDetail2013(JbdTravelPointDetail2013 jbdTravelPointDetail2013) {
        dao.saveJbdTravelPointDetail2013(jbdTravelPointDetail2013);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointDetail2013Manager#removeJbdTravelPointDetail2013(String id)
     */
    public void removeJbdTravelPointDetail2013(final String id) {
        dao.removeJbdTravelPointDetail2013(new Long(id));
    }
    //added for getJbdTravelPointDetail2013sByCrm
    public List getJbdTravelPointDetail2013sByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdTravelPointDetail2013sByCrm(crm,pager);
    }

	public void addJbdTravelPointRecord2013(String userCode, String travelType, SysUser defSysUser) {

		JbdTravelPoint2013 jbdTravelPoint2013=jbdTravelPoint2013Dao.getJbdTravelPoint2013(userCode);
		//Map map=this.getTravelPoints2013(travelType,defSysUser.getUserType());
		
		BigDecimal userPoint=new BigDecimal(0);
		if("1".equals(travelType)){
			userPoint=new BigDecimal(7500);
			List list=jbdTravelPointLog2013Manager.getJbdTravelPointLogByPassStar(userCode, "2");
			if(list.size()<4){
				throw new AppException("至少达成4次红宝石及以上奖衔");
			}
		}else if("2".equals(travelType)){
			userPoint=new BigDecimal(35000);
			List list1=jbdTravelPointLog2013Manager.getJbdTravelPointLogByPassStar(userCode, "7");
			if(list1.size()<1){
				throw new AppException("至少达成1次皇冠及以上奖衔");
			}
			List list2=jbdTravelPointLog2013Manager.getJbdTravelPointLogByPassStar(userCode, "4");
			if(list2.size()<4){
				throw new AppException("4个财政月不低于黄钻奖衔");
			}
		}else{
			throw new AppException("类型找不到");
		}
		
		
		if(jbdTravelPoint2013.getTotal().compareTo(userPoint)==-1){
			throw new AppException("积分不足");
		}
//		if(map.get("vips")!=null){
//			List vips=jbdTravelPoint2013Dao.getRecommendVip(userCode, map.get("startDate").toString(), map.get("endDate").toString());
//			if(vips.size()<StringUtil.formatInt(map.get("vips").toString())){
//				throw new AppException(map.get("vipsError").toString());
//			}
//		}
		
		jbdTravelPoint2013.setTotal(jbdTravelPoint2013.getTotal().subtract(userPoint));
		
		jbdTravelPoint2013Dao.saveJbdTravelPoint2013(jbdTravelPoint2013);
		
		Date curDate=new Date();
		JbdTravelPointDetail2013 jbdTravelPointDetail2013 =new JbdTravelPointDetail2013();
		jbdTravelPointDetail2013.setTravelType(travelType);
		jbdTravelPointDetail2013.setUsePoint(userPoint);
		jbdTravelPointDetail2013.setUserCode(userCode);
		jbdTravelPointDetail2013.setCreateTime(curDate);
		jbdTravelPointDetail2013.setCreateUser(defSysUser.getUserCode());
		
		JbdTravelPointLog2013 jbdTravelPointLog2013 =new JbdTravelPointLog2013();
		jbdTravelPointLog2013.setCreateTime(curDate);
		jbdTravelPointLog2013.setCreateUser(defSysUser.getUserCode());
		jbdTravelPointLog2013.setDealType("D");
		jbdTravelPointLog2013.setUsePoint(userPoint);
		jbdTravelPointLog2013.setUserCode(userCode);
		
		
		this.saveJbdTravelPointDetail2013(jbdTravelPointDetail2013);
		jbdTravelPointLog2013Manager.saveJbdTravelPointLog2013(jbdTravelPointLog2013);
	
		
	}

	public void changeJbdTravelPoint2013(JbdTravelPointLog2013 jbdTravelPointLog2013, SysUser defSysUser) {
		Date curDate = new Date();
		jbdTravelPointLog2013.setCreateTime(curDate);
		jbdTravelPointLog2013.setCreateUser(defSysUser.getUserCode());

		JbdTravelPoint2013 jbdTravelPoint2013=jbdTravelPoint2013Dao.getJbdTravelPoint2013(jbdTravelPointLog2013.getUserCode());
		
		if("A".equals(jbdTravelPointLog2013.getDealType())){
			jbdTravelPoint2013.setTotal(jbdTravelPoint2013.getTotal().add(jbdTravelPointLog2013.getUsePoint()));
			
		}else if ("D".equals(jbdTravelPointLog2013.getDealType())){
			jbdTravelPoint2013.setTotal(jbdTravelPoint2013.getTotal().subtract(jbdTravelPointLog2013.getUsePoint()));
		}

		jbdTravelPointLog2013Manager.saveJbdTravelPointLog2013(jbdTravelPointLog2013);
		jbdTravelPoint2013Dao.saveJbdTravelPoint2013(jbdTravelPoint2013);
		
		
	}

	public void removeJbdTravelPointRecord2013(String userCode, String travelType, SysUser defSysUser, String id) {

		JbdTravelPoint2013 jbdTravelPoint2013=jbdTravelPoint2013Dao.getJbdTravelPoint2013(userCode);
		
		//Map map=this.getTravelPoints2013(travelType,defSysUser.getUserType());
		//BigDecimal userPoint=new BigDecimal(map.get("point").toString());
		
		BigDecimal userPoint=new BigDecimal(0);
		if("1".equals(travelType)){
			userPoint=new BigDecimal(7500);
		}else if("2".equals(travelType)){
			userPoint=new BigDecimal(35000);
		}else{
			throw new AppException("类型找不到");
		}
		
		jbdTravelPoint2013.setTotal(jbdTravelPoint2013.getTotal().add(userPoint));

		jbdTravelPoint2013Dao.saveJbdTravelPoint2013(jbdTravelPoint2013);
		
		JbdTravelPointDetail2013 jbdTravelPointDetail2013=this.getJbdTravelPointDetail2013(id);
		if(jbdTravelPointDetail2013==null){
			throw new AppException("信息找不到");
		}
		Date curDate=new Date();
		JbdTravelPointLog2013 jbdTravelPointLog2013 =new JbdTravelPointLog2013();
		jbdTravelPointLog2013.setCreateTime(curDate);
		jbdTravelPointLog2013.setCreateUser(defSysUser.getUserCode());
		jbdTravelPointLog2013.setDealType("A");
		jbdTravelPointLog2013.setUsePoint(userPoint);
		jbdTravelPointLog2013.setUserCode(userCode);
		

		jbdTravelPointLog2013Manager.saveJbdTravelPointLog2013(jbdTravelPointLog2013);
		this.removeJbdTravelPointDetail2013(id);
		
		
	}
/*	private Map getTravelPoints2013(String travelType,String userType){
		Map map=new HashMap();
		BigDecimal point=new BigDecimal(0);
		Integer passStar=0;
		Integer passStarStart=0;
		Integer passStarTimes=0;
		String startDate="2012-9-29";
		String endDate="2013-9-27";
		String passStarError="";
		if("1".equals(travelType)){//东南亚旅游奖励
			point=new BigDecimal(7500);
			//passStar=7;
			passStarStart=2;
			passStarTimes=4;
			passStarError="必须达到4次红宝石及以上";
		}else if("2".equals(travelType)){//海外豪华旅游奖励
			point=new BigDecimal(35000);
			passStar=7;
			passStarStart=2;
			passStarTimes=4;
			passStarError="至少达成1次皇冠及以上奖衔，并且4个财政月不低于黄钻奖衔";
		}else{
			throw new AppException("类型找不到");
		}
		map.put("point", point);
		map.put("passStar", passStar);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("passStarError", passStarError);
		
		return map;
	}*/
	public void setJbdTravelPoint2013Dao(JbdTravelPoint2013Dao jbdTravelPoint2013Dao) {
		this.jbdTravelPoint2013Dao = jbdTravelPoint2013Dao;
	}

	public void setJbdTravelPointLog2013Manager(
			JbdTravelPointLog2013Manager jbdTravelPointLog2013Manager) {
		this.jbdTravelPointLog2013Manager = jbdTravelPointLog2013Manager;
	}

	public void saveJbdTravelPointDetails(List list,SysUser defSysUser) {
		
		for (int i = 0; i < list.size(); i++) {
			Map map=(Map) list.get(i);
			String wweek=map.get("wweek").toString();
			String userCode=map.get("userCode").toString();
			String type=map.get("type").toString();
			if("2013".equals(wweek)){
				this.addJbdTravelPointRecord2013(userCode, type, defSysUser);
			}else if("2014".equals(wweek)){
				jbdTravelPointDetail2014Manager.addJbdTravelPointRecord2014(userCode, type, defSysUser);
			}
		}
		
	}

	public void changeJbdTravelPoints(List list, SysUser defSysUser) {
		for (int i = 0; i < list.size(); i++) {
			Map map=(Map) list.get(i);
			String wweek=map.get("wweek").toString();
			String userCode=map.get("userCode").toString();
			String remark=map.get("remark").toString();
			String points=map.get("points").toString();
			String dealType=map.get("dealType").toString();
			if("2013".equals(wweek)){
				JbdTravelPointLog2013 jbdTravelPointLog2013=new JbdTravelPointLog2013();
				jbdTravelPointLog2013.setUserCode(userCode);
				jbdTravelPointLog2013.setRemark(remark);
				jbdTravelPointLog2013.setUsePoint(new BigDecimal(points));
				jbdTravelPointLog2013.setDealType(dealType);
				this.changeJbdTravelPoint2013(jbdTravelPointLog2013, defSysUser);
			}else if("2014".equals(wweek)){
				JbdTravelPointLog2014 jbdTravelPointLog2014=new JbdTravelPointLog2014();
				jbdTravelPointLog2014.setUserCode(userCode);
				jbdTravelPointLog2014.setRemark(remark);
				jbdTravelPointLog2014.setUsePoint(new BigDecimal(points));
				jbdTravelPointLog2014.setDealType(dealType);
				jbdTravelPointDetail2014Manager.changeJbdTravelPoint2014(jbdTravelPointLog2014, defSysUser);
			}else if("2015".equals(wweek)){
				JbdTravelPointLog2015 jbdTravelPointLog2015=new JbdTravelPointLog2015();
				jbdTravelPointLog2015.setUserCode(userCode);
				jbdTravelPointLog2015.setRemark(remark);
				jbdTravelPointLog2015.setUsePoint(new BigDecimal(points));
				jbdTravelPointLog2015.setDealType(dealType);
				jbdTravelPointLog2015Manager.changeJbdTravelPoint2015(jbdTravelPointLog2015, defSysUser);
			}
		}
	}

	public void setJbdTravelPointDetail2014Manager(JbdTravelPointDetail2014Manager jbdTravelPointDetail2014Manager) {
		this.jbdTravelPointDetail2014Manager = jbdTravelPointDetail2014Manager;
	}
}
