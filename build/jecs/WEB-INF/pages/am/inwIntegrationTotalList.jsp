<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="inwIntegrationTotalList.title"/></title>
<content tag="heading"><jecs:locale key="inwIntegrationTotalList.heading"/></content>
<meta name="menu" content="InwIntegrationTotalMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addInwIntegrationTotal">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editInwIntegrationTotal.html"/>?strAction=addInwIntegrationTotal'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="inwIntegrationTotalListTable"
	items="inwIntegrationTotalList"
	var="inwIntegrationTotal"
	action="${pageContext.request.contextPath}/inwIntegrationTotals.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editInwIntegrationTotal('${inwIntegrationTotal.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="userCode" title="inwIntegrationTotal.userCode" />
    			<ec:column property="totalPoints" title="inwIntegrationTotal.totalPoints" />
    			<ec:column property="remark" title="inwIntegrationTotal.remark" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editInwIntegrationTotal(id){
   		<jecs:power powerCode="editInwIntegrationTotal">
					window.location="editInwIntegrationTotal.html?strAction=editInwIntegrationTotal&id="+id;
			</jecs:power>
		}

</script>
