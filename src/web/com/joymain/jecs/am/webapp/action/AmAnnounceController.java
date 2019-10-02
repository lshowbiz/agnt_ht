package com.joymain.jecs.am.webapp.action;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.am.model.AmAnnounce;
import com.joymain.jecs.am.service.AmAnnounceManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;


public class AmAnnounceController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(AmAnnounceController.class);
    private AmAnnounceManager amAnnounceManager = null;
    
    private JmiMemberManager jmiMemberManager = null;
    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public void setAmAnnounceManager(AmAnnounceManager amAnnounceManager) {
        this.amAnnounceManager = amAnnounceManager;
    }
    
    
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}


	public String getLastDayOfMonth(String year, String month) {
    	   Calendar cal = Calendar.getInstance();
    	   //年
    	   cal.set(Calendar.YEAR, Integer.parseInt(year));
    	   //月，因为Calendar里的月是从0开始，所以要-1
    	   cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
    	   //日，设为一号
    	   cal.set(Calendar.DATE, 1);
    	   //月份加一，得到下个月的一号
    	   cal.add(Calendar.MONTH,1);
    	   //下一个月减一为本月最后一天
    	   cal.add(Calendar.DATE, -1);
    	   return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));//获得月末是几号

    	}

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        AmAnnounce amAnnounce = new AmAnnounce();
       
        BeanUtils.populate(amAnnounce, request.getParameterMap());
        
        String strAction = request.getParameter("strAction");
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        String userType = sessionLogin.getUserType();
        String userCode = sessionLogin.getUserCode();

		CommonRecord crm=RequestUtil.toCommonRecord(request);
		
        Pager pager = new Pager("amAnnounceListTable",request, 20);
        if(!sessionLogin.getIsManager()){//非管理中心的需要
        	crm.setValue("companyCode", sessionLogin.getCompanyCode());
        }
        
        if("browserAmAnnounce".equals(strAction)){
        	
        	
        	if(sessionLogin.getIsCompany()){//分公司
            	crm.setValue("permitClass", "11");
//            }else if(sessionLogin.getIsAgent()){//代理商
//            	AiAgent aiAgent = aiAgentManager.getAiAgent(userCode);
//            	if("P".equalsIgnoreCase(userType)){
//            		crm.setValue("permitClass", "23");
//            	}else{
//            		if("1".equals(aiAgent.getDeport())){
//            			crm.setValue("permitClass", "22");
//            		}else{
//            			crm.setValue("permitClass", "21");
//            		}
//            	}
            }else if(sessionLogin.getIsMember()){//Fn_Get_Team_No 判断团队
            	JmiMember miMember = jmiMemberManager.getJmiMember(userCode);
            	
            	String memberPermitClass="";
            	List list=jdbcTemplate.queryForList("select Fn_Get_Team_No_Am('"+miMember.getUserCode()+"') as team_no from dual");
            	if(!list.isEmpty()&&((Map)list.get(0)).get("team_no")!=null){
            		String teamNo=((Map)list.get(0)).get("team_no").toString();
            		memberPermitClass+="','"+teamNo;
            	}else{
            		memberPermitClass="3"+miMember.getCardType();
                	//crm.setValue("permitClass", memberPermitClass);
                	//一级生活馆
                	if("1".equals(miMember.getIsstore())){
                		memberPermitClass+="','21";
            			//crm.setValue("permitClass", memberPermitClass+"','21");
                	}
                	
                	//二级生活馆
                	if("2".equals(miMember.getIsstore())){
                		memberPermitClass+="','22";
            			//crm.setValue("permitClass", memberPermitClass+"','22");
                	}
            	}
            	
            	

            	if(!StringUtil.isEmpty(miMember.getMemberType())){
            		String memberType=miMember.getMemberType().trim();

                	//joyme会员
                	if("2".equals(memberType)){
                		memberPermitClass+="','41";
                		//crm.setValue("permitClass", memberPermitClass+"','41");
                	}
                	//青岛会员
                	if("9".equals(memberType)){
                		memberPermitClass+="','43";
                		//crm.setValue("permitClass", memberPermitClass+"','43");
                	}
            	}
            	
            	
            	
            	//登陆会员treeindex
        		String loninTreeIndex=miMember.getJmiRecommendRef().getTreeIndex();
        		
            	JmiMember lcMiMember = jmiMemberManager.getJmiMember("CN26185586");
            	if(lcMiMember!=null ){
            		String lcTreeIndex=lcMiMember.getJmiRecommendRef().getTreeIndex();
            		String rmemberIndexTmp = StringUtil.getLeft(loninTreeIndex, lcTreeIndex.length());
            		if(loninTreeIndex.length() >= lcTreeIndex.length() && rmemberIndexTmp.equals(lcTreeIndex)){
                		memberPermitClass+="','42";
            			//crm.setValue("permitClass", "42");
            		}
            	}

            	
            	//云南团队
            	JmiMember ynMiMember = jmiMemberManager.getJmiMember("CN18645446");
            	if(ynMiMember!=null ){
            		String ynTreeIndex=ynMiMember.getJmiRecommendRef().getTreeIndex();
            		String rmemberIndexTmp = StringUtil.getLeft(loninTreeIndex, ynTreeIndex.length());
            		if(loninTreeIndex.length() >= ynTreeIndex.length() && rmemberIndexTmp.equals(ynTreeIndex)){
                		memberPermitClass+="','45";
            		}
            	}
            	

            	JmiMember ltMiMember = jmiMemberManager.getJmiMember("CN54623113");
            	if(ltMiMember!=null ){
            		String ynTreeIndex=ltMiMember.getJmiRecommendRef().getTreeIndex();
            		String rmemberIndexTmp = StringUtil.getLeft(loninTreeIndex, ynTreeIndex.length());
            		if(loninTreeIndex.length() >= ynTreeIndex.length() && rmemberIndexTmp.equals(ynTreeIndex)){
                		memberPermitClass+="','46";
            		}
            	}
            	JmiMember SHMiMember = jmiMemberManager.getJmiMember("CN85777230");
            	if(SHMiMember!=null ){
            		String ynTreeIndex=SHMiMember.getJmiRecommendRef().getTreeIndex();
            		String rmemberIndexTmp = StringUtil.getLeft(loninTreeIndex, ynTreeIndex.length());
            		if(loninTreeIndex.length() >= ynTreeIndex.length() && rmemberIndexTmp.equals(ynTreeIndex)){
                		memberPermitClass+="','47";
            		}
            	}
            	JmiMember ZGFMiMember = jmiMemberManager.getJmiMember("CN17116583");
            	if(ZGFMiMember!=null ){
            		String ynTreeIndex=ZGFMiMember.getJmiRecommendRef().getTreeIndex();
            		String rmemberIndexTmp = StringUtil.getLeft(loninTreeIndex, ynTreeIndex.length());
            		if(loninTreeIndex.length() >= ynTreeIndex.length() && rmemberIndexTmp.equals(ynTreeIndex)){
                		memberPermitClass+="','48";
            		}
            	}
            	JmiMember CMHMiMember = jmiMemberManager.getJmiMember("CN14545393");
            	if(CMHMiMember!=null ){
            		String ynTreeIndex=CMHMiMember.getJmiRecommendRef().getTreeIndex();
            		String rmemberIndexTmp = StringUtil.getLeft(loninTreeIndex, ynTreeIndex.length());
            		if(loninTreeIndex.length() >= ynTreeIndex.length() && rmemberIndexTmp.equals(ynTreeIndex)){
                		memberPermitClass+="','49";
            		}
            	}

            	JmiMember CALMiMember = jmiMemberManager.getJmiMember("CN21344506");
            	if(CALMiMember!=null ){
            		String ynTreeIndex=CALMiMember.getJmiRecommendRef().getTreeIndex();
            		String rmemberIndexTmp = StringUtil.getLeft(loninTreeIndex, ynTreeIndex.length());
            		if(loninTreeIndex.length() >= ynTreeIndex.length() && rmemberIndexTmp.equals(ynTreeIndex)){
                		memberPermitClass+="','50";
            		}
            	}
            	

            	JmiMember SXHMiMember = jmiMemberManager.getJmiMember("CN49698390");
            	if(SXHMiMember!=null ){
            		String ynTreeIndex=SXHMiMember.getJmiRecommendRef().getTreeIndex();
            		String rmemberIndexTmp = StringUtil.getLeft(loninTreeIndex, ynTreeIndex.length());
            		if(loninTreeIndex.length() >= ynTreeIndex.length() && rmemberIndexTmp.equals(ynTreeIndex)){
                		memberPermitClass+="','51";
            		}
            	}

            	JmiMember ZJMiMember = jmiMemberManager.getJmiMember("CN15279222");
            	if(ZJMiMember!=null ){ 
            		String zjTreeIndex=ZJMiMember.getJmiRecommendRef().getTreeIndex();
            		String rmemberIndexTmp = StringUtil.getLeft(loninTreeIndex, zjTreeIndex.length());
            		if(loninTreeIndex.length() >= zjTreeIndex.length() && rmemberIndexTmp.equals(zjTreeIndex)){
                		memberPermitClass+="','52";
            		}
            	}
            	
            	
            	JmiMember tyMiMember1 = jmiMemberManager.getJmiMember("CN18766575");
            	JmiMember tyMiMember2 = jmiMemberManager.getJmiMember("CN16481747");
            	JmiMember tyMiMember3 = jmiMemberManager.getJmiMember("CN10919893");
            	
            	if(tyMiMember1!=null && tyMiMember2!=null && tyMiMember3!=null){
            		String ty1TreeIndex=tyMiMember1.getJmiRecommendRef().getTreeIndex();
            		String ty2TreeIndex=tyMiMember2.getJmiRecommendRef().getTreeIndex();
            		String ty3TreeIndex=tyMiMember3.getJmiRecommendRef().getTreeIndex();
            		

            		String rmemberIndexTmp = StringUtil.getLeft(loninTreeIndex, ty1TreeIndex.length());
            		
            		if( (loninTreeIndex.length() >= ty1TreeIndex.length() && rmemberIndexTmp.equals(ty1TreeIndex))
            			||  (loninTreeIndex.length() >= ty2TreeIndex.length() && rmemberIndexTmp.equals(ty2TreeIndex))	
            			||  (loninTreeIndex.length() >= ty3TreeIndex.length() && rmemberIndexTmp.equals(ty3TreeIndex))){
                			memberPermitClass+="','44";
            		}
            		
            		
            		
            		
            	}
            	

            	JmiMember TJMiMember = jmiMemberManager.getJmiMember("CN10919893");
            	if(TJMiMember!=null ){ 
            		String tjTreeIndex=TJMiMember.getJmiRecommendRef().getTreeIndex();
            		String rmemberIndexTmp = StringUtil.getLeft(loninTreeIndex, tjTreeIndex.length());
            		if(loninTreeIndex.length() >= tjTreeIndex.length() && rmemberIndexTmp.equals(tjTreeIndex)){
                		memberPermitClass+="','53";
            		}
            	}
            	JmiMember RWMiMember = jmiMemberManager.getJmiMember("CN11178310");
            	if(RWMiMember!=null ){ 
            		String rwTreeIndex=RWMiMember.getJmiRecommendRef().getTreeIndex();
            		String rmemberIndexTmp = StringUtil.getLeft(loninTreeIndex, rwTreeIndex.length());
            		if(loninTreeIndex.length() >= rwTreeIndex.length() && rmemberIndexTmp.equals(rwTreeIndex)){
                		memberPermitClass+="','54";
            		}
            	}
            	JmiMember CLMiMember = jmiMemberManager.getJmiMember("CN10582354");
            	if(CLMiMember!=null ){ 
            		String clTreeIndex=CLMiMember.getJmiRecommendRef().getTreeIndex();
            		String rmemberIndexTmp = StringUtil.getLeft(loninTreeIndex, clTreeIndex.length());
            		if(loninTreeIndex.length() >= clTreeIndex.length() && rmemberIndexTmp.equals(clTreeIndex)){
                		memberPermitClass+="','55";
            		}
            	}
            	
            	if(!StringUtil.isEmpty(memberPermitClass) && memberPermitClass.startsWith(",") ){
            		memberPermitClass=memberPermitClass.substring(1, memberPermitClass.length());
            	}
            	crm.setValue("permitClass", memberPermitClass);
            	//
//            	Long remainPv = 0l;
//            	String pvMsg = "";            	
//            	int msgTag=0;
//            	long allpv = 0l;
//            	
//            	
//            	GregorianCalendar g=new GregorianCalendar();            	
//            	String nowYear = String.valueOf(g.get(Calendar.YEAR));
//            	String nowMonth = String.valueOf(g.get(Calendar.MARCH)+1);
//
//            	
//            	String[] cxdate = this.cxDate();
//            	String startDat = cxdate[0];            	
//            	String endDat = cxdate[1];
//            	
//            	String fstTime = poMemberOrderManager.getMemberFirstOrderStatusTime(userCode);            	
//            	request.setAttribute("fstTime", fstTime);
//            	//判断是否符合重销资格
//            	DateUtil du = new DateUtil();
//            	Date fstDate = du.convertStringToDate(fstTime);
//            	String fstDate_mon = fstTime.split("-")[1];
//            	String fstDate_year = fstTime.split("-")[0];
//            	String fstDate_day = fstTime.split("-")[2].split(" ")[0];
//            	
//            	int inCxMon = this.getInCxMon(Integer.valueOf(fstDate_year), Integer.valueOf(fstDate_mon), Integer.valueOf(fstDate_day));
//            	int beCxMon = inCxMon + 2;
//            	
//         
//            	
//            	Date dateOfbeCxMon = this.getDayOfwork(Integer.valueOf(fstDate_year),beCxMon*4-3);
//            	
//            	if( (g.getTime().getTime() - dateOfbeCxMon.getTime()) < 0 ){            		
//            		msgTag = 2;
//            	}else{            		
//            		
//	            	allpv = poMemberOrderManager.getPoMemberROrderCTime(userCode,startDat,endDat);	            	
//	            	
//					if(allpv==0){
//						msgTag = 1;
//					}else if(allpv >= 50) {
//						msgTag = 3;
//					} else {
//					  msgTag = 4;
//					  remainPv = 50 - allpv;
//					}
//            	}
//            	
//            	request.setAttribute("remainPv", remainPv);
//            	request.setAttribute("msgTag", msgTag);
//            	request.setAttribute("endDat", endDat);

            	
            }        	
        	
        	crm.setValue("browserNo", sessionLogin.getUserCode());
        	crm.setValue("viewFlag", "view");
        	crm.setValue("status", "1");        	
        	
        	//查询已查看与未查看数
        	request.setAttribute("readNum", amAnnounceManager.getReadNum(crm, pager));
            request.setAttribute("noreadNum", amAnnounceManager.getNoReadNum(crm, pager));
        }
        
        if("editAmAnnounce".equals(strAction)){
        	//取消谁发布才可以看到自己发布的公告 结算部 每月计划修改
        	//crm.setValue("issuerName", userCode);
        }
        
        if("checkAmAnnounce".equals(strAction)){
        	request.setAttribute("userCode", userCode);
        	request.setAttribute("userName", sessionLogin.getUserName());
        }
        
        List amAnnounces = amAnnounceManager.getAmAnnouncesByCrm(crm,pager);
        
        request.setAttribute("amAnnounceListTable_totalRows", pager.getTotalObjects());

        request.setAttribute("strAction", strAction);
        request.setAttribute("amAnnounceExample", amAnnounce);
        return new ModelAndView("am/amAnnounceList", "amAnnounceList", amAnnounces);
    }

	
	public String[] cxDate(){
		
		String[] cxdate={"",""};
		
		Calendar c = new GregorianCalendar();
		
		int year=c.get(Calendar.YEAR);		
		int week=c.get(Calendar.WEEK_OF_YEAR);	//第几周	
		int weekNum=c.get(Calendar.DAY_OF_WEEK);		//今天星期几		
		//System.out.println("year:"+year);
		//System.out.println("week:"+week);
		
		int mo = week /4;
		int yu = week %4;
		//System.out.println("mo:"+mo + " ,yu:"+yu);

		int firstWeekNum = 0 ;//工作月的第一周数
		int lastWeekNum = 0 ;//工作月的最后一周数
		
		if(yu==0){			
			lastWeekNum = (mo) * 4 ;//工作月的最后一周数
			firstWeekNum = lastWeekNum-3 ;//工作月的第一周数
		}else{
			lastWeekNum = (mo+1) * 4 ;//工作月的最后一周数
			firstWeekNum = lastWeekNum-3 ;//工作月的第一周数
		}		
		//如果碰到周六直接算下一周
		if( weekNum == 7 ){
			if(mo==13){//跨年的情况
				year=year+1;
				firstWeekNum=1;
				lastWeekNum=4;
			}else{
				lastWeekNum=lastWeekNum+4;
				firstWeekNum=lastWeekNum-3; 
			}			
		}		
		//跨年
		int mon = c.getTime().getMonth();
		if(mo==0 && mon>1)
			year++;
			
		//System.out.println("firstWeekNum:"+firstWeekNum + " ,lastWeekNum:"+lastWeekNum);
		
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, firstWeekNum);
		c.set(Calendar.DAY_OF_WEEK, 1);
		
		Date firstDay = c.getTime();		
		firstDay = DateUtil.getDateOffset(firstDay,5,-1);
		String strFirstDay = DateUtil.convertDateToString(firstDay);//每周第一天
		//System.out.println(strFirstDay);
		cxdate[0]=strFirstDay;
		
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, lastWeekNum);
		c.roll(Calendar.DAY_OF_WEEK, 6);		
		Date lastDay=c.getTime();
		lastDay=DateUtil.getDateOffset(lastDay,5,-1);
		String strLastDay = DateUtil.convertDateToString(lastDay);//每周最后一天
		//System.out.println(strLastDay);
		cxdate[1]=strLastDay;
		
		return cxdate;
	}
	
	public int getInCxMon(int year, int month ,int day){
		
		java.util.Calendar c=java.util.Calendar.getInstance();
        c.set(year,month-1,day);//设置为2007年11月6号
        
        //System.out.println(c.getTime());
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        //System.out.println(dayOfWeek);
        
        int weekOfYear = c.get(java.util.Calendar.WEEK_OF_YEAR);
        
        if(dayOfWeek == 7 ) weekOfYear = weekOfYear + 1;
        
       // System.out.println(weekOfYear);//获得c代表的日期在当年是第几周
        int yu = (weekOfYear%4);
        int workMonth = (yu==0)?(weekOfYear/4):(yu=(weekOfYear/4)+1);
        //System.out.println(workMonth);//第一张单的工作月
        
        return workMonth;
	}
	
	public Date getDayOfwork(int year,int workWeek){
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
        c.set(Calendar.WEEK_OF_YEAR, workWeek);
        Date firstDay = c.getTime();	
        firstDay = DateUtil.getDateOffset(firstDay,5,-7);
        String strFirstDay = DateUtil.convertDateToString(firstDay);//每周第一天
        //System.out.println(strFirstDay);
        
		return firstDay;
	}
}
