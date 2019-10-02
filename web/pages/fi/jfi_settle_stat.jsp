<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiBankbookTempList.title" />
</title>
<content tag="heading">
<jecs:locale key="jfiBankbookTempList.heading" />
</content>
<meta name="menu" content="jfiBankbookTempMenu" />

<script language="javascript" src="scripts/validate.js"></script>
<!-- 装载日历JS -->
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form action="jfiSettleStat.html" method="get"
	name="jfiBankbookTempSearchForm" id="jfiBankbookTempSearchForm"
	onsubmit="return validateSearch(this);">
	<input name="search" value="name" type="hidden"/>
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:label key="fiBankbookTemp.moneyType" />
			<jecs:list name="moneyType" listCode="fibankbooktemp.moneytype"
				value="${param.moneyType}" defaultValue="0" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			<jecs:label key="fiBankbookTemp.status" />
			<jecs:list listCode="fiagentpayadvice.status" name="status"
				value="${param.status}" defaultValue="0" showBlankLine="true" />
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
			<jecs:label key="miMember.createTimeRange.start" />
			<input name="startCreateTime" id="startCreateTime" type="text" 
					value="${param.startCreateTime }" style="cursor: pointer;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			<jecs:label key="miMember.createTimeRange.end" />
			<input name="endCreateTime" id="endCreateTime" type="text" 
					value="${param.endCreateTime }" style="cursor: pointer;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			<jecs:label key="title.operator" />
			<input name="createrName" type="text" value="${param.createrName }"
				size="10" />
		</div>
		<div class="new_searchBar">
			<jecs:label key="pd.confirmUserNo" />
			<input name="checkerName" type="text" value="${param.checkerName }"
				size="10" />
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
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

<form action="/jfiSettleStat.html" method="get"
	name="jfiBankbookTempForm" id="jfiBankbookTempForm">
	<ec:table tableId="jfiBankbookTempListTable"
		items="jfiBankbookTempList" var="jfiBankbookTemp"
		autoIncludeParameters="true" retrieveRowsCallback="limit"
		form="jfiBankbookTempForm"
		action="${pageContext.request.contextPath}/jfiSettleStat.html"
		width="100%" rowsDisplayed="20" sortable="true"
		imagePath="./images/extremetable/*.gif" footer="${footTotalVar}">
		<ec:exportXls fileName="fi_settle_stat.xls" />
		<ec:row>
			<ec:column property="sysUser.userCode"
				title="label.member.or.agent.code">
			${jfiBankbookTemp.sysUser.userCode }
		</ec:column>
			<ec:column property="checkeTime" title="fiBankbookTemp.checkeTime">
				<fmt:formatDate value="${jfiBankbookTemp.checkeTime }"
					pattern="yyyy-MM-dd HH:mm:ss" />
			</ec:column>
			<ec:column property="notes" title="bdBounsDeduct.summary"
				sortable="false">
			<jecs:substr key="${fn:replace(jfiBankbookTemp.notes, vEnter, '<br>')}" length="15"/>
		</ec:column>
			<ec:column property="addMoney" title="fibankbooktemp.dealtype.A"
				styleClass="numberColumn" width="100" sortable="false">
				<c:if test="${not empty jfiBankbookTemp.addMoney}">
					<fmt:formatNumber value="${jfiBankbookTemp.addMoney}" type="number"
						pattern="###,###,##0.00" />
				</c:if>
				<c:if test="${empty jfiBankbookTemp.addMoney}">&nbsp;</c:if>
			</ec:column>
			<ec:column property="decMoney" title="fibankbooktemp.dealtype.D"
				styleClass="numberColumn" width="100" sortable="false">
				<c:if test="${not empty jfiBankbookTemp.decMoney}">
					<fmt:formatNumber value="${jfiBankbookTemp.decMoney}" type="number"
						pattern="###,###,##0.00" />
				</c:if>
				<c:if test="${empty jfiBankbookTemp.decMoney}">&nbsp;</c:if>
			</ec:column>
			<ec:column property="status" title="fiBankbookTemp.status"
				width="100">
				<jecs:code listCode="fiagentpayadvice.status"
					value="${jfiBankbookTemp.status}" />
			</ec:column>
			<ec:column property="createTime" title="fiPayData.createrName"
				width="100">
		${jfiBankbookTemp.createrName }<br>${jfiBankbookTemp.createTime }
		</ec:column>
			<ec:column property="checkerName" title="pd.confirmUserNo"
				width="100">
		${jfiBankbookTemp.checkerName }
		</ec:column>
		</ec:row>

	</ec:table>

</form>

<script type="text/javascript">

function validateSearch(theForm){
	if(theForm.moneyType.value=="" && theForm.userCode.value==""
		&& theForm.status.value=="" && theForm.startCreateTime.value=="" && theForm.endCreateTime.value==""
		&& theForm.createrName.value=="" && theForm.checkerName.value==""){
		alert("<jecs:locale key="please.input.search.condition"/>");
		return false;
	}
	return true;
}
</script>