
package com.joymain.jecs.bd.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.bd.dao.JbdMemberLinkCalcHistDao;
import com.joymain.jecs.bd.dao.JbdSellCalcSubHistDao;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.model.JbdGradeNote;
import com.joymain.jecs.bd.model.JbdMemberLinkCalcHist;
import com.joymain.jecs.bd.model.JbdSellCalcSubHist;
import com.joymain.jecs.bd.model.JbdTravelPoint2012;
import com.joymain.jecs.bd.model.JbdTravelPoint2013;
import com.joymain.jecs.bd.model.VJbdMemberLinkCalc;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.bd.service.JbdGradeNoteManager;
import com.joymain.jecs.bd.service.JbdMemberLinkCalcHistManager;
import com.joymain.jecs.bd.service.JbdTravelPoint2012Manager;
import com.joymain.jecs.bd.service.JbdTravelPoint2013Manager;
import com.joymain.jecs.mi.dao.JmiMemberDao;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
public class JbdMemberLinkCalcHistManagerImpl extends BaseManager implements JbdMemberLinkCalcHistManager {
    private JbdMemberLinkCalcHistDao dao;
    private JbdSellCalcSubHistDao jbdSellCalcSubHistDao;
    private JmiMemberDao jmiMemberDao;
    private BdPeriodManager bdPeriodManager;
    private JbdGradeNoteManager jbdGradeNoteManager;
    private JbdTravelPoint2012Manager jbdTravelPoint2012Manager;
    private JbdTravelPoint2013Manager jbdTravelPoint2013Manager;
    public void setJbdTravelPoint2012Manager(
			JbdTravelPoint2012Manager jbdTravelPoint2012Manager) {
		this.jbdTravelPoint2012Manager = jbdTravelPoint2012Manager;
	}

	public void setJbdGradeNoteManager(JbdGradeNoteManager jbdGradeNoteManager) {
		this.jbdGradeNoteManager = jbdGradeNoteManager;
	}

	public void setJbdSellCalcSubHistDao(JbdSellCalcSubHistDao jbdSellCalcSubHistDao) {
		this.jbdSellCalcSubHistDao = jbdSellCalcSubHistDao;
	}

