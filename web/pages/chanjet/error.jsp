<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page isErrorPage="true"%>
<html>
  <head>
    <title>错误页面</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link rel="stylesheet" href="../common/css/public_css_ussys.css" type="text/css">
  </head>
  <body>
    <div id="HEADA"><jsp:include page="./head.jsp"/></div>
    <div id="MAINA">
      <div class="mindexa">
        <div class="mleft">
          <jsp:include page="./left.jsp"/>
        </div>
        <div class="mright">
          <h3>错误信息</h3>
          <div class="mrmain">
		  	<div align="left" style="color:#CC3300; font-weight:bold; font-size:14px; margin-left:10px;"><%=exception.getMessage()%></div>
		  	<div align="left" style="color:#999999; font-size:14px; margin-left:10px;">
<%
    java.io.CharArrayWriter cw = new java.io.CharArrayWriter();
    java.io.PrintWriter pw = new java.io.PrintWriter(cw,true);
    exception.printStackTrace(pw);
	String exStr = cw.toString().replaceAll("\n","<br/>");
    out.println(exStr);
%>
			</div>
			<div class="pbutton2 pline"><input type="button" value="返回" class="button" onclick="history.back();"/></div>
          </div>
        </div>
      </div>
    </div>
    <div id="FOOTA"><jsp:include page="./bottom.jsp"/></div>
  </body>
</html>
<script>
document.getElementById("merCancel").className="msel";
</script>