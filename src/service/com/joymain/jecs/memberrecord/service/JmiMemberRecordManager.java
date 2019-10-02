
package com.joymain.jecs.memberrecord.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiMemberRecordManager extends Manager {
    public List getJmiMemberRecordList(CommonRecord crm, Pager pager);
}

