<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<script src="./scripts/validate.js"> </script> 
<script src="scripts/jquerymobile/jquery-1.7.1.min.js"></script>
<script src="scripts/jQTreeTable/jQTreeTable.js"></script>
<title><jecs:locale key="398会员推荐奖励查询明细"/></title>
<content tag="heading"><jecs:locale key="jbdMemberLinkCalcHistDetail.heading"/></content>
<%@ page pageEncoding="UTF-8"%>

	<div id="titleBar" class="searchBar">
		<input type="button" class="button" name="cancel"  onclick="history.back()" value="<jecs:locale key="operation.button.return"/>" />
	</div>

<ec:table tableId="jbdDayCustRecommendOrderListTable"
	items="jbdDayCustRecommendOrderList" var="jbdDayCustRecommends"
	width="100%"
	action="${pageContext.request.contextPath}/jbdDayCustRecommends.html?strAction=jbdDayCustRecommendDetail&id=${jbdDayCustRecommends.id}"
	retrieveRowsCallback="limit" rowsDisplayed="500" sortable="true"
	imagePath="./images/extremetable/*.gif">
	<ec:row style="text-align:center">
		<ec:column property="reuserCode" title="领取会员编号"
			style="text-align:center" />
		<ec:column property="recommendLvl" title="代数"
			style="text-align:center" />
		<ec:column property="orderType" title="订单类型"
			cell="com.joymain.jecs.util.ectable.EcTableCell"
			style="text-align:center">
			<jecs:code listCode="po.all.order_type"
				value="${jbdDayCustRecommends.orderType}" />
		</ec:column>
		<ec:column property="memberOrderNo" title="领取订单编号"
			style="text-align:center" />
		<ec:column property="orderMoney" title="订单金额"
			styleClass="numberColumn" cell="number" format="###,###,##0.00"
			calc="total" calcTitle="poOrder.amtCount" style="text-align:center" />
		<ec:column property="recommendPv" title="顾问推荐奖励金额（元）"
			styleClass="numberColumn" cell="number" format="###,###,##0.00"
			calc="total" calcTitle="poOrder.amtCount" style="text-align:center" />
		<ec:column property="comDate" title="结算时间" style="text-align:center" />
	</ec:row>
</ec:table>



<script type="text/javascript">
window.top.document.getElementById("menuLabel").innerHTML='<jecs:locale key="function.menu.basicManage"/>>><jecs:locale key="function.menu.bonusManage"/>>><jecs:locale key="function.menu.bonusSearch"/>>><jecs:locale key="function.menu.addMiMember.bdSubDetails.html"/>';

</script>