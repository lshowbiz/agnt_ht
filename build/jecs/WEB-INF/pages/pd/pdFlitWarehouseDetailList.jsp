<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdFlitWarehouseDetailList.title"/></title>
<content tag="heading"><jecs:locale key="pdFlitWarehouseDetailList.heading"/></content>
<meta name="menu" content="PdFlitWarehouseDetailMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addPdFlitWarehouseDetail">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdFlitWarehouseDetail.html"/>?strAction=addPdFlitWarehouseDetail'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="pdFlitWarehouseDetailListTable"
	items="pdFlitWarehouseDetailList"
	var="pdFlitWarehouseDetail"
	action="${pageContext.request.contextPath}/pdFlitWarehouseDetails.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editPdFlitWarehouseDetail('${pdFlitWarehouseDetail.uniNo}')"
								style="cursor: pointer;" title="<wecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="productNo" title="pdFlitWarehouseDetail.productNo" />
    			<ec:column property="price" title="pdFlitWarehouseDetail.price" />
    			<ec:column property="qty" title="pdFlitWarehouseDetail.qty" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editPdFlitWarehouseDetail(uniNo){
   		<jecs:power powerCode="editPdFlitWarehouseDetail">
					window.location="editPdFlitWarehouseDetail.html?strAction=editPdFlitWarehouseDetail&uniNo="+uniNo;
			</jecs:power>
		}

</script>
