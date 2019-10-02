<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jalLibraryPlateList.title"/></title>
<content tag="heading"><jecs:locale key="jalLibraryPlateList.heading"/></content>
<meta name="menu" content="JalLibraryPlateMenu"/>

<c:set var="buttons">
	
		<jecs:power powerCode="addJalLibraryPlate">
			    <button type="button" class="btn btn_ins" style="margin-right: 5px;width:auto;"
			        onclick="location.href='<c:url value="/editJalLibraryPlate.html"/>?strAction=addJalLibraryPlate'"><jecs:locale key="button.add"/></button>
		</jecs:power>
</c:set>

<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>

<ec:table 
	tableId="jalLibraryPlateListTable"
	items="jalLibraryPlateList"
	var="jalLibraryPlate"
	action="${pageContext.request.contextPath}/jalLibraryPlates.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJalLibraryPlate('${jalLibraryPlate.plateId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="plateName" title="jalLibraryPlate.plateName" />
    			<ec:column property="plateIndex" title="jalLibraryPlate.plateIndex" />
    			<ec:column property="plateRemark" title="jalLibraryPlate.plateRemark" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJalLibraryPlate(plateId){
   		<jecs:power powerCode="editJalLibraryPlate">
					window.location="editJalLibraryPlate.html?strAction=editJalLibraryPlate&plateId="+plateId;
			</jecs:power>
		}

</script>
