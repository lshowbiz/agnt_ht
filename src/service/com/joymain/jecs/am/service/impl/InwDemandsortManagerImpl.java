
package com.joymain.jecs.am.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;

import com.joymain.jecs.Constants;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.am.model.InwDemandsort;
import com.joymain.jecs.am.dao.InwDemandDao;
import com.joymain.jecs.am.dao.InwDemandsortDao;
import com.joymain.jecs.am.service.InwDemandsortManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
public class InwDemandsortManagerImpl extends BaseManager implements InwDemandsortManager {
    private InwDemandsortDao dao;
    
    private InwDemandDao inwDemandDao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setInwDemandsortDao(InwDemandsortDao dao) {
        this.dao = dao;
    }

    
    
    public void setInwDemandDao(InwDemandDao inwDemandDao) {
		this.inwDemandDao = inwDemandDao;
	}



	/**
     * @see com.joymain.jecs.am.service.InwDemandsortManager#getInwDemandsorts(com.joymain.jecs.am.model.InwDemandsort)
     */
    public List getInwDemandsorts(final InwDemandsort inwDemandsort) {
        return dao.getInwDemandsorts(inwDemandsort);
    }

    /**
     * @see com.joymain.jecs.am.service.InwDemandsortManager#getInwDemandsort(String id)
     */
    public InwDemandsort getInwDemandsort(final String id) {
    	Long idl = Long.parseLong(id);
        return dao.getInwDemandsort(idl);
    }

    /**
     * @see com.joymain.jecs.am.service.InwDemandsortManager#saveInwDemandsort(InwDemandsort inwDemandsort)
     */
    public void saveInwDemandsort(InwDemandsort inwDemandsort) {
        dao.saveInwDemandsort(inwDemandsort);
    }

    /**
     * @see com.joymain.jecs.am.service.InwDemandsortManager#removeInwDemandsort(String id)
     */
    public void removeInwDemandsort(final String id) {
    	Long idl = Long.parseLong(id);
        dao.removeInwDemandsort(idl);
        inwDemandDao.deleteInwDemandByDemandsortId(id);
    }
    //added for getInwDemandsortsByCrm
    public List getInwDemandsortsByCrm(CommonRecord crm, Pager pager){
	return dao.getInwDemandsortsByCrm(crm,pager);
    }

    /**
     * 创新共赢-需求分类-录入或修改之前不为空的校验
     * @author gw  2013-11-04
     * @param inwDemandsort
     * @param errors
     * @return boolean
     */
	public boolean getEmptyCheckResult(InwDemandsort inwDemandsort,BindException errors,String characterCoding) {
		String name = inwDemandsort.getName();
		if(StringUtil.isEmpty(name)){
			errors.rejectValue("name", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "inwDemandsort.name") }, this.getCharacterValue(characterCoding, "isNotNull"));
			return true;
		}
		String requireIntroduction = inwDemandsort.getRequireIntroduction();
        if(StringUtil.isEmpty(requireIntroduction)){
        	errors.rejectValue("requireIntroduction", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "inwDemandsort.requireIntroduction") }, this.getCharacterValue(characterCoding, "isNotNull"));
			return true;
		}
        String image = inwDemandsort.getImage();
        if(StringUtil.isEmpty(image)){
        	errors.rejectValue("image", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "inwDemandsort.image") }, this.getCharacterValue(characterCoding, "isNotNull"));
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

	/**
	 * 查询创新共赢需求分类表的所有数据
	 * @author gw  2013-11-05
	 * @return
	 */
	public List getDemandsortList() {
		
		return dao.getDemandsortList();
	}



	/**
	 * 创新共赢-------------需求分类------新需求--------通过ID获取需求分类的名字
	 * @author gw 2013-13-14
	 * @return
	 */
	public String getInwDemandsortById(String id) {
		return dao.getInwDemandsortById(id);
	}
	
}
