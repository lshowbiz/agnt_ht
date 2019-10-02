package com.joymain.jecs.sys.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pd.model.RspEntity;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysUserDao;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
 
public class SysUserManagerImpl extends BaseManager implements SysUserManager {
	public List getObjsBySQL(final String sqlQuery) {
		return userDao.getObjsBySQL(sqlQuery);
	}

	public void saveObjecsBySQL(final String sqlQuery) {
		userDao.saveObjecsBySQL(sqlQuery);
	}

	public List getSysUserNotInRole(CommonRecord crm, Pager pager) {
		// TODO Auto-generated method stub
		return userDao.getSysUserNotInRole(crm, pager);
	}

	public List getSysUsers(CommonRecord crm, Pager pager) {
		return userDao.getSysUsersByCrm(crm, pager);
	}

	private SysUserDao userDao;

	public void setSysUserDao(SysUserDao dao) {
		this.userDao = dao;
	}

	/**
	 * 鏍规嵁鐢ㄦ埛缂栧彿鑾峰彇鐢ㄦ埛淇℃伅
	 * 
	 * @param userCode
	 * @return
	 */
	public SysUser getSysUser(final String userCode) {
		return userDao.getSysUser(userCode);
	}
	
	public SysUser getUserByToken(String userCode, String token) {
		SysUser user = userDao.getSysUser(userCode);
		if(user!=null && token.equals(user.getToken())){
			return user;
		}
		return null;
	}

	public SysUser login(String userCode, String password) throws AppException {
		SysUser sysUser = userDao.getSysUser(userCode);
		// 鍒ゆ柇鐢ㄦ埛鏄惁瀛樺湪
		if (sysUser == null) {
			throw new AppException("sys.message.userNameWrong");
		}
		
		
		if("C".equalsIgnoreCase(sysUser.getUserType())){
			if(StringUtil.encodePassword(password, "md5").equalsIgnoreCase(sysUser.getPassword())){
				return sysUser;
			}else{
				throw new AppException("sys.message.passwordWrong");
			}
		}
		// 鏈�ぇ鐧诲綍娆℃暟
		String maxLoginTimes = Constants.sysConfigMap.get(
				sysUser.getCompanyCode()).get("sys.login.maxtimes");
		// 閿佸畾鐨勫ぉ鏁�		
		String maxLockedHour = Constants.sysConfigMap.get(
				sysUser.getCompanyCode()).get("sys.login.maxlockedhour");
		if (sysUser.getFailureTimes() >= StringUtil.formatInt(maxLoginTimes)) {
			if (sysUser.getLastLoginErrorTime()!=null&&(sysUser.getLastLoginErrorTime().getTime() + 1000 * 60 * 60
					* StringUtil.formatInt(maxLockedHour)) > new Date()
					.getTime()) {
				// 鎻愮ず琚攣瀹�				throw new AppException("sys.message.maxLockedHour");
			}
			sysUser.setLastLoginErrorTime(null);
			sysUser.setFailureTimes(0);
			userDao.saveSysUser(sysUser);

			// 瓒呰繃鏈�ぇ閿欒娆℃暟闄愬埗
			// throw new AppException("sys.message.maxLoginTimes");
		}

		// 鍒ゆ柇鑳藉惁鐧诲綍
		if (!StringUtil.encodePassword(password, "md5").equalsIgnoreCase(
				sysUser.getPassword())) {

			// 璁板綍鏈�悗鐧诲綍閿欒鏃堕棿銆佸け璐ユ鏁�			sysUser.setLastLoginErrorTime(new Date());
			sysUser.setFailureTimes(sysUser.getFailureTimes() + 1);
			userDao.saveSysUser(sysUser);

			throw new AppException("sys.message.passwordWrong");
		}

		// 鎴愬姛鍚庢竻鎺夐敊璇俊鎭�		sysUser.setLastLoginErrorTime(null);
		sysUser.setFailureTimes(0);
		userDao.saveSysUser(sysUser);
		return sysUser;
		// SysUser example;
		//
		// // 鍒ゆ柇鐢ㄦ埛鏄惁瀛樺湪鍙婂瘑鐮佹槸鍚︽纭�		// example = new SysUser();
		// example.setUserCode(userCode);
		// example.setPassword(StringUtil.encodePassword(password, "md5"));
		// List list = userDao.getSysUserByExample(example);
		// if (list.size() != 1) {
		// throw new AppException("sys.message.userNameOrPasswordWrong");
		// }

		// 鑾峰彇璇ョ敤鎴蜂俊鎭�鐢ㄦ埛鎵�湁鐨勮彍鍗曚俊鎭�鐢ㄦ埛鎵�湁鏉冮檺淇℃伅
		// return (SysUser) list.get(0);
	}
	public Map isLogin(String userCode, String password) throws AppException {
		Map<String, Object> resultMap=new HashMap<String, Object>();
		String msg="";
		
		
				
				
		SysUser sysUser = userDao.getSysUser(userCode);
		if (sysUser == null|| !"C".equalsIgnoreCase(sysUser.getUserType())) {
			//throw new AppException("sys.message.userNameWrong");
			resultMap.put("login", false);
			resultMap.put("msg", "sys.message.userNameWrong");
			resultMap.put("sysUser", sysUser);
			return resultMap;
		}
		
		if ("0".equals(sysUser.getState()) || "2".equals(sysUser.getState())) {
			resultMap.put("login", false);
			resultMap.put("msg", "sys.message.userIsRestrict");
			resultMap.put("sysUser", sysUser);
			return resultMap;
		}
		
		if(!StringUtil.encodePassword(password, "md5").equalsIgnoreCase(sysUser.getPassword())){
			String maxLoginTimes = Constants.sysConfigMap.get(
					sysUser.getCompanyCode()).get("sys.login.maxtimes");
			String maxLockedHour = Constants.sysConfigMap.get(
					sysUser.getCompanyCode()).get("sys.login.maxlockedhour");
			Integer failureTimes = sysUser.getFailureTimes()==null?0:sysUser.getFailureTimes();
			if (failureTimes+1 >= StringUtil.formatInt(maxLoginTimes)) {
				
				sysUser.setLastLoginErrorTime(null);
				sysUser.setFailureTimes(StringUtil.formatInt(maxLoginTimes));
				sysUser.setState("0");
				userDao.saveSysUser(sysUser);
				msg="账号已锁定";
				
			}else{
				sysUser.setFailureTimes(failureTimes + 1);
				int remainingTimes= StringUtil.formatInt(maxLoginTimes) -sysUser.getFailureTimes();
				msg="密码不正确!连续错误"+(maxLoginTimes)+"次后将锁定账号,剩余"+(remainingTimes<0?0:remainingTimes)+"次";
				userDao.saveSysUser(sysUser);
				
			}
			resultMap.put("login", false);
			resultMap.put("msg", msg);
			resultMap.put("sysUser", sysUser);
			return resultMap;
			
		}
		sysUser.setFailureTimes(0);
		userDao.saveSysUser(sysUser);
		resultMap.put("login", true);
		resultMap.put("msg", msg);
		resultMap.put("sysUser", sysUser);
		return resultMap;
	}
	/**
	 * @see com.joymain.jecs.sys.dao.SysUserDao#saveSysUser(SysUser sysUser)
	 */
	public void saveSysUser(final SysUser sysUser) {
		userDao.saveSysUser(sysUser);
	}

