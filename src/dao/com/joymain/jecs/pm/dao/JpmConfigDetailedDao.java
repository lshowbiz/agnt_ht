package com.joymain.jecs.pm.dao;

import java.util.List;
import java.util.Set;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pm.model.JpmConfigDetailed;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmConfigDetailedDao extends Dao
{
    
    /**
     * Retrieves all of the jpmConfigDetaileds
     */
    public List getJpmConfigDetaileds(JpmConfigDetailed jpmConfigDetailed);
    
    /**
     * Gets jpmConfigDetailed's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
     * found.
     * 
     * @param configdetailedNo the jpmConfigDetailed's configdetailedNo
     * @return jpmConfigDetailed populated jpmConfigDetailed object
     */
    public JpmConfigDetailed getJpmConfigDetailed(final Long configdetailedNo);
    
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
    public void removeJpmConfigDetailed(final Long configdetailedNo);
    
    // added for getJpmConfigDetailedsByCrm
    public List getJpmConfigDetailedsByCrm(CommonRecord crm, Pager pager);
    
    public Integer addJpmConfigDetailed(JpmConfigDetailed jpmConfigDetailed);
    
    public Integer modifyJpmConfigDetailed(JpmConfigDetailed jpmConfigDetailed);
    
    public Integer delJpmConfigDetailed(Long detailedId);
    
    public List<JpmConfigDetailed> getJpmConfigDetailedBySpecNo(String specNo);
    
    public void saveJpmConfigDetailedList(Set<JpmConfigDetailed> jpmConfigDetailedList);
    
    public void delJpmConfigDetailedBySpecNo(Long specNo);
    
    /**
     * 根据规格id查询对应配件数量
     * 
     * @param specNo
     * @return
     */
    public JpmConfigDetailed getJpmConfigDetailedNumBySpecNo(String specNo);
}
