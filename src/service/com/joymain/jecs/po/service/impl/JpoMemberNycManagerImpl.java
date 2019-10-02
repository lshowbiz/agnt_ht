
package com.joymain.jecs.po.service.impl;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.joymain.jecs.po.dao.JpoMemberNycLogDao;
import com.joymain.jecs.po.model.JpoMemberNycLog;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.po.model.JpoMemberNyc;
import com.joymain.jecs.po.dao.JpoMemberNycDao;
import com.joymain.jecs.po.service.JpoMemberNycManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.json.JSONObject;

public class JpoMemberNycManagerImpl extends BaseManager implements JpoMemberNycManager {
    private JpoMemberNycDao dao;
    private JpoMemberNycLogDao logDao;
    protected final transient Log log = LogFactory.getLog(getClass());

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpoMemberNycDao(JpoMemberNycDao dao) {
        this.dao = dao;
    }

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpoMemberNycLogDao(JpoMemberNycLogDao dao) {
        this.logDao = dao;
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberNycManager#getJpoMemberNycs(com.joymain.jecs.po.model.JpoMemberNyc)
     */
    public List getJpoMemberNycs(final JpoMemberNyc jpoMemberNyc) {
        return dao.getJpoMemberNycs(jpoMemberNyc);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberNycManager#getJpoMemberNyc(String id)
     */
    public JpoMemberNyc getJpoMemberNyc(final String id) {
        return dao.getJpoMemberNyc(Long.parseLong(id));
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberNycManager#saveJpoMemberNyc(JpoMemberNyc jpoMemberNyc)
     */
    public void saveJpoMemberNyc(JpoMemberNyc jpoMemberNyc,String userCode) {
        JpoMemberNyc old=null;
        if(jpoMemberNyc.getId()!=null){
            old=dao.getJpoMemberNyc(jpoMemberNyc.getId());
        }
        dao.saveJpoMemberNyc(jpoMemberNyc);

        JpoMemberNycLog log=new JpoMemberNycLog();

        if(old!=null){
            JSONObject oldJson = new JSONObject(old);
            log.setOldData(oldJson.toString());
        }

        JSONObject currentJson=new JSONObject(jpoMemberNyc);
        log.setNewData(currentJson.toString());
        log.setCreatAt(new Date());
        log.setTargetId(jpoMemberNyc.getId().toString());
        log.setId(UUID.randomUUID().toString());
        log.setCreator(userCode);
        logDao.saveJpoMemberNycLog(log);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberNycManager#removeJpoMemberNyc(String id)
     */
    public void removeJpoMemberNyc(final String id,String userCode) {
        Long idLong=Long.parseLong(id);
        JpoMemberNyc old=dao.getJpoMemberNyc(idLong);
        dao.removeJpoMemberNyc(idLong);
        JpoMemberNycLog log=new JpoMemberNycLog();

        if(old!=null){
            JSONObject oldJson = new JSONObject(old);
            log.setOldData(oldJson.toString());
        }


        log.setCreatAt(new Date());
        log.setTargetId(id);
        log.setId(UUID.randomUUID().toString());
        log.setCreator(userCode);
        logDao.saveJpoMemberNycLog(log);


    }
    //added for getJpoMemberNycsByCrm
    public List getJpoMemberNycsByCrm(CommonRecord crm, Pager pager){
	return dao.getJpoMemberNycsByCrm(crm,pager);
    }

    @Override
    public List getJpoMemberNycQualifiedsByCrm(CommonRecord crm, Pager pager) {
        return dao.getJpoMemberNycQualifiedsByCrm(crm,pager);
    }


    public void work(){
        log.info("execute   bacthUpdateNycStatus start");
        dao.bacthUpdateNycStatus();
        log.info("execute bacthUpdateNycStatus end");
    }
}
