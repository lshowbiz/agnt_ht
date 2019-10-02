<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdStatusExcStockDetailList.title"/></title>
<content tag="heading"><jecs:locale key="pdStatusExcStockDetailList.heading"/></content>
<meta name="menu" content="PdStatusExcStockDetailMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addPdStatusExcStockDetail">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdStatusExcStockDetail.html"/>?strAction=addPdStatusExcStockDetail'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="pdStatusExcStockDetailListTable"
	items="pdStatusExcStockDetailList"
	var="pdStatusExcStockDetail"
	action="${pageContext.request.contextPath}/pdStatusExcStockDetails.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editPdStatusExcStockDetail('${pdStatusExcStockDetail.uniNo}')"
								style="cursor: pointer;" title="<wecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="productNo" title="pdStatusExcStockDetail.productNo" />
    			<ec:column property="price" title="pdStatusExcStockDetail.price" />
    			<ec:column property="damageQty" title="pdStatusExcStockDetail.damageQty" />
    			<ec:column property="normalQty" title="pdStatusExcStockDetail.normalQty" />
    			<ec:column property="unknownQty" title="pdStatusExcStockDetail.unknownQty" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editPdStatusExcStockDetail(uniNo){
   		<jecs:power powerCode="editPdStatusExcStockDetail">
					window.location="editPdStatusExcStockDetail.html?strAction=editPdStatusExcStockDetail&uniNo="+uniNo;
			</jecs:power>
		}

</script>
