<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alCityList.title"/></title>
<content tag="heading"><jecs:locale key="alCityList.heading"/></content>
<meta name="menu" content="AlCityMenu"/>

<c:if test="${not empty successMessages}">
<script>
parent.window.frmCountryTree.location.reload();
</script>
</c:if>

<form action="alCitys.html?stateProvinceId=${param.stateProvinceId }" method="get" name="alCityForm" id="alCityForm">
<div  id="titleBar" class="searchBar">
<jecs:power powerCode="addAlCity">
	<button type="button" onclick="window.location.href='<c:url value="/editAlCity.html"/>?strAction=addAlCity&stateProvinceId=${param.stateProvinceId }'" value="<jecs:locale  key='member.new.num'/>" class="btn btn_ins"><jecs:locale  key='member.new.num'/>
	</button>
	</jecs:power>
</div>


</form>
<ec:table 
	tableId="alCityListTable"
	items="alCityList"
	var="alCity"
	action="${pageContext.request.contextPath}/alCitys.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="alStateProvince.stateProvinceName" title="alCompany.stateProvinceCode" />
    			<ec:column property="cityCode" title="alCity.cityCode" />
    			<ec:column property="cityName" title="alCity.cityName" />

		<ec:column property="1" title="title.operation" sortable="false" >
			<jecs:power powerCode="editAlCity">
				<a href="editAlCity.html?strAction=editAlCity&cityId=${alCity.cityId}">
				<img src="images/newIcons/pencil_16.gif" border="0" width="16" height="16"/></a>
			</jecs:power>
		</ec:column>
		
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editAlCity(cityId){
   		<jecs:power powerCode="editAlCity">
					window.location="editAlCity.html?strAction=editAlCity&cityId="+cityId;
			</jecs:power>
		}

</script>
