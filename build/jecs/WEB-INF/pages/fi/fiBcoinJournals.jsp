<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
	<title><jecs:locale key="fiBcoinJournalList.title"/></title>
	<link rel="stylesheet" href="<%=path%>/styles/calendar/calendar-blue.css" /> 
	<script type="text/javascript" src="<%=path%>/scripts/calendar/calendar.js"> </script> 
	<script type="text/javascript" src="<%=path%>/scripts/calendar/calendar-setup.js"> </script> 
	<script type="text/javascript" src="<%=path%>/scripts/calendar/lang/calendar-en.js"> </script>
	<script>
		function viewFiBcoinJournal(journalId){
			<jecs:power powerCode="viewFiBcoinJournal">
				window.location="<%=path%>/fiBcoinJournals.html?strAction=viewFiBcoinJournal&journalId="+journalId;
			</jecs:power>
		}
	</script>
</head>
<body>
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
<form action="<%=path%>/fiBcoinJournals.html" method="post" name="searchForm" id="searchForm">
	<div class="searchBar">
		<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			<div class="new_searchBar">
					<jecs:label key="label.member.or.agent.code" />
					<input name="userCode" type="text" value="${param.userCode}"
						size="14" />
				</div>
		</c:if>	
		<div class="new_searchBar">
				<jecs:label key="comm.busi.deal.transaction.date" />
				<input name="dealDateStart" id="dealDateStart" type="text" 
					value="${param.dealDateStart }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					-
				<input name="dealDateEnd" id="dealDateEnd" type="text" 
					value="${param.dealDateEnd }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>	
		<div class="new_searchBar">
				<button name="search" class="btn btn_sele" type="submit">
					<jecs:locale key="operation.button.search"/>
				</button>
			</div>
	</div>
</form>

<ec:table 
	tableId="fiBcoinJournalListTable"
	items="fiBcoinJournalList"
	var="fiBcoinJournal"
	autoIncludeParameters="true"
	action="${pageContext.request.contextPath}/fiBcoinJournals.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif" footer="${footTotalVar}">
<jecs:power powerCode="fiExport">
	<c:if test="${sessionScope.sessionLogin.userType=='C'}">
	<ec:exportXls fileName="fi_inc_exp_stat.xls"/>
	</c:if>
</jecs:power>
	<ec:row >
		<ec:column property="journalId" title="title.view" style="width:5px;" viewsDenied="xls">
			<a href="#" onclick="viewFiBcoinJournal('${fiBcoinJournal.journalId}')"><img src="images/newIcons/search_16.gif" border="0" width="16" height="16"/></a>
		</ec:column>
		<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			<ec:column property="sysUser.userCode" title="label.member.or.agent.code">
				${fiBcoinJournal.sysUser.userCode}
			</ec:column>
		</c:if>
		<ec:column property="dealDate" title="comm.busi.deal.transaction.date" style="white-space:nowrap;">
			<fmt:formatDate value="${fiBcoinJournal.dealDate }" pattern="yyyy-MM-dd"/>
		</ec:column>
		<ec:column property="notes" title="bdBounsDeduct.summary" escapeAutoFormat="true" sortable="false">
			<jecs:substr key="${fn:replace(fiBcoinJournal.notes, vEnter, '<br>')}" length="15"/>
		</ec:column>
		<ec:column property="description" title="bdBounsDeduct.description" sortable="false">
			<jecs:substr key="${fn:replace(jfiBankbookJournal.description, vEnter, '<br>')}" length="15"/>
		</ec:column>
		<ec:column property="addMoney" title="fibankbooktemp.dealtype.A" styleClass="numberColumn" sortable="false">
			<c:if test="${not empty fiBcoinJournal.addMoney}"><fmt:formatNumber value="${fiBcoinJournal.addMoney}" type="number" pattern="###,###,##0.00"/></c:if>
			<c:if test="${empty fiBcoinJournal.addMoney}">&nbsp;</c:if>
		</ec:column>
		<ec:column property="decMoney" title="fibankbooktemp.dealtype.D" styleClass="numberColumn" sortable="false">
			<c:if test="${not empty fiBcoinJournal.decMoney}"><fmt:formatNumber value="${fiBcoinJournal.decMoney}" type="number" pattern="###,###,##0.00"/></c:if>
			<c:if test="${empty fiBcoinJournal.decMoney}">&nbsp;</c:if>
		</ec:column>
		<ec:column property="balance" title="fiBankbookJournal.balance" styleClass="numberColumn">
			<c:if test="${empty fiBcoinJournal.balance}">0.00</c:if>
			<c:if test="${not empty fiBcoinJournal.balance}"><fmt:formatNumber value="${fiBcoinJournal.balance >= 1 ? fiBcoinJournal.balance - 0.004 : 0}" type="number" pattern="###,###,##0.00"/></c:if>
		</ec:column>
		<c:if test="${sessionLogin.userType=='C' }">
			<ec:column property="createrName" title="title.operator" style="white-space:nowrap;">
				${fiBcoinJournal.createrName}
			</ec:column>
		</c:if>
		
		<ec:column property="dataType" title="fibankbook.dataType" style="white-space:nowrap;">
			<jecs:code listCode="fibankbook.datatype" value="${fiBcoinJournal.dataType}"/>
		</ec:column>
	</ec:row>
</ec:table>	
</body>