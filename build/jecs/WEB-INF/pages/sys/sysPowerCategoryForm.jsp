<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysPowerCategoryDetail.title" /></title>
<content tag="heading">
<jecs:locale key="sysPowerCategoryDetail.heading" />
</content>

<script type="text/javascript">
<c:if test="${not empty param.returnVal}">
	window.opener.location.reload();
	window.close();
</c:if>
</script>

<form:form commandName="sysPowerCategory" method="post" action="editSysPowerCategory.html" id="sysPowerCategoryForm">
	<table class="detail">

		<form:hidden path="pcId" />
		<form:hidden path="parentId" />

		<tr>
			<th><jecs:locale key="sysPowerCategory.categoryName" /></th>
			<td><form:input path="categoryName" id="categoryName" cssClass="text medium" /></td>
		</tr>

		<tr>
			<th></th>
			<td>
				<input type="submit" class="button" name="save" onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
				<c:if test="${not empty sysPowerCategory.pcId}">
					<input type="submit" class="button" name="del" onclick="bCancel=false" value="<jecs:locale key="operation.button.delete"/>" />
				</c:if>
				<input type="button" class="button" name="cancel" onclick="window.close()" value="<jecs:locale key="operation.button.cancel"/>" />
		</td>
	</tr>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('sysPowerCategoryForm'));
</script>

<v:javascript formName="sysPowerCategory" cdata="false" dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
