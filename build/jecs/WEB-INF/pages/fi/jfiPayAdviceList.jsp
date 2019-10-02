<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiPayAdviceList.title" />
</title>
<content tag="heading">
<jecs:locale key="jfiPayAdviceList.heading" />
</content>
<meta name="menu" content="FiPayAdviceMenu" />

<script language="javascript" src="scripts/validate.js"></script>
<!-- 装载日历JS -->
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form action="jfiPayAdvices.html" method="get"
	name="jfiPayAdviceSearchForm" id="jfiPayAdviceSearchForm">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="fiBankbookTemp.status" />
			<jecs:list listCode="fiagentpayadvice.status" name="status"
				value="${param.status}" defaultValue="0" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiPayAdvice.adviceCode" />
			<input name="adviceCode" type="text" value="${param.adviceCode }"
				size="16" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiBankbookJournal.money" />
			<input name="payMoney" type="text" value="${param.payMoney }" size="8" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiPayAdvice.accountCode" />
			<select name="accountCode">
				<option value=""></option>
				<c:forEach items="${fiPayBanks}" var="payBankVar">
					<option value="${payBankVar.accountCode }"
						<c:if test="${payBankVar.accountCode==param.accountCode }"> selected</c:if>>
						${payBankVar.bankName }
					</option>
				</c:forEach>
			</select>
		</div>
		<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			<div class="new_searchBar">
				<jecs:title key="label.member.or.agent.code" />
				<input name="sysUser.userCode" type="text"
					value="${param['sysUser.userCode'] }" size="10" />
			</div>
		</c:if>
		<div class="new_searchBar">
			<jecs:title key="comm.busi.deal.transaction.date" />
			<input name="startPayDate" id="startPayDate" type="text" 
					value="${param.startPayDate }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="endPayDate" id="endPayDate" type="text" 
					value="${param.endPayDate }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<c:if test="${sessionScope.sessionLogin.userType!='C'}">
			<div class="new_searchBar">
				<jecs:title key="fiPayAdvice.dealType" />
				<input name="dealType" type="text" value="${param.dealType }"
					size="14" />
			</div>
		</c:if>

		<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			<div class="new_searchBar">
				<jecs:title key="fiPayAdvice.checkerName" />
				<input name="checkerName" type="text" value="${param.checkerName }"
					size="10" />
			</div>
		</c:if>

		<c:if test="${sessionScope.sessionLogin.userType!='C'}">
			<div class="new_searchBar">
				<jecs:title key="fiPayAdvice.notice" />
				<input name="notice" type="text" value="${param.notice }" size="16" />
			</div>
		</c:if>		
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
			<jecs:power powerCode="addPayAdvice">
				<button name="add" class="btn btn_ins" type="button" onclick="location.href='<c:url value="/editJfiPayAdvice.html"/>?strAction=addPayAdvice'">
					<jecs:locale key="member.new.num"/>
				</button>
			</jecs:power>
			<jecs:power powerCode="doNotDealPayAdvice">
				<button name="notDeal" class="btn btn_ins" type="button" onclick="doNotDeal($('jfiPayAdviceSearchForm'))">
					<jecs:locale key="button.not.deal"/>
				</button>
			</jecs:power>
			<jecs:power powerCode="notVerifiedPayAdvice">
				<button name="detV" class="btn btn_ins" type="button" onclick="notVerified($('jfiPayAdviceSearchForm'))">
					<jecs:locale key="button.not.verified"/>
				</button>
			</jecs:power>
		</div>
		<input type="hidden" name="strAdviceCodes" id="strAdviceCodes"
			value="" />
		<input type="hidden" name="strAction" value="listFiPayAdvices" />
	</div>
</form>

<form action="jfiPayAdvices.html" method="get" name="jfiPayAdviceForm"
	id="jfiPayAdviceForm">
	<%
