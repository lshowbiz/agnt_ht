package com.joymain.jecs.pd.webapp.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.pd.model.PdShUrl;
import com.joymain.jecs.pd.model.PdWarehouseFrozenBatch;
import com.joymain.jecs.pd.service.PdWarehouseFrozenBatchManager;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

public class PdWarehouseFrozenBatchFormController extends BaseFormController {
	private PdWarehouseFrozenBatchManager pdWarehouseFrozenBatchManager = null;
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void setPdWarehouseFrozenBatchManager(PdWarehouseFrozenBatchManager pdWarehouseFrozenBatchManager) {
		this.pdWarehouseFrozenBatchManager = pdWarehouseFrozenBatchManager;
	}
	
	public PdWarehouseFrozenBatchFormController() {
		setCommandName("pdWarehouseFrozenBatch");
		setCommandClass(PdWarehouseFrozenBatch.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
	throws Exception {
		String batchId = request.getParameter("batchId");
		PdWarehouseFrozenBatch pdWarehouseFrozenBatch = null;

		//区别功能的参数
		String strAction = request.getParameter("strAction");
		request.setAttribute("strAction",strAction);

		if("photoBatchUpdate".equals(strAction)){//Modify By WuCF 20160118 瓜藤网图片
			//查询数据
			String FILE_URL = ListUtil.getListValue("CN", "jpmproductsaleimage.url", "1");

			BigDecimal[] images = new BigDecimal[3];
			String[] links = new String[3];
			String sql = "select T2.VALUE_CODE,t2.value_title VALUE_TITLE,t2.order_no ORDER_NO from jsys_list_key t1, "+
						 " jsys_list_value t2 where t1.key_id=t2.key_id "+
						 " and t1.list_code='guaten.images.url' ORDER BY T2.VALUE_CODE";
			List<Map<String,String>> list=jdbcTemplate.queryForList(sql);
			int num =0;
			int numTemp = 0;
        	for(Map<String,String> map : list){
        		numTemp = num++;
        		images[numTemp] = new BigDecimal(String.valueOf(map.get("ORDER_NO")));
        		links[numTemp] = map.get("VALUE_TITLE").toString();
        	}
			
			request.setAttribute("FILE_URL", FILE_URL);
			request.setAttribute("links", links);
			request.setAttribute("images", images);
			request.setAttribute("strAction", strAction);
			pdWarehouseFrozenBatch = new PdWarehouseFrozenBatch();
		}else{
			if (!StringUtils.isEmpty(batchId)) {
				pdWarehouseFrozenBatch = pdWarehouseFrozenBatchManager.getPdWarehouseFrozenBatch(batchId);
			} else {
				pdWarehouseFrozenBatch = new PdWarehouseFrozenBatch();
			}
		}

		return pdWarehouseFrozenBatch;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command,
			BindException errors)
	throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		PdWarehouseFrozenBatch pdWarehouseFrozenBatch = (PdWarehouseFrozenBatch) command;
		boolean isNew = (pdWarehouseFrozenBatch.getBatchId() == null);
		Locale locale = request.getLocale();
		String key=null;
		String strAction = request.getParameter("strAction");
		if ("deletePdWarehouseFrozenBatch".equals(strAction)  ) {
			pdWarehouseFrozenBatchManager.removePdWarehouseFrozenBatch(pdWarehouseFrozenBatch.getBatchId().toString());
			key="pdWarehouseFrozenBatch.delete";
		}else if("photoBatchUpdate".equals(strAction)){//Modify By WuCF 20160118 瓜藤网图片
			//获得文件对象，然后获得文件字节流
			MultipartHttpServletRequest multipartRequest =
				(MultipartHttpServletRequest) request;
			CommonsMultipartFile file1 =
				(CommonsMultipartFile) multipartRequest.getFile("file1");
			CommonsMultipartFile file2 =
				(CommonsMultipartFile) multipartRequest.getFile("file2");
			CommonsMultipartFile file3 =
				(CommonsMultipartFile) multipartRequest.getFile("file3");
			
			String imageLink1 = request.getParameter("imageLink1");
			String imageLink2 = request.getParameter("imageLink2");
			String imageLink3 = request.getParameter("imageLink3");

			//生成的图片名称
			String filename1 = this.getFileName("1");
			String filename2 = this.getFileName("2");
			String filename3 = this.getFileName("3");
			
			String filePath = ConfigUtil.getConfigValue("CN", "jpmproductsaleimage.path2")+System.getProperty("file.separator");
			if(file1!=null && file1.getSize()>0){
				DataOutputStream out1 = new DataOutputStream(new FileOutputStream(filePath +"guaten" +System.getProperty("file.separator") + filename1+".jpg")); 
				InputStream is = null;// 附件输入流 
				try {
					is = file1.getInputStream();
					byte[] b=new byte[is.available()];
					is.read(b);
					out1.write(b); 
				} catch (IOException exception) {
					exception.printStackTrace();
				} finally {
					if (is != null) {
						is.close();
					} 
					if (out1 != null) {
						out1.close();
					} 
				} 
			}

			if(file2!=null && file2.getSize()>0){
				DataOutputStream out2 = new DataOutputStream(new FileOutputStream(filePath +"guaten" +System.getProperty("file.separator")+ filename2+".jpg")); 
				InputStream is2 = null;// 附件输入流 
				try {
					is2 = file2.getInputStream();
					byte[] b=new byte[is2.available()];
					is2.read(b);
					out2.write(b); 
				} catch (IOException exception) {
					exception.printStackTrace();
				} finally {
					if (is2 != null) {
						is2.close();
					} 
					if (out2 != null) {
						out2.close();
					} 
				} 
			}

			if(file3!=null && file3.getSize()>0){
				DataOutputStream out3 = new DataOutputStream(new FileOutputStream(filePath +"guaten" +System.getProperty("file.separator")+ filename3+".jpg")); 
				InputStream is3 = null;// 附件输入流 
				try {
					is3 = file3.getInputStream();
					byte[] b=new byte[is3.available()];
					is3.read(b);
					out3.write(b); 
				} catch (IOException exception) {
					exception.printStackTrace();
				} finally {
					if (is3 != null) {
						is3.close();
					} 
					if (out3 != null) {
						out3.close();
					} 
				} 
			}
			
			StringBuffer sqlBuf = new StringBuffer();
			//图片1修改信息
			sqlBuf.append("update jsys_list_value t set t.value_title='"+imageLink1+"' ");
			if(file1!=null && file1.getSize()>0){
				sqlBuf.append(",t.order_No='");
				sqlBuf.append(filename1);
				sqlBuf.append("'");
			}
			sqlBuf.append(" where t.key_id= (select t1.key_id from jsys_list_key t1 where t.key_id=t1.key_id and t1.list_code='guaten.images.url')");
			sqlBuf.append(" and t.value_code='1'");
			
			//执行修改链接的操作
			jdbcTemplate.update(sqlBuf.toString());
			
			
			
			//图片2修改信息
			sqlBuf = new StringBuffer("update jsys_list_value t set t.value_title='"+imageLink2+"' ");
			if(file2!=null && file2.getSize()>0){
				sqlBuf.append(",t.order_No='");
				sqlBuf.append(filename2);
				sqlBuf.append("'");
			}
			sqlBuf.append(" where t.key_id= (select t1.key_id from jsys_list_key t1 where t.key_id=t1.key_id and t1.list_code='guaten.images.url')");
			sqlBuf.append(" and t.value_code='2'");
			
			//执行修改链接的操作
			jdbcTemplate.update(sqlBuf.toString());
			
			
			
			//图片3修改信息
			sqlBuf = new StringBuffer("update jsys_list_value t set t.value_title='"+imageLink3+"' ");
			if(file3!=null && file3.getSize()>0){
				sqlBuf.append(",t.order_No='");
				sqlBuf.append(filename3);
				sqlBuf.append("'");
			}
			sqlBuf.append(" where t.key_id= (select t1.key_id from jsys_list_key t1 where t.key_id=t1.key_id and t1.list_code='guaten.images.url')");
			sqlBuf.append(" and t.value_code='3'");
			
			//执行修改链接的操作
			jdbcTemplate.update(sqlBuf.toString());

			String FILE_URL = ListUtil.getListValue("CN", "jpmproductsaleimage.url", "1");

			request.setAttribute("FILE_URL", FILE_URL);
			request.setAttribute("strAction", strAction);
		}else{
			pdWarehouseFrozenBatchManager.savePdWarehouseFrozenBatch(pdWarehouseFrozenBatch);
			key="pdWarehouseFrozenBatch.update";
		}
		key="saveOrUpdate.success";
		saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), key));  
		return new ModelAndView("redirect:editPdWarehouseFrozenBatch.html?strAction=photoBatchUpdate");
	}
	
	/**
	 * 生成图片名称
	 * @param flag
	 * @return
	 */
	private String getFileName(String flag){
		Random r = new Random(); //实例化一个Random类
		Integer num = r.nextInt(99999);//随机产生一个整数：0~11
		
		return flag+num;
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
