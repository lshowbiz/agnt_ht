
package com.joymain.jecs.am.service.impl;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import com.joymain.jecs.mi.dao.JmiMemberDao;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.am.dao.JamMsnModuleDao;
import com.joymain.jecs.am.model.JamMsnModule;
import com.joymain.jecs.am.service.JamMsnModuleManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
public class JamMsnModuleManagerImpl extends BaseManager implements JamMsnModuleManager {
    private JamMsnModuleDao dao;
    private JmiMemberDao jmiMemberDao;
   

	public void setJmiMemberDao(JmiMemberDao jmiMemberDao) {
		this.jmiMemberDao = jmiMemberDao;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJamMsnModuleDao(JamMsnModuleDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.am.service.JamMsnModuleManager#getJamMsnModules(com.joymain.jecs.am.model.JamMsnModule)
     */
    public List getJamMsnModules(final JamMsnModule jamMsnModule) {
        return dao.getJamMsnModules(jamMsnModule);
    }

    /**
     * @see com.joymain.jecs.am.service.JamMsnModuleManager#getJamMsnModule(String jmmNo)
     */
    public JamMsnModule getJamMsnModule(final String jmmNo) {
        return dao.getJamMsnModule(new Long(jmmNo));
    }

    /**
     * @see com.joymain.jecs.am.service.JamMsnModuleManager#saveJamMsnModule(JamMsnModule jamMsnModule)
     */
    public void saveJamMsnModule(JamMsnModule jamMsnModule) {
        dao.saveJamMsnModule(jamMsnModule);
    }

    /**
     * @see com.joymain.jecs.am.service.JamMsnModuleManager#removeJamMsnModule(String jmmNo)
     */
    public void removeJamMsnModule(final String jmmNo) {
        dao.removeJamMsnModule(new Long(jmmNo));
    }
    //added for getJamMsnModulesByCrm
    public List getJamMsnModulesByCrm(CommonRecord crm, Pager pager){
	return dao.getJamMsnModulesByCrm(crm,pager);
    }

	public List getJamMsnModuleByMtId(Long mtId) {
		return dao.getJamMsnModuleByMtId(mtId);
	}

	public String getJamMsnModeulBySql(String userCode, String msnKey,Object[] objects) {
		JmiMember jmiMember=jmiMemberDao.getJmiMember(userCode);
		if(jmiMember==null){
			return null;
		}
		List list=dao.getJamMsnModeulBySql(userCode, msnKey,jmiMember.getCardType());
		if(list.size()==0){
			return null;
		}else{
			Map map=(Map) list.get(0);
			String status=(String) map.get("status");
			if("1".equals(status)){
				String content=null;
				if(StringUtil.isEmpty(map.get("content").toString())){
					content = ((Map)dao.getJamMsnModeulBySql(userCode, msnKey,"99").get(0)).get("content").toString();
				}else{
					content = map.get("content").toString();
				}
				return MessageFormat.format(content, objects);
			}else{
				return null;
			}
		}
	}
}
