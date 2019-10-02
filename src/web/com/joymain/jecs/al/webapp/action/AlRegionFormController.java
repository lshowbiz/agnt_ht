package com.joymain.jecs.al.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.model.AlRegion;
import com.joymain.jecs.al.service.AlRegionManager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class AlRegionFormController extends BaseFormController {
    private AlRegionManager alRegionManager = null;

    public void setAlRegionManager(AlRegionManager alRegionManager) {
        this.alRegionManager = alRegionManager;
    }
    public AlRegionFormController() {
        setCommandName("alRegion");
        setCommandClass(AlRegion.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String regionId = request.getParameter("regionId");
        AlRegion alRegion = null;

        if (!StringUtils.isEmpty(regionId)) {
            alRegion = alRegionManager.getAlRegion(regionId);
        } else {
            alRegion = new AlRegion();
        }

        return alRegion;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        AlRegion alRegion = (AlRegion) command;
        boolean isNew = (alRegion.getRegionId() == null);

        if (request.getParameter("delete") != null) {
            alRegionManager.removeAlRegion(alRegion.getRegionId().toString());

            saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(),"alRegion.deleted"));
        } else {
            alRegionManager.saveAlRegion(alRegion);

            String key = (isNew) ? "alRegion.added" : "alRegion.updated";
            saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(),key));

            if (!isNew) {
                return new ModelAndView("redirect:editAlRegion.html", "regionId", alRegion.getRegionId());
            }
        }

        return new ModelAndView(getSuccessView());
    }
}
