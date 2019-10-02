<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jamMsnModuleList.title"/></title>
<content tag="heading"><jecs:locale key="jamMsnModuleList.heading"/></content>
<meta name="menu" content="JamMsnModuleMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJamMsnModule">
		<c:if test="${not empty mtId }">
			    <button type="button" class="btn btn_ins" 
			        onclick="location.href='<c:url value="editJamMsnModule.html"/>?strAction=addJamMsnModule&mtId=${mtId }'"
			        ><jecs:locale key="button.add"/></button>
		</c:if>
		</jecs:power>
</c:set>
<div id="titleBar" class="searchBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jamMsnModuleListTable"
	items="jamMsnModuleList"
	var="jamMsnModule"
	action="${pageContext.request.contextPath}/jamMsnModules.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="mmKey" title="bdOutwardBank.bankCode" />
    			<ec:column property="titile" title="alCharacterType.typeName" />
    			<ec:column property="jamMsnType.msnName" title="jamMsnType.msnName" />
    			<ec:column property="mmType" title="jamMsnModule.mmType" >
    				<jecs:code listCode="msnmodule.type" value="${jamMsnModule.mmType }"/>
    			</ec:column>

					<ec:column property="1" title="title.operation" sortable="false" width="100" >
					
						<img src="../images/newIcons/pencil_16.gif" onclick="javascript:editJamMsnModule('${jamMsnModule.jmmNo}','${jamMsnModule.jamMsnType.mtId}');" alt="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
						
					
					</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJamMsnModule(jmmNo,mtId){
   		<jecs:power powerCode="editJamMsnModule">
					window.location="editJamMsnModule.html?strAction=editJamMsnModule&jmmNo="+jmmNo+"&mtId="+mtId;
			</jecs:power>
		}

</script>
