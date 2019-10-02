
package com.joymain.jecs.am.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.am.model.InwViewpeople;
import com.joymain.jecs.am.dao.InwViewpeopleDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface InwViewpeopleManager extends Manager {
    /**
     * Retrieves all of the inwViewpeoples
     */
    public List getInwViewpeoples(InwViewpeople inwViewpeople);

    /**
     * Gets inwViewpeople's information based on id.
     * @param id the inwViewpeople's id
     * @return inwViewpeople populated inwViewpeople object
     */
    public InwViewpeople getInwViewpeople(final String id);

    /**
     * 创新共赢的建议-查询-向inwViewpeople表中录入数据
     * @author gw 2013-08-21 
     * @param inwViewpeople
     */
    public void saveInwViewpeople(InwViewpeople inwViewpeople);

    /**
     * Removes a inwViewpeople from the database by id
     * @param id the inwViewpeople's id
     */
    public void removeInwViewpeople(final String id);
    //added for getInwViewpeoplesByCrm
    public List getInwViewpeoplesByCrm(CommonRecord crm, Pager pager);

    /**
     *  通过建议表的ID获得建议查看的对象
     *  @author 2013-09-13
     * @param string
     * @return InwViewpeople
     */
	public InwViewpeople getInwViewpeopleBySuggestionId(String string);
}

