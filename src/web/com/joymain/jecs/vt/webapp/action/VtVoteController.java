package com.joymain.jecs.vt.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.bd.model.BdBounsDeduct;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.vt.model.VtVote;
import com.joymain.jecs.vt.service.VtVoteManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class VtVoteController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(VtVoteController.class);
    private VtVoteManager vtVoteManager = null;

    public void setVtVoteManager(VtVoteManager vtVoteManager) {
        this.vtVoteManager = vtVoteManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        String strAction=request.getParameter("strAction");
        if("strActionOperation".equals(request.getParameter("strActionOperation"))){
        	String status="";
        	String state="";
        	if("auditVtVote".equals(strAction)){
        		status="2";
        	}else if("toPendingVtVote".equals(strAction)){
        		status="1";
        	}else if("cacelVtVote".equals(strAction)){
        		status="3";
        	}else if("onVtVote".equals(strAction)){
        		state="1";
        	}else if("offVtVote".equals(strAction)){
        		state="0";
        	}
        	String[] strAdvicesCodes = request.getParameter("strAdvicesCodes").split(",");
        	boolean flag=false;
        	for (int i = 0; i < strAdvicesCodes.length; i++) {
				if (!StringUtils.isEmpty(strAdvicesCodes[i])) {
					VtVote vtVote=vtVoteManager.getVtVote(strAdvicesCodes[i]);
					if(!StringUtils.isEmpty(status)){
						vtVote.setStatus(status);
					}else if(!StringUtils.isEmpty(state)){
						vtVote.setState(state);
					}
					
					vtVoteManager.saveVtVote(vtVote);
					flag=true;
				}
        	}
        	if(flag){
				saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.operaterSuccess"));

		        return new ModelAndView("redirect:"+request.getHeader("Referer"));
        	}
        }
        
        
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("vtVoteListTable",request, 20);
        List vtVotes = vtVoteManager.getVtVotesByCrm(crm,pager);
        request.setAttribute("vtVoteListTable_totalRows", pager.getTotalObjects());


        return new ModelAndView("vt/vtVoteList", Constants.VTVOTE_LIST, vtVotes);
    }
}
