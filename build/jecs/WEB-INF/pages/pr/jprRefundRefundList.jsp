<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="prRefundList.title"/></title>
<content tag="heading"><jecs:locale key="prRefundList.heading"/></content>
<meta name="menu" content="PrRefundMenu"/>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form name="searchForm" action="jprRefundRefunds.html" id="searchForm"">

<div class="searchBar">
<div class="new_searchBar">
<jecs:title key="prReFund.reFundOrderNo"/>
<input name="roNo" type="text" size="10" value="${param.roNo }"/>
</div>

<div class="new_searchBar">
<jecs:title key="prReFund.originateOrderNo"/>
<input name="jpoMemberOrder.memberOrderNo" type="text" value="${param['jpoMemberOrder.memberOrderNo'] }"/>
</div>
<div class="new_searchBar">
<jecs:title key="miMember.memberNo"/>
<input name="sysUser.userCode" type="text" value="${param['sysUser.userCode'] }" size="12"/>
</div>
<div class="new_searchBar">
<jecs:title key="bdBounsDeduct.createTimeScope"/>
			<input name="createBTime" id="createBTime" type="text" 
			value="${param.createBTime }" style="cursor: pointer;width:70px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			-
			<input name="createETime" id="createETime" type="text"
			value="${param.createETime }" style="cursor: pointer;width:70px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
<div class="new_searchBar">			
<jecs:title key="busi.orderReturn.refundStatus"/>
<jecs:list name="refundStatus" listCode="pr.refund" value="${param.refundStatus}" defaultValue="0"/>
</div>

<div class="new_searchBar">
<jecs:title key="jprrefund.type"/>
<jecs:list name="refundTye" listCode="jprrefund.refundtype" defaultValue="" value="${param.refundTye }" showBlankLine=""></jecs:list>
</div>

<div class="new_searchBar">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button name="search" type="submit" class="btn btn_sele" >
	<jecs:locale key="operation.button.search"/>
</button>
<button type="button" class="btn btn_long" name="batchUpdateBT"  onclick="window.location.href='batchUpdateRefund.html'" >
	<jecs:locale key="refund.batch.button"/>
</button>
</div>
</div>
</form>

	<ec:table 
		tableId="prRefundListTable"
		items="jprRefundList"
		var="prRefund"
		action="${pageContext.request.contextPath}/jprRefundRefunds.html"
		width="100%"
		retrieveRowsCallback="limit"
		autoIncludeParameters="true"
		rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">	
				<ec:row>
				<ec:column property="1" title="alCharacterKey.select1" sortable="false" styleClass="centerColumn">
					<input type="checkbox" name="selectedId"  value="${prRefund.roNo}" class="checkbox"/>
				</ec:column>
				<ec:column property="roNo" title="prReFund.reFundOrderNo" />
				<ec:column property="jpoMemberOrder.memberOrderNo" title="prReFund.originateOrderNo" />
				<ec:column property="jpoMemberOrder.orderType" title="poMemberOrder.orderType" styleClass="centerColumn">
					<jecs:code listCode="po.all.order_type" value="${!empty prRefund.jpoMemberOrder ? prRefund.jpoMemberOrder.orderType : \"\" }"/>
				</ec:column>
				<ec:column property="miMember.memberNo" title="miMember.memberNo">
					${prRefund.sysUser.jmiMember.userCode } - ${prRefund.sysUser.jmiMember.petName }
				</ec:column>
				<ec:column property="pvAmt" title="poMemberOrder.pvAmt" styleClass="numberColumn"/>
				<ec:column property="amount" title="busi.order.amount" styleClass="numberColumn">
					<fmt:formatNumber value="${prRefund.amount}" type="number" pattern="###,###,##0.00"/>
				</ec:column>
				<ec:column property="jpoMemberOrder.checkDate" title="jprrefund.settlementTime" styleClass="centerColumn">
					<jecs:period dateStr="${prRefund.jpoMemberOrder.checkDate}"/>
				</ec:column>
				<ec:column property="refundTye" title="jprrefund.type" styleClass="centerColumn">
					<jecs:code listCode="jprrefund.refundtype" value="${prRefund.refundTye}"/>
				</ec:column>
				<ec:column property="resendSpNo" title="busi.orderReturn.warehouseNo" />
				<ec:column property="createTime" title="prRefund.createTime">
					<fmt:formatDate value="${ prRefund.createTime}" pattern="yyyy-MM-dd"/>
				</ec:column>
				<ec:column property="intoStatus" title="busi.orderReturn.intoStatus" styleClass="centerColumn">
					<jecs:code listCode="pr.into" value="${prRefund.intoStatus}" />										
				</ec:column>
				<ec:column property="refundStatus" title="busi.orderReturn.refundStatus" styleClass="centerColumn">
					<jecs:code listCode="pr.refund" value="${prRefund.refundStatus}"/>
				</ec:column>
				
				<ec:column property="status" title="title.operation" sortable="false" styleClass="centerColumn" style="width:100px;">
  					<nobr>
   					<jecs:power powerCode="viewPrRefund">
					<a href="viewJprRefund.html?strAction=viewPrRefund&roNo=${prRefund.roNo}"><img border="0" src="images/newIcons/search_16.gif" alt="<jecs:locale key="function.menu.view"/>" /></a>
					</jecs:power>
   					<jecs:power powerCode="refundPrRefund">
   					<c:if test='${prRefund.locked=="N" }'>
					<a href="editJprRefundRefund.html?strAction=refundPrRefund&roNo=${prRefund.roNo}"><img border="0" src="images/newIcons/pencil_16.gif" alt="<jecs:locale key="button.edit"/>" /></a>
   					</c:if>
   					</jecs:power>
					</nobr>
				</ec:column>
			</ec:row>
	</ec:table>	
