<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiSunMemberOrderList.title" />
</title>
<content tag="heading">
<jecs:locale key="jfiSunMemberOrderList.heading" />
</content>
<meta name="menu" content="JfiSunMemberOrderMenu" />

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form action="" method="get" name="searchForm" id="searchForm">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="jfiSunDistribution.agentCode" />
			<input name="agentNo" type="text" value="${param['agentNo'] }"
				size="12" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="miMember.memberNo" />
			<input name="userCode" type="text" value="${param['userCode'] }"
				size="12" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="poMemberOrder.memberOrderNo" />
			<input name="memberOrderNo" type="text" size="10"
				value="${param.memberOrderNo }" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="logType.BC" />
			<input name="startLogTime" id="startLogTime" type="text" 
					value="${param.startLogTime }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="endLogTime" id="endLogTime" type="text" 
					value="${param.endLogTime }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
	</div>
</form>

<ec:table tableId="jfiSunMemberOrderListTable"
	items="jfiSunMemberOrderList" var="jfiSunMemberOrder"
	action="${pageContext.request.contextPath}/jfiSunMemberOrders.html"
	width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
	sortable="true" imagePath="./images/extremetable/*.gif">
	<ec:row>
		<ec:column property="agentName" title="jfiSunMemberOrder.agentName" />
		<ec:column property="userCode" title="miMember.memberNo" />
		<ec:column property="memberOrderNo"
			title="poMemberOrder.memberOrderNo" />
		<ec:column property="firstName" title="shipping.firstName" />
		<ec:column property="lastName" title="shipping.lastName" />
		<ec:column property="province" title="shipping.province">
			<c:forEach items="${alStateProvinces }" var="al">
				<c:if test="${al.stateProvinceId==jfiSunMemberOrder.province }">
					<c:out value="${al.stateProvinceName}" />
				</c:if>
			</c:forEach>
		</ec:column>
		<ec:column property="edit" title="title.operation" sortable="false"
			viewsAllowed="html">
			<img src="<c:url value='/images/newIcons/search_16.gif'/>"
				onclick="javascript:editJfiSunMemberOrder('${jfiSunMemberOrder.moId}')"
				style="cursor: pointer;"
				title="<jecs:locale key="function.menu.view"/>" />
		</ec:column>
	</ec:row>

</ec:table>

<script type="text/javascript">

   function editJfiSunMemberOrder(moId){
					window.location="jfiSunMemberOrderView.html?moId="+moId;
		}

</script>
