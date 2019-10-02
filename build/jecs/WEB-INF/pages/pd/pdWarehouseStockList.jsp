<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdWarehouseStockList.title"/></title>
<content tag="heading"><jecs:locale key="pdWarehouseStockList.heading"/></content>
<meta name="menu" content="PdWarehouseStockMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 

<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 



<form name="frm" id="frm" action="<c:url value='/pdWarehouseStocks.html'/>" onsubmit="return check();">
	<input type="hidden" id="strAction" name="strAction" value="${requestParaMap.strAction}"/>
	
	<div class="searchBar">
		<div class="new_searchBar" style="white-space:nowrap;">
				<jecs:title  key="busi.warehouse.warehouseno"/>
					<input name="warehouseNo" id="warehouseNo"  onclick="selectWarehouse2('warehouseNo');" value="<c:out value='${requestParaMap.warehouseNo}'/>" cssClass="text medium"/>
					<button name="select" class="btn btn_sele" onclick="selectWarehouse2('warehouseNo');" >
				             <jecs:locale key="button.select"/>
			        </button>
<%-- 					<input type="button" class="button" name="select" onclick="selectWarehouse2('warehouseNo');" value="<jecs:locale key="button.select"/>" />
 --%>		</div>
            <div class="new_searchBar" style="white-space:nowrap;">			
				<jecs:title  key="busi.product.productno"/>
									<input name="productNo" id="jpmProductSaleNew.jpmProduct.productNo" value="<c:out value='${requestParaMap.productNo}'/>" cssClass="text medium"/>
			    <button type="button" name="select" class="btn btn_sele" onclick="selectProduct();" >
				             <jecs:locale key="button.select"/>
			    </button>
			</div>
			<div class="new_searchBar">	
						<button type="submit"  class="btn btn_sele" >
				             <jecs:locale  key='operation.button.search'/>
			            </button>
					<%-- <input type="button" class="button" name="select" onclick="selectProduct()" value="<jecs:locale key="button.select"/>" />
						
				<input type="submit" class="button" value="<jecs:locale  key='operation.button.search'/>"/> --%>
           </div>
</div>
</form>
<ec:table 
	tableId="pdWarehouseStockListTable"
	items="pdWarehouseStockList"
	var="pdWarehouseStock"
	action="${pageContext.request.contextPath}/pdWarehouseStocks.html"
	width="100%"
	method="post" 
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				
				<ec:exportCsv fileName="stock.csv" encoding="UTF-8"/>
				<ec:exportXls fileName="stock.xls" encoding="UTF-8"/>
				<ec:row>
					<c:if test="${sessionScope.sessionLogin.isManager}">
    				<ec:column property="stock.companyCode" title="sys.company.code" sortable="false">
    					${pdWarehouseStock.stock.companyCode}
    				</ec:column>
    			</c:if>
    			<ec:column property="warehouseNo" title="busi.warehouse.warehouseno" >
    					${pdWarehouseStock.warehouse.warehouseNo}/${pdWarehouseStock.warehouse.warehouseName}
    			</ec:column>
    			<ec:column property="productNo" title="busi.product.productno" />
    				
    			<ec:column property="productName" title="pmProduct.productName" sortable="false"/>	
    				
    					
    			<ec:column property="normalQty" title="pdWarehouseStock.normalQty" calc="total" calcTitle="busi.logistics.total" styleClass="numberColumn"  sortable="false"/>
    			<ec:column property="damageQty" title="pdWarehouseStock.damageQty" calc="total" calcTitle="busi.logistics.total" styleClass="numberColumn"  sortable="false"/>
    			<ec:column property="unknownQty" title="pdWarehouseStock.unknownQty" calc="total" calcTitle="busi.logistics.total" styleClass="numberColumn"  sortable="false"/>
    				
    			<ec:column property="unSendQty" calc="total" calcTitle="busi.logistics.total" styleClass="numberColumn" title="busi.pd.orderqty" sortable="false"/>
    			<ec:column property="onWayQty" calc="total" calcTitle="busi.logistics.total" styleClass="numberColumn" title="pdFlitWarehouse.onWayPdFlitWarehouse" sortable="false"/>
    			
    			<ec:column property="outWarehouseAudit" calc="total" calcTitle="busi.logistics.total" styleClass="numberColumn" title="outWarehouse.auditNum" sortable="false"/>
    			<ec:column property="pdFlitWarehouseAudit" calc="total" calcTitle="busi.logistics.total" styleClass="numberColumn" title="pdFlitWarehouse.auditNum" sortable="false"/>
    			
    			<ec:column property="useQty" calc="total" calcTitle="busi.logistics.total" styleClass="numberColumn" title="pd.stock.useable" sortable="false"/>
					<ec:column property="saleState" title="piProduct.statusNo" sortable="false">
						<jecs:code listCode="pmproduct.statusno" value="${pdWarehouseStock.saleState}"/>
					</ec:column>
				</ec:row>
				 
</ec:table>	




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
	/**Add By WuCF 20130502 **/
	function selectWarehouse2(str){
		window.open("<c:url value='/pdWarehouses.html'/>?strAction=selectWarehouse2&elementId="+str,"","height=600, width=1000, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
	}

	function check(){
		var warehouseNo = document.getElementById('warehouseNo');
		if(warehouseNo.value==null || warehouseNo.value==''){
			alert('<jecs:locale key="busi.warehouse.warehouseno"/><jecs:locale key="operation.notice.js.bankKey.notNull"/>');
			return false;
		}
		return true;
	}
</script>
