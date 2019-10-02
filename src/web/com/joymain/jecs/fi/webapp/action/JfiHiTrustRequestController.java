package com.joymain.jecs.fi.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.hitrust.HiTrustUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.JfiAlipayLog;
import com.joymain.jecs.fi.service.JfiAlipayLogManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 台湾信用卡支付请求
 * @author Alvin
 *
 */
public class JfiHiTrustRequestController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiHiTrustRequestController.class);
    private HiTrustUtil hiTrustUtil = null;

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        String moId = request.getParameter("moId");
        String url = hiTrustUtil.getHiTrustUrl(moId);
        if (!url.contains("http")){
        	saveMessage(request,LocaleUtil.getLocalText("zh_TW","notice.payment.failed") + ":" + LocaleUtil.getLocalText("zh_TW",url));
        	return new ModelAndView("fi/jfiHiTrustRequest");
        }
        return new ModelAndView("redirect:" + url);
    }

	public void setHiTrustUtil(HiTrustUtil hiTrustUtil) {
		this.hiTrustUtil = hiTrustUtil;
	}
}
