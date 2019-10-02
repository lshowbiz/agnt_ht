package com.joymain.jecs.po.webapp.action;

import java.io.InputStream;
import java.util.Date;
import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.po.model.JpoMemberNyc;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.po.model.JpoMemberNycQualify;
import com.joymain.jecs.po.service.JpoMemberNycQualifyManager;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

public class JpoMemberNycQualifyFormController extends BaseFormController {
    private JpoMemberNycQualifyManager jpoMemberNycQualifyManager = null;

    public void setJpoMemberNycQualifyManager(JpoMemberNycQualifyManager jpoMemberNycQualifyManager) {
        this.jpoMemberNycQualifyManager = jpoMemberNycQualifyManager;
    }
    public JpoMemberNycQualifyFormController() {
        setCommandName("jpoMemberNycQualify");
        setCommandClass(JpoMemberNycQualify.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JpoMemberNycQualify jpoMemberNycQualify = null;

        if (!StringUtils.isEmpty(id)) {
            jpoMemberNycQualify = jpoMemberNycQualifyManager.getJpoMemberNycQualify(id);
        } else {
            jpoMemberNycQualify = new JpoMemberNycQualify();
        }

        return jpoMemberNycQualify;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit'  method...");
        }

        JpoMemberNycQualify jpoMemberNycQualify = (JpoMemberNycQualify) command;


        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("xlsFile");
        //retrieve the file data
        InputStream stream = file.getInputStream();

        Date date = new Date();

        ExcelUtil eu = new ExcelUtil();
        //获取可读的工作表对象，定位到要读取的excel文件
        Workbook workbook = eu.getWorkbook(stream);
        //读取此文件的第一个工作表，工作表序号从0开始。
        Sheet sheet0 = workbook.getSheet(0);

        int rowCount = sheet0.getRows();
        for(int i=1;i<rowCount;i++){
            try{
                Cell[] cells = sheet0.getRow(i);
                if(cells.length<4){
                    continue;
                }
                JpoMemberNycQualify nycQualify = new JpoMemberNycQualify();
                int z=0;
                String jsWeek = cells[z++].getContents();
                if(StringUtil.blankOrNull(jsWeek)){
                    continue;
                }
                nycQualify.setJsWeek(Long.parseLong(jsWeek));

                String userCode = cells[z++].getContents();
                if(StringUtil.blankOrNull(userCode)){
                    continue;
                }
                nycQualify.setUserCode(userCode);


                String userName=cells[z++].getContents();
                if(StringUtil.blankOrNull(userName)){
                    continue;
                }
                nycQualify.setUserName(userName);

                String papernumber=cells[z++].getContents();
                if(StringUtil.blankOrNull(papernumber)){
                    continue;
                }
                nycQualify.setPapernumber(papernumber);

                String recommendNo=cells[z++].getContents();
                if(StringUtil.blankOrNull(recommendNo)){
                    continue;
                }
                nycQualify.setRecommendNo(recommendNo);

                String recommendName=cells[z++].getContents();
                if(StringUtil.blankOrNull(recommendName)){
                    continue;
                }
                nycQualify.setRecommendName(recommendName);

                String memberOrderNo=cells[z++].getContents();
                if(StringUtil.blankOrNull(memberOrderNo)){
                    continue;
                }
                nycQualify.setMemberOrderNo(memberOrderNo);

                String productNo=cells[z++].getContents();
                if(StringUtil.blankOrNull(productNo)){
                    continue;
                }
                nycQualify.setProductNo(productNo);

                String productName=cells[z++].getContents();
//            if(StringUtil.blankOrNull(productName)){
//                continue;
//            }
                nycQualify.setProductName(productName);


                String qualify=cells[z++].getContents();
                if(StringUtil.blankOrNull(qualify)){
                    continue;
                }
                nycQualify.setQualify(qualify);


                String remark=cells[z++].getContents();
                nycQualify.setRemarks(remark);
                jpoMemberNycQualifyManager.saveJpoMemberNycQualify(nycQualify);
            }catch (Exception e){

            }


           // jpoMemberNycManager.saveJpoMemberNyc(jpoMemberNyc,operatorUser.getUserCode());
        }
        return new ModelAndView(getSuccessView());
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
