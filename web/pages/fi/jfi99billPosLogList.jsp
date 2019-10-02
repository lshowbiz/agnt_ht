<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfi99billPosLogList.title"/></title>
<content tag="heading"><jecs:locale key="jfi99billPosLogList.heading"/></content>
<meta name="menu" content="Jfi99billPosLogMenu"/>
<c:if test="${payType =='1'}">
<ec:table 
	tableId="jpoMemberOrderListTable"
	items="jpoMemberOrderList"
	var="jpoMemberOrder"
	autoIncludeParameters="true"
	action="${pageContext.request.contextPath}/jpoMemberFOrders.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row onclick="showTR($('tr${ROWCOUNT}'));">
    			<ec:column property="memberOrderNo" title="poMemberOrder.memberOrderNo" />
    			<ec:column property="orderType" title="poMemberOrder.orderType" styleClass="centerColumn">
    				<jecs:code listCode="po.all.order_type" value="${jpoMemberOrder.orderType}"/>
    			</ec:column>
    			<ec:column property="sysUser.userCode" title="miMember.memberNo">
    				<font color="red">${jpoMemberOrder.sysUser.userCode}</font>
    			</ec:column>
    			<ec:column property="amount" title="busi.order.amount" styleClass="numberColumn"/>
    			<ec:column property="pvAmt" title="poMemberOrder.pvAmt" styleClass="numberColumn"/>
    			<ec:column property="status" title="pd.checkstatus" styleClass="centerColumn">
    				<jecs:code listCode="pos.status" value="${jpoMemberOrder.status}"/>
    			</ec:column>
				<ec:column property="edit" title="title.operation" sortable="false" style="width:100px;" viewsAllowed="html">
					<nobr>
					<c:if test="${jpoMemberOrder.status=='1'}">
							<img src="<c:url value='/images/newIcons/coins.gif'/>" 
								onclick="window.open('jfi99billPosLogs.html?strAction=isPay&orderId=${jpoMemberOrder.moId}&userCode=${sysUser.userCode }');"
								style="cursor: pointer;" title="<jecs:locale key="btn.pay.now"/>"/> 
					</c:if>
					</nobr>
				</ec:column>
				</ec:row>
				<c:if test="${ROWCOUNT>0}">
						<c:if test="${ROWCOUNT%2!=0}"><tr id="tr${ROWCOUNT}" style="display:none" class="odddetail"></c:if>
						<c:if test="${ROWCOUNT%2==0}"><tr id="tr${ROWCOUNT}" style="display:none" class="evendetail"></c:if>
							<td colspan="7">
								<c:forEach items="${jpoMemberOrder.jpoMemberOrderList}" var="jpoMemberOrderDetail" varStatus="status">
									<c:if test="${!status.first}"><br/></c:if>
									&nbsp;&nbsp;<img src="<c:url value='/images/menu_tree/file12.gif'/>"/>&nbsp;&nbsp;<font color=#888888>[<font color="green">${jpoMemberOrderDetail.qty}</font>]${jpoMemberOrderDetail.jpmProductSale.jpmProduct.productNo}/${jpoMemberOrderDetail.jpmProductSale.productName}</font>
								</c:forEach>
							</td>
						</tr>
				</c:if>

</ec:table>	
</c:if>
<c:if test="${payType =='0'}">
<c:set var="buttons">
	<input type="button" onclick="submitImf()" class="button" name="next"  value="<jecs:locale key="button.next"/>" />
</c:set>
<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<table class='detail' width="100%">
	<tr><th>
        <jecs:title  key="miMember.memberNo"/>
    </th>
    <td align="left">
        ${sysUser.userCode }
    </td></tr>
	<tr><th>
        <jecs:title  key="jfi99billTemp.orderAmount"/>
    </th>
    <td align="left">
        <input type="text" onchange="set_amt()" name="orderAmount" value="0" id="orderAmount" />
    </td></tr>
</table>
<script language="javascript">
function set_amt(){
	if (!isFinite($('orderAmount').value) || $('orderAmount').value < 0 ){
	  	$('orderAmount').value = 0;
	}
}
function submitImf(){
	if( $('orderAmount').value > 10 && $('orderAmount').value.indexOf('.') < 0 ){
		if(isFormPosted()){
			window.open('jfi99billPosLogs.html?strAction=isPay&userCode=${sysUser.userCode }&orderAmount=' + $('orderAmount').value);
			//window.location = 'jfi99billPosLogs.html?strAction=isPay&userCode=${sysUser.userCode }&orderAmount=' + $('orderAmount').value;
		}
	}else{
		alert('<jecs:locale key="jfi99billPosLogList.orderAmount"/>');
	}
}
</script>
</c:if>
