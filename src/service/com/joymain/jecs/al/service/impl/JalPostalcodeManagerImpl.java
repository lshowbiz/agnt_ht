
package com.joymain.jecs.al.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.al.model.JalPostalcode;
import com.joymain.jecs.al.dao.JalPostalcodeDao;
import com.joymain.jecs.al.service.JalPostalcodeManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JalPostalcodeManagerImpl extends BaseManager implements JalPostalcodeManager {
    private JalPostalcodeDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJalPostalcodeDao(JalPostalcodeDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.al.service.JalPostalcodeManager#getJalPostalcodes(com.joymain.jecs.al.model.JalPostalcode)
     */
    public List getJalPostalcodes(final JalPostalcode jalPostalcode) {
        return dao.getJalPostalcodes(jalPostalcode);
    }

    /**
     * @see com.joymain.jecs.al.service.JalPostalcodeManager#getJalPostalcode(String postalcodeId)
     */
    public JalPostalcode getJalPostalcode(final String postalcodeId) {
        return dao.getJalPostalcode(new Long(postalcodeId));
    }

    /**
     * @see com.joymain.jecs.al.service.JalPostalcodeManager#saveJalPostalcode(JalPostalcode jalPostalcode)
     */
    public void saveJalPostalcode(JalPostalcode jalPostalcode) {
        dao.saveJalPostalcode(jalPostalcode);
    }

    /**
     * @see com.joymain.jecs.al.service.JalPostalcodeManager#removeJalPostalcode(String postalcodeId)
     */
    public void removeJalPostalcode(final String postalcodeId) {
        dao.removeJalPostalcode(new Long(postalcodeId));
    }
    //added for getJalPostalcodesByCrm
    public List getJalPostalcodesByCrm(CommonRecord crm, Pager pager){
	return dao.getJalPostalcodesByCrm(crm,pager);
    }

	public JalPostalcode getJalPostalcodeByCode(String zipCode) {
		return dao.getJalPostalcodeByCode(zipCode);
	}
}
