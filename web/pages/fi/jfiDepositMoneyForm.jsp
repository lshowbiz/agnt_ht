<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiDepositMoneyDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiDepositMoneyDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
			<input type="button" class="button" name="cancel" onclick="window.history.back()" value="<jecs:locale key="operation.button.return"/>" />

</c:set>

<spring:bind path="jfiDepositMoney.*">
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

<form:form commandName="jfiDepositMoney" method="post" action="editJfiDepositMoney.html" onsubmit="return validateJfiDepositMoney(this)" id="jfiDepositMoneyForm">

	<div class="searchBar">	
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>


<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
       会员编号：
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        ${jfiDepositMoney.userCode }
    </td></tr>

    <tr><th>
        会员名称：
    </th>
    <td align="left">
    ${jfiDepositMoney.userName }
    </td></tr>

    <tr><th>
        结算年：
    </th>
    <td align="left">
        <!--form:errors path="wyear" cssClass="fieldError"/-->
        ${jfiDepositMoney.wyear }
    </td></tr>

    <tr><th>
        期别：
    </th>
    <td align="left">
        
    				<jecs:weekFormat week="${jfiDepositMoney.wweek }" weekType="w" />
    </td></tr>

    <tr><th>
        奖金金额：
    </th>
    <td align="left">
        <!--form:errors path="sendMoney" cssClass="fieldError"/-->
         ${jfiDepositMoney.sendMoney }
    </td></tr>

    <tr><th>
       应提交发票金额：
    </th>
    <td align="left">
    	${jfiDepositMoney.invoicePayable }
    </td></tr>

    <tr><th>
        保证金金额：
    </th>
    <td align="left">
    	${jfiDepositMoney.depositMoney }
    </td></tr>

    <tr><th>
       备注：
    </th>
    <td align="left">
    	<form:textarea path="remark" rows="7" cols="30" />
    </td></tr>

   

</table>

</form:form>