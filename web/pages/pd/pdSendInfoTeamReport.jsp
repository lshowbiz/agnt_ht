<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdPeriodList.title" /></title>
<content tag="heading">
<jecs:locale key="bdPeriodList.heading" />
</content>
<meta name="menu" content="BdSendRecordMenu" />

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<script type="text/javascript" src="./scripts/validate.js"> </script> 
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/print.css'/>" />
<style media="print">
	.noPrint { 
		display: none;
	}
</style>
<c:if test="${not empty errorMessages }">

	${ errorMessages }
	
		<c:remove var="errorMessages" scope="session" />
</c:if>
<form:form commandName="bdPeriod" method="post" action="pdSendInfoTeamReport.html"  onsubmit="return validateOthers(this)" id="bdPeriodForm" enctype="multipart/form-data">
														

	<spring:bind path="bdPeriod.*">
	    <c:if test="${not empty status.errorMessages}">
	    <div class="error">    
	        <c:forEach var="error" items="${status.errorMessages}">
	            <img src="<c:url value="/images/iconWarning.gif"/>"
	                alt="<jecs:locale key="icon.warning"/>" class="icon" />
	            <c:out value="${error}" escapeXml="false"/><br />
	        </c:forEach>
	    </div>
	    </c:if>
	</spring:bind>

	<table class="detail" width="70%">
	<tbody class="window" >
	<form:hidden path="wid"/>
		<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="bdReconsumMoneyReport.companyCH"/></span></th>
			<td>
			<span class="textbox"><jecs:company name="companyCode" selected="${param.companyCode }"  withAA="false"  styleClass="textbox-text" /></span>
			</td>
		</tr>
		</c:if>
			  <tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="date.type"/></span></th>
			<td>
			<span class="textbox">
				<jecs:locale key="sendinfo.okTime"/></span>
			</td>
			</tr> 
			<tr class="edit_tr">	
				   <th><span class="text-font-style-tc"><jecs:title key="pd.okTime"/></span></th>
				 <td>
				 	<span class="textbox" style="width:82px;">
				    <input name="okTimeStart" id="okTimeStart" type="text" value="${param.okTimeStart }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" Class="textbox-text"/>
	               	</span>
	                - 
	                <span class="textbox" style="width:82px;">
	                <input name="okTimeEnd" id="okTimeEnd" type="text" value="${param.okTimeEnd }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" Class="textbox-text"/>
					</span>
				</td>
			</tr>
			<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="fiPayData.importFile"/></span></th>
		    <td align="left">
		   		<span class="textbox"><input name="importExcelFile" type="file" id="importExcelFile" size="50" /></span>
			</td>
			</tr>
		    <tr class="edit_tr">
				<th>
					<label>
						<jecs:locale key="fiPayData.import.notice.label" />
						:
					</label>
				</th>
				<td colspan="3" >
				 	<jecs:locale key="pdSendInfoTeam.import.notice.text" />
				</td>			
			</tr>
		<tr>
		
		<tr>		
			<td class="command" colspan="4" align="center">
			<input type="submit" name="submit" value="<jecs:locale key="button.report"/>" class="btn btn_sele"/>
				 <input type="hidden" name="strAction" value="pdSendInfoTeamReport"/> 
		</td>
		</tr>
	</table>
	
</form:form>

<script type="text/javascript">
function validateOthers(theForm){
	
	var startdate= theForm.okTimeStart.value;
	var enddate= theForm.okTimeEnd.value;
	var startD = new Date(Date.parse(startdate.replace(/-/g,"/")));
	var endD   = new Date(Date.parse(enddate.replace(/-/g,"/")));
	var days = parseInt((endD.getTime()-startD.getTime()) / (1000 * 60 * 60 * 24));
	if(days > 30){
	alert("<jecs:locale key="date.isError"/>");
	return false;
	}  
	
	var file  = theForm.importExcelFile.value;
	if(file =='' || file == null){
		alert("<jecs:locale key="fiPayData.xlsFile.required"/>");
		theForm.importExcelFile.focus();
		return false;
	}else{
		var index = file.lastIndexOf(".");
		if(index < 0) {
			alert("<jecs:locale key="error.xlsFormat"/>");
			return false;
			} else {
			var ext = file.substring(index + 1, file.length);
			if(ext != "xls") {
				alert("<jecs:locale key="error.xlsFormat"/>");
				return false;
			}
		}
		return true;
	}
	
	
}
</script>
