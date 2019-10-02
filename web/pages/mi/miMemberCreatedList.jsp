<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdSubDetailList.title"/></title>
<content tag="heading"><jecs:locale key="bdSubDetailList.heading"/></content>
<meta name="menu" content="BdSubDetailMenu"/>



<table class='detail' width="100%">
		<tr>
			<th>
				<jecs:title key="alCompany.regName"/>
			</th>
			<td>
			<jecs:company name="companyCode" selected="${jmiMember.companyCode }" label="true"  withAA="false"  />	
			</td>
		</tr>
		
		<tr>
			<th>
				<jecs:title key="miMember.miRecommendRef"/>
			</th>
			<td>
				${jmiMember.recommendNo}
			</td>
		</tr>
		<%--<tr>
			<th>
				<jecs:title key="bdCalculatingSubDetail.name"/>
			</th>
			<td>
				${jmiMember.miUserName}
			</td>
		</tr>--%>
		<tr>
			<th>
				<jecs:title key="bdSendRecord.cardType"/>
			</th>
			<td>
    			<jecs:code listCode="member.level" value="${jmiMember.memberLevel}"/>
			</td>
		</tr>
			<tr>
			<th>
				<jecs:title key="miMember.memberNo"/>
			</th>
			<td>
				<font size="3" color="red">${jmiMember.userCode }</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<jecs:locale key="operation.notice.MarkMemberNo"/> 
			</td>
		</tr>
			<tr>
			<th>
				<jecs:title key="memberCreate.tips"/>
			</th>
			<td>
				<font size="3" color="red"><jecs:locale key="memberCreate.tips.detail"/> </font>
			</td>
		</tr>

<tr>
	<th class="command"><jecs:title key="sysOperationLog.moduleName" /></th>
	<td class="command">

<input type="button" class="button" name="back"  onclick="window.location.href='jmiMembers.html?strAction=memberSearch'" value="<jecs:locale key="operation.button.return"/>" />

	</td>
</tr>

</table>