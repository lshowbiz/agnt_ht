
package com.joymain.jecs.pd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdWarehouseUser;
import com.joymain.jecs.pd.dao.PdWarehouseUserDao;
import com.joymain.jecs.pd.service.PdWarehouseUserManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class PdWarehouseUserManagerImpl extends BaseManager implements PdWarehouseUserManager {
    private PdWarehouseUserDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdWarehouseUserDao(PdWarehouseUserDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdWarehouseUserManager#getPdWarehouseUsers(com.joymain.jecs.pd.model.PdWarehouseUser)
     */
    public List getPdWarehouseUsers(final PdWarehouseUser pdWarehouseUser) {
        return dao.getPdWarehouseUsers(pdWarehouseUser);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdWarehouseUserManager#getPdWarehouseUser(String wuId)
     */
    public PdWarehouseUser getPdWarehouseUser(final String wuId) {
        return dao.getPdWarehouseUser(new Long(wuId));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdWarehouseUserManager#savePdWarehouseUser(PdWarehouseUser pdWarehouseUser)
     */
    public void savePdWarehouseUser(PdWarehouseUser pdWarehouseUser) {
        dao.savePdWarehouseUser(pdWarehouseUser);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdWarehouseUserManager#removePdWarehouseUser(String wuId)
     */
    public void removePdWarehouseUser(final String wuId) {
        dao.removePdWarehouseUser(new Long(wuId));
    }
    //added for getPdWarehouseUsersByCrm
    public List getPdWarehouseUsersByCrm(CommonRecord crm, Pager pager){
	return dao.getPdWarehouseUsersByCrm(crm,pager);
    }
		public List getPdWarehouseByUser(String userCode) {
			// TODO Auto-generated method stub
			return dao.getPdWarehouseByUser(userCode);
		}

		public String getStringWarehouseByUser(String userCode) {
			// TODO Auto-generated method stub
			List list = dao.getPdWarehouseByUser(userCode);
			StringBuffer sb = new StringBuffer("'-x#x-'");
			if(list !=null){
				for (int i=0;i<list.size();i++){
					sb.append(",'"+((PdWarehouseUser)list.get(i)).getWarehouseNo()+"'");
				}
			}
			
			return sb.toString();
		}
}
