
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.bd.model.JbdMemberCongrations;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdMemberCongrationsManager extends Manager {

    List getJbdMemberCongrationsByCrm(CommonRecord crm, Pager pager);

    void saveImportJbdMemberCongrations(List<JbdMemberCongrations> congrationsLists);

}

