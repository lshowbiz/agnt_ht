
package com.joymain.jecs.bd.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.bd.dao.JbdTravelPoint2014Dao;
import com.joymain.jecs.bd.dao.JbdTravelPointDetail2014Dao;
import com.joymain.jecs.bd.model.JbdTravelPoint2013;
import com.joymain.jecs.bd.model.JbdTravelPoint2014;
import com.joymain.jecs.bd.model.JbdTravelPointDetail2013;
import com.joymain.jecs.bd.model.JbdTravelPointDetail2014;
import com.joymain.jecs.bd.model.JbdTravelPointLog2013;
import com.joymain.jecs.bd.model.JbdTravelPointLog2014;
import com.joymain.jecs.bd.service.JbdTravelPointDetail2014Manager;
import com.joymain.jecs.bd.service.JbdTravelPointLog2014Manager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
public class JbdTravelPointDetail2014ManagerImpl extends BaseManager implements JbdTravelPointDetail2014Manager {
    private JbdTravelPointDetail2014Dao dao;
    private JbdTravelPoint2014Dao jbdTravelPoint2014Dao;
    public void setJbdTravelPoint2014Dao(JbdTravelPoint2014Dao jbdTravelPoint2014Dao) {
		this.jbdTravelPoint2014Dao = jbdTravelPoint2014Dao;
	}
    private JbdTravelPointLog2014Manager jbdTravelPointLog2014Manager;
	public void setJbdTravelPointLog2014Manager(
			JbdTravelPointLog2014Manager jbdTravelPointLog2014Manager) {
		this.jbdTravelPointLog2014Manager = jbdTravelPointLog2014Manager;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdTravelPointDetail2014Dao(JbdTravelPointDetail2014Dao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointDetail2014Manager#getJbdTravelPointDetail2014s(com.joymain.jecs.bd.model.JbdTravelPointDetail2014)
     */
    public List getJbdTravelPointDetail2014s(final JbdTravelPointDetail2014 jbdTravelPointDetail2014) {
        return dao.getJbdTravelPointDetail2014s(jbdTravelPointDetail2014);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointDetail2014Manager#getJbdTravelPointDetail2014(String id)
     */
    public JbdTravelPointDetail2014 getJbdTravelPointDetail2014(final String id) {
        return dao.getJbdTravelPointDetail2014(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointDetail2014Manager#saveJbdTravelPointDetail2014(JbdTravelPointDetail2014 jbdTravelPointDetail2014)
     */
    public void saveJbdTravelPointDetail2014(JbdTravelPointDetail2014 jbdTravelPointDetail2014) {
        dao.saveJbdTravelPointDetail2014(jbdTravelPointDetail2014);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointDetail2014Manager#removeJbdTravelPointDetail2014(String id)
     */
    public void removeJbdTravelPointDetail2014(final String id) {
        dao.removeJbdTravelPointDetail2014(new Long(id));
    }
    //added for getJbdTravelPointDetail2014sByCrm
    public List getJbdTravelPointDetail2014sByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdTravelPointDetail2014sByCrm(crm,pager);
    }
    public void removeJbdTravelPointRecord2014(String userCode, String travelType, SysUser defSysUser, String id) {

		JbdTravelPoint2014 jbdTravelPoint2014=jbdTravelPoint2014Dao.getJbdTravelPoint2014(userCode);
		
		//Map map=this.getTravelPoints2014(travelType,defSysUser.getUserType());
		//BigDecimal userPoint=new BigDecimal(map.get("point").toString());
		
		BigDecimal userPoint=new BigDecimal(0);
		if("1".equals(travelType)){
			userPoint=new BigDecimal(7500);
		}else if("2".equals(travelType)){
			userPoint=new BigDecimal(35000);
		}else{
			throw new AppException("类型找不到");
		}
		
		jbdTravelPoint2014.setTotal(jbdTravelPoint2014.getTotal().add(userPoint));

		jbdTravelPoint2014Dao.saveJbdTravelPoint2014(jbdTravelPoint2014);
		
		JbdTravelPointDetail2014 jbdTravelPointDetail2014=this.getJbdTravelPointDetail2014(id);
		if(jbdTravelPointDetail2014==null){
			throw new AppException("信息找不到");
		}
		Date curDate=new Date();
		JbdTravelPointLog2014 jbdTravelPointLog2014 =new JbdTravelPointLog2014();
		jbdTravelPointLog2014.setCreateTime(curDate);
		jbdTravelPointLog2014.setCreateUser(defSysUser.getUserCode());
		jbdTravelPointLog2014.setDealType("A");
		jbdTravelPointLog2014.setUsePoint(userPoint);
		jbdTravelPointLog2014.setUserCode(userCode);
		

		jbdTravelPointLog2014Manager.saveJbdTravelPointLog2014(jbdTravelPointLog2014);
		this.removeJbdTravelPointDetail2014(id);
		
		
	}
    
    public void changeJbdTravelPoints(List list, SysUser defSysUser) {
		for (int i = 0; i < list.size(); i++) {
			Map map=(Map) list.get(i);
			String wweek=map.get("wweek").toString();
			String userCode=map.get("userCode").toString();
			String remark=map.get("remark").toString();
			String points=map.get("points").toString();
			String dealType=map.get("dealType").toString();
			if("2014".equals(wweek)){
				JbdTravelPointLog2014 jbdTravelPointLog2014=new JbdTravelPointLog2014();
				jbdTravelPointLog2014.setUserCode(userCode);
				jbdTravelPointLog2014.setRemark(remark);
				jbdTravelPointLog2014.setUsePoint(new BigDecimal(points));
				jbdTravelPointLog2014.setDealType(dealType);
				this.changeJbdTravelPoint2014(jbdTravelPointLog2014, defSysUser);
			}
			
		}
	}
    
	public void changeJbdTravelPoint2014(JbdTravelPointLog2014 jbdTravelPointLog2014, SysUser defSysUser) {
		Date curDate = new Date();
		jbdTravelPointLog2014.setCreateTime(curDate);
		jbdTravelPointLog2014.setCreateUser(defSysUser.getUserCode());

		JbdTravelPoint2014 jbdTravelPoint2014=jbdTravelPoint2014Dao.getJbdTravelPoint2014(jbdTravelPointLog2014.getUserCode());
		
		if("A".equals(jbdTravelPointLog2014.getDealType())){
			jbdTravelPoint2014.setTotal(jbdTravelPoint2014.getTotal().add(jbdTravelPointLog2014.getUsePoint()));
			
		}else if ("D".equals(jbdTravelPointLog2014.getDealType())){
			jbdTravelPoint2014.setTotal(jbdTravelPoint2014.getTotal().subtract(jbdTravelPointLog2014.getUsePoint()));
		}

		jbdTravelPointLog2014Manager.saveJbdTravelPointLog2014(jbdTravelPointLog2014);
		jbdTravelPoint2014Dao.saveJbdTravelPoint2014(jbdTravelPoint2014);
		
		
	}

	public void addJbdTravelPointRecord2014(String userCode, String travelType, SysUser defSysUser) {

		JbdTravelPoint2014 jbdTravelPoint2014=jbdTravelPoint2014Dao.getJbdTravelPoint2014(userCode);
		//Map map=this.getTravelPoints2014(travelType,defSysUser.getUserType());
		
		BigDecimal userPoint=new BigDecimal(0);
		if("1".equals(travelType)){
			userPoint=new BigDecimal(7500);
			List list=jbdTravelPointLog2014Manager.getJbdTravelPointLogByPassStar(userCode, "2");
			if(list.size()<4){
				throw new AppException("至少达成4次红宝石及以上奖衔");
			}
		}else if("2".equals(travelType)){
			userPoint=new BigDecimal(35000);
			List list1=jbdTravelPointLog2014Manager.getJbdTravelPointLogByPassStar(userCode, "7");
			if(list1.size()<1){
				throw new AppException("至少达成1次皇冠及以上奖衔");
			}
			List list2=jbdTravelPointLog2014Manager.getJbdTravelPointLogByPassStar(userCode, "4");
			if(list2.size()<4){
				throw new AppException("4个财政月不低于黄钻奖衔");
			}
		}else{
			throw new AppException("类型找不到");
		}
		
		
		if(jbdTravelPoint2014.getTotal().compareTo(userPoint)==-1){
			throw new AppException("积分不足");
		}
//		if(map.get("vips")!=null){
//			List vips=jbdTravelPoint2014Dao.getRecommendVip(userCode, map.get("startDate").toString(), map.get("endDate").toString());
//			if(vips.size()<StringUtil.formatInt(map.get("vips").toString())){
//				throw new AppException(map.get("vipsError").toString());
//			}
//		}
		
		jbdTravelPoint2014.setTotal(jbdTravelPoint2014.getTotal().subtract(userPoint));
		
		jbdTravelPoint2014Dao.saveJbdTravelPoint2014(jbdTravelPoint2014);
		
		Date curDate=new Date();
		JbdTravelPointDetail2014 jbdTravelPointDetail2014 =new JbdTravelPointDetail2014();
		jbdTravelPointDetail2014.setTravelType(travelType);
		jbdTravelPointDetail2014.setUsePoint(userPoint);
		jbdTravelPointDetail2014.setUserCode(userCode);
		jbdTravelPointDetail2014.setCreateTime(curDate);
		jbdTravelPointDetail2014.setCreateUser(defSysUser.getUserCode());
		
		JbdTravelPointLog2014 jbdTravelPointLog2014 =new JbdTravelPointLog2014();
		jbdTravelPointLog2014.setCreateTime(curDate);
		jbdTravelPointLog2014.setCreateUser(defSysUser.getUserCode());
		jbdTravelPointLog2014.setDealType("D");
		jbdTravelPointLog2014.setUsePoint(userPoint);
		jbdTravelPointLog2014.setUserCode(userCode);
		
		
		this.saveJbdTravelPointDetail2014(jbdTravelPointDetail2014);
		jbdTravelPointLog2014Manager.saveJbdTravelPointLog2014(jbdTravelPointLog2014);
	
		
	}

	public void saveJbdTravelPointDetails(List list, SysUser defSysUser) {
		
		for (int i = 0; i < list.size(); i++) {
			Map map=(Map) list.get(i);
			String wweek=map.get("wweek").toString();
			String userCode=map.get("userCode").toString();
			String type=map.get("type").toString();
			if("2014".equals(wweek)){
				this.addJbdTravelPointRecord2014(userCode, type, defSysUser);
			}
			
		}
		
	}

}
