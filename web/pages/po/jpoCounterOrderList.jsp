<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoCounterOrderList.title"/></title>
<content tag="heading"><jecs:locale key="jpoCounterOrderList.heading"/></content>
<meta name="menu" content="JpoCounterOrderMenu"/>


<form name="frm" action="<c:url value='/jpoCounterOrders.html'/>" >
	<input type="hidden" id="strAction" name="strAction" value="${requestParaMap.strAction}"/>
	<input type="hidden" id="printFlag" name="printFlag" value="${param.printFlag}"/>
	<input type="hidden" id="printCoNo" name="printCoNo" value="${param.printCoNo}"/>
<div class="searchBar">

<div class="new_searchBar">
    <jecs:title  key="busi.order.orderno"/>
    <input name="coNo" id="coNo" value="<c:out value='${requestParaMap.coNo}'/>" />
</div>
<div class="new_searchBar">
    <jecs:title  key="miMember.memberNo"/>
    <input name="userCode" id="userCode" value="<c:out value='${requestParaMap.userCode}'/>" size="10"/>
</div>
	<c:if test="${requestParaMap.strAction=='statisticPoCounterOrder'}">	
				<div class="new_searchBar">
				<jecs:title  key="busi.product.productno"/>
				<input name="productNo" id="productNo" value="<c:out value='${requestParaMap.productNo}'/>"/>
			    </div>
		</c:if>	
			
			<div class="new_searchBar">
			<jecs:title key="pd.createTime"/>		
				<input name="createTimeStart" id="createTimeStart" type="text" 
				value="${param.createTimeStart }" style="cursor: pointer;width:76px;"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
				<input name="createTimeEnd" id="createTimeEnd" type="text" 
				value="${param.createTimeEnd }" style="cursor: pointer;width:76px;"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			<div class="new_searchBar">
			<jecs:title  key="pocounterorder.csstatus"/>
			<jecs:list listCode="pocounterorder.csstatus" name="csStatus" id="csStatus" showBlankLine="true" value="${requestParaMap.csStatus}" defaultValue=""/>
			</div>
			<div class="new_searchBar">
			<jecs:title  key="pd.createUserNo"/>
			<input name="createUserNo" id="createUserNo" value="<c:out value='${requestParaMap.createUserNo}'/>" />
			</div>
			<div class="new_searchBar">
			<jecs:title key="busi.warehouse.outwatehouseno"/>
			<input name="warehouseNo" id="warehouseNo" value="<c:out value='${requestParaMap.warehouseNo}'/>" onclick="selectWarehouse('warehouseNo');" cssClass="text medium"/>
			</div>
			<div class="new_searchBar">
			<jecs:title key="pd.okTime"/>
			<input name="okTimeStart" id="okTimeStart" type="text" 
				value="${param.okTimeStart }" style="cursor: pointer;width:76px;"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				- 
			<input name="okTimeEnd" id="okTimeEnd" type="text" 
				value="${param.okTimeEnd }" style="cursor: pointer;width:76px;"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			<div class="new_searchBar">
			<jecs:title key="jpo.count.ship.time"/>
			<input name="shipTimeStart" id="shipTimeStart" type="text" 
				value="${param.shipTimeStart }" style="cursor: pointer;width:76px;"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				- 
			<input name="shipTimeEnd" id="shipTimeEnd" type="text" 
				value="${param.shipTimeEnd }" style="cursor: pointer;width:76px;"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>	
			
			<c:if test="${requestParaMap.strAction=='statisticPoCounterOrder'}">
			<div class="new_searchBar" >
				<div style="margin-left:40px;">
				 <button type="submit" class="btn btn_sele" style="width:auto">
					<jecs:locale  key='operation.button.search'/>
				</button>
				</div>
			</div>
			</c:if>	
			
		<c:if test="${requestParaMap.strAction!='statisticPoCounterOrder'}">
		<div class="new_searchBar">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    <button type="submit" class="btn btn_sele" style="width:auto">
			        <jecs:locale  key='operation.button.search'/>
			    </button>	
			<jecs:power powerCode="addPoCounterOrder">
				<c:if test="${listFlag =='1'}">
				
				<button type="button" class="btn btn_ins" name="search" style="width:auto" onclick="location.href='<c:url value="/editJpoCounterOrder.html"/>?strAction=addPoCounterOrder'">
			        <jecs:locale key="member.new.num"/>
			    </button>
			    <!-- 	
				<input name="search" class="button" type="button" onclick="location.href='<c:url value="/editJpoCounterOrder.html"/>?strAction=addPoCounterOrder'" value="<jecs:locale key="member.new.num"/>" />
	             -->
				</c:if>
			</jecs:power>
		</div>
		</c:if>
		
