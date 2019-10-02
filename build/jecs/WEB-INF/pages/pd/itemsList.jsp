<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="itemsList.title"/></title>
<content tag="heading"><jecs:locale key="itemsList.heading"/></content>
<meta name="menu" content="ItemsMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addItems">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editItems.html"/>?strAction=addItems'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="itemsListTable"
	items="itemsList"
	var="items"
	action="${pageContext.request.contextPath}/items.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editItems('${items.itemsId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="statusId" title="items.statusId" />
    			<ec:column property="accepttime" title="items.accepttime" />
    			<ec:column property="acceptaddress" title="items.acceptaddress" />
    			<ec:column property="event" title="items.event" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editItems(itemsId){
   		<jecs:power powerCode="editItems">
					window.location="editItems.html?strAction=editItems&itemsId="+itemsId;
			</jecs:power>
		}

</script>
