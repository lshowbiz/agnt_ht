<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>

<script type="text/javascript">
	function query(){
		//loading('<jecs:locale key="button.loading"/>');
		document.forms.jbdYd399RecommendForm.elements['ec_eti'].value='';
		document.forms.jbdYd399RecommendForm.elements['JbdYd399RecommendListAllotFiListTable_ev'].value='';
		document.forms.jbdYd399RecommendForm.elements['JbdYd399RecommendListAllotFiListTable_efn'].value='';
		document.forms.jbdYd399RecommendForm.submit()
		document.forms.jbdYd399RecommendForm.submit();
	}
		
		function allotSelectedAll(theForm){
			if(!confirm("确认执行操作？")){
				return false;
			}
			document.forms.jbdYd399RecommendForm.elements['ec_eti'].value='';
			theForm.strAction.value="jbdYd399RecommendListAllotFiAll";
			if(isFormPosted()){
				theForm.submit();
			}
		}	
</script>
<title><jecs:locale key="399店主推荐奖励进电子存折" /></title>
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

<form action="jbdYd399RecommendListAllotFi.html" method="post"
	name="jbdYd399RecommendForm" id="jbdYd399RecommendForm">

	<input type="hidden" id="strAction" name="strAction"
		value="jbdYd399RecommendListAllotFi" />
	<input type="hidden" name="strAdviceCodes" id="strAdviceCodes" value="" />
	
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="common.startTime" />
			<input name=startCreateTime type="text" value="${param.startCreateTime }" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" /> -
		</div>
		<div class="new_searchBar">
			<jecs:title key="customerFollow.endTime" />
			<input name="endCreateTime" type="text" value="${param.endCreateTime }" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="miMember.memberNo" />
			<input name="userCode" type="text" value="${param.userCode }"
				size="12" />
		</div>
	
		<div class="new_searchBar">
			<button name="search" class="btn btn_sele"   onclick="query()">
				<jecs:locale key="operation.button.search"/>
			</button>
			<button name="search" class="btn btn_long" type="button" onclick="allotSelectedAll(document.jbdYd399RecommendForm)">
				全部进存折
			</button>
		</div>
	</div>

	<ec:table tableId="JbdYd399RecommendListAllotFiListTable" items="JbdYd399RecommendListAllotFiList"
		var="JbdYd399RecommendListAllotFi"
		action="${pageContext.request.contextPath}/jbdYd399RecommendListAllotFi.html"
		width="100%" showPagination="false" showStatusBar="true"
		method="post"
		form="jbdYd399RecommendForm"
		sortable="true" imagePath="./images/extremetable/*.gif">
		<ec:row highlightRow="false" style="text-align:center">
			<ec:exportXls fileName="jbdYd399RecommendListAllotFi.xls" ></ec:exportXls>
			<ec:column property="calcTime" title="JbdYdRebateList.calcTime" style="text-align:center" />
			<ec:column property="jmiMember.userCode" title="miMember.memberNo" style="text-align:center" />
			<ec:column property="miUserName" title="bdCalculatingSubDetail.name" style="text-align:center" />
   			<ec:column property="memberLevel" title="级别" cell="com.joymain.jecs.util.ectable.EcTableCell" style="text-align:center">
   				<jecs:code listCode="member.level" value="${JbdYd399RecommendListAllotFi.memberLevel }"/>
   			</ec:column>
			<ec:column property="jmiMember.bank" title="银行" style="text-align:center"/>
			<ec:column property="jmiMember.bankaddress" title="开户支行" style="text-align:center"/>
			<ec:column property="jmiMember.bankbook" title="银行户名" style="text-align:center"/>
			<ec:column property="jmiMember.bankcard" title="银行帐号" style="text-align:center" escapeAutoFormat="true"/>
			<ec:column property="sendAmount" title="汇款金额"
				styleClass="numberColumn" cell="number" format="###,###,##0.00"
				calc="total" calcTitle="poOrder.amtCount" style="text-align:center"/>
		</ec:row>
	</ec:table>
</form>
