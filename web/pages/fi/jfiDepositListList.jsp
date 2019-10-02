<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiDepositListList.title"/></title>
<content tag="heading"><jecs:locale key="jfiDepositListList.heading"/></content>
<meta name="menu" content="JfiDepositListMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJfiDepositList">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJfiDepositList.html"/>?strAction=addJfiDepositList'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jfiDepositListListTable"
	items="jfiDepositListList"
	var="jfiDepositList"
	action="${pageContext.request.contextPath}/jfiDepositLists.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJfiDepositList('${jfiDepositList.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="dmId" title="jfiDepositList.dmId" />
    			<ec:column property="depositMoney" title="jfiDepositList.depositMoney" />
    			<ec:column property="createNo" title="jfiDepositList.createNo" />
    			<ec:column property="createTime" title="jfiDepositList.createTime" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJfiDepositList(id){
   		<jecs:power powerCode="editJfiDepositList">
					window.location="editJfiDepositList.html?strAction=editJfiDepositList&id="+id;
			</jecs:power>
		}

</script>
