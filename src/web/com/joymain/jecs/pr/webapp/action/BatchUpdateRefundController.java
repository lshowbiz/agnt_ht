package com.joymain.jecs.pr.webapp.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.pr.model.JprRefund;
import com.joymain.jecs.pr.model.JprRefundList;
import com.joymain.jecs.pr.service.JprRefundManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.ConfigUtil;
import com.joymain.jecs.util.GlobalVar;
import com.joymain.jecs.util.ListUtil;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class BatchUpdateRefundController extends BaseFormController
{
    
	private JprRefundManager jprRefundManager = null;
	private BdPeriodManager bdPeriodManager = null;

	public void setJprRefundManager(JprRefundManager jprRefundManager) {
		this.jprRefundManager = jprRefundManager;
	}

	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
    
	public BatchUpdateRefundController() {
		setCommandName("batchUpdateRefund");
		setCommandClass(JprRefund.class);
	}
    
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		JprRefund jprRefund = new JprRefund();
		
		return jprRefund;
	}
    
    public ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {
    	  log.info("GlobalVar.BatchRefundStatus===="+GlobalVar.BatchRefundStatus);
    	  List<String> messages = new ArrayList<String>();
    	  int errCount = 0;
    	  
    	  if("0".equals(GlobalVar.BatchRefundStatus)){
			  GlobalVar.BatchRefundStatus="1";//运行中
		  }else{
			//运行中
              errors.reject("batch.Refund.importFile.failed");
              return showForm(request, response, errors);
		  }
    	  try{
    		  
    		  MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
              CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("importExcelFile");
              if (file == null || file.getInputStream() == null) {
                  //文件读取错误
                  errors.reject("bdBounsDeduct.importFile.failed");
                  return showForm(request, response, errors);
              }
              // 设置上传路径
              //retrieve the file data
              InputStream stream = file.getInputStream();
              
              ExcelUtil eu = new ExcelUtil();
              //获取可读的工作表对象，定位到要读取的excel文件
              Workbook workbook = eu.getWorkbook(stream);
              //读取此文件的第一个工作表，工作表序号从0开始。
              Sheet sheet = workbook.getSheet(0);
              
              //从第2行开始读,第一行为标题
              messages.add(LocaleUtil.getLocalText("bdBounsDeduct.impot.skip.first"));
              
              String maxImportRows = ConfigUtil.getConfigValue("CN", "import.memberorder.rows");
              if(MeteorUtil.isBlank(maxImportRows)){
              	maxImportRows = "50";
              }
              
              JprRefund jprRefund = null;
              
              for (int i = 1; i < sheet.getRows(); i++) {
//              	logger.info(sheet.getRows());
              	if(sheet.getRows()>Integer.valueOf(maxImportRows)+1){
              		messages.add("<font color=red>"
                              + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                              + " - " + "一次最多导入"+ Integer.valueOf(maxImportRows) +"条数据  </font>");
                      errCount++;
                      break;
              	}
                  //订单编号,退货状态   1：退货中，0正常
                  String roNo = eu.getContents(sheet, 0, i);//退单编号
                  if(!MeteorUtil.isBlank(roNo)){//去除空格
                	  roNo = roNo.trim();
                  }
                  String returnableType = eu.getContents(sheet, 1, i);//退单类型（0：正常，1：五折退货）
                  String returnableStatus = eu.getContents(sheet, 2, i);//退款状态(1：未退款，2：已退款，3：不退款)
                  String memo = eu.getContents(sheet, 3, i);//备注
                  if(null == memo){
                	  memo = "";
                  }

                  String content = " ([ " + roNo + ","+returnableStatus+","+returnableType+","+memo+" ])";
                  String message = (i + 1) + ": ";
                  if (StringUtils.isEmpty(roNo)) {
                      messages.add("<font color=red>"
                              + message
                              + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                              + " - " + "退单编号不能为空" + content + "</font>");
                      errCount++;
                      continue;
                  }
                  jprRefund = jprRefundManager.getJprRefund(roNo);
                  if (jprRefund == null) {
                      messages.add("<font color=red>"
                              + message
                              + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                              + " - " + "退单不存在" + content + "</font>");
                      errCount++;
                      continue;
                  }
                  
                  if (!"0".equals(returnableType) && !"1".equals(returnableType)) {
                      messages.add("<font color=red>"
                              + message
                              + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                              + " - " + "退单类型不正确" + content + "</font>");
                      errCount++;
                      continue;
                  }
                 
                  if (!"1".equals(returnableStatus) && !"2".equals(returnableStatus) && !"3".equals(returnableStatus)) {
                      messages.add("<font color=red>"
                              + message
                              + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                              + " - " + "退单状态不正确" + content + "</font>");
                      errCount++;
                      continue;
                  }
                  
                  if (!MeteorUtil.isStrLen(memo, 4000)) {//限制超长
                      messages.add("<font color=red>"
                              + message
                              + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                              + " - " + "备注超长" + content + "</font>");
                      errCount++;
                      continue;
                  }
                  jprRefund.setRefundRemark(memo);
                  
                  String flag = this.editRefund(jprRefund, request, returnableStatus, returnableType);
                  if("1".equals(flag)){
                	  messages.add("<font color=blue>"
                              + message
                              + " - " + "操作成功" + content + "</font>");
                      errCount++;
                  }else{
                	  messages.add("<font color=red>"
                              + message
                              + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                              + " - " + flag + content + "</font>");
                      errCount++;
                  }
              }
              eu.closeWorkbook(workbook);
    		  messages.add("导入完成");
    		  
    	  }catch (Exception e) {
			// TODO: handle exception
		  }finally {
			  GlobalVar.BatchRefundStatus = "0";//运行完后
		  }
    	  request.setAttribute("messages", messages);
          request.setAttribute("isFinished", true);
          request.setAttribute("errCount", errCount);
          
        return new ModelAndView(this.getFormView());
    }
    
    public String editRefund(JprRefund jprRefund,HttpServletRequest request,String refundStatus,String refundTye ){
    	String flag = "1";
    	SysUser defSysUser = SessionLogin.getLoginUser(request);
		// =========================================

		jprRefund.setRefundTye(refundTye);
		jprRefund.setRefundUNo(defSysUser.getUserCode());
		jprRefund.setRefundTime(new Date());

		log.info("refundStatus is:" + refundStatus + "--" + jprRefund.getRefundStatus());

		if ("2".equals(jprRefund.getRefundStatus())) {//当前已退款状态改其他状态
			if ("2".equals(refundStatus)) {
				// 已退款改已退
				jprRefund.getJpoMemberOrder().setIsRetreatOrder("1");// 将订单的状态改为已退货
				fillWeek(jprRefund);
				setJprRefund( jprRefund, refundStatus, refundTye );
				jprRefundManager.saveJprRefund(jprRefund);
			} else {
				// modify gw 2015-02-27
				try {
					setJprRefund( jprRefund, refundStatus, refundTye );
					// 已退款改未退款或不退款
					jprRefundManager.saveJprRefundRefund(jprRefund, SessionLogin.getOperatorUser(request));
				} catch (Exception e) {
					log.info("退货退款出现异常:"+e.toString());
//					saveMessage(request, getText(defSysUser.getDefCharacterCoding(), "请勿进行重复的退款操作"));
					flag = "请勿进行重复的退款操作";
				}
			}
		} else {
			if ("2".equals(refundStatus)) {
				// 未退款或不退款改已退款
				jprRefund.getJpoMemberOrder().setIsRetreatOrder("1");// 将订单的状态改为已退货

				log.info("function run.");
				JprRefund myJpr = jprRefundManager.getRefundByMoId(jprRefund.getJpoMemberOrder().getMoId(),null);
				Boolean isEoc = false;
				if (null == myJpr || myJpr.getStjFlag() == null) {// 判断该订单是否验证过
					Integer stjPrice = jprRefund.getJpoMemberOrder().getStjPrice();
					if (stjPrice != null) {// 该订单是否存在 5w，20w的订单
						Iterator<JprRefundList> it = jprRefund.getJprReFundList().iterator(); // 循环退货单
						while (it.hasNext()) {
							JprRefundList refundList = it.next();
							if (stjPrice == 5) {// 5万套餐标记
								if (StringUtils.isNotEmpty(ListUtil.getListValue("CN", "5w_product", refundList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo()))) {
									jprRefund.setStjFlag(stjPrice);
									isEoc = true;
									break;
								}
							} else if (stjPrice == 20) {// 20万套餐标记
								if (StringUtils
										.isNotEmpty(ListUtil.getListValue("CN", "20w_product", refundList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo()))) {
									jprRefund.setStjFlag(stjPrice);
									isEoc = true;
									break;
								}
							}
						}
					}
				}
				log.info("function end.");

				fillWeek(jprRefund);
				try {
					setJprRefund( jprRefund, refundStatus, refundTye );
					jprRefundManager.saveJprRefundRefund(jprRefund, SessionLogin.getOperatorUser(request));
					if (isEoc) {
						jprRefundManager.getFunctionRp(jprRefund.getJpoMemberOrder().getMemberOrderNo(), 2);// 45w、65w订单退款触发
					}
				} catch (Exception e) {
			        log.info("退货退款出现异常:" + e.toString());
//					saveMessage(request, getText(defSysUser.getDefCharacterCoding(), "请勿进行重复的退款操作"));
					flag = "请勿进行重复的退款操作";
				}

			} else {
				setJprRefund( jprRefund, refundStatus, refundTye );
				// 未退款或不退款改未退款或不退款
				jprRefundManager.saveJprRefund(jprRefund);
			}
		}
		//Modify By WuCF 20151211 修改对应订单的字段
		jprRefundManager.updateJpoMemberOrderFlag(jprRefund);
		
		return flag;
    }
    
    public void setJprRefund(JprRefund jprRefund,String refundStatus,String refundTye ){
    	if(null!=jprRefund){
    		jprRefund.setRefundTye(refundTye);
            jprRefund.setRefundStatus(refundStatus);
    	}
    }
    
    /**
	 * 填入退货单结算期别 如果会员订单未结算，则退货单与会员订单同一期结算 如果会员订单已结算，则退货单当期结算
	 * 
	 * @param jprRefund
	 */
	private void fillWeek(JprRefund jprRefund) {
		JpoMemberOrder jpoMemberOrder = jprRefund.getJpoMemberOrder();
		if (jpoMemberOrder != null && !"1".equals(jpoMemberOrder.getLocked())) {
			// 审核时间
			BdPeriod bdPeriod = bdPeriodManager.getBdPeriodByTime(jpoMemberOrder.getCheckDate(), null);
			logger.info("getWyear==="+bdPeriod.getWyear()+"--getWmonth==="+bdPeriod.getWmonth());
			if (bdPeriod != null) {
				jprRefund.setWyear(bdPeriod.getWyear());
				jprRefund.setWmonth(bdPeriod.getWmonth());
				String Wweek = bdPeriod.getWyear().toString() + StringUtils.leftPad(bdPeriod.getWweek().toString(), 2, "0");
				jprRefund.setWweek(Integer.parseInt(Wweek));
				logger.info("Wweek==="+Wweek);
			}
		}
	}
}
