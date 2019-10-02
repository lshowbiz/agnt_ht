
package com.joymain.jecs.al.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.al.dao.AlCharacterValueDao;
import com.joymain.jecs.al.model.AlCharacterValue;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class AlCharacterValueDaoHibernate extends BaseDaoHibernate implements AlCharacterValueDao {

    /**
     * @see com.joymain.jecs.al.dao.AlCharacterValueDao#getAlCharacterValues(com.joymain.jecs.al.model.AlCharacterValue)
     */
    public List getAlCharacterValues(final AlCharacterValue alCharacterValue) {
        return getHibernateTemplate().find("from AlCharacterValue");
    }

    /**
     * @see com.joymain.jecs.al.dao.AlCharacterValueDao#getAlCharacterValue(Long valueId)
     */
    public AlCharacterValue getAlCharacterValue(final Long valueId) {
        AlCharacterValue alCharacterValue = (AlCharacterValue) getHibernateTemplate().get(AlCharacterValue.class, valueId);
        if (alCharacterValue == null) {
            log.warn("uh oh, alCharacterValue with valueId '" + valueId + "' not found...");
            throw new ObjectRetrievalFailureException(AlCharacterValue.class, valueId);
        }

        return alCharacterValue;
    }

    /**
     * @see com.joymain.jecs.al.dao.AlCharacterValueDao#saveAlCharacterValue(AlCharacterValue alCharacterValue)
     */    
    public void saveAlCharacterValue(final AlCharacterValue alCharacterValue) {
        getHibernateTemplate().saveOrUpdate(alCharacterValue);
    }

    /**
     * @see com.joymain.jecs.al.dao.AlCharacterValueDao#removeAlCharacterValue(Long valueId)
     */
    public void removeAlCharacterValue(final Long valueId) {
        getHibernateTemplate().delete(getAlCharacterValue(valueId));
    }
    
    /**
     * 根据键值ID及字符编码获取对应的语言值
     * @param keyId
     * @param characterCode
     * @return
     */
    public AlCharacterValue getAlCharacterValue(final Long keyId, final String characterCode){
    	return (AlCharacterValue)this.getObjectByHqlQuery("from AlCharacterValue where alCharacterKey.keyId='"+keyId+"' and characterCode='"+characterCode+"'");
    }
    
    /**
     * 根据键值ID及字符编码获取对应的语言值
     * @param characterKey
     * @param characterCode
     * @return AlCharacterValue
     */
    public AlCharacterValue getAlCharacterValue(final String characterKey, final String characterCode){
    	return (AlCharacterValue)this.getObjectByHqlQuery("from AlCharacterValue where alCharacterKey.characterKey='"+characterKey+"' and characterCode='"+characterCode+"'");
    }
    
    /**
     * 根据字符编码获取对应的语言集
     * @param characterCode
     * @return List
     */
    public List getAlCharacterValuesByCode(final String characterCode){
    	return this.getHibernateTemplate().find("from AlCharacterValue where characterCode=?", characterCode);
    }
    
    /**
     * 根据字符编码获取对应的语言集(SQL),用于初始化Listener
     * @param characterCode
     * @return
     */
    public List getAlCharacterValuesByCodeSQL(final String characterCode){
    	return this.findObjectsBySQL("select a.character_key, b.character_value from jal_character_key a, jal_character_value b where a.key_id=b.key_id and b.character_code='"+characterCode+"'");
    }
    
    /**
     * 根据键值ID获取对应的语言值,对应所有字符编码
     * @param keyId
     * @param userCode
     * @return
     */
    public List getAlCharacterValuesAll(final Long keyId, final String userCode){
    	String sqlQuery="select a.character_code, a.character_name, a.allowed_user,  b.VALUE_ID, b.CHARACTER_VALUE " +
			" from jal_character_coding a " +
			" left join jal_character_value b on (b.character_code=a.character_code and b.key_id='"+keyId+"')";
    	/*if(!StringUtils.isEmpty(userCode) && !Constants.ROOT_ACCOUNT.equalsIgnoreCase(userCode)){
    		sqlQuery+=" where a.allowed_user like '%"+userCode+"%'";
    	}*/
    	sqlQuery+=" order by a.character_code";
    	return this.findObjectsBySQL(sqlQuery);
    }
    
    /**
     * 批量保存语言值
     * @param alCharacterValues
     */
    public void saveAlCharacterValues(List alCharacterValues){
    	this.getHibernateTemplate().saveOrUpdateAll(alCharacterValues);
    }
    
    /**
     * 删除某字符集下所有的翻译
     * @param characterCode
     */
    public void removeAlCharacterValues(final String characterCode){
    	this.executeUpdate("delete from AlCharacterValue where characterCode='"+characterCode+"'");
    }
    public List getAlCharacterValuesByTypeSQL(CommonRecord crm, Pager pager) {
		String sql=this.toSql(crm, pager);
		return this.findObjectsBySQL(sql, pager);
	}
	private String toSql(CommonRecord crm, Pager pager){
			String sql=" select a.key_id,a.character_key,a.key_desc,zhong.character_value as zhong";
//			"zh_CN.Value_Id as zh_cn_value_id,zh_CN.character_value as zh_CN,"+
//			 " en_US.Value_Id as en_us_value_id,en_US.character_value as en_us";
			//参考语言
			String referral = crm.getString("referral", "");
			if (!StringUtils.isEmpty(referral)) {
				sql +=","+referral+".Value_Id as "+referral+"_value_id,"+referral+".character_value as "+referral;
			}
			
			//编辑语言
			String menuLan = crm.getString("menuLan", "");
			if (!StringUtils.isEmpty(menuLan)) {
				sql +=","+menuLan+".Value_Id as "+menuLan+"_value_id,"+menuLan+".character_value as "+menuLan;
			}
			
					 
			sql+= " from jal_character_key a";
//			sql+=" left join al_character_value zh_CN on a.key_id=zh_CN.key_id and zh_CN.character_code='zh_CN' "+
//			" left join al_character_value en_US on a.key_id=en_US.key_id and en_US.character_code='en_US' ";
			sql+=" left join jal_character_value zhong on a.key_id = zhong.key_id and zhong.character_code = 'zh_CN'  ";
//			参考语言 
			if (!StringUtils.isEmpty(referral)) {
				sql +=  " left join jal_character_value "+referral+" on a.key_id="+referral+".key_id and "+referral+".character_code='"+referral+"' ";
			}		
//			编辑语言
			if (!StringUtils.isEmpty(menuLan)) {
				sql +=  " left join jal_character_value "+menuLan+" on a.key_id="+menuLan+".key_id and "+menuLan+".character_code='"+menuLan+"' ";
			} 
					 
					sql+= " where 1=1 ";
			
			String typeId=crm.getString("typeId", "");
			if (!StringUtils.isEmpty(typeId) && !"0".equals(typeId)) {
				if("unTyped".equalsIgnoreCase(typeId)){
					sql += " and a.type_id is null";
				}else{
					sql += " and a.type_id="+typeId;
				}
			}
			
			String keyDesc=crm.getString("keyDesc", "");
			if (!StringUtils.isEmpty(keyDesc)) {
				sql += " and a.key_desc like '%"+keyDesc+"%'";
			}
			String characterKey=crm.getString("characterKey", "");
			if (!StringUtils.isEmpty(characterKey)) {
				sql += " and a.character_key like '%"+characterKey+"%'";
			}
			
			String characterValue=crm.getString("characterValue", "");
			if (!StringUtils.isEmpty(characterValue)) {
				sql += " and "+referral+".character_value like '%"+characterValue+"%'";
			}
			
			String characterValueEdit=crm.getString("characterValueEdit", "");
			if (!StringUtils.isEmpty(characterValueEdit)) {
				sql += " and "+menuLan+".character_value like '%"+characterValueEdit+"%'";
			}
			return sql;
	}
}