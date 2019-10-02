<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<%@ include file="/common/meta.jsp" %>
<title><jecs:locale key="webapp.name"/></title>
<link href="styles/style.css" rel="stylesheet" type="text/css" />
<script src="scripts/jquery/jquery-142min.js" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jmiSmsNoteManager.js'/>" ></script>
</head>
<script>
function openTutorial(){
	window.open("http://test.joymaingroup.com/tutorial/newSystem.jsp", "newwindow", "height=250, width=500, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no");
}
//window.open("newSystem.jsp", "newwindow", "height=150, width=400, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no");
</script>
<body  class="login_m1">

<form:form commandName="sysUser" name="loginForm" method="post" action="login.html" onsubmit="return validateLogin(this);">


		
	   <div class="subLeft">
			<div class="subLeft3">
				<div class="subRight">
					<img src="images/logo-1.png"/>
				</div>
			
			   <p><strong>User ID:</strong></br>
			   <input type="text" class="fm_1" size="62" id="userCode" name="userCode"/></p>
			   <p><strong>Password:</strong></br>
			   <input type="password" class="fm_1" size="64" id="password" name="password" /></p>
			   <p><strong>Verification Code:</strong></p>
				
				<div class="btn">
					<input type="text" class="fm_2" size="4" name="verifyCode"/> 
					 <button  id="btnSendCode" type="button" onclick="sendSms()" class="subButton" style="width:auto;font-size: 12px;color: #fff;background-color: #ec971f;border-color: #d58512;">发送验证码</button>
				</div>
			       <%-- <img style="vertical-align:middle;" src="<c:url value="/generateverifycode"/>?rnd=<%=Math.random() * 1000000%>" />
			   --%>
			   
			   <div class="btn"><button class="subButton" type="submit">LOGIN</button></div>
			   <spring:bind path="sysUser.*">
					<c:if test="${not empty status.errorCodes}">
						<c:forEach var="error" items="${status.errorCodes}">
							<img src="<c:url value="images/newIcons/warning_16.gif"/>" alt="<jecs:locale key="icon.warning"/>" class="icon" />
							<jecs:locale key='${error}' />
							<br />
						</c:forEach>
					</c:if>
					<c:if test="${empty status.errorCodes}">&nbsp;</c:if>
				</spring:bind>
			</div>
			
			<div class="subLeft2">
	   <a href="http://www.jmtop.com" target="_blank" title="JM International">JM International</a>
			</div>
	   </div>
	   

  </form:form>

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


/****
  	手机验证码
 */

var InterValObj; //timer变量，控制时间
var count = 120; //间隔函数，1秒执行
var curCount;//当前剩余秒数
var code = ""; //验证码
var codeLength = 6;//验证码长度

function sendMessage() {
	curCount = count;
		//产生验证码
	for (var i = 0; i < codeLength; i++) {
		code += parseInt(Math.random() * 9).toString();
	}
	//设置button效果，开始计时
	//$("#btnSendCode").attr("disabled", "true");
	//$("#btnSendCode").html("请在" + curCount + "秒内输入验证码");
	document.getElementById("btnSendCode").disabled=true;
	document.getElementById("btnSendCode").innerHTML="请在" + curCount + "秒内输入验证码";
	InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
}
//timer处理函数
function SetRemainTime() {
	if (curCount == 0) {
		window.clearInterval(InterValObj);//停止计时器
		//$("#btnSendCode").removeAttr("disabled");//启用按钮
		//$("#btnSendCode").html("重新发送验证码");
		document.getElementById("btnSendCode").disabled=false;
		document.getElementById("btnSendCode").innerHTML="重新发送验证码";
		code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效 
	} else {
		curCount--;
		document.getElementById("btnSendCode").innerHTML="请在" + curCount + "秒内输入验证码";
	}
}

function sendSms() {
	var userCode =document.getElementById("userCode").value;
	var password =document.getElementById("password").value;
    var result=true; 
	if(userCode==""){
		alert("<jecs:locale key="sysUser.userCode.required"/>");
		
		result= false;
	}
	if(password==""){
		alert("<jecs:locale key="sysUser.password.required"/>");
		
		result= false;
	}
	if(result){
		sendMessage();
		jmiSmsNoteManager.sendSms(userCode,password, callBack);
	}
	
	 
}

function callBack(valid) {
	if (valid == '') {
	} else {
		curCount=0;
		alert(valid);
	}

}

function ckGzh() {
	submitFormCommit(jmiMemberform);
}



</script>
<div style="display:none">
<img src="images/login/joinButtonBgOver.jpg"/>
<img src="images/login/loginResetBgOver.jpg"/>
<img src="images/login/loginSubBgOver.jpg"/>
<img src="images/login/joinButtonBg.jpg"/>
<img src="images/login/loginResetBg.jpg"/>
<img src="images/login/loginSubBg.jpg"/>
</div>
    <c:if test="${not empty sessionScope.active_success }">
	    <script type="text/javascript">
	    	alert('${sessionScope.active_success}');
	    </script>
		<c:remove var="active_success" scope="session" />
    </c:if>
</body>
</html>
