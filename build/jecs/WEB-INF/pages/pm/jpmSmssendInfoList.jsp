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
	<jecs:title  key="jpmSmssendInfo.smsRecipient"/>
	<input name="smsRecipient" id="smsRecipient" value="<c:out value='${param.smsRecipient}'/>"  cssClass="text medium"/>
	<jecs:title  key="jpmSmssendInfo.smsOperator"/>
	<input name="smsOperator" id="smsOperator" value="<c:out value='${param.smsOperator}'/>"  cssClass="text medium"/>
	<jecs:title  key="jpmSmssendInfo.phoneNum"/>
	<input name="phoneNum" id="phoneNum" value="<c:out value='${param.phoneNum}'/>"  cssClass="text medium"/>
	<jecs:locale key="jpmSmssendInfo.smsType" />
	<jecs:list listCode="jpmsmssendinfo" name="smsType" showBlankLine="true" id="smsType" value="${param.smsType}" defaultValue=""/>
	<jecs:locale key="pdexportlog.logstatus" />
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
	<input type="hidden" name="strAction" value="jpmSmssendInfo" />
	<div id="titleBar">
		<c:out value="${buttons}" escapeXml="false"/>
	</div>
	

<ec:table 
	tableId="jpmSmssendInfoListTable"
	items="jpmSmssendInfoList"
	var="jpmSmssendInfo"
	action="${pageContext.request.contextPath}/pdShipFees.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="false" imagePath="${pageContext.request.contextPath}/images/extremetable/*.gif" form="frm">
		<ec:row style="text-align: center;">
   			<ec:column property="smsRecipient" title="jpmSmssendInfo.smsRecipient" />
   			<ec:column property="smsTime" title="jpmSmssendInfo.smsTime" >
   				<fmt:formatDate value="${jpmSmssendInfo.smsTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
   			</ec:column>
   			<ec:column property="smsOperator" title="jpmSmssendInfo.smsOperator" />
   			<ec:column property="phoneNum" title="jpmSmssendInfo.phoneNum" />
   			<ec:column property="smsType" title="jpmSmssendInfo.smsType" >
   				<jecs:code listCode="jpmsmssendinfo.type" value="${jpmSmssendInfo.smsType}"/>
   			</ec:column>
			<ec:column property="smsMessage" title="jpmSmssendInfo.smsMessage" />
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