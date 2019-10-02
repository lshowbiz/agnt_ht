package com.joymain.jecs.bd.webapp.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.model.JbdMemberLinkCalcHist;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.bd.service.JbdMemberLinkCalcHistManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
public class JbdMemberQualifyStarImportController extends BaseFormController {
    private JmiMemberManager jmiMemberManager;
    private JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager;
    private BdPeriodManager bdPeriodManager;
	

    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
        this.jmiMemberManager = jmiMemberManager;
    }
	public void setJbdMemberLinkCalcHistManager(JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager) {
		this.jbdMemberLinkCalcHistManager = jbdMemberLinkCalcHistManager;
	}
	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
        this.bdPeriodManager = bdPeriodManager;
    }

	public JbdMemberQualifyStarImportController() {
		setCommandName("jbdMemberLinkCalcHist");
		setCommandClass(JbdMemberLinkCalcHist.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
	    JbdMemberLinkCalcHist jbdMemberLinkCalcHist = new JbdMemberLinkCalcHist();
	    
	    String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
        LinkedHashMap<String, String> starsMap = ListUtil.getListOptions(companyCode, "qualify.star.zero");
        request.setAttribute("starsMap", starsMap);
	    
		return jbdMemberLinkCalcHist;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        if ("jbdMemberQualifyStarImport".equals(request.getParameter("strAction"))) {
            
            
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
                List<JbdMemberLinkCalcHist> jbdMemberLinkCalcHists = new ArrayList<JbdMemberLinkCalcHist>();
                int errCount = 0;
                //从第2行开始读,第一行为标题
                messages.add(LocaleUtil.getLocalText("bdBounsDeduct.impot.skip.first"));
                for (int i = 1; i < sheet.getRows(); i++) {
                    //会员编号,扣补类型,扣补金额,备注
                    String userCode = eu.getContents(sheet, 0, i);
                    String wWeek = eu.getContents(sheet, 1, i);
                    
                    
                    String qualifyStar = eu.getContents(sheet, 2, i);
                    String qualifyRemark = eu.getContents(sheet, 3, i);
                    
                    
                    

                    String content = " ([ " + userCode + " ] [ " + wWeek + " ] )";
                    String message = (i + 1) + ": ";
                    if (StringUtils.isEmpty(userCode)) {
                        messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "会员编号不能为空" + content+"</font>");
                        errCount++;
                        continue;
                    }
                    JmiMember miMember = this.jmiMemberManager.getJmiMember(userCode.trim());
                    if (miMember == null) {
                        messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "会员不存在" + content+"</font>");
                        errCount++;
                        continue;
                    }
                    if(!StringUtil.isInteger(wWeek)){
                        messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "期别必须为数字" + content+"</font>");
                        errCount++;
                        continue;
                    }else{
                        BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(WeekFormatUtil.getFormatWeek("f",wWeek));
                        if(bdPeriod==null){
                            messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "期别不存在" + content+"</font>");
                            errCount++;
                            continue;
                        }
                    }
                    if(!Constants.sysListMap.get("qualify.star.zero").containsKey(qualifyStar)){
                        messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "资格奖衔必须为0-9的数字" + content+"</font>");
                        errCount++;
                        continue;
                    }

                    if(qualifyRemark!=null&&qualifyRemark.length()>2000){
                        messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "资格奖衔备注输入过长" + content+"</font>");
                        errCount++;
                        continue;
                    }
                    
                    JbdMemberLinkCalcHist jbdMemberLinkCalcHist = this.jbdMemberLinkCalcHistManager.getJJbdMemberLinkCalcHistByUserCodeWeek(userCode, WeekFormatUtil.getFormatWeek("f",wWeek));
                    if(jbdMemberLinkCalcHist==null) {
                        messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "会员期别对应奖衔记录不存在" + content+"</font>");
                        errCount++;
                        continue; 
                    }
                    jbdMemberLinkCalcHist.setQualifyStar(Integer.valueOf(qualifyStar));
                    jbdMemberLinkCalcHist.setQualifyRemark(qualifyRemark);
                    
                    jbdMemberLinkCalcHists.add(jbdMemberLinkCalcHist);
                    

                    //messages.add(message + LocaleUtil.getLocalText("bdBounsDeduct.import.OK") + content);
                }

                eu.closeWorkbook(workbook);
                
                if(errCount==0 && !jbdMemberLinkCalcHists.isEmpty()){
                    jbdMemberLinkCalcHistManager.updateJbdMemberQualifyStar(jbdMemberLinkCalcHists);
                    this.saveMessage(request, "导入成功");
                    return new ModelAndView(this.getSuccessView());
                }
                

                request.setAttribute("messages", messages);
                request.setAttribute("isFinished", true);
                request.setAttribute("errCount", errCount);

                return new ModelAndView(this.getFormView());
            } catch (IOException e) {
                this.saveMessage(request, getText("bdBounsDeduct.importFile.failed"));
                log.error(e.getMessage());
            } catch (Exception e) {
                this.saveMessage(request, getText("bdBounsDeduct.import.data.error"));
                log.error(e.getMessage());
            }
        }

        return new ModelAndView(this.getSuccessView());
    }
}
