<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<script language="JavaScript">
var param = window.dialogArguments;
document.writeln("<head>");
document.writeln("<meta HTTP-EQUIV=\"Content-Type\" CONTENT=\"text/html; charset=gb2312\">");
document.writeln("<title>");
document.writeln(param[0]);
document.writeln("</title>");
document.writeln("</head>");
document.writeln("<frameset rows=\"0,*\">");
document.writeln("    <frame name=\"dialog_header\" scrolling=\"no\" noresize target=\"main\" src=\"about:blank\">");
document.writeln("    <frame name=\"dialog_template\" scrolling=\""+param[3]+"\" noresize target=\"_self\" src=\""+param[1]+"\">");
document.writeln("    <noframes>");
document.writeln("    <body>");

document.writeln("    <p>此网页使用了框架，但您的浏览器不支持框架。</p>");

document.writeln("    </body>");
document.writeln("    </noframes>");
document.writeln("</frameset>");


</script>
</html>