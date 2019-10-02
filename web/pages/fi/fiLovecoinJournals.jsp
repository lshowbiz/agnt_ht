<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
	<title><jecs:locale key="fiLovecoinJournalList.title"/></title>
	<link rel="stylesheet" href="<%=path%>/styles/calendar/calendar-blue.css" /> 
	<script type="text/javascript" src="<%=path%>/scripts/calendar/calendar.js"> </script> 
	<script type="text/javascript" src="<%=path%>/scripts/calendar/calendar-setup.js"> </script> 
	<script type="text/javascript" src="<%=path%>/scripts/calendar/lang/calendar-en.js"> </script>
	<script>
		function viewFiLovecoinJournal(journalId){
			<jecs:power powerCode="viewFiLovecoinJournal">
				window.location="<%=path%>/fiLovecoinJournals.html?strAction=viewFiLovecoinJournal&journalId="+journalId;
			</jecs:power>
		}
	</script>
</head>
<body>
<form action="<%=path%>/fiLovecoinJournals.html" method="post" name="searchForm" id="searchForm">
	<div class="searchBar">
		<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			<div class="new_searchBar">
				<jecs:label key="label.member.or.agent.code"/>
				<input name="userCode" type="text" value="${param.userCode}" size="14"/>
			</div>
		</c:if>	
		<div class="new_searchBar">
			<jecs:label key="comm.busi.deal.transaction.date"/>
			<input name="dealDateStart" id="dealDateStart" type="text" 
				value="${param.dealDateStart}" style="cursor: pointer;width:76px;"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			-
			<input name="dealDateEnd" id="dealDateEnd" type="text"
				value="${param.dealDateEnd }" style="cursor: pointer;width:76px;"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		<!--  
			<input name="dealDateStart" id="dealDateStart" type="text" value="${param.dealDateStart}" class="readonly" readonly="readonly"/>
			<img src="<%=path%>/images/calendar/calendar7.gif" id="img_startOperateTime" style="cursor: pointer;" title="<fmt:message key="Calendar.TT.SEL_DATE"/>"/> 
			<script type="text/javascript"> 
				Calendar.setup({
				inputField     :    "dealDateStart", 
				ifFormat       :    "%Y-%m-%d %H:%M:00",
				button         :    "img_startOperateTime",
				showsTime	   :	true, 
				singleClick    :    true
				}); 
			</script>
		     - 
		     <input name="dealDateEnd" id="dealDateEnd" type="text" value="${param.dealDateEnd }" class="readonly" readonly="readonly"/>
		     <img src="<%=path%>/images/calendar/calendar7.gif" id="img_endOperateTime" style="cursor: pointer;" title="<fmt:message key="Calendar.TT.SEL_DATE"/>"/> 
			<script type="text/javascript"> 
				Calendar.setup({
				inputField     :    "dealDateEnd", 
				ifFormat       :    "%Y-%m-%d %H:%M:00",
				button         :    "img_endOperateTime", 
				showsTime	   :	true,
				singleClick    :    true
				}); 
			</script>	
		-->
		</div>
		<div class="new_searchBar">	
			<button name="search" class="btn btn_sele" style="width:auto" type="submit"><jecs:locale key="operation.button.search"/></button>
		</div>
	</div>
