<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="poMemberOrderList.title"/></title>
<content tag="heading"><jecs:locale key="poMemberOrderList.heading"/></content>
<meta name="menu" content="PoMemberOrderMenu"/>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form action="jprRefundBacking.html" method="get" name="searchForm" id="searchForm">

<input type="hidden" name="strAction" value="addPrRefund"/>

<div class="searchBar">
<div class="new_searchBar">
<jecs:label key="poMemberOrder.memberOrderNo"/>
<input name="memberOrderNo" type="text" value="${param.memberOrderNo }"/>
</div>

<div class="new_searchBar">
<jecs:label key="miMember.memberNo"/>
<input name="sysUser.userCode" type="text" value="${param['sysUser.userCode'] }"/>
</div>

<div class="new_searchBar">
<button name="search" class="btn btn_sele" type="submit" >
	<jecs:locale key="operation.button.search"/>
</button>
<button name="back" class="btn btn_sele" type="button" onclick="history.back()" >
	<jecs:locale key="operation.button.return"/>
</button>
</div>
</div>

<ec:table 
	tableId="prRefundBackingListTable"
	items="prRefundBackingList"
	var="poMemberOrder"
	retrieveRowsCallback="limit"
	action="${pageContext.request.contextPath}/jprRefundBacking.html"
	width="100%" form="searchForm"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
		<ec:row>
			<ec:column property="operation" title="title.operation" styleClass="centerColumn" style="width:5px;">
  				<nobr>
				<jecs:power powerCode="viewPoMemberOrder">
    			<a href="jpoMemberOrderView.html?strAction=viewPoMemberOrder&moId=${poMemberOrder.moId}"><img border="0" src="images/newIcons/search_16.gif" alt="<jecs:locale key="function.menu.view"/>" /></a>
    			</jecs:power>
    			<jecs:power powerCode="addPrRefund">
    			<a href="editJprRefund.html?strAction=addPrRefund&moId=${poMemberOrder.moId}"><img border="0" src="images/icons/creatOrder.gif" alt="<jecs:locale key="report.createPaper"/>" /></a>
    			</jecs:power>
    			</nobr>
			</ec:column>
			<ec:column property="memberOrderNo" title="poMemberOrder.memberOrderNo" />
			<ec:column property="sysUser.userCode" title="miMember.memberNo" />
			<ec:column property="sysUser.jmiMember.petName" title="miMember.petName" />
			<ec:column property="orderType" title="poMemberOrder.orderType" styleClass="centerColumn">
				<jecs:code listCode="po.all.order_type" value="${poMemberOrder.orderType}"/>
			</ec:column>
			<ec:column property="pvAmt" title="poMemberOrder.pvAmt" styleClass="numberColumn"/>
			<ec:column property="amount" title="busi.order.amount" styleClass="numberColumn"/>
<%-- 
			<ec:column property="payMode" title="busi.order.payMode" styleClass="centerColumn">
				<jecs:code listCode="po.paymode" value="${poMemberOrder.payMode}"/>
			</ec:column>
--%>
		</ec:row>
</ec:table>	
</form>
