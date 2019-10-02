package com.joymain.jecs.al.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.al.model.AlCharacterCoding;
import com.joymain.jecs.al.model.AlCharacterKey;
import com.joymain.jecs.al.model.AlCharacterValue;
import com.joymain.jecs.al.service.AlCharacterCodingManager;
import com.joymain.jecs.al.service.AlCharacterKeyManager;
import com.joymain.jecs.al.service.AlCharacterValueManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.listener.StartupListener;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class AlCharacterValueChangeController implements Controller {
	private final Log log = LogFactory.getLog(AlCharacterValueChangeController.class);
	private AlCharacterValueManager alCharacterValueManager = null;
	private AlCharacterKeyManager alCharacterKeyManager;
	private AlCharacterCodingManager alCharacterCodingManager;
	public void setAlCharacterCodingManager(
			AlCharacterCodingManager alCharacterCodingManager) {
		this.alCharacterCodingManager = alCharacterCodingManager;
	}

	public void setAlCharacterKeyManager(AlCharacterKeyManager alCharacterKeyManager) {
		this.alCharacterKeyManager = alCharacterKeyManager;
	}

	public void setAlCharacterValueManager(AlCharacterValueManager alCharacterValueManager) {
		this.alCharacterValueManager = alCharacterValueManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

        SysUser defSysUser = SessionLogin.getLoginUser(request);
        


		String referral=request.getParameter("referral");
		String menuLan=request.getParameter("menuLan");
		
			
			
		if("post".equalsIgnoreCase(request.getMethod()) && "updateLan".equals(request.getParameter("strAction"))){
			String[] lanKey=request.getParameterValues("lanKey");
			String[] keyId=request.getParameterValues("keyId");
			String[] lanVal=request.getParameterValues("lanVal");
			List<AlCharacterValue> updateLanList=new ArrayList<AlCharacterValue>();
			for (int i = 0; i < lanKey.length; i++) {
				if("".equals(lanKey[i])){
					AlCharacterValue alCharacterValueKeyNull=new AlCharacterValue();
					AlCharacterKey alCharacterKey=alCharacterKeyManager.getAlCharacterKey(keyId[i]);
					alCharacterValueKeyNull.setAlCharacterKey(alCharacterKey);
					alCharacterValueKeyNull.setCharacterValue(lanVal[i]);

					alCharacterValueKeyNull.setCharacterCode(menuLan);
					updateLanList.add(alCharacterValueKeyNull);
				}else{
					AlCharacterValue alCharacterValue=alCharacterValueManager.getAlCharacterValue(lanKey[i]);
					alCharacterValue.setCharacterValue(lanVal[i]);
					updateLanList.add(alCharacterValue);
				}
			}
			try {
				alCharacterValueManager.saveAlCharacterValues(updateLanList);
				StartupListener.setupContext(request.getSession().getServletContext(),true);
				MessageUtil.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
			} catch (Exception e) {
				MessageUtil.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateFail"));
			}
			
			return new ModelAndView("redirect:"+request.getHeader("referer"));
		}
		

		
		CommonRecord crm=RequestUtil.toCommonRecord(request);	
		if(StringUtil.isEmpty(referral)){
			referral=defSysUser.getDefCharacterCoding();
		}
		if(referral.equals(menuLan)){
			referral="zh_TW";
			if("zh_TW".equals(menuLan)){
				referral="zh_CN";
			}
		}
		crm.addField("referral", referral);
		request.setAttribute("referral", referral);
		request.setAttribute("menuLan", menuLan);

		// 设置分页参数		
		Pager pager = new Pager("alCharacterValueChangeListTable",request, 20);
		
		List<AlCharacterCoding> alLanguages = alCharacterCodingManager.getAlCharacterCodings(null);
		boolean flag=false;
		for (AlCharacterCoding coding : alLanguages) {
			if(menuLan.equals(coding.getCharacterCode())){
				flag=true;
			}
			if(menuLan.equals(coding.getCharacterCode())&&(StringUtil.isEmpty(coding.getAllowedUser())||!coding.getAllowedUser().contains(defSysUser.getUserCode()))){

				request.setAttribute("alCharacterValueChangeListTable_totalRows", pager.getTotalObjects());
				return new ModelAndView("al/alCharacterValueChangeList", "alCharacterValueChangeList", null);
			}
		}
		if(!flag){

			request.setAttribute("alCharacterValueChangeListTable_totalRows", pager.getTotalObjects());
			return new ModelAndView("al/alCharacterValueChangeList", "alCharacterValueChangeList", null);
		}
		for (AlCharacterCoding coding : alLanguages) {
			if(coding.getCharacterCode().equals(menuLan)){
				alLanguages.remove(coding);
				break;
			}
		}
		request.setAttribute("alLanguages", alLanguages);
		
		//分页获取数据
		List alCharacterValueChangeList=alCharacterValueManager.getAlCharacterValuesByTypeSQL(crm, pager);
		//根据数据重新设置分页数据
		request.setAttribute("alCharacterValueChangeListTable_totalRows", pager.getTotalObjects());
		return new ModelAndView("al/alCharacterValueChangeList", "alCharacterValueChangeList", alCharacterValueChangeList);
	}
}
