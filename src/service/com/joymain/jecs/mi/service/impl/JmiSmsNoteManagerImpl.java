
package com.joymain.jecs.mi.service.impl;

import java.util.Date;
import java.util.List;

import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysManagerDao;
import com.joymain.jecs.sys.dao.SysUserDao;
import com.joymain.jecs.sys.model.SysManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.mi.model.JmiSmsNote;
import com.joymain.jecs.mi.dao.JmiSmsNoteDao;
import com.joymain.jecs.mi.service.JmiSmsNoteManager;
import com.joymain.jecs.util.ConfigUtil;
import com.joymain.jecs.util.ListUtil;
import com.joymain.jecs.util.StringUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.smssend.SmsSend;
public class JmiSmsNoteManagerImpl extends BaseManager implements JmiSmsNoteManager {
    private JmiSmsNoteDao jmiSmsNoteDao;
    
    private SysManagerDao sysManagerDao;
    private SysUserDao sysUserDao;
    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiSmsNoteDao(JmiSmsNoteDao jmiSmsNoteDao) {
        this.jmiSmsNoteDao = jmiSmsNoteDao;
    }
    public void setSysManagerDao(SysManagerDao sysManagerDao) {
        this.sysManagerDao = sysManagerDao;
    }
    

	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}
    /**
     * @see com.joymain.jecs.mi.service.JmiSmsNoteManager#getJmiSmsNotes(com.joymain.jecs.mi.model.JmiSmsNote)
     */
    public List getJmiSmsNotes(final JmiSmsNote jmiSmsNote) {
        return jmiSmsNoteDao.getJmiSmsNotes(jmiSmsNote);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiSmsNoteManager#getJmiSmsNote(String id)
     */
    public JmiSmsNote getJmiSmsNote(final String id) {
        return jmiSmsNoteDao.getJmiSmsNote(new BigDecimal(id));
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiSmsNoteManager#saveJmiSmsNote(JmiSmsNote jmiSmsNote)
     */
    public void saveJmiSmsNote(JmiSmsNote jmiSmsNote) {
    	jmiSmsNoteDao.saveJmiSmsNote(jmiSmsNote);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiSmsNoteManager#removeJmiSmsNote(String id)
     */
    public void removeJmiSmsNote(final String id) {
    	jmiSmsNoteDao.removeJmiSmsNote(new BigDecimal(id));
    }
    //added for getJmiSmsNotesByCrm
    public List getJmiSmsNotesByCrm(CommonRecord crm, Pager pager){
	  return jmiSmsNoteDao.getJmiSmsNotesByCrm(crm,pager);
    }
	public JmiSmsNote getJmiSmsNoteByUserCode(String userCode) {
		return jmiSmsNoteDao.getJmiSmsNoteByUserCode(userCode);
	}
    public String sendSms(String userCode,String passWord){

		String verifyCode=StringUtil.generateCode(6);
		//调用短信接口=======

		String accessKeyId = (String)ConfigUtil.getConfigValue("CN","sms.accesskeyid");
		String accessKeySecret = (String)ConfigUtil.getConfigValue("CN","sms.accesskeysecret");
		String signName = (String)ConfigUtil.getConfigValue("CN","sms.signname");//阿里云短信控制台创建的签名名称
		String templateCode = (String)ConfigUtil.getConfigValue("CN","sms.templatecode");//阿里云短信控制台创建的模板CODE
		
		String paramstring = (String)ConfigUtil.getConfigValue("CN","sms.cloudshop.paramstring");
		String validTime = (String)ConfigUtil.getConfigValue("CN","ec.sms.valid.time");
		
		String msgInfo  = "亲爱的中脉家人，您的验证码为:"+verifyCode+"，请在"+validTime+"秒内输入验证，逾期后您需要重新获取一个验证码。";
		//调用短信发送平台方法
		//SmsSend.sendSms(url1, url2, phone, msgInfo);
		
		//
		//String [] result=new String[2];
		Date curDate=new Date();

		   
		long validTimeLong=StringUtil.formatLong(validTime);
		
		JmiSmsNote resJmiSmsNote=this.getJmiSmsNoteByUserCode(userCode);
		
		if(resJmiSmsNote!=null){
			long res_time=resJmiSmsNote.getCreateTime().getTime();
			long cur_time=curDate.getTime();
			long time=(cur_time-res_time)/1000;
			if(time<validTimeLong){
				return "验证码已发送";
			}
			
		}
		
		String phone="";
		SysManager sysManager= sysManagerDao.getSysManager(userCode);
		if(sysManager==null || StringUtils.isEmpty(sysManager.getMobile()) ){
			return "账号资料缺失,补充个人账号资料";
		}else{
			phone=sysManager.getMobile();
		}
		
		SysUser sysUser= sysUserDao.getSysUser(userCode);
		
		if(!StringUtil.encodePassword(passWord, "md5").equalsIgnoreCase(sysUser.getPassword())){
			return "密码错误";
		}
		//调用短信发送平台方法
		String msg = SmsSend.sendSmsVerifyCode(verifyCode, validTime, phone,accessKeyId, accessKeySecret, signName, templateCode, paramstring);
		if(StringUtils.isEmpty(msg)){
			JmiSmsNote jmiSmsNote=new JmiSmsNote();
			jmiSmsNote.setPhone(phone);
			jmiSmsNote.setVerifyCode(verifyCode);
			jmiSmsNote.setCreateTime(curDate);
			jmiSmsNote.setUserCode(userCode);
			this.saveJmiSmsNote(jmiSmsNote);
		}
		
		return msg;
		
		
	}
    
}
