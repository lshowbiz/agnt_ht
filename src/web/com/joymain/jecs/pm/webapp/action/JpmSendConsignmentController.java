package com.joymain.jecs.pm.webapp.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.mi.model.JmiAddrBook;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiAddrBookManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.pm.model.JpmConfigSpecDetailed;
import com.joymain.jecs.pm.model.JpmSendConsignment;
import com.joymain.jecs.pm.service.JpmConfigSpecDetailedManager;
import com.joymain.jecs.pm.service.JpmSendConsignmentManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.sun.tools.xjc.model.Model;

public class JpmSendConsignmentController extends BaseController implements Controller
{
    private final Log log = LogFactory.getLog(JpmSendConsignmentController.class);
    
    private JpmSendConsignmentManager jpmSendConsignmentManager = null;
    
    private AlStateProvinceManager alStateProvinceManager;
    
    private JpmConfigSpecDetailedManager jpmConfigSpecDetailedManager;
    
    private JmiAddrBookManager jmiAddrBookManager;
    
    private JmiMemberManager jmiMemberManager;
    
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager)
    {
        this.jmiMemberManager = jmiMemberManager;
    }

    public void setJpmSendConsignmentManager(JpmSendConsignmentManager jpmSendConsignmentManager)
    {
        this.jpmSendConsignmentManager = jpmSendConsignmentManager;
    }
    
    public void setAlStateProvinceManager(AlStateProvinceManager alStateProvinceManager)
    {
        this.alStateProvinceManager = alStateProvinceManager;
    }
    
    public void setJpmConfigSpecDetailedManager(
        JpmConfigSpecDetailedManager jpmConfigSpecDetailedManager)
    {
        this.jpmConfigSpecDetailedManager = jpmConfigSpecDetailedManager;
    }
    
    public void setJmiAddrBookManager(JmiAddrBookManager jmiAddrBookManager)
    {
        this.jmiAddrBookManager = jmiAddrBookManager;
    }
    
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        if (log.isDebugEnabled())
        {
            log.debug("entering 'handleRequest' method...");
        }
        
        String strAction = request.getParameter("strAction");
        
        String mvc = "";
        if (null == strAction || "".equals(strAction))
        {
            Pager pager = new Pager("jpmSendConsignmentListTable", request, 20);
            mvc = sendConsignmentPage(null, request, response, pager,null);
            request.setAttribute("jpmSendConsignmentListTable_totalRows", pager.getTotalObjects());
        }
        else if ("addConsignment".equals(strAction))
        {
            addConsignment(null, request, response);
            Pager pager = new Pager("jpmSendConsignmentListTable", request, 20);
            mvc = sendConsignmentPage(null, request, response, pager,"1");
            request.setAttribute("jpmSendConsignmentListTable_totalRows", pager.getTotalObjects());
        }
        else if ("delConsignment".equals(strAction))
        {
            delConsignment(null, request, response);
            Pager pager = new Pager("jpmSendConsignmentListTable", request, 20);
            mvc = sendConsignmentPage(null, request, response, pager,null);
            request.setAttribute("jpmSendConsignmentListTable_totalRows", pager.getTotalObjects());
        }
        return new ModelAndView(mvc);
        // Pager pager = new Pager("jpmSendConsignmentListTable",request, 20);
        //        
        // request.setAttribute("jpmSendConsignmentListTable_totalRows",
        // pager.getTotalObjects());
        /*****/
        
        // return new ModelAndView("pm/jpmSendConsignmentList",
        // Constants.JPMSENDCONSIGNMENT_LIST, jpmSendConsignments);
    }
    
    /**
     * 查询规格下的发货单信息
     * 
     * @return
     */
    public String sendConsignmentPage(Model model, HttpServletRequest request,
        HttpServletResponse response, Pager pager,String isAdd)
    {
        String specNo = request.getParameter("specNo");
        String productNo = request.getParameter("productNo");
        String molId = request.getParameter("molId");
        String userCode = request.getParameter("userCode");
        Map<String, String> map = new HashMap<String, String>();
        List<JpmSendConsignment> list = null;
        if (StringUtils.isNotEmpty(specNo))
        {
            list =
                jpmSendConsignmentManager.getJpmSendConsignmentListBySpecNo(Long.parseLong(specNo));
        }
        // 已配送瓶数
        Long wasNum = 0l;
        if (CollectionUtils.isNotEmpty(list))
        {
            for (JpmSendConsignment jpmSendConsignment : list)
            {
                wasNum += jpmSendConsignment.getConsignmenNum().longValue();
                JmiAddrBook jmiAddrBook =
                    jmiAddrBookManager.getJmiAddrBook(String.valueOf(jpmSendConsignment.getFabId()));
                jpmSendConsignment.setAddress(jpmSendConsignmentManager.getAddresByFabId(jmiAddrBook));
            }
        }
        // 查询此规格下剩余发货瓶数
        JpmConfigSpecDetailed jpmConfigSpecDetailed =
            jpmConfigSpecDetailedManager.getJpmConfigSpecDetailedBySpecNo(Long.parseLong(specNo));
        // 可配送瓶数
        Long consignmenNum = 0l;
        if (null != jpmConfigSpecDetailed.getProductNum())
        {
            consignmenNum = jpmConfigSpecDetailed.getProductNum().longValue() - wasNum;
        }
        
        // 查询该用户下所有收货地址信息
        List jmiAddrBooks = jmiAddrBookManager.getJmiAddrBooksByUserCode(userCode);
        request.setAttribute("jmiAddrBooks", jmiAddrBooks);
        //当是添加或修改方法进入时，清空发货单信息
        if(StringUtils.isEmpty(isAdd))
        {
            String consignmentNo = request.getParameter("consignmentNo");
            if (StringUtils.isNotEmpty(consignmentNo))
            {
                JpmSendConsignment jpmSendConsignment =
                    jpmSendConsignmentManager.getJpmSendConsignmentByConsignmentNo(Long.parseLong(consignmentNo));
                request.setAttribute("jpmSendConsignment", jpmSendConsignment);
            }
        }
        
        map.put("specNo", specNo);
        map.put("consignmenNum", consignmenNum.toString());
        map.put("productNo", productNo);
        map.put("molId", molId);
        map.put("userCode", userCode);
        request.setAttribute("model", new HashMap<String, String>(map));
        JmiMember jmiMember = jmiMemberManager.getJmiMember(userCode);
        List alStateProvinces = alStateProvinceManager.getAlStateProvincesByCountry(jmiMember.getCompanyCode());
        request.setAttribute("alStateProvinces", alStateProvinces);
        request.setAttribute("jpmSendConsignmentList", list);
        return "pm/jpmSendConsignmentList";
    }
    
    /**
     * 添加规格发货单页面
     * 
     * @return
     */
    public String addConsignment(Model model, HttpServletRequest request,
        HttpServletResponse response)
    {
        String userCode = request.getParameter("userCode");
        String specNo = request.getParameter("specNo");
        String productNo = request.getParameter("productNo");
        String molId = request.getParameter("molId");
        String consignmenNum = request.getParameter("consignmenNum");
        String fabId = request.getParameter("fabId");
        JpmSendConsignment jpmSendConsignment = new JpmSendConsignment();
        jpmSendConsignment.setConsignmenNum(BigDecimal.valueOf(Long.parseLong(consignmenNum)));
        jpmSendConsignment.setSpecNo(Long.parseLong(specNo));
        jpmSendConsignment.setFabId(Long.parseLong(fabId));
        jpmSendConsignment.setSendDate(new Date());
        jpmSendConsignment.setSendUserCode(userCode);
        jpmSendConsignment.setStatus("0");
        String consignmentNo = request.getParameter("consignmentNo");
        if (StringUtils.isNotEmpty(consignmentNo))
        {
            jpmSendConsignment.setConsignmentNo(Long.parseLong(consignmentNo));
        }
        jpmSendConsignmentManager.saveJpmSendConsignment(jpmSendConsignment);
        
        return null;
    }
    
    /**
     * 删除规格发货单
     * 
     * @return
     */
    public String delConsignment(Model model, HttpServletRequest request,
        HttpServletResponse response)
    {
        String specNo = request.getParameter("specNo");
        String consignmentNo = request.getParameter("consignmentNo");
        String userCode = request.getParameter("userCode");
        String code = request.getParameter("code");
        String productNo = request.getParameter("productNo");
        String molId = request.getParameter("molId");
        jpmSendConsignmentManager.delJpmSendConsignmentByConsignmentNo(Long.parseLong(consignmentNo));
        Map<String, String> map = new HashMap<String, String>();
        map.put("specNo", specNo);
        map.put("userCode", userCode);
        map.put("productNo", productNo);
        map.put("molId", molId);
        request.setAttribute("model", new HashMap<String, String>(map));
        return null;
    }
}
