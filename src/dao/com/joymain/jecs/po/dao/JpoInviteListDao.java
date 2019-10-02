
package com.joymain.jecs.po.dao;

import java.util.List;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.po.model.JpoInviteList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpoInviteListDao extends Dao {

    /**
     * Retrieves all of the jpoInviteLists
     */
    public List getJpoInviteLists(JpoInviteList jpoInviteList);

    /**
     * Gets jpoInviteList's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jpoInviteList's id
     * @return jpoInviteList populated jpoInviteList object
     */
    public JpoInviteList getJpoInviteList(final Long id);

    /**
     * Saves a jpoInviteList's information
     * @param jpoInviteList the object to be saved
     */    
    public void saveJpoInviteList(JpoInviteList jpoInviteList);

    /**
     * Removes a jpoInviteList from the database by id
     * @param id the jpoInviteList's id
     */
    public void removeJpoInviteList(final Long id);
    //added for getJpoInviteListsByCrm
    public List getJpoInviteListsByCrm(CommonRecord crm, Pager pager);

    /**
     * 根据存储过程获取邀请码
     * @author 2017-5-22 fu 
     * @return String
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
	public boolean maintainJpoInviteList(String userCode, int qty,String memberOrderNo,String editUserCode);

	List getJpoInviteListByHql(String hql);

	void saveJpoInvLists(List<JpoInviteList> list);
	
}

