<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiBankbookJournalList.title" />
</title>
<content tag="heading">
<jecs:locale key="fiBankbookJournalList.heading" />
</content>
<meta name="menu" content="FiBankbookJournalMenu" />

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>


<c:set var="footTotalVar">
	<tr>
		<td align="right" class="footer" colspan="5">
			<jecs:locale key="report.allTotal" />
		</td>
		<td align="right" class="footer">
			<b> <c:if test="${not empty sumMap.addMoney}">
					<fmt:formatNumber pattern="###,##0.00">${sumMap.addMoney}</fmt:formatNumber>
				</c:if> <c:if test="${empty sumMap.addMoney}">
				0.00
			</c:if> </b>
		</td>
		<td align="right" class="footer">
			<b> <c:if test="${not empty sumMap.decMoney}">
					<fmt:formatNumber pattern="###,##0.00">${sumMap.decMoney}</fmt:formatNumber>
				</c:if> <c:if test="${empty sumMap.decMoney}">
				0.00
			</c:if> </b>
		</td>
		<td align="right" class="footer">
			<b> <c:if test="${not empty sumMap.balance}">
					<fmt:formatNumber pattern="###,##0.00">${sumMap.balance}</fmt:formatNumber>
				</c:if> <c:if test="${empty sumMap.balance}">
				0.00
			</c:if> </b>
		</td>
		<td class="footer">
			&nbsp;
		</td>
		<td class="footer">
			&nbsp;
		</td>
	</tr>
</c:set>

<form action="" method="get" name="fiBankbookJournalSearchForm"
	id="fiBankbookJournalSearchForm">
	<div class="searchBar">
		<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			<div class="new_searchBar">
				<jecs:title key="label.member.or.agent.code" />
				<input name="sysUser.userCode" type="text"
					value="${param['sysUser.userCode'] }" size="14" />
			</div>
		</c:if>
		<div class="new_searchBar">
			<jecs:title key="fiBankbookTemp.bankbookType" />
			<jecs:list listCode="fiproductpointtemp.productpointtype" name="productPointType"
				id="bankbookType" value="${param.productPointType}" defaultValue="1" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="comm.busi.deal.transaction.date" />
			<input name="startDealDate" id="startDealDate" type="text" 
					value="${param.startDealDate }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="endDealDate" id="endDealDate" type="text" 
					value="${param.endDealDate }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
	</div>
</form>

<form action="fiProductPointJournals.html" method="get"
	name="fiBankbookJournalForm" id="fiBankbookJournalForm">
	<ec:table tableId="fiBankbookJournalListTable"
		items="fiProductPointJournalList" var="fiBankbookJournal"
		autoIncludeParameters="true"
		action="${pageContext.request.contextPath}/fiProductPointJournals.html"
		width="100%" form="fiBankbookJournalForm" retrieveRowsCallback="limit"
		rowsDisplayed="20" sortable="true"
		imagePath="./images/extremetable/*.gif" footer="${footTotalVar}">
		<c:if test="${sessionLogin.userType=='C' }">
			<ec:exportXls fileName="fiBankbookJournal.xls" />
		</c:if>
		<ec:row>
			<ec:column property="journalId" title="title.view" style="width:5px;"
				viewsAllowed="html">
				<a href="#"
					onclick="viewFiBankbookJournal('${fiBankbookJournal.journalId}')"><img
						src="images/newIcons/search_16.gif" border="0" width="16"
						height="16" />
				</a>
			</ec:column>
			<c:if test="${sessionScope.sessionLogin.userType=='C'}">
				<ec:column property="sysUser.userCode"
					title="label.member.or.agent.code">
				${fiBankbookJournal.sysUser.userCode }
			</ec:column>
			</c:if>
			<ec:column property="dealDate"
				title="comm.busi.deal.transaction.date" style="white-space:nowrap;">
				<fmt:formatDate value="${fiBankbookJournal.dealDate }"
					pattern="yyyy-MM-dd" />
			</ec:column>
			<ec:column property="notes" title="bdBounsDeduct.summary"
				escapeAutoFormat="true" sortable="false">			
			<jecs:substr key="${fn:replace(fiBankbookJournal.notes, vEnter, '<br>')}" length="15"/>
		</ec:column>
			<ec:column property="description" title="bdBounsDeduct.description"
				escapeAutoFormat="true" sortable="false">
			<jecs:substr key="${fn:replace(fiBankbookJournal.description, vEnter, '<br>')}" length="15"/>
		</ec:column>
			<ec:column property="addMoney" title="fibankbooktemp.dealtype.A"
				styleClass="numberColumn">
				<c:if test="${not empty fiBankbookJournal.addMoney}">
					<fmt:formatNumber value="${fiBankbookJournal.addMoney}"
						type="number" pattern="###,###,##0.00" />
				</c:if>
				<c:if test="${empty fiBankbookJournal.addMoney}">&nbsp;</c:if>
			</ec:column>
			<ec:column property="decMoney" title="fibankbooktemp.dealtype.D"
				styleClass="numberColumn">
				<c:if test="${not empty fiBankbookJournal.decMoney}">
					<fmt:formatNumber value="${fiBankbookJournal.decMoney}"
						type="number" pattern="###,###,##0.00" />
				</c:if>
				<c:if test="${empty fiBankbookJournal.decMoney}">&nbsp;</c:if>
			</ec:column>
			<ec:column property="balance" title="fiBankbookJournal.balance"
				styleClass="numberColumn">
				<c:if test="${empty fiBankbookJournal.balance}">0.00</c:if>
				<c:if test="${not empty fiBankbookJournal.balance}">
					<fmt:formatNumber
						value="${fiBankbookJournal.balance >= 1 ? fiBankbookJournal.balance - 0.004 : 0}"
						type="number" pattern="###,###,##0.00" />
				</c:if>
			</ec:column>
			<c:if test="${sessionLogin.userType=='C' }">
				<ec:column property="createrName" title="title.operator"
					style="white-space:nowrap;" />
			</c:if>

			<ec:column property="dataType" title="fibankbook.dataType"
				style="white-space:nowrap;">
				<jecs:code listCode="fibankbook.datatype"
					value="${fiBankbookJournal.dataType}" />
			</ec:column>
		</ec:row>
	</ec:table>
</form>

<script type="text/javascript">

   function editFiBankbookJournal(journalId){
   		<jecs:power powerCode="editFiBankbookJournal">
					window.location="editFiBankbookJournal.html?strAction=editFiBankbookJournal&journalId="+journalId;
			</jecs:power>
		}

function viewFiBankbookJournal(journalId){
	<jecs:power powerCode="fiViewProductPointJournal">
		window.location="fiViewProductPointJournal.html?journalId="+journalId;
	</jecs:power> 
	
	
	
}
</script>
