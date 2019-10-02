package com.joymain.jecs.bd.webapp.action;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.model.JbdBonusBalance;
import com.joymain.jecs.bd.model.JbdSendRecordHist;
import com.joymain.jecs.bd.model.VJbdSendRecord;
import com.joymain.jecs.bd.service.JbdBonusBalanceManager;
import com.joymain.jecs.bd.service.JbdSendRecordHistManager;
import com.joymain.jecs.fi.service.FiCoinLogManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.ListUtil;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdMemberLinkCalcHistController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdMemberLinkCalcHistController.class);
    private JbdSendRecordHistManager jbdSendRecordHistManager;
    private JbdBonusBalanceManager jbdBonusBalanceManager;
    private FiCoinLogManager fiCoinLogManager;
    public void setFiCoinLogManager(FiCoinLogManager fiCoinLogManager) {
		this.fiCoinLogManager = fiCoinLogManager;
	}

	public void setJbdBonusBalanceManager(
			JbdBonusBalanceManager jbdBonusBalanceManager) {
		this.jbdBonusBalanceManager = jbdBonusBalanceManager;
	}

	public void setJbdSendRecordHistManager(
			JbdSendRecordHistManager jbdSendRecordHistManager) {
		this.jbdSendRecordHistManager = jbdSendRecordHistManager;
	}

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        CommonRecord crm=RequestUtil.toCommonRecord(request);
        String companyCode="";
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        if("C".equals(defSysUser.getUserType())){
        	companyCode=defSysUser.getCompanyCode();  
    		if("AA".equals(defSysUser.getCompanyCode())){
    			companyCode=request.getParameter("companyCode");
    		}
    	}else if("M".equals(defSysUser.getUserType())){
    		crm.addField("userCode", defSysUser.getUserCode());

    		JbdBonusBalance jbdBonusBalance=jbdBonusBalanceManager.getJbdBonusBalance(defSysUser.getUserCode());

    		if(jbdBonusBalance.getBonusBalance().compareTo(new BigDecimal(3))==1&&jbdBonusBalance.getBonusBalance().compareTo(new BigDecimal(500))==-1&&"0".equals(jbdBonusBalance.getFlag())){
    			request.setAttribute("JbdBonusBalance", "JbdBonusBalance");
    		}
//    		else{
//    			request.setAttribute("JbdBonusBalanceDate", jbdBonusBalance.getFlagTime());
//    		}
    		
    		if("jbdApplication".equals(request.getParameter("strAction"))){
    			try {
    				

    		        Pager pager1 = new Pager("bdSendRegisterListTable",request, 0);
    		        CommonRecord crm1=RequestUtil.toCommonRecord(request);
    		        crm1.addField("allot", "1");
    		        crm1.addField("reissueStatus","1");
    		        crm1.addField("exitDateNull","exitDateNull");
    		        crm1.addField("active","0");
    		        crm1.addField("active","0");
    		        crm1.addField("notEqualCardType","0");
    		        crm1.addField("remittanceMoneyGreater","0");
    		        crm1.addField("registerStatus","1");
    		        crm1.addField("userCode",defSysUser.getUserCode());
    				
    		        List list=jbdSendRecordHistManager.getJbdSendRecordHistsByCrm(crm1, pager1);
    		        if(list.size()>0){
    					MessageUtil.saveMessage(request, LocaleUtil.getLocalText("bd.send.exist"));
    	    	        return new ModelAndView("redirect:bdSendRecords.html?strAction=bdSendRecords");
    		        }
    				
    				jbdBonusBalanceManager.saveApplication(defSysUser.getUserCode());
					MessageUtil.saveMessage(request, LocaleUtil.getLocalText("Application.updateSuccess"));
	    	        return new ModelAndView("redirect:bdSendRecords.html?strAction=bdSendRecords");
					
				} catch (Exception e) {
					MessageUtil.saveMessage(request, LocaleUtil.getLocalText(e.getMessage()));
				}
    		}
    	}
        
        Pager pager = new Pager("bdSendRecordListTable",request, 20);
        
        String memberNo= crm.getString("userCode",request.getParameter("userCode"));
        String wweek= crm.getString("wweek",request.getParameter("wweek"));
        String name= crm.getString("name",request.getParameter("name"));
        
        if(!StringUtil.isEmpty( crm.getString("userCode",""))){
        	JbdBonusBalance jbdBonusBalance=jbdBonusBalanceManager.getJbdBonusBalance(crm.getString("userCode",""));
        	if(null!=jbdBonusBalance&&null!=jbdBonusBalance.getFlagTime()){
        		request.setAttribute("JbdBonusBalanceDate", jbdBonusBalance.getFlagTime());
        	}
        }
        WeekFormatUtil.setSearchFweek(crm);
        
        if("M".equals(defSysUser.getUserType())){
        	crm.addField("companyCode", defSysUser.getCompanyCode());
        	List bdSendRecords=jbdSendRecordHistManager.getBdSendRecordsByCrmBySqlForView(crm);
        	BigDecimal bpoint =fiCoinLogManager.getFiCoinLogAmtByUserCode(defSysUser.getUserCode(), "B");
        	request.setAttribute("bpoint", bpoint);
        	request.setAttribute("sortAble", "false");
        	request.setAttribute("bdSendRecordListTable_totalRows", bdSendRecords.size());
            if(RequestUtil.isMobileRequest(request)){
            	return new ModelAndView("mobile/bd/bdSendRecordList", "bdSendRecordList", bdSendRecords);
            }
        	return new ModelAndView("bd/bdSendRecordList", "bdSendRecordList", bdSendRecords);
        }else{
        	
        	String exportExcel = request.getParameter("exportExcel");
        	if (StringUtil.isEmpty(memberNo)&&!StringUtil.isInteger(wweek)&&StringUtil.isEmpty(name)){
            	request.setAttribute("bdSendRecordListTable_totalRows", pager.getTotalObjects());
            	//判断是否点击了按钮
            	if("exportExcel".equals(exportExcel)) {
            		//调用导出方法
            		buildExcel(response,new ArrayList<JbdSendRecordHist>(),companyCode,defSysUser.getDefCharacterCoding());
            		return null;
            	}
    			return new ModelAndView("bd/bdSendRecordList", "bdSendRecordList", null);	
    		}else{
            	//判断是否点击了按钮
            	if("exportExcel".equals(exportExcel)) {
            		//调用导出方法
            		pager = new Pager("bdSendRecordListTable",request, 0);
            		List bdSendRecords=jbdSendRecordHistManager.getJbdSendRecordHistsByCrm(crm, pager);
            		buildExcel(response,bdSendRecords,companyCode,defSysUser.getDefCharacterCoding());
            		return null;
            	} else {
            		//列表
            		crm.addField("companyCode", companyCode);
        			crm.addField("tableName", "VJbdSendRecord");
        			
        			List bdSendRecords=jbdSendRecordHistManager.getJbdSendRecordHistsByCrm(crm, pager);
        			request.setAttribute("bdSendRecordListTable_totalRows", pager.getTotalObjects());
        			Object [] bonusObj=jbdSendRecordHistManager.getSumBonus(crm);
                	request.setAttribute("bonusObj", bonusObj);
                	request.setAttribute("sortAble", "true");
                	return new ModelAndView("bd/bdSendRecordList", "bdSendRecordList", bdSendRecords);
            	}
    		}
        }     
    }
    
    public void buildExcel(HttpServletResponse response, List bdSendRecords,String companyCode,String defCharacterCoding) {
    	//生成excel文件
		try {
			System.out.println("进入自定义导出excel");
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=bdSendRecord.xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			ExcelUtil eu = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			//在此创建的新excel文件创建一工作表 
			WritableSheet wsheet = wwb.createSheet("Sheet1", 0);
			
			//设置列头
			excelTopColumn(eu,wsheet,0,"会员编号,昵称,旧等级,等级,期别,总奖金,应发奖金,发展基金,冻结状态,状态,发放日期,未发因素,未发备注,发放备注");
			
			for(int i=0; i<bdSendRecords.size(); i++) {
				JbdSendRecordHist hist = (JbdSendRecordHist) bdSendRecords.get(i);
				
				String userCode = hist.getJmiMember().getUserCode();
				eu.addString(wsheet, 0, i+1, userCode);							//会员编号
				eu.addString(wsheet, 1, i+1, hist.getPetName());				//昵称
				
				//旧等级
				String oldCardType = ListUtil.getListValue(companyCode, "bd.cardtype", hist.getCardType());
				oldCardType = LocaleUtil.getLocalText(defCharacterCoding, oldCardType);
				eu.addString(wsheet, 2, i+1, oldCardType);				
				
				//等级
				String memberLevel = ListUtil.getListValue(companyCode, "member.level", String.valueOf(hist.getMemberLevel()));
				eu.addString(wsheet, 3, i+1, memberLevel);				
				Integer wweek = WeekFormatUtil.getFormatWeek("w", hist.getWweek());
				eu.addString(wsheet, 4, i+1, String.valueOf(wweek));					//期别
				
				BigDecimal remittanceMoney = hist.getRemittanceMoney()==null ? new BigDecimal(0) : hist.getRemittanceMoney();
				BigDecimal currentDev = hist.getCurrentDev()==null ? new BigDecimal(0) : hist.getCurrentDev();
				
				eu.addString(wsheet, 5, i+1, parseMoney("###,###,##0.00",remittanceMoney.add(currentDev)));	//总奖金
				eu.addString(wsheet, 6, i+1, parseMoney("###,###,##0.00",remittanceMoney));					//应发奖金
				eu.addString(wsheet, 7, i+1, parseMoney("###,###,##0.00",currentDev));						//发展基金
				
				//冻结状态
				String freezeStatus = ListUtil.getListValue(companyCode, "mimember.freezestatus", String.valueOf(hist.getFreezeStatus()));
				freezeStatus = LocaleUtil.getLocalText(defCharacterCoding, freezeStatus);
				eu.addString(wsheet, 8, i+1, freezeStatus);						
				
				//状态
				String registerStatusDesc = "";
				String registerStatus = hist.getRegisterStatus();
				if("1".equals(registerStatus) || "3".equals(registerStatus)) {
					registerStatusDesc = LocaleUtil.getLocalText(defCharacterCoding, "bdSendRecord.unSend");
				} else if("2".equals(registerStatus)) {
					registerStatusDesc = LocaleUtil.getLocalText(defCharacterCoding, "bdSendRecord.sended");
				} else if("4".equals(registerStatus)) {
					registerStatusDesc = LocaleUtil.getLocalText(defCharacterCoding, "busi.bd.notSend");
				}
				eu.addString(wsheet, 9, i+1, registerStatusDesc);							
				
				//发放日期
				Date sendDate = hist.getSendDate();
				if(null == sendDate) {
					eu.addString(wsheet, 10, i+1, "");	
				} else {
					eu.addString(wsheet, 10, i+1, DateUtil.getDate(sendDate,"yyyy-MM-dd HH:mm:ss"));
				}
				
				eu.addString(wsheet, 11, i+1, hist.getSendLateCause());				//未发因素
				eu.addString(wsheet, 12, i+1, hist.getSendLateRemark());			//未发备注
				eu.addString(wsheet, 13, i+1, hist.getSendRemark());				//发放备注
				
			}
			eu.writeExcel(wwb);
			eu.closeWritableWorkbook(wwb);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * @Description excel列头
     * @author houxyu
     * @date 2016-3-28
     * @param eu
     * @param wsheet
     * @param row  列头填充到第几行
     */
    public void excelTopColumn(ExcelUtil eu,WritableSheet wsheet,int row,String columns) throws Exception{
    	String [] cs = null;
		if(!MeteorUtil.isBlank(columns)){
			cs = columns.split(",");
		}
		for(int i=0;i<cs.length;i++){
			//列头  后续改为公共方法。
			eu.addString(wsheet, i, row, cs[i]);//工作表对象，列，行，值
		}
    }
    
    public static String parseMoney(String pattern,BigDecimal bd){
    	DecimalFormat df=new DecimalFormat(pattern);
    	return df.format(bd);
    }
  
}
