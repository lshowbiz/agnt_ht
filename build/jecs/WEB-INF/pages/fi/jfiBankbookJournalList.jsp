<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiBankbookJournalList.title" />
</title>
<content tag="heading">
<jecs:locale key="jfiBankbookJournalList.heading" />
</content>
<meta name="menu" content="JfiBankbookJournalMenu" />

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<form action="" method="get" name="jfiBankbookJournalSearchForm"
	id="jfiBankbookJournalSearchForm">
	<div class="searchBar">
		<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			<div class="new_searchBar">
				<jecs:title key="label.member.or.agent.code" />
				<input name="sysUser.userCode" type="text"
					value="${param['sysUser.userCode'] }" />
			</div>
		</c:if>
		<div class="new_searchBar">
			<jecs:title key="comm.busi.deal.transaction.date" />
			<input name="startDealDate" id="startDealDate" type="text" 
					value="${param.startDealDate }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			-<input name="endDealDate" id="endDealDate" type="text" 
					value="${param.endDealDate }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			<jecs:title key="fibankbook.dataType" />
			<jecs:list listCode="fibankbook.datatype" name="dataType"
				value="${param.dataType}" defaultValue="0" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
			<button name="exportExcel" class="btn btn_ins" type="submit" value="exportExcel">
				<jecs:locale key="button.report"/>
			</button>
		</div>
	</div>
</form>

<form action="jfiBankbookJournals.html" method="get"
	name="jfiBankbookJournalForm" id="jfiBankbookJournalForm">
	<ec:table tableId="jfiBankbookJournalListTable"
		items="jfiBankbookJournalList" var="jfiBankbookJournal"
		autoIncludeParameters="true"
		action="${pageContext.request.contextPath}/jfiBankbookJournals.html"
		width="100%" form="jfiBankbookJournalForm"
		retrieveRowsCallback="limit" rowsDisplayed="20" sortable="true"
		imagePath="./images/extremetable/*.gif">

		
		<ec:row>
			<ec:column property="journalId" title="title.view" style="width:5px;"
				viewsAllowed="html">
				<a href="#"
					onclick="viewJfiBankbookJournal('${jfiBankbookJournal.journalId}')"><img
						src="images/newIcons/search_16.gif" border="0" width="16"
						height="16" />
				</a>
			</ec:column>

			<ec:column property="sysUser.userCode"
				title="label.member.or.agent.code">
				${jfiBankbookJournal.sysUser.userCode }
			</ec:column>

			<ec:column property="dealDate"
				title="comm.busi.deal.transaction.date" style="white-space:nowrap;">
				<fmt:formatDate value="${jfiBankbookJournal.dealDate }"
					pattern="yyyy-MM-dd" />
			</ec:column>
			<ec:column property="notes" title="bdBounsDeduct.summary"
				escapeAutoFormat="true" sortable="false">
			<jecs:substr key="${fn:replace(jfiBankbookJournal.notes, vEnter, '<br>')}" length="15"/>
		</ec:column>

			<ec:column property="description" title="bdBounsDeduct.description"
				escapeAutoFormat="true" sortable="false">
			<jecs:substr key="${fn:replace(jfiBankbookJournal.description, vEnter, '<br>')}" length="15"/>
		</ec:column>


			<ec:column property="addmoney" title="fibankbooktemp.dealtype.A"
				styleClass="numberColumn" sortable="false"
				cell="com.joymain.jecs.util.ectable.EcTableCell">
				<c:if test="${not empty jfiBankbookJournal.addMoney}">
					<fmt:formatNumber value="${jfiBankbookJournal.addMoney}"
						type="number" pattern="###,###,##0.00" />
				</c:if>
				<c:if test="${empty jfiBankbookJournal.addMoney}">&nbsp;</c:if>
			</ec:column>
			<ec:column property="decmoney" title="fibankbooktemp.dealtype.D"
				styleClass="numberColumn" sortable="false"
				cell="com.joymain.jecs.util.ectable.EcTableCell">
				<c:if test="${not empty jfiBankbookJournal.decMoney}">
					<fmt:formatNumber value="${jfiBankbookJournal.decMoney}"
						type="number" pattern="###,###,##0.00" />
				</c:if>
				<c:if test="${empty jfiBankbookJournal.decMoney}">&nbsp;</c:if>
			</ec:column>
			<ec:column property="balance" title="fiBankbookJournal.balance"
				styleClass="numberColumn"
				cell="com.joymain.jecs.util.ectable.EcTableCell">
				<c:if test="${empty jfiBankbookJournal.balance}">0.00</c:if>
				<c:if test="${not empty jfiBankbookJournal.balance}">
					<fmt:formatNumber value="${jfiBankbookJournal.balance}"
						type="number" pattern="###,###,##0.00" />
				</c:if>
			</ec:column>
			<c:if test="${sessionLogin.userType=='C' }">
				<ec:column property="createrName" title="title.operator"
					style="white-space:nowrap;" />
			</c:if>
			<ec:column property="dataType" title="fibankbook.dataType"
				style="white-space:nowrap;"
				cell="com.joymain.jecs.util.ectable.EcTableCell">
				<jecs:code listCode="fibankbook.datatype"
					value="${jfiBankbookJournal.dataType}" />
			</ec:column>
		</ec:row>
	</ec:table>
</form>

<script type="text/javascript">

   function editJfiBankbookJournal(journalId){
   		<jecs:power powerCode="editJfiBankbookJournal">
					window.location="editJfiBankbookJournal.html?strAction=editJfiBankbookJournal&journalId="+journalId;
			</jecs:power>
		}

function viewJfiBankbookJournal(journalId){
	<jecs:power powerCode="viewFiBankbookJournal">
		window.location="jfiViewBankbookJournal.html?strAction=viewFiBankbookJournal&journalId="+journalId;
	</jecs:power>
}
</script>
