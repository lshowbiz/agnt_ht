<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jsysResRoleList.title"/></title>
<content tag="heading"><jecs:locale key="jsysResRoleList.heading"/></content>
<meta name="menu" content="JsysResRoleMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJsysResRole">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJsysResRole.html"/>?strAction=addJsysResRole'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jsysResRoleListTable"
	items="jsysResRoleList"
	var="jsysResRole"
	action="${pageContext.request.contextPath}/jsysResRoles.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJsysResRole('${jsysResRole.pid}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJsysResRole(pid){
   		<jecs:power powerCode="editJsysResRole">
					window.location="editJsysResRole.html?strAction=editJsysResRole&pid="+pid;
			</jecs:power>
		}

</script>
