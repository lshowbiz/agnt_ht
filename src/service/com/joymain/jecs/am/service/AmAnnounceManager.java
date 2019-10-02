
package com.joymain.jecs.am.service;

import java.util.List;

import com.joymain.jecs.am.model.AmAnnounce;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface AmAnnounceManager extends Manager {
    /**
     * Retrieves all of the amAnnounces
     */
    public List getAmAnnounces(AmAnnounce amAnnounce);

    /**
     * Gets amAnnounce's information based on aaNo.
     * @param aaNo the amAnnounce's aaNo
     * @return amAnnounce populated amAnnounce object
     */
    public AmAnnounce getAmAnnounce(final String aaNo);

    /**
     * Saves a amAnnounce's information
     * @param amAnnounce the object to be saved
     */
    public void saveAmAnnounce(AmAnnounce amAnnounce);

    /**
     * Removes a amAnnounce from the database by aaNo
     * @param aaNo the amAnnounce's aaNo
     */
    public void removeAmAnnounce(final String aaNo);
    //added for getAmAnnouncesByCrm
		public List getAmAnnouncesByCrm(CommonRecord crm, Pager pager);

		public void browserAmAnnounce(AmAnnounce amAnnounce,SysUser browser);

		public void checkAmAnnounce(AmAnnounce amAnnounce);
		
		public int removeAnnounceBatch(String aanos);
		public void checkAnnounceBatch(String aanos,String userCode,String userName);
		
		public long getReadNum(CommonRecord crm, Pager pager);
		public long getNoReadNum(CommonRecord crm, Pager pager);
		public long getNoReadNum(JmiMember jmiMember);
		
		public void reSetCreateTime(String aanos);
		
		public void setHighlight(String aanos,String highlight);
		public void updateOutAmAnnounce(String aanos);
}

