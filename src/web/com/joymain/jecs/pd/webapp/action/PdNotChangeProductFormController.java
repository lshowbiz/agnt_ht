package com.joymain.jecs.pd.webapp.action;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.pd.model.PdNotChangeProduct;
import com.joymain.jecs.pd.service.PdNotChangeProductManager;
import com.joymain.jecs.sys.model.SysUser;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;


public class PdNotChangeProductFormController extends BaseFormController {
    private PdNotChangeProductManager pdNotChangeProductManager = null;

    public void setPdNotChangeProductManager(PdNotChangeProductManager pdNotChangeProductManager) {
        this.pdNotChangeProductManager = pdNotChangeProductManager;
    }
    public PdNotChangeProductFormController() {
        setCommandName("pdNotChangeProduct");
        setCommandClass(PdNotChangeProduct.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        PdNotChangeProduct pdNotChangeProduct = null;
        String strAction = request.getParameter("strAction");

        if (!StringUtils.isEmpty(id)) {
            pdNotChangeProduct = pdNotChangeProductManager.getPdNotChangeProduct(id);
        } else {
            pdNotChangeProduct = new PdNotChangeProduct();
        }
		request.setAttribute("strAction", strAction);

        return pdNotChangeProduct;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        PdNotChangeProduct pdNotChangeProduct = (PdNotChangeProduct) command;
        SysUser defSysUser = SessionLogin.getLoginUser(request);

        boolean isNew = (pdNotChangeProduct.getId() == null);
        Locale locale = request.getLocale();
		String key=null;
		String strAction = request.getParameter("strAction");
		if ("deletePdNotChangeProduct".equals(strAction)  ) {
			pdNotChangeProductManager.removePdNotChangeProduct(pdNotChangeProduct.getId().toString());
			key="bdOutWardBank.deleteSuccess";
			this.saveMessage(request, "删除成功！");
		}else{
			boolean checkResult = pdNotChangeProductManager.getPdNotChangeProductCheckResutl(pdNotChangeProduct,errors,defSysUser.getDefCharacterCoding());
			if(checkResult){
				String createUserCode = pdNotChangeProduct.getCreateUserCode();
				if(StringUtil.isEmpty(createUserCode)){
					pdNotChangeProduct.setCreateUserCode(defSysUser.getUserCode());
				}
				pdNotChangeProductManager.savePdNotChangeProduct(pdNotChangeProduct);
				key="saveOrUpdate.success";
				this.saveMessage(request, "保存成功！");
			}else{
	        	return showForm(request,response,errors);
			}
		}
		  return new ModelAndView("redirect:pdNotChangeProducts.html?strAction=pdNotChangeProductQuery");
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		super.initBinder(request, binder);
	}
}
