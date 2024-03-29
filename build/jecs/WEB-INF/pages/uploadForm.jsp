<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="upload.title"/></title>
<content tag="heading"><jecs:locale key="upload.heading"/></content>
<meta name="menu" content="FileUpload"/>

<!--
    The most important part is to declare your form's enctype to be "multipart/form-data"
-->

<spring:bind path="fileUpload.*">
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

<form:form commandName="fileUpload" method="post" action="uploadFile.html" enctype="multipart/form-data"
    onsubmit="return validateFileUpload(this)" id="uploadForm">
<ul>
    <li class="info">
        <jecs:locale key="upload.message"/>
    </li>
    <li>
        <jecs:label key="uploadForm.name" styleClass="desc"/>
        <form:errors path="name" cssClass="fieldError"/>
        <form:input path="name" id="name" cssClass="text medium"/>
    </li>
    <li>
        <jecs:label key="uploadForm.file" styleClass="desc"/>
        <form:errors path="file" cssClass="fieldError"/>
        <spring:bind path="fileUpload.file">
        <input type="file" name="file" id="file" class="file medium" value="<c:out value="${status.value}"/>"/>
        </spring:bind>
    </li>
    <li class="buttonBar bottom">
        <input type="submit" name="upload" class="button" onclick="bCancel=false"
            value="<jecs:locale key="button.upload"/>" />
        <input type="submit" name="cancel" class="button" onclick="bCancel=true"
            value="<jecs:locale key="button.cancel"/>" />
    </li>
</ul>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('uploadForm'));
    highlightFormElements();
</script>
<v:javascript formName="fileUpload" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
