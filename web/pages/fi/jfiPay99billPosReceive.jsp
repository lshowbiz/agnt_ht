<%@ include file="/common/taglibs.jsp"%>
<c:if test="${isBackService == '1' }">
0
</c:if>
<c:if test="${isBackService != '1' }">
	<c:if test="${not empty successMessages}">
		<!-- <div class="message" id="successMessages">  -->
		<c:forEach var="msg" items="${successMessages}">
			<script type="text/JavaScript">
	            alert(' <c:out value="${msg}" escapeXml="false"/>');
	        </script>
			<c:remove var="successMessages" scope="session" />
		</c:forEach>
	</c:if>
	<% 
    if(request.getAttribute("exception")!=null){
    	Exception exception = (Exception) request.getAttribute("exception");
    %>
<div id="main" style="display:none">
	<h1><jecs:locale key="errorPage.heading"/></h1>
    <pre><% exception.printStackTrace(new java.io.PrintWriter(out)); %></pre>
</div>
    <%
    }
    %>
<script>opener.location.reload();window.close();</script>
</c:if>