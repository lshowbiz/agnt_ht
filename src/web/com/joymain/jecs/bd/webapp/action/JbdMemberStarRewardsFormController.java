package com.joymain.jecs.bd.webapp.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.JbdMemberStarRewards;
import com.joymain.jecs.bd.service.JbdMemberStarRewardsManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdMemberStarRewardsFormController extends BaseFormController
{
    private JbdMemberStarRewardsManager jbdMemberStarRewardsManager;
    
    public void setJbdMemberStarRewardsManager(
            JbdMemberStarRewardsManager jbdMemberStarRewardsManager)
    {
        this.jbdMemberStarRewardsManager = jbdMemberStarRewardsManager;
    }
    
    public JbdMemberStarRewardsFormController()
    {
        setCommandName("jbdMemberStarRewards");
        setCommandClass(JbdMemberStarRewards.class);
    }
    
    protected Object formBackingObject(HttpServletRequest request)
            throws Exception
    {
        
        String id = request.getParameter("id");
        
        JbdMemberStarRewards jbdMemberStarRewards = (JbdMemberStarRewards)jbdMemberStarRewardsManager.getObject(JbdMemberStarRewards.class, Long.valueOf(id));
        
        return jbdMemberStarRewards;
    }
    
    public ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception
    {
        
        JbdMemberStarRewards jbdMemberStarRewards = (JbdMemberStarRewards) command;
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        jbdMemberStarRewards.setCreateUser(defSysUser.getUserCode());
        jbdMemberStarRewards.setCreateTime(new Date());
        jbdMemberStarRewardsManager.saveObject(jbdMemberStarRewards);
        this.saveMessage(request, "修改成功");
        
        return new ModelAndView(
                "redirect:jbdMemberStarRewards.html?userCode="
                        + jbdMemberStarRewards.getUserCode());
    }
    
    protected void initBinder(HttpServletRequest request,
            ServletRequestDataBinder binder)
    {
        super.initBinder(request, binder);
    }
}
