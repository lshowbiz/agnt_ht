
package com.joymain.jecs.pd.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;

import com.joymain.jecs.Constants;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdShUrl;
import com.joymain.jecs.pd.dao.PdShUrlDao;
import com.joymain.jecs.pd.service.PdShUrlManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
public class PdShUrlManagerImpl extends BaseManager implements PdShUrlManager {
    private PdShUrlDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdShUrlDao(PdShUrlDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdShUrlManager#getPdShUrls(com.joymain.jecs.pd.model.PdShUrl)
     */
    public List getPdShUrls(final PdShUrl pdShUrl) {
        return dao.getPdShUrls(pdShUrl);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdShUrlManager#getPdShUrl(String id)
     */
    public PdShUrl getPdShUrl(final String id) {
        return dao.getPdShUrl(new Long(id));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdShUrlManager#savePdShUrl(PdShUrl pdShUrl)
     */
    public void savePdShUrl(PdShUrl pdShUrl) {
        dao.savePdShUrl(pdShUrl);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdShUrlManager#removePdShUrl(String id)
     */
    public void removePdShUrl(final String id) {
        dao.removePdShUrl(new Long(id));
    }
    
    //added for getPdShUrlsByCrm
    public List getPdShUrlsByCrm(CommonRecord crm, Pager pager){
	    return dao.getPdShUrlsByCrm(crm,pager);
    }

    /**
     * 物流公司链接不为空的校验
     * @author yxzz 2014-07-09
     * @param pdShUrl
     * @param errors
     * @param defCharacterCoding
     * @return boolean
     */
	public boolean getCheckEmpty(PdShUrl pdShUrl, BindException errors,String defCharacterCoding) {
		
		String  valueCode = pdShUrl.getValueCode();
		if(StringUtil.isEmpty(valueCode)){
			errors.rejectValue("valueCode", "isNotNull",new Object[] { this.getCharacterValue(defCharacterCoding, "pdShUrl.valueCode") },this.getCharacterValue(defCharacterCoding, "isNotNull"));
			return true;
		}
		
		String  valueTitle = pdShUrl.getValueTitle();
		if(StringUtil.isEmpty(valueTitle)){
			errors.rejectValue("valueTitle", "isNotNull",new Object[] { this.getCharacterValue(defCharacterCoding, "pdShUrl.valueTitle") },this.getCharacterValue(defCharacterCoding, "isNotNull"));
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
	 * 物流公司编码唯一性校验
	 * @author yxzz 2014-07-09
	 * @param pdShUrl
	 * @return boolean
	 */
	public boolean getValueCodeUniqueCheck(PdShUrl pdShUrl) {
		 return dao.getValueCodeUniqueCheck(pdShUrl);
	}

	/**
	 * 获取物流公司链接地址
	 * @author gw  2014-07-10
	 * @param shNo 物流公司编码
	 * @return string 物流公司链接地址
	 */
	public String getShUrl(String shNo) {
		return dao.getShUrl(shNo);
	}
	
}
