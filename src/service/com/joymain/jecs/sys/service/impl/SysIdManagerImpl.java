package com.joymain.jecs.sys.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysIdDao;
import com.joymain.jecs.sys.model.SysId;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;

public class SysIdManagerImpl extends BaseManager implements SysIdManager {
	private SysIdDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setSysIdDao(SysIdDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysIdManager#getSysIds(com.joymain.jecs.sys.model.SysId)
	 */
	public List getSysIds(final SysId sysId) {
		return dao.getSysIds(sysId);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysIdManager#getSysId(String id)
	 */
	public SysId getSysId(final String id) {
		return dao.getSysId(new Long(id));
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysIdManager#saveSysId(SysId sysId)
	 */
	public void saveSysId(SysId sysId) {
		dao.saveSysId(sysId);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysIdManager#removeSysId(String id)
	 */
	public void removeSysId(final String id) {
		dao.removeSysId(new Long(id));
	}

	/**
	 * 根据ID获取对应的序列器
	 * @param idCode
	 * @return
	 */
	public SysId getSysIdByCode(final String idCode) {
		return dao.getSysIdByCode(idCode);
	}

	/**
	 * 取得一个对应表的唯一主键
	 * @param table 指定的数据表
	 * @param idName 指定对应表的那个字段
	 * @return String =""表示未取得唯一主键
	 */

	public String buildIdStr(final String idCode) {
		if (idCode == null) {
			throw new AppException("idCode is empty!");
		}
		Map resultMap=dao.callProcBuildIdStr(idCode);
		if(resultMap==null || resultMap.get("p_out_code")==null){
			log.info("过程调用失败");
			throw new AppException("过程调用失败!");
		}
		
		return resultMap.get("p_out_code").toString();
	}
	
	/**
	 * 换货单和发货退回单生成发货单时发货单编号用新的函数
	 * @author fu 2016-03-17 
	 * @param idCode
	 * @return 
	 */
	public String buildIdStrTwo(final String idCode){
		if (idCode == null) {
			throw new AppException("idCode is empty!");
		}
		Map resultMap=dao.callProcBuildIdStrTwo(idCode);
		if(resultMap==null || resultMap.get("p_out_code")==null){
			log.info("过程调用失败");
			throw new AppException("过程调用失败!");
		}
		
		return resultMap.get("p_out_code").toString();
	}
	
	public String getBuildIdStr(String idCode){
		return this.buildIdStr(idCode);
	}

	/**
	 * 根据外部参数分页获取对应的序列器列表
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getSysIdsByPage(CommonRecord crm, Pager pager) {
		return dao.getSysIdsByPage(crm, pager);
	}
}
