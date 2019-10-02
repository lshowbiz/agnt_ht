<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<title></title>
<content tag="heading"></content>
<meta name="menu" content="JpoWineMemberOrderMenu"/>
<style>
.hideTd { color:#666; background-color:#CCC;}
.hideTd span { margin:0 10px;}
.eXtremeTable .odd td, .eXtremeTable .even td,.eXtremeTable .highlight td { line-height:22px;}
</style>
<c:set var="buttons">
		<input type="submit" class="button" value="<jecs:locale key='operation.button.search'/>"/>
</c:set>



<form name="frm" action="<c:url value='/jpmWineMemberOrders.html'/>" >
	<input type="hidden" id="strAction" name="strAction" value="${requestParaMap.strAction}"/>
	<div class="searchBar">
		<jecs:label key="pmWineMemberOrder.orderNo"/>
		<input name="memberOrderNo" value="<c:out value='${requestParaMap.memberOrderNo}'/>" cssClass="text medium"/>	
		
		<jecs:label key="pmWineMemberOrder.userCode"/>
		<input name="userCode" id="userCode" value="<c:out value='${requestParaMap.userCode}'/>" cssClass="text medium"/>	
			
		<jecs:label key="pmWineMemberOrder.orderType"/>
		<jecs:list listCode="po.all.order_type" name="orderType" showBlankLine="true" id="orderType" value="${requestParaMap.orderType}" defaultValue=""/>
		
		<jecs:label key="pmWineMemberOrder.configStatus"/>
			<jecs:list listCode="pmwinememberorder.status" name="status" id="status" showBlankLine="true" value="${requestParaMap.status}" defaultValue=""/>
			
		<c:out value="${buttons}" escapeXml="false"/>
	</div>
		
<ec:table 
	tableId="pmJpoWineMemberOrderListTable"
	items="pmJpoWineMemberOrderList"
	var="jpoMemberOrder"
	form="frm"
	action="${pageContext.request.contextPath}/jpmWineMemberOrders.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
		<ec:row onclick="showTR($('tr${ROWCOUNT}'));">
			<ec:column property="memberOrderNo" title="pmWineMemberOrder.orderNo" />
			
			<ec:column property="orderType" title="pmWineMemberOrder.orderType" >
				<jecs:code listCode="po.all.order_type" value="${jpoMemberOrder.orderType}"/>
			</ec:column>
			
			<ec:column property="productAmount" title="pmWineMemberOrder.productNum" />
			<ec:column property="productConfigAmount" title="pmWineMemberOrder.wasProductNum" />
			<ec:column property="orderTime" title="pd.createTime" />
			
		</ec:row>
		<c:if test="${ROWCOUNT>0}">
			<c:if test="${ROWCOUNT%2!=0}"><tr id="tr${ROWCOUNT}" style="display:none" class="odddetail"></c:if>
			<c:if test="${ROWCOUNT%2==0}"><tr id="tr${ROWCOUNT}" style="display:none" class="evendetail"></c:if>
				<td colspan="5" class="hideTd">
					<c:forEach items="${jpoMemberOrder.jpoMemberOrderList}" var="jpoMemberOrderDetail" varStatus="status">
						<c:if test="${!status.first}"><br/></c:if>
						&nbsp;&nbsp;<img src="<c:url value='/images/menu_tree/file12.gif'/>"/>&nbsp;&nbsp;
						<span><b>商品编号：</b>${jpoMemberOrderDetail.jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo}</span>
						<span><b>商品名称：</b>${jpoMemberOrderDetail.jpmProductSaleTeamType.jpmProductSaleNew.productName}</span>
						<span><b>单价：</b>&yen;&nbsp;${jpoMemberOrderDetail.price}</span>
						<span><b>商品数量：</b>${jpoMemberOrderDetail.qty}</span>
						<span><b>已配数量：</b>${jpoMemberOrderDetail.productConfigAmount}</span>
						<a href="jpmWineMemberOrders.html?strAction=orderConfigList&molId=${jpoMemberOrderDetail.molId}&productNo=${jpoMemberOrderDetail.jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo }" style="color: blue;"><jecs:locale key="pmWineMemberOrder.config"/></a>
						
					</c:forEach>
				</td>
			</tr>
		</c:if>
</ec:table>	
</form>
