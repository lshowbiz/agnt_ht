package com.joymain.jecs.bd.webapp.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.JbdMemberCongrations;
import com.joymain.jecs.bd.service.JbdMemberCongrationsManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdMemberCongrationsFormController extends BaseFormController
{
    private JbdMemberCongrationsManager jbdMemberCongrationsManager;

    public void setJbdMemberCongrationsManager(
            JbdMemberCongrationsManager jbdMemberCongrationsManager)
    {
        this.jbdMemberCongrationsManager = jbdMemberCongrationsManager;
    }
    
    public JbdMemberCongrationsFormController()
    {
        setCommandName("jbdMemberCongrations");
        setCommandClass(JbdMemberCongrations.class);
    }
    
    protected Object formBackingObject(HttpServletRequest request)
            throws Exception
    {
        
        String id = request.getParameter("id");
        
        JbdMemberCongrations jbdMemberCongrations = (JbdMemberCongrations)jbdMemberCongrationsManager.getObject(JbdMemberCongrations.class, Long.valueOf(id));
        
        return jbdMemberCongrations;
    }
    
    public ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception
    {
        
        JbdMemberCongrations jbdMemberCongrations = (JbdMemberCongrations) command;
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        
        String chineseName = jbdMemberCongrations.getChineseName();
        String englishName = jbdMemberCongrations.getEnglishName();
        
        String url = "redirect:jbdMemberCongrations.html?userCode="
                + jbdMemberCongrations.getUserCode()+"&yearMonth="+jbdMemberCongrations.getYearMonth()+"&starLevel="+jbdMemberCongrations.getStarLevel();
        
        if(StringUtils.isEmpty(chineseName)||StringUtils.isEmpty(englishName)) {
            this.saveMessage(request, "英文名及中文名不能为空！");
            return new ModelAndView(url);
        }
        
        jbdMemberCongrations.setCreateUser(defSysUser.getUserCode());
        jbdMemberCongrations.setCreateTime(new Date());
        jbdMemberCongrationsManager.saveObject(jbdMemberCongrations);
        this.saveMessage(request, "修改成功");
        return new ModelAndView(url);
    }
    
    protected void initBinder(HttpServletRequest request,
            ServletRequestDataBinder binder)
    {
        super.initBinder(request, binder);
    }
}
