<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="smsSendLogList.title"/></title>
<content tag="heading"><jecs:locale key="smsSendLogList.heading"/></content>
<meta name="menu" content="SmsSendLogMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addSmsSendLog">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editSmsSendLog.html"/>?strAction=addSmsSendLog'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="smsSendLogListTable"
	items="smsSendLogList"
	var="smsSendLog"
	action="${pageContext.request.contextPath}/smsSendLogs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editSmsSendLog('${smsSendLog.sslId}')"
								style="cursor: pointer;" title="<wecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="mobile" title="smsSendLog.mobile" />
    			<ec:column property="sendMsg" title="smsSendLog.sendMsg" />
    			<ec:column property="status" title="smsSendLog.status" />
    			<ec:column property="sendNum" title="smsSendLog.sendNum" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editSmsSendLog(sslId){
   		<jecs:power powerCode="editSmsSendLog">
					window.location="editSmsSendLog.html?strAction=editSmsSendLog&sslId="+sslId;
			</jecs:power>
		}

</script>
