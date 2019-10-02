<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="prRefundList.title"/></title>
<content tag="heading"><jecs:locale key="prRefundList.heading"/></content>
<meta name="menu" content="PrRefundMenu"/>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"></script>
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<script type="text/javascript">
function exportSub(){
	var obj_text = document.getElementById('exportFlag');
	obj_text.value="Y";
	document.getElementById('searchForm').action="refundExport.html";
	document.getElementById('searchForm').submit();
}
</script>
<form name="searchForm" action="jprRefundStatistic.html" id="searchForm">
<input type="hidden" name="exportFlag" id="exportFlag" value="0"/>
<div class="searchBar">

<div class="new_searchBar">
<jecs:label key="prReFund.reFundOrderNo"/>
<input name="roNo" type="text" size="10" value="${param.roNo }"/>
</div>

<div class="new_searchBar">
<jecs:label key="prReFund.originateOrderNo"/>
<input name="jpoMemberOrder.memberOrderNo" type="text" value="${param['jpoMemberOrder.memberOrderNo'] }"/>
</div>

<div class="new_searchBar">
<jecs:label key="miMember.memberNo"/>
<input name="sysUser.userCode" type="text" size="10" value="${param['sysUser.userCode'] }"/>
</div>

<div class="new_searchBar">
<jecs:label key="busi.order.intoTime"/>

			<input name="intoBTime" id="intoBTime" type="text" 
			value="${param.intoBTime }" style="cursor: pointer;width:70px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			-
			<input name="intoETime" id="intoETime" type="text"
			value="${param.intoETime }" style="cursor: pointer;width:70px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			
<div class="new_searchBar">			
<jecs:label key="busi.orderReturn.intoStatus"/>
<jecs:list name="intoStatus" listCode="pr.into" value="${param.intoStatus}" defaultValue="0" showBlankLine="true"/>
</div>

<div class="new_searchBar">
<jecs:label key="bdBounsDeduct.createTimeScope"/>
<input name="createBTime" id="createBTime" type="text" 
			value="${param.createBTime }" style="cursor: pointer;width:70px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			-
			<input name="createETime" id="createETime" type="text"
			value="${param.createETime }" style="cursor: pointer;width:70px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>

<div class="new_searchBar">			
<jecs:label key="busi.order.refundTime"/>
<input name="refundBTime" id="refundBTime" type="text" 
			value="${param.refundBTime }" style="cursor: pointer;width:70px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			-
			<input name="refundETime" id="refundETime" type="text"
			value="${param.refundETime }" style="cursor: pointer;width:70px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			
<div class="new_searchBar">			
<jecs:label key="busi.orderReturn.refundStatus"/>
<jecs:list name="refundStatus" listCode="pr.refund" value="${param.refundStatus}" defaultValue="0" showBlankLine="true"/>
</div>

<div class="new_searchBar">
<jecs:label key="busi.orderReturn.warehouseNo"/>
<input name="resendSpNo" type="text" size="10" value="${param.resendSpNo }"/>
</div>

<div class="new_searchBar">
<jecs:label key="jprrefund.locked"/>
<jecs:list name="locked" listCode="yesno" defaultValue="" value="${param.locked }" showBlankLine=""></jecs:list>
</div>

<div class="new_searchBar">
<jecs:label key="jprrefund.type"/>
<jecs:list name="refundTye" listCode="jprrefund.refundtype" defaultValue="" value="${param.refundTye }" showBlankLine=""></jecs:list>
</div>

<div class="new_searchBar">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button name="search" type="submit" onclick="document.getElementById('exportFlag').value=0" class="btn btn_sele" >
	<jecs:locale key="operation.button.search"/>
</button>
<button name="export" type="button" onclick="exportSub();" class="btn btn_long">
	<jecs:locale key="toolbar.text.xls"/>
</button>

