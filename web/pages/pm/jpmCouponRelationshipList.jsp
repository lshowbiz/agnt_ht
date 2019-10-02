<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmCouponRelationshipList.title"/></title>
<content tag="heading"><jecs:locale key="jpmCouponRelationshipList.heading"/></content>
<meta name="menu" content="JpmCouponRelationshipMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJpmCouponRelationship">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJpmCouponRelationship.html"/>?strAction=addJpmCouponRelationship'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jpmCouponRelationshipListTable"
	items="jpmCouponRelationshipList"
	var="jpmCouponRelationship"
	action="${pageContext.request.contextPath}/jpmCouponRelationships.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpmCouponRelationship('${jpmCouponRelationship.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="cpId" title="jpmCouponRelationship.cpId" />
    			<ec:column property="uniNo" title="jpmCouponRelationship.uniNo" />
    			<ec:column property="createTime" title="jpmCouponRelationship.createTime" />
    			<ec:column property="createUserCode" title="jpmCouponRelationship.createUserCode" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJpmCouponRelationship(id){
   		<jecs:power powerCode="editJpmCouponRelationship">
					window.location="editJpmCouponRelationship.html?strAction=editJpmCouponRelationship&id="+id;
			</jecs:power>
		}

</script>
