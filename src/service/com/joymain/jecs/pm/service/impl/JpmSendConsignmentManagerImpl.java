
package com.joymain.jecs.pm.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.mi.model.JmiAddrBook;
import com.joymain.jecs.pm.dao.JpmSendConsignmentDao;
import com.joymain.jecs.pm.model.JpmSendConsignment;
import com.joymain.jecs.pm.service.JpmSendConsignmentManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpmSendConsignmentManagerImpl extends BaseManager implements JpmSendConsignmentManager {
    private JpmSendConsignmentDao dao;
    
    private AlCityManager alCityManager;
    
    private AlStateProvinceManager alStateProvinceManager;
    
    private AlDistrictManager alDistrictManager;


    public void setAlCityManager(AlCityManager alCityManager)
    {
        this.alCityManager = alCityManager;
    }

    public void setAlStateProvinceManager(AlStateProvinceManager alStateProvinceManager)
    {
        this.alStateProvinceManager = alStateProvinceManager;
    }

    public void setAlDistrictManager(AlDistrictManager alDistrictManager)
    {
        this.alDistrictManager = alDistrictManager;
    }

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpmSendConsignmentDao(JpmSendConsignmentDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmSendConsignmentManager#getJpmSendConsignments(com.joymain.jecs.pm.model.JpmSendConsignment)
     */
    public List getJpmSendConsignments(final JpmSendConsignment jpmSendConsignment) {
        return dao.getJpmSendConsignments(jpmSendConsignment);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmSendConsignmentManager#getJpmSendConsignment(String consignmentNo)
     */
    public JpmSendConsignment getJpmSendConsignment(final String consignmentNo) {
        return dao.getJpmSendConsignment(new BigDecimal(consignmentNo));
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmSendConsignmentManager#saveJpmSendConsignment(JpmSendConsignment jpmSendConsignment)
     */
    public void saveJpmSendConsignment(JpmSendConsignment jpmSendConsignment) {
        dao.saveJpmSendConsignment(jpmSendConsignment);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmSendConsignmentManager#removeJpmSendConsignment(String consignmentNo)
     */
    public void removeJpmSendConsignment(final String consignmentNo) {
        dao.removeJpmSendConsignment(new BigDecimal(consignmentNo));
    }
    //added for getJpmSendConsignmentsByCrm
    public List getJpmSendConsignmentsByCrm(CommonRecord crm, Pager pager){
	return dao.getJpmSendConsignmentsByCrm(crm,pager);
    }
    
    @Override
    public List<JpmSendConsignment> getJpmSendConsignmentListBySpecNo(Long specNo)
    {
        return dao.getJpmSendConsignmentListBySpecNo(specNo);
    }
    
    @Override
    public void delJpmSendConsignmentByConsignmentNo(Long consignmentNo)
    {
        dao.delJpmSendConsignmentByConsignmentNo(consignmentNo);
    }
    
    @Override
    public JpmSendConsignment getJpmSendConsignmentByConsignmentNo(Long consignmentNo)
    {
        return dao.getJpmSendConsignmentByConsignmentNo(consignmentNo);
    }
    
    @Override
    public String getAddresByFabId(JmiAddrBook jmiAddrBook)
    {
        // 省
        AlStateProvince province = alStateProvinceManager.getAlStateProvince(jmiAddrBook.getProvince());
        // 市
        AlCity city = alCityManager.getAlCity(jmiAddrBook.getCity());
        // 区
        AlDistrict district = new AlDistrict();
        if(StringUtils.isNotEmpty(jmiAddrBook.getDistrict()))
        {
            district = alDistrictManager.getAlDistrict(jmiAddrBook.getDistrict());
        }
        
        StringBuffer address = new StringBuffer();
        if(StringUtils.isNotEmpty(province.getStateProvinceName()))
        {
            address.append(province.getStateProvinceName());
        }
        if (StringUtils.isNotEmpty(city.getCityName()))
        {
            address.append(city.getCityName());
        }
        if (StringUtils.isNotEmpty(district.getDistrictName()))
        {
            address.append(district.getDistrictName());
        }
        if (StringUtils.isNotEmpty(jmiAddrBook.getAddress()))
        {
            address.append(jmiAddrBook.getAddress());
        }
        if (StringUtils.isNotEmpty(jmiAddrBook.getFirstName()))
        {
            address.append("( ");
            address.append(jmiAddrBook.getFirstName());
            address.append(jmiAddrBook.getLastName());
            address.append(" )");
        }
        if (StringUtils.isNotEmpty(jmiAddrBook.getPhone()))
        {
            address.append(jmiAddrBook.getPhone());
        }
        
        return address.toString();
    }
}
