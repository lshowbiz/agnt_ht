package com.joymain.jecs.po.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.InputStream;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.excelutils.ExcelUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.action.FileUpload;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;



/**
 * 订单的批量查询和批量校验
 * @author fu 2016-03-11
 *
 */
//参考PdSendInfoReportController类和PdFileUpload2Controller类
public class JpoMemberBatchCheckController extends BaseFormController{
    private final Log log = LogFactory.getLog(JpoAutoShipController.class);
    private JpoMemberOrderManager jpoMemberOrderManager;
    private BdPeriodManager bdPeriodManager;
	
	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

    public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
    
    /**
	 * 构造函数
	 */
	public JpoMemberBatchCheckController() {
		setCommandName("fileUpload");
		setCommandClass(FileUpload.class); 
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String flagnum = request.getParameter("flagnum");
		request.setAttribute("flagnum", flagnum);
		 
		return super.formBackingObject(request);
	}
	
	/**
	 * 进入到上传页面的函数
	 * 
	 */
	public ModelAndView processFormSubmission(HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors)
	throws Exception {
		String flagnum = String.valueOf(request.getAttribute("flagnum"));
		log.info(flagnum+"!!!!!!!!!!!!!!!!!!!!requestFlagnum:"+request.getParameter("flagnum"));
		if (request.getParameter("cancel") != null) {
			return new ModelAndView(getCancelView());
		}
		return super.processFormSubmission(request, response, command, errors);
	}

