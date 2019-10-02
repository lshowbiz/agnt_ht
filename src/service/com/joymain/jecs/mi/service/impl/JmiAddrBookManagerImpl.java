
package com.joymain.jecs.mi.service.impl;

import java.util.List;

import com.joymain.jecs.mi.dao.JmiAddrBookDao;
import com.joymain.jecs.mi.dao.JmiMemberDao;
import com.joymain.jecs.mi.model.JmiAddrBook;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiAddrBookManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JmiAddrBookManagerImpl extends BaseManager implements JmiAddrBookManager {
    private JmiAddrBookDao dao;

    private JmiMemberDao jmiMemberDao;
    
    
    public void setJmiMemberDao(JmiMemberDao jmiMemberDao)
    {
        this.jmiMemberDao = jmiMemberDao;
    }

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiAddrBookDao(JmiAddrBookDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiAddrBookManager#getJmiAddrBooks(com.joymain.jecs.mi.model.JmiAddrBook)
     */
    public List getJmiAddrBooks(final JmiAddrBook jmiAddrBook) {
        return dao.getJmiAddrBooks(jmiAddrBook);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiAddrBookManager#getJmiAddrBook(String fabId)
     */
    public JmiAddrBook getJmiAddrBook(final String fabId) {
        return dao.getJmiAddrBook(new Long(fabId));
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiAddrBookManager#saveJmiAddrBook(JmiAddrBook jmiAddrBook)
     */
    public void saveJmiAddrBook(JmiAddrBook jmiAddrBook) {
        dao.saveJmiAddrBook(jmiAddrBook);
        
        //重置默认地址
        String userCode = jmiAddrBook.getJmiMember().getUserCode();
        List  jmiAddrBooks = this.getJmiAddrBooksByUserCode(userCode);
        if("1".equals(jmiAddrBook.getIsDefault())){
            for (int i = 0; i < jmiAddrBooks.size(); i++) {
                JmiAddrBook book=(JmiAddrBook)jmiAddrBooks.get(i);
                if(jmiAddrBook.getFabId()!=book.getFabId()){
                    book.setIsDefault("");
                    dao.saveObject(book);
                }
            }
        }
        List tempList=this.getJmiAddrBooksByUserCode(userCode);
        boolean flag=false;
        for (int i = 0; i < tempList.size(); i++) {
            JmiAddrBook book=(JmiAddrBook)tempList.get(i);
            if("1".equals(book.getIsDefault())){
                flag=true;
            }
        }
        if(!flag){
            JmiAddrBook book=(JmiAddrBook)tempList.get(0);
            book.setIsDefault("1");
            dao.saveObject(book);
        }
    }
    
    /**
     * @see com.joymain.jecs.mi.service.JmiAddrBookManager#removeJmiAddrBook(String fabId)
     */
    public void removeJmiAddrBook(final String fabId) {
        JmiAddrBook jmiAddrBook = dao.getJmiAddrBook(new Long(fabId));
        String isDefault = jmiAddrBook.getIsDefault();
        String userCode = jmiAddrBook.getJmiMember().getUserCode();
        dao.removeJmiAddrBook(new Long(fabId));
        //删除的是默认的地址，则另指定一个为默认地址
        if("1".equals(isDefault)) {
            List  jmiAddrBooks = this.getJmiAddrBooksByUserCode(userCode);
            if(jmiAddrBooks!=null && jmiAddrBooks.size()>0) {
                JmiAddrBook temp = (JmiAddrBook)jmiAddrBooks.get(0);
                temp.setIsDefault("1");
                dao.saveObject(temp);
            }
        }
    }
    //added for getJmiAddrBooksByCrm
    public List getJmiAddrBooksByCrm(CommonRecord crm, Pager pager){
	return dao.getJmiAddrBooksByCrm(crm,pager);
    }

	public List getJmiAddrBooksByUserCode(String userCode) {
		// TODO Auto-generated method stub
		return dao.getJmiAddrBooksByUserCode(userCode);
	}
	public void saveJmiAddrBookPc(JmiAddrBook jmiAddrBook) {
        JmiMember jmiMember=jmiMemberDao.getJmiMember(jmiAddrBook.getUserCode());
        jmiAddrBook.setJmiMember(jmiMember);
        dao.saveJmiAddrBook(jmiAddrBook);
    }
	
	/**
     * 根据会员编号查询默认收货地址
     * @param userCode
     * @return
     */
    public JmiAddrBook getDefaultAddrBookByUserCode(String userCode){
    	
    	return dao.getDefaultAddrBookByUserCode(userCode);
    }
}
