<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdExchangeOrderBackList.title"/></title>
<content tag="heading"><jecs:locale key="pdExchangeOrderBackList.heading"/></content>
<meta name="menu" content="PdExchangeOrderBackMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addPdExchangeOrderBack">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdExchangeOrderBack.html"/>?strAction=addPdExchangeOrderBack'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="pdExchangeOrderBackListTable"
	items="pdExchangeOrderBackList"
	var="pdExchangeOrderBack"
	action="${pageContext.request.contextPath}/pdExchangeOrderBacks.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editPdExchangeOrderBack('${pdExchangeOrderBack.uniNo}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="productNo" title="pdExchangeOrderBack.productNo" />
    			<ec:column property="price" title="pdExchangeOrderBack.price" />
    			<ec:column property="originalQty" title="pdExchangeOrderBack.originalQty" />
    			<ec:column property="qty" title="pdExchangeOrderBack.qty" />
    			<ec:column property="eoNo" title="pdExchangeOrderBack.eoNo" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editPdExchangeOrderBack(uniNo){
   		<jecs:power powerCode="editPdExchangeOrderBack">
					window.location="editPdExchangeOrderBack.html?strAction=editPdExchangeOrderBack&uniNo="+uniNo;
			</jecs:power>
		}

</script>
