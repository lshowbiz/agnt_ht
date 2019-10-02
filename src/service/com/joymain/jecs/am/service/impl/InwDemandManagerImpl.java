
package com.joymain.jecs.am.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;

import com.joymain.jecs.Constants;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.am.model.InwDemand;
import com.joymain.jecs.am.model.InwSuggestion;
import com.joymain.jecs.am.model.InwViewpeople;
import com.joymain.jecs.am.dao.InwDemandDao;
import com.joymain.jecs.am.service.InwDemandManager;
import com.joymain.jecs.am.service.InwSuggestionManager;
import com.joymain.jecs.am.service.InwViewpeopleManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
public class InwDemandManagerImpl extends BaseManager implements InwDemandManager {
    private InwDemandDao dao;
    
    //创新共赢的建议的Manager
    private InwSuggestionManager inwSuggestionManager;
    
    //创新共赢的建议查看的Manager
    private InwViewpeopleManager inwViewpeopleManager;
    
    //创新共赢的建议的InwSuggestionManager的set方法
    public void setInwSuggestionManager(InwSuggestionManager inwSuggestionManager) {
        this.inwSuggestionManager = inwSuggestionManager;
    }
    
    //创新共赢的建议查看的InwViewpeopleManager的set方法
	public void setInwViewpeopleManager(InwViewpeopleManager inwViewpeopleManager) {
		this.inwViewpeopleManager = inwViewpeopleManager;
	}

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setInwDemandDao(InwDemandDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.am.service.InwDemandManager#getInwDemands(com.joymain.jecs.am.model.InwDemand)
     */
    public List getInwDemands(final InwDemand inwDemand) {
        return dao.getInwDemands(inwDemand);
    }

    /**
     * @see com.joymain.jecs.am.service.InwDemandManager#getInwDemand(String id)
     */
    public InwDemand getInwDemand(final String id) {
        return dao.getInwDemand(new Long(id));
    }

    /**
     * @see com.joymain.jecs.am.service.InwDemandManager#saveInwDemand(InwDemand inwDemand)
     */
    public void saveInwDemand(InwDemand inwDemand) {
        dao.saveInwDemand(inwDemand);
    }

    /**
     * @see com.joymain.jecs.am.service.InwDemandManager#removeInwDemand(String id)
     */
    public void removeInwDemand(final String id) {
        dao.removeInwDemand(new Long(id));
    }
    
    /**
     * 需求(活动)删除-----新需求
     * @author gw 2013-09-13 update 2013-11-12  gw 
     * @param  ids
     * 
     */
    public void removeInwDemands(String ids){
    	if(!StringUtil.isEmpty(ids)){
    		int  a = ids.indexOf(",");
    		//可以批量删除的-----这个删除的时候是单选
    		if((-1)==a){
    			//删除创新共赢的活动(需求)的相关建议
		    	List<InwSuggestion> inwSuggestionList = inwSuggestionManager.getInwSuggestionByDemandid(ids);
		    	for(InwSuggestion inwSuggestion:inwSuggestionList){
		    		    inwSuggestionManager.removeInwSuggestion(inwSuggestion.getId().toString());
		    		    //删除每条建议相关的建议查看的内容
		    		    InwViewpeople inwViewpeople =  inwViewpeopleManager.getInwViewpeopleBySuggestionId(inwSuggestion.getId().toString());
		    		if(null!=inwViewpeople){
		    			inwViewpeopleManager.removeInwViewpeople(inwViewpeople.getId().toString());
		    		}
		    	}
    		}
    		//可以批量删除的-------删除的时候是多选
    		else{
	    		String[] suggestionList = ids.split(",");
	    		for(String demandId:suggestionList){
			    	//在删除创新共赢的活动时,将相关联的的数据也删除掉
			    	//删除创新共赢的活动(需求)的相关建议
			    	List<InwSuggestion> inwSuggestionList = inwSuggestionManager.getInwSuggestionByDemandid(demandId);
			    	for(InwSuggestion inwSuggestion:inwSuggestionList){
			    		    inwSuggestionManager.removeInwSuggestion(inwSuggestion.getId().toString());
			    		    //删除每条建议相关的建议查看的内容
			    		    InwViewpeople inwViewpeople =  inwViewpeopleManager.getInwViewpeopleBySuggestionId(inwSuggestion.getId().toString());
			    		if(null!=inwViewpeople){
			    			inwViewpeopleManager.removeInwViewpeople(inwViewpeople.getId().toString());
			    		}
			    	}
	    		}
    		}
    	}
    	dao.removeInwDemands(ids);
    }
    
    //added for getInwDemandsByCrm
    public List getInwDemandsByCrm(CommonRecord crm, Pager pager){
	return dao.getInwDemandsByCrm(crm,pager);
	
    }
    

    public void checkInwDemand(String ids , String userName) {
		dao.checkInwDemand(ids , userName);
		
	}

    /**
     * 创新共赢的活动(需求)保存之前的不为空的校验----新需求变更
     * @author 　gw 2013-09-10   update 2013-11-12  gw 
     * @param inwDemand
     * @param errors
     * @param characterCoding
     * @return boolean
     */
	public boolean getEmptyCheck(InwDemand inwDemand,BindException errors,String characterCoding) {
		String name = inwDemand.getName();
		if(StringUtil.isEmpty(name)){
			errors.rejectValue("name", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "inwDemand.name") },this.getCharacterValue(characterCoding, "isNotNull"));
			return true;
		}
		String summary = inwDemand.getSummary();
		if(StringUtil.isEmpty(summary)){
			errors.rejectValue("summary", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "inwDemand.summary") }, this.getCharacterValue(characterCoding, "isNotNull"));
			return true;
		}
		String detailExplanation = inwDemand.getDetailExplanation();
		if(StringUtil.isEmpty(detailExplanation)){
			errors.rejectValue("detailExplanation", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "inwDemand.detailExplanation") },this.getCharacterValue(characterCoding, "isNotNull"));
			return true;
		}
		//需求分类表的ID即demandsort_id字段不为空的校验
		String demandsortId = inwDemand.getDemandsortId();
		if(StringUtil.isEmpty(demandsortId)){
			errors.rejectValue("demandsortId", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "inwDemand.demandsortId") }, this.getCharacterValue(characterCoding, "isNotNull"));
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
	 * 创新共赢新的需求---图片在录入之前不为空的校验---新需求
	 * @author gw 2013--11-12
	 * @param inwDemand
	 * @param errors
	 * @param characterCoding
	 * @return
	 */
	public boolean getEmptyCheckForDemandImage(InwDemand inwDemand,
			BindException errors, String characterCoding) {
		String demandImage = inwDemand.getDemandImage();
		if(StringUtil.isEmpty(demandImage)){
			errors.rejectValue("demandImage", "isNotNull",new Object[] {this.getCharacterValue(characterCoding, "inwDemand.demandImage") }, this.getCharacterValue(characterCoding, "isNotNull"));
			return true;
		}
		return false;
	}

	/**
	 * 创新共赢-------新需求------删除需求分类这个大类时,同步删除创新共赢需求表的各子类
	 * @author gw  2013-11-12
	 * @param demandsortId
	 */
	public void deleteInwDemandByDemandsortId(String demandsortId) {
		dao.deleteInwDemandByDemandsortId(demandsortId);		
	}

	/**
	 * 创新共赢--------------新需求-------------通过ID获取需求的名称
	 * @author gw 2013-11-15
	 * @param id
	 * @return
	 */
	public String getInwDemandById(String id) {
		return dao.getInwDemandById(id);
	}

	/**
	 * 根据需求分类大类别的ID查询出该大类下所有小类需求
	 * @author gw 2014-06-25
	 * @param demandsortID
	 * @return list 
	 */
	public List getInwDemandByDemandsortId(String demandsortID) {
		return dao.getInwDemandByDemandsortId(demandsortID);
	}
	
}
