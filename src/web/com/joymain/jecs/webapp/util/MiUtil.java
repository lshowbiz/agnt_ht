package com.joymain.jecs.webapp.util;

import java.util.Date;

import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.ContextUtil;

public class MiUtil {
	public static void setCardType(JmiMember jmiMember){
		Date createTime=jmiMember.getCreateTime();
		if(createTime.getTime()>=1427817600696L){
			if(jmiMember.getNotFirst()==1){
				jmiMember.setCardType("已审核");
			}else{
				jmiMember.setCardType("未审核");
			}
		}else{
			String characterCoding=((SysUser) ContextUtil.getResource("sessionLogin")).getDefCharacterCoding();
			jmiMember.setCardType(LocaleUtil.getLocalText(ListUtil.getListValue(characterCoding, "bd.cardtype", jmiMember.getCardType())));
		}
	}
}
