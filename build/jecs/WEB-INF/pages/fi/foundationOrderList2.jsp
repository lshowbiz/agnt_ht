<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="foundationOrderList.title" />
</title>
<content tag="heading">
<jecs:locale key="foundationOrderList.heading" />
</content>
<meta name="menu" content="FoundationOrderMenu" />
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./js/jquery-1.4.2.min.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<c:set var="buttons">
	<!--<jecs:power powerCode="addFoundationOrder">
		<input type="button" class="button" style="margin-right: 5px"
			onclick="location.href='<c:url value="/editFoundationOrder.html"/>?strAction=addFoundationOrder'"
			value="<jecs:locale key="button.add"/>" />
	</jecs:power>-->
</c:set>


<form name="frm" id="frm"
	action="<c:url value='/foundationOrders.html'/>">
	<ec:table tableId="foundationOrderListTable" items="foundationOrderList"
		var="foundationOrder"
		action="${pageContext.request.contextPath}/foundationOrders.html"
		width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
		sortable="true" imagePath="./images/extremetable/*.gif">
		<ec:row>
			<ec:column property="jmiMember.userCode" title="foundationOrder.userCode" />
			<ec:column property="userName" title="sys.user.username">
				${ foundationOrder.jmiMember.firstName }${foundationOrder.jmiMember.lastName }
			</ec:column>
			<ec:column property="jmiMember.mobiletele" title="alCompany.phone" />
			<ec:column property="foundationItem.name" title="foundationItem.name" />
			<ec:column property="foundationItem.amount" title="foundationItem.amount" />
			<ec:column property="fiNum" title="foundationOrder.fiNum" />
			
			<ec:column property="status" title="foundationOrder.status">
				<jecs:code listCode="foundationpayresult"
					value="${foundationOrder.status}" />
			</ec:column> 
			<ec:column property="amount" title="foundationOrder.amount" />
			<ec:column property="createTime" title="foundationOrder.createTime" style="white-space:nowrap;">
				<fmt:formatDate value="${foundationOrder.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
			</ec:column>
			
			<ec:column property="edit" title="title.operation" sortable="false"
				viewsAllowed="html">
				<c:if test="${foundationOrder.status!=1}">
					<img src="<c:url value='/images/newIcons/coins.gif'/>"
					onclick="javascript:editFoundationOrder('${foundationOrder.orderId}')"
					style="cursor: pointer;" title="<jecs:locale key="button.edit"/>" />
				</c:if>
				
			</ec:column>
		</ec:row>
	
	</ec:table>
</form>
<script type="text/javascript">

   function editFoundationOrder(orderId){
			window.location="jfiPayFoundation.html?orderId="+orderId;
		}

</script>
