<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdAdjustStockDetailList.title"/></title>
<content tag="heading"><jecs:locale key="pdAdjustStockDetailList.heading"/></content>
<meta name="menu" content="PdAdjustStockDetailMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addPdAdjustStockDetail">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdAdjustStockDetail.html"/>?strAction=addPdAdjustStockDetail'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="pdAdjustStockDetailListTable"
	items="pdAdjustStockDetailList"
	var="pdAdjustStockDetail"
	action="${pageContext.request.contextPath}/pdAdjustStockDetails.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editPdAdjustStockDetail('${pdAdjustStockDetail.uniNo}')"
								style="cursor: pointer;" title="<wecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="productNo" title="pdAdjustStockDetail.productNo" />
    			<ec:column property="price" title="pdAdjustStockDetail.price" />
    			<ec:column property="qty" title="pdAdjustStockDetail.qty" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editPdAdjustStockDetail(uniNo){
   		<jecs:power powerCode="editPdAdjustStockDetail">
					window.location="editPdAdjustStockDetail.html?strAction=editPdAdjustStockDetail&uniNo="+uniNo;
			</jecs:power>
		}

</script>
