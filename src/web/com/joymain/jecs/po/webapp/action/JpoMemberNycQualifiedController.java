package com.joymain.jecs.po.webapp.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.po.model.JpoMemberNyc;
import com.joymain.jecs.po.model.JpoNycQualified;
import com.joymain.jecs.po.service.JpoMemberNycManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

public class JpoMemberNycQualifiedController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoMemberNycQualifiedController.class);
    private final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    private JpoMemberNycManager jpoMemberNycManager = null;

    public void setJpoMemberNycManager(JpoMemberNycManager jpoMemberNycManager) {
        this.jpoMemberNycManager = jpoMemberNycManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering  'handleRequest'  method...");
        }

        String strAction = request.getParameter("action");
        if("export".equals(strAction)){
            return export(request,response);
        }else if("import".equals(strAction)){
            return importNyc(request,response);
        }
        else{
            return  query(request,response);
        }
    }

    private ModelAndView importNyc(HttpServletRequest request,
                                   HttpServletResponse response) throws Exception{


        return null;
    }

    private ModelAndView export(HttpServletRequest request,
                                HttpServletResponse response) throws Exception{
        String data=request.getParameter("data");

        String[] rows = data.split("\\|\\|");
        List<JpoNycQualified> jpoMemberNycs=new ArrayList<JpoNycQualified>();
        for(String row: rows){
            String[] cells = row.split("\\|");
            JpoNycQualified nycQualified=new JpoNycQualified();
//            nycQualified.setId(Long.parseLong(cells[0]));
            nycQualified.setJsWeek(Long.parseLong(cells[0]));
            nycQualified.setUserCode(cells[1]);
            nycQualified.setUserName(cells[2]);
            nycQualified.setPaperNumber(cells[3]);
            nycQualified.setRecommendNo(cells[4]);
            nycQualified.setRecommendName(cells[5]);
            nycQualified.setMemberOrderNo(cells[6]);
            nycQualified.setProductNo(cells[7]);
            nycQualified.setProductName(cells[8]);
            nycQualified.setQualify(cells[9]);
            nycQualified.setStatus(cells[10]);
            nycQualified.setRemarks(cells[11]);
            jpoMemberNycs.add(nycQualified);
        }

//            CommonRecord crm=RequestUtil.toCommonRecord(request);
//            Pager pager = new Pager("jpoMemberNycQualifiedListTable",request, 20);
//            List jpoMemberNycs = jpoMemberNycManager.getJpoMemberNycQualifiedsByCrm(crm,pager);
        ServletOutputStream os = response.getOutputStream();
        ExcelUtil excel=new ExcelUtil();
        WritableWorkbook workbook=Workbook.createWorkbook(os);

        WritableSheet sheet =workbook.createSheet("Sheet1",0);

        excel.addString(sheet,0,0,"期别");
        excel.addString(sheet,1,0,"会员编号");
        excel.addString(sheet,2,0,"会员名称");
        excel.addString(sheet,3,0,"身份证号");
        excel.addString(sheet,4,0,"推荐人编号");
        excel.addString(sheet,5,0,"推荐人名称");

        excel.addString(sheet,6,0,"订单编号");
        excel.addString(sheet,7,0,"商品编号");
        excel.addString(sheet,8,0,"商品名称");
        excel.addString(sheet,9,0,"政策");
        excel.addString(sheet,10,0,"状态");
        excel.addString(sheet,11,0,"备注");

        for(int i=0;i<jpoMemberNycs.size();i++){
            JpoNycQualified nycQualified= jpoMemberNycs.get(i);
            //excel.addNumber(sheet,0,i+1,nycQualified.getId());
            excel.addNumber(sheet,0,i+1,nycQualified.getJsWeek());
            excel.addString(sheet,1,i+1,nycQualified.getUserCode());
            excel.addString(sheet,2,i+1,nycQualified.getUserName());
            excel.addString(sheet,3,i+1,nycQualified.getPaperNumber());
            excel.addString(sheet,4,i+1,nycQualified.getRecommendNo());
            excel.addString(sheet,5,i+1,nycQualified.getRecommendName());
            excel.addString(sheet,6,i+1,nycQualified.getMemberOrderNo());
            excel.addString(sheet,7,i+1,nycQualified.getProductNo());
            excel.addString(sheet,8,i+1,nycQualified.getProductName());
            excel.addString(sheet,9,i+1,nycQualified.getQualify());
            excel.addString(sheet,10,i+1,nycQualified.getStatus());
            excel.addString(sheet,11,i+1,nycQualified.getRemarks());
        }


        excel.writeExcel(workbook);
        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Disposition", "attachment; filename=NYC_"+sdf.format(new Date())+".xls");
        response.setHeader("Pragma", "public");
        response.setHeader("Cache-Control", "max-age=30" );
        excel.closeWritableWorkbook(workbook);
        os.close();
        return null;
    }


    private ModelAndView query(HttpServletRequest request,
                               HttpServletResponse response)  throws Exception{
        JpoMemberNyc jpoMemberNyc = new JpoMemberNyc();
        // populate object with request parameters
        BeanUtils.populate(jpoMemberNyc, request.getParameterMap());
        //List jpoMemberNycs = jpoMemberNycManager.getJpoMemberNycs(jpoMemberNyc);
        /**** auto pagination ***/
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jpoMemberNycQualifiedListTable",request, 20);
        List jpoMemberNycs = jpoMemberNycManager.getJpoMemberNycQualifiedsByCrm(crm,pager);
        request.setAttribute("jpoMemberNycQualifiedListTable_totalRows", pager.getTotalObjects());
        /*****/
        return new ModelAndView("po/jpoMemberNycQualifiedList", Constants.JPOMEMBERNYCQUALIFIED, jpoMemberNycs);
    }
}
