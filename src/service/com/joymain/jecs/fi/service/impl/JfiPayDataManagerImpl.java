
package com.joymain.jecs.fi.service.impl;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiPayData;
import com.joymain.jecs.fi.dao.JfiPayDataDao;
import com.joymain.jecs.fi.service.JfiPayDataManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.fi.model.JfiPayAdvice;
import com.joymain.jecs.fi.model.JfiPayData;
import com.joymain.jecs.fi.dao.JfiPayAdviceDao;
import com.joymain.jecs.util.exception.AppException;
public class JfiPayDataManagerImpl extends BaseManager implements JfiPayDataManager {
    private JfiPayDataDao dao;
	private JfiPayAdviceDao jfiPayAdviceDao;

	public void setJfiPayAdviceDao(JfiPayAdviceDao jfiPayAdviceDao) {
		this.jfiPayAdviceDao = jfiPayAdviceDao;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiPayDataDao(JfiPayDataDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiPayDataManager#getJfiPayDatas(com.joymain.jecs.fi.model.JfiPayData)
     */
    public List getJfiPayDatas(final JfiPayData jfiPayData) {
        return dao.getJfiPayDatas(jfiPayData);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiPayDataManager#getJfiPayData(String dataId)
     */
    public JfiPayData getJfiPayData(final String dataId) {
        return dao.getJfiPayData(new Long(dataId));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiPayDataManager#saveJfiPayData(JfiPayData jfiPayData)
     */
    public void saveJfiPayData(JfiPayData jfiPayData) {
        dao.saveJfiPayData(jfiPayData);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiPayDataManager#removeJfiPayData(String dataId)
     */
    public void removeJfiPayData(final String dataId) {
        dao.removeJfiPayData(new Long(dataId));
    }
    //added for getJfiPayDatasByCrm
    public List getJfiPayDatasByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiPayDatasByCrm(crm,pager);
    }

	/**
	 * 批量保存多条记录
	 * @param fiPayDatas
	 */
	public void saveJfiPayDatas(List jfiPayDatas){
		dao.saveJfiPayDatas(jfiPayDatas);
	}
    

	/**
	 * 核实银行到款数据
	 * @param dataId
	 * @param userCode
	 * @param adviceCode
	 * @param operaterCode
	 * @param operaterName
	 * @param noticeMsgPattern
	 */
	public void saveJfiPayDataVerify(final String dataId, final String userCode, String adviceCode, final String operaterCode,
	        final String operaterName, final String noticeMsgPattern) throws Exception {

		//更改付款通知为核实
		try {
			MessageFormat formatter = new MessageFormat(noticeMsgPattern);

			JfiPayData jfiPayData = dao.getJfiPayData(new Long(dataId));
			JfiPayAdvice jfiPayAdvice = this.jfiPayAdviceDao.getJfiPayAdvice(adviceCode);

			String excerpt = "";
			if (!StringUtils.isEmpty(jfiPayData.getExcerpt())) {
				excerpt = jfiPayData.getExcerpt();
			}

			String notice = formatter.format(new Object[] { jfiPayAdvice.getAccountCode(), jfiPayData.getDealDate(),
			        jfiPayAdvice.getAdviceCode(), jfiPayData.getDataId(), excerpt });
			//Map resultMap = dao.saveFiPayDataVerifyProc(dataId, adviceCode, operaterCode, operaterName, notice);
			Map resultMap = dao.saveJfiPayDataVerifyProc(dataId, adviceCode, operaterCode, operaterName, notice);
		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * 取消核实银行到款数据
	 * @param fiPayData
	 * @param userCode
	 * @param operaterCode
	 * @param operaterName
	 * @param noticeMsgPattern
	 */
	public void saveJfiPayDataUnVerify(final String dataId, final String userCode, final String operaterCode,
	        final String operaterName, final String unVerifyRemark, final String noticeMsgPattern) throws AppException {
		try {
			//重新获取
			JfiPayData jfiPayData = dao.getJfiPayData(new Long(dataId));
			JfiPayAdvice jfiPayAdvice = this.jfiPayAdviceDao.getJfiPayAdvice(jfiPayData.getAdviceCode());
			MessageFormat formatter = new MessageFormat(noticeMsgPattern);
			String excerpt = "";
			if (!StringUtils.isEmpty(jfiPayData.getExcerpt())) {
				excerpt = jfiPayData.getExcerpt();
			}
			Map resultMap = dao.saveJfiPayDataUnverifyProc(dataId, operaterCode, operaterName, unVerifyRemark, formatter
			        .format(new String[] { jfiPayAdvice.getAccountCode(), jfiPayData.getDealDate().toString(), jfiPayAdvice.getAdviceCode(),
			                jfiPayData.getDataId().toString(), excerpt }));
			if ("1".equals(resultMap.get("Vi_Errno").toString())) {
				//余额不足
				throw new AppException("存折或到款数据不存在");
			} else if ("2".equals(resultMap.get("Vi_Errno").toString())) {
				throw new AppException("error.fiPayData.unVerified");
			} else if ("3".equals(resultMap.get("Vi_Errno").toString())) {
				throw new AppException("error.fiBankbookJournal.balance.not.enough");
			} else if ("4".equals(resultMap.get("Vi_Errno").toString())) {
				throw new AppException("未知错误");
			}
		} catch (AppException ex) {
			throw ex;
		}
	}

	/**
	 * 根据外部条件获取对应的到款数据统计
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getJfiPayDataStatsByCrm(CommonRecord crm, Pager pager) {
		return dao.getJfiPayDataStatsByCrm(crm, pager);
	}

	/**
	 * 要看外部条件获取对应的到款数总额
	 * @param crm
	 * @return
	 */
	public HashMap getJfiPayDataSum(CommonRecord crm) {
		return dao.getJfiPayDataSum(crm);
	}

	/**
	 * 获取各银行(未)到款对应的统计
	 * @param companyCode
	 * @param startDealDate
	 * @param endDealDate
	 * @return
	 */
	public List getJfiPayDataGroupTotal(final String companyCode, final String startDealDate, final String endDealDate) {
		return dao.getJfiPayDataGroupTotal(companyCode, startDealDate, endDealDate);
	}
}
