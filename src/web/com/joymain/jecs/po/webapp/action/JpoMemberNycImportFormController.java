package com.joymain.jecs.po.webapp.action;

import com.joymain.jecs.po.model.JpoMemberNyc;
import com.joymain.jecs.po.service.JpoMemberNycManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;


import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JpoMemberNycImportFormController extends BaseFormController {
    private JpoMemberNycManager jpoMemberNycManager = null;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    public void setJpoMemberNycManager(JpoMemberNycManager jpoMemberNycManager) {
        this.jpoMemberNycManager = jpoMemberNycManager;
    }

    public JpoMemberNycImportFormController() {
        setCommandName("importJpoMemberNyc");
        //setCommandClass(JpoMemberNyc.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
            throws Exception {
        String id = request.getParameter("id");
        JpoMemberNyc jpoMemberNyc = new JpoMemberNyc();


        return jpoMemberNyc;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering'onSubmit'  method...");
        }
        SysUser operatorUser = SessionLogin.getOperatorUser(request);
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
        for (int i = 1; i < rowCount; i++) {
            try {
                JpoMemberNyc jpoMemberNyc = new JpoMemberNyc();
                Cell[] cells = sheet0.getRow(i);
                if (cells.length < 4) {
                    continue;
                }
                String yearOfMounth = cells[0].getContents();
                if (StringUtil.blankOrNull(yearOfMounth)) {
                    continue;
                }
                jpoMemberNyc.setYearOfMonth(yearOfMounth);
                String userCode = cells[1].getContents();
                if (StringUtil.blankOrNull(userCode)) {
                    continue;
                }
                jpoMemberNyc.setMemberNo(userCode);
                Date pushAt=null;
                if (cells[2].getType()== CellType.DATE) {
                    //pushAt=((DateCell)cells[2]).getDate();

                    DateCell dc = (DateCell) cells[2];
                    Date date2 = dc.getDate();
                    TimeZone zone = TimeZone.getTimeZone("GMT");
                    sdf.setTimeZone(zone);
                    String sDate = sdf.format(date2);
                    pushAt=sdf2.parse(sDate);
                    //System.out.println(sDate);

                }else{
                    String pushAtStr = cells[2].getContents();
                    if (!StringUtil.blankOrNull(pushAtStr)) {
                        pushAt=sdf2.parse(pushAtStr);
                    }
                }

                if(pushAt==null){
                    continue;
                }



                jpoMemberNyc.setPushAt(pushAt);


                String remark = cells[3].getContents();
                if (StringUtil.blankOrNull(remark)) {
                    continue;
                }
                jpoMemberNyc.setRemarks(sdf.format(new Date()) + ":" + remark);
                jpoMemberNyc.setStatus(JpoMemberNyc.STATUS_UNLOCK);
                jpoMemberNycManager.saveJpoMemberNyc(jpoMemberNyc, operatorUser.getUserCode());
            } catch (Exception e) {

            }
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
