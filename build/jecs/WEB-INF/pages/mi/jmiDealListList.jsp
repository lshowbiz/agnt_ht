<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiDealListList.title"/></title>
<content tag="heading"><jecs:locale key="jmiDealListList.heading"/></content>
<meta name="menu" content="JmiDealListMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJmiDealList">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJmiDealList.html"/>?strAction=addJmiDealList'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jmiDealListListTable"
	items="jmiDealListList"
	var="jmiDealList"
	action="${pageContext.request.contextPath}/jmiDealLists.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJmiDealList('${jmiDealList.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="userCode" title="jmiDealList.userCode" />
    			<ec:column property="inType" title="jmiDealList.inType" />
    			<ec:column property="linkNo" title="jmiDealList.linkNo" />
    			<ec:column property="createTime" title="jmiDealList.createTime" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJmiDealList(id){
   		<jecs:power powerCode="editJmiDealList">
					window.location="editJmiDealList.html?strAction=editJmiDealList&id="+id;
			</jecs:power>
		}

</script>
