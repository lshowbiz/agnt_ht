package com.joymain.jecs.al.webapp.action;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCharacterKey;
import com.joymain.jecs.al.model.AlCharacterType;
import com.joymain.jecs.al.service.AlCharacterKeyManager;
import com.joymain.jecs.al.service.AlCharacterTypeManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;

public class AlCharacterKeyController implements Controller {
	private final Log log = LogFactory.getLog(AlCharacterKeyController.class);
	private AlCharacterKeyManager alCharacterKeyManager = null;
	private AlCharacterTypeManager alCharacterTypeManager = null;

	public void setAlCharacterTypeManager(AlCharacterTypeManager alCharacterTypeManager) {
		this.alCharacterTypeManager = alCharacterTypeManager;
	}

	public void setAlCharacterKeyManager(AlCharacterKeyManager alCharacterKeyManager) {
		this.alCharacterKeyManager = alCharacterKeyManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		
		if("post".equalsIgnoreCase(request.getMethod()) && "changeAlCharacterKeyType".equalsIgnoreCase(request.getParameter("strAction"))){
			String newTypeId=request.getParameter("newTypeId");
			
			String keyIds=request.getParameter("keyIds");
			if(!StringUtils.isEmpty(keyIds)){
				String[] selectedIds=keyIds.split(",");
				if(selectedIds!=null && selectedIds.length>0){
					AlCharacterType alCharacterType=this.alCharacterTypeManager.getAlCharacterType(newTypeId);
					
					List<AlCharacterKey> alCharacterKeys=new ArrayList<AlCharacterKey>();
					for(int i=0; i<selectedIds.length;i++){
						AlCharacterKey alCharacterKey=this.alCharacterKeyManager.getAlCharacterKey(selectedIds[i]);
						alCharacterKey.setAlCharacterType(alCharacterType);
						alCharacterKeys.add(alCharacterKey);
					}
					this.alCharacterKeyManager.saveAlCharacterKeys(alCharacterKeys);
				}
			}
			MessageUtil.saveMessage(request, LocaleUtil.getLocalText("alCharacterKey.changeType.successfully"));
			
			String decodedQueryString  = URLDecoder.decode(request.getParameter("encodedQueryString"), "UTF-8");

			return new ModelAndView("redirect:alCharacterKeys.html?" + decodedQueryString);
		}
		CommonRecord crm=RequestUtil.toCommonRecord(request);
		// 设置分页参数		
		Pager pager = new Pager("alCharacterKeyListTable",request, 20);
		//分页获取数据
		List alCharacterKeys = alCharacterKeyManager.getAlCharacterKeysByPage(crm,pager);
		//根据数据重新设置分页数据
		request.setAttribute("alCharacterKeyListTable_totalRows", pager.getTotalObjects());
		
		//设置跳转地址
		if (!StringUtils.isEmpty(request.getQueryString())) {
			request.setAttribute("encodedQueryString", URLEncoder.encode(request.getQueryString(), "UTF-8"));
		}
		//

		return new ModelAndView("al/alCharacterKeyList", Constants.ALCHARACTERKEY_LIST, alCharacterKeys);
	}
}