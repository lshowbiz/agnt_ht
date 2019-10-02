<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="inwIntegrationList.title"/></title>
<content tag="heading"><jecs:locale key="inwIntegrationList.heading"/></content>
<meta name="menu" content="InwIntegrationMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addInwIntegration">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editInwIntegration.html"/>?strAction=addInwIntegration'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="inwIntegrationListTable"
	items="inwIntegrationList"
	var="inwIntegration"
	action="${pageContext.request.contextPath}/inwIntegrations.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editInwIntegration('${inwIntegration.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="suggestionUserCode" title="inwIntegration.suggestionUserCode" />
    			<ec:column property="integrationAdd" title="inwIntegration.integrationAdd" />
    			<ec:column property="integrationAddTime" title="inwIntegration.integrationAddTime" />
    			<ec:column property="suggestionid" title="inwIntegration.suggestionid" />
    			<ec:column property="integrationMinus" title="inwIntegration.integrationMinus" />
    			<ec:column property="integrationActivity" title="inwIntegration.integrationActivity" />
    			<ec:column property="integrationMinusTime" title="inwIntegration.integrationMinusTime" />
    			<ec:column property="operatorUserCode" title="inwIntegration.operatorUserCode" />
    			<ec:column property="other" title="inwIntegration.other" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editInwIntegration(id){
   		<jecs:power powerCode="editInwIntegration">
					window.location="editInwIntegration.html?strAction=editInwIntegration&id="+id;
			</jecs:power>
		}

</script>
