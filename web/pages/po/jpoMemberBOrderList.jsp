<%@ include file="/common/taglibs.jsp"%>

<c:if
	test="${fn:substring(sessionScope.sessionLogin.jmiMember.jmiRecommendRef.treeIndex,0,36)!='00100000000000000b00n00000u003000013'}">
	<title><jecs:locale key="jpoMemberOrderList.title" />
	</title>
	<content tag="heading">
	<jecs:locale key="jpoMemberOrderList.heading" /></content>
	<meta name="menu" content="JpoMemberOrderMenu" />

	<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
	<script type="text/javascript" src="./scripts/calendar/calendar.js">
		
	</script>
	<script type="text/javascript"
		src="./scripts/calendar/calendar-setup.js">
		
	</script>
	<script type="text/javascript" src="./scripts/calendar/lang.jsp">
		
	</script>

	<form action="jpoMemberBOrders.html" method="get" name="searchForm"
		id="searchForm">

		<div class="searchBar">
			<div class="new_searchBar">
				<jecs:title key="poMemberOrder.memberOrderNo" />
				<input name="memberOrderNo" type="text" size="10"
					value="${param.memberOrderNo }" />
			</div>
			<c:if test="${sessionScope.sessionLogin.userType=='C'}">
				<div class="new_searchBar">
					<jecs:title key="miMember.memberNo" />
					<input name="sysUser.userCode" type="text" size="10"
						value="${param['sysUser.userCode'] }" size="12" />
				</div>
			</c:if>

			<div class="new_searchBar">
				<jecs:list listCode="pmo.logtype" name="logType"
					value="${param.logType}" defaultValue="" style="width:auto;" />
				<input name="startLogTime" id="startLogTime" type="text"
					value="${param.startLogTime }"
					style="cursor: pointer; width: 77px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" /> - <input
					name="endLogTime" id="endLogTime" type="text"
					value="${param.endLogTime }" style="cursor: pointer; width: 77px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
			</div>

			<div class="new_searchBar">
				<jecs:title key="pocounterorder.csstatus" />
				<jecs:list name="status" listCode="po.status"
					value="${param.status}" defaultValue="0" showBlankLine="true" />
			</div>

			<input name="search" type="hidden"
				value="<jecs:locale key="operation.button.search"/>" />

			<div class="new_searchBar">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button name="search1" class="btn btn_sele" type="submit"
					style="width: auto">
					<jecs:locale key="operation.button.search" />
				</button>
				<jecs:power powerCode="addPoMemberBOrder">
					<c:if test="${sessionScope.sessionLogin.userType=='C'}">
						<button type="button" name="add" class="btn btn_ins"
							style="width: auto"
							onclick="location.href='<c:url value="/editJpoMemberBOrder.html?strAction=addPoMemberBOrder"/>'">
							<jecs:locale key="member.new.num" />
						</button>
					</c:if>
				</jecs:power>
			</div>
		</div>
	</form>

	<ec:table tableId="jpoMemberOrderListTable" items="jpoMemberOrderList"
		var="jpoMemberOrder" autoIncludeParameters="true"
		action="${pageContext.request.contextPath}/jpoMemberBOrders.html"
		width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
		sortable="true" imagePath="./images/extremetable/*.gif">
		<ec:row onclick="showTR($('tr${ROWCOUNT}'));">
			<ec:column property="memberOrderNo"
				title="poMemberOrder.memberOrderNo" />
			<ec:column property="orderType" title="poMemberOrder.orderType"
				styleClass="centerColumn">
				<jecs:code listCode="po.all.order_type"
					value="${jpoMemberOrder.orderType}" />
			</ec:column>
			<ec:column property="sysUser.userCode" title="miMember.memberNo" />
			<ec:column property="amount" title="busi.order.amount"
				styleClass="numberColumn" />


			<ec:column property="pvAmt" title="poMemberOrder.pvAmt"
				styleClass="numberColumn" />
			<%--
    			<ec:column property="payMode" title="fiPayAdvice.dealType" styleClass="centerColumn">
    				<jecs:code listCode="po.paymode" value="${jpoMemberOrder.payMode}"/>
    			</ec:column>
--%>

			<ec:column property="status" title="pd.checkstatus"
				styleClass="centerColumn">
				<jecs:code listCode="po.status" value="${jpoMemberOrder.status}" />
			</ec:column>
			<ec:column property="checkDate" title="poMemberOrder.period"
				styleClass="centerColumn">
				<jecs:period dateStr="${jpoMemberOrder.checkDate}" />
			</ec:column>
			<ec:column property="remark" title="busi.common.remark"
				styleClass="centerColumn" />
			<ec:column property="edit" title="title.operation" sortable="false"
				style="width:100px;" viewsAllowed="html">
				<nobr>
					<jecs:power powerCode="viewPoMemberOrder">
						<a
							href="jpoMemberOrderView.html?strAction=viewPoMemberOrder&moId=${jpoMemberOrder.moId}"><img
							border="0" src="images/newIcons/search_16.gif"
							alt="<jecs:locale key="function.menu.view"/>" />
						</a>
					</jecs:power>
					<c:if
						test="${jpoMemberOrder.status=='1' && jpoMemberOrder.isPay!='1' && jpoMemberOrder.submitStatus=='1'}">
						<jecs:power powerCode="editPoMemberBOrder">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>"
								onclick="javascript:editJpoMemberBOrder('${jpoMemberOrder.moId}')"
								style="cursor: pointer;"
								title="<jecs:locale key="button.edit"/>" />
						</jecs:power>

					</c:if>

				</nobr>
			</ec:column>

			<%--<ec:column property="submitStatus" title="poMemberOrder.checkStatus" styleClass="centerColumn">
    				<jecs:code listCode="po.checkstatus" value="${jpoMemberOrder.submitStatus}"/>
    			</ec:column>--%>

		</ec:row>
		<c:if test="${ROWCOUNT>0}">
			<c:if test="${ROWCOUNT%2!=0}">
				<tr id="tr${ROWCOUNT}" style="display: none" class="odddetail">
			</c:if>
			<c:if test="${ROWCOUNT%2==0}">
				<tr id="tr${ROWCOUNT}" style="display: none" class="evendetail">
			</c:if>
			<td colspan="7"><c:forEach
					items="${jpoMemberOrder.jpoMemberOrderList}"
					var="jpoMemberOrderDetail" varStatus="status">
					<c:if test="${!status.first}">
						<br />
					</c:if>
									&nbsp;&nbsp;<img
						src="<c:url value='/images/menu_tree/file12.gif'/>" />&nbsp;&nbsp;<font
						color=#888888>[<font color="green">${jpoMemberOrderDetail.qty}</font>]${jpoMemberOrderDetail.jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo}/${jpoMemberOrderDetail.jpmProductSaleTeamType.jpmProductSaleNew.productName}</font>
				</c:forEach></td>
			</tr>
		</c:if>

	</ec:table>
	<script type="text/javascript">
		function editJpoMemberBOrder(moId) {
			<jecs:power powerCode="editPoMemberBOrder">
			window.location = "editJpoMemberBOrder.html?strAction=editPoMemberBOrder&moId="
					+ moId;
			</jecs:power>
		}
	</script>
</c:if>