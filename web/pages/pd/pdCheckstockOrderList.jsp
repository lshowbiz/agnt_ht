<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdCheckstockOrderList.title"/></title>
<content tag="heading"><jecs:locale key="pdCheckstockOrderList.heading"/></content>
<meta name="menu" content="PdCheckstockOrderMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addPdCheckstockOrder">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdCheckstockOrder.html"/>?strAction=addPdCheckstockOrder'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="pdCheckstockOrderListTable"
	items="pdCheckstockOrderList"
	var="pdCheckstockOrder"
	action="${pageContext.request.contextPath}/pdCheckstockOrders.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editPdCheckstockOrder('${pdCheckstockOrder.pcoNo}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="companyCode" title="pdCheckstockOrder.companyCode" />
    			<ec:column property="warehouseNo" title="pdCheckstockOrder.warehouseNo" />
    	
    			<ec:column property="createUsrCode" title="pdCheckstockOrder.createUsrCode" />
    			<ec:column property="createTime" title="pdCheckstockOrder.createTime" />
    		
    			<ec:column property="checkUsrCode" title="pdCheckstockOrder.checkUsrCode" />
    			<ec:column property="checkTime" title="pdCheckstockOrder.checkTime" />
    	
    			<ec:column property="okUsrCode" title="pdCheckstockOrder.okUsrCode" />
    			<ec:column property="okTime" title="pdCheckstockOrder.okTime" />
    		
    			<ec:column property="stockFlag" title="pdCheckstockOrder.stockFlag" />
    			<ec:column property="orderFlag" title="pdCheckstockOrder.orderFlag" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editPdCheckstockOrder(pcoNo){
   		<jecs:power powerCode="editPdCheckstockOrder">
					window.location="editPdCheckstockOrder.html?strAction=editPdCheckstockOrder&pcoNo="+pcoNo;
			</jecs:power>
		}

</script>
