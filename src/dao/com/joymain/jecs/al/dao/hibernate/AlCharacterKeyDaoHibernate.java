package com.joymain.jecs.al.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.al.dao.AlCharacterKeyDao;
import com.joymain.jecs.al.model.AlCharacterKey;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class AlCharacterKeyDaoHibernate extends BaseDaoHibernate implements AlCharacterKeyDao {

	/**
	 * @see com.joymain.jecs.al.dao.AlCharacterKeyDao#getAlCharacterKeys(com.joymain.jecs.al.model.AlCharacterKey)
	 */
	public List getAlCharacterKeys(final AlCharacterKey alCharacterKey) {
		return getHibernateTemplate().find("from AlCharacterKey order by keyId desc");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (alCharacterKey == null) {
		 * return getHibernateTemplate().find("from AlCharacterKey"); } else { //
		 * filter on properties set in the alCharacterKey HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex =
		 * Example.create(alCharacterKey).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return session.createCriteria(AlCharacterKey.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.joymain.jecs.al.dao.AlCharacterKeyDao#getAlCharacterKey(Long keyId)
	 */
	public AlCharacterKey getAlCharacterKey(final Long keyId) {
		AlCharacterKey alCharacterKey = (AlCharacterKey) getHibernateTemplate().get(AlCharacterKey.class, keyId);
		if (alCharacterKey == null) {
			log.warn("uh oh, alCharacterKey with keyId '" + keyId + "' not found...");
			throw new ObjectRetrievalFailureException(AlCharacterKey.class, keyId);
		}

		return alCharacterKey;
	}

	/**
	 * @see com.joymain.jecs.al.dao.AlCharacterKeyDao#saveAlCharacterKey(AlCharacterKey alCharacterKey)
	 */
	public void saveAlCharacterKey(final AlCharacterKey alCharacterKey) {
		getHibernateTemplate().saveOrUpdate(alCharacterKey);
	}

	/**
	 * @see com.joymain.jecs.al.dao.AlCharacterKeyDao#removeAlCharacterKey(Long keyId)
	 */
	public void removeAlCharacterKey(final Long keyId) {
		getHibernateTemplate().delete(getAlCharacterKey(keyId));
	}

	/**
	 * 根据外部参数分页获取对应的键值列表
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getAlCharacterKeysByPage(CommonRecord crm, Pager pager) {
		String hqlQuery = "from AlCharacterKey where 1=1";
		
		String characterKey = crm.getString("characterKey", "");
		if (!StringUtils.isEmpty(characterKey)) {
			hqlQuery += " and characterKey like '%" + characterKey + "%'";
		}
		
		String keyDesc = crm.getString("keyDesc", "");
		if (!StringUtils.isEmpty(keyDesc)) {
			hqlQuery += " and keyDesc like '%" + keyDesc + "%'";
		}
		
		String typeId = crm.getString("typeId", "");
		if (!StringUtils.isEmpty(typeId) && !"0".equals(typeId)) {
			if("unTyped".equalsIgnoreCase(typeId)){
				hqlQuery += " and alCharacterType.typeId is null";
			}else{
				hqlQuery += " and alCharacterType.typeId='" + typeId + "'";
			}
		}
		
		if(pager==null){
			return this.getHibernateTemplate().find(hqlQuery);
		}
		
		// 设置排序
		if (!pager.getLimit().getSort().isSorted()) {
			//缺省排序
			hqlQuery += " order by keyDesc asc";
		} else {
			hqlQuery += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		//

		List alCharacterKeys = this.findObjectsByHqlQuery(hqlQuery, pager);

		return alCharacterKeys;
	}
	
	/**
	 * 根据编码键值获取对应的字符键
	 * @param characterKey
	 * @return
	 */
	public AlCharacterKey getAlCharacterKeyByKey(final String characterKey){
		return (AlCharacterKey)this.getObjectByHqlQuery("from AlCharacterKey where characterKey='"+characterKey+"'");
	}
	
	/**
	 * 批量保存多个字符键
	 * @param alCharacterKeys
	 */
	public void saveAlCharacterKeys(List alCharacterKeys){
		this.getHibernateTemplate().saveOrUpdateAll(alCharacterKeys);
	}
}
