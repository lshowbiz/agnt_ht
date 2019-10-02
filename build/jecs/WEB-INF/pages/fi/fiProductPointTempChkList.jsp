<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiBankbookTempList.title" />
</title>
<content tag="heading">
<jecs:locale key="fiBankbookTempList.heading" />
</content>
<meta name="menu" content="FiBankbookTempMenu" />

<script language="javascript" src="scripts/validate.js"></script>
<!-- è£è½½æ¥åJS -->
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form action="fiProductPointTempsChk.html" method="get"
	name="fiProductPointTempSearchForm" id="fiProductPointTempSearchForm">

	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="label.member.or.agent.code" />
			<input name="sysUser.userCode" type="text"
				value="${param['sysUser.userCode'] }" size="10" />
		</div>
		<jecs:power powerCode="fi.check.petName">
			<div class="new_searchBar">
				<jecs:title key="miMember.firstNameKana" />
				<input name="sysUser.jmiMember.firstNameKana" type="text"
					value="${param['sysUser.jmiMember.firstNameKana'] }" size="12" />
			</div>
			<div class="new_searchBar">
				<jecs:title key="miMember.lastNameKana" />
				<input name="sysUser.jmiMember.lastNameKana" type="text"
					value="${param['sysUser.jmiMember.lastNameKana'] }" size="12" />
			</div>
		</jecs:power>
		<div class="new_searchBar">
			<jecs:title key="fiProductPointTemp.bankbookType" />
			<jecs:list listCode="fiproductpointtemp.productpointtype" name="productPointType"
				id="productPointType" value="${param.productPointType}" defaultValue="1" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiBankbookTemp.moneyType" />
			<jecs:list listCode="fibankbooktemp.moneytype" name="moneyType"
				id="moneyType" value="${param.moneyType}" defaultValue=""
				showBlankLine="true" />
		</div>
		<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
			<div class="new_searchBar">
				<jecs:title key="bdReconsumMoneyReport.companyCH" />
				<jecs:company name="companyCode" prompt="" withAA='false' />
			</div>
		</c:if>
		<div class="new_searchBar">
			<jecs:title key="fiBankbookTemp.status" />
			<jecs:list listCode="fiagentpayadvice.status" name="status"
				value="${param.status}" defaultValue="0" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="pd.createTime" />
			<input name="startCreateTime" id="startCreateTime" type="text" 
					value="${param.startCreateTime }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="endCreateTime" id="endCreateTime" type="text" 
					value="${param.endCreateTime }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiPayData.createrName" />
			<input name="createrName" type="text" value="${param.createrName }"
				size="10" />
			<input type="hidden" name="strAction" value="listFiProductPointTempsChkJJ" />
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
			<jecs:power powerCode="verifyFiProductPointTemp">
				<button name="verify1" class="btn btn_ins" type="button" onclick="verify($('fiProductPointTempSearchForm'))">
					<jecs:locale key="button.verify"/>
				</button>
			</jecs:power>
			<jecs:power powerCode="doNotDealFiProductPointTemp">
				<button name="search" class="btn btn_ins" type="button" onclick="doNotDeal($('fiProductPointTempSearchForm'))">
					<jecs:locale key="button.not.deal"/>
				</button>
			</jecs:power>
			<jecs:power powerCode="notVerifiedFiProductPointTemp">
				<button name="search" class="btn btn_ins" type="button" onclick="notVerified($('fiProductPointTempSearchForm'))">
					<jecs:locale key="button.not.verified"/>
				</button>
			</jecs:power>
		</div>
		<input type="hidden" name="strTempIds" value="" />
	</div>
</form>

<c:set var="footTotalVar">
	<tr>
		<td align="right" class="footer" colspan="6">
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
		<td class="footer" colspan='2'>
			&nbsp;
		</td>
	</tr>
</c:set>

<%
request.setAttribute("vEnter", "\n");
%>

