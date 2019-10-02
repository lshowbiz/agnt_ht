package com.joymain.jecs.mi.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.dao.BdPeriodDao;
import com.joymain.jecs.mi.dao.JmiAssureDao;
import com.joymain.jecs.mi.dao.JmiMemberDao;
import com.joymain.jecs.mi.model.JmiAssure;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiAssureManager;
import com.joymain.jecs.po.dao.JpoMemberOrderDao;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.string.StringUtil;

public class JmiAssureManagerImpl extends BaseManager implements JmiAssureManager {
	private JmiAssureDao dao;
	
	private JmiMemberDao jmiMemberDao;
	private BdPeriodDao bdPeriodDao;
	private JpoMemberOrderDao jpoMemberOrderDao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setJmiAssureDao(JmiAssureDao dao) {
		this.dao = dao;
	}

	public JmiMemberDao getJmiMemberDao() {
		return jmiMemberDao;
	}

	public void setJmiMemberDao(JmiMemberDao jmiMemberDao) {
		this.jmiMemberDao = jmiMemberDao;
	}

	public BdPeriodDao getBdPeriodDao() {
		return bdPeriodDao;
	}

	public void setBdPeriodDao(BdPeriodDao bdPeriodDao) {
		this.bdPeriodDao = bdPeriodDao;
	}
	

	public void setJpoMemberOrderDao(JpoMemberOrderDao jpoMemberOrderDao) {
		this.jpoMemberOrderDao = jpoMemberOrderDao;
	}

	public JpoMemberOrderDao getJpoMemberOrderDao() {
		return jpoMemberOrderDao;
	}

	/**
	 * @see com.joymain.jecs.mi.service.JmiAssureManager#getJmiAssures(com.joymain.jecs.mi.model.JmiAssure)
	 */
	public List getJmiAssures(final JmiAssure jmiAssure) {
		return dao.getJmiAssures(jmiAssure);
	}

	/**
	 * @see com.joymain.jecs.mi.service.JmiAssureManager#getJmiAssure(String id)
	 */
	public JmiAssure getJmiAssure(final Long id) {
		return dao.getJmiAssure(id);
	}

	/**
	 * @see com.joymain.jecs.mi.service.JmiAssureManager#saveJmiAssure(JmiAssure
	 *      jmiAssure)
	 */
	public void saveJmiAssure(JmiAssure jmiAssure) {
		dao.saveJmiAssure(jmiAssure);
	}

	public void updateJmiAssure(JmiAssure jmiAssure) {
		dao.updateJmiAssure(jmiAssure);
	}
	
	/**
	 * @see com.joymain.jecs.mi.service.JmiAssureManager#removeJmiAssure(String
	 *      id)
	 */
	public void removeJmiAssure(final Long id) {
		dao.removeJmiAssure(id);
	}

