<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysCompanyPowList.title"/></title>
<content tag="heading"><jecs:locale key="sysCompanyPowList.heading"/></content>
<meta name="menu" content="SysCompanyPowMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addSysCompanyPow">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editSysCompanyPow.html"/>?strAction=addSysCompanyPow'"
			     value="<jecs:locale key="member.new.num"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="sysCompanyPowListTable"
	items="sysCompanyPowList"
	var="sysCompanyPow"
	action="${pageContext.request.contextPath}/sysCompanyPows.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row onclick="javascript:editSysCompanyPow('${sysCompanyPow.id}')">
    			<ec:column property="powerId" title="sysCompanyPow.powerId" />
    			<ec:column property="companyCode" title="sysCompanyPow.companyCode" />
				</ec:row>

</ec:table>	

<form name="form1" action="<c:url value='editSysCompanyPow.html'/>">
			<input type="hidden" name="strAction" id="strAction"/>
			<input type="hidden" name='id' id='id'/>
</form>

<script type="text/javascript">

   function editSysCompanyPow(id){
   		<jecs:power powerCode="editSysCompanyPow">
					window.location="editSysCompanyPow.html?strAction=editSysCompanyPow&id="+id;
			</jecs:power>
		}

</script>