</div>	
			
			


</form>










<c:set var="footTotalVar">
<tr>
	<td align="right" class="footer" colspan="4"><jecs:locale key="poOrder.amtCount"/></td>
<td class="footer" align="right">
		<b>
	<fmt:formatNumber pattern="###,###,##0.00">${poCounterOrderSumTotal[0]}</fmt:formatNumber>
</b>
</td>

	
	<td class="footer" align="right" colspan="4">

	</td>
</tr>
</c:set>








<ec:table 
	tableId="jpoCounterOrderListTable"
	items="jpoCounterOrderList"
	var="poCounterOrder"
	action="${pageContext.request.contextPath}/jpoCounterOrders.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif" footer="${footTotalVar }">
				<ec:row onclick="showTR($('tr${ROWCOUNT}'));">
					<c:if test="${sessionScope.sessionLogin.isManager}">
						<ec:column property="companyCode" title="sys.company.code" />
					</c:if>
					
    			<ec:column property="coNo" title="busi.order.orderno" />
    			<ec:column property="createTime" title="pd.createTime" >
    					<fmt:formatDate value="${poCounterOrder.createTime}" pattern="yyyy-MM-dd"/>
    			</ec:column>
    			
    			<ec:column property="sysUser.userCode" title="miMember.memberNo" >
    				${poCounterOrder.sysUser.userCode}
    			</ec:column>
    			<%--<ec:column property="sysUser.userName" title="bdCalculatingSubDetail.name" >
    				${poCounterOrder.sysUser.userName}
    			</ec:column>--%>
    			<ec:column property="warehouseNo" title="busi.warehouse.outwatehouseno" />
    			<ec:column property="amount" title="busi.finance.amount" styleClass="numberColumn">
    				<fmt:formatNumber value="${poCounterOrder.amount}" type="number" pattern="###,###,##0.00"/>
    			</ec:column>
    			
    			
    			<ec:column property="csStatus" title="pocounterorder.csstatus" styleClass="centerColumn" >
    				<jecs:code listCode="pocounterorder.csstatus" value="${poCounterOrder.csStatus}"/>
    			</ec:column>
    			<ec:column property="shipper.userCode" title="jpo.count.shiper" />
    			<ec:column property="shipTime" title="jpo.count.ship.time" />
					<ec:column property="edit" title="title.operation" sortable="false" styleClass="centerColumn" viewsAllowed="html"	>
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editPoCounterOrder('${poCounterOrder.coNo}','${requestParaMap.strAction}')"
								 style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
						
							<c:if test="${(requestParaMap.strAction=='paymentPoCounterOrder')||(requestParaMap.strAction=='searchPoCounterOrder')}">
										 	
								<img src="<c:url value='/images/icons/printer.gif'/>" 
								onclick="javascript:printOrder('${poCounterOrder.coNo}')"
								 style="cursor: pointer;" title="<jecs:locale key="button.print"/>"/> 
							</c:if>
							
					</ec:column>
				</ec:row>
				
				<c:if test="${ROWCOUNT>0}">
						<c:if test="${ROWCOUNT%2!=0}"><tr id="tr${ROWCOUNT}" style="display:none" class="odddetail"></c:if>
						<c:if test="${ROWCOUNT%2==0}"><tr id="tr${ROWCOUNT}" style="display:none" class="evendetail"></c:if>
							<c:if test="${sessionScope.sessionLogin.isManager}">
								<td colspan="2"  align="right" valign="top">&nbsp;</td>
							</c:if>
							<c:if test="${!sessionScope.sessionLogin.isManager}">
								<td align="right" valign="top">&nbsp;</td>
							</c:if>
								<td colspan="7">
								<c:set value="true" var="first"></c:set>
								<c:forEach items="${poCounterOrder.jpoCounterOrderDetails}" var="poCounterOrderDetail" varStatus="status">
									<c:if test="${poCounterOrderDetail.qty!=0}">
									<c:if test="${first=='false' }">
										<br/>
									</c:if>
									&nbsp;&nbsp;<img src="<c:url value='/images/menu_tree/file12.gif'/>"/>&nbsp;&nbsp;<font color=#888888>[<font color="green">${poCounterOrderDetail.qty}</font>]${poCounterOrderDetail.jpmProductSaleNew.jpmProduct.productNo}/${poCounterOrderDetail.jpmProductSaleNew.productName}</font>
									<c:set value="false" var="first"></c:set>
									</c:if>
								</c:forEach>
							</td>
						</tr>
				</c:if>
				
				<%--<c:if test="${ROWCOUNT>0}">
						<c:if test="${ROWCOUNT%2!=0}"><tr class="odd"></c:if>
						<c:if test="${ROWCOUNT%2==0}"><tr class="even"></c:if>
							<c:if test="${sessionScope.sessionLogin.isManager}">
								<td colspan="2"  align="right" valign="top">&nbsp;</td>
							</c:if>
							<c:if test="${!sessionScope.sessionLogin.isManager}">
								<td align="right" valign="top">&nbsp;</td>
							</c:if>
							
						
								<td colspan="6">
							
							
								<c:forEach items="${poCounterOrder.jpoCounterOrderDetails}" var="poCounterOrderDetail" varStatus="status">
									
									<c:if test="${poCounterOrderDetail.qty > 0}">
									<br/>
									<font color=#888888>[<font color="green">${poCounterOrderDetail.qty}</font>]${poCounterOrderDetail.jpmProductSale.jpmProduct.productNo}/
										${compamyProductMap[poCounterOrderDetail.pmProduct.productNo]}
										</font>
									</c:if>
								</c:forEach>
							</td>
							
						</tr>
				</c:if>--%>

