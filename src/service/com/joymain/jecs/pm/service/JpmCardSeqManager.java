
package com.joymain.jecs.pm.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pm.model.JpmCardSeq;
import com.joymain.jecs.pm.dao.JpmCardSeqDao;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpmCardSeqManager extends Manager {
    /**
     * Retrieves all of the jpmCardSeqs
     */
    public List getJpmCardSeqs(JpmCardSeq jpmCardSeq);

    /**
     * Gets jpmCardSeq's information based on id.
     * @param id the jpmCardSeq's id
     * @return jpmCardSeq populated jpmCardSeq object
     */
    public JpmCardSeq getJpmCardSeq(final String id);

    /**
     * Saves a jpmCardSeq's information
     * @param jpmCardSeq the object to be saved
     */
    public void saveJpmCardSeq(JpmCardSeq jpmCardSeq);

    /**
     * Removes a jpmCardSeq from the database by id
     * @param id the jpmCardSeq's id
     */
    public void removeJpmCardSeq(final String id);
    //added for getJpmCardSeqsByCrm
    public List getJpmCardSeqsByCrm(CommonRecord crm, Pager pager);
    /**
     * 保存当前订单中有虚拟产品的帐号，如果帐号用完抛出Exection cardNo.not.find
     * 如果不存在产品，则不保存 
     * @param jpoMemberOrder
     * 
     */
    public void saveUserJpmCardSeq(JpoMemberOrder jpoMemberOrder,String oldCard,String newCard);
    /**
     * 手动录入JOYME号
     * @param jpmCardSeq
     */
    public void saveUserJpmCardSeqByUser(JpmCardSeq jpmCardSeq,int qty);

	public List getJpmCardSeqGrade(CommonRecord crm,String strAction);
}

