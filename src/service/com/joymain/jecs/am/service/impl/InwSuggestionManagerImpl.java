
package com.joymain.jecs.am.service.impl;

import java.util.List;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;

import com.joymain.jecs.Constants;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.am.model.InwSuggestion;
import com.joymain.jecs.am.model.InwViewpeople;
import com.joymain.jecs.am.dao.InwSuggestionDao;
import com.joymain.jecs.am.service.InwSuggestionManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
public class InwSuggestionManagerImpl extends BaseManager implements InwSuggestionManager {
    private InwSuggestionDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setInwSuggestionDao(InwSuggestionDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.am.service.InwSuggestionManager#getInwSuggestions(com.joymain.jecs.am.model.InwSuggestion)
     */
    public List getInwSuggestions(final InwSuggestion inwSuggestion) {
        return dao.getInwSuggestions(inwSuggestion);
    }

    /**
     * 创新共赢-建议的详细查询
     * @author gw 2013-08-21
     * @param id
     * @return inwSuggestion
     */
    public InwSuggestion getInwSuggestion(final String id) {
        return dao.getInwSuggestion(new Long(id));
    }

    /**
     * @see com.joymain.jecs.am.service.InwSuggestionManager#saveInwSuggestion(InwSuggestion inwSuggestion)
     */
    public void saveInwSuggestion(InwSuggestion inwSuggestion) {
        dao.saveInwSuggestion(inwSuggestion);
    }

    /**
     * @see com.joymain.jecs.am.service.InwSuggestionManager#removeInwSuggestion(String id)
     */
    public void removeInwSuggestion(final String id) {
        dao.removeInwSuggestion(new Long(id));
    }
    //added for getInwSuggestionsByCrm
    public List getInwSuggestionsByCrm(CommonRecord crm, Pager pager){
	return dao.getInwSuggestionsByCrm(crm,pager);
    }

    /**
     * 创新共赢的后台意见的查询方法
     * @author gw 2013-08-20
     * @param crm
     * @param pager
     * @param lookStatus
     * @return list
     */
	public List getSuggestionAndViewPeople(CommonRecord crm, Pager pager,String lookStatus) {
		return dao.getSuggestionAndViewPeople(crm,pager,lookStatus);
	}

	/**
	 * 去数据库表inw_viewPeople中查,看当前登录用户对该条建议是否已阅
	 * @author gw 2013-08-23
	 * @param id
	 * @param userCode
	 * @return InwViewpeople
	 */
	public List getInwViewpeopleIsExist(String id, String userCode) {
		
		return dao.getInwViewpeopleIsExist(id,userCode);
	}

	/**
	 * 因需求分类和需求名称现在可以动态的调整了,因此添加一个不为空的校验
	 * @author 2013-09-03 update 2014-06-25  yxzz
	 * @param inwSuggestion
	 * @param errors
	 * @param defCharacterCoding
	 * @return boolean
	 */
	public boolean getReplyContent(InwSuggestion inwSuggestion,BindException errors, String characterCoding) {
		//需求分类不为空
		String demandsortId = inwSuggestion.getDemandsortId();
		if(StringUtil.isEmpty(demandsortId)){
			errors.rejectValue("demandsortId", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "inwDemand.demandsortId") }, this.getCharacterValue(characterCoding, "isNotNull"));
			return true;
		}
		
		//需求名称不为空
		String demandId = inwSuggestion.getDemandId();
		if(StringUtil.isEmpty(demandId)){
			errors.rejectValue("demandId", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "inwDemand.name") }, this.getCharacterValue(characterCoding, "isNotNull"));
			return true;
		}		
		
		return false;
				
	}

	/**
	 * 建议--通过demandId获取InwSuggestion
	 * @author 2014-06-25
	 * @param demandId
	 * @return List<InwSuggestion>
	 */
	public List<InwSuggestion> getInwSuggestionByDemandid(String demandId) {
		return dao.getInwSuggestionByDemandid(new Long(demandId));
	}
	
	/**
	 * @author gw 2013-09-10
	 * @param characterCoding
	 * @param msgKey
	 * @return String
	 */
	private String getCharacterValue(String characterCoding,String msgKey){
		String characterValue=(String) Constants.localLanguageMap.get(characterCoding).get(msgKey);
		if(!StringUtils.isEmpty(characterValue)){
			return characterValue;
		}else{
			return msgKey;
		}
	}	
	

	/**
     * 审核(批量审核)
     * @author 2014-06-24
     * @param ids
     */
	public void checkInwSuggestionList(String ids) {
		 dao.checkInwSuggestionList(ids);
	}

	/**
     * 创新共赢的建议的查询 方法--具体业务部门查询
     * @author gw 2014-07-01
     * @param crm
     * @param pager
     * @return list
     */
	public List getSuggestionForDepartment(CommonRecord crm, Pager pager) {
		return dao.getSuggestionForDepartment(crm,pager);
	}

}
