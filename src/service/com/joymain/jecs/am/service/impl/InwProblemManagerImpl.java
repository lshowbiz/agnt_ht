
package com.joymain.jecs.am.service.impl;

import java.util.List;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;

import com.joymain.jecs.Constants;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.am.model.InwProblem;
import com.joymain.jecs.am.dao.InwProblemDao;
import com.joymain.jecs.am.service.InwProblemManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
public class InwProblemManagerImpl extends BaseManager implements InwProblemManager {
    private InwProblemDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setInwProblemDao(InwProblemDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.am.service.InwProblemManager#getInwProblems(com.joymain.jecs.am.model.InwProblem)
     */
    public List getInwProblems(final InwProblem inwProblem) {
        return dao.getInwProblems(inwProblem);
    }

    /**
     * @see com.joymain.jecs.am.service.InwProblemManager#getInwProblem(String id)
     */
    public InwProblem getInwProblem(final String id) {
        return dao.getInwProblem(new Long(id));
    }

    /**
     * @see com.joymain.jecs.am.service.InwProblemManager#saveInwProblem(InwProblem inwProblem)
     */
    public void saveInwProblem(InwProblem inwProblem) {
        dao.saveInwProblem(inwProblem);
    }

    /**
     * @see com.joymain.jecs.am.service.InwProblemManager#removeInwProblem(String id)
     */
    public void removeInwProblem(final String id) {
        dao.removeInwProblem(new Long(id));
    }
    //added for getInwProblemsByCrm
    public List getInwProblemsByCrm(CommonRecord crm, Pager pager){
	return dao.getInwProblemsByCrm(crm,pager);
    }

    /**
     * 创新共赢的共赢帮助的查询
     * @author gw  2013-08-26
     * @param crm
     * @param pager
     * @return　list
     */
	public List getInwProblemList(CommonRecord crm, Pager pager) {
		return dao.getInwProblemList(crm,pager);
	}

	/**
     * 创新共赢的共赢帮助的审核(审核过的那些问题可以在前台显示)
     * @author gw  2013-08-28
     * @param idList
     */
	public void inwProblemAudit(String idList) {
		dao.inwProblemAudit(idList);
	}

	/**
	 * 创新共赢的共赢帮助 录入功能之前的不为空的校验
	 * @author gw 2013-09-16
	 * @param inwProblem
	 * @param errors
	 * @param defCharacterCoding
	 * @return boolean
	 */
	public boolean getEmptyCheck(InwProblem inwProblem, BindException errors,String characterCoding) {
		String species = inwProblem.getSpecies();
		if(StringUtil.isEmpty(species)){
			errors.rejectValue("species", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "inwProblem.species") }, "");
			return true;
		}
		String ask = inwProblem.getAsk();
		if(StringUtil.isEmpty(ask)){
			errors.rejectValue("ask", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "inwProblem.problem") }, "");
			return true;
		}
		String answer = inwProblem.getAnswer();
		if(StringUtil.isEmpty(answer)){
			errors.rejectValue("answer", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.securityQuestionAnswer") }, "");
			return true;
		}
		return false;
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
}
