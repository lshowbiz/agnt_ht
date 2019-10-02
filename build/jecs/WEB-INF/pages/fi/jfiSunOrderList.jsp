<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiSunOrderList.title"/></title>
<content tag="heading"><jecs:locale key="jfiSunOrderList.heading"/></content>
<meta name="menu" content="JfiSunOrderMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJfiSunOrder">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJfiSunOrder.html"/>?strAction=addJfiSunOrder'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jfiSunOrderListTable"
	items="jfiSunOrderList"
	var="jfiSunOrder"
	action="${pageContext.request.contextPath}/jfiSunOrders.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJfiSunOrder('${jfiSunOrder.moId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="companyCode" title="jfiSunOrder.companyCode" />
    			<ec:column property="countryCode" title="jfiSunOrder.countryCode" />
    			<ec:column property="memberOrderNo" title="jfiSunOrder.memberOrderNo" />
    			<ec:column property="orderType" title="jfiSunOrder.orderType" />
    			<ec:column property="userCode" title="jfiSunOrder.userCode" />
    			<ec:column property="userSpCode" title="jfiSunOrder.userSpCode" />
    			<ec:column property="amount" title="jfiSunOrder.amount" />
    			<ec:column property="pvAmt" title="jfiSunOrder.pvAmt" />
    			<ec:column property="consumerAmount" title="jfiSunOrder.consumerAmount" />
    			<ec:column property="payMode" title="jfiSunOrder.payMode" />
    			<ec:column property="orderUserCode" title="jfiSunOrder.orderUserCode" />
    			<ec:column property="orderTime" title="jfiSunOrder.orderTime" />
    			<ec:column property="status" title="jfiSunOrder.status" />
    			<ec:column property="checkTime" title="jfiSunOrder.checkTime" />
    			<ec:column property="checkUserCode" title="jfiSunOrder.checkUserCode" />
    			<ec:column property="checkDate" title="jfiSunOrder.checkDate" />
    			<ec:column property="remark" title="jfiSunOrder.remark" />
    			<ec:column property="pickup" title="jfiSunOrder.pickup" />
    			<ec:column property="submitTime" title="jfiSunOrder.submitTime" />
    			<ec:column property="submitUserCode" title="jfiSunOrder.submitUserCode" />
    			<ec:column property="submitStatus" title="jfiSunOrder.submitStatus" />
    			<ec:column property="locked" title="jfiSunOrder.locked" />
    			<ec:column property="firstName" title="jfiSunOrder.firstName" />
    			<ec:column property="lastName" title="jfiSunOrder.lastName" />
    			<ec:column property="province" title="jfiSunOrder.province" />
    			<ec:column property="city" title="jfiSunOrder.city" />
    			<ec:column property="district" title="jfiSunOrder.district" />
    			<ec:column property="address" title="jfiSunOrder.address" />
    			<ec:column property="postalcode" title="jfiSunOrder.postalcode" />
    			<ec:column property="phone" title="jfiSunOrder.phone" />
    			<ec:column property="email" title="jfiSunOrder.email" />
    			<ec:column property="mobiletele" title="jfiSunOrder.mobiletele" />
    			<ec:column property="agentNo" title="jfiSunOrder.agentNo" />
    			<ec:column property="samount" title="jfiSunOrder.samount" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJfiSunOrder(moId){
   		<jecs:power powerCode="editJfiSunOrder">
					window.location="editJfiSunOrder.html?strAction=editJfiSunOrder&moId="+moId;
			</jecs:power>
		}

</script>
