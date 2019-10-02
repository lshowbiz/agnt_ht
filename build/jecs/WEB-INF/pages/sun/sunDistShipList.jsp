<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sunDistShipList.title" />
</title>
<content tag="heading">
<jecs:locale key="sunDistShipList.heading" />
</content>
<meta name="menu" content="SunDistShipMenu" />
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />

<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>


<form name="sunDistShip" action="<c:url value='/sunDistShips.html'/>">
	<input type="hidden" id="strAction" name="strAction"
		value="${requestParaMap.strAction}" />

	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="pd.createTime" />
			<input name="createTimeStart" id="createTimeStart" type="text" 
					value="${param.createTimeStart }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="startDate" id="startDate" type="text" 
					value="${param.startDate }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			<jecs:title key="pd.okTime" />
			<input name="okTimeStart" id="okTimeStart" type="text" 
					value="${param.okTimeStart }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="okTimeEnd" id="okTimeEnd" type="text" 
					value="${param.okTimeEnd }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			<jecs:title key="pdWarehouseStockTrace.orderType" />
			<jecs:list listCode="pd.send.type" name="orderType"
				showBlankLine="true" id="orderType"
				value="${requestParaMap.orderType}" defaultValue="" />
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
	</div>
</form>
<ec:table tableId="sunDistShipListTable" items="sunDistShipList"
	var="sunDistShip"
	action="${pageContext.request.contextPath}/sunDistShips.html"
	width="100%" rowsDisplayed="20" sortable="true"
	imagePath="./images/extremetable/*.gif">
	<ec:exportXls fileName="pdSendInfosTotal.xls" />
	<ec:row onclick="showTR('tr${ROWCOUNT}');">
		<ec:column property="distCode" title="jfiSunDistribution.agentCode" />
		<ec:column property="pdSendInfo.siNo" title="pdSendInfo.siNo" />
		<ec:column property="pdSendInfo.orderNo" title="busi.order.orderno" />
		<ec:column property="pdSendInfo.recipientCaNo"
			title="alStateProvince.stateProvinceName">
							${alStateProvinceMap[sunDistShip.pdSendInfo.recipientCaNo]}
						</ec:column>
		<ec:column property="pdSendInfo.city" title="busi.city">
							${alCityMap[sunDistShip.pdSendInfo.city]}
						</ec:column>
		<ec:column property="pdSendInfo.recipientAddr"
			title="pdSendInfo.recipientAddr" />
		<ec:column property="pdSendInfo.recipientName"
			title="pdSendInfo.recipientName" />
		<ec:column property="pdSendInfo.orderFlag"
			title="pdSendInfo.orderFlag">
			<jecs:code listCode="pdsendinfo.orderflag"
				value="${sunDistShip.pdSendInfo.orderFlag}" />
		</ec:column>
		<ec:column property="pdSendInfo.createTime" title="pd.createTime">
			<fmt:formatDate value="${sunDistShip.pdSendInfo.createTime}"
				pattern="yyyy-MM-dd" />
		</ec:column>
		<ec:column property="pdSendInfo.okTime" title="pd.okTime">
			<fmt:formatDate value="${sunDistShip.pdSendInfo.okTime}"
				pattern="yyyy-MM-dd" />
		</ec:column>
		<c:if test="${ROWCOUNT>0}">
			<c:if test="${ROWCOUNT%2!=0}">
				<tr class="odddetail" style="display: none" id="tr${ROWCOUNT}">
			</c:if>
			<c:if test="${ROWCOUNT%2==0}">
				<tr class="evendetail" style="display: none" id="tr${ROWCOUNT}">
			</c:if>
			<td align="right" valign="top">
				&nbsp;
			</td>
			<td colspan="6">
				<c:forEach items="${sunDistShip.pdSendInfo.pdSendInfoDetails}"
					var="pdSendInfoDetail" varStatus="status">
					<c:if test="${pdSendInfoDetail.qty != 0}">

						<c:if test="${!status.first}">
							<br />
						</c:if>
						<img src="<c:url value='/images/menu_tree/file12.gif'/>" />
						<font color=#888888>[<font color="green">${pdSendInfoDetail.qty}</font>]${pdSendInfoDetail.productNo}/
							${compamyProductMap[pdSendInfoDetail.productNo]} </font>



					</c:if>


				</c:forEach>
			</td>

			</tr>
		</c:if>
	</ec:row>

</ec:table>

<script type="text/javascript">

   function editSunDistShip(sdsId){
   		<jecs:power powerCode="editSunDistShip">
					window.location="editSunDistShip.html?strAction=editSunDistShip&sdsId="+sdsId;
			</jecs:power>
		}

</script>
