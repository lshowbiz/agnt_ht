<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdWarehouseFrozenBatchList.title"/></title>
<content tag="heading"><jecs:locale key="pdWarehouseFrozenBatchList.heading"/></content>
<meta name="menu" content="PdWarehouseFrozenBatchMenu"/>

<c:set var="buttons">
		<%-- <jecs:power powerCode="addPdWarehouseFrozenBatch">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdWarehouseFrozenBatch.html"/>?strAction=addPdWarehouseFrozenBatch'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power> --%>
</c:set>
<div id="titleBar"> 
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="pdWarehouseFrozenBatchListTable"
	items="pdWarehouseFrozenBatchList"
	var="pdWarehouseFrozenBatch"
	action="${pageContext.request.contextPath}/pdWarehouseFrozenBatchs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editPdWarehouseFrozenBatch('${pdWarehouseFrozenBatch.batchId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="batchId" title="pdWarehouseFrozenBatch.batchId" />
    			<ec:column property="batchCode" title="pdWarehouseFrozenBatch.batchCode" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editPdWarehouseFrozenBatch(batchId){
   		<jecs:power powerCode="editPdWarehouseFrozenBatch">
				//window.location="editPdWarehouseFrozenBatch.html?strAction=editPdWarehouseFrozenBatch&batchId="+batchId;
				window.location="pdWarehouseFrozenDetails.html?batchId="+batchId;
		</jecs:power>
	}

</script>
