<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="inwViewpeopleList.title"/></title>
<content tag="heading"><jecs:locale key="inwViewpeopleList.heading"/></content>
<meta name="menu" content="InwViewpeopleMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addInwViewpeople">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editInwViewpeople.html"/>?strAction=addInwViewpeople'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="inwViewpeopleListTable"
	items="inwViewpeopleList"
	var="inwViewpeople"
	action="${pageContext.request.contextPath}/inwViewpeoples.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editInwViewpeople('${inwViewpeople.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="suggestionid" title="inwViewpeople.suggestionid" />
    			<ec:column property="viewStatus" title="inwViewpeople.viewStatus" />
    			<ec:column property="userCode" title="inwViewpeople.userCode" />
    			<ec:column property="viewTime" title="inwViewpeople.viewTime" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editInwViewpeople(id){
   		<jecs:power powerCode="editInwViewpeople">
					window.location="editInwViewpeople.html?strAction=editInwViewpeople&id="+id;
			</jecs:power>
		}

</script>