request.setAttribute("vEnter", "\n");
%>
	<ec:table tableId="jfiPayAdviceListTable" items="jfiPayAdviceList"
		var="jfiPayAdvice" autoIncludeParameters="true"
		retrieveRowsCallback="limit" form="jfiPayAdviceForm"
		action="${pageContext.request.contextPath}/jfiPayAdvices.html"
		width="100%" rowsDisplayed="20" sortable="true"
		imagePath="./images/extremetable/*.gif">
		<ec:row highlightRow="false" onclick="showTR($('tr${ROWCOUNT}'));">
			<ec:column alias="selectedId" headerCell="selectAll"
				style="width:5px;">
				<c:if test="${jfiPayAdvice.status!=2 && jfiPayAdvice.status!=4}">
					<input type="checkbox" name="selectedId"
						value="${jfiPayAdvice.adviceCode}" class="checkbox" />
				</c:if>
				<c:if test="${jfiPayAdvice.status==2 || jfiPayAdvice.status==4}">
			&nbsp;
			</c:if>
			</ec:column>
			<ec:column property="createTime" title="pd.createTime" />
			<ec:column property="adviceCode" title="fiPayAdvice.adviceCode" />
			<c:if test="${sessionScope.sessionLogin.userType=='C'}">
				<ec:column property="sysUser.userCode" title="pd.agentormember">
				${jfiPayAdvice.sysUser.userCode }
			</ec:column>
			</c:if>
			<ec:column property="accountCode" title="fiPayAdvice.accountCode" />
			<ec:column property="payDate" title="comm.busi.deal.transaction.date"
				style="white-space: nowrap;" />
			<ec:column property="payMoney" title="fiBankbookJournal.money">
				<fmt:formatNumber pattern="#,##0.00"
					value="${jfiPayAdvice.payMoney }" />
			</ec:column>
			<ec:column property="telephone" title="fiPayAdvice.telephone" />
			<ec:column property="status" title="fiBankbookTemp.status"
				style="white-space: nowrap;">
				<jecs:code listCode="fiagentpayadvice.status"
					value="${jfiPayAdvice.status}" />
			</ec:column>
			<c:if test="${sessionScope.sessionLogin.userType=='C'}">
				<ec:column property="checkerName" title="fiPayAdvice.checkerName"
					style="white-space: nowrap;" />
			</c:if>
			<ec:column property="1" title="title.operation" sortable="false"
				style="width:100px;">
				<jecs:power powerCode="viewFiPayAdvice">
					<a
						href="jfiViewPayAdvice.html?strAction=viewFiPayAdvice&adviceCode=${jfiPayAdvice.adviceCode}">
						<img src="images/newIcons/search_16.gif" border="0" width="16"
							height="16" />
					</a>
				</jecs:power>
				<jecs:power powerCode="editPayAdvice">
					<c:if
						test="${jfiPayAdvice.status!=2 && jfiPayAdvice.status!=4 && jfiPayAdvice.companyCode!='CN'}">
						<a
							href="editJfiPayAdvice.html?adviceCode=${jfiPayAdvice.adviceCode}&strAction=editPayAdvice">
							<img src="images/newIcons/pencil_16.gif" border="0" width="16"
								height="16" />
						</a>
					</c:if>
				</jecs:power>
			</ec:column>
		</ec:row>
		<c:if test="${ROWCOUNT>0}">
			<c:if test="${ROWCOUNT%2!=0}">
				<tr id="tr${ROWCOUNT}" class="odddetail">
			</c:if>
			<c:if test="${ROWCOUNT%2==0}">
				<tr id="tr${ROWCOUNT}" class="evendetail">
			</c:if>
			<td>
				&nbsp;
			</td>
			<td align="right" valign="top">
				<jecs:locale key="busi.common.remark" />
			</td>
			<c:if test="${sessionScope.sessionLogin.userType=='C'}">
				<td colspan="11">
					${fn:replace(jfiPayAdvice.remark, vEnter, '<br>')}
				</td>
			</c:if>
			<c:if test="${sessionScope.sessionLogin.userType!='C'}">
				<td colspan="9">
					${fn:replace(jfiPayAdvice.remark, vEnter, '<br>')}
				</td>
			</c:if>
			</tr>
		</c:if>

	</ec:table>

	<c:if test="${sessionScope.sessionLogin.userType!='C'}">
		<table class='detail' width="100%">
			<tr>
				<th>
					<label>
						<jecs:locale key="fiPayAdvice.input" />
						:
					</label>
				</th>
				<td align="left">
					<jecs:locale key="fiPayAdvice.input.notice2" />
				</td>
			</tr>
		</table>
	</c:if>
</form>

<script type="text/javascript">

function editFiPayAdvice(adviceCode){
	<jecs:power powerCode="editPayAdvice">
	window.location="editJfiPayAdvice.html?adviceCode="+adviceCode+"&strAction=editPayAdvice";
	</jecs:power>
}

function selectAll(theForm,status){
	var elements=document.getElementsByName("selectedId");
	if(elements!=undefined){
		for(var i=0;i<elements.length;i++){
			elements[i].checked=status;
		}
	}
}

function doNotDeal(theForm){
	if(!confirm("<jecs:locale key="jfiPayAdvice.confirm.doNotDeal"/>")){
		return false;
	}
	var elements=document.getElementsByName("selectedId");
	if(elements==undefined){
		alert("<jecs:locale key="please.select.payAdvice"/>");
		return false;
	}
	var selectedOne=false;
	for(var i=0;i<elements.length;i++){
		if(elements[i].checked){
			selectedOne=true;
			theForm.strAdviceCodes.value+=","+elements[i].value;
		}
	}
	
	if(!selectedOne){
		alert("<jecs:locale key="please.select.payAdvice"/>");
		return false;
	}
	theForm.strAction.value="doNotDealPayAdvice";
	theForm.submit();
}

function notVerified(theForm){
	if(!confirm("<jecs:locale key="fiPayAdvice.confirm.notVerified"/>")){
		return false;
	}
	var elements=document.getElementsByName("selectedId");
	if(elements==undefined){
		alert("<jecs:locale key="please.select.payAdvice"/>");
		return false;
	}
	var selectedOne=false;
	for(var i=0;i<elements.length;i++){
		if(elements[i].checked){
			selectedOne=true;
			theForm.strAdviceCodes.value+=","+elements[i].value;
		}
	}
	
	if(!selectedOne){
		alert("<jecs:locale key="please.select.payAdvice"/>");
		return false;
	}
	theForm.strAction.value="notVerifiedPayAdvice";
	theForm.submit();
}
</script>