package com.joymain.jecs.webapp.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.util.string.StringUtil;

/**
 * Tag for creating multiple &lt;select&gt; options for displaying a list of
 * company names.
 * 
 * <p>
 * <b>NOTE</b> - This tag requires a Java2 (JDK 1.2 or later) platform.
 * </p>
 * 
 * @author Alvin
 * @version
 * 
 * @jsp.tag name="period" bodycontent="empty"
 */
public class RegionTag extends TagSupport {
	private String regionId;

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	private String regionType;

	public void setRegionType(String regionType) {
		this.regionType = regionType;
	}

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		if (StringUtil.isEmpty(regionId)) {
			return this.SKIP_BODY;
		}
		try {
			if ("P".equalsIgnoreCase(regionType)) {
				ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(this.pageContext.getServletContext());
				AlStateProvinceManager alStateProvinceManager = (AlStateProvinceManager) context.getBean("alStateProvinceManager");
				AlStateProvince alStateProvince = alStateProvinceManager.getAlStateProvince(regionId);
				if (alStateProvince != null) {
					this.pageContext.getOut().write(alStateProvince.getStateProvinceName());
				}
			}else if ("C".equalsIgnoreCase(regionType)) {
				ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(this.pageContext.getServletContext());
				AlCityManager alCityManager = (AlCityManager) context.getBean("alCityManager");
				AlCity alCity = alCityManager.getAlCity(regionId);
				if (alCity != null) {
					this.pageContext.getOut().write(alCity.getCityName());
				}
			}else if ("D".equalsIgnoreCase(regionType)) {
				ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(this.pageContext.getServletContext());
				AlDistrictManager  alDistrictManager = (AlDistrictManager) context.getBean("alDistrictManager");
				AlDistrict alDistrict = alDistrictManager.getAlDistrict(regionId);
				if (alDistrict != null) {
					this.pageContext.getOut().write(alDistrict.getDistrictName());
				}
			}
			return this.SKIP_BODY;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.SKIP_BODY;
	}

}
