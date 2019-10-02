<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="foundationItemList.title"/></title>
<content tag="heading"><jecs:locale key="foundationItemList.heading"/></content>
<meta name="menu" content="FoundationItemMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addFoundationItem">
			    <button type="button" class="btn btn_ins" style="margin-right: 5px;width:auto;"
			        onclick="location.href='<c:url value="/editFoundationItem.html"/>?strAction=addFoundationItem'"><jecs:locale key="button.add"/></button>
		</jecs:power>
</c:set>
<div id="titleBar">
	<div class="new_searchBar">
		<c:out value="${buttons}" escapeXml="false"/>
	</div>
</div>
<!--  
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
-->
<ec:table 
	tableId="foundationItemListTable"
	items="foundationItemList"
	var="foundationItem"
	action="${pageContext.request.contextPath}/foundationItems.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
	    			<ec:column property="name" title="foundationItem.name" sortable="false"/>
	    			<ec:column property="amount" title="foundationItem.amount"  sortable="false"/>
	    			<ec:column property="unit" title="foundationItem.unit"  sortable="false"/>
	    			<ec:column property="type" title="foundationItem.type"  sortable="false">
	    				<jecs:code listCode="foundation.type" value="${foundationItem.type}"/>
	    			</ec:column>
	    			<ec:column property="status" title="foundationItem.status"  sortable="false">
	    				<jecs:code listCode="jmimemberteam.status" value="${foundationItem.status}"/>
	    			</ec:column>
	    			<ec:column property="field2" title="foundationItem.field2"  sortable="false"/>
	    			<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html" >
						<img src="<c:url value='/images/icons/editIcon.gif'/>" 
							onclick="javascript:editFoundationItem('${foundationItem.fiId}')"
							style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editFoundationItem(fiId){
   		<jecs:power powerCode="editFoundationItem">
					window.location="editFoundationItem.html?strAction=editFoundationItem&fiId="+fiId;
			</jecs:power>
		}

</script>
