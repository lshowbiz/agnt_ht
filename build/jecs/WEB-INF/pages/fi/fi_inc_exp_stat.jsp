<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiBankbookJournalList.title" />
</title>
<content tag="heading">
<jecs:locale key="fiBankbookJournalList.heading" />
</content>
<meta name="menu" content="fiBankbookJournalMenu" />

<!-- 装载日历JS -->
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form action="fiIncExpStat.html" method="get"
	name="fiBankbookJournalSearchForm" id="fiBankbookJournalSearchForm"
	onsubmit="return validateSearch(this);">
	<input name="search" value="name" type="hidden"/>
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:label key="fiBankbookTemp.bankbookType" />
			<jecs:list listCode="fibankbooktemp.bankbooktype" name="bankbookType"
				id="bankbookType" value="${param.bankbookType}" defaultValue="1" />
		</div>
		<div class="new_searchBar">
			<jecs:label key="fiBankbookTemp.moneyType" />
			<jecs:list name="moneyType" listCode="fibankbooktemp.moneytype"
				value="${param.moneyType}" defaultValue="0" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			<jecs:label key="label.member.or.agent.code" />
			<input name="sysUser.userCode" type="text"
				value="${param['sysUser.userCode'] }" size="10" />
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
		<td class="footer">
			&nbsp;
		</td>
	</tr>
</c:set>

<%
request.setAttribute("vEnter", "\n");
%>

<form action="" method="get" name="fiBankbookJournalForm"
	id="fiBankbookJournalForm">
	<ec:table tableId="fiBankbookJournalListTable"
		items="fiBankbookJournalList" var="fiBankbookJournal"
		autoIncludeParameters="true" retrieveRowsCallback="limit"
		form="fiBankbookJournalForm"
		action="${pageContext.request.contextPath}/fiIncExpStat.html"
		width="100%" rowsDisplayed="20" sortable="true"
		imagePath="./images/extremetable/*.gif" footer="${footTotalVar}">
		<ec:exportXls fileName="fi_inc_exp_stat.xls" />
		<ec:row>
			<ec:column property="sysUser.userCode"
				title="label.member.or.agent.code">
			${fiBankbookJournal.sysUser.userCode }
		</ec:column>
			<ec:column property="createTime" title="aiAgent.createTime"
				style="white-space:nowrap;">
				<fmt:formatDate value="${fiBankbookJournal.createTime }"
					pattern="yyyy-MM-dd HH:mm:ss" />
			</ec:column>
			<ec:column property="notes" title="bdBounsDeduct.summary"
				sortable="false">
			<jecs:substr key="${fn:replace(fiBankbookJournal.notes, vEnter, '<br>')}" length="15"/>
		</ec:column>
			<ec:column property="description" title="bdBounsDeduct.description"
				sortable="false">
			<jecs:substr key="${fn:replace(jfiBankbookJournal.description, vEnter, '<br>')}" length="15"/>
		</ec:column>
			<ec:column property="addMoney" title="fibankbooktemp.dealtype.A"
				styleClass="numberColumn" width="100" sortable="false">
				<c:if test="${not empty fiBankbookJournal.addMoney}">
					<fmt:formatNumber value="${fiBankbookJournal.addMoney}"
						type="number" pattern="###,###,##0.00" />
				</c:if>
				<c:if test="${empty fiBankbookJournal.addMoney}">&nbsp;</c:if>
			</ec:column>
			<ec:column property="decMoney" title="fibankbooktemp.dealtype.D"
				styleClass="numberColumn" width="100" sortable="false">
				<c:if test="${not empty fiBankbookJournal.decMoney}">
					<fmt:formatNumber value="${fiBankbookJournal.decMoney}"
						type="number" pattern="###,###,##0.00" />
				</c:if>
				<c:if test="${empty fiBankbookJournal.decMoney}">&nbsp;</c:if>
			</ec:column>
			<ec:column property="createrName" title="title.operator"
				style="white-space:nowrap;" width="100" />
			<ec:column property="journalId" title="title.view"
				style="width:100px;">
				<a href="#"
					onclick="viewfiBankbookJournal('${fiBankbookJournal.journalId}')"><img
						src="images/newIcons/search_16.gif" border="0" width="16"
						height="16" />
				</a>
			</ec:column>
		</ec:row>
	</ec:table>
</form>

<script type="text/javascript">

function viewfiBankbookJournal(journalId){
	<jecs:power powerCode="viewFiBankbookJournalJJ">
		window.location="fiViewBankbookJournal.html?strAction=viewFiBankbookJournalJJ&journalId="+journalId;
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