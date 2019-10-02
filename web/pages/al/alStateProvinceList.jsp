<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alStateProvinceList.title"/></title>
<content tag="heading"><jecs:locale key="alStateProvinceList.heading"/></content>
<meta name="menu" content="AlStateProvinceMenu"/>

<c:if test="${not empty successMessages}">
<script>
parent.window.frmCountryTree.location.reload();
</script>
</c:if>

<form action="alStateProvinces.html?countryId=${param.countryId }" method="get" name="alStateProvinceForm" id="alStateProvinceForm">

<div id="titleBar" class="searchBar">
			<jecs:power powerCode="addAlStateProvince">
<button type="button" onclick="window.location.href='<c:url value="/editAlStateProvince.html"/>?strAction=addAlStateProvince&countryId=${param.countryId }'"  class="btn btn_ins"><jecs:locale  key='member.new.num'/></button>			

			</jecs:power>
</div>


<ec:table tableId="alStateProvinceListTable"
	items="alStateProvinceList" autoIncludeParameters="true"
	var="alVar"
	retrieveRowsCallback="limit"
	action="${pageContext.request.contextPath}/alStateProvinces.html"
	width="100%"
	rowsDisplayed="20" form="alStateProvinceForm"
	imagePath="${pageContext.request.contextPath}/images/extremetable/*.gif" 
	>
	
	<ec:exportCsv fileName="state_province.csv"/>
	<ec:exportXls fileName="state_province.xls"/>
	
	<ec:row>
		<ec:column property="alCountry.countryCode" title="alStateProvince.alCountry.countryCode" />
		<ec:column property="stateProvinceCode" title="alStateProvince.stateProvinceCode" />		
		<ec:column property="stateProvinceName" title="alStateProvince.stateProvinceName">
			<jecs:locale key="${alVar.stateProvinceName }"/>
		</ec:column>
		<ec:column property="1" title="title.operation" sortable="false" style="white-space: nowrap;width:5px;">
			<nobr>
			<jecs:power powerCode="editAlStateProvince">
				<a href="editAlStateProvince.html?strAction=editAlStateProvince&stateProvinceId=${alVar.stateProvinceId}">
				<img src="images/newIcons/pencil_16.gif" border="0" width="16" height="16"/></a>
			</jecs:power>
			&nbsp;
			</nobr>
		</ec:column>
	</ec:row>
</ec:table>

</form>