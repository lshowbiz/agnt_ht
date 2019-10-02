<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdBounsDeductDetail.title"/></title>
<content tag="heading"><jecs:locale key="bdBounsDeductDetail.heading"/></content>



<spring:bind path="bdBounsDeduct.*">
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

<form:form commandName="bdBounsDeduct" method="post" action="editBdBounsDeduct.html" onsubmit="return validateBdBounsDeduct(this)" id="bdBounsDeductForm">


<input type="hidden" name="strAction" value="${param.strAction }"/>


<table class='detail' width="100%">

<form:hidden path="id"/>

   
	<tr><th>
        <jecs:label  key="miMember.memberNo"/>
    </th>
    <td align="left">
        <!--form:errors path="memberNo" cssClass="fieldError"/-->
        ${bdBounsDeduct.miMember.memberNo}
    </td></tr>
    
    <tr><th>
        <jecs:label  key="bdCalculatingSubDetail.name"/>
    </th>
    <td align="left">
        <!--form:errors path="memberNo" cssClass="fieldError"/-->
        ${bdBounsDeduct.miMember.sysUser.userName }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="miMember.cardType"/>
    </th>
    <td align="left">
        <!--form:errors path="memberNo" cssClass="fieldError"/-->
        <jecs:code listCode="bd.cardtype" value="${bdBounsDeduct.miMember.cardType}"/>
    </td></tr>

    

    <tr><th>
        <jecs:label  key="bdReconsumMoneyReport.typeCH"/>
    </th>
    <td align="left">
        <!--form:errors path="type" cssClass="fieldError"/-->
        <jecs:code listCode="bdbounsdeduct.item" value="${bdBounsDeduct.type}"/>
    </td></tr>
    
    <tr><th>
        <jecs:label  key="bdBounsDeduct.summary"/>
    </th>
    <td align="left">
        <!--form:errors path="summary" cssClass="fieldError"/-->
        ${bdBounsDeduct.summary }
    </td></tr>

    <tr><th>
        <jecs:label  key="bdBounsDeduct.money"/>
    </th>
    <td align="left">
        <!--form:errors path="money" cssClass="fieldError"/-->
        ${bdBounsDeduct.money }
    </td></tr>

    <tr><th>
        <jecs:label  key="bdBounsDeduct.remainMoney"/>
    </th>
    <td align="left">
        <!--form:errors path="remainMoney" cssClass="fieldError"/-->
        ${bdBounsDeduct.remainMoney }
    </td></tr>

    <tr><th>
        <jecs:label  key="bdBounsDeduct.deductMoney"/>
    </th>
    <td align="left">
        <!--form:errors path="deductMoney" cssClass="fieldError"/-->
         ${bdBounsDeduct.deductMoney }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="bdBounsDeduct.curWwekkRemainMoney"/>
    </th>
    <td align="left">
        <!--form:errors path="deductMoney" cssClass="fieldError"/-->
        ${bdBounsDeduct.remainMoney-bdBounsDeduct.deductMoney }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="bdBounsDeduct.wweek"/>
    </th>
    <td align="left">
        <!--form:errors path="deductMoney" cssClass="fieldError"/-->
        ${bdBounsDeduct.wweek }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="bdBounsDeduct.deductTime"/>
    </th>
    <td align="left">
        <!--form:errors path="deductTime" cssClass="fieldError"/-->
         ${bdBounsDeduct.deductTime }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="bdBounsDeduct.deducterCode"/>
    </th>
    <td align="left">
        <!--form:errors path="deductTime" cssClass="fieldError"/-->
        ${bdBounsDeduct.deducterCode }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="bdBounsDeduct.deducterName"/>
    </th>
    <td align="left">
        <!--form:errors path="deductTime" cssClass="fieldError"/-->
         ${bdBounsDeduct.deducterName }
    </td></tr>

	<tr><th>
        <jecs:label  key="bdBounsDeduct.status"/>
    </th>
    <td align="left">
        <!--form:errors path="deductTime" cssClass="fieldError"/-->
       <jecs:code listCode="bdbounsdeduct.status" value="${bdBounsDeduct.status}"/>
    </td></tr>
    
    <tr><th>
        <jecs:label  key="pd.okTime"/>
    </th>
    <td align="left">
        <!--form:errors path="deductTime" cssClass="fieldError"/-->
         ${bdBounsDeduct.checkTime }
    </td></tr>
    <tr><th>
        <jecs:label  key="bdBounsDeduct.checkerCode"/>
    </th>
    <td align="left">
        <!--form:errors path="deductTime" cssClass="fieldError"/-->
        ${bdBounsDeduct.checkerCode }
    </td></tr>
    <tr><th>
        <jecs:label  key="bdBounsDeduct.checkerName"/>
    </th>
    <td align="left">
        <!--form:errors path="deductTime" cssClass="fieldError"/-->
        ${bdBounsDeduct.checkerName }
    </td></tr>
    

    

    <tr><th>
        <jecs:label  key="prRefund.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
         ${bdBounsDeduct.createTime }
    </td></tr>
    <tr><th>
        <jecs:label  key="bdBounsDeduct.createCode"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
         ${bdBounsDeduct.createCode}
    </td></tr>
    <tr><th>
        <jecs:label  key="bdBounsDeduct.createName"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
         ${bdBounsDeduct.createName}
    </td></tr>
<tr>
	<th class="command"><jecs:label key="sysOperationLog.moduleName" /></th>
	<td class="command">
		<input type="button" class="button" name="back"  onclick="javascript:history.back();" value="<jecs:locale key="operation.button.return"/>" />
		
	</td>
</tr>
</table>

</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('bdBounsDeductForm'));
</script>

<v:javascript formName="bdBounsDeduct" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
