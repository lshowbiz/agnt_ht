package com.joymain.jecs.pd.webapp.action;

import java.io.InputStream;
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

import com.joymain.jecs.pd.model.PdNotChangeProduct;
import com.joymain.jecs.pd.service.PdNotChangeProductManager;
import com.joymain.jecs.pm.model.JpmProduct;
import com.joymain.jecs.pm.service.JpmProductManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.action.FileUpload;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 自助换货-批量上传不可换商品
 * @author fu 2016-09-27 
 *
 */

public class PdExchangeOrderFileUploadController extends BaseFormController{
	private PdNotChangeProductManager pdNotChangeProductManager = null;
	private JpmProductManager jpmProductManager = null;

    public void setPdNotChangeProductManager(PdNotChangeProductManager pdNotChangeProductManager) {
        this.pdNotChangeProductManager = pdNotChangeProductManager;
    }
	
	public void setJpmProductManager(JpmProductManager jpmProductManager) {
		this.jpmProductManager = jpmProductManager;
	}

	/**
	 * 构造函数
	 */
	public PdExchangeOrderFileUploadController() {
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
		log.info(flagnum+"在类PdExchangeOrderFileUploadController的方法processFormSubmission中运行之不可换商品上传:"+request.getParameter("flagnum"));
		
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
		
		log.info("在类PdExchangeOrderFileUploadController的方法onSubmit中运行");
		List ret=new ArrayList();
		//批量上传不可换商品
		ret = this.pdAddPdNotChange(request, response);

		request.setAttribute("errors", ret);
		return new ModelAndView(getSuccessView());
	}

	/**
	 * 批量导入自助换货的不可换商品
	 * @author fu 2016-09-27
	 * @param request
	 * @param response
	 * @return
	 */
	private List pdAddPdNotChange(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			log.info("在类PdExchangeOrderFileUploadController的方法pdAddPdNotChange中运行......"); 
			
			SysUser sysUser = SessionLogin.getLoginUser(request);
			
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
			
			//Modify By WuCF 20140311上传数据量限制
			if(row>=1002){
				ret.add(LocaleUtil.getLocalText("notice.row.number", "一次性上传数据不能超过1000条！"));
				return ret;
			}
			
			int errCount = 0;
			for(int i=1;i<row;i++){
				Cell[] column =sheet1.getRow(i);
				try {
						//判断下取到的值，判断如果为空的，就不用处理，可能exl文件中有空行的行（只是内容为空，但是行存在）
						if(column!=null && column[0].getContents()!=null && !"".equals(column[0].getContents())){
							if("1".equals(flagnum)){
								String returnStr = savePdNotChangeProduct(column,sysUser);
								if(StringUtils.isNotEmpty(returnStr)){ 
									ret.add(LocaleUtil.getLocalText("notice.row.number", "第"+i+"行："+returnStr));
								} 
							}	
						}
					}catch (AppException e) {
						log.info("e="+e.getMessage());
						ret.add(LocaleUtil.getLocalText("notice.row.number", new Object[]{i})+LocaleUtil.getLocalText(e.getMessage()));
					}
					catch (Exception e) {
						log.info("e="+e.getMessage());
						ret.add(LocaleUtil.getLocalText("notice.row.number", new Object[]{i})+LocaleUtil.getLocalText(e.getMessage()));
					}
				}
				
			//处理完后，需要从session中清楚标示符flagnum
			request.getSession().removeAttribute("flagnum");
				
			return ret;
	}

	/**
	 * 保存不可换商品
	 * @author fu 2016-09-27
	 * @param column
	 * @return
	 */
	private String savePdNotChangeProduct(Cell[] column,SysUser sysUser) {
		String returnStr = "";
		String productNo = column[0].getContents();//商品编号
		PdNotChangeProduct pdNotChangeProduct =pdNotChangeProductManager.getpdNotChangeProductByProductNo(productNo);
        if(null!=pdNotChangeProduct){
        	returnStr += "商品编号为"+productNo+"的不可换商品在系统中已经存在！ --此行修改失败！";
        	return returnStr;
        }else{
        	//判断商品编号是否在系统中存在
        	JpmProduct jpmProduct = jpmProductManager.getJpmProduct(productNo);
        	//商品编号在系统中不存在
        	if (jpmProduct == null){
        		returnStr += "商品编号"+productNo+"在系统中不存在，请核对商品编号！ --此行修改失败！";
    			return returnStr;
        	}
        	//商品编号在系统中存在
        	else{
        		PdNotChangeProduct pdNotChangeProductAdd = new PdNotChangeProduct();
        		pdNotChangeProductAdd.setCreateUserCode(sysUser.getUserCode());
        		pdNotChangeProductAdd.setCreateTime(new Date());
        		pdNotChangeProductAdd.setProductNo(productNo);
				pdNotChangeProductManager.savePdNotChangeProduct(pdNotChangeProductAdd);
				return returnStr;
        	}
        }
	}
	
}
