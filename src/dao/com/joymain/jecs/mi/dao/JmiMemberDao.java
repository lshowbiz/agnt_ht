
package com.joymain.jecs.mi.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiMemberUpdate;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiMemberDao extends Dao {

    /**
     * Retrieves all of the jmiMembers
     */
    public List getJmiMembers(JmiMember jmiMember);

    /**
     * Gets jmiMember's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
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
     * Removes a jmiMember from the database by userCode
     * @param userCode the jmiMember's userCode
     */
    public void removeJmiMember(final String userCode);
    //added for getJmiMembersByCrm
    public List getJmiMembersByCrm(CommonRecord crm, Pager pager);
    
    public List getLinkRefbyLinkNoOrderByCreateTime(String linkNo);
    public List getJmiMembersByCrm(CommonRecord crm) ;
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
	 * 
	 * @param memberNo
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List getMiRecommendRefsCountList(String memberNo, String startTime, String endTime);
	public List getMiRecommendRefsCounts(CommonRecord crm, Pager pager);
	/**
	 * 检查配偶身份证编号是否存在
	 * @param miMember
	 * 存在返回true,不存在返回false
	 * @return
	 */
	public boolean getCheckMiMemberSpouseIdNoByMiMember(JmiMember miMember);
	
	public boolean getIsJumper(String userCode); 
	public Map getMemberInfo(String userCode, String flag);

	public List getJWSMemberInfos(String userCode, String phone,String userName,String member_upgrade_day);

	public void sendPcn(JmiMember jmiMember,String operateType,String newCustomerLevel,String remark,SysUser defSysUser,String customerLevelNoteId);
	
	
	public List getTWPromotions(Date calEndDate);

	public boolean getCheckMiMemberMobileByMiMember(JmiMember miMember);
	
	public Date getDsDate();
	public String getPapernumberUserCode(JmiMember miMember);
	
	public Map getZcwMemberByUserCode(String userCode);
	
	public List getJmiTeamType();
	
	public List getjbdUserStateList(CommonRecord crm, Pager pager);
	public List getJmiClubs(CommonRecord crm, Pager pager);
	public String getPapernumberUserCode(String papernumber,String linkNo);
	public List getjbdUserDelList(CommonRecord crm, Pager pager);
	public List getJmiMemberUpdate();
	public void saveJmiMemberUpdate(JmiMemberUpdate jmiMemberUpdate);
	
	/**
	 * 验证当前用户是否有云店资格(查询云店手机号码)
	 * @param userCode
	 * @return
	 */
	public String getCheckUserCodeCloudshop(String userCode);
	
	/**
	 * 验证该云店手机号码是否使用
	 * @param userCode
	 * @param cloudShopPhone
	 * @return
	 */
	public String getCheckUserCodeCloudshoPhone(String cloudShopPhone,String userCode);
	
	/**
	 * 修改会员云店手机
	 * @param jmiMember
	 */
	public void updateJmiMemberCloudshopPhone(JmiMember jmiMember);
}

