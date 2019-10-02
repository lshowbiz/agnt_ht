<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiBillAccountList.title" />
</title>
<content tag="heading">
<jecs:locale key="fiBillAccountList.heading" />
</content>
<meta name="menu" content="FiBillAccountMenu" />


<form action="fiBillAccounts.html" method="get"
	name="fiBillAccountSearchForm" id="fiBillAccountSearchForm">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="fiBillAccount.AccountCode"/>
			<input name="billAccountCode" type="text"
				value="${param['billAccountCode'] }" size="14" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="jfiSunDistribution.agentCode"/>
			<input name="userCode" type="text" value="${param['userCode'] }"
				size="14" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiBillAccount.accountName" />
			<input name="accountName" type="text" value="${param['accountName'] }"
				size="14" />
		</div>
		<div class="new_searchBar">
	
	
			<!--适用类型：-->
			<!--<select id="accountType" name="accountType">-->
			<!--	<option></option>-->
			<!--	<option value="1" <c:if test='${param["accountType"] == 1}'>selected="selected"</c:if> >PC</option>-->
			<!--	<option value="2" <c:if test='${param["accountType"] == 2}'>selected="selected"</c:if> >移动终端</option>-->
			<!--</select>-->
	
			<jecs:title key="fiBillAccount.platform.type"></jecs:title>
			<jecs:list name="providerType" listCode="paycompany"
				value="${param.providerType}" defaultValue="" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
			<jecs:power powerCode="addFiBillAccount">
				<button name="search" class="btn btn_ins" type="button" onclick="location.href='<c:url value="/editFiBillAccount.html"/>?strAction=addFiBillAccount'">
					<jecs:locale key="button.add"/>
				</button>
			</jecs:power>
			<button name="search" class="btn btn_ins" type="button" onclick="location.href='<c:url value="/fiBillAccountImport.html"/>'">
				导入
			</button>
		</div>

	</div>
</form>

<ec:table tableId="fiBillAccountListTable" items="fiBillAccountList"
	var="fiBillAccount"
	action="${pageContext.request.contextPath}/fiBillAccounts.html"
	width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
	sortable="true" imagePath="./images/extremetable/*.gif">
	<ec:row>
		<ec:column property="billAccountCode" title="商户号" />
		<ec:column property="userCode" title="经销商编号" />
		<ec:column property="accountName" title="注册名称" />

		<ec:column property="status" title="状态">
			<c:if test="${fiBillAccount.status==0}">启用</c:if>
			<c:if test="${fiBillAccount.status==1}">禁用</c:if>
		</ec:column>


		<ec:column property="accountType" title="适用类型">
			<c:if test="${fiBillAccount.accountType==1}">PC</c:if>
			<c:if test="${fiBillAccount.accountType==2}">移动终端</c:if>
		</ec:column>
		<ec:column property="providerType" title="平台类型">
			<jecs:code listCode="paycompany"
				value="${fiBillAccount.providerType}" />
		</ec:column>
		<ec:column property="maxMoney" title="最大限额" />

		<ec:column property="edit" title="title.operation" sortable="false"
			viewsAllowed="html">

			<c:if test="${fiBillAccount.status==0}">
				<img src="<c:url value='/images/icons/w.gif'/>"
					onclick="javascript:stopFiBillAccount('${fiBillAccount.accountId}')"
					style="cursor: pointer;" title="禁用" />
			</c:if>
			<c:if test="${fiBillAccount.status==1}">
				<img src="<c:url value='/images/icons/r.gif'/>"
					onclick="javascript:startFiBillAccount('${fiBillAccount.accountId}')"
					style="cursor: pointer;" title="启用" />
			</c:if>	
					&nbsp;&nbsp;&nbsp;
					<jecs:power powerCode="editFiBillAccount">
				<img src="<c:url value='/images/icons/editIcon.gif'/>"
					onclick="javascript:editFiBillAccount('${fiBillAccount.accountId}')"
					style="cursor: pointer;" title="<jecs:locale key="button.edit"/>" />
			</jecs:power>

		</ec:column>
	</ec:row>
</ec:table>

<script type="text/javascript">

function editFiBillAccount(accountId){
  		<jecs:power powerCode="editFiBillAccount">
				window.location="editFiBillAccount.html?strAction=editFiBillAccount&accountId="+accountId;
		</jecs:power>
}

function startFiBillAccount(accountId){

	if(window.confirm("您确定要启用该商户号?")){
  	
  		window.location="fiBillAccounts.html?flag=0&accountId="+accountId;
  	}
}
function stopFiBillAccount(accountId){

	if(window.confirm("您确定要禁用该商户号?")){

  		window.location="fiBillAccounts.html?flag=1&accountId="+accountId;
  	}
}
</script>
