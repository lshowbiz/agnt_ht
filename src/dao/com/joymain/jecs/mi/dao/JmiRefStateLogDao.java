
package com.joymain.jecs.mi.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiRefStateLogDao extends Dao {

    List getJmiRefStateLogsByCrm(CommonRecord crm, Pager pager);

}


