<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiBankbookTempList.title" />
</title>
<content tag="heading">
<jecs:locale key="fiBankbookTempList.heading" />
</content>
<meta name="menu" content="JfiBankbookTempMenu" />
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form action="" method="get" name="jfiBankbookTempSearchForm"
	id="jfiBankbookTempSearchForm">
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
			<jecs:title key="fiBankbookTemp.billpospayNum" />
			<input name="billpospayNum" type="text"
				value="${param.billpospayNum }" size="8" />
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
			<jecs:power powerCode="addFiBankbookTemp">
				<button name="add" class="btn btn_ins" type="button" onclick="location.href='<c:url value="/editJfiBankbookTemp.html"/>?strAction=addFiBankbookTemp'">
					<jecs:locale key="member.new.num"/>
				</button>
			</jecs:power>
			<jecs:power powerCode="importFiBankbookTemp">
				<button name="import" class="btn btn_ins" type="button" onclick="location.href='<c:url value="/importJfiBankbookTemp.html"/>'">
					<jecs:locale key="button.import"/>
				</button>
			</jecs:power>
			<jecs:power powerCode="multiDeleteFiBankbookTemp">
				<button name="delete" class="btn btn_ins" type="button" onclick="doMultiDeleteFiBankbookTemp($('jfiBankbookTempSearchForm'));">
					<jecs:locale key="operation.button.delete"/>
				</button>
			</jecs:power>
		</div>
	</div>
	<input type="hidden" name="strAction" value="listfiBankbookTemps" />
	<input type="hidden" name="strTempIds" value="" />
</form>

<c:set var="footTotalVar">
	<tr>
		<td align="right" class="footer" colspan="8">
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

<ec:table tableId="jfiBankbookTempListTable" items="jfiBankbookTempList"
	var="jfiBankbookTemp" autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	action="${pageContext.request.contextPath}/jfiBankbookTemps.html"
	width="100%" rowsDisplayed="20" sortable="true"
	imagePath="./images/extremetable/*.gif" footer="${footTotalVar}">
	<ec:row highlightRow="false">
		<ec:column alias="selectedId" headerCell="selectAll"
			style="width:5px;">
			<c:if test="${jfiBankbookTemp.status!=2}">
				<input type="checkbox" name="selectedId"
					value="${jfiBankbookTemp.tempId}" />
			</c:if>
		</ec:column>
		<ec:column property="sysUser.userCode"
			title="label.member.or.agent.code">
			${jfiBankbookTemp.sysUser.userCode }
		</ec:column>
		<ec:column property="createTime" title="pd.createTime" width="90"/>
		<ec:column property="notes" title="bdBounsDeduct.summary"
			sortable="false">
			<jecs:substr key="${fn:replace(jfiBankbookTemp.notes, vEnter, '<br>')}" length="15"/>
		</ec:column>
		<ec:column property="description" title="bdBounsDeduct.description"
			sortable="false">
			<jecs:substr key="${fn:replace(jfiBankbookJournal.description, vEnter, '<br>')}" length="15"/>
		</ec:column>
		<ec:column property="billpospayNum"
			title="fiBankbookTemp.billpospayNum">
			${jfiBankbookTemp.billpospayNum }
		</ec:column>
		<ec:column property="moneyType" title="fiBankbookTemp.moneyType">
			<jecs:code listCode="fibankbooktemp.moneytype"
				value="${jfiBankbookTemp.moneyType}" />
		</ec:column>
		<ec:column property="money" title="fibankbooktemp.dealtype.A"
			styleClass="numberColumn" width="30">
			<c:if test="${jfiBankbookTemp.dealType=='A'}">
				<fmt:formatNumber value="${jfiBankbookTemp.money}" type="number"
					pattern="###,###,##0.00" />
			</c:if>
			<c:if test="${jfiBankbookTemp.dealType=='D'}">0.00</c:if>
		</ec:column>
		<ec:column property="money" title="fibankbooktemp.dealtype.D"
			styleClass="numberColumn" width="30">
			<c:if test="${jfiBankbookTemp.dealType=='A'}">0.00</c:if>
			<c:if test="${jfiBankbookTemp.dealType=='D'}">
				<fmt:formatNumber value="${jfiBankbookTemp.money}" type="number"
					pattern="###,###,##0.00" />
			</c:if>
		</ec:column>

		<ec:column property="status" title="fiBankbookTemp.status" width="35">
			<jecs:code listCode="fiagentpayadvice.status"
				value="${jfiBankbookTemp.status}" />
		</ec:column>
		<ec:column property="checkerName" title="pd.confirmUserNo" width="60">
			${jfiBankbookTemp.checkerName }
		</ec:column>
		<ec:column property="1" title="title.operation" sortable="false"
			styleClass="centerColumn" width="30">
			<jecs:power powerCode="editFiBankbookTempJJ">
				<c:if test="${jfiBankbookTemp.status!=2}">
					<a
						href="editJfiBankbookTemp.html?tempId=${jfiBankbookTemp.tempId}&strAction=editFiBankbookTempJJ">
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

function doMultiDeleteFiBankbookTemp(theForm){
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
	theForm.strAction.value="multiDeleteFiBankbookTemp";
	if(isFormPosted()){
		theForm.submit();
	}
}
</script>
