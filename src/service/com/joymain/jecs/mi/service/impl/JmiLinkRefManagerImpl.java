
package com.joymain.jecs.mi.service.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.driver.OracleTypes;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;

import com.joymain.jecs.mi.dao.JmiLinkRefDao;
import com.joymain.jecs.mi.dao.JmiMemberDao;
import com.joymain.jecs.mi.dao.JmiRefStateLogDao;
import com.joymain.jecs.mi.model.JmiLinkRef;
import com.joymain.jecs.mi.service.JmiLinkRefManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

public class JmiLinkRefManagerImpl extends BaseManager implements JmiLinkRefManager {
    private JmiLinkRefDao dao;
    private JmiRefStateLogDao jmiRefStateLogDao;
    
    private JmiMemberDao jmiMemberDao;
    
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
    public void setJmiLinkRefDao(JmiLinkRefDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiLinkRefManager#getJmiLinkRefs(com.joymain.jecs.mi.model.JmiLinkRef)
     */
    public List getJmiLinkRefs(final JmiLinkRef jmiLinkRef) {
        return dao.getJmiLinkRefs(jmiLinkRef);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiLinkRefManager#getJmiLinkRef(String memberNo)
     */
    public JmiLinkRef getJmiLinkRef(final String memberNo) {
        return dao.getJmiLinkRef(new String(memberNo));
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiLinkRefManager#saveJmiLinkRef(JmiLinkRef jmiLinkRef)
     */
    public void saveJmiLinkRef(JmiLinkRef jmiLinkRef) {
        dao.saveJmiLinkRef(jmiLinkRef);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiLinkRefManager#removeJmiLinkRef(String memberNo)
     */
    public void removeJmiLinkRef(final String memberNo) {
        dao.removeJmiLinkRef(new String(memberNo));
    }
    //added for getJmiLinkRefsByCrm
    public List getJmiLinkRefsByCrm(CommonRecord crm, Pager pager){
	return dao.getJmiLinkRefsByCrm(crm,pager);
    }

	public List getMiLinkRefManagersByTree(CommonRecord crm) {
		return dao.getMiLinkRefManagersByTree(crm);
	}

	public List getLinkRefbyLinkNoOrderByCreateTime(String linkNo) {
		return dao.getLinkRefbyLinkNoOrderByCreateTime(linkNo);
	}

	public List getLinkRefbyLinkNo(String linkNo) {
		return dao.getLinkRefbyLinkNo(linkNo);
	}

	public String getCallProcChangeLink(String memberNo, String linkNo, String userCode, String noCheck) {
		return dao.getCallProcChangeLink(memberNo, linkNo, userCode, noCheck);
	}

	public List getLinkRefsbyLinkNo(String linkNo) {
		return dao.getLinkRefsbyLinkNo(linkNo);
	}

	public boolean getJmiLinkRefIsM(String userCode) {
		//判断是否在CN18303490这个会员下
		JmiLinkRef topMiLinkRef = dao.getJmiLinkRef("CN18303490");
		JmiLinkRef curMiLinkRef = dao.getJmiLinkRef(userCode);
		String topIndex=topMiLinkRef.getTreeIndex();
		String curIndex=curMiLinkRef.getTreeIndex();
		String miLinkRefIndexTmp = StringUtil.getLeft(curIndex, topIndex.length());
		//
		if (curIndex.length()<topIndex.length()||!topIndex.equals(miLinkRefIndexTmp)){
			return false;
		}
		return true;
	}

	public List getLinkRefbyLinkNo(String linkNo, String sortField) {
		return dao.getLinkRefbyLinkNo(linkNo, sortField);
	}

	public List getLinkRefbyLinkNoByWeekGroupPv(String linkNo,String curWeek) {
		return dao.getLinkRefbyLinkNoByWeekGroupPv(linkNo,  curWeek);
	}

	public int getLinkRefCount(String treeIndex) {
		return dao.getLinkRefCount(treeIndex);
	}
	/**
	 * 
	 * @param jmiLinkRef
	 * @param state
	 * @param remark
	 * @param excludeVal需要排除授权的会员
	 */
	public void changeStateByLink(final JmiLinkRef jmiLinkRef,final String state,final String remark, final String excludeVal) {

	    @SuppressWarnings("unchecked")
        Map<String,Object> resultMap = (Map<String,Object>) jdbcTemplate.execute(   
                new CallableStatementCreator() {   
                   public CallableStatement createCallableStatement(Connection con) throws SQLException {   
                      String storedProc = "{call Proc_Batch_Handle_Authority(?,?,?,?,?,?,?,?)}";// 调用的sql   
                      CallableStatement cs = con.prepareCall(storedProc);   
                      cs.setString(1, "link_no");// 设置输入参数的值   
                      cs.setString(2, jmiLinkRef.getUserCode());
                      cs.setString(3, jmiLinkRef.getTreeIndex());
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
	    
	    //		List<JmiLinkRef> list=dao.getLinkRefByTreeIndex(jmiLinkRef.getTreeIndex());
//		String[]  excludeArry = StringUtils.split(excludeVal,",");
//		for (JmiLinkRef ref : list) {
//			JmiMember jmiMember=ref.getJmiMember();
//			String treeIndex = ref.getTreeIndex();
//			
//			if("0".equals(jmiMember.getActive()) && jmiMember.getExitDate()==null ){
//			    boolean flag = false;//跳出双重循环
//			    for(String exclude:excludeArry) {
//			        if(treeIndex.startsWith(exclude)) {
//			            //排除
//			            flag=true;
//			        }
//			    }
//			    if(flag) continue;
//			    
//				String curRemark=jmiMember.getRemark();
//				if(curRemark==null){
//					curRemark="";
//				}
//				jmiMember.setRemark(curRemark+" "+remark);
//				jmiMember.getSysUser().setState(state);
//				jmiMemberDao.saveJmiMember(jmiMember);
//			}
//		}
//		//保存授权或停权操作日志
//		JmiRefStateLog jmiRefStateLog = new JmiRefStateLog();
//		jmiRefStateLog.setNetType("link_no");
//		jmiRefStateLog.setOperateType(state);
//		jmiRefStateLog.setUserCode(jmiLinkRef.getUserCode());
//		jmiRefStateLog.setCreateTime(new Date());
//		jmiRefStateLogDao.saveObject(jmiRefStateLog);
	}
}
