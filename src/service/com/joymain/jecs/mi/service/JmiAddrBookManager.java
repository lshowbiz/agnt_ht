
package com.joymain.jecs.mi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.mi.model.JmiAddrBook;
import com.joymain.jecs.mi.dao.JmiAddrBookDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiAddrBookManager extends Manager {
	public List getJmiAddrBooksByUserCode(String userCode);
    /**
     * Retrieves all of the jmiAddrBooks
     */
    public List getJmiAddrBooks(JmiAddrBook jmiAddrBook);

    /**
     * Gets jmiAddrBook's information based on fabId.
     * @param fabId the jmiAddrBook's fabId
     * @return jmiAddrBook populated jmiAddrBook object
     */
    public JmiAddrBook getJmiAddrBook(final String fabId);

    /**
     * Saves a jmiAddrBook's information
     * @param jmiAddrBook the object to be saved
     */
    public void saveJmiAddrBook(JmiAddrBook jmiAddrBook);

    /**
     * Removes a jmiAddrBook from the database by fabId
     * @param fabId the jmiAddrBook's fabId
     */
    public void removeJmiAddrBook(final String fabId);
    //added for getJmiAddrBooksByCrm
    public List getJmiAddrBooksByCrm(CommonRecord crm, Pager pager);
    public void saveJmiAddrBookPc(JmiAddrBook jmiAddrBook);
    
    /**
     * 根据会员编号查询默认收货地址
     * @param userCode
     * @return
     */
    public JmiAddrBook getDefaultAddrBookByUserCode(String userCode);
}

