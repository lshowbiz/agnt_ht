<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="vtVotePowList.title"/></title>
<content tag="heading"><jecs:locale key="vtVotePowList.heading"/></content>
<meta name="menu" content="VtVotePowMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addVtVotePow">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editVtVotePow.html"/>?strAction=addVtVotePow'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="vtVotePowListTable"
	items="vtVotePowList"
	var="vtVotePow"
	action="${pageContext.request.contextPath}/vtVotePows.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editVtVotePow('${vtVotePow.vpId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="companyCode" title="vtVotePow.companyCode" />
    			<ec:column property="userType" title="vtVotePow.userType" />
    			<ec:column property="vtId" title="vtVotePow.vtId" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editVtVotePow(vpId){
   		<jecs:power powerCode="editVtVotePow">
					window.location="editVtVotePow.html?strAction=editVtVotePow&vpId="+vpId;
			</jecs:power>
		}

</script>
