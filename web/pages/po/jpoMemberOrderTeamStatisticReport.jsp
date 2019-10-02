<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<style>
.bottomLine{
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: #000000;
}
.topBottomLine{
	border-top-width: 1px;
	border-top-style: solid;
	border-top-color: #000000;
}
</style>
<%

/*<OBJECT id=WebBrowser classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 width=0> </OBJECT>

<input type=button value="打印预览" onclick=document.all.WebBrowser.ExecWB(7,1)>

<input type=button value="页面设置" onclick=document.all.WebBrowser.ExecWB(8,1)>

<input type=button value="打印本页" onclick=document.all.WebBrowser.ExecWB(6,1)>*/

%>
<script type="text/javascript">
<!--
function saveCode() {
        var winname = window.open('', '_blank', 'top=10000');
        var strHTML = document.all.printTable.innerHTML;
        winname.document.open('text/html', 'replace');
        winname.document.writeln(strHTML);
        winname.document.execCommand('saveas','','excel.xls');
        winname.close();
}
-->
</script>
<form>
	<div id="titleBar" class="noPrint">
	<input type="button" name="btnPrint" value="<jecs:locale key="button.print"/>" class="button" onclick="window.print()"/>
	<input type="button" name="btnCancel" value="<jecs:locale key="operation.button.cancel"/>" class="button" onclick="window.history.back();"/>
	<input type="button" name="save" value="<jecs:locale key="operation.button.save"/>" class="button" onclick="saveCode()"/>
	</div>
	
	<div id="printTable" class="portrait">
		<table width="90%" border="0" cellpadding="3" cellspacing="1" class="print">
			<tr>
				<td colspan="6" align="center" class="title"><jecs:locale key="report.bdSend.Statistic.selltitle"/></td>
			</tr>
			<tr>
			    <td colspan="6"><table width="100%" border="0" cellspacing="1" cellpadding="0">
			      <tr>
			        <td align="center"><jecs:locale key="bdBounsDeduct.wweek"/>[ ${wWeeks } ]</td>
			        <td align="center"><jecs:locale key="saFiIncomeReport.dataSection"/> ${startTime } - ${endTime }</td>
			      </tr>
			    </table></td>
			</tr>
			
			<tr>
				<td class="bottomLine"><jecs:locale key="saFiIncomeReport.item.name"/></td>
				<td class="bottomLine"><jecs:locale key="pdWarehouseStockTrace.orderType"/></td>
				<td class="bottomLine"><jecs:locale key="pmProduct.productName"/></td>
				<td class="bottomLine" align="right"><jecs:locale key="pd.qty"/></td>
				<td class="bottomLine" align="right"><jecs:locale key="pd.price"/></td>
				<td class="bottomLine" align="right"><jecs:locale key="busi.direct.pv"/></td>
				<td class="bottomLine" align="right"><jecs:locale key="busi.finance.amount"/></td>
			</tr>
			<!-- 会员订单统计开始 -->
				<c:set var="poMemberOrderQtyTotal" value="0"/>
				<c:set var="poMemberOrderPriceTotal" value="0"/>
				<c:set var="poMemberOrderPvTotal" value="0"/>
				<c:set var="poMemberOrderAmountTotal" value="0"/>
				
				<!-- 非重销单统计开始 -->
				<c:set var="poMemberOrderQtyTotal1" value="0"/>
				<c:set var="poMemberOrderPriceTotal1" value="0"/>
				<c:set var="poMemberOrderPvTotal1" value="0"/>
				<c:set var="poMemberOrderAmountTotal1" value="0"/>
				<c:forEach items="${poMemberOrderList}" var="poMemberOrderMap" varStatus="bdOrderStatus">
					<c:if test="${poMemberOrderMap.order_type=='1' }">
					<tr>
					<c:if test="${bdOrderStatus.count==1 }">
					    <td><jecs:locale key="function.menu.memberOrderStatistics"/></td>
					</c:if>
					<c:if test="${bdOrderStatus.count!=1 }">
					    <td>&nbsp;</td>
					</c:if>
					    <td><jecs:code listCode="po.all.order_type" value="${poMemberOrderMap.order_type}"/></td>
					    <td>${poMemberOrderMap.product_no} - ${poMemberOrderMap.product_name}</td>
					    <td align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderMap.qty_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.price }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.pv_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.amount }</fmt:formatNumber></td>
					    <c:set var="poMemberOrderQtyTotal1" value="${poMemberOrderQtyTotal1+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal1" value="${poMemberOrderPriceTotal1+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal1" value="${poMemberOrderPvTotal1+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal1" value="${poMemberOrderAmountTotal1+poMemberOrderMap.amount}"/>
					    <c:set var="poMemberOrderQtyTotal" value="${poMemberOrderQtyTotal+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal" value="${poMemberOrderPriceTotal+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal" value="${poMemberOrderPvTotal+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal" value="${poMemberOrderAmountTotal+poMemberOrderMap.amount}"/>
					</tr>
					</c:if>
				</c:forEach>
				<c:if test='${poMemberOrderQtyTotal1 != 0 }'>
				<tr>
					<td class="topBottomLine" colspan="3" align="right"><span class="bigger"><jecs:locale key="report.subtotal"/>:</span></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderQtyTotal1 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPriceTotal1 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPvTotal1 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderAmountTotal1 }</fmt:formatNumber></td>
				</tr>
				<tr>
					<td class="topBottomLine" colspan="3">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
				</tr>
				</c:if>
				<!-- 非重销单统计结束 -->
				<!-- 升级单统计开始 -->
				<c:set var="poMemberOrderQtyTotal2" value="0"/>
				<c:set var="poMemberOrderPriceTotal2" value="0"/>
				<c:set var="poMemberOrderPvTotal2" value="0"/>
				<c:set var="poMemberOrderAmountTotal2" value="0"/>
				<c:forEach items="${poMemberOrderList}" var="poMemberOrderMap" varStatus="bdOrderStatus">
					<c:if test="${poMemberOrderMap.order_type=='2' }">
					<tr>
					<c:if test="${bdOrderStatus.count==1 }">
					    <td><jecs:locale key="function.menu.memberOrderStatistics"/></td>
					</c:if>
					<c:if test="${bdOrderStatus.count!=1 }">
					    <td>&nbsp;</td>
					</c:if>
					    <td><jecs:code listCode="po.all.order_type" value="${poMemberOrderMap.order_type}"/></td>
					    <td>${poMemberOrderMap.product_no} - ${poMemberOrderMap.product_name}</td>
					    <td align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderMap.qty_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.price }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.pv_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.amount }</fmt:formatNumber></td>
					    <c:set var="poMemberOrderQtyTotal2" value="${poMemberOrderQtyTotal2+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal2" value="${poMemberOrderPriceTotal2+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal2" value="${poMemberOrderPvTotal2+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal2" value="${poMemberOrderAmountTotal2+poMemberOrderMap.amount}"/>
					    <c:set var="poMemberOrderQtyTotal" value="${poMemberOrderQtyTotal+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal" value="${poMemberOrderPriceTotal+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal" value="${poMemberOrderPvTotal+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal" value="${poMemberOrderAmountTotal+poMemberOrderMap.amount}"/>
					</tr>
					</c:if>
				</c:forEach>
				<c:if test='${poMemberOrderQtyTotal2 != 0 }'>
				<tr>
					<td class="topBottomLine" colspan="3" align="right"><span class="bigger"><jecs:locale key="report.subtotal"/>:</span></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderQtyTotal2 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPriceTotal2 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPvTotal2 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderAmountTotal2 }</fmt:formatNumber></td>
				</tr>
				<tr>
					<td class="topBottomLine" colspan="3">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
				</tr>
				</c:if>
				<!-- 升级销单统计结束 -->
				<!-- 顾问首单统计开始 -->
				<c:set var="poMemberOrderQtyTotal42" value="0"/>
				<c:set var="poMemberOrderPriceTotal42" value="0"/>
				<c:set var="poMemberOrderPvTotal42" value="0"/>
				<c:set var="poMemberOrderAmountTotal42" value="0"/>
				<c:forEach items="${poMemberOrderList}" var="poMemberOrderMap" varStatus="bdOrderStatus">
					<c:if test="${poMemberOrderMap.order_type=='42' }">
					<tr>
					<c:if test="${bdOrderStatus.count==1 }">
					    <td><jecs:locale key="function.menu.memberOrderStatistics"/></td>
					</c:if>
					<c:if test="${bdOrderStatus.count!=1 }">
					    <td>&nbsp;</td>
					</c:if>
					    <td><jecs:code listCode="po.all.order_type" value="${poMemberOrderMap.order_type}"/></td>
					    <td>${poMemberOrderMap.product_no} - ${poMemberOrderMap.product_name}</td>
					    <td align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderMap.qty_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.price }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.pv_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.amount }</fmt:formatNumber></td>
					    <c:set var="poMemberOrderQtyTotal42" value="${poMemberOrderQtyTotal42+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal42" value="${poMemberOrderPriceTotal42+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal42" value="${poMemberOrderPvTotal42+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal42" value="${poMemberOrderAmountTotal42+poMemberOrderMap.amount}"/>
					    <c:set var="poMemberOrderQtyTotal" value="${poMemberOrderQtyTotal+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal" value="${poMemberOrderPriceTotal+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal" value="${poMemberOrderPvTotal+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal" value="${poMemberOrderAmountTotal+poMemberOrderMap.amount}"/>
					</tr>
					</c:if>
				</c:forEach>
				<c:if test='${poMemberOrderQtyTotal42 != 0 }'>
				<tr>
					<td class="topBottomLine" colspan="3" align="right"><span class="bigger"><jecs:locale key="report.subtotal"/>:</span></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderQtyTotal42 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPriceTotal42 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPvTotal42 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderAmountTotal42 }</fmt:formatNumber></td>
				</tr>
				<tr>
					<td class="topBottomLine" colspan="3">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
				</tr>
				</c:if>
				<!-- 顾问首单统计结束 -->
				<!-- 顾问重消单统计开始 -->
				<c:set var="poMemberOrderQtyTotal43" value="0"/>
				<c:set var="poMemberOrderPriceTotal43" value="0"/>
				<c:set var="poMemberOrderPvTotal43" value="0"/>
				<c:set var="poMemberOrderAmountTotal43" value="0"/>
				<c:forEach items="${poMemberOrderList}" var="poMemberOrderMap" varStatus="bdOrderStatus">
					<c:if test="${poMemberOrderMap.order_type=='43' }">
					<tr>
					<c:if test="${bdOrderStatus.count==1 }">
					    <td><jecs:locale key="function.menu.memberOrderStatistics"/></td>
					</c:if>
					<c:if test="${bdOrderStatus.count!=1 }">
					    <td>&nbsp;</td>
					</c:if>
					    <td><jecs:code listCode="po.all.order_type" value="${poMemberOrderMap.order_type}"/></td>
					    <td>${poMemberOrderMap.product_no} - ${poMemberOrderMap.product_name}</td>
					    <td align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderMap.qty_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.price }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.pv_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.amount }</fmt:formatNumber></td>
					    <c:set var="poMemberOrderQtyTotal43" value="${poMemberOrderQtyTotal43+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal43" value="${poMemberOrderPriceTotal43+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal43" value="${poMemberOrderPvTotal43+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal43" value="${poMemberOrderAmountTotal43+poMemberOrderMap.amount}"/>
					    <c:set var="poMemberOrderQtyTotal" value="${poMemberOrderQtyTotal+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal" value="${poMemberOrderPriceTotal+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal" value="${poMemberOrderPvTotal+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal" value="${poMemberOrderAmountTotal+poMemberOrderMap.amount}"/>
					</tr>
					</c:if>
				</c:forEach>
				<c:if test='${poMemberOrderQtyTotal43 != 0 }'>
				<tr>
					<td class="topBottomLine" colspan="3" align="right"><span class="bigger"><jecs:locale key="report.subtotal"/>:</span></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderQtyTotal43 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPriceTotal43 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPvTotal43 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderAmountTotal43 }</fmt:formatNumber></td>
				</tr>
				<tr>
					<td class="topBottomLine" colspan="3">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
				</tr>
				</c:if>
				<!-- 顾问重消单统计结束 -->
				
				
				<!-- 续约单统计开始 -->
				<c:set var="poMemberOrderQtyTotal3" value="0"/>
				<c:set var="poMemberOrderPriceTotal3" value="0"/>
				<c:set var="poMemberOrderPvTotal3" value="0"/>
				<c:set var="poMemberOrderAmountTotal3" value="0"/>
				<c:forEach items="${poMemberOrderList}" var="poMemberOrderMap" varStatus="bdOrderStatus">
					<c:if test="${poMemberOrderMap.order_type=='3' }">
					<tr>
					<c:if test="${bdOrderStatus.count==1 }">
					    <td><jecs:locale key="function.menu.memberOrderStatistics"/></td>
					</c:if>
					<c:if test="${bdOrderStatus.count!=1 }">
					    <td>&nbsp;</td>
					</c:if>
					    <td><jecs:code listCode="po.all.order_type" value="${poMemberOrderMap.order_type}"/></td>
					    <td>${poMemberOrderMap.product_no} - ${poMemberOrderMap.product_name}</td>
					    <td align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderMap.qty_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.price }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.pv_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.amount }</fmt:formatNumber></td>
					    <c:set var="poMemberOrderQtyTotal3" value="${poMemberOrderQtyTotal3+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal3" value="${poMemberOrderPriceTotal3+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal3" value="${poMemberOrderPvTotal3+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal3" value="${poMemberOrderAmountTotal3+poMemberOrderMap.amount}"/>
					    <c:set var="poMemberOrderQtyTotal" value="${poMemberOrderQtyTotal+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal" value="${poMemberOrderPriceTotal+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal" value="${poMemberOrderPvTotal+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal" value="${poMemberOrderAmountTotal+poMemberOrderMap.amount}"/>
					</tr>
					</c:if>
				</c:forEach>
				<c:if test='${poMemberOrderQtyTotal3 != 0 }'>
				<tr>
					<td class="topBottomLine" colspan="3" align="right"><span class="bigger"><jecs:locale key="report.subtotal"/>:</span></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderQtyTotal3 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPriceTotal3 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPvTotal3 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderAmountTotal3 }</fmt:formatNumber></td>
				</tr>
				<tr>
					<td class="topBottomLine" colspan="3">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
				</tr>
				</c:if>
				<!-- 续约单统计结束 -->
				
				<!-- 重销单统计开始 -->
				<c:set var="poMemberOrderQtyTotal4" value="0"/>
				<c:set var="poMemberOrderPriceTotal4" value="0"/>
				<c:set var="poMemberOrderPvTotal4" value="0"/>
				<c:set var="poMemberOrderAmountTotal4" value="0"/>
				<c:forEach items="${poMemberOrderList}" var="poMemberOrderMap" varStatus="bdOrderStatus">
					<c:if test="${poMemberOrderMap.order_type=='4' }">
					<tr>
					<c:if test="${bdOrderStatus.count==1 }">
					    <td><jecs:locale key="function.menu.memberOrderStatistics"/></td>
					</c:if>
					<c:if test="${bdOrderStatus.count!=1 }">
					    <td>&nbsp;</td>
					</c:if>
					    <td><jecs:code listCode="po.all.order_type" value="${poMemberOrderMap.order_type}"/></td>
					    <td>${poMemberOrderMap.product_no} - ${poMemberOrderMap.product_name}</td>
					    <td align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderMap.qty_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.price }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.pv_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.amount }</fmt:formatNumber></td>
					    <c:set var="poMemberOrderQtyTotal4" value="${poMemberOrderQtyTotal4+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal4" value="${poMemberOrderPriceTotal4+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal4" value="${poMemberOrderPvTotal4+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal4" value="${poMemberOrderAmountTotal4+poMemberOrderMap.amount}"/>
					    <c:set var="poMemberOrderQtyTotal" value="${poMemberOrderQtyTotal+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal" value="${poMemberOrderPriceTotal+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal" value="${poMemberOrderPvTotal+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal" value="${poMemberOrderAmountTotal+poMemberOrderMap.amount}"/>
					</tr>
					</c:if>
				</c:forEach>
				<c:if test='${poMemberOrderQtyTotal4 != 0 }'>
				<tr>
					<td class="topBottomLine" colspan="3" align="right"><span class="bigger"><jecs:locale key="report.subtotal"/>:</span></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderQtyTotal4 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPriceTotal4 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPvTotal4 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderAmountTotal4 }</fmt:formatNumber></td>
				</tr>
				<tr>
					<td class="topBottomLine" colspan="3">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
				</tr>
				</c:if>
				<!-- 重销销单统计结束 -->
				<!-- 辅销单统计开始 -->
				<c:set var="poMemberOrderQtyTotal5" value="0"/>
				<c:set var="poMemberOrderPriceTotal5" value="0"/>
				<c:set var="poMemberOrderPvTotal5" value="0"/>
				<c:set var="poMemberOrderAmountTotal5" value="0"/>
				<c:forEach items="${poMemberOrderList}" var="poMemberOrderMap" varStatus="bdOrderStatus">
					<c:if test="${poMemberOrderMap.order_type=='5' }">
					<tr>
					<c:if test="${bdOrderStatus.count==1 }">
					    <td><jecs:locale key="function.menu.memberOrderStatistics"/></td>
					</c:if>
					<c:if test="${bdOrderStatus.count!=1 }">
					    <td>&nbsp;</td>
					</c:if>
					    <td><jecs:code listCode="po.all.order_type" value="${poMemberOrderMap.order_type}"/></td>
					    <td>${poMemberOrderMap.product_no} - ${poMemberOrderMap.product_name}</td>
					    <td align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderMap.qty_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.price }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.pv_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.amount }</fmt:formatNumber></td>
					    <c:set var="poMemberOrderQtyTotal5" value="${poMemberOrderQtyTotal5+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal5" value="${poMemberOrderPriceTotal5+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal5" value="${poMemberOrderPvTotal5+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal5" value="${poMemberOrderAmountTotal5+poMemberOrderMap.amount}"/>
					    <c:set var="poMemberOrderQtyTotal" value="${poMemberOrderQtyTotal+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal" value="${poMemberOrderPriceTotal+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal" value="${poMemberOrderPvTotal+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal" value="${poMemberOrderAmountTotal+poMemberOrderMap.amount}"/>
					</tr>
					</c:if>
				</c:forEach>
				<c:if test='${poMemberOrderQtyTotal5 != 0 }'>
				<tr>
					<td class="topBottomLine" colspan="3" align="right"><span class="bigger"><jecs:locale key="report.subtotal"/>:</span></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderQtyTotal5 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPriceTotal5 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPvTotal5 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderAmountTotal5 }</fmt:formatNumber></td>
				</tr>
				<tr>
					<td class="topBottomLine" colspan="3">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
				</tr>
				</c:if>
				<!-- 辅销单统计结束 -->
							<!-- 代金券换购单统计开始 -->
				<c:set var="poMemberOrderQtyTotal93" value="0"/>
				<c:set var="poMemberOrderPriceTotal93" value="0"/>
				<c:set var="poMemberOrderPvTotal93" value="0"/>
				<c:set var="poMemberOrderAmountTotal93" value="0"/>
				<c:forEach items="${poMemberOrderList}" var="poMemberOrderMap" varStatus="bdOrderStatus">
					<c:if test="${poMemberOrderMap.order_type=='93' }">
					<tr>
					    <td><jecs:locale key="function.menu.memberOrderStatistics"/></td>
					    <td><jecs:code listCode="po.all.order_type" value="${poMemberOrderMap.order_type}"/></td>
					    <td>${poMemberOrderMap.product_no} - ${poMemberOrderMap.product_name}</td>
					    <td align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderMap.qty_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.price }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.pv_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.amount }</fmt:formatNumber></td>
					    <c:set var="poMemberOrderQtyTotal93" value="${poMemberOrderQtyTotal93+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal93" value="${poMemberOrderPriceTotal93+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal93" value="${poMemberOrderPvTotal93+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal93" value="${poMemberOrderAmountTotal93+poMemberOrderMap.amount}"/>
					    <c:set var="poMemberOrderQtyTotal" value="${poMemberOrderQtyTotal+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal" value="${poMemberOrderPriceTotal+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal" value="${poMemberOrderPvTotal+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal" value="${poMemberOrderAmountTotal+poMemberOrderMap.amount}"/>
					</tr>
					</c:if>
				</c:forEach>
				<c:if test='${poMemberOrderQtyTotal93 != 0 }'>
				<tr>
					<td class="topBottomLine" colspan="3" align="right"><span class="bigger"><jecs:locale key="report.subtotal"/>:</span></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderQtyTotal93 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPriceTotal93 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPvTotal93 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderAmountTotal93 }</fmt:formatNumber></td>
				</tr>
				<tr>
					<td class="topBottomLine" colspan="3">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
				</tr> 
				</c:if>
				<!-- 代金券换购单统计结束 -->			
				
				
				<!-- 店辅首购单统计开始 -->
				<c:set var="poMemberOrderQtyTotal6" value="0"/>
				<c:set var="poMemberOrderPriceTotal6" value="0"/>
				<c:set var="poMemberOrderPvTotal6" value="0"/>
				<c:set var="poMemberOrderAmountTotal6" value="0"/>
				<c:forEach items="${poMemberOrderList}" var="poMemberOrderMap" varStatus="bdOrderStatus">
					<c:if test="${poMemberOrderMap.order_type=='6' }">
					<tr>
					<c:if test="${bdOrderStatus.count==1 }">
					    <td><jecs:locale key="function.menu.memberOrderStatistics"/></td>
					</c:if>
					<c:if test="${bdOrderStatus.count!=1 }">
					    <td>&nbsp;</td>
					</c:if>
					    <td><jecs:code listCode="po.all.order_type" value="${poMemberOrderMap.order_type}"/></td>
					    <td>${poMemberOrderMap.product_no} - ${poMemberOrderMap.product_name}</td>
					    <td align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderMap.qty_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.price }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.pv_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.amount }</fmt:formatNumber></td>
					    <c:set var="poMemberOrderQtyTotal6" value="${poMemberOrderQtyTotal6+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal6" value="${poMemberOrderPriceTotal6+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal6" value="${poMemberOrderPvTotal6+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal6" value="${poMemberOrderAmountTotal6+poMemberOrderMap.amount}"/>
					    <c:set var="poMemberOrderQtyTotal" value="${poMemberOrderQtyTotal+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal" value="${poMemberOrderPriceTotal+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal" value="${poMemberOrderPvTotal+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal" value="${poMemberOrderAmountTotal+poMemberOrderMap.amount}"/>
					</tr>
					</c:if>
				</c:forEach>
				<c:if test='${poMemberOrderQtyTotal6 != 0 }'>
				<tr>
					<td class="topBottomLine" colspan="3" align="right"><span class="bigger"><jecs:locale key="report.subtotal"/>:</span></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderQtyTotal6 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPriceTotal6 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPvTotal6 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderAmountTotal6 }</fmt:formatNumber></td>
				</tr>
				<tr>
					<td class="topBottomLine" colspan="3">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
				</tr>
				</c:if>
				<!-- 店辅首购销单统计结束 -->
				<!-- 店辅重销单统计开始 -->
				<c:set var="poMemberOrderQtyTotal9" value="0"/>
				<c:set var="poMemberOrderPriceTotal9" value="0"/>
				<c:set var="poMemberOrderPvTotal9" value="0"/>
				<c:set var="poMemberOrderAmountTotal9" value="0"/>
				<c:forEach items="${poMemberOrderList}" var="poMemberOrderMap" varStatus="bdOrderStatus">
					<c:if test="${poMemberOrderMap.order_type=='9' }">
					<tr>
					<c:if test="${bdOrderStatus.count==1 }">
					    <td><jecs:locale key="function.menu.memberOrderStatistics"/></td>
					</c:if>
					<c:if test="${bdOrderStatus.count!=1 }">
					    <td>&nbsp;</td>
					</c:if>
					    <td><jecs:code listCode="po.all.order_type" value="${poMemberOrderMap.order_type}"/></td>
					    <td>${poMemberOrderMap.product_no} - ${poMemberOrderMap.product_name}</td>
					    <td align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderMap.qty_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.price }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.pv_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.amount }</fmt:formatNumber></td>
					    <c:set var="poMemberOrderQtyTotal9" value="${poMemberOrderQtyTotal9+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal9" value="${poMemberOrderPriceTotal9+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal9" value="${poMemberOrderPvTotal9+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal9" value="${poMemberOrderAmountTotal9+poMemberOrderMap.amount}"/>
					    <c:set var="poMemberOrderQtyTotal" value="${poMemberOrderQtyTotal+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal" value="${poMemberOrderPriceTotal+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal" value="${poMemberOrderPvTotal+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal" value="${poMemberOrderAmountTotal+poMemberOrderMap.amount}"/>
					</tr>
					</c:if>
				</c:forEach>
				<c:if test='${poMemberOrderQtyTotal9 != 0 }'>
				<tr>
					<td class="topBottomLine" colspan="3" align="right"><span class="bigger"><jecs:locale key="report.subtotal"/>:</span></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderQtyTotal9 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPriceTotal9 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPvTotal9 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderAmountTotal9 }</fmt:formatNumber></td>
				</tr>
				<tr>
					<td class="topBottomLine" colspan="3">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
				</tr>
				</c:if>
				<!-- 店辅重销销单统计结束 -->
				<!-- 二级店铺首购单统计开始 -->
				<c:set var="poMemberOrderQtyTotal11" value="0"/>
				<c:set var="poMemberOrderPriceTotal11" value="0"/>
				<c:set var="poMemberOrderPvTotal11" value="0"/>
				<c:set var="poMemberOrderAmountTotal11" value="0"/>
				<c:forEach items="${poMemberOrderList}" var="poMemberOrderMap" varStatus="bdOrderStatus">
					<c:if test="${poMemberOrderMap.order_type=='11' }">
					<tr>
					<c:if test="${bdOrderStatus.count==1 }">
					    <td><jecs:locale key="function.menu.memberOrderStatistics"/></td>
					</c:if>
					<c:if test="${bdOrderStatus.count!=1 }">
					    <td>&nbsp;</td>
					</c:if>
					    <td><jecs:code listCode="po.all.order_type" value="${poMemberOrderMap.order_type}"/></td>
					    <td>${poMemberOrderMap.product_no} - ${poMemberOrderMap.product_name}</td>
					    <td align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderMap.qty_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.price }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.pv_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.amount }</fmt:formatNumber></td>
					    <c:set var="poMemberOrderQtyTotal11" value="${poMemberOrderQtyTotal11+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal11" value="${poMemberOrderPriceTotal11+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal11" value="${poMemberOrderPvTotal11+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal11" value="${poMemberOrderAmountTotal11+poMemberOrderMap.amount}"/>
					    <c:set var="poMemberOrderQtyTotal" value="${poMemberOrderQtyTotal+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal" value="${poMemberOrderPriceTotal+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal" value="${poMemberOrderPvTotal+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal" value="${poMemberOrderAmountTotal+poMemberOrderMap.amount}"/>
					</tr>
					</c:if>
				</c:forEach>
				<c:if test='${poMemberOrderQtyTotal11 != 0 }'>
				<tr>
					<td class="topBottomLine" colspan="3" align="right"><span class="bigger"><jecs:locale key="report.subtotal"/>:</span></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderQtyTotal11 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPriceTotal11 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPvTotal11 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderAmountTotal11 }</fmt:formatNumber></td>
				</tr>
				<tr>
					<td class="topBottomLine" colspan="3">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
				</tr>
				</c:if>
				<!-- 二级店铺首购单统计开始 -->
				<!-- 二级店铺升级单统计开始 -->
				<c:set var="poMemberOrderQtyTotal12" value="0"/>
				<c:set var="poMemberOrderPriceTotal12" value="0"/>
				<c:set var="poMemberOrderPvTotal12" value="0"/>
				<c:set var="poMemberOrderAmountTotal12" value="0"/>
				<c:forEach items="${poMemberOrderList}" var="poMemberOrderMap" varStatus="bdOrderStatus">
					<c:if test="${poMemberOrderMap.order_type=='12' }">
					<tr>
					<c:if test="${bdOrderStatus.count==1 }">
					    <td><jecs:locale key="function.menu.memberOrderStatistics"/></td>
					</c:if>
					<c:if test="${bdOrderStatus.count!=1 }">
					    <td>&nbsp;</td>
					</c:if>
					    <td><jecs:code listCode="po.all.order_type" value="${poMemberOrderMap.order_type}"/></td>
					    <td>${poMemberOrderMap.product_no} - ${poMemberOrderMap.product_name}</td>
					    <td align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderMap.qty_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.price }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.pv_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.amount }</fmt:formatNumber></td>
					    <c:set var="poMemberOrderQtyTotal12" value="${poMemberOrderQtyTotal12+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal12" value="${poMemberOrderPriceTotal12+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal12" value="${poMemberOrderPvTotal12+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal12" value="${poMemberOrderAmountTotal12+poMemberOrderMap.amount}"/>
					    <c:set var="poMemberOrderQtyTotal" value="${poMemberOrderQtyTotal+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal" value="${poMemberOrderPriceTotal+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal" value="${poMemberOrderPvTotal+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal" value="${poMemberOrderAmountTotal+poMemberOrderMap.amount}"/>
					</tr>
					</c:if>
				</c:forEach>
				<c:if test='${poMemberOrderQtyTotal12 != 0 }'>
				<tr>
					<td class="topBottomLine" colspan="3" align="right"><span class="bigger"><jecs:locale key="report.subtotal"/>:</span></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderQtyTotal12 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPriceTotal12 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPvTotal12 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderAmountTotal12 }</fmt:formatNumber></td>
				</tr>
				<tr>
					<td class="topBottomLine" colspan="3">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
				</tr>
				</c:if>
				<!-- 二级店铺升级单统计开始 -->
				<!-- 二级店铺重消单统计开始 -->
				<c:set var="poMemberOrderQtyTotal14" value="0"/>
				<c:set var="poMemberOrderPriceTotal14" value="0"/>
				<c:set var="poMemberOrderPvTotal14" value="0"/>
				<c:set var="poMemberOrderAmountTotal14" value="0"/>
				<c:forEach items="${poMemberOrderList}" var="poMemberOrderMap" varStatus="bdOrderStatus">
					<c:if test="${poMemberOrderMap.order_type=='14' }">
					<tr>
					<c:if test="${bdOrderStatus.count==1 }">
					    <td><jecs:locale key="function.menu.memberOrderStatistics"/></td>
					</c:if>
					<c:if test="${bdOrderStatus.count!=1 }">
					    <td>&nbsp;</td>
					</c:if>
					    <td><jecs:code listCode="po.all.order_type" value="${poMemberOrderMap.order_type}"/></td>
					    <td>${poMemberOrderMap.product_no} - ${poMemberOrderMap.product_name}</td>
					    <td align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderMap.qty_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.price }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.pv_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.amount }</fmt:formatNumber></td>
					    <c:set var="poMemberOrderQtyTotal14" value="${poMemberOrderQtyTotal14+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal14" value="${poMemberOrderPriceTotal14+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal14" value="${poMemberOrderPvTotal14+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal14" value="${poMemberOrderAmountTotal14+poMemberOrderMap.amount}"/>
					    <c:set var="poMemberOrderQtyTotal" value="${poMemberOrderQtyTotal+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal" value="${poMemberOrderPriceTotal+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal" value="${poMemberOrderPvTotal+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal" value="${poMemberOrderAmountTotal+poMemberOrderMap.amount}"/>
					</tr>
					</c:if>
				</c:forEach>
				<c:if test='${poMemberOrderQtyTotal14 != 0 }'>
				<tr>
					<td class="topBottomLine" colspan="3" align="right"><span class="bigger"><jecs:locale key="report.subtotal"/>:</span></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderQtyTotal14 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPriceTotal14 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPvTotal14 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderAmountTotal14 }</fmt:formatNumber></td>
				</tr>
				<tr>
					<td class="topBottomLine" colspan="3">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
				</tr>
				</c:if>
				<!-- 二级店铺重消单统计开始 -->
				
				<!-- 奖衔补单统计开始32 -->
				<c:set var="poMemberOrderQtyTotal32" value="0"/>
				<c:set var="poMemberOrderPriceTotal32" value="0"/>
				<c:set var="poMemberOrderPvTotal32" value="0"/>
				<c:set var="poMemberOrderAmountTotal32" value="0"/>
				<c:forEach items="${poMemberOrderList}" var="poMemberOrderMap" varStatus="bdOrderStatus">
					<c:if test="${poMemberOrderMap.order_type=='32' }">
					<tr>
					<c:if test="${bdOrderStatus.count==1 }">
					    <td><jecs:locale key="function.menu.memberOrderStatistics"/></td>
					</c:if>
					<c:if test="${bdOrderStatus.count!=1 }">
					    <td>&nbsp;</td>
					</c:if>
					    <td><jecs:code listCode="po.all.order_type" value="${poMemberOrderMap.order_type}"/></td>
					    <td>${poMemberOrderMap.product_no} - ${poMemberOrderMap.product_name}</td>
					    <td align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderMap.qty_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.price }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.pv_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.amount }</fmt:formatNumber></td>
					    <c:set var="poMemberOrderQtyTotal32" value="${poMemberOrderQtyTotal32+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal32" value="${poMemberOrderPriceTotal32+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal32" value="${poMemberOrderPvTotal32+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal32" value="${poMemberOrderAmountTotal32+poMemberOrderMap.amount}"/>
					    <c:set var="poMemberOrderQtyTotal" value="${poMemberOrderQtyTotal+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal" value="${poMemberOrderPriceTotal+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal" value="${poMemberOrderPvTotal+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal" value="${poMemberOrderAmountTotal+poMemberOrderMap.amount}"/>
					</tr>
					</c:if>
				</c:forEach>
				<c:if test='${poMemberOrderQtyTotal32 != 0 }'>
				<tr>
					<td class="topBottomLine" colspan="3" align="right"><span class="bigger"><jecs:locale key="report.subtotal"/>:</span></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderQtyTotal32 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPriceTotal32 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPvTotal32 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderAmountTotal32 }</fmt:formatNumber></td>
				</tr>
				<tr>
					<td class="topBottomLine" colspan="3">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
				</tr>
				</c:if>
				<!-- 奖衔补单统计结束 -->
				
				<!-- 抵用券购物单统计开始16 -->
				<c:set var="poMemberOrderQtyTotal16" value="0"/>
				<c:set var="poMemberOrderPriceTotal16" value="0"/>
				<c:set var="poMemberOrderPvTotal16" value="0"/>
				<c:set var="poMemberOrderAmountTotal16" value="0"/>
				<c:forEach items="${poMemberOrderList}" var="poMemberOrderMap" varStatus="bdOrderStatus">
					<c:if test="${poMemberOrderMap.order_type=='16' }">
					<tr>
					<c:if test="${bdOrderStatus.count==1 }">
					    <td><jecs:locale key="function.menu.memberOrderStatistics"/></td>
					</c:if>
					<c:if test="${bdOrderStatus.count!=1 }">
					    <td>&nbsp;</td>
					</c:if>
					    <td><jecs:code listCode="po.all.order_type" value="${poMemberOrderMap.order_type}"/></td>
					    <td>${poMemberOrderMap.product_no} - ${poMemberOrderMap.product_name}</td>
					    <td align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderMap.qty_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.price }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.pv_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.amount }</fmt:formatNumber></td>
					    <c:set var="poMemberOrderQtyTotal16" value="${poMemberOrderQtyTotal16+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal16" value="${poMemberOrderPriceTotal16+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal16" value="${poMemberOrderPvTotal16+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal16" value="${poMemberOrderAmountTotal16+poMemberOrderMap.amount}"/>
					    <c:set var="poMemberOrderQtyTotal" value="${poMemberOrderQtyTotal+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal" value="${poMemberOrderPriceTotal+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal" value="${poMemberOrderPvTotal+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal" value="${poMemberOrderAmountTotal+poMemberOrderMap.amount}"/>
					</tr>
					</c:if>
				</c:forEach>
				<c:if test='${poMemberOrderQtyTotal16 != 0 }'>
				<tr>
					<td class="topBottomLine" colspan="3" align="right"><span class="bigger"><jecs:locale key="report.subtotal"/>:</span></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderQtyTotal16 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPriceTotal16 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPvTotal16 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderAmountTotal16 }</fmt:formatNumber></td>
				</tr>
				<tr>
					<td class="topBottomLine" colspan="3">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
				</tr>
				</c:if>
				<!-- 抵用券购物单统计结束 -->
				
				<!-- 360年费单统计开始 41-->
				<c:set var="poMemberOrderQtyTotal41" value="0"/>
				<c:set var="poMemberOrderPriceTotal41" value="0"/>
				<c:set var="poMemberOrderPvTotal41" value="0"/>
				<c:set var="poMemberOrderAmountTotal41" value="0"/>
				<c:forEach items="${poMemberOrderList}" var="poMemberOrderMap" varStatus="bdOrderStatus">
					<c:if test="${poMemberOrderMap.order_type=='41' }">
					<tr>
					<c:if test="${bdOrderStatus.count==1 }">
					    <td><jecs:locale key="function.menu.memberOrderStatistics"/></td>
					</c:if>
					<c:if test="${bdOrderStatus.count!=1 }">
					    <td>&nbsp;</td>
					</c:if>
					    <td><jecs:code listCode="po.all.order_type" value="${poMemberOrderMap.order_type}"/></td>
					    <td>${poMemberOrderMap.product_no} - ${poMemberOrderMap.product_name}</td>
					    <td align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderMap.qty_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.price }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.pv_amt }</fmt:formatNumber></td>
					    <td align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderMap.amount }</fmt:formatNumber></td>
					    <c:set var="poMemberOrderQtyTotal41" value="${poMemberOrderQtyTotal41+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal41" value="${poMemberOrderPriceTotal41+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal41" value="${poMemberOrderPvTotal41+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal41" value="${poMemberOrderAmountTotal41+poMemberOrderMap.amount}"/>
					    <c:set var="poMemberOrderQtyTotal" value="${poMemberOrderQtyTotal+poMemberOrderMap.qty_amt}"/>
					    <c:set var="poMemberOrderPriceTotal" value="${poMemberOrderPriceTotal+poMemberOrderMap.price}"/>
					    <c:set var="poMemberOrderPvTotal" value="${poMemberOrderPvTotal+poMemberOrderMap.pv_amt}"/>
					    <c:set var="poMemberOrderAmountTotal" value="${poMemberOrderAmountTotal+poMemberOrderMap.amount}"/>
					</tr>
					</c:if>
				</c:forEach>
				<c:if test='${poMemberOrderQtyTotal41 != 0 }'>
				<tr>
					<td class="topBottomLine" colspan="3" align="right"><span class="bigger"><jecs:locale key="report.subtotal"/>:</span></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderQtyTotal41 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPriceTotal41 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPvTotal41 }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderAmountTotal41 }</fmt:formatNumber></td>
				</tr>
				<tr>
					<td class="topBottomLine" colspan="3">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
				</tr>
				</c:if>
				<!-- 360年费单统计结束 -->
			
			<!-- 会员退货订单统计开始 -->
				<c:set var="prRefundQtyTotal" value="0"/>
				<c:set var="prRefundPriceTotal" value="0"/>
				<c:set var="prRefundPvTotal" value="0"/>
				<c:set var="prRefundAmountTotal" value="0"/>
				<c:forEach items="${prRefundList}" var="prRefundMap" varStatus="bdRefundStatus">
					<tr>
					<c:if test="${bdRefundStatus.count==1 }">
					    <td><jecs:locale key="function.menu.prRefundStatistic"/></td>
					</c:if>
					<c:if test="${bdRefundStatus.count!=1 }">
					    <td>&nbsp;</td>
					</c:if>
					    <td><jecs:code listCode="po.all.order_type" value="${prRefundMap.order_type}"/></td>
					    <td>${prRefundMap.product_no} - ${prRefundMap.product_name}</td>
					    <td align="right">-<fmt:formatNumber pattern="###,##0">${prRefundMap.qty }</fmt:formatNumber></td>
					    <td align="right">-<fmt:formatNumber pattern="###,##0.00">${prRefundMap.price }</fmt:formatNumber></td>
					    <td align="right">-<fmt:formatNumber pattern="###,##0.00">${prRefundMap.pv }</fmt:formatNumber></td>
					    <td align="right">-<fmt:formatNumber pattern="###,##0.00">${prRefundMap.amount }</fmt:formatNumber></td>
					    <c:set var="prRefundQtyTotal" value="${prRefundQtyTotal-prRefundMap.qty}"/>
					    <c:set var="prRefundPriceTotal" value="${prRefundPriceTotal-prRefundMap.price}"/>
					    <c:set var="prRefundPvTotal" value="${prRefundPvTotal-prRefundMap.pv}"/>
					    <c:set var="prRefundAmountTotal" value="${prRefundAmountTotal-prRefundMap.amount}"/>
					</tr>
				</c:forEach>
				<c:if test='${prRefundQtyTotal != 0 }'>
				<tr>
					<td class="topBottomLine" colspan="3" align="right"><span class="bigger"><jecs:locale key="report.allTotal"/>:</span></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0">${prRefundQtyTotal }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${prRefundPriceTotal }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${prRefundPvTotal }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${prRefundAmountTotal }</fmt:formatNumber></td>
				</tr>
				<tr>
					<td class="topBottomLine" colspan="3">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
				</tr>
				</c:if>
			<!-- 会员退货订单统计结束 -->
			
				<tr>
					<td class="topBottomLine" colspan="3" align="right"><span class="bigger"><jecs:locale key="report.allTotal"/>:</span></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0">${poMemberOrderQtyTotal+prRefundQtyTotal }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPriceTotal+prRefundPriceTotal }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderPvTotal+prRefundPvTotal }</fmt:formatNumber></td>
				    <td class="topBottomLine" align="right"><fmt:formatNumber pattern="###,##0.00">${poMemberOrderAmountTotal+prRefundAmountTotal }</fmt:formatNumber></td>
				</tr>
				<tr>
					<td class="topBottomLine" colspan="3">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
					<td class="topBottomLine" align="right">&nbsp;</td>
				</tr>
			
			<tr>
				<td colspan="3" align="right" class="bigger">&nbsp;</td>
				<td align="right">&nbsp;</td>
				<td align="right">&nbsp;</td>
				<td align="right">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="6" align="right"><table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td><jecs:locale key="report.financial"/></td>
						<td width="40" class="bottomLine">&nbsp;</td>
						<td><jecs:locale key="report.cashier"/></td>
						<td width="40" class="bottomLine" align="center">/</td>
						<td><jecs:locale key="bdBounsDeduct.createName"/></td>
						<td width="60" class="bottomLine" align="center">/</td>
					</tr>
				</table></td>
			</tr>
			<tr>
				<td colspan="6" align="right"><table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td class="bottomLine">${sessionScope.sessionLogin.userName} / ${currentTime}</td>
					</tr>
				</table></td>
			</tr>
		</table>
	
	</div>
</form>