package com.joymain.jecs.pd.webapp.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.pd.model.PdSendInfo;
import com.joymain.jecs.pd.model.PdSendInfoDetail;
import com.joymain.jecs.pd.service.PdSendInfoDetailManager;
import com.joymain.jecs.pd.service.PdSendInfoManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.ups.util.Base64Tool;

public class PdPrintLabelController extends BaseController implements
		Controller {
	
	PdSendInfoManager pdSendInfoManager = null;
	PdSendInfoDetailManager pdSendInfoDetailManager =null;
	
	public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}

	public void setPdSendInfoDetailManager(
			PdSendInfoDetailManager pdSendInfoDetailManager) {
		this.pdSendInfoDetailManager = pdSendInfoDetailManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String siNo = request.getParameter("siNo");
		String type = request.getParameter("type");
		String uniNo = request.getParameter("uniNo");
		String fileFormate = request.getParameter("fileFormate");
		response.setContentType( "application/pdf" ); 
		OutputStream out =response.getOutputStream();
		if("single".equals(type)){
			PdSendInfoDetail pdSendInfoDetail = pdSendInfoDetailManager.getPdSendInfoDetail(uniNo);
			response.setHeader("Content-Disposition", "inline; filename="+pdSendInfoDetail.getUniNo()+"label.pdf");
			Base64Tool.codeToPdf(pdSendInfoDetail.getLabelCode(), out);
			
		}else {
			PdSendInfo pdSendInfo = pdSendInfoManager.getPdSendInfo(siNo);
			response.setHeader("Content-Disposition", "inline; filename="+siNo+"label.pdf");
			Set set = pdSendInfo.getPdSendInfoDetails();
			Iterator iterator =set.iterator();
			List<String> list = new ArrayList();
			while(iterator.hasNext()){
				PdSendInfoDetail pdSendInfoDetail = (PdSendInfoDetail) iterator.next();
				list.add(pdSendInfoDetail.getLabelCode());
				
			}
			Base64Tool.listToPdf(list, out);
		}
		
		
		return null;
	}

}
