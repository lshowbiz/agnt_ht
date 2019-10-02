package com.joymain.jecs.vt.webapp.action;

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
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.vt.model.VtVote;
import com.joymain.jecs.vt.model.VtVoteDetail;
import com.joymain.jecs.vt.model.VtVotePow;
import com.joymain.jecs.vt.service.VtVoteDetailManager;
import com.joymain.jecs.vt.service.VtVoteManager;
import com.joymain.jecs.vt.service.VtVoteNoteManager;
import com.joymain.jecs.vt.service.VtVotePowManager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class VtVoteFormController extends BaseFormController {
    private VtVoteManager vtVoteManager = null;

	private AlCompanyManager alCompanyManager = null;
	private VtVoteDetailManager vtVoteDetailManager;
	private VtVotePowManager vtVotePowManager;
	private VtVoteNoteManager vtVoteNoteManager;
    public void setVtVoteNoteManager(VtVoteNoteManager vtVoteNoteManager) {
		this.vtVoteNoteManager = vtVoteNoteManager;
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
    public VtVoteFormController() {
        setCommandName("vtVote");
        setCommandClass(VtVote.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String vtId = request.getParameter("vtId");
        VtVote vtVote = null;

        if (!StringUtils.isEmpty(vtId)) {
            vtVote = vtVoteManager.getVtVote(vtId);
        } else {
            vtVote = new VtVote();
        }


		Map userTypes = ListUtil.getListOptions(SessionLogin.getLoginUser(request).getCompanyCode(), "vote.usertype");
		request.setAttribute("userTypes", userTypes);
		
		List alCompanys = alCompanyManager.getAlCompanysExceptAA();
		request.setAttribute("alCompanys", alCompanys);

		request.setAttribute("vtVoteDetailSize", vtVote.getVtVoteDetails().size());

		request.setAttribute("vtVoteDetails", vtVote.getVtVoteDetails().toArray());
		
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
    	
        VtVote vtVote = (VtVote) command;

        if(vtVote.getVtId()!=null&&(!"1".equals(vtVote.getStatus())||vtVoteNoteManager.getUserNotesCount(vtVote.getVtId())>0)){
			MessageUtil.saveLocaleMessage(request, "vote.modify.fail.1");
    		return new ModelAndView("redirect:editVtVote.html?strAction=editVtVote&vtId="+vtVote.getVtId());
        }
        
        String options[]=request.getParameterValues("optionSelect");
		String rights[] = request.getParameterValues("right");
		
        if ("deleteVtVote".equals(request.getParameter("strAction"))) {
        	for (VtVoteDetail vtVoteDetail : vtVote.getVtVoteDetails()) {
				if(vtVoteDetail.getVtVoteNotes().size()>0){
					this.saveMessage(request, "vtVoteNotes.exist");
	        		return showForm(request, response, errors);
				}
			}
        	vtVoteManager.removeVtVote(vtVote.getVtId().toString());
			this.saveMessage(request, "bdOutWardBank.deleteSuccess");
        }else{
        	if(checkVtVote(vtVote, errors)){
        		return showForm(request, response, errors);
        	}
        	if(options.length==0){
        		return showForm(request, response, errors);
        		
        	}
        	if(vtVote.getVtId()==null){
        		vtVote.setStatus("1");
        		vtVote.setCreateTime(new Date());
        		vtVote.setCreateUser(defSysUser.getUserCode());
        		vtVote.setCompanyCode(defSysUser.getCompanyCode());
        		vtVote.setState("1");
        	}else{
        		vtVotePowManager.removeVtVotePowsByVtId(vtVote.getVtId());
        		vtVoteDetailManager.removeVtVoteDetailByVtId(vtVote.getVtId());
        	}
        	for (int i = 0; i < options.length; i++) {
        		if(!StringUtils.isEmpty(options[i])){
            		VtVoteDetail vtVoteDetail=new VtVoteDetail();
            		vtVoteDetail.setOrderNo(new Long(i));
            		vtVoteDetail.setContent(options[i]);
            		vtVoteDetail.setVtVote(vtVote);
            		vtVote.getVtVoteDetails().add(vtVoteDetail);
        		}
			}
        	if(rights!=null){
            	for (String right : rights) {
            		String[] curOption = right.split("-");
            		VtVotePow vtVotePow=new VtVotePow();
            		vtVotePow.setCompanyCode(curOption[0]);
            		vtVotePow.setUserType(curOption[1]);
            		vtVotePow.setVtVote(vtVote);
            		vtVote.getVtVotePows().add(vtVotePow);
    			}
        	}
        	vtVoteManager.saveVtVote(vtVote);
			MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
        }
		
        return new ModelAndView("redirect:vtVotes.html?strAction=vtVotes");
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}

    private boolean checkVtVote(VtVote vtVote,BindException errors){
    	boolean isNotPass=false;
    	if (StringUtil.isEmpty(vtVote.getSubject())){
			errors.rejectValue("subject", "isNotNull",new Object[] { this.getText("amAnnounce.subject") }, "");
			//errors.reject("shippingFirstName.isNotNull");
			isNotPass = true;
    	}
    	return isNotPass;
    }
}
