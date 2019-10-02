
package com.joymain.jecs.bd.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdMemberCongrationsDao extends Dao {

    List getJbdMemberCongrationsByCrm(CommonRecord crm, Pager pager);

}

