<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiBcoinPayconfigList.title"/></title>
<content tag="heading"><jecs:locale key="fiBcoinPayconfigList.heading"/></content>
<meta name="menu" content="FiBcoinPayconfigMenu"/>

<form action="${pageContext.request.contextPath}/fiBcoinPayconfigs.html" method="post" name="searchForm" id="searchForm">
	<div class="searchBar">

		<jecs:title key="fiBcoinPayconfig.title"/>
		<input name="title" type="text" value="${param.title}" size="14"/>
		
		<input name="search" class="button" type="submit" value="<jecs:locale key="operation.button.search"/>" />
		<jecs:power powerCode="addFiBcoinPayconfig">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editFiBcoinPayconfig.html"/>?strAction=addFiBcoinPayconfig'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
	</div>
</form>

<ec:table 
	tableId="fiBcoinPayconfigListTable"
	items="fiBcoinPayconfigList"
	var="fiBcoinPayconfig"
	action="${pageContext.request.contextPath}/fiBcoinPayconfigs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editFiBcoinPayconfig('${fiBcoinPayconfig.configId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
				<ec:column property="title" title="fiBcoinPayconfig.title" />
    			<ec:column property="startTime" title="fiBcoinPayconfig.startTime" >
    				
    			</ec:column>
    			<ec:column property="endTime" title="fiBcoinPayconfig.endTime" >
 
    			</ec:column>
    			
    			<ec:column property="limit" title="fiBcoinPayconfig.limit" >
    				<jecs:code listCode="fibcoinpayconfig.limit" value="${fiBcoinPayconfig.limit}"/>
    			</ec:column>

    			<ec:column property="proportion" title="fiBcoinPayconfig.proportion" >
    				<jecs:code listCode="fibcoinpayconfig.proportion" value="${fiBcoinPayconfig.proportion}"/>
    			</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editFiBcoinPayconfig(configId){
   		<jecs:power powerCode="editFiBcoinPayconfig">
					window.location="editFiBcoinPayconfig.html?strAction=editFiBcoinPayconfig&configId="+configId;
			</jecs:power>
		}

</script>
