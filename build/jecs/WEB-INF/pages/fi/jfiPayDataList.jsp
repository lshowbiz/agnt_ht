<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiPayDataList.title" />
</title>
<content tag="heading">
<jecs:locale key="jfiPayDataList.heading" />
</content>
<meta name="menu" content="FiPayDataMenu" />

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form action="jfiPayDatas.html" method="get" name="jfiPayDataSearchForm"
	id="jfiPayDataSearchForm">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="label.member.or.agent.code" />
			<input name="sysUser.userCode" type="text"
			value="${param['sysUser.userCode'] }" size="10" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiPayData.accountCode" />
			<select name="accountCode">
				<option value=""></option>
				<c:forEach items="${jfiPayBanks}" var="payBankVar">
					<option value="${payBankVar.accountCode }"
						<c:if test="${payBankVar.accountCode==param.accountCode }"> selected</c:if>>
						[${payBankVar.accountCode }]${payBankVar.bankName }
					</option>
				</c:forEach>
			</select>
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiBankbookTemp.status" />
			<jecs:list listCode="fiagentpayadvice.status" name="status"
				value="${param.status}" defaultValue="0" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiBankbookJournal.money" />
			<input name="incomeMoney" type="text" value="${param.incomeMoney }"
				size="8" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="comm.busi.deal.transaction.date" />
			<input name="startDealDate" id="startDealDate" type="text" 
				value="${param.startDealDate }" style="cursor: pointer;width:70px;"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			-
			<input name="endDealDate" id="endDealDate" type="text"
				value="${param.endDealDate }" style="cursor: pointer;width:70px;"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiPayData.excerpt" />
			<input name="excerpt" type="text" value="${param.excerpt }" size="14" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiPayData.createNo" />
			<input name="createNo" type="text" value="${param.createNo }"
				size="14" />
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button name="search" class="btn btn_sele" type="submit" >
				<jecs:locale key="operation.button.search"/>
			</button>
			<input type="hidden" name="strAction" value="listFiPayDatas" />
			<jecs:power powerCode="addFiPayData">
				<button name="add" class="btn btn_ins" type="button" onclick="location.href='<c:url value="/editJfiPayData.html"/>?strAction=addFiPayData'">
					<jecs:locale key="member.new.num"/>
				</button>
			</jecs:power>
			<jecs:power powerCode="importFiPayData">
				<button name="import" class="btn btn_ins" type="button" onclick="location.href='<c:url value="/importJfiPayData.html"/>?strAction=importFiPayData';">
					<jecs:locale key="button.import"/>
				</button>
			</jecs:power>
			<jecs:power powerCode="multiDeleteFiPayData">
				<button name="delete" class="btn btn_ins" type="button" onclick="doMultiDeleteFiPayData($('jfiPayDataSearchForm'));">
					<jecs:locale key="operation.button.delete"/>
				</button>
			</jecs:power>
			<input type="hidden" name="strTempIds" value="" />
		</div>
		<!-- 
		<jecs:title key="label.member.or.agent.code" />
		<input name="sysUser.userCode" type="text"
			value="${param['sysUser.userCode'] }" size="10" />
		<jecs:title key="fiPayData.accountCode" />
		<select name="accountCode" style="width: 200px;">
			<option value=""></option>
			<c:forEach items="${jfiPayBanks}" var="payBankVar">
				<option value="${payBankVar.accountCode }"
					<c:if test="${payBankVar.accountCode==param.accountCode }"> selected</c:if>>
					[${payBankVar.accountCode }]${payBankVar.bankName }
				</option>
			</c:forEach>
		</select>
		<jecs:title key="fiBankbookTemp.status" />
		<jecs:list listCode="fiagentpayadvice.status" name="status"
			value="${param.status}" defaultValue="0" showBlankLine="true" />
		<jecs:title key="fiBankbookJournal.money" />
		<input name="incomeMoney" type="text" value="${param.incomeMoney }"
			size="8" />
		<jecs:title key="comm.busi.deal.transaction.date" />
		<input name="startDealDate" id="startDealDate" type="text"
			value="${param.startDealDate }" style="cursor: pointer;"
			class="Wdate"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
		-
		<input name="endDealDate" id="endDealDate" type="text"
			value="${param.endDealDate }" style="cursor: pointer;" class="Wdate"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
		<jecs:title key="fiPayData.excerpt" />
		<input name="excerpt" type="text" value="${param.excerpt }" size="14" />
		<jecs:title key="fiPayData.createNo" />
		<input name="createNo" type="text" value="${param.createNo }"
			size="14" />
		<input name="search" class="button" type="submit"
			value="<jecs:locale key="operation.button.search"/>" />
		<input type="hidden" name="strAction" value="listFiPayDatas" />
		<jecs:power powerCode="addFiPayData">
			<input name="add" class="button"
				onclick="location.href='<c:url value="/editJfiPayData.html"/>?strAction=addFiPayData'"
				type="button" value="<jecs:locale key="member.new.num"/>" />
		</jecs:power>
		<jecs:power powerCode="importFiPayData">
			<input name="import" class="button"
				onclick="location.href='<c:url value="/importJfiPayData.html"/>?strAction=importFiPayData';"
				type="button" value="<jecs:locale key="button.import"/>" />
		</jecs:power>
		<jecs:power powerCode="multiDeleteFiPayData">
			<input name="delete" class="button"
				onclick="doMultiDeleteFiPayData($('jfiPayDataSearchForm'));"
				type="button" value="<jecs:locale key="operation.button.delete"/>" />
		</jecs:power>
		<input type="hidden" name="strTempIds" value="" /> -->
	</div>
