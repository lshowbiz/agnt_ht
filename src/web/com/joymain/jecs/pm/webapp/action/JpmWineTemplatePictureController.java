package com.joymain.jecs.pm.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.pm.model.JpmWineTemplatePicture;
import com.joymain.jecs.pm.service.JpmWineTemplatePictureManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JpmWineTemplatePictureController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpmWineTemplatePictureController.class);

    private JpmWineTemplatePictureManager jpmWineTemplatePictureManager = null;

    public void setJpmWineTemplatePictureManager(JpmWineTemplatePictureManager jpmWineTemplatePictureManager) {
        this.jpmWineTemplatePictureManager = jpmWineTemplatePictureManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JpmWineTemplatePicture jpmWineTemplatePicture = new JpmWineTemplatePicture();
        // populate object with request parameters
        BeanUtils.populate(jpmWineTemplatePicture, request.getParameterMap());

        //List jpmWineTemplatePictures = jpmWineTemplatePictureManager.getJpmWineTemplatePictures(jpmWineTemplatePicture);
        /**** auto pagination ***/
        CommonRecord crm = RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jpmWineTemplatePictureListTable", request, 20);
        List jpmWineTemplatePictures = jpmWineTemplatePictureManager.getJpmWineTemplatePicturesByCrm(crm, pager);
        request.setAttribute("jpmWineTemplatePictureListTable_totalRows", pager.getTotalObjects());
        /*****/
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        String fileUrl = ListUtil.getListValue(sessionLogin.getCompanyCode().toUpperCase(), "jpmproductsaleimage.url", "1") + "wine/";
        request.setAttribute("fileUrl", fileUrl);
        return new ModelAndView("pm/jpmWineTemplatePictureList", "jpmWineTemplatePictureList", jpmWineTemplatePictures);
    }
}
