<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><jecs:locale key="jbdSendRecordHistList.title"/></title>
    <meta name="heading" content="<jecs:locale key='jbdSendRecordHistList.heading'/>"/>
    <meta name="menu" content="FinanceMenu"/>
</head>





<form action="" method="get" name="miMemberForm" id="miMemberForm">

	<div class="searchBar">
		
		
			<jecs:label key="miMember.memberNo"/>
			<input name="userCode" type="text" value="${param.userCode}" size="10"/>	
			<input type="submit" class="button" name="cancel" value="<jecs:locale key="operation.button.search"/>" />
		
		
<c:set var="totalPv" value="0"></c:set>
<c:forEach items="${points}" var="var">
	<c:set var="totalPv" value="${totalPv+var.pv_amt }"></c:set>
</c:forEach>

<jecs:label key="jmiRecommendRefPoint.form"/> 
<fmt:formatNumber value="${totalPv/7 }" type="number" pattern="###,###,###"/>

	</div>





<ec:table 
	tableId="jmiRecommendRefPointListTable"
	items="points"
	var="point"
	 retrieveRowsCallback="limit" sortRowsCallback="limit"  filterRowsCallback="limit"
	action="${pageContext.request.contextPath}/bdSendRegister.html"
	width="100%"
	showPagination="false"
	 sortable="false" imagePath="./images/extremetable/*.gif">

				<ec:row>
    			<ec:column property="user_code" title="miMember.memberNo" />
    			<ec:column property="member_order_no" title="poMemberOrder.memberOrderNo" />
	
    			<ec:column property="pv_amt" title="poMemberOrder.pvAmt" styleClass="numberColumn" cell="number" format="###,###,##0" calc="total" calcTitle="poOrder.amtCount">
    				
    			</ec:column>
    			
				</ec:row>

</ec:table>	






</form>

