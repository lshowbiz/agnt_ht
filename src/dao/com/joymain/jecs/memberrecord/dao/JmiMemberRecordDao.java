
package com.joymain.jecs.memberrecord.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiMemberRecordDao extends Dao {

    public List getJmiMemberRecordList(CommonRecord crm, Pager pager) ;
}

