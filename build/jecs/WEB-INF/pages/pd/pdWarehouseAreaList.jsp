<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdWarehouseAreaList.title"/></title>
<content tag="heading"><jecs:locale key="pdWarehouseAreaList.heading"/></content>
<meta name="menu" content="PdWarehouseAreaMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addPdWarehouseArea"> 
			    <div class="new_searchBar">
			         <button type="button" class="btn btn_ins"  style="width:auto" onclick="pdWarehouseAreaAdd('${requestParaMap.warehouseNo}');">
				              <jecs:locale key="button.add"/>
		             </button>
			    </div>
		</jecs:power>
		
		<%-- <jecs:power powerCode="addPdWarehouseArea">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdWarehouseArea.html"/>?strAction=addPdWarehouseArea&warehouseNo=${requestParaMap.warehouseNo}'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power> --%>
		
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="pdWarehouseAreaListTable"
	items="pdWarehouseAreas"
	var="pdWarehouseArea"
	action="${pageContext.request.contextPath}/pdWarehouseAreas.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
	
	<ec:parameter name="strAction" value="${requestParaMap.strAction}"/>
				<ec:row >
					
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editPdWarehouseArea('${pdWarehouseArea.waId}')"
								style="cursor: pointer;" title="<jecs:locale key='button.edit'/>"/> 
					</ec:column>
    			<ec:column property="warehouseNo" title="busi.warehouse.warehouseno" />
    			<ec:column property="areaCode" title="alStateProvince.stateProvinceName" >
    				    <%-- ${alStateProvinceMap[pdWarehouseArea.areaCode]} --%>
    				    <c:forEach items="${alStateProvinces}" var="alStateProvince">
								<c:if test="${alStateProvince.stateProvinceId eq pdWarehouseArea.areaCode}">
								    ${alStateProvince.stateProvinceName}
								</c:if>
								
						</c:forEach>
					</ec:column>
    			
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editPdWarehouseArea(waId){
   		<jecs:power powerCode="editPdWarehouseArea">
					window.location="editPdWarehouseArea.html?strAction=editPdWarehouseArea&waId="+waId;
			</jecs:power>
		}
		
	function pdWarehouseAreaAdd(warehouseNo){
	     window.location="editPdWarehouseArea.html?strAction=addPdWarehouseArea&warehouseNo="+warehouseNo;
	}

</script>
