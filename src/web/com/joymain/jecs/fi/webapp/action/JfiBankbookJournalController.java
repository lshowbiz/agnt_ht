package com.joymain.jecs.fi.webapp.action;

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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.ListUtil;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.VJbdSendRecord;
import com.joymain.jecs.fi.model.JfiBankbookJournal;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 存折查询
 * @author Alvin
 *
 */
public class JfiBankbookJournalController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiBankbookJournalController.class);
    private JfiBankbookJournalManager jfiBankbookJournalManager = null;

    public void setJfiBankbookJournalManager(JfiBankbookJournalManager jfiBankbookJournalManager) {
        this.jfiBankbookJournalManager = jfiBankbookJournalManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        
        if("C".equals(loginSysUser.getUserType())){
            RequestUtil.freshSession(request,"listFiBankbookJournals",3l);
    	}else if("M".equals(loginSysUser.getUserType())){
            RequestUtil.freshSession(request,"listFiBankbookJournals",3l);
    	}
        
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		crm.addField("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());
		if (!SessionLogin.getLoginUser(request).getIsManager() && !SessionLogin.getLoginUser(request).getIsCompany()) {
			crm.addField("sysUser.userCode", SessionLogin.getLoginUser(request).getUserCode());
			crm.addField("filter","1");
		}
		/** add by hdg 2016-12-23 */
        CustomField[] fields = crm.getFields();
		w:for(CustomField field : fields) {
			String name=field.getName();
			if(!StringUtils.isEmpty(name)) {
				if("enddealdate".equals(name)) {
					String value = (String)field.getValue();
					if(!StringUtils.isEmpty(value)) {
						field.setValue(value+" 23:59:59");
					}
					break w;
				}
			}
		}
		 /** add by hdg 2016-12-23 */
		Pager pager = new Pager("jfiBankbookJournalListTable", request, 20);
		List jfiBankbookJournals = null;
		if (request.getParameter("search") != null || SessionLogin.getLoginUser(request).getIsMember() || SessionLogin.getLoginUser(request).getIsAgent()) {
			if("C".equals(loginSysUser.getUserType())){
				if("xls".equals(request.getParameter("jfiBankbookJournalListTable_ev"))){
	        		if(RequestUtil.saveOperationSession(request,"listFiBankbookJournalsXML",20l)!=0){
	        			return new ModelAndView("redirect:jfiBankbookJournals.html");
	        		}
	    		}else{
	    			if(RequestUtil.saveOperationSession(request,"listFiBankbookJournals",3l)!=0){
	        			return new ModelAndView("redirect:jfiBankbookJournals.html");
	        		}
	    		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"listFiBankbookJournals",3l)!=0){
        			return new ModelAndView("redirect:jfiBankbookJournals.html");
        		}
        	}
			jfiBankbookJournals = this.jfiBankbookJournalManager.getJfiBankbookJournalsByCrm(crm, pager);
		} else if("exportExcel".equals(request.getParameter("exportExcel"))) {
			String companyCode = "";
			if("C".equals(loginSysUser.getUserType())){
	        	companyCode=loginSysUser.getCompanyCode();  
	    		if("AA".equals(loginSysUser.getCompanyCode())){
	    			companyCode=request.getParameter("companyCode");
	    		}
	    	}
			String userCode = request.getParameter("sysUser.userCode");
			String startDealDate = request.getParameter("startDealDate");
			String endDealDate = request.getParameter("endDealDate");
			String dataType = request.getParameter("dataType");
			
			//判断任意一个条件是否有输入，没输入任何一个条件的话就返回空excel
			if(!StringUtils.isEmpty(userCode) || !StringUtils.isEmpty(startDealDate) 
					|| !StringUtils.isEmpty(endDealDate) || !StringUtils.isEmpty(dataType)) {
				
				pager = new Pager("jfiBankbookJournalListTable", request, 0);
				jfiBankbookJournals = this.jfiBankbookJournalManager.getJfiBankbookJournalsByCrm(crm, pager);
				//调用导出方法
	    		buildExcel(response,jfiBankbookJournals,loginSysUser,companyCode);
			} else {
				buildExcel(response,new ArrayList<JfiBankbookJournal>(),loginSysUser,companyCode);
			}
    		return null;
		}
		//根据数据重新设置分页数据
		request.setAttribute("jfiBankbookJournalListTable_totalRows", pager.getTotalObjects());
        return new ModelAndView("fi/jfiBankbookJournalList", Constants.JFIBANKBOOKJOURNAL_LIST, jfiBankbookJournals);
    }

    
	private void buildExcel(HttpServletResponse response,List jfiBankbookJournals, SysUser loginSysUser,String companyCode) {
		
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
			excelTopColumn(eu,wsheet,0,"会员编号,交易日期,摘要,描述,存入,取出,结余,操作者,数据来源");
			
			for(int i=0; i<jfiBankbookJournals.size(); i++) {
				JfiBankbookJournal jf = (JfiBankbookJournal) jfiBankbookJournals.get(i);
				String userCode = jf.getSysUser().getUserCode();
				eu.addString(wsheet, 0, i+1, userCode);							//会员编号
				
				//交易日期
				Date dealDate = jf.getDealDate();								
				if(null == dealDate) {
					eu.addString(wsheet, 1, i+1, "");	
				} else {
					eu.addString(wsheet, 1, i+1, DateUtil.getDate(dealDate,"yyyy-MM-dd"));
				}
				//摘要
				eu.addString(wsheet, 2, i+1, jf.getNotes());	
				//描述
				eu.addString(wsheet, 3, i+1, jf.getDescription());
				
				//存入
				BigDecimal addMoney = jf.getAddMoney()==null ? new BigDecimal(0) : jf.getAddMoney();
				eu.addString(wsheet, 4, i+1, parseMoney("###,###,##0.00",addMoney));	
				
				//取出
				BigDecimal decMoney = jf.getDecMoney()==null ? new BigDecimal(0) : jf.getDecMoney();
				eu.addString(wsheet, 5, i+1, parseMoney("###,###,##0.00",decMoney));	
				
				//结余
				BigDecimal balance = jf.getBalance()==null ? new BigDecimal(0) : jf.getBalance();
				eu.addString(wsheet, 6, i+1, parseMoney("###,###,##0.00",balance));	
				
				//操作者
				eu.addString(wsheet, 7, i+1, jf.getCreaterName());	
				
				//数据来源
				String dataType = ListUtil.getListValue(companyCode, "fibankbook.datatype", jf.getDataType());
				eu.addString(wsheet, 8, i+1, dataType);	
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
