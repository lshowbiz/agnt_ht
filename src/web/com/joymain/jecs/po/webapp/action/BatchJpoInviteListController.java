package com.joymain.jecs.po.webapp.action;

import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.po.model.JpoInviteList;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoInviteListManager;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.action.FileUpload;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;



public class BatchJpoInviteListController extends BaseFormController {

	private JmiMemberManager jmiMemberManager;
	private JpoMemberOrderManager jpoMemberOrderManager;
	private JpoInviteListManager jpoInviteListManager;
	
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	@Override
	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}
	
	public void setJpoInviteListManager(JpoInviteListManager jpoInviteListManager) {
		this.jpoInviteListManager = jpoInviteListManager;
	}

	public BatchJpoInviteListController() {
	        setCommandName("fileUpload");
	        setCommandClass(FileUpload.class);
	    }
	    
    @Override
	public ModelAndView processFormSubmission(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Object command,
                                              BindException errors)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return new ModelAndView(getCancelView());
        }
        if(RequestUtil.freshSession(request, "updateTrackingNo", 60L)>0){
        	return new ModelAndView("redirect:pdFileUpload.html?strAction=updateTrackingNo");
        }

        return super.processFormSubmission(request, response, command, errors);
    }

    @SuppressWarnings("rawtypes")
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
    	/*if(RequestUtil.saveOperationSession(request, "updateTrackingNo", 60L)>0){
        	return new ModelAndView("redirect:pdFileUpload.html?strAction=updateTrackingNo");
		}
    	*/
    	List ret=new ArrayList();
       
		log.info("在类BatchJpoInviteListController的方法onSubmit中开始运行");
        ret = this.updateBatchJmiValidLevelList(request, response);
        request.setAttribute("errors", ret);
		log.info("在类BatchJpoInviteListController的方法onSubmit中运行结束");

        return new ModelAndView(getSuccessView());
    }

	@SuppressWarnings("rawtypes")
	private List updateBatchJmiValidLevelList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("BatchJmiValidLevelList file.......................");
		
		List ret=new ArrayList();
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		MultipartHttpServletRequest multipartRequest =
            (MultipartHttpServletRequest) request;
        CommonsMultipartFile file =
            (CommonsMultipartFile) multipartRequest.getFile("file");
        
        InputStream stream = file.getInputStream();
        String confirmFlag = ConfigUtil.getConfigValue(sessionLogin.getCompanyCode(), "mi.modify.level"); 
        
        DateFormat df = DateFormat.getDateInstance();
        
        Workbook wb = Workbook.getWorkbook(stream);
        Sheet sheet1 = wb.getSheet(0); 
        
        Cell cell = null;
        int row = sheet1.getRows();//总行数
        log.info("BatchJmiValidLevelList_row="+row);
        
        Integer listNum = Integer.parseInt(ConfigUtil.getConfigValue(sessionLogin.getCompanyCode(), "modifyjpoinvitelist.maxnum"));
        
        if(row>=listNum+2){
        	ret.add(LocaleUtil.getLocalText("notice.row.number", "一次性上传数据不能超过"+listNum+"条！"));
        	return ret;
        }
        
        int col = sheet1.getColumns();//总列数
        
        String returnStr = "";
        	for(int i=1;i<row;i++){
        	       Cell[] column =sheet1.getRow(i);
        	   
	        	   if(column!=null && column[0].getContents()!=null && !"".equals(column[0].getContents())){
			        	
			        	try {
			    			log.info("在类BatchJpoInviteListController的方法updateBatchJmiValidLevelList中运行");
			        		returnStr = modifyBatchJpoInviteList(column,sessionLogin,request);
			        		if(StringUtils.isNotEmpty(returnStr)){ 
			        			int p = i+1;
								ret.add(LocaleUtil.getLocalText("UTF-8", "第"+p+"行"+column[0]+"："+returnStr));
							} 
						} catch (AppException e) {
							log.info("e="+e.getMessage());
							ret.add(LocaleUtil.getLocalText("UTF-8", new Object[]{i})+LocaleUtil.getLocalText(e.getMessage()));
						}
			        	catch (Exception e) {
							log.info("e="+e.getMessage());
							ret.add(LocaleUtil.getLocalText("UTF-8", new Object[]{i})+LocaleUtil.getLocalText(e.getMessage()));
						}
	        	}
	        }
        return ret;
	}
	
	
	/**
	 * 批量修改邀请码---校验导入数据的合法性
	 * @author 2017-5-22 fu
	 * @param column
	 * @param sessionLogin
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private String modifyBatchJpoInviteList(Cell[] column,SysUser sessionLogin,HttpServletRequest request) throws Exception{
		//新增的时候，邀请码和订单有关联，删除（作废)的时候，和订单编号不关联起来；
		log.info("在类BatchJpoInviteListController的方法modifyBatchJpoInviteList中运行----开始");
		String returnStr = "";
		String userCode = column[0].getContents();//会员编号
		String qty = "";
		if(column.length>=2){
			qty = column[1].getContents();//数量
		}
		String memberOrderNo = "";
		if(column.length>=3){
			memberOrderNo = column[2].getContents();//订单编号
		}
		
    	SysUser defSysUser=SessionLogin.getLoginUser(request);
		JmiMember jmiMember = null;
		boolean fsbz = false;//数字qty是否是负数的标识
		int num = 0;
		
		if(isBlank(userCode)){
			returnStr += "第一列：会员编号为空------此行邀请码操作失败！";
			return returnStr;
		}else{
			jmiMember=jmiMemberManager.getJmiMember(userCode);
	    	if(null==jmiMember){
	    		returnStr += "第一列：会员编号为"+userCode+"的会员在系统中不存在------此行邀请码操作失败！";
				return returnStr;
	    	}
		}
		
		if(isBlank(qty)){
			returnStr += "第二列：数量为空------此行邀请码操作失败！";
			return returnStr;
		}else{
			String fh = qty.substring(0, 1);
			if(isBlank(fh)){
				returnStr += "第二列：数量为空------此行邀请码操作失败！";
				return returnStr;
			}else{
				if("-".equals(fh)|| "一".equals(fh)){
					fsbz= true;//数字是负数；
				}
				try{
				   num = Integer.parseInt(qty);
				}catch(NumberFormatException e){
					log.info("在类BatchJpoInviteListController的方法modifyBatchJpoInviteList中运行异常："+e.toString());
					e.printStackTrace();
					returnStr += "第二列：填写的"+qty+"为非数字-----此行邀请码操作失败！";
					return returnStr;
				}
				
			}
		}
		
		//如果qty是负数，那么不校验订单编号
		//if(!fsbz){
			//getJpoMemberOrderByMemberOrderNo
			JpoMemberOrder jpoMemberOrder = null;
			if(isBlank(memberOrderNo)){
				//returnStr += "第三列：订单编号为空------此行邀请码操作失败！";
				//return returnStr;
			}else{//如果订单编号填写了，那么校验订单编号的有效性
				jpoMemberOrder=jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(memberOrderNo);
				if(null==jpoMemberOrder){
		    		returnStr += "第三列：订单编号为"+userCode+"的订单在系统中不存在------此行邀请码操作失败！";
					return returnStr;
		    	}
			}
		//}
		
		log.info("在类BatchJpoInviteListController的方法modifyBatchJpoInviteList中运行----数据合法性校验结束");

		//对上传的邀请码的数据进行处理
		returnStr += this.multipleModificationsJpoInviteList(userCode,memberOrderNo,num,fsbz,defSysUser);
	

		return returnStr;
		
	}
	 
	/**
	 * 批量修改邀请码---添加或修改邀请码
	 * @author 2017-5-22 fu
	 * @param userCode
	 * @param memberOrderNo
	 * @param num
	 * @param fsbz
	 */
	private String  multipleModificationsJpoInviteList(String userCode,
			String memberOrderNo, int num, boolean fsbz,SysUser defSysUser) {
		log.info("在类BatchJpoInviteListController的方法multipleModificationsJpoInviteList中运行;");
		String returnStr = "";
		//如果excel导入的数字是负数，那么就是将部分邀请码设置为不可用
		if(fsbz){
			int numc = Math.abs(num);//取负数num的绝对值，使其变成正数
			//查询会员可用的邀请码的总数
			int numDBA = jpoInviteListManager.getAvailableSum(userCode);
			if(numc>numDBA){
				returnStr += "会员"+userCode+"可使用的邀请码"+numDBA+"少于此次要删除的邀请码个数"+numc+"---此行修改失败";
		    	return returnStr;
			}
			boolean rt = jpoInviteListManager.maintainJpoInviteList(userCode, numc, memberOrderNo,defSysUser.getUserCode());
			if(!rt){
				returnStr += "批量删除邀请码失败---此行修改失败";
		    	return returnStr;
			}
		}
		//如果excel导入的数字是正数，那么就是添加邀请码
		else{
			for(int i=0;i<num;i++){
			    JpoInviteList jpoInviteList = new JpoInviteList();
			    jpoInviteList.setUserCode(userCode);
			    //获取邀请码
			    String inviteCode = jpoInviteListManager.getJpoInviteListForProcedure();
			    if(isBlank(inviteCode)){
			    	returnStr += "从存储过程获取的邀请码为空,i为"+i+"---此行修改失败";
			    	return returnStr;
			    }
			    jpoInviteList.setInviteCode(inviteCode);
			    jpoInviteList.setMemberOrderNo(memberOrderNo);
			    jpoInviteList.setCreateTime(new Date());
			    jpoInviteList.setStatus("0");// 0.未使用 1.已使用 2作废 
			    jpoInviteList.setVersion(new Long(0));
			    jpoInviteList.setCreateUserCode(defSysUser.getUserCode());
			    jpoInviteListManager.saveJpoInviteList(jpoInviteList);
			}
		}
		
		return returnStr;
	}

	/**
	 * 判断是否包含字母，数字
	 * @param number
	 * @return 
	 */
	public String isNumberOrWord(String str){
	     Pattern pattern =Pattern.compile("[a-zA-Z]|\\d");
	     Matcher matcher = pattern.matcher(str);
	     while (matcher.find()) {
	    	 // 如匹配成功即走到这里
	    	 return matcher.group();
	     }
	     return "";
	}
	
	/**
	 * 判断指定字符串是否为空  
	 * @param str
	 * @return：false
	 */
	public static boolean isBlank(String str){
		if (str == null) {
			return true;
		} else {
			if (str.equals("")) {
				return true;
			}
		}
		return false;
	}
		
}
