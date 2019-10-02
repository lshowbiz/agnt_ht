<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiFundbookJournalList.title" />
</title>
<content tag="heading">
<jecs:locale key="fiFundbookJournalList.heading" />
</content>
<meta name="menu" content="fiFundbookJournalMenu" />

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form action="fiFunkbookIncExpStat.html" method="get"
	name="fiFundbookJournalSearchForm" id="fiFundbookJournalSearchForm"
	onsubmit="return validateSearch(this);">
	<input name="search" value="name" type="hidden"/>
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:label key="fiBankbookTemp.bankbookType" />
			<jecs:list listCode="fifundbook.bankbooktype" name="bankbookType"
				id="bankbookType" value="${param.bankbookType}" defaultValue="1" />
		</div>
		<div class="new_searchBar">	
			<jecs:label key="fiBankbookTemp.moneyType" />
			<jecs:list name="moneyType" listCode="fifundbooktemp.moneytype"
				value="${param.moneyType}" defaultValue="" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			<jecs:label key="label.member.or.agent.code" />
			<input name="userCode" type="text" value="${param['userCode'] }"
				size="10" />
		</div>
		<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
			<div class="new_searchBar">
				<jecs:label key="bdReconsumMoneyReport.companyCH" />
				<jecs:company selected="${param.companyCode }" name="companyCode"
					prompt="" withAA='false' />
			</div>
		</c:if>
		<div class="new_searchBar">
			<jecs:label key="aiAgent.createTime" />
			<input name="startCreateDate" id="startCreateDate" type="text" 
					value="${param.startCreateDate }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="endCreateDate" id="endCreateDate" type="text" 
					value="${param.endCreateDate }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			<jecs:label key="title.operator" />
			<input name="createrName" type="text" value="${param.createrName }"
				size="10" />
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
	</div>
</form>

<c:set var="footTotalVar">
	<tr>
		<td align="right" class="footer" colspan="3">
			<jecs:locale key="report.allTotal" />
			(
			<b><fmt:formatNumber pattern="###,##0.00">${incExpMap.incTotal-incExpMap.expTotal}</fmt:formatNumber>
			</b>)
		</td>
		<td align="right" class="footer">
			<b> <c:if test="${not empty incExpMap.incTotal}">
					<fmt:formatNumber pattern="###,##0.00">${incExpMap.incTotal}</fmt:formatNumber>
				</c:if> <c:if test="${empty incExpMap.incTotal}">
			0.00
		</c:if>
			</b>
		</td>
		<td align="right" class="footer">
			<b> <c:if test="${not empty incExpMap.expTotal}">
					<fmt:formatNumber pattern="###,##0.00">${incExpMap.expTotal}</fmt:formatNumber>
				</c:if> <c:if test="${empty incExpMap.expTotal}">
			0.00
		</c:if>
			</b>
		</td>
		<td class="footer">
			&nbsp;
		</td>
		<td class="footer">
			&nbsp;
		</td>
	</tr>
</c:set>

<%
request.setAttribute("vEnter", "\n");
%>

<form action="fiFunkbookIncExpStat.html" method="get"
	name="fiFundbookJournalForm" id="fiFundbookJournalForm">
	<ec:table tableId="fiFundbookJournalListTable"
		items="fiFundbookJournals" var="fiFundbookJournal"
		autoIncludeParameters="true" retrieveRowsCallback="limit"
		form="fiFundbookJournalForm"
		action="${pageContext.request.contextPath}/fiFunkbookIncExpStat.html"
		width="100%" rowsDisplayed="20" sortable="true"
		imagePath="./images/extremetable/*.gif" footer="${footTotalVar}">
		<ec:exportXls fileName="fi_fundbook_inc_exp_stat.xls" />
		<ec:row>
			<ec:column property="userCode" title="label.member.or.agent.code">
			${fiFundbookJournal.userCode }
		</ec:column>
			<ec:column property="createTime" title="aiAgent.createTime"
				style="white-space:nowrap;">
				<fmt:formatDate value="${fiFundbookJournal.createTime }"
					pattern="yyyy-MM-dd HH:mm:ss" />
			</ec:column>
			<ec:column property="notes" title="bdBounsDeduct.summary"
				sortable="false">
			<jecs:substr key="${fn:replace(fiFundbookJournal.notes, vEnter, '<br>')}" length="25"/>
		</ec:column>
			<ec:column property="addMoney" title="fibankbooktemp.dealtype.A"
				styleClass="numberColumn" width="100" sortable="false">
				<c:if test="${not empty fiFundbookJournal.addMoney}">
					<fmt:formatNumber value="${fiFundbookJournal.addMoney}"
						type="number" pattern="###,###,##0.00" />
				</c:if>
				<c:if test="${empty fiFundbookJournal.addMoney}">&nbsp;</c:if>
			</ec:column>
			<ec:column property="decMoney" title="fibankbooktemp.dealtype.D"
				styleClass="numberColumn" width="100" sortable="false">
				<c:if test="${not empty fiFundbookJournal.decMoney}">
					<fmt:formatNumber value="${fiFundbookJournal.decMoney}"
						type="number" pattern="###,###,##0.00" />
				</c:if>
				<c:if test="${empty fiFundbookJournal.decMoney}">&nbsp;</c:if>
			</ec:column>
			<ec:column property="createrName" title="title.operator"
				style="white-space:nowrap;" width="100" />

			<%-- 
		<ec:column property="journalId" title="title.view" style="width:100px;">
			<a href="#" onclick="viewfiFundbookJournal('${fiFundbookJournal.journalId}')"><img src="images/newIcons/search_16.gif" border="0" width="16" height="16"/></a>
		</ec:column>
		--%>
		</ec:row>
	</ec:table>
</form>

<script type="text/javascript">

function viewfiFundbookJournal(journalId){
	<jecs:power powerCode="viewFiFundbookJournalJJ">
		window.location="fiViewFundbookJournal.html?strAction=viewFiFundbookJournalJJ&journalId="+journalId;
	</jecs:power>
}

function validateSearch(theForm){
	if(theForm.moneyType.value=="" && theForm.userCode.value=="" && theForm.startCreateDate.value=="" && theForm.endCreateDate.value=="" && theForm.createrName.value==""){
		alert("<jecs:locale key="please.input.search.condition"/>");
		return false;
	}
	return true;
}

function selectUser(theForm){
	var pars=new Array();
	pars[0]="<jecs:locale key="operation.notice.select.member.agent"/>";
	pars[1]="sysUserSelect.html?strAction=companyUserSelect&selectControl=agentAndMember&userCode="+theForm.userCode.value;
	pars[2]=window;
	var ret=showDialogWin("<%=request.getContextPath()%>",pars,650,400,"yes");
	if(ret!=undefined && ret!=""){
		theForm.userCode.value=ret.userCode;
	}
}
</script>