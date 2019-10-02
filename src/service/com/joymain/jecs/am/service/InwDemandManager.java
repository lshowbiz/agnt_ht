
package com.joymain.jecs.am.service;

import java.util.List;

import org.springframework.validation.BindException;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.am.model.AmAnnounce;
import com.joymain.jecs.am.model.InwDemand;
import com.joymain.jecs.am.dao.InwDemandDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface InwDemandManager extends Manager {
    /**
     * Retrieves all of the inwDemands
     */
    public List getInwDemands(InwDemand inwDemand);

    /**
     * Gets inwDemand's information based on id.
     * @param id the inwDemand's id
     * @return inwDemand populated inwDemand object
     */
    public InwDemand getInwDemand(final String id);

    /**
     * Saves a inwDemand's information
     * @param inwDemand the object to be saved
     */
    public void saveInwDemand(InwDemand inwDemand);

    /**
     * Removes a inwDemand from the database by id
     * @param id the inwDemand's id
     */
    public void removeInwDemand(final String id);
    public void removeInwDemands(String ids);
    //added for getInwDemandsByCrm
    public List getInwDemandsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * 审核
     * @param ids
     * @param userName
     */
    public void checkInwDemand(String ids ,String userName);

    /**
     * 创新共赢的活动(需求)保存之前的不为空的校验
     * @author 　gw 2013-09-10
     * @param inwDemand
     * @param errors
     * @param characterCoding
     * @return boolean
     */
	public boolean getEmptyCheck(InwDemand inwDemand,BindException errors,String characterCoding);

	/**
	 * 创新共赢新的需求---图片在录入之前不为空的校验
	 * @author gw 2013--11-12
	 * @param inwDemand
	 * @param errors
	 * @param characterCoding
	 * @return
	 */
	public boolean getEmptyCheckForDemandImage(InwDemand inwDemand,	BindException errors, String characterCoding);

	/**
	 * 创新共赢-------新需求------删除需求分类这个大类时,同步删除创新共赢需求表的各子类
	 * @author gw  2013-11-12
	 * @param id
	 */
	public void deleteInwDemandByDemandsortId(String id);
	
	
	/**
	 * 创新共赢--------------新需求-------------通过ID获取需求的名称
	 * @author gw 2013-11-15
	 * @param id
	 * @return
	 */
	public String getInwDemandById(String id);
	
	
	/**
	 * 根据需求分类大类别的ID查询出该大类下所有小类需求
	 * @author gw 2014-06-25
	 * @param demandsortID
	 * @return list 
	 */
	public List getInwDemandByDemandsortId(String demandsortID);
	
}



