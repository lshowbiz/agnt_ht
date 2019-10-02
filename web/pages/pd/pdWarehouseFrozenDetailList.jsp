<%@ page pageEncoding="utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdWarehouseFrozenDetailList.title"/></title>
<content tag="heading"><jecs:locale key="pdWarehouseFrozenDetailList.heading"/></content>
<meta name="menu" content="PdWarehouseFrozenDetailMenu"/>

<c:set var="buttons">
		<%-- <jecs:power powerCode="addPdWarehouseFrozenDetail">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdWarehouseFrozenDetail.html"/>?strAction=addPdWarehouseFrozenDetail'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power> --%>
	<div class="searchBar">
	    <div class="new_searchBar">
		<jecs:title key="busi.warehouse.warehouseno"/>
		<input type="text" name="warehouseNo" value="${param.warehouseNo}" />
		</div>
		<div class="new_searchBar" style="white-space:nowrap;width:400px;">
		<jecs:title key="busi.product.productno"/>
		<input type="text" name="productNo" value="${param.productNo}" />
		
		<%-- <input type="submit" value="查询" />
		<input type="button" value="返回" onclick="window.location='${pageContext.request.contextPath}/pdWarehouseFrozenBatchs.html'" /> --%>
	    
	    <button type="submit" class="btn btn_ins"  style="width:auto">
		        查询
        </button>
        <button type="button" class="btn btn_ins"  style="width:auto" onclick="window.location='${pageContext.request.contextPath}/pdWarehouseFrozenBatchs.html'">
		        返回
        </button>
	    </div>
	</div>	
</c:set> 
<form name="frozenDetail" action="${pageContext.request.contextPath}/pdWarehouseFrozenDetails.html" method="post">
	<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
	</div>
	<ec:table 
		tableId="pdWarehouseFrozenDetailListTable"
		items="pdWarehouseFrozenDetailList"
		var="pdWarehouseFrozenDetail"
		action="${pageContext.request.contextPath}/pdWarehouseFrozenDetails.html"
		width="100%"
		form="frozenDetail"
		retrieveRowsCallback="limit"
		rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
			<ec:exportCsv fileName="stock.csv" />
			<ec:exportXls fileName="stock.xls"/>
			<ec:row >
    			<ec:column property="companyCode" title="sys.company.code" />
    			<ec:column property="warehouseNo" title="busi.warehouse.warehouseno" />
    			<ec:column property="productNo" title="busi.product.productno" />
    			<ec:column property="normalQty" title="pdWarehouseStock.normalQty" />
    			<ec:column property="damageQty" title="pdWarehouseStock.damageQty" />
    			<ec:column property="unknownQty" title="pdWarehouseStock.unknownQty" />
			</ec:row>
	</ec:table>	
</form>

<script type="text/javascript">

   function editPdWarehouseFrozenDetail(uniNo){
   		<jecs:power powerCode="editPdWarehouseFrozenDetail">
					window.location="editPdWarehouseFrozenDetail.html?strAction=editPdWarehouseFrozenDetail&uniNo="+uniNo;
			</jecs:power>
		}

</script>
