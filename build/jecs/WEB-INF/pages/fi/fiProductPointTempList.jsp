<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiProductPointTempList.title" /></title>
<content tag="heading">
<jecs:locale key="fiProductPointTempList.heading" />
</content>
<meta name="menu" content="FiBankbookTempMenu" />
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form action="" method="get" name="fiProductPointTempSearchForm"
	id="fiProductPointTempSearchForm">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="label.member.or.agent.code" />
			<input name="sysUser.userCode" type="text"
				value="${param['sysUser.userCode'] }" size="10" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiBankbookTemp.moneyType" />
			<jecs:list listCode="fibankbooktemp.moneytype" name="moneyType"
				value="${param.moneyType}" defaultValue="0" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="pd.confirmUserNo" />
			<input name="checkerName" type="text" value="${param.checkerName }"
				size="12" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiProductPointTemp.bankbookType" />
			<jecs:list listCode="fiproductpointtemp.productpointtype" name="productPointType"
				id="productPointType" value="${param.productPointType}" defaultValue="1" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiBankbookTemp.status" />
			<jecs:list listCode="fiagentpayadvice.status" name="status"
				value="${param.status}" defaultValue="0" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiPayData.createrName" />
			<input name="createrName" type="text" value="${param.createrName }"
				size="8" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="miMember.createTimeRange.start" />
			<input name="startCreateTime" id="startCreateTime" type="text" 
					value="${param.startCreateTime }" style="cursor: pointer;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			<jecs:title key="miMember.createTimeRange.end" />
			<input name="endCreateTime" id="endCreateTime" type="text" 
					value="${param.endCreateTime }" style="cursor: pointer;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
			<jecs:power powerCode="addFiProductPointTempJJ">
				<button name="add" class="btn btn_ins" type="button"
					onclick="location.href='<c:url value="/editFiProductPointTemp.html"/>?strAction=addFiProductPointTempJJ'">
					<jecs:locale key="member.new.num"/>
				</button>
			</jecs:power>
			<jecs:power powerCode="multiDeleteFiProductPointTempJJ">
				<button name="delete" class="btn btn_ins" type="button"
					onclick="doMultiDeleteFiProductPointTemp($('fiProductPointTempSearchForm'));">
					<jecs:locale key="operation.button.delete"/>
				</button>
			</jecs:power>
			<jecs:power powerCode="importFiProductPointTempJJ">
				<button name="import" class="btn btn_ins" type="button"
					onclick="location.href='<c:url value="/importFiProductPointTemp.html"/>'">
					<jecs:locale key="button.import"/>
				</button>
			</jecs:power>
		</div>
	</div>
	<input type="hidden" name="strAction" value="listfiProductPointTempsJJ" />
	<input type="hidden" name="strTempIds" value="" />
</form>

<c:set var="footTotalVar">
	<tr>
		<td align="right" class="footer" colspan="7">
			<jecs:locale key="report.allTotal" />
		</td>
		<td align="right" class="footer">
			<b> <c:if test="${not empty incExpMap.incTotal}">
					<fmt:formatNumber pattern="###,##0.00">${incExpMap.incTotal}</fmt:formatNumber>
				</c:if> <c:if test="${empty incExpMap.incTotal}">
			0.00
		</c:if> </b>
		</td>
		<td align="right" class="footer">
			<b> <c:if test="${not empty incExpMap.expTotal}">
					<fmt:formatNumber pattern="###,##0.00">${incExpMap.expTotal}</fmt:formatNumber>
				</c:if> <c:if test="${empty incExpMap.expTotal}">
			0.00
		</c:if> </b>
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

<ec:table tableId="fiProductPointTempListTable" items="fiProductPointTempList"
	var="fiProductPointTemp" autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	action="${pageContext.request.contextPath}/fiProductPointTemps.html"
	width="100%" rowsDisplayed="20" sortable="true"
	imagePath="./images/extremetable/*.gif" footer="${footTotalVar}">
	<ec:row highlightRow="false">
		<ec:column alias="selectedId" headerCell="selectAll"
			style="width:5px;">
			<c:if test="${fiProductPointTemp.status!=2}">
				<input type="checkbox" name="selectedId"
					value="${fiProductPointTemp.tempId}" />
			</c:if>
		</ec:column>
		<ec:column property="sysUser.userCode"
			title="label.member.or.agent.code">
			${fiProductPointTemp.sysUser.userCode }
		</ec:column>
		<ec:column property="createTime" title="pd.createTime" >
			<fmt:formatDate value="${fiProductPointTemp.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
		</ec:column>
		<ec:column property="notes" title="bdBounsDeduct.summary"
			sortable="false">
			<jecs:substr key="${fiProductPointTemp.notes }" length="10"/> 
		</ec:column>
		<ec:column property="description" title="bdBounsDeduct.description"
			sortable="false">
			<jecs:substr key="${fiProductPointTemp.description }" length="10"/> 
		</ec:column>
		<ec:column property="moneyType" title="fiBankbookTemp.moneyType">
			<jecs:code listCode="fibankbooktemp.moneytype"
				value="${fiProductPointTemp.moneyType}" />
		</ec:column>
		<ec:column property="money" title="fibankbooktemp.dealtype.A"
			styleClass="numberColumn" width="30">
			<c:if test="${fiProductPointTemp.dealType=='A'}">
				<fmt:formatNumber value="${fiProductPointTemp.money}" type="number"
					pattern="###,###,##0.00" />
			</c:if>
			<c:if test="${fiProductPointTemp.dealType=='D'}">0.00</c:if>
		</ec:column>
		<ec:column property="money" title="fibankbooktemp.dealtype.D"
			styleClass="numberColumn" width="30">
			<c:if test="${fiProductPointTemp.dealType=='A'}">0.00</c:if>
			<c:if test="${fiProductPointTemp.dealType=='D'}">
				<fmt:formatNumber value="${fiProductPointTemp.money}" type="number"
					pattern="###,###,##0.00" />
			</c:if>
		</ec:column>
		<ec:column property="status" title="fiBankbookTemp.status" width="40">
			<jecs:code listCode="fiagentpayadvice.status"
				value="${fiProductPointTemp.status}" />
		</ec:column>
		<ec:column property="checkerName" title="pd.confirmUserNo" width="100">
			<font color="green">${fiProductPointTemp.checkerName }</font>&nbsp;<fmt:formatDate value="${fiProductPointTemp.checkeTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
		</ec:column>
		<ec:column property="1" title="title.operation" sortable="false"
			styleClass="centerColumn" width="30">
			<jecs:power powerCode="editFiProductPointTemp">
				<c:if test="${fiProductPointTemp.status!=2}">
					<a
						href="editFiProductPointTemp.html?tempId=${fiProductPointTemp.tempId}&strAction=editFiProductPointTempJJ">
						<img src="images/newIcons/pencil_16.gif" border="0" width="16"
							height="16" /> </a>
				</c:if>
			</jecs:power>
			&nbsp;
		</ec:column>
	</ec:row>

</ec:table>

<script>
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

function doMultiDeleteFiProductPointTemp(theForm){
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
	theForm.strAction.value="multiDeleteFiProductPointTempJJ";
	if(isFormPosted()){
		theForm.submit();
	}
}
</script>
