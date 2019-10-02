package com.joymain.jecs.po.webapp.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.pm.model.JpmCouponInfo;
import com.joymain.jecs.pm.service.JpmCouponInfoManager;
import com.joymain.jecs.po.model.JpoUserCoupon;
import com.joymain.jecs.po.service.JpoUserCouponManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class JpoUserCouponFormController extends BaseFormController {
    private JpoUserCouponManager jpoUserCouponManager = null;

    public void setJpoUserCouponManager(JpoUserCouponManager jpoUserCouponManager) {
        this.jpoUserCouponManager = jpoUserCouponManager;
    }
    public JpoUserCouponFormController() {
        setCommandName("jpoUserCoupon");
        setCommandClass(JpoUserCoupon.class);
    }
    
    private JmiMemberManager jmiMemberManager=null;
    private JpmCouponInfoManager jpmCouponInfoManager=null;
    
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

    public void setJpmCouponInfoManager(JpmCouponInfoManager jpmCouponInfoManager) {
		this.jpmCouponInfoManager = jpmCouponInfoManager;
	}
	protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JpoUserCoupon jpoUserCoupon = null;

        if (!StringUtils.isEmpty(id)) {
            jpoUserCoupon = jpoUserCouponManager.getJpoUserCoupon(id);
        } else {
            jpoUserCoupon = new JpoUserCoupon();
        }

        return jpoUserCoupon;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        SysUser defSysUser = SessionLogin.getLoginUser(request);

        boolean updateTrackingNo = this.updateTrackingNo(defSysUser,request, response, errors);
		if (updateTrackingNo) {
			MessageUtil.saveMessage(request, "模版导入成功");
			return new ModelAndView(getSuccessView());
		} else {
			return showForm(request, response, errors);
		}
    }
    private boolean updateTrackingNo(SysUser defSysUser, HttpServletRequest request, HttpServletResponse response,BindException errors) {
    	// 获得文件对象，然后获得文件字节流
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("couponFile");
		InputStream stream;
		Workbook wb;
		try {
			stream = file.getInputStream();
			wb = Workbook.getWorkbook(stream);
		} catch (Exception e) {
			errors.reject("notMessages", "系统异常");
			return false;
		}
		// 开始处理表单EXL中的数据
		
		Sheet sheet1 = wb.getSheet(0);
		int row = sheet1.getRows();// 总行数
		
		if (row >= 1002) {
			errors.reject("notMessages", "一次性上传数据不能超过1000条！");
			return false;
		}
		
		List<Map<String, String>> lm = new ArrayList<Map<String, String>>();
		for (int i = 1; i < row; i++) {
			int count = 0;
			Cell[] column = sheet1.getRow(i);
			try {
				// 判断下取到的值，判断如果为空的，就不用处理，可能exl文件中有空行的行（只是内容为空，但是行存在）
				if (column != null && column[0].getContents() != null && !"".equals(column[0].getContents())) {
					String userCode ="";
					if (column.length>=1) {
						userCode = StringUtils.isEmpty(column[0].getContents()) ? "" : column[0].getContents();// 用户编号
					}
					String couponName ="";
					if (column.length>=2) {
						couponName = StringUtils.isEmpty(column[1].getContents()) ? "" : column[1].getContents();// 代金券编号
					}
					String countNum ="";
					if (column.length>=3) {
						countNum = StringUtils.isEmpty(column[2].getContents()) ? "" : column[2].getContents();//数量
					}
					
					if (StringUtils.isEmpty(userCode)) {
						errors.reject("notMessages", "第" + i + "行《用户编号》不能为空，模版导入失败");
						count++;
					}else{
						JmiMember jmiMember = jmiMemberManager.getJmiMember(userCode);
						if (jmiMember==null) {
							errors.reject("notMessages", "第" + i + "行《用户编号》不存在，模版导入失败");
							count++;
						}
					}
					if (StringUtils.isEmpty(couponName)) {
						errors.reject("notMessages", "第" + i + "行《代金券名称》不能为空，模版导入失败");
						count++;
					} else {
						JpmCouponInfo byCouponName = jpmCouponInfoManager.getByCouponName(couponName);
						if (null==byCouponName) {
							errors.reject("notMessages", "第" + i + "行《代金券名称》，并无此代金券，模版导入失败");
							count++;
						}
					}
					if (StringUtils.isEmpty(countNum)) {
						errors.reject("notMessages", "第" + i + "行《赠送代金券数量》不能为空，模版导入失败");
						count++;
					} else {
						try {
							Integer.valueOf(countNum);
						} catch (Exception e) {
							errors.reject("notMessages", "第" + i + "行《赠送代金券数量》格式错误，模版导入失败");
							count++;
						}
					}
					
					// 不等于0说明模版有错误
					if (count == 0) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("userCode",userCode );
						map.put("couponName", couponName);
						map.put("countNum", countNum);
						lm.add(map);
					} else {
						return false;
					}
				}
				// 出现异常，导入失败
			} catch (Exception e) {
				errors.reject("notMessages", "第" + i + "行数据异常，模版导入失败");
				return false;
			}
		}
		// 保存数据
		for (Map<String, String> map : lm) {
			jpoUserCouponManager.saveJpoUserCouponMany(map);
		}
		return true;
	}
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		super.initBinder(request, binder);
	}
}
