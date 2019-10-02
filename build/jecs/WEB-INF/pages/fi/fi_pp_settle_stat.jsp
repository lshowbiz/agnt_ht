<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<title><jecs:locale key="fiBankbookTempList.title" />
</title>
<content tag="heading">
<jecs:locale key="fiBankbookTempList.heading" />
</content>
<meta name="menu" content="fiBankbookTempMenu" />

<script language="javascript" src="scripts/validate.js"></script>
<!-- 装载日历JS -->
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form action="fiPpSettleStat.html" method="get"
	name="fiBankbookTempSearchForm" id="fiBankbookTempSearchForm"
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
		</div>
		<div class="new_searchBar">
			<jecs:label key="fiBankbookTemp.bankbookType" />
			<jecs:list listCode="fiproductpointtemp.productpointtype" name="bankbookType"
				id="bankbookType" value="${param.bankbookType}" defaultValue="1" />
		</div>
		<div class="new_searchBar">
			<jecs:label key="miMember.createTimeRange" />
			<input name="startCreateTime" id="startCreateTime" type="text" 
					value="${param.startCreateTime }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="endCreateTime" id="endCreateTime" type="text" 
					value="${param.endCreateTime }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
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
		<td class="footer">
			&nbsp;
		</td>
	</tr>
</c:set>

<%
request.setAttribute("vEnter", "\n");
%>

<form action="/fiPpSettleStat.html" method="get" name="fiBankbookTempForm"
	id="fiBankbookTempForm">
	<ec:table tableId="fiBankbookTempListTable" items="fiBankbookTempList"
		var="fiBankbookTemp" autoIncludeParameters="true"
		retrieveRowsCallback="limit" form="fiBankbookTempForm"
		action="${pageContext.request.contextPath}/fiPpSettleStat.html"
		width="100%" rowsDisplayed="20" sortable="true"
		imagePath="./images/extremetable/*.gif" footer="${footTotalVar}">
		<ec:exportXls fileName="fi_settle_stat.xls" />
		<ec:row>
			<ec:column property="sysUser.userCode"
				title="label.member.or.agent.code">
			${fiBankbookTemp.sysUser.userCode }
		</ec:column>
			<ec:column property="checkeTime" title="fiBankbookTemp.checkeTime"
				cell="com.joymain.jecs.util.ectable.EcTableCell">
				<fmt:formatDate value="${fiBankbookTemp.checkeTime }"
					pattern="yyyy-MM-dd HH:mm:ss" />
			</ec:column>
			<ec:column property="notes" title="bdBounsDeduct.summary"
				sortable="false">
			<jecs:substr key="${fn:replace(fiBankbookTemp.notes, vEnter, '<br>')}" length="15"/>
		</ec:column>
			<ec:column property="addMoney" title="fibankbooktemp.dealtype.A"
				styleClass="numberColumn" width="100" sortable="false">
				<c:if test="${not empty fiBankbookTemp.addMoney}">
					<fmt:formatNumber value="${fiBankbookTemp.addMoney}" type="number"
						pattern="###,###,##0.00" />
				</c:if>
				<c:if test="${empty fiBankbookTemp.addMoney}">&nbsp;</c:if>
			</ec:column>
			<ec:column property="decMoney" title="fibankbooktemp.dealtype.D"
				styleClass="numberColumn" width="100" sortable="false">
				<c:if test="${not empty fiBankbookTemp.decMoney}">
					<fmt:formatNumber value="${fiBankbookTemp.decMoney}" type="number"
						pattern="###,###,##0.00" />
				</c:if>
				<c:if test="${empty fiBankbookTemp.decMoney}">&nbsp;</c:if>
			</ec:column>
			<ec:column property="status" title="fiBankbookTemp.status"
				width="100" cell="com.joymain.jecs.util.ectable.EcTableCell">
				<jecs:code listCode="fiagentpayadvice.status"
					value="${fiBankbookTemp.status}" />
			</ec:column>
			<ec:column property="createrName" title="fiPayData.createrName"
				width="100">
			${fiBankbookTemp.createrName }
		</ec:column>

			<ec:column property="createTime" title="创建时间" width="100"
				cell="com.joymain.jecs.util.ectable.EcTableCell">
				<fmt:formatDate value="${fiBankbookTemp.createTime }"
					pattern="yyyy-MM-dd HH:mm:ss" />
			</ec:column>

			<ec:column property="checkerName" title="pd.confirmUserNo"
				width="100">
		${fiBankbookTemp.checkerName }
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