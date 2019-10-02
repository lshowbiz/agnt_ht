<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiSecurityDepositJournalList.title" />
</title>
<content tag="heading">
<jecs:locale key="fiSecurityDepositJournalList.heading" />
</content>
<meta name="menu" content="FiSecurityDepositJournalMenu" />




<form action="fiSecurityDepositJournals.html" method="get"
	name="fiSecurityDepositsJournalsSearchForm"
	id="fiSecurityDepositsJournalsSearchForm">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="label.member.or.agent.code" />
			<input name="userCode" type="text" value="${param['userCode'] }"
				size="14" />
		</div>
		<div class="new_searchBar">
			<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
	</div>
</form>
<ec:table tableId="fiSecurityDepositJournalListTable"
	items="fiSecurityDepositJournalList" var="fiSecurityDepositJournal"
	action="${pageContext.request.contextPath}/fiSecurityDepositJournals.html"
	width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
	sortable="true" imagePath="./images/extremetable/*.gif">
	<ec:row>

		<ec:column property="userCode" title="label.member.or.agent.code" />
		<ec:column property="createTime"
			title="comm.busi.deal.transaction.date" />

		<ec:column property="addMoney" title="fibankbooktemp.dealtype.A"
			styleClass="numberColumn">
			<c:if test="${fiSecurityDepositJournal.dealType=='A'}">${fiSecurityDepositJournal.amount}</c:if>
		</ec:column>
		<ec:column property="decMoney" title="fibankbooktemp.dealtype.D"
			styleClass="numberColumn">
			<c:if test="${fiSecurityDepositJournal.dealType=='D'}">${fiSecurityDepositJournal.amount}</c:if>
		</ec:column>

		<ec:column property="notes" title="bdBounsDeduct.summary" />
		<ec:column property="balance" title="fiSecurityDeposit.balance" />

		<ec:column property="createrName" title="title.operator" />


	</ec:row>

</ec:table>

<script type="text/javascript">

   function editFiSecurityDepositJournal(journalId){
   		<jecs:power powerCode="editFiSecurityDepositJournal">
					window.location="editFiSecurityDepositJournal.html?strAction=editFiSecurityDepositJournal&journalId="+journalId;
			</jecs:power>
		}

</script>
