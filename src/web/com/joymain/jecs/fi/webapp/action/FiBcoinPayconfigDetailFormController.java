package com.joymain.jecs.fi.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.io.InputStream;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.pm.model.JpmProduct;
import com.joymain.jecs.pm.service.JpmProductManager;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.fi.model.FiBankbookTemp;
import com.joymain.jecs.fi.model.FiBcoinPayconfig;
import com.joymain.jecs.fi.model.FiBcoinPayconfigDetail;
import com.joymain.jecs.fi.service.FiBcoinPayconfigDetailManager;
import com.joymain.jecs.fi.service.FiBcoinPayconfigManager;

import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

public class FiBcoinPayconfigDetailFormController extends BaseFormController {
	private FiBcoinPayconfigManager fiBcoinPayconfigManager = null;
    private FiBcoinPayconfigDetailManager fiBcoinPayconfigDetailManager = null;
    private JpmProductManager jpmProductManager = null;

    public void setJpmProductManager(JpmProductManager jpmProductManager) {
        this.jpmProductManager = jpmProductManager;
    }
    public void setFiBcoinPayconfigDetailManager(FiBcoinPayconfigDetailManager fiBcoinPayconfigDetailManager) {
        this.fiBcoinPayconfigDetailManager = fiBcoinPayconfigDetailManager;
    }
    public void setFiBcoinPayconfigManager(FiBcoinPayconfigManager fiBcoinPayconfigManager) {
        this.fiBcoinPayconfigManager = fiBcoinPayconfigManager;
    }
    public FiBcoinPayconfigDetailFormController() {
        setCommandName("fiBcoinPayconfigDetail");
        setCommandClass(FiBcoinPayconfigDetail.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
    	
        String detailId = request.getParameter("detailId");
        FiBcoinPayconfigDetail fiBcoinPayconfigDetail = null;

        if (!StringUtils.isEmpty(detailId)) {
        	
            fiBcoinPayconfigDetail = fiBcoinPayconfigDetailManager.getFiBcoinPayconfigDetail(detailId);
        } else {
            fiBcoinPayconfigDetail = new FiBcoinPayconfigDetail();
        }
        
        String configId = request.getParameter("configId");//配置ID
        
        FiBcoinPayconfig fiBcoinPayconfig = fiBcoinPayconfigManager.getFiBcoinPayconfig(configId);
        request.setAttribute("fiBcoinPayconfig", fiBcoinPayconfig);
        
        fiBcoinPayconfigDetail.setConfigId(fiBcoinPayconfig.getConfigId());
        
        String flag = request.getParameter("flag");//添加方式，1：单个新增，2：导入
        request.setAttribute("flag", flag);
        
        return fiBcoinPayconfigDetail;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiBcoinPayconfigDetail fiBcoinPayconfigDetail = (FiBcoinPayconfigDetail) command;
        boolean isNew = (fiBcoinPayconfigDetail.getDetailId() == null);
        Locale locale = request.getLocale();
		String key=null;
		String strAction = request.getParameter("strAction");
		if ("deleteFiBcoinPayconfigDetail".equals(strAction)  ) {
			
			fiBcoinPayconfigDetailManager.removeFiBcoinPayconfigDetail(fiBcoinPayconfigDetail.getDetailId().toString());
		}else{
			
			String flag = request.getParameter("flag");//添加方式，1：单个新增，2：导入
			if(("1").equals(flag)){
				
				//校验，商品编码是否存在
				JpmProduct jpmProduct = jpmProductManager.getJpmProduct(fiBcoinPayconfigDetail.getProductNo());
				if(jpmProduct == null){
					
					saveMessage(request, "该商品编号不存在!");
					return new ModelAndView("redirect:editFiBcoinPayconfig.html?strAction=editFiBcoinPayconfig&configId="+fiBcoinPayconfigDetail.getConfigId());
				}
				
				//校验不要重复
				FiBcoinPayconfigDetail tempConfigDetail = fiBcoinPayconfigDetailManager.getFiBcoinPayconfigDetailsByProductNo(fiBcoinPayconfigDetail.getConfigId().toString(), fiBcoinPayconfigDetail.getProductNo());
								
				if(isNew){//新增
					if(tempConfigDetail!=null){
						
						saveMessage(request, "该商品编号在本促销活动中已经添加过了!");
						return new ModelAndView("redirect:editFiBcoinPayconfig.html?strAction=editFiBcoinPayconfig&configId="+fiBcoinPayconfigDetail.getConfigId());
					}
					
					fiBcoinPayconfigDetail.setNowQuantity(fiBcoinPayconfigDetail.getSumQuantity());
					saveMessage(request, "添加成功!");
				}else{//更新

					saveMessage(request, "修改成功!");
				}
				
				//保存
				fiBcoinPayconfigDetailManager.saveFiBcoinPayconfigDetail(fiBcoinPayconfigDetail);				
			}
			
			//批量导入
			if(("2").equals(flag)){
				
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("xlsFile");
				//retrieve the file data
				InputStream stream = file.getInputStream();

				ExcelUtil eu = new ExcelUtil();
				//获取可读的工作表对象，定位到要读取的excel文件
				Workbook workbook = eu.getWorkbook(stream);
				//读取此文件的第一个工作表，工作表序号从0开始。
				Sheet sheet = workbook.getSheet(0);

				List<FiBcoinPayconfigDetail> detailTemps = new ArrayList<FiBcoinPayconfigDetail>();
				for (int i = 1; i < sheet.getRows(); i++) {
					
					FiBcoinPayconfigDetail tempDetail = new FiBcoinPayconfigDetail();
					
					//商品编号
					String productNo = eu.getContents(sheet, 0, i);
					
					//限购数量
					String sumQuantity = eu.getContents(sheet, 1, i);
					if (!StringUtils.isEmpty(sumQuantity)) {
						sumQuantity = StringUtils.trim(sumQuantity);
						tempDetail.setSumQuantity(new Long(sumQuantity));//限购总数
						tempDetail.setNowQuantity(new Long(sumQuantity));//当前剩余数
					}					
					
					if (!StringUtils.isEmpty(productNo)) {
						productNo = StringUtils.trim(productNo);
					}else{
						MessageUtil.saveLocaleMessage(request, "第"+i+"行商品编号为空!");
						continue;
					}
					
					//校验，商品编码是否存在
					JpmProduct jpmProduct = jpmProductManager.getJpmProduct(productNo);
					if(jpmProduct == null){
						
						saveMessage(request,  "第"+i+"行商品编号不存在!");
					}else{
						
						tempDetail.setConfigId(fiBcoinPayconfigDetail.getConfigId());
						tempDetail.setProductNo(productNo);
					
						//校验不要重复
						FiBcoinPayconfigDetail tempConfigDetail = fiBcoinPayconfigDetailManager.getFiBcoinPayconfigDetailsByProductNo(tempDetail.getConfigId().toString(), tempDetail.getProductNo());
						
						if(tempConfigDetail!=null){
							
							saveMessage(request,  "第"+i+"行商品编号在本促销活动中已经添加过了!");
						}else{
							
							detailTemps.add(tempDetail);
						}
					}		
				}
				
				fiBcoinPayconfigDetailManager.saveFiBcoinPayconfigDetails(detailTemps);
				saveMessage(request, "导入操作执行完毕!");
			}
		}

		return new ModelAndView("redirect:editFiBcoinPayconfig.html?strAction=editFiBcoinPayconfig&configId="+fiBcoinPayconfigDetail.getConfigId());
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
