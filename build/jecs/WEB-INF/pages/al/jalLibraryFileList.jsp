<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jalLibraryFileList.title"/></title>
<content tag="heading"><jecs:locale key="jalLibraryFileList.heading"/></content>
<meta name="menu" content="JalLibraryFileMenu"/>
<script language="javascript" src="scripts/validate.js"></script>

<form action="jalLibraryFiles.html" method="get" name="jalLibraryFilesSearchForm" id="jalLibraryFilesSearchForm">
<div class="searchBar">
	<div class="new_searchBar">
		<label><jecs:locale  key="jalLibraryColumn.plateId"/>:</label>

		<select name="plateId" id="plateId" onchange="toSearchColumns(this)">
		<option value=""></option>
		<c:forEach items="${jalLibraryPlates}" var="jalLibraryPlate">

		<c:if test="${not empty param.plateId }">
			<c:if test="${jalLibraryPlate.plateId==param.plateId}">
				<option value="${jalLibraryPlate.plateId }" selected="selected">${jalLibraryPlate.plateName }</option>
			</c:if>
			<c:if test="${jalLibraryPlate.plateId!=param.plateId}">
				<option value="${jalLibraryPlate.plateId }">${jalLibraryPlate.plateName }</option>
			</c:if>
		</c:if>
		<c:if test="${empty param.plateId }">
			<option value="${jalLibraryPlate.plateId }">${jalLibraryPlate.plateName }</option>
		</c:if>
		</c:forEach>
		</select>
	</div>
<div class="new_searchBar">
	<label><jecs:locale  key="jalLibraryColumn.columnId"/>:</label>

	<select name="columnId" id="columnId">
	<option value=""></option>
	<c:forEach items="${jalLibraryColumns}" var="jalLibraryColumn">
	
	<c:if test="${not empty param.columnId }">
		<c:if test="${jalLibraryColumn.columnId==param.columnId}">
	<option value="${jalLibraryColumn.columnId }"  selected="selected">${jalLibraryColumn.columnName }</option>
		</c:if>
		<c:if test="${jalLibraryColumn.columnId!=param.columnId}">
	<option value="${jalLibraryColumn.columnId }">${jalLibraryColumn.columnName }</option>
		</c:if>
	</c:if>
	<c:if test="${empty param.columnId }">
		
		<option value="${jalLibraryColumn.columnId }">${jalLibraryColumn.columnName }</option>
		
	</c:if>
	
	</c:forEach>
	</select>
</div>

<input type="hidden" name="strAction" value=""/>
<input type="hidden" name="strTempIds" value=""/>

<div class="new_searchBar">
	<button name="search" class="btn btn_sele" style="width:auto" type="submit"><jecs:locale key="operation.button.search"/></button>
	<button name="verify" class="btn btn_long" style="width:auto" onclick="toDown()" type="button"><jecs:locale key="button.download"/></button>
</div>
</div>
</form>
<form action="jalLibraryFiles.html" method="get" name="jalLibraryFilesForm" id="jalLibraryFilesForm">
<ec:table 
	tableId="jalLibraryFileListTable"
	items="jalLibraryFileList"
	var="jalLibraryFile"
	autoIncludeParameters="true"
	form="jalLibraryFilesForm"
	action="${pageContext.request.contextPath}/jalLibraryFiles.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				 
				<ec:column alias="selectedId" headerCell="selectAll" style="width:5px;">
					
					<input type="checkbox" name="selectedId" value="${httpUrl}/${jalLibraryFile.fileUrl}"/>
					
		  		</ec:column>
		  		 
    			<ec:column property="fileName" title="jalLibraryFile.fileName" />
    			<ec:column property="columnName" title="jalLibraryColumn.columnName" />
    			<ec:column property="fileUrl" title="jalLibraryFile.fileUrl" />
				<ec:column property="edit" title="button.download" sortable="false" viewsAllowed="html">
							
								
								<a href="${httpUrl}/${jalLibraryFile.fileUrl}"><img src="<c:url value='/images/icons/download.gif'/>" 
								style="cursor: pointer;" title="<jecs:locale key="button.download"/>"/> </a>
					</ec:column>
				</ec:row>

</ec:table>	
</form>
<script type="text/javascript">

function selectAll(theForm,status){

	var elements=document.getElementsByName("selectedId");
	if(elements!=undefined){
		for(var i=0;i<elements.length;i++){
			elements[i].checked=status;
		}
	}
}
function toDown(){

	var elements=document.getElementsByName("selectedId");
	if(elements==undefined){
		alert("<jecs:locale key="please.select"/>");
		return false;
	}
	var selectedOne=false;
	for(var i=0;i<elements.length;i++){
		if(elements[i].checked){
			selectedOne=true;

			window.open(elements[i].value);
		}
	}
	if(!selectedOne){
		alert("<jecs:locale key="please.select"/>");
		return false;
	}	
}
function toSearchColumns(plateId){

	var theForm = document.getElementById("jalLibraryFilesSearchForm");
	
	theForm.plateId=plateId.value;
	theForm.submit();
}
</script>
