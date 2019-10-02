<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>



<script type="text/javascript">

	function query(){
		//loading('<jecs:locale key="button.loading"/>');
		document.forms.jbdYdRebateForm.elements['ec_eti'].value='';
		document.forms.jbdYdRebateForm.elements['JbdYdRebateListAllotFiListTable_ev'].value='';
		document.forms.jbdYdRebateForm.elements['JbdYdRebateListAllotFiListTable_efn'].value='';
		document.forms.jbdYdRebateForm.submit()
		document.forms.jbdYdRebateForm.submit();
	}
		
		function allotSelectedAll(theForm){
			
			
			if(!confirm("确认执行操作？")){
				return false;
			}
			document.forms.jbdYdRebateForm.elements['ec_eti'].value='';
			theForm.strAction.value="jbdYdRebateAllotFiAll";
			if(isFormPosted()){
				theForm.submit();
			}
		}	
</script>
<title><jecs:locale key="云店返利奖励进电子存折" /></title>
<content tag="heading">
<jecs:locale key="jbdDayCustRecommendList.heading" />
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



<form action="jbdYdRebateAllotFi.html" method="post"
	name="jbdYdRebateForm" id="jbdYdRebateForm">

	<input type="hidden" id="strAction" name="strAction"
		value="jbdYdRebateAllotFi" />
	<input type="hidden" name="strAdviceCodes" id="strAdviceCodes" value="" />
	
	<div class="new_searchBar">
        <jecs:title  key="miMember.memberNo"/>
		<input name="userCode" id="userCode" value="${param.userCode}" cssClass="text medium"/>	
   </div>
   
	<div class="new_searchBar">	
		<jecs:title key="customerFollow.createTime"/>
		<input name="startCalcTime" type="text" value="${param.startCalcTime }"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"  size="12"/>
	</div>
			
			
	<div class="new_searchBar">	
		<jecs:title key="customerFollow.endTime"/>
		<input name="endCalcTime" type="text" value="${param.endCalcTime }" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"  size="12"/>
	</div>
	
	

		<div class="new_searchBar">
			<button name="search" class="btn btn_sele"   onclick="query()">
				<jecs:locale key="operation.button.search"/>
			</button>
			<button name="search" class="btn btn_long" type="button" onclick="allotSelectedAll(document.jbdYdRebateForm)">
				全部进存折
			</button>
		</div>


	</div>






	<ec:table tableId="JbdYdRebateListAllotFiListTable" items="jbdYdRebateLists"
		var="jbdYdRebateList"
		action="${pageContext.request.contextPath}/jbdYdRebateAllotFi.html"
		width="100%" showPagination="false" showStatusBar="true"
		
		form="jbdYdRebateForm"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row highlightRow="false" style="text-align:center">

			
			<ec:exportXls fileName="jbdYdRebateLists.xls"></ec:exportXls>
			<%-- <ec:column property="wweek" title="bdBounsDeduct.wweek" style="text-align:center" /> --%>
			<ec:column property="jmiMember.userCode" title="miMember.memberNo" style="text-align:center" />
			<ec:column property="miUserName" title="bdCalculatingSubDetail.name" style="text-align:center" />
   			<ec:column property="memberLevel" title="miMember.cardType" cell="com.joymain.jecs.util.ectable.EcTableCell" style="text-align:center">
   				<jecs:code listCode="member.level" value="${jbdYdRebateList.memberLevel }"/>
   			</ec:column>
			
			<ec:column property="jmiMember.bank" title="bdSendRecord.openBank" style="text-align:center"/>
			<ec:column property="jmiMember.bankaddress" title="miMember.bcity" style="text-align:center"/>
			<ec:column property="jmiMember.bankbook" title="miMember.bname" style="text-align:center"/>
			<ec:column escapeAutoFormat="true" property="jmiMember.bankcard" title="miMember.bnum" style="text-align:center"/>
			<ec:column property="calcTime" title="JbdYdRebateList.calcTime" style="text-align:center" />
		
			<ec:column property="sendAmount" title="bdSendRecord.remittanceMoney"
				cell="number" format="###,###,##0.00"
				calc="total" calcTitle="poOrder.amtCount" style="text-align:center"/>

		</ec:row>

	</ec:table>
</form>
<script type="text/javascript">

</script>