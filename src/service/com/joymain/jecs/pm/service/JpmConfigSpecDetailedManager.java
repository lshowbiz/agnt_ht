package com.joymain.jecs.pm.service;

import java.util.List;

import com.joymain.jecs.pm.model.JpmConfigSpecDetailed;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmConfigSpecDetailedManager extends Manager
{
    /**
     * Retrieves all of the jpmConfigSpecDetaileds
     */
    public List getJpmConfigSpecDetaileds(JpmConfigSpecDetailed jpmConfigSpecDetailed);
    
    /**
     * Gets jpmConfigSpecDetailed's information based on specNo.
     * 
     * @param specNo the jpmConfigSpecDetailed's specNo
     * @return jpmConfigSpecDetailed populated jpmConfigSpecDetailed object
     */
    public JpmConfigSpecDetailed getJpmConfigSpecDetailed(final String specNo);
    
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
    public void removeJpmConfigSpecDetailed(final String specNo);
    
    // added for getJpmConfigSpecDetailedsByCrm
    public List getJpmConfigSpecDetailedsByCrm(CommonRecord crm, Pager pager);
    
    public JpmConfigSpecDetailed getJpmConfigSpecDetailedBySpecNo(Long specNo);
    
    public Long getJpmConfigSpecDetailedWeightByConfigNo(Long configNo);
    
    public void delJpmConfigSpecDetailedBySpecNo(Long specNo);
    
    public List<JpmConfigSpecDetailed> getJpmConfigSpecDetailedListByConfigNo(Long configNo);
    
    public Long getPriceByConfigNo(String configNo);
}
