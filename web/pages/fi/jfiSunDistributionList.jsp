<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiSunDistributionList.title"/></title>
<content tag="heading"><jecs:locale key="jfiSunDistributionList.heading"/></content>
<meta name="menu" content="JfiSunDistributionMenu"/>

<ec:table 
	tableId="jfiSunDistributionListTable"
	items="jfiSunDistributionList"
	var="jfiSunDistribution"
	action="${pageContext.request.contextPath}/jfiSunDistributions.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="userCode" title="jfiSunDistribution.agentCode" />
    			<ec:column property="userName" title="jfiSunMemberOrder.agentName" />
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/search_16.gif'/>" 
								onclick="javascript:editJfiSunDistribution('${jfiSunDistribution.userCode}')"
								style="cursor: pointer;" title="<jecs:locale key="function.menu.view"/>"/> 
				</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJfiSunDistribution(userCode){
					window.location="jfiSunMemberOrders.html?agentNo="+userCode;
		}

</script>