</ec:table>	

<c:if test="${requestParaMap.strAction=='statisticPoCounterOrder'}">
<ec:table 
	tableId="poCounterOrderTotalTable"
	items="poCounterOrderTotal"
	var="pd"
	action="${pageContext.request.contextPath}/pdEnterWarehouses.html"
	width="100%"
	showPagination="false"
	sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:parameter name="strAction" value="${requestParaMap.strAction}"/>
				<%--<ec:exportCsv fileName="countorderTotal.csv" />
				<ec:exportXls fileName="countorderTotal.xls"/>--%>
				<ec:row >
					
					<ec:column property="PRODUCT_NO" title="busi.product.productno" sortable="true"/>
    			<ec:column property="PRODUCT_NAME" title="pmProduct.productName" sortable="false"/>
    			<ec:column property="QTY" title="pd.qty" calc="total" calcTitle="busi.logistics.total" styleClass="numberColumn" sortable="false"/>
    			<ec:column property="AMOUNT" title="busi.order.amount" format="###,###,##0.00" calc="total" calcTitle="busi.logistics.total" styleClass="numberColumn" sortable="false"/>
    		
				</ec:row>

</ec:table>	
</c:if>

<script type="text/javascript">

    var printFlag = $('printFlag').value;
    var printCoNo = $('printCoNo').value;
    if((printFlag !='') && (printCoNo!='')){
    			printOrder(printCoNo);
    	}
   
	
   function editPoCounterOrder(coNo,strAction){
   		<jecs:power powerCode="${requestParaMap.strAction}">
					window.location="<c:url value="/editJpoCounterOrder.html"/>?strAction="+strAction+"&coNo="+coNo;
			</jecs:power>
		}
		
		function printOrder(siNo){
	 		window.open("<c:url value='/pdOrderPrints.html'/>?strAction=printPoCounterOrder&orderNo="+siNo);
	 	}

</script>
