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
<tbody class="window">
<form:hidden path="id"/>

   
	<tr class="edit_tr">
	<th><span class="text-font-style-tc">
        <jecs:label  key="miMember.memberNo"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="memberNo" cssClass="fieldError"/-->
        ${bdBounsDeduct.jmiMember.userCode}
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="bdCalculatingSubDetail.name"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="memberNo" cssClass="fieldError"/-->
        ${bdBounsDeduct.jmiMember.miUserName }
    </span></td></tr>
    
    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="miMember.cardType"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="memberNo" cssClass="fieldError"/-->
        <jecs:code listCode="bd.cardtype" value="${bdBounsDeduct.jmiMember.cardType}"/>
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="bdReconsumMoneyReport.typeCH"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="type" cssClass="fieldError"/-->
        <jecs:code listCode="bdbounsdeduct.item" value="${bdBounsDeduct.type}"/>
    </span></td></tr>
    
    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.summary"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="summary" cssClass="fieldError"/-->
        ${bdBounsDeduct.summary }
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.money"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="money" cssClass="fieldError"/-->
        ${bdBounsDeduct.money }
    </span></td></tr>

    <%--<tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.remainMoney"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="remainMoney" cssClass="fieldError"/-->
        ${bdBounsDeduct.remainMoney }
    </span></td></tr>

    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.deductMoney"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="deductMoney" cssClass="fieldError"/-->
         ${bdBounsDeduct.deductMoney }
    </span></td></tr>
    
    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.curWwekkRemainMoney"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="deductMoney" cssClass="fieldError"/-->
        ${bdBounsDeduct.remainMoney-bdBounsDeduct.deductMoney }
    </span></td></tr>--%>
    
    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.wweek"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="deductMoney" cssClass="fieldError"/-->
        ${bdBounsDeduct.wweek }
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.deductTime"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="deductTime" cssClass="fieldError"/-->
         ${bdBounsDeduct.deductTime }
    </span></td></tr>
    
    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.deducterCode"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="deductTime" cssClass="fieldError"/-->
        ${bdBounsDeduct.deducterCode }
    </span></td>
	
	<th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.deducterName"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="deductTime" cssClass="fieldError"/-->
         ${bdBounsDeduct.deducterName }
    </span></td></tr>

	<tr class="edit_tr">
	<th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.status"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="deductTime" cssClass="fieldError"/-->
       <jecs:code listCode="bdbounsdeduct.status" value="${bdBounsDeduct.status}"/>
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="pd.okTime"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="deductTime" cssClass="fieldError"/-->
         ${bdBounsDeduct.checkTime }
    </span></td></tr>
    
    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.checkerCode"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="deductTime" cssClass="fieldError"/-->
        ${bdBounsDeduct.checkerCode }
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.checkerName"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="deductTime" cssClass="fieldError"/-->
        ${bdBounsDeduct.checkerName }
    </span></td></tr>
    

    

    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="prRefund.createTime"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
         ${bdBounsDeduct.createTime }
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.createCode"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
         ${bdBounsDeduct.createCode}
    </span></td></tr>
    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.createName"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
         ${bdBounsDeduct.createName}
    </span></td></tr>
<tr>
	<td class="command" colspan="4" align="center">
		<button type="button" class="btn btn_sele" name="back"  onclick="javascript:history.back();">
			<jecs:locale key="operation.button.return"/>
		</button>
	</td>
</tr>
</tbody>
</table>

</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('bdBounsDeductForm'));
</script>

<v:javascript formName="bdBounsDeduct" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
