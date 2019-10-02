
package com.joymain.jecs.pd.dao;

import java.util.List;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdShUrl;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdShUrlDao extends Dao {

    /**
     * Retrieves all of the pdShUrls
     */
    public List getPdShUrls(PdShUrl pdShUrl);

    /**
     * Gets pdShUrl's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the pdShUrl's id
     * @return pdShUrl populated pdShUrl object
     */
    public PdShUrl getPdShUrl(final Long id);

    /**
     * Saves a pdShUrl's information
     * @param pdShUrl the object to be saved
     */    
    public void savePdShUrl(PdShUrl pdShUrl);

    /**
     * Removes a pdShUrl from the database by id
     * @param id the pdShUrl's id
     */
    public void removePdShUrl(final Long id);
    //added for getPdShUrlsByCrm
    public List getPdShUrlsByCrm(CommonRecord crm, Pager pager);

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

