
package com.joymain.jecs.pm.service.impl;

import java.util.List;
import com.joymain.jecs.pm.dao.JpmSalepromoterProDao;
import com.joymain.jecs.pm.model.JpmSalepromoterPro;
import com.joymain.jecs.pm.service.JpmSalepromoterProManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpmSalepromoterProManagerImpl extends BaseManager implements JpmSalepromoterProManager {
    private JpmSalepromoterProDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpmSalepromoterProDao(JpmSalepromoterProDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmSalepromoterProManager#getJpmSalepromoterPros(com.joymain.jecs.pm.model.JpmSalepromoterPro)
     */
    public List getJpmSalepromoterPros(final JpmSalepromoterPro jpmSalepromoterPro) {
        return dao.getJpmSalepromoterPros(jpmSalepromoterPro);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmSalepromoterProManager#getJpmSalepromoterPro(String id)
     */
    public JpmSalepromoterPro getJpmSalepromoterPro(final String id) {
        return dao.getJpmSalepromoterPro(new Long(id));
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmSalepromoterProManager#saveJpmSalepromoterPro(JpmSalepromoterPro jpmSalepromoterPro)
     */
    public void saveJpmSalepromoterPro(JpmSalepromoterPro jpmSalepromoterPro) {
        dao.saveJpmSalepromoterPro(jpmSalepromoterPro);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmSalepromoterProManager#removeJpmSalepromoterPro(String id)
     */
    public void removeJpmSalepromoterPro(final String id) {
        dao.removeJpmSalepromoterPro(new Long(id));
    }
    //added for getJpmSalepromoterProsByCrm
    public List getJpmSalepromoterProsByCrm(CommonRecord crm, Pager pager){
	return dao.getJpmSalepromoterProsByCrm(crm,pager);
    }

	public JpmSalepromoterPro getJpmSaleProByproductId(String spno, String proNo) {
		
		return dao.getJpmSaleProByproductId(Long.parseLong(spno), proNo);
	}

	public List<JpmSalepromoterPro> getJpmSaleProBySpno(String spno) {
		
		return dao.getJpmSaleProBySpno(spno);
	}

	public void delJpmSalepromoterProBySpno(String spno) {
		dao.delJpmSalepromoterProBySpno(spno);
	}

	public void removeJpmSalepromoterPro(JpmSalepromoterPro jsp) {
		dao.removeObject(JpmSalepromoterPro.class, jsp.getId());
	}

}
