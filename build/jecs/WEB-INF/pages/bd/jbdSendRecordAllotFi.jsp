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
		
			theForm.strAction.value="allotSelectedFiAll";
			if(isFormPosted()){
				theForm.submit();
			}
		}	
</script>
<title><jecs:locale key="jbdSendRecordList.title" />
</title>
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



<form action="jbdSendRecordAllotFi.html" method="post"
	name="bdSendRecordForm1" id="bdSendRecordForm1">

	<input type="hidden" id="strAction" name="strAction"
		value="jbdSendRecordAllotFi" />
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
			<%--<jecs:label key="bdSendRecord.openBank"/>
				<select name="bank">
				       <option value="">
						<jecs:locale key="list.please.select"/>
						</option>
			       	<c:forEach items="${sysBanks }" var="bank">
	
			       		<c:if test="${param.bank==bank.bankKey }">
			       			<option value="${bank.bankKey }" selected="selected"><jecs:locale key="${bank.bankValue }"/></option>        	
			       		</c:if>
			       		<c:if test="${param.bank!=bank.bankKey}">
			       			<option value="${bank.bankKey }"><jecs:locale key="${bank.bankValue }"/></option>  
			       		</c:if>
			       	</c:forEach>
	        	</select>--%>
		<div class="new_searchBar">
			<jecs:title key="bdSendRecord.remittanceMoneyScope" />
			<input name="startAllotMoney" type="text"
				value="${empty param.startAllotMoney ? startAllotMoney : param.startAllotMoney }"
				size="12" style="width: 75px;"/>
			-
			<input name="endAllotMoney" type="text"
				value="${param.endAllotMoney }" size="12"  style="width: 75px;"/>
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit" onclick="loading('<jecs:locale key="button.loading"/>');">
				<jecs:locale key="operation.button.search"/>
			</button>
			<button name="search" type="button" class="btn btn_ins" onclick="allotSelected(document.bdSendRecordForm1)">
				<jecs:locale key="function.menu.allotSelectedNote"/>
			</button>
			<button name="search" type="button" class="btn btn_ins" onclick="allotSelectedAll(document.bdSendRecordForm1)">
				<jecs:locale key="function.menu.allotSelectedNote.all"/>
			</button>
		</div>



	</div>



</form>

<%--<c:set var="footTotalVar">
<tr id="aaa">
	<td id="frontTd" align="right" class="footer" colspan="9"><jecs:locale key="bdSendRecord.totalRemittanceMoneyTotal"/></td>
	<td class="footer" align="right">
	<b>
<fmt:formatNumber value="${sumMoney }" pattern="###,###,##0.00"></fmt:formatNumber>
</b>
</td>
</tr>
</c:set>--%>



<div id="loading">
	<ec:table tableId="jbdSendRecordListTable" items="jbdSendRecordList"
		var="jbdSendRecord"
		autoIncludeParameters="true"
		action="${pageContext.request.contextPath}/jbdSendRecordAllotFi.html"
		width="100%" showPagination="false" showStatusBar="true"
		sortable="true" imagePath="./images/extremetable/*.gif"
		footer="${footTotalVar}">
		<ec:row highlightRow="false">


			<ec:column property="1" title="alCharacterKey.select1"
				sortable="false" styleClass="centerColumn">
				<input type="checkbox" name="selectedId" id="selectedId"
					value="${jbdSendRecord.id}" class="checkbox" />
			</ec:column>

			<ec:column property="w_week" title="bdBounsDeduct.wweek">
				<jecs:weekFormat week="${jbdSendRecord.w_week }" weekType="w" />
			</ec:column>
			<ec:column property="user_code" title="miMember.memberNo" />
			<ec:column property="name" title="bdCalculatingSubDetail.name" />
			<ec:column property="card_type" title="miMember.cardType">
				<jecs:code listCode="bd.cardtype"
					value="${jbdSendRecord.card_type }" />
			</ec:column>
			<ec:column property="bank" title="bdSendRecord.openBank" />
			<ec:column property="bankaddress" title="miMember.bcity" />
			<ec:column property="bankbook" title="miMember.bname" />
			<ec:column property="bankcard" title="miMember.bnum" />
			<ec:column property="remittance_money"
				title="bdSendRecord.remittanceMoney" styleClass="numberColumn"
				cell="number" format="###,###,##0.00" calc="total"
				calcTitle="poOrder.amtCount" />


		</ec:row>

	</ec:table>
</div>

