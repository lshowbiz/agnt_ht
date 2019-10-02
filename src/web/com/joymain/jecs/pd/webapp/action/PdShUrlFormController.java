package com.joymain.jecs.pd.webapp.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.pd.model.PdShUrl;
import com.joymain.jecs.pd.service.PdShUrlManager;
import com.joymain.jecs.sys.model.SysUser;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdShUrlFormController extends BaseFormController {
    private PdShUrlManager pdShUrlManager = null;

    public void setPdShUrlManager(PdShUrlManager pdShUrlManager) {
        this.pdShUrlManager = pdShUrlManager;
    }
    public PdShUrlFormController() {
        setCommandName("pdShUrl");
        setCommandClass(PdShUrl.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        PdShUrl pdShUrl = null;
        
        //区别功能的参数
        String strAction = request.getParameter("strAction");
        request.setAttribute("strAction",strAction);
        
        if (!StringUtils.isEmpty(id)) {
            pdShUrl = pdShUrlManager.getPdShUrl(id);
        } else {
            pdShUrl = new PdShUrl();
        }

        return pdShUrl;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
     
        PdShUrl pdShUrl = (PdShUrl) command;
        SysUser defUser = SessionLogin.getLoginUser(request);
        String userCode = defUser.getUserCode();
        String strAction = request.getParameter("strAction");
        request.setAttribute("strAction",strAction);
        if("pdShUrlAdd".equals(strAction)|| "pdShUrlQueryUpdate".equals(strAction)){
        	  //不为空的校验
        	  boolean checkEmpty = pdShUrlManager.getCheckEmpty(pdShUrl,errors,defUser.getDefCharacterCoding());  
        	  if(checkEmpty){
        		  return showForm(request,response,errors);
        	  }      	  
        	  //物流公司编码唯一性校验
        	  boolean valueCodeUniqueCheck = pdShUrlManager.getValueCodeUniqueCheck(pdShUrl);
        	  if(valueCodeUniqueCheck){
        		    this.saveMessage(request, "物流公司编码已经存在，请重新输入物流公司编码！");
		        	return showForm(request, response,errors);				
        	  }       	  
        	  if("pdShUrlAdd".equals(strAction)){
        		  pdShUrl.setCreateUserCode(userCode);
        		  pdShUrl.setCreateTime(new Date());
        		  pdShUrlManager.savePdShUrl(pdShUrl);
        		  this.saveMessage(request, "新增成功！");
        	  }else{
        		  pdShUrlManager.savePdShUrl(pdShUrl);
        		  this.saveMessage(request, "更新成功！");
        	  }        	  
        }else{
        	 if(null!=pdShUrl.getId()){
        	     pdShUrlManager.removePdShUrl(pdShUrl.getId().toString());
        	     this.saveMessage(request, "删除成功！");
        	 }
        }       
        return new ModelAndView(getSuccessView());
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		super.initBinder(request, binder);
	}
}
