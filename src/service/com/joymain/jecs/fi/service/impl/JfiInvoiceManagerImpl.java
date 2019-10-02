
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiInvoice;
import com.joymain.jecs.fi.dao.JfiInvoiceDao;
import com.joymain.jecs.fi.service.JfiInvoiceManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiInvoiceManagerImpl extends BaseManager implements JfiInvoiceManager {
    private JfiInvoiceDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiInvoiceDao(JfiInvoiceDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiInvoiceManager#getJfiInvoices(com.joymain.jecs.fi.model.JfiInvoice)
     */
    public List getJfiInvoices(final JfiInvoice jfiInvoice) {
        return dao.getJfiInvoices(jfiInvoice);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiInvoiceManager#getJfiInvoice(String id)
     */
    public JfiInvoice getJfiInvoice(final String id) {
        return dao.getJfiInvoice(new Long(id));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiInvoiceManager#saveJfiInvoice(JfiInvoice jfiInvoice)
     */
    public void saveJfiInvoice(JfiInvoice jfiInvoice) {
        dao.saveJfiInvoice(jfiInvoice);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiInvoiceManager#removeJfiInvoice(String id)
     */
    public void removeJfiInvoice(final String id) {
        dao.removeJfiInvoice(new Long(id));
    }
    //added for getJfiInvoicesByCrm
    public List getJfiInvoicesByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiInvoicesByCrm(crm,pager);
    }
    
    public List getJfiInvoiceExportByCrm(CommonRecord crm){
    	return dao.getJfiInvoiceExportByCrm(crm);
    }
    
    public List getJfiDepositExportByCrm(CommonRecord crm){
    	return dao.getJfiDepositExportByCrm(crm);
    }
    
    public List getJfiDepositInvoiceExportByCrm(CommonRecord crm){
    	return dao.getJfiDepositInvoiceExportByCrm(crm);
    }
    
}
