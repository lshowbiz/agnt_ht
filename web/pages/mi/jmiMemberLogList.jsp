<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiMemberLogList.title"/></title>
<content tag="heading"><jecs:locale key="jmiMemberLogList.heading"/></content>
<meta name="menu" content="JmiMemberLogMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJmiMemberLog">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJmiMemberLog.html"/>?strAction=addJmiMemberLog'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jmiMemberLogListTable"
	items="jmiMemberLogList"
	var="jmiMemberLog"
	action="${pageContext.request.contextPath}/jmiMemberLogs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJmiMemberLog('${jmiMemberLog.logId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="userCode" title="jmiMemberLog.userCode" />
    			<ec:column property="userName" title="jmiMemberLog.userName" />
    			<ec:column property="beforeBank" title="jmiMemberLog.beforeBank" />
    			<ec:column property="beforeBankaddress" title="jmiMemberLog.beforeBankaddress" />
    			<ec:column property="beforeBankbook" title="jmiMemberLog.beforeBankbook" />
    			<ec:column property="beforeBankcard" title="jmiMemberLog.beforeBankcard" />
    			<ec:column property="beforeBankprovince" title="jmiMemberLog.beforeBankprovince" />
    			<ec:column property="beforeBankcity" title="jmiMemberLog.beforeBankcity" />
    			<ec:column property="logTime" title="jmiMemberLog.logTime" />
    			<ec:column property="logType" title="jmiMemberLog.logType" />
    			<ec:column property="logUserCode" title="jmiMemberLog.logUserCode" />
    			<ec:column property="afterBank" title="jmiMemberLog.afterBank" />
    			<ec:column property="afterBankaddress" title="jmiMemberLog.afterBankaddress" />
    			<ec:column property="afterBankbook" title="jmiMemberLog.afterBankbook" />
    			<ec:column property="afterBankcard" title="jmiMemberLog.afterBankcard" />
    			<ec:column property="afterBankprovince" title="jmiMemberLog.afterBankprovince" />
    			<ec:column property="afterBankcity" title="jmiMemberLog.afterBankcity" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJmiMemberLog(logId){
   		<jecs:power powerCode="editJmiMemberLog">
					window.location="editJmiMemberLog.html?strAction=editJmiMemberLog&logId="+logId;
			</jecs:power>
		}

</script>
