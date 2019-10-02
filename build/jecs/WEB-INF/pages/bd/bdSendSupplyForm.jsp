<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdSendSupply.title"/></title>
<content tag="heading"><jecs:locale key="bdSendRecordDetail.heading"/></content>


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<script type="text/javascript">
function validateOthers(theForm){
	if(theForm.registerDate.value==""){
		alert("<jecs:locale key="please.input.date"/>");
		theForm.registerDate.focus();
		return false;
	}
	return true;
}
</script>
<spring:bind path="jbdSendRecordHist.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="images/newIcons/warning_16.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form:form commandName="jbdSendRecordHist" method="post" action="" id="bdSendSupplyForm" onsubmit="if(isFormPosted()){return true;}{return false;}">
	<div class="searchBar">
	<input type="submit" class="button" name="back" value="<jecs:locale key="bdSendSupply.supplySuccess"/>" />
		<input type="button" class="button" name="back"  onclick="window.history.back()" value="<jecs:locale key="operation.button.return"/>" />
	</div>

<table class='detail' width="100%">
    <tr><th>
        <jecs:label  key="bdBounsDeduct.wweek"/>
    </th>
    <td align="left">
        <!--form:errors path="wweek" cssClass="fieldError"/-->
        <jecs:weekFormat week="${jbdSendRecordHist.wweek }" weekType="w" />
    </td></tr>

    <tr><th>
        <jecs:label  key="miMember.memberNo"/>
    </th>
    <td align="left">
        <!--form:errors path="memberNo" cssClass="fieldError"/-->
        ${jbdSendRecordHist.jmiMember.userCode }
    </td></tr>

    <tr><th>
        <jecs:label  key="bdCalculatingSubDetail.name"/>
    </th>
    <td align="left">
        <!--form:errors path="name" cssClass="fieldError"/-->
        ${ jbdSendRecordHist.name}
    </td></tr>

	<tr><th>
        <jecs:label  key="bdSendRecord.remittanceBNum"/>
    </th>
    <td align="left">
        <!--form:errors path="name" cssClass="fieldError"/-->
        <select name="remittanceBNum">

        <c:forEach items="${bdOutwardBanks }" var="var">
				<c:if test="${var.bankCode==jbdSendRecordHist.remittanceBNum }">
					<option value="${var.bankId }" selected="selected">${var.bankCode }-${var.bankName }</option>
				</c:if>
				<c:if test="${var.bankCode!=jbdSendRecordHist.remittanceBNum }">
					<option value="${var.bankId }">${var.bankCode }-${var.bankName }</option>
				</c:if>
        		

        	
        </c:forEach>
        </select>
    </td></tr>

    <tr><th>
        <jecs:label  key="bdSendRecord.openBank"/>
    </th>
    <td align="left">
        <!--form:errors path="openBank" cssClass="fieldError"/-->
        ${jbdSendRecordHist.jmiMember.bank }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="bdSendRemittanceReport.openCityCH"/>
    </th>
    <td align="left">
        <!--form:errors path="bcity" cssClass="fieldError"/-->
        ${jbdSendRecordHist.jmiMember.bankaddress }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="bdOutwardBank.accountName"/>
    </th>
    <td align="left">
        <!--form:errors path="bname" cssClass="fieldError"/-->
        ${jbdSendRecordHist.jmiMember.bankbook }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="bdSendRecord.bnum"/>
    </th>
    <td align="left">
        <!--form:errors path="bnum" cssClass="fieldError"/-->
       ${jbdSendRecordHist.jmiMember.bankcard }
    </td></tr>

    
    
 	<tr><th>
        <jecs:label  key="bdSendRecord.remittanceMoney"/>
    </th>
    <td align="left">
        <!--form:errors path="remittanceMoney" cssClass="fieldError"/-->
       ${jbdSendRecordHist.remittanceMoney }
    </td></tr>
    
    

    

    <tr><th>
        <jecs:label  key="bdSendRecord.sendLateCause"/>
    </th>
    <td align="left">
        <!--form:errors path="sendLateCause" cssClass="fieldError"/-->
       ${jbdSendRecordHist.sendLateCause }
    </td></tr>

    <tr><th>
        <jecs:label  key="bdSendRecord.sendLateRemark"/>
    </th>
    <td align="left">
        <!--form:errors path="sendLateRemark" cssClass="fieldError"/-->
        ${jbdSendRecordHist.sendLateRemark }
    </td></tr>

   


	<tr><th>
        <jecs:label  key="bdSendRecord.sendDate"/>
    </th>
    <td align="left">
        <!--form:errors path="sendDate" cssClass="fieldError"/-->
			<input type="text" name="registerDate" id="registerDate" value="" size="8">
			<img src="./images/calendar/calendar7.gif" id="img_startOrderDate" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
			</span>
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "registerDate", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_startOrderDate", 
					singleClick    :    true
					}); 
				</script>
				<script type="text/javascript">
						var s="";
						var d = new Date(); 
						s += d.getYear()+ "-"; 
						s += (d.getMonth() + 1) + "-"; 
						s += d.getDate(); 
						document.getElementById('registerDate').value=s;
				</script>
    </td></tr>

    <tr><th>
        <jecs:label  key="bdSendRecord.sendRemark"/>
    </th>
    <td align="left">
        <!--form:errors path="sendRemark" cssClass="fieldError"/-->
         <form:textarea rows="6" cols="50" path="sendRemark" id="sendRemark" cssClass="text medium"/>
    </td></tr>
<%--<tr>
	<th class="command"><jecs:label key="sysOperationLog.moduleName" /></th>
	<td class="command">
	<input type="submit" class="button" name="back" value="<jecs:locale key="bdSendSupply.supplySuccess"/>" />
		<input type="button" class="button" name="back"  onclick="window.history.back()" value="<jecs:locale key="operation.button.return"/>" />

	</td>
</tr>--%>
</table>

</form:form>


