package com.joymain.jecs.fi.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.dao.JfiChinapayPosLogDao;
import com.joymain.jecs.fi.model.JfiChinapayPosLog;
import com.joymain.jecs.fi.model.JfiPosImport;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.fi.service.JfiChinapayPosLogManager;
import com.joymain.jecs.fi.service.JfiPosImportManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;

public class JfiChinapayPosLogManagerImpl extends BaseManager implements
		JfiChinapayPosLogManager {
	private final Log log = LogFactory
			.getLog(JfiChinapayPosLogManagerImpl.class);

	private JfiChinapayPosLogDao dao;

	private JfiBankbookJournalManager jfiBankbookJournalManager;

	private SysUserManager sysUserManager;

	private JfiPosImportManager jfiPosImportManager;

	public void setJfiPosImportManager(JfiPosImportManager jfiPosImportManager) {
		this.jfiPosImportManager = jfiPosImportManager;
	}

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setJfiChinapayPosLogDao(JfiChinapayPosLogDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.fi.service.JfiChinapayPosLogManager#getJfiChinapayPosLogs(com.joymain.jecs.fi.model.JfiChinapayPosLog)
	 */
	public List getJfiChinapayPosLogs(final JfiChinapayPosLog jfiChinapayPosLog) {
		return dao.getJfiChinapayPosLogs(jfiChinapayPosLog);
	}

	/**
	 * @see com.joymain.jecs.fi.service.JfiChinapayPosLogManager#getJfiChinapayPosLog(String
	 *      logId)
	 */
	public JfiChinapayPosLog getJfiChinapayPosLog(final String logId) {
		return dao.getJfiChinapayPosLog(new Long(logId));
	}

	/**
	 * @see com.joymain.jecs.fi.service.JfiChinapayPosLogManager#saveJfiChinapayPosLog(JfiChinapayPosLog
	 *      jfiChinapayPosLog)
	 */
	public void saveJfiChinapayPosLog(JfiChinapayPosLog jfiChinapayPosLog) {
		dao.saveJfiChinapayPosLog(jfiChinapayPosLog);
	}

	/**
	 * @see com.joymain.jecs.fi.service.JfiChinapayPosLogManager#removeJfiChinapayPosLog(String
	 *      logId)
	 */
	public void removeJfiChinapayPosLog(final String logId) {
		dao.removeJfiChinapayPosLog(new Long(logId));
	}

	// added for getJfiChinapayPosLogsByCrm
	public List getJfiChinapayPosLogsByCrm(CommonRecord crm, Pager pager) {
		return dao.getJfiChinapayPosLogsByCrm(crm, pager);
	}

	@Override
	public void saveJfiPayData(JfiChinapayPosLog jfiChinapayPosLog)
			throws Exception {
		log.info("JfiChinapayPosLogManager method saveJfiPayData");

		if (Constants.POS_TYPE_MA.equals(jfiChinapayPosLog.getMsgCode())) {
			// 新增pos支付日志
			saveJfiChinapayPosLog(jfiChinapayPosLog);
			// 参考号 金额 流水号 码证码
			String posNo = jfiChinapayPosLog.getPayDealCode();
			if (!StringUtils.isEmpty(posNo)) {
				posNo = StringUtils.trim(posNo);
			}
			String amount = String.valueOf(jfiChinapayPosLog.getPayAmount());// 交易金额
			if (!StringUtils.isEmpty(amount)) {
				amount = StringUtils.trim(amount);
			}
			String pid = jfiChinapayPosLog.getMobileNumber().split("\\|")[0];// 序列号
			if (!StringUtils.isEmpty(pid)) {
				pid = StringUtils.trim(pid);
			}
			String tel = jfiChinapayPosLog.getMobileNumber().split("\\|")[1];
			if (!StringUtils.isEmpty(tel)) {
				tel = StringUtils.trim(tel);
			}
			JfiPosImport jfiPosImport = new JfiPosImport();
			jfiPosImport.setPid(pid);
			jfiPosImport.setTel(tel);
			jfiPosImport.setAmount(new BigDecimal(amount));
			jfiPosImport.setPosNo(posNo);
			// 此处增加撤回操作的流程
			if(Constants.POS_BACK.equals(jfiChinapayPosLog.getIsBack()))
			{
				List<JfiPosImport> jfiPosImportList = jfiPosImportManager.getJfiPosImports(jfiPosImport);
				if(null != jfiPosImportList && jfiPosImportList.size() > 0)
				{
					JfiPosImport jfiPosImportBack = jfiPosImportList.get(0);
					if(("1".equals(jfiPosImportBack.getStatus()) || "2".equals(jfiPosImportBack.getStatus())) && "0".equals(jfiPosImportBack.getInc()))
					{
						//4表示pos机入账记录被撤回，不可以认领操作
						jfiPosImportBack.setStatus("4");
						jfiPosImportManager.saveJfiPosImport(jfiPosImportBack);
						return;
					}
					else
					{
						log.info("此单已被认领，不允许撤回操作");
						throw new AppException("此单已被认领，不允许撤回操作");
					}
				}
			}
			
			
			if ("0".equals(posNo)) {
				log.info("参考号不能为0");
				throw new AppException("参考号不能为0");
			}
			JfiPosImport jfiPosImportTmp = new JfiPosImport();
			jfiPosImportTmp.setPosNo(posNo);
			List jfiPosImportListTmp = jfiPosImportManager.getJfiPosImports(jfiPosImportTmp);
			if (jfiPosImportListTmp != null && jfiPosImportListTmp.size() != 0) {
				log.info("已存在相同的参考号");
				throw new AppException("已存在相同的参考号");
				// saveMessage(request, getText("已存在相同的参考号")+i);
			}

			jfiPosImport.setCreateTime(new Date());
			jfiPosImport.setCreateUser(jfiChinapayPosLog.getTerminalNo());
			jfiPosImport.setInc("0");
			jfiPosImport.setStatus("1");
			// 0为畅捷pos机录入
			if ("0".equals(jfiChinapayPosLog.getCompany())) {
				jfiPosImport.setMoneyType(Integer
						.parseInt(Constants.CHANGJIE_POS_TYPE));// 畅捷POS
			} else {
				jfiPosImport.setMoneyType(35);// 银联POS
			}

			jfiPosImportManager.saveJfiPosImport(jfiPosImport);
			log.info("JfiChinapayPosLogManager method saveSeccess");
			return;
		}
		SysUser sysUser = new SysUser();
		if (StringUtils.isNotEmpty(jfiChinapayPosLog.getUserCode())) {
			log.info("JfiChinapayPosLogManager method usercode is: "
					+ jfiChinapayPosLog.getUserCode());
			// 获取用户数据
			sysUser = sysUserManager
					.getSysUser(jfiChinapayPosLog.getUserCode());
			if (null != sysUser) {
				log
						.info("JfiChinapayPosLogManager method sysUser is not empty");
			}

			if (Constants.POS_TYPE_USER.equals(jfiChinapayPosLog.getMsgCode())) {
				// 新增pos支付日志
				saveJfiChinapayPosLog(jfiChinapayPosLog);
				// 获取总金额
				BigDecimal totalMoney = jfiChinapayPosLog.getPayAmount();
				log.info("JfiChinapayPosLogManager method totalMoney is:"
						+ totalMoney);
				BigDecimal[] moneyArray = new BigDecimal[1];
				moneyArray[0] = totalMoney;
				// moneyArray[1] = totalMoney;

				Integer[] moneyType = new Integer[1];
				Integer[] moneyTypeDeduct = new Integer[1];
				if ("0".equals(jfiChinapayPosLog.getCompany())) {
					moneyType[0] = Integer
							.parseInt(Constants.CHANGJIE_POS_TYPE);// 畅捷POS
					moneyTypeDeduct[0] = Integer
							.parseInt(Constants.CHANGJIE_POS_TYPE);// 畅捷POS
				} else {
					moneyType[0] = 35;// 银联POS
					moneyTypeDeduct[0] = 35;// 银联POS
				}
				// moneyType[1] = 36;

				String[] notes = new String[1];
				notes[0] = "pos支付";
				// notes[1] = "pos手续费";
				// pos数据进存折
				jfiBankbookJournalManager.saveJfiPayDataVerify("CN", sysUser,
						moneyType, moneyArray, sysUser.getUserCode(), sysUser
								.getUserName(), jfiChinapayPosLog
								.getPayDealCode(), notes, "1");

//				BigDecimal[] moneyArrayDeduct = { jfiChinapayPosLog
//						.getPayAmount() };
//
//				String[] notesDeduct = { "pos支付" };

				// 从用户帐户扣取金额
//				jfiBankbookJournalManager.saveJfiBankbookDeduct("CN", sysUser,
//						moneyTypeDeduct, moneyArrayDeduct, sysUser
//								.getUserCode(), sysUser.getUserName(), "0",
//						notesDeduct, "1");
			}

		}
	}
}
