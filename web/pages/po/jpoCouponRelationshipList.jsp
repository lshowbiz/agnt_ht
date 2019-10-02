<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoCouponRelationshipList.title"/></title>
<content tag="heading"><jecs:locale key="jpoCouponRelationshipList.heading"/></content>
<meta name="menu" content="JpoCouponRelationshipMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJpoCouponRelationship">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJpoCouponRelationship.html"/>?strAction=addJpoCouponRelationship'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jpoCouponRelationshipListTable"
	items="jpoCouponRelationshipList"
	var="jpoCouponRelationship"
	action="${pageContext.request.contextPath}/jpoCouponRelationships.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoCouponRelationship('${jpoCouponRelationship.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="moId" title="jpoCouponRelationship.moId" />
    			<ec:column property="cpId" title="jpoCouponRelationship.cpId" />
    			<ec:column property="createTime" title="jpoCouponRelationship.createTime" />
    			<ec:column property="createUserCode" title="jpoCouponRelationship.createUserCode" />
    			<ec:column property="remark" title="jpoCouponRelationship.remark" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJpoCouponRelationship(id){
   		<jecs:power powerCode="editJpoCouponRelationship">
					window.location="editJpoCouponRelationship.html?strAction=editJpoCouponRelationship&id="+id;
			</jecs:power>
		}

</script>
