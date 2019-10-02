<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdWarehouseList.title"/></title>
<content tag="heading"><jecs:locale key="pdWarehouseList.heading"/></content>
<meta name="menu" content="PdWarehouseMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addPdWarehouse">
			    <div class="new_searchBar">
			          <button type="button" class="btn btn_ins"  style="width:auto" onclick="pdWarehouseAdd();">
					        <jecs:locale key="button.add"/>
			          </button>
			    </div>
		</jecs:power>
		
		<%-- <jecs:power powerCode="addPdWarehouse">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdWarehouse.html"/>?strAction=addPdWarehouse'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power> --%>
</c:set>

<div class="searchBar">
		<c:out value="${buttons}" escapeXml="false"/>
	</div>
<ec:table 
	tableId="pdWarehouseListTable"
	items="pdWarehouseList"
	var="pdWarehouse"
	action="${pageContext.request.contextPath}/pdWarehouses.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
	<ec:parameter name="strAction" value="${param.strAction}" />
				<ec:row >
				
    			<ec:column property="warehouseNo" title="busi.warehouse.warehouseno" style="white-space:nowrap;"/>
    			<ec:column property="companyCode" title="sys.company.code" style="white-space:nowrap;"/>
    			<ec:column property="warehouseName" title="pdWarehouse.warehouseName" style="white-space:nowrap;"/>
    			<ec:column property="stateCode" title="miMember.province" style="white-space:nowrap;">
    					${alStateProvinceMap[pdWarehouse.stateCode]}
    			</ec:column>
    			<ec:column property="city" title="busi.city" style="white-space:nowrap;"/>
    			<ec:column property="warehouseAddr" title="pdWarehouse.warehouseAddr" style="white-space:nowrap;"/>
    			<ec:column property="warehouseZip" title="alCompany.zipCode" style="white-space:nowrap;"/>
    			<ec:column property="shNo" title="pd.shno" style="white-space:nowrap;">
    			  <jecs:code listCode="pd.shno" value="${pdWarehouse.shNo}"/>
    			</ec:column>
    			<ec:column property="warehouseLevel" title="pdWarehouse.warehouseLevel" style="white-space:nowrap;">
    			  <jecs:code listCode="pd.warehouselevels" value="${pdWarehouse.warehouseLevel}"/>
    			</ec:column>

    			<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html" style="white-space:nowrap;">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editPdWarehouse('${pdWarehouse.warehouseNo}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editPdWarehouse(warehouseNo){
   		<jecs:power powerCode="editPdWarehouse">
					window.location="editPdWarehouse.html?strAction=editPdWarehouse&warehouseNo="+warehouseNo;
			</jecs:power>
		}

    function pdWarehouseAdd(){
         window.location="editPdWarehouse.html?strAction=addPdWarehouse";
    }

</script>
