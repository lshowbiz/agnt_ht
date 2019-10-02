<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alCompanyList.title"/></title>
<content tag="heading"><jecs:locale key="alCompanyList.heading"/></content>
<meta name="menu" content="AlCompanyMenu"/>

<form action="alCompanys.html?typeId=${typeId }" method="get" name="alCompanyForm" id="alCompanyForm">

<table class='grid_table'>
	<tr class='grid_span'>
		<td>
			<jecs:power powerCode="addAlCompany">
			<a href="#" onclick="window.location.href='<c:url value="/editAlCompany.html"/>?strAction=addAlCompany'">
				<img alt="<jecs:locale  key='member.new.num'/>" src="images/newIcons/plus_16.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale key="member.new.num"/><jecs:locale key="bdReconsumMoneyReport.companyCH"/></font>
			</a>
			</jecs:power>
		</td>
	</tr>
</table>

<ec:table tableId="alCompanyListTable"
	items="alCompanyList" autoIncludeParameters="true"
	var="alVar"
	retrieveRowsCallback="limit"
	action="${pageContext.request.contextPath}/alCompanys.html"
	width="100%"
	rowsDisplayed="20" form="alCompanyForm"
	imagePath="${pageContext.request.contextPath}/images/extremetable/*.gif" 
	>
	
	<ec:exportCsv fileName="company.csv"/>
	<ec:exportXls fileName="company.xls"/>
	
	<ec:row>
		<ec:column property="1" title="title.operation" sortable="false" style="white-space: nowrap;width:5px;">
			<nobr>
			<jecs:power powerCode="editAlCompany">
				<a href="editAlCompany.html?strAction=editAlCompany&companyId=${alVar.companyId}">
				<img src="images/newIcons/pencil_16.gif" border="0" width="16" height="16"/></a>
			</jecs:power>
			<jecs:power powerCode="editAlCompanyArea">
				<a href="editAlCompanyArea.html?strAction=editAlCompanyArea&companyId=${alVar.companyId}">
				<img src="images/newIcons/globe_16.gif" border="0" width="16" height="16"/></a>
			</jecs:power>
			</nobr>
		</ec:column>
		<ec:column property="companyCode" title="alCompany.companyCode" />
		<ec:column property="companyName" title="alCompany.companyName"/>
		<ec:column property="regName" title="alCompany.regName"/>
		<ec:column property="preAgentCode" title="alCompany.preAgentCode"/>
		<ec:column property="characterCode" title="alCompany.characterCode"/>
		<ec:column property="currencyCode" title="alCompany.currencyCode"/>
		<ec:column property="taxRate" title="alCompany.taxRate"/>
		
	</ec:row>
</ec:table>

</form>