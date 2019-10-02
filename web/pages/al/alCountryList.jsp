<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alCountryList.title"/></title>
<content tag="heading"><jecs:locale key="alCountryList.heading"/></content>
<meta name="menu" content="AlCountryMenu"/>

<c:if test="${not empty successMessages}">
<script>
parent.window.frmCountryTree.location.reload();
</script>
</c:if>

<form action="alCountrys.html?typeId=${typeId }" method="get" name="alCountryForm" id="alCountryForm">

<div id="titleBar" class="searchBar">
			<jecs:power powerCode="addAlCountry">
<button type="button" onclick="window.location.href='<c:url value="/editAlCountry.html"/>?strAction=addAlCountry'" class="btn btn_ins"><jecs:locale  key='member.new.num'/></button> 		

			</jecs:power>
</div>



<ec:table tableId="alCountryListTable"
	items="alCountryList" autoIncludeParameters="true"
	var="alVar"
	retrieveRowsCallback="limit"
	action="${pageContext.request.contextPath}/alCountrys.html"
	width="100%"
	rowsDisplayed="20" form="alCountryForm"
	imagePath="${pageContext.request.contextPath}/images/extremetable/*.gif" 
	>
	
	<ec:exportCsv fileName="country.csv"/>
	<ec:exportXls fileName="country.xls"/>
	
	<ec:row>
		<ec:column property="countryCode" title="alCountry.countryCode" />
		<ec:column property="countryName" title="alCountry.countryName">
			<jecs:locale key="${alVar.countryName }"/>
		</ec:column>
		<ec:column property="countryId" title="title.operation" sortable="false" style="white-space: nowrap;width:5px;">
			<nobr>
  			<jecs:power powerCode="editAlCountry">
				<a href="editAlCountry.html?strAction=editAlCountry&countryId=${alVar.countryId}">
				<img src="images/newIcons/pencil_16.gif" border="0" width="16" height="16"/></a>
			</jecs:power>
			&nbsp;
			</nobr>
		</ec:column>
	</ec:row>
</ec:table>

</form>