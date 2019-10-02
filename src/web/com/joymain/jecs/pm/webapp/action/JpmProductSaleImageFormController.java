package com.joymain.jecs.pm.webapp.action;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import service.MsgSendService;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pd.service.PdSendInfoManager;
import com.joymain.jecs.pm.model.JpmProduct;
import com.joymain.jecs.pm.model.JpmProductSaleImage;
import com.joymain.jecs.pm.service.JpmProductManager;
import com.joymain.jecs.pm.service.JpmProductSaleImageManager;
import com.joymain.jecs.pm.service.JpmProductSaleNewManager;
import com.joymain.jecs.sys.model.JocsInterfaceLog;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.ReportLogUtilService;
import com.joymain.jecs.util.StringUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.image.ImageTool;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JpmProductSaleImageFormController extends BaseFormController {
	private JpmProductSaleImageManager jpmProductSaleImageManager = null;
	private PdSendInfoManager pdSendInfoManager = null;

	private JpmProductSaleNewManager jpmProductSaleNewManager = null;
	private JpmProductManager jpmProductManager = null;

	public void setJpmProductManager(JpmProductManager jpmProductManager) {
		this.jpmProductManager = jpmProductManager;
	}
	
	public void setJpmProductSaleNewManager(JpmProductSaleNewManager jpmProductSaleNewManager) {
		this.jpmProductSaleNewManager = jpmProductSaleNewManager;
	}
	
//	final String FILE_URL = "D:\\jecsImage\\productNew\\";
	public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}

	
	public void setJpmProductSaleImageManager(JpmProductSaleImageManager jpmProductSaleImageManager) {
		this.jpmProductSaleImageManager = jpmProductSaleImageManager;
	}
	public JpmProductSaleImageFormController() {
		setCommandName("jpmProductSaleImage");
		setCommandClass(JpmProductSaleImage.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
	throws Exception {
		String imageId = request.getParameter("imageId");
		JpmProductSaleImage jpmProductSaleImage = null;

		if (!StringUtils.isEmpty(imageId)) {
			jpmProductSaleImage = jpmProductSaleImageManager.getJpmProductSaleImage(imageId);
		} else {
			jpmProductSaleImage = new JpmProductSaleImage();
		}
		SysUser loginSysUser = SessionLogin.getLoginUser(request);
		/*
		LinkedHashMap<String,String> map = ListUtil.getListOptions(loginSysUser.getCompanyCode().toUpperCase(), "jpmproductsaleimage.proportion");
		String[] wh = new String[2];
		if("1".equals(jpmProductSaleImage.getImageType())){
			wh[0] = map.get("1").split(":")[0];
			wh[1] = map.get("1").split(":")[1];
		}else if("2".equals(jpmProductSaleImage.getImageType())){
			wh[0] = map.get("2").split(":")[0];
			wh[1] = map.get("2").split(":")[1];
		}else if("3".equals(jpmProductSaleImage.getImageType())){
			wh[0] = map.get("3").split(":")[0];
			wh[1] = map.get("3").split(":")[1];
		}
		request.setAttribute("wh", wh);*/
		String FILE_URL = ListUtil.getListValue(loginSysUser.getCompanyCode().toUpperCase(), "jpmproductsaleimage.url", "1")+"productNew/";
		request.setAttribute("FILE_URL", FILE_URL);
		return jpmProductSaleImage;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command,
			BindException errors)
	throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
 
		JpmProductSaleImage jpmProductSaleImage = (JpmProductSaleImage) command;
		boolean isNew = (jpmProductSaleImage.getImageId() == null);
		Locale locale = request.getLocale();
		String key=null; 
		String strAction = request.getParameter("strAction");
		//当前用户
		SysUser loginSysUser = SessionLogin.getLoginUser(request);
		String filePath = ConfigUtil.getConfigValue(loginSysUser.getCompanyCode().toUpperCase(), "jpmproductsaleimage.path")+System.getProperty("file.separator");
		File pfile = new File(filePath);
		//如果图片物理路径不存在，则先创建 
		if(!pfile.exists()){
			pfile.mkdirs();
		} 
		
		//获得图片类型的比例
		LinkedHashMap<String,String> map = ListUtil.getListOptions(loginSysUser.getCompanyCode().toUpperCase(), "jpmproductsaleimage.proportion");
		String[] proportion1 = new String[2];
		String[] proportion2 = new String[2];
		String[] proportion3 = new String[2];
		String str = map.get("1");
		proportion1 = str.split(":");
		if(StringUtils.isEmpty(proportion1[0])){
			proportion1[0] = "1";
		}
		if(StringUtils.isEmpty(proportion1[1])){
			proportion1[1] = "1";
		}
		
		str = map.get("2");
		proportion2 = str.split(":");
		if(StringUtils.isEmpty(proportion2[0])){
			proportion2[0] = "1";
		}
		if(StringUtils.isEmpty(proportion2[1])){
			proportion2[1] = "1";
		}
		
		str = map.get("3");
		proportion3 = str.split(":");
		if(StringUtils.isEmpty(proportion3[0])){
			proportion3[0] = "1";
		}
		if(StringUtils.isEmpty(proportion3[1])){
			proportion3[1] = "1";
		}
		
		//==========================Modify By WuCF 20160504 合规第二期代码迁移接口
		//获取接口开关标示
		String swtichSend = ListUtil.getListValue(loginSysUser.getCompanyCode().toUpperCase(), "interface.sendswitch", "goods.add");
		
		if ("deleteJpmProductSaleImage".equals(strAction)  ) {
			jpmProductSaleImageManager.removeJpmProductSaleImage(jpmProductSaleImage.getImageId().toString());
			key="jpmProductSaleImage.delete";
		}else if ("addJpmProductSaleImage".equals(strAction)  ) {
			List jsysList = pdSendInfoManager.jsysListValues("productimage.imagetype", "");
			String[] valueCodeStrs = new String[3];
			String tempStr = "";
			if(jsysList!=null && jsysList.size()<=3){
				for(int i=0;i<3;i++){
					tempStr = jsysList.get(i).toString().split("=")[1];
					valueCodeStrs[i] = tempStr.substring(0, tempStr.length()-1);
				} 
			}
			
			//判断图片是否已经添加，如果已经存在，则提示只需要修改：
			Pager pager = new Pager("jpmProductSaleImageListTable",request, 20);
			CommonRecord crm=RequestUtil.toCommonRecord(request);
			crm.setValue("uniNo", jpmProductSaleImage.getUniNo());
			crm.setValue("imagetype", jpmProductSaleImage.getImageType());
			List list = jpmProductSaleImageManager.getJpmProductSaleImagesByCrm(crm, pager);
			
			if(list!=null && list.size()>=1){
				errors.reject("error.jpmproductsaleimage.existed", new Object[] {},LocaleUtil.getLocalText("error.jpmproductsaleimage.existed"));
				return showForm(request, response, errors);
			}
			
			//获得文件对象，然后获得文件字节流
			MultipartHttpServletRequest multipartRequest =
				(MultipartHttpServletRequest) request;
			CommonsMultipartFile file =
				(CommonsMultipartFile) multipartRequest.getFile("file");
			
			//商品主键
			Long uniNo = jpmProductSaleImage.getUniNo();  
			
			//如果有文章中带有附件
			String filename1 = getFileName(uniNo); 
			String filename2 = getFileName(uniNo); 
			String filename3 = getFileName(uniNo); 
			if (file != null && !file.isEmpty()) {
				
				DataOutputStream out1 = new DataOutputStream(new FileOutputStream(filePath + filename1)); 
				DataOutputStream out2 = new DataOutputStream(new FileOutputStream(filePath + filename2)); 
				DataOutputStream out3 = new DataOutputStream(new FileOutputStream(filePath + filename3)); 
				InputStream is = null;// 附件输入流 
				try {
					is = file.getInputStream();
					byte[] b=new byte[is.available()];
					is.read(b);
					out1.write(b); 
					out2.write(b); 
					out3.write(b); 
				} catch (IOException exception) {
					exception.printStackTrace();
				} finally {
					if (is != null) {
						is.close();
					} 
					if (out3 != null) {
						out3.close();
					} 
					if (out2 != null) {
						out2.close();
					} 
					if (out1 != null) {
						out1.close();
					} 
				} 
				
				String fileFormat = ".jpg";
				//将小、中、大
				String destFilePath1 = filePath+filename1;
				File ff1 = new File(destFilePath1);
				ImageTool.createMiniImage(ff1, fileFormat, destFilePath1,Integer.parseInt(proportion1[0]), Integer.parseInt(proportion1[1])); 
				
				String destFilePath2 = filePath+filename2;
				File ff2 = new File(destFilePath2);
				ImageTool.createMiniImage(ff2, fileFormat, destFilePath2,Integer.parseInt(proportion2[0]), Integer.parseInt(proportion2[1])); 
				
				String destFilePath3 = filePath+filename3;
				File ff3 = new File(destFilePath3);
				ImageTool.createMiniImage(ff3, fileFormat, destFilePath3,Integer.parseInt(proportion3[0]), Integer.parseInt(proportion3[1]));  
				
			}else{
				filename1 = "nopic1.jpg";
				filename2 = "nopic2.jpg";
				filename3 = "nopic3.jpg";
			}
			//图片比例
			/*
			        小 56*56
			        中 150*110
			        大 360*360
			*/
			
			//3张图片：小、中、大			
			String status = "1";//新增默认为禁用，需要审核
			JpmProductSaleImage jpmProductSaleImage1 = new JpmProductSaleImage(uniNo, filename1, valueCodeStrs[0], Integer.parseInt(valueCodeStrs[0]), status);
			JpmProductSaleImage jpmProductSaleImage2 = new JpmProductSaleImage(uniNo, filename2, valueCodeStrs[1], Integer.parseInt(valueCodeStrs[1]), status);
			JpmProductSaleImage jpmProductSaleImage3 = new JpmProductSaleImage(uniNo, filename3, valueCodeStrs[2], Integer.parseInt(valueCodeStrs[2]), status);
 
			 
			jpmProductSaleImageManager.saveJpmProductSaleImage(jpmProductSaleImage1);
			jpmProductSaleImageManager.saveJpmProductSaleImage(jpmProductSaleImage2);
			jpmProductSaleImageManager.saveJpmProductSaleImage(jpmProductSaleImage3); 
			key="jpmProductSaleImage.add";  
		
			//==========================Modify By WuCF OMS接口 20141112
			//得到商品编码
			String productNo = jpmProductSaleNewManager.getProductNos(String.valueOf(jpmProductSaleImage.getUniNo()));//商品编码集合字符串 
			JpmProduct jp = jpmProductManager.getJpmProduct(productNo);
			//图片路径
			String FILE_URL = ListUtil.getListValue("CN", "jpmproductsaleimage.url", "1")+"productNew/";
			/*JpmProductSaleErp jpmProductSaleErp = new JpmProductSaleErp();
			jpmProductSaleErp.setERP_PRODUCT_NO(jp.getErpProductNo());//ERP编码
			jpmProductSaleErp.setPRODUCT_NO(productNo);//商品编码
			jpmProductSaleErp.setSIMG_LINK(FILE_URL+filename1);//小图路径
			jpmProductSaleErp.setCIMG_LINK(FILE_URL+filename2);//中图路径
			jpmProductSaleErp.setBIMG_LINK(FILE_URL+filename3);//大图路径
			jpmProductSaleErp.setCOMPANY_CODE(loginSysUser.getCompanyCode().toUpperCase());//国别
*/			
			
			
			//----------------------Modify By WuCF 20150105接口数据交互
			/*JSONObject jsonObject = JSONObject.fromObject(jpmProductSaleErp);
			//将json对象转换成json字符串
			String returnnoJsonString = jsonObject.toString();*/
			StringBuffer returnnoJsonString = new StringBuffer("");
			returnnoJsonString.append("{\"ERP_PRODUCT_NO\":\"");
			returnnoJsonString.append(jp.getErpProductNo());
			returnnoJsonString.append("\",\"PRODUCT_NO\":\"");
			returnnoJsonString.append(productNo);
			returnnoJsonString.append("\",\"SIMG_LINK\":\"");
			returnnoJsonString.append(FILE_URL+filename1);
			returnnoJsonString.append("\",\"CIMG_LINK\":\"");
			returnnoJsonString.append(FILE_URL+filename2);
			returnnoJsonString.append("\",\"BIMG_LINK\":\"");
			returnnoJsonString.append(FILE_URL+filename3);
			returnnoJsonString.append("\",\"COMPANY_CODE\":\"");
			returnnoJsonString.append(loginSysUser.getCompanyCode().toUpperCase());
			returnnoJsonString.append("\"}");
			
			//调用发送接口---------------------------开始
			MsgSendService msgSendService = new MsgSendService();
			msgSendService.setSender(Constants.QU_SEND);
			//方法名
			String methodName = "goods.add";
			//modify gw 2015-03-03 捕获异常-修改bug
			try{
				log.info("goods.add(jpmpProductSaleImageFormAdd)："+returnnoJsonString.toString());
				if("Y".equals(swtichSend)){//Modify By WuCF 20160504 开关判断
					String aa = msgSendService.post(returnnoJsonString.toString(), methodName);
					//调用发送接口---------------------------结束
					
					//=============================接口日志写入开始
					JocsInterfaceLog jocsInterfaceLog = new JocsInterfaceLog();
					jocsInterfaceLog.setLogType("0");//0：本地发送数据    1：本地接收数据
					jocsInterfaceLog.setSender(Constants.QU_SEND);//数据的接收方
					jocsInterfaceLog.setMethod(methodName);//方法名称
					jocsInterfaceLog.setContent(returnnoJsonString.toString());//发送内容content
					jocsInterfaceLog.setReturnDesc(aa);//返回内容
					
					ReportLogUtilService.addJocsInterface(jocsInterfaceLog);//写入日志操作
					//=============================接口日志写入完毕	
				}
			}catch(Exception e){
				e.printStackTrace();
				log.debug(e.toString());
			}
		}else if("editJpmProductSaleImage".equals(strAction) || "confirmJpmProductSaleImage".equals(strAction)  ){
			//获得文件对象，然后获得文件字节流 
			MultipartHttpServletRequest multipartRequest =
				(MultipartHttpServletRequest) request;
			CommonsMultipartFile file = 
				(CommonsMultipartFile) multipartRequest.getFile("file2");
			 
			//如果有文章中带有附件 
			String filename = getFileName(jpmProductSaleImage.getUniNo());
			if (file != null && !file.isEmpty()) {
				
				DataOutputStream out = new DataOutputStream(new FileOutputStream(
						filePath + filename));// 存放文件的绝对路径
				InputStream is = null;// 附件输入流 
				try {
					is = file.getInputStream();
					byte[] b=new byte[is.available()];
					is.read(b);
					out.write(b);
					
					jpmProductSaleImage.setImageLink(filename);   
				} catch (IOException exception) {
					exception.printStackTrace();
				} finally {
					if (is != null) {
						is.close();
					} 
					if (out != null) {
						out.close();
					}
				} 
			} 
			
			//图片处理  ，先获得图片小中大的宽和高
			String[] proportion = new String[2];
			if("1".equals(jpmProductSaleImage.getImageType())){
				proportion = proportion1;
			}else if("2".equals(jpmProductSaleImage.getImageType())){
				proportion = proportion2;
			}else if("3".equals(jpmProductSaleImage.getImageType())){
				proportion = proportion3;
			}
			//处理
			String fileFormat = ".jpg";//图片后缀
			String destFilePath1 = filePath+filename;//图片路径
			File ff1 = new File(destFilePath1);//图片文件file
			ImageTool.createMiniImage(ff1, fileFormat, destFilePath1,Integer.parseInt(proportion[0]), Integer.parseInt(proportion[1])); 

			
			jpmProductSaleImageManager.saveJpmProductSaleImage(jpmProductSaleImage);
			key="jpmProductSaleImage.update";
			
			//==========================Modify By WuCF OMS接口 20141112
			//得到商品编码
			String productNo = jpmProductSaleNewManager.getProductNos(String.valueOf(jpmProductSaleImage.getUniNo()));//商品编码集合字符串 
			JpmProduct jp = jpmProductManager.getJpmProduct(productNo);
			//图片路径
			String FILE_URL = ListUtil.getListValue("CN", "jpmproductsaleimage.url", "1")+"productNew/";
			/*JpmProductSaleErp jpmProductSaleErp = new JpmProductSaleErp();
			jpmProductSaleErp.setERP_PRODUCT_NO(jp.getErpProductNo());//ERP编码
			jpmProductSaleErp.setPRODUCT_NO(productNo);//商品编码
			if("1".equals(jpmProductSaleImage.getImageType())){
				jpmProductSaleErp.setSIMG_LINK(FILE_URL+filename);//小图路径
			}else if("2".equals(jpmProductSaleImage.getImageType())){
				jpmProductSaleErp.setCIMG_LINK(FILE_URL+filename);//中图路径
			}else if("3".equals(jpmProductSaleImage.getImageType())){
				jpmProductSaleErp.setBIMG_LINK(FILE_URL+filename);//大图路径
			}
			jpmProductSaleErp.setCOMPANY_CODE(loginSysUser.getCompanyCode().toUpperCase());//国别
*/			
			StringBuffer returnnoJsonString = new StringBuffer("");
			returnnoJsonString.append("{\"ERP_PRODUCT_NO\":\"");
			returnnoJsonString.append(jp.getErpProductNo());
			returnnoJsonString.append("\",\"PRODUCT_NO\":\"");
			returnnoJsonString.append(productNo);
			if("1".equals(jpmProductSaleImage.getImageType())){
				returnnoJsonString.append("\",\"SIMG_LINK\":\"");
				returnnoJsonString.append(FILE_URL+filename);
			}else if("2".equals(jpmProductSaleImage.getImageType())){
				returnnoJsonString.append("\",\"CIMG_LINK\":\"");
				returnnoJsonString.append(FILE_URL+filename);
			}else if("3".equals(jpmProductSaleImage.getImageType())){
				returnnoJsonString.append("\",\"BIMG_LINK\":\"");
				returnnoJsonString.append(FILE_URL+filename);
			}
			returnnoJsonString.append("\",\"COMPANY_CODE\":\"");
			returnnoJsonString.append(loginSysUser.getCompanyCode().toUpperCase());
			returnnoJsonString.append("\"}");
			
			//----------------------Modify By WuCF 20150105接口数据交互
			/*JSONObject jsonObject = JSONObject.fromObject(jpmProductSaleErp);
			//将json对象转换成json字符串
			String returnnoJsonString = jsonObject.toString();*/
			
			//调用发送接口---------------------------开始
			MsgSendService msgSendService = new MsgSendService();
			msgSendService.setSender(Constants.QU_SEND);
			//方法名
			String methodName = "goods.add";
			//modify gw 2015-03-03 捕获异常-修改bug
			try{
				log.info("goods.add(jpmpProductSaleImageFormEdit)："+returnnoJsonString.toString());
				if("Y".equals(swtichSend)){//Modify By WuCF 20160504 开关判断
					String aa = msgSendService.post(returnnoJsonString.toString(), methodName);
					//调用发送接口---------------------------结束
					
					//=============================接口日志写入开始
					JocsInterfaceLog jocsInterfaceLog = new JocsInterfaceLog();
					jocsInterfaceLog.setLogType("0");//0：本地发送数据    1：本地接收数据
					jocsInterfaceLog.setSender(Constants.QU_SEND);//数据的接收方
					jocsInterfaceLog.setMethod(methodName);//方法名称
					jocsInterfaceLog.setContent(returnnoJsonString.toString());//发送内容content
					jocsInterfaceLog.setReturnDesc(aa);//返回内容
					
					ReportLogUtilService.addJocsInterface(jocsInterfaceLog);//写入日志操作
					//=============================接口日志写入完毕
				}
			}catch(Exception e){
				e.printStackTrace();
				log.debug(e.toString());
			}
		}
		
		String view = "redirect:jpmProductSaleImages.html?strAction=editJpmProductSaleImage";
		if("confirmJpmProductSaleImage".equals(strAction)){
			view = "redirect:jpmProductSaleImages.html?strAction=confirmJpmProductSaleImage";
		}
		
		saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), key));  
		return new ModelAndView(view);
	}
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		
		String view = "";
		String key = "";
		String uniNoStr = request.getParameter("uniNoStr");//获取多选字符串的uniNoStr
		String status2 = request.getParameter("status2");//修改的状态
		String productNo = request.getParameter("productNo");//获取多选字符串的uniNoStr
		request.setAttribute("uniNoStr", uniNoStr);
		request.setAttribute("status2", status2);
		request.setAttribute("productNo", productNo); 
		if(StringUtils.isNotEmpty(uniNoStr) && !"null".equals(uniNoStr)){//批量审核处理
			view = "redirect:jpmProductSaleImages.html?strAction=confirmJpmProductSaleImage";
			key="jpmProductSaleNew.batchAudit";
			saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), key));  
			return ; 
		}else{
			super.initBinder(request, binder);
		}
		
		super.initBinder(request, binder);
	}
	
	/**
	 * 得到图片的名称
	 * @return：商品主键+时间+1000以内的随机数.jpg
	 */
	public static String getFileName(Long uniNo){
		SimpleDateFormat dateformat2=new SimpleDateFormat("yyyyMMddHHmmss");   
		String dateStr=dateformat2.format(new Date());
		return String.valueOf(uniNo+"_"+dateStr+"_"+new Random().nextInt(1000)+"_"+new Random().nextInt(1000)+".jpg");
	}
}
