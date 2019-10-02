package com.joymain.jecs.fi.webapp.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.fi.model.FiBcoinBalance;
import com.joymain.jecs.fi.model.FiBcoinPayconfig;
import com.joymain.jecs.fi.model.JfiBankbookBalance;
import com.joymain.jecs.fi.service.FiBcoinBalanceManager;
import com.joymain.jecs.fi.service.FiBcoinPayconfigManager;
import com.joymain.jecs.fi.service.JfiBankbookBalanceManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.service.JpoMemberOrderListManager;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.alipay.Alipay;
import com.joymain.jecs.util.bill99.Bill99;
import com.joymain.jecs.util.bill99ms.Bill99ms;
import com.joymain.jecs.util.tenpay.Tenpay;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 畅捷通业务查询请求表单
 * @author Alvin
 *
 */
public class ChanjetDownloadTransBillTwoController extends BaseController implements Controller {

    private final Log log = LogFactory.getLog(ChanjetDownloadTransBillTwoController.class);
    
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {

        return new ModelAndView("chanjet/downloadTransBill_two");
    }
}
