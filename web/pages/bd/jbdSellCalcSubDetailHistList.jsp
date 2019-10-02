<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdSellCalcSubDetailHistList.title"/></title>
<content tag="heading"><jecs:locale key="jbdSellCalcSubDetailHistList.heading"/></content>
<meta name="menu" content="JbdSellCalcSubDetailHistMenu"/>


	<div id="titleBar" class="titleBar">
		<input type="button" class="button" name="cancel"  onclick="window.close();" value="<jecs:locale key="operation.button.close"/>" />
	</div>

<c:set var="footTotalVar">
<tr>
	<td id="frontTd" align="right" class="footer" colspan="6"><jecs:locale key="poMemberOrder.pvAmt"/></td>
	<td class="footer" align="right">
	<b>
<fmt:formatNumber value="${sumPv }" pattern="###,###,##0.00"></fmt:formatNumber>
</b>
</td>
</tr>
</c:set>

<ec:table 
	tableId="jbdSellCalcSubDetailHistListTable"
	items="jbdSellCalcSubDetailHistList"
	var="jbdSellCalcSubDetailHist"
	action="${pageContext.request.contextPath}/jbdSellCalcSubDetailHists.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="10" sortable="true" imagePath="./images/extremetable/*.gif"  footer="${footTotalVar }">
				<ec:row >
    			<ec:column property="recommendedCode" title="miMember.memberNo" />
    			<ec:column property="recommendedCompanyCode" title="busi.poMemberOrder.company" />
    			<ec:column property="nlevel" title="bdMemberBounsCalcSell.level" />
    			<ec:column property="recommendedOrderNo" title="poMemberOrder.memberOrderNo" />
    			<ec:column property="recommendedOrderClass" title="order.type.bd" >
        <jecs:code listCode="order_class" value="${fn:trim(jbdSellCalcSubDetailHist.recommendedOrderClass)}"/>
       
    			</ec:column>
    			<ec:column property="recommendedOrderType" title="pd.orderType" >
        <jecs:code listCode="po.all.order_type" value="${fn:trim(jbdSellCalcSubDetailHist.recommendedOrderType)}"/>
    			
    			</ec:column>
    			<ec:column property="pv" title="report.inPv" />
				</ec:row>

</ec:table>	
