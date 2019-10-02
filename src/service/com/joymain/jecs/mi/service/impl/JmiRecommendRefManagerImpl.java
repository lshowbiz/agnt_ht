
package com.joymain.jecs.mi.service.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;

import com.joymain.jecs.Constants;
import com.joymain.jecs.mi.dao.JmiMemberDao;
import com.joymain.jecs.mi.dao.JmiRecommendRefDao;
import com.joymain.jecs.mi.dao.JmiRefStateLogDao;
import com.joymain.jecs.mi.model.JmiRecommendRef;
import com.joymain.jecs.mi.service.JmiRecommendRefManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JmiRecommendRefManagerImpl extends BaseManager implements JmiRecommendRefManager {
    private JmiRecommendRefDao dao;
    private JmiMemberDao jmiMemberDao;
    private JmiRefStateLogDao jmiRefStateLogDao;
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public void setJmiRefStateLogDao(JmiRefStateLogDao jmiRefStateLogDao) {
        this.jmiRefStateLogDao = jmiRefStateLogDao;
    }
	public void setJmiMemberDao(JmiMemberDao jmiMemberDao) {
		this.jmiMemberDao = jmiMemberDao;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiRecommendRefDao(JmiRecommendRefDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiRecommendRefManager#getJmiRecommendRefs(com.joymain.jecs.mi.model.JmiRecommendRef)
     */
    public List getJmiRecommendRefs(final JmiRecommendRef jmiRecommendRef) {
        return dao.getJmiRecommendRefs(jmiRecommendRef);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiRecommendRefManager#getJmiRecommendRef(String memberNo)
     */
    public JmiRecommendRef getJmiRecommendRef(final String memberNo) {
        return dao.getJmiRecommendRef(new String(memberNo));
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiRecommendRefManager#saveJmiRecommendRef(JmiRecommendRef jmiRecommendRef)
     */
    public void saveJmiRecommendRef(JmiRecommendRef jmiRecommendRef) {
        dao.saveJmiRecommendRef(jmiRecommendRef);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiRecommendRefManager#removeJmiRecommendRef(String memberNo)
     */
    public void removeJmiRecommendRef(final String memberNo) {
        dao.removeJmiRecommendRef(new String(memberNo));
    }
    //added for getJmiRecommendRefsByCrm
    public List getJmiRecommendRefsByCrm(CommonRecord crm, Pager pager){
	return dao.getJmiRecommendRefsByCrm(crm,pager);
    }

	public List getRecommendRefbyRecommendNo(String recommendNo) {
		return dao.getRecommendRefbyRecommendNo(recommendNo);
	}

	public String getCallProcChangeRecommend(String memberNo, String recommendNo, String userCode, String noCheck) {
		return dao.getCallProcChangeRecommend(memberNo, recommendNo, userCode, noCheck);
	}

	public List getJmiRecommendRefsByRecommendNo(String recommendNo) {
		return dao.getJmiRecommendRefsByRecommendNo(recommendNo);
	}

	public int getRecommendRefCount(String treeIndex) {
		return dao.getRecommendRefCount(treeIndex);
	}

	public void changeStateByRecommend(final JmiRecommendRef jmiRecommendRef, final String state,final String remark, final String excludeVal) {
	    @SuppressWarnings("unchecked")
        Map<String,Object> resultMap = (Map<String,Object>) jdbcTemplate.execute(   
                new CallableStatementCreator() {   
                   public CallableStatement createCallableStatement(Connection con) throws SQLException {   
                      String storedProc = "{call Proc_Batch_Handle_Authority(?,?,?,?,?,?,?,?)}";// 调用的sql   
                      CallableStatement cs = con.prepareCall(storedProc);   
                      cs.setString(1, "recommend_no");// 设置输入参数的值   
                      cs.setString(2, jmiRecommendRef.getUserCode());
                      cs.setString(3, jmiRecommendRef.getTreeIndex());
                      cs.setString(4, remark);
                      cs.setString(5, state);
                      cs.setString(6, excludeVal);
                      cs.registerOutParameter(7, OracleTypes.NUMBER);// 注册输出参数的类型  
                      cs.registerOutParameter(8, OracleTypes.VARCHAR);
                      return cs;   
                   }   
                }, new CallableStatementCallback() {   
                    public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {   
                      cs.execute();
                      Integer status = cs.getInt(7);
                      String msg = cs.getString(8);
                      Map<String,Object> temp = new HashMap<String, Object>();
                      temp.put("status", status);
                      temp.put("msg", msg);
                      return temp;// 获取输出参数的值   
                }   
             });  
        String status = resultMap.get("status").toString();
        if("0".equals(status)) {
            String errMsg = resultMap.get("msg").toString();
            throw new RuntimeException(errMsg);
        }
	    
//	    List<JmiRecommendRef> list=dao.getRecommendRefByTreeIndex(jmiRecommendRef.getTreeIndex());
//		String[]  excludeArry = StringUtils.split(excludeVal,",");
//		for (JmiRecommendRef ref : list) {
//			JmiMember jmiMember=ref.getJmiMember();
//			String treeIndex = ref.getTreeIndex();
//			
//			if("0".equals(jmiMember.getActive()) && jmiMember.getExitDate()==null ){
//			    boolean flag = false;//跳出双重循环
//                for(String exclude:excludeArry) {
//                    if(treeIndex.startsWith(exclude)) {
//                        //排除
//                        flag=true;
//                    }
//                }
//                if(flag) {
//                    continue;
//                }
//                
//				String curRemark=jmiMember.getRemark();
//				if(curRemark==null){
//					curRemark="";
//				}
//				jmiMember.setRemark(curRemark+" "+remark);
//				jmiMember.getSysUser().setState(state);
//				jmiMemberDao.saveJmiMember(jmiMember);
//			}
//			
//		}
//		//保存授权或停权操作日志
//        JmiRefStateLog jmiRefStateLog = new JmiRefStateLog();
//        jmiRefStateLog.setNetType("recommend_no");
//        jmiRefStateLog.setOperateType(state);
//        jmiRefStateLog.setUserCode(jmiRecommendRef.getUserCode());
//        jmiRefStateLog.setCreateTime(new Date());
//        jmiRefStateLogDao.saveObject(jmiRefStateLog);
	}

	public Map<String, String> getLeaderMembers() {
		String leaderStr=(String) Constants.sysConfigMap.get("AA").get("bd.leader.member");
		String []leaders=leaderStr.split(",");
		String userCodes="";
		for (int i = 0; i < leaders.length; i++) {
			if(i>0){
				userCodes+=",";
			}
			userCodes+="'"+leaders[i]+"'";
		}
		List<JmiRecommendRef> list=dao.getRecommendRefByUserCodes(userCodes);
		Map<String, String> map=new HashMap<String, String>();
		for (JmiRecommendRef ref : list) {
			map.put(ref.getUserCode(), ref.getTreeIndex());
		}
		return map;
	}

	public List getJmiRecommendRefOrder(String treeIndex) {
		return dao.getJmiRecommendRefOrder(treeIndex);
	}

	public List getMembersByUserCode(String userCode) {
		return dao.getMembersByUserCode(userCode);
	}

}