	/**
	 * 提交函数
	 */
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command,
			BindException errors)
	throws Exception {
	        if (log.isDebugEnabled()) {
	            log.debug("entering 'handleRequest' method...");
	        }
	        SysUser loginSysUser = SessionLogin.getLoginUser(request);
	
	        CommonRecord crm=RequestUtil.toCommonRecord(request);
	        crm.addField("companyCode", loginSysUser.getCompanyCode());

        	String flagnum = request.getParameter("flagnum");
    		request.setAttribute("flagnum", flagnum);
    		List list = new ArrayList();
    		//获得文件对象，然后获得文件字节流
    		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");
    		InputStream stream = file.getInputStream();
    		 
    		//开始处理表单EXL中的数据
    		Workbook wb = Workbook.getWorkbook(stream);
    		Sheet sheet1 = wb.getSheet(0);

    		Cell cell = null;
    		int row = sheet1.getRows();//总行数
    		int col = sheet1.getColumns();//总列数
    		String config = "";
    		//批量查询订单-根据订单编号获取会员编号、订单期别
    		if("1".equals(flagnum)){
    			config = "/WEB-INF/xls/jpoMemberOrderBatchQuery.xls";
    			
    			list = this.batchhandleJpoMemberOrder(row,sheet1);
    			request.getSession().removeAttribute("flagnum");
    			
    			ExcelUtils.addValue("list", list);
    			response.reset();

    			response.setContentType("application/vnd.ms-excel");
    			response.setHeader("Content-Disposition", "attachment;filename=jpoMemberOrderBatchQuery.xls");
    			ExcelUtils.export(request.getSession().getServletContext(), config,response.getOutputStream());
    			return null;

    		}
    		//将导入的订单信息和数据库中的订单信息做比较，导出比较结果中不一致的数据
    		else if("2".equals(flagnum)){
    			config = "/WEB-INF/xls/jpoMemberOrderBatchQueryCompare.xls";
    			list = this.batchhandleJpoMemberOrderCompare(row,sheet1);
    			request.getSession().removeAttribute("flagnum");
    			
    			ExcelUtils.addValue("list", list);
    			response.reset();

    			response.setContentType("application/vnd.ms-excel");
    			response.setHeader("Content-Disposition", "attachment;filename=jpoMemberOrderBatchQueryCompare.xls");
    			ExcelUtils.export(request.getSession().getServletContext(), config,response.getOutputStream());
    			return null;

    		}else{
    			return null;
    		}
    }
    
    /**
	 * 批量查询订单-根据订单编号获取会员编号、订单期别
	 * @author modifu fu 2016-3-11 
	 * @param row
	 * @param sheet1
	 * @throws 
	 */
	private List batchhandleJpoMemberOrder(int row,Sheet sheet1) throws Exception {
		log.info("在类JpoMemberBatchCheckController的方法batchhandleJpoMemberOrder中运行"); 
		List resultList = new ArrayList();
		
		//查询数据量限制
		if(row>=1002){
			 Map retMap = new HashMap();
	         retMap.put("failReason","一次性查询数据不能超过1000条！");
	         resultList.add(retMap);
	 		 return resultList;
		}
		
		for(int i=1;i<row;i++){
			Cell[] column =sheet1.getRow(i);
			try {
				//判断下取到的值，判断如果为空的，就不用处理，可能exl文件中有空行的行（只是内容为空，但是行存在）
				if(column!=null && column[0].getContents()!=null && !"".equals(column[0].getContents())){
					    String memberOrderNo = column[0].getContents();//订单号		
			            JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrderByInterface(memberOrderNo);
			            Map retMap = new HashMap();
						String failReason = "";//导入数据错误备注
			            //有效订单号
			            if(null!=jpoMemberOrder){ 
							  retMap.put("memberOrderNo",jpoMemberOrder.getMemberOrderNo());
							  retMap.put("userCode",jpoMemberOrder.getSysUser().getUserCode());
							  Date cDate = jpoMemberOrder.getCheckDate();
							  if(null==cDate){
								  failReason += "系统中订单期别为空--";
							  }else{
								  //String checkDate = (bdPeriodManager.getBdPeriodByTimeFormated(jpoMemberOrder.getCheckDate(),1L)).toString();
								  	BdPeriod  bdPeriod =bdPeriodManager.getBdPeriodByTime(jpoMemberOrder.getCheckDate(),1L);
			            		  	if(bdPeriod != null){
			            		  		String fyear = String.valueOf(bdPeriod.getFyear());
			            		  		String fmouth = String.valueOf(bdPeriod.getFmonth());
			            		  		String fweek = String.valueOf(bdPeriod.getFweek());
			            		  		StringBuffer checkDate = new StringBuffer();
			            		  		checkDate.append(fyear)
			            			  				.append(fmouth.length()<2?("0"+fmouth):fmouth)
			            			  				.append(fweek.length()<2?("0"+fweek):fweek);
			            			  
			            		  		if(StringUtil.isEmpty(checkDate.toString())){
			            		  			failReason += "系统中订单期别为空--";
			            		  		}else{
			            		  			retMap.put("checkDate",checkDate.toString());
			            		  		}
			            		  	}else{
			            		  		failReason += "系统中订单期别为空--";
			            		  	}
									  
							  }
						}
			            //无效订单号
			            else{
							  retMap.put("memberOrderNo",memberOrderNo);
							  failReason += "订单号在系统中不存在!";
						}
			            retMap.put("failReason",failReason);
			            resultList.add(retMap);
				}					
			}catch (Exception e) {
				log.info("在类JpoMemberBatchCheckController的方法batchhandleJpoMemberOrder中运行异常:"+e.getMessage());
				e.printStackTrace();
			}
		}
		return resultList;
	}
	
	/**
	 * 将导入的订单信息和数据库中的订单信息做比较，导出比较结果中不一致的数据
	 * @author fu 2016-3-11 
	 * @param row
	 * @param sheet1
	 * @throws 
	 */
	private List batchhandleJpoMemberOrderCompare(int row,Sheet sheet1) throws Exception {
		log.info("在类JpoMemberBatchCheckController的方法batchhandleJpoMemberOrderCompare中运行"); 
		List resultList = new ArrayList();
		//校验数据量限制
		if(row>=1002){
			 Map retMap = new HashMap();
	         retMap.put("failReason","一次性校验数据不能超过1000条！");
	         resultList.add(retMap);
	 		 return resultList;
		}
		for(int i=1;i<row;i++){
			Cell[] column =sheet1.getRow(i);
			try {
				//判断下取到的值，判断如果为空的，就不用处理，可能exl文件中有空行的行（只是内容为空，但是行存在）
				if(column!=null && column[0].getContents()!=null && !"".equals(column[0].getContents())){
					    String memberOrderNo = column[0].getContents();//订单号
					    String userCodeF = "";//会员编号
						String nameF = "";//会员姓名
						String amountF  = "";//订单金额
						String pvAmtF  = "";//订单PV
						String checkDateF  = "";//订单期别
						String failReason = "";//导入数据错误备注
						
						if(column.length==2){//预防数组越界
							userCodeF = column[1].getContents();//会员编号
						}
						if(column.length==3){//预防数组越界
							userCodeF = column[1].getContents();//会员编号
							nameF = column[2].getContents();//会员姓名
						}
						
						if(column.length==4){//预防数组越界
							userCodeF = column[1].getContents();//会员编号
							nameF = column[2].getContents();//会员姓名
							amountF = column[3].getContents();
						}
						
						if(column.length==5){//预防数组越界
							userCodeF = column[1].getContents();//会员编号
							nameF = column[2].getContents();//会员姓名
							amountF = column[3].getContents();
							pvAmtF = column[4].getContents();
						}
						
						if(column.length>=6){//预防数组越界
							userCodeF = column[1].getContents();//会员编号
							nameF = column[2].getContents();//会员姓名
							amountF = column[3].getContents();
							pvAmtF = column[4].getContents();
							checkDateF = column[5].getContents();
						}
						
			            JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrderByInterface(memberOrderNo);
			            
			            Map retMap = new HashMap();
						retMap.put("memberOrderNo",memberOrderNo);
						
			            //有效订单号
			            if(null!=jpoMemberOrder){ 
			            	  //比较会员编号,如果比较结果不一致，那么将数据库中的值导出
			            	  String userCode = jpoMemberOrder.getSysUser().getUserCode();
			            	  if(StringUtil.isEmpty(userCodeF)){
			            		  failReason += "会员编号没填--";
			            	  }else if((!StringUtil.isEmpty(userCodeF))&&(!userCodeF.equals(userCode))){
								  retMap.put("userCode",userCode);
			            	  }
			            	  
			            	  //比较会员姓名,如果比较结果不一致，那么将数据库中的值导出
			            	  //String userName = jpoMemberOrder.getFirstName()+jpoMemberOrder.getLastName();
			            	  //String userName = jpoMemberOrder.getSysUser().getUserName();
			            	  SysUser sysUser = jpoMemberOrder.getSysUser();
			            	  if(sysUser == null){
			            		  failReason += "会员信息没填--";
			            	  }
			            	  String userName = sysUser.getUserName();
			            	  
			            	  if(StringUtil.isEmpty(nameF)){
			            		  failReason += "会员姓名没填--";
			            	  }else if((!StringUtil.isEmpty(nameF))&&(!nameF.equals(userName))){
								  retMap.put("userName",userName);
			            	  }
			            	  
			            	  //比较订单金额,如果比较结果不一致，那么将数据库中的值导出
			            	  if(!StringUtil.isEmpty(amountF)){
			            		  BigDecimal amount = jpoMemberOrder.getAmount();
			            		  if(!amountF.equals(amount.toString())){
									  retMap.put("amount",amount);
			            		  }
			            	  }
			            	
			            	  //比较订单PV,如果比较结果不一致，那么将数据库中的值导出
			            	  if(!StringUtil.isEmpty(pvAmtF)){
			            		  BigDecimal pvAmt = jpoMemberOrder.getPvAmt();
			            		  if(!pvAmtF.equals(pvAmt.toString())){
									  retMap.put("pvAmt",pvAmt.toString());
			            		  }
			            	  }
			            	 
			            	  //比较订单期别,如果比较结果不一致，那么将数据库中的值导出
			            	  if(!StringUtil.isEmpty(checkDateF)){
								  Date cDate = jpoMemberOrder.getCheckDate();
			            		  if(null==cDate){
									  failReason += "系统中订单期别为空--";
								  }else{
				            		  //String checkDate = (bdPeriodManager.getBdPeriodByTimeFormated(jpoMemberOrder.getCheckDate(),1L)).toString();
				            		  BdPeriod  bdPeriod =bdPeriodManager.getBdPeriodByTime(jpoMemberOrder.getCheckDate(),1L);
				            		  if(bdPeriod != null){
				            			  String fyear = String.valueOf(bdPeriod.getFyear());
				            			  String fmouth = String.valueOf(bdPeriod.getFmonth());
				            			  String fweek = String.valueOf(bdPeriod.getFweek());
				            			  StringBuffer checkDate = new StringBuffer();
				            			  checkDate.append(fyear)
				            			  				.append(fmouth.length()<2?("0"+fmouth):fmouth)
				            			  				.append(fweek.length()<2?("0"+fweek):fweek);
				            			  
				            			  if(StringUtil.isEmpty(checkDate.toString())){
					            			  failReason += "系统中订单期别为空--";
					            		  }else if(!checkDateF.equals(checkDate.toString())){
											  retMap.put("checkDate",checkDate.toString());
					            		  }
				            		  }else{
				            			  failReason += "系统中订单期别为空--";
				            		  }
				            		  
								  }
			            	  }
						}
			            //无效订单号
			            else{
			            	failReason += "订单号在系统中不存在!";
						}
						retMap.put("failReason",failReason);
			            resultList.add(retMap);
				}					
			}catch (Exception e) {
				log.info("在类JpoMemberBatchCheckController的方法batchhandleJpoMemberOrderCompare中运行异常:"+e.getMessage()); 
				e.printStackTrace();
			}
		}
		return resultList;
	}
	
	
}
