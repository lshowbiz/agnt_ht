
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiBankbookTemp;
import com.joymain.jecs.fi.dao.JfiBankbookTempDao;
import com.joymain.jecs.fi.service.JfiBankbookTempManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiBankbookTempManagerImpl extends BaseManager implements JfiBankbookTempManager {
    private JfiBankbookTempDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiBankbookTempDao(JfiBankbookTempDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiBankbookTempManager#getJfiBankbookTemps(com.joymain.jecs.fi.model.JfiBankbookTemp)
     */
    public List getJfiBankbookTemps(final JfiBankbookTemp jfiBankbookTemp) {
        return dao.getJfiBankbookTemps(jfiBankbookTemp);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiBankbookTempManager#getJfiBankbookTemp(String tempId)
     */
    public JfiBankbookTemp getJfiBankbookTemp(final String tempId) {
        return dao.getJfiBankbookTemp(new Long(tempId));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiBankbookTempManager#saveJfiBankbookTemp(JfiBankbookTemp jfiBankbookTemp)
     */
    public void saveJfiBankbookTemp(JfiBankbookTemp jfiBankbookTemp) {
        dao.saveJfiBankbookTemp(jfiBankbookTemp);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiBankbookTempManager#removeJfiBankbookTemp(String tempId)
     */
    public void removeJfiBankbookTemp(final String tempId) {
        dao.removeJfiBankbookTemp(new Long(tempId));
    }
    //added for getJfiBankbookTempsByCrm
    public List getJfiBankbookTempsByCrm(CommonRecord crm, Pager pager){
    	return dao.getJfiBankbookTempsByCrm(crm,pager);
    }
    
	/**
	 * 获取收支统计金额 incTotal:收入  expTotal:支出
	 * @param crm
	 * @return
	 */
	public Map getIncExpStatMap(CommonRecord crm){
		return dao.getIncExpStatMap(crm);
	}

	/**
	 * 获取某用户的存折条数
	 * @param companyCode
	 * @param agentNo
	 * @return
	 */
	public long getCountByDate(final String companyCode, final String userCode){
		return dao.getCountByDate(companyCode, userCode);
	}

	/**
	 * 批量保存多条记录
	 * @param fiBankbookTemps
	 */
	public void saveJfiBankbookTemps(List jfiBankbookTemps) {
		dao.saveJfiBankbookTemps(jfiBankbookTemps);
	}

	public List checkPosNum(String posNum) {
		// TODO Auto-generated method stub
		return dao.checkPosNum(posNum);
	}

	public List checkAllPosNumFromJfiBankBookTemp() {
		// TODO Auto-generated method stub
		return dao.checkAllPosNumFromJfiBankBookTemp();
	}

	public List checkAllPosNumFromJfiPosImport() {
		// TODO Auto-generated method stub
		return dao.checkAllPosNumFromJfiPosImport();
	}
}
