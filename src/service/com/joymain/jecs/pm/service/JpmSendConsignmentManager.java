package com.joymain.jecs.pm.service;

import java.util.List;

import com.joymain.jecs.mi.model.JmiAddrBook;
import com.joymain.jecs.pm.model.JpmSendConsignment;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmSendConsignmentManager extends Manager
{
    /**
     * Retrieves all of the jpmSendConsignments
     */
    public List getJpmSendConsignments(JpmSendConsignment jpmSendConsignment);
    
    /**
     * Gets jpmSendConsignment's information based on consignmentNo.
     * 
     * @param consignmentNo the jpmSendConsignment's consignmentNo
     * @return jpmSendConsignment populated jpmSendConsignment object
     */
    public JpmSendConsignment getJpmSendConsignment(final String consignmentNo);
    
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
    public void removeJpmSendConsignment(final String consignmentNo);
    
    // added for getJpmSendConsignmentsByCrm
    public List getJpmSendConsignmentsByCrm(CommonRecord crm, Pager pager);
    
    public List<JpmSendConsignment> getJpmSendConsignmentListBySpecNo(Long specNo);
    
    public void delJpmSendConsignmentByConsignmentNo(Long consignmentNo);
    
    public JpmSendConsignment getJpmSendConsignmentByConsignmentNo(Long consignmentNo);
    
    public String getAddresByFabId(JmiAddrBook jmiAddrBook);
}
