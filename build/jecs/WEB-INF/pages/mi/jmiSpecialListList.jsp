<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiSpecialListList.title"/></title>
<content tag="heading"><jecs:locale key="jmiSpecialListList.heading"/></content>
<meta name="menu" content="JmiSpecialListMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJmiSpecialList">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJmiSpecialList.html"/>?strAction=addJmiSpecialList'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jmiSpecialListListTable"
	items="jmiSpecialListList"
	var="jmiSpecialList"
	action="${pageContext.request.contextPath}/jmiSpecialLists.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJmiSpecialList('${jmiSpecialList.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="userCode" title="jmiSpecialList.userCode" />
    			<ec:column property="scType" title="jmiSpecialList.scType" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJmiSpecialList(id){
   		<jecs:power powerCode="editJmiSpecialList">
					window.location="editJmiSpecialList.html?strAction=editJmiSpecialList&id="+id;
			</jecs:power>
		}

</script>
