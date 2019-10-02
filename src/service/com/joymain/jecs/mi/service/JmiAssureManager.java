
package com.joymain.jecs.mi.service;

import java.util.List;
import java.util.Map;

import org.springframework.validation.BindException;

import com.joymain.jecs.mi.model.JmiAssure;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiAssureManager extends Manager {
    /**
     * Retrieves all of the jmiAssures
     */
    public List getJmiAssures(JmiAssure jmiAssure);

    /**
     * Gets jmiAssure's information based on id.
     * @param id the jmiAssure's id
     * @return jmiAssure populated jmiAssure object
     */
    public JmiAssure getJmiAssure(final Long id);

    /**
     * Saves a jmiAssure's information
     * @param jmiAssure the object to be saved
     */
    public void saveJmiAssure(JmiAssure jmiAssure);
    
    public void updateJmiAssure(JmiAssure jmiAssure);

    /**
     * Removes a jmiAssure from the database by id
     * @param id the jmiAssure's id
     */
    public void removeJmiAssure(final Long id);
    //added for getJmiAssuresByCrm
    public List getJmiAssuresByCrm(CommonRecord crm, Pager pager);
    
    /**
     * 空值判断
     * @param errors
     * @param assureContent
     * @param characterCoding
     * @return
     */
    public boolean getCheckAssure(BindException errors, JmiAssure assure,String characterCoding);
    
    /**
     * 根据查询条件，获取报表数据
     */
    public List getReportByContion(CommonRecord crm);
    
    /**
     * 根据ID删除担保业务申请记录
     * @param id
     * @return 1代表成功  0失败
     */
    public int removeJmiAssureById(final String id);
    
    /**
     * 批量根据ID删除
     * @param id
     * @return 1代表成功  0失败
     */
    public int removeJmiAssureByIds(final String ids);
    
    public JpoMemberOrder getJpoMemberOrderByInterface(String memberOrderNo);
    
    /**
     * 批量新增
     */
    public void saveJmiAssureList(List<JmiAssure> jmiAssureList) throws Exception;
}

