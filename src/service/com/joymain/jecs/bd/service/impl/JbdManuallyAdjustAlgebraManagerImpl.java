
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdManuallyAdjustAlgebra;
import com.joymain.jecs.bd.dao.JbdManuallyAdjustAlgebraDao;
import com.joymain.jecs.bd.service.JbdManuallyAdjustAlgebraManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdManuallyAdjustAlgebraManagerImpl extends BaseManager implements JbdManuallyAdjustAlgebraManager {
    private JbdManuallyAdjustAlgebraDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdManuallyAdjustAlgebraDao(JbdManuallyAdjustAlgebraDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdManuallyAdjustAlgebraManager#getJbdManuallyAdjustAlgebras(com.joymain.jecs.bd.model.JbdManuallyAdjustAlgebra)
     */
    public List getJbdManuallyAdjustAlgebras(final JbdManuallyAdjustAlgebra jbdManuallyAdjustAlgebra) {
        return dao.getJbdManuallyAdjustAlgebras(jbdManuallyAdjustAlgebra);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdManuallyAdjustAlgebraManager#getJbdManuallyAdjustAlgebra(String id)
     */
    public JbdManuallyAdjustAlgebra getJbdManuallyAdjustAlgebra(final String id) {
        return dao.getJbdManuallyAdjustAlgebra(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdManuallyAdjustAlgebraManager#saveJbdManuallyAdjustAlgebra(JbdManuallyAdjustAlgebra jbdManuallyAdjustAlgebra)
     */
    public void saveJbdManuallyAdjustAlgebra(JbdManuallyAdjustAlgebra jbdManuallyAdjustAlgebra) {
        dao.saveJbdManuallyAdjustAlgebra(jbdManuallyAdjustAlgebra);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdManuallyAdjustAlgebraManager#removeJbdManuallyAdjustAlgebra(String id)
     */
    public void removeJbdManuallyAdjustAlgebra(final String id) {
        dao.removeJbdManuallyAdjustAlgebra(new Long(id));
    }
    //added for getJbdManuallyAdjustAlgebrasByCrm
    public List getJbdManuallyAdjustAlgebrasByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdManuallyAdjustAlgebrasByCrm(crm,pager);
    }

	public JbdManuallyAdjustAlgebra getJbdManuallyAdjustAlgebraByUserCodeAndWeek(String userCode, String wweek) {
		return dao.getJbdManuallyAdjustAlgebraByUserCodeAndWeek(userCode, wweek);
	}
}
