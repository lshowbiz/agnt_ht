<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jprRefundListList.title"/></title>
<content tag="heading"><jecs:locale key="jprRefundListList.heading"/></content>
<meta name="menu" content="JprRefundListMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJprRefundList">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJprRefundList.html"/>?strAction=addJprRefundList'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jprRefundListListTable"
	items="jprRefundListList"
	var="jprRefundList"
	action="${pageContext.request.contextPath}/jprRefundLists.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editJprRefundList('${jprRefundList.prlId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="productNo" title="jprRefundList.productNo" />
    			<ec:column property="price" title="jprRefundList.price" />
    			<ec:column property="qty" title="jprRefundList.qty" />
    			<ec:column property="pv" title="jprRefundList.pv" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJprRefundList(prlId){
   		<jecs:power powerCode="editJprRefundList">
					window.location="editJprRefundList.html?strAction=editJprRefundList&prlId="+prlId;
			</jecs:power>
		}

</script>