</form>

<form action="jfiPayDatas.html" method="get" name="jfiPayDataForm"
	id="jfiPayDataForm">
	<ec:table tableId="jfiPayDataListTable" items="jfiPayDataList"
		var="jfiPayData" autoIncludeParameters="true"
		retrieveRowsCallback="limit" form="jfiPayDataForm"
		action="${pageContext.request.contextPath}/jfiPayDatas.html"
		width="100%" rowsDisplayed="20" sortable="true"
		imagePath="./images/extremetable/*.gif">
		<ec:row>
			<ec:column alias="selectedId" headerCell="selectAll"
				style="width:5px;">
				<c:if test="${jfiPayData.status!=2}">
					<input type="checkbox" name="selectedId"
						value="${jfiPayData.dataId}" />
				</c:if>
			</ec:column>
			<ec:column property="accountCode" title="fiPayData.accountCode" />
			<ec:column property="dealDate"
				title="comm.busi.deal.transaction.date" style="white-space: nowrap;" />
			<ec:column property="incomeMoney" title="fiPayData.incomeMoney"
				styleClass="numberColumn">
				<fmt:formatNumber value="${jfiPayData.incomeMoney}" type="number"
					pattern="###,###,##0.00" />
			</ec:column>
			<ec:column property="excerpt" title="fiPayData.excerpt" />
			<ec:column property="createNo" title="fiPayData.createNo" />
			<ec:column property="adviceCode" title="fiPayData.adviceCode" />
			<ec:column property="checkerName" title="fiPayAdvice.checkerName" />
			<ec:column property="checkTime" title="fiBankbookTemp.checkeTime" />
			<ec:column property="sysUser.userCode"
				title="label.member.or.agent.code">
			${jfiPayData.sysUser.userCode }
		</ec:column>
			<ec:column property="status" title="fiBankbookTemp.status"
				style="white-space: nowrap;">
				<jecs:code listCode="fiagentpayadvice.status"
					value="${jfiPayData.status}" />
			</ec:column>
			<ec:column property="a" title="title.operation" sortable="false"
				style="width:100px;">
				<nobr>
					<jecs:power powerCode="viewFiPayData">
						<a href="jfiViewPayData.html?dataId=${jfiPayData.dataId}"><img
								src="images/newIcons/search_16.gif" border="0" width="16"
								height="16" />
						</a>
					</jecs:power>
					<c:if test="${jfiPayData.status!=2}">
						<jecs:power powerCode="editFiPayData">
							<a
								href="editJfiPayData.html?dataId=${jfiPayData.dataId}&strAction=editFiPayData"><img
									src="images/newIcons/pencil_16.gif" border="0" width="16"
									height="16" />
							</a>
						</jecs:power>
						<jecs:power powerCode="verifyFiPayData">
							<a
								href="verifyJfiPayData.html?dataId=${jfiPayData.dataId}&strAction=verifyFiPayData"><img
									src="images/newIcons/tick_16.gif" border="0" width="16"
									height="16" />
							</a>
						</jecs:power>
					</c:if>
					<c:if test="${jfiPayData.status==2}">
						<jecs:power powerCode="unVerifyFiPayData">
							<a
								href="unVerifyJfiPayData.html?dataId=${jfiPayData.dataId}&strAction=unVerifyFiPayData"><img
									src="images/icons/delete.gif" border="0" width="16" height="16"
									alt="<jecs:locale key="button.not.verified"/>" />
							</a>
						</jecs:power>
					</c:if>
				</nobr>
			</ec:column>
		</ec:row>

	</ec:table>

	<script>

function doMultiDeleteFiPayData(theForm){
	if(!confirm("<jecs:locale key="bdOutWardBank.confirm.delete"/>")){
		return false;
	}
	var elements=document.getElementsByName("selectedId");
	if(elements==undefined){
		alert("<jecs:locale key="please.select.bdBounsDeduct"/>");
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
		alert("<jecs:locale key="please.select.bdBounsDeduct"/>");
		return false;
	}
	theForm.strAction.value="multiDeleteFiPayData";
	if(isFormPosted()){
		theForm.submit();
	}
}
</script>