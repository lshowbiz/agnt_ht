<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiFundbookTempList.title" />
</title>
<content tag="heading">
<jecs:locale key="fiFundbookTempList.heading" />
</content>
<meta name="menu" content="FiFundbookTempMenu" />

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form action="" method="get" name="fiFundbookTempSearchForm"
	id="fiFundbookTempSearchForm">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="label.member.or.agent.code" />
			<input name="userCode" type="text" value="${param['userCode'] }"
				size="10" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiBankbookTemp.moneyType" />
			<jecs:list listCode="fifundbooktemp.moneytype" name="moneyType"
				value="${param.moneyType}" defaultValue="0" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="pd.confirmUserNo" />
			<input name="checkerName" type="text" value="${param.checkerName }"
				size="12" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiBankbookTemp.bankbookType" />
			<jecs:list listCode="fifundbook.bankbooktype" name="bankbookType"
				id="bankbookType" value="${param.bankbookType}" defaultValue="1" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiBankbookTemp.status" />
			<jecs:list listCode="fiagentpayadvice.status" name="status"
				value="${param.status}" defaultValue="0" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="miMember.createTimeRange" />
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
				size="8" />
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
			<jecs:power powerCode="addFiFundbookTempJJ">
				<button name="add" class="btn btn_ins" type="button" onclick="location.href='<c:url value="/editFiFundbookTemp.html"/>?strAction=addFiFundbookTempJJ'">
					<jecs:locale key="member.new.num"/>
				</button>
			</jecs:power>
			<jecs:power powerCode="importFiFundbookTempJJ">
				<button name="import" class="btn btn_ins" type="button" onclick="location.href='<c:url value="/importFiFundbookTemp.html"/>'">
					<jecs:locale key="button.import"/>
				</button>
			</jecs:power>
			<jecs:power powerCode="multiDeleteFiFundbookTempJJ">
				<button name="delete" class="btn btn_ins" type="button" onclick="doMultiDeleteFiFundbookTemp($('fiFundbookTempSearchForm'));">
					<jecs:locale key="operation.button.delete"/>
				</button>
			</jecs:power>
		</div>
	</div>
	<input type="hidden" name="strAction" value="listfiFundbookTempsJJ" />
	<input type="hidden" name="strTempIds" value="" />
</form>

<c:set var="footTotalVar">
	<tr>
		<td align="right" class="footer" colspan="6">
			<jecs:locale key="report.allTotal" />
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

<ec:table tableId="fiFundbookTempListTable" items="fiFundbookTempList"
	var="fiFundbookTemp" autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	action="${pageContext.request.contextPath}/fiFundbookTemps.html"
	width="100%" rowsDisplayed="20" sortable="true"
	imagePath="./images/extremetable/*.gif" footer="${footTotalVar}">
	<ec:row highlightRow="false">
		<ec:column alias="selectedId" headerCell="selectAll"
			style="width:5px;">
			<c:if test="${fiFundbookTemp.status!=2}">
				<input type="checkbox" name="selectedId"
					value="${fiFundbookTemp.tempId}" />
			</c:if>
		</ec:column>
		<ec:column property="userCode" title="label.member.or.agent.code">
			${fiFundbookTemp.userCode }
		</ec:column>
		<ec:column property="createTime" title="pd.createTime" />
		<ec:column property="notes" title="bdBounsDeduct.summary"
			sortable="false">
			${fn:replace(fiFundbookTemp.notes, vEnter, '<br>')}
		</ec:column>
		<ec:column property="moneyType" title="fiBankbookTemp.moneyType">
			<jecs:code listCode="fifundbooktemp.moneytype"
				value="${fiFundbookTemp.moneyType}" />
		</ec:column>
		<ec:column property="money" title="fibankbooktemp.dealtype.A"
			styleClass="numberColumn" width="60">
			<c:if test="${fiFundbookTemp.dealType=='A'}">
				<fmt:formatNumber value="${fiFundbookTemp.money}" type="number"
					pattern="###,###,##0.00" />
			</c:if>
			<c:if test="${fiFundbookTemp.dealType=='D'}">0.00</c:if>
		</ec:column>
		<ec:column property="money" title="fibankbooktemp.dealtype.D"
			styleClass="numberColumn" width="60">
			<c:if test="${fiFundbookTemp.dealType=='A'}">0.00</c:if>
			<c:if test="${fiFundbookTemp.dealType=='D'}">
				<fmt:formatNumber value="${fiFundbookTemp.money}" type="number"
					pattern="###,###,##0.00" />
			</c:if>
		</ec:column>
		<ec:column property="status" title="fiBankbookTemp.status" width="50">
			<jecs:code listCode="fiagentpayadvice.status"
				value="${fiFundbookTemp.status}" />
		</ec:column>
		<ec:column property="checkerName" title="pd.confirmUserNo" width="100">
			${fiFundbookTemp.checkerName }<br>${fiFundbookTemp.checkeTime }
		</ec:column>
		<ec:column property="1" title="title.operation" sortable="false"
			styleClass="centerColumn" width="100">
			<jecs:power powerCode="editFiFundbookTemp">
				<c:if test="${fiFundbookTemp.status!=2}">
					<a
						href="editFiFundbookTemp.html?tempId=${fiFundbookTemp.tempId}&strAction=editFiFundbookTemp">
						<img src="images/newIcons/pencil_16.gif" border="0" width="16"
							height="16" />
					</a>
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

