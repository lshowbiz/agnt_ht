<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdUserValidListList.title"/></title>
<content tag="heading"><jecs:locale key="jbdUserValidListList.heading"/></content>
<meta name="menu" content="JbdUserValidListMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJbdUserValidList">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJbdUserValidList.html"/>?strAction=addJbdUserValidList'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jbdUserValidListListTable"
	items="jbdUserValidListList"
	var="jbdUserValidList"
	action="${pageContext.request.contextPath}/jbdUserValidLists.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJbdUserValidList('${jbdUserValidList.id}')"
								style="cursor: pointer;" title="<wecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="userCode" title="jbdUserValidList.userCode" />
    			<ec:column property="oldFreezeStatus" title="jbdUserValidList.oldFreezeStatus" />
    			<ec:column property="newFreezeStatus" title="jbdUserValidList.newFreezeStatus" />
    			<ec:column property="wweek" title="jbdUserValidList.wweek" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJbdUserValidList(id){
   		<jecs:power powerCode="editJbdUserValidList">
					window.location="editJbdUserValidList.html?strAction=editJbdUserValidList&id="+id;
			</jecs:power>
		}

</script>
