<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiFundbookTempList.title" />
</title>
<content tag="heading">
<jecs:locale key="fiFundbookTempList.heading" />
</content>
<meta name="menu" content="FiFundbookTempMenu" />

<script language="javascript" src="scripts/validate.js"></script>
<!-- 装载日历JS -->
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form action="fiFundbookTempsChk.html" method="get"
	name="fiFundbookTempSearchForm" id="fiFundbookTempSearchForm">

	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="label.member.or.agent.code" />
			<input name="userCode" type="text" value="${param['userCode'] }"
				size="10" />
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
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiBankbookTemp.bankbookType" />
			<jecs:list listCode="fifundbook.bankbooktype" name="bankbookType"
				id="bankbookType" value="${param.bankbookType}" defaultValue="1" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiBankbookTemp.moneyType" />
			<jecs:list listCode="fifundbooktemp.moneytype" name="moneyType"
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
			<input type="hidden" name="strAction" value="listFiFundbookTempsChkJJ" />
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
			<jecs:power powerCode="verifyFiFundbookTempJJ">
				<button name="search" class="btn btn_ins" type="button" onclick="verify($('fiFundbookTempSearchForm'))">
					<jecs:locale key="button.verify"/>
				</button>
			</jecs:power>
			<jecs:power powerCode="doNotDealFiFundbookTempJJ">
				<button name="search" class="btn btn_ins" type="button" onclick="doNotDeal($('fiFundbookTempSearchForm'))">
					<jecs:locale key="button.not.deal"/>
				</button>
			</jecs:power>
			<jecs:power powerCode="notVerifiedFiFundbookTempJJ">
				<button name="search" class="btn btn_ins" type="button" onclick="notVerified($('fiFundbookTempSearchForm'))">
					<jecs:locale key="button.not.verified"/>
				</button>
			</jecs:power>
		</div>
		<input type="hidden" name="strTempIds" value="" />
	</div>
</form>

<c:set var="footTotalVar">
	<tr>
		<td align="right" class="footer" colspan="5">
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

<form action="fiFundbookTempsChk.html" method="get"
	name="fiFundbookTempForm" id="fiFundbookTempForm">

	<ec:table tableId="fiFundbookTempListTable"
		items="fiFundbookTempChkList" var="fiFundbookTemp"
		autoIncludeParameters="true" retrieveRowsCallback="limit"
		form="fiFundbookTempForm"
		action="${pageContext.request.contextPath}/fiFundbookTempsChk.html"
		width="100%" rowsDisplayed="20" sortable="true"
		imagePath="./images/extremetable/*.gif" footer="${footTotalVar}">
		<ec:row>


			<ec:column alias="selectedId" headerCell="selectAll"
				style="width:5px;">
				<c:if test="${fiFundbookTemp.status!=2}">
					<input type="checkbox" name="selectedId"
						value="${fiFundbookTemp.tempId}" class="checkbox" />
				</c:if>
				<c:if test="${fiFundbookTemp.status==2}">
			&nbsp;
			</c:if>
			</ec:column>
			<ec:column property="userCode" title="label.member.or.agent.code">
			${fiFundbookTemp.userCode }
		</ec:column>
			<ec:column property="moneyType" title="fiBankbookTemp.moneyType">
				<jecs:code listCode="fifundbooktemp.moneytype"
					value="${fiFundbookTemp.moneyType}" />
			</ec:column>
			<ec:column property="dealType" title="fiBankbookTemp.dealType">
				<jecs:code listCode="fibankbooktemp.dealtype"
					value="${fiFundbookTemp.dealType}" />
			</ec:column>
			<ec:column property="notes" title="bdBounsDeduct.summary"
				sortable="false">
			${fn:replace(fiFundbookTemp.notes, vEnter, '<br>')}
		</ec:column>
			<ec:column property="money" title="fibankbooktemp.dealtype.A"
				styleClass="numberColumn">
				<c:if test="${fiFundbookTemp.dealType=='A'}">
					<fmt:formatNumber value="${fiFundbookTemp.money}" type="number"
						pattern="###,###,##0.00" />
				</c:if>
				<c:if test="${fiFundbookTemp.dealType=='D'}">0.00</c:if>
			</ec:column>
			<ec:column property="money" title="fibankbooktemp.dealtype.D"
				styleClass="numberColumn">
				<c:if test="${fiFundbookTemp.dealType=='A'}">0.00</c:if>
				<c:if test="${fiFundbookTemp.dealType=='D'}">
					<fmt:formatNumber value="${fiFundbookTemp.money}" type="number"
						pattern="###,###,##0.00" />
				</c:if>
			</ec:column>
			<ec:column property="status" title="fiBankbookTemp.status"
				style="white-space:nowrap;">
				<jecs:code listCode="fiagentpayadvice.status"
					value="${fiFundbookTemp.status}" />
			</ec:column>
			<ec:column property="createTime" title="pd.createTime">
			${fiFundbookTemp.createrName}<br><fmt:formatDate value="${fiFundbookTemp.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
		</ec:column>
			<ec:column property="checkerName" title="pd.confirmUserNo"
				style="white-space:nowrap;" />
			<ec:column property="checkeTime" title="fiBankbookTemp.checkeTime" format="yyyy-MM-dd HH:mm:ss" cell="date" />
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
	theForm.strAction.value="doNotDealFiFundbookTempJJ";
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
	theForm.strAction.value="notVerifiedFiFundbookTempJJ";
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
	theForm.strAction.value="verifyFiFundbookTempJJ";
	if(isFormPosted()){
		theForm.submit();
	}
}
</script>