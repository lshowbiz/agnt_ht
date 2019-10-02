package com.joymain.jecs.bd.webapp.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.jdbc.core.JdbcTemplate;

import service.MsgSendService;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.pm.model.JpmSmssendInfo;
import com.joymain.jecs.pm.service.JpmProductSaleNewManager;
import com.joymain.jecs.sys.model.JocsInterfaceLog;
import com.joymain.jecs.util.ListUtil;
import com.joymain.jecs.util.ReportLogUtilService;
import com.joymain.jecs.util.SmsSend;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.string.StringUtil;

public class JbdPassStarReSaleQuartzController {

	private BdPeriodManager bdPeriodManager=null;
	private JpmProductSaleNewManager jpmProductSaleNewManager;
	public void setJpmProductSaleNewManager(
			JpmProductSaleNewManager jpmProductSaleNewManager) {
		this.jpmProductSaleNewManager = jpmProductSaleNewManager;
	}
	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	private JmiMemberManager jmiMemberManager;
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	private static final MsgSendService SEND = new MsgSendService();
	/**
	 * 准奖衔短信提示
	 */
	public void getSendSms(){

		Date curDate=new Date();
		BdPeriod miBdPeriod=bdPeriodManager.getBdPeriodByTime(curDate, null);
		String mi_wyear= miBdPeriod.getWyear()+""+ (miBdPeriod.getWweek()<10?"0" + miBdPeriod.getWweek():miBdPeriod.getWweek());
		String sql="select t.user_code,t.pass_star,b.mobiletele from jbd_day_bouns_calc t ,jmi_member b ,jsys_user c  where t.user_code=b.user_code  and t.pass_star > 20  and b.exit_date is null and b.user_code=c.user_code and c.state='1' and t.w_week = "+mi_wyear+unsend();
		
		List list=jdbcTemplate.queryForList(sql);
		

		String url1 = ListUtil.getListValue("CN", "smssend.url", "1");
		String url2 = ListUtil.getListValue("CN", "smssend.url", "2");
		
		if(!list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				Map map=(Map) list.get(i);
				String user_code=map.get("user_code").toString();
				String pass_star=map.get("pass_star").toString();
				String mobiletele=map.get("mobiletele")+"";


				if(!StringUtil.isEmpty(mobiletele) && mobiletele.length()==11 ){
					String msgInfo  = "尊敬的"+user_code+"：您好！您"+mi_wyear+"财月已完成相关业绩考核，请登录系统完成个人指标，确保达成合格奖衔。此短信为实时业绩提醒，仅供参考。";
					//调用短信发送平台方法
					
					//System.out.println("===========");
					try {
						SmsSend.sendSms(url1, url2, mobiletele, msgInfo);
						
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					//System.out.println(">>>>>>>>>>>>");
					JpmSmssendInfo jpmSmssendInfo = new JpmSmssendInfo();
					jpmSmssendInfo.setSmsType("6");////短信类型  例如：1：仓库发货确认    2：找回密码   3：电影票  目前只有三种，在列表中配置
					jpmSmssendInfo.setSmsRecipient(user_code);//短信接收人:用户会员编号
					jpmSmssendInfo.setSmsMessage(msgInfo);//短信内容
					jpmSmssendInfo.setSmsTime(new Date());//发送时间
					jpmSmssendInfo.setSmsOperator("global");//操作人，可以填写空
					jpmSmssendInfo.setSmsStatus("1");//保留字段，是否发送成功！ 默认为1：成功！ 现在短信还不能知道是否发送成功，没有返回值！
					jpmSmssendInfo.setRemark("奖衔考核重消提醒");//备注
					jpmSmssendInfo.setPhoneNum(mobiletele);//手机号
					jpmProductSaleNewManager.saveJpmSmssendInfo(jpmSmssendInfo);
				}
				
				
				
				
				
			}
		}
		
	}
	

