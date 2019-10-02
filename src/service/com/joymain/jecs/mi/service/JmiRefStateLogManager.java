
package com.joymain.jecs.mi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiRefStateLogManager extends Manager {

    List getJmiRefStateLogsByCrm(CommonRecord crm, Pager pager);

}