	public int removeJmiAssureById(final String id) {
		try {
			dao.removeJmiAssure(Long.parseLong(id));
			return 1;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public int removeJmiAssureByIds(String ids) {
		if(StringUtils.isEmpty(ids)) {
			return 0;
		}
		try {
			dao.removeJmiAssureByIds(ids);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	// added for getJmiAssuresByCrm
	public List getJmiAssuresByCrm(CommonRecord crm, Pager pager) {
		try {
			return dao.getJmiAssuresByCrm(crm, pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList();
	}
	
	@Override
	public List getReportByContion(CommonRecord crm) {
		try {
			return dao.getReportByContion(crm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList();
	}
	
	@Override
	public void saveJmiAssureList(List<JmiAssure> jmiAssureList) throws Exception {
		dao.saveJmiAssureList(jmiAssureList);
	}
	
	@Override
	public JpoMemberOrder getJpoMemberOrderByInterface(String memberOrderNo) {
		return dao.getJpoMemberOrderByInterface(memberOrderNo);
	}
	@Override
	public boolean getCheckAssure(BindException errors, JmiAssure assure,String characterCoding) {
		
		String assureType = assure.getAssureType();
		String userCode = assure.getUserCode();
		if(StringUtil.isEmpty(assureType)){
			errors.rejectValue("assureType", "isNotNull",
					new Object[]{this.getCharacterValue(characterCoding, "担保类型") },
					this.getCharacterValue(characterCoding, "担保类型不能为空"));
			return false;
		}
		
		//3、判断会员是否存在
		if(StringUtil.isEmpty(userCode)){
			errors.rejectValue("userCode", "isNotNull",
					new Object[]{this.getCharacterValue(characterCoding, "会员编号") },
					this.getCharacterValue(characterCoding, "会员编号不能为空"));
			return false;
		} 
		JmiMember jmimember = jmiMemberDao.getJmiMember(new String(userCode));
		if(null == jmimember) {
			errors.rejectValue("userCode", "",
					new Object[]{this.getCharacterValue(characterCoding, "会员编号不存在") },
					this.getCharacterValue(characterCoding, "会员编号不存在"));
			return false;
		}
		
		//2、如果担保类型不是不退货担保，则需要判断担保内容、业务开始时间是否为空
		if(!"4".equals(assureType)) {
			String assureContent = assure.getAssureContent();
			/*if(!StringUtil.isEmpty(assureContent)){
				assureContent = assureContent.replaceAll(",", "").trim();
			}*/
			if(StringUtil.isEmpty(assureContent)){
				errors.rejectValue("assureContent", "isNotNull",
						new Object[]{this.getCharacterValue(characterCoding, "担保内容") },
						this.getCharacterValue(characterCoding, "担保内容不能为空"));
				return false;
			} 
			assure.setAssureContent(assureContent);
			
			//4、判断业务开始和结束时间
			Date startTime = assure.getStartTime();
			Date endTime = assure.getEndTime();
			if(null == startTime){
				errors.rejectValue("startTime", "isNotNull",
						new Object[]{this.getCharacterValue(characterCoding, "业务开始时间") },
						this.getCharacterValue(characterCoding, "业务开始时间不能为空"));
				return false;
			}
			if("2".equals(assureType)) {
				//5、业绩担保需要判断截止时间是否为空
				if(null == endTime){
					errors.rejectValue("endTime", "isNotNull",
							new Object[]{this.getCharacterValue(characterCoding, "业务截止时间") },
							this.getCharacterValue(characterCoding, "业务截止时间不能为空"));
					return false;
				}
				//这里需要判断是否达成担保，再根据结果设置isFlag的值，待完成
				//isFlag = jmiAssureManager.xxx();
				//jmiAssure.setIsFlag(isFlag);
			} else {
				// 1、和 3 担保类型的截止时间是允许为空的，会导致在导出报表的时候漏掉这条数据
				// 所以截止时间默认在开始时间的基础上加1年
				//DategetDateOffset(startTime,)
				if(null == endTime) {
					endTime = DateUtil.getDateOffset(startTime, Calendar.YEAR, 1);
					assure.setEndTime(endTime);
				}
			}
		} else {
			//判断担保订单编号
			String assureOrderCode = assure.getAssureOrderCode();
			if(StringUtil.isEmpty(assureOrderCode)){
				errors.rejectValue("assureOrderCode", "isNotNull",
						new Object[]{this.getCharacterValue(characterCoding, "担保订单编号") },
						this.getCharacterValue(characterCoding, "担保订单编号不能为空"));
				return false;
			}
			if(assureOrderCode.length() > 1000){
				errors.rejectValue("assureOrderCode", "",
						new Object[]{this.getCharacterValue(characterCoding, "担保订单编号过长") },
						this.getCharacterValue(characterCoding, "担保订单编号过长"));
				return false;
			}
			String []orderCodes = assureOrderCode.split(",");
			int orderFlag = 0;//用于记录多条订单中是否有不合法的订单
			w:for(String orderCode : orderCodes) {
				
				JpoMemberOrder order = getJpoMemberOrderByInterface(orderCode);
				if(null == order) {
					errors.rejectValue("assureOrderCode", "",
							new Object[]{this.getCharacterValue(characterCoding, "担保订单编号不存在或者订单状态不合法") },
							this.getCharacterValue(characterCoding, "担保订单编号不存在或者订单状态不合法"));
					orderFlag = 1;
					break w;
				}
			}
			if(orderFlag == 1) {
				return false;
			}
		}
		//业务开始时间和业务结束时间的判断
		if(assure.getStartTime() != null && assure.getEndTime() != null) {
			if (assure.getStartTime().getTime() > assure.getEndTime().getTime()) {
				errors.rejectValue("startTime", "",
						new Object[]{this.getCharacterValue(characterCoding, "业务开始时间不能大于业务截止时间") },
						this.getCharacterValue(characterCoding, "业务开始时间不能大于业务截止时间"));
				return false;
	        } 
		}
		//备注 长度判断
		String memo = assure.getMemo();
		if(!StringUtil.isEmpty(memo)){
			int length = MeteorUtil.length(memo);
			if(length > 200) {
				errors.rejectValue("memo", "",
						new Object[]{this.getCharacterValue(characterCoding, "备注长度超过限制") },
						this.getCharacterValue(characterCoding, "备注长度超过限制"));
				return false;
			}
		}
		return true;
	}
	
	
	private String getCharacterValue(String characterCoding,String msgKey){
		String characterValue=(String) Constants.localLanguageMap.get(characterCoding).get(msgKey);
		if(!StringUtils.isEmpty(characterValue)){
			return characterValue;
		}else{
			return msgKey;
		}
	}
	public static void main(String[] args) throws Exception {
		Date startTime = new Date();
		Thread.sleep(10000);
		Date endTime = new Date();
		System.out.println(startTime + "\r\n"+ endTime);
		if (startTime.getTime() > endTime.getTime()) {
            System.out.println("startTime 大于 endTime ");
        } else if (startTime.getTime() < endTime.getTime()) {
            System.out.println("startTime 小于 endTime");
        } else {//相等
        	System.out.println("相等");
        }
	}
}