	public void getValidAndFreezeSendSms(){

		Date curDate=new Date();
		BdPeriod miBdPeriod=bdPeriodManager.getBdPeriodByTime(curDate, null);
		String mi_wyear= miBdPeriod.getWyear()+""+ (miBdPeriod.getWmonth()<10?"0" + miBdPeriod.getWmonth():miBdPeriod.getWmonth());
		String sql="select  t.user_code,t.mobiletele from jmi_member t,jsys_user c  where t.active='0' and t.freeze_status=0 and t.exit_date is  null and t.user_code not in (select user_code from jbd_member_frozen) and c.user_code=t.user_code and c.state='1' and t.valid_week="+mi_wyear+unsend();
		List list=jdbcTemplate.queryForList(sql);
		
		String url1 = ListUtil.getListValue("CN", "smssend.url", "1");
		String url2 = ListUtil.getListValue("CN", "smssend.url", "2");

		//System.out.println("1>>>>>>>>>>>>"+sql);
		if(!list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				Map map=(Map) list.get(i);
				String user_code=map.get("user_code").toString();
				String mobiletele=map.get("mobiletele")+"";


				if(!StringUtil.isEmpty(mobiletele) && mobiletele.length()==11 ){
					String msgInfo  = "尊敬的"+user_code+"：您好！您距您经销商资格有效截止期还有15天，请在15天内登录系统完成个人指标，以保持您的经销资格，谢谢！如有疑问，请联系客服。";
					try {
						SmsSend.sendSms(url1, url2, mobiletele, msgInfo);
					} catch (Exception e) {
						e.printStackTrace();
					}
					//System.out.println(">>>>>>>>>>>>");
					JpmSmssendInfo jpmSmssendInfo = new JpmSmssendInfo();
					jpmSmssendInfo.setSmsType("7");////短信类型  例如：1：仓库发货确认    2：找回密码   3：电影票  目前只有三种，在列表中配置
					jpmSmssendInfo.setSmsRecipient(user_code);//短信接收人:用户会员编号
					jpmSmssendInfo.setSmsMessage(msgInfo);//短信内容
					jpmSmssendInfo.setSmsTime(new Date());//发送时间
					jpmSmssendInfo.setSmsOperator("global");//操作人，可以填写空
					jpmSmssendInfo.setSmsStatus("1");//保留字段，是否发送成功！ 默认为1：成功！ 现在短信还不能知道是否发送成功，没有返回值！
					jpmSmssendInfo.setRemark("42PV未重消提醒");//备注
					jpmSmssendInfo.setPhoneNum(mobiletele);//手机号
					jpmProductSaleNewManager.saveJpmSmssendInfo(jpmSmssendInfo);
				}
				
				
				
				
				
			}
		}
		
