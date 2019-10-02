package com.joymain.jecs.fi.webapp.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.fi.model.FiBillAccount;
import com.joymain.jecs.fi.model.JfiQuota;
import com.joymain.jecs.fi.service.FiBillAccountManager;
import com.joymain.jecs.fi.service.JfiQuotaManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.PropertiesUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

@SuppressWarnings("rawtypes")
public class BusinessFromController extends BaseFormController {
	private FiBillAccountManager fiBillAccountManager = null;
	private JfiQuotaManager jfiQuotaManager = null;
	private AlCountryManager alCountryManager;
	private static String filePath = PropertiesUtil.getPropertie("BaseDeploy.properties", "keyFilePath");

	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public void setFiBillAccountManager(FiBillAccountManager fiBillAccountManager) {
		this.fiBillAccountManager = fiBillAccountManager;
	}

	public void setJfiQuotaManager(JfiQuotaManager jfiQuotaManager) {
		this.jfiQuotaManager = jfiQuotaManager;
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String accountId = request.getParameter("accountId");
		String strAction = request.getParameter("strReq");
		FiBillAccount fiBillAccount = null;
		if (!StringUtils.isEmpty(accountId)) {
			fiBillAccount = fiBillAccountManager.getFiBillAccount(accountId);
			CommonRecord crm = RequestUtil.toCommonRecord(request);
			crm.setValue("userCode", fiBillAccount.getUserCode());
			crm.setValue("province", fiBillAccount.getProvince());
			crm.setValue("businessType", fiBillAccount.getBusinessType());
			Pager pager = new Pager("fiBillAccountListTable", request, 5);
			List fiBillAccounts = fiBillAccountManager.getFiBillAccountsByCrm(crm, pager);
			request.setAttribute("fiBillAccountListTable_totalRows", pager.getTotalObjects());
			request.setAttribute("fiBillAccountList", fiBillAccounts);
		} else {
			fiBillAccount = new FiBillAccount();
		}
		AlCountry alCountry = new AlCountry();//获取地区
		alCountry = alCountryManager.getAlCountryByCode("CN");
		alCountry.getAlStateProvinces().iterator();
		request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
		request.setAttribute("fiBillAccount", fiBillAccount);
		request.setAttribute("strReq", strAction);
		return fiBillAccount;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) {
		String  _url = getSuccessView();
		try {
			FiBillAccount fiBillAccount = (FiBillAccount) command;
			SysUser loginUser = SessionLogin.getLoginUser(request);
			String strAction = request.getParameter("strReq");
			String accountId = request.getParameter("accountId");//商户号编码
			String oldMaxMoney = request.getParameter("oldMaxMoney"); // 历史的最多金额
			List tempList = null;
			if ("del".equals(strAction)) {
				fiBillAccountManager.removeFiBillAccount(fiBillAccount.getAccountId().toString());
				saveMessage(request, "删除成功!");
			} else if ("start".equals(strAction) && StringUtils.isNotEmpty(accountId)) {
				fiBillAccount = fiBillAccountManager.getFiBillAccount(accountId);
				if(!"0".equals(fiBillAccount.getStatus())){//当前商户号不是启动状态
					tempList = getBusinesss(fiBillAccount);
					if (tempList.size() > 0) {
						saveMessage(request, "1".equals(fiBillAccount.getBusinessType()) ? "该地区已存在启用的商户号!" : "该经销商对应的收款商户号已经存在！");
						return showForm(request, response, errors);
					}
					fiBillAccount.setStatus("0");
					oldMaxMoney = "";//表示需要修改最大限额
				}else{
					oldMaxMoney = fiBillAccount.getMaxMoney()+"";
					saveMessage(request, "操作成功!");
				}
				//_url = "redirect:editBusiness.html?strReq=edit&accountId="+accountId;
				strAction = "edit";
			} else if ("stop".equals(strAction) && StringUtils.isNotEmpty(accountId)) {
				fiBillAccount = fiBillAccountManager.getFiBillAccount(accountId);
				fiBillAccount.setStatus("1");
				oldMaxMoney = fiBillAccount.getMaxMoney()+"";//不去修改限额
				//_url = "redirect:editBusiness.html?strReq=edit&accountId="+accountId;
			} else {
				fiBillAccount.setCreateTime(new Date());
				fiBillAccount.setCreateUserCode(loginUser.getUserCode());
				fiBillAccount.setCreateUserName(loginUser.getUserName());
				tempList = getBusinesss(fiBillAccount);
				if (tempList.size() > 0) {
					saveMessage(request, "1".equals(fiBillAccount.getBusinessType()) ? "该地区已存在启用的商户号!" : "该经销商对应的收款商户号已经存在！");
					return showForm(request, response, errors);
				}
				if ("2".equals(fiBillAccount.getMerchantType())) {
					MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
					CommonsMultipartFile file1 = (CommonsMultipartFile) multipartRequest.getFile("filePassword");
					CommonsMultipartFile file2 = (CommonsMultipartFile) multipartRequest.getFile("filePassword2");
					CommonsMultipartFile[] fileArr = new CommonsMultipartFile[] { file1, file2 };
					String[] name = new String[fileArr.length];
					for (int i = 0; i < fileArr.length; i++) {
						name[i] = saveFile(fileArr[i], getPath(fiBillAccount));
					}
					if (StringUtils.isNotEmpty(name[0])) {
						fiBillAccount.setPassword(name[0]);
					}
					if (StringUtils.isNotEmpty(name[1])) {
						fiBillAccount.setPassword2(name[1]);
					}
				} else {
					fiBillAccount.setPassword(request.getParameter("filePassword"));
					fiBillAccount.setPassword2(request.getParameter("filePassword2"));
				}
				fiBillAccount.setCreateTime(new Date());
				fiBillAccount.setCreateUserCode(loginUser.getUserCode());
				fiBillAccount.setCreateUserName(loginUser.getUserName());
			}
			fiBillAccountManager.saveFiBillAccount(fiBillAccount);
			if ("2".equals(fiBillAccount.getBusinessType()) && "0".equals(fiBillAccount.getStatus())) { // 全额支付
				if (StringUtils.isEmpty(oldMaxMoney) || !oldMaxMoney.equals(String.valueOf(fiBillAccount.getMaxMoney()))) {// 如果最大限额变化，则修改新增一条记录，然后把以前记录禁用
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
					String validityPeriod = sdf.format(new Date());// 期别

					CommonRecord crm = new CommonRecord();
					crm.setValue("validityPeriod", validityPeriod);
					crm.setValue("accountId", fiBillAccount.getAccountId());
					crm.setValue("operatorName", loginUser.getUserCode());
					jfiQuotaManager.updateJfiQuotaStatus(crm);

					JfiQuota jfiQuota = new JfiQuota();
					jfiQuota.setValidityPeriod(validityPeriod);// 期别
					jfiQuota.setAccountId(fiBillAccount.getAccountId());// 商户号表主键
					jfiQuota.setStatus("0");// 状态默认启用
					jfiQuota.setMaxMoney(fiBillAccount.getMaxMoney());// 最大限额
					jfiQuota.setOperateName(loginUser.getUserCode());
					jfiQuota.setOperateTime(new Date());// 新增数据
					jfiQuotaManager.saveJfiQuota(jfiQuota);
				}
			}
			saveMessage(request, "操作成功!");
		} catch (Exception e) {
			saveMessage(request, "操作失败!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView(_url);
	}
	/**
	 * 获取 同一个地区或者会员启用商户号个数
	 * @param fiBillAccount
	 * @return
	 */
	private List getBusinesss(FiBillAccount fiBillAccount) {
		List tempList = new ArrayList();
		FiBillAccount searchBean = new FiBillAccount();
		if ("0".equals(fiBillAccount.getStatus())) { // 如果是启动的会员需要验证
			searchBean.setStatus(fiBillAccount.getProvince());
			if ("1".equals(fiBillAccount.getBusinessType())) {
				searchBean.setProvince(fiBillAccount.getProvince());// 一个地区只能有一个启用的
			} else {
				searchBean.setUserCode(fiBillAccount.getUserCode());// 一个会员只能有一个启用的
			}
			tempList = fiBillAccountManager.getFiBillAccounts(searchBean);
		}
		return tempList;
	}

	private String saveFile(MultipartFile file, String filePath) {
		try {
			if ((file != null) && (!(file.isEmpty()))) {
				createFile(filePath);
				filePath += "/" + file.getOriginalFilename();
				file.transferTo(new File(filePath));
				return filePath;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * filePath/PC/1 文件上传存储目录
	 * @param fiBillAccount
	 * @return
	 */
	private String getPath(FiBillAccount fiBillAccount){
		String path = "1".equals(fiBillAccount.getAccountType()) ? "/PC/" : "/APP/";
		return filePath + path + fiBillAccount.getProviderType();
	}
	
	private void createFile(String filePath) {
		try {
			File file = new File(filePath);
			// 如果文件夹不存在则创建
			if (!file.exists() && !file.isDirectory()) {
				System.out.println("//不存在");
				file.mkdirs();
			} else {
				System.out.println("//目录存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		BusinessFromController b = new BusinessFromController();
		b.createFile("D:\\zz1\\bdq");
	}

}
