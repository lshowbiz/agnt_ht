package com.joymain.jecs.pm.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.pm.model.JpmConfigDetailed;
import com.joymain.jecs.pm.dao.JpmConfigDetailedDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmConfigDetailedManager extends Manager
{
    /**
     * Retrieves all of the jpmConfigDetaileds
     */
    public List getJpmConfigDetaileds(JpmConfigDetailed jpmConfigDetailed);
    
    /**
     * Gets jpmConfigDetailed's information based on configdetailedNo.
     * 
     * @param configdetailedNo the jpmConfigDetailed's configdetailedNo
     * @return jpmConfigDetailed populated jpmConfigDetailed object
     */
    public JpmConfigDetailed getJpmConfigDetailed(final String configdetailedNo);
    
    /**
     * Saves a jpmConfigDetailed's information
     * 
     * @param jpmConfigDetailed the object to be saved
     */
    public void saveJpmConfigDetailed(JpmConfigDetailed jpmConfigDetailed);
    
    /**
     * Removes a jpmConfigDetailed from the database by configdetailedNo
     * 
     * @param configdetailedNo the jpmConfigDetailed's configdetailedNo
     */
    public void removeJpmConfigDetailed(final String configdetailedNo);
    
    // added for getJpmConfigDetailedsByCrm
    public List getJpmConfigDetailedsByCrm(CommonRecord crm, Pager pager);
    
    public void addJpmConfigDetailed(HttpServletRequest request,SysUser jsysUser);
    
    public Integer modifyJpmConfigDetailed(JpmConfigDetailed jpmConfigDetailed);
    
    public Integer delJpmConfigDetailed(Long detailedId);
    
    public List<JpmConfigDetailed> getJpmConfigDetailedBySpecNo(String specNo);
    
    /**
     * 根据规格id查询对应配件数量
     * 
     * @param specNo
     * @return
     */
    public JpmConfigDetailed getJpmConfigDetailedNumBySpecNo(String specNo);
}
