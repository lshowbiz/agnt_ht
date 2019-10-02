package com.joymain.jecs.po.webapp.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
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
import com.joymain.jecs.bd.model.JbdSummaryList;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.pr.model.JprRefund;
import com.joymain.jecs.pr.service.JprRefundManager;
import com.joymain.jecs.util.ConfigUtil;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;

public class BatchUpdateMemberOrderController extends BaseFormController
{
    
    private JpoMemberOrderManager jpoMemberOrderManagerReport = null;
    private JprRefundManager jprRefundManager = null;
    private BdPeriodManager bdPeriodManager;
    
    
    
    public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public void setJprRefundManager(JprRefundManager jprRefundManager) {
        this.jprRefundManager = jprRefundManager;
    }
    
    public void setJpoMemberOrderManagerReport(
			JpoMemberOrderManager jpoMemberOrderManagerReport) {
		this.jpoMemberOrderManagerReport = jpoMemberOrderManagerReport;
	}
    
	public BatchUpdateMemberOrderController() {
		setCommandName("batchUpdateMemberOrder");
		setCommandClass(JpoMemberOrder.class);
	}
    
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		JpoMemberOrder poMemberOrder = new JpoMemberOrder();

		return poMemberOrder;
	}
    
    public ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {
        try {
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
            
            List<String> messages = new ArrayList<String>();
            int errCount = 0;
            //从第2行开始读,第一行为标题
            messages.add(LocaleUtil.getLocalText("bdBounsDeduct.impot.skip.first"));
            
            String maxImportRows = ConfigUtil.getConfigValue("CN", "import.memberorder.rows");
            if(MeteorUtil.isBlank(maxImportRows)){
            	maxImportRows = "50";
            }
            
            JpoMemberOrder jpoMemberOrder = null;
            JbdSummaryList jbdSummaryList = null;
            BdPeriod bdPeriodd =  null;
            String succesImport = "0";//成功标志
            String maxDate = bdPeriodManager.getStartTimeByOrder("max");
            String minDate = bdPeriodManager.getStartTimeByOrder("min");
            
//            logger.info("maxDate=="+maxDate+"--minDate=="+minDate);
            for (int i = 1; i < sheet.getRows(); i++) {
//            	logger.info(sheet.getRows());
            	if(sheet.getRows()>Integer.valueOf(maxImportRows)+1){
            		messages.add("<font color=red>"
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "一次最多导入"+ Integer.valueOf(maxImportRows) +"条数据  </font>");
                    errCount++;
                    break;
            	}
                //订单编号,退货状态   1：退货中，0正常
                String memberOrderNo = eu.getContents(sheet, 0, i);//订单编号
                if(!MeteorUtil.isBlank(memberOrderNo)){
                	memberOrderNo = memberOrderNo.trim();
                }
                String returnableStatus = eu.getContents(sheet, 1, i);//退货状态
                String checkDate = eu.getContents(sheet, 2, i);//核算日期，激活时必填
                if(null == checkDate){
                	checkDate = "";
                }

                String content = " ([ " + memberOrderNo + ","+returnableStatus+","+checkDate+" ])";
                String message = (i + 1) + ": ";
                if (StringUtils.isEmpty(memberOrderNo)) {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "订单编号不能为空" + content + "</font>");
                    errCount++;
                    continue;
                }
                jpoMemberOrder = this.jpoMemberOrderManagerReport.getJpoMemberOrderByMemberOrderNo(memberOrderNo);
                if (jpoMemberOrder == null) {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "订单不存在" + content + "</font>");
                    errCount++;
                    continue;
                }else{
                	//验证订单是否已结算   已归档
                	if("1".equals(jpoMemberOrder.getLocked()) && "1".equals(jpoMemberOrder.getLocked2())){
                		messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "订单已结算，不允许订单修改状态 " + content + "</font>");
                        errCount++;
                        continue;
                	}
                	
                	if(!"2".equals(jpoMemberOrder.getStatus())){
                		messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "订单状态不正确，不允许修改状态 " + content + "</font>");
                        errCount++;
                        continue;
                	}
                	
