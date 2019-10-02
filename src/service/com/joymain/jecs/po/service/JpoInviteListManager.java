
package com.joymain.jecs.po.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.po.model.JpoInviteList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpoInviteListManager extends Manager {
    /**
     * Retrieves all of the jpoInviteLists
     */
    public List getJpoInviteLists(JpoInviteList jpoInviteList);

    /**
     * Gets jpoInviteList's information based on id.
     * @param id the jpoInviteList's id
     * @return jpoInviteList populated jpoInviteList object
     */
    public JpoInviteList getJpoInviteList(final String id);

    /**
     * Saves a jpoInviteList's information
     * @param jpoInviteList the object to be saved
     */
    public void saveJpoInviteList(JpoInviteList jpoInviteList);

    /**
     * Removes a jpoInviteList from the database by id
     * @param id the jpoInviteList's id
     */
    public void removeJpoInviteList(final String id);
    //added for getJpoInviteListsByCrm
    public List getJpoInviteListsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * 根据存储过程获取邀请码
     * @author 2017-5-22 fu 
     * @return
     */
    public String getJpoInviteListForProcedure();
    
    /**
     * 查询会员可用的邀请码个数
     * @author 2017-5-22 fu 
     * @param userCode
     * @return int 
     */
    public int getAvailableSum(String userCode);
    
    /**
     * 批量将邀请码设置为不可用状态
     * @author 2017-5-22 fu 
     * @param userCode
     * @param qty
     * @param memberOrderNo
     * @param editUserCode
     * @return boolean 
     */
    public boolean maintainJpoInviteList(String userCode,int qty,String memberOrderNo,String editUserCode);

	void saveInviteCode(SysUser user, String userCodeInvite, int num, String orderNo, String remark, String type,
			String newDate);

	public List getJpoInviteListByHql(String siNo);
    
}

