package com.joymain.jecs.pm.webapp.action;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pm.model.JpmCardSeq;
import com.joymain.jecs.pm.service.JpmCardSeqManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpmCardSeqGradeController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpmCardSeqGradeController.class);
    private JpmCardSeqManager jpmCardSeqManager = null;

    public void setJpmCardSeqManager(JpmCardSeqManager jpmCardSeqManager) {
        this.jpmCardSeqManager = jpmCardSeqManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        SysUser defSysUser = SessionLogin.getLoginUser(request);
        


        String strAction=request.getParameter("strAction");

        CommonRecord crm=RequestUtil.toCommonRecord(request);


        crm.addField("grade", "grade");
        List gradeList=jpmCardSeqManager.getJpmCardSeqGrade(crm,"");
        request.setAttribute("gradeList", gradeList);
        

        if("rebuildGrade".equals(strAction)){
        	for (Object object : gradeList) {
	    		Map map=(Map)object;
	    		String id=map.get("id").toString();
	
				JpmCardSeq jpmCardSeq=jpmCardSeqManager.getJpmCardSeq(id);
				jpmCardSeq.setGrade("");
				jpmCardSeqManager.saveJpmCardSeq(jpmCardSeq);
				
        	}
			this.saveMessage(request, "还完成功");
	        return new ModelAndView("redirect:jpmCardSeqGrades.html?strAction=jpmCardSeqGrades");
			
        }
        
        
        

        String curGrade=request.getParameter("curGrade");
        String gradeNum=request.getParameter("gradeNum");
        String gradeBTime=request.getParameter("gradeBTime");
        String gradeETime=request.getParameter("gradeETime");
        
        if("getGrade".equals(strAction)){
        	
        	
        	if(StringUtil.isEmpty(curGrade)||!StringUtil.isInteger(gradeNum)||StringUtil.isEmpty(gradeBTime)||StringUtil.isEmpty(gradeETime)){

				MessageUtil.saveLocaleMessage(request, "请输入抽奖条件");
   		        return new ModelAndView("redirect:jpmCardSeqGrades.html?strAction=jpmCardSeqGrades");
        	}
        	
        	
        	
        	crm.addField("grade", "");
//        	crm.addField("gradeBTime", "");
//        	crm.addField("gradeETime", "");
        	
//        	boolean existGradeFlag=false;
//        	for (Object object : gradeList) {
//        		Map map=(Map)object;
//        		String grade=map.get("grade").toString();
//        		if(grade.equals(curGrade)){
//        			existGradeFlag=true;//已抽奖
//        		}
//        		
//        		
//        		
//			}
        	
//        	if(existGradeFlag){
//        		this.saveMessage(request, "已抽奖");
//        	}else{
	           	 List getGradeList=jpmCardSeqManager.getJpmCardSeqGrade(crm,strAction);
	           	 
	           	 if(getGradeList.isEmpty()){
	 				MessageUtil.saveLocaleMessage(request, "可抽奖号为空");
	   		        return new ModelAndView("redirect:jpmCardSeqGrades.html?strAction=jpmCardSeqGrades");
	           	 }
	           	 
	           	 
	           	 for (Object object : getGradeList) {
	   				Map map=(Map)object;
	   				String id=map.get("id").toString();
	   				JpmCardSeq jpmCardSeq=jpmCardSeqManager.getJpmCardSeq(id);
//	   				jpmCardSeq.setFlag("1");
	   				jpmCardSeq.setGrade(curGrade);
	   				jpmCardSeqManager.saveJpmCardSeq(jpmCardSeq);
	   			}
	
	   				this.saveMessage(request, "抽奖成功");
	   		        return new ModelAndView("redirect:jpmCardSeqGrades.html?strAction=jpmCardSeqGrades");
//        	}

        	
        }
        
        

        
//		crm.addField("userCode", defSysUser.getUserCode());
//		crm.addField("state", "1");
//		crm.addField("createBTime", "2012-4-21");
//		crm.addField("createETime", "2012-5-4");
		

        
        
        

        return new ModelAndView("pm/jpmCardSeqGradeList");
    }
}
