package com.joymain.jecs.bd.webapp.action;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.JbdSubBonusList;
import com.joymain.jecs.bd.service.JbdSubBonusListManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdSubBonusListFormController extends BaseFormController {
    private JbdSubBonusListManager jbdSubBonusListManager = null;

	private JdbcTemplate jdbcTemplate = null;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public void setJbdSubBonusListManager(JbdSubBonusListManager jbdSubBonusListManager) {
        this.jbdSubBonusListManager = jbdSubBonusListManager;
    }
    public JbdSubBonusListFormController() {
        setCommandName("jbdSubBonusList");
        setCommandClass(JbdSubBonusList.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdSubBonusList jbdSubBonusList = null;

        if (!StringUtils.isEmpty(id)) {
            jbdSubBonusList = jbdSubBonusListManager.getJbdSubBonusList(id);
        } else {
            jbdSubBonusList = new JbdSubBonusList();
        }

        return jbdSubBonusList;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {

        SysUser defSysUser = SessionLogin.getLoginUser(request);
    	
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("importExcelFile");

		if (file == null || file.getInputStream() == null) {
			//文件读取错误
			errors.reject("bdBounsDeduct.importFile.failed");
			return showForm(request, response, errors);
		}

		String treatedNo=request.getParameter("treatedNo");
		String sql_treated="select count(*) from jbd_sub_bonus_list  where  treated_no='"+treatedNo+"'";
		Integer treatedNoCount=jdbcTemplate.queryForInt(sql_treated);
		if(treatedNoCount>0){
			this.saveMessage(request, "批号已存在");
			return new ModelAndView("redirect:editJbdSubBonusList.html");
		}
		Connection conn=null;
		PreparedStatement pstmt=null;
		Statement pstmt1=null;
		ResultSet rs=null;
		Integer count=0;
		try {
			//retrieve the file data
			InputStream stream = file.getInputStream();

			ExcelUtil eu = new ExcelUtil();
			//获取可读的工作表对象，定位到要读取的excel文件
			Workbook workbook = eu.getWorkbook(stream);
			//读取此文件的第一个工作表，工作表序号从0开始。
			Sheet sheet = workbook.getSheet(0);
			List<String> messages = new ArrayList<String>();
			
			
			Date curDate=new Date();
			conn=this.jdbcTemplate.getDataSource().getConnection();
			conn.setAutoCommit(false);
			//从第2行开始读,第一行为标题
			messages.add(LocaleUtil.getLocalText("bdBounsDeduct.impot.skip.first"));

			String sql="INSERT INTO jbd_sub_bonus_list (id,user_code,amount,remark,status,create_time,create_no,treated_no) "
					+ "VALUES (JD_SEQ.Nextval,?,?,?,'1',sysdate,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt1=conn.createStatement();
			for (int i = 1; i < sheet.getRows(); i++) {

				String userCode = eu.getContents(sheet, 0, i);
				String amount = eu.getContents(sheet, 1, i);
				String remark = eu.getContents(sheet, 2, i);
				
				if(StringUtil.isEmpty(userCode) || !StringUtil.isDouble(amount) ){
					continue;
				}
				if(new BigDecimal(amount).compareTo(new BigDecimal(0))!=1){
					continue;
				}
				if(new BigDecimal(amount).scale()>2){
					continue;
				}
				pstmt.setString(1, userCode);
				pstmt.setBigDecimal(2, new BigDecimal(amount));
				pstmt.setString(3, remark);
				pstmt.setString(4, defSysUser.getUserCode());
				pstmt.setString(5, treatedNo);
				pstmt.addBatch();
				//System.out.println(i);
			}
			pstmt.executeBatch();
			
			String sql1="select count(*) as count from jbd_sub_bonus_list a where not exists (select 1 from jmi_member b  where b.user_code=a.user_code ) and a.treated_no='"+treatedNo+"'";
			rs= pstmt1.executeQuery(sql1);
			
			if(rs.next()){
				count=rs.getInt("count");
			}
			if(count>0){
				conn.rollback();
			}else{
				conn.commit();
				this.saveMessage(request, "导入成功");
			}
			eu.closeWorkbook(workbook);
			//System.out.println("使用时间:");
			//System.out.println(new Date().getTime()-curDate.getTime());
		} catch (AppException e) {
			this.saveMessage(request, "金额存在不合法数字");
		}catch (IOException e) {
			this.saveMessage(request, getText("bdBounsDeduct.importFile.failed"));
			log.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			this.saveMessage(request, getText("bdBounsDeduct.import.data.error"));
			log.error(e.getMessage());
        } finally{
        	if(rs!=null){
        		rs.close();
        	}
        	if(pstmt1!=null){
        		pstmt1.close();
        	}
        	if(pstmt!=null){
        		pstmt.close();
        	}
        	if(conn!=null){
        		conn.close();
        	}
        	
			
        }
		if(count>0){
			this.saveMessage(request, "会员编号不存在");
		}
        return new ModelAndView("redirect:editJbdSubBonusList.html");
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
