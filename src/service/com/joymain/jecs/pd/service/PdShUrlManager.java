package com.joymain.jecs.pd.service;

import java.util.List;

import org.springframework.validation.BindException;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdShUrl;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdShUrlManager extends Manager {
    /**
     * Retrieves all of the pdShUrls
     */
    public List getPdShUrls(PdShUrl pdShUrl);

    /**
     * Gets pdShUrl's information based on id.
     * @param id the pdShUrl's id
     * @return pdShUrl populated pdShUrl object
     */
    public PdShUrl getPdShUrl(final String id);

    /**
     * Saves a pdShUrl's information
     * @param pdShUrl the object to be saved
     */
    public void savePdShUrl(PdShUrl pdShUrl);

    /**
     * Removes a pdShUrl from the database by id
     * @param id the pdShUrl's id
     */
    public void removePdShUrl(final String id);
    //added for getPdShUrlsByCrm
    public List getPdShUrlsByCrm(CommonRecord crm, Pager pager);

    /**
     * 物流公司链接不为空的校验
     * @author yxzz 2014-07-09
     * @param pdShUrl
     * @param errors
     * @param defCharacterCoding
     * @return boolean
     */
	public boolean getCheckEmpty(PdShUrl pdShUrl, BindException errors,String defCharacterCoding);

	/**
	 * 物流公司编码唯一性校验
	 * @author yxzz 2014-07-09
	 * @param pdShUrl
	 * @return boolean
	 */
	public boolean getValueCodeUniqueCheck(PdShUrl pdShUrl);
	
	
	/**
	 * 获取物流公司链接地址
	 * @author gw  2014-07-10
	 * @param shNo 物流公司编码
	 * @return string 物流公司链接地址
	 */
	public String getShUrl(String shNo);
	
}

