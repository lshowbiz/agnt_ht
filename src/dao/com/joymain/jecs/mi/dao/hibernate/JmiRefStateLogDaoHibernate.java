
package com.joymain.jecs.mi.dao.hibernate;

import java.util.List;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.dao.JmiRefStateLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JmiRefStateLogDaoHibernate extends BaseDaoHibernate implements JmiRefStateLogDao {

    @Override
    public List getJmiRefStateLogsByCrm(CommonRecord crm, Pager pager)
    {
        StringBuffer  sql = new StringBuffer("SELECT A.ID,A.USER_CODE,A.NET_TYPE,A.OPERATE_TYPE,U.STATE,A.CREATE_TIME, B.TREE_INDEX FROM JMI_REF_STATE_LOG A, ");
        String netType = crm.getString("netType", "");
        if("link_no".equals(netType)) {
            sql.append("JMI_LINK_REF B");
        } else if("recommend_no".equals(netType)) {
            sql.append("JMI_RECOMMEND_REF B");
        }
        sql.append(",JSYS_USER U WHERE A.USER_CODE=B.USER_CODE AND A.USER_CODE=U.USER_CODE AND A.NET_TYPE='"+netType+"'");
        String treeIndex = crm.getString("treeIndex", "");
        sql.append(" AND B.TREE_INDEX LIKE '"+treeIndex+"%' AND B.TREE_INDEX<>'"+treeIndex+"'");
        sql.append(" AND A.ID=(SELECT MAX(J.ID) AS ID FROM JMI_REF_STATE_LOG J WHERE J.USER_CODE=A.USER_CODE GROUP BY J.USER_CODE)");
        return this.findObjectsBySQL(sql.toString(), pager);
    }

}
