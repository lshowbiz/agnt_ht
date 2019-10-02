<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdSendChangeRegisterForm.title"/></title>
<content tag="heading"><jecs:locale key="bdSendRecordDetail.heading"/></content>



<spring:bind path="jbdSendNote">
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
<form:form commandName="jbdSendNote" method="post" action="" id="bdSendChangeRegisterForm">


	<div class="searchBar">
	<input type="submit" class="button" name="back" value="<jecs:locale key="operation.button.save"/>" />
		<input type="button" class="button" name="back" onclick="window.location.href='jbdSendNoteChangeRegister.html?strAction=jbdSendNoteChangeRegister&needReload=1'" value="<jecs:locale key="operation.button.return"/>" />
	
	</div>


<table class='detail' width="100%">
    <tr><th>
        <jecs:label  key="bd.app.time"/>
    </th>
    <td align="left">
        ${jbdSendNote.createTime }
    </td></tr>

    <tr><th>
        <jecs:label  key="miMember.memberNo"/>
    </th>
    <td align="left">
        <!--form:errors path="memberNo" cssClass="fieldError"/-->
        ${jbdSendNote.jmiMember.userCode }
    </td></tr>

    <tr><th>
        <jecs:label  key="bdCalculatingSubDetail.name"/>
    </th>
    <td align="left">
        <!--form:errors path="name" cssClass="fieldError"/-->
        ${ jbdSendNote.jmiMember.miUserName}
    </td></tr>

	<tr><th>
        <jecs:label  key="bdSendRecord.remittanceBNum"/>
    </th>
    <td align="left">
        <!--form:errors path="name" cssClass="fieldError"/-->
        <select name="remittanceBNum">

        <c:forEach items="${bdOutwardBanks }" var="var">
				<c:if test="${var.bankCode==jbdSendNote.remittanceBNum }">
					<option value="${var.bankId }" selected="selected">${var.bankCode }-${var.bankName }</option>
				</c:if>
				<c:if test="${var.bankCode!=jbdSendNote.remittanceBNum }">
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
        ${jbdSendNote.jmiMember.bank }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="bdSendRemittanceReport.openCityCH"/>
    </th>
    <td align="left">
        <!--form:errors path="bcity" cssClass="fieldError"/-->
        ${jbdSendNote.jmiMember.bankaddress }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="bdOutwardBank.accountName"/>
    </th>
    <td align="left">
        <!--form:errors path="bname" cssClass="fieldError"/-->
        ${jbdSendNote.jmiMember.bankbook }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="bdSendRecord.bnum"/>
    </th>
    <td align="left">
        <!--form:errors path="bnum" cssClass="fieldError"/-->
       ${jbdSendNote.jmiMember.bankcard }
    </td></tr>

    
    
 	<tr><th>
        <jecs:label  key="bdSendRecord.remittanceMoney"/>
    </th>
    <td align="left">
        <!--form:errors path="remittanceMoney" cssClass="fieldError"/-->
         <fmt:formatNumber value="${jbdSendNote.remittanceMoney }" type="number" pattern="###,###,##0.00"/>
    </td></tr>
    
    <tr><th>
        <jecs:label  key="bdSendRecord.sendDate"/>
    </th>
    <td align="left">
        <!--form:errors path="sendLateCause" cssClass="fieldError"/-->
      <fmt:formatDate value="${jbdSendNote.sendDate }" pattern="yyyy-MM-dd"/>
 
    </td></tr>

    <tr><th>
        <jecs:label  key="bdSendRecord.operaterCode"/>
    </th>
    <td align="left">
        <!--form:errors path="sendLateCause" cssClass="fieldError"/-->
       ${jbdSendNote.operaterCode }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="bdSendRecord.registerStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="sendLateCause" cssClass="fieldError"/-->
       <jecs:code listCode="bdsendregister.registerstatus" value="${jbdSendNote.registerStatus}"/>
    </td></tr>
    
    <tr><th>
        <jecs:label  key="bdSendRecord.supplyStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="sendLateCause" cssClass="fieldError"/-->
       <jecs:code listCode="bdsendrecord.reissuestatus" value="${jbdSendNote.reissueStatus}"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="bdSendRecord.sendLateCause"/>
    </th>
    <td align="left">
        <!--form:errors path="sendLateCause" cssClass="fieldError"/-->
      <jecs:list name="sendLateCause" listCode="bdsendrecord.sendlateremark" value="${ jbdSendNote.sendLateCause}" defaultValue="0" showBlankLine="true"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="bdSendRecord.sendLateRemark"/>
    </th>
    <td align="left">
        <!--form:errors path="sendLateRemark" cssClass="fieldError"/-->
        <form:textarea rows="6" cols="50" path="sendLateRemark" id="sendLateRemark"/>
    </td></tr>

</table>

</form:form>


