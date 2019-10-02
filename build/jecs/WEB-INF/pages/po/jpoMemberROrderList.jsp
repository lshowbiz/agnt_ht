<%@ include file="/common/taglibs.jsp"%>

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

<form action="jpoMemberROrders.html" method="get" name="searchForm"
	id="searchForm">
	<div class="searchBar">
		<div class="new_searchBar">
			<input name="orderType" type="hidden" value="${param.orderType }" />
			<jecs:title key="poMemberOrder.memberOrderNo" />
			<input name="memberOrderNo" type="text" size="10"
				value="${param.memberOrderNo }" />
		</div>

		<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			<div class="new_searchBar">
				<jecs:title key="miMember.memberNo" />
				<input name="sysUser.userCode" type="text"
					value="${param['sysUser.userCode'] }" size="12" />
			</div>
		</c:if>

		<div class="new_searchBar">
			<jecs:list listCode="pmo.logtype" name="logType"
				value="${param.logType}" defaultValue="" style="width:auto;" />
			<input name="startLogTime" id="startLogTime" type="text"
				value="${param.startLogTime }" style="cursor: pointer; width: 77px;"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" /> - <input
				name="endLogTime" id="endLogTime" type="text"
				value="${param.endLogTime }" style="cursor: pointer; width: 77px;"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="pocounterorder.csstatus" />
			<jecs:list name="status" listCode="po.status" value="${param.status}"
				defaultValue="0" showBlankLine="true" />
		</div>

		<input name="search" type="hidden"
			value="<jecs:locale key="operation.button.search"/>" />

		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button name="search" class="btn btn_sele" type="submit"
				style="width: auto">
				<jecs:locale key="operation.button.search" />
			</button>

			<jecs:power powerCode="addPoMemberROrder">
				<c:if test="${sessionScope.sessionLogin.userType=='C'}">
					<button name="add" class="btn btn_ins" type="button"
						style="width: auto"
						onclick="location.href='<c:url value="/editJpoMemberROrder.html?strAction=addPoMemberROrder&orderType=${param.orderType }"/>'">
						<jecs:locale key="member.new.num" />
					</button>
				</c:if>
				<c:if test="${sessionScope.sessionLogin.userType!='C'}">
					<c:if
						test="${fn:substring(sessionScope.sessionLogin.jmiMember.jmiRecommendRef.treeIndex,0,36)=='00100000000000000b00n00000u003000013'}">
						<c:if test="${sessionScope.sessionLogin.companyCode=='CN' }">
							<button name="add" class="btn btn_ins" type="button"
								style="width: auto"
								onclick="location.href='<c:url value="/editJpoMemberROrderLC.html?strAction=addPoMemberROrderLC&orderType=${param.orderType }"/>'">
								<jecs:locale key="member.new.num" />
							</button>
						</c:if>
						<c:if test="${sessionScope.sessionLogin.companyCode!='CN' }">
							<button name="add" class="btn btn_ins" type="button"
								style="width: auto"
								onclick="location.href='<c:url value="/editJpoMemberROrder.html?strAction=addPoMemberROrder&orderType=${param.orderType }"/>'">
								<jecs:locale key="member.new.num" />
							</button>
						</c:if>
					</c:if>
					<c:if
						test="${fn:substring(sessionScope.sessionLogin.jmiMember.jmiRecommendRef.treeIndex,0,36)!='00100000000000000b00n00000u003000013'}">
						<button name="add" class="button" type="button"
							style="width: auto"
							onclick="location.href='<c:url value="/editJpoMemberROrder.html?strAction=addPoMemberROrder&orderType=${param.orderType }"/>'">
							<jecs:locale key="member.new.num" />
						</button>
					</c:if>
				</c:if>
			</jecs:power>
		</div>
	</div>
</form>

<ec:table tableId="jpoMemberOrderListTable" items="jpoMemberOrderList"
	var="jpoMemberOrder" autoIncludeParameters="true"
	action="${pageContext.request.contextPath}/jpoMemberROrders.html"
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
		<jecs:power powerCode="po.pay.by.jj">
			<ec:column property="jjAmount" title="busi.order.jjAmount" />
		</jecs:power>
		<ec:column property="pvAmt" title="poMemberOrder.pvAmt"
			styleClass="numberColumn" />
		<%--
    			<ec:column property="payMode" title="fiPayAdvice.dealType" styleClass="centerColumn">
    				<jecs:code listCode="po.paymode" value="${jpoMemberOrder.payMode}"/>
    			</ec:column>
--%>
		<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			<ec:column property="1" title="pd.ordertype.prRefund"
				styleClass="centerColumn">
				<c:set var="jprRefund" value="0" />
				<c:forEach items="${jpoMemberOrder.jprRefund}" var="jprRefundList"
					varStatus="status">
					<c:if test="${jprRefundList.refundStatus==2 }">
						<c:set var="jprRefund" value="1" />
					</c:if>
				</c:forEach>

				<c:if test="${jprRefund == 1 }">
					<jecs:locale key="yesno.yes" />
				</c:if>
				<c:if test="${jprRefund == 0 }">
					<jecs:locale key="yesno.no" />
				</c:if>
			</ec:column>
		</c:if>
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

			<jecs:power powerCode="viewPoMemberOrder">
				<a
					href="jpoMemberOrderView.html?strAction=viewPoMemberOrder&moId=${jpoMemberOrder.moId}&orderType=${param.orderType}"><img
					border="0" src="images/newIcons/search_16.gif"
					alt="<jecs:locale key="function.menu.view"/>" />
				</a>
			</jecs:power>
			<c:if
				test="${empty jpoMemberOrder.companyPay || sessionScope.sessionLogin.userType=='C'}">
				<c:if
					test="${jpoMemberOrder.status=='1' && jpoMemberOrder.isPay!='1' && jpoMemberOrder.submitStatus=='1'}">
					<c:if test="${jpoMemberOrder.productType!='LC' }">
						<jecs:power powerCode="editPoMemberROrder">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>"
								onclick="javascript:editJpoMemberROrder('${jpoMemberOrder.moId}','${param.orderType }')"
								style="cursor: pointer;"
								title="<jecs:locale key="button.edit"/>" />
						</jecs:power>
					</c:if>
					<c:if test="${jpoMemberOrder.productType=='LC' }">
						<jecs:power powerCode="editPoMemberROrderLC">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>"
								onclick="javascript:window.location='editJpoMemberROrderLC.html?strAction=editPoMemberROrderLC&moId=${jpoMemberOrder.moId}&orderType=${param.orderType}'"
								style="cursor: pointer;"
								title="<jecs:locale key="button.edit"/>" />
						</jecs:power>
					</c:if>
				</c:if>

			</c:if>
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
	function editJpoMemberROrder(moId, orderType) {
		<jecs:power powerCode="editPoMemberROrder">
		window.location = "editJpoMemberROrder.html?strAction=editPoMemberROrder&moId="
				+ moId + "&orderType=" + orderType;
		</jecs:power>
	}
</script>
