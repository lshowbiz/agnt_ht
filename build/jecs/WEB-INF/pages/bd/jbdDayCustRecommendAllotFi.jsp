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
		
			theForm.strAction.value="jbdDayCustRecommendAllotFiAll";
			if(isFormPosted()){
				theForm.submit();
			}
		}	
</script>
<title><jecs:locale key="顾问推荐奖励进电子存折" /></title>
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



<form action="jbdDayCustRecommendAllotFi.html" method="post"
	name="bdSendRecordForm1" id="bdSendRecordForm1">

	<input type="hidden" id="strAction" name="strAction"
		value="jbdDayCustRecommendAllotFi" />
	<input type="hidden" name="strAdviceCodes" id="strAdviceCodes" value="" />
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="bdBounsDeduct.wweek" />
			<input name=startCreateTime type="text" value="${param.startCreateTime }" onclick="WdatePicker({dateFmt:'yyyyMMdd',readOnly:true})"  size="12"/> -
		</div>
		<div class="new_searchBar">
			<%-- <jecs:title key="bdBounsDeduct.wweek" /> --%>
			<input name="endCreateTime" type="text" value="${param.endCreateTime }" onclick="WdatePicker({dateFmt:'yyyyMMdd',readOnly:true})"  size="12"/>
		</div>
		<div class="new_searchBar">
			<jecs:title key="miMember.memberNo" />
			<input name="userCode" type="text" value="${param.userCode }"
				size="12" />
		</div>
	
	

		<div class="new_searchBar">
			<button name="search" class="btn btn_sele" type="submit" onclick="loading('<jecs:locale key="button.loading"/>');">
				<jecs:locale key="operation.button.search"/>
			</button>
			<button name="search" class="btn btn_long" type="button" onclick="allotSelectedAll(document.bdSendRecordForm1)">
				全部进存折
			</button>
		</div>


	</div>






	<ec:table tableId="JbdDayCustRecommendAllotFiListTable" items="JbdDayCustRecommendAllotFiList"
		var="JbdDayCustRecommendAllotFi"
		action="${pageContext.request.contextPath}/jbdDayCustRecommendAllotFi.html"
		width="100%" showPagination="false" showStatusBar="true"
		method="post"
		form="bdSendRecordForm1"
		sortable="true" imagePath="./images/extremetable/*.gif">
		<ec:row highlightRow="false" style="text-align:center">

			
			<ec:exportXls fileName="jbdDayCustRecommendAllotFi.xls"></ec:exportXls>
			<ec:column property="wweek" title="bdBounsDeduct.wweek" style="text-align:center" />
			<ec:column property="jmiMember.userCode" title="miMember.memberNo" style="text-align:center" />
			<ec:column property="userName" title="bdCalculatingSubDetail.name" style="text-align:center" />
   			<ec:column property="memberLevel" title="级别" cell="com.joymain.jecs.util.ectable.EcTableCell" style="text-align:center">
   				<jecs:code listCode="member.level" value="${JbdDayCustRecommendAllotFi.memberLevel }"/>
   			</ec:column>
			
			<ec:column property="jmiMember.bank" title="银行" style="text-align:center"/>
			<ec:column property="jmiMember.bankaddress" title="开户支行" style="text-align:center"/>
			<ec:column property="jmiMember.bankbook" title="银行户名" style="text-align:center"/>
			<ec:column property="jmiMember.bankcard" title="银行帐号" style="text-align:center"/>
			<ec:column property="comDate" title="结算时间" style="text-align:center" />
		
			<ec:column property="sendMoney" title="汇款金额"
				styleClass="numberColumn" cell="number" format="###,###,##0.00"
				calc="total" calcTitle="poOrder.amtCount" style="text-align:center"/>

		</ec:row>

	</ec:table>
</form>