
package com.joymain.jecs.po.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.po.model.JpoInviteList;
import com.joymain.jecs.po.dao.JpoInviteListDao;
import com.joymain.jecs.po.service.JpoInviteListManager;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpoInviteListManagerImpl extends BaseManager implements JpoInviteListManager {
    private JpoInviteListDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpoInviteListDao(JpoInviteListDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.po.service.JpoInviteListManager#getJpoInviteLists(com.joymain.jecs.po.model.JpoInviteList)
     */
    @Override
	public List getJpoInviteLists(final JpoInviteList jpoInviteList) {
        return dao.getJpoInviteLists(jpoInviteList);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoInviteListManager#getJpoInviteList(String id)
     */
    @Override
	public JpoInviteList getJpoInviteList(final String id) {
        return dao.getJpoInviteList(new Long(id));
    }

    /**
     * @see com.joymain.jecs.po.service.JpoInviteListManager#saveJpoInviteList(JpoInviteList jpoInviteList)
     */
    @Override
	public void saveJpoInviteList(JpoInviteList jpoInviteList) {
        dao.saveJpoInviteList(jpoInviteList);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoInviteListManager#removeJpoInviteList(String id)
     */
    @Override
	public void removeJpoInviteList(final String id) {
        dao.removeJpoInviteList(new Long(id));
    }
    //added for getJpoInviteListsByCrm
    @Override
	public List getJpoInviteListsByCrm(CommonRecord crm, Pager pager){
	return dao.getJpoInviteListsByCrm(crm,pager);
    }
    
    /**
     * 根据存储过程获取邀请码
     * @author 2017-5-22 fu 
     * @return String
     */
    @Override
	public String getJpoInviteListForProcedure(){
    	 return dao.getJpoInviteListForProcedure();
    }
    
    /**
     * 查询会员可用的邀请码个数
     * @author 2017-5-22 fu 
     * @param userCode
     * @return int 
     */
    @Override
	public int getAvailableSum(String userCode){
    	 return dao.getAvailableSum(userCode);
    }
    
    /**
     * 批量将邀请码设置为不可用状态
     * @author 2017-5-22 fu 
     * @param userCode
     * @param qty
     * @param memberOrderNo
     * @param editUserCode
     * @return boolean 
     */
    @Override
	public boolean maintainJpoInviteList(String userCode,int qty,String memberOrderNo,String editUserCode){
    	return dao.maintainJpoInviteList(userCode,qty,memberOrderNo,editUserCode);
    }
    

	@Override
	public void saveInviteCode(SysUser user,String userCodeInvite, int num,String orderNo,String remark,String type,String newDate) {
		List<JpoInviteList> listJpo = new ArrayList<JpoInviteList>();
		if (num>0) {//增加
			for (int i = 0; i < num; i++) {
				String code = this.getJpoInviteListForProcedure();
				Date cd = new Date();
				if(!MeteorUtil.isBlank(newDate)){
					try {
						cd = MeteorUtil.doConvertToDate(newDate);
					} catch (Exception e) {
						log.error("日期格式转换错误",e);
						e.printStackTrace();
					}
				}
				
				/*JpoInviteList invite = new JpoInviteList(userCodeInvite, code, orderNo, 
						cd, "0", "", null, 0,remark,type,user.getUserCode(),null,null);*/
				JpoInviteList invite = new JpoInviteList();
				invite.setUserCode(userCodeInvite);
				invite.setInviteCode(code);
				invite.setMemberOrderNo(orderNo);
				invite.setCreateTime(cd);
				invite.setStatus("0");
				invite.setVersion(0L);
				invite.setRemark(remark);
				invite.setInviteType(type);
				invite.setEditUserCode(user.getUserCode());
				
				listJpo.add(invite);
			}
			dao.saveJpoInvLists(listJpo);
		}else{//删除
			int count = Math.abs(num);
			List jpoInviteListByHql = dao.getJpoInviteListByHql("from JpoInviteList where userCode='"+userCodeInvite+"' and status=0");
			if (jpoInviteListByHql.size()>=count) {
				for (int i = 0; i < count; i++) {
					JpoInviteList jpoInvite= (JpoInviteList) jpoInviteListByHql.get(i);
					jpoInvite.setStatus("2");//作废
					jpoInvite.setRemark(remark);
					jpoInvite.setEditTime(new Date());
					jpoInvite.setEditUserCode(user.getUserCode());
					dao.saveJpoInviteList(jpoInvite);
				}
			}else{
				for (int i = 0; i < jpoInviteListByHql.size(); i++) {
					JpoInviteList jpoInvite= (JpoInviteList) jpoInviteListByHql.get(i);
					jpoInvite.setStatus("2");//作废
					jpoInvite.setRemark(remark);
					jpoInvite.setEditTime(new Date());
					jpoInvite.setEditUserCode(user.getUserCode());
					dao.saveJpoInviteList(jpoInvite);
				}
			}
		}
	}

	@Override
	public List getJpoInviteListByHql(String userCode) {
		List jpoInviteListByHql = dao.getJpoInviteListByHql("from JpoInviteList where userCode='"+userCode+"' and status=0");
		return jpoInviteListByHql;
	}
}
