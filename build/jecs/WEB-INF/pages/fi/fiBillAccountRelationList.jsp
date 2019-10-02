<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiBillAccountRelationList.title"/></title>
<content tag="heading"><jecs:locale key="fiBillAccountRelationList.heading"/></content>
<meta name="menu" content="FiBillAccountRelationMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addFiBillAccountRelation">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editFiBillAccountRelation.html"/>?strAction=addFiBillAccountRelation'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="fiBillAccountRelationListTable"
	items="fiBillAccountRelationList"
	var="fiBillAccountRelation"
	action="${pageContext.request.contextPath}/fiBillAccountRelations.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editFiBillAccountRelation('${fiBillAccountRelation.relationId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="province" title="fiBillAccountRelation.province" />
    			<ec:column property="city" title="fiBillAccountRelation.city" />
    			<ec:column property="district" title="fiBillAccountRelation.district" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editFiBillAccountRelation(relationId){
   		<jecs:power powerCode="editFiBillAccountRelation">
					window.location="editFiBillAccountRelation.html?strAction=editFiBillAccountRelation&relationId="+relationId;
			</jecs:power>
		}

</script>
