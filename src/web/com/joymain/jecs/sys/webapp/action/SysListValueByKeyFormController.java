package com.joymain.jecs.sys.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.pd.model.PdShipStrategyMain;
import com.joymain.jecs.pd.service.PdShipStrategyMainManager;
import com.joymain.jecs.sys.model.SysListKey;
import com.joymain.jecs.sys.model.SysListValue;
import com.joymain.jecs.sys.service.SysListKeyManager;
import com.joymain.jecs.sys.service.SysListValueManager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.listener.StartupListener;
import com.joymain.jecs.webapp.util.LocaleUtil;

public class SysListValueByKeyFormController extends BaseFormController {
	private SysListValueManager sysListValueManager = null;
	private SysListKeyManager sysListKeyManager = null;
	private AlCompanyManager alCompanyManager = null;
	private PdShipStrategyMainManager pdShipStrategyMainManager = null;
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
    public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
        this.alCompanyManager = alCompanyManager;
    }
    
    public void setPdShipStrategyMainManager(PdShipStrategyMainManager pdShipStrategyMainManager) {
		this.pdShipStrategyMainManager = pdShipStrategyMainManager;
	}

	public void setSysListKeyManager(SysListKeyManager sysListKeyManager) {
		this.sysListKeyManager = sysListKeyManager;
	}

	public void setSysListValueManager(SysListValueManager sysListValueManager) {
		this.sysListValueManager = sysListValueManager;
	}

	public SysListValueByKeyFormController() {
		setCommandName("sysListValue");
		setCommandClass(SysListValue.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String valueId = request.getParameter("valueId");
		SysListValue sysListValue = null;

		if (!StringUtils.isEmpty(valueId)) {
			sysListValue = sysListValueManager.getSysListValue(valueId);
		} else {
			sysListValue = new SysListValue();
			
			String keyId=request.getParameter("keyId");
			SysListKey sysListKey=this.sysListKeyManager.getSysListKey(keyId);
			sysListValue.setSysListKey(sysListKey);
		}
		//获取不使用此值的公司
		String[] exCompanyCodes=new String[]{};
		if(!StringUtils.isEmpty(sysListValue.getExCompanyCode())){
			exCompanyCodes=sysListValue.getExCompanyCode().split(",");
		}
		request.setAttribute("exCompanyCodes", exCompanyCodes);
		//读取当前系统中所有的公司列表
		AlCompany alCompany = new AlCompany();
        List alCompanys = alCompanyManager.getAlCompanys(alCompany);
        request.setAttribute("alCompanys", alCompanys);

		return sysListValue;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		SysListValue sysListValue = (SysListValue) command;
		boolean isNew = (sysListValue.getValueId() == null);
		String keyId=request.getParameter("keyId");

		if ("deleteSysListValueByKey".equalsIgnoreCase(request.getParameter("strAction"))) {
			sysListValueManager.removeSysListValue(sysListValue.getValueId().toString());
			//重新载入
			StartupListener.setupContext(request.getSession().getServletContext(),true);
			saveMessage(request, getText("sysListValue.deleted"));
			
			//Modify By WuCF 20160120 删除对应的策略主从表中的数据
			SysListKey sysListKey=this.sysListKeyManager.getSysListKey(keyId);
			String listCode = sysListKey.getListCode();//添加参数时的变化
			if("ship.strategy".equals(listCode)){//商品类别
				pdShipStrategyMainManager.removePdShipStrategyMain(sysListValue.getValueId().toString());
			}
		} else {
			
			if ("addSysListValueByKey".equalsIgnoreCase(request.getParameter("strAction"))) {
				// 判断是否存在
				SysListValue oldSysListValue = this.sysListValueManager.getSysListValueByCode(keyId, sysListValue.getValueCode());
				if (oldSysListValue != null) {
					// 存在
					errors.reject("sysListValue.ValueCodeExits", new Object[] {},LocaleUtil.getLocalText("sysListValue.ValueCodeExits"));
					return showForm(request, response, errors);
				}
			}
			SysListKey sysListKey=this.sysListKeyManager.getSysListKey(keyId);
			sysListValue.setSysListKey(sysListKey);
			
			String exCompanyCode="";
			String[] companyCodes=request.getParameterValues("companyCode");
			if(companyCodes!=null){
				for(int i=0;i<companyCodes.length;i++){
					if(i>0){
						exCompanyCode+=",";
					}
					exCompanyCode+=companyCodes[i];
				}
			}
			sysListValue.setExCompanyCode(exCompanyCode);
			
			sysListValueManager.saveSysListValue(sysListValue);
			
			String key = (isNew) ? "sysListValue.added" : "sysListValue.updated";
			saveMessage(request, getText(key));
			
			StartupListener.setupContext(request.getSession().getServletContext(),true);
			
			//Modify By WuCF 20160120 新增或编辑对应主从表中的数据
			String listCode = sysListKey.getListCode();//添加参数时的变化
			if("ship.strategy".equals(listCode)){//商品类别
				PdShipStrategyMain pdShipStrategyMain = new PdShipStrategyMain();
				if(isNew){//新增
					pdShipStrategyMain.setKeyId(sysListValue.getSysListKey().getKeyId());
					pdShipStrategyMain.setValueId(sysListValue.getValueId());
					pdShipStrategyMain.setValueCode(sysListValue.getValueCode());
					pdShipStrategyMain.setValueTitle(sysListValue.getValueTitle());
					pdShipStrategyMain.setExCompanyCode(sysListValue.getExCompanyCode());
					pdShipStrategyMain.setOrderNo(sysListValue.getOrderNo());
					
					StringBuffer sqlBuf = new StringBuffer("insert into pd_ship_strategy_main(value_id,key_id,value_code,value_title,ex_company_code,order_no) values('");
					sqlBuf.append(sysListValue.getValueId());
					sqlBuf.append("','");
					sqlBuf.append(sysListValue.getSysListKey().getKeyId());
					sqlBuf.append("','");
					sqlBuf.append(sysListValue.getValueCode());
					sqlBuf.append("','");
					sqlBuf.append(sysListValue.getValueTitle());
					sqlBuf.append("','");
					sqlBuf.append(sysListValue.getExCompanyCode()==null ? "":sysListValue.getExCompanyCode());
					sqlBuf.append("','");
					sqlBuf.append(sysListValue.getOrderNo()==null ? "":sysListValue.getOrderNo());
					sqlBuf.append("')");
					
					jdbcTemplate.execute(sqlBuf.toString());
				}else{
					pdShipStrategyMain = pdShipStrategyMainManager.getPdShipStrategyMain(sysListValue.getValueId().toString());
					pdShipStrategyMain.setValueCode(sysListValue.getValueCode());
					pdShipStrategyMain.setValueTitle(sysListValue.getValueTitle());
					pdShipStrategyMain.setExCompanyCode(sysListValue.getExCompanyCode());
					pdShipStrategyMain.setOrderNo(sysListValue.getOrderNo());
					
					pdShipStrategyMainManager.savePdShipStrategyMain(pdShipStrategyMain);//保存
				}
			}
		}
		
		List sysListValues=sysListValueManager.getSysListValuesByKeyId(keyId);
		request.setAttribute("keyId", keyId);
		return new ModelAndView("sys/sysListValueByKeyList",Constants.SYSLISTVALUE_LIST, sysListValues);
	}
}
