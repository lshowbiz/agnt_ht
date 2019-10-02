
package com.joymain.jecs.pr.dao;

import java.util.List;
import java.util.Set;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pr.model.JprRefund;
import com.joymain.jecs.pr.model.JprRefundList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JprRefundListDao extends Dao {

    /**
     * Retrieves all of the jprRefundLists
     */
    public List getJprRefundLists(JprRefundList jprRefundList);

    /**
     * Gets jprRefundList's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param prlId the jprRefundList's prlId
     * @return jprRefundList populated jprRefundList object
     */
    public JprRefundList getJprRefundList(final Long prlId);

    /**
     * Saves a jprRefundList's information
     * @param jprRefundList the object to be saved
     */    
    public void saveJprRefundList(JprRefundList jprRefundList);

    /**
     * Removes a jprRefundList from the database by prlId
     * @param prlId the jprRefundList's prlId
     */
    public void removeJprRefundList(final Long prlId);
    //added for getJprRefundListsByCrm
    public List getJprRefundListsByCrm(CommonRecord crm, Pager pager);
    /**
     * get JprRefundList by molid
     * @param molId
     * @return
     */
    public List<JprRefundList> getRefundListByMolId(Long molId,String roNo);
    
    /**
     * 查询退货明细
     * @author fu 2015-09-11
     * @param jprRefund
     * @param jprRefundSet
     * @return set 
     */
    public Set getRefundListForRono(JprRefund jprRefund,Set jprRefundSet);
    
    /**
     * @Description SQL删除数据
     * @author houxyu
     * @date 2016-3-4
     * @param roNo
     */
    public void removeJprRefundListBySql(String roNo);
    
}

