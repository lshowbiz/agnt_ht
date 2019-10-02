package com.joymain.jecs.mi.webapp.action;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.joymain.jecs.bd.dao.BdPeriodDao;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiRemitSale;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiRemitSaleManager;
import com.joymain.jecs.pm.model.JmiMemberTeam;
import com.joymain.jecs.pm.service.JmiMemberTeamManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;

public class JmiRemitSaleImportController extends BaseFormController
{
    private JmiMemberManager jmiMemberManager = null;
    private JmiMemberTeamManager jmiMemberTeamManager = null;
    private JdbcTemplate jdbcTemplate;
    private JmiRemitSaleManager jmiRemitSaleManager;
    private BdPeriodDao dao;
   
    public JmiRemitSaleImportController()
    {
        setCommandName("jmiRemitSale");
        setCommandClass(JmiRemitSale.class);
    }
    
    public ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception
    {
        try
        {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("importExcelFile");
            if (file == null || file.getInputStream() == null){
                //文件读取错误
                errors.reject("bdBounsDeduct.importFile.failed");
                return showForm(request, response, errors);
            }
            String strAction = request.getParameter("strAction");
            
            // 设置上传路径
            //retrieve the file data
            InputStream stream = file.getInputStream();
            ExcelUtil eu = new ExcelUtil();
            //获取可读的工作表对象，定位到要读取的excel文件
            Workbook workbook = eu.getWorkbook(stream);
            //读取此文件的第一个工作表，工作表序号从0开始。
            Sheet sheet = workbook.getSheet(0);
            
            List<String> messages = new ArrayList<String>();
            List<String> sqls = new ArrayList<String>();
            Set<String> set = new HashSet<String>();
            int errCount = 0;
            
            //从第2行开始读,第一行为标题
            messages.add(LocaleUtil.getLocalText("bdBounsDeduct.impot.skip.first"));
            for (int i = 1; i < sheet.getRows(); i++)
            {
                //会员编号,免重消开始时间,免重消结束时间,备注
                String userCode = eu.getContents(sheet, 0, i);
                String startWeek = eu.getContents(sheet, 1, i);
                String endWeek = eu.getContents(sheet, 2, i);
                String remark = eu.getContents(sheet, 3, i);
                String errLine = "excel第"+(i+1)+"行";
                String content = " ([ " + userCode + ","+startWeek+","+endWeek+","+remark+","+errLine+" ])";
                String message = (i + 1) + ": ";
                String msg=userCode+","+startWeek+","+endWeek;
                
                JmiRemitSale jmiRemitSale = new JmiRemitSale();
                
                //获取当前创建人
                SysUser sysUser = (SysUser) request.getSession().getAttribute("sessionLogin");
    			jmiRemitSale.setCreateUser(sysUser.getUserCode());
    			//设置创建时间
    			jmiRemitSale.setCreateTime(this.getDate());
                String createTime = jmiRemitSale.getCreateTime();
				String createUser = jmiRemitSale.getCreateUser();
				
				StringBuilder builder = new StringBuilder();
	            BigDecimal id = new BigDecimal(0);
				//校验文件数据
	            if (StringUtils.isEmpty(userCode)){
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "会员编号不能为空" + content + "</font>");
                    errCount++;continue;
                }else {
                	jmiRemitSale.setUserCode(userCode);
                }
				if(set.contains(msg)){
              	  messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "此条数据存在重复，请删除" + content + "</font>");
                    		errCount++;continue;
				}else{
              		set.add(msg);
				}
				if(startWeek.length()!=6 || !StringUtils.isNumeric(startWeek)){ 
					messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "开始期别格式有误，数字且长度为6位" + content + "</font>");
                    errCount++;continue;
				}
				if(endWeek.length()!=6 || !StringUtils.isNumeric(startWeek)){
					messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "结束期别格式有误，数字且长度为6位" + content + "</font>");
                    errCount++;continue;
				}
                if (StringUtils.isEmpty(startWeek)){
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "免重消开始日期不能为空" + content + "</font>");
                    errCount++;continue;              
                } else {
                	jmiRemitSale.setStartWeek(new BigDecimal(startWeek));
                }
                if (StringUtils.isEmpty(endWeek)){
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "免重消结束日期不能为空" + content + "</font>");
                    errCount++;continue;
                } else {
                	jmiRemitSale.setEndWeek(new BigDecimal(endWeek));
                }         
                
                BigDecimal bstartWeek =new BigDecimal(startWeek);
                BigDecimal bendWeek = new BigDecimal(endWeek);
        		if(getBdPeriod() >= bstartWeek.intValue()){
        				messages.add("<font color=red>" + message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "免重消开始时间不能在已结算期内" + content + "</font>");
                        errCount++;continue;
        		}
                if(bstartWeek.intValue() > bendWeek.intValue()){
                	messages.add("<font color=red>" + message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                    + " - " + "免重消开始日期要小于等于结束日期" + content + "</font>");
                	errCount++;continue;
                }
            	
                int startweek = Integer.parseInt(startWeek);
    			int endweek = Integer.parseInt(endWeek);
    			if("remitSaleImport".equals(strAction)){
    				// 查出是属于免重销的客户周期不能添加
    				if (isRemitSale(userCode, startweek, endweek) != 0) {
        				messages.add("<font color=red>" + message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                        + " - " + "该会员日期已存在免重消期内" + content + "</font>");
                    	errCount++;continue;
        			}
    				JmiMember jmiMember = this.jmiMemberManager.getJmiMember(userCode);
                    if (jmiMember == null){
                    		messages.add("<font color=red>"
                    				+ message
                    				+ LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                    				+ " - " + "会员编号不存在" + content + "</font>");
                    		errCount++;continue;
                    }
                    BigDecimal tid = getRandomId(id);
                    String sql = "insert into Ws_Tp_Member ( start_week , end_week, remark, user_code ,id,create_user,create_time) values ("+ startWeek + "," + endWeek + "," + "'" + remark + "'" + "," + "'" + userCode + "'"
					+ "," + tid + ","+ "'"+createUser+"'"+","+"TO_DATE"+"("+"'"+createTime+"'"+","+"'"+"yyyy-mm-dd hh24:mi"+"'"+")"+")";
                    sqls.add(sql);
    			}
    			
    			if("remitSaleTeamImport".equals(strAction)){
    				builder.append(" SELECT X1.USER_CODE FROM (SELECT a.user_code,a.RECOMMEND_NO,level, ");
    				builder.append(" row_number() over (partition by a.user_code order by level) rn ");
    				builder.append(" FROM JMI_MEMBER a START WITH a.user_code in ('"+userCode+"') ");
    				builder.append(" CONNECT BY PRIOR a.user_code = a.RECOMMEND_NO order by 1) X1 WHERE X1.RN=1 ");
    				//判断团队编号是否正确
    					JmiMemberTeam jmiMemberTeam = jmiMemberTeamManager.getJmiMemberTeam(userCode);
    					if ( jmiMemberTeam == null){
    						messages.add("<font color=red>"
    								+ message
    								+ LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
    								+ " - " + "团队编号不存在，请检查团队编号是否有误" + content + "</font>");
    						errCount++;continue;
    					}
    					List member = jdbcTemplate.queryForList(builder.toString());
    					for(int j = 0;j<member.size();j++){
    						userCode = member.get(j).toString().substring(11,21);
    						// 查出是属于免重销的客户周期不能添加
    	    				if (isRemitSale(userCode, startweek, endweek) != 0) {
    	        				messages.add("<font color=red>" + message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
    	                        + " - " + "该会员日期已存在免重消期内" + userCode+"," + startWeek+"," + endWeek +","+ remark + "</font>");
    	                    	errCount++;continue;
    	        			}
    						BigDecimal tid = getRandomId(id);
    						String sql = "insert into Ws_Tp_Member ( start_week , end_week, remark, user_code ,id,create_user,create_time) values ("+ startWeek + "," + endWeek + "," + "'" + remark + "'" + "," + "'" + userCode + "'"
    								+ "," + tid + ","+ "'"+createUser+"'"+","+"TO_DATE"+"("+"'"+createTime+"'"+","+"'"+"yyyy-mm-dd hh24:mi"+"'"+")"+")";
    						sqls.add(sql);
    					}
                }
            }
            eu.closeWorkbook(workbook);
           
            if (errCount == 0 && !sqls.isEmpty()){
                for (String string : sqls) {
                	try{
                		this.jdbcTemplate.execute(string);
                	}catch(Exception e){
                		e.printStackTrace();
                	}
				}
                this.saveMessage(request, "操作成功");
                return new ModelAndView(this.getSuccessView());
            }
            request.setAttribute("messages", messages);
            request.setAttribute("isFinished", true);
            request.setAttribute("errCount", errCount);
            return new ModelAndView(this.getFormView());
        }
        catch (IOException e){
            this.saveMessage(request,getText("bdBounsDeduct.importFile.failed"));
            log.error(e.getMessage());
        }
        catch (Exception e){
            this.saveMessage(request,getText("bdBounsDeduct.import.data.error"));
            log.error(e.getMessage());
        }
        return new ModelAndView(this.getSuccessView());
    }
    
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		super.initBinder(request, binder);
	}
    
	private BigDecimal getRandomId(BigDecimal id){
		String orderNo = "";
		String trandNo = String.valueOf((Math.random() * 9 + 1) * 1000000);
		orderNo = trandNo.toString().substring(0, 8);
		id = new BigDecimal(orderNo);
		return id;
	}
	// 获取当前创建时间
	private String getDate() throws SQLException {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			return sdf.format(new Date());
		}
	//--------------------------------数据校验的方法-------------------------------
	
	private int isRemitSale(String userCode, int startweek, int endweek) {
		// 查出已經是免重销的客户周期 ,不允许在输入
		List<Map<String, Object>> starEndWeek = jmiRemitSaleManager.ajaxStarEndtWeek(userCode);

		for (int i = 0; i < starEndWeek.size(); i++) {
			Map<String, Object> Map = (Map<String, Object>) starEndWeek.get(i);
			int value1 = Integer.parseInt(Map.get("startWeek").toString());
			int value2 = Integer.parseInt(Map.get("endWeek").toString());

			if (startweek <= value2 && startweek >= value1) {
				// 警告flag 1 输入日期已经在免重消范围内
				return 1;
			}
			if (endweek <= value2 && endweek >= value1) {
				// 警告flag 1 输入日期已经在免重消范围内
				return 2;
			}
			if (startweek <= value1 && endweek >= value2) {
				return 3;
			}
		}
		return 0;
	}

	// 获取最新结算周
	// 获取当前周数 wid随意设置 。
	public int getBdPeriod() {
		int maxBdPeriodWeek = 0;
		BdPeriod bdPeriod = dao.getBdPeriodByTime(new Date(), (long) 1);
		int currentYear = bdPeriod.getWyear();
		// ajaxBdPeriodBonus 1 获取本年已经结算的周期 0 获取本年没结算的周期。
		List<Map<String, Object>> BdPeriodBonus = jmiRemitSaleManager.ajaxBdPeriodBonus("1", currentYear);
		for (int i = 0; i < BdPeriodBonus.size(); i++) {
			Map<String, Object> Map = (Map<String, Object>) BdPeriodBonus.get(i);
			 String tempWeek = Integer.parseInt(Map.get("wWeek").toString())<10?"0"+Integer.parseInt(Map.get("wWeek").toString()):Map.get("wWeek").toString();
			int week = Integer.parseInt(Map.get("wYear").toString() + tempWeek);
			if (week > maxBdPeriodWeek) {
				maxBdPeriodWeek = week;
			}
		}
		return maxBdPeriodWeek;
	}
	public void setBdPeriodDao(BdPeriodDao dao) {
			this.dao = dao;
	}
	public void setJmiRemitSaleManager(JmiRemitSaleManager jmiRemitSaleManager) {
			this.jmiRemitSaleManager = jmiRemitSaleManager;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
	        this.jdbcTemplate = jdbcTemplate;
	}
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
	        this.jmiMemberManager = jmiMemberManager;
	}    
	public void setJmiMemberTeamManager(JmiMemberTeamManager jmiMemberTeamManager) {
		this.jmiMemberTeamManager = jmiMemberTeamManager;
	}    
}
