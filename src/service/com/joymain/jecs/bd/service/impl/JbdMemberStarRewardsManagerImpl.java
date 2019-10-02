
package com.joymain.jecs.bd.service.impl;

import java.util.List;



import com.joymain.jecs.bd.dao.JbdMemberStarRewardsDao;
import com.joymain.jecs.bd.model.JbdMemberStarRewards;
import com.joymain.jecs.bd.service.JbdMemberStarRewardsManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdMemberStarRewardsManagerImpl extends BaseManager implements JbdMemberStarRewardsManager {
   
    private JbdMemberStarRewardsDao jbdMemberStarRewardsDao;

    public void setJbdMemberStarRewardsDao(
            JbdMemberStarRewardsDao jbdMemberStarRewardsDao)
    {
        super.setDao(jbdMemberStarRewardsDao);
        this.jbdMemberStarRewardsDao = jbdMemberStarRewardsDao;
    }

    @Override
    public List getJbdMemberStarRewardsByCrm(CommonRecord crm, Pager pager)
    {
        return jbdMemberStarRewardsDao.getJbdMemberStarRewardsByCrm(crm, pager);
    }

    @Override
    public void saveImportJbdMemberStarRewards(
            List<JbdMemberStarRewards> rewardLists)
    {
        for(JbdMemberStarRewards rewards : rewardLists) {
            jbdMemberStarRewardsDao.saveObject(rewards);
        }
    }

    @Override
    public void batchDeleteJbdMemberStarRewards(String ids)
    {
        String[] idArr = ids.split(",");
        for(String id : idArr) {
            this.removeObject(JbdMemberStarRewards.class, Long.valueOf(id));
        }
    }

}
