<%@ page pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp"%> 

<title><jecs:locale key="pdExportLogList.title"/></title>
<content tag="heading"><jecs:locale key="pdExportLogList.heading"/></content>
<meta name="menu" content="pdExportLogMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<script type='text/javascript' src='<c:url value="/dwr/interface/pdSendInfoManager.js"/>'></script>
<script type='text/javascript' src='<c:url value="/dwr/engine.js"/>'></script>
<script type='text/javascript' src='<c:url value="/dwr/util.js"/>'></script>
<c:set var="buttons">
	<jecs:locale key="pdexportlog.logtype" />
	<jecs:list listCode="pdexportlog.type" name="logType" showBlankLine="true" id="status" value="${param.logType}" defaultValue=""/>
	<jecs:locale key="pdexportlog.logstatus" />
	<jecs:list listCode="pdexportlog.status" name="logStatus" showBlankLine="true" id="logStatus" value="${param.logStatus}" defaultValue=""/>
	<jecs:title key="pdexportlog.logstarttime"/>
	<input name="logStartTime" size='11' id="logStartTime"  value="${logStartTime}">
   	<img src="./images/calendar/calendar7.gif" id="img_startTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
	<script type="text/javascript"> 
		Calendar.setup({
		inputField     :    "logStartTime", 
		ifFormat       :    "%Y-%m-%d",  
		button         :    "img_startTime", 
		singleClick    :    true
		}); 
	</script>
	- <input name="logEndTime" size='11'  id="logEndTime"  value="${logEndTime}">
   	<img src="./images/calendar/calendar7.gif" id="img_endTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
	<script type="text/javascript"> 
		Calendar.setup({
		inputField     :    "logEndTime", 
		ifFormat       :    "%Y-%m-%d",  
		button         :    "img_endTime", 
		singleClick    :    true
		}); 
	</script>
	<input type="submit" class="button" value="<jecs:locale  key='operation.button.search'/>"/>
</c:set>

<form name="frm" action="${pageContext.request.contextPath}/pdShipFees.html" >
	<input type="hidden" name="strAction" value="pdExportLog" />
	<div id="titleBar">
		<c:out value="${buttons}" escapeXml="false"/>
	</div>
	

<ec:table 
	tableId="pdExportLogListTable"
	items="pdExportLogList"
	var="pdExportLog"
	action="${pageContext.request.contextPath}/pdShipFees.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="false" imagePath="${pageContext.request.contextPath}/images/extremetable/*.gif" form="frm">
		<ec:row style="text-align: center;">
   			<ec:column property="logName" title="pdexportlog.logname" />
   			<ec:column property="logStartTime" title="pdexportlog.logstarttime" >
   				<fmt:formatDate value="${pdExportLog.logStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
   			</ec:column>
   			<ec:column property="logEndTime" title="pdexportlog.logendtime" >
   				<fmt:formatDate value="${pdExportLog.logEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
   			</ec:column>
   			<ec:column property="logUserCode" title="pdProductsMain.editUreNo" />
   			<ec:column property="logType" title="pdexportlog.logtype" >
   				<jecs:code listCode="pdexportlog.type" value="${pdExportLog.logType}"/>
   			</ec:column>
   			<ec:column property="logStatus" title="pdexportlog.logstatus" >
   				<c:choose>
   					<c:when test="${pdExportLog.logStatus==0}">
   						<font color="red"><jecs:code listCode="pdexportlog.status" value="${pdExportLog.logStatus}"/></font>
   					</c:when>
   					<c:when test="${pdExportLog.logStatus==1}">
   						<font color="green"><jecs:code listCode="pdexportlog.status" value="${pdExportLog.logStatus}"/></font>
   					</c:when>
   					<c:when test="${pdExportLog.logStatus==2}">
   						<font color="grey"><jecs:code listCode="pdexportlog.status" value="${pdExportLog.logStatus}"/></font>
   					</c:when>
   				</c:choose>
   			</ec:column>
   			<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
				<c:if test="${pdExportLog.logStatus==1}">
					<a href='<jecs:code listCode="jpmproductsaleimage.url" value="1"/>xls/${pdExportLog.logName}'><font color="red"><b><jecs:locale key="button.download"/></b></font></a>
				</c:if>
			</ec:column>
			<ec:column property="logDesc" title="poMemberOrder.remark" />
		</ec:row>
</ec:table>	
<script type="text/javascript">
	function editpdExportLog(psfId) {
 		<jecs:power powerCode="editpdExportLog">
			window.location="editpdExportLog.html?strAction=editpdExportLog&psfId="+psfId;
		</jecs:power>
	}
	function updateHurryFlag(siNo,sonId,jsonUrl){
		//waiting();
		var str = pdSendInfoManager.jsonArraySend(siNo,jsonUrl,sonId,validatePdHurry);
	}
	
	function validatePdHurry(ret){
		alert(ret);
		//waitingEnd();
		/**if(ret==true){
			alert("<jecs:locale key="pd.update.hurry.success"/>");
			document.getElementById('hurryFlag').value='1';
		}else{
			alert("<jecs:locale key="pd.update.hurry.failured"/>");
		}**/	
	}
</script>
</form>