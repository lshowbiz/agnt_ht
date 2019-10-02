
package com.joymain.jecs.am.service;

import java.util.List;

import org.springframework.validation.BindException;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.am.model.InwSuggestion;
import com.joymain.jecs.am.model.InwViewpeople;
import com.joymain.jecs.am.dao.InwSuggestionDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface InwSuggestionManager extends Manager {
    /**
     * Retrieves all of the inwSuggestions
     */
    public List getInwSuggestions(InwSuggestion inwSuggestion);

    /**
     * 创新共赢-建议的详细查询
     * @author gw 2013-08-21
     * @param id
     * @return inwSuggestion
     */
    public InwSuggestion getInwSuggestion(final String id);

    /**
     * Saves a inwSuggestion's information
     * @param inwSuggestion the object to be saved
     */
    public void saveInwSuggestion(InwSuggestion inwSuggestion);

    /**
     * Removes a inwSuggestion from the database by id
     * @param id the inwSuggestion's id
     */
    public void removeInwSuggestion(final String id);
    //added for getInwSuggestionsByCrm
    public List getInwSuggestionsByCrm(CommonRecord crm, Pager pager);

    /**
     * 创新共赢的后台意见(活动)的查询方法
     * @author gw 2013-08-20
     * @param crm
     * @param pager
     * @return list
     */
	public List getSuggestionAndViewPeople(CommonRecord crm, Pager pager,String lookStatus);

	/**
	 * 去数据库表inw_viewPeople中查,看当前登录用户对该条建议是否已阅
	 * @author gw 2013-08-23
	 * @param id
	 * @param userCode
	 * @return List
	 */
	public List getInwViewpeopleIsExist(String id, String userCode);

	

	/**
	 * 建议查看--建议回复之前对回复内容的不为空校验
	 * @author 2013-09-03
	 * @param inwSuggestion
	 * @param errors
	 * @param defCharacterCoding
	 * @return boolean
	 */
	public boolean getReplyContent(InwSuggestion inwSuggestion,BindException errors, String defCharacterCoding);

	/**
	 * 建议--通过demandId获取InwSuggestion
	 * @author 2013-09-03
	 * @param demandId
	 * @return List<InwSuggestion>
	 */
	public List<InwSuggestion> getInwSuggestionByDemandid(String demandId);
	
	
	/**
     * 审核(批量审核)
     * @author 2014-06-24
     * @param ids
     */
    public void checkInwSuggestionList(String ids);

    /**
     * 创新共赢的建议的查询 方法--具体业务部门查询
     * @author gw 2014-07-01
     * @param crm
     * @param pager
     * @return list
     */
	public List getSuggestionForDepartment(CommonRecord crm, Pager pager);
	
}

