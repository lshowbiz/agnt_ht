package com.joymain.jecs.am.webapp.action;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
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

import com.joymain.jecs.am.model.InwDemand;
import com.joymain.jecs.am.service.InwDemandManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.image.ImageTool;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class InwDemandFormController extends BaseFormController {
    private InwDemandManager inwDemandManager = null;

    public void setInwDemandManager(InwDemandManager inwDemandManager) {
        this.inwDemandManager = inwDemandManager;
    }
    public InwDemandFormController() {
        setCommandName("inwDemand");
        setCommandClass(InwDemand.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        InwDemand inwDemand = null;

        if (!StringUtils.isEmpty(id)) {
            inwDemand = inwDemandManager.getInwDemand(id);
        } else {
            inwDemand = new InwDemand();
        }
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        /*String FILE_URL = ListUtil.getListValue(loginSysUser.getCompanyCode().toUpperCase(), "jpmproductsaleimage.url", "3");*/
        //读取图片的路径
        String FILE_URL = ConfigUtil.getConfigValue(loginSysUser.getCompanyCode().toUpperCase(),"column.image.demandurl");
        //String FILE_URL = "D:\\jecsImage\\demand\\";
		request.setAttribute("FILE_URL", FILE_URL);
        return inwDemand;
    }

    
    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        InwDemand inwDemand = (InwDemand) command;
        
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        /*String filePath = ConfigUtil.getConfigValue(sessionLogin.getCompanyCode().toUpperCase(), "inwdemandimage.path")+System.getProperty("file.separator");*/
        //gw 2013-09-13  定义目标上传图片的地址 (需要配置)
        //getServletContext().getRealPath("/") 获取当前项目在tomcat下的运行路径--这个针对的是HttpServletRequest
        String filePath = getServletContext().getRealPath("/images")+"/column/";
        File pfile = new File(filePath);
		//如果图片物理路径不存在，则先创建 
		if(!pfile.exists()){
			pfile.mkdirs();
		} 

		boolean isNew = (inwDemand.getId() == null);
        Locale locale = request.getLocale();
		String key=null;
		String strAction = request.getParameter("strAction");
		//删除
		if ("deleteInwDemand".equals(strAction)  ) {
			inwDemandManager.removeInwDemand(inwDemand.getId().toString());
			key="inwDemand.delete";
		}
		//新增
		else if("addInwDemand".equals(strAction)||"addInwDemandSystem".equals(strAction)){
			 //保存之前先做不为空的校验
		    boolean emptyCheck = inwDemandManager.getEmptyCheck(inwDemand,errors,sessionLogin.getDefCharacterCoding());
		    if(emptyCheck){
		    	return showForm(request, response, errors);
		    }else{
				inwDemand.setPostUserCode(sessionLogin.getUserName());
				inwDemand.setPostTime(new Date());
				inwDemand.setVerify("N");
				//other  类别 (1代表 需求,2代表活动,当然还有其他种类的.这个用于扩展用),3代表针对当前系统或者功能提的建议时的需求 
				if("addInwDemand".equals(strAction)){
				     inwDemand.setOther("1");
				}else if("addInwDemandSystem".equals(strAction)){
					inwDemand.setOther("3");
				}
				//获得文件对象，然后获得文件字节流 
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				CommonsMultipartFile file =  (CommonsMultipartFile) multipartRequest.getFile("file");
				
				//如果有文章中带有附件 
				String filename = getFileName();
				if (file != null && !file.isEmpty()) {
					
					DataOutputStream out = new DataOutputStream(new FileOutputStream(
							filePath + filename));// 存放文件的绝对路径
					InputStream is = null;// 附件输入流 
					try {
						is = file.getInputStream();
						byte[] b=new byte[is.available()];
						is.read(b);
						out.write(b);
						inwDemand.setDemandImage(filename);
						
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
				String demandImage = inwDemand.getDemandImage();
				if(StringUtil.isEmpty(demandImage)){
					errors.rejectValue("demandImage", "isNotNull",new Object[] {LocaleUtil.getLocalText( "inwDemand.demandImage") }, "");
					return showForm(request, response, errors);
				}else{
				//图片处理
					String fileFormat = ".jpg";
					String destFilePath1 = filePath + filename;
					File ff1 = new File(destFilePath1);
					ImageTool.createMiniImage(ff1, fileFormat, destFilePath1,56,56); 
					
					inwDemandManager.saveInwDemand(inwDemand);
					this.saveMessage(request, "新增成功！");
				}
		}
	}
	//修改
	else if("editInwDemand".equals(strAction)){
		//获得文件对象，然后获得文件字节流 
		MultipartHttpServletRequest multipartRequest =
			(MultipartHttpServletRequest) request;
		CommonsMultipartFile file = 
			(CommonsMultipartFile) multipartRequest.getFile("file2");
		
		//如果有文章中带有附件 
		String filename = getFileName();
		if (file != null && !file.isEmpty()) {
			
			DataOutputStream out = new DataOutputStream(new FileOutputStream(
					filePath + filename));// 存放文件的绝对路径
			InputStream is = null;// 附件输入流 
			try {
				is = file.getInputStream();
				byte[] b=new byte[is.available()];
				is.read(b);
				out.write(b);
			
				inwDemand.setDemandImage(filename);
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
		ImageTool.createMiniImage(ff1, fileFormat, destFilePath1,56,56); 
		
		inwDemandManager.saveInwDemand(inwDemand);
		this.saveMessage(request, "修改成功！");
		
	}
	//审核
	else{
		inwDemand.setVerify("Y");
		inwDemand.setReviewedTime(new Date());
		inwDemand.setReviewedUserCode(sessionLogin.getUserName());
		inwDemandManager.saveInwDemand(inwDemand);
		key ="checked";
	}
	
        return new ModelAndView("redirect:inwDemands.html?strAction=editDemands");
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
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
    
}
