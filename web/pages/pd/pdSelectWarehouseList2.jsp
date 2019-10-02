<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdWarehouseList.title"/></title>
<content tag="heading"><jecs:locale key="pdWarehouseList.heading"/></content>
<meta name="menu" content="PdWarehouseMenu"/>
<script type="text/javascript" src="./scripts/jquery/jquery-142min.js"> </script>
<script type="text/javascript" src="./scripts/jquery/jquery-form.js"> </script>





<!-- 
<input type="button" class="button" name="save"  onclick="switchSearchBar('searchTable')" value="<jecs:locale key="operation.button.search"/>" />

<form name="frm" action="<c:url value='/piProducts.html'/>" >
	<div class="searchBar">

<table width="100%" border="0" id="searchTable" class="searchTable">
	<tr>
		<td>
			<input type="hidden" id="strAction"  name="strAction" value="${requestParaMap.strAction}" />
			<input type="hidden" id="selectControl" name="selectControl" value="${selectControl}"/>
			<input type="hidden" id="elementId" name="elementId" value="${elementId}"/>
        
        <input type="submit" class="button" 
        value="<jecs:locale  key='operation.button.search'/>"/>
		</td>
		
	</tr>
</table>
</div>

-->
<form name="frm" action="<c:url value='/pdWarehouses.html'/>" >
   <div class="searchBar">
       <input type="hidden" name="strAction" id="strAction" value="${param.strAction}"/>
	   <input type="hidden" name="selectControl" id="selectControl" value="${param.selectControl}"/>
	   <input type="hidden" name="elementId" id="elementId" value="${param.elementId}"/>
	   <!--  
       <div class="new_searchBar">
	        <button  type="button" class="btn btn_sele" style="width:auto" onclick="selectAll();" >
				     <jecs:locale  key='button.selectAll'/>
			</button>		
			<button  type="button" class="btn btn_sele" style="width:auto" onclick="reSelectAll();" >
				     <jecs:locale  key='common.reSelectAll'/>
			</button>
			
		</div>
		-->
		<div class="new_searchBar">
            <button  type="button" class="btn btn_conf" style="width:auto" onclick="selectMe();" >
				     <jecs:locale  key='operation.button.confirm'/>
			</button>		
			<button  type="button" class="btn btn_sele" style="width:auto" onclick="closeMe();" >
				     <jecs:locale  key='operation.button.close'/>
			</button>
		</div>
   </div>


<%-- <table width="100%" border="0" id="searchTable">
	<tr>
		<td align="left">
			<input type="button" class="button" onclick="selectAll();"
	        value="<jecs:locale  key='button.selectAll'/>"/>
	        <input type="button" class="button" onclick="reSelectAll();"
	        value="<jecs:locale  key='common.reSelectAll'/>"/>
		</td>
		<td align="right">
			   <input type="hidden" name="strAction" id="strAction" value="${param.strAction}"/>
			   <input type="hidden" name="selectControl" id="selectControl" value="${param.selectControl}"/>
			   <input type="hidden" name="elementId" id="elementId" value="${param.elementId}"/>
        <input type="button" class="button" onclick="selectMe();"
        value="<jecs:locale  key='operation.button.confirm'/>"/>
        <input type="button" class="button" onclick="closeMe();"
        value="<jecs:locale  key='operation.button.close'/>"/>
		</td>
	</tr>
</table> --%>

<ec:table 
	tableId="pdWarehouseListTable"
	items="pdWarehouseList"
	var="pdWarehouse"
	action="${pageContext.request.contextPath}/pdWarehouses.html"
	width="100%"
	form="frm"
	method="post"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
		
				<ec:row>
					<ec:column property="edit" title="alCharacterKey.select1" sortable="false" styleClass="centerColumn" viewsAllowed="html"	>
						<!-- <img src="<c:url value='/images/newIcons/tick_16.gif'/>" onclick="javascript:selectPdWarehouse('${pdWarehouse.warehouseNo}')" style="cursor: pointer;" title="<jecs:locale key="button.select"/>"/> -->
						<input type="checkbox" name="warehousenos" value="${pdWarehouse.warehouseNo }" onclick="javascript:selectPdWarehouse2('${pdWarehouse.warehouseNo}')"/>
							
					</ec:column>
    			<ec:column property="warehouseNo" title="busi.warehouse.warehouseno" />
    		<ec:column property="companyCode" title="sys.company.code" />
    			<ec:column property="warehouseName" title="pdWarehouse.warehouseName" />
    			<ec:column property="stateCode" title="miMember.province" />
    			<ec:column property="city" title="busi.city" />
    			<ec:column property="warehouseAddr" title="pdWarehouse.warehouseAddr" />
    			<ec:column property="warehouseZip" title="alCompany.zipCode" />
    			
    			
    			
    			<ec:column property="shNo" title="pd.shno" >
    			  <jecs:code listCode="pd.shno" value="${pdWarehouse.shNo}"/>
    			</ec:column>
    			<ec:column property="warehouseLevel" title="pdWarehouse.warehouseLevel" >
    			  <jecs:code listCode="pd.warehouselevels" value="${pdWarehouse.warehouseLevel}"/>
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
	
	function selectPdWarehouse2(warehouseNo){
		//alert(warehouseNo);
	}

	function closeMe(){
		this.close();
	}
	
	function selectAll(){
		var warehouseNos = document.getElementsByName("warehousenos");
		for(var i=0;i<warehouseNos.length;i++){ 
			warehouseNos[i].checked=true;
		} 		 
	}
	
	 function switchAll() {
	   	if ($("#selectedAll").attr("checked")) {  
	        $(":checkbox").attr("checked", true);
	    } else {  
	        $(":checkbox").attr("checked", false);  
	    }
   } 
	
	
	function reSelectAll(){
		var warehouseNos = document.getElementsByName("warehousenos");
		for(var i=0;i<warehouseNos.length;i++){ 
			if(warehouseNos[i].checked==false){
				warehouseNos[i].checked=true;
			}else if(warehouseNos[i].checked==true){
				warehouseNos[i].checked=false;
			}
		} 		 
	}
	
	function selectAll(){
		var warehouseNos = document.getElementsByName("warehousenos");
		for(var i=0;i<warehouseNos.length;i++){ 
			warehouseNos[i].checked=true;
		} 		 
	}
	
	function selectMe(){
		var warehouseNos = document.getElementsByName("warehousenos");
		var selectStr = '';
		for(var i=0;i<warehouseNos.length;i++){ 
			if(warehouseNos[i].checked){
				selectStr += warehouseNos[i].value+",";
			}
		}  
		if(selectStr==''){
			alert("<jecs:locale key='amMessage.discuss.select'/>");//?
			return;
		} 
		//var elementId=$("#elementId").value;
		var elementId = document.getElementById("elementId").value;
		window.opener.document.getElementById(elementId).value=selectStr.substring(0,selectStr.length-1);
		this.close();
	}
	
	
</script>
