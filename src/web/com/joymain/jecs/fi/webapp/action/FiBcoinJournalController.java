package com.joymain.jecs.fi.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.fi.model.FiBcoinJournal;
import com.joymain.jecs.fi.service.FiBcoinJournalManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class FiBcoinJournalController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiBcoinJournalController.class);
    private FiBcoinJournalManager fiBcoinJournalManager;

    public void setFiBcoinJournalManager(FiBcoinJournalManager fiBcoinJournalManager) {
        this.fiBcoinJournalManager = fiBcoinJournalManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        String strAction = request.getParameter("strAction") != null ? request.getParameter("strAction") : "";
        if (strAction.equals("viewFiBcoinJournal")) {
        	String journalId = request.getParameter("journalId");
        	FiBcoinJournal fiBcoinJournal= fiBcoinJournalManager.getFiBcoinJournal(journalId);
    		if(!StringUtils.isEmpty(fiBcoinJournal.getRemark())){
    			fiBcoinJournal.setRemark(StringUtils.replace(fiBcoinJournal.getRemark(), "\n", "<br>"));
    		}
    		
    		if(!StringUtils.isEmpty(fiBcoinJournal.getNotes())){
    			fiBcoinJournal.setNotes(StringUtils.replace(fiBcoinJournal.getNotes(), "\n", "<br>"));
    		}
    		request.setAttribute("fiBcoinJournal", fiBcoinJournal);
    		return new ModelAndView("fi/viewFiBcoinJournals");
        }
        SysUser su = SessionLogin.getLoginUser(request);
        String userCode = su.getUserCode();
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        /** add by hdg 2016-12-23 */
        CustomField[] fields = crm.getFields();
		w:for(CustomField field : fields) {
			String name=field.getName();
			if(!StringUtils.isEmpty(name)) {
				if("dealdateend".equals(name)) {
					String value = (String)field.getValue();
					if(!StringUtils.isEmpty(value)) {
						field.setValue(value+" 23:59:59");
					}
					break w;
				}
			}
		}
		 /** add by hdg 2016-12-23 */
        if("M".equals(su.getUserType())){
        	crm.addField("userCode", userCode);
        }
        Pager pager = new Pager("fiBcoinJournalListTable", request, 20);
        List fiBcoinJournals = fiBcoinJournalManager.getFiBcoinJournalsByCrm(crm, pager);
        if (fiBcoinJournals != null) {
        	request.setAttribute("fiBcoinJournalList", fiBcoinJournals);
        }
        
        //Modify By WuCF 20140320 查询结果的总计 
		Map sumMap = this.fiBcoinJournalManager.getSumAmountByCrm(crm);
		request.setAttribute("sumMap", sumMap); 
        
        //根据数据重新设置分页数据
		request.setAttribute("fiBcoinJournalListTable_totalRows", pager.getTotalObjects());
        return new ModelAndView("fi/fiBcoinJournals");
    }
}
