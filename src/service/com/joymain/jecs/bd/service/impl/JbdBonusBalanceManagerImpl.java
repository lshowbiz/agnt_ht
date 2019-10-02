
package com.joymain.jecs.bd.service.impl;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.mi.dao.JmiMemberDao;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.bd.model.BdBounsDeduct;
import com.joymain.jecs.bd.model.JbdBonusBalance;
import com.joymain.jecs.bd.dao.JbdBonusBalanceDao;
import com.joymain.jecs.bd.service.BdBounsDeductManager;
import com.joymain.jecs.bd.service.JbdBonusBalanceManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
public class JbdBonusBalanceManagerImpl extends BaseManager implements JbdBonusBalanceManager {
    private JbdBonusBalanceDao dao;
    private BdBounsDeductManager bdBounsDeductManager;
    private JmiMemberDao jmiMemberDao;

	public void setJmiMemberDao(JmiMemberDao jmiMemberDao) {
		this.jmiMemberDao = jmiMemberDao;
	}

	public void setBdBounsDeductManager(BdBounsDeductManager bdBounsDeductManager) {
		this.bdBounsDeductManager = bdBounsDeductManager;
	}
	

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdBonusBalanceDao(JbdBonusBalanceDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdBonusBalanceManager#getJbdBonusBalances(com.joymain.jecs.bd.model.JbdBonusBalance)
     */
    public List getJbdBonusBalances(final JbdBonusBalance jbdBonusBalance) {
        return dao.getJbdBonusBalances(jbdBonusBalance);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdBonusBalanceManager#getJbdBonusBalance(String userCode)
     */
    public JbdBonusBalance getJbdBonusBalance(final String userCode) {
        return dao.getJbdBonusBalance(new String(userCode));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdBonusBalanceManager#saveJbdBonusBalance(JbdBonusBalance jbdBonusBalance)
     */
    public void saveJbdBonusBalance(JbdBonusBalance jbdBonusBalance) {
        dao.saveJbdBonusBalance(jbdBonusBalance);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdBonusBalanceManager#removeJbdBonusBalance(String userCode)
     */
    public void removeJbdBonusBalance(final String userCode) {
        dao.removeJbdBonusBalance(new String(userCode));
    }
    //added for getJbdBonusBalancesByCrm
    public List getJbdBonusBalancesByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdBonusBalancesByCrm(crm,pager);
    }

	public void saveApplication(String userCode) {
		JbdBonusBalance jbdBonusBalance=this.getJbdBonusBalance(userCode);
		if(jbdBonusBalance.getBonusBalance().compareTo(new BigDecimal(3))==1&&jbdBonusBalance.getBonusBalance().compareTo(new BigDecimal(500))==-1&&"0".equals(jbdBonusBalance.getFlag())){

				try {
					
					
					jbdBonusBalance.setFlagTime(new Date());
		        	jbdBonusBalance.setFlag("1");
		        	this.saveJbdBonusBalance(jbdBonusBalance);
				} catch (Exception e) {
					throw new AppException("Application.updateFail");
				}
		}else{
			throw new AppException("Application.updateFail");
		}
	}

	public void checkJmiMember(String strReissueCodes[], SysUser defSysUser) {
		Date curDate=new Date();
		for (int i = 0; i < strReissueCodes.length; i++) {
			if (!StringUtils.isEmpty(strReissueCodes[i])) {
				JbdBonusBalance jbdBonusBalance=this.getJbdBonusBalance(strReissueCodes[i]);
				if(jbdBonusBalance==null){
					throw new AppException("memberNoFind");
				}
				if("0".equals(jbdBonusBalance.getStatus())){
					throw new AppException("memberChecked");
				}
				jbdBonusBalance.setStatus("0");
				jbdBonusBalance.setCheckUser(defSysUser.getUserCode());
				jbdBonusBalance.setCheckTime(curDate);
				this.saveJbdBonusBalance(jbdBonusBalance);
			}
		}
	}
}
