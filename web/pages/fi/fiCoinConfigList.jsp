<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiCoinConfigList.title"/></title>
<content tag="heading"><jecs:locale key="fiCoinConfigList.heading"/></content>
<meta name="menu" content="FiCoinConfigMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addFiCoinConfig">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editFiCoinConfig.html"/>?strAction=addFiCoinConfig'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="fiCoinConfigListTable"
	items="fiCoinConfigList"
	var="fiCoinConfig"
	action="${pageContext.request.contextPath}/fiCoinConfigs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editFiCoinConfig('${fiCoinConfig.configId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="configName" title="fiCoinConfig.configName" />
    			<ec:column property="startTime" title="fiCoinConfig.startTime" />
    			<ec:column property="endTime" title="fiCoinConfig.endTime" />
    			<ec:column property="memberTopCode" title="fiCoinConfig.memberTopCode" />
    			<ec:column property="description" title="fiCoinConfig.description" />
    			<ec:column property="field1" title="fiCoinConfig.field1" />
    			<ec:column property="createCode" title="fiCoinConfig.createCode" />
    			<ec:column property="createName" title="fiCoinConfig.createName" />
    			<ec:column property="createTime" title="fiCoinConfig.createTime" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editFiCoinConfig(configId){
   		<jecs:power powerCode="editFiCoinConfig">
					window.location="editFiCoinConfig.html?strAction=editFiCoinConfig&configId="+configId;
			</jecs:power>
		}

</script>
