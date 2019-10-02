<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiPayBankList.title" />
</title>
<content tag="heading">
<jecs:locale key="jfiPayBankList.heading" />
</content>
<meta name="menu" content="FiPayBankMenu" />

<form action="jfiPayBanks.html" method="get" name="jfiPayBankSearchForm"
	id="jfiPayBankSearchForm">
	<div class="searchBar">
		<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
			<div class="new_searchBar">
				<jecs:title key="bdReconsumMoneyReport.companyCH" />
				<jecs:company selected="${param.companyCode }" name="companyCode"
					prompt="" withAA='false' />
			</div>
		</c:if>
		<div class="new_searchBar">
			<jecs:title key="bdOutwardBank.bankCode" />
			<input name="accountCode" type="text" value="${param.accountCode }" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="bdOutwardBank.accountName" />
			<input name="accountName" type="text" value="${param.accountName }" />
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
			<jecs:power powerCode="addFiPayBank">
				<button name="search" class="btn btn_ins" type="button" onclick="location.href='<c:url value="/editJfiPayBank.html"/>?strAction=addFiPayBank'">
					<jecs:locale key="member.new.num"/>
				</button>
			</jecs:power>
		</div>
	</div>
</form>

<form action="jfiPayBanks.html" method="get" name="jfiPayBankForm"
	id="jfiPayBankForm">
	<ec:table tableId="jfiPayBankListTable" items="jfiPayBankList"
		var="jfiPayBank"
		action="${pageContext.request.contextPath}/jfiPayBanks.html"
		autoIncludeParameters="true" retrieveRowsCallback="limit" width="100%"
		form="jfiPayBankForm" rowsDisplayed="20" sortable="true"
		imagePath="./images/extremetable/*.gif">
		<ec:row>
			<ec:column property="companyCode" title="fiPayBank.companyCode" />
			<ec:column property="accountCode" title="bdOutwardBank.bankCode" />
			<ec:column property="bankName"
				title="bdSendRemittanceReport.openBankCH" />
			<ec:column property="bankCity"
				title="bdSendRemittanceReport.openCityCH" />
			<ec:column property="accountName" title="bdOutwardBank.accountName" />
			<ec:column property="accountNo" title="fiPayBank.accountNo" />
			<ec:column property="serialNo" title="title.index" />
			<ec:column property="1" title="title.operation" sortable="false"
				width="100">
				<jecs:power powerCode="editFiBankbookTemp">
					<a
						href="editJfiPayBank.html?accountCode=${jfiPayBank.accountCode}&strAction=editFiPayBank">
						<img src="images/newIcons/pencil_16.gif" border="0" width="16"
							height="16" />
					</a>
				</jecs:power>
			&nbsp;
		</ec:column>
		</ec:row>

	</ec:table>

</form>

<script type="text/javascript">

function editFiPayBank(accountCode){
	<jecs:power powerCode="editFiPayBank">
	window.location="editFiPayBank.html?accountCode="+accountCode+"&strAction=editFiPayBank";
	</jecs:power>
}

</script>