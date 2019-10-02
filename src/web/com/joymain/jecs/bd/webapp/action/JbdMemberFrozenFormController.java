package com.joymain.jecs.bd.webapp.action;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.JbdMemberFrozen;
import com.joymain.jecs.bd.service.JbdMemberFrozenManager;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.webapp.action.BaseFormController;

public class JbdMemberFrozenFormController extends BaseFormController {
	private JbdMemberFrozenManager jbdMemberFrozenManager = null;
	private JdbcTemplate jdbcTemplate;
	
	public void setJbdMemberFrozenManager(JbdMemberFrozenManager jbdMemberFrozenManager) {
		this.jbdMemberFrozenManager = jbdMemberFrozenManager;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

    public JbdMemberFrozenFormController() {
        setCommandName("jbdMemberFrozen");
        setCommandClass(JbdMemberFrozen.class);
    }
    
	protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        return new JbdMemberFrozen();
    }

    public ModelAndView onSubmit(HttpServletRequest request,HttpServletResponse response, Object command,
                                 BindException errors) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        String strAction = request.getParameter("strAction");
        //新增
        if("addJbdMemberFrozen".equals(strAction)){
	        JbdMemberFrozen jbdMemberFrozen = (JbdMemberFrozen) command;
	        
	        if(jbdMemberFrozen.getUserCode()==null||"".equals(jbdMemberFrozen.getUserCode())){
	        	saveMessage(request, "会员编号不能为空！");
				return new ModelAndView("redirect:editJbdMemberFrozen.html?strAction=addJbdMemberFrozen");
	        }
	        
	        JbdMemberFrozen vo = jbdMemberFrozenManager.getJbdMemberFrozen(jbdMemberFrozen.getUserCode());
			if(vo != null){
				saveMessage(request, "该会员编号已经存在！");
				return new ModelAndView("redirect:editJbdMemberFrozen.html?strAction=addJbdMemberFrozen");
			}
			
	        jbdMemberFrozenManager.saveJbdMemberFrozen(jbdMemberFrozen);
	        saveMessage(request, "保存成功！");
        }
        //导入会员编号
        if("importJbdMemberFrozen".equals(strAction)){
        	return this.importJbdMemberFrozen(request);
        }
        return new ModelAndView("redirect:jbdMemberFrozens.html?strAction=viewJbdMemberFrozen");
    }
    
    /**
     * 入导Excle文件中的数据
     * @param request
     * @return
     * @throws Exception
     */
    public ModelAndView importJbdMemberFrozen(HttpServletRequest request) throws Exception{
    	 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
         CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("xlsFile");
         if(file!=null&&file.getSize()==0){
        	 saveMessage(request, "请选择要导入的文件！");
        	 return new ModelAndView("redirect:editJbdMemberFrozen.html?strAction=importJbdMemberFrozen");
         }
         InputStream stream = file.getInputStream();
         ExcelUtil eu = new ExcelUtil();
         //获取可读的工作表对象，定位到要读取的excel文件
         Workbook workbook = eu.getWorkbook(stream);
         //读取此文件的第一个工作表，工作表序号从0开始。
         Sheet sheet = workbook.getSheet(0);
         
         List<JbdMemberFrozen> list = new ArrayList<JbdMemberFrozen>();
         
         int rowCount = sheet.getRows();
         
         if(rowCount<=65536){
	         for(int i=1;i<rowCount;i++){
	        	 JbdMemberFrozen jbdMemberFrozen = new JbdMemberFrozen();
	        	 Cell[] cells = sheet.getRow(i);
	        	 String userCode = cells[0].getContents();
	        	 //过滤空数据
	        	 if(userCode!=null&&!"".equals(userCode)){
	        		 jbdMemberFrozen.setUserCode(userCode);
	        		 list.add(jbdMemberFrozen);
	        	 }
	        	 //每5000条插入一次数据
	        	 if(i%5000==0){
	        		 insertJbdMemberFrozen(list);
	        		 list.clear();
	        	 }
	         }
	         //导入数据少于每次5000条时或i%5000不为0时
	         if(list.size()>0){
	        	 insertJbdMemberFrozen(list);
	         }else if((rowCount-1)==0&&list.size()==0){
	        	 saveMessage(request, "没有要导入的会员编号数据！");
	        	 return new ModelAndView("redirect:editJbdMemberFrozen.html?strAction=importJbdMemberFrozen");
	         }
	         saveMessage(request, "导入成功！");
	         return new ModelAndView("redirect:jbdMemberFrozens.html?strAction=viewJbdMemberFrozen");
         }else{
        	 saveMessage(request, "导入失败，导入数据条数最大不超过65536条！");
        	 return new ModelAndView("redirect:editJbdMemberFrozen.html?strAction=importJbdMemberFrozen");
         }
    }
    
    /**
     * 批量插入数量
     * @param list
     */
    public void insertJbdMemberFrozen(List<JbdMemberFrozen> list) {
        final List<JbdMemberFrozen> temList = list;
        String sql = "insert into JBD_MEMBER_FROZEN(user_code) select ? user_code from dual " +   
                " where not exists (select user_code from JBD_MEMBER_FROZEN where user_code = ?)";
        try{
            this.jdbcTemplate.batchUpdate(sql, new MyBatchPreparedStatementSetter(temList));
        }catch (org.springframework.dao.DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 处理批量插入的回调类  
     *
     */
    private class MyBatchPreparedStatementSetter implements BatchPreparedStatementSetter{ 
        final List<JbdMemberFrozen> temList;
        
        public MyBatchPreparedStatementSetter(List<JbdMemberFrozen> list){
            temList = list;
        }
        
        public int getBatchSize() {
            return temList.size();
        }
        
		public void setValues(PreparedStatement ps, int i) throws SQLException {
			JbdMemberFrozen jbdMemberFrozen = temList.get(i);
			ps.setString(1, jbdMemberFrozen.getUserCode());
			ps.setString(2, jbdMemberFrozen.getUserCode());
		}
    }
}
