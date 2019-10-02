<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<title><jecs:locale key="jfiPosImportList.title" />
</title>
<content tag="heading">
<jecs:locale key="jfiPosImportList.heading" />
</content>
<meta name="menu" content="JfiPosImportMenu" />
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>



<form action="jfiPosImports.html" method="get" name="searchForm"
	id="searchForm">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="jfiPosImport.posNo" />
			<input name="posNo" type="text" value="${param.posNo }" size="12" />
		</div>
		<div class="new_searchBar">
			<!--	<jecs:title key="jfiPosImport.moneyType"/>-->
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;到账来源：
			<select name="moneyType" value="${param.moneyType }" defaultValue=""
				showBlankLine="true">
				<option value="" selected="selected"></option>
				<option value="35">
					银商支付
				</option>
				<option value="106">
					畅捷通支付
				</option>
			</select>
		</div>
		<div class="new_searchBar">
			<jecs:title key="miMember.memberNo" />
			<input name="userCode" type="text" value="${param.userCode }"
				size="12" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="poMemberOrder.Times" />
			<input name="startCreateTime" id="startCreateTime" type="text" 
					value="${param.startCreateTime }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="endCreateTime" id="endCreateTime" type="text" 
					value="${param.endCreateTime }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			<jecs:title key="logType.BC" />
			<input name="startCheckTime" id="startCheckTime" type="text" 
					value="${param.startCheckTime }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="endCheckTime" id="endCheckTime" type="text" 
					value="${param.endCheckTime }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			<jecs:title key="comm.busi.deal.transaction.date" />
			<input name="startIncTime" id="startIncTime" type="text" 
					value="${param.startIncTime }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="endIncTime" id="endIncTime" type="text" 
					value="${param.endIncTime }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiBankbookTemp.status" />
			<jecs:list name="status" listCode="pos.status" value="${param.status}"
				defaultValue="0" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="jfi99billLog.inc" />
			<jecs:list name="inc" listCode="yesno" value="${param.inc}"
				defaultValue="" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
			<jecs:power powerCode="jfiPosImportExcel">
				<button name="add" class="btn btn_ins" type="button" onclick="location.href='<c:url value="/jfiPosImportExcel.html?strAction=jfiPosImportExcel"/>'">
					<jecs:locale  key='button.import'/>
				</button>
			</jecs:power>
			<jecs:power powerCode="doCheckPosImport">
				<button name="verify1" class="btn btn_ins" type="button" onclick="verify($('searchForm'))" type="button">
					<jecs:locale key="button.verify"/>
				</button>
			</jecs:power>
			<jecs:power powerCode="doNotCheckPosImport">
				<button name="search" class="btn btn_ins" type="button" onclick="doNotDeal($('searchForm'))">
					<jecs:locale key="button.not.deal"/>
				</button>
			</jecs:power>
			<jecs:power powerCode="checkPosImport">
				<button name="search" class="btn btn_ins" type="button" onclick="notVerified($('searchForm'))">
					<jecs:locale key="button.not.verified"/>
				</button>
			</jecs:power>
			<c:if test="${param.status=='1'}">
				<button name="search" class="btn btn_ins" type="button" onclick="delVerified($('searchForm'))">
					<jecs:locale key="operation.button.delete"/>
				</button>
			</c:if>
		</div>
			<input type="hidden" name="strTempIds" value="" />
			<input type="hidden" name="strAction" value="jfiPosImports" />
	</div>
</form>

<c:set var="footTotalVar">
	<tr>
		<td align="right" class="footer" colspan="2">
			<jecs:locale key="report.allTotal" />
			:
		</td>
		<td align="right" class="footer">
			<b> <c:if test="${not empty sumAmount}">
					<fmt:formatNumber pattern="###,##0.00">${sumAmount}</fmt:formatNumber>
				</c:if> <c:if test="${empty sumAmount}">
			0.00
		</c:if>
			</b>
		</td>
		<td class="footer" colspan="10">
			&nbsp;
		</td>
	</tr>
</c:set>

<form action="jfiPosImports.html" method="get" name="jfiPosImportForm"
	id="jfiPosImportForm">
	<ec:table tableId="jfiPosImportListTable" items="jfiPosImportList"
		var="jfiPosImport" autoIncludeParameters="true"
		retrieveRowsCallback="limit" form="jfiPosImportForm"
		action="${pageContext.request.contextPath}/jfiPosImports.html"
		width="100%" rowsDisplayed="20" sortable="true"
		imagePath="./images/extremetable/*.gif" footer="${footTotalVar}">
		<ec:exportXls fileName="jfiPosImportExp.xls" />
		<ec:row>

			<ec:column alias="selectedId" headerCell="selectAll"
				style="width:5px;">
				<c:if test="${jfiPosImport.inc=='0' && jfiPosImport.status!='4'}">

					<input type="checkbox" name="selectedId" id="selectedId"
						value="${jfiPosImport.jpiId }" class="checkbox" />

				</c:if>
			</ec:column>
			<ec:column property="posNo" title="jfiPosImport.posNo" />
			<ec:column property="amount" title="fiBankbookJournal.money">
				<fmt:formatNumber value="${jfiPosImport.amount}" type="number"
					pattern="###,###,##0.00" />
			</ec:column>
			<ec:column property="pid" title="po.field.serial" />
			<ec:column property="tel" title="login.verifyCode" />
			<ec:column property="status" title="pd.checkstatus"
				styleClass="centerColumn">
				<jecs:code listCode="pos.status" value="${jfiPosImport.status}" />
			</ec:column>
			<ec:column property="createTime" title="创建日期">
				<fmt:formatDate value="${jfiPosImport.createTime }"
					pattern="yyyy-MM-dd HH:mm:ss" />
			</ec:column>
			<ec:column property="createUser" title="aiAgent.createNo" />
			<ec:column property="checkTime" title="logType.BC">
				<fmt:formatDate value="${jfiPosImport.checkTime }"
					pattern="yyyy-MM-dd HH:mm:ss" />
			</ec:column>
			<ec:column property="checkUser" title="busi.user.check" />
			<ec:column property="userCode" title="miMember.memberNo" />
			<ec:column property="inc" title="jfi99billLog.inc"
				styleClass="centerColumn">
				<jecs:code listCode="yesno" value="${jfiPosImport.inc}" />
			</ec:column>
			<ec:column property="incTime" title="进账日期">
				<fmt:formatDate value="${jfiPosImport.incTime }"
					pattern="yyyy-MM-dd HH:mm:ss" />
			</ec:column>
		</ec:row>

	</ec:table>
</form>
<script type="text/javascript">

   function editJfiPosImport(jpiId){
   		<jecs:power powerCode="editJfiPosImport">
					window.location="editJfiPosImport.html?strAction=editJfiPosImport&jpiId="+jpiId;
			</jecs:power>
		}

</script>


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
	theForm.strAction.value="doNotCheckPosImport";
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
	theForm.strAction.value="checkPosImport";
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
	theForm.strAction.value="doCheckPosImport";
	if(isFormPosted()){
		theForm.submit();
	}
}

function delVerified(theForm){
	if(!confirm("<jecs:locale key="amMessage.confirmdelete"/>")){
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
	theForm.strAction.value="deletePosImport";
	if(isFormPosted()){
		theForm.submit();
	}
}
</script>