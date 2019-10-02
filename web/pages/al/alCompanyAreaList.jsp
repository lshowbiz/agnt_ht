<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alCompanyAreaList.title"/></title>
<content tag="heading"><jecs:locale key="alCompanyAreaList.heading"/></content>
<meta name="menu" content="AlCompanyAreaMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addAlCompanyArea">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editAlCompanyArea.html"/>?strAction=addAlCompanyArea'"  <jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="alCompanyAreaListTable"
	items="alCompanyAreaList"
	var="alCompanyArea"
	action="${pageContext.request.contextPath}/alCompanyAreas.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row onclick="javascript:editAlCompanyArea('${alCompanyArea.id}')">
    			<ec:column property="companyCode" title="alCompanyArea.companyCode" />
    			<ec:column property="level1AreaCode" title="alCompanyArea.level1AreaCode" />
    			<ec:column property="level2AreaCode" title="alCompanyArea.level2AreaCode" />
    			<ec:column property="level3AreaCode" title="alCompanyArea.level3AreaCode" />
    			<ec:column property="level4AreaCode" title="alCompanyArea.level4AreaCode" />
    			<ec:column property="level5AreaCode" title="alCompanyArea.level5AreaCode" />
    			<ec:column property="level6AreaCode" title="alCompanyArea.level6AreaCode" />
				</ec:row>

</ec:table>	

<form name="form1" action="<c:url value='editAlCompanyArea.html'/>">
			<input type="hidden" name="strAction" id="strAction"/>
			<input type="hidden" name='id' id='id'/>
</form>

<script type="text/javascript">

   function editAlCompanyArea(id){
   		<jecs:power powerCode="editAlCompanyArea">
					window.location="editAlCompanyArea.html?strAction=editAlCompanyArea&id="+id;
			</jecs:power>
		}

</script>
