package com.joymain.jecs.mi.webapp.action;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.mi.model.JmiAddrBook;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiAddrBookManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.po.model.JpoShippingFee;
import com.joymain.jecs.po.service.JpoShippingFeeManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiAddrBookController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiAddrBookController.class);
    private JmiAddrBookManager jmiAddrBookManager = null;
    private AlCountryManager alCountryManager;
    private JmiMemberManager jmiMemberManager = null;
    private AlCityManager alCityManager;
    private AlDistrictManager alDistrictManager;
    public void setAlCityManager(AlCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}
	public void setAlDistrictManager(AlDistrictManager alDistrictManager) {
		this.alDistrictManager = alDistrictManager;
	}
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}
    public void setJmiAddrBookManager(JmiAddrBookManager jmiAddrBookManager) {
        this.jmiAddrBookManager = jmiAddrBookManager;
    }

	private JdbcTemplate jdbcTemplate = null;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	private AlStateProvinceManager alStateProvinceManager;
    public void setAlStateProvinceManager(
			AlStateProvinceManager alStateProvinceManager) {
		this.alStateProvinceManager = alStateProvinceManager;
	}
    private JpoShippingFeeManager jpoShippingFeeManager;
    
	public void setJpoShippingFeeManager(JpoShippingFeeManager jpoShippingFeeManager) {
		this.jpoShippingFeeManager = jpoShippingFeeManager;
	}
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
    	SysUser defSysUser=SessionLogin.getLoginUser(request);

    	String countryCode=null;
    	
    	if("M".equals(defSysUser.getUserType())){
        	JmiMember jmiMember=jmiMemberManager.getJmiMember(defSysUser.getUserCode());
        	countryCode=jmiMember.getCompanyCode();
        }else{
        	countryCode=defSysUser.getCompanyCode();
        	
        }
    	
        AlCountry alCountry = new AlCountry();//获取地区
    	alCountry = alCountryManager.getAlCountryByCode(countryCode);
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());

        CommonRecord crm=RequestUtil.toCommonRecord(request);

        Pager pager = new Pager("jmiAddrBookListTable",request, 20);
        
        if("M".equals(defSysUser.getUserType())){
            crm.addField("userCode", defSysUser.getUserCode());
        }else{
        	if(StringUtil.isEmpty(request.getParameter("userCode"))){

                request.setAttribute("jmiAddrBookListTable_totalRows", pager.getTotalObjects());
                return new ModelAndView("mi/jmiAddrBookList", Constants.JMIADDRBOOK_LIST, null);
        	}
        	crm.addField("companyCode", defSysUser.getCompanyCode());
        }
        
        
        
        List<JmiAddrBook> jmiAddrBooks = jmiAddrBookManager.getJmiAddrBooksByCrm(crm,pager);
        for (JmiAddrBook book : jmiAddrBooks) {
			List<AlCity> citys=alCityManager.getAlCityByProvinceId(new Long(book.getProvince()));
			for (AlCity city : citys) {
				if(city.getCityId().toString().equals(book.getCity())){
					book.setCity(city.getCityName());
					List<AlDistrict> alDistricts = alDistrictManager.getAlDistrictByCityId(new Long(city.getCityId()));
					for (AlDistrict district : alDistricts) {
						if(district.getDistrictId().toString().equals(book.getDistrict())){
							book.setDistrict(district.getDistrictName());
							break;
						}
					}
					break;
				}
			}
       	
       	
        	
		}
        
        
        
        request.setAttribute("jmiAddrBookListTable_totalRows", pager.getTotalObjects());

        

 //       setFeel();
        
 //       setCity();
        
        //setCityFeel(request);
        
        //setProvinceFeel(request);
        return new ModelAndView("mi/jmiAddrBookList", Constants.JMIADDRBOOK_LIST, jmiAddrBooks);
    }
	private void setProvinceFeel(HttpServletRequest request){
		AlCountry alCountry = new AlCountry();//获取地区
    	alCountry = alCountryManager.getAlCountryByCode("CN");
    	Set<AlStateProvince> provinces=alCountry.getAlStateProvinces();
    	int a=0;
    	String shippingType="DTW";
    	for (AlStateProvince province : provinces) {
    		JpoShippingFee jpoShippingFee=new JpoShippingFee();
			jpoShippingFee.setProvince(province.getStateProvinceId().toString());
			jpoShippingFee.setCity("");
			jpoShippingFee.setDistrict("");
			jpoShippingFee.setCountryCode("CN");

			
    		CommonRecord crm = RequestUtil.toCommonRecord(request);
    		crm.addField("province", province.getStateProvinceId().toString());
    		crm.addField("shippingType", shippingType);
    		List list = jpoShippingFeeManager.getJpoShippingFeesByCrm(crm, new Pager(request,0));
			
//			JpoShippingFee searchjpoShippingFee=new JpoShippingFee();
//			searchjpoShippingFee.setProvince(province.getStateProvinceId().toString());
//			searchjpoShippingFee.setShippingType(shippingType);
//			List list=jpoShippingFeeManager.getJpoShippingFees(searchjpoShippingFee);
			
			
			jpoShippingFee.setShippingType(shippingType);
			jpoShippingFee.setShippingFee(((JpoShippingFee)list.get(0)).getShippingFee());
			jpoShippingFee.setShippingRefee(((JpoShippingFee)list.get(0)).getShippingRefee());
			jpoShippingFee.setShippingWeight(((JpoShippingFee)list.get(0)).getShippingWeight());
			jpoShippingFee.setShippingReweight(((JpoShippingFee)list.get(0)).getShippingReweight());
			System.out.println(a++);
			jpoShippingFeeManager.saveJpoShippingFee(jpoShippingFee);
    	}
	}
	private void setCityFeel(HttpServletRequest request){
        AlCountry alCountry = new AlCountry();//获取地区
    	alCountry = alCountryManager.getAlCountryByCode("CN");
    	Set<AlStateProvince> provinces=alCountry.getAlStateProvinces();
    	int a=0;
    	String shippingType="DTW";
    	for (AlStateProvince province : provinces) {
    		Set<AlCity> citys=province.getAlCitys();
			for (AlCity city : citys) {
				
				if(city.getAlDistricts().size()>0){
					JpoShippingFee jpoShippingFee=new JpoShippingFee();
					jpoShippingFee.setProvince(province.getStateProvinceId().toString());
					jpoShippingFee.setCity(city.getCityId().toString());
					jpoShippingFee.setDistrict("");
					jpoShippingFee.setCountryCode("CN");

					

		    		CommonRecord crm = RequestUtil.toCommonRecord(request);
		    		crm.addField("province", province.getStateProvinceId().toString());
		    		crm.addField("city", city.getCityId().toString());
		    		crm.addField("shippingType", shippingType);
		    		List list = jpoShippingFeeManager.getJpoShippingFeesByCrm(crm, new Pager(request,0));
					
//					JpoShippingFee searchjpoShippingFee=new JpoShippingFee();
//					searchjpoShippingFee.setProvince(province.getStateProvinceId().toString());
//					searchjpoShippingFee.setCity(city.getCityId().toString());
//					searchjpoShippingFee.setShippingType(shippingType);
//					List list=jpoShippingFeeManager.getJpoShippingFees(searchjpoShippingFee);
					
					
					jpoShippingFee.setShippingType(shippingType);
					jpoShippingFee.setShippingFee(((JpoShippingFee)list.get(0)).getShippingFee());
					jpoShippingFee.setShippingRefee(((JpoShippingFee)list.get(0)).getShippingRefee());
					jpoShippingFee.setShippingWeight(((JpoShippingFee)list.get(0)).getShippingWeight());
					jpoShippingFee.setShippingReweight(((JpoShippingFee)list.get(0)).getShippingReweight());
					System.out.println(a++);
					jpoShippingFeeManager.saveJpoShippingFee(jpoShippingFee);
				}
				
			}
    	}
	}
	private void setFeel(){

        AlCountry alCountry = new AlCountry();//获取地区
    	alCountry = alCountryManager.getAlCountryByCode("CN");
    	Set<AlStateProvince> provinces=alCountry.getAlStateProvinces();
    	for (AlStateProvince province : provinces) {
			Set<AlCity> citys=province.getAlCitys();
			for (AlCity city : citys) {
				Set<AlDistrict> alDistricts=city.getAlDistricts();

				
				if(alDistricts.size()>0){
					for (AlDistrict district : alDistricts) {
						JpoShippingFee jpoShippingFee=new JpoShippingFee();
						jpoShippingFee.setProvince(province.getStateProvinceCode());
						jpoShippingFee.setCity(city.getCityCode());
						jpoShippingFee.setDistrict(district.getDistrictCode());
						jpoShippingFee.setCountryCode("CN");
						jpoShippingFee.setShippingType("G001");
						jpoShippingFee.setShippingFee(new BigDecimal(0));
						jpoShippingFeeManager.saveJpoShippingFee(jpoShippingFee);
					}
				}else{
					JpoShippingFee jpoShippingFee=new JpoShippingFee();
					jpoShippingFee.setProvince(province.getStateProvinceCode());
					jpoShippingFee.setCity(city.getCityCode());
					jpoShippingFee.setCountryCode("CN");
					jpoShippingFee.setShippingType("G001");
					jpoShippingFee.setShippingFee(new BigDecimal(0));
					jpoShippingFeeManager.saveJpoShippingFee(jpoShippingFee);
				}
			}
		}
		
		
		
		
	}
	private void setCity(){
		try {
			DataSource ds=jdbcTemplate.getDataSource();
	        
	        Connection conn=ds.getConnection();
	        Statement st=conn.createStatement();
			
			
			ResultSet rs = st.executeQuery("select * from city order by sz_code");

	        AlCountry alCountry= new AlCountry();
	    	alCountry = alCountryManager.getAlCountryByCode("CN");
	    	
			String province="";
			String proviceCode="";
			
			String city="";
			String cityCode="";
			while (rs.next()) {
				String name=rs.getString("name");
				String zmCode=rs.getString("zm_code");
				if(StringUtil.isEmpty(zmCode)){
					if("市辖区".equals(name)){
						zmCode=proviceCode+"s";
					}else if("县".equals(name)){
						zmCode=proviceCode+"x";
					}else{
						zmCode=proviceCode+(int)(Math.random()*10);
					}
				}else{
					zmCode=zmCode.replaceAll(" ", "");
				}
				String szCode=rs.getString("sz_code");
					
					if("0000".equals(StringUtils.right(szCode, 4))){

						AlStateProvince alStateProvince=new AlStateProvince();
						alStateProvince.setStateProvinceCode(zmCode);
						alStateProvince.setStateProvinceName(name);
						alStateProvince.setAlCountry(alCountry);
						alStateProvinceManager.saveAlStateProvince(alStateProvince);

						province=szCode;
						proviceCode=zmCode;
						
					}else if(StringUtils.left(province, 2).equals(StringUtils.left(szCode, 2))&&"00".equals(StringUtils.right(szCode, 2))){
						AlStateProvince al=alStateProvinceManager.getAlStateProvinceByCode(alCountry.getCountryId().toString(), proviceCode);
						AlCity alcity=new AlCity();
						alcity.setCityName(name);
						alcity.setCityCode(zmCode);
						alcity.setAlStateProvince(al);
						alCityManager.saveAlCity(alcity);
						
						city=szCode;
						cityCode=zmCode;
					}else if(StringUtils.left(city, 4).equals(StringUtils.left(szCode, 4))){
						AlCity alcity=alCityManager.getAlCityByCityCode(cityCode);
						AlDistrict alDistrict=new AlDistrict();
						alDistrict.setDistrictCode(zmCode);
						alDistrict.setDistrictName(name);
						alDistrict.setAlCity(alcity);
						alDistrictManager.saveAlDistrict(alDistrict);
					}
				
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
      
	}
}
