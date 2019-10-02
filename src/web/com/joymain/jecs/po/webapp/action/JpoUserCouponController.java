package com.joymain.jecs.po.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.po.model.JpoUserCoupon;
import com.joymain.jecs.po.service.JpoUserCouponManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.MessageUtil;

public class JpoUserCouponController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoUserCouponController.class);
    private JpoUserCouponManager jpoUserCouponManager = null;

    public void setJpoUserCouponManager(JpoUserCouponManager jpoUserCouponManager) {
        this.jpoUserCouponManager = jpoUserCouponManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        String userCode = request.getParameter("userCode");
        String status = request.getParameter("status");
        String avlestatus=request.getParameter("avlestatus");
        String cpName=request.getParameter("cpName");
        String createStartTime=request.getParameter("createStartTime");
        String createEndTime=request.getParameter("createEndTime");
        String syStartTime=request.getParameter("syStartTime");
        String syEndTime=request.getParameter("syEndTime");
        
        JpoUserCoupon jpoUserCoupon = new JpoUserCoupon();
        
        String strAction=StringUtils.isEmpty(request.getParameter("strAction"))?"":request.getParameter("strAction");
        
        //修改状态
        if ("updateCoupon".equals(strAction)) {
			String ids = request.getParameter("ids");
			try {
				jpoUserCouponManager.updateUserCouponByIds(ids);
				MessageUtil.saveMessage(request, "修改成功");
			} catch (Exception e) {
				MessageUtil.saveMessage(request, "修改失败");
			}
		}else if("glCoupon".equals(strAction)){
			return new ModelAndView("redirect:/editJpoUserCoupon.html");
		}
	
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jpoUserCouponListTable",request, 20);
        List jpoUserCoupons=jpoUserCouponManager.getJpoUserCouponsByCrmToSql(crm, pager);
        request.setAttribute("jpoUserCouponListTable_totalRows", pager.getTotalObjects());
        return new ModelAndView("po/jpoUserCouponList","jpoUserCoupons", jpoUserCoupons);
    }
}