</div>
</div>
</form>
	<ec:table 
		tableId="prRefundListTable"
		items="jprRefundList"
		var="prRefund"
		action="${pageContext.request.contextPath}/jprRefundStatistic.html"
		width="100%"
		retrieveRowsCallback="limit"
		autoIncludeParameters="true"
		rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row>
				<ec:exportXls fileName="prRefundStatistic.xls"/>
				<ec:column property="roNo" title="prReFund.reFundOrderNo" />
				<ec:column property="jpoMemberOrder.memberOrderNo" title="prReFund.originateOrderNo" />
				<ec:column property="jpoMemberOrder.orderType" title="poMemberOrder.orderType" cell="com.joymain.jecs.util.ectable.EcTableCell">
					<jecs:code listCode="po.all.order_type" value="${prRefund.jpoMemberOrder.orderType }"/>
				</ec:column>
				<ec:column property="sysUser.jmiMember.userCode" title="miMember.memberNo">
					${prRefund.sysUser.jmiMember.userCode } - ${prRefund.sysUser.jmiMember.petName }
				</ec:column>
				<ec:column property="pvAmt" title="poMemberOrder.pvAmt" styleClass="numberColumn"/>
				<ec:column property="amount" title="busi.order.amount" styleClass="numberColumn" cell="number" format="###,###,##0.00"/>
				<ec:column property="jpoCheckDate" title="jprrefund.settlementTime" styleClass="centerColumn"/>
				<ec:column property="prRefund.jpoMemberOrder.locked" title="jprrefund.locked" cell="com.joymain.jecs.util.ectable.EcTableCell" styleClass="centerColumn">
					<jecs:code listCode="yesno" value="${prRefund.jpoMemberOrder.locked}"/>
				</ec:column>  
				<ec:column property="refundTye" title="jprrefund.type" styleClass="centerColumn">
					<jecs:code listCode="jprrefund.refundtype" value="${prRefund.refundTye}"/>
				</ec:column>
				<ec:column property="resendSpNo" title="busi.orderReturn.warehouseNo" />
				<ec:column property="createTime" title="prRefund.createTime">
					<fmt:formatDate value="${ prRefund.createTime}" pattern="yyyy-MM-dd"/>
				</ec:column>
				<ec:column property="intoStatus" title="busi.orderReturn.intoStatus" cell="com.joymain.jecs.util.ectable.EcTableCell" styleClass="centerColumn">
					<jecs:code listCode="pr.into" value="${prRefund.intoStatus}" />										
				</ec:column>
				<ec:column property="refundStatus" title="busi.orderReturn.refundStatus" cell="com.joymain.jecs.util.ectable.EcTableCell" styleClass="centerColumn">
					<jecs:code listCode="pr.refund" value="${prRefund.refundStatus}"/>
				</ec:column>
				
				
				<ec:column property="status" title="title.operation" sortable="false" styleClass="centerColumn" style="width:100px;" viewsDenied="xls">
  					<nobr>
   					<jecs:power powerCode="viewPrRefund">
					<a href="viewJprRefund.html?strAction=viewPrRefund&roNo=${prRefund.roNo}"><img border="0" src="images/newIcons/search_16.gif" alt="<jecs:locale key="function.menu.view"/>" /></a>
					</jecs:power>
					</nobr>
				</ec:column>
			</ec:row>
	</ec:table>	
<ec:table 
	tableId="pmProductListTable"
	items="jpmProductList"
	var="pmProduct"
	action="${pageContext.request.contextPath}/prAssistReturnStatistics.html"
	width="100%"
	showPagination="false" sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:row>
    			<ec:column property="product_no" title="busi.product.productno"/>
    			<ec:column property="product_name" title="pmProduct.productName"/>
    			<ec:column property="qty" title="poOrder.count"  styleClass="numberColumn" cell="number" format="###,###,##0" calc="total" calcTitle="poOrder.amtCount"/>
    		<%--  	<ec:column property="pv" title="busi.direct.pv"  styleClass="numberColumn" cell="number" format="###,###,##0" calc="total" calcTitle="poOrder.amtCount"/>	 --%>
    			<ec:column property="pv" title="busi.direct.pv"  styleClass="numberColumn" cell="number" format="###,###,##0.00" calc="total" calcTitle="poOrder.amtCount"/>
    			<ec:column property="price" title="pd.price"  styleClass="numberColumn" cell="number" format="###,###,##0.00" calc="total" calcTitle="poOrder.amtCount"/>
    		<%--   	<ec:column property="totalpv" title="poMemberOrder.pvAmt"  styleClass="numberColumn" cell="number" format="###,###,##0" calc="total" calcTitle="poOrder.amtCount"/> --%>
    			<ec:column property="totalpv" title="poMemberOrder.pvAmt"  styleClass="numberColumn" cell="number" format="###,###,##0.00" calc="total" calcTitle="poOrder.amtCount"/>
    			<ec:column property="totalprice" title="busi.order.amount"  styleClass="numberColumn" cell="number" format="###,###,##0.00" calc="total" calcTitle="poOrder.amtCount"/>
				</ec:row>

</ec:table>
	