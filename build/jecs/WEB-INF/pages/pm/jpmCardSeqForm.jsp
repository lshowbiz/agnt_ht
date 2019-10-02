<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmCardSeqDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpmCardSeqDetail.heading"/></content>

<c:set var="buttons">

				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
</c:set>

<spring:bind path="jpmCardSeq.*">
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

<form:form commandName="jpmCardSeq" method="post" action="editJpmCardSeq.html"  id="jpmCardSeqForm">

<div class="searchBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="miMember.memberNo"/>
    </th>
    <td align="left">
        <!--form:errors path="cardNo" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>
    
    <tr><th>
        <jecs:label  key="poMemberOrder.memberOrderNo"/>
    </th>
    <td align="left">
        <!--form:errors path="cardNo" cssClass="fieldError"/-->
        <form:input path="memberOrderNo" id="memberOrderNo" cssClass="text medium"/>
    </td></tr>
    
    <tr><th>
        <jecs:label  key="pd.qty"/>
    </th>
    <td align="left">
        <!--form:errors path="cardNo" cssClass="fieldError"/-->
        <input id="qty" name="qty" class="text medium" >
    </td></tr>

    
</table>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jpmCardSeqForm'));
</script>
