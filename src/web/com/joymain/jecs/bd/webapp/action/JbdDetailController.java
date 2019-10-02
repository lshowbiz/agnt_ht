package com.joymain.jecs.bd.webapp.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.bd.service.JbdMemberLinkCalcHistManager;
import com.joymain.jecs.bd.service.JbdSellCalcSubDetailHistManager;
import com.joymain.jecs.bd.service.JbdSellCalcSubHistManager;
import com.joymain.jecs.bd.service.JbdVentureLeaderSubHistManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdDetailController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdDetailController.class);
    private JbdSellCalcSubHistManager jbdSellCalcSubHistManager = null;
    private JbdVentureLeaderSubHistManager jbdVentureLeaderSubHistManager;
    private JbdSellCalcSubDetailHistManager jbdSellCalcSubDetailHistManager;
    private JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager;
    private BdPeriodManager bdPeriodManager;
    public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public void setJbdMemberLinkCalcHistManager(
			JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager) {
		this.jbdMemberLinkCalcHistManager = jbdMemberLinkCalcHistManager;
	}

	public void setJbdVentureLeaderSubHistManager(
			JbdVentureLeaderSubHistManager jbdVentureLeaderSubHistManager) {
		this.jbdVentureLeaderSubHistManager = jbdVentureLeaderSubHistManager;
	}

	public void setJbdSellCalcSubHistManager(JbdSellCalcSubHistManager jbdSellCalcSubHistManager) {
        this.jbdSellCalcSubHistManager = jbdSellCalcSubHistManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        String userCode=request.getParameter("userCode");
        String wweek=request.getParameter("wweek");
        
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        if("M".equals(defSysUser.getUserType())){
        	userCode=defSysUser.getUserCode();
        }
    	String newWeek=(String) Constants.sysConfigMap.get("AA").get("new.week");
    	
    	
    	
        String type=request.getParameter("type");
        if("ventureSalesPv".equals(type)){
            List jbdSellCalcSubHists=jbdSellCalcSubHistManager.getJbdSellCalcSubHistsByLink(userCode, new Integer(wweek));
            request.setAttribute("jbdSellCalcSubHists", jbdSellCalcSubHists);
        }
        if("ventureLeaderPv".equals(type)){//旧：感恩 新：级差
        	List jbdVentureLeaderSubHists=jbdVentureLeaderSubHistManager.getJbdVentureLeaderSubHistByUserCode(userCode, wweek, "01");
            request.setAttribute("jbdVentureLeaderSubHists", jbdVentureLeaderSubHists);
        }
        /** 20160817 add  销售奖改版  */
        if("ventureLeaderPv201607".equals(type)){//销售奖
        	List jbdVentureLeaderSubHists=jbdVentureLeaderSubHistManager.getJbdVentureLeaderSubHistByUserCode(userCode, wweek, "01");
            request.setAttribute("jbdVentureLeaderSubHists", jbdVentureLeaderSubHists);
        }
        /** 201805 add  销售奖改版  */
        if("ventureLeaderPv201805".equals(type)){//销售奖
        	List jbdVentureLeaderSubHists=jbdVentureLeaderSubHistManager.getJbdVentureLeaderSubHistByUserCode(userCode, wweek, "01");
            request.setAttribute("jbdVentureLeaderSubHists", jbdVentureLeaderSubHists);
        }
        if("ventureLeaderPvDetail201805".equals(type)){//销售奖
        	List jbdVentureLeaderSubHistDetails=jbdVentureLeaderSubHistManager.getJbdVentureLeaderSubHistDetailByUserCode(userCode, wweek);
            request.setAttribute("jbdVentureLeaderSubHistDetails", jbdVentureLeaderSubHistDetails);
        }
        if("successLeaderPv".equals(type)){//代数奖
        	
        	if(StringUtil.formatInt(wweek)>=StringUtil.formatInt(newWeek)){
            	List jbdSuccessLeaderPvList=jbdMemberLinkCalcHistManager.getJbdSuccessLeaderPv(userCode, wweek);
                request.setAttribute("jbdSuccessLeaderPvList", jbdSuccessLeaderPvList);
        	}else{
        		List jbdVentureLeaderSubHists=jbdVentureLeaderSubHistManager.getJbdVentureLeaderSubHistByUserCode(userCode, wweek, "02");
                request.setAttribute("jbdVentureLeaderSubHists", jbdVentureLeaderSubHists);
        	}
        	
        	
        }
        if("successSalesPv".equals(type)){//卓越领导奖
        	List jbdMemberLinkCalcHists=jbdMemberLinkCalcHistManager.getSuccessSaleBonus(userCode, wweek);
            request.setAttribute("jbdMemberLinkCalcHists", jbdMemberLinkCalcHists);
        }
        if("franchiseMoney".equals(type)){
        	List franchiseMoneys=jbdMemberLinkCalcHistManager.getFranchises(userCode, wweek, "01");
            request.setAttribute("franchiseMoneys", franchiseMoneys);
        }
        
        if("consumerAmount".equals(type)){
        	List consumerAmounts=jbdMemberLinkCalcHistManager.getFranchises(userCode, wweek, "02");
            request.setAttribute("consumerAmounts", consumerAmounts);
        }
        
        if("storeExpandPv".equals(type)){
        	List storeExpandPvs=jbdMemberLinkCalcHistManager.getStoreExpandPv(userCode, wweek);
            request.setAttribute("storeExpandPvs", storeExpandPvs);
        }
        if("bdjPv201607".equals(type)){
        	List storeExpandPvs=jbdMemberLinkCalcHistManager.getbdjPv201607(userCode, wweek);
            request.setAttribute("storeExpandPvs", storeExpandPvs);
        }
        
        if("storeServePv".equals(type)){
        	List storeServePvs=jbdMemberLinkCalcHistManager.getStoreServePv(userCode, wweek);
            request.setAttribute("storeServePvs", storeServePvs);
        }
        //店铺推荐奖
        if("storeRecommendPv".equals(type)){
        	List storeRecommendPvs=jbdMemberLinkCalcHistManager.getStoreRecommendPv(userCode, wweek);
            request.setAttribute("storeRecommendPvs", storeRecommendPvs);
        }
        //服务奖明细 
        if("servicePv".equals(type)){
			 List servicePvList=jbdMemberLinkCalcHistManager.getServicePv(userCode, wweek);
	         request.setAttribute("servicePvList", servicePvList);
	    }
        
      //服务奖明细 201607以后的路线
        if("servicePv201607".equals(type)){
			 List servicePvList=jbdMemberLinkCalcHistManager.getServicePv201607(userCode, wweek);
	         request.setAttribute("servicePvList", servicePvList);
	    }
        
        //推荐奖金
        if("recommendBonusPv".equals(type)){

        	if(StringUtil.formatInt(wweek)>=StringUtil.formatInt(newWeek)){

          	  List jbdSellCalcRecommendBouns=jbdSellCalcSubDetailHistManager.getJbdSellCalcRecommendBouns(userCode, new Integer(wweek),null);
                request.setAttribute("jbdSellCalcRecommendBouns", jbdSellCalcRecommendBouns);
        	}else{

            	  List jbdSellCalcRecommendBouns=jbdSellCalcSubDetailHistManager.getJbdSellCalcRecommendBouns(userCode, new Integer(wweek),"02");
                  request.setAttribute("jbdSellCalcRecommendBouns", jbdSellCalcRecommendBouns);
        	}
        	
        	
        }
      //推广奖
        if("popularizeBonusPv".equals(type)){
        	List popularizeBonusPvs=jbdMemberLinkCalcHistManager.getPopularizeBonusPv(userCode, wweek);
            request.setAttribute("popularizeBonusPvs", popularizeBonusPvs);
        	
        }
        //扶持奖
        if("ventureFund".equals(type)){
        	List ventureFundPvs=jbdMemberLinkCalcHistManager.getVentureFundPv(userCode, wweek);
            request.setAttribute("ventureFundPvs", ventureFundPvs);
        }

        //代数奖明细
        if("successLeaderPvDetail".equals(type)){
        	
        	BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(wweek);

        	Date sDate=null;
        	Date eDate=null;
        	
        	
        	List bdPeriodList=bdPeriodManager.getBdPeriodsByFmonth(bdPeriod.getFyear()+"", bdPeriod.getFmonth()+"");
			if(!bdPeriodList.isEmpty()){		
				if(bdPeriodList.size()==1){
					BdPeriod curBdPeriod=(BdPeriod) bdPeriodList.get(0);
					sDate=curBdPeriod.getStartTime();
					eDate=curBdPeriod.getEndTime();
				}else{
					for (int i = 0; i < bdPeriodList.size(); i++) {
						BdPeriod curBdPeriod=(BdPeriod) bdPeriodList.get(i);
						if(i==0){
							sDate=curBdPeriod.getStartTime();
						}else if(i==(bdPeriodList.size()-1)){
							eDate=curBdPeriod.getEndTime();
						}
					}
				}
			}
        	
        	
        	
        	
        	
        	
        	
        	String stime = DateUtil.getDate(sDate,"yyyy-MM-dd HH:mm:ss");
        	String etime = DateUtil.getDate(eDate,"yyyy-MM-dd HH:mm:ss");
 /*       	List successLeaderPvDetailList=null;
        	String passStar=request.getParameter("passStar");
    		if("1".equals(passStar) || "2".equals(passStar) || "3".equals(passStar)){
    			passStar="v_jbd_gem_list";
    		}else if("4".equals(passStar) || "5".equals(passStar) || "6".equals(passStar)){
    			passStar="v_jbd_diamond_list";
    		}else if("7".equals(passStar) || "8".equals(passStar) || "9".equals(passStar)){
    			passStar="v_jbd_crown_list";
    		}else{
    			//request.setAttribute("successLeaderPvDetailList", successLeaderPvDetailList);
    		}*/
    		
    		String algebra=request.getParameter("algebra");
    		String passStar=request.getParameter("passStar");
        	//if(passStar.length()>5){
    		List successLeaderPvDetailList=jbdMemberLinkCalcHistManager.getSuccessLeaderPvDetail(userCode, wweek, "v_jbd_crown_list", stime, etime,algebra,passStar);
        	//}

            request.setAttribute("bdRecCalculatingSubDetailsTable_totalRows",successLeaderPvDetailList.size());
        	
            request.setAttribute("successLeaderPvDetailList", successLeaderPvDetailList);
        }
        //销售奖明细的明细
        if("ventureLeaderPvDetail".equals(type)){
        	BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(wweek);
        	
        	
        	

        	Date sDate=null;
        	Date eDate=null;
        	
        	
        	List bdPeriodList=bdPeriodManager.getBdPeriodsByFmonth(bdPeriod.getFyear()+"", bdPeriod.getFmonth()+"");
			if(!bdPeriodList.isEmpty()){		
				if(bdPeriodList.size()==1){
					BdPeriod curBdPeriod=(BdPeriod) bdPeriodList.get(0);
					sDate=curBdPeriod.getStartTime();
					eDate=curBdPeriod.getEndTime();
				}else{
					for (int i = 0; i < bdPeriodList.size(); i++) {
						BdPeriod curBdPeriod=(BdPeriod) bdPeriodList.get(i);
						if(i==0){
							sDate=curBdPeriod.getStartTime();
						}else if(i==(bdPeriodList.size()-1)){
							eDate=curBdPeriod.getEndTime();
						}
					}
				}
			}
        	
        	String stime = DateUtil.getDate(sDate,"yyyy-MM-dd HH:mm:ss");
        	String etime = DateUtil.getDate(eDate,"yyyy-MM-dd HH:mm:ss");
        	
        	List ventureLeaderPvDetailList=jbdMemberLinkCalcHistManager.getVentureLeaderPvDetail(userCode, stime, etime);
        	

            request.setAttribute("ventureLeaderPvDetailList", ventureLeaderPvDetailList);
        }
   	
        
        
        request.setAttribute("type", type);
        if(StringUtil.formatInt(wweek)>=StringUtil.formatInt(newWeek)){
        	 return new ModelAndView("bd/jbdDetail2015");
    	}else{
    		 return new ModelAndView("bd/jbdDetail");
    	}
    	
       
       
    }

	public void setJbdSellCalcSubDetailHistManager(
			JbdSellCalcSubDetailHistManager jbdSellCalcSubDetailHistManager) {
		this.jbdSellCalcSubDetailHistManager = jbdSellCalcSubDetailHistManager;
	}
}
