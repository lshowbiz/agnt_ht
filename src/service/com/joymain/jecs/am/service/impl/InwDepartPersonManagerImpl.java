
package com.joymain.jecs.am.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;

import com.joymain.jecs.Constants;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.am.model.InwDepartPerson;
import com.joymain.jecs.am.dao.InwDepartPersonDao;
import com.joymain.jecs.am.service.InwDepartPersonManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
public class InwDepartPersonManagerImpl extends BaseManager implements InwDepartPersonManager {
    private InwDepartPersonDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setInwDepartPersonDao(InwDepartPersonDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.am.service.InwDepartPersonManager#getInwDepartPersons(com.joymain.jecs.am.model.InwDepartPerson)
     */
    public List getInwDepartPersons(final InwDepartPerson inwDepartPerson) {
        return dao.getInwDepartPersons(inwDepartPerson);
    }

    /**
     * @see com.joymain.jecs.am.service.InwDepartPersonManager#getInwDepartPerson(String id)
     */
    public InwDepartPerson getInwDepartPerson(final String id) {
        return dao.getInwDepartPerson(new Long(id));
    }

    /**
     * @see com.joymain.jecs.am.service.InwDepartPersonManager#saveInwDepartPerson(InwDepartPerson inwDepartPerson)
     */
    public void saveInwDepartPerson(InwDepartPerson inwDepartPerson) {
        dao.saveInwDepartPerson(inwDepartPerson);
    }

    /**
     * @see com.joymain.jecs.am.service.InwDepartPersonManager#removeInwDepartPerson(String id)
     */
    public void removeInwDepartPerson(final String id) {
        dao.removeInwDepartPerson(new Long(id));
    }
    //added for getInwDepartPersonsByCrm
    public List getInwDepartPersonsByCrm(CommonRecord crm, Pager pager){
	return dao.getInwDepartPersonsByCrm(crm,pager);
    }

    /**
     * 部门人员编辑之前不为空的校验
     * @author yxzz 2014-07-02
     * @param inwDepartPerson
     * @param errors
     * @param characterCoding
     * @return boolean
     */
	public boolean getCheckEmpty(InwDepartPerson inwDepartPerson,BindException errors, String characterCoding) {		
		String userCode = inwDepartPerson.getUserCode();
		if(StringUtil.isEmpty(userCode)){
			//部门类别不为空
			errors.rejectValue("userCode", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.memberNo") },this.getCharacterValue(characterCoding, "isNotNull"));
			return true;
		}		
		String departmentId = inwDepartPerson.getDepartmentId();
		if(StringUtil.isEmpty(departmentId)){
            //部门名称不为空			
			errors.rejectValue("departmentId", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "inwDepartment.name") },this.getCharacterValue(characterCoding, "isNotNull"));
			return true;
		}
		
		return false;
	}
	
	/**
	 * @author gw 2014-07-02
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
	 * 获取部门表的主键
	 * @author yxzz 2014-07-02
	 * @param userCodeHD  会员编号(登录系统的账号)
	 * @return string 
	 */
	public String getDepartmentId(String userCodeHD) {
		return dao.getDepartmentId(userCodeHD);
	}

	/**
	 * 会员编号唯一性校验
	 * @author yxzz 2014-07-02
	 * @param inwDepartPerson
	 * @return boolean 
	 */
	public boolean getUserCodeUniqueCheckResult(InwDepartPerson inwDepartPerson) {
		return dao.getUserCodeUniqueCheckResult(inwDepartPerson);
	}
	
}
