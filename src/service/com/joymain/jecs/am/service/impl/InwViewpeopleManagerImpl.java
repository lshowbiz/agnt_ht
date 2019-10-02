
package com.joymain.jecs.am.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.am.model.InwViewpeople;
import com.joymain.jecs.am.dao.InwViewpeopleDao;
import com.joymain.jecs.am.service.InwViewpeopleManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class InwViewpeopleManagerImpl extends BaseManager implements InwViewpeopleManager {
    private InwViewpeopleDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setInwViewpeopleDao(InwViewpeopleDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.am.service.InwViewpeopleManager#getInwViewpeoples(com.joymain.jecs.am.model.InwViewpeople)
     */
    public List getInwViewpeoples(final InwViewpeople inwViewpeople) {
        return dao.getInwViewpeoples(inwViewpeople);
    }

    /**
     * @see com.joymain.jecs.am.service.InwViewpeopleManager#getInwViewpeople(String id)
     */
    public InwViewpeople getInwViewpeople(final String id) {
        return dao.getInwViewpeople(new Long(id));
    }

    /**
     * 创新共赢的建议-查询-向inwViewpeople表中录入数据
     * @author gw 2013-08-21 
     * @param inwViewpeople
     */
    public void saveInwViewpeople(InwViewpeople inwViewpeople) {
        dao.saveInwViewpeople(inwViewpeople);
    }

    /**
     * @see com.joymain.jecs.am.service.InwViewpeopleManager#removeInwViewpeople(String id)
     */
    public void removeInwViewpeople(final String id) {
        dao.removeInwViewpeople(new Long(id));
    }
    //added for getInwViewpeoplesByCrm
    public List getInwViewpeoplesByCrm(CommonRecord crm, Pager pager){
	return dao.getInwViewpeoplesByCrm(crm,pager);
    }

    /**
     *  通过建议表的ID获得建议查看的对象
     *  @author 2013-09-13
     * @param string
     * @return InwViewpeople
     */
	public InwViewpeople getInwViewpeopleBySuggestionId(String suggestionId) {
		return dao.getInwViewpeopleBySuggestionId(suggestionId);
	}
}
