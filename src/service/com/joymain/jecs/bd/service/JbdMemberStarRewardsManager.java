
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.bd.model.JbdMemberStarRewards;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdMemberStarRewardsManager extends Manager {

    List getJbdMemberStarRewardsByCrm(CommonRecord crm, Pager pager);

    void saveImportJbdMemberStarRewards(List<JbdMemberStarRewards> rewardLists);

    void batchDeleteJbdMemberStarRewards(String ids);

}

