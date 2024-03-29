<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="signup.title"/></title>
<content tag="heading"><jecs:locale key="signup.heading"/></content>
<body id="signup"/>

<spring:bind path="user.*">
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

<div class="separator"></div>

<form:form commandName="user" method="post" action="signup.html" onsubmit="return validateUser(this)" id="signupForm">
<ul>
    <li class="info">
        <jecs:locale key="signup.message"/>
    </li>
    <li>
        <jecs:label styleClass="desc" key="user.username"/>
        <form:errors path="username" cssClass="fieldError"/>
        <form:input path="username" id="username" cssClass="text large"/>
    </li>
    <li>
        <div>
            <div class="left">
                <jecs:label styleClass="desc" key="user.password"/>
                <form:errors path="password" cssClass="fieldError"/>
                <form:password path="password" id="password" cssClass="text medium"/>
            </div>
            <div>
                <jecs:label styleClass="desc" key="user.confirmPassword"/>
                <form:errors path="confirmPassword" cssClass="fieldError"/>
                <form:password path="confirmPassword" id="confirmPassword" cssClass="text medium"/>
            </div>
        </div>
    </li>
    <li>
        <jecs:label styleClass="desc" key="user.passwordHint"/>
        <form:errors path="passwordHint" cssClass="fieldError"/>
        <form:input path="passwordHint" id="passwordHint" cssClass="text large"/>
    </li>
    <li>
        <div class="left">
            <jecs:label styleClass="desc" key="user.firstName"/>
            <form:errors path="firstName" cssClass="fieldError"/>
            <form:input path="firstName" id="firstName" cssClass="text medium" maxlength="50"/>
        </div>
        <div>
            <jecs:label styleClass="desc" key="user.lastName"/>
            <form:errors path="lastName" cssClass="fieldError"/>
            <form:input path="lastName" id="lastName" cssClass="text medium" maxlength="50"/>
        </div>
    </li>
    <li>
        <div>
            <div class="left">
                <jecs:label styleClass="desc" key="user.email"/>
                <form:errors path="email" cssClass="fieldError"/>
                <form:input path="email" id="email" cssClass="text medium"/>
            </div>
            <div>
                <jecs:label styleClass="desc" key="user.phoneNumber"/>
                <form:errors path="phoneNumber" cssClass="fieldError"/>
                <form:input path="phoneNumber" id="phoneNumber" cssClass="text medium"/>
            </div>
        </div>
    </li>
    <li>
        <jecs:label styleClass="desc" key="user.website"/>
        <form:errors path="website" cssClass="fieldError"/>
        <form:input path="website" id="website" cssClass="text large"/>
    </li>
    <li>
        <label class="desc"><jecs:locale key="user.address.address"/></label>
        <div class="group">
            <div>
                <form:input path="address.address" id="address.address" cssClass="text large"/>
                <form:errors path="address.address" cssClass="fieldError"/>
                <p><jecs:label key="user.address.address"/></p>
            </div>
            <div class="left">
                <form:input path="address.city" id="address.city" cssClass="text medium"/>
                <form:errors path="address.city" cssClass="fieldError"/>
                <p><jecs:label key="user.address.city"/></p>
            </div>
            <div>
                <form:input path="address.province" id="address.province" cssClass="text state" size="2"/>
                <form:errors path="address.province" cssClass="fieldError"/>
                <p><jecs:label key="user.address.province"/></p>
            </div>
            <div class="left">
                <form:input path="address.postalCode" id="address.postalCode" cssClass="text zip"/>
                <form:errors path="address.postalCode" cssClass="fieldError"/>
                <p><jecs:label key="user.address.postalCode"/></p>
            </div>
            <div>
                <jecs:country name="address.country" prompt="" default="${user.address.country}"/>
                <p><jecs:label key="user.address.country"/></p>
            </div>
        </div>
    </li>
    <li class="buttonBar bottom">
        <input type="submit" class="button" name="save" onclick="bCancel=false" value="<jecs:locale key="button.register"/>"/>
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>"/>
    </li>
</ul>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('signupForm'));
    highlightFormElements();
</script>

<v:javascript formName="user" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>


