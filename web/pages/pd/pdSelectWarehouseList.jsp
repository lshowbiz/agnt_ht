<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdWarehouseList.title" />
</title>
<content tag="heading">
<jecs:locale key="pdWarehouseList.heading" />
</content>
<meta name="menu" content="PdWarehouseMenu" />





 
<!--
<input type="button" class="button" name="save"  onclick="switchSearchBar('searchTable')" value="<jecs:locale key="operation.button.search"/>" />

<form name="frm" action="<c:url value='/piProducts.html'/>" >
	<div class="searchBar">

<table width="100%" border="0" id="searchTable" class="searchTable">
	<tr>
		<td>
			<input type="hidden" id="strAction"  name="strAction" value="${param.strAction}" />
			<input type="hidden" id="selectControl" name="selectControl" value="${selectControl}"/>
			<input type="hidden" id="elementId" name="elementId" value="${elementId}"/>
        
        <input type="submit" class="button" 
        value="<jecs:locale  key='operation.button.search'/>"/>
		</td>
		
	</tr>
</table>
</div>

-->
<c:set var="buttons">
<div class="new_searchBar">
       <button type="submit"  class="btn btn_sele" >
				   <jecs:locale  key='operation.button.search'/>
		</button>
		 <button name="button" class="btn btn_sele" onclick="closeMe();" >
				   <jecs:locale  key='operation.button.close'/>
		</button>
</div>
	<%-- <input type="submit" class="button" value="<jecs:locale  key='operation.button.search'/>"/> --%>
</c:set>

<form name="frm" action="<c:url value='/pdWarehouses.html'/>">
	<table width="100%" border="0" id="searchTable">
		<tr>
			<td align="right">
				<input type="hidden" name="strAction" id="strAction"
					value="${param.strAction}" />
				<input type="hidden" name="selectControl" id="selectControl"
					value="${param.selectControl}" />
				<input type="hidden" name="elementId" id="elementId"
					value="${param.elementId}" />
			</td>

		</tr>
	</table> 
	<div class="searchBar">
	<div class="new_searchBar">
		<jecs:title key="busi.warehouse.warehouseno"/>
		<input name="warehouseNo"  value="<c:out value='${param.warehouseNo}'/>" cssClass="text medium"/>	
	</div>
	<div class="new_searchBar">	
		<jecs:title key="pdWarehouse.warehouseName"/>
		<input name="warehouseName"  value="<c:out value='${param.warehouseName}'/>" cssClass="text medium"/>	
	</div>	
		
		<c:out value="${buttons}" escapeXml="false" />
	<%-- <input type="button" class="button" onclick="closeMe();"
					value="<jecs:locale  key='operation.button.close'/>" /> --%>
					
	</div>
	<ec:table tableId="pdWarehouseListTable" items="pdWarehouseList"
		var="pdWarehouse"
		action="${pageContext.request.contextPath}/pdWarehouses.html"
		width="100%" form="frm" method="post" retrieveRowsCallback="limit"
		rowsDisplayed="20" sortable="true"
		imagePath="./images/extremetable/*.gif">

		<ec:row>
			<ec:column property="edit" title="button.select" sortable="false"
				styleClass="centerColumn" viewsAllowed="html">
				<img src="<c:url value='/images/newIcons/tick_16.gif'/>"
					onclick="javascript:selectPdWarehouse('${pdWarehouse.warehouseNo}')"
					style="cursor: pointer;" title="<jecs:locale key="button.select"/>" />
			</ec:column>
			<ec:column property="warehouseNo" title="busi.warehouse.warehouseno" />
			<ec:column property="companyCode" title="sys.company.code" />
			<ec:column property="warehouseName" title="pdWarehouse.warehouseName" />
			<ec:column property="stateCode" title="miMember.province" />
			<ec:column property="city" title="busi.city" />
			<ec:column property="warehouseAddr" title="pdWarehouse.warehouseAddr" />
			<ec:column property="warehouseZip" title="alCompany.zipCode" />



			<ec:column property="shNo" title="pd.shno">
				<jecs:code listCode="pd.shno" value="${pdWarehouse.shNo}" />
			</ec:column>
			<ec:column property="warehouseLevel"
				title="pdWarehouse.warehouseLevel">
				<jecs:code listCode="pd.warehouselevels"
					value="${pdWarehouse.warehouseLevel}" />
			</ec:column>
		</ec:row>

	</ec:table>


</form>
<script type="text/javascript">

function searchWarehouse(){
	
	}
   function selectPdWarehouse(warehouseNo){
   			var elementId=$('elementId').value;
   			//alert(elementId);
   			window.opener.document.getElementById(elementId).value=warehouseNo;
   			this.close();
   			//	alert(pdWarehouse);
   			 //window.opener.document.getElementById($F('elementId')).value=warehouseNo;
   				
				//	window.location="editPdWarehouse.html?waId="+waId;
		}

	function closeMe(){
		this.close();
	}
</script>
