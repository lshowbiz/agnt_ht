<%@ page language="java" errorPage="/error.jsp"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiDepositMoneyList.title" />
</title>
<content tag="heading">
<jecs:locale key="jfiDepositMoneyList.heading" />
</content>
<meta name="menu" content="JfiDepositMoneyMenu" />


<form action="" method="get" name="miMemberForm" id="miMemberForm">
	<input type="hidden" id="strAction" name="strAction"
		value="jmiMemberFreeze" />
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="miMember.memberNo" />
			<input name="userCode" type="text" value="${param.userCode}" size="10" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="bdBounsDeduct.wweek"></jecs:title>
			<input name="wweek" type="text" value="${param.wweek }" size="10" />
		</div>
		<div class="new_searchBar">
			<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
	</div>



</form>








<ec:table tableId="jfiDepositMoneyListTable" items="jfiDepositMoneyList"
	var="jfiDepositMoney"
	action="${pageContext.request.contextPath}/jfiDepositMoneys.html"
	width="100%" autoIncludeParameters="true" retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true"
	imagePath="./images/extremetable/*.gif">
	<ec:row>
		<ec:column property="userCode" title="miMember.memberNo" />
		<ec:column property="userName" title="bdCalculatingSubDetail.name" />

		<ec:column property="wweek" title="bdBounsDeduct.wweek">
			<jecs:weekFormat week="${jfiDepositMoney.wweek }" weekType="w" />
		</ec:column>
		<ec:column property="sendMoney" title="奖金金额" />
		<ec:column property="invoicePayable" title="应提交发票金额" />
		<ec:column property="depositMoney" title="保证金金额" />
		<ec:column property="remark" title="备注" />
		<%-- <ec:column property="createNo" title="jfiDepositMoney.createNo" />
    			<ec:column property="createTime" title="jfiDepositMoney.createTime" />
    			<ec:column property="companyCode" title="jfiDepositMoney.companyCode" /> --%>
		<ec:column property="1" title="title.operation" sortable="false">
			<img src="images/newIcons/pencil_16.gif"
				onclick="window.location.href='editJfiDepositMoney.html?id=${jfiDepositMoney.id}&strAction=editJfiDepositMoney';"
				alt="<jecs:locale key="button.edit"/>" style="cursor: pointer" />

		</ec:column>
	</ec:row>

</ec:table>
