<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfi99billPosSendLogList.title"/></title>
<content tag="heading"><jecs:locale key="jfi99billPosSendLogList.heading"/></content>
<meta name="menu" content="Jfi99billPosSendLogMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJfi99billPosSendLog">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJfi99billPosSendLog.html"/>?strAction=addJfi99billPosSendLog'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jfi99billPosSendLogListTable"
	items="jfi99billPosSendLogList"
	var="jfi99billPosSendLog"
	action="${pageContext.request.contextPath}/jfi99billPosSendLogs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJfi99billPosSendLog('${jfi99billPosSendLog.logId}')"
								style="cursor: pointer;" title="<wecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="companyCode" title="jfi99billPosSendLog.companyCode" />
    			<ec:column property="userCode" title="jfi99billPosSendLog.userCode" />
    			<ec:column property="actionType" title="jfi99billPosSendLog.actionType" />
    			<ec:column property="actionNo" title="jfi99billPosSendLog.actionNo" />
    			<ec:column property="createCode" title="jfi99billPosSendLog.createCode" />
    			<ec:column property="createTime" title="jfi99billPosSendLog.createTime" />
    			<ec:column property="returnTime" title="jfi99billPosSendLog.returnTime" />
    			<ec:column property="hashMd5Code" title="jfi99billPosSendLog.hashMd5Code" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJfi99billPosSendLog(logId){
   		<jecs:power powerCode="editJfi99billPosSendLog">
					window.location="editJfi99billPosSendLog.html?strAction=editJfi99billPosSendLog&logId="+logId;
			</jecs:power>
		}

</script>
