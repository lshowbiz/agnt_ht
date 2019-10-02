<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderList.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberOrderList.heading"/></content>
<meta name="menu" content="JpoMemberOrderMenu"/>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form action="jpoMemberOrderChangeDateStatistic.html" method="get" name="searchForm" id="searchForm">
<div class="searchBar">
<div class="new_searchBar">
<jecs:label key="bdBounsDeduct.wweek"/>
<input name="formatedWeek" type="text" size="6" value="${param.formatedWeek }"/>
</div>

<div class="new_searchBar">
<label><jecs:locale key="saFiIncomeReport.dataSection"/></label>

<input name="createBTime" id="createBTime" type="text" 
			value="${param.createBTime }" style="cursor: pointer;width:70px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			-
			<input name="createETime" id="createETime" type="text"
			value="${param.createETime }" style="cursor: pointer;width:70px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>

<div class="new_searchBar">			
<button name="search" class="btn btn_sele" type="submit" >
	<jecs:locale key="operation.button.search"/>
</button>
</div>
</div>
</form>

<ec:table
	tableId="jpoMemberOrderListTable1"
	items="jpoMemberOrderList1"
	var="jpoMemberOrder1"
	width="100%"
	showPagination="false"
	rowsDisplayed="0" sortable="false" imagePath="./images/extremetable/*.gif">
			<ec:row>
    			<ec:column property="memberOrderNo" title="poMemberOrder.memberOrderNo" />
    			<ec:column property="orderType" title="poMemberOrder.orderType" styleClass="centerColumn">
    				<jecs:code listCode="po.all.order_type" value="${jpoMemberOrder1.orderType}"/>
    			</ec:column>
    			<ec:column property="sysUser.userCode" title="miMember.memberNo" />
    			<ec:column property="amount" title="busi.order.amount" styleClass="numberColumn" cell="number" format="###,###,##0.00" calc="total" calcTitle="poOrder.amtCount"/>
    			<ec:column property="jjAmount" title="busi.order.jjAmount"  styleClass="numberColumn" cell="number" format="###,###,##0.00" calc="total" calcTitle="poOrder.amtCount"/>
    			<ec:column property="pvAmt" title="poMemberOrder.pvAmt" styleClass="numberColumn" cell="number" format="###,###,##0.00" calc="total" calcTitle="poOrder.amtCount"/>
    			<ec:column property="checkTime" title="logType.BC" styleClass="centerColumn">
    				<fmt:formatDate value="${jpoMemberOrder1.checkTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
    			</ec:column>
    			<ec:column property="checkTime" title="poMemberOrder.period1" styleClass="centerColumn">
    				<jecs:period dateStr="${jpoMemberOrder1.checkTime }"/>
    			</ec:column>
    			<ec:column property="checkDate" title="logType.C" styleClass="centerColumn">
    				<fmt:formatDate value="${jpoMemberOrder1.checkDate }" pattern="yyyy-MM-dd"/>
    			</ec:column>
    			<ec:column property="checkDate" title="poMemberOrder.period" styleClass="centerColumn">
    				<jecs:period dateStr="${jpoMemberOrder1.checkDate}"/>
    			</ec:column>
    			<ec:column property="1" title="bdReconsumMoneyReport.typeCH" styleClass="centerColumn">
    				<jecs:locale key="ps.flittingIn"/>
    			</ec:column>
			</ec:row>
</ec:table>	

<ec:table 
	tableId="jpoMemberOrderListTable2"
	items="jpoMemberOrderList2"
	var="jpoMemberOrder2"
	width="100%"
	showPagination="false"
	rowsDisplayed="0" sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:row>
    			<ec:column property="memberOrderNo" title="poMemberOrder.memberOrderNo" />
    			<ec:column property="orderType" title="poMemberOrder.orderType" styleClass="centerColumn">
    				<jecs:code listCode="po.all.order_type" value="${jpoMemberOrder2.orderType}"/>
    			</ec:column>
    			<ec:column property="sysUser.userCode" title="miMember.memberNo" />
    			<ec:column property="amount" title="busi.order.amount" styleClass="numberColumn" cell="number" format="###,###,##0.00" calc="total" calcTitle="poOrder.amtCount"/>
    			<ec:column property="jjAmount" title="busi.order.jjAmount"  styleClass="numberColumn" cell="number" format="###,###,##0.00" calc="total" calcTitle="poOrder.amtCount"/>
    			<ec:column property="pvAmt" title="poMemberOrder.pvAmt" styleClass="numberColumn" cell="number" format="###,###,##0.00" calc="total" calcTitle="poOrder.amtCount"/>
    			<ec:column property="checkTime" title="logType.BC" styleClass="centerColumn">
    				<fmt:formatDate value="${jpoMemberOrder2.checkTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
    			</ec:column>
    			<ec:column property="checkTime" title="poMemberOrder.period1" styleClass="centerColumn">
    				<jecs:period dateStr="${jpoMemberOrder2.checkTime }"/>
    			</ec:column>
    			<ec:column property="checkDate" title="logType.C" styleClass="centerColumn">
    				<fmt:formatDate value="${jpoMemberOrder2.checkDate }" pattern="yyyy-MM-dd"/>
    			</ec:column>
    			<ec:column property="checkDate" title="poMemberOrder.period" styleClass="centerColumn">
    				<jecs:period dateStr="${jpoMemberOrder2.checkDate}"/>
    			</ec:column>
    			<ec:column property="1" title="bdReconsumMoneyReport.typeCH" styleClass="centerColumn">
    				<jecs:locale key="ps.flittingOut"/>
    			</ec:column>
				</ec:row>
</ec:table>	