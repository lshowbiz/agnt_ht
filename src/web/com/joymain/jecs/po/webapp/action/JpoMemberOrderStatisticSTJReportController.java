package com.joymain.jecs.po.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.ConfigUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 生态家套餐导出
 * @author xiaoman001
 *
 */
public class JpoMemberOrderStatisticSTJReportController extends BaseController implements Controller {

	private JpoMemberOrderManager jpoMemberOrderManagerReport = null;
	
	private SysUserManager sysUserManager;
	
	public SysUserManager getSysUserManager() {
		return sysUserManager;
	}

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
//		if(RequestUtil.saveOperationSession(request,"poMemberOrderStatisticSTJ",20l)!=0){
//			return new ModelAndView("redirect:jpoEcoPackageStatistics.html");
//		}
		try{
			SysUser loginSysUser = SessionLogin.getLoginUser(request);
			
			CommonRecord crm = RequestUtil.toCommonRecord(request);
			crm.addField("companyCode", loginSysUser.getCompanyCode());
//			if (loginSysUser.getIsMember()) {
//				crm.addField("sysUser.userCode", loginSysUser.getUserCode());
//			}
			crm.addField("productFlag", "0");

			String proInfo = request.getParameter("proInfo");
			request.setAttribute("proInfo", 1);
			log.info("proInfo is:"+proInfo);
			
			Integer listNum = Integer.parseInt(ConfigUtil.getConfigValue(loginSysUser.getCompanyCode(), "export.listnum"));
			crm.setValue("listNum", listNum);
			List jpoMemberOrders = jpoMemberOrderManagerReport.getJpoMemberOrdersByCrm(crm, null);
			log.info("===============jpoMemberOrders集合数据大小："+jpoMemberOrders.size());
			//判断结果集合是否超过限度
			String key = "";
			if(jpoMemberOrders!=null && jpoMemberOrders.get(0)!=null && "1".equals(jpoMemberOrders.get(0).toString())){
				JpoMemberOrder jmo = new JpoMemberOrder();
				key = "导出数据数量超过最大限制数："+listNum+"，导出失败！请缩小查询条件范围！";
				jmo.setMoId(Long.parseLong(listNum.toString()));
				jmo.setIsRetreatOrder(key);
				jpoMemberOrders = new ArrayList();
				jpoMemberOrders.add(jmo);
			}else{
				if("1".equals(proInfo)){//导出报表包含明细
					Integer total = jpoMemberOrders.size();
					JpoMemberOrder jmoTemp = null;
					for(Object obj : jpoMemberOrders){
						jmoTemp = (JpoMemberOrder)obj;
						if(jmoTemp.getJpoMemberOrderList()!=null){
							total += jmoTemp.getJpoMemberOrderList().size();
						}
					}
					log.info("=====导出订单&商品数据量："+total);
					if(total>=65530){
						JpoMemberOrder jmo = new JpoMemberOrder();
						key = "导出订单数据(包含商品)数据量太大："+total+"，导出失败！请缩小查询条件范围！";
						jmo.setMoId(Long.parseLong(listNum.toString()));
						jmo.setIsRetreatOrder(key);
						jpoMemberOrders = new ArrayList();
						jpoMemberOrders.add(jmo);
					}
				}
			}
			
			bindProvince(jpoMemberOrders);
			
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename=STJOrder.xls");
			
			log.info("jpoMemberOrders list size is:"+jpoMemberOrders.size());
			if(!"".equals(key)){
				MessageUtil.saveMessage(request, key);  
			}
			
			return new ModelAndView("po/expOrderSTJ","jpoMemberOrderList", jpoMemberOrders);
		}catch(Exception e){
			log.error("",e);
			return null;
		}
		
	}
	
	
	/**
	 * 绑定省份城市名称
	 * @param jpoMemberOrders
	 * @throws Exception
	 */
	private void bindProvince(List<JpoMemberOrder> jpoMemberOrders)throws Exception{
		
		for (int i = 0; i < jpoMemberOrders.size(); i++) {
			
			JpoMemberOrder order = (JpoMemberOrder) jpoMemberOrders.get(i);

			SysUser link =  sysUserManager.getSysUser(order.getSysUser().getJmiMember().getLinkNo());
			SysUser recommand =  sysUserManager.getSysUser(order.getSysUser().getJmiMember().getRecommendNo());
			order.setLinkUserName(link.getUserName());
			order.setRecommandName(recommand.getUserName());
			
			
			switch(Integer.parseInt(order.getIsRetreatOrder())){
			case 1:
				order.setIsRetreatOrder(LocaleUtil.
						getLocalText("operation.notice.js.refunded")); //已退
				break;
			default:
				order.setIsRetreatOrder(LocaleUtil.
						getLocalText("member.status0")); //正常
				break;
			}
			
			switch (Integer.parseInt(order.getSysUser().getJmiMember().getPapertype())) {
			case 1:
				order.getSysUser().getJmiMember().setPapertype(LocaleUtil.
						getLocalText("member.papertype1")); //身份证
				break;

			default:
				order.getSysUser().getJmiMember().setPapertype(LocaleUtil.
						getLocalText("msgclassno.10")); //其他
				break;
			}
			
			
			
		}
	}
	
	public void setJpoMemberOrderManagerReport(
			JpoMemberOrderManager jpoMemberOrderManagerReport) {
		this.jpoMemberOrderManagerReport = jpoMemberOrderManagerReport;
	}

}