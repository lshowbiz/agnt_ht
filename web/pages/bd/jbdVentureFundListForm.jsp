<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdVentureFundListDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdVentureFundListDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
		</jecs:power>
		
		
		<input type="button" class="button" name="cancel" onclick="window.history.back()" value="<jecs:locale key="operation.button.return"/>" />
</c:set>

<spring:bind path="jbdVentureFundList.*">
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

<form:form commandName="jbdVentureFundList" method="post" action="editJbdVentureFundList.html"  id="jbdVentureFundListForm">

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
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="bdBounsDeduct.wweek"/>
    </th>
    <td align="left">
        <!--form:errors path="wweek" cssClass="fieldError"/-->
        <form:input path="wweek" id="wweek" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="bdBonusRankingReport.recommandPerson"/>
    </th>
    <td align="left">
        <!--form:errors path="recommendNo" cssClass="fieldError"/-->
        <form:input path="recommendNo" id="recommendNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="poMemberOrder.memberOrderNo"/>
    </th>
    <td align="left">
        <!--form:errors path="orderNo" cssClass="fieldError"/-->
        <form:input path="orderNo" id="orderNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pd.orderType"/>
    </th>
    <td align="left">
        <!--form:errors path="orderType" cssClass="fieldError"/-->
         <jecs:list name="orderType" id="orderType" listCode="po.all.order_type" value="${jbdVentureFundList.orderType }" defaultValue="" showBlankLine="true"/>	
    </td></tr>

    <tr><th>
        <jecs:label  key="report.inPv"/>
    </th>
    <td align="left">
        <!--form:errors path="pvAmt" cssClass="fieldError"/-->
        <form:input path="pvAmt" id="pvAmt" cssClass="text medium"/>
    </td></tr>


    <tr><th>
        <jecs:label  key="busi.common.remark"/>
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
					<form:textarea path="remark" id="remark"  rows="7" cols="35"/>
    </td></tr>

</table>
</table-->
</form:form>
