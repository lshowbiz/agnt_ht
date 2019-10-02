package com.joymain.jecs.mi.webapp.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.util.ConfigUtil;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.webapp.action.BaseFormController;

public class BatchParentCodeResultController extends BaseFormController
{
    
	private JdbcTemplate jdbcTemplate = null;
	private ExcelUtil eu = null;
	private WritableSheet wsheet = null;
	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}    
	public BatchParentCodeResultController() {
		setCommandName("batchParentCodeResult");
	}
    
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		return "";
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
            
            eu = new ExcelUtil();
            //获取可读的工作表对象，定位到要读取的excel文件
            Workbook workbook = eu.getWorkbook(stream);
            //读取此文件的第一个工作表，工作表序号从0开始。
            Sheet sheet = workbook.getSheet(0);
            
            //从第2行开始读,第一行为标题
            String maxImportRows = ConfigUtil.getConfigValue("CN", "import.batchparentcoderesult.rows");
            if(MeteorUtil.isBlank(maxImportRows)){
            	maxImportRows = "1000";
            }
            String userCodes = "";
            
            for (int i = 1; i < sheet.getRows(); i++) {
//            	logger.info(sheet.getRows());
            	if(sheet.getRows()>Integer.valueOf(maxImportRows)+1){
            		this.saveMessage(request,
    	                    getText("一次最多导入"+ Integer.valueOf(maxImportRows) +"条数据"));
            		return new ModelAndView(this.getSuccessView());
            	}
                //会员编号
                String userCode = eu.getContents(sheet, 0, i);//会员编号
                if(!MeteorUtil.isBlank(userCode)){
                	userCodes += "'" + userCode.trim() + "',";
                }

            }
            
            eu.closeWorkbook(workbook);
            
          //生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=ParentCodeResult.xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			eu = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			//在此创建的新excel文件创建一工作表 
			wsheet = wwb.createSheet("Sheet1", 0);
			
			//设置列头
			excelTopColumn(eu,wsheet,0,"会员编号,会员姓名,推荐政策委编号,推荐政策委姓名,安置政策委编号,安置政策委姓名,推荐战略委编号,推荐战略委姓名,安置战略委编号,安置战略委姓名,团队编号,团队姓名");
			if(!MeteorUtil.isBlank(userCodes)){
				userCodes = userCodes.substring(0,userCodes.length()-1);
//				log.info("userCodes============="+userCodes);
				StringBuffer sb = new StringBuffer("SELECT USER_CODE,USER_NAME,RE_ZCWCODE,RE_ZCWNAME,LI_ZCWCODE,LI_ZCWNAME,RE_SYTCODE,RE_SYTNAME,LI_SYTCODE,LI_SYTNAME,TEAM_CODE,TEAM_NAME FROM JMI_PARENT_CODE_RESULT ");
				sb.append(" where 1=1  ");
				
				String[] us = userCodes.split(",");
				String tempus = "";
				String tsflag  = "1";
				for(int z=0;z<us.length;z++){
					tempus += us[z] + ",";
					if(z>0 && z%990==0){
						tempus = tempus.substring(0,tempus.length()-1);
						if("1".equals(tsflag)){
							sb.append(" and USER_CODE in("+tempus+") ");
							tsflag = "0";
						}else{
							sb.append(" or USER_CODE in("+tempus+") ");
						}
						
						tempus = "";
					}
					if(z == us.length-1){
						if(!MeteorUtil.isBlank(tempus)){
							tempus = tempus.substring(0,tempus.length()-1);
							if("1".equals(tsflag)){
								sb.append(" and USER_CODE in("+tempus+") ");
								tsflag = "0";
							}else{
								sb.append(" or USER_CODE in("+tempus+") ");
							}
							tempus = "";
						}
					}
					
				}
				
				
				log.info("sb.toString============="+sb.toString());
				this.jdbcTemplate.query(sb.toString(), new ResultSetExtractor() {
					public Object extractData(ResultSet rs) throws SQLException {
						try {
							int kk = 1;
							while (rs.next()) {
								int index=0;
								eu.addString(wsheet, index++, kk, rs.getString("USER_CODE"));
								eu.addString(wsheet, index++, kk, rs.getString("USER_NAME"));
								eu.addString(wsheet, index++, kk, rs.getString("RE_ZCWCODE"));
								eu.addString(wsheet, index++, kk, rs.getString("RE_ZCWNAME"));
								eu.addString(wsheet, index++, kk, rs.getString("LI_ZCWCODE"));
								eu.addString(wsheet, index++, kk, rs.getString("LI_ZCWNAME"));
								eu.addString(wsheet, index++, kk, rs.getString("RE_SYTCODE"));
								eu.addString(wsheet, index++, kk, rs.getString("RE_SYTNAME"));
								eu.addString(wsheet, index++, kk, rs.getString("LI_SYTCODE"));
								eu.addString(wsheet, index++, kk, rs.getString("LI_SYTNAME"));
								eu.addString(wsheet, index++, kk, rs.getString("TEAM_CODE"));
								eu.addString(wsheet, index++, kk, rs.getString("TEAM_NAME"));
				
								kk++;
							}
		
						} catch (Exception e) {
							e.printStackTrace();
						}finally {  
							JdbcUtils.closeResultSet(rs);
						}
						return null;
					}
				});
				eu.writeExcel(wwb);
				eu.closeWritableWorkbook(wwb);
				os.close();

				return null;
			}else{
				this.saveMessage(request,
	                    getText("请按模版填入正确的会员编号"));
			}
			
            return new ModelAndView(this.getSuccessView());
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
    
    /**
     * @Description excel列头
     * @author houxyu
     * @date 2016-3-28
     * @param eu
     * @param wsheet
     * @param row  列头填充到第几行
     */
    public void excelTopColumn(ExcelUtil eu,WritableSheet wsheet,int row,String columns) throws Exception{
    	String [] cs = null;
		if(!MeteorUtil.isBlank(columns)){
			cs = columns.split(",");
		}
		for(int i=0;i<cs.length;i++){
			//列头  后续改为公共方法。
			eu.addString(wsheet, i, row, cs[i]);//工作表对象，列，行，值
		}
    }
}
