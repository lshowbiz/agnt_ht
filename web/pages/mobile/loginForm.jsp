<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<html>
    <head>
    	<meta name="viewport" content="width=device-width, initial-scale=1"> 
        <%@ include file="/common/mobile/meta.jsp" %>
		<link href="<c:url value='/images/favicon.ico'/>" rel="shortcut icon" />
		<link rel="stylesheet" href="${ctx}/scripts/jquerymobile/jquery.mobile-1.0.min.css" />
		<script src="${ctx}/scripts/jquerymobile/jquery-1.7.1.min.js"></script>
		<script src="${ctx}/scripts/jquerymobile/jquery.mobile-1.0.min.js"></script>
		<script type="text/javascript">
			var confirmDeleteMsg="<jecs:locale key="confirm.delete"/>";
			function openDetail(el){
				el.slideDown(1000);
			} 
		</script>	
		<style>
		
		body {
	position: relative;
	min-width: 320px; 
	width: 320px; 
	margin: 0 auto;
	padding: 0px;
}
.ui-mobile-viewport {
	margin: 0 auto;
}
		</style>
		<title>用户登录</title>
    </head>
	<body>
		
		<section id="page1" data-role="page">  
  <header data-role="header"  data-theme="b" ><h1 style="margin: 0.6em 0px 0.8em;" >用户登录</h1></header>  
  <div data-role="content" >  
		 <div id="error">
				<spring:bind path="sysUser.*">
					<c:if test="${not empty status.errorMessages}">
						<c:forEach var="error" items="${status.errorMessages}">
							<img src="<c:url value="images/newIcons/warning_16.gif"/>" alt="<jecs:locale key="icon.warning"/>" class="icon" />
							<c:out value="${error}" escapeXml="false"/><br />
						</c:forEach>
					</c:if>
					<c:if test="${empty status.errorMessages}">&nbsp;</c:if>
				</spring:bind>
			</div>
			
<form:form commandName="sysUser" name="loginForm" method="post" action="login.html" onsubmit="return validateLogin(this);">

			
<div data-role="fieldcontain">  
			<label for="userCode">帐户:</label>
            <input type="text" id="userCode" name="userCode"/>
			<label for="password">密码:</label>
            <input type="password" id="password"  name="password" />
			<label for="password">校验码:</label>
			<span>
			<input type="text" name="verifyCode"  /><img style="vertical-align:middle;" src="<c:url value="/generateverifycode"/>?rnd=<%=Math.random() * 1000000%>" />
			</span>
      			 
</div>		

			<input  type="submit" name="save" data-theme="b"  value="登录"  />
        </form:form>
		
  </div>  
</section> 

<script type="text/javascript">
document.getElementById("userCode").focus();
function validateLogin(theForm){
	if(theForm.userCode.value==""){
		alert("<jecs:locale key="sysUser.userCode.required"/>");
		theForm.userCode.focus();
		return false;
	}
	if(theForm.password.value==""){
		alert("<jecs:locale key="sysUser.password.required"/>");
		theForm.password.focus();
		return false;
	}
	return true;
}
</script>
</body>

</html>







