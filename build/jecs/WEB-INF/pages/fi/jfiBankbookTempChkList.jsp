<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiBankbookTempList.title" />
</title>
<content tag="heading">
<jecs:locale key="jfiBankbookTempList.heading" />
</content>
<meta name="menu" content="FiBankbookTempMenu" />

<script language="javascript" src="scripts/validate.js"></script>
<!-- è£è½½æ¥åJS -->
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form action="jfiBankbookTempsChk.html" method="get"
	name="jfiBankbookTempSearchForm" id="jfiBankbookTempSearchForm">
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
		</div>
		<input type="hidden" name="strAction" value="listFiBankbookTempsChk" />
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
			<jecs:power powerCode="verifyFiBankbookTemp">
				<button name="verify1" class="btn btn_ins" type="button" onclick="verify($('jfiBankbookTempSearchForm'))">
					<jecs:locale key="button.verify"/>
				</button>
			</jecs:power>
			<jecs:power powerCode="doNotDealFiBankbookTemp">
				<button name="search" class="btn btn_ins" type="button" onclick="doNotDeal($('jfiBankbookTempSearchForm'))">
					<jecs:locale key="button.not.deal"/>
				</button>
			</jecs:power>
			<jecs:power powerCode="notVerifiedFiBankbookTemp">
				<button name="search" class="btn btn_ins" type="button" onclick="notVerified($('jfiBankbookTempSearchForm'))">
					<jecs:locale key="button.not.verified"/>
				</button>
			</jecs:power>
		</div>
		<input type="hidden" name="strTempIds" value="" />
	</div>
</form>

<c:set var="footTotalVar">
	<tr>
		<td align="right" class="footer" colspan="8">
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

<form action="jfiBankbookTempsChk.html" method="get"
	name="jfiBankbookTempForm" id="jfiBankbookTempForm">
	<ec:table tableId="jfiBankbookTempListTable"
		items="jfiBankbookTempList" var="jfiBankbookTemp"
		autoIncludeParameters="true" retrieveRowsCallback="limit"
		form="jfiBankbookTempForm"
		action="${pageContext.request.contextPath}/jfiBankbookTempsChk.html"
		width="100%" rowsDisplayed="500" sortable="true"
		imagePath="./images/extremetable/*.gif" footer="${footTotalVar}">
		<ec:exportXls fileName="jfibankbooktempschk.xls" />
		<ec:row>
			<ec:column alias="selectedId" headerCell="selectAll"
				style="width:5px;" viewsAllowed="html">
				<c:if test="${jfiBankbookTemp.status!=2}">
					<input type="checkbox" name="selectedId"
						value="${jfiBankbookTemp.tempId}" class="checkbox" />
				</c:if>
				<c:if test="${jfiBankbookTemp.status==2}">
			&nbsp;
			</c:if>
			</ec:column>
			<ec:column property="sysUser.userCode"  style="width:90px;"
				title="label.member.or.agent.code">
			${jfiBankbookTemp.sysUser.userCode }
		</ec:column>
			<ec:column property="sysUser.userName" title="会员名称">
			${jfiBankbookTemp.sysUser.userName}
		</ec:column>
			<ec:column property="moneyType" title="fiBankbookTemp.moneyType"
				cell="com.joymain.jecs.util.ectable.EcTableCell">
				<jecs:code listCode="fibankbooktemp.moneytype"
					value="${jfiBankbookTemp.moneyType}" />
			</ec:column>
			<ec:column property="dealType" title="fiBankbookTemp.dealType"
				cell="com.joymain.jecs.util.ectable.EcTableCell">
				<jecs:code listCode="fibankbooktemp.dealtype"
					value="${jfiBankbookTemp.dealType}" />
			</ec:column>
			<ec:column property="notes" title="bdBounsDeduct.summary"
				sortable="false">
			<jecs:substr key="${fn:replace(jfiBankbookTemp.notes, vEnter, '<br>')}" length="8"/>
		</ec:column>

			<ec:column property="description" title="bdBounsDeduct.description"
				sortable="false">
			<jecs:substr key="${fn:replace(jfiBankbookJournal.description, vEnter, '<br>')}" length="8"/>
		</ec:column>
			<ec:column property="billpospayNum"
				title="fiBankbookTemp.billpospayNum">
			${jfiBankbookTemp.billpospayNum }
		</ec:column>
			<ec:column property="addmoney" title="fibankbooktemp.dealtype.A"
				styleClass="numberColumn" style="width:115px;"
				cell="com.joymain.jecs.util.ectable.EcTableCell">
				<c:if test="${jfiBankbookTemp.dealType=='A'}">
					<fmt:formatNumber value="${jfiBankbookTemp.money}" type="number"
						pattern="###,###,##0.00" />
				</c:if>
				<c:if test="${jfiBankbookTemp.dealType=='D'}">0.00</c:if>
			</ec:column>
			<ec:column property="decmoney" title="fibankbooktemp.dealtype.D"
				styleClass="numberColumn"  style="width:115px;"
				cell="com.joymain.jecs.util.ectable.EcTableCell">
				<c:if test="${jfiBankbookTemp.dealType=='A'}">0.00</c:if>
				<c:if test="${jfiBankbookTemp.dealType=='D'}">
					<fmt:formatNumber value="${jfiBankbookTemp.money}" type="number"
						pattern="###,###,##0.00" />
				</c:if>
			</ec:column>
			<ec:column property="status" title="fiBankbookTemp.status"
				style="white-space:nowrap;"
				cell="com.joymain.jecs.util.ectable.EcTableCell">
				<jecs:code listCode="fiagentpayadvice.status"
					value="${jfiBankbookTemp.status}" />
			</ec:column>
			<ec:column property="createTime" title="pd.createTime"
				cell="com.joymain.jecs.util.ectable.EcTableCell" width="130">
				<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${jfiBankbookTemp.createTime}' />
			
		</ec:column>
			<ec:column property="checkerName" title="pd.confirmUserNo"
				style="white-space:nowrap;" />
			<ec:column property="checkeTime" title="fiBankbookTemp.checkeTime"  style="width:130px;">
				<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${jfiBankbookTemp.checkeTime}' />
			</ec:column>
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
	theForm.strAction.value="doNotDealFiBankbookTemp";
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
	theForm.strAction.value="notVerifiedFiBankbookTemp";
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
	theForm.strAction.value="verifyFiBankbookTemp";
	if(isFormPosted()){
		theForm.submit();
	}
}
</script>