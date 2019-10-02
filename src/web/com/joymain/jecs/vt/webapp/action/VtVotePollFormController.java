package com.joymain.jecs.vt.webapp.action;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.mi.model.JmiAddrBook;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysModuleManager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.vt.model.VtVote;
import com.joymain.jecs.vt.model.VtVoteDetail;
import com.joymain.jecs.vt.model.VtVoteNote;
import com.joymain.jecs.vt.model.VtVotePow;
import com.joymain.jecs.vt.service.VtVoteDetailManager;
import com.joymain.jecs.vt.service.VtVoteManager;
import com.joymain.jecs.vt.service.VtVoteNoteManager;
import com.joymain.jecs.vt.service.VtVotePowManager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class VtVotePollFormController extends BaseFormController {
    private VtVoteManager vtVoteManager = null;
    private VtVoteNoteManager vtVoteNoteManager;
	private AlCompanyManager alCompanyManager = null;
	private VtVoteDetailManager vtVoteDetailManager;
	private VtVotePowManager vtVotePowManager;
	private SysModuleManager sysModuleManager;
    public void setSysModuleManager(SysModuleManager sysModuleManager) {
		this.sysModuleManager = sysModuleManager;
	}
	public void setVtVoteDetailManager(VtVoteDetailManager vtVoteDetailManager) {
		this.vtVoteDetailManager = vtVoteDetailManager;
	}
	public void setVtVotePowManager(VtVotePowManager vtVotePowManager) {
		this.vtVotePowManager = vtVotePowManager;
	}
	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}
	public void setVtVoteManager(VtVoteManager vtVoteManager) {
        this.vtVoteManager = vtVoteManager;
    }
	public void setVtVoteNoteManager(VtVoteNoteManager vtVoteNoteManager) {
		this.vtVoteNoteManager = vtVoteNoteManager;
	}
    public VtVotePollFormController() {
        setCommandName("vtVote");
        setCommandClass(VtVote.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
    	SysUser defSysUser=SessionLogin.getLoginUser(request);
    	
        String vtId = request.getParameter("vtId");
        VtVote vtVote = null;

        if (!StringUtils.isEmpty(vtId)) {
            vtVote = vtVoteManager.getVtVote(vtId);
        } else {
            vtVote = new VtVote();
        }
        
        //查出投票结果
        List resList=vtVoteNoteManager.getUserNotesForVtVote(vtVote.getVtId());
    	request.setAttribute("resList", resList);
    	
    	//判断是否过期，过期则不可以投票
    	Date curDate=new Date();
    	Date startDate=vtVote.getStartTime();
    	Date endDate=vtVote.getEndTime();
    	boolean flag=false;
    	if(startDate!=null&&curDate.before(startDate)){
    		flag=true;
    	}
    	if(endDate!=null&&curDate.after(endDate)){
    		flag=true;
    	}
    	
    	//查出会员有没有投过票
        Long detailCount=vtVoteNoteManager.getUserNotes(defSysUser.getUserCode(), vtVote.getVtId());
        if(detailCount>0 || "0".equals(vtVote.getState())||flag){
        	request.setAttribute("detailCount", detailCount);
        }
        if(sysModuleManager.getSysUserPowerByCode(defSysUser, "viewVoteResult")||"AA".equals(defSysUser.getCompanyCode())){
        	request.setAttribute("viewVoteResult", "viewVoteResult");
        }
        return vtVote;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

    	SysUser defSysUser=SessionLogin.getLoginUser(request);
    	String userType=defSysUser.getUserType().equals("M")?defSysUser.getJmiMember().getCardType():defSysUser.getUserType();
    	
        VtVote vtVote = (VtVote) command;

        //判断有没有选中
        String selectedOptions[]=request.getParameterValues("selectedOption");
        if(selectedOptions==null||selectedOptions.length==0){
    		this.saveMessage(request, "vote.detail.select");
    		return showForm(request, response, errors);
        }
        //判断选项是否大于可选项
        if(selectedOptions.length>vtVote.getOptionNum()){
        	String message = MessageFormat.format(this.getText("vote.detail.select.num"), new String[]{vtVote.getOptionNum().toString()});
    		this.saveMessage(request, message);
    		return showForm(request, response, errors);
        }
        
        
        //单选
        if(vtVote.getOptionNum()==1){
        		this.saveVtVoteNote(selectedOptions[0], defSysUser, userType);
        }else{//多选
        	for (String ss : selectedOptions) {
            	this.saveVtVoteNote(ss, defSysUser, userType);
			}
        }
		this.saveMessage(request, "vtVote.success");
     
        return new ModelAndView("redirect:editVtVotePoll.html?strAction=editVtVotePoll&vtId="+vtVote.getVtId());
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
    private void saveVtVoteNote(String vdId,SysUser defSysUser,String userType){
    	VtVoteNote vtVoteNote=new VtVoteNote();
    	VtVoteDetail vtVoteDetail=vtVoteDetailManager.getVtVoteDetail(vdId);
    	vtVoteNote.setVtVoteDetail(vtVoteDetail);
    	vtVoteNote.setCompanyCode(defSysUser.getCompanyCode());
    	vtVoteNote.setCreateTime(new Date());
    	vtVoteNote.setUserCode(defSysUser.getUserCode());
    	vtVoteNote.setUserType(userType);
    	vtVoteNoteManager.saveVtVoteNote(vtVoteNote);
    }
}
