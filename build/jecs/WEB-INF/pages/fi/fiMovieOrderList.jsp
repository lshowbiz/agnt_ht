<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<title><jecs:locale key="fiMovieOrderList.title" />
</title>
<content tag="heading">
<jecs:locale key="fiMovieOrderList.heading" />
</content>
<meta name="menu" content="FiMovieOrderMenu" />


<div class="searchBar">
	<form action="fiMovieOrders.html" method="get"
		name="fiMovieOrdersSearchForm" id="fiMovieOrdersSearchForm">
		<div class="new_searchBar">
			<jecs:title key="pd.agentormember" />
			<input name="userCode" type="text" value="${param.userCode }"
				size="14" />
		</div>
		<div class="new_searchBar">
			<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
	</form>
</div>
<ec:table tableId="fiMovieOrderListTable" items="fiMovieOrderList"
	var="fiMovieOrder"
	action="${pageContext.request.contextPath}/fiMovieOrders.html"
	width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
	sortable="true" imagePath="./images/extremetable/*.gif">

	<ec:exportXls fileName="fiMovieOrder.xls" />
	<ec:row>
		<ec:column property="userCode" title="会员编号" />
		<ec:column property="orderId" title="订单编号" />
		<ec:column property="amount" title="金额" />

		<ec:column property="status" title="出票状态">
			<c:if test="${fiMovieOrder.status eq '0'}">未出票</c:if>
			<c:if test="${fiMovieOrder.status eq '1'}">已出票</c:if>
		</ec:column>
		<ec:column property="createTime" title="创建时间">
			<fmt:formatDate value="${fiMovieOrder.createTime}"
				pattern="yyyy-MM-dd" />
		</ec:column>
	</ec:row>

</ec:table>

<script type="text/javascript">

   function editFiMovieOrder(orderId){
   		<jecs:power powerCode="editFiMovieOrder">
					window.location="editFiMovieOrder.html?strAction=editFiMovieOrder&orderId="+orderId;
			</jecs:power>
		}

</script>
