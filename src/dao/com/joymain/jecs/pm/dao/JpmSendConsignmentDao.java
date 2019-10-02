package com.joymain.jecs.pm.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pm.model.JpmSendConsignment;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmSendConsignmentDao extends Dao
{
    
    /**
     * Retrieves all of the jpmSendConsignments
     */
    public List getJpmSendConsignments(JpmSendConsignment jpmSendConsignment);
    
    /**
     * Gets jpmSendConsignment's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
     * found.
     * 
     * @param consignmentNo the jpmSendConsignment's consignmentNo
     * @return jpmSendConsignment populated jpmSendConsignment object
     */
    public JpmSendConsignment getJpmSendConsignment(final BigDecimal consignmentNo);
    
    /**
     * Saves a jpmSendConsignment's information
     * 
     * @param jpmSendConsignment the object to be saved
     */
    public void saveJpmSendConsignment(JpmSendConsignment jpmSendConsignment);
    
    /**
     * Removes a jpmSendConsignment from the database by consignmentNo
     * 
     * @param consignmentNo the jpmSendConsignment's consignmentNo
     */
    public void removeJpmSendConsignment(final BigDecimal consignmentNo);
    
    // added for getJpmSendConsignmentsByCrm
    public List getJpmSendConsignmentsByCrm(CommonRecord crm, Pager pager);
    
    public List<JpmSendConsignment> getJpmSendConsignmentListBySpecNo(Long specNo);
    
    public void delJpmSendConsignmentByConsignmentNo(Long consignmentNo);
    
    public JpmSendConsignment getJpmSendConsignmentByConsignmentNo(Long consignmentNo);
}