//                	//判断指定核算日期所在期别是否已结算
//                	Long wid =1l;
//                	//根据这个日期，查询出期别表的对象
//                	bdPeriodd =  bdPeriodManager.getBdPeriodByTime(jpoMemberOrder.getCheckDate(),wid);
//                	if(!"0".equals(bdPeriodd.getArchivingStatus())){
//                		messages.add("<font color=red>"
//                                + message
//                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
//                                + " - " + "订单期别已归档或归档中，不允许订单修改状态 " + content + "</font>");
//                        errCount++;
//                        continue;
//                	}
                	
                	//验证是否有退单
                	List<JprRefund> jprRefundList = this.jprRefundManager.getJprRefundForMemberOrder(memberOrderNo);
					if (null != jprRefundList && jprRefundList.size() > 0 && "1".equals(returnableStatus)) {
                		messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "订单已有退单，不允许订单修改状态 " + content + "</font>");
                        errCount++;
                        continue;
                	}
                }
                
                if (!"1".equals(returnableStatus) && !"0".equals(returnableStatus)) {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "订单状态不正确" + content + "</font>");
                    errCount++;
                    continue;
                }
                //状态没有发生改变时 不允许修改
                if(returnableStatus.equals(jpoMemberOrder.getReturnableStatus()) || (MeteorUtil.isBlank(jpoMemberOrder.getReturnableStatus()) && "0".equals(returnableStatus)) ){
                	messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "订单状态没有发生改变，不允许修改" + content + "</font>");
                    errCount++;
                    continue;
                }

                //激活时  核算日期必填
                if ("0".equals(returnableStatus) && "".equals(checkDate)) {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "激活时，核算日期不能为空 " + content + "</font>");
                    errCount++;
                    continue;
                }	
                
                Date d1 = null;
                if ("0".equals(returnableStatus)) {
                	
                	//判断下日期格式是否正确
                	try{
                		d1 = MeteorUtil.doConvertToDateObject(checkDate);
                	}catch (Exception e) {
                		messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "核算日期格式不正确，请重新设置核算日期 " + content + "</font>");
                        errCount++;
                        continue;
					}
                	
                	if (d1.getTime()>new Date().getTime()) {
                        messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "核算日期不能大于当前时间" + content + "</font>");
                        errCount++;
                        continue;
                    }	
                	
                	//判断指定核算日期所在期别是否已结算
                	Long wid =1l;
                	//根据这个日期，查询出期别表的对象
                	bdPeriodd =  bdPeriodManager.getBdPeriodByTime(d1,wid);
                	if(null != bdPeriodd && bdPeriodd.getBonusSend()==0 ){//所在期数未结算方可修改
                		jpoMemberOrder.setCheckDate(d1);
                	}else{
                		messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "核算日期所在期别已结算或不存在，请重新设置核算日期 " + content + "</font>");
                        errCount++;
                        continue;
                	}
                }

                jpoMemberOrder.setReturnableStatus(returnableStatus);

//              挂起：in_type=7，old_check_date = 订单审核日期，new_check_date = 期别表最大start_time
//              激活：in_type=7，old_check_date = 期别表最小start_time，new_check_date = 订单审核日期
              //插入每日计算表
        		jbdSummaryList=new JbdSummaryList();
        		jbdSummaryList.setUserCode(jpoMemberOrder.getSysUser().getUserCode());
        		jbdSummaryList.setCardType(jpoMemberOrder.getSysUser().getJmiMember().getCardType());
        		jbdSummaryList.setInType(7);
        		jbdSummaryList.setCreateTime(new Date());
        		jbdSummaryList.setOrderType(jpoMemberOrder.getOrderType());
        		if("0".equals(returnableStatus)){//激活
        			jbdSummaryList.setOldCheckDate(MeteorUtil.doConvertToDate(minDate));
            		jbdSummaryList.setNewCheckDate(jpoMemberOrder.getCheckDate());
        		}else if("1".equals(returnableStatus)){//挂起
        			jbdSummaryList.setOldCheckDate(jpoMemberOrder.getCheckDate());
            		jbdSummaryList.setNewCheckDate(MeteorUtil.doConvertToDate(maxDate));
        		}
        		
        		jbdSummaryList.setPvAmt(jpoMemberOrder.getPvAmt());
        		jbdSummaryList.setOldLinkNo(null);
        		jbdSummaryList.setNewLinkNo(null);
        		jbdSummaryList.setOldRecommendNo(null);
        		jbdSummaryList.setNewRecommendNo(null);
        		jbdSummaryList.setNewCompanyCode(jpoMemberOrder.getCompanyCode());
        		jbdSummaryList.setUserCreateTime(null);
        		jbdSummaryList.setWweek(bdPeriodManager.getBdPeriodByTimeFormated(jpoMemberOrder.getCheckDate(), null));
        		jbdSummaryList.setOrderNo(jpoMemberOrder.getMemberOrderNo());

        		//保存
                jpoMemberOrderManagerReport.saveJpoMemberOrder(jpoMemberOrder,jbdSummaryList);
                succesImport = "1";
            }
            
            eu.closeWorkbook(workbook);
			if (errCount == 0 && "1".equals(succesImport)) {
				this.saveMessage(request, "批量修改成功");
				return new ModelAndView(this.getSuccessView());
			} else {
				if("1".equals(succesImport)) {
					messages.add("部分修改成功");
				}else{
					messages.add("<font color=red>批量修改失败</font>");
				}
				
			}

			request.setAttribute("messages", messages);
            request.setAttribute("isFinished", true);
            request.setAttribute("errCount", errCount);
            
            return new ModelAndView(this.getFormView());
        }
        catch (IOException e) {
            this.saveMessage(request,
                    getText("bdBounsDeduct.importFile.failed"));
            log.error(e.getMessage());
        }
        catch (Exception e) {
            this.saveMessage(request,
                    getText("bdBounsDeduct.import.data.error"));
            log.error(e.getMessage());
        }
        
        return new ModelAndView(this.getSuccessView());
    }
}
