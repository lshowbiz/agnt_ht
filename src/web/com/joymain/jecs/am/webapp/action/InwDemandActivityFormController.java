package com.joymain.jecs.am.webapp.action;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
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
import com.joymain.jecs.am.model.InwSuggestion;
import com.joymain.jecs.am.model.InwViewpeople;
import com.joymain.jecs.am.service.InwDemandManager;
import com.joymain.jecs.am.service.InwDemandsortManager;
import com.joymain.jecs.am.service.InwSuggestionManager;
import com.joymain.jecs.am.service.InwViewpeopleManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.image.ImageTool;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 创新共赢的活动------创新共赢的新需求---创新共赢的子需求
 * @author gw 2013-09-06------update  gw   2013-11-04
 *
 */
public class InwDemandActivityFormController extends BaseFormController {
    private InwDemandManager inwDemandManager = null;
    
    //创新共赢的建议的Manager
    private InwSuggestionManager inwSuggestionManager;
    
    //创新共赢的建议查看的Manager
    private InwViewpeopleManager inwViewpeopleManager;

    //创新共赢的需求分类表的的Manager
    private InwDemandsortManager inwDemandsortManager;
    
    public void setInwDemandManager(InwDemandManager inwDemandManager) {
        this.inwDemandManager = inwDemandManager;
    }
    
    //创新共赢的建议的InwSuggestionManager的set方法
    public void setInwSuggestionManager(InwSuggestionManager inwSuggestionManager) {
        this.inwSuggestionManager = inwSuggestionManager;
    }
    
    //创新共赢的建议查看的InwViewpeopleManager的set方法
	public void setInwViewpeopleManager(InwViewpeopleManager inwViewpeopleManager) {
		this.inwViewpeopleManager = inwViewpeopleManager;
	}

	//创新共赢的需求分类的inwDemandsortManager的set方法
	public void setInwDemandsortManager(InwDemandsortManager inwDemandsortManager) {
		this.inwDemandsortManager = inwDemandsortManager;
	}

	public InwDemandActivityFormController() {
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
        //这个路径是固定机器的某个盘符的一个路径
		//String FILE_URL = ListUtil.getListValue(loginSysUser.getCompanyCode().toUpperCase(), "jpmproductsaleimage.url", "1");
        //读取图片的路径----这个路径是本机tomcat下的一个路径
        String FILE_URL = ConfigUtil.getConfigValue(loginSysUser.getCompanyCode().toUpperCase(),"column.image.demandurl");
        
		request.setAttribute("FILE_URL", FILE_URL);
		String strAction = request.getParameter("strAction");
		request.setAttribute("strAction", strAction);
		//查创新 共赢的需求分类表的所有数据
		List demandsortList = inwDemandsortManager.getDemandsortList();
		request.setAttribute("demandsortList", demandsortList);
		
		
        return inwDemand;
    }

    
    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        //查创新 共赢的需求分类表的所有数据
		List demandsortList = inwDemandsortManager.getDemandsortList();
		request.setAttribute("demandsortList", demandsortList);
		
