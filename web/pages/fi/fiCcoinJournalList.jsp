<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
	<title><jecs:locale key="fiCcoinJournalList.title"/></title>
	<link rel="stylesheet" href="<%=path%>/styles/calendar/calendar-blue.css" /> 
	<script type="text/javascript" src="<%=path%>/scripts/calendar/calendar.js"> </script> 
	<script type="text/javascript" src="<%=path%>/scripts/calendar/calendar-setup.js"> </script> 
	<script type="text/javascript" src="<%=path%>/scripts/calendar/lang/calendar-en.js"> </script>
	<script>
		function viewFiCcoinJournal(journalId){
			<jecs:power powerCode="viewFiCcoinJournal">
				window.location="<%=path%>/fiCcoinJournalList.html?strAction=viewFiCcoinJournal&journalId="+journalId;
			</jecs:power>
		}
	</script>
</head>
<body>
<form action="<%=path%>/fiCcoinJournalList.html" method="post" name="searchForm" id="searchForm">
	<div class="searchBar">
		<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			<jecs:title key="label.member.or.agent.code"/>
			<input name="userCode" type="text" value="${param.userCode}" size="14"/>
		</c:if>	
		<jecs:title key="comm.busi.deal.transaction.date"/>
		<input name="dealDateStart" id="dealDateStart" type="text" value="${param.dealDateStart}" style="width:80px;" class="readonly" readonly="readonly"/>
		<img src="<%=path%>/images/calendar/calendar7.gif" id="img_startOperateTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
		<script type="text/javascript"> 
			Calendar.setup({
			inputField     :    "dealDateStart", 
			ifFormat       :    "%Y-%m-%d",
			button         :    "img_startOperateTime", 
			showsTime	   :	true,
			singleClick    :    true
			}); 
		</script>
	     - 
	     <input name="dealDateEnd" id="dealDateEnd" type="text" value="${param.dealDateEnd }" style="width:80px;" class="readonly" readonly="readonly"/>
	     <img src="<%=path%>/images/calendar/calendar7.gif" id="img_endOperateTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
		<script type="text/javascript"> 
			Calendar.setup({
			inputField     :    "dealDateEnd", 
			ifFormat       :    "%Y-%m-%d",
			button         :    "img_endOperateTime", 
			showsTime	   :	true,
			singleClick    :    true
			}); 
		</script>		
		<input name="search" class="button" type="submit" value="<jecs:locale key="operation.button.search"/>" />
	</div>	
</form>

<ec:table 
	tableId="fiCcoinJournalListTable"
	items="fiCcoinJournalList"
	var="fiCcoinJournal"
	action="${pageContext.request.contextPath}/fiCcoinJournalList.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
	<ec:row >
		<ec:column property="journalId" title="title.view" style="width:5px;">
			<a href="#" onclick="viewFiCcoinJournal('${fiCcoinJournal.journalId}')"><img src="images/newIcons/search_16.gif" border="0" width="16" height="16"/></a>
		</ec:column>
		<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			<ec:column property="sysUser.userCode" title="label.member.or.agent.code">
				${fiCcoinJournal.sysUser.userCode}
			</ec:column>
		</c:if>
		<ec:column property="dealDate" title="comm.busi.deal.transaction.date" style="white-space:nowrap;">
			<fmt:formatDate value="${fiCcoinJournal.dealDate }" pattern="yyyy-MM-dd"/>
		</ec:column>
		<ec:column property="notes" title="bdBounsDeduct.summary" escapeAutoFormat="true" sortable="false">
			${fn:replace(fiCcoinJournal.notes, vEnter, '<br>')}
		</ec:column>
		<ec:column property="coin" title="fibankbooktemp.dealtype.A" styleClass="numberColumn">
			<c:if test="${not empty fiCcoinJournal.addMoney}"><fmt:formatNumber value="${fiCcoinJournal.addMoney}" type="number" pattern="###,###,##0.00"/></c:if>
			<c:if test="${empty fiCcoinJournal.addMoney}">&nbsp;</c:if>
		</ec:column>
		<ec:column property="coin" title="fibankbooktemp.dealtype.D" styleClass="numberColumn">
			<c:if test="${not empty fiCcoinJournal.decMoney}"><fmt:formatNumber value="${fiCcoinJournal.decMoney}" type="number" pattern="###,###,##0.00"/></c:if>
			<c:if test="${empty fiCcoinJournal.decMoney}">&nbsp;</c:if>
		</ec:column>
		<ec:column property="balance" title="fiBankbookJournal.balance" styleClass="numberColumn">
			<c:if test="${empty fiCcoinJournal.balance}">0.00</c:if>
			<c:if test="${not empty fiCcoinJournal.balance}"><fmt:formatNumber value="${fiCcoinJournal.balance}" type="number" pattern="###,###,##0.00"/></c:if>
		</ec:column>
		<c:if test="${sessionLogin.userType=='C' }">
			<ec:column property="createrName" title="title.operator" style="white-space:nowrap;">
				${fiCcoinJournal.createrName}
			</ec:column>
		</c:if>
	</ec:row>
</ec:table>	
</body>