package com.joymain.jecs.pm.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.MsgSendService;

import com.joymain.jecs.pm.dao.JocsInterfaceRetransmissionDao;
import com.joymain.jecs.pm.model.JocsInterfaceRetransmission;
import com.joymain.jecs.pm.service.JocsInterfaceRetransmissionManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.JocsInterfaceLog;
import com.joymain.jecs.util.ReportLogUtilService;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JocsInterfaceRetransmissionManagerImpl extends BaseManager implements
    JocsInterfaceRetransmissionManager
{
    private JocsInterfaceRetransmissionDao dao;
    
    /**
     * Set the Dao for communication with the data layer.
     * 
     * @param dao
     */
    public void setJocsInterfaceRetransmissionDao(JocsInterfaceRetransmissionDao dao)
    {
        this.dao = dao;
    }
    
    /**
     * @see com.joymain.jecs.pm.service.JocsInterfaceRetransmissionManager#getJocsInterfaceRetransmissions(com.joymain.jecs.pm.model.JocsInterfaceRetransmission)
     */
    public List getJocsInterfaceRetransmissions(
        final JocsInterfaceRetransmission jocsInterfaceRetransmission)
    {
        return dao.getJocsInterfaceRetransmissions(jocsInterfaceRetransmission);
    }
    
    /**
     * @see com.joymain.jecs.pm.service.JocsInterfaceRetransmissionManager#getJocsInterfaceRetransmission(String
     *      logId)
     */
    public JocsInterfaceRetransmission getJocsInterfaceRetransmission(final String logId)
    {
        return dao.getJocsInterfaceRetransmission(new BigDecimal(logId));
    }
    
    /**
     * @see com.joymain.jecs.pm.service.JocsInterfaceRetransmissionManager#saveJocsInterfaceRetransmission(JocsInterfaceRetransmission
     *      jocsInterfaceRetransmission)
     */
    public void saveJocsInterfaceRetransmission(
        JocsInterfaceRetransmission jocsInterfaceRetransmission)
    {
        dao.saveJocsInterfaceRetransmission(jocsInterfaceRetransmission);
    }
    
    /**
     * @see com.joymain.jecs.pm.service.JocsInterfaceRetransmissionManager#removeJocsInterfaceRetransmission(String
     *      logId)
     */
    public void removeJocsInterfaceRetransmission(final String logId)
    {
        dao.removeJocsInterfaceRetransmission(new BigDecimal(logId));
    }
    
    // added for getJocsInterfaceRetransmissionsByCrm
    public List getJocsInterfaceRetransmissionsByCrm(CommonRecord crm, Pager pager)
    {
        return dao.getJocsInterfaceRetransmissionsByCrm(crm, pager);
    }
    
    
    @Override   
	public String retranInterfaceInfo(String logIds) {
		String result = "0";
		try{
			if(",".equals(String.valueOf(logIds.charAt(logIds.length()-1)))){
				logIds = logIds.substring(0, logIds.length()-1);
			}
			String[] logStrs = logIds.split(",");
			for(String logId : logStrs){
				//将数据加入到Map对象集合
				JocsInterfaceRetransmission jifrm = this.getJocsInterfaceRetransmission(logId);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("logId", jifrm.getLogId());
				map.put("logType", jifrm.getLogType());
				map.put("flag", jifrm.getFlag());
				map.put("method", jifrm.getMethod());
				map.put("type", jifrm.getType());
				map.put("charset", jifrm.getCharset());
				map.put("ver", jifrm.getVer());
				map.put("content", jifrm.getContent());
				
				//保存完成后修改状态
				jifrm.setSender(jifrm.getSender());
				jifrm.setRetranStatus("1");
				jifrm.setRetranType("1");//手动重发
				this.saveJocsInterfaceRetransmission(jifrm);
				
				//调用发送接口---------------------------开始
				MsgSendService msgSendService = new MsgSendService();
				msgSendService.setSender(jifrm.getSender());
//				//方法名
				String aa = msgSendService.post(jifrm.getContent(), jifrm.getMethod());
				//调用发送接口---------------------------结束
				
				//=============================接口日志写入开始
				JocsInterfaceLog jocsInterfaceLog = new JocsInterfaceLog();
				jocsInterfaceLog.setLogType("0");//0：本地发送数据    1：本地接收数据
				jocsInterfaceLog.setSender(jifrm.getSender());//数据的接收方
				jocsInterfaceLog.setMethod(jifrm.getMethod());//方法名称
				jocsInterfaceLog.setContent(jifrm.getContent());//发送内容content
				jocsInterfaceLog.setReturnDesc(aa);//返回内容
				
				ReportLogUtilService.addJocsInterface(jocsInterfaceLog);//写入日志操作
				//=============================接口日志写入完毕
				
				//Modify By WuCF 20151210 返回如果为空，则直接提示失败，重新再点击重发。
				if(aa==null || "".equals(aa)){
					return "2";
				}
			}
		}catch(Exception e){
			result = "1";
			e.printStackTrace();
		}
		return result;
	}
	
	 @Override
    public void resendMsg()
    {
        JocsInterfaceRetransmission jifrm = new JocsInterfaceRetransmission();
        jifrm.setRetranStatus("0");// 表示未重新发送消息
        jifrm.setRetranType("0");// 表示自动扫描重新发送消息
        
        List<JocsInterfaceRetransmission> jifList = dao.getJocsInterfaceRetransmissions(jifrm);
        if (null != jifList && 0 < jifList.size())
        {
            for (JocsInterfaceRetransmission sendJifrm : jifList)
            {
                MsgSendService mhs = new MsgSendService();
                mhs.setSender(sendJifrm.getSender());
                mhs.post(sendJifrm.getContent(), sendJifrm.getMethod());
            }
        }
    }
}
