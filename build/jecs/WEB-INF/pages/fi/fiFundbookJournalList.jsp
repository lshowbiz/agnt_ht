<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiFundbookJournalList.title" />
</title>
<content tag="heading">
<jecs:locale key="fiFundbookJournalList.heading" />
</content>
<meta name="menu" content="FiFundbookJournalMenu" />
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>


<c:set var="footTotalVar">
	<tr>
		<td align="right" class="footer" colspan="3">
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

	</tr>
</c:set>
<form action="" method="get" name="fiFundbookJournalSearchForm"
	id="fiFundbookJournalSearchForm">
	<div class="searchBar">
		<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			<div class="new_searchBar">
				<jecs:title key="label.member.or.agent.code" />
				<input name="userCode" type="text" value="${param['userCode'] }"
					size="14" />
			</div>
		</c:if>
		<div class="new_searchBar">
			<jecs:title key="fiBankbookTemp.bankbookType" />
			<jecs:list listCode="fifundbook.bankbooktype" name="bankbookType"
				id="bankbookType" value="${param.bankbookType}" defaultValue="" />
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

<form action="fiFundbookJournals.html" method="get"
	name="fiFundbookJournalForm" id="fiFundbookJournalForm">
	<ec:table tableId="fiFundbookJournalListTable"
		items="fiFundbookJournalList" var="fiFundbookJournal"
		autoIncludeParameters="true"
		action="${pageContext.request.contextPath}/fiFundbookJournals.html"
		width="100%" form="fiFundbookJournalForm" retrieveRowsCallback="limit"
		rowsDisplayed="20" sortable="true"
		imagePath="./images/extremetable/*.gif" footer="${footTotalVar}">
		<c:if test="${sessionLogin.userType=='C' }">
			<ec:exportXls fileName="fiFundbookJournal.xls" />
		</c:if>
		<ec:row>
			<%-- 
		<ec:column property="journalId" title="title.view" style="width:5px;">
			<a href="#" onclick="viewFiFundbookJournal('${fiFundbookJournal.journalId}')"><img src="images/newIcons/search_16.gif" border="0" width="16" height="16"/></a>
		</ec:column>
		--%>
			<c:if test="${sessionScope.sessionLogin.userType=='C'}">
				<ec:column property="userCode" title="label.member.or.agent.code">
				${fiFundbookJournal.userCode }
			</ec:column>
			</c:if>
			<ec:column property="dealDate"
				title="comm.busi.deal.transaction.date" style="white-space:nowrap;">
				<fmt:formatDate value="${fiFundbookJournal.dealDate }"
					pattern="yyyy-MM-dd" />
			</ec:column>
			<ec:column property="notes" title="bdBounsDeduct.summary"
				escapeAutoFormat="true" sortable="false">			
			<jecs:substr key="${fn:replace(fiFundbookJournal.notes, vEnter, '<br>')}" length="20"></jecs:substr>
		</ec:column>
			<ec:column property="money" title="fibankbooktemp.dealtype.A"
				styleClass="numberColumn">
				<c:if test="${not empty fiFundbookJournal.addMoney}">
					<fmt:formatNumber value="${fiFundbookJournal.addMoney}"
						type="number" pattern="###,###,##0.00" />
				</c:if>
				<c:if test="${empty fiFundbookJournal.addMoney}">&nbsp;</c:if>
			</ec:column>
			<ec:column property="money" title="fibankbooktemp.dealtype.D"
				styleClass="numberColumn">
				<c:if test="${not empty fiFundbookJournal.decMoney}">
					<fmt:formatNumber value="${fiFundbookJournal.decMoney}"
						type="number" pattern="###,###,##0.00" />
				</c:if>
				<c:if test="${empty fiFundbookJournal.decMoney}">&nbsp;</c:if>
			</ec:column>
			<ec:column property="balance" title="fiBankbookJournal.balance"
				styleClass="numberColumn">
				<c:if test="${empty fiFundbookJournal.balance}">0.00</c:if>
				<c:if test="${not empty fiFundbookJournal.balance}">
					<fmt:formatNumber value="${fiFundbookJournal.balance}"
						type="number" pattern="###,###,##0.00" />
				</c:if>
			</ec:column>
			<c:if test="${sessionLogin.userType=='C' }">
				<ec:column property="createrName" title="title.operator"
					style="white-space:nowrap;" />
			</c:if>
		</ec:row>
	</ec:table>
</form>
<script type="text/javascript">

   function editFiFundbookJournal(journalId){
   		<jecs:power powerCode="editFiFundbookJournal">
					window.location="editFiFundbookJournal.html?strAction=editFiFundbookJournal&journalId="+journalId;
			</jecs:power>
		}

function viewFiFundbookJournal(journalId){
	<jecs:power powerCode="viewFiFundbookJournal">
		window.location="fiViewFundbookJournal.html?strAction=viewFiFundbookJournal&journalId="+journalId;
	</jecs:power>
}
</script>


<!-- 

<c:set var="buttons">
		<jecs:power powerCode="addFiFundbookJournal">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editFiFundbookJournal.html"/>?strAction=addFiFundbookJournal'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="fiFundbookJournalListTable"
	items="fiFundbookJournalList"
	var="fiFundbookJournal"
	action="${pageContext.request.contextPath}/fiFundbookJournals.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editFiFundbookJournal('${fiFundbookJournal.journalId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="tempId" title="fiFundbookJournal.tempId" />
    			<ec:column property="companyCode" title="fiFundbookJournal.companyCode" />
    			<ec:column property="userCode" title="fiFundbookJournal.userCode" />
    			<ec:column property="serial" title="fiFundbookJournal.serial" />
    			<ec:column property="dealType" title="fiFundbookJournal.dealType" />
    			<ec:column property="dealDate" title="fiFundbookJournal.dealDate" />
    			<ec:column property="money" title="fiFundbookJournal.money" />
    			<ec:column property="notes" title="fiFundbookJournal.notes" />
    			<ec:column property="balance" title="fiFundbookJournal.balance" />
    			<ec:column property="remark" title="fiFundbookJournal.remark" />
    			<ec:column property="createrCode" title="fiFundbookJournal.createrCode" />
    			<ec:column property="createrName" title="fiFundbookJournal.createrName" />
    			<ec:column property="createTime" title="fiFundbookJournal.createTime" />
    			<ec:column property="moneyType" title="fiFundbookJournal.moneyType" />
    			<ec:column property="uniqueCode" title="fiFundbookJournal.uniqueCode" />
    			<ec:column property="bankbookType" title="fiFundbookJournal.bankbookType" />
    			<ec:column property="lastJournalId" title="fiFundbookJournal.lastJournalId" />
    			<ec:column property="lastMoney" title="fiFundbookJournal.lastMoney" />
    			<ec:column property="dataType" title="fiFundbookJournal.dataType" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editFiFundbookJournal(journalId){
   		<jecs:power powerCode="editFiFundbookJournal">
					window.location="editFiFundbookJournal.html?strAction=editFiFundbookJournal&journalId="+journalId;
			</jecs:power>
		}

</script>
 -->