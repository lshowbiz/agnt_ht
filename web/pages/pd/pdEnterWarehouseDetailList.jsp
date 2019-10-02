<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdEnterWarehouseDetailList.title"/></title>
<content tag="heading"><jecs:locale key="pdEnterWarehouseDetailList.heading"/></content>
<meta name="menu" content="PdEnterWarehouseDetailMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addPdEnterWarehouseDetail">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdEnterWarehouseDetail.html"/>?strAction=addPdEnterWarehouseDetail'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="pdEnterWarehouseDetailListTable"
	items="pdEnterWarehouseDetailList"
	var="pdEnterWarehouseDetail"
	action="${pageContext.request.contextPath}/pdEnterWarehouseDetails.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editPdEnterWarehouseDetail('${pdEnterWarehouseDetail.uniNo}')"
								style="cursor: pointer;" title="<wecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="productNo" title="pdEnterWarehouseDetail.productNo" />
    			<ec:column property="price" title="pdEnterWarehouseDetail.price" />
    			<ec:column property="qty" title="pdEnterWarehouseDetail.qty" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editPdEnterWarehouseDetail(uniNo){
   		<jecs:power powerCode="editPdEnterWarehouseDetail">
					window.location="editPdEnterWarehouseDetail.html?strAction=editPdEnterWarehouseDetail&uniNo="+uniNo;
			</jecs:power>
		}

</script>
