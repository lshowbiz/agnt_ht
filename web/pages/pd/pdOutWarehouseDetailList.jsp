<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdOutWarehouseDetailList.title"/></title>
<content tag="heading"><jecs:locale key="pdOutWarehouseDetailList.heading"/></content>
<meta name="menu" content="PdOutWarehouseDetailMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addPdOutWarehouseDetail">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdOutWarehouseDetail.html"/>?strAction=addPdOutWarehouseDetail'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="pdOutWarehouseDetailListTable"
	items="pdOutWarehouseDetailList"
	var="pdOutWarehouseDetail"
	action="${pageContext.request.contextPath}/pdOutWarehouseDetails.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editPdOutWarehouseDetail('${pdOutWarehouseDetail.uniNo}')"
								style="cursor: pointer;" title="<wecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="productNo" title="pdOutWarehouseDetail.productNo" />
    			<ec:column property="price" title="pdOutWarehouseDetail.price" />
    			<ec:column property="qty" title="pdOutWarehouseDetail.qty" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editPdOutWarehouseDetail(uniNo){
   		<jecs:power powerCode="editPdOutWarehouseDetail">
					window.location="editPdOutWarehouseDetail.html?strAction=editPdOutWarehouseDetail&uniNo="+uniNo;
			</jecs:power>
		}

</script>