		/*DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sql="select  t.user_code,t.mobiletele from jmi_member t where t.active='0' and t.freeze_status=1 and t.exit_date is  null "+unsend()+" and t.valid_week= "
				+ "( select min(w_Year || Lpad(w_Month, 2, 0)) period  from ( Select Distinct w_year,w_month,start_time "
				+ "From  Jbd_Period where start_time <=to_date('"+dateFormat.format(curDate)+"', 'yyyy-mm-dd hh24:mi:ss')   Order By w_Year desc, w_Month desc) where  rownum <=7)";
		list=jdbcTemplate.queryForList(sql);
		//System.out.println("2>>>>>>>>>>>>"+sql);
		if(!list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				Map map=(Map) list.get(i);
				String user_code=map.get("user_code").toString();
				String mobiletele=map.get("mobiletele")+"";


				if(!StringUtil.isEmpty(mobiletele) && mobiletele.length()==11 ){
					String msgInfo  = "尊敬的"+user_code+"：您好！您距您经销商资格有效截止期还有15天，请在15天内登录系统完成个人指标，以保持您的经销资格，谢谢！如有疑问，请联系客服。";
					try {
						SmsSend.sendSms(url1, url2, mobiletele, msgInfo);
					} catch (Exception e) {
						e.printStackTrace();
					}
					JpmSmssendInfo jpmSmssendInfo = new JpmSmssendInfo();
					jpmSmssendInfo.setSmsType("8");////短信类型  例如：1：仓库发货确认    2：找回密码   3：电影票  目前只有三种，在列表中配置
					jpmSmssendInfo.setSmsRecipient(user_code);//短信接收人:用户会员编号
					jpmSmssendInfo.setSmsMessage(msgInfo);//短信内容
					jpmSmssendInfo.setSmsTime(new Date());//发送时间
					jpmSmssendInfo.setSmsOperator("global");//操作人，可以填写空
					jpmSmssendInfo.setSmsStatus("1");//保留字段，是否发送成功！ 默认为1：成功！ 现在短信还不能知道是否发送成功，没有返回值！
					jpmSmssendInfo.setRemark("冻结半年提醒");//备注
					jpmSmssendInfo.setPhoneNum(mobiletele);//手机号
					jpmProductSaleNewManager.saveJpmSmssendInfo(jpmSmssendInfo);
				}
				
			}
		}*/
	}
	private String unsend(){
		Map unsendMap=ListUtil.getListOptions("AA", "msm.unsend");
		String sql="";
		Iterator  it = unsendMap.entrySet().iterator(); 
		while(it.hasNext()) { 
			Map.Entry entry = (Map.Entry) it.next(); 
		    String key = entry.getKey().toString(); 
		    //String val = entry.getValue().toString();
		   sql+=",'"+key+"'";
		} 
		sql=sql.replaceFirst(",", "");
		return " and t.user_code not in ("+sql+")";
	}
	
	
	public void postJmiMember(){
		String memberAddCrm = ListUtil.getListValue("AA", "interface.sendswitch", "member.add.crm");
		String memberAddQudao = ListUtil.getListValue("AA", "interface.sendswitch", "member.add.qudao");
	
		Map membertypeMap=ListUtil.getListOptions("AA", "membertype");
		List list=jmiMemberManager.getJmiMemberUpdate();
		System.out.println("执行过"+list.size());
		for (int i = 0; i < list.size(); i++) {
			try {
				Map map=(Map) list.get(i);
				
				if(map.get("start_week")!=null){
					map.put("start_week", WeekFormatUtil.getFormatMonth("w", map.get("start_week").toString()));
				}
				if(map.get("valid_week")!=null){
					map.put("valid_week", WeekFormatUtil.getFormatMonth("w", map.get("valid_week").toString()));
				}
				

				if(map.get("member_type")!=null){
					map.put("member_type", membertypeMap.get(map.get("member_type")));
				}
				
				if("Y".equals(memberAddCrm)){
					JSONObject crm_json = JSONObject.fromObject(map);
					
	                SEND.setSender(Constants.CRM_SEND);
	                SEND.post(crm_json.toString(), "member.add");
	                
				}
				
               
                if("Y".equals(memberAddQudao)){
                	 Map qudao_map=new HashMap();
                     qudao_map.put("member_num", map.get("user_code"));
                     qudao_map.put("member_name", map.get("pet_name"));
                     qudao_map.put("true_name", map.get("mi_user_name"));
                     qudao_map.put("reg_time", map.get("create_time"));
                     qudao_map.put("sex", map.get("sex"));
                     qudao_map.put("mobile", map.get("mobiletele"));
                     qudao_map.put("phone", map.get("phone"));
                     qudao_map.put("email", map.get("email"));
                     qudao_map.put("consignee_state", map.get("province"));
                     qudao_map.put("consignee_city", map.get("city"));
                     qudao_map.put("consignee_area", map.get("district"));
                     qudao_map.put("consignee_address", map.get("address"));
                     
                     JSONObject jsonForQudao = JSONObject.fromObject(qudao_map);
                     //System.out.println(jsonForQudao);
                     SEND.setSender(Constants.QU_SEND);
                     String aa = SEND.post(jsonForQudao.toString(), "member.add");
                     
                   //=============================接口日志写入开始 20160520
             		JocsInterfaceLog jocsInterfaceLog = new JocsInterfaceLog();
             		jocsInterfaceLog.setLogType("0");//0：本地发送数据    1：本地接收数据
             		jocsInterfaceLog.setSender(Constants.QU_SEND);//数据的接收方
             		jocsInterfaceLog.setMethod("member.add");//方法名称
             		jocsInterfaceLog.setContent(jsonForQudao.toString());//发送内容content
             		jocsInterfaceLog.setReturnDesc(aa);//返回内容
             		
             		ReportLogUtilService.addJocsInterface(jocsInterfaceLog);//写入日志操作
                }
                
                String id=map.get("id").toString();
                System.out.println("删除ID号1："+id);
                this.jdbcTemplate.update("delete from jmi_member_update where id="+id);
                System.out.println("删除ID号2："+id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
}
