<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdNetworkListList.title"/></title>
<content tag="heading"><jecs:locale key="jbdNetworkListList.heading"/></content>
<meta name="menu" content="JbdNetworkListMenu"/>


<form action="jbdNetworkLists.html" method="get" name="bdSendChangeRegisterForm1" id="bdSendChangeRegisterForm1">

<c:if test="${sessionScope.sessionLogin.userType=='C'}">
<div class="searchBar">

	<div class="new_searchBar">	
	<jecs:title key="miMember.memberNo"/>
		<input name="userCode" type="text" value="${param.userCode}" size="10"/>
		</div>
		
	<div class="new_searchBar">			
	<jecs:title key="bdNetworkCost.startWeek"/>
		<input name="startWeek" type="text" value="${param.startWeek}" size="10"/>
		</div>
		
	<div class="new_searchBar">	
	<jecs:title key="bdNetworkCost.endWeek"/>
		<input name="endWeek" type="text" value="${param.endWeek}" size="10"/>
		</div>
		
	<div class="new_searchBar">	
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="submit" class="btn btn_sele" name="cancel" >
			<jecs:locale key="operation.button.search"/>
		</button>
		</div>
</div>
</c:if>
</form>


<c:set var="footTotalVar">
<tr >
	<td id="frontTd" align="right" class="footer" colspan="4"><jecs:locale key="bdSendRecord.totalRemittanceMoneyTotal"/></td>
	<td class="footer" align="right" >
	<b>
<fmt:formatNumber value="${sumnetworkMoney }" pattern="###,###,##0.00"></fmt:formatNumber>
</b>
</td><td class="footer" align="right" >
	<b>
<fmt:formatNumber value="${sumbalanceMoney }" pattern="###,###,##0.00"></fmt:formatNumber>
</b>
</td><td class="footer" align="right" >
	<b>
<fmt:formatNumber value="${sumdeductMoney }" pattern="###,###,##0.00"></fmt:formatNumber>
</b>
</td>
<td class="footer"   colspan="1"></td>
</tr>
</c:set>

<ec:table 
	tableId="jbdNetworkListListTable"
	items="jbdNetworkListList"
	var="jbdNetworkList"
	action="${pageContext.request.contextPath}/jbdNetworkLists.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif" footer="${footTotalVar }">
				<ec:exportCsv fileName="stock.csv" />
				<ec:exportXls fileName="stock.xls"/>
				<ec:row >
    			<ec:column property="wweek" title="bdBounsDeduct.wweek" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:weekFormat week="${jbdNetworkList.wweek }" weekType="w" />
    			</ec:column>
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="startMonth" title="jbdNetworkList.startMonth" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:monthFormat monthType="w" month="${jbdNetworkList.startMonth }" />
    			</ec:column>
    			<ec:column property="endMonth" title="jbdNetworkList.endMonth" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:monthFormat monthType="w" month="${jbdNetworkList.endMonth }" />
    			</ec:column>
    			<ec:column property="networkMoney" title="bdSendRecord.networkMoney" />
    			<ec:column property="balanceMoney" title="bdBounsDeduct.remainMoney" />
    			<ec:column property="deductMoney" title="jbdMemberLinkCalcHist.deductMoney" />
    			<ec:column property="calcWeek" title="serviceName.BdBonusCalc" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:weekFormat week="${jbdNetworkList.calcWeek }" weekType="w" />
    			</ec:column>
				</ec:row>

</ec:table>	

