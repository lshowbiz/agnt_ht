<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysReportLogList.title"/></title>
<content tag="heading"><jecs:locale key="sysReportLogList.heading"/></content>
<meta name="menu" content="SysReportLogMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addSysReportLog">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editSysReportLog.html"/>?strAction=addSysReportLog'"
			     value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="sysReportLogListTable"
	items="sysReportLogList"
	var="sysReportLog"
	action="${pageContext.request.contextPath}/sysReportLogs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row onclick="javascript:editSysReportLog('${sysReportLog.reportId}')">
    			<ec:column property="reportName" title="sysReportLog.reportName" />
    			<ec:column property="companyCode" title="sysReportLog.companyCode" />
    			<ec:column property="userCode" title="sysReportLog.userCode" />
    			<ec:column property="fileName" title="sysReportLog.fileName" />
    			<ec:column property="createTime" title="sysReportLog.createTime" />
    			<ec:column property="usedTime" title="sysReportLog.usedTime" />
    			<ec:column property="recordsCount" title="sysReportLog.recordsCount" />
    			<ec:column property="status" title="sysReportLog.status" />
				</ec:row>

</ec:table>	

<form name="form1" action="<c:url value='editSysReportLog.html'/>">
			<input type="hidden" name="strAction" id="strAction"/>
			<input type="hidden" name='reportId' id='reportId'/>
</form>

<script type="text/javascript">

   function editSysReportLog(reportId){
   		<jecs:power powerCode="editSysReportLog">
					window.location="editSysReportLog.html?strAction=editSysReportLog&reportId="+reportId;
			</jecs:power>
		}

</script>