        InwDemand inwDemand = (InwDemand) command;
        
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        //这个路径是固定机器的某个盘符的一个路径
		//String filePath = ConfigUtil.getConfigValue(sessionLogin.getCompanyCode().toUpperCase(), "jpmproductsaleimage.path")+System.getProperty("file.separator");
        //图片路径---这个是tomcat下的一个路径:http://127.0.0.1:9090/jecs-new/images/column
         String filePath = getServletContext().getRealPath("/images")+"/column/";
        File pfile = new File(filePath);
		//如果图片物理路径不存在，则先创建 
		if(!pfile.exists()){
			pfile.mkdirs();
		} 
		String key=null;
		String strAction = request.getParameter("strAction");
		if ("deleteInwDemandActivity".equals(strAction)  ) {
			inwDemandManager.removeInwDemand(inwDemand.getId().toString());
			//在删除创新共赢的活动时,将相关联的的数据也删除掉
			//删除创新共赢的活动(需求)的相关建议
			List<InwSuggestion> inwSuggestionList = inwSuggestionManager.getInwSuggestionByDemandid(inwDemand.getId().toString());
			for(InwSuggestion inwSuggestion:inwSuggestionList){
				    inwSuggestionManager.removeInwSuggestion(inwSuggestion.getId().toString());
				    //删除每条建议相关的建议查看的内容
				    InwViewpeople inwViewpeople =  inwViewpeopleManager.getInwViewpeopleBySuggestionId(inwSuggestion.getId().toString());
				if(null!=inwViewpeople){
					inwViewpeopleManager.removeInwViewpeople(inwViewpeople.getId().toString());
				}
			}
			key="inwDemand.delete";
		}
		//添加活动
		else if("addInwDemandActivity".equals(strAction)){
			    //保存之前先做不为空的校验
			    boolean emptyCheck = inwDemandManager.getEmptyCheck(inwDemand,errors,sessionLogin.getDefCharacterCoding());
			    if(emptyCheck){
			    	return showForm(request, response, errors);
			    }else{
					//发布人
					inwDemand.setPostUserCode(sessionLogin.getUserName());
					//发布时间
					inwDemand.setPostTime(new Date());
					//未审核
					inwDemand.setVerify("N");
					//other  类别 (1代表 需求,2代表活动,当然还有其他种类的.这个用于扩展用) 
					inwDemand.setOther("2");
					//获得文件对象，然后获得文件字节流 
				    //-----------------------------------------------?
					MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
					//--------------------------------------------?获取文件(附件)
					CommonsMultipartFile file =  (CommonsMultipartFile) multipartRequest.getFile("file");
					//定义图片(附件)的名称
					String filename = getFileName();
					//如果有文章中带有附件 
					if (file != null && !file.isEmpty()) {
						//定义一个数据输出流
						DataOutputStream out = new DataOutputStream(new FileOutputStream(filePath + filename));// 存放文件的绝对路径
						// 定义输入流 
						InputStream is = null;
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
					boolean demandImageIsEmpty = inwDemandManager.getEmptyCheckForDemandImage(inwDemand,errors,sessionLogin.getDefCharacterCoding());
					
					if(demandImageIsEmpty){
						return showForm(request, response, errors);
					}else{
						//----------------------------------------------------?这个图片处理有点问题吧----------?
						//图片处理
						String fileFormat = ".jpg";
						String destFilePath1 = filePath + filename;
						File ff1 = new File(destFilePath1);
						ImageTool.createMiniImage(ff1, fileFormat, destFilePath1,147,111); 
						
						inwDemandManager.saveInwDemand(inwDemand);
						this.saveMessage(request, "新增成功！");
						request.setAttribute("saveMark", "saveMark");
					}
			    }
			
		//return showForm(request, response, errors);
	}
	else if("editInwDemandActivity".equals(strAction)){
		//保存之前先做不为空的校验
	    boolean emptyCheck = inwDemandManager.getEmptyCheck(inwDemand,errors,sessionLogin.getDefCharacterCoding());
	    if(emptyCheck){
	    	return showForm(request, response, errors);
	    }else{
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
			ImageTool.createMiniImage(ff1, fileFormat, destFilePath1,147,111); 
			
			inwDemandManager.saveInwDemand(inwDemand);
			this.saveMessage(request, "修改成功！");
	    }	
	}else{
		inwDemand.setVerify("Y");
		inwDemand.setReviewedTime(new Date());
		inwDemand.setReviewedUserCode(sessionLogin.getUserName());
		inwDemandManager.saveInwDemand(inwDemand);
		key ="checked";
	}
		request.setAttribute("strAction", strAction);
        return new ModelAndView("redirect:inwDemandsActivity.html?strAction=editDemandsActivity");
    }
    
    
    /**
     * @author gw 2013-09-09
	 * 得到图片的名称
	 * @return：时间+1000以内的随机数(整数.jpg
	 */
	public static String getFileName(){
		return String.valueOf(new Date().getTime())+"_"+new Random().nextInt(1000)+".jpg";
	}
	
	
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		super.initBinder(request, binder);
	}
}
