<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="foundationItemList.title"/></title>
<content tag="heading"><jecs:locale key="foundationItemList.heading"/></content>
<meta name="menu" content="FoundationItemMenu"/>
 
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="foundationItemListTable"
	items="foundationItemList"
	var="foundationItem"
	action="${pageContext.request.contextPath}/foundationItems.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
	    			<ec:column property="name" title="foundationItem.ainame" />
	    			<ec:column property="amount" title="foundationItem.aiamount" >
	    				${foundationItem.amount}/${foundationItem.unit}
	    			</ec:column>

	    			<ec:column property="edit" title="title.operation.foundation" sortable="false" viewsAllowed="html">
						<img src="<c:url value='/images/newIcons/coins.gif'/>" 
							onclick="javascript:toFoundation('${foundationItem.fiId}')"
							style="cursor: pointer;" title="<jecs:locale key="button.foundation"/>"/> 
					</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function toFoundation(fiId){
			window.location="editFoundationOrder.html?fiId="+fiId;
	}

</script>