function doMultiDeleteFiFundbookTemp(theForm){
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
	theForm.strAction.value="multiDeleteFiFundbookTempJJ";
	if(isFormPosted()){
		theForm.submit();
	}
}
</script>



<%-- 
<c:set var="buttons">
		<jecs:power powerCode="addFiFundbookTemp">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editFiFundbookTemp.html"/>?strAction=addFiFundbookTemp'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="fiFundbookTempListTable"
	items="fiFundbookTempList"
	var="fiFundbookTemp"
	action="${pageContext.request.contextPath}/fiFundbookTemps.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editFiFundbookTemp('${fiFundbookTemp.tempId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="companyCode" title="fiFundbookTemp.companyCode" />
    			<ec:column property="userCode" title="fiFundbookTemp.userCode" />
    			<ec:column property="serial" title="fiFundbookTemp.serial" />
    			<ec:column property="dealType" title="fiFundbookTemp.dealType" />
    			<ec:column property="moneyType" title="fiFundbookTemp.moneyType" />
    			<ec:column property="money" title="fiFundbookTemp.money" />
    			<ec:column property="notes" title="fiFundbookTemp.notes" />
    			<ec:column property="status" title="fiFundbookTemp.status" />
    			<ec:column property="createrCode" title="fiFundbookTemp.createrCode" />
    			<ec:column property="createrName" title="fiFundbookTemp.createrName" />
    			<ec:column property="createTime" title="fiFundbookTemp.createTime" />
    			<ec:column property="lastUpdaterCode" title="fiFundbookTemp.lastUpdaterCode" />
    			<ec:column property="lastUpdaterName" title="fiFundbookTemp.lastUpdaterName" />
    			<ec:column property="lastUpdateTime" title="fiFundbookTemp.lastUpdateTime" />
    			<ec:column property="checkerCode" title="fiFundbookTemp.checkerCode" />
    			<ec:column property="checkerName" title="fiFundbookTemp.checkerName" />
    			<ec:column property="checkeTime" title="fiFundbookTemp.checkeTime" />
    			<ec:column property="checkType" title="fiFundbookTemp.checkType" />
    			<ec:column property="dealDate" title="fiFundbookTemp.dealDate" />
    			<ec:column property="checkMsg" title="fiFundbookTemp.checkMsg" />
    			<ec:column property="createNo" title="fiFundbookTemp.createNo" />
    			<ec:column property="bankbookType" title="fiFundbookTemp.bankbookType" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editFiFundbookTemp(tempId){
   		<jecs:power powerCode="editFiFundbookTemp">
					window.location="editFiFundbookTemp.html?strAction=editFiFundbookTemp&tempId="+tempId;
			</jecs:power>
		}

</script>
--%>