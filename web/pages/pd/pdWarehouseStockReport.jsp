<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdWarehouseStockList.title"/></title>
<content tag="heading"><jecs:locale key="pdWarehouseStockList.heading"/></content>
<meta name="menu" content="PdWarehouseStockMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 

<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 



<form name="frm" id="frm" action="<c:url value='/pdWarehouseStockReports.html'/>" >
	<input type="hidden" id="strAction" name="strAction" value="${requestParaMap.strAction}"/>
	<input type="hidden" id="groupType" name="groupType" value="productNo"/>
	<div class="searchBar">
		<div class="new_searchBar">
				<jecs:label  key="busi.warehouse.warehouseno"/>
					<input name="warehouseNo" id="warehouseNo"  onclick="selectWarehouse('warehouseNo');" value="<c:out value='${requestParaMap.warehouseNo}'/>" cssClass="text medium"/>
		</div>
		<div class="new_searchBar" style="white-space:nowrap;">	
		        <jecs:label  key="busi.product.productno"/>
									<input name="productNo" id="jpmProductSaleNew.jpmProduct.productNo" value="<c:out value='${requestParaMap.productNo}'/>" cssClass="text medium"/>
									<button name="select" class="btn btn_sele" onclick="selectProduct();" >
										   <jecs:locale key="button.select"/>
									</button>	
			
				<%-- <jecs:label  key="piProduct.statusNo"/>
        		<jecs:list listCode="pmproduct.statusno" name="status" showBlankLine="true" id="status" value="${requestParaMap.status}" defaultValue=""/> --%>
        </div>
        <div class="new_searchBar" style="white-space:nowrap;">	
				<jecs:label  key="piProduct.statusNo"/>
        		<jecs:list listCode="pmproduct.statusno" name="status" showBlankLine="true" id="status" value="${requestParaMap.status}" defaultValue=""/>
									 <button type="button" class="btn btn_sele" onclick="ssearchPd(document.frm)">
										   <jecs:locale  key='operation.button.search'/>
									</button>
<%-- 					<input type="button" class="button" name="select" onclick="selectProduct()" value="<jecs:locale key="button.select"/>" />
 --%>		</div>
			
			<%-- 	<input type="submit" class="button" value="<jecs:locale  key='operation.button.search'/>"/> --%>

</div>

<ec:table 
	tableId="pdWarehouseStockListTable"
	items="pdWarehouseStockList"
	var="pdWarehouseStock"
	action="${pageContext.request.contextPath}/pdWarehouseStockReports.html"
	width="100%"
	method="post"
	form="frm"
	showPagination="./images/extremetable0" sortable="true" imagePath="./images/extremetable/*.gif">
				
				<ec:exportCsv fileName="stock.csv" />
				<ec:exportXls fileName="stock.xls"/>
				<ec:row>
					
    			<ec:column property="warehouse" title="busi.warehouse.warehouseno" >
    					${requestParaMap.warehouseNo}
    			</ec:column>
    			<ec:column property="productNo" title="busi.product.productno" />
    				
    			<ec:column property="productName" title="pmProduct.productName" sortable="false">	
    				${compamyProductMap[pdWarehouseStock.productNo]}
    		  </ec:column>
    					
    			<ec:column property="normalQty" title="pdWarehouseStock.normalQty" calc="total" calcTitle="busi.logistics.total" styleClass="numberColumn"  sortable="false">
    				<c:if test="${pdWarehouseStock.normalQty >= pdWarehouseStock.storageCordon}"><font color='green'></c:if>
    				<c:if test="${pdWarehouseStock.normalQty < pdWarehouseStock.storageCordon}"><font face='Arial Black' size='2' color='red'></c:if>
    					${pdWarehouseStock.normalQty}</font>
    			</ec:column>
    			
    			<ec:column property="damageQty" title="pdWarehouseStock.damageQty" calc="total" calcTitle="busi.logistics.total" styleClass="numberColumn"  sortable="false"/>
    			<ec:column property="unknownQty" title="pdWarehouseStock.unknownQty" calc="total" calcTitle="busi.logistics.total" styleClass="numberColumn"  sortable="false"/>
    				
    			<ec:column property="unSendQty" calc="total" calcTitle="busi.logistics.total" styleClass="numberColumn" title="busi.pd.orderqty" sortable="false"/>
    			<ec:column property="onWayQty" calc="total" calcTitle="busi.logistics.total" styleClass="numberColumn" title="pdFlitWarehouse.onWayPdFlitWarehouse" sortable="false"/>
    			
    			<ec:column property="useQty" calc="total" calcTitle="busi.logistics.total" styleClass="numberColumn" title="pd.stock.useable" sortable="false">
    				<c:if test="${pdWarehouseStock.useQty >= pdWarehouseStock.storageCordon}"><font color='green'></c:if>
    				<c:if test="${pdWarehouseStock.useQty < pdWarehouseStock.storageCordon}"><font face='Arial Black' size='2' color='red'></c:if>
    					${pdWarehouseStock.useQty}</font>
    			</ec:column>
    			
    			<ec:column property="storageCordon" calc="total" calcTitle="busi.logistics.total" styleClass="numberColumn" title="pmProduct.storageCordon" sortable="false">
    				<c:if test="${pdWarehouseStock.useQty >= pdWarehouseStock.storageCordon}"><font color='green'></c:if>
    				<c:if test="${pdWarehouseStock.useQty < pdWarehouseStock.storageCordon}"><font face='Arial Black' size='2'  color='red'></c:if>	
    				${pdWarehouseStock.storageCordon}</font>
    			</ec:column>
    				<ec:column property="saleState" title="piProduct.statusNo" sortable="false">
						<jecs:code listCode="pmproduct.statusno" value="${pdWarehouseStock.saleState}"/>
					</ec:column>
				</ec:row>
				 
</ec:table>	

</form>


<script type="text/javascript">

   function editPdWarehouseStock(uniNo){
   		<jecs:power powerCode="editPdWarehouseStock">
					window.location="editPdWarehouseStock.html?strAction=editPdWarehouseStock&uniNo="+uniNo;
			</jecs:power>
		}

function selectProduct(){
    		open("<c:url value='/jpmProducts.html?strAction=selectProduct&selectControl=2'/>","","height=500, width=1000, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    		//open("<c:url value='/pmProducts.html?strAction=selectProduct&selectControl=1'/>","","height=500, width=600, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    	}
    
    function ssearchPd(theForm){
          theForm.submit();
    }
    
</script>
