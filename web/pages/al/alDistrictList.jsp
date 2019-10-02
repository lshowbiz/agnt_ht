<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alDistrictList.title"/></title>
<content tag="heading"><jecs:locale key="alDistrictList.heading"/></content>
<meta name="menu" content="AlDistrictMenu"/>

<c:if test="${not empty successMessages}">
<script>
parent.window.frmCountryTree.location.reload();
</script>
</c:if>

<form action="alDistricts.html?cityId=${param.cityId }" method="get" name="districtForm" id="districtForm">
<div class="searchBar">
<jecs:power powerCode="addAlDistrict">
	<input type="button" onclick="window.location.href='<c:url value="/editAlDistrict.html"/>?strAction=addAlDistrict&cityId=${param.cityId }'" value="<jecs:locale  key='member.new.num'/>" class="button">
	</jecs:power>
</div>


</form>

<ec:table 
	tableId="alDistrictListTable"
	items="alDistrictList"
	var="alDistrict"
	action="${pageContext.request.contextPath}/alDistricts.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="alCity.cityName" title="alDistrict.in.city" />
    			
    			<ec:column property="districtCode" title="alDistrict.districtCode" />
    			<ec:column property="districtName" title="alDistrict.districtName" />	
    			
    			<ec:column property="1" title="title.operation" sortable="false">
					<jecs:power powerCode="editAlDistrict">
						<a href="editAlDistrict.html?strAction=editAlDistrict&districtId=${alDistrict.districtId}">
						<img src="images/newIcons/pencil_16.gif" border="0" width="16" height="16"/></a>
					</jecs:power>
					</ec:column>
				</ec:row>

</ec:table>	


<script type="text/javascript">

   function editAlDistrict(districtId){
   		<jecs:power powerCode="editAlDistrict">
					window.location="editAlDistrict.html?strAction=editAlDistrict&districtId="+districtId;
			</jecs:power>
		}

</script>