	public void setJmiMemberDao(JmiMemberDao jmiMemberDao) {
		this.jmiMemberDao = jmiMemberDao;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdMemberLinkCalcHistDao(JbdMemberLinkCalcHistDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdMemberLinkCalcHistManager#getJbdMemberLinkCalcHists(com.joymain.jecs.bd.model.JbdMemberLinkCalcHist)
     */
    public List getJbdMemberLinkCalcHists(final JbdMemberLinkCalcHist jbdMemberLinkCalcHist) {
        return dao.getJbdMemberLinkCalcHists(jbdMemberLinkCalcHist);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdMemberLinkCalcHistManager#getJbdMemberLinkCalcHist(String id)
     */
    public JbdMemberLinkCalcHist getJbdMemberLinkCalcHist(final String id) {
        return dao.getJbdMemberLinkCalcHist(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdMemberLinkCalcHistManager#saveJbdMemberLinkCalcHist(JbdMemberLinkCalcHist jbdMemberLinkCalcHist)
     */
    public void saveJbdMemberLinkCalcHist(JbdMemberLinkCalcHist jbdMemberLinkCalcHist) {
        dao.saveJbdMemberLinkCalcHist(jbdMemberLinkCalcHist);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdMemberLinkCalcHistManager#removeJbdMemberLinkCalcHist(String id)
     */
    public void removeJbdMemberLinkCalcHist(final String id) {
        dao.removeJbdMemberLinkCalcHist(new Long(id));
    }
    //added for getJbdMemberLinkCalcHistsByCrm
    public List getJbdMemberLinkCalcHistsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdMemberLinkCalcHistsByCrm(crm,pager);
    }

	public void saveJbdMemberLinkCalcHistPv(JbdMemberLinkCalcHist jbdMemberLinkCalcHist) throws AppException{
        if(StringUtil.isEmpty(jbdMemberLinkCalcHist.getKeepUserCode())){
        	dao.saveJbdMemberLinkCalcHist(jbdMemberLinkCalcHist);
        }else{
        	JmiMember jmiMember=jmiMemberDao.getJmiMember(jbdMemberLinkCalcHist.getKeepUserCode());
        	if(jmiMember==null){
				throw new AppException("miMember.notFound");
        	}else{
        		List<JbdSellCalcSubHist> list=jbdSellCalcSubHistDao.getJbdSellCalcSubHistsByLinkHist(jbdMemberLinkCalcHist.getUserCode(), jbdMemberLinkCalcHist.getWweek());
        		boolean flag=false;
        		for (JbdSellCalcSubHist hist : list) {
					if(jmiMember.getUserCode().equals(hist.getUserCode())){
						hist.setKeepPv(jbdMemberLinkCalcHist.getKeepPv());
						jbdSellCalcSubHistDao.saveJbdSellCalcSubHist(hist);
						flag=true;
						break;
					}
				}
        		if(flag){
        			for (JbdSellCalcSubHist hist : list) {
        				if(!jmiMember.getUserCode().equals(hist.getUserCode())){
            				hist.setKeepPv(new BigDecimal(0));
    						jbdSellCalcSubHistDao.saveJbdSellCalcSubHist(hist);
        				}
        			}
        		}

            	dao.saveJbdMemberLinkCalcHist(jbdMemberLinkCalcHist);
        	}
        }
        	
        		
        
        
	}

	public VJbdMemberLinkCalc getJbdMemberLinkCalcHistsByUserCodeWeek(String userCode, String wweek) {
		return dao.getJbdMemberLinkCalcHistsByUserCodeWeek(userCode, wweek);
	}

	public List getBdMemberBounsCalcLinkBySql(String userCode, int wweek, String endUserCode) {

		List bdMemberBounsCalcs=new ArrayList();
		String tmpMemberNo = userCode;
		while(true){
			Map  bdMemberBounsCalcMap= dao.findByWweekAndMemberBySql(wweek, tmpMemberNo);
			if(bdMemberBounsCalcMap==null){
				break;
			}
			bdMemberBounsCalcs.add(bdMemberBounsCalcMap);
			if("8888888888".equals(bdMemberBounsCalcMap.get("user_code")) || endUserCode.equals(bdMemberBounsCalcMap.get("user_code"))){
				break;
			}
			tmpMemberNo = (String) bdMemberBounsCalcMap.get("link_no");
		}
		return bdMemberBounsCalcs;
	}

	public Map getBonusRecordbyUserCode(String userCode, String wWeek) {
		return dao.getBonusRecordbyUserCode(userCode, wWeek);
	}

	public Map getBdSendRecordMap(String userCode, String wweek) {
		return dao.getBdSendRecordMap(userCode, wweek);
	}

	public List getSuccessSaleBonus(String recommendNo, String wweek) {
		return dao.getSuccessSaleBonus(recommendNo, wweek);
	}

	public List getFranchises(String recommendNo, String wweek, String type) {
		return dao.getFranchises(recommendNo, wweek, type);
	}

	public JbdMemberLinkCalcHist getJJbdMemberLinkCalcHistByUserCodeWeek(String userCode, String wweek) {
		return dao.getJJbdMemberLinkCalcHistByUserCodeWeek(userCode, wweek);
	}

	public List getStoreExpandPv(String userCode, String wweek) {
		return dao.getStoreExpandPv(userCode, wweek);
	}

	public List getStoreServePv(String userCode, String wweek) {
		return dao.getStoreServePv(userCode, wweek);
	}

	public List getStoreRecommendPv(String userCode, String wweek) {
		return dao.getStoreRecommendPv(userCode, wweek);
	}

	public String getLastHonorStar(String userCode) {
        BdPeriod bdPeriod=bdPeriodManager.getLatestSendBonus();
		String bdWeek= bdPeriod.getWyear()+""+ (bdPeriod.getWweek()<10?"0" + bdPeriod.getWweek():bdPeriod.getWweek());
        Map map=this.getBdSendRecordMap(userCode, bdWeek);
        if(map==null){
        	return "";
        }
		return (String) map.get("honor_star");
	}

	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public List getVentureFundPv(String userCode, String wweek) {
		return dao.getVentureFundPv(userCode, wweek);
	}

	public void saveJbdMemberLinkCalcHistGrade(JbdMemberLinkCalcHist jbdMemberLinkCalcHist,HttpServletRequest request,SysUser defSysUser) {
		
		String oldHonorStar=request.getParameter("oldHonorStar");
		String oldPassStar=request.getParameter("oldPassStar");
		Date curDate=new Date();
		JbdGradeNote jbdGradeNote=new JbdGradeNote();
		if(!oldHonorStar.equals(jbdMemberLinkCalcHist.getHonorStar().toString())){
			jbdGradeNote.setOldHonorStar(StringUtil.formatInt(oldHonorStar));
			jbdGradeNote.setNewHonorStar(jbdMemberLinkCalcHist.getHonorStar());
		}
		if(!oldPassStar.equals(jbdMemberLinkCalcHist.getPassStar().toString())){
			jbdGradeNote.setOldPassStar(StringUtil.formatInt(oldPassStar));
			jbdGradeNote.setNewPassStar(jbdMemberLinkCalcHist.getPassStar());
		}
		
		jbdGradeNote.setCreateNo(defSysUser.getUserCode());
		jbdGradeNote.setCreateTime(curDate);
		jbdGradeNote.setUserCode(jbdMemberLinkCalcHist.getUserCode());
		jbdGradeNote.setWweek(jbdMemberLinkCalcHist.getWweek());
		jbdGradeNoteManager.saveJbdGradeNote(jbdGradeNote);
		dao.saveJbdMemberLinkCalcHist(jbdMemberLinkCalcHist);
		

		String changeTravelPoint2012=request.getParameter("changeTravelPoint2012");
		if("changeTravelPoint2012".equals(changeTravelPoint2012)){

			//同时更新旅游积分奖衔
			JbdTravelPoint2012 jbdTravelPoint2012=jbdTravelPoint2012Manager.getJbdTravelPoint2012(jbdMemberLinkCalcHist.getUserCode());
			if(jbdTravelPoint2012!=null){
				jbdTravelPoint2012.setPassStar(jbdMemberLinkCalcHist.getHonorStar());
				jbdTravelPoint2012Manager.saveJbdTravelPoint2012(jbdTravelPoint2012);
			}
			//
			JbdTravelPoint2013 jbdTravelPoint2013=jbdTravelPoint2013Manager.getJbdTravelPoint2013(jbdMemberLinkCalcHist.getUserCode());
			if(jbdTravelPoint2013!=null){
				jbdTravelPoint2013.setPassStar(jbdMemberLinkCalcHist.getHonorStar());
				jbdTravelPoint2013Manager.saveJbdTravelPoint2013(jbdTravelPoint2013);
			}
		}
		
		
		
	}

	public void setJbdTravelPoint2013Manager(
			JbdTravelPoint2013Manager jbdTravelPoint2013Manager) {
		this.jbdTravelPoint2013Manager = jbdTravelPoint2013Manager;
	}

    @Override
    public void updateJbdMemberQualifyStar(
            List<JbdMemberLinkCalcHist> jbdMemberLinkCalcHists)
    {
        for(JbdMemberLinkCalcHist jbdMemberLinkCalcHist : jbdMemberLinkCalcHists) {
            this.dao.saveObject(jbdMemberLinkCalcHist);
        }
    }

	@Override
	public List getJbdSuccessLeaderPv(String userCode, String wweek) {
		return dao.getJbdSuccessLeaderPv(userCode, wweek);
	}

	@Override
	public List getSuccessLeaderPvDetail(String userCode, String wweek,
			String passStar, String startDate, String endDate,String algebra,String passStar1) {
		return dao.getSuccessLeaderPvDetail(userCode, wweek, passStar, startDate, endDate,algebra, passStar1);
	}

	@Override
	public Map getJBdLevel(String userCode, String wweek) {
		return dao.getJBdLevel(userCode, wweek);
	}

	@Override
	public List getVentureLeaderPvDetail(String userCode, String startDate, String endDate) {
		return dao.getVentureLeaderPvDetail(userCode, startDate, endDate);
	}

	@Override
	public List getServicePv(String userCode, String wweek) {
		return dao.getServicePv(userCode, wweek);
	}
	@Override
	public List getServicePv201607(String userCode, String wweek){
		return dao.getServicePv201607(userCode, wweek);
	}

	@Override
	public List getbdjPv201607(String userCode, String wweek) {
		// TODO Auto-generated method stub
		return dao.getbdjPv201607(userCode, wweek);
	}
	@Override
	public List getPopularizeBonusPv(String userCode, String wweek) {
		// TODO Auto-generated method stub
		return dao.getPopularizeBonusPv(userCode, wweek);
	}
}
