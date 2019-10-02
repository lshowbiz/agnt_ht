package com.joymain.jecs.pm.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pm.model.JpmConfigSpecDetailed;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmConfigSpecDetailedDao extends Dao
{
    
    /**
     * Retrieves all of the jpmConfigSpecDetaileds
     */
    public List getJpmConfigSpecDetaileds(JpmConfigSpecDetailed jpmConfigSpecDetailed);
    
    /**
     * Gets jpmConfigSpecDetailed's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
     * found.
     * 
     * @param specNo the jpmConfigSpecDetailed's specNo
     * @return jpmConfigSpecDetailed populated jpmConfigSpecDetailed object
     */
    public JpmConfigSpecDetailed getJpmConfigSpecDetailed(final Long specNo);
    
    /**
     * Saves a jpmConfigSpecDetailed's information
     * 
     * @param jpmConfigSpecDetailed the object to be saved
     */
    public void saveJpmConfigSpecDetailed(JpmConfigSpecDetailed jpmConfigSpecDetailed);
    
    /**
     * Removes a jpmConfigSpecDetailed from the database by specNo
     * 
     * @param specNo the jpmConfigSpecDetailed's specNo
     */
    public void removeJpmConfigSpecDetailed(final Long specNo);
    
    // added for getJpmConfigSpecDetailedsByCrm
    public List getJpmConfigSpecDetailedsByCrm(CommonRecord crm, Pager pager);
    
    public void insertJpmConfigSpecDetailed(JpmConfigSpecDetailed jpmConfigSpecDetailed);
    
    public void modifyJpmConfigSpecDetailed(JpmConfigSpecDetailed jpmConfigSpecDetailed);
    
    public JpmConfigSpecDetailed getJpmConfigSpecDetailedBySpecNo(Long specNo);
    
    public Long getJpmConfigSpecDetailedWeightByConfigNo(Long configNo);
    
    public void delJpmConfigSpecDetailedBySpecNo(Long specNo);
    
    public List<JpmConfigSpecDetailed> getJpmConfigSpecDetailedListByConfigNo(Long configNo);
    
    public Long getPriceByConfigNo(String configNo);
}
