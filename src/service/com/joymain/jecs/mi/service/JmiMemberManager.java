
package com.joymain.jecs.mi.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;

import com.joymain.jecs.pd.model.RspEntity;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiMemberLog;
import com.joymain.jecs.bd.model.JbdSummaryList;
import com.joymain.jecs.mi.dao.JmiMemberDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiMemberManager extends Manager {
    /**
     * Retrieves all of the jmiMembers
     */
    public List getJmiMembers(JmiMember jmiMember);

    /**
     * Gets jmiMember's information based on userCode.
     * @param userCode the jmiMember's userCode
     * @return jmiMember populated jmiMember object
     */
    public JmiMember getJmiMember(final String userCode);
    public List findJmiMemberById(String userCode);

    /**
     * Saves a jmiMember's information
     * @param jmiMember the object to be saved
     */
    public void saveJmiMember(JmiMember jmiMember);
    /**
     * 保存并记录日志   20151228 houxyu
     * @param jmiMember
     * @param JmiMemberLog
     */
    public void saveJmiMember(JmiMember jmiMember,JmiMemberLog JmiMemberLog);
    /**
     * Removes a jmiMember from the database by userCode
     * @param userCode the jmiMember's userCode
     */
    public void removeJmiMember(final String userCode);
    //added for getJmiMembersByCrm
    public List getJmiMembersByCrm(CommonRecord crm, Pager pager);
    public List getJmiMembersByCrm(CommonRecord crm);
    public List getLinkRefbyLinkNoOrderByCreateTime(String linkNo);
    public String saveNewJmiMember(JmiMember jmiMember,SysUser defSysUser,HttpServletRequest request);
	/**
	 * 执行存储过程,检查体系完整性
	 * @param checkType
	 * @return
	 */
	public Map callProcCheckRef(String checkType);
	/**
	 * 检查身份证编号是否存在
	 * @param miMember
	 * 存在返回true,不存在返回false
	 * @return
	 */
	public boolean getCheckMiMemberIdNoByMiMember(JmiMember miMember);
	/**
	 * 检查会员资料完整性
	 * @param request
	 * @param jmiMember
	 * @param errors
	 * @return
	 */
	public boolean getCheckMiMember(HttpServletRequest request, JmiMember jmiMember,BindException errors,String characterCoding,String type);
	/**
	 * 重置sysUser中的userName
	 *
	 */
	public void getSetUserName(JmiMember jmiMember);
	public List getMiRecommendRefsCountList(String memberNo, String startTime, String endTime);
	public List getMiRecommendRefsCounts(CommonRecord crm, Pager pager);
	public void saveMemberCompany(JmiMember jmiMember,HttpServletRequest request);
	/**
	 * 修改会员卡别
	 * @param newCardType
	 * @param miMember
	 */
	public void changeMiMemberCardType(String newCardType, JmiMember jmiMember,Integer wweek,SysUser defSysUser);
	
	public void saveJmiMemberAndName(JmiMember miMember);
	
	public void saveJmiMemberActive(String userCode);
	
	public boolean getIsJumper(String userCode); 
	public Map getMemberInfo(String userCode, String flag);
	
	public List getJWSMemberInfos(String userCode, String phone,String userName,String member_upgrade_day);
	public String getCheckRecommendNo(BindException errors,String recommendNo,String defUserCode,String characterCoding);
	public String getCheckLinkNo(BindException errors, String recommendNo, String linkNo,String defUserCode,String characterCoding) ;
	//验证证件号
	public  String  getCheckIdNo(BindException errors,String papernumber,String defUserCode,String papertype,String companyCode,String characterCoding);
	public String reSendJmiMember(String[] strCodes);
	
	public void changeMiMemberCardTypeAll(List<Map> jmiMembers,SysUser defSysUser);
	public void changeMiMemberMemberLevelAll(List<Map> jmiMembers,SysUser defSysUser);
	
	public void sendPcnMananger(JmiMember jmiMember,String operateType,String newCustomerLevel,String remark,SysUser defSysUser,String customerLevelNoteId);
//	
	public void sendPcnsNewMember(String[] strCodes );

	public List getTWPromotions(Date calEndDate);
	
	public void activeMembers(List<Map> jmiMembers);
	public Map getZcwMemberByUserCode(String userCode);
	public void freezeMembers(List<Map> jmiMembers);
	public void saveStateMembers(List<Map> jmiMembers,SysUser defSysUser) ;
	public List getJmiTeamType();

	public List getjbdUserStateList(CommonRecord crm, Pager pager);
	public void saveRemarkMembers(List<Map> jmiMembers) ;
	public List getJmiClubs(CommonRecord crm, Pager pager);

	// add by gw 2014-05-14 更改加盟店类型   
	public void saveIsstoreMembers(List<Map> jmiMembers);

    public void updateStarRemark(List<JmiMember> lists);
    
    public boolean getIdCardCheck(String id);
    
    public boolean getIdCardCheckTw(String idNo);
    //接口JOCS
    public List getCheckJmiMemberDubbo(Map<String,String> map);
/*	public String getTeamType(String recommendNo);*/
	public void changeMemberLevel(JmiMember jmiMember,Integer wweek,SysUser defSysUser,String isValid,Integer newMemberLevel,Integer oldMemberLevel);
	public List getjbdUserDelList(CommonRecord crm, Pager pager);
	
	public RspEntity getJmiMemberForGHB(String jsonString);
	
	public void saveInfoMembers(List<Map> list);
	public void exitDateMembers(List<Map> jmiMembers) ;
	public List getJmiMemberUpdate();
    public RspEntity getUpRecommendNo(String jsonString);
    
    /**
     * 验证当前用户编码是否有云店资格
     * @param userCode
     * @return
     */
    public String getCheckUserCodeCloudshop(String userCode);
    
    /**
     * 保存或修改会员
     * @param member
     */
    public void updateJmiMemberCloudshopPhone(JmiMember member);
    
    /**
     * 验证该云店手机是否使用
     * @param phone
     * @return
     */
    public String getCheckUserCodeCloudshoPhone(String phone,String userCode);
    /**
     * 退出，并记录
     * @param member
     * @param jbdSummaryList
     */
	public void saveExitMimember(JmiMember member,JbdSummaryList jbdSummaryList);
}