	public List getSysUserRoleList(CommonRecord crm, Pager pager) {
		//return userDao.getSysUserRoleList(crm, pager);
		//modify by lihao,20170119,角色名称 不显示包含“禁用”的角色名
		return userDao.getSysUserRoleListNew(crm, pager);
	}

	public String getLoginKey(String userCode, String operationCode) {
		// TODO Auto-generated method stub
		String key = "";
		Calendar cal = Calendar.getInstance();
//		String text = "userCode=" + userCode + "&operationCode="
//				+ operationCode + "&time=" + cal.getTimeInMillis();
		String text =userCode +"~" + cal.getTimeInMillis();
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(Constants.ENCRYPT_PASSWORD);
		encryptor.setAlgorithm("PBEWithMD5AndDES");
		key = encryptor.encrypt(text);
		key=new String(Base64.encodeBase64(key.getBytes()));
		try {
			key = URLEncoder.encode(key, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			log.error("e" + e.getMessage());
		}
		return key;
	}
	
	/**
	 * 返回登录用户/密码匹配
	 * @userCodeAndPsswordJson：用户名和密码的json字符串
	 * @return：-2：用户名密码解析异常   -1：json解析异常  0：用户名为空   1：用户名不存在   2：用户名密码不匹配      3：匹配成功
	 */
	public RspEntity getLoginStatus(String userCodeAndPsswordJson){
		 String returnStr = userDao.getLoginStatus(userCodeAndPsswordJson);
		 String rspStr = "";
		 String subMsg = "";
		 if("-2".equals(returnStr)){
			 rspStr = "error";
			 subMsg = "用户名密码解析异常";
		 }else if("-1".equals(returnStr)){
			 rspStr = "error";
			 subMsg = "json解析异常";
		 }else if("0".equals(returnStr)){
			 rspStr = "error";
			 subMsg = "用户名为空";
		 }else if("1".equals(returnStr)){
			 rspStr = "error";
			 subMsg = "用户名不存在";
		 }else if("2".equals(returnStr)){
			 rspStr = "error";
			 subMsg = "用户名密码不匹配";
		 }else if("3".equals(returnStr)){
			 rspStr = "suss";
			 subMsg = "匹配成功";
		 }
		 RspEntity rsp = new RspEntity();
		 rsp.setRsp(rspStr);
		 rsp.setSub_msg(subMsg);
		 return rsp;
	}

}
