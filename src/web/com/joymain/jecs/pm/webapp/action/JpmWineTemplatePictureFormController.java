package com.joymain.jecs.pm.webapp.action;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.pm.model.JpmProductWineTemplateSub;
import com.joymain.jecs.pm.model.JpmWineTemplatePicture;
import com.joymain.jecs.pm.service.JpmWineTemplatePictureManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JpmWineTemplatePictureFormController extends BaseFormController {
    private JpmWineTemplatePictureManager jpmWineTemplatePictureManager = null;

    public void setJpmWineTemplatePictureManager(JpmWineTemplatePictureManager jpmWineTemplatePictureManager) {
        this.jpmWineTemplatePictureManager = jpmWineTemplatePictureManager;
    }

    public JpmWineTemplatePictureFormController() {
        setCommandName("jpmWineTemplatePicture");
        setCommandClass(JpmWineTemplatePicture.class);
    }

    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        String idf = request.getParameter("idf");
        JpmWineTemplatePicture jpmWineTemplatePicture = null;
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        String fileUrl = ListUtil.getListValue(sessionLogin.getCompanyCode().toUpperCase(), "jpmproductsaleimage.url", "1") + "wine/";
        request.setAttribute("fileUrl", fileUrl);
        if (!StringUtils.isEmpty(idf)) {
            jpmWineTemplatePicture = jpmWineTemplatePictureManager.getJpmWineTemplatePicture(idf);
        } else {
            jpmWineTemplatePicture = new JpmWineTemplatePicture();
        }

        return jpmWineTemplatePicture;
    }

    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JpmWineTemplatePicture jpmWineTemplatePicture = (JpmWineTemplatePicture) command;
        boolean isNew = (jpmWineTemplatePicture.getIdf() == null);
        Locale locale = request.getLocale();

        //这个路径是固定机器的某个盘符的一个路径，在wine目录下是酒业材料的图片
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        String filePath = ConfigUtil.getConfigValue(sessionLogin.getCompanyCode().toUpperCase(), "column.image.url") + System.getProperty("file.separator");
        filePath += "wine" + System.getProperty("file.separator");
        File pfile = new File(filePath);
        //如果图片物理路径不存在，则先创建 
        if (!pfile.exists()) {
            pfile.mkdirs();
        }

        String key = null;
        String strAction = request.getParameter("strAction");
        if ("deleteJpmWineTemplatePicture".equals(strAction)) {
            jpmWineTemplatePictureManager.removeJpmWineTemplatePicture(jpmWineTemplatePicture.getIdf().toString());
            key = "jpmWineTemplatePicture.delete";
        } else {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");
            //定义图片(附件)的名称
            if (file != null && !file.isEmpty()) {
                //定义一个数据输出流
                FileItem fi = file.getFileItem();
                String pref = fi.getName().substring(fi.getName().lastIndexOf(".") + 1);
                String filename = String.valueOf(new Date().getTime()) + "_" + new SecureRandom().nextInt(1000) + "." + pref;
                String picturePath = filePath + filename;
                DataOutputStream out = new DataOutputStream(new FileOutputStream(picturePath));// 存放文件的绝对路径
                jpmWineTemplatePicture.setPicturePath(picturePath);
                jpmWineTemplatePicture.setPictureNameSrc(fi.getName());//原文件的名字
                jpmWineTemplatePicture.setPictureNameDist(filename);
                // 定义输入流 
                InputStream is = null;
                try {
                    is = file.getInputStream();
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    out.write(b);
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
            String subNo = request.getParameter("subNo");

            jpmWineTemplatePicture.setJpmProductWineTemplateSub(new JpmProductWineTemplateSub());
            jpmWineTemplatePicture.getJpmProductWineTemplateSub().setSubNo(subNo);
            jpmWineTemplatePictureManager.saveJpmWineTemplatePicture(jpmWineTemplatePicture);
            key = "jpmWineTemplatePicture.update";
        }

        return new ModelAndView(getSuccessView());
    }

    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        // TODO Auto-generated method stub
        //		binder.setAllowedFields(allowedFields);
        //		binder.setDisallowedFields(disallowedFields);
        //		binder.setRequiredFields(requiredFields);
        super.initBinder(request, binder);
    }

    public static void main(String[] args) {
        System.out.println("ttd.txt".lastIndexOf("."));
    }
}
