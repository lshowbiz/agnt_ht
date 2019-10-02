
package com.joymain.jecs.pm.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pm.model.JocsInterfaceRetransmission;
import com.joymain.jecs.pm.dao.JocsInterfaceRetransmissionDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JocsInterfaceRetransmissionManager extends Manager {
    /**
     * Retrieves all of the jocsInterfaceRetransmissions
     */
    public List getJocsInterfaceRetransmissions(JocsInterfaceRetransmission jocsInterfaceRetransmission);

    /**
     * Gets jocsInterfaceRetransmission's information based on logId.
     * @param logId the jocsInterfaceRetransmission's logId
     * @return jocsInterfaceRetransmission populated jocsInterfaceRetransmission object
     */
    public JocsInterfaceRetransmission getJocsInterfaceRetransmission(final String logId);

    /**
     * Saves a jocsInterfaceRetransmission's information
     * @param jocsInterfaceRetransmission the object to be saved
     */
    public void saveJocsInterfaceRetransmission(JocsInterfaceRetransmission jocsInterfaceRetransmission);

    /**
     * Removes a jocsInterfaceRetransmission from the database by logId
     * @param logId the jocsInterfaceRetransmission's logId
     */
    public void removeJocsInterfaceRetransmission(final String logId);
    //added for getJocsInterfaceRetransmissionsByCrm
    public List getJocsInterfaceRetransmissionsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * Add By WuCF 20141209 接口数据重发
     * @param logId
     * @return
     */
    public String retranInterfaceInfo(String logIds);
    
    /**
     * 定时器扫描重发错误消息
     * @return
     */
    public void resendMsg();
}

