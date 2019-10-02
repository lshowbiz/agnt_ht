<%@ page language="java" errorPage="/error.jsp"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderList.title"/></title>
<meta name="menu" content="JpoMemberOrderMenu"/>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form action="" method="get" name="searchForm" id="searchForm">
<input name="flag" type="hidden" value="1"/>
<div class="searchBar">
	<div class="new_searchBar">   
		<jecs:label key="poMemberOrder.memberOrderNo"/>
		<input name="orderNo" type="text" size="10" value="${param.orderNo }"/>
	</div>
	
	<div class="new_searchBar">   
		<jecs:label key="miMember.memberNo"/>
		<input name="userCode" type="text" value="${param.userCode}" size="12"/>
	</div>
	
	<div class="new_searchBar">   
		<jecs:label key="miMember.memberType"/>
		<input name="teamCode1" type="text" value="${param.teamCode1}" size="12"/>
	</div>
	
	<div class="new_searchBar">  
		<jecs:label key="pd.createTime"/>
		<input name="startDate" id="startDate" type="text" 
			value="${param.startDate }" style="cursor: pointer;width:77px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			-
			<input name="endDate" id="endDate" type="text"
			value="${param.endDate }" style="cursor: pointer;width:77px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	</div>
	
	<div class="new_searchBar">  
		<label>传送时间: </label>
		<input name="orderPaystartDate" id="orderPaystartDate" type="text" 
			value="${param.orderPaystartDate }" style="cursor: pointer;width:77px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			-
			<input name="orderPayendDate" id="orderPayendDate" type="text"
			value="${param.orderPayendDate }" style="cursor: pointer;width:77px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	</div>

	<div class="new_searchBar" style="margin-left: 40px">
		<button name="search" class="btn btn_sele" type="submit" >
			<jecs:locale key="operation.button.search"/>
		</button>
	</div>
</div>
</form>

	<c:set var="footTotalVar">
	<tr>
		<td align="right" class="footer" colspan="3">
			<jecs:locale key="report.allTotal"/>
		</td>
		<td align="right" class="footer">
			<b>
				<c:if test="${not empty sumMap.amount}">
					<fmt:formatNumber pattern="###,##0.00">${sumMap.amount}</fmt:formatNumber>
				</c:if>
				<c:if test="${empty sumMap.amount}">
					0.00
				</c:if>
			</b>
		</td>
		<td align="right" class="footer"><b>
			<c:if test="${not empty sumMap.yd_pv}">
				<fmt:formatNumber pattern="###,##0.00">${sumMap.yd_pv}</fmt:formatNumber>
			</c:if>
			<c:if test="${empty sumMap.yd_pv}">
				0.00
			</c:if></b>
		</td>
		<td align="right" class="footer"><b>
			<c:if test="${not empty sumMap.pvamt}">
				<fmt:formatNumber pattern="###,##0.00">${sumMap.pvamt}</fmt:formatNumber>
			</c:if>
			<c:if test="${empty sumMap.pvamt}">
				0.00
			</c:if></b>
		</td>
		
		<!--新加展示字段  -->
		<td align="right" class="footer"><b>
			<c:if test="${not empty sumMap.yd_rebate}">
				<fmt:formatNumber pattern="###,##0.00">${sumMap.yd_rebate}</fmt:formatNumber>
			</c:if>
			<c:if test="${empty sumMap.yd_rebate}">
				0.00
			</c:if></b>
		</td>
		<td align="right" class="footer"><b>
			<c:if test="${not empty sumMap.ec_rebate}">
				<fmt:formatNumber pattern="###,##0.00">${sumMap.ec_rebate}</fmt:formatNumber>
			</c:if>
			<c:if test="${empty sumMap.ec_rebate}">
				0.00
			</c:if></b>
		</td>
		<td align="right" class="footer"><b>
			<c:if test="${not empty sumMap.total_rebate}">
				<fmt:formatNumber pattern="###,##0.00">${sumMap.total_rebate}</fmt:formatNumber>
			</c:if>
			<c:if test="${empty sumMap.total_rebate}">
				0.00
			</c:if></b>
		</td>
		
		<td class="footer">&nbsp;</td>
		<td class="footer">&nbsp;</td>
		<td class="footer">&nbsp;</td>
	</tr>
	</c:set>

	<ec:table tableId="ydOrderListTable" items="ydOrderList"
		var="order" autoIncludeParameters="true"
		action="${pageContext.request.contextPath}/ydOrdersatic.html"
		width="100%"  
		retrieveRowsCallback="limit"
		rowsDisplayed="20" 
		sortable="false" imagePath="./images/extremetable/*.gif"  footer="${footTotalVar}">
		
		<ec:row onclick="showTR($('tr${ROWCOUNT}'));">
			<ec:exportXls fileName="ydOrderList.xls"></ec:exportXls>
		
			<ec:column property="moid" title="ID" styleClass="centerColumn"/>
	    	<ec:column property="orderNo" title="poMemberOrder.memberOrderNo" styleClass="centerColumn"/>
	    	<ec:column property="userCode" title="miMember.memberNo" styleClass="centerColumn"/>
	    	<ec:column property="amount" title="busi.order.amount" styleClass="centerColumn" />
	    	<ec:column property="ydPV" title="poMemberOrder.ydpv" styleClass="centerColumn" />
	    	<ec:column property="pvamt" title="poMemberOrder.pvAmt" styleClass="centerColumn" />
	    	<ec:column property="ydRebate" title="poMemberOrder.ydRebate" styleClass="centerColumn" />
	    	<ec:column property="ecRebate" title="poMemberOrder.ecRebate" styleClass="centerColumn" />
	    	<ec:column property="totalRebate" title="poMemberOrder.totalRebate" styleClass="centerColumn" />
	    	<ec:column property="teamCode" title="miMember.memberType" styleClass="centerColumn"/>
	    	<ec:column property="orderTimeString" title="pd.createTime" styleClass="centerColumn"/>
	    	<ec:column property="orderPayTime" title="传送时间"  parse="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd HH:mm:ss" cell="date" styleClass="centerColumn"/>
		</ec:row> 
		<c:if test="${ROWCOUNT>0}">
			<c:if test="${ROWCOUNT%2!=0}"><tr id="tr${ROWCOUNT}" style="display:none" class="odddetail"></c:if>
			<c:if test="${ROWCOUNT%2==0}"><tr id="tr${ROWCOUNT}" style="display:none" class="evendetail"></c:if>
					<td colspan="13">
					<c:forEach items="${order.ydOrderItem}" var="orderItem" varStatus="status">
						<c:if test="${!status.first}"><br/></c:if>
						&nbsp;&nbsp;<img src="<c:url value='/images/menu_tree/file12.gif'/>"/>&nbsp;&nbsp;
						<font color=#888888>
						[<font color="green">${orderItem.qty}</font>]
						${orderItem.productNo}/${orderItem.productName}
						</font>
					</c:forEach>
				</td>
			</tr>
		</c:if>
	
	</ec:table>	