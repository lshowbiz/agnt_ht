package com.joymain.jecs.fi.webapp.action;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.fi.model.FiAvailableInvoice;
import com.joymain.jecs.fi.service.FiAvailableInvoiceManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.ContextUtil;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.action.FileUpload;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 功能描述:合规可用发票导入
 * @author fu 
 * @date:2015-11-29
 */
//PdFileUpload2Controller
public class FiAvailableImportController extends BaseFormController {

	private FiAvailableInvoiceManager fiAvailableInvoiceManager = null;
    private JmiMemberManager jmiMemberManager;
    
    public void setFiAvailableInvoiceManager(FiAvailableInvoiceManager fiAvailableInvoiceManager) {
        this.fiAvailableInvoiceManager = fiAvailableInvoiceManager;
    }
    
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	/**
	 * 构造函数
	 */
	public FiAvailableImportController(){
		setCommandName("fileUpload");
		setCommandClass(FileUpload.class); 
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String characterCoding=((SysUser) ContextUtil.getResource("sessionLogin")).getDefCharacterCoding();
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
		if(RequestUtil.freshSession(request, "updateTrackingNo", 60L)>0){
			return new ModelAndView("redirect:fiAvailableInvoiceImport.html");
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
		if(RequestUtil.saveOperationSession(request, "updateTrackingNo", 60L)>0){
			return new ModelAndView("redirect:fiAvailableInvoiceImport.html");
		}
		List ret=new ArrayList();
		log.info("upload...");
    	ret = this.initSetFiAvailableInvoice(request, response);

		request.setAttribute("errors", ret);
		return new ModelAndView(getSuccessView());
	}
	
	/**
	 * 批量导入可用发票的数据  fu 2015-11-29
	 * @param request
	 * @param response
	 * @return list
	 * @throws Exception
	 */
	private List initSetFiAvailableInvoice(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("initSetFiAvailableInvoice file......................."); 
		
		String flagnum = String.valueOf(request.getAttribute("flagnum"));
		log.info("###############flagnum:"+flagnum);
		List ret=new ArrayList();
		//获得文件对象，然后获得文件字节流
		MultipartHttpServletRequest multipartRequest =
			(MultipartHttpServletRequest) request;
		CommonsMultipartFile file =
			(CommonsMultipartFile) multipartRequest.getFile("file");

		InputStream stream = file.getInputStream();

		 
		//开始处理表单EXL中的数据
		Workbook wb = Workbook.getWorkbook(stream);
		Sheet sheet1 = wb.getSheet(0);

		Cell cell = null;
		int row = sheet1.getRows();//总行数
		int col = sheet1.getColumns();//总列数
		
		//上传数据量限制
		if(row>=1002){
			ret.add(LocaleUtil.getLocalText("notice.row.number", "一次性上传数据不能超过1000条！"));
			return ret;
		}
		
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		for(int i=1;i<row;i++){
			Cell[] column =sheet1.getRow(i);
			try {
				//判断下取到的值，判断如果为空的，就不用处理，可能exl文件中有空行的行（只是内容为空，但是行存在）
				if(column!=null && ((column[0].getContents()!=null && !"".equals(column[0].getContents()))
						||( column[1].getContents()!=null && !"".equals(column[1].getContents())))){
						String returnStr = initSaveFiAvailableInvoice(column);
						if(StringUtils.isNotEmpty(returnStr)){ 
							ret.add(LocaleUtil.getLocalText("notice.row.number", "第"+i+"行："+returnStr));
					}
				}
			} catch (AppException e) {
				log.info("e="+e.getMessage());
				ret.add(LocaleUtil.getLocalText("notice.row.number", new Object[]{i})+LocaleUtil.getLocalText(e.getMessage()));
			}
			catch (Exception e) {
				log.info("e="+e.getMessage());
				ret.add(LocaleUtil.getLocalText("notice.row.number", new Object[]{i})+LocaleUtil.getLocalText(e.getMessage()));
			}

		} 
			
		return ret;
	}
	
	
	/**
	 * 合规可用发票导入 fu 2015-11-29
	 * @param column
	 * @param sessionLogin
	 * @throws Exception
	 */
	private String initSaveFiAvailableInvoice(Cell[] column){
		String returnStr = "";
		String userCode = column[0].getContents();//会员号
		String userName = column[1].getContents();//姓名
		String invoiceValue = column[2].getContents();//可用发票金额
		String bond = column[3].getContents();//应退保证金
		String jyear = column[4].getContents();//发票所属奖金结算年度
		String jmonth = column[5].getContents();//发票所属奖金结算结算月
		String ownedCompany = column[6].getContents();//发票所属公司
		String remark = "";//备注
		if(column.length>=8){//如果备注列不加数据，则不用读取第8列（column[7]），否则数组越界
			remark = column[7].getContents();
		}
		
		if(StringUtil.isEmpty(userCode)){
			returnStr += "第一列：会员号不存在:"+userCode+"； --此行修改失败！";
			return returnStr;
		}
		if(StringUtil.isEmpty(userName)){
			returnStr += "第二列：姓名为空:"+userName+"； --此行修改失败！";
			return returnStr;
		}
		if(StringUtil.isEmpty(invoiceValue)){
			returnStr += "第三列：可用发票金额为空:"+invoiceValue+"； --此行修改失败！";
			return returnStr;
		}else{
			//判断是否是数字
			boolean isNum = this.isNum(invoiceValue);
			if(!isNum){
				returnStr += "第三列：可用发票金额必须为数字:"+invoiceValue+"； --此行修改失败！";
				return returnStr;
			}
		}
		
		if(StringUtil.isEmpty(bond)){
			returnStr += "第四列：应退保证金为空:"+bond+"； --此行修改失败！";
			return returnStr;
		}else{
			//判断是否是数字
			boolean isNum = this.isNum(bond);
			if(!isNum){
				returnStr += "第四列：应退保证金必须为数字:"+bond+"； --此行修改失败！";
				return returnStr;
			}
		}
		
		if(StringUtil.isEmpty(jyear)){
			returnStr += "第五列：发票所属奖金结算年度为空:"+jyear+"； --此行修改失败！";
			return returnStr;
		}else{
			//判断是否是数字
			boolean isNum = this.isNum(jyear);
			if(!isNum){
				returnStr += "第五列：发票所属奖金结算年度必须为数字:"+jyear+"； --此行修改失败！";
				return returnStr;
			}
		}
		if(StringUtil.isEmpty(jmonth)){
			returnStr += "第六列：发票所属奖金结算结算月为空:"+jmonth+"； --此行修改失败！";
			return returnStr;
		}else{
			//判断是否是数字
			boolean isNum = this.isNum(jmonth);
			if(!isNum){
				returnStr += "第六列：发票所属奖金结算结算月必须为数字:"+jmonth+"； --此行修改失败！";
				return returnStr;
			}
		}
		
		if(StringUtil.isEmpty(ownedCompany)){
			returnStr += "第七列：发票所属公司为空:"+ownedCompany+"； --此行修改失败！";
			return returnStr;
		}
		
		//检测期别是否存在
		String qb = WeekFormatUtil.getFormatWeek("f", jmonth);
		if(StringUtil.isEmpty(qb)){
			returnStr += "第七列： 发票所属奖金结算月(期别）录入不对:"+jmonth+"； --此行修改失败！";
			return returnStr;
		}
		
		JmiMember jmiMember = jmiMemberManager.getJmiMember(userCode);
		if(null==jmiMember){
			returnStr += "第一列：会员号在系统中不存在:"+userCode.trim()+"； --此行修改失败！";
			return returnStr;
		}else{
			FiAvailableInvoice fiAvailableInvoice = new FiAvailableInvoice();
			fiAvailableInvoice.setUserCode(userCode.trim());
			fiAvailableInvoice.setUserName(userName.trim());
			fiAvailableInvoice.setInvoiceValue(new BigDecimal(invoiceValue));
			fiAvailableInvoice.setBond(new BigDecimal(bond));
			fiAvailableInvoice.setJyear(Integer.parseInt(jyear));
			fiAvailableInvoice.setJmonth(Integer.parseInt(qb));//存入数据库的是工作周(例如:201510),在页面显示的是财政周
			fiAvailableInvoice.setOwnedCompany(ownedCompany);
			fiAvailableInvoice.setCreateTime(new Date());
			fiAvailableInvoice.setLogicalDelete("N");
			fiAvailableInvoice.setStatus(0);

			//备注
			if(!StringUtil.isEmpty(remark)){
				fiAvailableInvoice.setRemark(remark);
			}
			fiAvailableInvoiceManager.saveFiAvailableInvoice(fiAvailableInvoice);
		}
		
		return returnStr;
	}	 

	
	/**
	 * 判断指定字符串是否为空  
	 * @param str
	 * @return：false
	 * D:\workspace\joymain\02 源代码\jecs\src\web\com\joymain\jecs\pd\webapp\action\PdFileUpload2Controller.java:221: 找不到符号
    [javac] 符号： 变量 StringUtils
	 */
	public static boolean isBlank(String str){
		boolean bool = true;
		if(str!=null && !"".equals(str.trim().replace(" ", ""))){
			bool = false;
		}	
		return bool;
	}
	
	/**
	 * 判断是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
}
