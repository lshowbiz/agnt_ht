<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiBillAccountList.title"/></title>
<content tag="heading"><jecs:locale key="fiBillAccountList.heading"/></content>
<meta name="menu" content="FiBillAccountMenu"/>

<form action="fiBillAccountCheck.html" method="get" name="fiBillAccountSearchForm" id="fiBillAccountSearchForm">
<div class="searchBar">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<jecs:label  key="miMember.memberNo"/>	
	<input name="userCode" id="userCode" type="text" value="${param['userCode'] }" size="14"/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>

</div>
</form>
<br>
<b><jecs:locale key="fiBillAccountList.title"/>:</b><br>
<table class='detail' width="100%">
	<tr>
		<td><jecs:locale  key="fiBillAccount.billAccountCode"/></td>
		<td><jecs:locale  key="fiBillAccount.accountName"/></td>
		<td><jecs:locale  key="fiBillAccount.registerEmail"/></td>
		<td><jecs:locale  key="fiBillAccount.status"/></td>
		<td><jecs:locale  key="fiBillAccount.isDefault"/></td>
		<td><jecs:locale  key="fiBillAccount.fiBillAccountRelations"/></td>
	</tr>
	
	<tr>
		<td>${fiBillAccount.billAccountCode }</td>
		<td>${fiBillAccount.accountName }</td>
		<td>${fiBillAccount.registerEmail }</td>
		
		<td>
			<c:if test="${fiBillAccount.status==1}"><jecs:locale  key="fiBillAccount.status.yes"/></c:if>
    		<c:if test="${fiBillAccount.status==0}"><jecs:locale  key="fiBillAccount.status.no"/></c:if>
		</td>
		<td>
			<c:if test="${fiBillAccount.isDefault==1}"><jecs:locale  key="fiBillAccount.isdefault.yes"/></c:if>
    		<c:if test="${fiBillAccount.isDefault==0}"><jecs:locale  key="fiBillAccount.isdefault.no"/></c:if>
		</td>
		<td>
			<jecs:region regionType="p" regionId="${fiBillAccount.province}"></jecs:region>
			<%-- 
			<table class='detail' width="100%">
				<c:forEach items="${fiBillAccount.fiBillAccountRelations}" var="billAccountRelation" varStatus='status'>
					<tr>
						<td>${status.index+1}</td>
						<td>
							<jecs:region regionType="p" regionId="${billAccountRelation.province}"></jecs:region>
							<jecs:region regionType="c" regionId="${billAccountRelation.city}"></jecs:region>
							<jecs:region regionType="d" regionId="${billAccountRelation.district}"></jecs:region>
						</td>
					</tr>
				</c:forEach>
			</table>
			--%>
		</td>
	</tr>
</table>
<br>
<b><jecs:locale key="fiCommonAddr.title"/>:</b><br>
<table class='detail' width="100%">
	<tr>
		<td><jecs:locale  key="miMember.memberNo"/></td>
		<td><jecs:locale  key="shipping.province"/></td>
		<td><jecs:locale  key="shipping.city"/></td>
		<td><jecs:locale  key="shipping.district"/></td>
		<td><jecs:locale  key="shipping.address"/></td>
	</tr>
	
	<tr>
		<td>${fiCommonAddr.userCode }</td>

		<td><jecs:region regionType="p" regionId="${fiCommonAddr.province}"></jecs:region></td>
		<td><jecs:region regionType="c" regionId="${fiCommonAddr.city}"></jecs:region></td>
		<td><jecs:region regionType="d" regionId="${fiCommonAddr.district}"></jecs:region></td>
		<td>${fiCommonAddr.address }</td>
	</tr>
</table>