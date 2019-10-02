<%-- Error Messages --%>
<c:if test="${not empty errors}">
	<div class="error" id="errorMessages">
		<c:forEach var="error" items="${errors}">
			<img src="<c:url value="images/newIcons/warning_16.gif"/>"
				alt="<jecs:locale key="icon.warning"/>" class="icon" />
			<c:out value="${error}" escapeXml="false" />
			<br />
		</c:forEach>
	</div>
	<c:remove var="errors" />
</c:if>

<%-- Success Messages --%>
<c:if test="${not empty successMessages}">
	<!-- <div class="message" id="successMessages">  -->
	<c:forEach var="msg" items="${successMessages}">
		<%--<img src="<c:url value="/images/iconInformation.gif"/>"
                alt="<jecs:locale key="icon.information"/>" class="icon" />
            <c:out value="${msg}" escapeXml="false"/><br />--%>

		<script type="text/JavaScript">
            alert(' <c:out value="${msg}" escapeXml="false"/>');
        </script>
		<c:remove var="successMessages" scope="session" />
	</c:forEach>
	<!-- </div>  -->
</c:if>
<%-- Reload Special Iframe --%>
<c:if test="${not empty iframe}">
	<script type="text/javaScript">
		window.parent.<c:out value="${iframe}"/>.location.reload();
	</script>
	<c:remove var="iframe" />
</c:if>