<form action="fiProductPointTempsChk.html" method="get"
	name="fiProductPointTempForm" id="fiProductPointTempForm">
	<ec:table tableId="fiProductPointTempListTable" items="fiProductPointTempList"
		var="fiProductPointTemp" autoIncludeParameters="true"
		retrieveRowsCallback="limit" form="fiProductPointTempForm"
		action="${pageContext.request.contextPath}/fiProductPointTempsChk.html"
		width="100%" rowsDisplayed="1000" sortable="true"
		imagePath="./images/extremetable/*.gif" footer="${footTotalVar}">
		<ec:exportXls fileName="fiProductPointTempsChk.xls" />

		<ec:row>
			<ec:column alias="selectedId" headerCell="selectAll"
				style="width:5px;" viewsAllowed="html">
				<c:if test="${fiProductPointTemp.status!=2}">
					<input type="checkbox" name="selectedId"
						value="${fiProductPointTemp.tempId}" class="checkbox" />
				</c:if>
				<c:if test="${fiProductPointTemp.status==2}">
			&nbsp;
			</c:if>
			</ec:column>
			<ec:column property="sysUser.userCode"
				title="label.member.or.agent.code">
			${fiProductPointTemp.sysUser.userCode }
		</ec:column>
			<ec:column property="sysUser.userName" title="会员名称">
			${fiProductPointTemp.sysUser.userName }
		</ec:column>
			<ec:column property="moneyType" title="fiBankbookTemp.moneyType"
				cell="com.joymain.jecs.util.ectable.EcTableCell">
				<jecs:code listCode="fibankbooktemp.moneytype"
					value="${fiProductPointTemp.moneyType}" />
			</ec:column>
			<ec:column property="dealType" title="fiBankbookTemp.dealType"
				cell="com.joymain.jecs.util.ectable.EcTableCell">
				<jecs:code listCode="fibankbooktemp.dealtype"
					value="${fiProductPointTemp.dealType}" />
			</ec:column>
			<ec:column property="notes" title="bdBounsDeduct.summary"
				sortable="false">
				<jecs:substr key="${fiProductPointTemp.notes }" length="15"/>
		</ec:column>
			<ec:column property="description" title="bdBounsDeduct.description"
				sortable="false">
				<jecs:substr key="${fiProductPointTemp.description }" length="15"/>
		</ec:column>
			<ec:column property="addMoney" title="fibankbooktemp.dealtype.A"
				styleClass="numberColumn"
				cell="com.joymain.jecs.util.ectable.EcTableCell">
				<c:if test="${fiProductPointTemp.dealType=='A'}">
					<fmt:formatNumber value="${fiProductPointTemp.money}" type="number"
						pattern="###,###,##0.00" />
				</c:if>
				<c:if test="${fiProductPointTemp.dealType=='D'}">0.00</c:if>
			</ec:column>
			<ec:column property="decMoney" title="fibankbooktemp.dealtype.D"
				styleClass="numberColumn"
				cell="com.joymain.jecs.util.ectable.EcTableCell">
				<c:if test="${fiProductPointTemp.dealType=='A'}">0.00</c:if>
				<c:if test="${fiProductPointTemp.dealType=='D'}">
					<fmt:formatNumber value="${fiProductPointTemp.money}" type="number"
						pattern="###,###,##0.00" />
				</c:if>
			</ec:column>
			<ec:column property="status" title="fiBankbookTemp.status"
				style="white-space:nowrap;"
				cell="com.joymain.jecs.util.ectable.EcTableCell">
				<jecs:code listCode="fiagentpayadvice.status"
					value="${fiProductPointTemp.status}" />
			</ec:column>
			<ec:column property="createTime" title="pd.createTime">
			${fiProductPointTemp.createTime}
		</ec:column>
			<ec:column property="checkerName" title="pd.confirmUserNo"
				style="white-space:nowrap;" />
			<ec:column property="checkeTime" title="fiBankbookTemp.checkeTime" />
		</ec:row>

	</ec:table>

</form>

<script type="text/javascript">
function selectAll(theForm,status){
	var elements=document.getElementsByName("selectedId");
	if(elements!=undefined){
		for(var i=0;i<elements.length;i++){
			elements[i].checked=status;
		}
	}
}

function doNotDeal(theForm){
	if(!confirm("<jecs:locale key="fiBankbookTemp.confirm.doNotDeal"/>")){
		return false;
	}
	var elements=document.getElementsByName("selectedId");
	if(elements==undefined){
		alert("<jecs:locale key="please.select.fiBankbookTemp"/>");
		return false;
	}
	var selectedOne=false;
	for(var i=0;i<elements.length;i++){
		if(elements[i].checked){
			selectedOne=true;
			theForm.strTempIds.value+=","+elements[i].value;
		}
	}
	
	if(!selectedOne){
		alert("<jecs:locale key="please.select.fiBankbookTemp"/>");
		return false;
	}
	theForm.strAction.value="doNotDealFiProductPointTemp";
	if(isFormPosted()){
		theForm.submit();
	}
}

function notVerified(theForm){
	if(!confirm("<jecs:locale key="fiBankbookTemp.confirm.notVerified"/>")){
		return false;
	}
	var elements=document.getElementsByName("selectedId");
	if(elements==undefined){
		alert("<jecs:locale key="please.select.fiBankbookTemp"/>");
		return false;
	}
	var selectedOne=false;
	for(var i=0;i<elements.length;i++){
		if(elements[i].checked){
			selectedOne=true;
			theForm.strTempIds.value+=","+elements[i].value;
		}
	}
	
	if(!selectedOne){
		alert("<jecs:locale key="please.select.fiBankbookTemp"/>");
		return false;
	}
	theForm.strAction.value="notVerifiedFiProductPointTemp";
	if(isFormPosted()){
		theForm.submit();
	}
}

function verify(theForm){
	if(!confirm("<jecs:locale key="fiBankbookTemp.confirm.verify"/>")){
		return false;
	}
	var elements=document.getElementsByName("selectedId");
	if(elements==undefined){
		alert("<jecs:locale key="please.select.fiBankbookTemp"/>");
		return false;
	}
	var selectedOne=false;
	for(var i=0;i<elements.length;i++){
		if(elements[i].checked){
			selectedOne=true;
			theForm.strTempIds.value+=","+elements[i].value;
		}
	}
	
	if(!selectedOne){
		alert("<jecs:locale key="please.select.fiBankbookTemp"/>");
		return false;
	}
	theForm.strAction.value="verifyFiProductPointTemp";
	if(isFormPosted()){
		theForm.submit();
	}
}
</script>