<!--  
	<div class="searchBar">
		<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			<jecs:label key="label.member.or.agent.code"/>
			<input name="userCode" type="text" value="${param.userCode}" size="14"/>
		</c:if>	
		<jecs:label key="comm.busi.deal.transaction.date"/>
		<input name="dealDateStart" id="dealDateStart" type="text" value="${param.dealDateStart}" class="readonly" readonly="readonly"/>
		<img src="<%=path%>/images/calendar/calendar7.gif" id="img_startOperateTime" style="cursor: pointer;" title="<fmt:message key="Calendar.TT.SEL_DATE"/>"/> 
		<script type="text/javascript"> 
			Calendar.setup({
			inputField     :    "dealDateStart", 
			ifFormat       :    "%Y-%m-%d %H:%M:00",
			button         :    "img_startOperateTime",
			showsTime	   :	true, 
			singleClick    :    true
			}); 
		</script>
	     - 
	     <input name="dealDateEnd" id="dealDateEnd" type="text" value="${param.dealDateEnd }" class="readonly" readonly="readonly"/>
	     <img src="<%=path%>/images/calendar/calendar7.gif" id="img_endOperateTime" style="cursor: pointer;" title="<fmt:message key="Calendar.TT.SEL_DATE"/>"/> 
		<script type="text/javascript"> 
			Calendar.setup({
			inputField     :    "dealDateEnd", 
			ifFormat       :    "%Y-%m-%d %H:%M:00",
			button         :    "img_endOperateTime", 
			showsTime	   :	true,
			singleClick    :    true
			}); 
		</script>		
		<input name="search" class="button" type="submit" value="<jecs:locale key="operation.button.search"/>" />
	</div>
-->
</form>

<ec:table 
	tableId="fiLovecoinJournalListTable"
	items="fiLovecoinJournalList"
	var="fiLovecoinJournal"
	autoIncludeParameters="true"
	action="${pageContext.request.contextPath}/fiLovecoinJournals.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
<jecs:power powerCode="fiExport">
	<c:if test="${sessionScope.sessionLogin.userType=='C'}">
	<ec:exportXls fileName="fi_inc_exp_stat.xls"/>
	</c:if>
</jecs:power>
	<ec:row >
		<%--  
		<ec:column property="journalId" title="title.view" style="width:5px;">
			<a href="#" onclick="viewFiLovecoinJournal('${fiLovecoinJournal.journalId}')"><img src="images/newIcons/search_16.gif" border="0" width="16" height="16"/></a>
		</ec:column>
		--%>
		<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			<ec:column property="sysUser.userCode" title="label.member.or.agent.code">
				${fiLovecoinJournal.sysUser.userCode}
			</ec:column>
		</c:if>
		<ec:column property="dealDate" title="comm.busi.deal.transaction.date" style="white-space:nowrap;">
			<fmt:formatDate value="${fiLovecoinJournal.dealDate }" pattern="yyyy-MM-dd"/>
		</ec:column>
		<c:set var="notesValue" scope="request">
			${fn:replace(fiLovecoinJournal.notes, vEnter, '<br>')}
		</c:set>
		<ec:column property="notes" title="bdBounsDeduct.summary" escapeAutoFormat="true" sortable="false" style="white-space:nowrap;">
			${requestScope.notesValue }
		</ec:column>
		<ec:column property="addMoney" title="fibankbooktemp.dealtype.A" styleClass="numberColumn">
			<c:if test="${not empty fiLovecoinJournal.addMoney}"><fmt:formatNumber value="${fiLovecoinJournal.addMoney}" type="number" pattern="###,###,##0.00"/></c:if>
			<c:if test="${empty fiLovecoinJournal.addMoney}">&nbsp;</c:if>
		</ec:column>
		<ec:column property="decMoney" title="fibankbooktemp.dealtype.D" styleClass="numberColumn">
			<c:if test="${not empty fiLovecoinJournal.decMoney}"><fmt:formatNumber value="${fiLovecoinJournal.decMoney}" type="number" pattern="###,###,##0.00"/></c:if>
			<c:if test="${empty fiLovecoinJournal.decMoney}">&nbsp;</c:if>
		</ec:column>
		<ec:column property="balance" title="fiBankbookJournal.balance" styleClass="numberColumn">
			<c:if test="${empty fiLovecoinJournal.balance}">0.00</c:if>
			<c:if test="${not empty fiLovecoinJournal.balance}"><fmt:formatNumber value="${fiLovecoinJournal.balance}" type="number" pattern="###,###,##0.00"/></c:if>
		</ec:column>
		<c:if test="${sessionLogin.userType=='C' }">
			<ec:column property="createrName" title="title.operator" style="white-space:nowrap;">
				${fiLovecoinJournal.createrName}
			</ec:column>
		</c:if>
	</ec:row>
</ec:table>	
</body>