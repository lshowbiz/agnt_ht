<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jamDownloadList.title"/></title>
<content tag="heading"><jecs:locale key="jamDownloadList.heading"/></content>
<meta name="menu" content="JamDownloadMenu"/>


<div class="searchBar">
	<div class="new_searchBar" style="margin-left:20px;">
		<jecs:power powerCode="addJamDownload">
			    <button type="button" class="btn btn_ins" style="margin-right: 5px;width:auto"
			        onclick="location.href='<c:url value="/editJamDownload.html"/>?strAction=addJamDownload'"><jecs:locale key="member.new.num"/></button>
		</jecs:power>
	</div>
</div>
<ec:table 
	tableId="jamDownloadListTable"
	items="jamDownloadList"
	var="jamDownload"
	action="${pageContext.request.contextPath}/jamDownloads.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="fileType" title="jamDownload.fileType" >
    				<jecs:code listCode="filetype" value="${jamDownload.fileType}"/>
    			
    			</ec:column>
    			<ec:column property="fileName" title="uploadForm.name" />
    			<ec:column property="fileLink" title="jamDownload.fileLink" />
    			<ec:column property="fileDesc" title="vtVote.description" />
    			<ec:column property="status" title="customerFollow.state" >
    				<jecs:code listCode="amannounce.status" value="${jamDownload.status}"/>
    			</ec:column>
    			<ec:column property="createNo" title="customerRecord.createNo" />
    			<ec:column property="createTime" title="pd.createTime" />
    			
    			
    			
    			<ec:column property="1" title="title.operation" sortable="false" width="100px">
    				
					<jecs:power powerCode="viewJamDownload">
						<img src="images/newIcons/search_16.gif" onclick="window.location.href='editJamDownload.html?dlId=${jamDownload.dlId}&strAction=viewJamDownload';" alt="<jecs:locale key="function.menu.view"/>" style="cursor:pointer"/>
					</jecs:power>
					<jecs:power powerCode="editJamDownload">
						<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editJamDownload.html?dlId=${jamDownload.dlId}&strAction=editJamDownload';" alt="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
					</jecs:power>
    			
    			</ec:column>
    			
				</ec:row>

</ec:table>	

