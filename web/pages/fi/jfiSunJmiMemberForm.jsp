<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiSunJmiMemberDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiSunJmiMemberDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJfiSunJmiMember">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JfiSunJmiMember')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jfiSunJmiMember.*">
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

<form:form commandName="jfiSunJmiMember" method="post" action="editJfiSunJmiMember.html" onsubmit="return validateJfiSunJmiMember(this)" id="jfiSunJmiMemberForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiSunJmiMember')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="userCode"/>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.linkNo"/>
    </th>
    <td align="left">
        <!--form:errors path="linkNo" cssClass="fieldError"/-->
        <form:input path="linkNo" id="linkNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.recommendNo"/>
    </th>
    <td align="left">
        <!--form:errors path="recommendNo" cssClass="fieldError"/-->
        <form:input path="recommendNo" id="recommendNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.cardType"/>
    </th>
    <td align="left">
        <!--form:errors path="cardType" cssClass="fieldError"/-->
        <form:input path="cardType" id="cardType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.firstName"/>
    </th>
    <td align="left">
        <!--form:errors path="firstName" cssClass="fieldError"/-->
        <form:input path="firstName" id="firstName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.lastName"/>
    </th>
    <td align="left">
        <!--form:errors path="lastName" cssClass="fieldError"/-->
        <form:input path="lastName" id="lastName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.petName"/>
    </th>
    <td align="left">
        <!--form:errors path="petName" cssClass="fieldError"/-->
        <form:input path="petName" id="petName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.sex"/>
    </th>
    <td align="left">
        <!--form:errors path="sex" cssClass="fieldError"/-->
        <form:input path="sex" id="sex" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.birthday"/>
    </th>
    <td align="left">
        <!--form:errors path="birthday" cssClass="fieldError"/-->
        <form:input path="birthday" id="birthday" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.email"/>
    </th>
    <td align="left">
        <!--form:errors path="email" cssClass="fieldError"/-->
        <form:input path="email" id="email" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.papertype"/>
    </th>
    <td align="left">
        <!--form:errors path="papertype" cssClass="fieldError"/-->
        <form:input path="papertype" id="papertype" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.papernumber"/>
    </th>
    <td align="left">
        <!--form:errors path="papernumber" cssClass="fieldError"/-->
        <form:input path="papernumber" id="papernumber" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.bank"/>
    </th>
    <td align="left">
        <!--form:errors path="bank" cssClass="fieldError"/-->
        <form:input path="bank" id="bank" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.bankaddress"/>
    </th>
    <td align="left">
        <!--form:errors path="bankaddress" cssClass="fieldError"/-->
        <form:input path="bankaddress" id="bankaddress" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.bankcard"/>
    </th>
    <td align="left">
        <!--form:errors path="bankcard" cssClass="fieldError"/-->
        <form:input path="bankcard" id="bankcard" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.bankbook"/>
    </th>
    <td align="left">
        <!--form:errors path="bankbook" cssClass="fieldError"/-->
        <form:input path="bankbook" id="bankbook" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.phone"/>
    </th>
    <td align="left">
        <!--form:errors path="phone" cssClass="fieldError"/-->
        <form:input path="phone" id="phone" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.faxtele"/>
    </th>
    <td align="left">
        <!--form:errors path="faxtele" cssClass="fieldError"/-->
        <form:input path="faxtele" id="faxtele" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.mobiletele"/>
    </th>
    <td align="left">
        <!--form:errors path="mobiletele" cssClass="fieldError"/-->
        <form:input path="mobiletele" id="mobiletele" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.officetele"/>
    </th>
    <td align="left">
        <!--form:errors path="officetele" cssClass="fieldError"/-->
        <form:input path="officetele" id="officetele" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.countryCode"/>
    </th>
    <td align="left">
        <!--form:errors path="countryCode" cssClass="fieldError"/-->
        <form:input path="countryCode" id="countryCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.province"/>
    </th>
    <td align="left">
        <!--form:errors path="province" cssClass="fieldError"/-->
        <form:input path="province" id="province" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.city"/>
    </th>
    <td align="left">
        <!--form:errors path="city" cssClass="fieldError"/-->
        <form:input path="city" id="city" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.address"/>
    </th>
    <td align="left">
        <!--form:errors path="address" cssClass="fieldError"/-->
        <form:input path="address" id="address" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.postalcode"/>
    </th>
    <td align="left">
        <!--form:errors path="postalcode" cssClass="fieldError"/-->
        <form:input path="postalcode" id="postalcode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.exitDate"/>
    </th>
    <td align="left">
        <!--form:errors path="exitDate" cssClass="fieldError"/-->
        <form:input path="exitDate" id="exitDate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.remark"/>
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <form:input path="remark" id="remark" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.isstore"/>
    </th>
    <td align="left">
        <!--form:errors path="isstore" cssClass="fieldError"/-->
        <form:input path="isstore" id="isstore" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.checkNo"/>
    </th>
    <td align="left">
        <!--form:errors path="checkNo" cssClass="fieldError"/-->
        <form:input path="checkNo" id="checkNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.checkDate"/>
    </th>
    <td align="left">
        <!--form:errors path="checkDate" cssClass="fieldError"/-->
        <form:input path="checkDate" id="checkDate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.createNo"/>
    </th>
    <td align="left">
        <!--form:errors path="createNo" cssClass="fieldError"/-->
        <form:input path="createNo" id="createNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.deadlineDate"/>
    </th>
    <td align="left">
        <!--form:errors path="deadlineDate" cssClass="fieldError"/-->
        <form:input path="deadlineDate" id="deadlineDate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.pbNo"/>
    </th>
    <td align="left">
        <!--form:errors path="pbNo" cssClass="fieldError"/-->
        <form:input path="pbNo" id="pbNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.pbNo1"/>
    </th>
    <td align="left">
        <!--form:errors path="pbNo1" cssClass="fieldError"/-->
        <form:input path="pbNo1" id="pbNo1" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.pbNo2"/>
    </th>
    <td align="left">
        <!--form:errors path="pbNo2" cssClass="fieldError"/-->
        <form:input path="pbNo2" id="pbNo2" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.active"/>
    </th>
    <td align="left">
        <!--form:errors path="active" cssClass="fieldError"/-->
        <form:input path="active" id="active" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.miUserName"/>
    </th>
    <td align="left">
        <!--form:errors path="miUserName" cssClass="fieldError"/-->
        <form:input path="miUserName" id="miUserName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.spouseName"/>
    </th>
    <td align="left">
        <!--form:errors path="spouseName" cssClass="fieldError"/-->
        <form:input path="spouseName" id="spouseName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.spouseIdno"/>
    </th>
    <td align="left">
        <!--form:errors path="spouseIdno" cssClass="fieldError"/-->
        <form:input path="spouseIdno" id="spouseIdno" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.bankCity"/>
    </th>
    <td align="left">
        <!--form:errors path="bankCity" cssClass="fieldError"/-->
        <form:input path="bankCity" id="bankCity" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.bankProvince"/>
    </th>
    <td align="left">
        <!--form:errors path="bankProvince" cssClass="fieldError"/-->
        <form:input path="bankProvince" id="bankProvince" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.district"/>
    </th>
    <td align="left">
        <!--form:errors path="district" cssClass="fieldError"/-->
        <form:input path="district" id="district" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.linkNum"/>
    </th>
    <td align="left">
        <!--form:errors path="linkNum" cssClass="fieldError"/-->
        <form:input path="linkNum" id="linkNum" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.pendingLinkNum"/>
    </th>
    <td align="left">
        <!--form:errors path="pendingLinkNum" cssClass="fieldError"/-->
        <form:input path="pendingLinkNum" id="pendingLinkNum" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.recommendNum"/>
    </th>
    <td align="left">
        <!--form:errors path="recommendNum" cssClass="fieldError"/-->
        <form:input path="recommendNum" id="recommendNum" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.pendingRecommendNum"/>
    </th>
    <td align="left">
        <!--form:errors path="pendingRecommendNum" cssClass="fieldError"/-->
        <form:input path="pendingRecommendNum" id="pendingRecommendNum" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.activeTime"/>
    </th>
    <td align="left">
        <!--form:errors path="activeTime" cssClass="fieldError"/-->
        <form:input path="activeTime" id="activeTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.memberType"/>
    </th>
    <td align="left">
        <!--form:errors path="memberType" cssClass="fieldError"/-->
        <form:input path="memberType" id="memberType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.oriCard"/>
    </th>
    <td align="left">
        <!--form:errors path="oriCard" cssClass="fieldError"/-->
        <form:input path="oriCard" id="oriCard" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.flag"/>
    </th>
    <td align="left">
        <!--form:errors path="flag" cssClass="fieldError"/-->
        <form:input path="flag" id="flag" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.oriPv"/>
    </th>
    <td align="left">
        <!--form:errors path="oriPv" cssClass="fieldError"/-->
        <form:input path="oriPv" id="oriPv" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.changeStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="changeStatus" cssClass="fieldError"/-->
        <form:input path="changeStatus" id="changeStatus" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.subStoreStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="subStoreStatus" cssClass="fieldError"/-->
        <form:input path="subStoreStatus" id="subStoreStatus" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.subRecommendStore"/>
    </th>
    <td align="left">
        <!--form:errors path="subRecommendStore" cssClass="fieldError"/-->
        <form:input path="subRecommendStore" id="subRecommendStore" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.subStoreCheckDate"/>
    </th>
    <td align="left">
        <!--form:errors path="subStoreCheckDate" cssClass="fieldError"/-->
        <form:input path="subStoreCheckDate" id="subStoreCheckDate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.identityType"/>
    </th>
    <td align="left">
        <!--form:errors path="identityType" cssClass="fieldError"/-->
        <form:input path="identityType" id="identityType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.townAddr"/>
    </th>
    <td align="left">
        <!--form:errors path="townAddr" cssClass="fieldError"/-->
        <form:input path="townAddr" id="townAddr" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.commPostalcode"/>
    </th>
    <td align="left">
        <!--form:errors path="commPostalcode" cssClass="fieldError"/-->
        <form:input path="commPostalcode" id="commPostalcode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.commProvince"/>
    </th>
    <td align="left">
        <!--form:errors path="commProvince" cssClass="fieldError"/-->
        <form:input path="commProvince" id="commProvince" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.commCity"/>
    </th>
    <td align="left">
        <!--form:errors path="commCity" cssClass="fieldError"/-->
        <form:input path="commCity" id="commCity" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.commAddr"/>
    </th>
    <td align="left">
        <!--form:errors path="commAddr" cssClass="fieldError"/-->
        <form:input path="commAddr" id="commAddr" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.villageAddr"/>
    </th>
    <td align="left">
        <!--form:errors path="villageAddr" cssClass="fieldError"/-->
        <form:input path="villageAddr" id="villageAddr" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.companyName"/>
    </th>
    <td align="left">
        <!--form:errors path="companyName" cssClass="fieldError"/-->
        <form:input path="companyName" id="companyName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.personCharge"/>
    </th>
    <td align="left">
        <!--form:errors path="personCharge" cssClass="fieldError"/-->
        <form:input path="personCharge" id="personCharge" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.companyAddr"/>
    </th>
    <td align="left">
        <!--form:errors path="companyAddr" cssClass="fieldError"/-->
        <form:input path="companyAddr" id="companyAddr" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.uniteNumber"/>
    </th>
    <td align="left">
        <!--form:errors path="uniteNumber" cssClass="fieldError"/-->
        <form:input path="uniteNumber" id="uniteNumber" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.startWeek"/>
    </th>
    <td align="left">
        <!--form:errors path="startWeek" cssClass="fieldError"/-->
        <form:input path="startWeek" id="startWeek" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.validWeek"/>
    </th>
    <td align="left">
        <!--form:errors path="validWeek" cssClass="fieldError"/-->
        <form:input path="validWeek" id="validWeek" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.freezeStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="freezeStatus" cssClass="fieldError"/-->
        <form:input path="freezeStatus" id="freezeStatus" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.beforeFreezeStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="beforeFreezeStatus" cssClass="fieldError"/-->
        <form:input path="beforeFreezeStatus" id="beforeFreezeStatus" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.commDistrict"/>
    </th>
    <td align="left">
        <!--form:errors path="commDistrict" cssClass="fieldError"/-->
        <form:input path="commDistrict" id="commDistrict" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.town"/>
    </th>
    <td align="left">
        <!--form:errors path="town" cssClass="fieldError"/-->
        <form:input path="town" id="town" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.isDiscount"/>
    </th>
    <td align="left">
        <!--form:errors path="isDiscount" cssClass="fieldError"/-->
        <form:input path="isDiscount" id="isDiscount" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.isSubStore"/>
    </th>
    <td align="left">
        <!--form:errors path="isSubStore" cssClass="fieldError"/-->
        <form:input path="isSubStore" id="isSubStore" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunJmiMember.activeStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="activeStatus" cssClass="fieldError"/-->
        <form:input path="activeStatus" id="activeStatus" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiSunJmiMember')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jfiSunJmiMemberForm'));
</script>

<v:javascript formName="jfiSunJmiMember" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
