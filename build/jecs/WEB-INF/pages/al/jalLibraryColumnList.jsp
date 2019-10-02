<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jalLibraryColumnList.title"/></title>
<content tag="heading"><jecs:locale key="jalLibraryColumnList.heading"/></content>
<meta name="menu" content="JalLibraryColumnMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJalLibraryColumn">
			    <button type="button" class="btn btn_ins" style="margin-right: 5px;width:auto"
			        onclick="location.href='<c:url value="/editJalLibraryColumn.html"/>?strAction=addJalLibraryColumn'"><jecs:locale key="button.add"/></button>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jalLibraryColumnListTable"
	items="jalLibraryColumnList"
	var="jalLibraryColumn"
	action="${pageContext.request.contextPath}/jalLibraryColumns.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJalLibraryColumn('${jalLibraryColumn.columnId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
							&nbsp;
							<img src="<c:url value='/images/icons/back.gif'/>" 
								onclick="javascript:toUploadLibrary('${jalLibraryColumn.columnId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.upload"/>"/>
								&nbsp;

					</ec:column>
				
    			<ec:column property="columnName" title="jalLibraryColumn.columnName" />
    			<ec:column property="plateName" title="jalLibraryColumn.plateId" />
    			
    			<ec:column property="createName" title="jalLibraryColumn.createName" />
    			<ec:column property="createTime" title="jalLibraryColumn.createTime" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJalLibraryColumn(columnId){
   		<jecs:power powerCode="editJalLibraryColumn">
					window.location="editJalLibraryColumn.html?strAction=editJalLibraryColumn&columnId="+columnId;
			</jecs:power>
		}
function toUploadLibrary(columnId){
   		<jecs:power powerCode="addJalLibraryFile">
					window.location="editJalLibraryFile.html?strAction=addJalLibraryFile&columnId="+columnId;
			</jecs:power>
		}
</script>
