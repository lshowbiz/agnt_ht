package com.joymain.jecs.sys.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.service.SysListKeyManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

public class SysListKeyController implements Controller {
	private final Log log = LogFactory.getLog(SysListKeyController.class);
	private SysListKeyManager sysListKeyManager = null;

	public void setSysListKeyManager(SysListKeyManager sysListKeyManager) {
		this.sysListKeyManager = sysListKeyManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		CommonRecord crm = RequestUtil.toCommonRecord(request);
		// 设置分页参数
		Pager pager = new Pager("sysListKeyListTable", request, 20);
		// 分页获取数据
		List sysListKeys = sysListKeyManager.getSysListKeysByPage(crm, pager);
		// 根据数据重新设置分页数据
		request.setAttribute("sysListKeyListTable_totalRows", pager.getTotalObjects());

		return new ModelAndView("sys/sysListKeyList", Constants.SYSLISTKEY_LIST, sysListKeys);
	}
}