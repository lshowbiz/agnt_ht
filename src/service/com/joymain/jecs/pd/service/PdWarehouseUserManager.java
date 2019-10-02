
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdWarehouseUser;
import com.joymain.jecs.pd.dao.PdWarehouseUserDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdWarehouseUserManager extends Manager {
    
	
	/**
	 * 得到仓库员管理的仓库,并转化成String. 结构是 "'-x#x-','warehouseNo1','warehouseNo2'"
	 * 
	 * @param userCode
	 * @return
	 */
	public String getStringWarehouseByUser(String userCode);
	/**
	 * 得到仓库员管理的仓库
	 */
	public List getPdWarehouseByUser(String userCode);
	/**
     * Retrieves all of the pdWarehouseUsers
     */
    public List getPdWarehouseUsers(PdWarehouseUser pdWarehouseUser);

    /**
     * Gets pdWarehouseUser's information based on wuId.
     * @param wuId the pdWarehouseUser's wuId
     * @return pdWarehouseUser populated pdWarehouseUser object
     */
    public PdWarehouseUser getPdWarehouseUser(final String wuId);

    /**
     * Saves a pdWarehouseUser's information
     * @param pdWarehouseUser the object to be saved
     */
    public void savePdWarehouseUser(PdWarehouseUser pdWarehouseUser);

    /**
     * Removes a pdWarehouseUser from the database by wuId
     * @param wuId the pdWarehouseUser's wuId
     */
    public void removePdWarehouseUser(final String wuId);
    //added for getPdWarehouseUsersByCrm
    public List getPdWarehouseUsersByCrm(CommonRecord crm, Pager pager);
}

