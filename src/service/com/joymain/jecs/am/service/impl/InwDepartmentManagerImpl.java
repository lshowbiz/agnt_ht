
package com.joymain.jecs.am.service.impl;

import java.util.List;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;

import com.joymain.jecs.Constants;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.am.model.InwDepartment;
import com.joymain.jecs.am.dao.InwDepartmentDao;
import com.joymain.jecs.am.service.InwDepartmentManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
public class InwDepartmentManagerImpl extends BaseManager implements InwDepartmentManager {
    private InwDepartmentDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setInwDepartmentDao(InwDepartmentDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.am.service.InwDepartmentManager#getInwDepartments(com.joymain.jecs.am.model.InwDepartment)
     */
    public List getInwDepartments(final InwDepartment inwDepartment) {
        return dao.getInwDepartments(inwDepartment);
    }

    /**
     * @see com.joymain.jecs.am.service.InwDepartmentManager#getInwDepartment(String id)
     */
    public InwDepartment getInwDepartment(final String id) {
        return dao.getInwDepartment(new Long(id));
    }

    /**
     * @see com.joymain.jecs.am.service.InwDepartmentManager#saveInwDepartment(InwDepartment inwDepartment)
     */
    public void saveInwDepartment(InwDepartment inwDepartment) {
        dao.saveInwDepartment(inwDepartment);
    }

    /**
     * @see com.joymain.jecs.am.service.InwDepartmentManager#removeInwDepartment(String id)
     */
    public void removeInwDepartment(final String id) {
        dao.removeInwDepartment(new BigDecimal(id));
    }
    //added for getInwDepartmentsByCrm
    public List getInwDepartmentsByCrm(CommonRecord crm, Pager pager){
	return dao.getInwDepartmentsByCrm(crm,pager);
    }

    /**
     * 创新共赢的部门录入或编辑之前的不为空的校验
     * @author gw  2014-05-21
     * @param inwDepartment
     * @param errors
     * @param characterCoding
     * @return boolean
     */
	public boolean getCheckInwDepartmentEmpty(InwDepartment inwDepartment,BindException errors,String characterCoding) {
		
		String name = inwDepartment.getName();
		if(StringUtil.isEmpty(name)){
            //部门名称不为空			
			errors.rejectValue("name", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "inwDepartment.name") },this.getCharacterValue(characterCoding, "isNotNull"));
			return true;
		}
		
		String category = inwDepartment.getCategory();
		if(StringUtil.isEmpty(category)){
			//部门类别不为空
			errors.rejectValue("category", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "inwDepartment.category") },this.getCharacterValue(characterCoding, "isNotNull"));
			return true;
		}
		
		String higerId = inwDepartment.getHigerId();
		//如果部门类别不是一级部门,那么上级部门的ID(HIGER_ID)也不能为空
		if((!("1".equals(category)))&& StringUtil.isEmpty(higerId)){
			if(StringUtil.isEmpty(inwDepartment.getHigerDepartName())){
				//上级部门不为空
				errors.rejectValue("higerDepartName", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "inwDepartment.higerDepartName") },this.getCharacterValue(characterCoding, "isNotNull"));
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * @author gw 2014-05-21
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
     * 创新共赢的部门录入或编辑之前的部门名称唯一性校验
	 * @author gw  2014-05-21
	 * @param inwDepartment
	 * @return boolean
	 */
	public boolean getNameUniqueCheckResult(InwDepartment inwDepartment) {
		return dao.getNameUniqueCheckResult(inwDepartment);
	}

	/**
     * 创新共赢的部门-----删除
	 * @author gw  2014-05-21
	 * @param inwDepartment
	 * @return boolean
	 */
	public void inwDepartmentRemove(InwDepartment inwDepartment) {
		 dao.inwDepartmentRemove(inwDepartment);
	}

	/**
	 * 创新共赢----指定部门---获取部门查询建议的权限(需求)
	 * @author gw 2014-05-30
	 * @param departmentId
	 * @return string 
	 */
	public String getInwDepartmentIdListById(String departmentId) {
		return dao.getInwDepartmentIdListById(departmentId);
	}

	/**
	 * 查询出所有部门的信息列表
	 * @author yxzz 2014-07-02
	 * @return list 
	 */
	public List getInwDepartmentList() {
		return dao.getInwDepartmentList();
	}
	
}
