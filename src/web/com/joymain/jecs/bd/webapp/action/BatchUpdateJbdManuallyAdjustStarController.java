package com.joymain.jecs.bd.webapp.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.model.JbdManuallyAdjustStar;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.bd.service.JbdManuallyAdjustStarManager;
import com.joymain.jecs.mi.model.JmiGradeLock;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiGradeLockManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysBank;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysBankManager;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class BatchUpdateJbdManuallyAdjustStarController extends BaseFormController
{
    
    private JmiMemberManager jmiMemberManager = null;
    private JdbcTemplate jdbcTemplate;
    private BdPeriodManager bdPeriodManager;
    private JbdManuallyAdjustStarManager jbdManuallyAdjustStarManager = null;
    public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
        this.jmiMemberManager = jmiMemberManager;
    }
    public void setJbdManuallyAdjustStarManager(JbdManuallyAdjustStarManager jbdManuallyAdjustStarManager) {
        this.jbdManuallyAdjustStarManager = jbdManuallyAdjustStarManager;
    }
    
    public BatchUpdateJbdManuallyAdjustStarController()
    {
        setCommandName("batchUpdateJbdManuallyAdjustStar");
        setCommandClass(JbdManuallyAdjustStar.class);
    }
    
    protected Object formBackingObject(HttpServletRequest request)
            throws Exception
    {
    	JbdManuallyAdjustStar jbdManuallyAdjustStar = new JbdManuallyAdjustStar();
        
        
        return jbdManuallyAdjustStar;
    }
    
    public ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception
    {
        SysUser defSysUser = SessionLogin.getLoginUser(request);    
        try
        {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("importExcelFile");
            if (file == null || file.getInputStream() == null)
            {
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
            int suCount = 0;
            //从第2行开始读,第一行为标题
            messages.add(LocaleUtil.getLocalText("bdBounsDeduct.impot.skip.first"));
            
            String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
            LinkedHashMap<String, String> ryjxMap = ListUtil.getListOptionsByLocal(companyCode, "honor.star.zero");
            LinkedHashMap<String, String> hgjxMap = ListUtil.getListOptionsByLocal(companyCode, "pass.star.zero");
            LinkedHashMap<String, String> ryjbMap = ListUtil.getListOptionsByLocal(companyCode, "honor.grade.zero");
            LinkedHashMap<String, String> hgjbMap = ListUtil.getListOptionsByLocal(companyCode, "pass.grade.zero");
            
            JbdManuallyAdjustStar jbdManuallyAdjustStar= null;
            List<JbdManuallyAdjustStar> jbdManuallyAdjustStarList = new ArrayList<JbdManuallyAdjustStar>();
            for (int i = 1; i < sheet.getRows(); i++)
            {
                //会员编号,期别,身份
                String userCode = eu.getContents(sheet, 0, i);
                String week = eu.getContents(sheet, 1, i);
                String ryjx = null==eu.getContents(sheet, 2, i)?"":eu.getContents(sheet, 2, i).trim();
                String hgjx = null==eu.getContents(sheet, 3, i)?"":eu.getContents(sheet, 3, i).trim();
                String ryjb = null==eu.getContents(sheet, 4, i)?"":eu.getContents(sheet, 4, i).trim();
                String hgjb = null==eu.getContents(sheet, 5, i)?"":eu.getContents(sheet, 5, i).trim();
                
                String content = " ([ " + userCode + ","+week+","+ryjx+","+hgjx+","+ryjb+","+hgjb+" ])";
                String message = (i + 1) + ": ";
                if (StringUtils.isEmpty(userCode))
                {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "会员编号不能为空" + content + "</font>");
                    errCount++;
                    continue;
                }
                JmiMember jmiMember = this.jmiMemberManager.getJmiMember(userCode);
                if (jmiMember == null)
                {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "会员不存在" + content + "</font>");
                    errCount++;
                    continue;
                }
                
                if (StringUtils.isEmpty(week))
                {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "期别不能为空" + content + "</font>");
                    errCount++;
                    continue;
                } else {
                	BdPeriod bdPeriod=bdPeriodManager.getMonthBdPeriod(WeekFormatUtil.getFormatWeek("f", week.trim()));
                	if(bdPeriod==null){
                		messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "期别不是结算期" + content + "</font>");
                        errCount++;
                        continue;
                	}
                	
                }

                if (!MeteorUtil.isBlank(ryjx)){
                	if(!ryjxMap.containsValue(ryjx.trim())){
                		messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "荣誉奖衔不正确" + content + "</font>");
                        errCount++;
                        continue;
                	}else {
                    	for(String s:ryjxMap.keySet()){
                    		if(ryjxMap.get(s).equals(ryjx)){
                    			ryjx = s ;
                    			break;
                    		}
                    	}
                
                	}
                }
                
                if (!MeteorUtil.isBlank(hgjx)){
                	if(!hgjxMap.containsValue(hgjx.trim())){
                		messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "合格奖衔不正确" + content + "</font>");
                        errCount++;
                        continue;
                	}else {
                    	for(String s:hgjxMap.keySet()){
                    		if(hgjxMap.get(s).equals(hgjx)){
                    			hgjx = s ;
                    			break;
                    		}
                    	}
                	}
                }
                
                if (!MeteorUtil.isBlank(ryjb)){
                	if(!ryjbMap.containsValue(ryjb.trim())){
                		messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "荣誉级别不正确" + content + "</font>");
                        errCount++;
                        continue;
                	}else {
                    	for(String s:ryjbMap.keySet()){
                    		if(ryjbMap.get(s).equals(ryjb)){
                    			ryjb = s ;
                    			break;
                    		}
                    	}
                	}
                }
                
                if (!MeteorUtil.isBlank(hgjb)){
                	if(!hgjbMap.containsValue(hgjb.trim())){
                		messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "合格级别不正确" + content + "</font>");
                        errCount++;
                        continue;
                	}else {
                    	for(String s:hgjbMap.keySet()){
                    		if(hgjbMap.get(s).equals(hgjb)){
                    			hgjb = s ;
                    			break;
                    		}
                    	}
                	}
                }
                
                
                JbdManuallyAdjustStar curJbdManuallyAdjustStar= jbdManuallyAdjustStarManager.getJbdManuallyAdjustStarByUserCodeAndWeek(jmiMember.getUserCode(), week.trim());
				if(curJbdManuallyAdjustStar!=null){
					 messages.add("<font color=red>"
	                          + message
	                          + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
	                          + " - " + "记录已存在" + content + "</font>");
	                 errCount++;
	                 continue;
				 }
				 
				jbdManuallyAdjustStar = new JbdManuallyAdjustStar();
	            
				jbdManuallyAdjustStar.setUserCode(userCode.trim());
				jbdManuallyAdjustStar.setCreateNo(defSysUser.getUserCode());
				jbdManuallyAdjustStar.setCreateTime(new Date());
				jbdManuallyAdjustStar.setWweek(Integer.valueOf(WeekFormatUtil.getFormatWeek("f", week.trim())));

				if(!MeteorUtil.isBlank(ryjx)){
					jbdManuallyAdjustStar.setHonorStar(Integer.valueOf(ryjx));
				}
				if(!MeteorUtil.isBlank(hgjx)){
					jbdManuallyAdjustStar.setPassStar(Integer.valueOf(hgjx));
				}
				if(!MeteorUtil.isBlank(ryjb)){
					jbdManuallyAdjustStar.setHonorGrade(Integer.valueOf(ryjb));
				}
				if(!MeteorUtil.isBlank(hgjb)){
					jbdManuallyAdjustStar.setPassGrade(Integer.valueOf(hgjb));
				}
				
            	
				jbdManuallyAdjustStarList.add(jbdManuallyAdjustStar);
            }
            
            eu.closeWorkbook(workbook);
            
            if (errCount == 0)
            {
            	jbdManuallyAdjustStarManager.saveJbdManuallyAdjustStarList(jbdManuallyAdjustStarList);
                this.saveMessage(request, "批量修改成功");
                return new ModelAndView(this.getSuccessView());
            }
            
            request.setAttribute("messages", messages);
            request.setAttribute("isFinished", true);
            request.setAttribute("errCount", errCount);
            
            return new ModelAndView(this.getFormView());
        }
        catch (IOException e)
        {
        	e.printStackTrace();
            this.saveMessage(request,
                    getText("bdBounsDeduct.importFile.failed"));
            log.error(e.getMessage());
        }
        catch (Exception e)
        {
        	e.printStackTrace();
            this.saveMessage(request,
                    getText("bdBounsDeduct.import.data.error"));
            log.error(e.getMessage());
        }
        
        return new ModelAndView(this.getSuccessView());
    }
}
