package com.joymain.jecs.po.webapp.action;


import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

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
import com.joymain.jecs.po.model.JpoInviteList;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoInviteListManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

public class JpoInviteListBatchController extends BaseFormController {
	private JpoInviteListManager jpoInviteListManager = null;
	
	private JmiMemberManager jmiMemberManager=null;

	public void setJpoInviteListManager(JpoInviteListManager jpoInviteListManager) {
		this.jpoInviteListManager = jpoInviteListManager;
	}

	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	public JpoInviteListBatchController() {
		setCommandName("jpoInviteList");
		setCommandClass(JpoInviteList.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		JpoInviteList jpoInviteList = new JpoInviteList();
		return jpoInviteList;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		
		SysUser defSysUser = SessionLogin.getLoginUser(request);
		
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
		boolean updateTrackingNo = this.updateTrackingNo(defSysUser,request, response, errors);
		if (updateTrackingNo) {
			MessageUtil.saveMessage(request, "模版导入成功");
			return new ModelAndView(getSuccessView());
		} else {
			return showForm(request, response, errors);
		}
	}

	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		super.initBinder(request, binder);
	}

	/**
	 * 操作处理函数
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private boolean updateTrackingNo(SysUser user,HttpServletRequest request, HttpServletResponse response, BindException errors)
			throws Exception {
		// 获得文件对象，然后获得文件字节流
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("inviteFile");
		InputStream stream = file.getInputStream();
		// 开始处理表单EXL中的数据
		Workbook wb = Workbook.getWorkbook(stream);
		Sheet sheet1 = wb.getSheet(0);
		Cell cell = null;
		int row = sheet1.getRows();// 总行数
		int col = sheet1.getColumns();// 总列数
		
		if (row >= 1002) {
			errors.reject("notMessages", "一次性上传数据不能超过1000条！");
			return false;
		}
		int errCount = 0;
		List<Map<String, String>> lm = new ArrayList<Map<String, String>>();
		for (int i = 1; i < row; i++) {
			int count = 0;
			Cell[] column = sheet1.getRow(i);
			try {
				// 判断下取到的值，判断如果为空的，就不用处理，可能exl文件中有空行的行（只是内容为空，但是行存在）
				if (column != null && column[0].getContents() != null && !"".equals(column[0].getContents())) {
					String returnStr = "";
					String siNo ="";
					if (column.length>=1) {
						siNo = StringUtils.isEmpty(column[0].getContents()) ? "" : column[0].getContents();// 用户编号
					}
					String recipientCaNo ="";
					if (column.length>=2) {
						recipientCaNo = StringUtils.isEmpty(column[1].getContents()) ? "" : column[1].getContents();// 数量
					}
					String orderNo ="";
					if (column.length>=3) {
						orderNo = StringUtils.isEmpty(column[2].getContents()) ? "" : column[2].getContents();//订单
					}
					
					String type ="";
					if (column.length>=4) {
						type = StringUtils.isEmpty(column[3].getContents()) ? "" : column[3].getContents();//备注
					}
					
					String remark ="";
					if (column.length>=5) {
						remark = StringUtils.isEmpty(column[4].getContents()) ? "" : column[4].getContents();//备注
					}
					
					String newDate ="";
					if (column.length>=6) {
						
						if (column[5].getType() == CellType.DATE) {
							DateCell dc = (DateCell) column[5];
							Date date = dc.getDate();
	                        TimeZone zone = TimeZone.getTimeZone("GMT");
	                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                        sdf.setTimeZone(zone);
	                        String sDate = sdf.format(date);
	                        newDate = sDate;
							
						}else{
							
							newDate = StringUtils.isEmpty(column[5].getContents()) ? "" : column[5].getContents();//创建时间
						}
						
					}
					
					if (StringUtils.isEmpty(siNo)) {
						errors.reject("notMessages", "第" + i + "行《用户编号》不能为空，模版导入失败");
						count++;
					}else{
						JmiMember jmiMember = jmiMemberManager.getJmiMember(siNo);
						if (jmiMember==null) {
							errors.reject("notMessages", "第" + i + "行《用户编号》不存在，模版导入失败");
							count++;
						}
					}
					if (StringUtils.isEmpty(recipientCaNo)) {
						errors.reject("notMessages", "第" + i + "行《数量》不能为空，模版导入失败");
						count++;
					} else {
						try {
							Integer.valueOf(recipientCaNo);
						} catch (Exception e) {
							errors.reject("notMessages", "第" + i + "行《数量》格式错误，模版导入失败");
							count++;
						}
						if (Integer.valueOf(recipientCaNo)<0) {
							List jpoInviteListByHql2 = jpoInviteListManager.getJpoInviteListByHql(siNo);
							int recipientCaNoCount = Math.abs(Integer.valueOf(recipientCaNo));
							if (jpoInviteListByHql2.size()<recipientCaNoCount) {
								errors.reject("notMessages", "第" + i + "行《数量》错误，该会员名下邀请码数量不足，模版导入失败");
								count++;
							}
						}
					}
					JpoMemberOrder jpoMemberOrder = null;
					//如果订单编号填写了，那么校验订单编号的有效性
					if(StringUtils.isNotBlank(orderNo)){
						jpoMemberOrder=jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(orderNo);
						if(null==jpoMemberOrder){
				    		errors.reject("notMessages", "第" + i + "行订单编号为"+orderNo+"的订单在系统中不存在,模版导入失败");
							count++;
				    	}
					}
					
					if (!MeteorUtil.isBlank(newDate)) {
						try {
							MeteorUtil.doConvertToDate(newDate);
						} catch (Exception e) {
							errors.reject("notMessages", "第" + i + "行《创建日期》格式错误，模版导入失败");
							count++;
						}
					}
					
					String typeTemp="";
					if (StringUtils.isEmpty(type)) {
						errors.reject("notMessages", "第" + i + "行《操作类型》不能为空，模版导入失败");
						count++;
					}else{
						if (type.equals("正常下单")) {
							typeTemp="1";
						}else if(type.equals("系统赠送")){
							typeTemp="2";
						}else{
							errors.reject("notMessages", "第" + i + "行《操作类型》输入异常，模版导入失败");
							count++;
						}
					}
					// 不等于0说明模版有错误
					if (count == 0) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("userCode", siNo);
						map.put("count", recipientCaNo);
						map.put("orderNo", orderNo);
						map.put("remark", remark);
						map.put("type", typeTemp);
						map.put("newDate", newDate);
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
			jpoInviteListManager.saveInviteCode(user,map.get("userCode"), Integer.valueOf(map.get("count")),map.get("orderNo"),map.get("remark"),map.get("type"),map.get("newDate"));
		}
		return true;
	}

}
