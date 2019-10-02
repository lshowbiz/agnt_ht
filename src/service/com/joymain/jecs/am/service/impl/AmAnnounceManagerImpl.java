
package com.joymain.jecs.am.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.am.dao.AmAnnounceDao;
import com.joymain.jecs.am.model.AmAnnounce;
import com.joymain.jecs.am.model.AmAnnounceRecord;
import com.joymain.jecs.am.service.AmAnnounceManager;
import com.joymain.jecs.am.service.AmAnnounceRecordManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

public class AmAnnounceManagerImpl extends BaseManager implements AmAnnounceManager {
	
	public int readNum =0;
	public int noreadNum =0;
	
    public void checkAmAnnounce(AmAnnounce amAnnounce) {
		// TODO Auto-generated method stub
    	dao.saveAmAnnounce(amAnnounce);
	}
	private AmAnnounceDao dao;
    AmAnnounceRecordManager amAnnounceRecordManager ;
    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setAmAnnounceDao(AmAnnounceDao dao) {
        this.dao = dao;
    }

    public void browserAmAnnounce(AmAnnounce amAnnounce,SysUser browser) {
		// TODO Auto-generated method stub
    	
    	if(!amAnnounceRecordManager.existAmAnnounceRecord(amAnnounce.getAaNo(), browser.getUserCode())){
    		AmAnnounceRecord amAnnounceRecord = new AmAnnounceRecord();
    		amAnnounceRecord.setAmAnnounce(amAnnounce);
    		amAnnounceRecord.setBrowser(browser);
    		amAnnounceRecord.setBrowseTime(new Date());
    		amAnnounceRecordManager.saveAmAnnounceRecord(amAnnounceRecord);
    	}
    	
	}

	/**
     * @see com.joymain.jecs.am.service.AmAnnounceManager#getAmAnnounces(com.joymain.jecs.am.model.AmAnnounce)
     */
    public List getAmAnnounces(final AmAnnounce amAnnounce) {
        return dao.getAmAnnounces(amAnnounce);
    }

    /**
     * @see com.joymain.jecs.am.service.AmAnnounceManager#getAmAnnounce(String aaNo)
     */
    public AmAnnounce getAmAnnounce(final String aaNo) {
        return dao.getAmAnnounce(new String(aaNo));
    }

    /**
     * @see com.joymain.jecs.am.service.AmAnnounceManager#saveAmAnnounce(AmAnnounce amAnnounce)
     */
    public void saveAmAnnounce(AmAnnounce amAnnounce) {
        dao.saveAmAnnounce(amAnnounce);
    }

    /**
     * @see com.joymain.jecs.am.service.AmAnnounceManager#removeAmAnnounce(String aaNo)
     */
    public void removeAmAnnounce(final String aaNo) {
        dao.removeAmAnnounce(new String(aaNo));
    }
    //added for getAmAnnouncesByCrm
		public List getAmAnnouncesByCrm(CommonRecord crm, Pager pager){
			String browserNo = crm.getString("browserNo","");
			List list = dao.getAmAnnouncesByCrm(crm,pager);
			if(StringUtils.isNotEmpty(browserNo)){
				return this.reBuildAmAnnounces(list, browserNo);
			}else{
				return list;
			}
			
				
		}

		private List reBuildAmAnnounces(List list,String browserNo){
			List listRet = new ArrayList();
			this.readNum=0;
			this.noreadNum=0;
			if(list != null){
				for(int i=0;i<list.size();i++){
					HashMap map = (HashMap) list.get(i);
					if(amAnnounceRecordManager.existAmAnnounceRecord((String) map.get("aano"), browserNo)){
						this.readNum++;
						map.put("readed", "1");
						map.put("style", "color:red;font:bold");
					}else{
						this.noreadNum++;
						map.put("readed", "0");
						map.put("style", "");
					}
					listRet.add(map);
					
				}
			}
			return listRet;
		}
		public void setAmAnnounceRecordManager(
				AmAnnounceRecordManager amAnnounceRecordManager) {
			this.amAnnounceRecordManager = amAnnounceRecordManager;
		}

		public int removeAnnounceBatch(String aanos) {
			return dao.removeAnnounceBatch(aanos);
		}

		public void checkAnnounceBatch(String aanos, String userCode,
				String userName) {
			dao.checkAnnounceBatch(aanos, userCode, userName);
			
		}

		public long getNoReadNum(CommonRecord crm, Pager pager) {
			// TODO Auto-generated method stub
			return dao.getAnnounceNoReadNum(crm, pager);
		}

		public long getReadNum(CommonRecord crm, Pager pager) {
			// TODO Auto-generated method stub
			return dao.getAnnounceReadNum(crm, pager);
		}

		public long getNoReadNum(JmiMember jmiMember) {
        	String memberPermitClass="3"+jmiMember.getCardType();
        	CommonRecord crm=new CommonRecord();
        	crm.addField("viewFlag", "view");
        	crm.addField("browserNo", jmiMember.getUserCode());
        	crm.addField("status", "1");
        	crm.addField("companyCode", jmiMember.getCompanyCode());
        	try {
				crm.setValue("permitClass", memberPermitClass);
	        	if("1".equals(jmiMember.getIsstore())){
	    			crm.setValue("permitClass", memberPermitClass+"','21");
	        	}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return this.getNoReadNum(crm, null);
		}

		public void reSetCreateTime(String aanos) {
			String aanoStr[]=aanos.split(",");
			for (int i = 0; i < aanoStr.length; i++) {
				if(!StringUtil.isEmpty(aanoStr[i])){
					AmAnnounce amAnnounce=this.getAmAnnounce(aanoStr[i]);
					amAnnounce.setCreateTime(new Date());
					this.saveAmAnnounce(amAnnounce);
				}
				
			}
		}

		public void setHighlight(String aanos,String highlight) {
			String aanoStr[]=aanos.split(",");
			for (int i = 0; i < aanoStr.length; i++) {
				if(!StringUtil.isEmpty(aanoStr[i])){
					AmAnnounce amAnnounce=this.getAmAnnounce(aanoStr[i]);
					amAnnounce.setHighlight(highlight);
					this.saveAmAnnounce(amAnnounce);
				}
				
			}
		}

		/**
		 * 下架公告
		 * @param aanos
		 */
		public void updateOutAmAnnounce(String aanos){
			StringBuffer sbAanos = new StringBuffer("");
			String aanoStr[]=aanos.split(",");
			for (int i = 0; i < aanoStr.length; i++) {
				if(!StringUtil.isEmpty(aanoStr[i])){
					sbAanos.append("'").append(aanoStr[i]).append("'");
					if(i!=aanoStr.length-1){
						sbAanos.append(",");
					}
				}
			}
			dao.updateOutAmAnnounce(sbAanos.toString());
		}
}
