<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiBcoinPayconfigDetailList.title" />
</title>
<content tag="heading">
<jecs:locale key="fiBcoinPayconfigDetailList.heading" />
</content>
<meta name="menu" content="FiBcoinPayconfigDetailMenu" />

<c:set var="buttons">
	<jecs:power powerCode="addFiBcoinPayconfigDetail">
		<div class="new_searchBar">
			<button name="search" class="btn btn_ins" type="button"
				onclick="location.href='<c:url value="/editFiBcoinPayconfigDetail.html"/>?strAction=addFiBcoinPayconfigDetail'">
				<jecs:locale key="button.add"/>
			</button>
		</div>
	</jecs:power>
</c:set>
<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false" />
</div>
<ec:table tableId="fiBcoinPayconfigDetailListTable"
	items="fiBcoinPayconfigDetailList" var="fiBcoinPayconfigDetail"
	action="${pageContext.request.contextPath}/fiBcoinPayconfigDetails.html"
	width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
	sortable="true" imagePath="/jecs/images/extremetable/*.gif">
	<ec:row>
		<ec:column property="edit" title="title.operation" sortable="false"
			viewsAllowed="html">
			<img src="<c:url value='/images/icons/editIcon.gif'/>"
				onclick="javascript:editFiBcoinPayconfigDetail('${fiBcoinPayconfigDetail.detailId}')"
				style="cursor: pointer;" title="<jecs:locale key="button.edit"/>" />
		</ec:column>
		<ec:column property="productNo"
			title="fiBcoinPayconfigDetail.productNo" />
		<ec:column property="configId" title="fiBcoinPayconfigDetail.configId" />
	</ec:row>

</ec:table>

<script type="text/javascript">

   function editFiBcoinPayconfigDetail(detailId){
   		<jecs:power powerCode="editFiBcoinPayconfigDetail">
					window.location="editFiBcoinPayconfigDetail.html?strAction=editFiBcoinPayconfigDetail&detailId="+detailId;
			</jecs:power>
		}

</script>
