
package com.joymain.jecs.am.service.impl;

import java.util.List;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;

import com.joymain.jecs.Constants;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.am.model.InwDepartCompetence;
import com.joymain.jecs.am.dao.InwDepartCompetenceDao;
import com.joymain.jecs.am.service.InwDepartCompetenceManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
public class InwDepartCompetenceManagerImpl extends BaseManager implements InwDepartCompetenceManager {
    private InwDepartCompetenceDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setInwDepartCompetenceDao(InwDepartCompetenceDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.am.service.InwDepartCompetenceManager#getInwDepartCompetences(com.joymain.jecs.am.model.InwDepartCompetence)
     */
    public List getInwDepartCompetences(final InwDepartCompetence inwDepartCompetence) {
        return dao.getInwDepartCompetences(inwDepartCompetence);
    }

    /**
     * @see com.joymain.jecs.am.service.InwDepartCompetenceManager#getInwDepartCompetence(String id)
     */
    public InwDepartCompetence getInwDepartCompetence(final String id) {
        return dao.getInwDepartCompetence(new Long(id));
    }

    /**
     * @see com.joymain.jecs.am.service.InwDepartCompetenceManager#saveInwDepartCompetence(InwDepartCompetence inwDepartCompetence)
     */
    public void saveInwDepartCompetence(InwDepartCompetence inwDepartCompetence) {
        dao.saveInwDepartCompetence(inwDepartCompetence);
    }

    /**
     * @see com.joymain.jecs.am.service.InwDepartCompetenceManager#removeInwDepartCompetence(String id)
     */
    public void removeInwDepartCompetence(final String id) {
        dao.removeInwDepartCompetence(new Long(id));
    }
    
    /**
     * 部门权限的查询(初始化查询或有条件查询)
     * @author gw  2014-05-27 
     * @param crm
     * @param pager
     * @return list
     */
    public List getInwDepartCompetencesByCrm(CommonRecord crm, Pager pager){
	     return dao.getInwDepartCompetencesByCrm(crm,pager);
    }

    /**
     * 部门权限的编辑之前不为空的校验
     * @author gw 2014-05-28
     * @param inwDepartCompetence
     * @param errors
     * @param characterCoding
     * @return boolean
     */
	public boolean getCheckEmptyResult(InwDepartCompetence inwDepartCompetence,BindException errors,String characterCoding) {
		
		String departmentId = inwDepartCompetence.getDepartmentId();
		if(StringUtil.isEmpty(departmentId)){
			errors.rejectValue("departmentId", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "inwDepartment.name") },this.getCharacterValue(characterCoding, "isNotNull"));
			return true;
		}
		
		String demandId = inwDepartCompetence.getDemandId();
		if(StringUtil.isEmpty(demandId)){
			errors.rejectValue("demandId", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "sysPower.powerName") },this.getCharacterValue(characterCoding, "isNotNull"));
			return true;
		}
		
		return false;
	}
	
	/**
	 * @author gw 2014-05-28
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
	 * 根据部门表的主键departmentId查询该部门所拥有建议查看的权限
	 * @author yxzz 2014-07-01
	 * @param departmentId
	 * @return String
	 */
	public String getDemandIdListByDepartmentId(String departmentId) {
	     return dao.getDemandIdListByDepartmentId(departmentId);
	}
	
}
