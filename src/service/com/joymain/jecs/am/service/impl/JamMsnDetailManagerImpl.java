
package com.joymain.jecs.am.service.impl;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.am.model.JamMsnDetail;
import com.joymain.jecs.am.dao.JamMsnDetailDao;
import com.joymain.jecs.am.service.JamMsnDetailManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JamMsnDetailManagerImpl extends BaseManager implements JamMsnDetailManager {
    private JamMsnDetailDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJamMsnDetailDao(JamMsnDetailDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.am.service.JamMsnDetailManager#getJamMsnDetails(com.joymain.jecs.am.model.JamMsnDetail)
     */
    public List getJamMsnDetails(final JamMsnDetail jamMsnDetail) {
        return dao.getJamMsnDetails(jamMsnDetail);
    }

    /**
     * @see com.joymain.jecs.am.service.JamMsnDetailManager#getJamMsnDetail(String mdId)
     */
    public JamMsnDetail getJamMsnDetail(final String mdId) {
        return dao.getJamMsnDetail(new Long(mdId));
    }

    /**
     * @see com.joymain.jecs.am.service.JamMsnDetailManager#saveJamMsnDetail(JamMsnDetail jamMsnDetail)
     */
    public void saveJamMsnDetail(JamMsnDetail jamMsnDetail) {
        dao.saveJamMsnDetail(jamMsnDetail);
    }

    /**
     * @see com.joymain.jecs.am.service.JamMsnDetailManager#removeJamMsnDetail(String mdId)
     */
    public void removeJamMsnDetail(final String mdId) {
        dao.removeJamMsnDetail(new Long(mdId));
    }
    //added for getJamMsnDetailsByCrm
    public List getJamMsnDetailsByCrm(CommonRecord crm, Pager pager){
	return dao.getJamMsnDetailsByCrm(crm,pager);
    }

	public List getJamMsnDetailsBySql(String userCode) {
		return dao.getJamMsnDetailsBySql(userCode);
	}

	public boolean getJamMsnDetailsBySql(String userCode, String msnKey) {
		List list=dao.getJamMsnDetailsBySql(userCode, msnKey);
		if(list.size()==0){
			return false;
		}else{
			Map map=(Map) list.get(0);
			String status=(String) map.get("status");
			if("1".equals(status)){
				return true;
			}else{
				return false;
			}
		}
	}

}
