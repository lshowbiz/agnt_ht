<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>



<script type="text/javascript">

		function allotSelected(theForm){
			theForm.strAdviceCodes.value='';
		
			if(!confirm("<jecs:locale key="bdSendRecordAllot.confirm.allot"/>")){
				return false;
			}
			var elements=document.getElementsByName("selectedId");
		
			if(elements==undefined){
				alert("<jecs:locale key="please.select.bdSendRegister"/>");
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
				alert("<jecs:locale key="please.select.bdSendRegister"/>");
				return false;
			}
			theForm.strAction.value="allotSelectedFi";
			if(isFormPosted()){
				theForm.submit();
			}
		}
			
		function allotSelectedAll(theForm){
			
			
			if(!confirm("确认执行操作？")){
				return false;
			}
		
			theForm.strAction.value="allotSelectedDevAll";
			if(isFormPosted()){
				theForm.submit();
			}
		}	
</script>
<title><jecs:locale key="jbdSendRecordList.title" /></title>
<content tag="heading">
<jecs:locale key="jbdSendRecordList.heading" />
</content>
<meta name="menu" content="JbdSendRecordMenu" />


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>



<c:if test="${not empty errorList  }">
	<div class="error" id="errorMessages">
		<c:forEach var="error" items="${errorList}">
			<img src="<c:url value="images/newIcons/warning_16.gif"/>"
				alt="<jecs:locale key="icon.warning"/>" class="icon" />
			<c:out value="${error}" escapeXml="false" />
			<br />
		</c:forEach>
	</div>
</c:if>



<form action="jbdSendRecordAllotDev.html" method="post"
	name="bdSendRecordForm1" id="bdSendRecordForm1">

	<input type="hidden" id="strAction" name="strAction"
		value="jbdSendRecordAllotDev" />
	<input type="hidden" name="strAdviceCodes" id="strAdviceCodes" value="" />
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="bdBounsDeduct.wweek" />
			<input name="wweek" type="text" value="${param.wweek }" size="8" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="miMember.memberNo" />
			<input name="userCode" type="text" value="${param.userCode }"
				size="12" />
		</div>
	
	
			<%--<jecs:label key="bdSendRecord.remittanceMoneyScope"/>
		<input name="startAllotMoney" type="text" value="${empty param.startAllotMoney ? startAllotMoney : param.startAllotMoney }" size="12"/> - 
				<input name="endAllotMoney" type="text" value="${param.endAllotMoney }" size="12"/>--%>
	
		<div class="new_searchBar">
			<button name="search" class="btn btn_sele" type="submit" onclick="loading('<jecs:locale key="button.loading"/>');">
				<jecs:locale key="operation.button.search"/>
			</button>
			<button name="search" class="btn btn_long" type="button" onclick="allotSelectedAll(document.bdSendRecordForm1)">
				<jecs:locale key="function.menu.allotSelectedDev.all"/>
			</button>
		</div>


	</div>



</form>




<div id="loading">
	<ec:table tableId="jbdSendRecordListTable" items="jbdSendRecordList"
		var="jbdSendRecord"
		action="${pageContext.request.contextPath}/jbdSendRecordAllotDev.html"
		width="100%" showPagination="false" showStatusBar="true"
		autoIncludeParameters="true" retrieveRowsCallback="limit"
		sortable="true" imagePath="./images/extremetable/*.gif">
		<ec:row highlightRow="false">

			<ec:exportXls fileName="jbdSendRecordAllotDev.xls"></ec:exportXls>

			<%--<ec:column property="1" title="alCharacterKey.select1" sortable="false" styleClass="centerColumn">
						<input type="checkbox" name="selectedId" id="selectedId" value="${jbdSendRecord.id}" class="checkbox" />
				</ec:column>--%>

			<ec:column property="w_week" title="bdBounsDeduct.wweek"
				cell="com.joymain.jecs.util.ectable.EcTableCell">
				<jecs:weekFormat week="${jbdSendRecord.w_week }" weekType="w" />
			</ec:column>
			<ec:column property="user_code" title="miMember.memberNo" />
			<ec:column property="name" title="bdCalculatingSubDetail.name" />
			<ec:column property="card_type" title="miMember.cardType"
				cell="com.joymain.jecs.util.ectable.EcTableCell">
				<jecs:code listCode="bd.cardtype"
					value="${jbdSendRecord.card_type }" />
			</ec:column>

			<ec:column property="current_dev" title="bdSendRecord.currentDev"
				styleClass="numberColumn" cell="number" format="###,###,##0.00"
				calc="total" calcTitle="poOrder.amtCount" />

		</ec:row>

	</ec:table>
</div>
