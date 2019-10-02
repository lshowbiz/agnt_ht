package com.joymain.jecs.am.webapp.action;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.image.ImageTool;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.am.model.InwDemandsort;
import com.joymain.jecs.am.service.InwDemandsortManager;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创新共赢-需求分类表的提交form类----新的需求
 * @author gw  2013-11-04
 * @author Administrator
 *
 */
public class InwDemandsortFormController extends BaseFormController {
    private InwDemandsortManager inwDemandsortManager = null;
    

    public void setInwDemandsortManager(InwDemandsortManager inwDemandsortManager) {
        this.inwDemandsortManager = inwDemandsortManager;
    }

	public InwDemandsortFormController() {
        setCommandName("inwDemandsort");
        setCommandClass(InwDemandsort.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        InwDemandsort inwDemandsort = null;
        if (!StringUtils.isEmpty(id)) {
            inwDemandsort = inwDemandsortManager.getInwDemandsort(id);
        } else {
            inwDemandsort = new InwDemandsort();
        }
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        //这个路径是固定机器的某个盘符的一个路径
		//String FILE_URL = ListUtil.getListValue(loginSysUser.getCompanyCode().toUpperCase(), "jpmproductsaleimage.url", "1");
        //读取图片的路径----这个路径是本机tomcat下的一个路径
        String FILE_URL = ConfigUtil.getConfigValue(loginSysUser.getCompanyCode().toUpperCase(),"column.image.demandurl");
		request.setAttribute("FILE_URL", FILE_URL);
		String strAction = request.getParameter("strAction");
		request.setAttribute("strAction",strAction);
        return inwDemandsort;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        //获取当前登录的对象
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        InwDemandsort inwDemandsort = (InwDemandsort) command;
        //这个路径是固定机器的某个盘符的一个路径
		//String filePath = ConfigUtil.getConfigValue(sessionLogin.getCompanyCode().toUpperCase(), "jpmproductsaleimage.path")+System.getProperty("file.separator");
        //图片路径---这个是tomcat下的一个路径:http://127.0.0.1:9090/jecs-new/images/column
         String filePath = getServletContext().getRealPath("/images")+"/column/";
        File pfile = new File(filePath);
		//如果图片物理路径不存在，则先创建 
		if(!pfile.exists()){
			pfile.mkdirs();
		} 
        String strAction = request.getParameter("strAction");
        //删除
        if("deleteInwDemandsort".equals(strAction)){
        	String id = request.getParameter("id");
        	inwDemandsortManager.removeInwDemandsort(id);
        }
        //录入
        else if("addInwDemandsort".equals(strAction)){
			//获得文件对象，然后获得文件字节流 
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			CommonsMultipartFile file =  (CommonsMultipartFile) multipartRequest.getFile("file");
			//如果有文章中带有附件 
			String filename = getFileName();
			if (file != null && !file.isEmpty()) {
				DataOutputStream out = new DataOutputStream(new FileOutputStream(filePath + filename));// 存放文件的绝对路径
				InputStream is = null;// 附件输入流 
				try {
					is = file.getInputStream();
					byte[] b=new byte[is.available()];
					is.read(b);
					out.write(b);
					inwDemandsort.setImage(filename);
					
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
			
			//图片处理
				String fileFormat = ".jpg";
				String destFilePath1 = filePath + filename;
				File ff1 = new File(destFilePath1);
				ImageTool.createMiniImage(ff1, fileFormat, destFilePath1,147,111); 
				 //修改或保存之前,做不为空的校验
		        boolean emptyCheck = inwDemandsortManager.getEmptyCheckResult(inwDemandsort,errors,sessionLogin.getDefCharacterCoding());
		        if(emptyCheck){
		        	return showForm(request,response,errors);
		        }else{
		        	inwDemandsortManager.saveInwDemandsort(inwDemandsort);
					this.saveMessage(request, "新增成功");
		        }
			
        }
        //修改
        else{
        	//获得文件对象，然后获得文件字节流 
    		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file2");
    		//如果有文章中带有附件 
    		String filename = getFileName();
    		if (file != null && !file.isEmpty()) {
    			
    			DataOutputStream out = new DataOutputStream(new FileOutputStream(filePath + filename));// 存放文件的绝对路径
    			InputStream is = null;// 附件输入流 
    			try {
    				is = file.getInputStream();
    				byte[] b=new byte[is.available()];
    				is.read(b);
    				out.write(b);
					inwDemandsort.setImage(filename);
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
    		
    		//图片处理
    		String fileFormat = ".jpg";
    		String destFilePath1 = filePath + filename;
    		File ff1 = new File(destFilePath1);
    		ImageTool.createMiniImage(ff1, fileFormat, destFilePath1,147,111); 
	        //修改或保存之前,做不为空的校验
	        boolean emptyCheck = inwDemandsortManager.getEmptyCheckResult(inwDemandsort,errors,sessionLogin.getDefCharacterCoding());
	        if(emptyCheck){
	        	return showForm(request,response,errors);
	        }else{
	        	inwDemandsortManager.saveInwDemandsort(inwDemandsort);
				this.saveMessage(request, "修改成功");
	        }
        }
        //return new ModelAndView(getSuccessView());
        return new ModelAndView("redirect:inwDemandsorts.html?strAction=queryInwDemandsort");
   }
    
    /**z
	 * 得到图片的名称
	 * @return：时间+1000以内的随机数.jpg
	 */
	public static String getFileName(){
		return String.valueOf(new Date().getTime())+"_"+new Random().nextInt(1000)+".jpg";
	}
    
    
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		super.initBinder(request, binder);
	}
    
}
