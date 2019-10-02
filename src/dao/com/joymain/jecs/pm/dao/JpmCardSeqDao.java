
package com.joymain.jecs.pm.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pm.model.JpmCardSeq;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmCardSeqDao extends Dao {

    /**
     * Retrieves all of the jpmCardSeqs
     */
    public List getJpmCardSeqs(JpmCardSeq jpmCardSeq);

    /**
     * Gets jpmCardSeq's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jpmCardSeq's id
     * @return jpmCardSeq populated jpmCardSeq object
     */
    public JpmCardSeq getJpmCardSeq(final Long id);

    /**
     * Saves a jpmCardSeq's information
     * @param jpmCardSeq the object to be saved
     */    
    public void saveJpmCardSeq(JpmCardSeq jpmCardSeq);

    /**
     * Removes a jpmCardSeq from the database by id
     * @param id the jpmCardSeq's id
     */
    public void removeJpmCardSeq(final Long id);
    //added for getJpmCardSeqsByCrm
    public List getJpmCardSeqsByCrm(CommonRecord crm, Pager pager);
    
    public JpmCardSeq getJpmCardSeqByCardNoAndSeq(String cardNo,String seq); 
    /**
     * 随机找出未使用的帐号
     * @param qty
     * @return
     */
    public String getJpmCardSeqsNotUse(int qty);
    
    public List getJpmCardSeqByIds(String ids);
	public List getJpmCardSeqGrade(CommonRecord crm,String strAction);
}

